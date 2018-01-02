// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.awt.ax;
import ji.annotate.dg;
import ji.wang.ej;
import ji.filter.tiff.sa;
import ji.filter.tiff.hz;
import java.util.Vector;
import ji.v1event.a6;
import ji.io.hp;
import ji.annotate.df;
import java.awt.image.ImageProducer;
import ji.filter.tiff.hm;
import ji.filter.tiff.hl;
import java.io.IOException;
import java.io.FileNotFoundException;
import ji.io.ac;
import ji.filter.tiff.hj;
import ji.image.c2;
import ji.filter.output.jiImageSaveFailedException;
import ji.render.c1;
import ji.encode.hi;
import ji.encode.fp;
import ji.util.d;
import ji.io.h;
import ji.image.dx;
import ji.v1event.af;
import java.awt.Image;
import ji.document.ad;
import ji.filter.tiff.ho;
import java.io.OutputStream;
import ji.util.i;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import ji.awt.c;
import ji.filter.tiff.hn;
import ji.io.q;

public class gb
{
    String a;
    private Object b;
    q c;
    hn d;
    c e;
    static String[] f;
    long g;
    boolean h;
    FileOutputStream i;
    PrintWriter j;
    hk[] k;
    static boolean l;
    
    private static final boolean b() {
        return i.c(126) || i.c(5);
    }
    
    public final void a(final hk[] k) {
        this.k = k;
    }
    
    public gb(final Object b, final String a) throws Exception {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = -1L;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = null;
        this.b = b;
        this.a = a;
    }
    
    public final boolean a(final int n, final boolean[] array) throws Exception {
        if (this.h) {
            this.i = new FileOutputStream("c:\\viewone\\writer.log");
            this.j = new PrintWriter(this.i);
        }
        if (this.c == null) {
            this.c = q.a(this.b, this.a);
        }
        this.d = new hn();
        if (array != null) {
            int n2 = 0;
            for (int i = 0; i < array.length; ++i) {
                if (array[i]) {
                    ++n2;
                }
            }
            this.d.d = new ho[n2];
            for (int j = 0; j < n2; ++j) {
                this.d.d[j] = new ho();
            }
        }
        else {
            this.d.d = new ho[n];
            for (int k = 0; k < n; ++k) {
                this.d.d[k] = new ho();
            }
        }
        return true;
    }
    
    public final void a(final int n) throws Exception {
        if (this.d != null && this.d.d != null && this.d.d.length > n) {
            this.d.d[n] = null;
        }
    }
    
    public static final hk[] a(final ad ad, final String s, final Image image, final int n, int b, final int n2, final af af, final String s2, final String s3, final dx dx, final boolean b2, final double n3, final double n4, final boolean b3, final boolean b4, final boolean b5, final int n5, final int n6, final boolean b6, final boolean b7, final boolean b8) throws jiImageSaveFailedException {
        int n7 = 0;
        boolean b9 = false;
        hk[] array6;
        try {
            if (b()) {
                h.d(s, "TIFF writer: Image width = ".concat(String.valueOf(String.valueOf(n))));
                h.d(s, "TIFF writer: ViewONE Burner: Image height = ".concat(String.valueOf(String.valueOf(b))));
                h.d(s, "TIFF writer: Uncompressed size (full color) = ".concat(String.valueOf(String.valueOf(d.a((long)(n * b * 4), s)))));
            }
            int n8 = 0;
            String s4 = null;
            int n9 = 4;
            int n10 = 1;
            int n11 = 1;
            boolean b10 = true;
            final int n12 = b;
            boolean b11 = true;
            int a = -1;
            c c = null;
            hi hi = null;
            n7 = 2;
            if (fp.a(ad) && (n6 == 5 || n6 == 6)) {
                n7 = 3;
                if (b()) {
                    h.d(s, "TIFF writer: Creating TIFF with JPEG or LZW encoding...");
                }
                s4 = q.a(ad, s).n();
                c = new c("jiTIFFWriterBands");
                int max = 64;
                if (max * n * 4 > 204800) {
                    max = Math.max(204800 / (n * 4), 5);
                }
                if (b4) {
                    if (b5) {
                        b9 = true;
                        b11 = false;
                    }
                    else if (dx.m * dx.n * 4 > 2097152) {
                        b9 = false;
                    }
                    else {
                        b9 = true;
                        b11 = false;
                    }
                }
                if (!b11) {
                    max = -1;
                }
                hi = new hi();
                n7 = 4;
                a = fp.a(image, s4, ad, s, n5, max, af, s2, s3, c, n6, n2, dx, hi, b2, b6, b7);
                n7 = 5;
                if (c.b() < 2) {
                    h.d(s, "TIFF band < 2 writer error at step: ".concat(String.valueOf(String.valueOf(n7))));
                }
                b = (int)c.b(0);
                n8 = (int)c.b(1);
                if (n8 > 0) {
                    if (a == 6) {
                        if (b9) {
                            n9 = 6;
                            if (b()) {
                                h.d(s, "TIFF writer:Using TIFF6 standard...");
                            }
                        }
                        else {
                            n9 = 7;
                            if (b()) {
                                h.d(s, "TIFF writer: Using TIFF7 standard...");
                            }
                        }
                        n10 = 3;
                        n11 = 8;
                    }
                    else {
                        if (hi.b == 8) {
                            n9 = 5;
                            n11 = 8;
                            n10 = 1;
                        }
                        else {
                            n9 = 5;
                            n11 = 8;
                            n10 = 3;
                        }
                        if (b()) {
                            h.d(s, "TIFF writer: Using TIFF standard for LZW...");
                        }
                    }
                    b10 = false;
                }
                else {
                    h.d(s, "Unable to encode in JPEG, so resorting to monochrome.");
                    c.c();
                }
                n7 = 6;
            }
            n7 = 7;
            if (b10) {
                n7 = 8;
                int n13 = n / 8;
                if (n13 * 8 != n) {
                    ++n13;
                }
                final byte[] array = new byte[n13 * b];
                if (b()) {
                    h.d(s, "TIFF writer: Converting all pixels to monochrome...");
                }
                final ImageProducer source = image.getSource();
                if (source instanceof c1) {
                    n7 = 9;
                    if (b()) {
                        h.d(s, "TIFF writer: Retrieving image data from virtual memory...");
                    }
                    try {
                        final c1 c2 = (c1)source;
                        final int[] array2 = new int[n];
                        ad.b(image);
                        n7 = 10;
                        for (int i = 0; i < b; ++i) {
                            final int n14 = i * n13;
                            int n15 = 0;
                            int n16 = 128;
                            c2.a(0, i, n, 1, array2);
                            for (final int n17 : array2) {
                                if (((n17 & 0xFF0000) >> 16) + ((n17 & 0xFF00) >> 8) + (n17 & 0xFF) > 384) {
                                    final byte[] array3 = array;
                                    final int n18 = n14 + n15;
                                    array3[n18] |= (byte)n16;
                                }
                                n16 >>= 1;
                                if (n16 == 0) {
                                    n16 = 128;
                                    ++n15;
                                }
                            }
                        }
                        ad.a(image);
                    }
                    catch (Exception ex) {
                        if (b()) {
                            h.d(s, "TIFF writer: Exception caused whilst retrieving from virtual memory: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                        }
                        throw new jiImageSaveFailedException("Exception caused whilst retrieving from virtual memory: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                    }
                    n7 = 11;
                }
                else {
                    n7 = 12;
                    if (b()) {
                        h.d(s, "TIFF writer: Retrieving image data from memory...");
                    }
                    ad.b(image);
                    if (n2 == 1 && source instanceof c1) {
                        n7 = 13;
                        System.currentTimeMillis();
                        final c2 c3 = new c2(s, ad, af, source, 0, 0, n, b, 0, array, n2, true);
                        c3.a();
                        c3.i();
                    }
                    else {
                        n7 = 14;
                        if (n2 == 1) {
                            ad.a(s, ad, null, image, 0, 0, n, b, array);
                            ad.a(image);
                        }
                        else {
                            final int[] array4 = new int[n * b];
                            System.currentTimeMillis();
                            ad.a(image, 0, 0, n, b, n, array4);
                            ad.a(image);
                            try {
                                n7 = 15;
                                for (int k = 0; k < b; ++k) {
                                    final int n19 = k * n;
                                    final int n20 = k * n13;
                                    int n21 = 0;
                                    int n22 = 128;
                                    for (int l = 0; l < n; ++l) {
                                        final int n23 = array4[n19 + l];
                                        if (((n23 & 0xFF0000) >> 16) + ((n23 & 0xFF00) >> 8) + (n23 & 0xFF) > 384) {
                                            final byte[] array5 = array;
                                            final int n24 = n20 + n21;
                                            array5[n24] |= (byte)n22;
                                        }
                                        n22 >>= 1;
                                        if (n22 == 0) {
                                            n22 = 128;
                                            ++n21;
                                        }
                                    }
                                }
                            }
                            catch (Exception ex2) {
                                if (b()) {
                                    h.d(s, "TIFF writer: Exception caused whilst retrieving from memory: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                                }
                                throw new jiImageSaveFailedException("Exception caused whilst retrieving from memory: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                            }
                        }
                        n7 = 16;
                    }
                }
                n7 = 17;
                byte b12 = 0;
                for (int n25 = 0; n25 < array.length && b12 == 0; b12 += array[n25], ++n25) {}
                if (b12 == 0) {
                    if (b()) {
                        h.d(s, "TIFF writer: uncompressed image data is all zeros!");
                    }
                    throw new jiImageSaveFailedException("Uncompressed image data is all 0");
                }
                boolean b13 = false;
                for (int n26 = 0; n26 < array.length && !b13; b13 = (array[n26] != 255), ++n26) {}
                n7 = 18;
                if (!b13) {
                    if (b()) {
                        h.d(s, "TIFF writer: uncompressed image data is all ones!");
                    }
                    throw new jiImageSaveFailedException("Uncompressed image data is all 255");
                }
                if (b()) {
                    h.d(s, "TIFF writer: Converted all pixels to monochrome...");
                }
                s4 = q.a(ad, s).n();
                final hj hj = new hj();
                n7 = 19;
                final byte[] a2 = hj.a(array, n, b);
                n7 = 20;
                if (a2 == null) {
                    n7 = 21;
                    if (b()) {
                        h.d(s, "TIFF writer: encoded data array is null");
                    }
                    throw new jiImageSaveFailedException("Encoded data array is null");
                }
                n7 = 22;
                if (a2.length < 8) {
                    if (b()) {
                        h.d(s, "TIFF writer: size of compressed data is less than 8 bytes");
                    }
                    throw new jiImageSaveFailedException("Size of compressed data is less than 8 bytes");
                }
                byte b14 = 0;
                for (int n27 = 0; n27 < a2.length && b14 == 0; b14 += a2[n27], ++n27) {}
                if (b14 == 0) {
                    if (b()) {
                        h.d(s, "TIFF writer: compressed data is all zero");
                    }
                    throw new jiImageSaveFailedException("Compressed data is all zero");
                }
                n8 = a2.length;
                try {
                    final ac ac = new ac(s4, true, false, 0, ad, s);
                    ac.b(n);
                    ac.b(b);
                    ac.b(n);
                    ac.b(n8);
                    ac.b(a2, 0, n8);
                    ac.a(ad);
                }
                catch (FileNotFoundException ex3) {
                    d.a(ex3);
                    throw new jiImageSaveFailedException(ex3.getMessage());
                }
                catch (IOException ex4) {
                    d.a(ex4);
                    throw new jiImageSaveFailedException(ex4.getMessage());
                }
            }
            n7 = 23;
            if (b10) {
                n7 = 24;
                array6 = new hk[] { new hk() };
                array6[0].g = b9;
                array6[0].a = 1;
                array6[0].b = b;
                array6[0].c = n8;
                (array6[0].h = new hl[1])[0] = new hl();
                array6[0].h[0].d = s4;
                array6[0].h[0].a = 0;
                array6[0].h[0].b = n8;
                array6[0].h[0].e = 9;
                array6[0].h[0].c = b;
                array6[0].a(new hm(259, 3, n9));
                array6[0].a(new hm(266, 3, 1));
                array6[0].a(new hm(277, 3, n10));
                array6[0].a(new hm(258, 3, n11));
                array6[0].a(new hm(279, 4, n8));
                array6[0].a(new hm(273, 4, 1));
                array6[0].a(new hm(278, 4, b));
                if (b2 || b8) {
                    array6[0].a(new hm(256, 4, n));
                    array6[0].a(new hm(257, 4, n12));
                }
                n7 = 25;
            }
            else {
                n7 = 26;
                if (c.b() < 2) {
                    h.d(s, "TIFF band < 2 writer error at step: ".concat(String.valueOf(String.valueOf(n7))));
                }
                b = (int)c.b(0);
                final int intValue = (int)c.b(1);
                final int a3 = c.b() / 2;
                array6 = new hk[] { new hk() };
                array6[0].a = a3;
                array6[0].b = b;
                array6[0].c = intValue;
                if (!(array6[0].g = b9)) {
                    array6[0].e = true;
                }
                else {
                    array6[0].e = false;
                }
                array6[0].a(new hm(259, 3, n9));
                array6[0].a(new hm(266, 3, 1));
                array6[0].a(new hm(277, 3, n10));
                if (b2 || b8) {
                    array6[0].a(new hm(256, 4, n));
                    array6[0].a(new hm(257, 4, n12));
                }
                n7 = 27;
                if (a == 6) {
                    n7 = 28;
                    array6[0].a(new hm(258, 3, n11, n11, n11));
                }
                else {
                    n7 = 29;
                    if (hi != null) {
                        if (hi.b == 8) {
                            array6[0].a(new hm(258, 3, n11));
                            final Integer[] array7 = new Integer[hi.c.length * 3];
                            int n28 = 0;
                            for (int n29 = 0; n29 < hi.c.length; ++n29) {
                                array7[n28++] = new Integer((short)(((hi.c[n29] & -1) >> 16 & 0xFF) << 8 & 0xFF00));
                            }
                            for (int n30 = 0; n30 < hi.c.length; ++n30) {
                                array7[n28++] = new Integer((short)(((hi.c[n30] & -1) >> 8 & 0xFF) << 8 & 0xFF00));
                            }
                            for (int n31 = 0; n31 < hi.c.length; ++n31) {
                                array7[n28++] = new Integer((short)((hi.c[n31] & 0xFF) << 8 & 0xFF00));
                            }
                            array6[0].a(new hm(320, 3, array7));
                        }
                        else {
                            array6[0].a(new hm(258, 3, n11, n11, n11));
                        }
                    }
                    else {
                        array6[0].a(new hm(258, 3, n11, n11, n11));
                    }
                    array6[0].a(new hm(284, 3, 1));
                }
                n7 = 30;
                array6[0].d = true;
                if (a == 6) {
                    n7 = 31;
                    array6[0].f = true;
                    array6[0].a(new hm(262, 3, 6));
                }
                else if (hi.b == 8) {
                    n7 = 32;
                    array6[0].a(new hm(262, 3, 3));
                }
                else {
                    n7 = 33;
                    array6[0].a(new hm(262, 3, 2));
                }
                n7 = 34;
                array6[0].h = new hl[a3];
                final Integer[] array8 = new Integer[a3];
                final Integer[] array9 = new Integer[a3];
                if (c.b() < 1) {
                    h.d(s, "TIFF band < 1 writer error at step: ".concat(String.valueOf(String.valueOf(n7))));
                }
                array6[0].a(new hm(278, 4, (int)c.b(0)));
                n7 = 35;
                for (int n32 = 0; n32 < a3; ++n32) {
                    final int intValue2 = (int)c.b(n32 * 2);
                    final int intValue3 = (int)c.b(n32 * 2 + 1);
                    array6[0].h[n32] = new hl();
                    array6[0].h[n32].d = s4;
                    array6[0].h[n32].a = 0;
                    array6[0].h[n32].b = intValue3;
                    array6[0].h[n32].e = 9;
                    array6[0].h[n32].c = intValue2;
                    array8[n32] = new Integer(0);
                    array9[n32] = new Integer(intValue3);
                }
                array6[0].a(new hm(273, 4, array8));
                array6[0].a(new hm(279, 4, array9));
                n7 = 36;
            }
            if (b3) {
                final hm hm = new hm(282);
                hm.b = 5;
                hm.j = new Double(n3);
                array6[0].a(hm);
                final hm hm2 = new hm(283);
                hm2.b = 5;
                hm2.j = new Double(n4);
                array6[0].a(hm2);
                array6[0].a(new hm(256, 4, n));
                array6[0].a(new hm(257, 4, n12));
            }
        }
        catch (Exception ex5) {
            h.d(s, "TIFF writer error at step: ".concat(String.valueOf(String.valueOf(n7))));
            d.a(ex5);
            throw new jiImageSaveFailedException("TIFF writer error at step: ".concat(String.valueOf(String.valueOf(n7))));
        }
        return array6;
    }
    
    private final void b(final String s) {
        if (this.j != null) {
            this.j.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.m()))).append(": ").append(s))));
        }
        if (b()) {
            ji.io.h.d(this.a, s);
        }
    }
    
    public final boolean a(final ac ac, final dx dx, final ad ad, df df, final af af, final int n, final boolean b, final Object o, final boolean b2, final boolean b3, final int n2, final int n3, final boolean c, final boolean g, final boolean d, final boolean e, final boolean f, final boolean b4) throws Exception {
        this.b("saveTIFFPage step 1...");
        if (d) {
            df = null;
        }
        if (this.d == null) {
            throw new Exception("TIFF saveStart call not issued");
        }
        final hn hn = (hn)dx.ax;
        final hp hp = new hp(ac, hn.e);
        String s;
        if (dx.g != null) {
            s = dx.g.toString();
        }
        else if (dx.h != null) {
            s = dx.h;
        }
        else {
            s = dx.f.toString();
        }
        int n4 = Math.min(dx.v - 1, hn.d.length - 1);
        this.b("pageObject=".concat(String.valueOf(String.valueOf(o))));
        this.b("imageHeader.currentObject=".concat(String.valueOf(String.valueOf(dx.g))));
        this.b("imageHeader.localFilename=".concat(String.valueOf(String.valueOf(dx.h))));
        this.b("sourcePath=".concat(String.valueOf(String.valueOf(s))));
        if (b3) {
            n4 = 0;
        }
        String string = s;
        if (string == null && o != null) {
            string = o.toString();
        }
        if (string != null && string.indexOf("#") >= 0) {
            n4 = Math.max(d.b0(string) - 1, 0);
        }
        final int min = Math.min(n4, hn.d.length - 1);
        final int b5 = hn.d[min].b;
        int n5 = hn.an.length;
        if (this.k != null) {
            this.b("tiffwriter numIndexIn=".concat(String.valueOf(String.valueOf(n))));
            if (this.k.length > n) {
                if (this.k[n] != null) {
                    n5 = this.k[n].a;
                }
                this.b("tiffwriter numStrips=".concat(String.valueOf(String.valueOf(n5))));
            }
        }
        final ho ho = hn.d[min];
        int n6 = ho.a + 2;
        boolean b6 = false;
        if (!d && !ad.cz()) {
            b6 = (d.b3(!b) == 1 || d.b3(!b) == 3);
        }
        final boolean b7 = d.b3(!b) == 2;
        double n7 = 0.0;
        final double n8 = 50.0 / ho.b;
        int n9 = 0;
        final a6 a6 = new a6(this, 4, "");
        int n10 = -1;
        for (int i = 0; i < ho.b; ++i) {
            if (a(this.a)) {
                return false;
            }
            hp.a((long)n6);
            final hm hm = new hm();
            hm.a = hp.d();
            hm.b = hp.d();
            hm.c = hp.e();
            hm.d = hp.e();
            try {
                if (e1.e && hm.a == 520) {
                    if (hn.ah != null) {
                        this.d.d[n].x = new byte[hn.ah.length][hn.ah[0].length];
                        for (int j = 0; j < hn.ah.length; ++j) {
                            for (int k = 0; k < hn.ah[j].length; ++k) {
                                this.d.d[n].x[j][k] = hn.ah[j][k];
                            }
                        }
                    }
                }
                else if (e1.e && hm.a == 519) {
                    if (hn.ag != null) {
                        this.d.d[n].w = new byte[hn.ag.length][hn.ag[0].length];
                        for (int l = 0; l < hn.ag.length; ++l) {
                            for (int n11 = 0; n11 < hn.ag[l].length; ++n11) {
                                this.d.d[n].w[l][n11] = hn.ag[l][n11];
                            }
                        }
                    }
                }
                else if (e1.e && hm.a == 521) {
                    if (hn.ai != null) {
                        this.d.d[n].y = new byte[hn.ai.length][hn.ai[0].length];
                        for (int n12 = 0; n12 < hn.ai.length; ++n12) {
                            for (int n13 = 0; n13 < hn.ai[n12].length; ++n13) {
                                this.d.d[n].y[n12][n13] = hn.ai[n12][n13];
                            }
                        }
                    }
                }
                else if (hm.a == 513) {
                    this.d.d[n].o = hn.ab;
                }
                else if (hm.a == 514) {
                    this.d.d[n].p = hn.ac;
                }
                else if (hm.a == 32932 && df != null && b6) {
                    n10 = i;
                }
                else if (hm.a != 55555 || df == null || !b7) {
                    this.a(hp, hm, hn, ad);
                    this.b(String.valueOf(String.valueOf(new StringBuffer("Including dir i=").append(i).append(", tag=").append(hm.a).append(" (").append(hm.a(this.a)).append("), intValue=").append(hm.e))));
                }
                this.d.d[n].a(hm);
            }
            catch (Exception ex) {
                if (d.cy()) {
                    ex.printStackTrace();
                }
            }
            n6 += 12;
            n7 += n8;
            if (af != null && (int)n7 > n9) {
                n9 = (int)n7;
                a6.a(String.valueOf(n9));
                af.a(a6);
            }
        }
        if (this.d.d[n].o > 0 && this.d.d[n].p > 0) {
            this.d.d[n].q = new byte[this.d.d[n].p];
            hp.a((long)this.d.d[n].o);
            hp.a(this.d.d[n].q);
        }
        int n14 = 0;
        if (this.k != null && this.k.length > n && this.k[n] != null) {
            final int a7 = this.k[n].a();
            if (a7 > 0) {
                for (int n15 = 0; n15 < a7; ++n15) {
                    final hm a8 = this.k[n].a(n15);
                    boolean b8 = false;
                    if (this.d.d[n].a(a8.a) != null) {
                        this.b(String.valueOf(String.valueOf(new StringBuffer("replacing newDir.tag=").append(a8.a).append(" (").append(a8.a(this.a)).append("), newDir.intValue=").append(a8.e))));
                        this.d.d[n].a(a8);
                        b8 = true;
                    }
                    if (!b8) {
                        this.b(String.valueOf(String.valueOf(new StringBuffer("Not found tag ").append(a8.a).append(" (").append(a8.a(this.a)).append("), adding new one"))));
                        this.d.d[n].c(a8);
                    }
                    if (c && n14 == 0) {
                        n14 = 1;
                        if (b4) {
                            this.d.d[n].c(new hm(513, 4, 0));
                            this.d.d[n].c(new hm(514, 4, 0));
                        }
                    }
                }
            }
        }
        if (ji.util.i.c(248) && ji.util.i.c(158)) {
            int n16 = 1;
            switch (n2) {
                case 0: {
                    if ((n3 & 0x1) > 0) {
                        n16 = 2;
                        break;
                    }
                    if ((n3 & 0x2) > 0) {
                        n16 = 4;
                        break;
                    }
                    n16 = 1;
                    break;
                }
                case 90: {
                    if ((n3 & 0x1) > 0) {
                        n16 = 6;
                        break;
                    }
                    if ((n3 & 0x2) > 0) {
                        n16 = 7;
                        break;
                    }
                    n16 = 6;
                    break;
                }
                case 180: {
                    if ((n3 & 0x1) > 0) {
                        n16 = 4;
                        break;
                    }
                    if ((n3 & 0x2) > 0) {
                        n16 = 2;
                        break;
                    }
                    n16 = 3;
                    break;
                }
                case 270: {
                    if ((n3 & 0x1) > 0) {
                        n16 = 7;
                        break;
                    }
                    if ((n3 & 0x2) > 0) {
                        n16 = 8;
                        break;
                    }
                    n16 = 8;
                    break;
                }
            }
            this.d.d[n].b(new hm(274));
            this.d.d[n].b(new hm(8));
            this.d.d[n].b(new hm(7));
            this.d.d[n].b(new hm(6));
            this.d.d[n].b(new hm(5));
            this.d.d[n].b(new hm(4));
            this.d.d[n].b(new hm(3));
            this.d.d[n].b(new hm(2));
            this.d.d[n].b(new hm(1));
            this.d.d[n].c(new hm(274, 3, n16));
        }
        boolean cz = false;
        if (ad != null) {
            cz = ad.cz();
        }
        if (!cz && !d && df != null && (b6 || b7) && df.c(dx.v) > 0) {
            if (b7) {
                final hm hm2 = new hm();
                hm2.a = 55555;
                hm2.b = 1;
                this.d.d[n].a(hm2);
            }
            else {
                final hm hm3 = new hm();
                hm3.a = 32932;
                hm3.b = 1;
                ac a9 = null;
                try {
                    a9 = this.a(df, dx, dx.v, ad, this.a, b2);
                    hm3.j = a9.a();
                    hm3.c = (int)a9.v();
                }
                catch (Exception ex2) {
                    ji.io.h.a(this.a, ex2);
                }
                this.d.d[n].a(hm3);
                if (a9 != null) {
                    try {
                        a9.a(ad);
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        else if (!d && n10 != -1) {
            this.d.d[n].b(32932);
        }
        this.d.d[n].k = new hl[n5];
        final byte[] array = new byte[10240];
        double n17 = 50.0;
        final double n18 = 50.0 / n5;
        int n19 = 50;
        this.b("tiffwriter numStrips = ".concat(String.valueOf(String.valueOf(n5))));
        this.b("tiffwriter numIndexIn = ".concat(String.valueOf(String.valueOf(n))));
        if (this.k != null) {
            if (this.k.length > n) {
                for (int n20 = 0; n20 < n5; ++n20) {
                    this.d.d[n].k[n20] = new hl();
                    final hl hl = this.d.d[n].k[n20];
                    hl.a = this.k[n].h[n20].a;
                    hl.b = this.k[n].h[n20].b;
                    hl.c = this.k[n].h[n20].c;
                    hl.d = this.k[n].h[n20].d;
                }
            }
        }
        else {
            for (int n21 = 0; n21 < n5; ++n21) {
                this.d.d[n].k[n21] = new hl();
                final hl hl2 = this.d.d[n].k[n21];
                hl2.a = hn.an[n21].a;
                hl2.b = hn.an[n21].b;
                hl2.c = hn.an[n21].c;
                hl2.d = this.c.n();
                hp.a((long)hl2.a);
                int n22 = Math.min(hl2.b, 10240);
                final ac ac2 = new ac(hl2.d, true, false, 0, ad, this.a);
                int b9 = hl2.b;
                int a10 = 0;
                ac2.b(dx.m);
                ac2.b(dx.n);
                ac2.b(hl2.c);
                ac2.b(hl2.b);
                while (a10 != -1 && b9 > 0) {
                    a10 = hp.a(array, 0, n22);
                    if (a10 > 0) {
                        ac2.b(array, 0, a10);
                    }
                    b9 -= a10;
                    n22 = Math.min(n22, b9);
                }
                ac2.a(ad);
                n17 += n18;
                if (af != null && (int)n17 > n19) {
                    n19 = (int)n17;
                    a6.a(String.valueOf(n19));
                    af.a(a6);
                }
            }
        }
        this.d.d[n].c = c;
        this.d.d[n].d = d;
        this.d.d[n].e = e;
        this.d.d[n].f = f;
        this.d.d[n].g = g;
        hp.a();
        return true;
    }
    
    public final boolean a(final ac ac, final af af, final ad ad, final String s, final boolean[] array) throws Exception {
        if (this.d == null) {
            throw new Exception("TIFF saveStart call not issued");
        }
        ac.c(19789);
        final hp hp = new hp(ac, 0);
        hp.b(42);
        this.a(hp, false, array);
        this.a(hp, false, array, ad, s);
        this.a(hp, false, af, array, ad, s);
        if (this.d != null) {
            if (this.d.d != null) {
                for (int i = 0; i < this.d.d.length; ++i) {
                    if (this.d.d[i] != null) {
                        this.d.d[i].c();
                        if (this.d.d[i].l != null) {
                            this.d.d[i].l.removeAllElements();
                            this.d.d[i].l = null;
                        }
                        if (this.d.d[i].k != null) {
                            for (int j = 0; j < this.d.d[i].k.length; ++j) {
                                if (this.d.d[i].k[j] != null) {
                                    this.d.d[i].k[j].a = 0;
                                    this.d.d[i].k[j].b = 0;
                                    final String d = this.d.d[i].k[j].d;
                                    try {
                                        if (d != null) {
                                            ac.c(d, s);
                                        }
                                    }
                                    catch (Exception ex) {}
                                    this.d.d[i].k[j].d = null;
                                    this.d.d[i].k[j] = null;
                                }
                            }
                            this.d.d[i].k = null;
                        }
                        this.d.d[i] = null;
                    }
                }
                this.d.d = null;
            }
            this.d = null;
        }
        final boolean b = true;
        if (this.j != null) {
            this.j.close();
            this.i.close();
            this.j = null;
        }
        return b;
    }
    
    private final void a(final ho ho, final boolean b, final boolean b2) {
        if (b) {
            ho.b(305);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_SOFTWARE");
            }
            ho.b(306);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_DATETIME");
            }
            ho.b(315);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_ARTIST");
            }
        }
        if (b2) {
            ho.b(513);
            ho.b(514);
            ho.b(521);
            ho.b(519);
            ho.b(520);
            ho.b(512);
            ho.b(515);
            ho.b(530);
        }
        else if (this.d.ac <= 0 && ho.q == null && !b) {
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGINTERCHANGEFORMAT because jpegHeader length is zero");
            }
            ho.b(513);
        }
        if (b) {
            ho.b(514);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGINTERCHANGEFORMATLENGTH");
            }
        }
        if (e1.e) {
            if (this.d.ai != null && b) {
                ho.b(521);
                if (b()) {
                    ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGACTABLES");
                }
            }
        }
        else {
            ho.b(521);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGACTABLES");
            }
        }
        if (e1.e) {
            if (this.d.ag != null && b) {
                ho.b(519);
                if (b()) {
                    ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGQTABLES");
                }
            }
        }
        else {
            ho.b(519);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGQTABLES");
            }
        }
        if (e1.e) {
            if (this.d.ah != null && b) {
                ho.b(520);
                if (b()) {
                    ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGDCTABLES");
                }
            }
        }
        else {
            ho.b(520);
            if (b()) {
                ji.io.h.d(this.a, "TIFF writer: Removing tag: TIFFTAG_JPEGDCTABLES");
            }
        }
    }
    
    private final void a(final hp hp, final boolean b, final boolean[] array) throws Exception {
        for (int length = this.d.d.length, i = 0; i < length; ++i) {
            final ho ho = this.d.d[i];
            if (ho != null) {
                try {
                    if (this.k != null && this.k.length > i && this.k[i] != null) {
                        final int c = this.k[i].c();
                        if (c > 0) {
                            for (int j = 0; j < c; ++j) {
                                ho.b(this.k[i].b(j).a);
                            }
                            this.k[i].d();
                        }
                    }
                }
                catch (Exception ex) {}
                try {
                    final int d = this.d.d[i].d();
                    if (d > 0) {
                        for (int k = 0; k < d; ++k) {
                            ho.b(this.d.d[i].c(k).a);
                        }
                        this.d.d[i].e();
                    }
                }
                catch (Exception ex2) {}
                ho.f();
            }
        }
        final int length2 = this.d.d.length;
        int n = (int)hp.g() + 4;
        for (int l = 0; l < length2; ++l) {
            final ho ho2 = this.d.d[l];
            if (ho2 != null) {
                hp.c((int)hp.g() + 4);
                final boolean f = ho2.f;
                this.a(ho2, f, ho2.g);
                final int a = ho2.a();
                n = n + 2 + a * 12;
                hp.b(a);
                ho2.l = new Vector();
                int n2 = -1;
                final hm[] b2 = this.d.d[l].b();
                for (int n3 = 0; n3 < a; ++n3) {
                    final hm hm = b2[n3];
                    hp.b(hm.a);
                    hp.b(hm.b);
                    final int n4 = 0;
                    switch (hm.b) {
                        case 1: {
                            if (hm.c <= 1) {
                                hm.c = 1;
                                hm.d = hm.e << 24;
                                break;
                            }
                            ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                            hm.d = n2--;
                            break;
                        }
                        case 3: {
                            if (hm.c <= 1) {
                                hm.c = 1;
                                hm.d = hm.e << 16;
                                break;
                            }
                            if (hm.c <= 2) {
                                hm.c = 2;
                                final Integer[] array2 = (Integer[])hm.j;
                                hm.d = (array2[0] << 16) + array2[1];
                                break;
                            }
                            ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                            hm.d = n2--;
                            break;
                        }
                        case 4: {
                            if (hm.c <= 1) {
                                hm.c = 1;
                                if (hm.a == 513 && !f) {
                                    this.d.d[l].r = hp.f() + 4;
                                    break;
                                }
                                if (hm.a == 513 && f) {
                                    ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                                    break;
                                }
                                if (hm.a == 514 && !f) {
                                    this.d.d[l].s = hp.f() + 4;
                                    break;
                                }
                                if (hm.a == 514 && f) {
                                    ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                                    break;
                                }
                                if (hm.a == 273) {
                                    if (this.d.d[l].m > 0) {
                                        hm.d = this.d.d[l].m;
                                    }
                                    else {
                                        hm.d = hm.e;
                                    }
                                    this.g = hm.d;
                                    break;
                                }
                                if (hm.e == 0 && hm.j != null && hm.j instanceof Integer[]) {
                                    hm.e = ((Integer[])hm.j)[0];
                                }
                                hm.d = hm.e;
                                break;
                            }
                            else {
                                if (!e1.e) {
                                    ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                                    hm.d = n2--;
                                    break;
                                }
                                if (hm.a == 519 && !f) {
                                    this.d.d[l].v = hp.f() + 4;
                                    break;
                                }
                                if (hm.a == 521 && !f) {
                                    this.d.d[l].t = hp.f() + 4;
                                    break;
                                }
                                if (hm.a == 520 && !f) {
                                    this.d.d[l].u = hp.f() + 4;
                                    break;
                                }
                                ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                                hm.d = n2--;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                            hm.d = n2--;
                            break;
                        }
                        case 5: {
                            ho2.l.addElement(new hz(n2, hm.a, l, n3, hp.g()));
                            hm.d = n2--;
                            break;
                        }
                    }
                    hp.c(hm.c);
                    hm.n = (int)hp.g();
                    hp.c(hm.d);
                    n += n4;
                    if (b()) {
                        ji.io.h.d(this.a, String.valueOf(String.valueOf(new StringBuffer("TIFF writer: saved tag ").append(hm.a).append(" (").append(hm.a(this.a)).append("), offset = ").append(hm.d).append(", value = ").append(hm.e))));
                    }
                }
            }
        }
        hp.c(0);
    }
    
    private final void a(final hp hp, final boolean b, final boolean[] array, final ad ad, final String s, final int n, final Vector vector) throws Exception {
        final boolean f = this.d.d[n].f;
        if (vector != null && vector.size() > 0) {
            for (int i = 0; i < vector.size(); ++i) {
                final hz hz = vector.elementAt(i);
                final hm a = this.d.d[n].a(hz.d);
                final int d = (int)hp.g();
                try {
                    Block_7: {
                        switch (a.b) {
                            case 0: {
                                if (!b) {
                                    try {
                                        a.d = d;
                                        hp.a(hz.e);
                                        hp.c(a.c);
                                        hp.c(a.d);
                                        hp.a(hp.g());
                                        if (a.a == 32932) {
                                            if (a.j == null || !(a.j instanceof String)) {
                                                break;
                                            }
                                            final String s2 = (String)a.j;
                                            final ac ac = new ac(s2, false, false, 0, ad, s);
                                            try {
                                                int n2 = (int)Math.max(ac.v(), 10240L);
                                                final byte[] array2 = new byte[n2];
                                                long v = ac.v();
                                                long n3 = ac.a(array2);
                                                hp.a((long)(int)hp.g());
                                                while (v > 0) {
                                                    if (n3 > 0) {
                                                        hp.b(array2, 0, (int)n3);
                                                    }
                                                    v -= n3;
                                                    n2 = (int)Math.min(n2, v);
                                                    n3 = ac.a(array2, 0, n2);
                                                }
                                                break;
                                            }
                                            catch (Exception ex) {
                                                ji.io.h.a(s, ex);
                                                break;
                                            }
                                            finally {
                                                try {
                                                    ac.a(ad);
                                                }
                                                catch (Exception ex7) {}
                                                ji.io.ac.c(s2, s);
                                            }
                                        }
                                        for (int j = 0; j < a.c; ++j) {
                                            hp.c((int)((Object[])a.j)[j] << 24);
                                        }
                                        break;
                                    }
                                    catch (Exception ex2) {
                                        if (ji.util.d.cy()) {
                                            ex2.printStackTrace();
                                            break;
                                        }
                                        break;
                                    }
                                    break Block_7;
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    if (!b) {
                                        a.d = d;
                                    }
                                    if (!b) {
                                        hp.a(hz.e);
                                        hp.c(a.c);
                                    }
                                    if (b && a.a == 273) {
                                        hp.a((long)a.d);
                                        for (int k = 0; k < a.c; ++k) {
                                            hp.b(this.a((int)((Object[])a.j)[k], a.a, n));
                                        }
                                    }
                                    else if (b && a.c > 1) {
                                        hp.a((long)a.d);
                                        for (int l = 0; l < a.c; ++l) {
                                            hp.b((int)((Object[])a.j)[l]);
                                        }
                                    }
                                    else if (!b) {
                                        hp.c(a.d);
                                    }
                                    if (!b) {
                                        hp.a(hp.g());
                                        for (int n4 = 0; n4 < a.c; ++n4) {
                                            hp.b((int)((Object[])a.j)[n4]);
                                        }
                                        break;
                                    }
                                    break;
                                }
                                catch (Exception ex3) {
                                    if (ji.util.d.cy()) {
                                        ex3.printStackTrace();
                                        break;
                                    }
                                    break;
                                }
                            }
                            case 3: {
                                try {
                                    if (!b) {
                                        a.d = d;
                                    }
                                    if (!b) {
                                        hp.a(hz.e);
                                        hp.c(a.c);
                                    }
                                    if (b && a.a == 513 && f) {
                                        final hl[] m = this.d.d[n].k;
                                        hp.a(hz.e + 4);
                                        hp.c(m[0].e);
                                    }
                                    else if (b && a.a == 514 && f) {
                                        final hl[] k2 = this.d.d[n].k;
                                        hp.a(hz.e + 4);
                                        hp.c(k2[0].b);
                                    }
                                    else if (b && a.a == 273) {
                                        hp.a((long)a.d);
                                        if (a.j != null) {
                                            for (int n5 = 0; n5 < a.c; ++n5) {
                                                final int intValue = (int)((Object[])a.j)[n5];
                                                boolean b2 = true;
                                                final hl[] k3 = this.d.d[n].k;
                                                if (k3 != null && k3.length > n5) {
                                                    hp.c(this.d.d[n].k[n5].e);
                                                    b2 = false;
                                                }
                                                if (b2) {
                                                    hp.c(this.a(intValue, a.a, n));
                                                }
                                            }
                                        }
                                    }
                                    else if (b && a.c > 1) {
                                        hp.a((long)a.d);
                                        if (a.j != null) {
                                            for (int n6 = 0; n6 < a.c; ++n6) {
                                                hp.c((int)((Object[])a.j)[n6]);
                                            }
                                        }
                                    }
                                    else if (!b) {
                                        hp.c(a.d);
                                    }
                                    if (b) {
                                        break;
                                    }
                                    hp.a(hp.g());
                                    if (a.j != null) {
                                        for (int n7 = 0; n7 < a.c; ++n7) {
                                            if (a.j != null) {
                                                hp.c((int)((Object[])a.j)[n7]);
                                            }
                                            else {
                                                hp.c(a.d);
                                            }
                                        }
                                        break;
                                    }
                                    break;
                                }
                                catch (Exception ex4) {
                                    if (ji.util.d.cy()) {
                                        ex4.printStackTrace();
                                        break;
                                    }
                                    break;
                                }
                            }
                            case 1: {
                                if (!b) {
                                    try {
                                        final String s3 = (String)a.j;
                                        a.c = s3.length();
                                        a.d = d;
                                        hp.a(hz.e);
                                        hp.c(a.c);
                                        hp.c(a.d);
                                        hp.a(hp.g());
                                        hp.b(s3.getBytes());
                                    }
                                    catch (Exception ex5) {
                                        if (ji.util.d.cy()) {
                                            ex5.printStackTrace();
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                if (!b) {
                                    try {
                                        a.c = 2;
                                        a.d = d;
                                        final int n8 = 0;
                                        final Double n9 = (Double)a.j;
                                        final int intValue2 = (int)(Object)n9;
                                        double n10 = 0.0;
                                        if (n9 - intValue2 > 0) {
                                            n10 = Math.round(1.0 / (n9 - intValue2));
                                        }
                                        int n11;
                                        int n12;
                                        if (n10 > 0) {
                                            n11 = (int)n10;
                                            n12 = intValue2 * n8 + 1;
                                        }
                                        else {
                                            n11 = intValue2;
                                            n12 = 1;
                                        }
                                        hp.a(hz.e);
                                        hp.c(1);
                                        hp.c(a.d);
                                        hp.a(hp.g());
                                        hp.c(n11);
                                        hp.c(n12);
                                    }
                                    catch (Exception ex8) {}
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    this.d.d[n].a(a);
                }
                catch (Exception ex6) {
                    if (ji.util.d.cy()) {
                        ex6.printStackTrace();
                    }
                }
            }
        }
    }
    
    private final void a(final hp hp, final boolean b, final boolean[] array, final ad ad, final String s) throws Exception {
        for (int length = this.d.d.length, i = 0; i < length; ++i) {
            if (this.d.d[i] != null) {
                this.a(hp, b, array, ad, s, i, this.d.d[i].l);
            }
        }
    }
    
    private final int a(final int n, final int n2, final int n3) {
        int e = n;
        final hl[] k = this.d.d[n3].k;
        if (k != null) {
            for (int i = 0; i < k.length; ++i) {
                if (k[i].a == n && k[i].e > 0) {
                    e = k[i].e;
                    break;
                }
            }
        }
        return e;
    }
    
    private final void a(final hp hp, final boolean b, final af af, final boolean[] array, final ad ad, final String s) throws Exception {
        final int length = this.d.d.length;
        final a6 a6 = new a6(this, 4, "");
        int n = 0;
        for (int i = 0; i < length; ++i) {
            if (array[i]) {
                ++n;
            }
        }
        int n2 = 0;
        if (gb.l) {
            this.b("Image save: numToSave = ".concat(String.valueOf(String.valueOf(n))));
        }
        for (int j = 0; j < length; ++j) {
            if (this.d.d[j] != null) {
                final hl[] k = this.d.d[j].k;
                if (k != null) {
                    ac ac = null;
                    String d = null;
                    int p6 = 0;
                    int p7 = 0;
                    if (!this.d.d[j].f && this.d.d[j].q != null) {
                        this.d.d[j].o = (int)hp.f();
                        hp.b(this.d.d[j].q);
                        final long f = hp.f();
                        hp.a(this.d.d[j].r);
                        hp.c(this.d.d[j].o);
                        hp.a(f);
                    }
                    if (e1.e) {
                        if (this.d.d[j].w != null && this.d.d[j].v != 0) {
                            final long[] array2 = new long[this.d.d[j].w.length];
                            for (int l = 0; l < this.d.d[j].w.length; ++l) {
                                array2[l] = hp.f();
                                hp.b(this.d.d[j].w[l]);
                            }
                            final long f2 = hp.f();
                            for (int n3 = 0; n3 < this.d.d[j].w.length; ++n3) {
                                hp.c((int)array2[n3]);
                            }
                            final long f3 = hp.f();
                            hp.a(this.d.d[j].v);
                            hp.c((int)f2);
                            hp.a(f3);
                        }
                        if (this.d.d[j].y != null && this.d.d[j].t != 0) {
                            final long[] array3 = new long[this.d.d[j].y.length];
                            for (int n4 = 0; n4 < this.d.d[j].y.length; ++n4) {
                                array3[n4] = hp.f();
                                hp.b(this.d.d[j].y[n4]);
                            }
                            final long f4 = hp.f();
                            for (int n5 = 0; n5 < this.d.d[j].y.length; ++n5) {
                                hp.c((int)array3[n5]);
                            }
                            final long f5 = hp.f();
                            hp.a(this.d.d[j].t);
                            hp.c((int)f4);
                            hp.a(f5);
                        }
                        if (this.d.d[j].x != null && this.d.d[j].u != 0) {
                            final long[] array4 = new long[this.d.d[j].x.length];
                            for (int n6 = 0; n6 < this.d.d[j].x.length; ++n6) {
                                array4[n6] = hp.f();
                                hp.b(this.d.d[j].x[n6]);
                            }
                            final long f6 = hp.f();
                            for (int n7 = 0; n7 < this.d.d[j].x.length; ++n7) {
                                hp.c((int)array4[n7]);
                            }
                            final long f7 = hp.f();
                            hp.a(this.d.d[j].u);
                            hp.c((int)f6);
                            hp.a(f7);
                        }
                    }
                    this.d.d[j].m = (int)hp.g();
                    this.d.d[j].n = true;
                    for (int n8 = 0; n8 < k.length; ++n8) {
                        final int e = (int)hp.g();
                        final hl hl = k[n8];
                        this.d.d[j].k[n8].e = e;
                        boolean b2 = true;
                        if (gb.l && (b() || this.j != null)) {
                            this.b("Image save: band file length = ".concat(String.valueOf(String.valueOf(ji.io.ac.a(hl.d, s)))));
                        }
                        if (ji.util.d.by(hl.d)) {
                            ji.io.h.d(s, "Uh? No image data?");
                            throw new Exception("Internal error: No Image data!");
                        }
                        if (!ji.io.ac.d(hl.d, s)) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Uh? ").append(hl.d).append(" does not exist!"))));
                            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("Internal error: ").append(hl.d).append(" does not exist!"))));
                        }
                        boolean b3 = true;
                        if (d != null && d.equals(hl.d)) {
                            b2 = false;
                            b3 = false;
                        }
                        if (b3) {
                            if (ac != null) {
                                ac.a(ad);
                            }
                            d = hl.d;
                            ac = new ac(hl.d, false, false, 0, ad, s);
                            b2 = false;
                        }
                        if (gb.l) {
                            this.b("Image save: doOpen = ".concat(String.valueOf(String.valueOf(b3))));
                        }
                        if (b3) {
                            p6 = ac.p();
                            p7 = ac.p();
                        }
                        final int p8 = ac.p();
                        final int p9 = ac.p();
                        if (gb.l) {
                            this.b("Image save: tempImageWidth = ".concat(String.valueOf(String.valueOf(p6))));
                            this.b("Image save: tempImageHeight = ".concat(String.valueOf(String.valueOf(p7))));
                            this.b("Image save: bandHeight = ".concat(String.valueOf(String.valueOf(p8))));
                            if (b() || this.j != null) {
                                this.b(String.valueOf(String.valueOf(new StringBuffer("Image save: actual band bandSize = ").append(p9).append(", ").append(hl.b).append(", ").append(ac.v() - ac.r() - 4))));
                            }
                        }
                        int n9 = Math.min(hl.b, 102400);
                        final byte[] array5 = new byte[n9];
                        int n10 = p9;
                        int a8;
                        int a7 = a8 = ac.a(array5);
                        hp.a((long)(int)hp.g());
                        while (a7 != -1 && n10 > 0) {
                            if (a7 > 0) {
                                hp.b(array5, 0, a7);
                            }
                            n10 -= a7;
                            n9 = Math.min(n9, n10);
                            a7 = ac.a(array5, 0, n9);
                            a8 += a7;
                        }
                        if (ac != null && b2) {
                            ac.a(ad);
                            ac = null;
                        }
                    }
                    if (ac != null) {
                        ac.a(ad);
                    }
                }
            }
            ++n2;
            try {
                if (af != null) {
                    a6.a("".concat(String.valueOf(String.valueOf(100 * n2 / n))));
                    af.a(a6);
                }
            }
            catch (Exception ex) {}
        }
        this.a(hp, true, array, ad, s);
        this.a(hp, array);
    }
    
    private final void a(final hp hp, final boolean[] array) throws Exception {
        for (int length = this.d.d.length, i = 0; i < length; ++i) {
            final ho ho = this.d.d[i];
            if (ho != null) {
                final hm a = this.d.d[i].a(273);
                if (a != null && a.n > 0 && a.c <= 1) {
                    hp.a((long)a.n);
                    hp.c(ho.m);
                }
            }
        }
    }
    
    private final void a(final hp hp, final hm hm, final hn hn, final Object o) throws Exception {
        Label_0529: {
            if (hm.c * sa.a[hm.b] <= 4) {
                switch (hm.b) {
                    case 1: {
                        if ((hn.f & 0x1) > 0) {
                            switch (hm.c) {
                                case 4: {
                                    hm.e = (hm.d & 0xFF);
                                    break Label_0529;
                                }
                                case 3: {
                                    hm.e = (hm.d >> 8 & 0xFF);
                                    break Label_0529;
                                }
                                case 2: {
                                    hm.e = (hm.d >> 16 & 0xFF);
                                    break Label_0529;
                                }
                                default: {
                                    hm.e = (hm.d >> 24 & 0xFF);
                                    break Label_0529;
                                }
                            }
                        }
                        else {
                            switch (hm.c) {
                                case 4: {
                                    hm.e = (hm.d >> 24 & 0xFF);
                                    break Label_0529;
                                }
                                case 3: {
                                    hm.e = (hm.d >> 16 & 0xFF);
                                    break Label_0529;
                                }
                                case 2: {
                                    hm.e = (hm.d >> 8 & 0xFF);
                                    break Label_0529;
                                }
                                default: {
                                    hm.e = (hm.d & 0xFF);
                                    break Label_0529;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                        if ((hn.f & 0x1) > 0) {
                            switch (hm.c) {
                                case 2: {
                                    hm.j = new Integer[2];
                                    ((Integer[])hm.j)[0] = new Integer(hm.d & 0xFFFF);
                                    ((Integer[])hm.j)[1] = new Integer(hm.d >> 16 & 0xFFFF);
                                    break Label_0529;
                                }
                                default: {
                                    hm.e = (hm.d & 0xFFFF);
                                    break Label_0529;
                                }
                            }
                        }
                        else {
                            switch (hm.c) {
                                case 2: {
                                    hm.j = new Integer[2];
                                    ((Integer[])hm.j)[0] = new Integer(hm.d >> 16 & 0xFFFF);
                                    ((Integer[])hm.j)[1] = new Integer(hm.d & 0xFFFF);
                                    break Label_0529;
                                }
                                default: {
                                    hm.e = (hm.d >> 16 & 0xFFFF);
                                    break Label_0529;
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        hm.e = hm.d;
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < sa.b.length; ++i) {
                    if (sa.b[i] == hm.a) {
                        hm.j = this.a(hp, hm, o);
                        break;
                    }
                }
            }
        }
    }
    
    private final Object a(final hp hp, final hm hm, final Object o) throws Exception {
        Object trim = null;
        final long f = hp.f();
        if (hm.d < 0) {
            return null;
        }
        hp.a((long)hm.d);
        switch (hm.b) {
            case 1: {
                final Integer[] array = new Integer[hm.c];
                for (int i = 0; i < hm.c; ++i) {
                    array[i] = new Integer(hp.c());
                }
                trim = array;
                break;
            }
            case 2: {
                final int n = hm.c * sa.a[hm.b];
                final byte[] array2 = new byte[n];
                hp.a(array2, 0, n);
                trim = new String(array2).trim();
                break;
            }
            case 3: {
                final Integer[] array3 = new Integer[hm.c];
                for (int j = 0; j < hm.c; ++j) {
                    array3[j] = new Integer(hp.d());
                }
                trim = array3;
                break;
            }
            case 4: {
                boolean b = true;
                if (hm.c > 100) {
                    try {
                        if (this.c == null) {
                            this.c = q.a(o, this.a);
                        }
                        final String n2 = this.c.n();
                        final byte[] array4 = new byte[hm.c * 4];
                        final ac ac = new ac(n2, true, false, 0, o, this.a);
                        hp.a(array4);
                        ac.b(array4);
                        ac.a(o);
                        final ac ac2 = new ac(n2, false, true, array4.length, o, this.a);
                        final hp hp2 = new hp(ac2);
                        hp2.a(hp.b());
                        final Integer[] array5 = new Integer[hm.c];
                        for (int k = 0; k < hm.c; ++k) {
                            array5[k] = new Integer(hp2.e());
                        }
                        trim = array5;
                        hp2.a();
                        ac2.a(o);
                        ji.io.ac.c(n2, this.a);
                        b = false;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (b) {
                    final Integer[] array6 = new Integer[hm.c];
                    for (int l = 0; l < hm.c; ++l) {
                        array6[l] = new Integer(hp.e());
                    }
                    trim = array6;
                    break;
                }
                break;
            }
            default: {
                if (hm.c > 1) {
                    double n3 = hp.e();
                    final int e = hp.e();
                    if (e > 0) {
                        n3 /= e;
                    }
                    trim = new Double(n3);
                    break;
                }
                double n4 = hp.e();
                final int e2 = hp.e();
                if (e2 > 0) {
                    n4 /= e2;
                }
                trim = new Double(n4);
                break;
            }
        }
        hp.a(f);
        return trim;
    }
    
    private ac a(final df df, final dx dx, final int n, final ad ad, final String s, final boolean b) {
        df.i();
        final ax g = df.g();
        final c c = new c("WangOrderedSavedAnnots", true);
        while (g.a()) {
            final dg b2 = df.b(g.b());
            c.a("".concat(String.valueOf(String.valueOf(b2.ce()))), b2);
        }
        final ax d = c.d();
        ac ac = null;
        try {
            ac = new ac(q.a(ad, s).n(), true, false, 0, ad, s);
            ac.b(ej.b());
            while (d.a()) {
                final dg dg = (dg)c.d(d.b());
                if (dg.i(1) == n) {
                    ej.a(dg, dx, ad, s, ac, b);
                }
            }
        }
        catch (Exception ex) {
            ji.io.h.a(s, ex);
        }
        return ac;
    }
    
    public final void a(final dx dx, final ad ad) {
    }
    
    public final void a() {
        try {
            this.a(null, (ad)null);
            this.a = null;
            this.b = null;
            this.c = null;
            try {
                if (this.k != null) {
                    for (int i = 0; i < this.k.length; ++i) {
                        if (this.k[i] != null) {
                            this.k[i].e();
                            this.k[i] = null;
                        }
                    }
                    this.k = null;
                }
            }
            catch (Exception ex) {}
            if (this.e != null) {
                this.e.c();
                this.e = null;
            }
            gb.f = null;
        }
        catch (Exception ex2) {}
    }
    
    public static final boolean a(final String s) {
        return d.ck(s) && i.c(217);
    }
    
    static {
        gb.f = null;
        gb.l = false;
    }
}
