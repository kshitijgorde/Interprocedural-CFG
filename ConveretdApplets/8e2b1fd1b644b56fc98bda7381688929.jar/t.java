import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends p
{
    private double[] R;
    private int tra;
    
    public t(final int n) {
        super(n, 1);
        this.R = new double[1];
        this.tra = 0;
        this.tra = 0;
    }
    
    public t(final int n, int tra) {
        super(n, 1);
        this.R = new double[1];
        this.tra = 0;
        if (tra < 0) {
            tra = 0;
        }
        this.tra = tra;
    }
    
    public boolean a(final double n, final double n2) {
        this.R[0] = n2;
        return super.b(n, this.R);
    }
    
    public void V() {
        super.era = Double.POSITIVE_INFINITY;
        super.dra = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (super.S >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                for (int j = 0; j < super.X.V(); ++j) {
                    super.era = Math.min(super.era, Math.abs(super.X.getValue(i, j)));
                    super.dra = Math.max(super.dra, Math.abs(super.X.getValue(i, j)));
                }
            }
        }
        super.ora = true;
    }
    
    public double a(final int n, final int n2) {
        return Math.abs(super.X.getValue(n, n2));
    }
    
    public double b(final int n, final int n2) {
        if (super.ura != null) {
            return Math.abs(super.ura.getValue(n, n2));
        }
        return Math.abs(super.X.getValue(n, n2));
    }
    
    public void b(final Graphics graphics, final o o, final o o2) {
        if (super.S < 0) {
            return;
        }
        this._(graphics, o, o2);
        graphics.setColor(super.T);
        final int max = Math.max(super.U, this.b());
        int n = this._();
        if (super.V >= 0) {
            n = Math.min(n, super.V);
        }
        if (max == n) {
            graphics.fillOval((int)o.b(super.W[max]) - this.tra, (int)o2.b(Math.abs(super.X.getValue(max, 0))) - this.tra, 1 + this.tra * 2, 1 + this.tra * 2);
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
                final double value = super.X.getValue(j, 0);
                final double value2 = super.X.getValue(j + 1, 0);
                final int n2 = (int)o.b(super.W[j]);
                final int n3 = (int)o2.b(Math.abs(value));
                final int n4 = (int)o.b(super.W[j + 1]);
                final int n5 = (int)o2.b(Math.abs(value2));
                if (value * value2 > 0.0) {
                    if (this.tra == 0) {
                        graphics.drawLine(n2, n3, n4, n5);
                    }
                    else {
                        d._(graphics, n2, n3, n4, n5, this.tra);
                    }
                    array[j + 1] = (array[j] = true);
                }
            }
            for (int k = max; k <= n; ++k) {
                if (!array[k]) {
                    graphics.fillOval((int)o.b(super.W[k]) - this.tra, (int)o2.b(Math.abs(super.X.getValue(k, 0))) - this.tra, 1 + this.tra * 2, 1 + this.tra * 2);
                }
            }
        }
    }
    
    public int X() {
        return this.tra;
    }
    
    public void I(final int tra) {
        this.tra = tra;
    }
}
