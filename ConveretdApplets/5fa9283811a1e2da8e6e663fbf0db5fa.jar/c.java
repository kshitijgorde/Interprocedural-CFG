import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    public double p;
    public double d;
    public double a;
    public double n;
    public double v;
    public double i;
    public f p;
    public z p;
    public z d;
    public a p;
    public double l;
    public double b;
    public double c;
    public int p;
    public int d;
    public l p;
    public l d;
    public l a;
    public l n;
    public String p;
    public double e;
    public double f;
    public m p;
    public m d;
    public g p;
    public g d;
    public g a;
    public f d;
    public z a;
    public da p;
    public String d;
    public boolean p;
    public boolean d;
    public boolean a;
    public boolean n;
    public boolean v;
    public boolean i;
    public boolean l;
    public boolean b;
    public boolean c;
    public int a;
    public y p;
    public y d;
    public y a;
    public y n;
    public v p;
    public z n;
    public z v;
    public t p;
    public h p;
    public Graphics p;
    public o p;
    public String a;
    public String n;
    public int n;
    public Image[] p;
    public dn[] p;
    public String[] p;
    public int v;
    public int i;
    public int l;
    public boolean e;
    public boolean f;
    public int b;
    public int c;
    public int e;
    public int[] p;
    public v d;
    public double g;
    public int f;
    public int g;
    public String v;
    public String i;
    public double h;
    public double j;
    public boolean g;
    public boolean h;
    public boolean j;
    public boolean k;
    public boolean m;
    public String l;
    public Color[] p;
    public String b;
    public String c;
    public String e;
    public String f;
    public boolean o;
    public String g;
    public String h;
    public boolean q;
    public boolean r;
    public boolean s;
    public int[] d;
    public int h;
    public int[] a;
    
    public final char p(final char c) {
        switch (c) {
            case '-': {
                return '.';
            }
            case '+': {
                return '.';
            }
            case '=': {
                return '/';
            }
            case '?': {
                return '/';
            }
            case ';': {
                return 'w';
            }
            case '&': {
                return 'w';
            }
            case ']': {
                return '4';
            }
            case '(': {
                return 'f';
            }
            case ')': {
                return 's';
            }
            case '{': {
                return 't';
            }
            case '}': {
                return 't';
            }
            case '$': {
                return ':';
            }
            case '[': {
                return 'c';
            }
            case '#': {
                return 'h';
            }
            case '%': {
                return 'p';
            }
            case '_': {
                return 'g';
            }
            case '|': {
                return 'm';
            }
            case '~': {
                return 'a';
            }
            case '*': {
                return 'b';
            }
            default: {
                return c;
            }
        }
    }
    
    public final String p(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        boolean b = false;
        for (int i = 0; i < length; ++i) {
            if (b) {
                sb.append(this.p(s.charAt(i)));
            }
            b = !b;
        }
        sb.reverse();
        return sb.toString();
    }
    
    public final void p(final String s) {
        this.p.p(20.0, this.p - 50);
        this.p.setColor(Color.black);
        this.p.a(this.p(s));
    }
    
    public final boolean p() {
        if (this.d.equals(this.v)) {
            this.o = false;
            return true;
        }
        this.p(this.g);
        return false;
    }
    
    public final void d() {
        this.p(this.f);
    }
    
    public final void a() {
        for (int i = 0; i < 2; ++i) {
            this.d[i] = 0;
        }
    }
    
    public final void p(final int n, final int n2, final Color color) {
        final int[] d = this.d;
        ++d[n];
        for (int i = 0; i < 2; ++i) {
            if (i != n && this.d[i] != 0) {
                if (this.d[i] == this.a[this.h]) {
                    ++this.h;
                }
                else {
                    this.q = false;
                }
                if (i == this.a[this.h]) {
                    ++this.h;
                }
                else {
                    this.q = false;
                }
                this.d[i] = 0;
                break;
            }
        }
        if (this.d[n] > 90) {
            this.q = false;
        }
    }
    
    public final boolean p(final int n, final int n2, final int n3) {
        return Math.abs(n - n2) <= n3;
    }
    
    public final boolean p(final Color color, final Color color2, final int n) {
        return this.p(color.getRed(), color2.getRed(), n) && this.p(color.getGreen(), color2.getGreen(), n) && this.p(color.getBlue(), color2.getBlue(), n);
    }
    
    public final boolean p(final int n, final Color color) {
        return this.p(new Color(n), color, 80);
    }
    
    public final int p(final int n, final int n2) {
        return Math.abs(n - n2);
    }
    
    public final int p(final Color color, final Color color2) {
        int p2 = this.p(color.getRed(), color2.getRed());
        final int p3 = this.p(color.getGreen(), color2.getGreen());
        if (p2 < p3) {
            p2 = p3;
        }
        final int p4 = this.p(color.getBlue(), color2.getBlue());
        if (p2 < p4) {
            p2 = p4;
        }
        return p2;
    }
    
    public final int p(final int n, final Color color) {
        return this.p(new Color(n), color);
    }
    
    public final boolean p(final Image image) {
        final Graphics graphics = image.getGraphics();
        if (!this.r) {
            return true;
        }
        if (!this.q) {
            return false;
        }
        final int n = (int)this.n.p + 22;
        final int n2 = (int)this.n.d + 12;
        this.h = 0;
        this.a();
        final int n3 = 20;
        final int n4 = 20;
        graphics.setColor(Color.black);
        graphics.fillRect(n3, n4, 1, 1);
        graphics.fillRect(n3 + 1, n4 + 1, 1, 1);
        graphics.setColor(Color.white);
        graphics.fillRect(n3 + 1, n4, 1, 1);
        graphics.fillRect(n3, n4 + 1, 1, 1);
        this.p(image, n3, n4, 2, 2);
        graphics.setColor(Color.white);
        graphics.fillRect(n3, n4, 1, 1);
        graphics.fillRect(n3 + 1, n4 + 1, 1, 1);
        if (this.p(this.p[0], Color.black) && this.p(this.p[1], Color.white) && this.p(this.p[2], Color.white) && this.p(this.p[3], Color.black)) {
            this.p(image, n, n2, 105, 10);
        Label_0501:
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < 105; ++j) {
                    final int n5 = this.p[i * 105 + j];
                    if (this.p(n5, Color.black)) {
                        this.p(0, n5, Color.black);
                    }
                    else if (this.p(n5, Color.white)) {
                        this.p(1, n5, Color.white);
                    }
                    else {
                        this.q = false;
                    }
                    if (!this.q) {
                        System.out.println(this.p("/%yi?x3n") + i * 105 + j);
                        System.out.println(this.p("ad%i8(2(A*") + this.p(n5, Color.black));
                        System.out.println(this.p("&doi+(\u00e7(\u00e9;") + this.p(n5, Color.white));
                        break Label_0501;
                    }
                }
            }
            if (this.q) {
                this.r = false;
            }
            return this.q;
        }
        this.s = true;
        this.r = false;
        return true;
    }
    
    public final void p(final int p2, final int d) {
        this.p = p2;
        this.d = d;
        this.p = new l(5.0, 5.0);
        this.d = new l(p2 - 185, d - 5);
        this.a = new l(this.d);
        this.n = new l();
        this.m();
        this.e = (this.p.p + this.d.p) / 2.0;
        this.f = (this.p.d + this.d.d) / 2.0;
        this.p = new m(this.p, this.d);
        this.d = new m(this.p, this.d);
        this.p = new g(this);
        this.d = new g(this);
        this.a = null;
        this.p.p(this.d);
        this.d.p(5);
        if (this.p != null) {
            this.a = this.p;
        }
        else {
            this.a = this.d;
        }
    }
    
    public final void d(final Graphics p) {
        this.p = p;
    }
    
    public final void p() {
        this.e = (this.p.p + this.d.p) / 2.0;
        this.f = (this.p.d + this.d.d) / 2.0;
    }
    
    public final void m() {
        this.n.p(this.d.p + 5.0, this.d.d - 258.0);
    }
    
    public final void d(final double n, final double n2) {
        this.d.p(n, n2);
        this.p();
        this.m();
        this.p.p(this.p, this.d);
        this.p.p(this.p);
        this.p.p(this.d.p + 5.0, this.d.p + 150.0);
    }
    
    public final void p(final double n, final double n2) {
        final double n3 = this.d.p + n;
        double n4;
        if (n3 < 50.0) {
            n4 = 50.0;
        }
        else if (n3 > this.p - 185) {
            n4 = this.p - 185;
        }
        else {
            n4 = n3;
        }
        final double n5 = this.d.d + n2;
        double n6;
        if (n5 < 358.0) {
            n6 = 358.0;
        }
        else if (n5 > this.d - 5) {
            n6 = this.d - 5;
        }
        else {
            n6 = n5;
        }
        this.d(n4, n6);
    }
    
    public final void j() {
        boolean b = false;
        boolean b2 = false;
        double p;
        if (this.p - this.d.p < 430.0) {
            b = true;
            p = this.p - 430;
        }
        else {
            p = this.d.p;
        }
        double d;
        if (this.d.d < 528.0) {
            b2 = true;
            d = 528.0;
        }
        else {
            d = this.d.d;
        }
        if (b || b2) {
            this.d(p, d);
        }
    }
    
    public final void q() {
        this.d(this.a.p, this.a.d);
        this.v = false;
    }
    
    public final int p(final String s) {
        for (int i = 0; i < this.n; ++i) {
            if (this.p[i].equals(s)) {
                return i;
            }
        }
        System.out.print("The image: " + s + " is missing!");
        return -1;
    }
    
    public final boolean p(final int n) {
        final dn dn = this.p[n];
        try {
            dn.n = this.p[n].getWidth(null);
            dn.v = this.p[n].getHeight(null);
            dn.a = dn.n * dn.v;
            if (dn.n < 1 || dn.v < 1) {
                return false;
            }
            dn.i();
            new PixelGrabber(this.p[n], 0, 0, dn.n, dn.v, dn.d, 0, dn.n).grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("Err:loadVectPix-Image InterruptedException");
            return false;
        }
        dn.d();
        return true;
    }
    
    public final void c() {
        for (int i = 0; i < this.n; ++i) {
            this.p(i);
        }
    }
    
    public final boolean p(final Image image, final int n, final int n2, final int n3, final int n4) {
        try {
            this.p = new int[n3 * n4];
            new PixelGrabber(image, n, n2, n3, n4, this.p, 0, n3).grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("Err:loadLocalVectPixImage InterruptedException");
            return false;
        }
        return true;
    }
    
    public final void b() {
        this.p = new a(this.d, new z(this.d.p + 10.0, this.d.d, this.d.a), new z(this.d.p, this.d.d + 10.0, this.d.a));
    }
    
    public final void l() {
        this.d.n(this.p);
        this.d.a -= 10.0;
    }
    
    public final void v() {
        this.p.p(new l(this.e, this.f), this.p, this);
        this.p.a += 10.0;
        this.l();
    }
    
    public final void f() {
        final l l = new l();
        l.p(this.d, this);
        final int n = (int)l.p;
        final int n2 = (int)l.d;
        this.p.setColor(Color.red);
        this.p.drawOval(n - 8, n2 - 8, 16, 16);
        this.p.drawLine(n, n2 - 8, n, n2 - 16);
        this.p.drawLine(n, n2 + 8, n, n2 + 16);
        this.p.drawLine(n - 8, n2, n - 16, n2);
        this.p.drawLine(n + 8, n2, n + 16, n2);
    }
    
    public final void i() {
        if (this.p.p != null && this.p.p.n) {
            this.p.p.p(this.v);
        }
        else {
            this.p.p(this.v);
        }
    }
    
    public final void o() {
        this.d.p = -7.877;
        this.d.d = 11.77;
        this.d.a = -4.32;
        this.d.p();
    }
    
    public final void g() {
        final double n = 1.0E12;
        this.a.p = -this.d.p * n;
        this.a.d = -this.d.d * n;
        this.a.a = -this.d.a * n;
    }
    
    public final void n() {
        if (this.n + 30.0 < this.i) {
            this.n += 30.0;
        }
        else {
            this.n = this.i;
        }
    }
    
    public final void k() {
        if (this.n - 30.0 > this.v) {
            this.n -= 30.0;
        }
        else {
            this.n = this.v;
        }
    }
    
    public final void e() {
        this.n = this.v;
        this.b = false;
        this.a = true;
        this.o();
    }
    
    public final void h() {
        this.b = true;
        this.j = true;
        this.p.p = this.a;
    }
    
    public final void n(final Graphics graphics) {
        if (this.f) {
            if (this.e) {
                final int n = (int)this.n.p + 185 - 40;
                final int n2 = (int)this.n.d;
                if (this.l >= 0) {
                    graphics.drawImage(this.p[this.l], n, n2, null);
                }
            }
            this.e = !this.e;
        }
    }
    
    public final void p(final Graphics graphics) {
    }
    
    public final void v(final Graphics graphics) {
        if (this.i < 0) {
            this.i = this.p("controlpaneleasy.gif");
            this.l = this.p(this.l);
        }
        graphics.drawImage(this.p[this.i], (int)this.n.p, (int)this.n.d, null);
        this.n(graphics);
    }
    
    public final void a(final Graphics graphics) {
        if (this.k) {
            this.v(graphics);
        }
        else {
            this.p(graphics);
        }
    }
    
    public c(final double p3, final double d, final double n) {
        this.p = new f(0.0, 0.0, 1.0);
        this.p = null;
        this.d = null;
        this.p = null;
        this.l = 0.1308996938995747;
        this.b = 10.0;
        this.c = 1.5;
        this.p = 0;
        this.d = 0;
        this.n = null;
        this.p = "";
        this.e = 0.0;
        this.f = 0.0;
        this.p = null;
        this.d = null;
        this.p = null;
        this.d = null;
        this.a = null;
        this.d = new f();
        this.a = new z();
        this.p = null;
        this.d = "7tR23okl0y";
        this.p = true;
        this.d = false;
        this.a = true;
        this.n = false;
        this.v = false;
        this.i = true;
        this.l = false;
        this.b = false;
        this.a = 0;
        this.p = null;
        this.a = "-+E;4;+&%=\u00e7?f$k%x}#{a#";
        this.n = "*%s}y{k#";
        this.n = 0;
        this.p = new Image[30];
        this.p = new dn[30];
        this.p = new String[30];
        this.v = -1;
        this.i = -1;
        this.l = -1;
        this.e = true;
        this.f = true;
        this.b = -1;
        this.c = -1;
        this.e = 0;
        this.g = 20.0;
        this.f = 0;
        this.g = 0;
        this.v = null;
        this.i = null;
        this.h = 1.0;
        this.j = 1.0;
        this.g = false;
        this.h = false;
        this.j = false;
        this.k = true;
        this.m = false;
        this.l = new String();
        this.p = new Color[26];
        this.b = "am'o,[i-tdk]z}2)+a?(" + this.a;
        this.c = "*h~[^-?*%ei&/~onvia_%~h|oi";
        this.e = "7[|+\u00e7d?]J{#)^a?(";
        this.f = this.c + "*@9g#uP*, s{pcq~l{fnwop[s leose~jeal0p8¬5,xn~wpoinbkanluo 1rdow ¬l|e&n\u00e7~~p\u00e0 \u00e8l\u00e9oir~}in~o^[( 7d4eu{hpsufrkrhoc[g e:egluo*";
        this.o = true;
        this.g = "%!we4uzlga+v? \u00e7d%r&o,&jsesn~kps |}|[#elrsrTob[) 6e4hs}j oeRvs~th7 YoG{Q jsdd@e@eino 9W?PxcAiflubiuopr s:qmj~fr5aspi 6l4mo}ih";
        this.h = "adz~iollenr&ioedi toc}w oki[d~ibo zorg~ 6,1nro7is)~rieovi e[tielabiuapo p~w 3{/ouN";
        this.s = false;
        this.d = new int[2];
        this.h = 0;
        this.a = new int[] { 40, 1, 3, 0, 20, 1, 2, 0, 7, 1, 2, 0, 70, 1, 2, 0, 17, 1, 1, 0, 4, 1, 2, 0, 7, 1, 2, 0, 70, 1, 2, 0, 16, 1, 2, 0, 3, 1, 3, 0, 7, 1, 2, 0, 31, 1, 2, 0, 3, 1, 1, 0, 3, 1, 4, 0, 3, 1, 1, 0, 3, 1, 2, 0, 1, 1, 2, 0, 3, 1, 1, 0, 3, 1, 2, 0, 4, 1, 4, 0, 2, 1, 4, 0, 3, 1, 4, 0, 1, 1, 4, 0, 2, 1, 3, 0, 4, 1, 2, 0, 1, 1, 2, 0, 7, 1, 4, 0, 3, 1, 4, 0, 3, 1, 2, 0, 1, 1, 3, 0, 1, 1, 2, 0, 1, 1, 2, 0, 2, 1, 3, 0, 2, 1, 4, 0, 2, 1, 3, 0, 2, 1, 2, 0, 1, 1, 2, 0, 2, 1, 3, 0, 2, 1, 2, 0, 5, 1, 2, 0, 2, 1, 1, 0, 3, 1, 2, 0, 1, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 2, 1, 1, 0, 1, 1, 2, 0, 3, 1, 2, 0, 1, 1, 3, 0, 6, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 2, 1, 2, 0, 2, 1, 3, 0, 1, 1, 3, 0, 1, 1, 2, 0, 1, 1, 2, 0, 1, 1, 3, 0, 1, 1, 2, 0, 2, 1, 2, 0, 1, 1, 3, 0, 1, 1, 2, 0, 3, 1, 2, 0, 1, 1, 3, 0, 1, 1, 2, 0, 6, 1, 2, 0, 4, 1, 4, 0, 1, 1, 3, 0, 4, 1, 2, 0, 2, 1, 1, 0, 1, 1, 2, 0, 3, 1, 2, 0, 2, 1, 2, 0, 6, 1, 2, 0, 5, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 0, 2, 1, 2, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 0, 3, 1, 2, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 0, 6, 1, 2, 0, 3, 1, 2, 0, 1, 1, 2, 0, 2, 1, 4, 0, 2, 1, 2, 0, 1, 1, 1, 0, 2, 1, 2, 0, 3, 1, 2, 0, 2, 1, 2, 0, 6, 1, 2, 0, 5, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 1, 1, 4, 0, 1, 1, 4, 0, 2, 1, 4, 0, 1, 1, 4, 0, 3, 1, 4, 0, 1, 1, 4, 0, 6, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 4, 1, 3, 0, 1, 1, 2, 0, 1, 1, 6, 0, 2, 1, 2, 0, 2, 1, 2, 0, 6, 1, 2, 0, 5, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 3, 0, 1, 1, 3, 0, 4, 1, 3, 0, 1, 1, 3, 0, 5, 1, 3, 0, 1, 1, 3, 0, 3, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 4, 1, 2, 0, 3, 1, 2, 0, 1, 1, 3, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 1, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 3, 1, 2, 0, 4, 1, 2, 0, 3, 1, 2, 0, 5, 1, 2, 0, 3, 1, 2, 0, 3, 1, 2, 0, 2, 1, 2, 0, 3, 1, 5, 0, 2, 1, 4, 0, 3, 1, 2, 0, 3, 1, 2, 0, 4, 1, 2, 0, 1, 1, 2, 0, 2, 1, 2, 0, 3, 1, 4, 0, 3, 1, 4, 0, 3, 1, 2, 0, 2, 1, 2, 0, 2, 1 };
        this.p = p3;
        this.d = d;
        this.a = 700.5;
        this.n = n;
        this.v = n;
        this.i = 300.0;
        this.r = true;
        this.q = true;
        this.p = new h(this);
        this.o();
        this.g();
        this.p = new z(0.0, 0.0, this.a);
        this.d = new z();
        this.l();
        this.b();
        this.v();
        this.p = new da();
        this.n = new z(0.0, 0.0, 0.0);
        this.v = new z(0.0, 0.0, 0.0);
        this.d = new v();
        for (int i = 0; i < 30; ++i) {
            this.p[i] = new dn(i);
        }
        this.c = true;
    }
}
