import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class fq
{
    protected double[] Ea;
    protected mq Fa;
    protected String name;
    protected String f;
    protected String[] g;
    protected mq d;
    protected double kb;
    protected double lb;
    protected double mb;
    protected double nb;
    protected int h;
    protected int i;
    protected boolean j;
    protected boolean sb;
    protected boolean k;
    protected int Aa;
    protected Color Ba;
    protected Color l;
    protected int Ca;
    protected int Da;
    protected double[] m;
    
    public void l(final int ca) {
        if (ca >= 0) {
            this.Ca = ca;
        }
    }
    
    public void m(final int da) {
        this.Da = da;
    }
    
    public void a(final double[] m) {
        this.m = m;
    }
    
    public int f() {
        return this.Aa;
    }
    
    public void setColor(final Color ba) {
        this.Ba = ba;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void m(final String f) {
        this.f = f;
    }
    
    public void a(final int n, final String s) {
        this.g[n] = s;
    }
    
    public String a() {
        return this.f;
    }
    
    public String b(final int n) {
        return this.g[n];
    }
    
    public void _(final Color l) {
        this.l = l;
    }
    
    public void b(int n, int n2) {
        if (n < 1) {
            n = 1;
        }
        if (n2 < 1) {
            n2 = 1;
        }
        this.Ea = new double[n];
        this.Fa = new mq(n, n2);
        this.h = 0;
        this.i = 0;
        this.j = false;
        this.sb = false;
        this.k = false;
        this.Aa = -1;
        this.f = null;
        this.g = new String[n2];
        for (int i = 0; i < this.g.length; ++i) {
            this.g[i] = null;
        }
        this.d = null;
    }
    
    public void O() {
        this.kb = Double.POSITIVE_INFINITY;
        this.lb = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (this.Aa >= 0 && b <= _) {
            this.kb = this.Ea[b];
            this.lb = this.Ea[_];
        }
        this.j = true;
    }
    
    public void h() {
        this.mb = Double.POSITIVE_INFINITY;
        this.nb = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (this.Aa >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                for (int j = 0; j < this.Fa.c(); ++j) {
                    this.mb = Math.min(this.mb, this.Fa.getValue(i, j));
                    this.nb = Math.max(this.nb, this.Fa.getValue(i, j));
                }
            }
        }
        if (this.m != null && this.m.length > 0 && (this.m.length > 1 || (this.m.length == 1 && this.m[0] != 0.0))) {
            for (int k = 0; k < this.m.length; ++k) {
                this.mb = Math.min(this.mb, this.m[k]);
                this.nb = Math.max(this.nb, this.m[k]);
            }
        }
        this.sb = true;
    }
    
    public double e() {
        double n = Double.POSITIVE_INFINITY;
        if (this.Aa >= 0) {
            n = this.Ea[0];
        }
        return n;
    }
    
    public double f() {
        double n = Double.NEGATIVE_INFINITY;
        if (this.Aa >= 0) {
            n = this.Ea[this.Aa];
        }
        return n;
    }
    
    public double b() {
        if (this.j) {
            return this.lb;
        }
        this.O();
        return this.lb;
    }
    
    public double a() {
        if (this.sb) {
            return this.nb;
        }
        this.h();
        return this.nb;
    }
    
    public double _() {
        if (this.j) {
            return this.kb;
        }
        this.O();
        return this.kb;
    }
    
    public double l() {
        if (this.sb) {
            return this.mb;
        }
        this.h();
        return this.mb;
    }
    
    public void _(final boolean k) {
        this.k = k;
        this.j = false;
        this.sb = false;
    }
    
    public int b() {
        if (this.Aa < 0) {
            return 0;
        }
        if (this.k) {
            return this.h;
        }
        return 0;
    }
    
    public int _() {
        if (this.Aa < 0) {
            return 0;
        }
        if (this.k) {
            return this.i;
        }
        return this.Aa;
    }
    
    public double g() {
        final int b = this.b();
        final int _ = this._();
        if (this.Aa < 0 && b > _) {
            return Double.NEGATIVE_INFINITY;
        }
        return this.Ea[b];
    }
    
    public double h() {
        final int b = this.b();
        final int _ = this._();
        if (this.Aa < 0 && b > _) {
            return Double.POSITIVE_INFINITY;
        }
        return this.Ea[_];
    }
    
    public void _(final int n, final int n2) {
        this.j = false;
        this.sb = false;
        this.h = Math.max(n, 0);
        this.i = Math.min(n2, this.Aa);
        if (this.i < this.h) {
            this.h = 1;
            this.i = 0;
            return;
        }
        if (this.i > this.Aa) {
            this.i = this.Aa;
        }
    }
    
    public void l(final double n, final double n2) {
        int n3 = 0;
        int aa = this.Aa;
        for (int i = 0; i <= this.Aa; ++i) {
            n3 = i;
            if (this.Ea[i] >= n) {
                break;
            }
        }
        for (int j = this.Aa; j >= 0; --j) {
            aa = j;
            if (this.Ea[j] <= n2) {
                break;
            }
        }
        if (this.Ea[aa] < n || this.Ea[n3] > n2) {
            this._(1, 0);
        }
        else {
            this._(n3, aa);
        }
    }
    
    public int b(final double n) {
        if (this.h > this.i) {
            return -1;
        }
        final int b = this.b();
        int n2 = this._();
        if (this.Da >= 0 && this.Da < n2) {
            n2 = this.Da;
        }
        int n3 = b;
        double abs = Double.MAX_VALUE;
        for (int i = n2; i >= b; --i) {
            if (Math.abs(this.Ea[i] - n) < abs) {
                n3 = i;
                abs = Math.abs(this.Ea[i] - n);
            }
        }
        return n3;
    }
    
    public double a(final int n) {
        return this.Ea[n];
    }
    
    public double a(final int n, final int n2) {
        return this.Fa.getValue(n, n2);
    }
    
    public double b(final int n, final int n2) {
        if (this.d != null) {
            return this.d.getValue(n, n2);
        }
        return this.Fa.getValue(n, n2);
    }
    
    public int g() {
        return this.Fa.c();
    }
    
    public int h() {
        if (this.d != null) {
            return this.d.c();
        }
        return this.Fa.c();
    }
    
    public boolean a(final double n, final double[] array) {
        if (array == null || this.Aa >= this.Ea.length - 1) {
            return false;
        }
        ++this.Aa;
        this.Ea[this.Aa] = n;
        for (int i = 0; i < this.Fa.c(); ++i) {
            if (i < array.length) {
                this.Fa.a(this.Aa, i, array[i]);
            }
            else {
                this.Fa.a(this.Aa, i, 0.0);
            }
        }
        this.j = false;
        this.sb = false;
        return true;
    }
    
    public void b(final int n, final int n2, final double n3) {
        this.Fa.a(n, n2, n3);
    }
    
    public void clear() {
        this.j = false;
        this.sb = false;
        this.Aa = -1;
    }
    
    public void _(final Graphics graphics, final Np np, final Np np2) {
        final int n = (int)np.l();
        final int n2 = (int)np.a();
        graphics.setColor(this.l);
        if (this.m != null) {
            for (int i = 0; i < this.m.length; ++i) {
                final int n3 = (int)np2.b(this.m[i]);
                graphics.drawLine(n, n3, n2, n3);
            }
        }
    }
    
    public abstract void b(final Graphics p0, final Np p1, final Np p2);
    
    fq(final int n, final int n2) {
        this.h = 0;
        this.i = 0;
        this.Aa = -1;
        this.Ba = Color.white;
        this.l = Color.lightGray;
        this.Ca = 0;
        this.Da = -1;
        this.b(n, n2);
    }
    
    public void P() {
        this.d = (mq)this.Fa.clone();
    }
    
    public String getName() {
        return this.name;
    }
}
