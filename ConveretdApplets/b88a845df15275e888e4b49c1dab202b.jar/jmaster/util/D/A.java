// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D;

import javax.imageio.stream.ImageOutputStreamImpl;
import java.io.FileOutputStream;
import java.io.File;
import z.B.M;
import z.B.k;
import z.B.R;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;

public class A
{
    public static GraphicsConfiguration A() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
    
    public static BufferedImage B(final Image image, final ImageObserver imageObserver) {
        return A(image, null, false, imageObserver);
    }
    
    public static BufferedImage A(final Image image, final ImageObserver imageObserver) {
        return A(image, null, true, imageObserver);
    }
    
    public static BufferedImage A(final Image image, GraphicsConfiguration a, final boolean b, final ImageObserver imageObserver) {
        if (!b && image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        final int width = image.getWidth(imageObserver);
        final int height = image.getHeight(imageObserver);
        if (a == null) {
            a = A();
        }
        final BufferedImage bufferedImage = new BufferedImage(width, height, 1);
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        graphics.drawImage(image, 0, 0, imageObserver);
        graphics.dispose();
        return bufferedImage;
    }
    
    public static void D(final BufferedImage bufferedImage, final double n, final OutputStream outputStream) throws IOException {
        B(bufferedImage, n, outputStream);
    }
    
    public static void C(BufferedImage a, final double n, final OutputStream outputStream) throws IOException {
        if (a.getType() != 1) {
            a = A(a, (ImageObserver)null);
        }
        final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(outputStream);
        final JPEGEncodeParam defaultJPEGEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(a);
        defaultJPEGEncodeParam.setQuality((float)n, true);
        jpegEncoder.setJPEGEncodeParam(defaultJPEGEncodeParam);
        jpegEncoder.encode(a);
    }
    
    public static void B(final BufferedImage bufferedImage, final double n, final OutputStream outputStream) throws IOException {
        final ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
        final ImageWriteParam defaultWriteParam = imageWriter.getDefaultWriteParam();
        defaultWriteParam.setCompressionMode(2);
        defaultWriteParam.setCompressionQuality((float)n);
        imageWriter.setOutput(new MemoryCacheImageOutputStream(outputStream));
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), defaultWriteParam);
    }
    
    public static void A(BufferedImage a, final double n, final OutputStream outputStream) throws IOException {
        if (a.getType() != 1) {
            a = A(a, (ImageObserver)null);
        }
        new k(outputStream, new R()).A(a);
    }
    
    public static void A(final BufferedImage bufferedImage, final double n, final File file) throws IOException {
        file.createNewFile();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            D(bufferedImage, n, outputStream);
        }
        finally {
            try {
                ((FileOutputStream)outputStream).close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void A(final BufferedImage bufferedImage, final double n, final String s) throws IOException {
        final File file = new File(s);
        file.createNewFile();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            D(bufferedImage, n, outputStream);
        }
        finally {
            try {
                ((FileOutputStream)outputStream).close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void B(final BufferedImage bufferedImage, final OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage, "gif", outputStream);
    }
    
    public static void A(final BufferedImage bufferedImage, final OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage, "png", outputStream);
    }
    
    protected class _A extends ImageOutputStreamImpl
    {
        OutputStream A;
        
        public void write(final int n) throws IOException {
            this.A.write(n);
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            this.A.write(array, n, n2);
        }
        
        public int read() throws IOException {
            return 0;
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            return 0;
        }
    }
}
