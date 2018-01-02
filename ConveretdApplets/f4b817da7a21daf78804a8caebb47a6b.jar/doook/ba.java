// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public class ba extends bn
{
    private static Hashtable e;
    public Image b;
    public String b;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public Object a(final String s) {
        if ("image".equals(s)) {
            return this.b;
        }
        return super.a(s);
    }
    
    public String b(final String s) {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final ba ba = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(ba.g(), 0); i != -1; i = s.indexOf(ba.g(), i + 1)) {
                if (a(s, i - 1) && a(s, i + ba.g().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(ba.g());
        }
        return n;
    }
    
    public static int c(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final ba ba = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(ba.g(), 0); i != -1; i = s.indexOf(ba.g(), i + 1)) {
                if (a(s, i - 1) && a(s, i + ba.g().length())) {
                    ++n2;
                }
            }
            n += n2;
        }
        return n;
    }
    
    public static int b(final String s, final int n) {
        final Enumeration a = a();
        int length = s.length();
        while (a.hasMoreElements()) {
            final ba ba = a.nextElement();
            int n2 = s.indexOf(ba.g(), n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + ba.g().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(ba.g(), n2 + 1);
                }
            }
        }
        return (length != s.length()) ? length : -1;
    }
    
    public static synchronized void a(final ba ba) {
        final String g = ba.g();
        if (ba.c(63)) {
            ba.e.remove(g);
        }
        else if (g != null && ba.b != null) {
            ba.e.put(g, ba);
        }
    }
    
    public static synchronized ba a(final String s) {
        return ba.e.get(s);
    }
    
    public static synchronized Enumeration a() {
        return ba.e.elements();
    }
    
    public static int a() {
        return ba.e.size();
    }
    
    public static synchronized ba a(final int n) {
        final Enumeration a = a();
        int n2 = 0;
        while (a.hasMoreElements()) {
            final ba ba = a.nextElement();
            if (n == n2) {
                return ba;
            }
            ++n2;
        }
        return null;
    }
    
    public ba(final int n, final String s) {
        super(n, s);
    }
    
    static {
        ba.e = new Hashtable();
    }
}
