import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends d
{
    public boolean p;
    public int p;
    public String p;
    public dn p;
    public int d;
    public boolean d;
    public boolean a;
    public v p;
    public double p;
    public double d;
    public double a;
    public double n;
    public z[] p;
    public f p;
    public f d;
    public f a;
    public f n;
    public z p;
    public l p;
    public z[] d;
    public boolean n;
    public boolean v;
    public g p;
    public u[] p;
    public g d;
    public boolean i;
    public boolean l;
    public boolean b;
    public int a;
    public int n;
    public v d;
    public int v;
    public int i;
    public w[] p;
    
    public final void p(final double a, final double n) {
        this.a = a;
        this.n = n;
        this.p = (super.p.g - a) / 2.0;
        this.d = (super.p.g - n) / 2.0;
    }
    
    public final boolean p() {
        final int n = this.p.v - 1;
        if (this.a) {
            this.p.p(this.p.p[0].p, this.p.p[0].a);
        }
        else {
            this.p.p(this.p.p[0].a, this.p.p[0].p);
        }
        this.d.p(this.p.p[n].a, this.p.p[n].p);
        this.p.p();
        this.d.p();
        if (this.a) {
            this.p[0].p(this.p.p[0].p, this.p, this.p);
        }
        else {
            this.p[0].p(this.p.p[0].a, this.p, this.p);
        }
        this.p[0].p(this.p[0], this.d, this.d);
        this.p[1].p(this.p[0], this.p, this.a);
        this.p[2].p(this.p[1], this.d, this.n);
        this.p[3].p(this.p[0], this.d, this.n);
        this.p(this.p[0], this.p[1], this.p[2], this.p[3]);
        this.h();
        super.n = this.f(super.d);
        super.v = this.p(super.n, super.p);
        if (super.n) {
            if (super.v) {
                this.a();
            }
            else {
                this.i();
            }
            if (super.d.d(super.p)) {
                if (super.p.d) {
                    super.p.d();
                }
                else {
                    super.d.d();
                }
                return true;
            }
        }
        return false;
    }
    
    public final void d() {
        this.p.n(this.p[0]);
        this.a.p(this.a / this.p.n, this.p);
        this.n.p(this.n / this.p.v, this.d);
    }
    
    public final void c() {
        this.p[0].p(this.p[0], this.p[2]);
    }
    
    public final void g() {
        final i i = new i(true);
        super.p.p.setColor(Color.red);
        this.c();
        for (int j = 0; j < this.v; ++j) {
            this.p[j].p(i, super.p);
            if (i.p) {
                this.p[j].p(super.p);
            }
        }
    }
    
    public final boolean p(final m m) {
        boolean b = true;
        this.n = false;
        for (int i = 0; i < 4; ++i) {
            this.p.p(this.d[i], super.p);
            this.p.p[i].p.n(this.p);
            if (m.p(this.p)) {
                this.n = true;
            }
            else {
                b = false;
            }
        }
        return b;
    }
    
    public final void p() {
        if (this.p(super.p.p)) {
            this.p.b();
            this.p.d();
            this.p.a();
        }
        else {
            this.p.p();
            this.p.b();
            if ((this.n || this.p.p(super.p.a)) && this.d.d(this.p)) {
                this.d.a();
            }
        }
    }
    
    public final void l() {
        this.p.a(4);
        if (super.v) {
            this.i = this.f(this.d[this.n].a);
            this.l = this.p(this.i, this.d[this.a].a);
            if (this.i) {
                if (this.l) {
                    this.d.p(this.d[0], this.d[1], this.d[2], this.d[3]);
                    this.d.f();
                }
                else {
                    this.p();
                }
            }
        }
        else {
            this.p();
        }
    }
    
    public final void r() {
        double n = this.d[0].a;
        double n2 = this.d[0].a;
        this.a = 0;
        this.n = 0;
        for (int i = 1; i < 4; ++i) {
            if (n < this.d[i].a) {
                n = this.d[i].a;
                this.a = i;
            }
            if (n2 > this.d[i].a) {
                n2 = this.d[i].a;
                this.n = i;
            }
        }
    }
    
    public final void p(final int n) {
        if (this.p.d) {
            super.p.p.setColor(this.p.p[n]);
        }
        else {
            super.p.p.setColor(new Color(n));
        }
    }
    
    public final int p(final int n, final int n2) {
        final int n3 = this.p.d[n + 1];
        if (n2 != n3) {
            this.p(n3);
        }
        return n3;
    }
    
    public final void j() {
        this.d[1].d(this.d[0], this.a);
        this.d[2].d(this.d[1], this.n);
        this.d[3].d(this.d[0], this.n);
    }
    
    public final boolean d() {
        if (!super.p.j && this.p.n > 16) {
            return false;
        }
        int n = 10;
        int n2 = 0;
        int p = this.p.d[0];
        final int n3 = this.p.n - 1;
        this.d = p;
        if (this.v || !this.p.d) {
            this.p(p);
        }
        else {
            final double p2 = this.p.p();
            final Color color = this.p.p[p];
            super.p.p.setColor(new Color((int)(p2 * color.getRed()), (int)(p2 * color.getGreen()), (int)(p2 * color.getBlue())));
        }
        if (this.d) {
            if (super.p.d) {
                super.p.a();
                n = super.p.a();
            }
            else {
                super.d.a();
                n = super.d.a();
            }
        }
        if (n <= 9) {
            return false;
        }
        this.d[0].n(this.p);
        this.j();
        this.r();
        for (int i = 0; i < this.p.v; ++i) {
            this.d[0].n(this.p);
            this.d[1].d(this.d[0], this.a);
            for (int j = 0; j < this.p.n; ++j) {
                this.d[2].d(this.d[1], this.n);
                this.d[3].d(this.d[0], this.n);
                if (p != this.d) {
                    this.l();
                }
                if (n2 >= this.p.a - 1) {
                    return true;
                }
                p = this.p(n2, p);
                this.d[0].n(this.d[1]);
                this.d[1].d(this.d[0], this.a);
                ++n2;
            }
            this.p.d(this.p, this.n);
        }
        return true;
    }
    
    public final void v() {
        this.d();
        if (this.p.n >= 1 && this.p.v >= 1) {
            this.d();
        }
    }
    
    public final void f() {
        if (this.p < 0) {
            this.p = super.p.p(this.p);
        }
        if (this.p >= 0) {
            this.p = super.p.p[this.p];
            if (this.b) {
                this.p.a();
            }
        }
        else {
            this.p.a = 0;
            this.b = false;
            System.out.print("could not find the image:" + this.p);
        }
    }
    
    public final void n() {
        if (this.p() && super.n) {
            if (this.p) {
                this.v();
            }
            else {
                this.g();
            }
        }
    }
    
    public q(final boolean p12, final int p13, final String p14, final boolean a, final int d, final double p15, final double d2, final double a2, final double n, final v p16, final boolean v, final c c) {
        this.p = false;
        this.p = -1;
        this.p = new String();
        this.p = null;
        this.d = true;
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
        this.n = 0.0;
        this.p = new z[4];
        this.p = new f();
        this.d = new f();
        this.a = new f();
        this.n = new f();
        this.p = new z();
        this.p = new l();
        this.d = new z[4];
        this.n = false;
        this.v = true;
        this.p = new u[4];
        this.a = 0;
        this.n = 0;
        this.v = 0;
        this.i = 50;
        this.p = new w[this.i];
        this.p(4, p16.p, p16, c);
        this.p = p12;
        this.p = p13;
        this.p = p14;
        this.a = a;
        this.d = d;
        this.p = p15;
        this.d = d2;
        this.a = a2;
        this.n = n;
        this.p = p16;
        this.v = v;
        for (int i = 0; i < 4; ++i) {
            this.p[i] = new z();
        }
        if (p12) {
            for (int j = 0; j < 4; ++j) {
                this.d[j] = new z();
                this.p[j] = new u();
            }
        }
        else {
            this.v = 1;
            for (int k = 0; k < this.v; ++k) {
                this.p[k] = new w();
            }
            this.c();
        }
        this.p = new g(c, p16);
        this.d = new g(c, p16);
        this.d = new v(4, this.p, super.p);
        this.b = false;
    }
}
