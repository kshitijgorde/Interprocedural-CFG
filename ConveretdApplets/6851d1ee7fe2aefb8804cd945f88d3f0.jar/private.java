import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class private extends instanceof
{
    private boolean[] Gla;
    private double[] xla;
    private int eka;
    
    public private(final int n) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        this.Gla = new boolean[n];
        this.eka = 0;
    }
    
    public private(final int n, int eka) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        this.Gla = new boolean[n];
        if (eka < 0) {
            eka = 0;
        }
        this.eka = eka;
    }
    
    public boolean _(final double n, final double n2, final double n3) {
        if (super.aka < super.Dla.length - 1) {
            this.Gla[super.aka + 1] = (n2 > 0.0);
        }
        this.xla[0] = n3;
        return super.b(n, this.xla);
    }
    
    public void q() {
        super.Uka = Double.POSITIVE_INFINITY;
        super.Vka = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (super.aka >= 0 && u <= v) {
            for (int i = Math.max(u - 1, 0); i <= v; ++i) {
                for (int j = 0; j < super.yla.t(); ++j) {
                    final double value = super.yla.getValue(i, j);
                    if (value > 0.0) {
                        super.Uka = Math.min(super.Uka, value);
                        super.Vka = Math.max(super.Vka, value);
                    }
                }
            }
        }
        super.zla = true;
    }
    
    private int m(int n) {
        while (--n >= 0) {
            if (super.yla.getValue(n, 0) > 0.0) {
                return n;
            }
        }
        return -1;
    }
    
    private int _(final throws throws1, final int n, final int n2) {
        final int n3 = 1;
        final int n4 = 6;
        int max;
        if (throws1 instanceof try) {
            max = Math.max(Math.min((int)(((try)throws1).r() * 0.5), n4), n3);
        }
        else {
            double abs = Math.abs(Math.abs(super.Dla[this.v()]) - Math.abs(super.Dla[this.u()]));
            if (Math.abs(throws1.l()) - Math.abs(throws1.k()) != 0.0) {
                abs /= Math.abs(Math.abs(throws1.l()) - Math.abs(throws1.k()));
            }
            max = (int)(Math.max(n3, Math.min(n4, 0.25 * abs * Math.abs(throws1.n() - throws1.m()) / (n2 - n + 1.0))) + 0.5);
        }
        return max;
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
        final int _ = this._(throws1, max, n);
        final int n2 = 10;
        if (max == n) {
            int n3 = (int)throws1.b(super.Dla[max]);
            final double value = super.yla.getValue(max, 0);
            final int n4 = (int)throws2.b(value);
            final int m = this.m(max);
            if (m >= 0) {
                final int n5 = (int)throws2.b(super.yla.getValue(m, 0));
                if (this.eka == 0) {
                    graphics.drawLine(n3 - 10, n5, n3, n5);
                }
                else {
                    continue.b(graphics, n3 - 10, n5, n3, n5, this.eka);
                }
            }
            if (value > 0.0) {
                final Rectangle clipBounds = graphics.getClipBounds();
                int n6;
                if (clipBounds != null) {
                    n6 = clipBounds.x + clipBounds.width - 2;
                }
                else {
                    n6 = n3 + _ + n2;
                }
                if (this.Gla[max]) {
                    n3 += _;
                }
                if (this.eka == 0) {
                    graphics.drawLine(n3 + 5, n4, n6, n4);
                }
                else {
                    continue.b(graphics, n3 + _, n4, n6, n4, this.eka);
                }
            }
        }
        else if (max < n) {
            for (int i = max; i <= n; ++i) {
                final double value2 = super.yla.getValue(i, 0);
                int n7 = (int)throws1.b(super.Dla[i]);
                final int n8 = (int)throws2.b(value2);
                if (i == max) {
                    final int j = this.m(max);
                    if (j >= 0) {
                        final int n9 = (int)throws2.b(super.yla.getValue(j, 0));
                        if (this.eka == 0) {
                            graphics.drawLine(n7 - 10, n9, n7, n9);
                        }
                        else {
                            continue.b(graphics, n7 - 10, n9, n7, n9, this.eka);
                        }
                    }
                }
                int n10;
                if (i < n) {
                    n10 = (int)throws1.b(super.Dla[i + 1]);
                }
                else {
                    final Rectangle clipBounds2 = graphics.getClipBounds();
                    if (clipBounds2 != null) {
                        n10 = clipBounds2.x + clipBounds2.width - 2;
                    }
                    else {
                        n10 = n7 + _ + n2;
                    }
                }
                if (this.Gla[i]) {
                    n7 += _;
                }
                if (value2 > 0.0) {
                    if (this.eka == 0) {
                        graphics.drawLine(n7, n8, n10, n8);
                    }
                    else {
                        continue.b(graphics, n7, n8, n10, n8, this.eka);
                    }
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
