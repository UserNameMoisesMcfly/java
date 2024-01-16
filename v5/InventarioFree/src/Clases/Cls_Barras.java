package Clases;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Cls_Barras {

    public BufferedImage generateBarcode(String code) throws IOException {
        Code128Bean barcode128 = new Code128Bean();
        final int dpi = 160;

        // Configure the barcode generator
        // Aquí puedes configurar tu código de barras, como su altura, resolución, etc.

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                baos, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcode128.generateBarcode(canvas, code);
        canvas.finish();

        // Convert to BufferedImage
        return canvas.getBufferedImage();
    }
}
