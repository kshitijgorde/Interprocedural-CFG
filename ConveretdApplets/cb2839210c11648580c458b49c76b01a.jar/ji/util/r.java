// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.net.URL;
import ji.res.z;
import ji.v1event.af;
import java.awt.Component;

public class r
{
    private static String a;
    private static String b;
    private static String c;
    
    public static final boolean a() {
        return true;
    }
    
    public static final boolean b() {
        return false;
    }
    
    public static void a(final String a) {
        r.a = a;
    }
    
    public static void b(final String b) {
        r.b = b;
    }
    
    public static void c(final String c) {
        r.c = c;
    }
    
    public static String a(final Component component, final String s, final Object o, final af af) {
        String s2 = "";
        if (r.a != null) {
            s2 = r.a;
        }
        else if (r.c != null) {
            try {
                z.b(false);
                final byte[] a = z.a(component, r.c, r.c, af, o, s, new y());
                if (a != null) {
                    s2 = new String(a);
                }
            }
            catch (Exception ex) {
                s2 = ex.getMessage();
            }
        }
        else {
            final StringBuffer sb = new StringBuffer(d.b(807, s));
            sb.append("\n");
            sb.append(d.b(808, s));
            sb.append("\n");
            sb.append(d.b(809, s));
            sb.append("\n");
            sb.append(d.b(810, s));
            sb.append("\n");
            sb.append(d.b(811, s));
            sb.append("\n");
            sb.append(d.b(812, s));
            s2 = sb.toString();
        }
        return s2;
    }
    
    public static boolean c() {
        return r.b != null;
    }
    
    public static void a(final Component component, final String s, final af af, final URL url) {
        if (r.b != null) {
            try {
                final Object b = d.b(url, r.b, s);
                if (b instanceof URL) {
                    e.a((URL)b, component, af, s, "DotNetSecErrorPage");
                }
                else {
                    d.a(d.b(806, s), d.b(813, s), component, null, null, s, false);
                }
            }
            catch (Exception ex) {
                d.a(ex, d.b(806, s), component, null, s);
            }
        }
    }
    
    static {
        r.a = null;
        r.b = null;
        r.c = null;
    }
}
