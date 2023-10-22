package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cls_Inventario {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_INVENTARIO = "SELECT inv_pro_codigo, pro_descripcion, nomproveedor, categoria, inv_entradas, inv_salidas, inv_stock, inv_cuerpo, inv_reja, inv_tapa FROM inventario INNER JOIN artículos ON inv_pro_codigo = pro_codigo";

    public Cls_Inventario() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setTitulosInventario() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        DT.addColumn("Código");
        DT.addColumn("Descripción");
        DT.addColumn("Presentacion");//////////////////////
        DT.addColumn("Categoria");/////////////////////
        // DT.addColumn("Precio");//////////////
        DT.addColumn("Entrada");
        DT.addColumn("Salida");
        DT.addColumn("Tarimas");
        DT.addColumn("Cuerpo");
        DT.addColumn("Divisor");//////////////
        DT.addColumn("Tapa");//////////////
        return DT;
    }

    public DefaultTableModel getDatosInventario() {
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_INVENTARIO);
            RS = PS.executeQuery();
            Object[] fila = new Object[10];//////////// 
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);////////////////
                fila[3] = RS.getString(4);
                //fila[4] = RS.getString(5);//////
                fila[4] = RS.getInt(5);
                fila[5] = RS.getInt(6);
                fila[6] = RS.getInt(7);
                fila[7] = RS.getInt(8);
                fila[8] = RS.getInt(9);
                fila[9] = RS.getInt(10);
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
