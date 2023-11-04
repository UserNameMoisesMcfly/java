package Clases;

import Conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Productos {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_PRODUCTOS = "INSERT INTO artículos (pro_codigo,pro_descripcion,nomproveedor,categoria, ubicacion, cuerpo, reja, tapa) values (?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT_PRODUCTOS = "SELECT * FROM artículos";
    Connection conn;

    public Cls_Productos() {
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
        DT.addColumn("Folio");
        DT.addColumn("Descripción");
        DT.addColumn("Marca");/////////////////
        DT.addColumn("Categoria");/////////////////
        DT.addColumn("Notas");/////////////////
        //DT.addColumn("Estado"); ////////////////////
        DT.addColumn("Cuerpo");
        DT.addColumn("Reja");
        DT.addColumn("Tapa");
        return DT;
    }

    public DefaultTableModel getDatosProductos() {
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRODUCTOS);
            RS = PS.executeQuery();
            Object[] fila = new Object[8];////////
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);/////////////////////
                fila[3] = RS.getString(4);/////////////////////
                fila[4] = RS.getString(5);/////////////////////
                fila[5] = RS.getString(6);/////////////////////
                fila[6] = RS.getString(7);/////////////////////
                fila[7] = RS.getString(8);/////////////////////
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos productos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }

public int registrarProducto(String codigo, String descripcion, String nomproveedor, String categoria, String ubicacion, String cuerpo, String reja, String tapa) {
    int res = 0;
    int numeroDeCajas = 0;
    int tarimas = 0;

    try (Connection conn = CN.getConnection()) {
        // Consultar número de cajas basado en cuerpo, reja y tapa de la tabla "articulos"
        PreparedStatement psCajas = conn.prepareStatement("SELECT LEAST(cuerpo, reja, tapa) AS numeroCajas FROM artículos WHERE pro_codigo = ?");
        psCajas.setString(1, codigo);
        ResultSet rsCajas = psCajas.executeQuery();
        if (rsCajas.next()) {
            numeroDeCajas = rsCajas.getInt("numeroCajas");
        }

        // Calcular tarimas basadas en categoría
        if ("20".equals(categoria) && numeroDeCajas > 0) {
            tarimas = numeroDeCajas / 207;
        } else if ("40".equals(categoria) && numeroDeCajas > 0) {
            tarimas = numeroDeCajas / 90;
        }

        // Registrar el producto y actualizar tarimas
        PreparedStatement PS = conn.prepareStatement(SQL_INSERT_PRODUCTOS);
        PS.setString(1, codigo);
        PS.setString(2, descripcion);
        PS.setString(3, nomproveedor);
        PS.setString(4, categoria);
        PS.setString(5, ubicacion);
        PS.setString(6, cuerpo);
        PS.setString(7, reja);
        PS.setString(8, tapa);
        //PS.setInt(9, tarimas);
        res = PS.executeUpdate();

        // Actualizar tarimas en tabla "articulos"
        //PreparedStatement psUpdateTarimas = conn.prepareStatement("UPDATE artículos SET tarimas = ? WHERE pro_codigo = ?");
        //psUpdateTarimas.setInt(1, tarimas);
        //psUpdateTarimas.setString(2, codigo);
        //psUpdateTarimas.executeUpdate();

        if (res > 0) {
            JOptionPane.showMessageDialog(null, "Articulo registrado con éxito.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo registrar el producto.");
        System.err.println("Error al registrar el producto." + e.getMessage());
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

    public int actualizarProducto(String codigo, String descripcion, String nomproveedor, String nomcategoria, String codigo_old, String nomUbicacion, String numcuerpo, String numreja, String numtapa) {//////////
        String SQL = "UPDATE artículos SET pro_codigo='" + codigo + "',pro_descripcion='" + descripcion + "',nomproveedor='" + nomproveedor + "',categoria='" + nomcategoria + "',ubicacion='" + nomUbicacion + "',cuerpo='" + numcuerpo + "',reja='" + numreja + "',tapa='" + numtapa + "' WHERE pro_codigo='" + codigo_old + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Articulo actualizado con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos" + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarProducto(String codigo) {
        String SQL = "DELETE from artículos WHERE pro_codigo ='" + codigo + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Articulo eliminado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar el articulo.");
            System.err.println("Error al eliminar producto." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public void ConsultarProveedor(JComboBox proveedor) {
        String sql = ("SELECT nombre_pro FROM proveedor");

        try {
            conn = CN.getConnection();
            PS = conn.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                proveedor.addItem(RS.getString("nombre_pro"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No fue posible mostrar los proveedores " + e.toString());
        }

    }

    public void ConsultarCategorias(JComboBox categorias) {

        String sql = ("SELECT valor, unidad FROM categorias");

        try {
            conn = CN.getConnection();
            PS = conn.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                categorias.addItem(RS.getString("valor" )/*+" "+RS.getString("unidad")*/);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No fue posible mostrar las categorias");
        }

    }

}
