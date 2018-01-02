// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.document.ad;
import ji.util.e;
import java.awt.image.PixelGrabber;
import ji.v1event.a6;
import ji.image.c2;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import ji.io.ac;
import ji.v1event.af;
import java.util.Hashtable;
import ji.io.gn;
import ji.util.i;
import java.awt.image.ImageObserver;
import ji.io.fg;
import ji.decode.ou;
import ji.io.h;
import ji.util.d;
import ji.image.dx;
import ji.io.at;
import java.awt.Image;

public class e3 extends cj
{
    private boolean a;
    private boolean b;
    private Image c;
    private static at d;
    private static String e;
    private boolean f;
    private String g;
    private String[] h;
    
    public void a(final String g) {
        this.g = g;
    }
    
    public e3() {
        this.a = false;
        this.b = false;
        this.c = null;
        this.f = false;
        this.g = null;
        this.h = new String[] { "doc", "htm", "html", "image", "jp2", "djvu", "png", "bmp", "pix", "db", "zip", "xls", "txt", "text", "pdf" };
    }
    
    public final String getFilterName() {
        return "jiFilterJG";
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        try {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.g, "Analysing JPEG...");
            }
            if (e3.d == null) {
                e3.d = new at(this.g);
            }
            final long currentTimeMillis = System.currentTimeMillis();
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            this.b = false;
            this.f = false;
            if (e3.e != null && fh.e != null && !fh.e.toLowerCase().equals(e3.e.toLowerCase())) {
                b3 = true;
            }
            if (b3) {
                this.c();
            }
            e3.e = fh.e;
            final dx dx = new dx();
            ji.util.d.bh(fh.f);
            dx.ay = false;
            fh.b.a(0L);
            final byte[] array = new byte[6];
            fh.b.a(array);
            dx.l = new String(array);
            if (dx.l.toLowerCase().equals("gif89a") || dx.l.toLowerCase().equals("gif87a")) {
                b = !ji.util.d.d2();
            }
            try {
                if (b) {
                    dx.ay = true;
                    dx.m = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                    dx.n = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                    final int n = fh.b.i() & 0xFF;
                    final int n2 = (n & 0x70) >> 4;
                    final int n3 = n & 0x3;
                    if (n2 <= 1 && n3 < 3) {
                        b2 = true;
                    }
                    else if (n2 <= 1 && n3 == 3) {
                        dx.x = 1;
                    }
                    if (dx.m <= 0 || dx.n <= 0) {
                        try {
                            while (fh.b.i() != 44 && !fh.b.g()) {}
                            final int n4 = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                            final int n5 = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                            final int n6 = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                            final int n7 = (fh.b.i() & 0xFF) + (fh.b.i() & 0xFF) * 256;
                            dx.m = n6 - n4;
                            dx.n = n7 - n5;
                        }
                        catch (Exception ex3) {}
                    }
                }
                else {
                    int n8 = 0;
                    dx.l = "Jpeg";
                    try {
                        fh.b.a(0L);
                        if (ou.a(fh.b, dx, this.g, false) != null) {
                            n8 = 1;
                        }
                    }
                    catch (fg fg) {
                        throw fg;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (n8 == 0) {
                        int n9 = 0;
                        final int n10 = 192;
                        final int n11 = 195;
                        final byte[] array2 = new byte[2];
                        final byte[] array3 = new byte[4];
                        fh.b.a(0L);
                        fh.b.a(array2);
                        while (n9 == 0 && !fh.b.g()) {
                            fh.b.a(array3);
                            final int n12 = array3[0] & 0xFF;
                            final int n13 = array3[1] & 0xFF;
                            final int n14 = ((array3[2] & 0xFF) << 8) + (array3[3] & 0xFF);
                            if (n12 == 255) {
                                if (n13 >= n10 && n13 <= n11) {
                                    final byte[] array4 = new byte[5];
                                    fh.b.a(array4);
                                    dx.n = ((array4[1] & 0xFF) << 8) + (array4[2] & 0xFF);
                                    dx.m = ((array4[3] & 0xFF) << 8) + (array4[4] & 0xFF);
                                    dx.ac = 100.0;
                                    dx.ad = 100.0;
                                    try {
                                        dx.bk.put("xResolution", String.valueOf(String.valueOf(new StringBuffer("").append(dx.ac).append(" DPI (assumed)"))));
                                        dx.bk.put("yResolution", String.valueOf(String.valueOf(new StringBuffer("").append(dx.ad).append(" DPI (assumed)"))));
                                    }
                                    catch (Exception ex4) {}
                                    n8 = 1;
                                    n9 = 1;
                                }
                                else {
                                    fh.b.a(new byte[n14 - 2]);
                                }
                            }
                            else {
                                fh.b.a(0L);
                                this.a(fh.f, fh.g, fh.o, true);
                                if (this.c == null) {
                                    n9 = 1;
                                }
                                else {
                                    dx.m = this.c.getWidth(null);
                                    dx.n = this.c.getHeight(null);
                                    if (dx.m > 0 && dx.m > 0) {
                                        n9 = 1;
                                    }
                                    else {
                                        n8 = 1;
                                        n9 = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (n8 == 0) {
                        final aa6 e = this.e(fh.f);
                        if (e != null) {
                            dx.m = e.a();
                            dx.n = e.b();
                        }
                        else {
                            fh.b.a(0L);
                            this.a(fh.f, fh.g, fh.o, true);
                            dx.m = this.c.getWidth(null);
                            dx.n = this.c.getHeight(null);
                            this.a(fh.f, new aa6(fh.e, dx.m, dx.n));
                        }
                    }
                }
                if (b && !i.c(3)) {
                    throw new gn(String.valueOf(String.valueOf(new StringBuffer("GIF - ").append(ji.util.d.b(630, this.g)).append("\n\n ").append(ji.util.d.b(631, this.g)))));
                }
                dx.u = 1;
                dx.i = fh.b.v();
            }
            catch (Exception ex2) {
                ji.util.d.b(ex2);
                if (this.c != null) {
                    this.c.flush();
                    this.c = null;
                }
                if (ex2 instanceof gn || ex2 instanceof fg) {
                    throw ex2;
                }
                return null;
            }
            if (!fh.i && !b && (dx.m <= 0 || dx.n <= 0)) {
                if (this.c != null) {
                    this.c.flush();
                    this.c = null;
                }
                return null;
            }
            if (dx.bk == null) {
                dx.bk = new Hashtable(5);
            }
            if (!fh.i) {
                dx.bk.put("Width", "".concat(String.valueOf(String.valueOf(dx.m))));
                dx.bk.put("Height", "".concat(String.valueOf(String.valueOf(dx.n))));
            }
            if (i.c(87) && b) {
                if (b2) {
                    dx.z = 1;
                    dx.aa = 1;
                    dx.am = 1;
                }
                else {
                    dx.z = 8;
                    dx.aa = 1;
                    dx.am = 8;
                }
            }
            else {
                if (!i.c(87)) {
                    dx.z = 8;
                    dx.aa = 3;
                }
                dx.am = dx.z * dx.aa;
            }
            dx.an = 0;
            dx.ar = false;
            dx.at = 0;
            if (b) {
                dx.a = 6;
            }
            else {
                dx.a = 2;
            }
            if (dx != null && ji.util.d.dv()) {
                ji.io.h.e(this.g, "JPEG header analysed in ".concat(String.valueOf(String.valueOf(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
            }
            return dx;
        }
        finally {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.g, "Analysed JPEG.");
            }
        }
    }
    
    private final void a(final String s, final af af, final Object o, final boolean b) {
        final long currentTimeMillis = System.currentTimeMillis();
        this.b = false;
        if (this.c == null) {
            try {
                final ac ac = new ac(s, false, false, 0, o, this.g);
                byte[] array;
                try {
                    array = new byte[(int)ac.v()];
                }
                catch (Exception ex) {
                    if (ji.util.d.dk() || ji.util.d.dl()) {
                        this.b = true;
                    }
                    throw ex;
                }
                ac.a(array);
                ac.a(o);
                this.c = Toolkit.getDefaultToolkit().createImage(array, 0, array.length);
                if (this.c != null && b) {
                    ji.util.d.a(new Frame(), this.c);
                    ji.util.d.a(new Frame(), this.c);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.g, "JPEG image decoded in ".concat(String.valueOf(String.valueOf(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
        }
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final boolean e() {
        return false;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        super.c = true;
        try {
            super.b = false;
            if (ji.util.d.dv()) {
                ji.io.h.e(this.g, "Decompressing JPEG...");
            }
            try {
                fh.c.e(true);
                if (fh.d.x == 1) {
                    fh.c.a(true);
                }
                this.a(fh.d.h, fh.g, fh.o, !i.c(87));
                int a = 0;
                final long currentTimeMillis = System.currentTimeMillis();
                if (i.c(87)) {
                    System.currentTimeMillis();
                    final c2 c2 = new c2(this.g, fh.o, fh.d, fh.g, this.c, 0, 0, fh.d.m, fh.d.n, 0, fh.c);
                    c2.a(fh.d.ak);
                    a = (c2.a() ? 1 : 0);
                    if (a != 0) {
                        if ((c2.b() | 0x40) == 0x40) {
                            a = 0;
                        }
                        else if ((c2.b() | 0x80) == 0x80) {
                            a = 0;
                        }
                    }
                    if (a == 0 && i.c(85)) {
                        ji.io.h.d(this.g, "Trying recovery by using Standard Java...");
                    }
                    if (ji.util.d.dv() || this.a) {
                        ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(fh.d.n * fh.d.m * 4).append(" decompressed bytes from Java-image in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
                    }
                    c2.i();
                }
                this.f = false;
                if (a == 0) {
                    fh.d.z = 8;
                    fh.d.aa = 3;
                    fh.d.am = fh.d.z * fh.d.aa;
                    fh.c.b(4, false, fh.o);
                    fh.c.e(true);
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    if (!this.f) {
                        if (this.b) {
                            throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(296, this.g)))).append("\n").append(ji.util.d.b(297, this.g)).append("\n").append(ji.util.d.b(298, this.g)))));
                        }
                        int min = ji.util.d.ar / 8;
                        if (ji.util.d.em()) {
                            min = Math.min(fh.d.m * fh.d.n, 3145728);
                        }
                        int min2 = Math.min(min / fh.d.m, fh.d.n);
                        if (min2 < 1) {
                            min2 = 1;
                        }
                        final int n = min2 * fh.d.m;
                        int n2 = fh.d.n;
                        int n3 = 0;
                        final a6 a2 = new a6(this, 4, "");
                        final int[] array = new int[n];
                        if (ji.util.d.dv() && min2 > 0) {
                            ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(n * 4).append(" buffer bytes allocated in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis2)))));
                        }
                        while (n2 > 0 && !super.b) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            new PixelGrabber(this.c, 0, n3, fh.d.m, min2, array, 0, fh.d.m).grabPixels();
                            if (ji.util.d.dv() && min2 > 0) {
                                ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(min2 * fh.d.m * 4).append(" decompressed bytes extracted in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis3)))));
                            }
                            final long currentTimeMillis4 = System.currentTimeMillis();
                            fh.c.a(array, min2 * fh.d.m, fh.o, n3, n3 + min2 - 1, true);
                            if (ji.util.d.dv() && min2 > 0) {
                                ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(min2 * fh.d.m * 4).append(" decompressed bytes saved in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis4)))));
                            }
                            n3 += min2;
                            n2 -= min2;
                            if (n2 < min2) {
                                min2 = n2;
                            }
                            if (fh.g != null) {
                                a2.a("".concat(String.valueOf(String.valueOf(100 * n3 / fh.d.n))));
                                fh.g.a(a2);
                            }
                        }
                    }
                    final long currentTimeMillis5 = System.currentTimeMillis();
                    fh.c.e(fh.o);
                    if (ji.util.d.dv() || this.a) {
                        ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(fh.d.n * fh.d.m * 4).append(" decompressed bytes prepared in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis5)))));
                    }
                    if (ji.util.d.dv() || this.a) {
                        ji.io.h.e(this.g, String.valueOf(String.valueOf(new StringBuffer("JPEG ").append(fh.d.n * fh.d.m * 4).append(" decompressed bytes extracted from Java-image in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
                    }
                    try {
                        if (fh.g != null && ji.util.d.du() && fh.d.ak) {
                            final a6 a3 = new a6(this, 23, "");
                            a3.a("100");
                            fh.g.a(a3);
                        }
                    }
                    catch (Exception ex) {}
                }
                else {
                    try {
                        if (fh.g != null && ji.util.d.du() && fh.d.ak) {
                            final a6 a4 = new a6(this, 23, "");
                            a4.a("100");
                            fh.g.a(a4);
                        }
                    }
                    catch (Exception ex2) {}
                    if (fh.d.am > 1) {
                        fh.c.e(fh.o);
                    }
                }
            }
            finally {
                this.c = null;
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (ji.util.e.ai()) {
                super.b = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        this.f = false;
        if (this.c != null) {
            this.c.flush();
            this.c = null;
        }
    }
    
    private final void a(final String s, final aa6 aa6) {
        try {
            if (s != null && aa6 != null) {
                e3.d.a(s, aa6);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void c() {
        try {
            e3.d.f();
        }
        catch (Exception ex) {}
    }
    
    public final aa6 e(final String s) {
        try {
            return (aa6)e3.d.c(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        try {
            ac.a(0L);
            final byte[] array = new byte[6];
            ac.a(array);
            final String s5 = new String(array);
            int n = 0;
            if (s5.toLowerCase().equals("gif89a") || s5.toLowerCase().equals("gif87a")) {
                n = (ji.util.d.d2() ? 0 : 1);
            }
            if (n != 0) {
                return 1;
            }
        }
        catch (Exception ex) {
            return 2;
        }
        if (i.c(5)) {
            ji.io.h.d(this.g, "File could be a JPEG, but need to load image header to work it out.");
        }
        return 2;
    }
    
    public void a(final dx dx, final dx dx2) {
        dx2.z = dx.z;
        dx2.aa = dx.aa;
        dx2.am = dx.am;
    }
    
    static {
        e3.d = null;
        e3.e = null;
    }
    
    class aa6
    {
        public int a;
        public int b;
        public String c;
        
        public aa6(final e3 e3, final String c, final int a, final int b) {
            this.a = 0;
            this.b = 0;
            this.c = null;
            this.c = c;
            this.a = a;
            this.b = b;
        }
        
        public final int a() {
            return this.a;
        }
        
        public final int b() {
            return this.b;
        }
    }
}
