// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.graphics;

import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.GraphicsConfiguration;

public final class GraphicsTools
{
    private static int[] scratch;
    private static GraphicsConfiguration config;
    
    public static void allocateScratch(final int n) {
        GraphicsTools.scratch = new int[n];
    }
    
    private static BufferedImage loadImage(final String s) {
        try {
            return ImageIO.read(GraphicsTools.class.getClassLoader().getResourceAsStream(s));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static BufferedImage loadOptimizedImage(final String s) {
        final BufferedImage loadImage;
        final BufferedImage bufferedImage = loadImage = loadImage(s);
        final int width = loadImage.getWidth();
        final int height = loadImage.getHeight();
        final int n = width;
        final BufferedImage bufferedImage2 = bufferedImage;
        if (bufferedImage.getColorModel().equals(GraphicsTools.config.getColorModel())) {
            return bufferedImage2;
        }
        final BufferedImage compatibleImage;
        final Graphics2D graphics;
        (graphics = (compatibleImage = GraphicsTools.config.createCompatibleImage(n, height, bufferedImage2.getTransparency())).createGraphics()).drawImage(bufferedImage2, 0, 0, null);
        graphics.dispose();
        return compatibleImage;
    }
    
    public static BufferedImage createOptimizedImage(final int n, final int n2, final int n3) {
        return GraphicsTools.config.createCompatibleImage(n, n2, n3);
    }
    
    public static BufferedImage createScaled(final BufferedImage bufferedImage, final int n, final int n2) {
        final BufferedImage bufferedImage2;
        final Graphics2D graphics;
        (graphics = (bufferedImage2 = new BufferedImage(n, n2, (bufferedImage.getTransparency() == 1) ? 1 : 2)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        drawScaled(graphics, bufferedImage, 0, 0, n, n2);
        return bufferedImage2;
    }
    
    private static void drawScaled(final Graphics2D graphics2D, BufferedImage bufferedImage, int width, int height, final int n, final int n2) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        if (n < width / 2 || n2 < height / 2) {
            int n3 = (n < width / 2) ? (width / 2) : ((n < width) ? n : width);
            int n4 = (n2 < height / 2) ? (height / 2) : ((n2 < height) ? n2 : height);
            final BufferedImage bufferedImage2;
            final Graphics2D graphics;
            (graphics = (bufferedImage2 = new BufferedImage(n3, n4, (bufferedImage.getTransparency() == 1) ? 1 : 2)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            bufferedImage = bufferedImage;
            width = width;
            height = height;
            do {
                graphics.drawImage(bufferedImage, 0, 0, n3, n4, 0, 0, width, height, null);
                bufferedImage = bufferedImage2;
                width = n3;
                height = n4;
                if (n3 > n && (n3 /= 2) < n) {
                    n3 = n;
                }
                if (n4 > n2 && (n4 /= 2) < n2) {
                    n4 = n2;
                }
            } while (n3 != n || n4 != n2);
            graphics.dispose();
            graphics2D.drawImage(bufferedImage2, 0, 0, n, n2, 0, 0, width, height, null);
            return;
        }
        graphics2D.drawImage(bufferedImage, 0, 0, n, n2, 0, 0, width, height, null);
    }
    
    public static BufferedImage blur(BufferedImage bufferedImage, final int n, int n2, final double n3) {
        final BufferedImage bufferedImage2 = bufferedImage;
        final BufferedImage bufferedImage3 = bufferedImage;
        final int n4 = 2;
        final int n5 = 2;
        final double n6 = 0.5;
        final int n7 = n5;
        n2 = n4;
        BufferedImage compatibleImage = bufferedImage3;
        bufferedImage = bufferedImage2;
        final int width = bufferedImage2.getWidth();
        final int height = bufferedImage.getHeight();
        if (compatibleImage == null) {
            compatibleImage = GraphicsTools.config.createCompatibleImage(width, height);
        }
        final int n8 = width * height;
        int[] scratch;
        if ((scratch = GraphicsTools.scratch) == null) {
            scratch = new int[n8 * 2];
        }
        final double pow = Math.pow(n6, 1.0 / (n7 << 1));
        final BufferedImage bufferedImage4 = bufferedImage;
        final int[] array = scratch;
        final int n9 = width;
        final int n10 = height;
        final int n11 = n9;
        int[] array2 = array;
        final BufferedImage bufferedImage5 = bufferedImage4;
        if (n11 == 0 || n10 == 0) {
            final int[] array3 = new int[0];
        }
        else {
            if (array2 == null) {
                array2 = new int[n11 * n10];
            }
            else if (array2.length < n11 * n10) {
                throw new IllegalArgumentException("Pixel array must have a length >= w*h");
            }
            final int type;
            if ((type = bufferedImage5.getType()) == 2 || type == 1) {
                final int[] array3 = (int[])bufferedImage5.getRaster().getDataElements(0, 0, n11, n10, array2);
            }
            else {
                bufferedImage5.getRGB(0, 0, n11, n10, array2, 0, n11);
            }
        }
        for (int i = 0; i < n7; ++i) {
            blur(scratch, 0, scratch, n8, width, height, n2, pow);
            blur(scratch, n8, scratch, 0, height, width, n2, pow);
        }
        final BufferedImage bufferedImage6 = compatibleImage;
        final int[] array4 = scratch;
        final int n12 = width;
        final int n13 = height;
        final int n14 = n12;
        final int[] array5 = array4;
        final BufferedImage bufferedImage7 = bufferedImage6;
        if (array5 != null && n14 != 0 && n13 != 0) {
            if (array5.length < n14 * n13) {
                throw new IllegalArgumentException("Pixel array must have a length >= w*h");
            }
            final int type2;
            if ((type2 = bufferedImage7.getType()) == 2 || type2 == 1) {
                bufferedImage7.getRaster().setDataElements(0, 0, n14, n13, array5);
            }
            else {
                bufferedImage7.setRGB(0, 0, n14, n13, array5, 0, n14);
            }
        }
        return compatibleImage;
    }
    
    private static void blur(final int[] array, final int n, final int[] array2, final int n2, final int n3, final int n4, final int n5, final double n6) {
        final int n7 = (n5 << 1) + 1;
        final int n8 = n5 + 1;
        final int n9 = (int)(n6 * 65536.0 / n7) + 1;
        int n10 = 0;
        for (int i = 0; i < n4; ++i) {
            int n11 = i;
            final int n12 = array[n + n10];
            int n13 = 0 + n8 * (n12 >> 16 & 0xFF);
            int n14 = 0 + n8 * (n12 >> 8 & 0xFF);
            int n15 = 0 + n8 * (n12 & 0xFF);
            for (int j = 1; j <= n5; ++j) {
                final int n16 = array[n + n10 + j];
                n13 += (n16 >> 16 & 0xFF);
                n14 += (n16 >> 8 & 0xFF);
                n15 += (n16 & 0xFF);
            }
            for (int k = 0; k < n3; ++k) {
                array2[n2 + n11] = (n13 * n9 >> 16 << 16 | n14 * n9 >> 16 << 8 | n15 * n9 >> 16);
                n11 += n4;
                int n17;
                if ((n17 = k + n8) >= n3) {
                    n17 = n3 - 1;
                }
                int n18;
                if ((n18 = k - n5) < 0) {
                    n18 = 0;
                }
                final int n19 = array[n + n10 + n17];
                final int n20 = array[n + n10 + n18];
                n13 = n13 + (n19 >> 16 & 0xFF) - (n20 >> 16 & 0xFF);
                n14 = n14 + (n19 >> 8 & 0xFF) - (n20 >> 8 & 0xFF);
                n15 = n15 + (n19 & 0xFF) - (n20 & 0xFF);
            }
            n10 += n3;
        }
    }
    
    static {
        GraphicsTools.config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}
