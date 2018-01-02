import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class public extends instanceof
{
    private double[] xla;
    private int eka;
    
    public public(final int n) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        this.eka = 0;
    }
    
    public public(final int n, int eka) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        if (eka < 0) {
            eka = 0;
        }
        this.eka = eka;
    }
    
    public boolean a(final double n, final double n2) {
        this.xla[0] = n2;
        return super.b(n, this.xla);
    }
    
    public void q() {
        super.Uka = Double.POSITIVE_INFINITY;
        super.Vka = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (super.aka >= 0 && u <= v) {
            for (int i = u; i <= v; ++i) {
                for (int j = 0; j < super.yla.t(); ++j) {
                    super.Uka = Math.min(super.Uka, Math.abs(super.yla.getValue(i, j)));
                    super.Vka = Math.max(super.Vka, Math.abs(super.yla.getValue(i, j)));
                }
            }
        }
        super.zla = true;
    }
    
    public double b(final int n, final int n2) {
        return Math.abs(super.yla.getValue(n, n2));
    }
    
    public double _(final int n, final int n2) {
        if (super.Ala != null) {
            return Math.abs(super.Ala.getValue(n, n2));
        }
        return Math.abs(super.yla.getValue(n, n2));
    }
    
    public void b(final Graphics graphics, final throws throws1, final throws throws2) {
        if (super.aka < 0) {
            return;
        }
        this._(graphics, throws1, throws2);
        graphics.setColor(super.xa);
        final int max = Math.max(super.Bla, this.u());
        int n = this.v();
        if (super.Cla >= 0) {
            n = Math.min(n, super.Cla);
        }
        if (max == n) {
            graphics.fillOval((int)throws1.b(super.Dla[max]) - this.eka, (int)throws2.b(Math.abs(super.yla.getValue(max, 0))) - this.eka, 1 + this.eka * 2, 1 + this.eka * 2);
        }
        else if (max < n) {
            final boolean[] array = new boolean[n + 1];
            for (int i = 0; i < array.length; ++i) {
                if (i < max) {
                    array[i] = true;
                }
                else {
                    array[i] = false;
                }
            }
            for (int j = max; j < n; ++j) {
                final double value = super.yla.getValue(j, 0);
                final double value2 = super.yla.getValue(j + 1, 0);
                final int n2 = (int)throws1.b(super.Dla[j]);
                final int n3 = (int)throws2.b(Math.abs(value));
                final int n4 = (int)throws1.b(super.Dla[j + 1]);
                final int n5 = (int)throws2.b(Math.abs(value2));
                if (value * value2 > 0.0) {
                    if (this.eka == 0) {
                        graphics.drawLine(n2, n3, n4, n5);
                    }
                    else {
                        continue.b(graphics, n2, n3, n4, n5, this.eka);
                    }
                    array[j + 1] = (array[j] = true);
                }
            }
            for (int k = max; k <= n; ++k) {
                if (!array[k]) {
                    graphics.fillOval((int)throws1.b(super.Dla[k]) - this.eka, (int)throws2.b(Math.abs(super.yla.getValue(k, 0))) - this.eka, 1 + this.eka * 2, 1 + this.eka * 2);
                }
            }
        }
    }
    
    public int x() {
        return this.eka;
    }
    
    public void h(final int eka) {
        this.eka = eka;
    }
}
