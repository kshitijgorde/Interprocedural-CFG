import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class p
{
    protected double[] W;
    protected case X;
    protected String name;
    protected String xra;
    protected String[] yra;
    protected case ura;
    protected double gra;
    protected double fra;
    protected double era;
    protected double dra;
    protected int zra;
    protected int Ara;
    protected boolean Bra;
    protected boolean ora;
    protected boolean Cra;
    protected int S;
    protected Color T;
    protected Color Dra;
    protected int U;
    protected int V;
    protected double[] Era;
    
    public void x(final int u) {
        if (u >= 0) {
            this.U = u;
        }
    }
    
    public void J(final int v) {
        this.V = v;
    }
    
    public void a(final double[] era) {
        this.Era = era;
    }
    
    public int H() {
        return this.S;
    }
    
    public void setColor(final Color t) {
        this.T = t;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void q(final String xra) {
        this.xra = xra;
    }
    
    public void a(final int n, final String s) {
        this.yra[n] = s;
    }
    
    public String F() {
        return this.xra;
    }
    
    public String _(final int n) {
        return this.yra[n];
    }
    
    public void k(final Color dra) {
        this.Dra = dra;
    }
    
    public void b(int n, int n2) {
        if (n < 1) {
            n = 1;
        }
        if (n2 < 1) {
            n2 = 1;
        }
        this.W = new double[n];
        this.X = new case(n, n2);
        this.zra = 0;
        this.Ara = 0;
        this.Bra = false;
        this.ora = false;
        this.Cra = false;
        this.S = -1;
        this.xra = null;
        this.yra = new String[n2];
        for (int i = 0; i < this.yra.length; ++i) {
            this.yra[i] = null;
        }
        this.ura = null;
    }
    
    public void X() {
        this.gra = Double.POSITIVE_INFINITY;
        this.fra = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (this.S >= 0 && b <= _) {
            this.gra = this.W[b];
            this.fra = this.W[_];
        }
        this.Bra = true;
    }
    
    public void V() {
        this.era = Double.POSITIVE_INFINITY;
        this.dra = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (this.S >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                for (int j = 0; j < this.X.V(); ++j) {
                    this.era = Math.min(this.era, this.X.getValue(i, j));
                    this.dra = Math.max(this.dra, this.X.getValue(i, j));
                }
            }
        }
        if (this.Era != null && this.Era.length > 0 && (this.Era.length > 1 || (this.Era.length == 1 && this.Era[0] != 0.0))) {
            for (int k = 0; k < this.Era.length; ++k) {
                this.era = Math.min(this.era, this.Era[k]);
                this.dra = Math.max(this.dra, this.Era[k]);
            }
        }
        this.ora = true;
    }
    
    public double n() {
        double n = Double.POSITIVE_INFINITY;
        if (this.S >= 0) {
            n = this.W[0];
        }
        return n;
    }
    
    public double m() {
        double n = Double.NEGATIVE_INFINITY;
        if (this.S >= 0) {
            n = this.W[this.S];
        }
        return n;
    }
    
    public double b() {
        if (this.Bra) {
            return this.fra;
        }
        this.X();
        return this.fra;
    }
    
    public double a() {
        if (this.ora) {
            return this.dra;
        }
        this.V();
        return this.dra;
    }
    
    public double _() {
        if (this.Bra) {
            return this.gra;
        }
        this.X();
        return this.gra;
    }
    
    public double k() {
        if (this.ora) {
            return this.era;
        }
        this.V();
        return this.era;
    }
    
    public void F(final boolean cra) {
        this.Cra = cra;
        this.Bra = false;
        this.ora = false;
    }
    
    public int b() {
        if (this.S < 0) {
            return 0;
        }
        if (this.Cra) {
            return this.zra;
        }
        return 0;
    }
    
    public int _() {
        if (this.S < 0) {
            return 0;
        }
        if (this.Cra) {
            return this.Ara;
        }
        return this.S;
    }
    
    public double f() {
        final int b = this.b();
        final int _ = this._();
        if (this.S < 0 && b > _) {
            return Double.NEGATIVE_INFINITY;
        }
        return this.W[b];
    }
    
    public double g() {
        final int b = this.b();
        final int _ = this._();
        if (this.S < 0 && b > _) {
            return Double.POSITIVE_INFINITY;
        }
        return this.W[_];
    }
    
    public void l(final int n, final int n2) {
        this.Bra = false;
        this.ora = false;
        this.zra = Math.max(n, 0);
        this.Ara = Math.min(n2, this.S);
        if (this.Ara < this.zra) {
            this.zra = 1;
            this.Ara = 0;
            return;
        }
        if (this.Ara > this.S) {
            this.Ara = this.S;
        }
    }
    
    public void c(final double n, final double n2) {
        int n3 = 0;
        int s = this.S;
        for (int i = 0; i <= this.S; ++i) {
            n3 = i;
            if (this.W[i] >= n) {
                break;
            }
        }
        for (int j = this.S; j >= 0; --j) {
            s = j;
            if (this.W[j] <= n2) {
                break;
            }
        }
        if (this.W[s] < n || this.W[n3] > n2) {
            this.l(1, 0);
        }
        else {
            this.l(n3, s);
        }
    }
    
    public int b(final double n) {
        if (this.zra > this.Ara) {
            return -1;
        }
        final int b = this.b();
        int n2 = this._();
        if (this.V >= 0 && this.V < n2) {
            n2 = this.V;
        }
        int n3 = b;
        double abs = Double.MAX_VALUE;
        for (int i = n2; i >= b; --i) {
            if (Math.abs(this.W[i] - n) < abs) {
                n3 = i;
                abs = Math.abs(this.W[i] - n);
            }
        }
        return n3;
    }
    
    public double f(final int n) {
        return this.W[n];
    }
    
    public double a(final int n, final int n2) {
        return this.X.getValue(n, n2);
    }
    
    public double b(final int n, final int n2) {
        if (this.ura != null) {
            return this.ura.getValue(n, n2);
        }
        return this.X.getValue(n, n2);
    }
    
    public int d() {
        return this.X.V();
    }
    
    public int Y() {
        if (this.ura != null) {
            return this.ura.V();
        }
        return this.X.V();
    }
    
    public boolean b(final double n, final double[] array) {
        if (array == null || this.S >= this.W.length - 1) {
            return false;
        }
        ++this.S;
        this.W[this.S] = n;
        for (int i = 0; i < this.X.V(); ++i) {
            if (i < array.length) {
                this.X._(this.S, i, array[i]);
            }
            else {
                this.X._(this.S, i, 0.0);
            }
        }
        this.Bra = false;
        this.ora = false;
        return true;
    }
    
    public void b(final int n, final int n2, final double n3) {
        this.X._(n, n2, n3);
    }
    
    public void clear() {
        this.Bra = false;
        this.ora = false;
        this.S = -1;
    }
    
    public void _(final Graphics graphics, final o o, final o o2) {
        final int n = (int)o.k();
        final int n2 = (int)o.a();
        graphics.setColor(this.Dra);
        if (this.Era != null) {
            for (int i = 0; i < this.Era.length; ++i) {
                final int n3 = (int)o2.b(this.Era[i]);
                graphics.drawLine(n, n3, n2, n3);
            }
        }
    }
    
    public abstract void b(final Graphics p0, final o p1, final o p2);
    
    p(final int n, final int n2) {
        this.zra = 0;
        this.Ara = 0;
        this.S = -1;
        this.T = Color.white;
        this.Dra = Color.lightGray;
        this.U = 0;
        this.V = -1;
        this.b(n, n2);
    }
    
    public void z() {
        this.ura = (case)this.X.clone();
    }
    
    public String getName() {
        return this.name;
    }
}
