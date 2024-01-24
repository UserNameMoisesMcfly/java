package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

public class Cls_Remision {

    public void crearPDF(String origen, String noPro, String almacen, String pedido, String destino, String cliente, String linea , String autoriza, String transporte, String placas, String operador, String solicita, JTable tabla, String rutaDestino) {
        /*try {
            Document document = new Document(PageSize.LETTER);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaDestino));
            document.open();

            // FORMATO EN IMAGEN
            Image image = Image.getInstance("src/Remisiones/remision.jpg");
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());
            document.add(image);
            
            // Obtener PdfContentByte
            PdfContentByte cb = writer.getDirectContent();

            // fuente y tamaño
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);

            // DATOS REMISION INICIAL           
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, origen, 150, 653, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, noPro, 150, 640, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, almacen, 150, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, pedido, 150, 614, 0);
            
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, destino, 435, 640, 0);
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, cliente, 435, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, linea, 435, 614, 0); 
            cb.endText();
           
            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n"));
            
            // DATOS DE LA TABLA   
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

            document.add(pdfTable);
            
            // DATOS REMISION FINAL       
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, autoriza, 150, 330, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, transporte, 150, 317, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, placas, 150, 304, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, operador, 150, 291, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, solicita, 150, 278, 0); 
            cb.endText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHoraActual = sdf.format(new Date());
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, fechaHoraActual, 150, 689, 0);
            
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        Document document = new Document(PageSize.LETTER);
        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaDestino));
            document.open();

            // FORMATO EN IMAGEN
            Image image = Image.getInstance("src/Remisiones/remision.jpg");
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());
            document.add(image);
            
            // Obtener PdfContentByte
            PdfContentByte cb = writer.getDirectContent();

            // fuente y tamaño
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);

            // DATOS REMISION INICIAL           
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, origen, 150, 653, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, noPro, 150, 640, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, almacen, 150, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, pedido, 150, 614, 0);
            
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, destino, 435, 640, 0);
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, cliente, 435, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, linea, 435, 614, 0); 
            cb.endText();
            
            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n"));
            
            // DATOS DE LA TABLA   
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

            document.add(pdfTable);
            
            // Calcula la altura estimada del contenido final
            float alturaTexto = 15; // Altura estimada de una línea de texto
            float espacioEntreLineas = 5; // Espacio entre líneas de texto
            int numeroDeLineasTextoFinal = 5; // Ajusta al número de líneas de texto que tienes al final
            
            float alturaDelContenidoFinal = (alturaTexto + espacioEntreLineas) * numeroDeLineasTextoFinal;
            
            // Comprueba si es necesario agregar una nueva página
            float espacioRestante = document.getPageSize().getHeight() - document.bottomMargin();
            if (espacioRestante < alturaDelContenidoFinal) {
                document.newPage();
            }

            // DATOS REMISION FINAL       
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, autoriza, 150, 330, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, transporte, 150, 317, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, placas, 150, 304, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, operador, 150, 291, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, solicita, 150, 278, 0); 
            cb.endText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHoraActual = sdf.format(new Date());
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, fechaHoraActual, 150, 689, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
