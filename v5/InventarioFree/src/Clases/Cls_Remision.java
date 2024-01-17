package Clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class Cls_Remision {

    public void crearPDF(String origen, String noPro, String almacen, String pedido, String destino, String cliente, String linea , String autoriza, String transporte, String placas, String operador, String solicita, String rutaDestino) {
        try {
            Document document = new Document(PageSize.LETTER);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaDestino));
            document.open();

            // Agregar imagen de fondo
            Image image = Image.getInstance("src/Remisiones/remision.jpg");
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());
            document.add(image);

            // Agregar texto
            document.add(new Paragraph("Origen: " + origen));
            document.add(new Paragraph("No. Proveedor: " + noPro));
            document.add(new Paragraph("Almacen: " + almacen));
            document.add(new Paragraph("Pedido: " + pedido));
            document.add(new Paragraph("Destino: " + destino));
            document.add(new Paragraph("Cliente: " + cliente));
            document.add(new Paragraph("Linea: " + linea));
            document.add(new Paragraph("Autoriza: " + autoriza));
            document.add(new Paragraph("Transporte: " + transporte));
            document.add(new Paragraph("Placas: " + placas));
            document.add(new Paragraph("Operador: " + operador));
            document.add(new Paragraph("Solicita Cliente: " + solicita));
            // ... agregar el resto de los campos

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
