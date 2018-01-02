// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import ji.graphic.ce;
import java.lang.reflect.Method;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Rectangle;
import ji.net.bg;
import ji.io.fb;
import ji.net.jiServerException;
import java.io.FileNotFoundException;
import ji.io.ac;
import ji.io.q;
import ji.res.z;
import ji.sec.f;
import ji.v1event.ar;
import java.awt.event.FocusListener;
import ji.v1event.b;
import java.awt.SystemColor;
import ji.v1base.bz;
import ji.v1event.a6;
import ji.v1event.af;
import ji.awt.ax;
import ji.io.h;
import ji.v1base.jiPanel;
import ji.awt.c;
import ji.document.ad;
import java.applet.Applet;
import java.net.URL;
import ji.v1event.bo;
import java.awt.Component;
import java.awt.Color;

public class e
{
    private static boolean a;
    private static boolean b;
    private static boolean c;
    private static boolean d;
    private static String e;
    private static Color f;
    public static Color g;
    public static Color h;
    private static Color i;
    private static Color j;
    private static Color k;
    private static Component l;
    public static bo m;
    private static Object n;
    public static boolean o;
    public static boolean p;
    public static boolean q;
    public static boolean r;
    public static boolean s;
    public static boolean t;
    public static URL u;
    public static Applet v;
    public static ad w;
    public static String[][] x;
    public static String y;
    public static String z;
    public static String aa;
    public static String ab;
    public static String ac;
    public static String ad;
    private static double ae;
    public static int af;
    public static int ag;
    private static boolean ah;
    private static Object ai;
    private static boolean aj;
    private static Object ak;
    public static boolean al;
    public static Object am;
    private static boolean an;
    private static boolean ao;
    private static int ap;
    private static int aq;
    private static double ar;
    private static double as;
    private static int at;
    private static boolean au;
    private static boolean av;
    private static boolean aw;
    private static boolean ax;
    private static Color ay;
    private static Color az;
    private static c a0;
    private static boolean a1;
    private static jiPanel a2;
    private static boolean a3;
    private static boolean a4;
    private static boolean a5;
    private static boolean a6;
    private static String a7;
    private static String a8;
    private static int a9;
    private static int ba;
    public static int bb;
    public static int bc;
    private static c bd;
    private static c be;
    public static int bf;
    public static URL bg;
    public static URL bh;
    private static boolean bi;
    private static boolean bj;
    private static int bk;
    private static String bl;
    private static String[] bm;
    private static String[] bn;
    private static int bo;
    private static boolean bp;
    private static boolean bq;
    private static boolean br;
    private static boolean bs;
    private static boolean bt;
    private static boolean bu;
    private static boolean bv;
    private static boolean bw;
    private static boolean bx;
    private static boolean by;
    private static boolean bz;
    private static boolean b0;
    private static boolean b1;
    private static String b2;
    private static String b3;
    private static c b4;
    private static String b5;
    public static String[] b6;
    public static String[] b7;
    public static int b8;
    private static boolean b9;
    private static boolean ca;
    public static boolean cb;
    private static String cc;
    private static String cd;
    private static boolean ce;
    private static boolean cf;
    public static boolean cg;
    
    public static final void a(final String s) {
        final ax d = ji.util.e.b4.d();
        while (d.a()) {
            ji.io.h.d(s, (String)ji.util.e.b4.d(d.b()));
        }
    }
    
    public static final void b(final String s) {
        if (!ji.util.d.by(s)) {
            final int d = d(s);
            if (d > 0) {
                ji.util.e.b4.a(s);
            }
            ji.util.e.b4.a(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(d + 1))));
        }
    }
    
    public static final void c(final String s) {
        if (!ji.util.d.by(s)) {
            final int d = d(s);
            if (d > 0) {
                ji.util.e.b4.a(s);
            }
            if (d > 1) {
                ji.util.e.b4.a(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(d - 1))));
            }
        }
    }
    
    public static final int d(final String s) {
        if (ji.util.d.by(s)) {
            return 0;
        }
        String s2 = null;
        try {
            s2 = (String)ji.util.e.b4.d(s);
        }
        catch (Exception ex) {}
        if (s2 == null) {
            return 0;
        }
        int c = 0;
        final char[] charArray = s2.toCharArray();
        for (int i = charArray.length - 1; i > 0; --i) {
            if (charArray[i] == ':') {
                c = ji.util.d.c(s2.substring(i + 1), 0);
                break;
            }
        }
        return c;
    }
    
    public static final boolean a() {
        return ji.util.e.bq;
    }
    
    public static final void a(final boolean bp) {
        ji.util.e.bp = bp;
    }
    
    public static final boolean b() {
        return ji.util.e.bp;
    }
    
    public static final void a(final int bo) {
        ji.util.e.bo = bo;
    }
    
    public static final int c() {
        return ji.util.e.bo;
    }
    
    public static final void b(final boolean bj) {
        ji.util.e.bj = bj;
    }
    
    public static final boolean d() {
        return ji.util.e.bj;
    }
    
    public static final void e(final String b2) {
        ji.util.e.b2 = b2;
    }
    
    public static final String e() {
        if (ji.util.d.by(ji.util.e.b2)) {
            return "";
        }
        return ji.util.e.b2;
    }
    
    public static final void f(final String bl) {
        ji.util.e.bl = bl;
    }
    
    public static final String f() {
        return ji.util.e.bl;
    }
    
    public static final void a(final boolean br, final String b3) {
        ji.util.e.br = br;
        ji.util.e.b3 = b3;
    }
    
    public static final void a(final boolean bi, final int bk, final af af, final Component component) {
        final boolean bi2 = ji.util.e.bi;
        ji.util.e.bi = bi;
        ji.util.e.bk = bk;
        if (bi2 && !bi && af != null) {
            af.a(new a6(component, 10, ""));
        }
    }
    
    public static final boolean g() {
        return ji.util.e.bi;
    }
    
    public static final String g(final String s) {
        return ji.util.d.b(ji.util.e.bk, s);
    }
    
    public static final void c(final boolean a4) {
        ji.util.e.a4 = a4;
    }
    
    public static final void a(final jiPanel a2, final boolean a3) {
        ji.util.e.a2 = a2;
        ji.util.e.a3 = a3;
    }
    
    public static final boolean h() {
        return ji.util.e.aw;
    }
    
    public static final void d(final boolean a5) {
        ji.util.e.a5 = a5;
    }
    
    public static final boolean i() {
        return ji.util.e.a5;
    }
    
    public static final void e(final boolean aw) {
        ji.util.e.aw = aw;
    }
    
    public static final boolean h(final String s) {
        return (av() || ji.util.i.c(69)) && !ji.util.i.c(110);
    }
    
    public static final boolean j() {
        return ji.util.e.av;
    }
    
    public static final void f(final boolean av) {
        ji.util.e.av = av;
    }
    
    public static final void g(final boolean an) {
        ji.util.e.an = an;
    }
    
    public static final boolean k() {
        return ji.util.e.an;
    }
    
    public static final void b(final int ap) {
        ji.util.e.ap = ap;
    }
    
    public static final int l() {
        return ji.util.e.ap;
    }
    
    public static final void c(final int aq) {
        ji.util.e.aq = aq;
    }
    
    public static final int m() {
        return ji.util.e.aq;
    }
    
    public static final void a(final double as) {
        ji.util.e.as = as;
    }
    
    public static final double n() {
        return ji.util.e.as;
    }
    
    public static final double o() {
        return ji.util.e.ar;
    }
    
    public static final void h(final boolean au) {
        ji.util.e.au = au;
    }
    
    public static final boolean p() {
        return ji.util.e.au;
    }
    
    public static final void d(final int at) {
        ji.util.e.at = at;
    }
    
    public static final int q() {
        return ji.util.e.at;
    }
    
    public static final void i(final boolean ao) {
        ji.util.e.ao = ao;
    }
    
    public static final boolean r() {
        return ji.util.e.ao;
    }
    
    public static final void j(final boolean a) {
        ji.util.e.a = a;
    }
    
    public static final boolean s() {
        return ji.util.e.a;
    }
    
    public static final void k(final boolean b) {
        ji.util.e.b = b;
    }
    
    public static final void l(final boolean c) {
        ji.util.e.c = c;
    }
    
    public static final void m(final boolean bs) {
        ji.util.e.bs = bs;
    }
    
    public static final void n(final boolean bt) {
        ji.util.e.bt = bt;
    }
    
    public static final boolean t() {
        return ji.util.e.bt;
    }
    
    public static final void o(final boolean bu) {
        ji.util.e.bu = bu;
    }
    
    public static final boolean u() {
        return ji.util.e.bu && !ji.util.d.ei();
    }
    
    public static final void p(final boolean by) {
        ji.util.e.by = by;
    }
    
    public static final boolean v() {
        return ji.util.e.by;
    }
    
    public static final void q(final boolean bz) {
        ji.util.e.bz = bz;
    }
    
    public static final boolean w() {
        return ji.util.e.bz;
    }
    
    public static final boolean x() {
        return ji.util.e.bv && u();
    }
    
    public static boolean y() {
        return ji.util.e.bx;
    }
    
    public static void r(final boolean bx) {
        ji.util.e.bx = bx;
    }
    
    public static final boolean z() {
        return (ji.util.e.bw && u()) || ji.util.i.c(144);
    }
    
    public static final void s(final boolean bw) {
        ji.util.e.bw = bw;
    }
    
    public static final void t(final boolean bv) {
        ji.util.e.bv = bv;
    }
    
    public static final void u(final boolean b0) {
        ji.util.e.b0 = b0;
    }
    
    public static final boolean aa() {
        return true;
    }
    
    public static final void v(final boolean b1) {
        ji.util.e.b1 = b1;
    }
    
    public static final boolean ab() {
        return ji.util.e.b1;
    }
    
    public static final boolean ac() {
        return true;
    }
    
    public static final boolean ad() {
        return ji.util.e.c;
    }
    
    public static final boolean ae() {
        return ji.util.e.bs;
    }
    
    public static final boolean af() {
        return ji.util.e.b;
    }
    
    public static final boolean ag() {
        return ji.util.i.c(223) && ji.util.e.b && ji.util.d.di() != 1;
    }
    
    public static final double ah() {
        return ji.util.e.ae;
    }
    
    public static final void b(final double ae) {
        ji.util.e.ae = ae;
    }
    
    public static final boolean ai() {
        return true;
    }
    
    public static final void w(final boolean s) {
        ji.util.e.s = s;
    }
    
    public static final boolean aj() {
        return ji.util.e.s;
    }
    
    public static final void x(final boolean t) {
        ji.util.e.t = t;
    }
    
    public static final boolean ak() {
        return ji.util.e.t;
    }
    
    public static final void i(final String b5) {
        ji.util.e.b5 = b5;
    }
    
    public static final String al() {
        return ji.util.e.b5;
    }
    
    public static final void a(final jiPanel jiPanel, final Color background) {
        if (background != null) {
            try {
                for (int componentCount = jiPanel.getComponentCount(), i = 0; i < componentCount; ++i) {
                    final Component component = jiPanel.getComponent(i);
                    try {
                        component.setBackground(background);
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public static final void b(final jiPanel jiPanel, final Color lightShade) {
        if (lightShade != null) {
            try {
                for (int componentCount = jiPanel.getComponentCount(), i = 0; i < componentCount; ++i) {
                    final Component component = jiPanel.getComponent(i);
                    try {
                        if (component instanceof jiPanel) {
                            ((jiPanel)component).setLightShade(lightShade);
                        }
                        else if (component instanceof bz) {
                            ((bz)component).a(lightShade);
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public static final void a(final URL u) {
        ji.util.e.u = u;
    }
    
    public static final URL am() {
        return ji.util.e.u;
    }
    
    public static final URL j(final String s) {
        final Object y = ji.util.d.y(s);
        if (y != null && y instanceof Applet) {
            return ((Applet)y).getCodeBase();
        }
        return am();
    }
    
    public static final URL k(final String s) {
        final Applet applet = (Applet)ji.util.d.y(s);
        if (applet != null) {
            return applet.getDocumentBase();
        }
        if (ji.util.e.v != null) {
            return ji.util.e.v.getDocumentBase();
        }
        return null;
    }
    
    public static final void l(final String e) {
        e.e = e;
    }
    
    public static final String an() {
        return ji.util.e.e;
    }
    
    public static final Color ao() {
        return ji.util.e.f;
    }
    
    public static final void ap() {
        ji.util.e.f = null;
        ji.util.e.i = null;
        ji.util.e.j = null;
    }
    
    public static final void a(final Color f) {
        boolean b = true;
        if (ji.util.e.f == null && f == null) {
            b = false;
        }
        else if (ji.util.e.f != null && f != null && ji.util.e.f.equals(f)) {
            b = false;
        }
        ji.util.e.f = f;
        if (b) {
            ji.util.e.i = null;
            ji.util.e.j = null;
        }
        if (ji.util.e.f == null) {
            ji.util.e.g = null;
            ji.util.e.h = null;
        }
        else {
            ji.util.e.g = ji.util.e.f.darker().darker();
            ji.util.e.h = ji.util.e.f.brighter();
        }
    }
    
    public static final void b(final Color h) {
        ji.util.e.h = h;
    }
    
    public static final Color aq() {
        Color color = SystemColor.control;
        if (ji.util.e.f != null) {
            color = ji.util.e.f;
        }
        return color;
    }
    
    public static final void a(final Component component) {
        Color background = SystemColor.control;
        try {
            if (ji.util.e.f != null) {
                background = ji.util.e.f;
            }
            if (component.getBackground() != null) {
                if (!component.getBackground().equals(background)) {
                    component.setBackground(background);
                }
            }
            else {
                component.setBackground(background);
            }
        }
        catch (Exception ex) {
            try {
                component.setBackground(background);
            }
            catch (Exception ex2) {}
        }
    }
    
    public static final void a(final String s, final b b) {
        m(s).a(b);
    }
    
    public static final void b(final String s, final b b) {
        m(s).b(b);
    }
    
    public static final void y(final boolean a6) {
        ji.util.e.a6 = a6;
    }
    
    public static final boolean ar() {
        return ji.util.e.a6;
    }
    
    public static final bo m(final String s) {
        synchronized (ji.util.e.n) {
            if (ji.util.e.m == null) {
                ji.util.e.m = new bo(s);
            }
        }
        // monitorexit(e.n)
        return ji.util.e.m;
    }
    
    private static final void al(final String s) {
    }
    
    public static final void a(final String s, final Component component) {
        component.addFocusListener(m(s));
    }
    
    public static final void b(final String s, final Component component) {
        component.removeFocusListener(m(s));
    }
    
    public static final void b(final Component component) {
        if (ji.util.d.c4()) {
            return;
        }
        try {
            if (component != null) {
                if (a7()) {
                    if (ji.util.i.c(102)) {
                        ji.io.h.d(null, "Requesting focus on ".concat(String.valueOf(String.valueOf(component))));
                    }
                    component.requestFocus();
                }
                else {
                    a(new tn(component), null);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean c(final String s, final Component component) {
        boolean b = false;
        try {
            final bo m = m(s);
            if (component != null && m != null) {
                final Object a = m.a();
                if (a != null && a.equals(component)) {
                    b = true;
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final void a(final String s, final ar ar, final boolean b) {
        final bo m = m(s);
        if (b) {
            m.a(ar);
        }
        else {
            m.b(ar);
        }
    }
    
    public static final void a(final String s, final ad ad) {
        m(s).a(s, ad);
        al(s);
    }
    
    public static final boolean as() {
        return ji.util.e.a4 & ji.util.d.do();
    }
    
    public static final void n(final String s) {
        if (!as()) {
            m(s).b();
        }
    }
    
    public static final void d(final String s, final Component component) {
        if (ji.util.e.cb) {
            ji.util.e.l = m(s).c();
        }
    }
    
    public static final boolean at() {
        return true;
    }
    
    public static final void au() {
        try {
            if (ji.util.e.cb && ji.util.e.l != null) {
                try {
                    if (at()) {
                        b(ji.util.e.l);
                        ji.util.e.l = null;
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
    
    public static final boolean c(final Component component) {
        return component != null && component.isShowing() && component.isVisible() && component.isEnabled() && (!(component instanceof ar) || ((ar)component).acceptFocus());
    }
    
    public static final boolean av() {
        return ji.util.e.r;
    }
    
    public static final boolean aw() {
        if (!ji.util.e.o) {
            Class a3 = null;
            try {
                a3 = ji.util.d.a3("java.awt.event.KeyEvent");
            }
            catch (Exception ex) {}
            if (a3 != null) {
                ji.util.e.p = true;
            }
            else {
                ji.util.e.p = false;
            }
            ji.util.e.o = true;
        }
        return ji.util.e.p;
    }
    
    public static final boolean o(final String s) {
        if (!ji.util.e.q) {
            Class a3 = null;
            try {
                if (ji.util.e.cd == null) {
                    ji.util.e.cd = ji.sec.f.a("java.version", s);
                }
                if (ji.util.e.cd == null) {
                    a3 = ji.util.d.a3("java.awt.print.PrinterJob");
                }
            }
            catch (Exception ex) {}
            if (ji.util.e.cd != null) {
                ji.util.e.cd = ji.util.e.cd.substring(0, 3);
                if (ji.util.d.a(ji.util.e.cd, 1.1) < 1.2) {
                    ji.util.e.r = false;
                }
                else {
                    ji.util.e.r = true;
                }
            }
            else if (a3 != null) {
                ji.util.e.r = true;
            }
            else {
                ji.util.e.r = false;
            }
            ji.util.e.q = true;
        }
        return ji.util.e.r;
    }
    
    public static final boolean ax() {
        return ji.util.e.ce;
    }
    
    public static final void z(final boolean b) {
        if (b) {
            ji.util.e.ce = true;
        }
    }
    
    public static final boolean ay() {
        return ji.util.e.cf;
    }
    
    public static final void aa(final boolean cf) {
        ji.util.e.cf = cf;
    }
    
    public static final boolean p(final String s) {
        return aw() || av();
    }
    
    public static final String q(final String s) {
        try {
            if (ji.util.e.cc == null) {
                ji.util.e.cc = ji.sec.f.a("os.name", s);
            }
        }
        catch (Exception ex) {
            ji.util.e.cc = ex.toString();
        }
        return ji.util.e.cc;
    }
    
    public static final int r(final String s) {
        int n = 0;
        if (u(s)) {
            if (t(s)) {
                n = 1;
            }
            else if (az()) {
                n = 3;
            }
            else if (v(s)) {
                n = 2;
            }
            else if (aa(s)) {
                n = 4;
            }
            else if (s(s)) {
                n = 5;
            }
            else if (w(s)) {
                n = 6;
            }
            else if (y(s)) {
                n = 7;
            }
        }
        else {
            n = -1;
        }
        return n;
    }
    
    public static final boolean s(final String s) {
        boolean a = a("windows 2000", s);
        if (!a && u("windows nt")) {
            final String a2 = ji.sec.f.a("os.version", s);
            if (a2 != null && a2.startsWith("5.0")) {
                a = true;
            }
        }
        return a;
    }
    
    public static final boolean t(final String s) {
        return a("windows 95", s);
    }
    
    public static final boolean u(final String s) {
        return a("windows ", s);
    }
    
    public static final boolean v(final String s) {
        return a("windows 98", s);
    }
    
    public static final boolean w(final String s) {
        final boolean a = a("windows xp", s);
        if (!a && a("windows nt", s)) {
            final String a2 = ji.sec.f.a("os.version", s);
            if (a2 != null && a2.startsWith("5.1")) {
                return true;
            }
        }
        return a;
    }
    
    public static final boolean x(final String s) {
        final int r = r(s);
        return r != -1 && r >= 5;
    }
    
    public static final boolean y(final String s) {
        boolean a = a("windows vista", s);
        if (!a && a("windows nt", s)) {
            final String a2 = ji.sec.f.a("os.version", s);
            if (a2 != null && a2.startsWith("6.0")) {
                a = true;
            }
        }
        return a;
    }
    
    public static final boolean z(final String s) {
        final int r = r(s);
        return r != -1 && r >= 7;
    }
    
    public static final boolean aa(final String s) {
        return a("windows me", s);
    }
    
    public static final boolean ab(final String s) {
        if (!ji.util.e.ca) {
            final String a = ji.sec.f.a("os.version", s);
            if (a != null && a.startsWith("4") && a("windows nt", s)) {
                ji.util.e.b9 = true;
            }
            ji.util.e.ca = true;
        }
        return ji.util.e.b9;
    }
    
    public static final boolean az() {
        return ji.util.e.b9;
    }
    
    public static final boolean a(final String s, final String s2) {
        boolean b = false;
        try {
            final String q = q(s2);
            if (q != null && q.toLowerCase().indexOf(s) >= 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final Color c(final Color color) {
        return new Color(-16777216 + (Math.min(color.getRed() * 115 / 100, 255) << 16) + (Math.min(color.getGreen() * 115 / 100, 255) << 8) + Math.min(color.getBlue() * 115 / 100, 255));
    }
    
    public static final Color a0() {
        try {
            if (ji.util.e.i == null) {
                if (ji.util.i.c(7) && t()) {
                    ji.util.e.i = aq().darker();
                }
                else {
                    ji.util.e.i = new Color(0, 122, 179);
                }
            }
        }
        catch (Exception ex) {}
        return ji.util.e.i;
    }
    
    public static final Color a1() {
        try {
            if (ji.util.e.k == null) {
                if (ji.util.i.c(7) && t()) {
                    ji.util.e.k = new Color(0, 149, 189);
                }
                else {
                    ji.util.e.k = a0();
                }
            }
        }
        catch (Exception ex) {}
        return ji.util.e.k;
    }
    
    public static final Color a2() {
        if (ji.util.e.j != null) {
            return ji.util.e.j;
        }
        return a1().brighter();
    }
    
    public static final void d(final Color k) {
        try {
            if (k != null) {
                if (ji.util.e.k != null || k != null) {
                    if (ji.util.e.k != null && k != null && ji.util.e.k.equals(k)) {}
                }
                ji.util.e.k = k;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final Color a3() {
        if (ji.util.e.j != null) {
            return ji.util.e.j;
        }
        return a0().brighter().brighter().brighter();
    }
    
    public static final void e(final Color i) {
        try {
            if (i != null) {
                boolean b = true;
                if (ji.util.e.i == null && i == null) {
                    b = false;
                }
                else if (ji.util.e.i != null && i != null && ji.util.e.i.equals(i)) {
                    b = false;
                }
                ji.util.e.i = i;
                if (b) {
                    d(i);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void f(final Color j) {
        try {
            if (j != null) {
                if (ji.util.e.j != null || j != null) {
                    if (ji.util.e.j != null && j != null && ji.util.e.j.equals(j)) {}
                }
                ji.util.e.j = j;
            }
        }
        catch (Exception ex) {}
    }
    
    private static final String a(final String s, final String s2, final String s3, final String s4, final Exception ex, final Object o, final String s5) {
        String c = null;
        try {
            byte[] array;
            if (ji.util.d.by(s2)) {
                array = ji.res.z.a(s, an(), 1, null, null, s5, new y());
            }
            else {
                array = s2.getBytes();
            }
            if (array != null) {
                final String a = a(new String(array), s3, ex, s5);
                c = ji.io.q.a(o, s5).c("txt");
                final ac ac = new ac(c, true, false, 0, o, s5);
                ac.b(a.getBytes());
                ac.a(o);
            }
        }
        catch (Exception ex2) {
            c = null;
        }
        return c;
    }
    
    private static final String a(final String s, final String s2, final Exception ex, final String s3) {
        String s4 = s;
        try {
            s4 = ae(s4);
            if (!ji.util.d.by(s2)) {
                s4 = ji.util.d.b(s4, "<FILE>", s2);
            }
            else {
                s4 = ji.util.d.b(s4, "<<FILE>>", ji.util.d.b(270, s3));
            }
            s4 = ji.util.d.b(s4, "<ERRORTEXT>", ji.util.d.bc(ex.getMessage()));
            s4 = ji.util.d.b(s4, "<ERROR>", ji.util.d.bc(String.valueOf(String.valueOf(ex.toString())).concat("\n")));
            s4 = ji.util.d.b(s4, "xception: ", "xception:\n \n");
            if (ji.util.e.bd != null) {
                for (int i = 0; i < ji.util.e.bd.b(); ++i) {
                    s4 = ji.util.d.b(s4, (String)ji.util.e.bd.b(i), (String)ji.util.e.be.b(i));
                }
            }
        }
        catch (Exception ex2) {}
        return s4;
    }
    
    public static void ac(final String s) {
        if (s != null) {
            try {
                if (ji.util.e.bd == null) {
                    ji.util.e.bd = new c("jiUtil24");
                    ji.util.e.be = new c("jiUtil25");
                }
                final int index = s.indexOf("<");
                final int index2 = s.indexOf(">");
                if (index >= 0 && index2 >= 0) {
                    final String substring = s.substring(index, index2 + 1);
                    final String substring2 = s.substring(index2 + 1);
                    ji.util.e.bd.c(substring);
                    ji.util.e.be.c(substring2);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private static final String a(final String s, final Object o, final String s2) {
        String c = null;
        try {
            final byte[] a = ji.res.z.a(s, an(), 1, null, null, s2, new y());
            if (a != null) {
                c = ji.io.q.a(o, s2).c(ji.util.d.bh(s));
                final ac ac = new ac(c, true, false, 0, o, s2);
                ac.b(a);
                ac.a(o);
            }
        }
        catch (Exception ex) {
            c = null;
        }
        b(false);
        return c;
    }
    
    public static final boolean a(final String s, final Component component, final String s2) {
        try {
            if (ji.util.e.ad == null || !ji.util.d.eg()) {
                return false;
            }
            ji.util.d.a(String.valueOf(String.valueOf(ji.util.e.ad)).concat(String.valueOf(String.valueOf("?errortext=".concat(String.valueOf(String.valueOf(s)))))), component, null, true, s2);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
        return true;
    }
    
    public static final void ad(final String ac) {
        ji.util.e.ac = ac;
    }
    
    public static final String a4() {
        return ji.util.e.ac;
    }
    
    public static final String a5() {
        return ji.util.e.ab;
    }
    
    public static final String a6() {
        return ji.util.e.aa;
    }
    
    public static final String a(final Exception ex, final Object o, final Component component, final String s) {
        Applet v = (Applet)ji.util.d.y(s);
        if (v == null) {
            v = ji.util.e.v;
        }
        return a(ex, o, v, component, s);
    }
    
    public static final String a(final Exception ex, final Object o, final Applet applet, final Component component, final String s) {
        String s2 = null;
        try {
            String s3 = "";
            String string = "";
            if (o != null) {
                string = o.toString();
            }
            String s4;
            if (ex instanceof FileNotFoundException) {
                s4 = "notfound.txt";
                if (!ji.util.i.c(32)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.util.d.b(940, s)).append("\n \n").append(ji.util.d.b(941, s)).append("\n \n").append(ji.util.d.b(942, s)).append("\n \n").append(ji.util.d.b(943, s)).append(ji.util.d.b(944, s)).append("\n \n").append(ji.util.d.b(945, s)).append("\n \n")));
                    try {
                        if (!ji.util.d.by(ji.util.d.k())) {
                            final int index = s3.toLowerCase().indexOf("daeja");
                            if (index > 0) {
                                final int index2 = s3.indexOf(".\n", index);
                                if (index2 > 0) {
                                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3.substring(0, index)))).append(ji.util.d.k()).append(s3.substring(index2))));
                                }
                                else {
                                    s3 = ji.util.d.b(s3, "daeja", ji.util.d.k());
                                }
                            }
                        }
                    }
                    catch (Exception ex3) {}
                }
                if (ji.util.e.y != null && applet != null) {
                    ji.util.d.a(a(ji.util.e.y, o.toString(), ex, s), component, null, true, s);
                }
            }
            else if (ex instanceof jiServerException) {
                s4 = "openerror.txt";
                if (!ji.util.i.c(32)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.util.d.b(937, s)).append("\n \n").append(ji.util.d.b(938, s)).append("\n \n")));
                }
                if (ji.util.e.z != null && applet != null) {
                    a(ji.util.e.z, o.toString(), ex, s);
                }
            }
            else if (ex instanceof fb) {
                s4 = "openerror.txt";
                if (!ji.util.i.c(32)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.util.d.b(937, s)).append("\n \n").append(ji.util.d.b(938, s)).append("\n \n").append(ji.util.d.b(1293, s)).append("\n \n")));
                }
                if (ji.util.e.z != null && applet != null) {
                    a(ji.util.e.z, o.toString(), ex, s);
                }
            }
            else if (ex instanceof bg) {
                s4 = "openerror.txt";
                if (!ji.util.i.c(32)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.util.d.b(937, s)).append("\n \n").append(ji.util.d.b(938, s)).append("\n \n").append(ji.util.d.b(939, s)).append("\n \n")));
                }
                string = null;
                if (ji.util.e.z != null && applet != null) {
                    a(ji.util.e.z, string, ex, s);
                }
            }
            else {
                s4 = "openerror.txt";
                if (!ji.util.i.c(32)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.util.d.b(937, s)).append("\n \n").append(ji.util.d.b(938, s)).append("\n \n").append(ji.util.d.b(939, s)).append("\n \n")));
                }
                if (ji.util.e.z != null && applet != null) {
                    a(ji.util.e.z, o.toString(), ex, s);
                }
            }
            s2 = a(s4, s3, string, ex.toString(), ex, component, s);
            if (s2 == null) {
                if (ex instanceof FileNotFoundException) {
                    s2 = a("notfound.tif", (Object)component, s);
                }
                else {
                    s2 = a("openerror.tif", (Object)component, s);
                    if (s2 == null) {
                        s2 = a("notfound.tif", (Object)component, s);
                    }
                }
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        b(false);
        return s2;
    }
    
    public static final String ae(String s) {
        if (ji.util.e.b7 != null) {
            if (ji.util.e.b6 != null) {
                if (ji.util.e.b8 >= 0) {
                    s = ji.util.d.b(s, "<DOCREF>", ji.util.e.b6[ji.util.e.b8]);
                    s = ji.util.d.b(s, "<DOCREF>", "-");
                }
                else {
                    s = ji.util.d.b(s, "<DOCREF>", "-");
                }
            }
            if (ji.util.e.b8 >= 0) {
                s = ji.util.d.b(s, "<DOCINDEX>", String.valueOf(String.valueOf(new StringBuffer("").append(ji.util.e.b8 + 1))));
                s = ji.util.d.b(s, "<DOCINDEX>", "-");
                s = ji.util.d.b(s, "<DOCNUM>", "".concat(String.valueOf(String.valueOf(ji.util.e.b7.length))));
                s = ji.util.d.b(s, "<DOCNUM>", "-");
            }
            else {
                s = ji.util.d.b(s, "<DOCINDEX>", "-");
                s = ji.util.d.b(s, "<DOCNUM>", "-");
            }
        }
        else {
            s = ji.util.d.b(s, "<DOCREF>", "-");
            s = ji.util.d.b(s, "<DOCINDEX>", "-");
            s = ji.util.d.b(s, "<DOCNUM>", "-");
        }
        return s;
    }
    
    public static final boolean a(final Rectangle rectangle, final Point point) {
        boolean b = false;
        try {
            if (point.x >= rectangle.x && point.x <= rectangle.x + rectangle.width && point.y >= rectangle.y && point.y <= rectangle.y + rectangle.height) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final boolean a(final Component component, final Rectangle rectangle) {
        return a(component, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static final boolean a(final Component component, final int n, final int n2, final int n3, final int n4) {
        boolean b = false;
        try {
            if (component != null) {
                final Rectangle bounds = component.getBounds();
                if (bounds.x != n || bounds.y != n2 || bounds.width != n3 || bounds.height != n4) {
                    b = true;
                    component.setBounds(n, n2, n3, n4);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return b;
    }
    
    public static final boolean a(final Component component, final int n, final int n2) {
        boolean b = false;
        try {
            final Point location = component.getLocation();
            if (location.x != n || location.y != n2) {
                b = true;
                component.setLocation(n, n2);
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static void a(final Runnable runnable, final String s) {
        a(runnable, false, s);
    }
    
    public static void a(final Runnable runnable, final boolean b, final String s) {
        if (av()) {
            if (a7() && !b) {
                try {
                    runnable.run();
                }
                catch (Throwable t) {
                    ji.util.d.a(t);
                }
            }
            else {
                try {
                    final Class<?> forName = Class.forName("java.awt.EventQueue");
                    final m m = new m();
                    m.a(forName);
                    final Method b2 = m.b("invokeLater");
                    if (b2 != null) {
                        m.a(b2, runnable);
                    }
                }
                catch (Throwable t2) {
                    ji.util.d.a(t2);
                    runnable.run();
                }
            }
        }
        else if (a7() && !b) {
            try {
                if (ji.util.i.c(124)) {
                    ji.io.h.d(s, "Shutdown dialog: dispatch already, running");
                }
                runnable.run();
            }
            catch (Throwable t3) {
                ji.util.d.a(t3);
            }
        }
        else {
            try {
                if (ji.util.i.c(124)) {
                    ji.io.h.d(s, "Shutdown dialog: putting onto queue");
                }
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new bm(Toolkit.getDefaultToolkit(), runnable));
            }
            catch (Throwable t4) {
                ji.util.d.a(t4);
                runnable.run();
            }
        }
    }
    
    public static void b(final Runnable runnable, final String s) {
        if (av()) {
            try {
                final Class<?> forName = Class.forName("java.awt.EventQueue");
                final m m = new m();
                m.a(forName);
                final Method b = m.b("invokeLater");
                if (b != null) {
                    m.a(b, runnable);
                }
            }
            catch (Exception ex) {
                runnable.run();
            }
        }
        else {
            runnable.run();
        }
    }
    
    public static boolean a7() {
        if (av()) {
            try {
                final Class<?> forName = Class.forName("java.awt.EventQueue");
                final m m = new m();
                m.a(forName);
                final Method b = m.b("isDispatchThread");
                if (b == null) {
                    return false;
                }
                final Object a = m.a(b);
                if (a != null) {
                    return (boolean)a;
                }
                return false;
            }
            catch (Exception ex) {
                return false;
            }
        }
        try {
            if (ji.util.i.c(124)) {
                ji.io.h.d("", "thread name ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
            }
            if (Thread.currentThread().getName().startsWith("AWT-EventQueue-")) {
                return true;
            }
        }
        catch (Exception ex2) {}
        return false;
    }
    
    public static void ab(final boolean ax) {
        ji.util.e.ax = ax;
    }
    
    public static boolean af(final String s) {
        return ji.util.e.ax && (!ji.util.d.ay(s) || av());
    }
    
    public static void g(final Color ay) {
        ji.util.e.ay = ay;
    }
    
    public static Color a8() {
        return ji.util.e.ay;
    }
    
    public static void h(final Color az) {
        ji.util.e.az = az;
    }
    
    public static Color a9() {
        return ji.util.e.az;
    }
    
    public static final void ba() {
        try {
            ac(true);
            if (ji.util.e.a0 != null) {
                ji.util.e.a0.c();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void bb() {
        if (ji.util.d.do()) {
            try {
                for (int i = 0; i < ji.util.e.a0.b(); ++i) {
                    final Component component = (Component)ji.util.e.a0.b(i);
                    try {
                        if (component instanceof ce) {
                            ((ce)component).a();
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                ji.util.e.a1 = true;
            }
        }
        try {
            if (ji.util.e.a2 != null) {
                ji.util.d.ew();
                if (ji.util.e.a3) {
                    ji.util.e.a2.setBorderColor(aq().darker());
                }
                else {
                    ji.util.e.a2.setBackground(aq().darker());
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    public static final void bc() {
        ac(false);
    }
    
    public static final void ac(final boolean b) {
        if (ji.util.d.do()) {
            try {
                for (int i = 0; i < ji.util.e.a0.b(); ++i) {
                    final Component component = (Component)ji.util.e.a0.b(i);
                    try {
                        if (component instanceof ce) {
                            ((ce)component).b();
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                ji.util.e.a1 = false;
            }
        }
        try {
            if (ji.util.e.a2 != null) {
                ji.util.d.ew();
                if (ji.util.e.a3) {
                    ji.util.e.a2.setBorderColor(aq());
                }
                else {
                    ji.util.e.a2.setBackground(aq());
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    public static final String a(final Object o) {
        String s = "none";
        try {
            if (o instanceof jiPanel) {
                s = ((jiPanel)o).getId();
            }
            else if (o instanceof bz) {
                s = ((bz)o).getId();
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public static final void a(final URL url, final Component component, final af af, final String s, final String s2) {
        a(url, null, component, af, s, s2);
    }
    
    public static final void a(final URL url, final String s, final Component component, final af af, final String s2, final String s3) {
        try {
            if (ji.util.e.v != null) {
                if (ji.util.d.a(url, s2, String.valueOf(String.valueOf(s3)).concat("/Util2ShowDoc")) <= 0 && !ji.util.d.av(s2)) {
                    ji.util.d.a("Hyperlink", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(260, s2)))).append(": ").append(url))), component, 30, null, af, s2);
                }
                else if (s != null) {
                    ji.util.e.v.getAppletContext().showDocument(url, s);
                }
                else {
                    ji.util.e.v.getAppletContext().showDocument(url);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex, "Hyperlink", component, af, s2);
        }
    }
    
    public static final void ag(final String a7) {
        ji.util.e.a7 = a7;
    }
    
    public static final void ah(final String a8) {
        ji.util.e.a8 = a8;
    }
    
    public static final void e(final int n) {
        ji.util.e.a9 = n;
        ji.util.e.ba = n;
    }
    
    public static final String ai(final String s) {
        String s2;
        if (ji.util.e.bi) {
            final String g = g(s);
            if (ji.util.d.by(g)) {
                if (ji.util.e.a7 != null) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.a7))).append("\n").append(ji.util.d.b(755, s)).append("...")));
                }
                else {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(710, s)))).append("\n").append(ji.util.d.b(755, s)).append("...")));
                }
            }
            else if (ji.util.e.a7 != null) {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.a7))).append("\n").append(ji.util.d.b(755, s)).append("...")));
            }
            else {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(710, s)))).append(" ").append(g).append("\n").append(ji.util.d.b(755, s)).append("...")));
            }
        }
        else if (ji.util.e.br) {
            s2 = ji.util.d.e(ji.util.e.b3, s);
        }
        else if (ji.util.e.a8 != null) {
            s2 = ji.util.e.a8;
        }
        else if (ji.util.e.a7 != null) {
            s2 = ji.util.e.a7;
        }
        else {
            s2 = ji.util.d.b(430, s);
        }
        return s2;
    }
    
    public static final boolean bd() {
        return true;
    }
    
    public static final int be() {
        if (bd()) {
            return 0;
        }
        return ji.util.e.a9;
    }
    
    public static final int bf() {
        if (bd()) {
            return 0;
        }
        return ji.util.e.ba;
    }
    
    public static final boolean bg() {
        return ji.util.e.d;
    }
    
    public static final void b(final URL bg) {
        ji.util.e.bg = bg;
    }
    
    public static final void c(final URL bh) {
        ji.util.e.bh = bh;
    }
    
    public static final URL bh() {
        return ji.util.e.bh;
    }
    
    public static final boolean bi() {
        return ji.util.e.bg != null;
    }
    
    public static final boolean bj() {
        return ji.util.e.bh != null;
    }
    
    public static final void a(final Component component, final af af, final String s) {
        try {
            if (ji.util.d.cy()) {
                ji.io.h.d(s, "LogHandler = ".concat(String.valueOf(String.valueOf(ji.util.e.bg))));
            }
            if (ji.util.e.bg != null) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(s, "Sending log...");
                }
                final String a = ji.util.d.a(component, s);
                if (ji.util.d.cy()) {
                    ji.io.h.d(s, "Log = ".concat(String.valueOf(String.valueOf(a))));
                }
                if (a != null) {
                    if (ji.io.ac.d(a, s)) {
                        a(a, ji.util.e.bg, component, af, s);
                        try {
                            ji.io.ac.c(a, s);
                        }
                        catch (Exception ex2) {}
                        if (ji.util.d.cy()) {
                            ji.io.h.d(s, "Send log complete.");
                        }
                    }
                    else {
                        ji.io.h.d(s, "Log not created");
                    }
                }
                else {
                    ji.io.h.d(s, "Log not created");
                }
            }
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Log not sent");
            ex.printStackTrace();
        }
    }
    
    public static final void a(final String s, final URL url, final Component component, final af af, final String s2) throws Exception {
        if (ji.util.d.cy()) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Send file ").append(s).append(" to ").append(url))));
        }
        if (url != null) {
            if (ji.io.ac.d(s, s2)) {
                final String string = url.toString();
                final byte[] array = new byte[(int)ji.io.ac.a(s, s2)];
                final ac ac = new ac(s, false, false, 0, false, component, s2);
                final int a = ac.a(array);
                ac.a(component);
                if (a > 0) {
                    a(array);
                    if (ji.util.d.cy()) {
                        ji.io.h.d(s2, "Server object = ".concat(String.valueOf(String.valueOf(string))));
                    }
                }
                else {
                    ji.io.h.d(s2, "File empty: ".concat(String.valueOf(String.valueOf(s))));
                }
            }
            else {
                ji.io.h.d(s2, "File note found: ".concat(String.valueOf(String.valueOf(s))));
            }
        }
        else {
            ji.io.h.d(s2, "Empty server URL");
        }
    }
    
    private static final String a(final byte[] array) {
        String s = "";
        if (array != null) {
            final int length = array.length;
            final String concat = "0000000000".concat(String.valueOf(String.valueOf(length)));
            final int length2 = concat.length();
            s = concat.substring(length2 - 10, length2);
            for (int i = 0; i < length; ++i) {
                final String concat2 = "00".concat(String.valueOf(String.valueOf(array[i] & 0xFF)));
                final int length3 = concat2.length();
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(concat2.substring(length3 - 3, length3))));
            }
        }
        return s;
    }
    
    public static final void a(final boolean ah, final Object ai) {
        ji.util.e.ah = ah;
        if (ah) {
            ji.util.e.ai = ai;
        }
        else {
            ji.util.e.ai = null;
        }
    }
    
    public static final Object bk() {
        if (ji.util.e.ah) {
            return ji.util.e.ai;
        }
        return null;
    }
    
    public static final void b(final boolean aj, final Object ak) {
        ji.util.e.aj = aj;
        if (aj) {
            ji.util.e.ak = ak;
        }
        else {
            ji.util.e.ak = null;
        }
    }
    
    public static final Object bl() {
        if (ji.util.e.aj) {
            return ji.util.e.ak;
        }
        return null;
    }
    
    public static final void c(final boolean al, final Object am) {
        ji.util.e.al = al;
        if (al) {
            ji.util.e.am = am;
        }
        else {
            ji.util.e.am = null;
        }
    }
    
    public static final boolean bm() {
        return ji.util.e.al;
    }
    
    public static final void a(final String s, final Object o, final String s2, final String s3) {
        try {
            if (s != null) {
                if (s.startsWith("V!RF")) {
                    final String substring = s.substring("V!RF".length());
                    long n = 0L;
                    final byte[] bytes = substring.getBytes();
                    final int length = bytes.length;
                    for (int i = 0; i < length; ++i) {
                        n += bytes[i];
                    }
                    ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(" file checksum (").append(length).append(" bytes) : ").append(n))));
                }
                else {
                    long n2 = 0L;
                    final ac ac = new ac(s, false, false, 0, o, s3);
                    final int n3 = (int)ac.w();
                    final byte[] array = new byte[65536];
                    int a;
                    while ((a = ac.a(array)) != -1) {
                        for (int j = 0; j < a; ++j) {
                            n2 += array[j];
                        }
                    }
                    ac.a(o);
                    ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(" file checksum (").append(n3).append(" bytes) : ").append(n2).append(" > ").append(s))));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final String b(final String s, final String s2) {
        try {
            final String query = new URL(s).getQuery();
            if (ji.util.d.by(query)) {
                return null;
            }
            StringTokenizer stringTokenizer;
            String substring;
            String nextToken;
            int index;
            for (stringTokenizer = new StringTokenizer(query, "&"), substring = null; stringTokenizer.hasMoreTokens() && substring == null; substring = nextToken.substring(index + 1)) {
                nextToken = stringTokenizer.nextToken();
                index = nextToken.indexOf(61);
                if (index != -1 && index > 0 && nextToken.substring(0, index).equals(s2)) {}
            }
            return substring;
        }
        catch (MalformedURLException ex) {
            ji.util.d.a(ex);
            return null;
        }
    }
    
    public static final boolean c(final String s, final String s2) {
        if (s != null && s2 != null) {
            if (!s.equals(s2)) {
                return true;
            }
        }
        else {
            if (s == null && s2 != null) {
                return true;
            }
            if (s != null && s2 == null) {
                return true;
            }
        }
        return false;
    }
    
    public static String[] aj(final String s) {
        am(s);
        return ji.util.e.bn;
    }
    
    public static String[] ak(final String s) {
        am(s);
        return ji.util.e.bm;
    }
    
    public static final int d(final String s, final String s2) {
        if (ji.util.d.by(s)) {
            return 0;
        }
        am(s2);
        int n = 0;
        final String lowerCase = s.toLowerCase();
        for (int i = 0; i < ji.util.e.bm.length; ++i) {
            if (lowerCase.equals(ji.util.e.bm[i].toLowerCase())) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static void a(final int n, final Image image, final String s, final ad ad) {
        try {
            final int width = image.getWidth(null);
            final int height = image.getHeight(null);
            final long n2 = width * height;
            final int[] array = new int[width * height];
            final c3 a = ji.util.d.a(image, s, ad);
            ji.util.d.a(ad, s, image, a, 0, 0, width, height, width, array);
            ji.util.d.a(image, a);
            final long n3 = 16777215L;
            boolean b = true;
            for (int n4 = 0; n4 < n2; ++n4) {
                if ((array[n4] & n3) != n3) {
                    b = false;
                    break;
                }
            }
            if (b) {
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Page ").append(n).append(" is all white!"))));
                ad.b6();
            }
        }
        catch (Exception ex) {}
    }
    
    private static final void am(final String s) {
        if (ji.util.e.bm == null) {
            ji.util.e.bm = new String[4];
            ji.util.e.bn = new String[4];
            a(0, String.valueOf(String.valueOf(new StringBuffer("<_self><").append(ji.util.d.b(520, s)).append(">"))));
            a(1, String.valueOf(String.valueOf(new StringBuffer("<_blank><").append(ji.util.d.b(521, s)).append(">"))));
            a(2, String.valueOf(String.valueOf(new StringBuffer("<_parent><").append(ji.util.d.b(523, s)).append(">"))));
            a(3, String.valueOf(String.valueOf(new StringBuffer("<_top><").append(ji.util.d.b(522, s)).append(">"))));
        }
    }
    
    private static final void a(final int n, final String s) {
        final String[] b = ji.util.d.b(s, s, "<", ">");
        if (b != null && b.length > 2) {
            ji.util.e.bm[n] = b[1];
            ji.util.e.bn[n] = b[2];
        }
    }
    
    public static final void a(final String[] array) {
        try {
            if (array != null) {
                ji.util.e.bm = new String[array.length];
                ji.util.e.bn = new String[array.length];
                for (int i = 0; i < array.length; ++i) {
                    a(i, array[i]);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        ji.util.e.a = true;
        ji.util.e.b = false;
        ji.util.e.c = false;
        ji.util.e.d = false;
        ji.util.e.e = null;
        ji.util.e.f = null;
        ji.util.e.g = null;
        ji.util.e.h = null;
        ji.util.e.i = null;
        ji.util.e.j = null;
        ji.util.e.k = null;
        ji.util.e.l = null;
        ji.util.e.m = null;
        ji.util.e.n = new Object();
        ji.util.e.o = false;
        ji.util.e.p = false;
        ji.util.e.q = false;
        ji.util.e.r = false;
        ji.util.e.s = false;
        ji.util.e.t = false;
        ji.util.e.u = null;
        ji.util.e.v = null;
        ji.util.e.w = null;
        ji.util.e.x = new String[][] { { "notfound.txt", "not found" } };
        ji.util.e.y = null;
        ji.util.e.z = null;
        ji.util.e.aa = null;
        ji.util.e.ab = null;
        ji.util.e.ac = null;
        ji.util.e.ad = null;
        ji.util.e.ae = 0.7;
        ji.util.e.af = 30;
        ji.util.e.ag = 30;
        ji.util.e.ah = false;
        ji.util.e.ai = null;
        ji.util.e.aj = false;
        ji.util.e.ak = null;
        ji.util.e.al = false;
        ji.util.e.am = null;
        ji.util.e.an = true;
        ji.util.e.ao = true;
        ji.util.e.ap = 1;
        ji.util.e.aq = 0;
        ji.util.e.ar = 0.02;
        ji.util.e.as = ji.util.e.ar;
        ji.util.e.at = 0;
        ji.util.e.au = false;
        ji.util.e.av = false;
        ji.util.e.aw = false;
        ji.util.e.ax = true;
        ji.util.e.ay = new Color(0, 0, 0);
        ji.util.e.az = new Color(255, 255, 225);
        ji.util.e.a0 = new c("jiUtil21");
        ji.util.e.a1 = true;
        ji.util.e.a2 = null;
        ji.util.e.a3 = false;
        ji.util.e.a4 = false;
        ji.util.e.a5 = true;
        ji.util.e.a6 = true;
        ji.util.e.a7 = null;
        ji.util.e.a8 = null;
        ji.util.e.a9 = 250;
        ji.util.e.ba = 3000;
        ji.util.e.bb = 20;
        ji.util.e.bc = 500;
        ji.util.e.bd = null;
        ji.util.e.be = null;
        ji.util.e.bf = 800;
        ji.util.e.bg = null;
        ji.util.e.bh = null;
        ji.util.e.bi = false;
        ji.util.e.bj = false;
        ji.util.e.bk = -1;
        ji.util.e.bl = null;
        ji.util.e.bm = null;
        ji.util.e.bn = null;
        ji.util.e.bo = 1;
        ji.util.e.bp = false;
        ji.util.e.bq = false;
        ji.util.e.br = false;
        ji.util.e.bs = false;
        ji.util.e.bt = true;
        ji.util.e.bu = true;
        ji.util.e.bv = false;
        ji.util.e.bw = false;
        ji.util.e.bx = false;
        ji.util.e.by = true;
        ji.util.e.bz = true;
        ji.util.e.b0 = true;
        ji.util.e.b1 = false;
        ji.util.e.b2 = null;
        ji.util.e.b3 = null;
        ji.util.e.b4 = new c("jiUtil26");
        ji.util.e.b5 = null;
        ji.util.e.b6 = null;
        ji.util.e.b7 = null;
        ji.util.e.b8 = 0;
        ji.util.e.b9 = false;
        ji.util.e.ca = false;
        ji.util.e.cb = true;
        ji.util.e.cc = null;
        ji.util.e.cd = null;
        ji.util.e.ce = false;
        ji.util.e.cf = true;
        ji.util.e.cg = false;
    }
}
