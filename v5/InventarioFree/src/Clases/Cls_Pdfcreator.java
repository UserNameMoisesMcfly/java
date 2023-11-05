package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import javax.swing.JTable;

public class Cls_Pdfcreator {
    
    class MyHeaderFooter extends PdfPageEventHelper {
        Image headerImage;
        Image footerImage;

        public MyHeaderFooter() throws Exception {
            headerImage = Image.getInstance("src/pdf/head.png");
            headerImage.scaleToFit(PageSize.A4.getWidth() - 60, 10000);

            footerImage = Image.getInstance("src/pdf/pie.png");
            footerImage.scaleToFit(PageSize.A4.getWidth() - 60, 10000);
        }

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte cb = writer.getDirectContent();
                
                headerImage.setAbsolutePosition(30, PageSize.A4.getHeight() - headerImage.getScaledHeight() - 30);
                cb.addImage(headerImage);
                
                footerImage.setAbsolutePosition(30, 30);
                cb.addImage(footerImage);
                
            } catch (Exception e) {
                System.err.println("Exception adding header/footer: " + e.getMessage());
            }
        }
    }

    public boolean exportarPDF(JTable tabla, String rutaArchivo) {
        Document doc = new Document(PageSize.A4, 30, 30, 30, 50); 
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
            writer.setPageEvent(new MyHeaderFooter());
            doc.open();

            doc.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));

            float[] columnWidths = new float[tabla.getColumnCount()];
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                columnWidths[i] = 4f;
            }

            PdfPTable pdfTable = new PdfPTable(columnWidths);
            pdfTable.setWidthPercentage(100);

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.WHITE);
            BaseColor headerColor = new BaseColor(50, 50, 50);

            for (int i = 0; i < tabla.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tabla.getColumnName(i), headerFont));
                cell.setBackgroundColor(headerColor);
                cell.setPadding(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(cell);
            }

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 6);
            BaseColor alternateColor = new BaseColor(235, 235, 235);

            int[] selectedRows;
            
            // Si no hay filas seleccionadas, selecciona todas
            if (tabla.getSelectedRows().length == 0) {
                selectedRows = new int[tabla.getRowCount()];
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    selectedRows[i] = i;
                }
            } else {
                selectedRows = tabla.getSelectedRows();
            }

            for (int row : selectedRows) {
                for (int cols = 0; cols < tabla.getColumnCount(); cols++) {
                    PdfPCell cell = new PdfPCell(new Phrase(tabla.getModel().getValueAt(row, cols).toString(), cellFont));
                    cell.setPadding(5);
                    if (row % 2 == 1) {
                        cell.setBackgroundColor(alternateColor);
                    }
                    pdfTable.addCell(cell);
                }
            }

            doc.add(pdfTable);

            PdfPTable signaturesTable = new PdfPTable(2);
            signaturesTable.setWidthPercentage(100);
            signaturesTable.setTotalWidth(doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin());
            
            PdfPCell cell1 = createSignatureCell("Sello y firma de salida RPM");
            signaturesTable.addCell(cell1);

            PdfPCell cell2 = createSignatureCell("Sello y firma del cliente");
            signaturesTable.addCell(cell2);

            float tableHeight = signaturesTable.getTotalHeight();
            signaturesTable.writeSelectedRows(0, -1, doc.left(), tableHeight + 80, writer.getDirectContent());

            doc.close();
                return true; // Retorna true al finalizar con éxito
            } catch (Exception e) {
                System.err.println("Error al exportar a PDF: " + e.getMessage());
                if(doc != null) {
                    doc.close(); // Asegúrate de cerrar el documento en caso de error también
                }
                return false; // Retorna false si ocurre una excepción
            }
}

    private PdfPCell createSignatureCell(String text) {
        PdfPCell cell = new PdfPCell();
        LineSeparator line = new LineSeparator();
        line.setPercentage(50);
        Chunk lineChunk = new Chunk(line);
        Paragraph p = new Paragraph(lineChunk);
        p.add("\n");
        p.add(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 8)));
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
