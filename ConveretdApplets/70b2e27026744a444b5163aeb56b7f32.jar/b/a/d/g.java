// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import java.util.TimeZone;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

public class g
{
    private static SimpleDateFormat a;
    
    public static String a(final String s) {
        return a(s, "UTF-8", true);
    }
    
    public static String a(final String s, final String s2, final boolean b) {
        if (s == null || s2 == null) {
            return null;
        }
        try {
            final byte[] bytes = s.getBytes(s2);
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; ++i) {
                final char c = (char)(bytes[i] & 0xFF);
                if (('0' <= c && c <= '9') || ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || c == '.' || c == '-' || c == '*' || c == '_') {
                    sb.append(c);
                }
                else if (b && c == ' ') {
                    sb.append("+");
                }
                else {
                    sb.append("%" + d.a(c, 2));
                }
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public static String b(final String s) {
        return a(s, "UTF-8");
    }
    
    public static String c(final String s) {
        return a(s, "ISO-8859-1");
    }
    
    public static String a(final String s, final String s2) {
        if (s == null || s2 == null) {
            return null;
        }
        final byte[] array = new byte[s.length()];
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 == 37 && i < s.length() - 2) {
                try {
                    char1 = (char)Integer.parseInt(s.substring(i + 1, i + 3), 16);
                }
                catch (NumberFormatException ex) {
                    char1 = 64;
                }
                i += 2;
            }
            else if (char1 == 43) {
                char1 = 32;
            }
            array[n++] = (byte)char1;
        }
        try {
            return new String(array, 0, n, s2);
        }
        catch (UnsupportedEncodingException ex2) {
            return null;
        }
    }
    
    public static String a(final Date date) {
        return g.a.format(date);
    }
    
    static {
        (g.a = new SimpleDateFormat("EEE, dd-MMM-yy HH:mm:ss z")).setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
