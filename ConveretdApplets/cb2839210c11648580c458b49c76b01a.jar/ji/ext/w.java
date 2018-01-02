// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.sec.f;
import ji.zip.a4;
import ji.res.z;
import ji.util.y;
import ji.io.fb;
import ji.v1event.a6;
import ji.io.h;
import java.awt.Component;
import ji.v1event.af;
import ji.io.ac;
import ji.util.cn;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import ji.awt.c;
import ji.io.q;
import java.awt.Canvas;

public class w extends Canvas
{
    static String a;
    static String b;
    static String c;
    static String d;
    q e;
    static c f;
    
    public w() {
        this.e = null;
    }
    
    public static final boolean a(final String s, final String s2) {
        return i.c(25) && ac.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(cn.a(e.am(), ji.util.d.eg(), s2)))).append("/").append(s).append(".").append("v1"))), s2);
    }
    
    public static final String a() {
        return w.a;
    }
    
    public static final boolean a(final String s, final af af, final int n, final Component component, final String s2) throws Exception {
        boolean a = false;
        boolean al = false;
        if (i.c(25)) {
            try {
                if (i.c(37)) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("PRO: Install Archive (").append(s).append(")..."))));
                }
                al = ji.util.d.al;
                ji.util.d.al = true;
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(".").append("jar")));
                if (af != null) {
                    e.ag(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(708, s2)))).append(" ").append(ji.util.d.b(n, s2)))));
                    af.a(new a6(component, 9, "..."));
                }
                final String a2 = cn.a(e.am(), ji.util.d.eg(), s2);
                final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append("/").append(value)));
                final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(726, s2)))).append("\n").append(ji.util.d.b(727, s2)).append("\n").append("\n").append(ji.util.d.b(728, s2)).append("\n").append(ji.util.d.b(729, s2)).append("\n").append(ji.util.d.b(730, s2))));
                h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, downloading ").append(value).append(" to ").append(value2).append("..."))));
                ac ac = null;
                try {
                    ac = new ac(value2, true, false, 0, false, component, false, s2);
                    ac.b("test".getBytes());
                    ac.a(component);
                }
                catch (Exception ex) {
                    h.d(s2, "Auto-Update: Error : ".concat(String.valueOf(String.valueOf(ex))));
                    try {
                        if (ac != null) {
                            ac.a(component);
                        }
                    }
                    catch (Exception ex5) {}
                    h.d(s2, "Auto-Update: *** User does not have sufficient privileges  ***");
                    w.a = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(934, s2)))).append("\n \n ").append(ji.util.d.b(935, s2)).append("\n \n ").append(ji.util.d.b(936, s2))));
                    throw new fb(w.a);
                }
                if (i.c(37)) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("PRO: loading ").append(value).append("..."))));
                }
                z.a(value, e.an(), 3, af, component, s2, false, value2, value3, new y());
                if (ji.util.d.er()) {
                    h.d(s2, "Auto-Update, aborted by user.");
                    return false;
                }
                if (ji.io.ac.d(value2, s2)) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, #10 downloaded ").append(value2).append(" (").append(ji.io.ac.a(value2, s2)).append(" bytes)..."))));
                    try {
                        final a4 a3 = new a4(ji.util.d.eg());
                        if (ji.util.d.r(s2)) {
                            h.d(s2, "Auto-Update, leaving archive in downloaded path.");
                            a = true;
                        }
                        else {
                            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, exporting classes from ").append(value2).append(" to ").append(a2))));
                            a = a4.a(value2, a2, null, true, component, s2, true, true, null);
                            h.d(s2, "Auto-Update, exported.");
                        }
                        final String value4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append("/").append("vlocal.v1")));
                        final ac ac2 = new ac(value4, false, false, 0, false, component, false, s2);
                        final byte[] array = new byte[(int)ac2.w()];
                        ac2.a(array);
                        ac2.a(component);
                        ji.io.ac.c(value4, s2);
                        final String value5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append("/").append(s).append(".").append("v1")));
                        final ac ac3 = new ac(value4, true, false, 0, false, component, true, s2);
                        ac3.b(array);
                        ac3.b(String.valueOf(String.valueOf(value5)).concat("\r\n").getBytes());
                        ac3.a(component);
                        final ac ac4 = new ac(value5, true, false, 0, false, component, true, s2);
                        ac4.b("".concat(String.valueOf(String.valueOf(ji.util.d.cd(true)))).getBytes());
                        ac4.a(component);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    try {
                        if (!ji.util.d.r(s2)) {
                            h.d(s2, "Auto-Update, deleting exported package JAR ".concat(String.valueOf(String.valueOf(value2))));
                            ji.io.ac.c(value2, s2);
                        }
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
                return a;
            }
            catch (fb fb) {
                throw fb;
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
            finally {
                ji.util.d.al = al;
            }
        }
        return a;
    }
    
    public static final boolean a(final Component component, final String s, final af af, final n n, final String b, final int n2, final int e, final boolean b2) {
        final String concat = String.valueOf(String.valueOf(b)).concat(".class");
        String concat2 = null;
        boolean b3 = false;
        try {
            boolean b4 = false;
            if (e.u(s) && ji.util.d.a0(s) && a(e)) {
                concat2 = String.valueOf(String.valueOf(b)).concat(".dll");
                b4 = true;
            }
            if (b4) {
                final b7 a = a(b);
                if (a == null) {
                    if (a(component, s, af, n, concat2, concat, n2, b2)) {
                        final b7 b5 = new b7();
                        b5.a = concat;
                        b5.b = b;
                        b5.c = concat2;
                        b5.e = e;
                        b5.d = 1;
                        w.f.a(b, b5);
                        b3 = true;
                    }
                }
                else {
                    final b7 b6 = a;
                    ++b6.d;
                    b3 = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b3;
    }
    
    public static final void b(final String s, final String s2) {
        final b7 a = a(s);
        if (a != null && a.d > 0) {
            final b7 b7 = a;
            --b7.d;
            if (a.d == 0 && ji.util.d.eg() && ji.util.d.av(s2)) {
                c(s2);
            }
        }
    }
    
    private static final b7 a(final String s) {
        b7 b7 = null;
        try {
            if (w.f != null) {
                b7 = (b7)w.f.d(s);
            }
        }
        catch (Exception ex) {}
        return b7;
    }
    
    private static final boolean a(final Component component, final String s, final af af, final n n, final String s2, final String s3, final int n2, final boolean b) throws Exception {
        boolean b2;
        if (n != null) {
            b(s);
            b2 = a(component, af, s, s3, n2);
            if (b2) {
                b2 = a(n, s2, s2, n2, component, af, s, false, null, b);
            }
        }
        else {
            b2 = true;
        }
        if (!b2) {
            try {
                n.h(s);
            }
            catch (Exception ex) {}
        }
        return b2;
    }
    
    private static void b(final String s) {
        try {
            w.c = ji.sec.f.a("file.separator", s);
        }
        catch (Exception ex) {}
        if (ji.util.d.eg() && ji.util.d.av(s)) {
            w.d = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a1(s)))).append(w.c).append("java").append(w.c).append("classes")));
        }
    }
    
    private static final boolean a(final n n, final String s, final String s2, final int n2, final Component component, final af af, final String s3, final boolean b, final Object[] array, final boolean b2) {
        boolean b3 = false;
        try {
            byte[] a = null;
            e.a(true, n2, null, null);
            final String a2 = z.a(component, s, ji.util.d.b(s, ji.util.d.bh(s), "v1"), af, s3, component);
            if (a2 == null) {
                a = z.a(component, s, ji.util.d.b(s, ji.util.d.bh(s), "v1"), af, null, s3, true, new y());
            }
            boolean b4 = false;
            String s4;
            if (ji.util.d.eg() && ji.util.d.av(s3)) {
                b(s3);
                s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a1(s3)))).append(w.c).append("java").append(w.c).append("bin").append(w.c).append(s2)));
                try {
                    ac.c(s4, s3);
                }
                catch (Exception ex3) {}
                if (a2 != null) {
                    try {
                        final ac ac = new ac(a2, false, false, 0, component, s3);
                        a = new byte[(int)ac.w()];
                        ac.a(a);
                        ac.a(component);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if (a2 == null) {
                final q a3 = q.a(component, s3);
                b4 = true;
                s4 = a3.b(".lib");
            }
            else {
                s4 = a2;
            }
            Label_0328: {
                if (a2 != null) {
                    if (!ji.util.d.eg() || !ji.util.d.av(s3)) {
                        break Label_0328;
                    }
                }
                try {
                    final ac ac2 = new ac(s4, true, false, 0, component, s3);
                    ac2.b(a);
                    ac2.a(component);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            if (!b) {
                final q a4 = q.a(component, s3);
                String b5 = null;
                if (!b4) {
                    b5 = a4.b(".lib");
                }
                ji.sec.f.a(s4, component, s3, b5);
            }
            b3 = true;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            e.a(false, 0, af, component);
        }
        try {
            if (b3) {
                n.a(array);
            }
        }
        catch (Throwable t2) {
            t2.printStackTrace();
            b3 = false;
        }
        return b3;
    }
    
    private static final boolean a(final Component component, final af af, final String s, final String s2, final int n) throws Exception {
        e.ai(s);
        return !ji.util.d.eg() || !ji.util.d.av(s) || a(s2, n, component, af, s);
    }
    
    private static final boolean a(final String s, final int n, final Component component, final af af, final String s2) throws Exception {
        boolean b = false;
        try {
            e.a(true, n, null, null);
            if (ji.util.d.a0(s2)) {
                ac.e(w.b = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(w.d))).append(w.c).append("daeja").append(w.c).append("ji"))), s2);
                if (s != null) {
                    String lowerCase = s;
                    final int index = lowerCase.toLowerCase().indexOf(".class");
                    if (index >= 0) {
                        lowerCase = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(lowerCase.substring(0, index)))).append("c").append(lowerCase.substring(index)))).toLowerCase();
                    }
                    final byte[] a = z.a(component, lowerCase, ji.util.d.b(lowerCase, ji.util.d.bh(lowerCase), "v1"), af, null, s2, new y());
                    if (a == null) {
                        throw new Exception(ji.util.d.b(254, s2));
                    }
                    w.b = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(w.b))).append(w.c).append(s)));
                    try {
                        ac.c(w.b, s2);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    final ac ac = new ac(w.b, true, false, 0, component, s2);
                    ac.b(a);
                    ac.a(component);
                    b = true;
                }
            }
        }
        finally {
            e.a(false, 0, af, component);
        }
        return b;
    }
    
    private static final void c(final String s) {
        try {
            if (w.b != null) {
                ac.c(w.b, s);
                w.b = null;
                ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(w.d))).append(w.c).append("daeja").append(w.c).append("ji"))), s);
                ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(w.d))).append(w.c).append("daeja"))), s);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean a(final int n) {
        return (n & 0x1) == 0x1;
    }
    
    public static final boolean b(final int n) {
        return (n & 0x2) == 0x2;
    }
    
    public static final boolean c(final int n) {
        return (n & 0x4) == 0x4;
    }
    
    public static final boolean d(final int n) {
        return (n & 0x8) == 0x8;
    }
    
    public static final boolean e(final int n) {
        return (n & 0x10) == 0x20;
    }
    
    static {
        w.a = null;
        w.b = null;
        w.c = "/";
        w.d = "";
        w.f = new c("jiExtManager1");
    }
}
