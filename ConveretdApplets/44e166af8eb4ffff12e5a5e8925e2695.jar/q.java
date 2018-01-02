import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends o
{
    private double[] \u00d1;
    private double[] \u00d2;
    private double[] \u00d3;
    private double[] \u00d4;
    private Color[] \u00d5;
    private Color[] \u00d6;
    private double \u00d8;
    private o \u00d9;
    private boolean \u00da;
    
    public q(final o \u00f9, final boolean \u00fa, final int p3) {
        super.l = \u00f9.l;
        super.m = \u00f9.m;
        super.n = \u00f9.n;
        super.o = \u00f9.o;
        super.j = \u00f9.j;
        super.t = \u00f9.t;
        super.u = \u00f9.u;
        this.\u00d9 = \u00f9;
        this.\u00da = \u00fa;
        super.p = p3;
    }
    
    public l N() {
        final int l = super.l;
        final int m = super.m;
        final double n = this.\u00d3[super.r];
        final double n2 = this.\u00d4[super.r];
        return new l(l + (int)(n * Math.sin(n2)), m - (int)(n * Math.cos(n2)), this.\u00d6[super.r++]);
    }
    
    public void K() {
        ++super.q;
        if (this.\u00da) {
            this.u(super.p - super.q);
            return;
        }
        this.u(super.q);
    }
    
    public void S() {
        super.q = 0;
        this.\u00d9.O(super.l, super.m);
        this.\u00d9.J();
        super.j = this.\u00d9.j;
        this.\u00d1 = new double[super.j];
        this.\u00d2 = new double[super.j];
        this.\u00d5 = new Color[super.j];
        this.\u00d8 = 0.0;
        for (int i = 0; i < super.j; ++i) {
            final l n = this.\u00d9.N();
            this.\u00d1[i] = o.T(n, super.l, super.m);
            this.\u00d2[i] = o.V(n, super.l, super.m);
            this.\u00d5[i] = n.f;
            if (this.\u00d1[i] > this.\u00d8) {
                this.\u00d8 = this.\u00d1[i];
            }
        }
        if (this.\u00da) {
            this.u(super.p);
            return;
        }
        this.u(0);
    }
    
    private void u(final int n) {
        this.\u00d3 = new double[this.\u00d9.j];
        this.\u00d4 = new double[this.\u00d9.j];
        this.\u00d6 = new Color[this.\u00d9.j];
        super.j = 0;
        final double n2 = this.\u00d8 * n / (super.p - 1);
        for (int i = 0; i < this.\u00d9.j; ++i) {
            this.\u00d3[super.j] = this.\u00d1[i] - n2;
            if (this.\u00d3[super.j] >= 0.0) {
                this.\u00d4[super.j] = this.\u00d2[i] + (this.\u00d8 - this.\u00d3[super.j]) * n / super.p / this.\u00d8 * 3.0;
                this.\u00d6[super.j] = this.\u00d5[i];
                ++super.j;
            }
        }
    }
}
