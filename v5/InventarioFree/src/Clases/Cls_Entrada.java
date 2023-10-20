package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Entrada {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ENTRADA = "INSERT INTO entrada (ent_categoria, ent_pro_codigo, ent_fecha, ent_cantidad, cuerpo_merma, reja_merma) values (?,?,?,?,?,?)";
    private final String SQL_SELECT_ENTRADA = "SELECT ent_categoria, ent_fecha, ent_pro_codigo, pro_descripcion, nomproveedor, categoria , ent_cantidad, cuerpo_merma, reja_merma  FROM entrada INNER JOIN artículos ON ent_pro_codigo = pro_codigo";

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
        DT.addColumn("Cantidad");
        DT.addColumn("Merma Cuerpo");
        DT.addColumn("Merma Reja");
        return DT;

    }

    public DefaultTableModel getDatosEntradas() {
        try {
            setTitulosEntrada();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ENTRADA);
            RS = PS.executeQuery();
            Object[] fila = new Object[9];////////////////////
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

    public int registrarEntrada(String categoria, String codigo, Date fecha, int cantidad,int cuerpomerma,int rejamerma) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_ENTRADA);
            PS.setString(1, categoria);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setInt(5, cuerpomerma);
            PS.setInt(6, rejamerma);
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
                        PS = CN.getConnection().prepareStatement(UPDATE_INV_STOCK);
                        PS.setString(1, codigo);
                        res3 = PS.executeUpdate();

                        if (res3 > 0) {
                            System.out.println("Update Inventario");
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
