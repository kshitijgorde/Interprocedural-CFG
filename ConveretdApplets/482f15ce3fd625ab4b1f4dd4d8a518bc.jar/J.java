import java.awt.Graphics;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class J extends Canvas
{
    public static final byte[] a(final int[] array, final byte[] array2) throws Exception {
        return a(array, array2, 0);
    }
    
    public static final byte[] a(final int[] array, final byte[] a, final int n) throws Exception {
        final ay ay;
        (ay = new ay()).a();
        ay.b(n);
        ay.a(0.0f);
        ay.a(0);
        ay.a = a;
        ay.a(array);
        return ay.a();
    }
    
    public static final byte[] a(final Image image) throws Exception {
        final J n = new J();
        final short n2 = (short)image.getWidth(n);
        final short n3 = (short)image.getHeight(n);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final BufferedImage bufferedImage;
        final Graphics graphics;
        (graphics = (bufferedImage = new BufferedImage(n2, n3, 1)).getGraphics()).drawImage(image, 0, 0, Color.black, null);
        graphics.dispose();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
