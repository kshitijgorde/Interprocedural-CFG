// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.io.UnsupportedEncodingException;

public class URLDecoder
{
    public static String decode(final String s) {
        if (s == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        final byte[] array = new byte[s.length()];
        int n = 0;
        int i = 0;
        try {
            while (i < s.length()) {
                final char char1 = s.charAt(i);
                if (char1 == '+') {
                    sb.append(' ');
                }
                else if (char1 == '%') {
                    array[n] = (byte)Integer.parseInt(s.substring(i + 1, i + 3), 16);
                    ++n;
                    i += 2;
                }
                else {
                    sb.append(char1);
                }
                if ((++i < s.length() && s.charAt(i) != '%') || i >= s.length()) {
                    sb.append(new String(array, 0, n, "UTF8"));
                    n = 0;
                }
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
        catch (UnsupportedEncodingException ex2) {
            return null;
        }
        return sb.toString();
    }
}
