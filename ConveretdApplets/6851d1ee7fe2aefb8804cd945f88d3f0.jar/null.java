import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class null extends instanceof
{
    private int Wla;
    private int Xla;
    private boolean Yla;
    private double[] xla;
    
    public null(final int n, final int xla, final int wla, final boolean yla) {
        super(n, 1);
        this.Wla = 5;
        this.Xla = 0;
        this.Yla = false;
        this.xla = new double[1];
        this.Xla = xla;
        this.Wla = wla;
        this.Yla = yla;
    }
    
    public boolean a(final double n, final double n2) {
        this.xla[0] = n2;
        return super.b(n, this.xla);
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
        final int n2 = (int)throws2.b(0.0);
        int max2;
        if (throws1 instanceof try) {
            max2 = Math.max(Math.min((int)(((try)throws1).r() * 0.45 * (this.Yla ? 2 : 1)), this.Wla), this.Xla);
        }
        else {
            max2 = 0;
        }
        for (int i = max; i <= n; ++i) {
            final int n3 = (int)throws1.b(super.Dla[i]);
            if (super.yla.getValue(i, 0) != 0.0) {
                final int n4 = (int)throws2.b(super.yla.getValue(i, 0));
                graphics.fillRect(n3 - max2 / 2, Math.min(n4, n2), max2 + 1, Math.abs(n2 - n4));
            }
        }
    }
}
