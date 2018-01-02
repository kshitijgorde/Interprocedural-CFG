// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public final class K extends an
{
    private static Hashtable a;
    public Image a;
    public String a;
    
    private static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public final Object a(final String s) {
        if ("image".equals(s)) {
            return this.a;
        }
        return super.a(s);
    }
    
    public final String a() {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final K k = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(k.c, 0); i != -1; ++i, i = s.indexOf(k.c, i)) {
                if (a(s, i - 1) && a(s, i + k.c.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(k.c);
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final K k = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(k.c, 0); i != -1; ++i, i = s.indexOf(k.c, i)) {
                if (a(s, i - 1) && a(s, i + k.c.length())) {
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
            final K k = a.nextElement();
            int n2 = s.indexOf(k.c, n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + k.c.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    ++n2;
                    n2 = s.indexOf(k.c, n2);
                }
            }
        }
        if (length != s.length()) {
            return length;
        }
        return -1;
    }
    
    public static synchronized void a(final K k) {
        final String c = k.c;
        if (k.a(63)) {
            if (K.a.remove(c) == null) {
                final Enumeration a = a();
                while (a.hasMoreElements()) {
                    final K i;
                    if ((i = a.nextElement()) != null && i.g == k.g) {
                        K.a.remove(i.c);
                    }
                }
            }
        }
        else if (c != null && k.a != null) {
            K.a.put(c, k);
        }
    }
    
    public static synchronized K a(final String s) {
        return K.a.get(s);
    }
    
    public static synchronized Enumeration a() {
        return K.a.elements();
    }
    
    public K(final int n, final String s) {
        super(n, s);
    }
    
    static {
        K.a = new Hashtable();
    }
}
