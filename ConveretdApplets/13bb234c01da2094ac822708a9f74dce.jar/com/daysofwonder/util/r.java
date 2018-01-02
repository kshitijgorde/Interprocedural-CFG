// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class r
{
    public static String a(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i += 3) {
            sb.append(a(array, i));
        }
        return sb.toString();
    }
    
    protected static char[] a(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = array.length - n - 1;
        for (int n4 = (n3 >= 2) ? 2 : n3, i = 0; i <= n4; ++i) {
            final byte b = array[n + i];
            n2 += ((b < 0) ? (b + 256) : b) << 8 * (2 - i);
        }
        final char[] array2 = new char[4];
        for (int j = 0; j < 4; ++j) {
            array2[j] = a(n2 >>> 6 * (3 - j) & 0x3F);
        }
        if (n3 < 1) {
            array2[2] = '=';
        }
        if (n3 < 2) {
            array2[3] = '=';
        }
        return array2;
    }
    
    protected static char a(final int n) {
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
    
    public static byte[] a(final String s) {
        int n = 0;
        for (int n2 = s.length() - 1; s.charAt(n2) == '='; --n2) {
            ++n;
        }
        final byte[] array = new byte[s.length() * 6 / 8 - n];
        int n3 = 0;
        for (int i = 0; i < s.length(); i += 4) {
            final int n4 = (a(s.charAt(i)) << 18) + (a(s.charAt(i + 1)) << 12) + (a(s.charAt(i + 2)) << 6) + a(s.charAt(i + 3));
            for (int n5 = 0; n5 < 3 && n3 + n5 < array.length; ++n5) {
                array[n3 + n5] = (byte)(n4 >> 8 * (2 - n5) & 0xFF);
            }
            n3 += 3;
        }
        return array;
    }
    
    protected static int a(final char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + '\u001a';
        }
        if (c >= '0' && c <= '9') {
            return c - '0' + '4';
        }
        if (c == '+') {
            return 62;
        }
        if (c == '/') {
            return 63;
        }
        if (c == '=') {
            return 0;
        }
        return -1;
    }
}
