import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class instanceof
{
    protected double[] Dla;
    protected throw yla;
    protected String name;
    protected String ama;
    protected String[] bma;
    protected throw Ala;
    protected double hka;
    protected double ika;
    protected double Uka;
    protected double Vka;
    protected int cma;
    protected int dma;
    protected boolean ema;
    protected boolean zla;
    protected boolean fma;
    protected int aka;
    protected Color xa;
    protected Color gma;
    protected int Bla;
    protected int Cla;
    protected double[] hma;
    
    public void w(final int bla) {
        if (bla >= 0) {
            this.Bla = bla;
        }
    }
    
    public void x(final int cla) {
        this.Cla = cla;
    }
    
    public void a(final double[] hma) {
        this.hma = hma;
    }
    
    public int A() {
        return this.aka;
    }
    
    public void setColor(final Color xa) {
        this.xa = xa;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void y(final String ama) {
        this.ama = ama;
    }
    
    public void _(final int n, final String s) {
        this.bma[n] = s;
    }
    
    public String L() {
        return this.ama;
    }
    
    public String a(final int n) {
        return this.bma[n];
    }
    
    public void e(final Color gma) {
        this.gma = gma;
    }
    
    public void d(int n, int n2) {
        if (n < 1) {
            n = 1;
        }
        if (n2 < 1) {
            n2 = 1;
        }
        this.Dla = new double[n];
        this.yla = new throw(n, n2);
        this.cma = 0;
        this.dma = 0;
        this.ema = false;
        this.zla = false;
        this.fma = false;
        this.aka = -1;
        this.ama = null;
        this.bma = new String[n2];
        for (int i = 0; i < this.bma.length; ++i) {
            this.bma[i] = null;
        }
        this.Ala = null;
    }
    
    public void r() {
        this.hka = Double.POSITIVE_INFINITY;
        this.ika = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (this.aka >= 0 && u <= v) {
            this.hka = this.Dla[u];
            this.ika = this.Dla[v];
        }
        this.ema = true;
    }
    
    public void q() {
        this.Uka = Double.POSITIVE_INFINITY;
        this.Vka = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (this.aka >= 0 && u <= v) {
            for (int i = u; i <= v; ++i) {
                for (int j = 0; j < this.yla.t(); ++j) {
                    this.Uka = Math.min(this.Uka, this.yla.getValue(i, j));
                    this.Vka = Math.max(this.Vka, this.yla.getValue(i, j));
                }
            }
        }
        if (this.hma != null && this.hma.length > 0 && (this.hma.length > 1 || (this.hma.length == 1 && this.hma[0] != 0.0))) {
            for (int k = 0; k < this.hma.length; ++k) {
                this.Uka = Math.min(this.Uka, this.hma[k]);
                this.Vka = Math.max(this.Vka, this.hma[k]);
            }
        }
        this.zla = true;
    }
    
    public double c() {
        double n = Double.POSITIVE_INFINITY;
        if (this.aka >= 0) {
            n = this.Dla[0];
        }
        return n;
    }
    
    public double d() {
        double n = Double.NEGATIVE_INFINITY;
        if (this.aka >= 0) {
            n = this.Dla[this.aka];
        }
        return n;
    }
    
    public double l() {
        if (this.ema) {
            return this.ika;
        }
        this.r();
        return this.ika;
    }
    
    public double n() {
        if (this.zla) {
            return this.Vka;
        }
        this.q();
        return this.Vka;
    }
    
    public double k() {
        if (this.ema) {
            return this.hka;
        }
        this.r();
        return this.hka;
    }
    
    public double m() {
        if (this.zla) {
            return this.Uka;
        }
        this.q();
        return this.Uka;
    }
    
    public void j(final boolean fma) {
        this.fma = fma;
        this.ema = false;
        this.zla = false;
    }
    
    public int u() {
        if (this.aka < 0) {
            return 0;
        }
        if (this.fma) {
            return this.cma;
        }
        return 0;
    }
    
    public int v() {
        if (this.aka < 0) {
            return 0;
        }
        if (this.fma) {
            return this.dma;
        }
        return this.aka;
    }
    
    public double e() {
        final int u = this.u();
        final int v = this.v();
        if (this.aka < 0 && u > v) {
            return Double.NEGATIVE_INFINITY;
        }
        return this.Dla[u];
    }
    
    public double f() {
        final int u = this.u();
        final int v = this.v();
        if (this.aka < 0 && u > v) {
            return Double.POSITIVE_INFINITY;
        }
        return this.Dla[v];
    }
    
    public void e(final int n, final int n2) {
        this.ema = false;
        this.zla = false;
        this.cma = Math.max(n, 0);
        this.dma = Math.min(n2, this.aka);
        if (this.dma < this.cma) {
            this.cma = 1;
            this.dma = 0;
            return;
        }
        if (this.dma > this.aka) {
            this.dma = this.aka;
        }
    }
    
    public void k(final double n, final double n2) {
        int n3 = 0;
        int aka = this.aka;
        for (int i = 0; i <= this.aka; ++i) {
            n3 = i;
            if (this.Dla[i] >= n) {
                break;
            }
        }
        for (int j = this.aka; j >= 0; --j) {
            aka = j;
            if (this.Dla[j] <= n2) {
                break;
            }
        }
        if (this.Dla[aka] < n || this.Dla[n3] > n2) {
            this.e(1, 0);
        }
        else {
            this.e(n3, aka);
        }
    }
    
    public int b(final double n) {
        if (this.cma > this.dma) {
            return -1;
        }
        final int u = this.u();
        int n2 = this.v();
        if (this.Cla >= 0 && this.Cla < n2) {
            n2 = this.Cla;
        }
        int n3 = u;
        double abs = Double.MAX_VALUE;
        for (int i = n2; i >= u; --i) {
            if (Math.abs(this.Dla[i] - n) < abs) {
                n3 = i;
                abs = Math.abs(this.Dla[i] - n);
            }
        }
        return n3;
    }
    
    public double e(final int n) {
        return this.Dla[n];
    }
    
    public double b(final int n, final int n2) {
        return this.yla.getValue(n, n2);
    }
    
    public double _(final int n, final int n2) {
        if (this.Ala != null) {
            return this.Ala.getValue(n, n2);
        }
        return this.yla.getValue(n, n2);
    }
    
    public int B() {
        return this.yla.t();
    }
    
    public int w() {
        if (this.Ala != null) {
            return this.Ala.t();
        }
        return this.yla.t();
    }
    
    public boolean b(final double n, final double[] array) {
        if (array == null || this.aka >= this.Dla.length - 1) {
            return false;
        }
        ++this.aka;
        this.Dla[this.aka] = n;
        for (int i = 0; i < this.yla.t(); ++i) {
            if (i < array.length) {
                this.yla._(this.aka, i, array[i]);
            }
            else {
                this.yla._(this.aka, i, 0.0);
            }
        }
        this.ema = false;
        this.zla = false;
        return true;
    }
    
    public void a(final int n, final int n2, final double n3) {
        this.yla._(n, n2, n3);
    }
    
    public void clear() {
        this.ema = false;
        this.zla = false;
        this.aka = -1;
    }
    
    public void _(final Graphics graphics, final throws throws1, final throws throws2) {
        final int n = (int)throws1.m();
        final int n2 = (int)throws1.n();
        graphics.setColor(this.gma);
        if (this.hma != null) {
            for (int i = 0; i < this.hma.length; ++i) {
                final int n3 = (int)throws2.b(this.hma[i]);
                graphics.drawLine(n, n3, n2, n3);
            }
        }
    }
    
    public abstract void b(final Graphics p0, final throws p1, final throws p2);
    
    instanceof(final int n, final int n2) {
        this.cma = 0;
        this.dma = 0;
        this.aka = -1;
        this.xa = Color.white;
        this.gma = Color.lightGray;
        this.Bla = 0;
        this.Cla = -1;
        this.d(n, n2);
    }
    
    public void s() {
        this.Ala = (throw)this.yla.clone();
    }
    
    public String getName() {
        return this.name;
    }
}
