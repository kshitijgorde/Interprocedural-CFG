import java.awt.Rectangle;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ai extends l implements b
{
    public WordFallApplet a;
    public w b;
    public y c;
    public ac d;
    public long e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public j j;
    public j k;
    public j l;
    public j m;
    public j n;
    public aa o;
    public double p;
    public int q;
    
    public void a(final k k) {
        super.a(k);
        if (this.o != null) {
            k.f(this.o);
            this.o = null;
        }
    }
    
    public void b() {
        super.b();
        if (this.i <= 0) {
            return;
        }
        if (super.i) {
            super.q = 0;
            return;
        }
        if (this.g) {
            if (--this.q <= 0) {
                super.i = true;
                super.f = false;
                if (!this.j.j() || !this.k.j() || !this.l.j() || !this.m.j() || !this.n.j()) {
                    String s = "" + "Longest Word:\n" + this.d.n.toUpperCase() + "\n\n" + "Best Word:\n" + this.d.l.toUpperCase() + " (" + this.d.m + " pts.)";
                    if (this.a.k.z == 5) {
                        s = s + "\n\nRank:\n" + w.bg[Math.min(this.b.aj - 1, w.bg.length - 1)];
                    }
                    if (this.a.k.z == 5) {
                        this.a.b();
                        this.a.a("Game Over!!", s, "Click here to continue", 2);
                    }
                    else {
                        this.a.f();
                        this.a.a("Level Up!", s, "Click here to continue", 2);
                    }
                }
                else if (this.a.k.z == 5) {
                    this.a.b();
                }
                else {
                    this.a.f();
                }
            }
            if (this.p > 0.0) {
                this.p -= 0.025;
                if (this.p < 0.0) {
                    this.p = 0.0;
                }
                this.j();
            }
        }
        else if (this.p < 1.0) {
            this.p += 0.025;
            if (this.p >= 1.0) {
                this.p = 1.0;
            }
            this.j();
        }
        if (this.o != null) {
            this.o.j = (int)Math.max(Math.min((this.p - 0.5) * 2.0 * 256.0, 255.0), 0.0);
        }
    }
    
    public void a(final n n) {
        if (!this.j.j() || !this.k.j() || !this.l.j() || !this.m.j() || !this.n.j()) {
            return;
        }
        final u b = this.a.b("SansSerif", 1, 14);
        final int n2 = (this.l.h() - this.j.h()) / 2;
        final int n3 = (int)(Math.min(this.p * 1.25, 1.0) * this.j.c());
        final int n4 = (int)Math.max(Math.min((this.p - 0.5) * 2.0 * 256.0, 255.0), 0.0);
        n.a(this.j, new Rectangle(n2, (this.j.c() - n3) / 2, this.j.h(), n3), new Rectangle(0, 0, this.j.h(), this.j.c()));
        int n5 = 0;
        if (this.b.z == 5) {
            n.d = true;
            n.e = new f(255, 255, 255, n4);
            n.a(this.l, 5, n5);
            n.d = false;
        }
        else {
            final int n6 = (super.c - this.k.h()) / 2 + 5;
            n5 = 28;
            n.d = true;
            n.e = new f(255, 255, 255, n4);
            n.a(this.k, n6, n5);
            n.d = false;
            if (this.b.z == 0) {
                n.e = new f(219, 69, 6, n4);
                n.f = b;
                this.a(n, 63, "Level " + this.b.aj + " complete!");
                n5 = 46;
            }
        }
        if (this.i == 0) {
            n.f = b;
            n.e = new f(255, 255, 255, n4);
            this.a(n, 0, "GET READY TO");
            this.a(n, b.d, "MAKE SOME WORDS!");
            return;
        }
        n.f = b;
        n.e = new f(97, 79, 55, n4);
        this.a(n, n5 + 42, "Longest Word:");
        this.a(n, n5 + 87, "Best Word:");
        if (this.a.k.z == 5) {
            this.a(n, n5 + 132, "Rank:");
        }
        n.e = new f(188, 99, 0, n4);
        this.a(n, n5 + 59, this.d.n.toUpperCase());
        this.a(n, n5 + 103, this.d.l.toUpperCase() + " (" + this.d.m + " pts.)");
        if (this.b.z == 5) {
            this.a(n, n5 + 148, w.bg[Math.min(this.b.aj - 1, w.bg.length - 1)]);
        }
    }
    
    public ai(final WordFallApplet a, final ac d) {
        super.s = true;
        this.a = a;
        this.b = this.a.k;
        this.c = new y((int)System.currentTimeMillis());
        this.d = d;
        this.j = this.a.f.o;
        this.k = this.a.f.r;
        this.l = this.a.f.t;
        this.m = this.a.f.u;
        this.n = this.a.f.v;
        if (!this.j.j() || !this.k.j() || !this.l.j() || !this.m.j() || !this.n.j()) {
            this.g = true;
        }
        this.f = false;
        if (this.o == null && this.n.j() && this.m.j()) {
            (this.o = new aa(this.a.h, 200, this)).c(super.i);
            this.o.b(super.f);
            if (this.a.k.z == 5) {
                this.o.d = this.n;
            }
            else {
                this.o.d = this.m;
            }
            final int h = this.o.d.h();
            this.o.a(super.a + (super.c - h) / 2 + 2, super.b + (int)(super.d * 0.68), h, this.o.d.c());
            this.o.p = true;
        }
    }
    
    public void c(final boolean b) {
        super.c(b);
        if (this.o != null) {
            this.o.c(b);
        }
    }
    
    public void a(final int n) {
        this.a.c(21);
    }
    
    public void b(final int n) {
    }
    
    public int k() {
        if (this.j == null || !this.j.j()) {
            return 0;
        }
        return this.j.c();
    }
    
    public int l() {
        if (this.j == null || !this.j.j()) {
            return 0;
        }
        return this.j.h();
    }
    
    public void d(final int n) {
        if (this.i == 2) {
            this.h = true;
        }
        if (n == 200 && !this.g) {
            this.g = true;
            this.o.c(true);
            if (this.a.k.z == 5) {
                this.q = 0;
            }
            else if (this.h && this.a.a0 && Math.random() < 0.75) {
                this.a.n();
                this.a.a0 = false;
                this.h = false;
                this.q = 0;
            }
            else {
                this.q = 30;
            }
            this.j();
        }
    }
    
    public void b(final boolean b) {
        super.b(b);
        if (this.o != null) {
            this.o.b(b);
        }
    }
    
    public void b(final int n, final int n2, final int n3) {
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        if (this.o != null && this.o.d != null) {
            final int h = this.o.d.h();
            this.o.a(super.a + (super.c - h) / 2 + 2, super.b + (int)(super.d * 0.68), h, this.o.d.c());
        }
    }
    
    public void b(final k k) {
        super.b(k);
        if (this.o != null) {
            super.m.c(this.o);
            super.m.b(this.o, this);
        }
    }
    
    public void f(final int n) {
    }
    
    public void c(final int i) {
        this.i = i;
        this.e = System.currentTimeMillis();
    }
    
    public void g(final int n) {
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
    }
}
