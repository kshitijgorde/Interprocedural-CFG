// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.C;

import java.io.UnsupportedEncodingException;

public class D
{
    private static final byte B = 61;
    private static final byte[] A;
    
    private static int A(final byte[] array, final int n, final byte[] array2, final int n2) {
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((D.A[array[n]] & 0xFF) << 18 | (D.A[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            final int n3 = (D.A[array[n]] & 0xFF) << 18 | (D.A[array[n + 1]] & 0xFF) << 12 | (D.A[array[n + 2]] & 0xFF) << 6;
            array2[n2] = (byte)(n3 >>> 16);
            array2[n2 + 1] = (byte)(n3 >>> 8);
            return 2;
        }
        final int n4 = (D.A[array[n]] & 0xFF) << 18 | (D.A[array[n + 1]] & 0xFF) << 12 | (D.A[array[n + 2]] & 0xFF) << 6 | (D.A[array[n + 3]] & 0xFF);
        array2[n2] = (byte)(n4 >> 16);
        array2[n2 + 1] = (byte)(n4 >> 8);
        array2[n2 + 2] = (byte)n4;
        return 3;
    }
    
    public static byte[] A(final String s) {
        byte[] array;
        try {
            array = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array = s.getBytes();
        }
        final int length = array.length;
        final byte[] array2 = new byte[length * 3 / 4];
        int n = 0;
        final byte[] array3 = new byte[4];
        int n2 = 0;
        for (int i = 0; i < 0 + length; ++i) {
            final byte b = (byte)(array[i] & 0x7F);
            final byte b2 = D.A[b];
            if (b2 >= -5 && b2 >= -1) {
                array3[n2++] = b;
                if (n2 > 3) {
                    n += A(array3, 0, array2, n);
                    n2 = 0;
                    if (b == 61) {
                        break;
                    }
                }
            }
        }
        final byte[] array4 = new byte[n];
        System.arraycopy(array2, 0, array4, 0, n);
        return array4;
    }
    
    static {
        A = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
    }
}
