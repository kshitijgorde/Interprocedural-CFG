// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

public class ByteUtil
{
    public static byte[] duplicate(final byte[] array) {
        return duplicate(array, 0, array.length);
    }
    
    public static byte[] duplicate(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[i + n];
        }
        return array2;
    }
    
    public static byte[] getBytes(final short n) {
        return new byte[] { (byte)(n >>> 8 & 0xFF), (byte)(n & 0xFF) };
    }
    
    public static short getShort(final byte[] array) {
        return (short)((short)(0xFF & array[0]) << 8 | (short)(0xFF & array[1]));
    }
    
    public static short getShort(final byte[] array, final int n) {
        return (short)((short)(0xFF & array[n]) << 8 | (short)(0xFF & array[n + 1]));
    }
    
    public static boolean bytesEqual(final byte[] array, final byte[] array2, final int n) {
        return bytesEqual(array, 0, array2, 0, n);
    }
    
    public static boolean bytesEqual(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            if (array[i + n] != array2[i + n2]) {
                return false;
            }
        }
        return true;
    }
}
