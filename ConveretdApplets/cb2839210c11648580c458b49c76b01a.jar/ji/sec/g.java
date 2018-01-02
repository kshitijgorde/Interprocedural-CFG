// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.io.File;
import ji.util.e;
import ji.v1event.af;
import java.awt.Component;
import ji.io.h;
import ji.util.i;
import ji.util.d;
import ji.ext.v;

public class g
{
    static int a;
    public static int b;
    public static int c;
    static boolean d;
    static boolean e;
    static boolean f;
    static boolean g;
    static boolean h;
    static boolean i;
    static boolean j;
    static boolean k;
    static boolean l;
    static boolean m;
    static boolean n;
    static boolean o;
    static String p;
    static boolean q;
    public static boolean r;
    private static String s;
    private static Object t;
    private static boolean u;
    public static v v;
    private static boolean w;
    private static boolean x;
    
    private static final String e() {
        if (!ji.sec.g.q) {
            try {
                ji.sec.g.p = System.getProperty("java.vendor");
                ji.sec.g.q = true;
            }
            catch (Exception ex) {
                ji.sec.g.p = ex.toString();
            }
        }
        return ji.sec.g.p;
    }
    
    public static final boolean a() {
        if (!ji.sec.g.i) {
            try {
                final String e = e();
                if (e != null) {
                    if (e.toLowerCase().indexOf("netscape") >= 0) {
                        ji.sec.g.h = true;
                    }
                    ji.sec.g.i = true;
                }
            }
            catch (Exception ex) {}
        }
        return ji.sec.g.h;
    }
    
    public static final boolean b() {
        if (!ji.sec.g.k) {
            try {
                if (!c()) {
                    final String property = System.getProperty("mozilla.workaround");
                    if (property != null && property.toLowerCase().indexOf("true") >= 0) {
                        ji.sec.g.j = true;
                    }
                }
                ji.sec.g.k = true;
            }
            catch (Exception ex) {}
        }
        return ji.sec.g.j;
    }
    
    public static final boolean c() {
        return f();
    }
    
    private static final boolean f() {
        if (!ji.sec.g.m) {
            try {
                final String e = e();
                if (e != null) {
                    if (e.toLowerCase().indexOf("microsoft") >= 0) {
                        ji.sec.g.l = true;
                    }
                    ji.sec.g.m = true;
                }
            }
            catch (Exception ex) {}
        }
        return ji.sec.g.l;
    }
    
    public static final boolean a(final String s, final String s2) {
        if (s != null) {
            final Object w = ji.util.d.w(s2);
            if (w != null) {
                if (ji.sec.g.s == null) {
                    ji.sec.g.s = ji.util.d.c(w, s2);
                    if (ji.sec.g.s != null) {
                        ji.sec.g.s = ji.sec.g.s.toLowerCase();
                    }
                }
                if (ji.sec.g.s != null) {
                    final String lowerCase = s.toLowerCase();
                    if (lowerCase.indexOf(ji.sec.g.s) >= 0 && lowerCase.indexOf(String.valueOf(String.valueOf(ji.sec.g.s)).concat("/..")) < 0) {
                        int n = lowerCase.lastIndexOf("/");
                        if (n < 0) {
                            n = lowerCase.lastIndexOf("\\");
                        }
                        if (n > 0) {
                            if (lowerCase.lastIndexOf(".") > n) {
                                return true;
                            }
                        }
                        else if (lowerCase.indexOf(".") > 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static final void c(final String s) {
        try {
            if (!ji.sec.g.u) {
                if (ji.util.i.c(195)) {
                    ji.io.h.d(s, "EXCACHE: loading system...");
                }
                ji.sec.g.v = new v();
                ji.sec.g.u = ji.sec.g.v.b((Component)ji.util.d.w(s), s, null);
                if (ji.util.i.c(195)) {
                    ji.io.h.d(s, "EXCACHE: loaded system: ".concat(String.valueOf(String.valueOf(ji.sec.g.u))));
                }
                if (ji.sec.g.u) {
                    ji.sec.g.v.a();
                    ji.sec.g.v.c(s);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final void a(final String s, final boolean b) {
        if ((ji.util.e.ax() && ji.util.e.u()) || b) {
            try {
                if (!ji.sec.g.w) {
                    final boolean b2 = b || (ji.util.i.c(185) && ji.util.i.c(198) && ji.util.e.ay());
                    if (!ji.sec.g.u && b2 && ji.sec.g.v == null) {
                        synchronized (ji.sec.g.t) {
                            try {
                                ji.util.e.aa(false);
                                c(s);
                            }
                            finally {
                                ji.util.e.aa(true);
                            }
                            if (ji.sec.g.u) {
                                b(s);
                            }
                        }
                        // monitorexit(g.t)
                    }
                }
            }
            catch (Exception ex) {
                ji.sec.g.w = false;
            }
            finally {
                ji.sec.g.w = true;
            }
        }
    }
    
    public static final void d() {
        ji.sec.g.v = null;
        ji.sec.g.w = false;
        ji.sec.g.u = false;
    }
    
    public static final boolean a(final String s) {
        a(s, false);
        return ji.sec.g.u;
    }
    
    public static final void b(final String s, final String s2) {
        if (ji.sec.g.u && ji.sec.g.v != null && !new File(s).exists()) {
            ji.sec.g.v.e(s, s2);
        }
    }
    
    public static final void b(final String s) {
        a(s, ji.sec.g.a, ji.sec.g.b, ji.sec.g.c, ji.sec.g.d, ji.sec.g.e, ji.sec.g.f, ji.sec.g.g);
    }
    
    public static final void a(final String s, final int n, final int n2, final int n3, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (ji.sec.g.u) {
            ji.sec.g.v.a(s, n, n2, n3, b, b2, b3, b4);
        }
    }
    
    static {
        ji.sec.g.a = 5000;
        ji.sec.g.b = 524288000;
        ji.sec.g.c = 300000;
        ji.sec.g.d = false;
        ji.sec.g.e = true;
        ji.sec.g.f = true;
        ji.sec.g.g = false;
        ji.sec.g.h = false;
        ji.sec.g.i = false;
        ji.sec.g.j = false;
        ji.sec.g.k = false;
        ji.sec.g.l = false;
        ji.sec.g.m = false;
        ji.sec.g.n = false;
        ji.sec.g.o = false;
        ji.sec.g.p = null;
        ji.sec.g.q = false;
        ji.sec.g.r = false;
        ji.sec.g.s = null;
        ji.sec.g.t = new Object();
        ji.sec.g.u = false;
        ji.sec.g.v = null;
        ji.sec.g.w = false;
        ji.sec.g.x = false;
    }
}
