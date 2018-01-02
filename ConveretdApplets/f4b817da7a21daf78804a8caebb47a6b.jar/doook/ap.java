// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class ap extends bn
{
    public static ap b;
    public static V b;
    public Color c;
    public Color d;
    public Color e;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    public Color m;
    public Color n;
    public Color o;
    public Color f;
    public String k;
    public int p;
    public int q;
    private Font a;
    private Font b;
    private Font c;
    public int r;
    private String s;
    public Image l;
    public Image m;
    public Image n;
    public Image h;
    public Color p;
    public Color q;
    
    public Font a() {
        if (this.b == null) {
            this.b = new Font(this.k, this.q | 0x1, this.p);
        }
        return this.b;
    }
    
    public Font b() {
        if (this.a == null) {
            this.a = new Font(this.k, this.q, this.p);
        }
        return this.a;
    }
    
    public Font c() {
        if (this.c == null || this.c.getSize() + 4 != this.a.getSize()) {
            this.c = new Font(this.k, this.q, this.p - 4);
        }
        return this.c;
    }
    
    public void g() {
        this.a = null;
        this.b = null;
    }
    
    public void c(final String s) {
        if (s == null) {
            return;
        }
        this.s = s;
    }
    
    public String c() {
        return this.s.toLowerCase();
    }
    
    public String f() {
        return "Themes/" + this.c() + "/";
    }
    
    public Image a() {
        return this.e() ? this.n : null;
    }
    
    public Image b() {
        return this.f() ? this.h : null;
    }
    
    public boolean e() {
        return this.c(60);
    }
    
    public boolean f() {
        return this.c(59);
    }
    
    public boolean g() {
        return this.c(58);
    }
    
    public boolean h() {
        return this.c(57);
    }
    
    public boolean i() {
        return this.c(56);
    }
    
    public void a(final boolean b) {
        this.a(56, b);
    }
    
    public String toString() {
        return "outerBackground = " + this.c + "\n" + "innerBackground = " + this.d + "\n" + "helpText = " + this.e + "\n" + "helpBackground = " + this.g + "\n" + "tabsText = " + this.j + "\n" + "tabsBackground = " + this.k + "\n" + "normalMessages = " + this.l + "\n" + "superMessages = " + this.p + "\n" + "flaggedMessages = " + this.m + "\n" + "normalBackground = " + this.n + "\n" + "superBackground = " + this.q + "\n" + "privateMessages = " + this.o + "\n" + "privateBackground = " + this.f + "\n" + "fontName = " + this.k + "\n" + "fontSize = " + this.p + "\n" + "fontStyle = " + this.q + "\n" + "directory = " + this.s + "\n" + "roundedCorners = " + this.r + "\n" + "background = " + this.e() + "\n" + "chatBackground = " + this.f() + "\n" + "scaleChatBackground = " + this.g() + "\n" + "imageButtons = " + this.h() + "\n" + "imageTabs = " + this.i() + "\n";
    }
    
    public ap(final int n, final String s) {
        super(n, s);
        this.c = aH.c;
        this.d = aH.c;
        this.e = Color.black;
        this.g = aH.c;
        this.h = Color.black;
        this.i = Color.white;
        this.j = Color.black;
        this.k = aH.c;
        this.l = Color.black;
        this.m = Color.red;
        this.n = aH.c;
        this.o = Color.blue;
        this.f = aH.a;
        this.k = "SansSerif";
        this.p = 12;
        this.q = 0;
        this.a = null;
        this.b = null;
        this.c = null;
        this.r = 0;
        this.s = "Default";
        this.p = Color.black;
        this.q = aH.c;
    }
    
    static {
        ap.b = new ap(0, "");
        (ap.b = new V(67341, 1)).a(0, 62);
        ap.b.a(0, 0, 1);
        ap.b.a(0, 1, aH.c.getRGB());
        ap.b.a(0, 2, aH.c.getRGB());
        ap.b.a(0, 3, Color.black.getRGB());
        ap.b.a(0, 4, aH.c.getRGB());
        ap.b.a(0, 5, Color.black.getRGB());
        ap.b.a(0, 6, aH.c.getRGB());
        ap.b.a(0, 7, Color.black.getRGB());
        ap.b.a(0, 8, Color.red.getRGB());
        ap.b.a(0, 9, aH.c.getRGB());
        ap.b.a(0, 10, Color.blue.getRGB());
        ap.b.a(0, 11, aH.a.getRGB());
        ap.b.a(0, 12, 0);
        ap.b.a(0, 13, 12);
        ap.b.a(0, 15, Color.black.getRGB());
        ap.b.a(0, 16, Color.white.getRGB());
        ap.b.a(0, 17, Color.black.getRGB());
        ap.b.a(0, 18, aH.c.getRGB());
        ap.b.a(0, 0, aG.a("Default"));
        ap.b.a(0, 1, "SansSerif");
        ap.b.a(0, 2, "Default");
        ap.b.a(0, 14, 0);
    }
}
