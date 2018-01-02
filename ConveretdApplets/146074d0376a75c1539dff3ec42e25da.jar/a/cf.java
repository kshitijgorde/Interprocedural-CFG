// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class cf extends bZ
{
    public static cf q;
    public static cf w;
    private static es q;
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
    public int o;
    public int p;
    public Color m;
    public Color Q;
    public int a;
    private int s;
    private int d;
    
    public final boolean q() {
        return this.q(53);
    }
    
    public final boolean w() {
        return this.q(52);
    }
    
    public static boolean e() {
        return a.y;
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
        return this.q(40);
    }
    
    public final boolean a() {
        return this.q(39);
    }
    
    public final boolean s() {
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
            this.e = new Font(this.q, this.w, this.q - (cV.s() ? 2 : 4));
        }
        return this.e;
    }
    
    public final void q() {
        this.q = null;
        this.w = null;
    }
    
    public final void w(final String e) {
        if (e == null) {
            return;
        }
        this.e = e;
    }
    
    public final String w() {
        return this.e.toLowerCase();
    }
    
    public final String e() {
        return "Themes/" + this.e.toLowerCase() + "/";
    }
    
    public final Image q() {
        if (this.q(60)) {
            return a.a.r;
        }
        return null;
    }
    
    public final Image w() {
        if (this.q(59)) {
            return a.a.t;
        }
        return null;
    }
    
    public final boolean d() {
        return this.q(60);
    }
    
    public final void q(final boolean b) {
        this.q(60, b);
    }
    
    public final boolean f() {
        return this.q(59);
    }
    
    public final void w(final boolean b) {
        this.q(59, b);
    }
    
    public final boolean g() {
        return !this.q(43);
    }
    
    public final void e(final boolean b) {
        this.q(43, !b);
    }
    
    public final boolean h() {
        return !this.q(42);
    }
    
    public final void r(final boolean b) {
        this.q(42, !b);
    }
    
    public final boolean j() {
        return this.q(58);
    }
    
    public final void t(final boolean b) {
        this.q(58, b);
    }
    
    public final boolean k() {
        return this.q(57);
    }
    
    public final void y(final boolean b) {
        this.q(57, b);
    }
    
    public final boolean l() {
        return this.q(56);
    }
    
    public final void u(final boolean b) {
        this.q(56, b);
    }
    
    public final boolean z() {
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
    
    public final int w() {
        return this.s;
    }
    
    public final void q(final int s) {
        if (s <= 1024 && s >= 400) {
            this.s = s;
        }
    }
    
    public final int e() {
        return this.d;
    }
    
    public final void w(final int d) {
        if (d <= 768 && d >= 300) {
            this.d = d;
        }
    }
    
    public final String toString() {
        return "outerBackground = " + this.q + "\n" + "innerBackground = " + this.w + "\n" + "helpText = " + this.e + "\n" + "helpBackground = " + this.r + "\n" + "tabsText = " + this.u + "\n" + "tabsBackground = " + this.i + "\n" + "normalMessages = " + this.o + "\n" + "flaggedMessages = " + this.p + "\n" + "normalBackground = " + this.a + "\n" + "privateMessages = " + this.s + "\n" + "privateBackground = " + this.d + "\n" + "fontName = " + this.q + "\n" + "fontSize = " + this.q + "\n" + "fontStyle = " + this.w + "\n" + "directory = " + this.e + "\n" + "roundedCorners = " + this.e + "\n" + "background = " + this.q(60) + "\n" + "chatBackground = " + this.q(59) + "\n" + "scaleChatBackground = " + this.q(58) + "\n" + "imageButtons = " + this.q(57) + "\n" + "imageTabs = " + this.q(56) + "\n";
    }
    
    public cf(final int n, final String s) {
        super(n, s);
        this.m = Color.black;
        this.Q = Color.black;
        this.a = 100;
        this.q = a.k.e;
        this.w = a.k.e;
        this.e = Color.black;
        this.r = a.k.e;
        this.t = Color.black;
        this.y = Color.white;
        this.u = Color.black;
        this.i = a.k.e;
        this.o = Color.black;
        this.p = Color.red;
        this.a = a.k.e;
        this.s = Color.blue;
        this.d = a.k.q;
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
        this.o = this.q;
        this.p = this.w;
        this.q(51, true);
        this.q(50, true);
    }
    
    public final void q(final cf cf) {
        this.q(cf.q());
        this.q = cf.q;
        this.w = cf.w;
        this.e = cf.e;
        this.r = cf.r;
        this.u = cf.u;
        this.i = cf.i;
        this.o = cf.o;
        this.p = cf.p;
        this.a = cf.a;
        this.s = cf.s;
        this.d = cf.d;
        this.f = cf.f;
        this.g = cf.g;
        this.q = new String(cf.q);
        this.w = cf.w;
        this.q = cf.q;
        this.t = cf.t;
        this.y = cf.y;
        this.h = cf.h;
        this.j = cf.j;
        this.w(cf.e.toLowerCase());
        this.e = cf.e;
        if (cf.q != null) {
            this.q = cf.q;
        }
        if (cf.w != null) {
            this.w = cf.w;
        }
        this.k = cf.k;
        this.l = cf.l;
        this.z = cf.z;
        this.x = cf.x;
        this.c = cf.c;
        this.v = cf.v;
        this.b = cf.b;
        this.n = cf.n;
        this.w = cf.w;
        this.o = cf.o;
        this.p = cf.p;
        this.m = cf.m;
        this.Q = cf.Q;
        this.a = cf.a;
        this.s = cf.s;
        this.d = cf.d;
    }
    
    public final int q(final cf cf) {
        final int q;
        if ((q = super.q(cf)) != 0) {
            return q;
        }
        if (this.q.getRGB() != cf.q.getRGB()) {
            return this.q.getRGB() - cf.q.getRGB();
        }
        if (this.w.getRGB() != cf.w.getRGB()) {
            return this.w.getRGB() - cf.w.getRGB();
        }
        if (this.e.getRGB() != cf.e.getRGB()) {
            return this.e.getRGB() - cf.e.getRGB();
        }
        if (this.r.getRGB() != cf.r.getRGB()) {
            return this.r.getRGB() - cf.r.getRGB();
        }
        if (this.u.getRGB() != cf.u.getRGB()) {
            return this.u.getRGB() - cf.u.getRGB();
        }
        if (this.i.getRGB() != cf.i.getRGB()) {
            return this.i.getRGB() - cf.i.getRGB();
        }
        if (this.o.getRGB() != cf.o.getRGB()) {
            return this.o.getRGB() - cf.o.getRGB();
        }
        if (this.w.getRGB() != cf.w.getRGB()) {
            return this.w.getRGB() - cf.w.getRGB();
        }
        if (this.p.getRGB() != cf.p.getRGB()) {
            return this.p.getRGB() - cf.p.getRGB();
        }
        if (this.a.getRGB() != cf.a.getRGB()) {
            return this.a.getRGB() - cf.a.getRGB();
        }
        if (this.s.getRGB() != cf.s.getRGB()) {
            return this.s.getRGB() - cf.s.getRGB();
        }
        if (this.d.getRGB() != cf.d.getRGB()) {
            return this.d.getRGB() - cf.d.getRGB();
        }
        if (this.f.getRGB() != cf.f.getRGB()) {
            return this.f.getRGB() - cf.f.getRGB();
        }
        if (this.g.getRGB() != cf.g.getRGB()) {
            return this.g.getRGB() - cf.g.getRGB();
        }
        if (this.t.getRGB() != cf.t.getRGB()) {
            return this.t.getRGB() - cf.t.getRGB();
        }
        if (this.y.getRGB() != cf.y.getRGB()) {
            return this.y.getRGB() - cf.y.getRGB();
        }
        if (this.h.getRGB() != cf.h.getRGB()) {
            return this.h.getRGB() - cf.h.getRGB();
        }
        if (this.j.getRGB() != cf.j.getRGB()) {
            return this.j.getRGB() - cf.j.getRGB();
        }
        if (this.k.getRGB() != cf.k.getRGB()) {
            return this.k.getRGB() - cf.k.getRGB();
        }
        if (this.l.getRGB() != cf.l.getRGB()) {
            return this.l.getRGB() - cf.l.getRGB();
        }
        if (this.z.getRGB() != cf.z.getRGB()) {
            return this.z.getRGB() - cf.z.getRGB();
        }
        if (this.x.getRGB() != cf.x.getRGB()) {
            return this.x.getRGB() - cf.x.getRGB();
        }
        if (this.c.getRGB() != cf.c.getRGB()) {
            return this.c.getRGB() - cf.c.getRGB();
        }
        if (this.v.getRGB() != cf.v.getRGB()) {
            return this.v.getRGB() - cf.v.getRGB();
        }
        if (this.b.getRGB() != cf.b.getRGB()) {
            return this.b.getRGB() - cf.b.getRGB();
        }
        if (this.n.getRGB() != cf.n.getRGB()) {
            return this.n.getRGB() - cf.n.getRGB();
        }
        if (this.m.getRGB() != cf.m.getRGB()) {
            return this.m.getRGB() - cf.m.getRGB();
        }
        if (this.Q.getRGB() != cf.Q.getRGB()) {
            return this.Q.getRGB() - cf.Q.getRGB();
        }
        if (this.q.compareTo(cf.q) != 0) {
            return this.q.compareTo(cf.q);
        }
        if (this.w != cf.w) {
            return this.w - cf.w;
        }
        if (this.q != cf.q) {
            return this.q - cf.q;
        }
        if (this.e != cf.e) {
            return this.e - cf.e;
        }
        if (this.q != cf.q) {
            return 1;
        }
        if (this.w != cf.w) {
            return 1;
        }
        if (this.w != null && cf.w != null && this.w.compareTo(cf.w) != 0) {
            return this.w.compareTo(cf.w);
        }
        if (this.o != cf.o) {
            return this.o - cf.o;
        }
        if (this.p != cf.p) {
            return this.p - cf.p;
        }
        if (this.a != cf.a) {
            return this.a - cf.a;
        }
        if (this.s != cf.s) {
            return this.s - cf.s;
        }
        if (this.d != cf.d) {
            return this.d - cf.d;
        }
        return 0;
    }
    
    static {
        cf.w = (cf.q = new cf(0, ""));
        (cf.q = new es(67341, 1)).q(0, 62);
        cf.q.q(0, 0, 1);
        cf.q.q(0, 1, k.e.getRGB());
        cf.q.q(0, 2, k.e.getRGB());
        cf.q.q(0, 3, Color.black.getRGB());
        cf.q.q(0, 4, k.e.getRGB());
        cf.q.q(0, 5, Color.black.getRGB());
        cf.q.q(0, 6, k.e.getRGB());
        cf.q.q(0, 7, Color.black.getRGB());
        cf.q.q(0, 8, Color.red.getRGB());
        cf.q.q(0, 9, k.e.getRGB());
        cf.q.q(0, 10, Color.blue.getRGB());
        cf.q.q(0, 11, k.q.getRGB());
        cf.q.q(0, 12, 0);
        cf.q.q(0, 13, 12);
        cf.q.q(0, 15, Color.black.getRGB());
        cf.q.q(0, 16, Color.white.getRGB());
        cf.q.q(0, 0, eb.q("Default"));
        cf.q.q(0, 1, "SansSerif");
        cf.q.q(0, 2, "Default");
        cf.q.q(0, 14, 0);
    }
}
