// 
// Decompiled by Procyon v0.5.30
// 

package b;

import java.io.IOException;
import java.io.OutputStream;

public class j
{
    public static void if(final float[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            array[i] = 0.0f;
        }
    }
    
    public static void a(final float[] array, final float[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
    }
    
    public static void a(final float[] array, final int n, final float[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i] = array[n + i];
        }
    }
    
    public static short a() {
        return (short)(0xFFFFL & System.currentTimeMillis());
    }
    
    public static byte[] a(final float[] array, final int n) {
        final short[] array2 = new short[80];
        if (n > 80) {
            throw new RuntimeException("error in fwrite16\n");
        }
        for (int i = 0; i < n; ++i) {
            final float n2 = array[i];
            float n3;
            if (n2 >= 0.0f) {
                n3 = n2 + 0.5f;
            }
            else {
                n3 = n2 - 0.5f;
            }
            if (n3 > 32767.0f) {
                n3 = 32767.0f;
            }
            if (n3 < -32768.0f) {
                n3 = -32768.0f;
            }
            array2[i] = (short)n3;
        }
        return a(array2);
    }
    
    public static void a(final float[] array, final int n, final OutputStream outputStream) {
        final short[] array2 = new short[80];
        if (n > 80) {
            throw new RuntimeException("error in fwrite16\n");
        }
        for (int i = 0; i < n; ++i) {
            final float n2 = array[i];
            float n3;
            if (n2 >= 0.0f) {
                n3 = n2 + 0.5f;
            }
            else {
                n3 = n2 - 0.5f;
            }
            if (n3 > 32767.0f) {
                n3 = 32767.0f;
            }
            if (n3 < -32768.0f) {
                n3 = -32768.0f;
            }
            array2[i] = (short)n3;
            try {
                outputStream.write(a(array2[i]));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static byte[] a(final int n) {
        final byte[] array = new byte[2];
        final int n2 = 255;
        array[0] = (byte)(n2 & n);
        array[1] = (byte)((n2 << 8 & n) >> 8);
        return array;
    }
    
    public static short a(final byte[] array) {
        return (short)(0xFFFF & (array[0] | array[1] << 8));
    }
    
    public static short a(final byte b, final byte b2) {
        return (short)(0xFFFF & ((0xFF & b) | (0xFF & b2) << 8));
    }
    
    public static short[] if(final byte[] array) {
        final short[] array2 = new short[array.length / 2];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = a(array[2 * i], array[2 * i + 1]);
        }
        return array2;
    }
    
    public static short[] a(final byte[] array, final int n) {
        final short[] array2 = new short[n / 2];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = a(array[2 * i], array[2 * i + 1]);
        }
        return array2;
    }
    
    public static byte[] a(final short[] array) {
        final byte[] array2 = new byte[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final byte[] a = a(array[i]);
            array2[2 * i] = a[0];
            array2[2 * i + 1] = a[1];
        }
        return array2;
    }
}
