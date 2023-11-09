package Clases;

import Conexion.Conectar;
import java.awt.Desktop;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Class_Excel {
    
    private Conectar CN;
    
    public Class_Excel() {
        CN = new Conectar();
    }

    public void exportarAExcel(String proCodigo, String rutaArchivo) throws IOException {
        String query = "SELECT a.pro_codigo, a.pro_descripcion, a.nomproveedor, a.categoria, a.ubicacion, a.cuerpo, a.reja, a.tapa, " +
                       "e.ent_categoria, e.ent_fecha, e.ent_cantidad, e.res_cuerpo, e.res_reja, e.res_tapa, e.cuerpo_merma, e.reja_merma, e.tapa_merma " +
                       "FROM artículos a " +
                       "LEFT JOIN entrada e ON a.pro_codigo = e.ent_pro_codigo " +
                       "WHERE a.pro_codigo = ?";
        try (Connection conn = CN.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
             
            pst.setString(1, proCodigo);
            ResultSet rs = pst.executeQuery();
            
            Workbook libro = new HSSFWorkbook();
            Sheet hoja = libro.createSheet("Datos");

            // Añadir los títulos de las columnas
            String[] titulos = {
                "Código Producto", "Descripción", "Nombre Proveedor", "Categoría", "Ubicación", "Cuerpo", "Reja", "Tapa",
                "Categoría Entrada", "Fecha Entrada", "Cantidad Entrada", "Cuerpo Res", "Reja Res", "Tapa Res", "Cuerpo Merma", "Reja Merma", "Tapa Merma"
            };
            
            Row filaTitulo = hoja.createRow(0);
            for (int i = 0; i < titulos.length; i++) {
                Cell celdaTitulo = filaTitulo.createCell(i);
                celdaTitulo.setCellValue(titulos[i]);
            }
            
            int indiceFila = 1;
            while (rs.next()) {
                Row fila = hoja.createRow(indiceFila);

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    Cell celda = fila.createCell(i - 1);
                    if (rs.getObject(i) != null) {
                        celda.setCellValue(rs.getObject(i).toString());
                    } else {
                        celda.setCellValue("");
                    }
                }
                
                indiceFila++;
            }
            
            // Escribir el libro en un archivo
            try (FileOutputStream archivo = new FileOutputStream(new File(rutaArchivo))) {
                libro.write(archivo);
                Desktop.getDesktop().open(new File(rutaArchivo));
            } catch (IOException e) {
                throw e;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
