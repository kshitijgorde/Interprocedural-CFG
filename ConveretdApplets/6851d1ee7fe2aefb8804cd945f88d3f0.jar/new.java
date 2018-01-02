import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class new extends instanceof
{
    private double[] xla;
    private int Wla;
    private int Xla;
    private Color Zla;
    private Color _ma;
    
    public new(final int n, final int xla, final int wla) {
        super(n, 4);
        this.xla = new double[4];
        this.Wla = 6;
        this.Xla = 1;
        this.Zla = Color.green.darker();
        this._ma = Color.red;
        this.Xla = xla;
        this.Wla = wla;
    }
    
    public boolean _(final double n, final double n2, final double n3, final double n4, final double n5) {
        this.xla[0] = n2;
        this.xla[1] = n3;
        this.xla[2] = n4;
        this.xla[3] = n5;
        return super.b(n, this.xla);
    }
    
    public void b(final Graphics graphics, final throws throws1, final throws throws2) {
        if (super.aka < 0) {
            return;
        }
        final int max = Math.max(super.Bla, this.u());
        int n = this.v();
        if (super.Cla >= 0) {
            n = Math.min(n, super.Cla);
        }
        int max2;
        if (throws1 instanceof try) {
            max2 = Math.max(Math.min((int)(((try)throws1).r() * 0.45), this.Wla), this.Xla);
        }
        else {
            double abs = Math.abs(Math.abs(super.Dla[this.v()]) - Math.abs(super.Dla[this.u()]));
            if (Math.abs(throws1.l()) - Math.abs(throws1.k()) != 0.0) {
                abs /= Math.abs(Math.abs(throws1.l()) - Math.abs(throws1.k()));
            }
            max2 = (int)(Math.max(this.Xla, Math.min(this.Wla, 0.25 * abs * Math.abs(throws1.n() - throws1.m()) / (n - max + 1.0))) + 0.5);
        }
        final boolean equals = this._ma.equals(this.Zla);
        for (int i = max; i <= n; ++i) {
            final double value = super.yla.getValue(i, 0);
            final double value2 = super.yla.getValue(i, 3);
            if (value2 > value) {
                graphics.setColor(this.Zla);
            }
            else if (value2 < value) {
                graphics.setColor(this._ma);
            }
            else {
                graphics.setColor(super.xa);
            }
            final int n2 = (int)throws1.b(super.Dla[i]);
            final int n3 = (int)throws2.b(super.yla.getValue(i, 0));
            final int n4 = (int)throws2.b(super.yla.getValue(i, 1));
            final int n5 = (int)throws2.b(super.yla.getValue(i, 2));
            final int n6 = (int)throws2.b(super.yla.getValue(i, 3));
            graphics.drawLine(n2, n4, n2, Math.min(n3, n6));
            graphics.drawLine(n2, n5, n2, Math.max(n3, n6));
            if (n6 > n3) {
                graphics.fillRect(n2 - max2, n3, 2 * max2 + 1, n6 - n3 + 1);
            }
            else if (n6 < n3) {
                if (equals) {
                    graphics.drawRect(n2 - max2, n6, 2 * max2, n3 - n6);
                }
                else {
                    graphics.fillRect(n2 - max2, n6, 2 * max2 + 1, n3 - n6 + 1);
                }
            }
            else {
                graphics.drawLine(n2 - max2, n6, n2 + max2, n6);
            }
        }
    }
    
    public void a(final Color ma) {
        this._ma = ma;
    }
    
    public void b(final Color zla) {
        this.Zla = zla;
    }
}
