// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class cf
{
    private static char[] if;
    private static byte[] a;
    
    public static String if(final String s) {
        return new String(a(s.getBytes()));
    }
    
    public static char[] a(final byte[] array) {
        return a(array, array.length);
    }
    
    public static char[] a(final byte[] array, final int n) {
        final int n2 = (n * 4 + 2) / 3;
        final char[] array2 = new char[(n + 2) / 3 * 4];
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        for (int i = 0, n3 = 0; i < n; n4 = (array[i++] & 0xFF), n5 = ((i < n) ? (array[i++] & 0xFF) : 0), n6 = ((i < n) ? (array[i++] & 0xFF) : 0), n7 = n4 >>> 2, n8 = ((n4 & 0x3) << 4 | n5 >>> 4), n9 = ((n5 & 0xF) << 2 | n6 >>> 6), n10 = (n6 & 0x3F), array2[n3++] = cf.if[n7], array2[n3++] = cf.if[n8], array2[n3] = ((n3 < n2) ? cf.if[n9] : '='), ++n3, array2[n3] = ((n3 < n2) ? cf.if[n10] : '='), ++n3) {}
        return array2;
    }
    
    public static String do(final String s) {
        return new String(a(s));
    }
    
    public static byte[] a(final String s) {
        return a(s.toCharArray());
    }
    
    public static byte[] a(final char[] array) {
        int length = array.length;
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (length > 0 && array[length - 1] == '=') {
            --length;
        }
        final int n = length * 3 / 4;
        final byte[] array2 = new byte[n];
        int i = 0;
        int n2 = 0;
        while (i < length) {
            final char c = array[i++];
            final char c2 = array[i++];
            final char c3 = (i < length) ? array[i++] : 'A';
            final char c4 = (i < length) ? array[i++] : 'A';
            if (c > '\u007f' || c2 > '\u007f' || c3 > '\u007f' || c4 > '\u007f') {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            final byte b = cf.a[c];
            final byte b2 = cf.a[c2];
            final byte b3 = cf.a[c3];
            final byte b4 = cf.a[c4];
            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            final int n3 = b << 2 | b2 >>> 4;
            final int n4 = (b2 & 0xF) << 4 | b3 >>> 2;
            final int n5 = (b3 & 0x3) << 6 | b4;
            array2[n2++] = (byte)n3;
            if (n2 < n) {
                array2[n2++] = (byte)n4;
            }
            if (n2 >= n) {
                continue;
            }
            array2[n2++] = (byte)n5;
        }
        return array2;
    }
    
    static {
        cf.if = new char[64];
        int n = 0;
        for (char c = 'A'; c <= 'Z'; ++c) {
            cf.if[n++] = c;
        }
        for (char c2 = 'a'; c2 <= 'z'; ++c2) {
            cf.if[n++] = c2;
        }
        for (char c3 = '0'; c3 <= '9'; ++c3) {
            cf.if[n++] = c3;
        }
        cf.if[n++] = '+';
        cf.if[n++] = '/';
        cf.a = new byte[128];
        for (int i = 0; i < cf.a.length; ++i) {
            cf.a[i] = -1;
        }
        for (int j = 0; j < 64; ++j) {
            cf.a[cf.if[j]] = (byte)j;
        }
    }
}
