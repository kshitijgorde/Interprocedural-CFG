// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

public final class Base64
{
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 64;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int SIXBIT = 6;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final byte PAD = 61;
    private static byte[] base64Alphabet;
    private static byte[] lookUpBase64Alphabet;
    
    public static boolean isBase64(final String s) {
        return isArrayByteBase64(s.getBytes());
    }
    
    public static boolean isBase64(final byte b) {
        return b == 61 || Base64.base64Alphabet[b] != -1;
    }
    
    public static boolean isArrayByteBase64(final byte[] array) {
        final int length = array.length;
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; ++i) {
            if (!isBase64(array[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static byte[] encode(final byte[] array) {
        final int n = array.length * 8;
        final int n2 = n % 24;
        final int n3 = n / 24;
        byte[] array2;
        if (n2 != 0) {
            array2 = new byte[(n3 + 1) * 4];
        }
        else {
            array2 = new byte[n3 * 4];
        }
        int i;
        for (i = 0; i < n3; ++i) {
            final int n4 = i * 3;
            final byte b = array[n4];
            final byte b2 = array[n4 + 1];
            final byte b3 = array[n4 + 2];
            final byte b4 = (byte)(b2 & 0xF);
            final byte b5 = (byte)(b & 0x3);
            final int n5 = i * 4;
            final byte b6 = ((b & 0xFFFFFF80) == 0x0) ? ((byte)(b >> 2)) : ((byte)(b >> 2 ^ 0xC0));
            final byte b7 = ((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0));
            final byte b8 = ((b3 & 0xFFFFFF80) == 0x0) ? ((byte)(b3 >> 6)) : ((byte)(b3 >> 6 ^ 0xFC));
            array2[n5] = Base64.lookUpBase64Alphabet[b6];
            array2[n5 + 1] = Base64.lookUpBase64Alphabet[b7 | b5 << 4];
            array2[n5 + 2] = Base64.lookUpBase64Alphabet[b4 << 2 | b8];
            array2[n5 + 3] = Base64.lookUpBase64Alphabet[b3 & 0x3F];
        }
        final int n6 = i * 3;
        final int n7 = i * 4;
        if (n2 == 8) {
            final byte b9 = array[n6];
            final byte b10 = (byte)(b9 & 0x3);
            array2[n7] = Base64.lookUpBase64Alphabet[((b9 & 0xFFFFFF80) == 0x0) ? ((byte)(b9 >> 2)) : ((byte)(b9 >> 2 ^ 0xC0))];
            array2[n7 + 1] = Base64.lookUpBase64Alphabet[b10 << 4];
            array2[n7 + 3] = (array2[n7 + 2] = 61);
        }
        else if (n2 == 16) {
            final byte b11 = array[n6];
            final byte b12 = array[n6 + 1];
            final byte b13 = (byte)(b12 & 0xF);
            final byte b14 = (byte)(b11 & 0x3);
            final byte b15 = ((b11 & 0xFFFFFF80) == 0x0) ? ((byte)(b11 >> 2)) : ((byte)(b11 >> 2 ^ 0xC0));
            final byte b16 = ((b12 & 0xFFFFFF80) == 0x0) ? ((byte)(b12 >> 4)) : ((byte)(b12 >> 4 ^ 0xF0));
            array2[n7] = Base64.lookUpBase64Alphabet[b15];
            array2[n7 + 1] = Base64.lookUpBase64Alphabet[b16 | b14 << 4];
            array2[n7 + 2] = Base64.lookUpBase64Alphabet[b13 << 2];
            array2[n7 + 3] = 61;
        }
        return array2;
    }
    
    public static byte[] decode(final byte[] array) {
        if (array.length == 0) {
            return new byte[0];
        }
        final int n = array.length / 4;
        int n2 = 0;
        int length = array.length;
        while (array[length - 1] == 61) {
            if (--length == 0) {
                return new byte[0];
            }
        }
        final byte[] array2 = new byte[length - n];
        for (int i = 0; i < n; ++i) {
            final int n3 = i * 4;
            final byte b = array[n3 + 2];
            final byte b2 = array[n3 + 3];
            final byte b3 = Base64.base64Alphabet[array[n3]];
            final byte b4 = Base64.base64Alphabet[array[n3 + 1]];
            if (b != 61 && b2 != 61) {
                final byte b5 = Base64.base64Alphabet[b];
                final byte b6 = Base64.base64Alphabet[b2];
                array2[n2] = (byte)(b3 << 2 | b4 >> 4);
                array2[n2 + 1] = (byte)((b4 & 0xF) << 4 | (b5 >> 2 & 0xF));
                array2[n2 + 2] = (byte)(b5 << 6 | b6);
            }
            else if (b == 61) {
                array2[n2] = (byte)(b3 << 2 | b4 >> 4);
            }
            else if (b2 == 61) {
                final byte b7 = Base64.base64Alphabet[b];
                array2[n2] = (byte)(b3 << 2 | b4 >> 4);
                array2[n2 + 1] = (byte)((b4 & 0xF) << 4 | (b7 >> 2 & 0xF));
            }
            n2 += 3;
        }
        return array2;
    }
    
    static {
        Base64.base64Alphabet = new byte[255];
        Base64.lookUpBase64Alphabet = new byte[64];
        for (int i = 0; i < 255; ++i) {
            Base64.base64Alphabet[i] = -1;
        }
        for (int j = 90; j >= 65; --j) {
            Base64.base64Alphabet[j] = (byte)(j - 65);
        }
        for (int k = 122; k >= 97; --k) {
            Base64.base64Alphabet[k] = (byte)(k - 97 + 26);
        }
        for (int l = 57; l >= 48; --l) {
            Base64.base64Alphabet[l] = (byte)(l - 48 + 52);
        }
        Base64.base64Alphabet[43] = 62;
        Base64.base64Alphabet[47] = 63;
        for (int n = 0; n <= 25; ++n) {
            Base64.lookUpBase64Alphabet[n] = (byte)(65 + n);
        }
        for (int n2 = 26, n3 = 0; n2 <= 51; ++n2, ++n3) {
            Base64.lookUpBase64Alphabet[n2] = (byte)(97 + n3);
        }
        for (int n4 = 52, n5 = 0; n4 <= 61; ++n4, ++n5) {
            Base64.lookUpBase64Alphabet[n4] = (byte)(48 + n5);
        }
        Base64.lookUpBase64Alphabet[62] = 43;
        Base64.lookUpBase64Alphabet[63] = 47;
    }
}
