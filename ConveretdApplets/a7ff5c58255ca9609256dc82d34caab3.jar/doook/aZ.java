// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class aZ extends D
{
    public static aZ c;
    public static aJ a;
    public Color a;
    public Color b;
    public Color l;
    public Color h;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    public Color m;
    public Color n;
    public Color o;
    public Color p;
    public Color q;
    public String h;
    public int ao;
    public int ap;
    private Font f;
    private Font g;
    private Font h;
    public int aq;
    private String P;
    public Image o;
    public Image p;
    public Image q;
    public Image r;
    public Color r;
    public Color s;
    
    public Font a() {
        if (this.g == null) {
            this.g = new Font(this.h, this.ap | 0x1, this.ao);
        }
        return this.g;
    }
    
    public Font b() {
        if (this.f == null) {
            this.f = new Font(this.h, this.ap, this.ao);
        }
        return this.f;
    }
    
    public Font c() {
        if (this.h == null || this.h.getSize() + 4 != this.f.getSize()) {
            this.h = new Font(this.h, this.ap, this.ao - 4);
        }
        return this.h;
    }
    
    public void k() {
        this.f = null;
        this.g = null;
    }
    
    public void a(final String p) {
        if (p == null) {
            return;
        }
        this.P = p;
    }
    
    public String b() {
        return this.P.toLowerCase();
    }
    
    public String e() {
        return "Themes/" + this.b() + "/";
    }
    
    public Image a() {
        return this.k() ? this.q : null;
    }
    
    public Image b() {
        return this.l() ? this.r : null;
    }
    
    public boolean k() {
        return this.a(60);
    }
    
    public boolean l() {
        return this.a(59);
    }
    
    public boolean m() {
        return this.a(58);
    }
    
    public boolean f() {
        return this.a(57);
    }
    
    public boolean g() {
        return this.a(56);
    }
    
    public void a(final boolean b) {
        this.a(56, b);
    }
    
    public String toString() {
        return "outerBackground = " + this.a + "\n" + "innerBackground = " + this.b + "\n" + "helpText = " + this.l + "\n" + "helpBackground = " + this.h + "\n" + "tabsText = " + this.f + "\n" + "tabsBackground = " + this.g + "\n" + "normalMessages = " + this.m + "\n" + "superMessages = " + this.r + "\n" + "flaggedMessages = " + this.n + "\n" + "normalBackground = " + this.o + "\n" + "superBackground = " + this.s + "\n" + "privateMessages = " + this.p + "\n" + "privateBackground = " + this.q + "\n" + "fontName = " + this.h + "\n" + "fontSize = " + this.ao + "\n" + "fontStyle = " + this.ap + "\n" + "directory = " + this.P + "\n" + "roundedCorners = " + this.aq + "\n" + "background = " + this.k() + "\n" + "chatBackground = " + this.l() + "\n" + "scaleChatBackground = " + this.m() + "\n" + "imageButtons = " + this.f() + "\n" + "imageTabs = " + this.g() + "\n";
    }
    
    public aZ(final int n, final String s) {
        super(n, s);
        this.a = ah.a;
        this.b = ah.a;
        this.l = Color.black;
        this.h = ah.a;
        this.d = Color.black;
        this.e = Color.white;
        this.f = Color.black;
        this.g = ah.a;
        this.m = Color.black;
        this.n = Color.red;
        this.o = ah.a;
        this.p = Color.blue;
        this.q = ah.c;
        this.h = "SansSerif";
        this.ao = 12;
        this.ap = 0;
        this.f = null;
        this.g = null;
        this.h = null;
        this.aq = 0;
        this.P = "Default";
        this.r = Color.black;
        this.s = ah.a;
    }
    
    static {
        aZ.c = new aZ(0, "");
        (aZ.a = new aJ(67341, 1)).b(0, 62);
        aZ.a.a(0, 0, 1);
        aZ.a.a(0, 1, ah.a.getRGB());
        aZ.a.a(0, 2, ah.a.getRGB());
        aZ.a.a(0, 3, Color.black.getRGB());
        aZ.a.a(0, 4, ah.a.getRGB());
        aZ.a.a(0, 5, Color.black.getRGB());
        aZ.a.a(0, 6, ah.a.getRGB());
        aZ.a.a(0, 7, Color.black.getRGB());
        aZ.a.a(0, 8, Color.red.getRGB());
        aZ.a.a(0, 9, ah.a.getRGB());
        aZ.a.a(0, 10, Color.blue.getRGB());
        aZ.a.a(0, 11, ah.c.getRGB());
        aZ.a.a(0, 12, 0);
        aZ.a.a(0, 13, 12);
        aZ.a.a(0, 15, Color.black.getRGB());
        aZ.a.a(0, 16, Color.white.getRGB());
        aZ.a.a(0, 17, Color.black.getRGB());
        aZ.a.a(0, 18, ah.a.getRGB());
        aZ.a.a(0, 0, ar.b("Default"));
        aZ.a.a(0, 1, "SansSerif");
        aZ.a.a(0, 2, "Default");
        aZ.a.a(0, 14, 0);
    }
}
