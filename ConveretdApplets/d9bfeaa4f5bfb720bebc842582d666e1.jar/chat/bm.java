// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public final class bm extends an
{
    public static bm a;
    private static m a;
    public Color a;
    public Color b;
    public Color c;
    public Color d;
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
    public Color p;
    public Color q;
    public Color r;
    public String a;
    public int a;
    public int b;
    private Font a;
    private Font b;
    public int c;
    private String b;
    public Image a;
    public Image b;
    public Image c;
    public Image d;
    
    public final Font a() {
        if (this.b == null) {
            this.b = new Font(this.a, this.b | 0x1, this.a);
        }
        return this.b;
    }
    
    public final Font b() {
        if (this.a == null) {
            this.a = new Font(this.a, this.b, this.a);
        }
        return this.a;
    }
    
    public final void a() {
        this.a = null;
        this.b = null;
    }
    
    public final void a(final String b) {
        if (b == null) {
            return;
        }
        this.b = b;
    }
    
    public final String b() {
        return "Themes/" + this.b.toLowerCase() + "/";
    }
    
    public final Image a() {
        if (this.a(60)) {
            return this.c;
        }
        return null;
    }
    
    public final Image b() {
        if (this.a(59)) {
            return this.d;
        }
        return null;
    }
    
    public final boolean a() {
        return this.a(60);
    }
    
    public final boolean b() {
        return this.a(59);
    }
    
    public final boolean c() {
        return this.a(58);
    }
    
    public final boolean d() {
        return this.a(57);
    }
    
    public final boolean e() {
        return this.a(56);
    }
    
    public final void b() {
        this.a(56, false);
    }
    
    public final String toString() {
        return "outerBackground = " + this.a + "\n" + "innerBackground = " + this.b + "\n" + "helpText = " + this.c + "\n" + "helpBackground = " + this.d + "\n" + "tabsText = " + this.g + "\n" + "tabsBackground = " + this.h + "\n" + "normalMessages = " + this.i + "\n" + "flaggedMessages = " + this.k + "\n" + "normalBackground = " + this.l + "\n" + "privateMessages = " + this.m + "\n" + "privateBackground = " + this.n + "\n" + "fontName = " + this.a + "\n" + "fontSize = " + this.a + "\n" + "fontStyle = " + this.b + "\n" + "directory = " + this.b + "\n" + "roundedCorners = " + this.c + "\n" + "background = " + this.a(60) + "\n" + "chatBackground = " + this.a(59) + "\n" + "scaleChatBackground = " + this.a(58) + "\n" + "imageButtons = " + this.a(57) + "\n" + "imageTabs = " + this.a(56) + "\n";
    }
    
    public bm(final int n, final String s) {
        super(n, s);
        this.a = chat.j.c;
        this.b = chat.j.c;
        this.c = Color.black;
        this.d = chat.j.c;
        this.e = Color.black;
        this.f = Color.white;
        this.g = Color.black;
        this.h = chat.j.c;
        this.i = Color.black;
        this.j = Color.black;
        this.k = Color.red;
        this.l = chat.j.c;
        this.m = Color.blue;
        this.n = chat.j.a;
        this.o = new Color(32960);
        this.p = Color.white;
        this.q = new Color(32960);
        this.r = new Color(15658734);
        this.a = "SansSerif";
        this.a = 12;
        this.b = 0;
        this.a = null;
        this.b = null;
        this.c = 0;
        this.b = "Default";
    }
    
    static {
        bm.a = new bm(0, "");
        (bm.a = new m(67341, 1)).a(0, 62, true);
        bm.a.a(0, 0, 1);
        bm.a.a(0, 1, j.c.getRGB());
        bm.a.a(0, 2, j.c.getRGB());
        bm.a.a(0, 3, Color.black.getRGB());
        bm.a.a(0, 4, j.c.getRGB());
        bm.a.a(0, 5, Color.black.getRGB());
        bm.a.a(0, 6, j.c.getRGB());
        bm.a.a(0, 7, Color.black.getRGB());
        bm.a.a(0, 8, Color.red.getRGB());
        bm.a.a(0, 9, j.c.getRGB());
        bm.a.a(0, 10, Color.blue.getRGB());
        bm.a.a(0, 11, j.a.getRGB());
        bm.a.a(0, 12, 0);
        bm.a.a(0, 13, 12);
        bm.a.a(0, 15, Color.black.getRGB());
        bm.a.a(0, 16, Color.white.getRGB());
        bm.a.a(0, 17, new Color(32960).getRGB());
        bm.a.a(0, 18, Color.white.getRGB());
        bm.a.a(0, 19, new Color(32960).getRGB());
        bm.a.a(0, 20, new Color(15658734).getRGB());
        bm.a.a(0, "Default");
        bm.a.a(1, "SansSerif");
        bm.a.a(2, "Default");
        bm.a.a(0, 14, 0);
    }
}
