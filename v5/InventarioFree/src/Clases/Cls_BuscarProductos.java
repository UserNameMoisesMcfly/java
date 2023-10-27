package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Cls_BuscarProductos {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_PRODUCTOS = 
    "SELECT pro_codigo, pro_descripcion, nomproveedor, categoria, cuerpo, reja, tapa, " + 
    "entrada.ent_cantidad, " + 
    "CASE " +
        "WHEN categoria = 20 THEN entrada.ent_cantidad / 207 " +
        "WHEN categoria = 40 THEN entrada.ent_cantidad / 90 " +
        "ELSE 0 " + 
    "END AS calculo, " +
    "entrada.ent_categoria " + // Agrega esta línea
    "FROM artículos " + 
    "INNER JOIN inventario ON pro_codigo = inv_pro_codigo " +
    "LEFT JOIN entrada ON artículos.pro_codigo = entrada.ent_pro_codigo " +
    "ORDER BY pro_codigo ASC";

    public Cls_BuscarProductos() {
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
        DT.addColumn("Presentacion");
        DT.addColumn("Categoria");
        DT.addColumn("Cuerpo");
        DT.addColumn("Reja");
        DT.addColumn("Tapa");
        DT.addColumn("Cajas");
        DT.addColumn("Tarimas");
        DT.addColumn("Folio de entrada");
        return DT;
    }

    public DefaultTableModel getDatosProductos() {
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PRODUCTOS);
            RS = PS.executeQuery();
            Object[] fila = new Object[10];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                fila[7] = RS.getInt(8);
                fila[8] = (int) Math.round(RS.getDouble(9)); // Modificación aquí
                fila[9] = RS.getString(10);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos buscar productos: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
    return DT;
}

    public DefaultTableModel getDatoP(int crt, String inf) {
        String SQL;
        if (crt == 2) {
            SQL = "SELECT pro_codigo, pro_descripcion, nomproveedor,categoria, cuerpo, reja, tapa, entrada.ent_cantidad, " + 
                    "CASE " +
                    "WHEN categoria = 20 THEN entrada.ent_cantidad / 207 " +
                    "WHEN categoria = 40 THEN entrada.ent_cantidad / 90 " +
                    "ELSE 0 " + 
                    "END AS calculo, " +
                    "entrada.ent_categoria " +
                    "FROM artículos INNER JOIN inventario ON pro_codigo = inv_pro_codigo LEFT JOIN entrada ON artículos.pro_codigo = entrada.ent_pro_codigo where pro_codigo like '" + inf + "'";
        } else {
            SQL = "SELECT pro_codigo, pro_descripcion, nomproveedor,categoria, cuerpo, reja, tapa, entrada.ent_cantidad, " + 
                    "CASE " +
                    "WHEN categoria = 20 THEN entrada.ent_cantidad / 207 " +
                    "WHEN categoria = 40 THEN entrada.ent_cantidad / 90 " +
                    "ELSE 0 " + 
                    "END AS calculo, " +
                    "entrada.ent_categoria " +
                    "FROM artículos INNER JOIN inventario ON pro_codigo = inv_pro_codigo LEFT JOIN entrada ON artículos.pro_codigo = entrada.ent_pro_codigo where pro_descripcion like '" + inf + "%'";
        }
        try {
            setTitulosProductos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[10]; // Aquí había un error en la cantidad de objetos, debería ser 9 en lugar de 5.
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                fila[7] = RS.getInt(8); // Aquí recuperamos ent_cantidad
                fila[8] = (int) Math.round(RS.getDouble(9)); // Aquí recuperamos el cálculo basado en la categoría
                fila[9] = RS.getString(10);
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
}
