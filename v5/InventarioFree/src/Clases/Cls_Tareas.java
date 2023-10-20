package Clases;

import Conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Tareas {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_PRODUCTOS = "INSERT INTO tareas (numeroSerie,nombre,herramienta,estadoTarea) values (?,?,?,?)";
    private final String SQL_SELECT_PRODUCTOS = "SELECT *FROM tareas";
    Connection conn;

    public Cls_Tareas() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setTitulosProductos() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        DT.addColumn("Numero de serie");/////////////////
        DT.addColumn("Nombre");
        DT.addColumn("Herramineta utilizada");
        DT.addColumn("Estado de la tarea");/////////////////

        // DT.addColumn("nueva columna en la tabla"); ////////////////////
        return DT;
    }

    public DefaultTableModel getDatosProductos() {
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRODUCTOS);
            RS = PS.executeQuery();
            Object[] fila = new Object[4];////////
            while (RS.next()) {
                fila[0] = RS.getString(2);
                fila[1] = RS.getString(3);
                fila[2] = RS.getString(4);/////////////////////
                fila[3] = RS.getString(5);/////////////////////
                // fila[4] = RS.getString(5);/////////////////////
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

    public int registrarProducto(String nombre, String herramineta, String numeroSerie, String estadoTarea) {/////////////////////////
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_PRODUCTOS);
            PS.setString(1, numeroSerie);
            PS.setString(2, nombre);
            PS.setString(3, herramineta);/////////////////////
            PS.setString(4, estadoTarea);////////////////////
            //PS.setString(5, ubicacion);////////////////
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Tarea registrada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la tarea");
            System.err.println("Error al registrar la tarea, " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public void insertarProductoInventario(String codigoProducto) {
        int res;
        try {
            PS = CN.getConnection().prepareStatement("CALL NUEVO_PRODUCTO('" + codigoProducto + "')");
            PS.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al insertar registro en la tabla inventario." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
    }

    public int verificarCodigoInventario(String codigo) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT count(inv_pro_codigo) from inventario where inv_pro_codigo='" + codigo + "'");
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

    public int actualizarTarea(String numeroSerie, String nombre, String herramienta, String estadoTarea, String numSerieId) {//////////
        String SQL = "UPDATE tareas SET numeroSerie='" + numeroSerie + "',nombre='" + nombre + "',herramienta='" + herramienta + "',estadoTarea='" + estadoTarea + "' WHERE numeroSerie='" + numSerieId + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Tarea actualizada con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarTarea(String codigo) {
        String SQL = "DELETE from tareas WHERE numeroSerie ='" + codigo + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Tarea eliminada con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar la tarea");
            System.err.println("Error al eliminar la tarea " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public void ConsultarEstado(JComboBox proveedor) {
        String sql = ("SELECT estadoTarea FROM tareas");

        try {
            conn = CN.getConnection();
            PS = conn.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                proveedor.addItem(RS.getString("estadoTarea"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No fue posible mostrar la  tarea " + e.toString());
        }

    }

    public void ConsultarCategorias(JComboBox categorias) {

        String sql = ("SELECT nomcategoria FROM categorias");

        try {
            conn = CN.getConnection();
            PS = conn.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                categorias.addItem(RS.getString("nomcategoria"));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No fue posible mostrar las categorias");
        }

    }

}
