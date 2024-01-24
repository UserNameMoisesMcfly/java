package Clases;

import java.awt.Desktop;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.swing.*;
import java.io.*;

public class Class_Excel {

    public void exportarTablaAExcel(JTable table, String rutaArchivo) throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Datos");

        // Añadir los títulos de las columnas
        Row filaTitulo = hoja.createRow(0);
        for (int i = 0; i < table.getColumnCount(); i++) {
            Cell celdaTitulo = filaTitulo.createCell(i);
            celdaTitulo.setCellValue(table.getColumnName(i));
        }

        // Añadir los datos de las filas
        for (int i = 0; i < table.getRowCount(); i++) {
            Row fila = hoja.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Cell celda = fila.createCell(j);
                if (table.getValueAt(i, j) != null) {
                    celda.setCellValue(table.getValueAt(i, j).toString());
                } else {
                    celda.setCellValue("");
                }
            }
        }

        // Escribir el libro en un archivo
        try (FileOutputStream archivo = new FileOutputStream(new File(rutaArchivo))) {
            libro.write(archivo);
            Desktop.getDesktop().open(new File(rutaArchivo));
        } catch (IOException e) {
            throw e;
        }
    }
}
