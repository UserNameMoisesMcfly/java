package Clases;

import Conexion.Conectar;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Salida {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_SALIDA = "INSERT INTO salida (sal_factura, sal_pro_codigo, sal_fecha, sal_cantidad) values (?,?,?,?)";
    private final String SQL_SELECT_SALIDA = "SELECT sal_factura, sal_fecha, sal_pro_codigo, pro_descripcion, nomproveedor, categoria , sal_cantidad FROM salida INNER JOIN artículos ON sal_pro_codigo = pro_codigo";

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
        DT.addColumn("Número de salida");
        DT.addColumn("Fecha");
        DT.addColumn("Código de Artículo");
        DT.addColumn("Descripción");
        DT.addColumn("Proveedor");////////////////////////////
        DT.addColumn("Categoria");////////////////////////
        //DT.addColumn("Precio");////////////////
        DT.addColumn("Cantidad");
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
                fila[5] = RS.getString(6);/////////
                // fila[6] = RS.getString(7);
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

    public int registrarSalida(String categoria, String codigo, Date fecha, int cantidad) {
        int res = 0;

        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_SALIDA);
            PS.setString(1, categoria);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            res = PS.executeUpdate();
            if (res > 0) {

                String GET_SUMA = "SELECT SUM(sal_cantidad) AS suma_sal_cantidad FROM salida WHERE sal_pro_codigo = ?;";
                PS = CN.getConnection().prepareStatement(GET_SUMA);
                PS.setString(1, codigo);
                RS = PS.executeQuery();
                if (RS.next()) {
                    int sumaTotal = RS.getInt("suma_sal_cantidad");
                    System.out.println("Codigo: " + codigo);
                    System.out.println("Suma total de: " + sumaTotal);
                    //////// 00000000
                    int res2 = 0;
                    String UPDATE_INV_SALIDAS = "UPDATE inventario SET inv_salidas = ? WHERE inv_pro_codigo = ?";
                    PS = CN.getConnection().prepareStatement(UPDATE_INV_SALIDAS);
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

                JOptionPane.showMessageDialog(null, "Salida realizada con éxito.");
                alerta(codigo);//////////
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