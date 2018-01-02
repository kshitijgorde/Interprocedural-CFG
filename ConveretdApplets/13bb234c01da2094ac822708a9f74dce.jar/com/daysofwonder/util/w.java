// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.StringTokenizer;
import java.awt.Rectangle;

public class w
{
    private static char[] a;
    
    public static void a(final int n, final byte[] array) {
        a(n, array, 0);
    }
    
    public static void a(final int n, final byte[] array, final int n2) {
        array[n2 + 0] = (byte)((n & 0xFF000000) >> 24);
        array[n2 + 1] = (byte)((n & 0xFF0000) >> 16);
        array[n2 + 2] = (byte)((n & 0xFF00) >> 8);
        array[n2 + 3] = (byte)(n & 0xFF);
    }
    
    public static byte[] a(final long n) {
        final byte[] array = new byte[8];
        a(n, array);
        return array;
    }
    
    public static void a(final long n, final byte[] array) {
        a(n, array, 0);
    }
    
    public static void a(final long n, final byte[] array, final int n2) {
        array[n2 + 0] = (byte)((n & 0xFF00000000000000L) >> 56);
        array[n2 + 1] = (byte)((n & 0xFF000000000000L) >> 48);
        array[n2 + 2] = (byte)((n & 0xFF0000000000L) >> 40);
        array[n2 + 3] = (byte)((n & 0xFF00000000L) >> 32);
        array[n2 + 4] = (byte)((n & 0xFF000000L) >> 24);
        array[n2 + 5] = (byte)((n & 0xFF0000L) >> 16);
        array[n2 + 6] = (byte)((n & 0xFF00L) >> 8);
        array[n2 + 7] = (byte)(n & 0xFFL);
    }
    
    public static int a(final byte b, final byte b2, final byte b3, final byte b4) {
        return (b & 0xFF) << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
    }
    
    public static int a(final int n, final int n2, final int n3, final int n4) {
        return (n & 0xFF) << 24 | (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF);
    }
    
    public static int a(final byte[] array) {
        return a(array, 0);
    }
    
    public static int a(final byte[] array, final int n) {
        return a(array[n], array[n + 1], array[n + 2], array[n + 3]);
    }
    
    public static int a(final int n, final int n2) {
        return (n & 0xFF) << 8 | (n2 & 0xFF);
    }
    
    public static long a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return (n & 0xFF) << 56 | (n2 & 0xFF) << 48 | (n3 & 0xFF) << 40 | (n4 & 0xFF) << 32 | (n5 & 0xFF) << 24 | (n6 & 0xFF) << 16 | (n7 & 0xFF) << 8 | (n8 & 0xFFL);
    }
    
    public static String b(final byte[] array) {
        return a(array, 0, array.length);
    }
    
    public static String a(final byte[] array, final int n, final int n2) {
        final char[] array2 = new char[n2 - n << 1];
        int i = n;
        int n3 = 0;
        while (i < n + n2) {
            final int n4 = array[i] & 0xFF;
            final int n5 = (n4 & 0xF0) >> 4;
            final int n6 = n4 & 0xF;
            array2[n3++] = w.a[n5];
            array2[n3++] = w.a[n6];
            ++i;
        }
        return new String(array2);
    }
    
    public static String a(final byte[] array, final int n, final int n2, final int n3) {
        int i = n;
        final StringBuffer sb = new StringBuffer();
        do {
            int n4 = i;
            do {
                final int n5 = array[n4++] & 0xFF;
                if (n5 <= 15) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(n5).toUpperCase());
                sb.append(" ");
            } while (n4 < i + n3 && n4 < n + n2);
            if (n4 != i + n3) {
                for (int n6 = i + n3 - n4, j = 1; j <= n6; ++j) {
                    sb.append(".. ");
                }
            }
            int n7 = i;
            sb.append(" [");
            for (int k = 1; k <= n3; ++k) {
                if (n7 < n + n2) {
                    final char c = (char)array[n7++];
                    if (c >= ' ') {
                        sb.append(c);
                    }
                    else {
                        sb.append(" ");
                    }
                }
                else {
                    sb.append(".");
                }
            }
            sb.append("]\n");
            i = n7;
        } while (i < n + n2);
        return sb.toString();
    }
    
    public static String a(final String s, final Object[] array) {
        if (s != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    if (i < s.length() - 1) {
                        final char char2 = s.charAt(++i);
                        if (Character.isDigit(char2)) {
                            final char c = (char)(char2 - '0');
                            if (array.length > c) {
                                if (array[c] instanceof String) {
                                    sb.append((String)array[c]);
                                }
                                else if (array[c] != null) {
                                    sb.append(array[c].toString());
                                }
                            }
                        }
                        else if (char2 == '%') {
                            sb.append(char1);
                        }
                        else {
                            sb.append(char1).append(char2);
                        }
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            return sb.toString();
        }
        return "";
    }
    
    public static Rectangle a(final String s) {
        final int[] array = new int[4];
        int n = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(s, ","); stringTokenizer.hasMoreTokens() && n < 4; array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim())) {}
        return new Rectangle(array[0], array[1], array[2], array[3]);
    }
    
    public static long a() {
        return System.currentTimeMillis();
    }
    
    static {
        w.a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
