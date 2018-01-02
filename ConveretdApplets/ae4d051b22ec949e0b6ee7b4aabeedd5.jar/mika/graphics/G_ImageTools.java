// 
// Decompiled by Procyon v0.5.30
// 

package mika.graphics;

import java.awt.MediaTracker;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import mika.system.S_Debug;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;

public final class G_ImageTools
{
    static int m_iCachedImageWidth;
    static int m_iCachedImageHeight;
    static int m_iCachedMaskWidth;
    static int m_iCachedMaskHeight;
    static int[] m_iPixels;
    static int[] m_iAlpha;
    static int[] m_iCachePixels;
    static Image m_imgCacheImage;
    
    public static Image extractImage(final Component component, final Image image, final int n, final int n2, final int n3, final int n4) {
        checkImageValidity(component, image);
        final Image image2 = component.createImage(n3, n4);
        image2.getGraphics().drawImage(image, -n, -n2, component);
        return image2;
    }
    
    public static Image extractImage(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        checkImageValidity(component, image);
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        final int[] array2 = new int[n3 * n4];
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image, n5, n6, n3, n4, array2, 0, n3);
        try {
            pixelGrabber.grabPixels();
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
            return null;
        }
        final DirectColorModel directColorModel = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
        for (int i = 0; i < n3 * n4; ++i) {
            array[i] = ((array[i] & 0xFFFFFF) | ((array2[i] >> 16 & 0xFF) + (array2[i] >> 8 & 0xFF) + (array2[i] & 0xFF)) / 3 << 24);
        }
        return component.createImage(new MemoryImageSource(n3, n4, directColorModel, array, 0, n3));
    }
    
    public static Image extractImage(final Component component, final Image image, final int n, final int n2, final int n3, final int n4, final Image image2, final int n5, final int n6) {
        checkImageValidity(component, image);
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        final int[] array2 = new int[n3 * n4];
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, n5, n6, n3, n4, array2, 0, n3);
        try {
            pixelGrabber.grabPixels();
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
            return null;
        }
        final DirectColorModel directColorModel = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
        for (int i = 0; i < n3 * n4; ++i) {
            array[i] = ((array[i] & 0xFFFFFF) | ((array2[i] >> 16 & 0xFF) + (array2[i] >> 8 & 0xFF) + (array2[i] & 0xFF)) / 3 << 24);
        }
        return component.createImage(new MemoryImageSource(n3, n4, directColorModel, array, 0, n3));
    }
    
    public static void grabAndCachePixels(final ImageObserver imageObserver, final Image image, final Image image2) {
        G_ImageTools.m_iCachedImageWidth = image.getWidth(imageObserver);
        G_ImageTools.m_iCachedImageHeight = image.getHeight(imageObserver);
        G_ImageTools.m_iCachedMaskWidth = image2.getWidth(imageObserver);
        G_ImageTools.m_iCachedMaskHeight = image2.getHeight(imageObserver);
        G_ImageTools.m_iPixels = new int[G_ImageTools.m_iCachedImageWidth * G_ImageTools.m_iCachedImageHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, G_ImageTools.m_iCachedImageWidth, G_ImageTools.m_iCachedImageHeight, G_ImageTools.m_iPixels, 0, G_ImageTools.m_iCachedImageWidth);
        G_ImageTools.m_iAlpha = new int[G_ImageTools.m_iCachedMaskWidth * G_ImageTools.m_iCachedMaskHeight];
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, 0, 0, G_ImageTools.m_iCachedMaskWidth, G_ImageTools.m_iCachedMaskHeight, G_ImageTools.m_iAlpha, 0, G_ImageTools.m_iCachedMaskWidth);
        try {
            pixelGrabber.grabPixels();
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
        }
    }
    
    public static void grabAndCacheImagePixels(final ImageObserver imageObserver, final Image image) {
        G_ImageTools.m_iCachedImageWidth = image.getWidth(imageObserver);
        G_ImageTools.m_iCachedImageHeight = image.getHeight(imageObserver);
        G_ImageTools.m_iPixels = new int[G_ImageTools.m_iCachedImageWidth * G_ImageTools.m_iCachedImageHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, G_ImageTools.m_iCachedImageWidth, G_ImageTools.m_iCachedImageHeight, G_ImageTools.m_iPixels, 0, G_ImageTools.m_iCachedImageWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
        }
    }
    
    public static void grabAndCacheMaskPixels(final ImageObserver imageObserver, final Image image) {
        G_ImageTools.m_iCachedMaskWidth = image.getWidth(imageObserver);
        G_ImageTools.m_iCachedMaskHeight = image.getHeight(imageObserver);
        G_ImageTools.m_iAlpha = new int[G_ImageTools.m_iCachedMaskWidth * G_ImageTools.m_iCachedMaskHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, G_ImageTools.m_iCachedMaskWidth, G_ImageTools.m_iCachedMaskHeight, G_ImageTools.m_iAlpha, 0, G_ImageTools.m_iCachedMaskWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
        }
    }
    
    public static void freeCachedPixels() {
        G_ImageTools.m_iPixels = null;
        G_ImageTools.m_iAlpha = null;
    }
    
    public static Image extractImage(final Component component, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] array = new int[n3 * n4];
        final DirectColorModel directColorModel = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
        for (int n7 = n2 * G_ImageTools.m_iCachedImageWidth + n, n8 = n6 * G_ImageTools.m_iCachedMaskWidth + n5, i = 0; i < n3 * n4; ++i, ++n7, ++n8) {
            if (i != 0 && i % n3 == 0) {
                n7 += G_ImageTools.m_iCachedImageWidth - n3;
                n8 += G_ImageTools.m_iCachedMaskWidth - n3;
            }
            final int n9 = G_ImageTools.m_iAlpha[n8];
            array[i] = ((G_ImageTools.m_iPixels[n7] & 0xFFFFFF) | ((n9 >> 16 & 0xFF) + (n9 >> 8 & 0xFF) + (n9 & 0xFF)) / 3 << 24);
        }
        return component.createImage(new MemoryImageSource(n3, n4, directColorModel, array, 0, n3));
    }
    
    public static Image rotate(final Component component, final Image image, final float n, final boolean b) {
        checkImageValidity(component, image);
        final int[] pixels = getPixels(component, image, false);
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        final float n2 = (float)Math.cos(n);
        final float n3 = (float)Math.sin(n);
        final int n4 = height / 2;
        final int n5 = width / 2;
        int n6 = height;
        int n7 = width;
        if (b) {
            final float[] array = new float[4];
            final float[] array2 = new float[4];
            array[0] = -n5;
            array2[0] = -n4;
            array[1] = n5;
            array2[1] = -n4;
            array[2] = -n5;
            array2[2] = n4;
            array[3] = n5;
            array2[3] = n4;
            int n8 = 0;
            int n9 = 0;
            for (int i = 0; i < 4; ++i) {
                final int abs = Math.abs(Math.round(array[i] * n2 - array2[i] * n3));
                final int abs2 = Math.abs(Math.round(array2[i] * n2 + array[i] * n3));
                if (abs > n8) {
                    n8 = abs;
                }
                if (abs2 > n9) {
                    n9 = abs2;
                }
            }
            n6 = n8 * 2;
            n7 = n9 * 2;
        }
        final int[] array3 = new int[n6 * n7];
        final int n10 = n7 / 2;
        final int n11 = n6 / 2;
        int n12 = 0;
        for (int j = -n10; j < n10; ++j) {
            for (int k = -n11; k < n11; ++k) {
                final int n13 = Math.round(k * n2 - j * n3) + n5;
                final int n14 = Math.round(j * n2 + k * n3) + n4;
                if (n13 >= 0 && n13 < width && n14 >= 0 && n14 < height) {
                    array3[n12++] = pixels[width * n14 + n13];
                }
                else {
                    ++n12;
                }
            }
        }
        return createPreparedImage(component, array3, n7, n6);
    }
    
    public static int makeRGB(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }
    
    public static int getR(final int n) {
        return (n & 0xFF0000) >> 16;
    }
    
    public static int getG(final int n) {
        return (n & 0xFF00) >> 8;
    }
    
    public static int getB(final int n) {
        return n & 0xFF;
    }
    
    public static Image multiply(final Component component, final Image image, final float n) {
        checkImageValidity(component, image);
        final int[] pixels = getPixels(component, image, false);
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        for (int i = 0; i < width * height; ++i) {
            pixels[i] = makeRGB((int)(getR(pixels[i]) * n), (int)(getG(pixels[i]) * n), (int)(getB(pixels[i]) * n));
        }
        return createPreparedImage(component, pixels, width, height);
    }
    
    public static Image multiply(final Component component, final Image image, final float n, final float n2, final float n3) {
        checkImageValidity(component, image);
        final int[] pixels = getPixels(component, image, false);
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        for (int i = 0; i < width * height; ++i) {
            pixels[i] = makeRGB((int)(getR(pixels[i]) * n), (int)(getG(pixels[i]) * n2), (int)(getB(pixels[i]) * n3));
        }
        return createPreparedImage(component, pixels, width, height);
    }
    
    public static int[] getPixels(final Component component, final Image imgCacheImage, final boolean b) {
        checkImageValidity(component, imgCacheImage);
        int[] iCachePixels;
        try {
            if (imgCacheImage == G_ImageTools.m_imgCacheImage) {
                return G_ImageTools.m_iCachePixels;
            }
            final int width = imgCacheImage.getWidth(component);
            final int height = imgCacheImage.getHeight(component);
            iCachePixels = new int[width * height];
            new PixelGrabber(imgCacheImage, 0, 0, width, height, iCachePixels, 0, width).grabPixels();
        }
        catch (InterruptedException ex) {
            S_Debug.print(ex);
            return null;
        }
        if (b) {
            G_ImageTools.m_imgCacheImage = imgCacheImage;
            G_ImageTools.m_iCachePixels = iCachePixels;
        }
        return iCachePixels;
    }
    
    public static Image createPreparedImage(final Component component, final int[] array, final int n, final int n2) {
        final Image image = component.createImage(new MemoryImageSource(n, n2, new DirectColorModel(24, 16711680, 65280, 255), array, 0, n));
        component.prepareImage(image, component);
        return image;
    }
    
    public static Image createPreparedImage(final Component component, final int[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Image image = component.createImage(new MemoryImageSource(n4, n5, array, n2 * n3 + n, n4));
        component.prepareImage(image, component);
        return image;
    }
    
    public static Image loadImage(final Component component, final URL url, final String s) {
        final Image image = component.getToolkit().getImage(url);
        waitForImageToLoad(component, image, s);
        return image;
    }
    
    public static void waitForImageToLoad(final Component component, final Image image, final String s) {
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(component);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
                final int statusAll = mediaTracker.statusAll(false);
                if ((statusAll & 0x2) != 0x0) {
                    S_Debug.print("G_ImageTools: Loading image '" + s + "' aborted.");
                }
                if ((statusAll & 0x4) != 0x0) {
                    S_Debug.print("G_ImageTools: Loading image '" + s + "' errored.");
                }
            }
            catch (InterruptedException ex) {
                S_Debug.print(ex);
            }
        }
    }
    
    private static void checkImageValidity(final Component component, final Image image) {
        if (image.getWidth(component) <= 0 || image.getHeight(component) <= 0) {
            S_Debug.print("Image '" + image + "' size invalid: " + image.getWidth(component) + "x" + image.getHeight(component));
        }
    }
}
