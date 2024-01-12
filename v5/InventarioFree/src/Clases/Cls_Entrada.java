package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Entrada {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ENTRADA = "INSERT INTO entrada (ent_categoria, ent_pro_codigo, ent_fecha, ent_tarima, ent_cajas, res_cuerpo, res_reja, res_tapa, cuerpo_merma, reja_merma, tapa_merma, sob_cuerpo, sob_reja, sob_tapa) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT_ENTRADA = "SELECT ent_categoria, ent_fecha, ent_pro_codigo, pro_descripcion, nomproveedor, valor, ent_tarima, ent_cajas, res_cuerpo, res_reja, res_tapa, cuerpo_merma, reja_merma, tapa_merma FROM entrada INNER JOIN artículos ON ent_pro_codigo = pro_codigo INNER JOIN categorias ON id = categoria ORDER BY ent_fecha DESC";
    private final String SQL_SELECT_CATEGORIA= "SELECT valor, cajaTarima FROM artículos INNER JOIN categorias ON id = categoria WHERE pro_codigo = ?";
    private final String SQL_SELECT_ID= "SELECT MAX(ent_id) FROM entrada ";
    
    public Cls_Entrada() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setTitulosEntrada() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DT.addColumn("Folio de entrada");
        DT.addColumn("Fecha");
        DT.addColumn("Folio de lote");
        DT.addColumn("Descripción");
        DT.addColumn("Proveedor");
        DT.addColumn("Categoria");
        DT.addColumn("Tarimas");
        DT.addColumn("Cajas");
        DT.addColumn("Retiro de Cuerpos");
        DT.addColumn("Retiro de Divisores");
        DT.addColumn("Retiro de Tapa");
        DT.addColumn("Merma Cuerpo");
        DT.addColumn("Merma Divisor");
        DT.addColumn("Merma Tapa");
        return DT;
    }

    public DefaultTableModel getDatosEntradas() {
        try {
            setTitulosEntrada();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ENTRADA);
            RS = PS.executeQuery();
            Object[] fila = new Object[14];////////////////////
            while (RS.next()) {                         
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);//////
                fila[5] = RS.getString(6);///////
                fila[6] = RS.getInt(7);
                fila[7] = RS.getInt(8);
                fila[8] = RS.getInt(9);
                fila[9] = RS.getInt(10);
                fila[10] = RS.getInt(11);
                fila[11] = RS.getInt(12);
                fila[12] = RS.getInt(13);
                fila[13] = RS.getInt(14);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
    public String generarFolio(String identificador, Date fecha) {
        String folio = "";
        try {
            PS = CN.getConnection().prepareStatement(SQL_SELECT_CATEGORIA);
            PS.setString(1, identificador);
            RS = PS.executeQuery();
            if (RS.next()) {
                String tarima = RS.getString("cajaTarima");
                String categoria = RS.getString("valor");
        
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String fechaStr = sdf.format(fecha);
                
                folio = numeroUnico() + tarima + fechaStr + categoria;  
            } else {
                throw new Exception("Código no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folio;
    }
    
    public int numeroUnico(){
        int id = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ID);
            RS = PS.executeQuery();
            
            if(RS.next()){
                id = RS.getInt("MAX(ent_id)");
                id++;
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public int registrarEntrada(String categoria, String codigo, Date fecha, int tarima, int caja,int rescuerpo, int resreja, int restapa, int cuerpomerma,int rejamerma, int tapamerma) {
    int res = 0;
    try {
        PS = CN.getConnection().prepareStatement(SQL_INSERT_ENTRADA);
        PS.setString(1, categoria);
        PS.setString(2, codigo);
        PS.setDate(3, fecha);
        PS.setInt(4, tarima);
        PS.setInt(5, caja);
        PS.setInt(6, rescuerpo);
        PS.setInt(7, resreja);
        PS.setInt(8, restapa);
        PS.setInt(9, cuerpomerma);
        PS.setInt(10, rejamerma);
        PS.setInt(11, tapamerma);
        PS.setInt(12, 0);
        PS.setInt(13, 0);
        PS.setInt(14, 0);
        res = PS.executeUpdate();
        if (res > 0) {
                String GET_SUMA = "SELECT SUM(ent_cajas) AS suma_ent_cantidad FROM entrada WHERE ent_pro_codigo = ?;";
                PS = CN.getConnection().prepareStatement(GET_SUMA);
                PS.setString(1, codigo);
                RS = PS.executeQuery();
                if (RS.next()) {
                    int sumaTotal = RS.getInt("suma_ent_cantidad");
                    System.out.println("Codigo: " + codigo);
                    System.out.println("Suma total de: " + sumaTotal);
                    //
                    // Esta seccion que actualiza el stock
                    int res2 = 0;
                    String UPDATE_INV_ENTRADAS = "UPDATE inventario SET inv_entradas = ? WHERE inv_pro_codigo = ?";
                    PS = CN.getConnection().prepareStatement(UPDATE_INV_ENTRADAS);
                    PS.setInt(1, sumaTotal);
                    PS.setString(2, codigo);

                    res2 = PS.executeUpdate();
                    if (res2 > 0) {
                        System.out.println("Tabla de entradas act.");

                        int res3 = 0;
                        String UPDATE_INV_STOCK = "UPDATE inventario SET inv_stock = inv_entradas - inv_salidas WHERE inv_pro_codigo = ?";
                        PS =    CN.getConnection().prepareStatement(UPDATE_INV_STOCK);
                        PS.setString(1, codigo);
                        res3 = PS.executeUpdate();

                        if (res3 > 0) {
                            System.out.println("Update Inventario");

                            // Actualiza el cuerpo
                            String UPDATE_ART_CUERPO = "UPDATE artículos SET cuerpo = cuerpo + ? WHERE pro_codigo = ?";
                            PS = CN.getConnection().prepareStatement(UPDATE_ART_CUERPO);
                            PS.setInt(1, rescuerpo - cuerpomerma);  
                            PS.setString(2, codigo);
                            int res4 = PS.executeUpdate();

                            if (res4 > 0) {
                                System.out.println("Campo cuerpo de la tabla de artículos actualizada");
                            } else {
                                System.out.println("Error al actualizar el campo cuerpo de la tabla de artículos");
                            }

                            // Actualiza la reja
                            String UPDATE_ART_REJA = "UPDATE artículos SET reja = reja + ? WHERE pro_codigo = ?";
                            PS = CN.getConnection().prepareStatement(UPDATE_ART_REJA);
                            PS.setInt(1, resreja - rejamerma);  
                            PS.setString(2, codigo);
                            int res5 = PS.executeUpdate();

                            if (res5 > 0) {
                                System.out.println("Campo reja de la tabla de artículos actualizada");
                            } else {
                                System.out.println("Error al actualizar el campo reja de la tabla de artículos");
                            }

                            // Actualiza la tapa
                            String UPDATE_ART_TAPA = "UPDATE artículos SET tapa = tapa + ? WHERE pro_codigo = ?";
                            PS = CN.getConnection().prepareStatement(UPDATE_ART_TAPA);
                            PS.setInt(1, restapa - tapamerma);  
                            PS.setString(2, codigo);
                            int res6 = PS.executeUpdate();

                            if (res6 > 0) {
                                System.out.println("Campo tapa de la tabla de artículos actualizada");
                            } else {
                                System.out.println("Error al actualizar el campo tapa de la tabla de artículos");
                            }

                            // Después de actualizar las cantidades, consulta las cantidades actuales.
                            String QUERY_PIEZAS_ACTUALES = "SELECT res_cuerpo-cuerpo_merma as cuerpo, res_reja-reja_merma as reja, res_tapa-tapa_merma as tapa, cajaTarima FROM entrada INNER JOIN artículos ON ent_pro_codigo = pro_codigo INNER JOIN categorias ON categoria = id WHERE ent_categoria = ?";
                            PS = CN.getConnection().prepareStatement(QUERY_PIEZAS_ACTUALES);
                            PS.setString(1, categoria);
                            RS = PS.executeQuery();

                            int cuerposDisponibles = 0;
                            int rejasDisponibles = 0;
                            int tapasDisponibles = 0;
                            int tamañoTarima = 0;

                            if (RS.next()) {
                                cuerposDisponibles = RS.getInt("cuerpo");
                                rejasDisponibles = RS.getInt("reja");
                                tapasDisponibles = RS.getInt("tapa");
                                tamañoTarima = RS.getInt("cajaTarima");
                            }

                            // Calcula cuántas cajas completas puedes formar con las piezas disponibles.
                            int cajasCompletas = Math.min(Math.min(cuerposDisponibles, rejasDisponibles), (tapasDisponibles/2));
                            
                            cuerposDisponibles -= cajasCompletas;
                            rejasDisponibles -= cajasCompletas;
                            tapasDisponibles -= (cajasCompletas*2);
                            
                            System.out.println("Número de cajas completas que se pueden formar: " + cajasCompletas);
                            
                            // Calcula cuantas tarimas seran dependiendo de la cantidad de cajas y la categoria
                            int tarimasCompletas = cajasCompletas/tamañoTarima;
                            int cajasSobrantes = cajasCompletas-(tamañoTarima*tarimasCompletas);
                                    
                            // Actualiza el número de cajas completas en la tabla "entradas".
                            String UPDATE_ENTRADAS = "UPDATE entrada SET ent_tarima = ?, ent_cajas = ?, sob_cuerpo = ?, sob_reja = ?, sob_tapa = ? WHERE ent_categoria = ?";
                            PS = CN.getConnection().prepareStatement(UPDATE_ENTRADAS);
                            PS.setInt(1, tarimasCompletas);
                            PS.setInt(2, cajasSobrantes);
                            PS.setInt(3, cuerposDisponibles);
                            PS.setInt(4, rejasDisponibles);
                            PS.setInt(5, tapasDisponibles);
                            PS.setString(6, categoria);
                            int resUpdate = PS.executeUpdate();

                            if (resUpdate > 0) {
                                System.out.println("Número de cajas actualizado correctamente en la tabla de entradas.");
                            } else {
                                System.out.println("Error al actualizar el número de cajas en la tabla de entradas.");
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Entrada realizada con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la entrada.");
            System.err.println("Error al registrar la entrada." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}
