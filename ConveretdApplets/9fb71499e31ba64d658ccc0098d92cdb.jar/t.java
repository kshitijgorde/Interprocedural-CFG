import java.awt.Insets;
import java.awt.image.RGBImageFilter;
import java.net.URL;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Color;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends l implements b
{
    public static final String[] a;
    public WordFallApplet b;
    public e c;
    public j[] d;
    public u e;
    public u f;
    public int g;
    public int h;
    public int i;
    public int j;
    public String k;
    public boolean l;
    public int m;
    public int n;
    public int o;
    public int p;
    public ag q;
    public ah r;
    
    public void a(final k k) {
        super.a(k);
        if (this.q != null) {
            k.f(this.q);
            this.q = null;
        }
        if (this.r != null) {
            k.f(this.r);
            this.r = null;
        }
    }
    
    public f c(final int n, final int n2, final int n3) {
        final f f = new f(0, 0, 0);
        final double n4 = (n3 < 128) ? (n3 * (255 + n2) / 255) : (n3 + n2 - n3 * n2 / 255);
        final int c = (int)(2 * n3 - n4);
        final int n5 = 6 * n / 256;
        int n6 = (int)(c + (n4 - c) * ((n - n5 * 256 / 6) * 6) / 255.0);
        if (n6 > 255) {
            n6 = 255;
        }
        int c2 = (int)(n4 - (n4 - c) * ((n - n5 * 256 / 6) * 6) / 255.0);
        if (c2 < 0) {
            c2 = 0;
        }
        switch (n5) {
            case 0: {
                f.a = (int)n4;
                f.b = n6;
                f.c = c;
                break;
            }
            case 1: {
                f.a = c2;
                f.b = (int)n4;
                f.c = c;
                break;
            }
            case 2: {
                f.a = c;
                f.b = (int)n4;
                f.c = n6;
                break;
            }
            case 3: {
                f.a = c;
                f.b = c2;
                f.c = (int)n4;
                break;
            }
            case 4: {
                f.a = n6;
                f.b = c;
                f.c = (int)n4;
                break;
            }
            case 5: {
                f.a = (int)n4;
                f.b = c;
                f.c = c2;
                break;
            }
            default: {
                f.a = (int)n4;
                f.b = n6;
                f.c = c;
                break;
            }
        }
        f.d = 255;
        return f;
    }
    
    public void b() {
        ++this.j;
        if (this.j % 10 == 0) {
            ++this.m;
            this.j();
        }
        switch (this.g) {
            case 0: {
                boolean b = true;
                for (int i = 0; i < t.a.length; ++i) {
                    if (this.d[i].r) {
                        this.b.e("Failed to load '" + t.a[i] + "'");
                        this.b.b("loading");
                    }
                    if (!this.d[i].j()) {
                        b = false;
                    }
                }
                if (b) {
                    this.b.p();
                    this.q = new ag(super.m, 0, this);
                    this.q.p = true;
                    this.b.h.c(this.q);
                    this.a(super.a, super.b, super.c, super.d);
                    this.g = 1;
                    return;
                }
                break;
            }
            case 1: {
                this.h = super.d / 2 - 100;
                this.g = 2;
            }
            case 2: {
                this.j();
                this.h -= 4;
                if (this.h < 0) {
                    this.h = 0;
                    this.i = 60;
                    this.g = 3;
                    return;
                }
                break;
            }
            case 3: {
                if (this.j % 5 == 0) {
                    this.j();
                }
                if (this.i > 0) {
                    --this.i;
                    return;
                }
                if (!this.c.g) {
                    break;
                }
                if (this.r == null) {
                    this.g = 4;
                    return;
                }
                if (!this.r.f) {
                    this.k = "Loading Complete!";
                    this.r.b((super.c - this.r.c) / 2, super.d / 2 + 132);
                    this.r.b(true);
                    return;
                }
                break;
            }
            default: {
                if (this.j % 5 == 0) {
                    this.j();
                    return;
                }
                break;
            }
        }
    }
    
    public boolean k() {
        return this.g == 4;
    }
    
    public void a(final n n) {
        if (this.g == 0) {
            n.a(Color.black);
            n.b(0, 0, super.c, super.d);
            return;
        }
        if (this.g >= 0) {
            n.a(Color.black);
            n.b(0, this.d[0].c(), super.c, super.d - this.d[0].c());
            n.a(this.d[0], 0, 0);
            n.a(this.d[1], this.d[0].h(), 0);
            for (int i = 0; i < this.b.a2.length; ++i) {
                if (this.b.a2[i] != null) {
                    n.f = this.f;
                    if (this.l) {
                        n.e = this.c(Math.abs((this.m + i) * 10 % 170 - 85), 255, 200);
                    }
                    else {
                        n.a(new Color(154, 161, 57));
                    }
                    n.a("" + (i + 1) + ".", this.o, 114 + i * this.f.d);
                    if (this.l) {
                        n.e = this.c(Math.abs((this.m + i) * 10 % 170 - 85), 255, 150);
                    }
                    else {
                        n.a(new Color(145, 201, 144));
                    }
                    n.a(this.b.a2[i], this.n, 114 + i * this.f.d);
                }
            }
            for (int j = 0; j < this.b.a3.length; ++j) {
                if (this.b.a5[j] != null && this.b.a4[j] != null) {
                    final String s = this.b.a4[j];
                    final String s2 = this.b.a5[j];
                    n.f = this.f;
                    if (this.l) {
                        n.e = this.c(Math.abs((this.m + j) * 10 % 170 - 85), 255, 200);
                    }
                    else {
                        n.a(new Color(154, 161, 57));
                    }
                    n.a('\"' + s2 + '\"', this.p, 114 + j * 3 * this.f.d);
                    if (this.l) {
                        n.e = this.c(Math.abs((this.m + j * 3) * 10 % 170 - 85), 255, 150);
                    }
                    else {
                        n.a(new Color(145, 201, 144));
                    }
                    n.a("  by " + s, this.p, 114 + j * 3 * this.f.d + this.f.d);
                }
            }
        }
        final int n2 = super.c / 2;
        final int n3 = super.d / 2;
        if (this.g >= 2) {
            final int n4 = n2 - this.d[3].h() / 2;
            final int n5 = n3 - 32 + 130 + this.h;
            n.a(this.d[3], n4, n5);
            final n n6 = new n(n);
            n6.a(n4, n5, (int)(this.c.l() * this.d[3].h() / 100.0), this.d[3].c());
            n6.a(this.d[4], n4, n5);
            if (this.r.f) {
                n.f = this.e;
                n.a(new Color(24, 143, 153));
            }
            else {
                n.f = this.e;
                n.a(new Color(41, 239, 255));
            }
            n.a(this.k, n2 - n.f.a(this.k) / 2, n3 - 32 + 125 + this.h);
        }
    }
    
    public t(final WordFallApplet b, final String s) {
        super(b.h);
        this.g = 0;
        this.b = b;
        this.e = this.b.b("SansSerif", 1, 14);
        this.f = this.b.b("SansSerif", 0, 10);
        this.c = this.b.d;
        this.k = "Now Loading " + s + "... Please wait...";
        this.l = true;
    }
    
    public void a(final int n) {
    }
    
    public void b(final int n) {
    }
    
    public void l() {
        this.d = new j[t.a.length];
        for (int i = 0; i < t.a.length; ++i) {
            this.d[i] = this.c.a("images/" + t.a[i]);
        }
        this.g = 0;
    }
    
    public boolean m() {
        return this.g != 0;
    }
    
    static {
        a = new String[] { "zz_upsell_bg1.gif", "zz_upsell_bg2.gif", "zz_upsell_button_hilite.gif", "zz_loaderbar2.gif", "zz_loaderbar1.gif" };
    }
    
    public void d(final int n) {
        switch (n) {
            case 0: {
                try {
                    String parameter = this.b.getParameter("adUrl");
                    if (parameter == null) {
                        parameter = "http://www.popcap.com/bookworm.php";
                    }
                    final String s = "javascript:";
                    if (parameter.startsWith(s)) {
                        String s2 = parameter.substring(s.length());
                        final int index = s2.indexOf(40);
                        if (index != -1) {
                            s2 = s2.substring(0, index);
                        }
                        JSObject.getWindow((Applet)this.b).call(s2, (Object[])null);
                        return;
                    }
                    this.b.getAppletContext().showDocument(new URL(parameter), "_blank");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case 1: {
                this.g = 4;
            }
            default: {}
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        if (this.q != null && this.d[2].j()) {
            final j e = this.d[2];
            final int h = e.h();
            final int c = e.c();
            this.q.d = this.c.a(this.d[1], 0, 199, h, c, null);
            this.q.e = e;
            this.q.a(142, 197, h, c);
            this.q.o = new Insets(-9, -20, -6, -10);
        }
        final int o = 225;
        this.o = o;
        for (int i = 0; i < this.b.a2.length; ++i) {
            if (this.b.a2[i] != null) {
                final int o2 = o - this.f.a("" + (i + 1) + ". " + this.b.a2[i]) / 2;
                if (o2 < this.o) {
                    this.o = o2;
                    this.n = this.o + this.f.a("" + (i + 1) + ". ");
                }
            }
        }
        final int p4 = 339;
        this.p = p4;
        for (int j = 0; j < this.b.a3.length; ++j) {
            if (this.b.a5[j] != null && this.b.a4[j] != null) {
                final int p5 = p4 - this.f.a('\"' + this.b.a5[j] + '\"') / 2;
                if (p5 < this.p) {
                    this.p = p5;
                }
                final int p6 = p4 - this.f.a("  by " + this.b.a4[j]) / 2;
                if (p6 < this.p) {
                    this.p = p6;
                }
            }
        }
    }
    
    public void b(final k k) {
        super.b(k);
        if (this.r == null) {
            this.r = new ah(this.b.h, 1, this);
            this.r.p = true;
            this.r.c = 1;
            this.r.b = new Color(255, 255, 255);
            this.r.a = new Color(37, 221, 235);
            this.r.b = "Click Here to Continue with Bookworm Basic";
            this.r.c = this.e;
            this.r.a(0, 0, this.e.a(this.r.b), this.e.d + this.r.c * 2);
            this.r.b(false);
        }
        k.c(this.r);
    }
    
    public void f(final int n) {
    }
    
    public void g(final int n) {
    }
}
