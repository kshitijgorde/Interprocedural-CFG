import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class iq extends fq
{
    private double[] za;
    private int c;
    
    public iq(final int n) {
        super(n, 1);
        this.za = new double[1];
        this.c = 0;
        this.c = 0;
    }
    
    public iq(final int n, int c) {
        super(n, 1);
        this.za = new double[1];
        this.c = 0;
        if (c < 0) {
            c = 0;
        }
        this.c = c;
    }
    
    public boolean _(final double n, final double n2) {
        this.za[0] = n2;
        return super.a(n, this.za);
    }
    
    public void h() {
        super.mb = Double.POSITIVE_INFINITY;
        super.nb = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (super.Aa >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                for (int j = 0; j < super.Fa.c(); ++j) {
                    super.mb = Math.min(super.mb, Math.abs(super.Fa.getValue(i, j)));
                    super.nb = Math.max(super.nb, Math.abs(super.Fa.getValue(i, j)));
                }
            }
        }
        super.sb = true;
    }
    
    public double a(final int n, final int n2) {
        return Math.abs(super.Fa.getValue(n, n2));
    }
    
    public double b(final int n, final int n2) {
        if (super.d != null) {
            return Math.abs(super.d.getValue(n, n2));
        }
        return Math.abs(super.Fa.getValue(n, n2));
    }
    
    public void b(final Graphics graphics, final Np np, final Np np2) {
        if (super.Aa < 0) {
            return;
        }
        this._(graphics, np, np2);
        graphics.setColor(super.Ba);
        final int max = Math.max(super.Ca, this.b());
        int n = this._();
        if (super.Da >= 0) {
            n = Math.min(n, super.Da);
        }
        if (max == n) {
            graphics.fillOval((int)np.b(super.Ea[max]) - this.c, (int)np2.b(Math.abs(super.Fa.getValue(max, 0))) - this.c, 1 + this.c * 2, 1 + this.c * 2);
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
                final double value = super.Fa.getValue(j, 0);
                final double value2 = super.Fa.getValue(j + 1, 0);
                final int n2 = (int)np.b(super.Ea[j]);
                final int n3 = (int)np2.b(Math.abs(value));
                final int n4 = (int)np.b(super.Ea[j + 1]);
                final int n5 = (int)np2.b(Math.abs(value2));
                if (value * value2 > 0.0) {
                    if (this.c == 0) {
                        graphics.drawLine(n2, n3, n4, n5);
                    }
                    else {
                        rp.b(graphics, n2, n3, n4, n5, this.c);
                    }
                    array[j + 1] = (array[j] = true);
                }
            }
            for (int k = max; k <= n; ++k) {
                if (!array[k]) {
                    graphics.fillOval((int)np.b(super.Ea[k]) - this.c, (int)np2.b(Math.abs(super.Fa.getValue(k, 0))) - this.c, 1 + this.c * 2, 1 + this.c * 2);
                }
            }
        }
    }
    
    public int e() {
        return this.c;
    }
    
    public void k(final int c) {
        this.c = c;
    }
}
