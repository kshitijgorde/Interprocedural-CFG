// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;

public class A
{
    private static GraphicsConfiguration B() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
    
    private static boolean A() {
        return GraphicsEnvironment.isHeadless();
    }
    
    public static BufferedImage B(final BufferedImage bufferedImage) {
        final ColorModel colorModel = bufferedImage.getColorModel();
        return new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(bufferedImage.getWidth(), bufferedImage.getHeight()), colorModel.isAlphaPremultiplied(), null);
    }
    
    public static BufferedImage A(final BufferedImage bufferedImage) {
        return B(bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight());
    }
    
    public static BufferedImage B(final BufferedImage bufferedImage, final int n, final int n2) {
        return A() ? new BufferedImage(n, n2, bufferedImage.getType()) : B().createCompatibleImage(n, n2, bufferedImage.getTransparency());
    }
    
    public static BufferedImage B(final int n, final int n2) {
        return A() ? new BufferedImage(n, n2, 1) : B().createCompatibleImage(n, n2);
    }
    
    public static BufferedImage A(final int n, final int n2) {
        return A() ? new BufferedImage(n, n2, 2) : B().createCompatibleImage(n, n2, 3);
    }
    
    public static BufferedImage A(final URL url) throws IOException {
        return C(ImageIO.read(url));
    }
    
    public static BufferedImage C(final BufferedImage bufferedImage) {
        if (A()) {
            return bufferedImage;
        }
        if (bufferedImage.getColorModel().equals(B().getColorModel())) {
            return bufferedImage;
        }
        final BufferedImage compatibleImage = B().createCompatibleImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getTransparency());
        final Graphics graphics = compatibleImage.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        return compatibleImage;
    }
    
    public static BufferedImage B(final BufferedImage bufferedImage, final int n) {
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();
        int n3;
        int n4;
        if (width > height) {
            if (n >= width) {
                throw new IllegalArgumentException("newSize must be lower than the image width");
            }
            if (n <= 0) {
                throw new IllegalArgumentException("newSize must be greater than 0");
            }
            final float n2 = width / height;
            n3 = n;
            n4 = (int)(n / n2);
        }
        else {
            if (n >= height) {
                throw new IllegalArgumentException("newSize must be lower than the image height");
            }
            if (n <= 0) {
                throw new IllegalArgumentException("newSize must be greater than 0");
            }
            final float n5 = height / width;
            n4 = n;
            n3 = (int)(n / n5);
        }
        final BufferedImage b = B(bufferedImage, n3, n4);
        final Graphics2D graphics = b.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(bufferedImage, 0, 0, b.getWidth(), b.getHeight(), null);
        graphics.dispose();
        return b;
    }
    
    public static BufferedImage C(final BufferedImage bufferedImage, final int n, final int n2) {
        if (n >= bufferedImage.getWidth() || n2 >= bufferedImage.getHeight()) {
            throw new IllegalArgumentException("newWidth and newHeight cannot be greater than the image dimensions");
        }
        if (n <= 0 || n2 <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
        }
        final BufferedImage b = B(bufferedImage, n, n2);
        final Graphics2D graphics = b.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(bufferedImage, 0, 0, b.getWidth(), b.getHeight(), null);
        graphics.dispose();
        return b;
    }
    
    public static BufferedImage A(final BufferedImage bufferedImage, final int i) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        final boolean b = bufferedImage.getTransparency() != 1;
        final boolean b2 = width > height;
        if (b2) {
            if (i >= width) {
                throw new IllegalArgumentException("newSize must be lower than the image width");
            }
        }
        else if (i >= height) {
            throw new IllegalArgumentException("newSize must be lower than the image height");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("newSize must be greater than 0");
        }
        final float n = width / height;
        final float n2 = height / width;
        BufferedImage bufferedImage2 = bufferedImage;
        BufferedImage b3 = null;
        Graphics2D graphics = null;
        int n3 = width;
        int n4 = height;
        do {
            if (b2) {
                width /= 2;
                if (width < i) {
                    width = i;
                }
                height = (int)(width / n);
            }
            else {
                height /= 2;
                if (height < i) {
                    height = i;
                }
                width = (int)(height / n2);
            }
            if (b3 == null || b) {
                if (graphics != null) {
                    graphics.dispose();
                }
                b3 = B(bufferedImage, width, height);
                graphics = b3.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            graphics.drawImage(bufferedImage2, 0, 0, width, height, 0, 0, n3, n4, null);
            n3 = width;
            n4 = height;
            bufferedImage2 = b3;
        } while (i != (b2 ? width : height));
        graphics.dispose();
        if (width != bufferedImage2.getWidth() || height != bufferedImage2.getHeight()) {
            final BufferedImage b4 = B(bufferedImage, width, height);
            final Graphics2D graphics2 = b4.createGraphics();
            graphics2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2.drawImage(bufferedImage2, 0, 0, width, height, 0, 0, width, height, null);
            graphics2.dispose();
            bufferedImage2 = b4;
        }
        return bufferedImage2;
    }
    
    public static BufferedImage A(final BufferedImage bufferedImage, final int n, final int n2) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        final boolean b = bufferedImage.getTransparency() != 1;
        if (n <= 0 || n2 <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
        }
        BufferedImage bufferedImage2 = bufferedImage;
        BufferedImage b2 = null;
        Graphics2D graphics = null;
        int n3 = width;
        int n4 = height;
        if (n >= width) {
            width = n;
        }
        if (n2 >= height) {
            height = n2;
        }
        do {
            if (width > n) {
                width /= 2;
                if (width < n) {
                    width = n;
                }
            }
            if (height > n2) {
                height /= 2;
                if (height < n2) {
                    height = n2;
                }
            }
            if (b2 == null || b) {
                if (graphics != null) {
                    graphics.dispose();
                }
                b2 = B(bufferedImage, width, height);
                graphics = b2.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }
            graphics.drawImage(bufferedImage2, 0, 0, width, height, 0, 0, n3, n4, null);
            n3 = width;
            n4 = height;
            bufferedImage2 = b2;
        } while (width != n || height != n2);
        graphics.dispose();
        if (width != bufferedImage2.getWidth() || height != bufferedImage2.getHeight()) {
            final BufferedImage b3 = B(bufferedImage, width, height);
            final Graphics2D graphics2 = b3.createGraphics();
            graphics2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2.drawImage(bufferedImage2, 0, 0, width, height, 0, 0, width, height, null);
            graphics2.dispose();
            bufferedImage2 = b3;
        }
        return bufferedImage2;
    }
    
    public static int[] A(final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, int[] array) {
        if (n3 == 0 || n4 == 0) {
            return new int[0];
        }
        if (array == null) {
            array = new int[n3 * n4];
        }
        else if (array.length < n3 * n4) {
            throw new IllegalArgumentException("pixels array must have a length >= w*h");
        }
        final int type = bufferedImage.getType();
        if (type == 2 || type == 1) {
            return (int[])bufferedImage.getRaster().getDataElements(n, n2, n3, n4, array);
        }
        return bufferedImage.getRGB(n, n2, n3, n4, array, 0, n3);
    }
    
    public static void B(final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int[] array) {
        if (array == null || n3 == 0 || n4 == 0) {
            return;
        }
        if (array.length < n3 * n4) {
            throw new IllegalArgumentException("pixels array must have a length >= w*h");
        }
        final int type = bufferedImage.getType();
        if (type == 2 || type == 1) {
            bufferedImage.getRaster().setDataElements(n, n2, n3, n4, array);
        }
        else {
            bufferedImage.setRGB(n, n2, n3, n4, array, 0, n3);
        }
    }
}
