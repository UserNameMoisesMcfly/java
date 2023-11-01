package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import javax.swing.JTable;

public class Cls_Pdfcreator {

    public void exportarPDF(JTable tabla, String rutaArchivo) {
        try {
            Document doc = new Document(PageSize.A4, 30, 30, 30, 30); // Establece márgenes de 30 en todos los lados
            PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
            doc.open();

            // Ancho de las columnas basado en el contenido de las mismas
            float[] columnWidths = new float[tabla.getColumnCount()];
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                columnWidths[i] = 4f; // Establecer ancho de columna. Ajusta el valor si es necesario.
            }

            PdfPTable pdfTable = new PdfPTable(columnWidths);
            pdfTable.setWidthPercentage(100); // Establecer ancho relativo en la página

            // Definimos una fuente para los encabezados
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE); // Reduce el tamaño de fuente aquí
            BaseColor headerColor = new BaseColor(50, 50, 50); // Gris oscuro
            
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tabla.getColumnName(i), headerFont));
                cell.setBackgroundColor(headerColor);
                cell.setPadding(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(cell);
            }

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8); // Define un tamaño de fuente más pequeño para las celdas
            BaseColor alternateColor = new BaseColor(235, 235, 235); // Gris claro
            for (int rows = 0; rows < tabla.getRowCount(); rows++) {
                for (int cols = 0; cols < tabla.getColumnCount(); cols++) {
                    PdfPCell cell = new PdfPCell(new Phrase(tabla.getModel().getValueAt(rows, cols).toString(), cellFont));
                    cell.setPadding(5);
                    if (rows % 2 == 1) {
                        cell.setBackgroundColor(alternateColor);
                    }
                    pdfTable.addCell(cell);
                }
            }

            doc.add(pdfTable);
            doc.close();

        } catch (Exception e) {
            System.err.println("Error al exportar a PDF: " + e.getMessage());
        }
    }
}
