// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public final class aU extends aJ
{
    public static aU q;
    public static aU w;
    private static cr q;
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
        return cu.i;
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
        return this.q(45);
    }
    
    public final boolean a() {
        return this.q(40);
    }
    
    public final boolean s() {
        return this.q(39);
    }
    
    public final boolean d() {
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
            this.e = new Font(this.q, this.w, this.q - (W.e() ? 2 : 4));
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
        return "Themes/" + this.e.toLowerCase() + "/";
    }
    
    public final Image q() {
        if (this.q(60)) {
            return cu.r;
        }
        return null;
    }
    
    public final Image w() {
        if (this.q(59)) {
            return cu.t;
        }
        return null;
    }
    
    public final boolean f() {
        return !this.q(43);
    }
    
    public final boolean g() {
        return !this.q(42);
    }
    
    public final boolean h() {
        return this.q(58);
    }
    
    public final boolean j() {
        return this.q(57);
    }
    
    public final boolean k() {
        return this.q(56);
    }
    
    public final void q(final boolean b) {
        this.q(56, false);
    }
    
    public final boolean l() {
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
    
    public aU(final int n, final String s) {
        super(n, s);
        this.m = Color.black;
        this.Q = Color.black;
        this.y = 100;
        this.q = ah.e;
        this.w = ah.e;
        this.e = Color.black;
        this.r = ah.e;
        this.t = Color.black;
        this.y = Color.white;
        this.u = Color.black;
        this.i = ah.e;
        this.o = Color.black;
        this.p = Color.red;
        this.a = ah.e;
        this.s = Color.blue;
        this.d = ah.q;
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
    
    public final void q(final aU au) {
        this.q(au.q());
        this.q = au.q;
        this.w = au.w;
        this.e = au.e;
        this.r = au.r;
        this.u = au.u;
        this.i = au.i;
        this.o = au.o;
        this.p = au.p;
        this.a = au.a;
        this.s = au.s;
        this.d = au.d;
        this.f = au.f;
        this.g = au.g;
        this.q = new String(au.q);
        this.w = au.w;
        this.q = au.q;
        this.t = au.t;
        this.y = au.y;
        this.h = au.h;
        this.j = au.j;
        this.q(au.e.toLowerCase());
        this.e = au.e;
        if (au.q != null) {
            this.q = au.q;
        }
        if (au.w != null) {
            this.w = au.w;
        }
        this.k = au.k;
        this.l = au.l;
        this.z = au.z;
        this.x = au.x;
        this.c = au.c;
        this.v = au.v;
        this.b = au.b;
        this.n = au.n;
        this.w = au.w;
        this.r = au.r;
        this.t = au.t;
        this.m = au.m;
        this.Q = au.Q;
        this.y = au.y;
        this.u = au.u;
        this.i = au.i;
    }
    
    static {
        aU.w = (aU.q = new aU(0, ""));
        (aU.q = new cr(67341, 1)).q(0, 62);
        aU.q.q(0, 0, 1);
        aU.q.q(0, 1, ah.e.getRGB());
        aU.q.q(0, 2, ah.e.getRGB());
        aU.q.q(0, 3, Color.black.getRGB());
        aU.q.q(0, 4, ah.e.getRGB());
        aU.q.q(0, 5, Color.black.getRGB());
        aU.q.q(0, 6, ah.e.getRGB());
        aU.q.q(0, 7, Color.black.getRGB());
        aU.q.q(0, 8, Color.red.getRGB());
        aU.q.q(0, 9, ah.e.getRGB());
        aU.q.q(0, 10, Color.blue.getRGB());
        aU.q.q(0, 11, ah.q.getRGB());
        aU.q.q(0, 12, 0);
        aU.q.q(0, 13, 12);
        aU.q.q(0, 15, Color.black.getRGB());
        aU.q.q(0, 16, Color.white.getRGB());
        aU.q.q(0, 0, ak.q("Default"));
        aU.q.q(0, 1, "SansSerif");
        aU.q.q(0, 2, "Default");
        aU.q.q(0, 14, 0);
    }
}
