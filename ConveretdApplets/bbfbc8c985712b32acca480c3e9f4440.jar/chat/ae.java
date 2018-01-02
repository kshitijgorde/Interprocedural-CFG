// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public final class ae extends U
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
            final ae ae = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(ae.d, 0); i != -1; ++i, i = s.indexOf(ae.d, i)) {
                if (a(s, i - 1) && a(s, i + ae.d.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(ae.d);
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final ae ae = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(ae.d, 0); i != -1; ++i, i = s.indexOf(ae.d, i)) {
                if (a(s, i - 1) && a(s, i + ae.d.length())) {
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
            final ae ae = a.nextElement();
            int n2 = s.indexOf(ae.d, n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + ae.d.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    ++n2;
                    n2 = s.indexOf(ae.d, n2);
                }
            }
        }
        if (length != s.length()) {
            return length;
        }
        return -1;
    }
    
    public static synchronized void a(final ae ae) {
        final String d = ae.d;
        if (ae.a(63)) {
            if (ae.a.remove(d) == null) {
                final Enumeration a = a();
                while (a.hasMoreElements()) {
                    final ae ae2;
                    if ((ae2 = a.nextElement()) != null && ae2.i == ae.i) {
                        ae.a.remove(ae2.d);
                    }
                }
            }
        }
        else if (d != null && ae.a != null) {
            ae.a.put(d, ae);
        }
    }
    
    public static synchronized ae a(final String s) {
        return ae.a.get(s);
    }
    
    public static synchronized Enumeration a() {
        return ae.a.elements();
    }
    
    public ae(final int n, final String s) {
        super(n, s);
    }
    
    static {
        ae.a = new Hashtable();
    }
}
