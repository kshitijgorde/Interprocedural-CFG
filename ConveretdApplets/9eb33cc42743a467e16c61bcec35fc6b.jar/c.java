// 
// Decompiled by Procyon v0.5.30
// 

public final class c
{
    public static final int[] z;
    public static final byte[] w;
    
    public static byte[] j(final byte[] array) {
        return j(array, 0, array.length);
    }
    
    public static byte[] j(final byte[] array, final int n, final int n2) {
        final int n3 = n2 % 3;
        final int n4 = n + (n2 - n3);
        final byte[] array2 = new byte[n2 / 3 * 4 + ((n3 != 0) ? 4 : 0)];
        int i;
        int n5;
        for (i = n, n5 = 0; i < n4; i += 3, n5 += 4) {
            final int n6 = (array[i] & 0xFC) >> 2;
            final int n7 = (array[i] & 0x3) << 4 | (array[i + 1] & 0xF0) >> 4;
            final int n8 = (array[i + 1] & 0xF) << 2 | (array[i + 2] & 0xC0) >> 6;
            final byte b = (byte)(array[i + 2] & 0x3F);
            array2[n5] = c.w[n6];
            array2[n5 + 1] = c.w[n7];
            array2[n5 + 2] = c.w[n8];
            array2[n5 + 3] = c.w[b];
        }
        if (n3 != 0) {
            final int n9 = (array[i] & 0xFC) >> 2;
            int n10 = (array[i] & 0x3) << 4;
            int n11 = 64;
            final int n12 = 64;
            if (n3 == 2) {
                n10 |= (array[i + 1] & 0xF0) >> 4;
                n11 = (array[i + 1] & 0xF) << 2;
            }
            array2[n5++] = c.w[n9];
            array2[n5++] = c.w[n10];
            array2[n5++] = c.w[n11];
            array2[n5++] = c.w[n12];
        }
        return array2;
    }
    
    public static byte[] i(final byte[] array) {
        return i(array, 0, array.length);
    }
    
    public static byte[] i(final byte[] array, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final int n5 = n + n2;
        byte[] array2 = new byte[n2 * 3 / 4];
        int i = n;
        int n6 = 0;
        while (i < n5) {
            final int n7 = c.z[array[i]];
            if (n7 < 0) {
                if (array[i] == c.w[64]) {
                    break;
                }
            }
            else {
                n3 = (n3 << 6 | n7);
                n4 += 6;
                if (n4 >= 8) {
                    n4 -= 8;
                    array2[n6++] = (byte)(n3 >> n4 & 0xFF);
                }
            }
            ++i;
        }
        if (array2.length > n6) {
            final byte[] array3 = new byte[n6];
            System.arraycopy(array2, 0, array3, 0, n6);
            array2 = array3;
        }
        return array2;
    }
    
    static {
        z = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
        w = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47, 61 };
    }
}
