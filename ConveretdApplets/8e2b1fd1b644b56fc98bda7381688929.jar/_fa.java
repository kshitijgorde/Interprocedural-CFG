import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class _fa extends p
{
    private int P;
    private int Q;
    private double[] R;
    
    public _fa(final int n) {
        super(n, 1);
        this.P = 5;
        this.Q = 0;
        this.R = new double[1];
    }
    
    public boolean a(final double n, final double n2) {
        this.R[0] = n2;
        return super.b(n, this.R);
    }
    
    public void b(final Graphics graphics, final o o, final o o2) {
        if (super.S < 0) {
            return;
        }
        graphics.setColor(super.T);
        final int max = Math.max(super.U, this.b());
        int n = this._();
        if (super.V >= 0) {
            n = Math.min(n, super.V);
        }
        final int n2 = (int)o2.b(0.0);
        int max2;
        if (o instanceof u) {
            max2 = Math.max(Math.min((int)(((u)o).a() * 0.5), this.P), this.Q);
        }
        else {
            max2 = 0;
        }
        for (int i = max; i <= n; ++i) {
            final int n3 = (int)o.b(super.W[i]);
            if (super.X.getValue(i, 0) != 0.0) {
                final int n4 = (int)o2.b(super.X.getValue(i, 0));
                graphics.fillRect(n3 - max2 / 2, Math.min(n4, n2), max2 + 1, Math.abs(n2 - n4));
            }
        }
    }
}
