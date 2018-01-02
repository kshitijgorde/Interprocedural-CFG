// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import com.photochannel.easyUploader.EasyUploader;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.io.OutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.ByteArrayOutputStream;
import com.sun.image.codec.jpeg.ImageFormatException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.photochannel.easyUploader.b;

public class V
{
    private static b a;
    private static ClassLoader b;
    private static /* synthetic */ boolean c;
    
    public static void a(final b a) {
        V.a = a;
    }
    
    public static void a(final ClassLoader b) {
        V.b = b;
    }
    
    public static ImageIcon a(final ImageIcon imageIcon, final Dimension dimension, final Z z) {
        if (!V.c && imageIcon == null) {
            throw new AssertionError();
        }
        if (!V.c && (dimension.width <= 0 || dimension.height <= 0)) {
            throw new AssertionError();
        }
        if (!V.c && V.a == null) {
            throw new AssertionError();
        }
        if (imageIcon.getIconWidth() <= dimension.width && imageIcon.getIconHeight() <= dimension.height) {
            return imageIcon;
        }
        double n = 1.0;
        final int width = imageIcon.getImage().getWidth(null);
        final int height = imageIcon.getImage().getHeight(null);
        if (width > dimension.width) {
            n = dimension.width / width;
        }
        if (n * height > dimension.height) {
            n *= dimension.height / (n * height);
        }
        final int max = Math.max((int)(n * width), 1);
        final int max2 = Math.max((int)(n * height), 1);
        if (z == Z.c) {
            return new ImageIcon(imageIcon.getImage().getScaledInstance(max, max2, 4));
        }
        final BufferedImage a;
        final Graphics2D graphics = (a = V.a.a(max, max2)).createGraphics();
        switch (U.a[z.ordinal()]) {
            case 1: {
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                break;
            }
            case 2: {
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                break;
            }
        }
        graphics.drawImage(imageIcon.getImage(), 0, 0, max, max2, 0, 0, width, height, null);
        graphics.dispose();
        return new ImageIcon(a);
    }
    
    private static BufferedImage d(final String s) {
        if (!V.c && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        try {
            final RenderedImage renderedImage = (RenderedImage)V.b.loadClass("com.photochannel.easyUploader.utility.TiffProvider").getMethod("load", String.class).invoke(null, s);
            if (!V.c && renderedImage == null) {
                throw new AssertionError();
            }
            if (!V.c && V.a == null) {
                throw new AssertionError();
            }
            final int width = renderedImage.getWidth();
            final int height = renderedImage.getHeight();
            if (!V.c && (width <= 0 || height <= 0)) {
                throw new AssertionError();
            }
            final BufferedImage a;
            final Graphics2D graphics = (a = V.a.a(width, height)).createGraphics();
            graphics.drawRenderedImage(renderedImage, new AffineTransform());
            graphics.dispose();
            return a;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SecurityException ex2) {
            ex2.printStackTrace();
        }
        catch (NoSuchMethodException ex3) {
            ex3.printStackTrace();
        }
        catch (IllegalArgumentException ex4) {
            ex4.printStackTrace();
        }
        catch (IllegalAccessException ex5) {
            ex5.printStackTrace();
        }
        catch (InvocationTargetException ex7) {
            final InvocationTargetException ex6 = ex7;
            if (ex7.getCause() instanceof GeneralSecurityException) {
                throw new am();
            }
            ex6.printStackTrace();
        }
        return null;
    }
    
    private static BufferedImage e(final String s) {
        return ImageIO.read(new File(s));
    }
    
    public static ImageIcon a(final URL url) {
        if (url.toString().startsWith(G.b().toString())) {
            final String replace = url.toString().replace(G.b().toString(), "/");
            final ImageIcon a;
            if ((a = a(replace, replace)) != null) {
                return a;
            }
        }
        System.out.println("loading from URL: " + url);
        try {
            url.openStream().close();
            return new ImageIcon(url);
        }
        catch (IOException ex) {
            System.err.println("Image failed to load: " + url);
            ex.printStackTrace();
            return new ImageIcon();
        }
    }
    
    public static a a(final String s) {
        if (!V.c && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (au.c(s)) {
            return new a(d(s));
        }
        if (au.e(s)) {
            return new a(e(s));
        }
        final File file;
        if (!(file = new File(s)).exists()) {
            throw new FileNotFoundException(s);
        }
        if (!au.d(s)) {
            return new a(new ImageIcon(s).getImage());
        }
        RandomAccessFile randomAccessFile = null;
        try {
            final bb a;
            if ((a = a(randomAccessFile = new RandomAccessFile(file, "r"), false)).a) {
                throw new am("Jpeg in CMYK colorspace");
            }
            final bb bb;
            if (!(bb = a).e || !bb.f || !bb.g || !bb.h || !bb.i) {
                throw new am("Invalid JFIF data");
            }
            final byte[] a2;
            if (a.b > 0L && (a2 = bi.a(randomAccessFile, a.b, false)) != null) {
                final a a3;
                (a3 = new a(new ImageIcon(a2).getImage())).b = true;
                a3.c = new Dimension(a.c, a.d);
                final a a4 = a3;
                randomAccessFile.close();
                return a4;
            }
            final ImageIcon imageIcon;
            if ((imageIcon = new ImageIcon(s)).getImageLoadStatus() == 4) {
                throw new ah(s);
            }
            final a a5 = new a(imageIcon.getImage());
            randomAccessFile.close();
            return a5;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            throw new ah(s);
        }
        catch (ImageFormatException ex) {
            throw new am(ex.getMessage());
        }
        finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
    }
    
    public static Image b(final String s) {
        if (!V.c && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (au.c(s)) {
            return d(s);
        }
        if (au.e(s)) {
            return e(s);
        }
        if (!new File(s).exists()) {
            throw new FileNotFoundException(s);
        }
        return new ImageIcon(s).getImage();
    }
    
    public static byte[] a(final float n, Image image, final byte[][] array) {
        if (!V.c && (n <= 0.0f || n > 1.0f)) {
            throw new AssertionError();
        }
        if (!V.c && image == null) {
            throw new AssertionError();
        }
        BufferedImage bufferedImage;
        if (image instanceof BufferedImage) {
            bufferedImage = (BufferedImage)image;
        }
        else {
            image = image;
            if (!V.c && image == null) {
                throw new AssertionError();
            }
            if (!V.c && V.a == null) {
                throw new AssertionError();
            }
            final int width = image.getWidth(null);
            final int height = image.getHeight(null);
            if (!V.c && (width <= 0 || height <= 0)) {
                throw new AssertionError();
            }
            final BufferedImage a;
            final Graphics graphics;
            (graphics = (a = V.a.a(width, height)).getGraphics()).drawImage(image, 0, 0, null);
            graphics.dispose();
            bufferedImage = a;
        }
        final ByteArrayOutputStream byteArrayOutputStream;
        final JPEGImageEncoder jpegEncoder;
        final JPEGEncodeParam defaultJPEGEncodeParam = (jpegEncoder = JPEGCodec.createJPEGEncoder((OutputStream)(byteArrayOutputStream = new ByteArrayOutputStream()))).getDefaultJPEGEncodeParam(bufferedImage);
        if (array != null) {
            defaultJPEGEncodeParam.setMarkerData(225, array);
        }
        defaultJPEGEncodeParam.setQuality(n, false);
        jpegEncoder.setJPEGEncodeParam(defaultJPEGEncodeParam);
        try {
            jpegEncoder.encode(bufferedImage);
            byteArrayOutputStream.flush();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            bufferedImage.flush();
            return byteArray;
        }
        catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    private static bb a(final RandomAccessFile randomAccessFile, final boolean b) {
        final bb bb;
        (bb = new bb(null)).a = false;
        bb.b = -1L;
        final L l = new L(randomAccessFile);
        try {
            if (l.d() < 4) {
                throw new am("Invalid JPEG file.");
            }
            l.a = true;
            if (l.b() != 65496) {
                throw new am("Invalid JPEG file: missing SOI");
            }
            int b2;
            while ((b2 = l.b()) != 65497) {
                if (b2 == 65498) {
                    bb.h = true;
                    break;
                }
                final int b3 = l.b();
                switch (b2) {
                    case 65504: {
                        if (l.b("JFIF\ufffd\ufffd")) {
                            break;
                        }
                        break;
                    }
                    case 65472:
                    case 65474: {
                        bb.g = true;
                        final long a = l.a();
                        l.c(1);
                        bb.c = l.b();
                        bb.d = l.b();
                        l.a((int)a);
                        break;
                    }
                    case 65505: {
                        if (l.b("Exif")) {
                            bb.b = l.a();
                            break;
                        }
                        break;
                    }
                    case 65518: {
                        if (!l.b("Adobe")) {
                            break;
                        }
                        if (l.b(11) == 2) {
                            System.out.println("Rejecting CMYK jpeg");
                            bb.a = true;
                            return bb;
                        }
                        break;
                    }
                    case 65499: {
                        bb.f = true;
                        break;
                    }
                    case 65476: {
                        bb.e = true;
                        break;
                    }
                    case 65497: {
                        bb.i = true;
                        if (l.a() != randomAccessFile.length()) {
                            throw new am("Invalid JPEG file: data beyond EOI marker");
                        }
                        break;
                    }
                }
                l.c(b3 - 2);
            }
            if (!bb.i) {
                for (int n = 2; n < randomAccessFile.length() - 256L; ++n) {
                    l.a((int)(randomAccessFile.length() - n));
                    final int b4;
                    if ((b4 = l.b()) == 65497) {
                        bb.i = true;
                        break;
                    }
                    if (b4 == 65498) {
                        break;
                    }
                }
            }
            bb.a = false;
            return bb;
        }
        catch (Exception ex) {
            System.err.println(ex);
            throw new am("Invalid JPEG file.");
        }
    }
    
    public static ImageIcon a(final String s, final String s2) {
        final int n = 64000;
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(EasyUploader.class.getResourceAsStream(s));
        final byte[] array = new byte[n];
        int read;
        try {
            read = bufferedInputStream.read(array);
        }
        catch (IOException ex) {
            System.err.println("Couldn't read stream from file: " + s);
            return null;
        }
        try {
            bufferedInputStream.close();
        }
        catch (IOException ex2) {
            System.err.println("Can't close file " + s);
            return null;
        }
        if (read <= 0) {
            System.err.println("Empty file: " + s);
            return null;
        }
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(array), s2);
    }
    
    public static Color c(final String s) {
        try {
            return new Color(E.a(s.substring(1, 3)), E.a(s.substring(3, 5)), E.a(s.substring(5, 7)));
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    static {
        V.c = !V.class.desiredAssertionStatus();
    }
}
