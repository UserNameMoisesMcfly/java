package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import javax.swing.JTable;

public class Cls_Pdfcreator {

    public void exportarPDF(JTable tabla, String rutaArchivo) {
        try {
            Document doc = new Document(PageSize.A4, 30, 30, 30, 30); // Establece márgenes de 30 en todos los lados
            PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
            doc.open();

            // 1. Cargar la imagen desde la ruta relativa
            Image headerImage = Image.getInstance("src/pdf/head.png");
            
            // 2. Establecer dimensiones de la imagen si es necesario
            // Por ejemplo, ajustar el ancho al de la página y dejar que el alto se ajuste proporcionalmente:
            headerImage.scaleToFit(PageSize.A4.getWidth() - 60, 10000);  // el número grande es solo para asegurar que el alto no se limite.
            
            // 3. Agregar la imagen al documento
            doc.add(headerImage);

            // Agrega un espacio vertical para posicionar la tabla en la mitad de la página
            doc.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n"));

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

            // Añadir 10 saltos de línea después de la tabla
            doc.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n"));

            // Añadir líneas para las firmas en el mismo renglón
            PdfPTable signaturesTable = new PdfPTable(2);
            signaturesTable.setWidthPercentage(100);
            signaturesTable.setWidths(new int[]{1, 1});

            PdfPCell cell1 = new PdfPCell();
            PdfPCell cell2 = new PdfPCell();

            // Ajustar la longitud de las líneas a la mitad y centrarlas
            LineSeparator line = new LineSeparator();
            line.setPercentage(50); // Ajustar al 50% de la longitud completa
            Chunk lineChunk = new Chunk(line);
            
            // Añadir líneas y texto centrados a las celdas
            Paragraph p1 = new Paragraph(lineChunk);
            p1.add("\n");
            p1.add(new Phrase("Sello y firma de salida RPM", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            p1.setAlignment(Element.ALIGN_CENTER);
            cell1.addElement(p1);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            signaturesTable.addCell(cell1);

            Paragraph p2 = new Paragraph(lineChunk);
            p2.add("\n");
            p2.add(new Phrase("Sello y firma del cliente", FontFactory.getFont(FontFactory.HELVETICA, 8)));
            p2.setAlignment(Element.ALIGN_CENTER);
            cell2.addElement(p2);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            signaturesTable.addCell(cell2);

            doc.add(signaturesTable);

            doc.close();
            
        } catch (Exception e) {
            System.err.println("Error al exportar a PDF: " + e.getMessage());
        }
    }
}