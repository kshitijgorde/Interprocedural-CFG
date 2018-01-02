// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public final class R
{
    private static final byte[] q;
    private static final byte[] w;
    private static final byte[] e;
    private static final byte[] r;
    private static final byte[] t;
    private static final byte[] y;
    
    private static final byte[] w(final int n) {
        if ((n & 0x10) == 0x10) {
            return R.r;
        }
        if ((n & 0x20) == 0x20) {
            return R.y;
        }
        return R.w;
    }
    
    private static byte[] q(final byte[] array, final int n, final int n2, final byte[] array2, final int n3, int n4) {
        final byte[] array3 = (((n4 = n4) & 0x10) == 0x10) ? R.e : (((n4 & 0x20) == 0x20) ? R.t : R.q);
        final int n5 = ((n2 > 0) ? (array[n] << 24 >>> 8) : 0) | ((n2 > 1) ? (array[n + 1] << 24 >>> 16) : 0) | ((n2 > 2) ? (array[n + 2] << 24 >>> 24) : 0);
        switch (n2) {
            case 3: {
                array2[n3] = array3[n5 >>> 18];
                array2[n3 + 1] = array3[n5 >>> 12 & 0x3F];
                array2[n3 + 2] = array3[n5 >>> 6 & 0x3F];
                array2[n3 + 3] = array3[n5 & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = array3[n5 >>> 18];
                array2[n3 + 1] = array3[n5 >>> 12 & 0x3F];
                array2[n3 + 2] = array3[n5 >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = array3[n5 >>> 18];
                array2[n3 + 1] = array3[n5 >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
            default: {
                return array2;
            }
        }
    }
    
    public static String q(final byte[] array) {
        String q = null;
        try {
            q = q(array, 0, array.length, 0);
        }
        catch (IOException ex) {}
        return q;
    }
    
    private static String q(byte[] q, final int n, final int n2, final int n3) {
        q = q(q, 0, n2, 0);
        try {
            return new String(q, "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            return new String(q);
        }
    }
    
    private static byte[] q(byte[] array, final int n, final int n2, final int n3) {
        if (array == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (n < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + n);
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + n2);
        }
        if (n + n2 > array.length) {
            throw new IllegalArgumentException("Cannot have offset of " + n + " and length of " + n2 + " with array of length " + array.length);
        }
        if ((n3 & 0x2) != 0x0) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            DeflaterOutputStream deflaterOutputStream = null;
            bT bt = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                bt = new bT(byteArrayOutputStream, 0x1 | n3);
                ((GZIPOutputStream)(deflaterOutputStream = new GZIPOutputStream(bt))).write(array, n, n2);
                deflaterOutputStream.close();
            }
            catch (IOException ex) {
                throw ex;
            }
            finally {
                try {
                    deflaterOutputStream.close();
                }
                catch (Exception ex2) {}
                try {
                    bt.close();
                }
                catch (Exception ex3) {}
                try {
                    byteArrayOutputStream.close();
                }
                catch (Exception ex4) {}
            }
            return byteArrayOutputStream.toByteArray();
        }
        final boolean b = (n3 & 0x8) != 0x0;
        int n4 = (n2 / 3 << 2) + ((n2 % 3 > 0) ? 4 : 0);
        if (b) {
            n4 += n4 / 76;
        }
        final byte[] array2 = new byte[n4];
        int i = 0;
        int n5 = 0;
        final int n6 = n2 - 2;
        int n7 = 0;
        while (i < n6) {
            q(array, i + n, 3, array2, n5, n3);
            n7 += 4;
            if (b && n7 >= 76) {
                array2[n5 + 4] = 10;
                ++n5;
                n7 = 0;
            }
            i += 3;
            n5 += 4;
        }
        if (i < n2) {
            q(array, i + n, n2 - i, array2, n5, n3);
            n5 += 4;
        }
        if (n5 <= array2.length - 1) {
            array = new byte[n5];
            System.arraycopy(array2, 0, array, 0, n5);
            return array;
        }
        return array2;
    }
    
    static byte[] q(final int n) {
        return w(n);
    }
    
    static int q(byte[] array, int n, byte[] array2, int n2, int n3) {
        final byte[] array3 = array;
        final int n4 = 0;
        final byte[] array4 = array2;
        final int n5 = 0;
        n3 = n3;
        n2 = n5;
        array2 = array4;
        n = n4;
        array = array3;
        if (array3 == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (array2 == null) {
            throw new NullPointerException("Destination array was null.");
        }
        if (n < 0 || n + 3 >= array.length) {
            throw new IllegalArgumentException("Source array with length " + array.length + " cannot have offset of " + n + " and still process four bytes.");
        }
        if (n2 < 0 || n2 + 2 >= array2.length) {
            throw new IllegalArgumentException("Destination array with length " + array2.length + " cannot have offset of " + n2 + " and still store three bytes.");
        }
        final byte[] w = w(n3);
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((w[array[n]] & 0xFF) << 18 | (w[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            final int n6 = (w[array[n]] & 0xFF) << 18 | (w[array[n + 1]] & 0xFF) << 12 | (w[array[n + 2]] & 0xFF) << 6;
            array2[n2] = (byte)(n6 >>> 16);
            array2[n2 + 1] = (byte)(n6 >>> 8);
            return 2;
        }
        final int n7 = (w[array[n]] & 0xFF) << 18 | (w[array[n + 1]] & 0xFF) << 12 | (w[array[n + 2]] & 0xFF) << 6 | (w[array[n + 3]] & 0xFF);
        array2[n2] = (byte)(n7 >> 16);
        array2[n2 + 1] = (byte)(n7 >> 8);
        array2[n2 + 2] = (byte)n7;
        return 3;
    }
    
    static byte[] q(byte[] array, byte[] array2, int n, int n2) {
        final byte[] array3 = array;
        final byte[] array4 = array2;
        final int n3 = n;
        n2 = n2;
        n = n3;
        array2 = array4;
        array = array3;
        q(array2, 0, n, array, 0, n2);
        return array;
    }
    
    static {
        q = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        w = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        e = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        r = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        t = new byte[] { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
        y = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
    }
}
