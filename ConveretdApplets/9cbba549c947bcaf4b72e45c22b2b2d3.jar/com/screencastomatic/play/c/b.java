// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.c;

import java.awt.RenderingHints;
import java.awt.image.Kernel;
import java.awt.image.ConvolveOp;
import java.awt.Graphics2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;
import java.io.InputStream;
import javax.imageio.ImageReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class b
{
    public static BufferedImage a(final String s) {
        try {
            final InputStream resourceAsStream = b.class.getResourceAsStream("/com/screencastomatic/play/images/" + s);
            ImageIO.setUseCache(false);
            final Iterator<ImageReader> imageReadersBySuffix = ImageIO.getImageReadersBySuffix("GIF");
            if (!imageReadersBySuffix.hasNext()) {
                throw new IllegalStateException("No readers found for type gif");
            }
            final ImageReader imageReader = imageReadersBySuffix.next();
            imageReader.setInput(ImageIO.createImageInputStream(resourceAsStream));
            return imageReader.read(0);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Failed to read gif with name: " + s, ex);
        }
    }
    
    public static BufferedImage b(final String s) {
        return a("png", s);
    }
    
    public static BufferedImage a(final String s, final String s2) {
        try {
            final InputStream resourceAsStream = b.class.getResourceAsStream("/com/screencastomatic/play/images/" + s2);
            ImageIO.setUseCache(false);
            final Iterator<ImageReader> imageReadersBySuffix = ImageIO.getImageReadersBySuffix(s);
            if (!imageReadersBySuffix.hasNext()) {
                throw new IllegalStateException("No readers found for type " + s);
            }
            final ImageReader imageReader = imageReadersBySuffix.next();
            imageReader.setInput(ImageIO.createImageInputStream(resourceAsStream));
            return imageReader.read(0);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Failed to read " + s + " from resource: " + s2, ex);
        }
    }
    
    public static Image a(final Image image, final Color color) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new a(color)));
    }
    
    public static Dimension a(final BufferedImage bufferedImage, final int n) {
        return new Dimension(bufferedImage.getWidth() + 4 * n, bufferedImage.getHeight() + 4 * n);
    }
    
    public static Point a(final Point point, final int n) {
        return new Point(point.x - n * 2, point.y - n * 2);
    }
    
    public static BufferedImage b(final BufferedImage bufferedImage, final int n) {
        final Dimension a = a(bufferedImage, n);
        final BufferedImage bufferedImage2 = new BufferedImage(a.width, a.height, 2);
        final Graphics2D graphics = bufferedImage2.createGraphics();
        graphics.drawImage(bufferedImage, n * 2, n * 2, null);
        graphics.setComposite(AlphaComposite.SrcIn);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, bufferedImage2.getWidth(), bufferedImage2.getHeight());
        graphics.dispose();
        return a(n, false).filter(a(n, true).filter(bufferedImage2, null), null);
    }
    
    public static ConvolveOp a(final int n, final boolean b) {
        if (n < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        final int n2 = n * 2 + 1;
        final float[] array = new float[n2];
        final float n3 = n / 3.0f;
        final float n4 = 2.0f * n3 * n3;
        final float n5 = (float)Math.sqrt(n4 * 3.141592653589793);
        float n6 = 0.0f;
        for (int i = -n; i <= n; ++i) {
            final float n7 = i * i;
            final int n8 = i + n;
            array[n8] = (float)Math.exp(-n7 / n4) / n5;
            n6 += array[n8];
        }
        for (int j = 0; j < array.length; ++j) {
            final float[] array2 = array;
            final int n9 = j;
            array2[n9] /= n6;
        }
        Kernel kernel;
        if (b) {
            kernel = new Kernel(n2, 1, array);
        }
        else {
            kernel = new Kernel(1, n2, array);
        }
        return new ConvolveOp(kernel, 1, null);
    }
}
