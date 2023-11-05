package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Salida {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_SALIDA = "INSERT INTO salida (sal_folio, sal_fecha, sal_entId, sal_descripcion, sal_mermaCaja, sal_tarimas) values (?,?,?,?,?,?)";
    private final String SQL_SELECT_SALIDA = "SELECT sal_folio, sal_fecha, ent_categoria, sal_descripcion, nomproveedor, sal_mermaCaja, sal_tarimas FROM salida " + "INNER JOIN entrada ON sal_entId = ent_id INNER JOIN artículos ON ent_pro_codigo = pro_codigo ORDER BY sal_fecha DESC";
    private final String SQL_SELECT_CATEGORIA= "SELECT categoria FROM artículos INNER JOIN entrada ON ent_pro_codigo = pro_codigo WHERE ent_categoria = ?";
    private final String SQL_SELECT_ID= "SELECT MAX(sal_id) FROM salida ";
    
    public Cls_Salida() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setTitulosSalida() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        DT.addColumn("Folio de Salida");
        DT.addColumn("Fecha");
        DT.addColumn("Folio de Entrada");
        DT.addColumn("Descripción");
        DT.addColumn("Proveedor");
        DT.addColumn("Merma Cajas");
        DT.addColumn("Tarimas");
        return DT;
    }

    public DefaultTableModel getDatosSalida() {
        try {
            setTitulosSalida();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_SALIDA);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];////////////
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);//////////
                fila[5] = RS.getInt(6);/////////
                fila[6] = RS.getInt(7);
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
                int categoria = RS.getInt("categoria");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String fechaStr = sdf.format(fecha);
                folio = numeroUnico() + fechaStr + categoria;  
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
                id = RS.getInt("MAX(sal_id)");
                id++;
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    
    public int obtenerId(String entId){
        int id = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT ent_id FROM entrada WHERE ent_categoria = " + entId);
            RS = PS.executeQuery();
            
            if(RS.next()){
                id = RS.getInt("ent_id");
                id++;
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    
    public int restaMerma(String entID, int merma){
        int id = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT categoria, CASE WHEN categoria = 20 THEN (entrada.ent_cantidad - " + merma + ") / 207 WHEN categoria = 40 THEN (entrada.ent_cantidad - " + merma + ") / 90 ELSE 0 END AS calculo FROM artículos INNER JOIN inventario ON pro_codigo = inv_pro_codigo LEFT JOIN entrada ON artículos.pro_codigo = entrada.ent_pro_codigo WHERE ent_categoria =" + entID );
            RS = PS.executeQuery();
            if(RS.next()){
                id = (int) Math.round(RS.getDouble("calculo"));
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public int registrarSalida(String folio, Date fecha, int entId, String descripcion,  int mermacaja,  int tarimas) {
        int res = 0;

        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_SALIDA);
            PS.setString(1, folio);
            PS.setDate(2, fecha);
            PS.setInt(3, entId);
            PS.setString(4, descripcion);
            PS.setInt(5, mermacaja);
            PS.setInt(6, tarimas);
            res = PS.executeUpdate();
            if (res > 0) {

                String GET_SUMA = "SELECT SUM(sal_tarimas) AS suma_sal_cantidad FROM salida WHERE sal_pro_codigo = ?;";
                PS = CN.getConnection().prepareStatement(GET_SUMA);
                PS.setString(1, folio);
                RS = PS.executeQuery();
                if (RS.next()) {
                    int sumaTotal = RS.getInt("suma_sal_cantidad");
                    System.out.println("Codigo: " + folio);
                    System.out.println("Suma total de: " + sumaTotal);
                    //////// 00000000
                    int res2 = 0;
                    String UPDATE_INV_SALIDAS = "UPDATE inventario SET inv_salidas = ? WHERE inv_pro_codigo = ?";
                    PS = CN.getConnection().prepareStatement(UPDATE_INV_SALIDAS);
                    PS.setInt(1, sumaTotal);
                    PS.setString(2, folio);

                    res2 = PS.executeUpdate();
                    if (res2 > 0) {
                        System.out.println("Tabla de entradas act.");

                        int res3 = 0;
                        String UPDATE_INV_STOCK = "UPDATE inventario SET inv_stock = inv_entradas - inv_salidas WHERE inv_pro_codigo = ?";
                        PS = CN.getConnection().prepareStatement(UPDATE_INV_STOCK);
                        PS.setString(1, folio);
                        res3 = PS.executeUpdate();

                        if (res3 > 0) {
                            System.out.println("Update Inventario");
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Salida realizada con éxito.");
                alerta(folio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la salida.");
            System.err.println("Error al registrar la salida." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int verificarStock(String codigo) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT inv_stock from inventario where inv_pro_codigo='" + codigo + "'");
            RS = PS.executeQuery();

            while (RS.next()) {
                res = RS.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public void alerta(String codigo) {////////
        int ress = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT inv_stock from inventario where inv_pro_codigo='" + codigo + "'");
            RS = PS.executeQuery();

            while (RS.next()) {
                ress = RS.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }

        //en caso de querer poner una alerta por minimo de stock
        if (ress < 50) {
            JOptionPane.showMessageDialog(null, "El stock de este producto es menor a 50!! se requiere pedir más");
        }

    }//////////////////////////
}