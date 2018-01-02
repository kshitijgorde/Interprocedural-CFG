// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public class ByteArrayUtil
{
    public static byte[] conc(final byte[] array, final byte[] array2) {
        return conc(array, array2, array2.length);
    }
    
    public static byte[] conc(final byte[] array, final byte[] array2, final byte[] array3) {
        final byte[] array4 = new byte[array.length + array2.length + array3.length];
        System.arraycopy(array, 0, array4, 0, array.length);
        System.arraycopy(array2, 0, array4, array.length, array2.length);
        System.arraycopy(array3, 0, array4, array.length + array2.length, array3.length);
        return array4;
    }
    
    public static byte[] conc(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4) {
        final byte[] array5 = new byte[array.length + array2.length + array3.length + array4.length];
        System.arraycopy(array, 0, array5, 0, array.length);
        System.arraycopy(array2, 0, array5, array.length, array2.length);
        final int n = array.length + array2.length;
        System.arraycopy(array3, 0, array5, n, array3.length);
        System.arraycopy(array4, 0, array5, n + array3.length, array4.length);
        return array5;
    }
    
    public static byte[] conc(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final byte[] array5) {
        final byte[] array6 = new byte[array.length + array2.length + array3.length + array4.length + array5.length];
        System.arraycopy(array, 0, array6, 0, array.length);
        System.arraycopy(array2, 0, array6, array.length, array2.length);
        final int n = array.length + array2.length;
        System.arraycopy(array3, 0, array6, n, array3.length);
        final int n2 = n + array3.length;
        System.arraycopy(array4, 0, array6, n2, array4.length);
        System.arraycopy(array5, 0, array6, n2 + array4.length, array5.length);
        return array6;
    }
    
    public static byte[] conc(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final byte[] array5, final byte[] array6) {
        final byte[] array7 = new byte[array.length + array2.length + array3.length + array4.length + array5.length + array6.length];
        System.arraycopy(array, 0, array7, 0, array.length);
        System.arraycopy(array2, 0, array7, array.length, array2.length);
        final int n = array.length + array2.length;
        System.arraycopy(array3, 0, array7, n, array3.length);
        final int n2 = n + array3.length;
        System.arraycopy(array4, 0, array7, n2, array4.length);
        final int n3 = n2 + array4.length;
        System.arraycopy(array5, 0, array7, n3, array5.length);
        System.arraycopy(array6, 0, array7, n3 + array5.length, array6.length);
        return array7;
    }
    
    public static byte[] conc(final byte[] array, final byte[] array2, final int n) {
        if (array == null || array.length == 0) {
            return copy(array2, 0, n);
        }
        final byte[] array3 = new byte[array.length + n];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, n);
        return array3;
    }
    
    public static byte[] inttobyte(final long n, final int n2) {
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[n2 - i - 1] = (byte)((n & 255 << i * 8) >> i * 8);
        }
        return array;
    }
    
    public static byte[] copy(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    public static boolean equal(final byte[] array, final byte[] array2) {
        if (array == null && array2 == null) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean equal(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        if (n3 <= 0) {
            return true;
        }
        if (array == null || array2 == null || n < 0 || n2 < 0) {
            return false;
        }
        if (n + n3 > array.length || n2 + n3 > array2.length) {
            return false;
        }
        for (int i = 0; i < n3; ++i) {
            if (array[n + i] != array2[n2 + i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean equal(final char[] array, final int n, final char[] array2, final int n2, final int n3) {
        if (n3 <= 0) {
            return true;
        }
        if (array == null || array2 == null || n < 0 || n2 < 0) {
            return false;
        }
        if (n + n3 > array.length || n2 + n3 > array2.length) {
            return false;
        }
        for (int i = 0; i < n3; ++i) {
            if (array[n + i] != array2[n2 + i]) {
                return false;
            }
        }
        return true;
    }
    
    public static void bzero(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n + n2; ++i) {
            array[i] = 0;
        }
    }
    
    public static void bzero(final char[] array, final int n, final int n2) {
        for (int i = n; i < n + n2; ++i) {
            array[i] = '\0';
        }
    }
    
    public static void byteArrayToCharArray(final byte[] array, final int n, final char[] array2, int n2, final int n3) {
        int n4 = 1;
        for (int i = n; i < n + n3; ++i) {
            if (n4 != 0) {
                array2[n2] = (char)array[n];
                final int n5 = n2;
                array2[n5] <<= 8;
                final int n6 = n2;
                array2[n6] &= '\uff00';
                n4 = 0;
            }
            else {
                final int n7 = n2;
                array2[n7] |= (char)(array[n] & 0xFF);
                ++n2;
                n4 = 1;
            }
        }
    }
    
    public static void charArrayToByteArray(final char[] array, int n, final byte[] array2, final int n2, final int n3) {
        int n4 = 1;
        for (int i = n2; i < n2 + n3; ++i) {
            if (n4 != 0) {
                array2[i] = (byte)(array[n] >> 8 & '\u00ff');
                n4 = 0;
            }
            else {
                final int n5 = i;
                array2[n5] |= (byte)(array[n] & '\u00ff');
                ++n;
                n4 = 1;
            }
        }
    }
}
