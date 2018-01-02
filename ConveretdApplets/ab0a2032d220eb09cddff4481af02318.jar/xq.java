import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class xq extends fq
{
    private double[] za;
    private int xa;
    private int ya;
    
    public xq(final int n) {
        super(n, 4);
        this.za = new double[4];
        this.xa = 6;
        this.ya = 1;
    }
    
    public boolean a(final double n, final double n2, final double n3, final double n4, final double n5) {
        this.za[0] = n2;
        this.za[1] = n3;
        this.za[2] = n4;
        this.za[3] = n5;
        return super.a(n, this.za);
    }
    
    public void b(final Graphics graphics, final Np np, final Np np2) {
        if (super.Aa < 0) {
            return;
        }
        graphics.setColor(super.Ba);
        final int max = Math.max(super.Ca, this.b());
        int n = this._();
        if (super.Da >= 0) {
            n = Math.min(n, super.Da);
        }
        int max2;
        if (np instanceof jq) {
            max2 = Math.max(Math.min((int)(((jq)np).a() * 0.45), this.xa), this.ya);
        }
        else {
            double abs = Math.abs(Math.abs(super.Ea[this._()]) - Math.abs(super.Ea[this.b()]));
            if (Math.abs(np.b()) - Math.abs(np._()) != 0.0) {
                abs /= Math.abs(Math.abs(np.b()) - Math.abs(np._()));
            }
            max2 = (int)(Math.max(this.ya, Math.min(this.xa, 0.25 * abs * Math.abs(np.a() - np.l()) / (n - max + 1.0))) + 0.5);
        }
        for (int i = max; i <= n; ++i) {
            final int n2 = (int)np.b(super.Ea[i]);
            final int n3 = (int)np2.b(super.Fa.getValue(i, 0));
            final int n4 = (int)np2.b(super.Fa.getValue(i, 1));
            final int n5 = (int)np2.b(super.Fa.getValue(i, 2));
            final int n6 = (int)np2.b(super.Fa.getValue(i, 3));
            graphics.drawLine(n2, n4, n2, Math.min(n3, n6));
            graphics.drawLine(n2, n5, n2, Math.max(n3, n6));
            if (n6 > n3) {
                graphics.fillRect(n2 - max2, n3, 2 * max2 + 1, n6 - n3 + 1);
            }
            else if (n6 < n3) {
                graphics.drawRect(n2 - max2, n6, 2 * max2, n3 - n6);
            }
            else {
                graphics.drawLine(n2 - max2, n6, n2 + max2, n6);
            }
        }
    }
}
