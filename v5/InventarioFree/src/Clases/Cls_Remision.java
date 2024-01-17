package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cls_Remision {

    public void crearPDF(String origen, String noPro, String almacen, String pedido, String destino, String cliente, String linea , String autoriza, String transporte, String placas, String operador, String solicita, String rutaDestino) {
        try {
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

            // DATOS REMISION            
            cb.beginText();
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, origen, 150, 653, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, noPro, 150, 640, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, almacen, 150, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, pedido, 150, 614, 0);
            
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, destino, 435, 640, 0);
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, cliente, 435, 627, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, linea, 435, 614, 0); 
            
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, autoriza, 150, 330, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, transporte, 150, 317, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, placas, 150, 304, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, operador, 150, 291, 0); 
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, solicita, 150, 278, 0); 
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHoraActual = sdf.format(new Date());
            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, fechaHoraActual, 150, 689, 0);
            
            cb.endText();

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
