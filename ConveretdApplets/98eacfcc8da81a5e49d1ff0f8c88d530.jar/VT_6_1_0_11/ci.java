// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

public final class ci
{
    private static BitSet a;
    private static BitSet b;
    private static byte[] c;
    private static byte[] d;
    private static char[] e;
    private static byte[] f;
    
    public static final String a(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return new String(a(s.getBytes("8859_1")), "8859_1");
        }
        catch (UnsupportedEncodingException ex) {
            throw new Error(ex.toString());
        }
    }
    
    private static byte[] a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[(array.length + 2) / 3 << 2];
        int i = 0;
        int j = 0;
        while (i < array.length - 2) {
            array2[j++] = ci.c[array[i] >>> 2 & 0x3F];
            array2[j++] = ci.c[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
            array2[j++] = ci.c[(array[i + 2] >>> 6 & 0x3) | (array[i + 1] << 2 & 0x3F)];
            array2[j++] = ci.c[array[i + 2] & 0x3F];
            i += 3;
        }
        if (i < array.length) {
            array2[j++] = ci.c[array[i] >>> 2 & 0x3F];
            if (i < array.length - 1) {
                array2[j++] = ci.c[(array[i + 1] >>> 4 & 0xF) | (array[i] << 4 & 0x3F)];
                array2[j++] = ci.c[array[i + 1] << 2 & 0x3F];
            }
            else {
                array2[j++] = ci.c[array[i] << 4 & 0x3F];
            }
        }
        while (j < array2.length) {
            array2[j] = 61;
            ++j;
        }
        return array2;
    }
    
    public static final byte[] a(byte[] array, final int n, int n2, cU[] array2, final boolean b) {
        if (array == null) {
            array = new byte[0];
            n2 = 0;
        }
        if (b && array2 == null) {
            array2 = new cU[0];
        }
        final String string = Integer.toString(n2, 16);
        int n3 = 0;
        if (n2 > 0) {
            n3 = 0 + (string.length() + 2 + n2 + 2);
        }
        if (b) {
            n3 += 3;
            for (int i = 0; i < array2.length; ++i) {
                n3 += array2[i].a().length() + 2 + array2[i].b().length() + 2;
            }
            n3 += 2;
        }
        final byte[] array3 = new byte[n3];
        int n4 = 0;
        if (n2 > 0) {
            final int length = string.length();
            try {
                System.arraycopy(string.getBytes("8859_1"), 0, array3, 0, length);
            }
            catch (UnsupportedEncodingException ex) {
                throw new Error(ex.toString());
            }
            int n5 = length + 0;
            array3[n5++] = 13;
            array3[n5++] = 10;
            System.arraycopy(array, n, array3, n5, n2);
            n4 = n5 + n2;
            array3[n4++] = 13;
            array3[n4++] = 10;
        }
        if (b) {
            array3[n4++] = 48;
            array3[n4++] = 13;
            array3[n4++] = 10;
            for (int j = 0; j < array2.length; ++j) {
                final int length2 = array2[j].a().length();
                try {
                    System.arraycopy(array2[j].a().getBytes("8859_1"), 0, array3, n4, length2);
                }
                catch (UnsupportedEncodingException ex2) {
                    throw new Error(ex2.toString());
                }
                int n6 = n4 + length2;
                array3[n6++] = 58;
                array3[n6++] = 32;
                final int length3 = array2[j].b().length();
                try {
                    System.arraycopy(array2[j].b().getBytes("8859_1"), 0, array3, n6, length3);
                }
                catch (UnsupportedEncodingException ex3) {
                    throw new Error(ex3.toString());
                }
                n4 = n6 + length3;
                array3[n4++] = 13;
                array3[n4++] = 10;
            }
            array3[n4++] = 13;
            array3[n4++] = 10;
        }
        if (n4 != array3.length) {
            throw new Error("Calculated " + array3.length + " bytes but wrote " + n4 + " bytes!");
        }
        return array3;
    }
    
    static final long a(final InputStream inputStream) {
        final byte[] array = new byte[16];
        int n = 0;
        int read;
        while ((read = inputStream.read()) > 0 && (read == 32 || read == 9)) {}
        if (read < 0) {
            throw new EOFException("Premature EOF while reading chunk length");
        }
        final byte[] array2 = array;
        final int n2 = 0;
        ++n;
        array2[n2] = (byte)read;
        int n3;
        while ((n3 = inputStream.read()) > 0 && n3 != 13 && n3 != 10 && n3 != 32 && n3 != 9 && n3 != 59 && n < array.length) {
            array[n++] = (byte)n3;
        }
        while ((n3 == 32 || n3 == 9) && (n3 = inputStream.read()) > 0) {}
        if (n3 == 59) {
            while ((n3 = inputStream.read()) > 0 && n3 != 13 && n3 != 10) {}
        }
        if (n3 < 0) {
            throw new EOFException("Premature EOF while reading chunk length");
        }
        if (n3 != 10 && (n3 != 13 || inputStream.read() != 10)) {
            throw new dh("Didn't find valid chunk length: " + new String(array, 0, n, "8859_1"));
        }
        try {
            return Long.parseLong(new String(array, 0, n, "8859_1").trim(), 16);
        }
        catch (NumberFormatException ex) {
            throw new dh("Didn't find valid chunk length: " + new String(array, 0, n, "8859_1"));
        }
    }
    
    static {
        ci.a = new BitSet(256);
        for (int i = 48; i <= 57; ++i) {
            ci.a.set(i);
        }
        for (int j = 65; j <= 90; ++j) {
            ci.a.set(j);
        }
        for (int k = 97; k <= 122; ++k) {
            ci.a.set(k);
        }
        ci.a.set(43);
        ci.a.set(95);
        ci.a.set(45);
        ci.a.set(46);
        (ci.b = new BitSet(256)).set(33);
        ci.b.set(34);
        ci.b.set(35);
        ci.b.set(36);
        ci.b.set(64);
        ci.b.set(91);
        ci.b.set(92);
        ci.b.set(93);
        ci.b.set(94);
        ci.b.set(96);
        ci.b.set(123);
        ci.b.set(124);
        ci.b.set(125);
        ci.b.set(126);
        ci.c = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        ci.d = new byte[128];
        for (int l = 0; l < ci.c.length; ++l) {
            ci.d[ci.c[l]] = (byte)l;
        }
        ci.e = new char[64];
        for (int n = 0; n < ci.e.length; ++n) {
            ci.e[n] = (char)(n + 32);
        }
        ci.f = new byte[128];
        for (int n2 = 0; n2 < ci.e.length; ++n2) {
            ci.f[ci.e[n2]] = (byte)n2;
        }
    }
}
