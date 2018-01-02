// 
// Decompiled by Procyon v0.5.30
// 

public class dd extends r
{
    public c p;
    public int p;
    public l p;
    public l d;
    public boolean p;
    public l a;
    public boolean d;
    public l n;
    public boolean a;
    public boolean n;
    
    private final void a() {
        this.p = null;
        this.p = 0;
        this.p = new l();
        this.d = new l();
        this.p = true;
        this.a = new l();
        this.d = true;
        this.n = new l();
        this.a = true;
        this.n = false;
    }
    
    public final void d() {
        this.p.d();
        this.n.d();
        this.d.p();
        this.a.p();
        this.p = true;
        this.d = true;
    }
    
    public final void p(final l l, final l i) {
        this.d();
        this.p.n(l);
        this.n.n(i);
    }
    
    public final void p(final double n, final double n2, final double n3, final double n4) {
        this.d();
        this.p.d(n, n2);
        this.n.d(n3, n4);
    }
    
    public final void p() {
        this.d();
        this.p.d(0.0, 0.0);
        this.n.d(0.0, 0.0);
    }
    
    public final boolean p(final l l) {
        return this.p(this.p.p, this.n.p, l.p, 'X') && this.p(this.p.d, this.n.d, l.d, 'Y');
    }
    
    public final boolean p(final l l, final dd dd) {
        final x x = new x(this.p, this.n, this.p);
        final x x2 = new x(dd.p, dd.n, this.p);
        final double p2 = x.p;
        final double d = x.d;
        final double a = x.a;
        final double p3 = x2.p;
        final double d2 = x2.d;
        final double a2 = x2.a;
        final double n = d * a2 - d2 * a;
        final double n2 = p2 * a2 - p3 * a;
        final double n3 = p2 * d2 - p3 * d;
        final double n4 = -n3;
        if (!this.n(n) || !this.n(n3) || !this.n(n2)) {
            if (!this.n(n3)) {
                l.p(n / n3, n2 / n4);
                if (this.p(l) && dd.p(l)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void p(final c c) {
        c.p.drawLine((int)Math.round(this.p.p), (int)Math.round(this.p.d), (int)Math.round(this.n.p), (int)Math.round(this.n.d));
    }
    
    public final boolean p(final m m) {
        final g g = new g(this.p);
        g.p(m);
        return this.p(g);
    }
    
    public final boolean p(final g g) {
        final l l = new l();
        boolean b = false;
        this.p = true;
        this.d = true;
        for (int i = 0; i < g.a; ++i) {
            if (g.p[i].p(l, this)) {
                b = true;
                if (this.p) {
                    this.d.i(l);
                    this.p = false;
                }
                else {
                    this.a.i(l);
                    this.d = false;
                }
            }
        }
        return b;
    }
    
    private final void p(final l l) {
        if (this.a) {
            this.p.i(l);
            this.a = false;
        }
        else {
            this.n.i(l);
            this.n = true;
        }
    }
    
    public final boolean p(final m m, final g g) {
        boolean b;
        boolean b2;
        if (m != null) {
            b = m.p(this.p);
            b2 = m.p(this.n);
        }
        else {
            b = g.p(this.p);
            b2 = g.p(this.n);
        }
        if (b && b2) {
            return true;
        }
        this.a = true;
        this.n = false;
        if (m != null) {
            this.p(m);
        }
        else {
            this.p(g);
        }
        final boolean b3 = !this.p;
        final boolean b4 = !this.d;
        if (!b && !b2 && this.p && this.d) {
            return false;
        }
        if (b) {
            this.a = false;
        }
        if (b2) {
            this.p(this.n);
        }
        if (b3) {
            this.p(this.d);
        }
        if (b4) {
            this.p(this.a);
        }
        return !this.a && this.n;
    }
    
    public dd() {
        this.a();
    }
    
    public dd(final c p) {
        this.a();
        this.p = p;
    }
}
