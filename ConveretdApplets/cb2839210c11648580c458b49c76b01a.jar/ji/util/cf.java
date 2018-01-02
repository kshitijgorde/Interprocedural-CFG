// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.io.UnsupportedEncodingException;

public class cf
{
    private static final byte[] a;
    private static final byte[] b;
    private static final byte[] c;
    
    private static byte[] a(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) {
        final int n4 = ((n2 > 0) ? (array[n] << 24 >>> 8) : 0) | ((n2 > 1) ? (array[n + 1] << 24 >>> 16) : 0) | ((n2 > 2) ? (array[n + 2] << 24 >>> 24) : 0);
        switch (n2) {
            case 3: {
                array2[n3] = cf.a[n4 >>> 18];
                array2[n3 + 1] = cf.a[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = cf.a[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = cf.a[n4 & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = cf.a[n4 >>> 18];
                array2[n3 + 1] = cf.a[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = cf.a[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = cf.a[n4 >>> 18];
                array2[n3 + 1] = cf.a[n4 >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
            default: {
                return array2;
            }
        }
    }
    
    public static String a(final byte[] array, final int n) {
        return a(array, 0, array.length, n);
    }
    
    public static String a(final byte[] array, final int n, final int n2, final int n3) {
        final boolean b = (n3 & 0x8) == 0x0;
        final int n4 = n2 * 4 / 3;
        final byte[] array2 = new byte[n4 + ((n2 % 3 > 0) ? 4 : 0) + (b ? (n4 / 76) : 0)];
        int i = 0;
        int n5 = 0;
        final int n6 = n2 - 2;
        int n7 = 0;
        while (i < n6) {
            a(array, i + n, 3, array2, n5);
            n7 += 4;
            if (b && n7 == 76) {
                array2[n5 + 4] = 10;
                ++n5;
                n7 = 0;
            }
            i += 3;
            n5 += 4;
        }
        if (i < n2) {
            a(array, i + n, n2 - i, array2, n5);
            n5 += 4;
        }
        try {
            return new String(array2, 0, n5, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return new String(array2, 0, n5);
        }
    }
    
    private static int a(final byte[] array, final int n, final byte[] array2, final int n2) {
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((cf.c[array[n]] & 0xFF) << 18 | (cf.c[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            final int n3 = (cf.c[array[n]] & 0xFF) << 18 | (cf.c[array[n + 1]] & 0xFF) << 12 | (cf.c[array[n + 2]] & 0xFF) << 6;
            array2[n2] = (byte)(n3 >>> 16);
            array2[n2 + 1] = (byte)(n3 >>> 8);
            return 2;
        }
        try {
            final int n4 = (cf.c[array[n]] & 0xFF) << 18 | (cf.c[array[n + 1]] & 0xFF) << 12 | (cf.c[array[n + 2]] & 0xFF) << 6 | (cf.c[array[n + 3]] & 0xFF);
            array2[n2] = (byte)(n4 >> 16);
            array2[n2 + 1] = (byte)(n4 >> 8);
            array2[n2 + 2] = (byte)n4;
            return 3;
        }
        catch (Exception ex) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(array[n]).append(": ").append(cf.c[array[n]]))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(array[n + 1]).append(": ").append(cf.c[array[n + 1]]))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(array[n + 2]).append(": ").append(cf.c[array[n + 2]]))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("").append(array[n + 3]).append(": ").append(cf.c[array[n + 3]]))));
            return -1;
        }
    }
    
    public static byte[] b(byte[] array, int n, int n2, final int n3) {
        if ((n3 & 0x8) != 0x0) {
            final byte[] array2 = new byte[n2];
            int n4 = 0;
            for (int i = n; i < n2; ++i) {
                if (array[i] > 32) {
                    array2[n4++] = array[i];
                }
            }
            if (n2 - n4 > 0) {
                array = array2;
                n = 0;
                n2 = n4;
            }
        }
        final byte[] array3 = new byte[n2 * 3 / 4];
        int n5 = 0;
        final byte[] array4 = new byte[4];
        int n6 = 0;
        for (int j = n; j < n + n2; ++j) {
            final byte b = (byte)(array[j] & 0x7F);
            final byte b2 = cf.c[b];
            if (b2 < -5) {
                System.err.println(String.valueOf(String.valueOf(new StringBuffer("Bad Base64 input character at ").append(j).append(": ").append(array[j]).append("(decimal)"))));
                return null;
            }
            if (b2 >= -1) {
                array4[n6++] = b;
                if (n6 > 3) {
                    n5 += a(array4, 0, array3, n5);
                    n6 = 0;
                    if (b == 61) {
                        break;
                    }
                }
            }
        }
        final byte[] array5 = new byte[n5];
        System.arraycopy(array3, 0, array5, 0, n5);
        return array5;
    }
    
    static {
        b = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        byte[] a2;
        try {
            a2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            a2 = cf.b;
        }
        a = a2;
        c = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
    }
}
