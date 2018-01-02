// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class ai
{
    public static void a(final int[] array, final byte[] array2) {
        array2[0] = (byte)(array[0] & 0xFF);
        array2[1] = (byte)((array[1] & 0x3FF) >> 2);
        array2[2] = (byte)((array[1] & 0x3) << 6 | (array[2] >> 2 & 0x3F));
        array2[3] = (byte)((array[2] & 0x3) << 6 | (array[3] & 0x1) << 5 | (array[4] & 0x1FFF) >> 8);
        array2[4] = (byte)(array[4] & 0xFF);
        array2[5] = (byte)((array[5] & 0xF) << 4 | (array[6] & 0x7F) >> 3);
        array2[6] = (byte)((array[6] & 0x7) << 5 | (array[7] & 0x1F));
        array2[7] = (byte)((array[8] & 0x1FFF) >> 5);
        array2[8] = (byte)((array[8] & 0x1F) << 3 | (array[9] & 0xF) >> 1);
        array2[9] = (byte)((array[9] & 0x1) << 7 | (array[10] & 0x7F));
    }
    
    public static void a(final int[] array, final short[] array2) {
        int n = 0;
        array2[n++] = 27425;
        array2[n++] = 80;
        for (int i = 0; i < 11; i = (short)(i + 1)) {
            a(array[i], c.b[i], array2, n);
            n += c.b[i];
        }
    }
    
    public static byte[] a(final short[] array) {
        final byte[] array2 = new byte[10];
        for (int i = 0; i < 80; ++i) {
            if (array[i + 2] == 129) {
                array2[i / 8] = (byte)(0xFF & (array2[i / 8] | 1 << 7 - i % 8));
            }
        }
        return array2;
    }
    
    public static short[] a(final byte[] array) {
        final short[] array2 = new short[82];
        array2[0] = 27425;
        array2[1] = 80;
        for (int i = 0; i < 80; ++i) {
            if ((array[i / 8] & 1 << 7 - i % 8) != 0x0) {
                array2[i + 2] = 129;
            }
            else {
                array2[i + 2] = 127;
            }
        }
        return array2;
    }
    
    static void a(int n, final int n2, final short[] array, final int n3) {
        int n4 = n3 + n2;
        for (int i = 0; i < n2; i = (short)(i + 1)) {
            if ((short)(n & 0x1) == 0) {
                array[--n4] = 127;
            }
            else {
                array[--n4] = 129;
            }
            n >>= 1;
        }
    }
    
    public static void a(final short[] array, int n, final int[] array2, final int n2) {
        for (int i = 0; i < 11; i = (short)(i + 1)) {
            array2[i + n2] = a(c.b[i], array, n);
            n += c.b[i];
        }
    }
    
    static short a(final int n, final short[] array, int n2) {
        short n3 = 0;
        for (int i = 0; i < n; i = (short)(i + 1)) {
            n3 <<= 1;
            if (array[n2++] == 129) {
                ++n3;
            }
        }
        return n3;
    }
}
