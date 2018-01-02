// 
// Decompiled by Procyon v0.5.30
// 

public class d extends k
{
    public int p;
    public boolean p;
    public boolean d;
    private int d;
    private int a;
    private boolean a;
    private int n;
    public int v;
    public w[] p;
    public double p;
    public int i;
    public double d;
    public int l;
    public boolean n;
    public boolean v;
    public z p;
    public g p;
    public g d;
    public v p;
    
    public final void p(final int v, final int p4, final v p5, final c p6) {
        this.v = v;
        this.p = p4;
        super.p = p6;
        this.m();
        this.p = new g(p6, p5);
        this.d = new g(p6);
        this.p = p5;
    }
    
    public final void b() {
    }
    
    public final void m() {
        for (int i = 0; i < this.v; ++i) {
            this.p[i] = new w();
        }
    }
    
    public final void p(final z z, final z z2, final z z3, final z z4) {
        this.p[0].d(z, z2);
        this.p[1].d(z2, z3);
        this.p[2].d(z3, z4);
        this.p[3].d(z4, z);
    }
    
    public final void o() {
        this.d = false;
    }
    
    public final void e() {
        final i i = new i(true);
        for (int j = 0; j < this.v; ++j) {
            this.d = this.p[j].p(i, super.p);
        }
    }
    
    public final void q() {
        this.d = 0;
        this.a = 0;
        this.a = false;
        this.n = 0;
    }
    
    public final boolean p(final e e) {
        final boolean b = true;
        while (this.d < this.v && b) {
            if (this.p[this.d].p) {
                e.p = this.p[this.d].p;
                this.p[this.d].p = false;
                ++this.a;
                return true;
            }
            if (this.p[this.d].d) {
                e.p = this.p[this.d].d;
                this.p[this.d].d = false;
                if (this.a == 0) {
                    this.n = this.d;
                    this.a = true;
                }
                ++this.a;
                return true;
            }
            if (this.p[this.d].a) {
                e.p = this.p[this.d].a;
                this.p[this.d].a = false;
                if (this.d < this.v - 1) {
                    this.p[this.d + 1].p = false;
                }
                ++this.a;
                return true;
            }
            ++this.d;
        }
        if (this.a && this.d >= this.v) {
            e.p = this.p[this.n].d;
            this.a = false;
            ++this.a;
            return true;
        }
        return this.d < this.v;
    }
    
    public final void i() {
        final l l = new l();
        super.p.p = this.p;
        this.b();
        this.p.p = this.p;
        this.o();
        this.p.l();
        for (int i = 0; i < this.v; ++i) {
            l.p(this.p[i].p, super.p);
            this.p.p(i, l);
        }
        this.p.d(this.v);
        if (this.p.p(super.p.a)) {
            this.p.p = true;
        }
        else {
            this.p.p = false;
        }
    }
    
    public final void k() {
        final e e = new e();
        final l l = new l();
        final l i = new l();
        super.p.p = this.p;
        this.p.i();
        this.e();
        this.q();
        if (this.p(e)) {
            l.p(e.p, super.p);
            while (this.p(e)) {
                i.p(e.p, super.p);
                this.p.p(l, i);
                l.d(i);
            }
        }
    }
    
    public final void a() {
        this.b();
        this.k();
        this.p.p = this.p;
        if (this.p.p(super.p.a)) {
            this.p.p = true;
        }
        else {
            this.p.p = false;
        }
    }
    
    public final void h() {
        this.p = this.p[0].p.a;
        this.i = 0;
        this.d = this.p[0].p.a;
        this.l = 0;
        for (int i = 1; i < this.v; ++i) {
            if (this.p < this.p[i].p.a) {
                this.p = this.p[i].p.a;
                this.i = i;
            }
            if (this.d > this.p[i].p.a) {
                this.d = this.p[i].p.a;
                this.l = i;
            }
        }
    }
    
    public d() {
        this.p = -1;
        this.p = false;
        this.d = false;
        this.d = 0;
        this.a = 0;
        this.a = false;
        this.n = 0;
        this.v = 0;
        this.p = new w[10];
        this.n = true;
        this.v = false;
        this.p = new z();
        this.p = null;
    }
}
