package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//testo
public class Cls_Entrada {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ENTRADA = "INSERT INTO entrada (ent_categoria, ent_pro_codigo, ent_fecha, ent_cantidad, res_cuerpo, res_reja, res_tapa, cuerpo_merma, reja_merma, tapa_merma) values (?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT_ENTRADA = "SELECT ent_categoria, ent_fecha, ent_pro_codigo, pro_descripcion, nomproveedor, categoria , ent_cantidad, res_cuerpo, res_reja, res_tapa, cuerpo_merma, reja_merma, tapa_merma FROM entrada INNER JOIN artículos ON ent_pro_codigo = pro_codigo";

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
            Object[] fila = new Object[13];////////////////////
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
    
    

    public int registrarEntrada(String categoria, String codigo, Date fecha, int cantidad,int rescuerpo, int resreja, int restapa, int cuerpomerma,int rejamerma, int tapamerma) {
    int res = 0;
    try {
        PS = CN.getConnection().prepareStatement(SQL_INSERT_ENTRADA);
        PS.setString(1, categoria);
        PS.setString(2, codigo);
        PS.setDate(3, fecha);
        PS.setInt(4, cantidad);
        PS.setInt(5, rescuerpo);
        PS.setInt(6, resreja);
        PS.setInt(7, restapa);
        PS.setInt(8, cuerpomerma);
        PS.setInt(9, rejamerma);
        PS.setInt(10, tapamerma);
        res = PS.executeUpdate();
        if (res > 0) {

                String GET_SUMA = "SELECT SUM(ent_cantidad) AS suma_ent_cantidad FROM entrada WHERE ent_pro_codigo = ?;";
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
int totalCuerpo = rescuerpo + cuerpomerma;
String UPDATE_ART_CUERPO = "UPDATE artículos SET cuerpo = cuerpo - ? WHERE pro_codigo = ?";
PS = CN.getConnection().prepareStatement(UPDATE_ART_CUERPO);
PS.setInt(1, totalCuerpo);  
PS.setString(2, codigo);
int res4 = PS.executeUpdate();

if (res4 > 0) {
    System.out.println("Campo cuerpo de la tabla de artículos actualizada");
} else {
    System.out.println("Error al actualizar el campo cuerpo de la tabla de artículos");
}

// Actualiza la reja
int totalReja = resreja + rejamerma;
String UPDATE_ART_REJA = "UPDATE artículos SET reja = reja - ? WHERE pro_codigo = ?";
PS = CN.getConnection().prepareStatement(UPDATE_ART_REJA);
PS.setInt(1, totalReja);  
PS.setString(2, codigo);
int res5 = PS.executeUpdate();

if (res5 > 0) {
    System.out.println("Campo reja de la tabla de artículos actualizada");
} else {
    System.out.println("Error al actualizar el campo reja de la tabla de artículos");
}

// Actualiza la tapa
int totalTapa = restapa + tapamerma;
String UPDATE_ART_TAPA = "UPDATE artículos SET tapa = tapa - ? WHERE pro_codigo = ?";
PS = CN.getConnection().prepareStatement(UPDATE_ART_TAPA);
PS.setInt(1, totalTapa);  
PS.setString(2, codigo);
int res6 = PS.executeUpdate();

if (res6 > 0) {
    System.out.println("Campo tapa de la tabla de artículos actualizada");
} else {
    System.out.println("Error al actualizar el campo tapa de la tabla de artículos");
}
                            
                            // Después de actualizar las cantidades, consulta las cantidades actuales.
String QUERY_PIEZAS_ACTUALES = "SELECT cuerpo, reja, tapa FROM artículos WHERE pro_codigo = ?";
PS = CN.getConnection().prepareStatement(QUERY_PIEZAS_ACTUALES);
PS.setString(1, codigo);
RS = PS.executeQuery();

int cuerposDisponibles = 0;
int rejasDisponibles = 0;
int tapasDisponibles = 0;

if (RS.next()) {
    cuerposDisponibles = RS.getInt("cuerpo");
    rejasDisponibles = RS.getInt("reja");
    tapasDisponibles = RS.getInt("tapa");
}

// Calcula cuántas cajas completas puedes formar con las piezas disponibles.
int cajasCompletas = Math.min(Math.min(cuerposDisponibles, rejasDisponibles), tapasDisponibles);

System.out.println("Número de cajas completas que se pueden formar: " + cajasCompletas);

// Actualiza el número de cajas completas en la tabla "entradas".
String UPDATE_ENTRADAS = "UPDATE entrada SET ent_cantidad = ? WHERE ent_pro_codigo = ?";
PS = CN.getConnection().prepareStatement(UPDATE_ENTRADAS);
PS.setInt(1, cajasCompletas);
PS.setString(2, codigo);
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
