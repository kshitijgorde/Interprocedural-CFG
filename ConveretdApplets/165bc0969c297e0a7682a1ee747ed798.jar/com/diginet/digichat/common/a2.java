// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.esial.util.c;
import com.diginet.digichat.awt.cv;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import com.diginet.digichat.network.v;

public class a2 extends k
{
    public static a2 a;
    public static v b;
    public Color c;
    public Color clrOutText;
    public Color d;
    public Color clrInnText;
    public Color e;
    public Color f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    public Color m;
    public Color n;
    public Color o;
    public Color clrSrchBack;
    public Color clrSrchText;
    public Color clrCtrlText;
    public Color clrCtrlBack;
    public Color clrScrlText;
    public Color clrScrlBack;
    public Color clrIMText;
    public Color clrIMBack;
    public Color clrBaddyText;
    public Color clrBaddyBack;
    public Color clrInvText;
    public Color clrInvBack;
    public Color clrMenuText;
    public Color clrMenuBack;
    public Color clrHghlText;
    public Color clrHghlBack;
    public String p;
    public String strMenuFont;
    public int q;
    public int r;
    public int nMenuStyle;
    public int nMenuSize;
    private Font s;
    private Font t;
    private Font u;
    private Font fntMenuFont;
    public int v;
    private String w;
    public Image x;
    public Image z;
    public Image aa;
    public Image[] imgStates;
    
    public Font a() {
        if (this.t == null) {
            this.t = new Font(this.p, this.r | 0x1, this.q);
        }
        return this.t;
    }
    
    public Font b() {
        if (this.s == null) {
            this.s = new Font(this.p, this.r, this.q);
        }
        return this.s;
    }
    
    public Font c() {
        if (this.u == null || this.u.getSize() + 4 != this.s.getSize()) {
            this.u = new Font(this.p, this.r, this.q - 4);
        }
        return this.u;
    }
    
    public Font getMenuFont() {
        if (!this.getFont()) {
            return this.b();
        }
        if (this.fntMenuFont == null) {
            this.fntMenuFont = new Font(this.strMenuFont, this.nMenuStyle, this.nMenuSize);
        }
        return this.fntMenuFont;
    }
    
    public void d() {
        this.s = null;
        this.t = null;
    }
    
    public void a(final String w) {
        if (w == null) {
            return;
        }
        this.w = w;
    }
    
    public String e() {
        return this.w.toLowerCase();
    }
    
    public String f() {
        return String.valueOf(String.valueOf("Themes/").concat(String.valueOf(this.e()))).concat(String.valueOf("/"));
    }
    
    public Image g() {
        return this.i() ? this.z : null;
    }
    
    public Image h() {
        return this.j() ? this.aa : null;
    }
    
    public boolean i() {
        return this.i(60);
    }
    
    public void a(final boolean b) {
        this.a(60, b);
    }
    
    public boolean j() {
        return this.i(59);
    }
    
    public void b(final boolean b) {
        this.a(59, b);
    }
    
    public boolean k() {
        return this.i(58);
    }
    
    public void c(final boolean b) {
        this.a(58, b);
    }
    
    public boolean l() {
        return this.i(57);
    }
    
    public void d(final boolean b) {
        this.a(57, b);
    }
    
    public boolean m() {
        return this.i(56);
    }
    
    public void e(final boolean b) {
        this.a(56, b);
    }
    
    public boolean getState() {
        return this.i(55);
    }
    
    public void setState(final boolean b) {
        this.a(55, b);
    }
    
    public boolean getHelp() {
        return this.i(54);
    }
    
    public void setHelp(final boolean b) {
        this.a(54, b);
    }
    
    public boolean getBrand() {
        return this.i(53);
    }
    
    public void setBrand(final boolean b) {
        this.a(53, b);
    }
    
    public boolean getGrad() {
        return this.i(52);
    }
    
    public void setGrad(final boolean b) {
        this.a(52, b);
    }
    
    public boolean getAnim() {
        return this.i(51);
    }
    
    public void setAnim(final boolean b) {
        this.a(51, b);
    }
    
    public boolean getColors() {
        return this.i(50);
    }
    
    public void setColors(final boolean b) {
        this.a(50, b);
    }
    
    public boolean getFont() {
        return this.i(49);
    }
    
    public void setFont(final boolean b) {
        this.a(49, b);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("outerBackground = ").concat(String.valueOf(this.c))).concat(String.valueOf("\n"))).concat(String.valueOf("innerBackground = "))).concat(String.valueOf(this.d))).concat(String.valueOf("\n"))).concat(String.valueOf("helpText = "))).concat(String.valueOf(this.e))).concat(String.valueOf("\n"))).concat(String.valueOf("helpBackground = "))).concat(String.valueOf(this.f))).concat(String.valueOf("\n"))).concat(String.valueOf("tabsText = "))).concat(String.valueOf(this.i))).concat(String.valueOf("\n"))).concat(String.valueOf("tabsBackground = "))).concat(String.valueOf(this.j))).concat(String.valueOf("\n"))).concat(String.valueOf("normalMessages = "))).concat(String.valueOf(this.k))).concat(String.valueOf("\n"))).concat(String.valueOf("flaggedMessages = "))).concat(String.valueOf(this.l))).concat(String.valueOf("\n"))).concat(String.valueOf("normalBackground = "))).concat(String.valueOf(this.m))).concat(String.valueOf("\n"))).concat(String.valueOf("privateMessages = "))).concat(String.valueOf(this.n))).concat(String.valueOf("\n"))).concat(String.valueOf("privateBackground = "))).concat(String.valueOf(this.o))).concat(String.valueOf("\n"))).concat(String.valueOf("fontName = "))).concat(String.valueOf(this.p))).concat(String.valueOf("\n"))).concat(String.valueOf("fontSize = "))).concat(String.valueOf(this.q))).concat(String.valueOf("\n"))).concat(String.valueOf("fontStyle = "))).concat(String.valueOf(this.r))).concat(String.valueOf("\n"))).concat(String.valueOf("directory = "))).concat(String.valueOf(this.w))).concat(String.valueOf("\n"))).concat(String.valueOf("roundedCorners = "))).concat(String.valueOf(this.v))).concat(String.valueOf("\n"))).concat(String.valueOf("background = "))).concat(String.valueOf(this.i()))).concat(String.valueOf("\n"))).concat(String.valueOf("chatBackground = "))).concat(String.valueOf(this.j()))).concat(String.valueOf("\n"))).concat(String.valueOf("scaleChatBackground = "))).concat(String.valueOf(this.k()))).concat(String.valueOf("\n"))).concat(String.valueOf("imageButtons = "))).concat(String.valueOf(this.l()))).concat(String.valueOf("\n"))).concat(String.valueOf("imageTabs = "))).concat(String.valueOf(this.m()))).concat(String.valueOf("\n"));
    }
    
    public a2(final int n, final String s) {
        super(n, s);
        this.imgStates = new Image[] { null, null, null, null };
        this.setAnim(true);
        this.c = cv.c;
        this.clrOutText = Color.black;
        this.d = cv.c;
        this.clrInnText = Color.black;
        this.e = Color.black;
        this.f = cv.c;
        this.g = Color.black;
        this.h = Color.white;
        this.i = Color.black;
        this.j = cv.c;
        this.k = Color.black;
        this.l = Color.red;
        this.m = cv.c;
        this.n = Color.blue;
        this.o = cv.a;
        this.clrScrlText = Color.black;
        this.clrScrlBack = cv.c;
        this.clrSrchText = Color.black;
        this.clrSrchBack = Color.white;
        this.clrCtrlText = Color.black;
        this.clrCtrlBack = cv.b;
        this.clrIMText = Color.red;
        this.clrIMBack = cv.clrLightMag;
        this.clrBaddyText = Color.darkGray;
        this.clrBaddyBack = cv.clrLightGrn;
        this.clrInvText = Color.white;
        this.clrInvBack = Color.blue;
        this.clrMenuText = Color.black;
        this.clrMenuBack = cv.c;
        this.clrHghlBack = Color.black;
        this.clrHghlText = Color.white;
        this.clrHghlBack = Color.black;
        this.p = "SansSerif";
        this.q = 12;
        this.r = 0;
        this.strMenuFont = "SansSerif";
        this.nMenuStyle = 0;
        this.nMenuSize = 12;
        this.s = null;
        this.t = null;
        this.u = null;
        this.fntMenuFont = null;
        this.v = 0;
        this.w = "Default";
    }
    
    static {
        a2.a = new a2(0, "");
        (a2.b = new v(67341, 1)).f(0, 51);
        a2.b.f(0, 62);
        a2.b.a(0, 0, 1);
        a2.b.a(0, 1, cv.c.getRGB());
        a2.b.a(0, 2, cv.c.getRGB());
        a2.b.a(0, 3, Color.black.getRGB());
        a2.b.a(0, 4, cv.c.getRGB());
        a2.b.a(0, 5, Color.black.getRGB());
        a2.b.a(0, 6, cv.c.getRGB());
        a2.b.a(0, 7, Color.black.getRGB());
        a2.b.a(0, 8, Color.red.getRGB());
        a2.b.a(0, 9, cv.c.getRGB());
        a2.b.a(0, 10, Color.blue.getRGB());
        a2.b.a(0, 11, cv.a.getRGB());
        a2.b.a(0, 12, 0);
        a2.b.a(0, 13, 12);
        a2.b.a(0, 15, Color.black.getRGB());
        a2.b.a(0, 16, Color.white.getRGB());
        a2.b.a(0, 17, Color.black.getRGB());
        a2.b.a(0, 18, Color.black.getRGB());
        a2.b.a(0, 19, Color.red.getRGB());
        a2.b.a(0, 20, cv.clrLightMag.getRGB());
        a2.b.a(0, 21, Color.black.getRGB());
        a2.b.a(0, 22, Color.white.getRGB());
        a2.b.a(0, 23, Color.black.getRGB());
        a2.b.a(0, 24, cv.b.getRGB());
        a2.b.a(0, 25, Color.black.getRGB());
        a2.b.a(0, 26, cv.c.getRGB());
        a2.b.a(0, 0, c.a("Default"));
        a2.b.a(0, 1, "SansSerif");
        a2.b.a(0, 2, "Default");
        a2.b.a(0, 14, 0);
    }
}
