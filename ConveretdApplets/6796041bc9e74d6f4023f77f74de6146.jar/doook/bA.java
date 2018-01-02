// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class bA extends cF
{
    public static bA b;
    public static cD d;
    public Color a;
    public Color b;
    public Color i;
    public Color k;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    public Color l;
    public Color m;
    public Color n;
    public Color o;
    public Color p;
    public String i;
    public int ae;
    public int af;
    private Font b;
    private Font c;
    private Font d;
    public int aE;
    private String aa;
    public Image t;
    public Image u;
    public Image v;
    public Image w;
    public Color q;
    public Color r;
    
    public Font a() {
        if (this.c == null) {
            this.c = new Font(this.i, this.af | 0x1, this.ae);
        }
        return this.c;
    }
    
    public Font b() {
        if (this.b == null) {
            this.b = new Font(this.i, this.af, this.ae);
        }
        return this.b;
    }
    
    public Font c() {
        if (this.d == null || this.d.getSize() + 4 != this.b.getSize()) {
            this.d = new Font(this.i, this.af, this.ae - 4);
        }
        return this.d;
    }
    
    public void b() {
        this.b = null;
        this.c = null;
    }
    
    public void a(final String aa) {
        if (aa == null) {
            return;
        }
        this.aa = aa;
    }
    
    public String b() {
        return this.aa.toLowerCase();
    }
    
    public String e() {
        return "Themes/" + this.b() + "/";
    }
    
    public Image a() {
        return this.n() ? this.v : null;
    }
    
    public Image b() {
        return this.o() ? this.w : null;
    }
    
    public boolean n() {
        return this.d(60);
    }
    
    public void a(final boolean b) {
        this.a(60, b);
    }
    
    public boolean o() {
        return this.d(59);
    }
    
    public void b(final boolean b) {
        this.a(59, b);
    }
    
    public boolean h() {
        return this.d(58);
    }
    
    public void c(final boolean b) {
        this.a(58, b);
    }
    
    public boolean p() {
        return this.d(57);
    }
    
    public void d(final boolean b) {
        this.a(57, b);
    }
    
    public boolean q() {
        return this.d(56);
    }
    
    public void e(final boolean b) {
        this.a(56, b);
    }
    
    public String toString() {
        return "outerBackground = " + this.a + "\n" + "innerBackground = " + this.b + "\n" + "helpText = " + this.i + "\n" + "helpBackground = " + this.k + "\n" + "tabsText = " + this.f + "\n" + "tabsBackground = " + this.g + "\n" + "normalMessages = " + this.l + "\n" + "superMessages = " + this.q + "\n" + "flaggedMessages = " + this.m + "\n" + "normalBackground = " + this.n + "\n" + "superBackground = " + this.r + "\n" + "privateMessages = " + this.o + "\n" + "privateBackground = " + this.p + "\n" + "fontName = " + this.i + "\n" + "fontSize = " + this.ae + "\n" + "fontStyle = " + this.af + "\n" + "directory = " + this.aa + "\n" + "roundedCorners = " + this.aE + "\n" + "background = " + this.n() + "\n" + "chatBackground = " + this.o() + "\n" + "scaleChatBackground = " + this.h() + "\n" + "imageButtons = " + this.p() + "\n" + "imageTabs = " + this.q() + "\n";
    }
    
    public bA(final int n, final String s) {
        super(n, s);
        this.a = bR.a;
        this.b = bR.a;
        this.i = Color.black;
        this.k = bR.a;
        this.d = Color.black;
        this.e = Color.white;
        this.f = Color.black;
        this.g = bR.a;
        this.l = Color.black;
        this.m = Color.red;
        this.n = bR.a;
        this.o = Color.blue;
        this.p = bR.c;
        this.i = "SansSerif";
        this.ae = 12;
        this.af = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.aE = 0;
        this.aa = "Default";
        this.q = Color.black;
        this.r = bR.a;
    }
    
    static {
        bA.b = new bA(0, "");
        (bA.d = new cD(67341, 1)).b(0, 62);
        bA.d.a(0, 0, 1);
        bA.d.a(0, 1, bR.a.getRGB());
        bA.d.a(0, 2, bR.a.getRGB());
        bA.d.a(0, 3, Color.black.getRGB());
        bA.d.a(0, 4, bR.a.getRGB());
        bA.d.a(0, 5, Color.black.getRGB());
        bA.d.a(0, 6, bR.a.getRGB());
        bA.d.a(0, 7, Color.black.getRGB());
        bA.d.a(0, 8, Color.red.getRGB());
        bA.d.a(0, 9, bR.a.getRGB());
        bA.d.a(0, 10, Color.blue.getRGB());
        bA.d.a(0, 11, bR.c.getRGB());
        bA.d.a(0, 12, 0);
        bA.d.a(0, 13, 12);
        bA.d.a(0, 15, Color.black.getRGB());
        bA.d.a(0, 16, Color.white.getRGB());
        bA.d.a(0, 17, Color.black.getRGB());
        bA.d.a(0, 18, bR.a.getRGB());
        bA.d.a(0, 0, ao.e("Default"));
        bA.d.a(0, 1, "SansSerif");
        bA.d.a(0, 2, "Default");
        bA.d.a(0, 14, 0);
    }
}
