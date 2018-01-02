import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class hq extends fq
{
    private double[] za;
    private int c;
    
    public hq(final int n) {
        super(n, 1);
        this.za = new double[1];
        this.c = 0;
        this.c = 0;
    }
    
    public hq(final int n, int c) {
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
            graphics.fillOval((int)np.b(super.Ea[max]) - this.c, (int)np2.b(super.Fa.getValue(max, 0)) - this.c, 1 + this.c * 2, 1 + this.c * 2);
        }
        else if (max < n) {
            if (this.c == 0) {
                final int[] array = new int[1 + n - max];
                final int[] array2 = new int[1 + n - max];
                for (int i = max; i <= n; ++i) {
                    array[i - max] = (int)np.b(super.Ea[i]);
                    array2[i - max] = (int)np2.b(super.Fa.getValue(i, 0));
                }
                graphics.drawPolyline(array, array2, array.length);
            }
            else {
                for (int j = max; j < n; ++j) {
                    rp.b(graphics, (int)np.b(super.Ea[j]), (int)np2.b(super.Fa.getValue(j, 0)), (int)np.b(super.Ea[j + 1]), (int)np2.b(super.Fa.getValue(j + 1, 0)), this.c);
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
