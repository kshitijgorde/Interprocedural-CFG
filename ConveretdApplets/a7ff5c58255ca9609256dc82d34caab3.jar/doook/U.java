// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public class U extends D
{
    private static Hashtable b;
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
    
    public String a(final String s) {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final U u = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(u.d(), 0); i != -1; i = s.indexOf(u.d(), i + 1)) {
                if (a(s, i - 1) && a(s, i + u.d().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(u.d());
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final U u = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(u.d(), 0); i != -1; i = s.indexOf(u.d(), i + 1)) {
                if (a(s, i - 1) && a(s, i + u.d().length())) {
                    ++n2;
                }
            }
            n += n2;
        }
        return n;
    }
    
    public static int a(final String s, final int n) {
        final Enumeration a = a();
        int length = s.length();
        while (a.hasMoreElements()) {
            final U u = a.nextElement();
            int n2 = s.indexOf(u.d(), n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + u.d().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(u.d(), n2 + 1);
                }
            }
        }
        return (length != s.length()) ? length : -1;
    }
    
    public static synchronized void a(final U u) {
        final String d = u.d();
        if (u.a(63)) {
            U.b.remove(d);
        }
        else if (d != null && u.b != null) {
            U.b.put(d, u);
        }
    }
    
    public static synchronized U a(final String s) {
        return U.b.get(s);
    }
    
    public static synchronized Enumeration a() {
        return U.b.elements();
    }
    
    public static int g() {
        return U.b.size();
    }
    
    public static synchronized U a(final int n) {
        final Enumeration a = a();
        int n2 = 0;
        while (a.hasMoreElements()) {
            final U u = a.nextElement();
            if (n == n2) {
                return u;
            }
            ++n2;
        }
        return null;
    }
    
    public U(final int n, final String s) {
        super(n, s);
    }
    
    static {
        U.b = new Hashtable();
    }
}
