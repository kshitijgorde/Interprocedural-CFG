// 
// Decompiled by Procyon v0.5.30
// 

package ji.jpeg;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import ji.v1event.a6;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.OutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import ji.io.a8;
import ji.util.c3;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.IndexColorModel;
import ji.image.c2;
import ji.util.d;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import ji.render.c1;
import ji.io.h;
import ji.image.dx;
import ji.encode.hi;
import ji.awt.c;
import ji.v1event.af;
import java.awt.Component;
import ji.io.ac;
import java.awt.Image;
import ji.util.i;

public class jiJpegEncoder
{
    private static final boolean newPixelGrabMethod = true;
    private static final int encodedPixelDepth = 24;
    
    private static final boolean isLogging() {
        return i.c(126) || i.c(5);
    }
    
    public static final void encode(final Image image, final ac ac, final Component component, final String s, final Float n, final int n2, final af af, final String s2, final String s3, final c c, final int a, final hi hi, final dx dx) throws Exception {
        hi.a = a;
        hi.b = 24;
        final int m = dx.m;
        final int n3 = dx.n;
        ac.b(m);
        ac.b(n3);
        if (isLogging()) {
            h.d(s, "TIFF writer: Band size = ".concat(String.valueOf(String.valueOf(n2))));
        }
        if (n2 <= 0) {
            encodeBand(ac, component, s, image, n, c, m, n3);
        }
        else {
            final int min = Math.min(n2, n3);
            int i = n3;
            int n4 = 0;
            final int n5 = 10;
            int n6 = 0;
            setStatus(component, af, s2, 0);
            int min2 = Math.min(Math.max(Math.min(min, 524288 / (4 * m)), 10), n3);
            int[] array = null;
            byte[] array2 = null;
            final ImageProducer source = image.getSource();
            c1 c2 = null;
            int n7 = 1;
            while (i > 0) {
                MemoryImageSource memoryImageSource;
                if (a == 24) {
                    if (source instanceof c1) {
                        if (n7 != 0 && af != null && s3 != null) {
                            setStatus(component, af, s3, n6);
                        }
                        System.currentTimeMillis();
                        if (c2 == null) {
                            c2 = (c1)source;
                            c2.g();
                        }
                        if (array2 == null) {
                            array2 = new byte[m * min2 * 4];
                        }
                        c2.a(0, n4, m, min2, array2);
                        if (array == null) {
                            array = new int[m * min2];
                        }
                        for (int j = 0; j < array.length; ++j) {
                            final int n8 = j * 4;
                            array[j] = (0xFF000000 | (array2[n8 + 1] & 0xFF) << 16 | (array2[n8 + 2] & 0xFF) << 8 | (array2[n8 + 3] & 0xFF));
                        }
                        memoryImageSource = new MemoryImageSource(m, min2, ColorModel.getRGBdefault(), array, 0, m);
                    }
                    else {
                        if (array == null) {
                            array = new int[m * min2];
                        }
                        d.a(component, image);
                        final c2 c3 = new c2(s, component, af, source, 0, n4, m, min2, 0, array, a);
                        c3.a();
                        c3.i();
                        memoryImageSource = new MemoryImageSource(m, min2, ColorModel.getRGBdefault(), array, 0, m);
                    }
                }
                else if (a == 8) {
                    if (array2 == null) {
                        array2 = new byte[m * min2];
                    }
                    final c2 c4 = new c2(s, component, af, source, 0, n4, m, min2, 0, array2, a, false);
                    c4.a();
                    final int[] d = c4.d();
                    final byte[] array3 = new byte[d.length];
                    final byte[] array4 = new byte[d.length];
                    final byte[] array5 = new byte[d.length];
                    for (int k = 0; k < d.length; ++k) {
                        final int n9 = d[k];
                        array3[k] = (byte)(n9 >> 16 & 0xFF);
                        array4[k] = (byte)(n9 >> 8 & 0xFF);
                        array5[k] = (byte)(n9 & 0xFF);
                    }
                    memoryImageSource = new MemoryImageSource(m, min2, new IndexColorModel(8, d.length, array3, array4, array5), array2, 0, m);
                    c4.i();
                }
                else if (a == 1) {
                    if (array == null) {
                        array = new int[m * min2];
                    }
                    final c3 a2 = d.a(image, s, component);
                    d.a(component, s, image, a2, 0, n4, m, min2, m, array);
                    d.a(image, a2);
                    memoryImageSource = new MemoryImageSource(m, min2, ColorModel.getRGBdefault(), array, 0, m);
                }
                else {
                    if (array2 == null) {
                        array2 = new byte[m * min2 * 4];
                    }
                    final c2 c5 = new c2(s, component, af, source, 0, n4, m, min2, 0, array2, a, false);
                    c5.a();
                    c5.i();
                    if (array == null) {
                        array = new int[m * min2];
                    }
                    for (int l = 0; l < array.length; ++l) {
                        final int n10 = l * 4;
                        array[l] = (0xFF000000 | (array2[n10 + 1] & 0xFF) << 16 | (array2[n10 + 2] & 0xFF) << 8 | (array2[n10 + 3] & 0xFF));
                    }
                    memoryImageSource = new MemoryImageSource(m, min2, ColorModel.getRGBdefault(), array, 0, m);
                }
                n7 = 0;
                final Image image2 = Toolkit.getDefaultToolkit().createImage(memoryImageSource);
                d.a(component, image2);
                encodeBand(ac, component, s, image2, n, c, m, min2);
                try {
                    image2.flush();
                }
                catch (Exception ex) {}
                i -= min2;
                n4 += min2;
                if (i < min2) {
                    min2 = i;
                }
                if (!d.b() && af != null) {
                    final int n11 = 100 * n4 / n3;
                    if (n11 < n6 + n5) {
                        continue;
                    }
                    n6 = n11;
                    setStatus(component, af, s2, n11);
                }
            }
            if (c2 != null) {
                c2.f();
            }
            setStatus(component, af, s2, 100);
        }
        ac.b(0);
        try {
            if (image != null) {
                image.flush();
            }
        }
        catch (Exception ex2) {}
    }
    
    private static final void encodeBand(final ac ac, final Component component, final String s, final Image image, final Float n, final c c, final int n2, final int n3) throws Exception {
        try {
            final String concat = String.valueOf(String.valueOf(ac.a())).concat("_1");
            final ac ac2 = new ac(concat, true, true, 102400, false, component, true, s);
            final a8 a8 = new a8(ac2, component);
            final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder((OutputStream)a8);
            final BufferedImage bufferedImage = getBufferedImage(component, image, n3, n2);
            final JPEGEncodeParam defaultJPEGEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(bufferedImage);
            defaultJPEGEncodeParam.setQuality((float)n, true);
            jpegEncoder.encode(bufferedImage, defaultJPEGEncodeParam);
            a8.flush();
            ac2.a(component);
            copyDataFromFile(concat, ac, component, s, n3, c);
            try {
                bufferedImage.flush();
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    private static void setStatus(final Component component, final af af, final String s, final int n) {
        if (af != null) {
            final a6 a6 = new a6(component, 17, s);
            a6.a(true);
            a6.a(n);
            a6.a(s);
            af.a(a6);
        }
    }
    
    private static BufferedImage getBufferedImage(final Component component, final Image image, final int n, final int n2) {
        final BufferedImage bufferedImage = new BufferedImage(n2, n, 1);
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }
    
    private static void copyDataFromFile(final String s, final ac ac, final Component component, final String s2, final int n, final c c) throws Exception {
        final ac ac2 = new ac(s, false, false, 0, false, component, false, s2);
        final int n2 = (int)ac2.v();
        final byte[] array = new byte[Math.min(10240, n2)];
        ac.b(n);
        ac.b((int)ac2.v());
        for (int i = ac2.a(array); i > 0; i = ac2.a(array)) {
            ac.b(array, 0, i);
        }
        ac2.a(component);
        ac.c(s, s2);
        c.c(new Integer(n));
        c.c(new Integer(n2));
    }
    
    public void releaseResources() {
    }
}
