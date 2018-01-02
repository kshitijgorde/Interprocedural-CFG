// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.esial.util.d;
import com.diginet.digichat.awt.b6;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import com.diginet.digichat.network.v;

public class a2 extends k
{
    public static a2 a;
    public static v b;
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
    public String p;
    public int q;
    public int r;
    private Font s;
    private Font t;
    private Font u;
    public int v;
    private String w;
    public Image x;
    public Image y;
    public Image z;
    public Image aa;
    
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
        return "Themes/" + this.e() + "/";
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
    
    public boolean j() {
        return this.i(59);
    }
    
    public boolean k() {
        return this.i(58);
    }
    
    public boolean l() {
        return this.i(57);
    }
    
    public boolean m() {
        return this.i(56);
    }
    
    public void a(final boolean b) {
        this.a(56, b);
    }
    
    public String toString() {
        return "outerBackground = " + this.c + "\n" + "innerBackground = " + this.d + "\n" + "helpText = " + this.e + "\n" + "helpBackground = " + this.f + "\n" + "tabsText = " + this.i + "\n" + "tabsBackground = " + this.j + "\n" + "normalMessages = " + this.k + "\n" + "flaggedMessages = " + this.l + "\n" + "normalBackground = " + this.m + "\n" + "privateMessages = " + this.n + "\n" + "privateBackground = " + this.o + "\n" + "fontName = " + this.p + "\n" + "fontSize = " + this.q + "\n" + "fontStyle = " + this.r + "\n" + "directory = " + this.w + "\n" + "roundedCorners = " + this.v + "\n" + "background = " + this.i() + "\n" + "chatBackground = " + this.j() + "\n" + "scaleChatBackground = " + this.k() + "\n" + "imageButtons = " + this.l() + "\n" + "imageTabs = " + this.m() + "\n";
    }
    
    public a2(final int n, final String s) {
        super(n, s);
        this.c = b6.c;
        this.d = b6.c;
        this.e = Color.black;
        this.f = b6.c;
        this.g = Color.black;
        this.h = Color.white;
        this.i = Color.black;
        this.j = b6.c;
        this.k = Color.black;
        this.l = Color.red;
        this.m = b6.c;
        this.n = Color.blue;
        this.o = b6.a;
        this.p = "SansSerif";
        this.q = 12;
        this.r = 0;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = 0;
        this.w = "Default";
    }
    
    static {
        a2.a = new a2(0, "");
        (a2.b = new v(67341, 1)).f(0, 62);
        a2.b.a(0, 0, 1);
        a2.b.a(0, 1, b6.c.getRGB());
        a2.b.a(0, 2, b6.c.getRGB());
        a2.b.a(0, 3, Color.black.getRGB());
        a2.b.a(0, 4, b6.c.getRGB());
        a2.b.a(0, 5, Color.black.getRGB());
        a2.b.a(0, 6, b6.c.getRGB());
        a2.b.a(0, 7, Color.black.getRGB());
        a2.b.a(0, 8, Color.red.getRGB());
        a2.b.a(0, 9, b6.c.getRGB());
        a2.b.a(0, 10, Color.blue.getRGB());
        a2.b.a(0, 11, b6.a.getRGB());
        a2.b.a(0, 12, 0);
        a2.b.a(0, 13, 12);
        a2.b.a(0, 15, Color.black.getRGB());
        a2.b.a(0, 16, Color.white.getRGB());
        a2.b.a(0, 0, d.a("Default"));
        a2.b.a(0, 1, "SansSerif");
        a2.b.a(0, 2, "Default");
        a2.b.a(0, 14, 0);
    }
}
