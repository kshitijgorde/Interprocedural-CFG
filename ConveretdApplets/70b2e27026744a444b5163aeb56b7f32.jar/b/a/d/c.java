// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class c
{
    public static String a(final char c, final int n) {
        if (n < 0) {
            return null;
        }
        if (n == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; ++i) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String a(final int n, final char c, final int n2) {
        return a("" + n, c, n2);
    }
    
    public static String a(final String s, final char c, final int n) {
        if (s == null) {
            return null;
        }
        if (s.length() >= n) {
            return s;
        }
        return a(c, n - s.length()) + s;
    }
    
    public static String a(final String s, final int n) {
        if (s == null) {
            return null;
        }
        if (n < 0 && s.length() <= -n) {
            return "";
        }
        if (s.length() <= n) {
            return s;
        }
        if (n < 0) {
            return s.substring(0, s.length() + n);
        }
        return s.substring(s.length() - n);
    }
    
    public static String a(String string, final String s, final String s2) {
        int index;
        for (int n = 0; (index = string.indexOf(s, n)) >= 0; string = string.substring(0, index) + s2 + string.substring(index + s.length()), n = index + s2.length()) {}
        return string;
    }
    
    public static String[] a(String substring, final char c) {
        if (substring == null) {
            return null;
        }
        final Vector vector = new Vector<String>();
        int index;
        while ((index = substring.indexOf(c)) >= 0) {
            vector.addElement(substring.substring(0, index));
            substring = substring.substring(index + 1);
        }
        vector.add(substring);
        return vector.toArray(new String[vector.size()]);
    }
    
    public static String a(final String s) {
        if (d.a(s)) {
            return s;
        }
        int n = s.length() - 1;
        while (s.charAt(n) <= ' ' && --n >= 0) {}
        return s.substring(0, n + 1);
    }
    
    public static String a(final byte[] array, final int n, final int n2) {
        return a(array, "UTF-8", n, n2);
    }
    
    public static String a(final byte[] array) {
        return a(array, 0, array.length);
    }
    
    public static String a(final byte[] array, final String s, final int n, final int n2) {
        String s2 = null;
        if (array != null) {
            try {
                s2 = new String(array, n, n2, s);
            }
            catch (UnsupportedEncodingException ex) {
                s2 = new String(array, n, n2);
            }
        }
        return s2;
    }
}
