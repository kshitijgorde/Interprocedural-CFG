import java.awt.Rectangle;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class as extends l implements b
{
    public WordFallApplet a;
    public w b;
    public String c;
    public aa d;
    public aa e;
    public j f;
    public j g;
    public u h;
    
    public void a(final k k) {
        super.a(k);
        if (this.e != null) {
            k.f(this.e);
            this.e = null;
        }
        if (this.d != null) {
            k.f(this.d);
            this.d = null;
        }
    }
    
    public void b() {
        if (super.i) {
            return;
        }
        ++super.q;
        if (!this.f.j() || !this.g.j() || this.h == null) {
            this.k();
            this.a.b();
            return;
        }
        if (this.d == null) {
            this.d = new aa(this.a.h, 100, this);
            this.d.d = this.a.f.q;
            this.d.p = true;
            final int h = this.d.d.h();
            final int c = this.d.d.c();
            this.d.a(super.a + (int)(super.c * 0.33) - h / 2, super.d - c, h, c);
            super.m.c(this.d);
        }
        if (this.e == null) {
            this.e = new aa(this.a.h, 101, this);
            this.e.d = this.a.f.p;
            this.e.p = true;
            final int h2 = this.e.d.h();
            final int c2 = this.e.d.c();
            this.e.a(super.a + (int)(super.c * 0.66) - h2 / 2, super.d - c2, h2, c2);
            super.m.c(this.e);
        }
    }
    
    private void k() {
        this.c(true);
        this.b(false);
        this.b.c(false);
        this.j();
    }
    
    public void a(final n n) {
        final j o = this.a.f.o;
        final j s = this.a.f.s;
        if (!o.j() || !s.j() || this.h == null) {
            return;
        }
        n.a(o, 0, 0);
        n.a(s, (super.c - s.h()) / 2 + 5, 30);
        if (this.c != null) {
            final Rectangle rectangle = new Rectangle(30, 60, super.c - 60, super.d);
            n.e = new f(0, 0, 0);
            n.f = this.h;
            this.a(n, rectangle, this.c, 16, 0);
        }
    }
    
    public as(final WordFallApplet a, final String c) {
        super.s = true;
        this.a = a;
        this.b = this.a.k;
        this.c = c;
        this.f = this.a.f.o;
        this.g = this.a.f.s;
        this.h = this.a.b("SansSerif", 1, 14);
    }
    
    public void c(final boolean b) {
        super.c(b);
        if (this.e != null) {
            this.e.c(b);
        }
        if (this.d != null) {
            this.d.c(b);
        }
    }
    
    public void a(final int n) {
        this.a.c(21);
    }
    
    public void b(final int n) {
    }
    
    public void d(final int n) {
        this.k();
        if (n == 101) {
            this.a.b();
        }
    }
    
    public void b(final boolean b) {
        super.b(b);
        if (this.e != null) {
            this.e.b(b);
        }
        if (this.d != null) {
            this.d.b(b);
        }
    }
    
    public void b(final int n, final int n2, final int n3) {
        if (n3 < 0) {
            this.k();
        }
    }
    
    public void f(final int n) {
    }
    
    public void g(final int n) {
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
        super.a(n, b, b2);
        if (n == 27) {
            this.k();
        }
    }
}
