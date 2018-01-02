// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private static byte[] a;
    private static byte[] b;
    
    static {
        c.a = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        c.b = new byte[128];
        for (int i = 65; i <= 90; ++i) {
            c.b[i] = (byte)(i - 65);
        }
        for (int j = 97; j <= 122; ++j) {
            c.b[j] = (byte)(j - 97 + 26);
        }
        for (int k = 48; k <= 57; ++k) {
            c.b[k] = (byte)(k - 48 + 52);
        }
        c.b[43] = 62;
        c.b[47] = 63;
    }
    
    public static byte[] a(final byte[] array) {
        byte[] array2;
        if (array.length % 3 == 0) {
            array2 = new byte[4 * array.length / 3];
        }
        else {
            array2 = new byte[4 * (array.length / 3 + 1)];
        }
        for (int i = 0, n = 0; i < array.length / 3 * 3; i += 3, n += 4) {
            final int n2 = array[i] & 0xFF;
            final int n3 = array[i + 1] & 0xFF;
            final int n4 = array[i + 2] & 0xFF;
            final int n5 = n2 >>> 2 & 0x3F;
            final int n6 = (n2 << 4 | n3 >>> 4) & 0x3F;
            final int n7 = (n3 << 2 | n4 >>> 6) & 0x3F;
            final int n8 = n4 & 0x3F;
            array2[n] = c.a[n5];
            array2[n + 1] = c.a[n6];
            array2[n + 2] = c.a[n7];
            array2[n + 3] = c.a[n8];
        }
        switch (array.length % 3) {
            case 1: {
                final int n9 = array[array.length - 1] & 0xFF;
                final int n10 = n9 >>> 2 & 0x3F;
                final int n11 = n9 << 4 & 0x3F;
                array2[array2.length - 4] = c.a[n10];
                array2[array2.length - 3] = c.a[n11];
                array2[array2.length - 2] = 61;
                array2[array2.length - 1] = 61;
                break;
            }
            case 2: {
                final int n12 = array[array.length - 2] & 0xFF;
                final int n13 = array[array.length - 1] & 0xFF;
                final int n14 = n12 >>> 2 & 0x3F;
                final int n15 = (n12 << 4 | n13 >>> 4) & 0x3F;
                final int n16 = n13 << 2 & 0x3F;
                array2[array2.length - 4] = c.a[n14];
                array2[array2.length - 3] = c.a[n15];
                array2[array2.length - 2] = c.a[n16];
                array2[array2.length - 1] = 61;
                break;
            }
        }
        return array2;
    }
    
    public static byte[] b(final byte[] array) {
        byte[] array2;
        if (array[array.length - 2] == 61) {
            array2 = new byte[(array.length / 4 - 1) * 3 + 1];
        }
        else if (array[array.length - 1] == 61) {
            array2 = new byte[(array.length / 4 - 1) * 3 + 2];
        }
        else {
            array2 = new byte[array.length / 4 * 3];
        }
        for (int i = 0, n = 0; i < array.length - 4; i += 4, n += 3) {
            final byte b = c.b[array[i]];
            final byte b2 = c.b[array[i + 1]];
            final byte b3 = c.b[array[i + 2]];
            final byte b4 = c.b[array[i + 3]];
            array2[n] = (byte)(b << 2 | b2 >> 4);
            array2[n + 1] = (byte)(b2 << 4 | b3 >> 2);
            array2[n + 2] = (byte)(b3 << 6 | b4);
        }
        if (array[array.length - 2] == 61) {
            array2[array2.length - 1] = (byte)(c.b[array[array.length - 4]] << 2 | c.b[array[array.length - 3]] >> 4);
        }
        else if (array[array.length - 1] == 61) {
            final byte b5 = c.b[array[array.length - 4]];
            final byte b6 = c.b[array[array.length - 3]];
            final byte b7 = c.b[array[array.length - 2]];
            array2[array2.length - 2] = (byte)(b5 << 2 | b6 >> 4);
            array2[array2.length - 1] = (byte)(b6 << 4 | b7 >> 2);
        }
        else {
            final byte b8 = c.b[array[array.length - 4]];
            final byte b9 = c.b[array[array.length - 3]];
            final byte b10 = c.b[array[array.length - 2]];
            final byte b11 = c.b[array[array.length - 1]];
            array2[array2.length - 3] = (byte)(b8 << 2 | b9 >> 4);
            array2[array2.length - 2] = (byte)(b9 << 4 | b10 >> 2);
            array2[array2.length - 1] = (byte)(b10 << 6 | b11);
        }
        return array2;
    }
    
    public static byte[] a(final String s) {
        byte[] array;
        if (s.charAt(s.length() - 2) == '=') {
            array = new byte[(s.length() / 4 - 1) * 3 + 1];
        }
        else if (s.charAt(s.length() - 1) == '=') {
            array = new byte[(s.length() / 4 - 1) * 3 + 2];
        }
        else {
            array = new byte[s.length() / 4 * 3];
        }
        for (int i = 0, n = 0; i < s.length() - 4; i += 4, n += 3) {
            final byte b = c.b[s.charAt(i)];
            final byte b2 = c.b[s.charAt(i + 1)];
            final byte b3 = c.b[s.charAt(i + 2)];
            final byte b4 = c.b[s.charAt(i + 3)];
            array[n] = (byte)(b << 2 | b2 >> 4);
            array[n + 1] = (byte)(b2 << 4 | b3 >> 2);
            array[n + 2] = (byte)(b3 << 6 | b4);
        }
        if (s.charAt(s.length() - 2) == '=') {
            array[array.length - 1] = (byte)(c.b[s.charAt(s.length() - 4)] << 2 | c.b[s.charAt(s.length() - 3)] >> 4);
        }
        else if (s.charAt(s.length() - 1) == '=') {
            final byte b5 = c.b[s.charAt(s.length() - 4)];
            final byte b6 = c.b[s.charAt(s.length() - 3)];
            final byte b7 = c.b[s.charAt(s.length() - 2)];
            array[array.length - 2] = (byte)(b5 << 2 | b6 >> 4);
            array[array.length - 1] = (byte)(b6 << 4 | b7 >> 2);
        }
        else {
            final byte b8 = c.b[s.charAt(s.length() - 4)];
            final byte b9 = c.b[s.charAt(s.length() - 3)];
            final byte b10 = c.b[s.charAt(s.length() - 2)];
            final byte b11 = c.b[s.charAt(s.length() - 1)];
            array[array.length - 3] = (byte)(b8 << 2 | b9 >> 4);
            array[array.length - 2] = (byte)(b9 << 4 | b10 >> 2);
            array[array.length - 1] = (byte)(b10 << 6 | b11);
        }
        return array;
    }
}
