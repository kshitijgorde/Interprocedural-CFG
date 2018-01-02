// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public final class be extends ba
{
    public static be q;
    public static be w;
    private static cJ q;
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
    public int t;
    public int y;
    private Font q;
    private Font w;
    private Font e;
    public int u;
    private String r;
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
    public int i;
    public int o;
    public Color m;
    public Color Q;
    public int p;
    private int a;
    private int s;
    
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
            this.w = new Font(this.q, this.y | 0x1, this.t);
        }
        return this.w;
    }
    
    public final Font w() {
        if (this.q == null) {
            this.q = new Font(this.q, this.y, this.t);
        }
        return this.q;
    }
    
    public final Font e() {
        if (this.e == null || this.e.getSize() + 4 != this.q.getSize()) {
            this.e = new Font(this.q, this.y, this.t - (bI.a() ? 2 : 4));
        }
        return this.e;
    }
    
    public final void q() {
        this.q = null;
        this.w = null;
    }
    
    public final void q(final String r) {
        if (r == null) {
            return;
        }
        this.r = r;
    }
    
    public final String w() {
        return "Themes/" + this.r.toLowerCase() + "/";
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
        return !this.q(43);
    }
    
    public final boolean f() {
        return !this.q(42);
    }
    
    public final boolean g() {
        return this.q(58);
    }
    
    public final boolean h() {
        return this.q(57);
    }
    
    public final boolean j() {
        return this.q(56);
    }
    
    public final void q(final boolean b) {
        this.q(56, false);
    }
    
    public final boolean k() {
        return this.q(55);
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
    
    public final int y() {
        return this.a;
    }
    
    public final void y(final int a) {
        if (a <= 1024 && a >= 400) {
            this.a = a;
        }
    }
    
    public final int u() {
        return this.s;
    }
    
    public final void u(final int s) {
        if (s <= 768 && s >= 300) {
            this.s = s;
        }
    }
    
    public final String toString() {
        return "outerBackground = " + this.q + "\n" + "innerBackground = " + this.w + "\n" + "helpText = " + this.e + "\n" + "helpBackground = " + this.r + "\n" + "tabsText = " + this.u + "\n" + "tabsBackground = " + this.i + "\n" + "normalMessages = " + this.o + "\n" + "flaggedMessages = " + this.p + "\n" + "normalBackground = " + this.a + "\n" + "privateMessages = " + this.s + "\n" + "privateBackground = " + this.d + "\n" + "fontName = " + this.q + "\n" + "fontSize = " + this.t + "\n" + "fontStyle = " + this.y + "\n" + "directory = " + this.r + "\n" + "roundedCorners = " + this.u + "\n" + "background = " + this.q(60) + "\n" + "chatBackground = " + this.q(59) + "\n" + "scaleChatBackground = " + this.q(58) + "\n" + "imageButtons = " + this.q(57) + "\n" + "imageTabs = " + this.q(56) + "\n";
    }
    
    public be(final int n, final String s) {
        super(n, s);
        this.m = Color.black;
        this.Q = Color.black;
        this.p = 100;
        this.q = a.i.e;
        this.w = a.i.e;
        this.e = Color.black;
        this.r = a.i.e;
        this.t = Color.black;
        this.y = Color.white;
        this.u = Color.black;
        this.i = a.i.e;
        this.o = Color.black;
        this.p = Color.red;
        this.a = a.i.e;
        this.s = Color.blue;
        this.d = a.i.q;
        this.f = new Color(15658734);
        this.g = this.a;
        this.h = this.q.darker();
        this.j = Color.lightGray.brighter();
        this.q = "SansSerif";
        this.t = 12;
        this.y = 0;
        this.q = null;
        this.w = null;
        this.e = null;
        this.u = 0;
        this.r = "Default";
        this.k = new Color(15526360);
        this.l = Color.white;
        this.z = Color.black;
        this.x = Color.white;
        this.c = new Color(3238597);
        this.v = Color.white;
        this.b = this.j;
        this.n = this.i;
        this.w = this.q;
        this.i = this.t;
        this.o = this.y;
        this.q(51, true);
        this.q(50, true);
    }
    
    public final void q(final be be) {
        this.q(be.q());
        this.q = be.q;
        this.w = be.w;
        this.e = be.e;
        this.r = be.r;
        this.u = be.u;
        this.i = be.i;
        this.o = be.o;
        this.p = be.p;
        this.a = be.a;
        this.s = be.s;
        this.d = be.d;
        this.f = be.f;
        this.g = be.g;
        this.q = new String(be.q);
        this.y = be.y;
        this.t = be.t;
        this.t = be.t;
        this.y = be.y;
        this.h = be.h;
        this.j = be.j;
        this.q = be.q;
        this.w = be.w;
        this.e = be.e;
        this.r = be.r;
        this.q(be.r.toLowerCase());
        this.u = be.u;
        if (be.q != null) {
            this.q = be.q;
        }
        if (be.w != null) {
            this.w = be.w;
        }
        this.k = be.k;
        this.l = be.l;
        this.z = be.z;
        this.x = be.x;
        this.c = be.c;
        this.v = be.v;
        this.b = be.b;
        this.n = be.n;
        this.w = be.w;
        this.i = be.i;
        this.o = be.o;
        this.m = be.m;
        this.Q = be.Q;
        this.p = be.p;
        this.a = be.a;
        this.s = be.s;
    }
    
    static {
        be.w = (be.q = new be(0, ""));
        (be.q = new cJ(67341, 1)).q(0, 62);
        be.q.q(0, 0, 1);
        be.q.q(0, 1, i.e.getRGB());
        be.q.q(0, 2, i.e.getRGB());
        be.q.q(0, 3, Color.black.getRGB());
        be.q.q(0, 4, i.e.getRGB());
        be.q.q(0, 5, Color.black.getRGB());
        be.q.q(0, 6, i.e.getRGB());
        be.q.q(0, 7, Color.black.getRGB());
        be.q.q(0, 8, Color.red.getRGB());
        be.q.q(0, 9, i.e.getRGB());
        be.q.q(0, 10, Color.blue.getRGB());
        be.q.q(0, 11, i.q.getRGB());
        be.q.q(0, 12, 0);
        be.q.q(0, 13, 12);
        be.q.q(0, 15, Color.black.getRGB());
        be.q.q(0, 16, Color.white.getRGB());
        be.q.q(0, 0, cv.q("Default"));
        be.q.q(0, 1, "SansSerif");
        be.q.q(0, 2, "Default");
        be.q.q(0, 14, 0);
    }
}
