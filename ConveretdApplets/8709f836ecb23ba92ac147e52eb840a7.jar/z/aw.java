// 
// Decompiled by Procyon v0.5.30
// 

package z;

import com.sun.image.codec.jpeg.JPEGDecodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import java.io.ByteArrayInputStream;
import java.awt.Image;
import java.io.InputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.FileInputStream;
import java.util.Date;
import java.io.File;
import javax.swing.ImageIcon;

public class aw
{
    private final String a;
    private ImageIcon b;
    private int c;
    private int d;
    private int e;
    private int f;
    private long g;
    private long h;
    private String i;
    private static final Object j;
    private static /* synthetic */ boolean k;
    
    public aw(final String a) {
        this.g = -1L;
        this.h = 0L;
        this.i = null;
        if (!aw.k && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
    }
    
    private void b(final int c) {
        if (!aw.k && c < 0) {
            throw new AssertionError();
        }
        this.c = c;
    }
    
    private void c(final int d) {
        if (!aw.k && d < 0) {
            throw new AssertionError();
        }
        this.d = d;
    }
    
    public final int a() {
        return this.f;
    }
    
    public final int b() {
        return this.e;
    }
    
    public final String c() {
        if (!aw.k && (this.a == null || this.a.length() <= 0)) {
            throw new AssertionError();
        }
        return this.a;
    }
    
    public final String d() {
        if (!aw.k && (this.a == null || this.a.length() <= 0)) {
            throw new AssertionError();
        }
        return au.a(this.a);
    }
    
    public final String e() {
        if (!aw.k && (this.a == null || this.a.length() <= 0)) {
            throw new AssertionError();
        }
        if (this.i == null) {
            this.i = "";
            final Date date = new Date(new File(this.a).lastModified());
            this.i = String.format("%02d/%02d/%02d", 1 + date.getMonth(), date.getDate(), date.getYear() % 100);
        }
        return this.i;
    }
    
    public final long f() {
        if (this.g == -1L) {
            final long length;
            if ((length = new File(this.c()).length()) > 2147483647L) {
                throw new RuntimeException("File too large: " + this.a);
            }
            this.g = (int)length;
        }
        return this.g;
    }
    
    public final int g() {
        if (this.h == this.f()) {
            return 100;
        }
        final int n = (int)(this.h / this.f() * 100.0);
        if (!aw.k && (n < 0 || n > 100)) {
            throw new AssertionError();
        }
        return n;
    }
    
    public final void a(final ImageIcon b) {
        this.b = b;
    }
    
    public final ImageIcon h() {
        return this.b;
    }
    
    public final ag a(final float n, final boolean b, final int n2, final int n3) {
        if (!aw.k && (n <= 0.0f || n > 1.0f)) {
            throw new AssertionError();
        }
        if (!aw.k && (this.a == null || this.a.length() <= 0)) {
            throw new AssertionError();
        }
        synchronized (aw.j) {
            if (au.d(this.a)) {
                if (b && this.a(n2, n3)) {
                    try {
                        final FileInputStream fileInputStream;
                        final JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder((InputStream)(fileInputStream = new FileInputStream(this.a)));
                        final ImageIcon imageIcon;
                        if ((imageIcon = new ImageIcon(jpegDecoder.decodeAsBufferedImage())).getImageLoadStatus() == 4) {
                            fileInputStream.close();
                            System.out.println("TRAPPED: memory ex");
                            throw new ah();
                        }
                        this.e = imageIcon.getIconWidth();
                        this.f = imageIcon.getIconHeight();
                        byte[][] markerData = null;
                        final JPEGDecodeParam jpegDecodeParam;
                        if ((jpegDecodeParam = jpegDecoder.getJPEGDecodeParam()).getMarker(225)) {
                            markerData = jpegDecodeParam.getMarkerData(225);
                        }
                        fileInputStream.close();
                        final ImageIcon a;
                        if ((a = z.h.a(imageIcon, n2, n3)).getImageLoadStatus() == 4) {
                            System.out.println("TRAPPED: memory ex");
                            throw new ah();
                        }
                        this.c(a.getIconHeight());
                        this.b(a.getIconWidth());
                        if (markerData != null) {
                            bi.a(markerData, this.c, this.d, false);
                        }
                        final byte[] a2 = V.a(n, a.getImage(), markerData);
                        this.g = a2.length;
                        final ag ag;
                        (ag = new ag(new ByteArrayInputStream(a2), a2.length)).d = true;
                        return ag;
                    }
                    catch (OutOfMemoryError outOfMemoryError) {
                        throw new ah(this.a);
                    }
                }
                return new ag(new FileInputStream(this.a), new File(this.a).length());
            }
            if (au.h(this.a)) {
                return new ag(new FileInputStream(this.a), new File(this.a).length());
            }
            ImageIcon a3 = new ImageIcon(V.b(this.a));
            this.e = a3.getIconWidth();
            this.f = a3.getIconHeight();
            if (b && this.a(n2, n3)) {
                a3 = z.h.a(a3, n2, n3);
            }
            this.c(a3.getIconHeight());
            this.b(a3.getIconWidth());
            final byte[] a4 = V.a(n, a3.getImage(), null);
            this.g = a4.length;
            return new ag(new ByteArrayInputStream(a4), a4.length);
        }
    }
    
    private boolean a(final int n, final int n2) {
        if (this.c == 0) {
            this.c = aA.a(this.a).width;
        }
        final int c = this.c;
        if (this.d == 0) {
            this.d = aA.a(this.a).height;
        }
        return z.h.a(c, this.d, n, n2) < 1.0f;
    }
    
    public final long i() {
        return this.f();
    }
    
    public final void a(final int n) {
        if (!aw.k && n <= 0) {
            throw new AssertionError();
        }
        this.h += n;
        if (!aw.k && this.h > this.f()) {
            throw new AssertionError();
        }
        if (this.h > 2147483647L) {
            throw new RuntimeException("Too many bytes transferred: " + this.h + " of " + this.a);
        }
    }
    
    public final void j() {
        this.h = 0L;
    }
    
    static {
        aw.k = !aw.class.desiredAssertionStatus();
        j = new Object();
    }
}
