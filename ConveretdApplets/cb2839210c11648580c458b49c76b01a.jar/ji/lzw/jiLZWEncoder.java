// 
// Decompiled by Procyon v0.5.30
// 

package ji.lzw;

import ji.v1event.a6;
import ji.util.c3;
import ji.image.c2;
import java.awt.Component;
import ji.util.d;
import java.awt.image.ImageProducer;
import ji.render.c1;
import ji.image.dx;
import ji.encode.hi;
import ji.awt.c;
import ji.v1event.af;
import ji.document.ad;
import ji.io.ac;
import java.awt.Image;

public class jiLZWEncoder
{
    private static final int BITS = 12;
    private static final int HASHING_SHIFT = 4;
    private static final int MAX_VALUE = 4095;
    private static final int MAX_CODE = 4094;
    private static final int TABLE_SIZE = 5021;
    private static final int EOF = -1;
    private int output_bit_count;
    private int output_bit_buffer;
    private short[] code_value;
    private short[] prefix_code;
    private short[] append_character;
    private int encodedPixelDepth;
    private static final boolean newPixelGrabMethod = true;
    
    public jiLZWEncoder() {
        this.output_bit_count = 0;
        this.output_bit_buffer = 0;
        this.code_value = new short[5021];
        this.prefix_code = new short[5021];
        this.append_character = new short[5021];
        this.encodedPixelDepth = 0;
    }
    
    public final int getEncodedPixelDepth() {
        return this.encodedPixelDepth;
    }
    
    public final void releaseResources() {
    }
    
    public static final void encode(final Image image, final ac ac, final ad ad, final String s, final int n, final af af, final String s2, final String s3, final c c, final int n2, final hi hi, final dx dx) throws Exception {
        final ImageProducer source = image.getSource();
        int n3;
        int n4;
        if (source instanceof c1) {
            final c1 c2 = (c1)source;
            n3 = c2.a();
            n4 = c2.b();
        }
        else {
            n3 = dx.m;
            n4 = dx.n;
        }
        ac.b(n3);
        ac.b(n4);
        encodeBand(ac, ad, s, image, c, n3, n4, n, af, s2, s3, n2, hi);
    }
    
    private static final void encodeBand(final ac ac, final ad ad, final String s, final Image image, final c c, final int n, final int n2, int n3, final af af, final String s2, final String s3, final int a, final hi hi) throws Exception {
        try {
            if (n3 == -1) {
                n3 = n2;
            }
            final int min = Math.min(n3, n2);
            int i = n2;
            int n4 = 0;
            final int n5 = 10;
            int n6 = 0;
            final String a2 = ac.a();
            int min2 = Math.min(Math.max(Math.min(min, 524288 / (4 * n)), 10), n2);
            final byte[] array = new byte[n * min2 * 3];
            int[] array2 = null;
            byte[] array3 = null;
            final ImageProducer source = image.getSource();
            c1 c2 = null;
            hi.a = a;
            hi.b = 24;
            int n7 = 1;
            int n8 = 0;
            while (i > 0) {
                if (a == 24) {
                    if (source instanceof c1) {
                        System.currentTimeMillis();
                        if (n7 != 0 && af != null && s3 != null) {
                            setStatus(ad, af, s3, n6);
                        }
                        if (c2 == null) {
                            c2 = (c1)source;
                            c2.g();
                        }
                        if (array3 == null) {
                            array3 = new byte[n * min2 * 4];
                        }
                        c2.a(0, n4, n, min2, array3);
                        int n9 = 0;
                        for (int j = 0; j < array3.length; j += 4) {
                            array[n9 + 0] = array3[j + 1];
                            array[n9 + 1] = array3[j + 2];
                            array[n9 + 2] = array3[j + 3];
                            n9 += 3;
                        }
                    }
                    else {
                        if (array2 == null) {
                            array2 = new int[n * min2];
                        }
                        if (!ad.bi(23)) {
                            d.a(ad, image);
                        }
                        final c2 c3 = new c2(s, ad, af, source, 0, n4, n, min2, 0, array2, a);
                        c3.a();
                        c3.i();
                        for (int k = 0; k < array2.length; ++k) {
                            final int n10 = array2[k];
                            final int n11 = k * 3;
                            array[n11 + 0] = (byte)(n10 >> 16 & 0xFF);
                            array[n11 + 1] = (byte)(n10 >> 8 & 0xFF);
                            array[n11 + 2] = (byte)(n10 & 0xFF);
                        }
                    }
                }
                else if (a == 8) {
                    if (!ad.bi(23)) {
                        d.a(ad, image);
                    }
                    boolean b = true;
                    if (source instanceof c1 && ((c1)source).c() == 24) {
                        b = false;
                        System.currentTimeMillis();
                        if (n7 != 0 && af != null && s3 != null) {
                            setStatus(ad, af, s3, n6);
                        }
                        if (c2 == null) {
                            c2 = (c1)source;
                            c2.g();
                        }
                        if (array3 == null) {
                            array3 = new byte[n * min2 * 4];
                        }
                        c2.a(0, n4, n, min2, array3);
                        int n12 = 0;
                        for (int l = 0; l < array3.length; l += 4) {
                            array[n12 + 0] = array3[l + 1];
                            array[n12 + 1] = array3[l + 2];
                            array[n12 + 2] = array3[l + 3];
                            n12 += 3;
                        }
                    }
                    if (b) {
                        if (!ad.bi(23)) {
                            d.a(ad, image);
                        }
                        final c2 c4 = new c2(s, ad, af, source, 0, n4, n, min2, 0, array, a, false);
                        c4.a();
                        hi.b = 8;
                        if (hi.c == null) {
                            hi.c = c4.d();
                        }
                        c4.i();
                    }
                }
                else if (a == 1 || a == 2) {
                    boolean b2 = true;
                    if (ad.bi(23) && source instanceof c1) {
                        b2 = false;
                        if (((c1)source).c() == 24) {
                            if (n7 != 0 && af != null && s3 != null) {
                                setStatus(ad, af, s3, n6);
                            }
                            if (c2 == null) {
                                c2 = (c1)source;
                                c2.g();
                            }
                            if (array3 == null) {
                                array3 = new byte[n * min2 * 4];
                            }
                            c2.a(0, n4, n, min2, array3);
                            int n13 = 0;
                            for (int n14 = 0; n14 < array3.length; n14 += 4) {
                                array[n13 + 0] = array3[n14 + 1];
                                array[n13 + 1] = array3[n14 + 2];
                                array[n13 + 2] = array3[n14 + 3];
                                n13 += 3;
                            }
                        }
                        else {
                            if (array2 == null) {
                                array2 = new int[n * min2];
                            }
                            final c2 c5 = new c2(s, ad, af, source, 0, n4, n, min2, 0, array2, a);
                            c5.a();
                            c5.i();
                            for (int n15 = 0; n15 < array2.length; ++n15) {
                                final int n16 = array2[n15];
                                final int n17 = n15 * 3;
                                array[n17 + 0] = (byte)(n16 >> 16 & 0xFF);
                                array[n17 + 1] = (byte)(n16 >> 8 & 0xFF);
                                array[n17 + 2] = (byte)(n16 & 0xFF);
                            }
                        }
                    }
                    if (b2) {
                        if (array2 == null) {
                            array2 = new int[n * min2];
                        }
                        d.a(ad, image);
                        final c3 a3 = d.a(image, s, ad);
                        d.a(ad, s, image, a3, 0, n4, n, min2, n, array2);
                        d.a(image, a3);
                        for (int n18 = 0; n18 < array2.length; ++n18) {
                            final int n19 = array2[n18];
                            final int n20 = n18 * 3;
                            array[n20 + 0] = (byte)(n19 >> 16 & 0xFF);
                            array[n20 + 1] = (byte)(n19 >> 8 & 0xFF);
                            array[n20 + 2] = (byte)(n19 & 0xFF);
                        }
                    }
                }
                else {
                    if (!ad.bi(23)) {
                        d.a(ad, image);
                    }
                    if (array3 == null) {
                        array3 = new byte[n * min2 * 4];
                    }
                    final c2 c6 = new c2(s, ad, af, source, 0, n4, n, min2, 0, array3, a, false);
                    c6.a();
                    c6.i();
                    int n21 = 0;
                    for (int n22 = 0; n22 < array3.length; n22 += 4) {
                        array[n21 + 0] = array3[n22 + 1];
                        array[n21 + 1] = array3[n22 + 2];
                        array[n21 + 2] = array3[n22 + 3];
                        n21 += 3;
                    }
                }
                n7 = 0;
                final String concat = String.valueOf(String.valueOf(a2)).concat("_1");
                final nt nt = new nt();
                if (!false) {
                    if (hi.b == 8) {
                        nt.a(array, 0, n, min2, new int[] { 8 }, n, concat, ad, s);
                    }
                    else {
                        nt.a(array, 0, n, min2, new int[] { 8, 8, 8 }, n * 3, concat, ad, s);
                    }
                    copyDataFromFile(concat, ac, ad, s, min2, c);
                }
                else {
                    byte[] array4;
                    if (hi.b == 8) {
                        array4 = nt.a(array, 0, n, min2, new int[] { 8 }, n, ad, s);
                    }
                    else {
                        array4 = nt.a(array, 0, n, min2, new int[] { 8, 8, 8 }, n * 3, ad, s);
                    }
                    copyDataFromArray(array4, ac, ad, s, min2, c);
                }
                i -= min2;
                n4 += min2;
                if (i < min2) {
                    min2 = i;
                }
                if (af != null) {
                    final int n23 = 100 * n4 / n2;
                    if (n23 >= n6 + n5) {
                        n6 = n23;
                        setStatus(ad, af, s2, n23);
                    }
                }
                ++n8;
            }
            if (c2 != null) {
                c2.f();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    private static void setStatus(final ad ad, final af af, final String s, final int n) {
        if (af != null) {
            final a6 a6 = new a6(ad, 17, s);
            a6.a(true);
            a6.a(n);
            a6.a(s);
            af.a(a6);
        }
    }
    
    private static void copyDataFromFile(final String s, final ac ac, final ad ad, final String s2, final int n, final c c) throws Exception {
        final ac ac2 = new ac(s, false, false, 0, false, ad, false, s2);
        final int n2 = (int)ac2.v();
        final byte[] array = new byte[Math.min(10240, n2)];
        ac.b(n);
        ac.b((int)ac2.v());
        for (int i = ac2.a(array); i > 0; i = ac2.a(array)) {
            ac.b(array, 0, i);
        }
        ac2.a(ad);
        ac.c(s, s2);
        c.c(new Integer(n));
        c.c(new Integer(n2));
    }
    
    private static void copyDataFromArray(final byte[] array, final ac ac, final ad ad, final String s, final int n, final c c) throws Exception {
        final int length = array.length;
        ac.b(n);
        ac.b(length);
        ac.b(array, 0, length);
        c.c(new Integer(n));
        c.c(new Integer(length));
    }
}
