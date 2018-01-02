import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class yq extends fq
{
    private int xa;
    private int ya;
    private double[] za;
    
    public yq(final int n) {
        super(n, 1);
        this.xa = 5;
        this.ya = 0;
        this.za = new double[1];
    }
    
    public boolean _(final double n, final double n2) {
        this.za[0] = n2;
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
        final int n2 = (int)np2.b(0.0);
        int max2;
        if (np instanceof jq) {
            max2 = Math.max(Math.min((int)(((jq)np).a() * 0.5), this.xa), this.ya);
        }
        else {
            max2 = 0;
        }
        for (int i = max; i <= n; ++i) {
            final int n3 = (int)np.b(super.Ea[i]);
            if (super.Fa.getValue(i, 0) != 0.0) {
                final int n4 = (int)np2.b(super.Fa.getValue(i, 0));
                graphics.fillRect(n3 - max2 / 2, Math.min(n4, n2), max2 + 1, Math.abs(n2 - n4));
            }
        }
    }
}
