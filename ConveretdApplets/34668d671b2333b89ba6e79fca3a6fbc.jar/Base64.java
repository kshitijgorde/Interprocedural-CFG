// 
// Decompiled by Procyon v0.5.30
// 

class Base64
{
    public static String encode(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i += 3) {
            sb.append(encodeBlock(array, i));
        }
        return sb.toString();
    }
    
    protected static char[] encodeBlock(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = array.length - n - 1;
        for (int n4 = (n3 >= 2) ? 2 : n3, i = 0; i <= n4; ++i) {
            final byte b = array[n + i];
            n2 += ((b < 0) ? (b + 256) : b) << 8 * (2 - i);
        }
        final char[] array2 = new char[4];
        for (int j = 0; j < 4; ++j) {
            array2[j] = getChar(n2 >>> 6 * (3 - j) & 0x3F);
        }
        if (n3 < 1) {
            array2[2] = '=';
        }
        if (n3 < 2) {
            array2[3] = '=';
        }
        return array2;
    }
    
    protected static char getChar(final int n) {
        if (n >= 0 && n <= 25) {
            return (char)(65 + n);
        }
        if (n >= 26 && n <= 51) {
            return (char)(97 + (n - 26));
        }
        if (n >= 52 && n <= 61) {
            return (char)(48 + (n - 52));
        }
        if (n == 62) {
            return '+';
        }
        if (n == 63) {
            return '/';
        }
        return '?';
    }
}
