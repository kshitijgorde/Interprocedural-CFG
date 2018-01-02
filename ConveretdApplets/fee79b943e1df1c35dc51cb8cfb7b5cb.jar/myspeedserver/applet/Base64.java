// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

public class Base64
{
    private static final char[] IV;
    private static final byte[] JV;
    
    static {
        IV = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        JV = new byte[256];
        for (int i = 0; i < Base64.IV.length; ++i) {
            Base64.JV[Base64.IV[i]] = (byte)i;
        }
    }
    
    public static String addCRLF(final String s) {
        final int length = s.length();
        final char[] array = new char[length + (length - 1) / 76 * 2];
        int i = 0;
        int n = 0;
        while (i < length) {
            final int min = Math.min(76, length - i);
            s.getChars(i, i + min, array, n);
            i += min;
            n += min;
            if (i < length) {
                array[n++] = '\r';
                array[n++] = '\n';
            }
        }
        return new String(array);
    }
    
    public static byte[] decode(final String s) {
        final int n = s.endsWith("==") ? 2 : (s.endsWith("=") ? 1 : 0);
        final char[] charArray = s.toCharArray();
        final int n2 = charArray.length / 4;
        final byte[] array = new byte[Math.max(0, n2 * 3 - n)];
        final int length = array.length;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            final int n5 = (((Base64.JV[charArray[n4 + 0] & '\u00ff'] << 6) + Base64.JV[charArray[n4 + 1] & '\u00ff'] << 6) + Base64.JV[charArray[n4 + 2] & '\u00ff'] << 6) + Base64.JV[charArray[n4 + 3] & '\u00ff'];
            if (n3 < length) {
                array[n3++] = (byte)(n5 >> 16);
            }
            if (n3 < length) {
                array[n3++] = (byte)(n5 >> 8);
            }
            if (n3 < length) {
                array[n3++] = (byte)n5;
            }
            n4 += 4;
        }
        return array;
    }
    
    public static String encode(final byte[] array) {
        char[] array2;
        int n;
        int i;
        int n2;
        byte b;
        byte b2;
        byte b3;
        for (array2 = new char[(array.length + 2) / 3 * 4], n = 0, i = 0, n2 = array.length / 3 * 3; i < n2; b = array[i++], b2 = array[i++], b3 = array[i++], array2[n++] = Base64.IV[b >>> 2 & 0x3F], array2[n++] = Base64.IV[(b << 4 & 0x30) + (b2 >>> 4 & 0xF)], array2[n++] = Base64.IV[(b2 << 2 & 0x3C) + (b3 >>> 6 & 0x3)], array2[n++] = Base64.IV[b3 & 0x3F]) {}
        final int n3 = array.length - n2;
        if (n3 > 0) {
            final byte b4 = array[i++];
            final byte b5 = (byte)((n3 == 2) ? array[i++] : 0);
            final int n4 = 0;
            array2[n++] = Base64.IV[b4 >>> 2 & 0x3F];
            array2[n++] = Base64.IV[(b4 << 4 & 0x30) + (b5 >>> 4 & 0xF)];
            array2[n++] = ((n3 == 2) ? Base64.IV[(b5 << 2 & 0x3C) + (n4 >>> 6 & 0x3)] : '=');
            array2[n++] = '=';
        }
        if (n != array2.length || i != array.length) {
            throw new RuntimeException("Base64 encode error on len=" + array.length);
        }
        return new String(array2);
    }
    
    public static String trim(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\r' && char1 != '\n') {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
