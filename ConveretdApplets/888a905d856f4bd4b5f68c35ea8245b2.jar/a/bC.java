// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class bC extends bp
{
    public static bC q;
    public static bC w;
    private static dI q;
    public Color q;
    public Color w;
    public Color e;
    public Color r;
    public Color t;
    public Color y;
    public Color u;
    public Color i;
    public Color o;
    public Color p;
    public Color a;
    public Color s;
    public Color d;
    public Color f;
    public Color g;
    public Color h;
    public Color j;
    public String q;
    public int q;
    public int w;
    private Font q;
    private Font w;
    private Font e;
    public int e;
    private String e;
    private Image q;
    private Image w;
    public Color k;
    public Color l;
    public Color z;
    public Color x;
    public Color c;
    public Color v;
    public Color b;
    public Color n;
    public String w;
    public int r;
    public int t;
    public Color m;
    public Color Q;
    public int y;
    private int u;
    private int i;
    
    public final boolean q() {
        return this.q(53);
    }
    
    public final boolean w() {
        return this.q(52);
    }
    
    public static boolean e() {
        return dN.i;
    }
    
    public final boolean r() {
        return this.q(51);
    }
    
    public final boolean t() {
        return this.q(50);
    }
    
    public final boolean y() {
        return this.q(49);
    }
    
    public final boolean u() {
        return this.q(48);
    }
    
    public final boolean i() {
        return this.q(47);
    }
    
    public final boolean o() {
        return this.q(46);
    }
    
    public final boolean p() {
        return this.q(44);
    }
    
    public final boolean a() {
        return this.q(45);
    }
    
    public final boolean s() {
        return this.q(40);
    }
    
    public final boolean d() {
        return this.q(39);
    }
    
    public final boolean f() {
        return this.q(38);
    }
    
    public final Font q() {
        if (this.w == null) {
            this.w = new Font(this.q, this.w | 0x1, this.q);
        }
        return this.w;
    }
    
    public final Font w() {
        if (this.q == null) {
            this.q = new Font(this.q, this.w, this.q);
        }
        return this.q;
    }
    
    public final Font e() {
        if (this.e == null || this.e.getSize() + 4 != this.q.getSize()) {
            this.e = new Font(this.q, this.w, this.q - (ap.t() ? 2 : 4));
        }
        return this.e;
    }
    
    public final void q() {
        this.q = null;
        this.w = null;
    }
    
    public final void q(final String e) {
        if (e == null) {
            return;
        }
        this.e = e;
    }
    
    public final String e() {
        return this.e.toLowerCase();
    }
    
    public final String r() {
        return "Themes/" + this.e.toLowerCase() + "/";
    }
    
    public final Image q() {
        if (this.q(60)) {
            return dN.r;
        }
        return null;
    }
    
    public final Image w() {
        if (this.q(59)) {
            return dN.t;
        }
        return null;
    }
    
    public final boolean g() {
        return this.q(60);
    }
    
    public final void q(final boolean b) {
        this.q(60, b);
    }
    
    public final boolean h() {
        return this.q(59);
    }
    
    public final void w(final boolean b) {
        this.q(59, b);
    }
    
    public final boolean j() {
        return !this.q(43);
    }
    
    public final void e(final boolean b) {
        this.q(43, !b);
    }
    
    public final boolean k() {
        return !this.q(42);
    }
    
    public final void r(final boolean b) {
        this.q(42, !b);
    }
    
    public final boolean l() {
        return this.q(58);
    }
    
    public final void t(final boolean b) {
        this.q(58, b);
    }
    
    public final boolean z() {
        return this.q(57);
    }
    
    public final void y(final boolean b) {
        this.q(57, b);
    }
    
    public final boolean x() {
        return this.q(56);
    }
    
    public final void u(final boolean b) {
        this.q(56, b);
    }
    
    public final boolean c() {
        return this.q(55);
    }
    
    public final void i(final boolean b) {
        this.q(55, b);
    }
    
    public final Image e() {
        return this.q;
    }
    
    public final void q(final Image q) {
        this.q = q;
    }
    
    public final Image r() {
        return this.w;
    }
    
    public final void w(final Image w) {
        this.w = w;
    }
    
    public final int r() {
        return this.u;
    }
    
    public final void q(final int u) {
        if (u <= 1024 && u >= 400) {
            this.u = u;
        }
    }
    
    public final int t() {
        return this.i;
    }
    
    public final void w(final int i) {
        if (i <= 768 && i >= 300) {
            this.i = i;
        }
    }
    
    public final String toString() {
        return "outerBackground = " + this.q + "\n" + "innerBackground = " + this.w + "\n" + "helpText = " + this.e + "\n" + "helpBackground = " + this.r + "\n" + "tabsText = " + this.u + "\n" + "tabsBackground = " + this.i + "\n" + "normalMessages = " + this.o + "\n" + "flaggedMessages = " + this.p + "\n" + "normalBackground = " + this.a + "\n" + "privateMessages = " + this.s + "\n" + "privateBackground = " + this.d + "\n" + "fontName = " + this.q + "\n" + "fontSize = " + this.q + "\n" + "fontStyle = " + this.w + "\n" + "directory = " + this.e + "\n" + "roundedCorners = " + this.e + "\n" + "background = " + this.q(60) + "\n" + "chatBackground = " + this.q(59) + "\n" + "scaleChatBackground = " + this.q(58) + "\n" + "imageButtons = " + this.q(57) + "\n" + "imageTabs = " + this.q(56) + "\n";
    }
    
    public bC(final int n, final String s) {
        super(n, s);
        this.m = Color.black;
        this.Q = Color.black;
        this.y = 100;
        this.q = aB.e;
        this.w = aB.e;
        this.e = Color.black;
        this.r = aB.e;
        this.t = Color.black;
        this.y = Color.white;
        this.u = Color.black;
        this.i = aB.e;
        this.o = Color.black;
        this.p = Color.red;
        this.a = aB.e;
        this.s = Color.blue;
        this.d = aB.q;
        this.f = new Color(15658734);
        this.g = this.a;
        this.h = this.q.darker();
        this.j = Color.lightGray.brighter();
        this.q = "SansSerif";
        this.q = 12;
        this.w = 0;
        this.q = null;
        this.w = null;
        this.e = null;
        this.e = 0;
        this.e = "Default";
        this.k = new Color(15526360);
        this.l = Color.white;
        this.z = Color.black;
        this.x = Color.white;
        this.c = new Color(3238597);
        this.v = Color.white;
        this.b = this.j;
        this.n = this.i;
        this.w = this.q;
        this.r = this.q;
        this.t = this.w;
        this.q(51, true);
        this.q(50, true);
    }
    
    public final void q(final bC bc) {
        this.q(bc.w());
        this.q = bc.q;
        this.w = bc.w;
        this.e = bc.e;
        this.r = bc.r;
        this.u = bc.u;
        this.i = bc.i;
        this.o = bc.o;
        this.p = bc.p;
        this.a = bc.a;
        this.s = bc.s;
        this.d = bc.d;
        this.f = bc.f;
        this.g = bc.g;
        this.q = new String(bc.q);
        this.w = bc.w;
        this.q = bc.q;
        this.t = bc.t;
        this.y = bc.y;
        this.h = bc.h;
        this.j = bc.j;
        this.q(bc.e.toLowerCase());
        this.e = bc.e;
        if (bc.q != null) {
            this.q = bc.q;
        }
        if (bc.w != null) {
            this.w = bc.w;
        }
        this.k = bc.k;
        this.l = bc.l;
        this.z = bc.z;
        this.x = bc.x;
        this.c = bc.c;
        this.v = bc.v;
        this.b = bc.b;
        this.n = bc.n;
        this.w = bc.w;
        this.r = bc.r;
        this.t = bc.t;
        this.m = bc.m;
        this.Q = bc.Q;
        this.y = bc.y;
        this.u = bc.u;
        this.i = bc.i;
    }
    
    public final int q(final bC bc) {
        final int q;
        if ((q = super.q(bc)) != 0) {
            return q;
        }
        if (this.q.getRGB() != bc.q.getRGB()) {
            return this.q.getRGB() - bc.q.getRGB();
        }
        if (this.w.getRGB() != bc.w.getRGB()) {
            return this.w.getRGB() - bc.w.getRGB();
        }
        if (this.e.getRGB() != bc.e.getRGB()) {
            return this.e.getRGB() - bc.e.getRGB();
        }
        if (this.r.getRGB() != bc.r.getRGB()) {
            return this.r.getRGB() - bc.r.getRGB();
        }
        if (this.u.getRGB() != bc.u.getRGB()) {
            return this.u.getRGB() - bc.u.getRGB();
        }
        if (this.i.getRGB() != bc.i.getRGB()) {
            return this.i.getRGB() - bc.i.getRGB();
        }
        if (this.o.getRGB() != bc.o.getRGB()) {
            return this.o.getRGB() - bc.o.getRGB();
        }
        if (this.w.getRGB() != bc.w.getRGB()) {
            return this.w.getRGB() - bc.w.getRGB();
        }
        if (this.p.getRGB() != bc.p.getRGB()) {
            return this.p.getRGB() - bc.p.getRGB();
        }
        if (this.a.getRGB() != bc.a.getRGB()) {
            return this.a.getRGB() - bc.a.getRGB();
        }
        if (this.s.getRGB() != bc.s.getRGB()) {
            return this.s.getRGB() - bc.s.getRGB();
        }
        if (this.d.getRGB() != bc.d.getRGB()) {
            return this.d.getRGB() - bc.d.getRGB();
        }
        if (this.f.getRGB() != bc.f.getRGB()) {
            return this.f.getRGB() - bc.f.getRGB();
        }
        if (this.g.getRGB() != bc.g.getRGB()) {
            return this.g.getRGB() - bc.g.getRGB();
        }
        if (this.t.getRGB() != bc.t.getRGB()) {
            return this.t.getRGB() - bc.t.getRGB();
        }
        if (this.y.getRGB() != bc.y.getRGB()) {
            return this.y.getRGB() - bc.y.getRGB();
        }
        if (this.h.getRGB() != bc.h.getRGB()) {
            return this.h.getRGB() - bc.h.getRGB();
        }
        if (this.j.getRGB() != bc.j.getRGB()) {
            return this.j.getRGB() - bc.j.getRGB();
        }
        if (this.k.getRGB() != bc.k.getRGB()) {
            return this.k.getRGB() - bc.k.getRGB();
        }
        if (this.l.getRGB() != bc.l.getRGB()) {
            return this.l.getRGB() - bc.l.getRGB();
        }
        if (this.z.getRGB() != bc.z.getRGB()) {
            return this.z.getRGB() - bc.z.getRGB();
        }
        if (this.x.getRGB() != bc.x.getRGB()) {
            return this.x.getRGB() - bc.x.getRGB();
        }
        if (this.c.getRGB() != bc.c.getRGB()) {
            return this.c.getRGB() - bc.c.getRGB();
        }
        if (this.v.getRGB() != bc.v.getRGB()) {
            return this.v.getRGB() - bc.v.getRGB();
        }
        if (this.b.getRGB() != bc.b.getRGB()) {
            return this.b.getRGB() - bc.b.getRGB();
        }
        if (this.n.getRGB() != bc.n.getRGB()) {
            return this.n.getRGB() - bc.n.getRGB();
        }
        if (this.m.getRGB() != bc.m.getRGB()) {
            return this.m.getRGB() - bc.m.getRGB();
        }
        if (this.Q.getRGB() != bc.Q.getRGB()) {
            return this.Q.getRGB() - bc.Q.getRGB();
        }
        if (this.q.compareTo(bc.q) != 0) {
            return this.q.compareTo(bc.q);
        }
        if (this.w != bc.w) {
            return this.w - bc.w;
        }
        if (this.q != bc.q) {
            return this.q - bc.q;
        }
        if (this.e != bc.e) {
            return this.e - bc.e;
        }
        if (this.q != bc.q) {
            return 1;
        }
        if (this.w != bc.w) {
            return 1;
        }
        if (this.w != null && bc.w != null && this.w.compareTo(bc.w) != 0) {
            return this.w.compareTo(bc.w);
        }
        if (this.r != bc.r) {
            return this.r - bc.r;
        }
        if (this.t != bc.t) {
            return this.t - bc.t;
        }
        if (this.y != bc.y) {
            return this.y - bc.y;
        }
        if (this.u != bc.u) {
            return this.u - bc.u;
        }
        if (this.i != bc.i) {
            return this.i - bc.i;
        }
        return 0;
    }
    
    static {
        bC.w = (bC.q = new bC(0, ""));
        (bC.q = new dI(67341, 1)).q(0, 62);
        bC.q.q(0, 0, 1);
        bC.q.q(0, 1, aB.e.getRGB());
        bC.q.q(0, 2, aB.e.getRGB());
        bC.q.q(0, 3, Color.black.getRGB());
        bC.q.q(0, 4, aB.e.getRGB());
        bC.q.q(0, 5, Color.black.getRGB());
        bC.q.q(0, 6, aB.e.getRGB());
        bC.q.q(0, 7, Color.black.getRGB());
        bC.q.q(0, 8, Color.red.getRGB());
        bC.q.q(0, 9, aB.e.getRGB());
        bC.q.q(0, 10, Color.blue.getRGB());
        bC.q.q(0, 11, aB.q.getRGB());
        bC.q.q(0, 12, 0);
        bC.q.q(0, 13, 12);
        bC.q.q(0, 15, Color.black.getRGB());
        bC.q.q(0, 16, Color.white.getRGB());
        bC.q.q(0, 0, be.w("Default"));
        bC.q.q(0, 1, "SansSerif");
        bC.q.q(0, 2, "Default");
        bC.q.q(0, 14, 0);
    }
}
