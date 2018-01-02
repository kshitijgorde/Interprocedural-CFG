// 
// Decompiled by Procyon v0.5.30
// 

package ji.encode;

import ji.util.m;
import ji.io.ac;
import ji.util.d;
import ji.io.h;
import ji.util.i;
import ji.image.dx;
import ji.awt.c;
import ji.v1event.af;
import java.awt.Image;
import ji.util.e;
import ji.document.ad;

public class fp
{
    public static final boolean a(final ad ad) {
        return ad.bi(0) && e.av();
    }
    
    public static final int a(final Image image, final String s, final ad ad, final String s2, final int n, final int n2, final af af, final String s3, final String s4, final c c, int n3, final int n4, final dx dx, final hi hi, final boolean b, final boolean b2, final boolean b3) throws Exception {
        int n5 = 0;
        hi.a = n4;
        hi.b = n4;
        if (b2) {
            n5 = 0;
        }
        else if (b3) {
            n5 = 1;
        }
        else {
            if (b) {
                if (!i.c(160) || a(ad)) {}
                n5 = 1;
            }
            if (n5 == 0) {
                if (dx.f()) {
                    n5 = 1;
                }
                else if (n3 == 6) {
                    n5 = 1;
                }
                else if (n4 == 24) {
                    n5 = 1;
                }
                else {
                    n5 = 0;
                }
            }
        }
        if (n5 != 0) {
            if (a()) {
                h.d(s2, "TIFF writer: Encoding JPEG...");
            }
            n3 = 6;
            final Object a2 = d.a2("ji.jpeg.jiJpegEncoder");
            if (a2 == null) {
                h.d(s2, "Cannot load ji.jpeg.jiJpegEncoder");
                throw new Exception("Cannot load ji.jpeg.jiJpegEncoder");
            }
            final ac ac = new ac(s, true, true, 102400, false, ad, true, s2);
            final m m = new m(a2);
            final Object[] array = new Object[13];
            int n6 = 0;
            array[n6++] = image;
            array[n6++] = ac;
            array[n6++] = ad;
            array[n6++] = s2;
            array[n6++] = new Float(n / 100.0);
            array[n6++] = new Integer(n2);
            array[n6++] = af;
            array[n6++] = s3;
            array[n6++] = s4;
            array[n6++] = c;
            array[n6++] = new Integer(n4);
            array[n6++] = hi;
            array[n6++] = dx;
            m.a("encode", array);
            ac.a(ad);
            if (m != null) {
                m.b();
            }
        }
        else {
            if (a()) {
                h.d(s2, "TIFF writer: Encoding LZW...");
            }
            n3 = 5;
            final ac ac2 = new ac(s, true, false, 0, false, ad, true, s2);
            final m i = new m(d.a2("ji.lzw.jiLZWEncoder"));
            final Object[] array2 = new Object[12];
            int n7 = 0;
            array2[n7++] = image;
            array2[n7++] = ac2;
            array2[n7++] = ad;
            array2[n7++] = s2;
            array2[n7++] = new Integer(n2);
            array2[n7++] = af;
            array2[n7++] = s3;
            array2[n7++] = s4;
            array2[n7++] = c;
            array2[n7++] = new Integer(n4);
            array2[n7++] = hi;
            array2[n7++] = dx;
            i.a("encode", array2);
            ac2.a(ad);
            if (i != null) {
                i.b();
            }
        }
        return n3;
    }
    
    private static final boolean a() {
        return i.c(126) || i.c(5);
    }
}
