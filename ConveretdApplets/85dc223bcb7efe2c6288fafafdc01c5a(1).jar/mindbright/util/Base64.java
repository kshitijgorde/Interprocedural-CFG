// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.util;

public final class Base64
{
    private static final int[] fromBase64;
    private static final byte[] toBase64;
    private static final int PAD_CHAR = 64;
    
    public static byte[] encode(final byte[] data) {
        return encode(data, 0, data.length);
    }
    
    public static byte[] encode(final byte[] data, final int offset, final int length) {
        final int r = length % 3;
        final int n = offset + (length - r);
        final byte[] encoded = new byte[length / 3 * 4 + ((r != 0) ? 4 : 0)];
        int i;
        int j;
        for (i = offset, j = 0; i < n; i += 3, j += 4) {
            final int x1 = (data[i] & 0xFC) >> 2;
            final int x2 = (data[i] & 0x3) << 4 | (data[i + 1] & 0xF0) >> 4;
            final int x3 = (data[i + 1] & 0xF) << 2 | (data[i + 2] & 0xC0) >> 6;
            final int x4 = data[i + 2] & 0x3F;
            encoded[j] = Base64.toBase64[x1];
            encoded[j + 1] = Base64.toBase64[x2];
            encoded[j + 2] = Base64.toBase64[x3];
            encoded[j + 3] = Base64.toBase64[x4];
        }
        if (r != 0) {
            final int x1 = (data[i] & 0xFC) >> 2;
            int x2 = (data[i] & 0x3) << 4;
            int x3 = 64;
            final int x4 = 64;
            if (r == 2) {
                x2 |= (data[i + 1] & 0xF0) >> 4;
                x3 = (data[i + 1] & 0xF) << 2;
            }
            encoded[j++] = Base64.toBase64[x1];
            encoded[j++] = Base64.toBase64[x2];
            encoded[j++] = Base64.toBase64[x3];
            encoded[j++] = Base64.toBase64[x4];
        }
        return encoded;
    }
    
    public static byte[] decode(final byte[] data) {
        return decode(data, 0, data.length);
    }
    
    public static byte[] decode(final byte[] encoded, final int offset, final int length) {
        int v = 0;
        int bits = 0;
        final int n = offset + length;
        byte[] data = new byte[length * 3 / 4];
        int i = offset;
        int j = 0;
        while (i < n) {
            final int c = Base64.fromBase64[encoded[i]];
            if (c < 0) {
                if (encoded[i] == Base64.toBase64[64]) {
                    break;
                }
            }
            else {
                v = (v << 6 | c);
                bits += 6;
                if (bits >= 8) {
                    bits -= 8;
                    data[j++] = (byte)(v >> bits & 0xFF);
                }
            }
            ++i;
        }
        if (data.length > j) {
            final byte[] tmp = new byte[j];
            System.arraycopy(data, 0, tmp, 0, j);
            data = tmp;
        }
        return data;
    }
    
    static {
        fromBase64 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
        toBase64 = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47, 61 };
    }
}
