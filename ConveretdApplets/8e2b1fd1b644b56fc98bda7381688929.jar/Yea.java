import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Yea extends p
{
    double[] R;
    private int P;
    private int Q;
    
    public Yea(final int n) {
        super(n, 4);
        this.R = new double[4];
        this.P = 6;
        this.Q = 0;
    }
    
    public boolean a(final double n, final double n2, final double n3, final double n4, final double n5) {
        this.R[0] = n2;
        this.R[1] = n3;
        this.R[2] = n4;
        this.R[3] = n5;
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
        int max2;
        if (o instanceof u) {
            max2 = Math.max(Math.min((int)(((u)o).a() * 0.45), this.P), this.Q);
        }
        else {
            double abs = Math.abs(Math.abs(super.W[this._()]) - Math.abs(super.W[this.b()]));
            if (Math.abs(o.b()) - Math.abs(o._()) != 0.0) {
                abs /= Math.abs(Math.abs(o.b()) - Math.abs(o._()));
            }
            max2 = (int)(Math.max(this.Q, Math.min(this.P, 0.25 * abs * Math.abs(o.a() - o.k()) / (n - max + 1.0))) + 0.5);
        }
        for (int i = max; i <= n; ++i) {
            final int n2 = (int)o.b(super.W[i]);
            final int n3 = (int)o2.b(super.X.getValue(i, 0));
            final int n4 = (int)o2.b(super.X.getValue(i, 1));
            final int n5 = (int)o2.b(super.X.getValue(i, 2));
            final int n6 = (int)o2.b(super.X.getValue(i, 3));
            graphics.drawLine(n2 - max2, n3, n2, n3);
            graphics.drawLine(n2, n6, n2 + max2, n6);
            graphics.drawLine(n2, n4, n2, n5);
        }
    }
}
