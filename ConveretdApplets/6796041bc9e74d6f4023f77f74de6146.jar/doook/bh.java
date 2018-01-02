// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public class bh extends cF
{
    private static Hashtable h;
    public Image e;
    public String d;
    
    public boolean a(final String s, final Object o) {
        if ("restricted".equals(s)) {
            this.a(36, (boolean)o);
            return true;
        }
        return false;
    }
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public Object a(final String s) {
        if ("image".equals(s)) {
            return this.e;
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.d(36));
        }
        return super.a(s);
    }
    
    public String c(final String s) {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final bh bh = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(bh.f(), 0); i != -1; i = s.indexOf(bh.f(), i + 1)) {
                if (a(s, i - 1) && a(s, i + bh.f().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(bh.f());
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final bh bh = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(bh.f(), 0); i != -1; i = s.indexOf(bh.f(), i + 1)) {
                if (a(s, i - 1) && a(s, i + bh.f().length())) {
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
            final bh bh = a.nextElement();
            int n2 = s.indexOf(bh.f(), n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + bh.f().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(bh.f(), n2 + 1);
                }
            }
        }
        return (length != s.length()) ? length : -1;
    }
    
    public static synchronized void a(final bh bh) {
        final String f = bh.f();
        if (bh.d(63)) {
            bh.h.remove(f);
        }
        else if (f != null && bh.d != null) {
            bh.h.put(f, bh);
        }
    }
    
    public static synchronized bh a(final String s) {
        return bh.h.get(s);
    }
    
    public static synchronized Enumeration a() {
        return bh.h.elements();
    }
    
    public static int g() {
        return bh.h.size();
    }
    
    public static synchronized bh a(final int n) {
        final Enumeration a = a();
        int n2 = 0;
        while (a.hasMoreElements()) {
            final bh bh = a.nextElement();
            if (n == n2) {
                return bh;
            }
            ++n2;
        }
        return null;
    }
    
    public bh(final int n, final String s) {
        super(n, s);
    }
    
    static {
        bh.h = new Hashtable();
    }
}
