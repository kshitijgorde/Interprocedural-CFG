// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

public class WebUtils
{
    private static final char[] kHexChars;
    
    public static String URLDecode(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            char char1 = s.charAt(i);
            if (char1 == '%' && i + 2 < length) {
                final int atoh = atoh(s.charAt(i + 1));
                final int atoh2 = atoh(s.charAt(i + 2));
                if (atoh >= 0 && atoh2 >= 0) {
                    char1 = (char)(atoh * 16 + atoh2);
                    i += 2;
                }
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    public static String URLEncode(final String s) {
        String string = s;
        int length = string.length();
        while (length-- > 0) {
            if (!Character.isLetterOrDigit(string.charAt(length)) && string.charAt(length) != ' ') {
                final byte b = (byte)string.charAt(length);
                final StringBuffer sb = new StringBuffer();
                sb.append("%");
                appendHexPair(b, sb);
                string = string.substring(0, length) + sb.toString() + string.substring(length + 1);
            }
        }
        return string;
    }
    
    private static int atoh(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c + '\n' - 'A';
        }
        if (c >= 'a' && c <= 'f') {
            return c + '\n' - 'a';
        }
        return -1;
    }
    
    private static void appendHexPair(final byte b, final StringBuffer sb) {
        final char c = WebUtils.kHexChars[(b & 0xF0) >> 4];
        final char c2 = WebUtils.kHexChars[b & 0xF];
        sb.append(c);
        sb.append(c2);
    }
    
    static {
        kHexChars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
