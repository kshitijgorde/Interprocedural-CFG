// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import java.util.StringTokenizer;
import ji.net.a0;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Point;
import ji.v1event.g9;
import ji.io.h;
import ji.awt.bb;
import ji.io.ac;
import ji.v1event.as;
import java.awt.Rectangle;
import ji.util.e;
import ji.util.i;
import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import java.lang.reflect.Method;
import ji.util.m;
import ji.awt.c;

public class v implements n
{
    private static c a;
    private Object b;
    private m c;
    private String d;
    private static c e;
    private static c f;
    private static int g;
    private static String h;
    private static String i;
    private static String j;
    private static c k;
    private static c l;
    private static c m;
    private static c n;
    private static boolean o;
    private static boolean p;
    private static boolean q;
    private static boolean r;
    private static boolean s;
    private static long t;
    private Object u;
    private Object v;
    private int w;
    private static Object x;
    private boolean y;
    private String z;
    private static boolean aa;
    private Method ab;
    private Method ac;
    private Method ad;
    private Method ae;
    private Method af;
    private Method ag;
    private Method ah;
    private Method ai;
    private Method aj;
    private Method ak;
    private Method al;
    private Method am;
    private Method an;
    private Method ao;
    private Method ap;
    private Method aq;
    private Method ar;
    private Method as;
    private Method at;
    private Method au;
    private Method av;
    private Method aw;
    private Method ax;
    private Method ay;
    private Method az;
    private Method a0;
    private Method a1;
    private Method a2;
    private Method a3;
    private Method a4;
    private Method a5;
    private Method a6;
    private Method a7;
    private Method a8;
    private Method a9;
    private Method ba;
    
    public v() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.u = new Object();
        this.v = new Object();
        this.y = false;
        this.z = "";
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.a0 = null;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
        this.a5 = null;
        this.a6 = null;
        this.a7 = null;
        this.a8 = null;
        this.a9 = null;
        this.ba = null;
    }
    
    private final void e() {
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.a0 = null;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
    }
    
    private final boolean d(final Component component, final String s, final af af) {
        try {
            if (!ji.util.d.lx && !ji.ext.v.aa) {
                try {
                    ji.ext.v.aa = true;
                    return ji.ext.w.a(component, s, af, this, "jiex1", 767, 1, false) && (ji.util.d.lx = true);
                }
                finally {
                    ji.ext.v.aa = false;
                }
            }
            if (ji.util.d.lx && !ji.ext.v.aa) {
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public final boolean a(final String s) {
        final boolean b = ji.util.i.c(7) && ji.util.e.t();
        final boolean b2 = ji.util.d.db() && ji.util.e.u(s);
        return b || b2;
    }
    
    public final boolean a(final Component component, final String s, final af af) {
        if (this.a(s)) {
            try {
                ji.util.d.u = true;
                ji.ext.v.s = this.d(component, s, af);
            }
            finally {
                ji.util.d.u = false;
            }
            return ji.ext.v.s;
        }
        return false;
    }
    
    public final void b(final String s) {
        try {
            if (ji.ext.v.s) {
                ji.ext.w.b("jiex1", s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean b(final Component component, final String s, final af af) {
        boolean b = false;
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                b = true;
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s, af);
                }
                if (!ji.ext.v.r) {
                    return false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public int a(final String s, final Rectangle rectangle, final Component component, final String s2, final af af) {
        int intValue = 0;
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s2, af);
                }
                this.a();
                final Integer n = (Integer)this.c.a("getWindowHandleFromRect", s, new Integer(rectangle.x), new Integer(rectangle.y), new Integer(rectangle.width), new Integer(rectangle.height));
                if (n != null) {
                    intValue = n;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return intValue;
    }
    
    public final int c(final Component component, final String s, final af af) {
        int c = 0;
        boolean b = false;
        if (ji.util.d.cr(s) && ji.util.i.c(7) && ji.util.e.t()) {
            b = true;
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s, af);
            }
            ji.ext.v.o = ji.ext.v.r;
        }
        try {
            if (!ji.ext.v.o || !b) {
                return 0;
            }
            this.a(component, af, s);
            c = this.c(component, s);
            if (ji.ext.v.a != null) {
                for (int i = 0; i < ji.ext.v.a.b(); ++i) {
                    this.c((as)ji.ext.v.a.b(i));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public final int c(final String s) {
        int i = 0;
        try {
            synchronized (this.u) {
                if (!ji.ext.v.p && ji.util.i.c(185) && ji.util.i.c(198)) {
                    i = this.i(s);
                    ji.ext.v.p = true;
                    // monitorexit(this.u)
                    return i;
                }
                final boolean b = false;
                // monitorexit(this.u)
                return b ? 1 : 0;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }
    
    public final void a(final Component component, final String s, final int n) {
        try {
            if (ji.ext.v.o) {
                this.b(component, s, n);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.d(s);
    }
    
    public final void d(final String s) {
        try {
            if (ji.ext.v.r) {
                this.e();
                ji.ext.w.b("jiex1", s);
                this.h(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (ji.ext.v.r && !ji.ext.v.p) {
                ji.ext.v.r = false;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void a(final as as) {
        if (as != null) {
            try {
                if (ji.ext.v.a == null) {
                    ji.ext.v.a = new c("jiWinExLib4", 2);
                }
                if (!ji.ext.v.a.a(as)) {
                    ji.ext.v.a.c(as);
                }
                if (ji.ext.v.o) {
                    this.c(as);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void b(final as as) {
        if (as != null) {
            try {
                if (ji.ext.v.a != null && ji.ext.v.a.a(as)) {
                    ji.ext.v.a.b(as);
                }
                if (ji.ext.v.o) {
                    this.d(as);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean a(final Object[] array) throws Exception {
        this.a();
        this.c.c("jiCheck");
        return true;
    }
    
    public final void a() throws Exception {
        try {
            if (this.b == null) {
                this.b = ji.util.d.a2("daeja.ji.jiex1");
            }
            if (this.b != null && this.c == null) {
                this.c = new m(this.b);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    private void a(final Component component, final af af, final String d) {
        ++ji.ext.v.g;
        this.d = d;
        try {
            if (ji.ext.v.k == null) {
                ji.ext.v.k = new c("jiex1Lib1");
            }
            if (ji.ext.v.k.d(d) == null) {
                ji.ext.v.k.a(d, d);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void b() {
        try {
            if (ji.ext.v.s && ji.ext.v.t > 0) {
                this.a();
                this.c.c("jiEmptyClipboard");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean a(final String s, final int n, final int n2, final int n3, final String s2) {
        try {
            if (ji.ext.v.s) {
                this.a();
                ji.ext.v.t = ji.io.ac.a(s, s2);
                return (boolean)this.c.a("jiCopyToClipboard", s, new Integer(n), new Integer(n2), new Integer(n3));
            }
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean e(final String s) {
        try {
            if (ji.ext.v.s) {
                this.a();
                return (boolean)this.c.a("jiCopyTextToClipboard", s, new Boolean(ji.util.i.c(90)));
            }
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean f(final String s) {
        try {
            if (ji.ext.v.s) {
                this.a();
                return (boolean)this.c.a("jiGetTextFromClipboard", s);
            }
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private final int b(final Component component, final String s) {
        int n = 0;
        if (ji.util.d.cr(s)) {
            try {
                final m m = new m(ji.util.d.e(component).getPeer());
                Object o = null;
                final Method b = m.b("gethwnd");
                if (b != null) {
                    o = m.a(b);
                    if (o != null) {
                        n = (int)o;
                    }
                }
                if (o == null || n <= 0) {
                    final Method b2 = m.b("getHwnd");
                    if (b2 != null) {
                        o = m.a(b2);
                        if (o != null) {
                            n = (int)o;
                        }
                    }
                }
                if (o == null || n <= 0) {
                    final Method b3 = m.b("getNearestHwnd");
                    if (b3 != null) {
                        o = m.a(b3);
                        if (o != null) {
                            n = (int)o;
                        }
                    }
                }
                if (o == null || n <= 0) {
                    final Method b4 = m.b("getTopHwnd");
                    if (b4 != null) {
                        o = m.a(b4);
                        if (o != null) {
                            n = (int)o;
                        }
                    }
                }
                if (o == null || n <= 0) {
                    final Method b5 = m.b("getDrawingSurfaceInfo");
                    if (b5 != null) {
                        final Object a = m.a(b5);
                        if (a != null) {
                            final m i = new m(a);
                            i.a();
                            final Method b6 = i.b("getHWnd");
                            if (b6 != null) {
                                o = i.a(b6);
                                if (o != null) {
                                    n = (int)o;
                                }
                            }
                        }
                        else {
                            o = null;
                            n = 0;
                        }
                    }
                }
                if (o == null || n <= 0) {
                    this.a();
                    final Object a2 = this.c.a(this.c.b("jiGetTopWindow"));
                    if (a2 != null) {
                        n = (int)a2;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }
    
    private int i(final String s) {
        try {
            if (ji.ext.v.m == null) {
                ji.ext.v.m = new c("jiex1Lib2a");
            }
            final tq tq = (tq)ji.ext.v.m.d(s);
            if (tq == null) {
                final String concat = String.valueOf(String.valueOf(ji.ext.v.i)).concat(String.valueOf(String.valueOf(s)));
                this.a();
                final Object a = this.c.a("jiCreateFilePipe", s, concat);
                int intValue = 0;
                if (a != null) {
                    intValue = (int)a;
                    Runnable runnable = null;
                    if (intValue > 0) {
                        runnable = new tq(concat, s, intValue);
                        new bb(s, runnable).start();
                    }
                    ji.ext.v.m.a(s, runnable);
                }
                return intValue;
            }
            return tq.a();
        }
        catch (Throwable t) {
            t.printStackTrace();
            return 0;
        }
    }
    
    private int c(final Component component, final String s) {
        try {
            if (!ji.util.d.cr(s)) {
                return 0;
            }
            if (ji.ext.v.l == null) {
                ji.ext.v.l = new c("jiex1Lib2");
            }
            final u7 u7 = (u7)ji.ext.v.l.d(s);
            if (u7 != null) {
                return u7.b();
            }
            final int b = this.b(component, s);
            if (b > 0) {
                final String concat = String.valueOf(String.valueOf(ji.ext.v.h)).concat(String.valueOf(String.valueOf(s)));
                this.a();
                final Object a = this.c.a("jiSetHook", s, new Integer(b), concat);
                int intValue = 0;
                if (a != null) {
                    intValue = (int)a;
                    this.w = intValue;
                    Runnable runnable = null;
                    if (intValue > 0) {
                        runnable = new u7(concat, s, b);
                        new bb(s, runnable).start();
                    }
                    ji.ext.v.l.a(s, runnable);
                }
                return intValue;
            }
            ji.io.h.d(s, "Can't get Window handle!");
            return 0;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return 0;
        }
    }
    
    private void b(final Component component, final String s, final int n) {
        try {
            if (!ji.util.e.av() && !ji.util.d.dp() && n > 0) {
                this.a();
                final u7 u7 = (u7)ji.ext.v.l.d(s);
                if (u7 != null && u7.b() == n) {
                    this.c.a("jiReleaseHook", s, String.valueOf(String.valueOf(ji.ext.v.h)).concat(String.valueOf(String.valueOf(s))), new Integer(n));
                    ji.ext.v.l.a(s);
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private final void a(final g9 g9, final int n, final int n2) {
        try {
            if (ji.ext.v.e != null) {
                final String ek = ji.util.d.ek();
                if (ek != null) {
                    final c c = new c("jiex1Lib3");
                    for (int i = 0; i < ji.ext.v.e.b(); ++i) {
                        final Object b = ji.ext.v.e.b(i);
                        if (b instanceof Component) {
                            try {
                                final Component component = (Component)b;
                                if (component.isVisible() && component.isShowing()) {
                                    final Rectangle bounds = component.getBounds();
                                    final Point locationOnScreen = component.getLocationOnScreen();
                                    bounds.x = locationOnScreen.x;
                                    bounds.y = locationOnScreen.y;
                                    if (bounds.contains(new Point(n, n2))) {
                                        c.c(component);
                                    }
                                }
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    Label_0282: {
                        if (c.b() == 1) {
                            synchronized (ji.ext.v.x) {
                                ((as)c.b(0)).mouseWheelMoved(g9);
                                // monitorexit(v.x)
                                break Label_0282;
                            }
                        }
                        if (c.b() > 1) {
                            for (int j = 0; j < c.b(); ++j) {
                                final String a = ji.util.d.a((Component)c.b(j));
                                if (a != null && a.equals(ek)) {
                                    ((as)c.b(j)).mouseWheelMoved(g9);
                                    break;
                                }
                            }
                        }
                    }
                    c.c();
                }
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
    }
    
    private void c(final as as) {
        if (as != null) {
            try {
                if (ji.ext.v.e == null) {
                    ji.ext.v.e = new c("jiWinExLib4", 2);
                    ji.ext.v.f = new c("jiWinExLib4a", 2);
                }
                if (!ji.ext.v.e.a(as)) {
                    ji.ext.v.e.c(as);
                    try {
                        if (as instanceof Component) {
                            ji.ext.v.f.a(as.toString(), ji.util.d.a((Component)as));
                        }
                        else {
                            ji.ext.v.f.a(as.toString(), " ");
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    private void d(final as as) {
        if (as != null) {
            try {
                if (ji.ext.v.e != null && ji.ext.v.e.a(as)) {
                    ji.ext.v.e.b(as);
                    ji.ext.v.f.a(as.toString());
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean h(final String s) {
        boolean b = false;
        try {
            --ji.ext.v.g;
            if (ji.ext.v.k != null) {
                try {
                    if (ji.ext.v.k.d(s) != null) {
                        this.a();
                        try {
                            if (ji.ext.v.l != null) {
                                final u7 u7 = (u7)ji.ext.v.l.d(s);
                                if (u7 != null) {
                                    this.c.a("jiReleaseHook", s, u7.a(), new Integer(u7.b()));
                                    u7.c();
                                    ji.ext.v.l.a(s);
                                }
                            }
                        }
                        catch (Exception ex) {}
                        try {
                            ji.ext.v.k.a(s);
                        }
                        catch (Exception ex2) {}
                        b = (ji.ext.v.k.b() == 0);
                        if (b) {
                            if (this.c != null) {
                                try {
                                    this.c.a("jiResetHook", new Integer(this.w));
                                }
                                catch (Exception ex3) {}
                                try {
                                    this.c.c("jiExtReleaseAllPipes");
                                }
                                catch (Exception ex4) {}
                                this.c.b();
                                this.c = null;
                            }
                            this.b = null;
                            if (ji.ext.v.e != null) {
                                ji.ext.v.e.c();
                            }
                            if (ji.ext.v.f != null) {
                                ji.ext.v.f.c();
                            }
                            if (ji.ext.v.l != null) {
                                ji.ext.v.l.c();
                            }
                        }
                    }
                }
                catch (Exception ex5) {}
            }
        }
        catch (Throwable t) {}
        return b;
    }
    
    public final int a(final String s, final Component component) {
        this.f(s, "Retrieving parent window handle");
        int n = -1;
        try {
            final Frame e = ji.util.d.e(component);
            final m m = new m(e.getPeer());
            Object o = null;
            final Method b = m.b("gethwnd");
            if (b != null) {
                o = m.a(b);
                if (o != null) {
                    n = (int)o;
                }
            }
            this.f(s, "unable to discover window handle by invoking gethwnd(), attempting getHwnd()");
            if (o == null || n <= 0) {
                final Method b2 = m.b("getHwnd");
                if (b2 != null) {
                    o = m.a(b2);
                    if (o != null) {
                        n = (int)o;
                    }
                }
            }
            if (o == null) {
                this.f(s, "unable to discover window handle by invoking getHwnd(), attempting to use external library");
            }
            if (o == null || n <= 0) {
                final Rectangle bounds = e.getBounds();
                final String s2 = new String(String.valueOf((int)(Math.random() * 10000)));
                final Dialog dialog = new Dialog(e, s2, false);
                dialog.setSize(89, 12);
                dialog.setLocation(-1000, -1000);
                dialog.show();
                n = this.a(s2, bounds, component, s, null);
                dialog.hide();
                dialog.dispose();
            }
            if (n <= 0) {
                this.f(s, "unable to discover window handle by using external library");
            }
            else {
                this.f(s, "Window handle = ".concat(String.valueOf(String.valueOf(n))));
            }
        }
        catch (Exception ex) {
            this.f(s, String.valueOf(String.valueOf(new StringBuffer("There was a problem when attempting to discover the parent window handle (").append(ex).append(")"))));
        }
        return n;
    }
    
    public boolean a(final boolean y) {
        final boolean y2 = this.y;
        this.y = y;
        return y2;
    }
    
    public String g(final String z) {
        final String z2 = this.z;
        this.z = z;
        return z2;
    }
    
    private void f(final String s, final String s2) {
        if (this.y) {
            String concat = "";
            if (this.z != null && this.z.length() > 0) {
                concat = String.valueOf(String.valueOf(this.z)).concat(": ");
            }
            ji.io.h.d(s, String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s2))));
        }
    }
    
    public void a(final boolean b, final String s, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s, null);
                }
                this.a();
                if (this.c != null) {
                    if (this.a6 == null) {
                        this.a6 = this.c.b("setDebugging");
                    }
                    this.c.a(this.a6, new Boolean(b));
                }
                else {
                    this.f(s, "jiex1Invoker object is null");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean b(final String s, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s, null);
                }
                this.a();
                if (this.ba == null) {
                    this.ba = this.c.b("isWindowsXPSP2OrGreater");
                }
                return (boolean)this.c.a(this.ba);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean c(final String s, final Component component) {
        final int a = this.a(s, component);
        return a > 0 && this.a(a, s, component);
    }
    
    private boolean a(final int n, final String s, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s, null);
                }
                this.a();
                if (this.a7 == null) {
                    this.a7 = this.c.b("isInternetExplorer7");
                }
                return (boolean)this.c.a(this.a7, new Integer(n));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean a(final String s, final String s2, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s2, null);
                }
                this.a();
                if (this.c != null) {
                    final Object a = this.c.a("registryKeyExists_LOCAL_MACHINE", s);
                    if (a != null) {
                        if (a instanceof Boolean) {
                            return (boolean)a;
                        }
                        if (ji.util.d.cy()) {
                            ji.io.h.d(s2, "invalid return type");
                        }
                    }
                    else if (ji.util.d.cy()) {
                        ji.io.h.d(s2, "invoke returns null");
                    }
                }
                else if (ji.util.d.cy()) {
                    ji.io.h.d(s2, "invoke library is null");
                }
            }
            else if (ji.util.d.cy()) {
                ji.io.h.d(s2, "not v3 features");
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public boolean b(final String s, final String s2, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s2, null);
                }
                this.a();
                if (this.c != null) {
                    final Object a = this.c.a("registryKeyDelete_LOCAL_MACHINE", s);
                    if (a != null) {
                        if (a instanceof Boolean) {
                            return (boolean)a;
                        }
                        if (ji.util.d.cy()) {
                            ji.io.h.d(s2, "invalid return type");
                        }
                    }
                    else if (ji.util.d.cy()) {
                        ji.io.h.d(s2, "invoke returns null");
                    }
                }
                else if (ji.util.d.cy()) {
                    ji.io.h.d(s2, "invoke library is null");
                }
            }
            else if (ji.util.d.cy()) {
                ji.io.h.d(s2, "not v3 features");
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public long a(final String s, final boolean b, final String s2, final Component component) {
        try {
            if (!ji.util.i.c(7) || !ji.util.e.t()) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(s2, "not v3 features");
                }
                return 0L;
            }
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s2, null);
            }
            this.a();
            if (this.c == null) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(s2, "invoke library is null");
                }
                return 0L;
            }
            final Object a = this.c.a("loadLibrary", s, new Boolean(b));
            if (a != null) {
                return (long)a;
            }
            if (ji.util.d.cy()) {
                ji.io.h.d(s2, "invoke returns null");
            }
            return 0L;
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    public long c(final String s, final String s2, final Component component) {
        try {
            if (!ji.util.i.c(7) || !ji.util.e.t()) {
                return 0L;
            }
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s2, null);
            }
            this.a();
            final Long n = (Long)this.c.a("getModuleHandle", s);
            if (n != null) {
                return n;
            }
            return 0L;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    public boolean a(final long n, final String s, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!ji.ext.v.r) {
                    ji.ext.v.r = this.d(component, s, null);
                }
                this.a();
                return (boolean)this.c.a("freeLibrary", new Long(n));
            }
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public final long a(final String s, final String s2, final String s3) {
        final boolean b = false;
        try {
            if (this.ah == null) {
                this.ah = this.c.b("openFile");
            }
            boolean b2 = false;
            if (s2 != null && s2.equals("rw")) {
                b2 = true;
            }
            if (s != null) {
                final Object a = this.c.a(this.ah, s, new Boolean(b2));
                if (a instanceof Long) {
                    return (long)a;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return b ? 1 : 0;
    }
    
    public final void a(final long n, final byte[] array, final int n2, final int n3, final boolean b) {
        try {
            if (this.ab == null) {
                this.ab = this.c.b("writeFileBytes");
            }
            this.c.a(this.ab, new Long(n), array, new Integer(n2), new Integer(n3), new Boolean(b));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void a(final long n, final int[] array, final int n2, final int n3, final boolean b) {
        try {
            if (this.as == null) {
                this.as = this.c.b("writeFileInts");
            }
            this.c.a(this.as, new Long(n), array, new Integer(n2), new Integer(n3), new Boolean(b));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void a(final long n, final long n2) {
        try {
            if (this.ac == null) {
                this.ac = this.c.b("seekFile");
            }
            this.c.a(this.ac, new Long(n), new Long(n2));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final long a(final long n) {
        try {
            if (this.ae == null) {
                this.ae = this.c.b("fileLength");
            }
            final Object a = this.c.a(this.ae, new Long(n));
            if (a != null) {
                return (long)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0L;
    }
    
    public final long a(final String s, final String s2) {
        try {
            if (this.ax == null) {
                this.ax = this.c.b("fileLengthAbs");
            }
            final Object a = this.c.a(this.ax, s);
            if (a != null) {
                return (long)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return -1L;
    }
    
    public final long b(final String s, final String s2) {
        try {
            if (this.ay == null) {
                this.ay = this.c.b("fileLastModifiedAbs");
            }
            final Object a = this.c.a(this.ay, s);
            if (a != null) {
                return (long)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0L;
    }
    
    public final long b(final long n) {
        try {
            if (this.af == null) {
                this.af = this.c.b("getFilePointer");
            }
            final Object a = this.c.a(this.af, new Long(n));
            if (a != null) {
                return (long)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0L;
    }
    
    public final void c(final long n) {
        try {
            if (this.ag == null) {
                this.ag = this.c.b("closeFile");
            }
            this.c.a(this.ag, new Long(n));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void a(final long n, final int n2) {
        try {
            if (this.am == null) {
                this.am = this.c.b("writeFileByte");
            }
            this.c.a(this.am, new Long(n), new Integer(n2));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void b(final long n, final int n2) {
        try {
            if (this.aj == null) {
                this.aj = this.c.b("writeFileShort");
            }
            this.c.a(this.aj, new Long(n), new Integer(n2));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void c(final long n, final int n2) {
        try {
            if (this.ak == null) {
                this.ak = this.c.b("writeFileInt");
            }
            this.c.a(this.ak, new Long(n), new Integer(n2));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void b(final long n, final long n2) {
        try {
            if (this.an == null) {
                this.an = this.c.b("writeFileLong");
            }
            this.c.a(this.an, new Long(n), new Long(n2));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final int a(final long n, final byte[] array, final int n2, final int n3) {
        try {
            if (this.al == null) {
                this.al = this.c.b("readFileBytes");
            }
            final Object a = this.c.a(this.al, new Long(n), array, new Integer(n2), new Integer(n3));
            if (a != null) {
                return (int)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0;
    }
    
    public final int b(final long n, final int[] array, final int n2, final int n3, final boolean b) {
        try {
            if (this.at == null) {
                this.at = this.c.b("readFileInts");
            }
            final Object a = this.c.a(this.at, new Long(n), array, new Integer(n2), new Integer(n3), new Boolean(b));
            if (a != null) {
                return (int)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0;
    }
    
    public final int d(final long n) {
        try {
            if (this.ao == null) {
                this.ao = this.c.b("readFileByte");
            }
            final Object a = this.c.a(this.ao, new Long(n));
            if (a != null) {
                return (int)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0;
    }
    
    public final int e(final long n) {
        try {
            if (this.ap == null) {
                this.ap = this.c.b("readFileShort");
            }
            final Object a = this.c.a(this.ap, new Long(n));
            if (a != null) {
                return (int)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0;
    }
    
    public final int f(final long n) {
        try {
            if (this.aq == null) {
                this.aq = this.c.b("readFileInt");
            }
            final Object a = this.c.a(this.aq, new Long(n));
            if (a != null) {
                return (int)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0;
    }
    
    public final long g(final long n) {
        try {
            if (this.ar == null) {
                this.ar = this.c.b("readFileLong");
            }
            final Object a = this.c.a(this.ar, new Long(n));
            if (a != null) {
                return (long)a;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return 0L;
    }
    
    public final boolean c(final String s, final String s2) {
        try {
            if (this.av == null) {
                this.av = this.c.b("fileExists");
            }
            if (s != null) {
                final Object a = this.c.a(this.av, s);
                if (a instanceof Boolean) {
                    return (boolean)a;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public final boolean d(final String s, final String s2) {
        try {
            if (this.aw == null) {
                this.aw = this.c.b("deleteFile");
            }
            if (s != null) {
                final Object a = this.c.a(this.aw, s);
                if (a instanceof Boolean) {
                    return (boolean)a;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public final boolean e(final String s, final String s2) {
        try {
            if (this.a4 == null) {
                this.a4 = this.c.b("moveToDisk");
            }
            if (s != null) {
                final Object a = this.c.a(this.a4, s);
                if (a instanceof Boolean) {
                    return (boolean)a;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public final void a(final String s, final int n, final int n2, final int n3, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        try {
            if (this.a2 == null) {
                this.a2 = this.c.b("setParams");
            }
            this.c.a(this.a2, new Integer(n), new Integer(n2), new Integer(n3), new Boolean(b), new Boolean(b2), new Boolean(b3), new Boolean(b4));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final String a(final Component component, final String s) {
        try {
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s, null);
            }
            this.a();
            if (this.a5 == null) {
                this.a5 = this.c.b("getWindowsDirectory");
            }
            return (String)this.c.a(this.a5);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return null;
        }
    }
    
    public final boolean a(final Component component, final String s, final String s2, final boolean b) {
        try {
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s, null);
            }
            this.a();
            if (this.a8 == null) {
                this.a8 = this.c.b("addFontResource");
            }
            return (boolean)this.c.a(this.a8, s2, new Boolean(b));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    public final boolean b(final Component component, final String s, final String s2, final boolean b) {
        try {
            if (!ji.ext.v.r) {
                ji.ext.v.r = this.d(component, s, null);
            }
            this.a();
            if (this.a8 == null) {
                this.a8 = this.c.b("removeFontResource");
            }
            return (boolean)this.c.a(this.a8, s2, new Boolean(b));
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    static {
        v.a = null;
        v.e = null;
        v.f = null;
        v.g = 0;
        v.h = "\\\\.\\pipe\\v1mwque";
        v.i = "\\\\.\\pipe\\v1mwfque";
        v.j = "\\\\.\\pipe\\v1mwlque";
        v.k = null;
        v.l = null;
        v.m = null;
        v.n = null;
        v.o = false;
        v.p = false;
        v.q = false;
        v.r = false;
        v.s = false;
        v.t = 0L;
        v.x = new Object();
        v.aa = false;
    }
    
    class tq implements Runnable
    {
        String a;
        String b;
        int c;
        boolean d;
        FileInputStream e;
        DataInputStream f;
        
        public tq(final String a, final String b, final int c) {
            this.a = null;
            this.b = ji.ext.v.this.d;
            this.c = 0;
            this.d = false;
            this.e = null;
            this.f = null;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public int a() {
            return this.c;
        }
        
        private final void b() {
            try {
                if (this.f != null) {
                    this.f.close();
                    this.f = null;
                }
            }
            catch (IOException ex) {}
            try {
                if (this.e != null) {
                    this.e.close();
                    this.e = null;
                }
            }
            catch (IOException ex2) {}
        }
        
        public void run() {
            try {
                this.e = new FileInputStream(this.a);
                if (this.e != null) {
                    this.f = new DataInputStream(this.e);
                    while (!this.d) {
                        final String line = this.f.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.toLowerCase().equals("end")) {
                            this.d = true;
                            break;
                        }
                        if (!line.toLowerCase().startsWith("purged")) {
                            continue;
                        }
                        ji.net.a0.a(ji.util.d.bc(line.substring("purged".length())), this.b);
                    }
                    this.b();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    if (ji.ext.v.m != null) {
                        ji.ext.v.m.a(this.b);
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                ji.ext.v.p = false;
            }
        }
    }
    
    class u7 implements Runnable
    {
        String a;
        String b;
        int c;
        boolean d;
        FileInputStream e;
        DataInputStream f;
        
        public u7(final String a, final String b, final int c) {
            this.a = null;
            this.b = ji.ext.v.this.d;
            this.c = 0;
            this.d = false;
            this.e = null;
            this.f = null;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public String a() {
            return this.a;
        }
        
        public int b() {
            return this.c;
        }
        
        public void c() {
            this.d = true;
        }
        
        private final void d() {
            try {
                if (this.f != null) {
                    this.f.close();
                    this.f = null;
                }
            }
            catch (IOException ex) {}
            try {
                if (this.e != null) {
                    this.e.close();
                    this.e = null;
                }
            }
            catch (IOException ex2) {}
        }
        
        public void run() {
            try {
                this.e = new FileInputStream(this.a);
                if (this.e != null) {
                    this.f = new DataInputStream(this.e);
                    while (!this.d) {
                        final String line = this.f.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.toLowerCase().equals("end")) {
                            this.d = true;
                            break;
                        }
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        if (stringTokenizer.countTokens() < 6) {
                            continue;
                        }
                        final String s = (String)stringTokenizer.nextElement();
                        final String s2 = (String)stringTokenizer.nextElement();
                        final String s3 = (String)stringTokenizer.nextElement();
                        final String s4 = (String)stringTokenizer.nextElement();
                        final String s5 = (String)stringTokenizer.nextElement();
                        final String s6 = (String)stringTokenizer.nextElement();
                        if (!ji.util.i.c(7) || !ji.util.e.t()) {
                            continue;
                        }
                        final int c = ji.util.d.c(s3, 0);
                        final int c2 = ji.util.d.c(s4, 0);
                        final int c3 = ji.util.d.c(s5, 0);
                        int c4 = ji.util.d.c(s6, 0);
                        if (c4 != 0) {
                            c4 = -c4;
                        }
                        ji.ext.v.this.a(new g9(this, 3, 0, 3, c4, c3), c, c2);
                    }
                    this.d();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    if (ji.ext.v.l != null) {
                        ji.ext.v.l.a(this.b);
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
}
