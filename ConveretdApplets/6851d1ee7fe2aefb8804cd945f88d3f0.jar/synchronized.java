import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class synchronized extends instanceof
{
    private double[] xla;
    
    public synchronized(final int n) {
        super(n, 3);
        this.xla = new double[3];
    }
    
    public boolean a(final double n, final double n2, final double n3, final double n4) {
        this.xla[0] = n2;
        this.xla[1] = n3;
        this.xla[2] = n4;
        return super.b(n, this.xla);
    }
    
    public void q() {
        super.Uka = Double.POSITIVE_INFINITY;
        super.Vka = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (super.aka >= 0 && u <= v) {
            for (int i = u; i <= v; ++i) {
                if (super.yla.getValue(i, 0) != 0.0) {
                    final double value = super.yla.getValue(i, 1);
                    super.Uka = Math.min(super.Uka, value);
                    super.Vka = Math.max(super.Vka, value);
                }
            }
        }
        super.zla = true;
    }
    
    public double b(final int n, final int n2) {
        return super.yla.getValue(n, 1);
    }
    
    public double _(final int n, final int n2) {
        if (super.Ala != null) {
            return super.Ala.getValue(n, 1);
        }
        return super.yla.getValue(n, 1);
    }
    
    public int w() {
        return 0;
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
        final int n2 = (int)throws2.m();
        final int n3 = (int)throws2.n();
        for (int i = max; i <= n; ++i) {
            if (super.yla.getValue(i, 0) == 1.0) {
                graphics.setColor(new Color((int)super.yla.getValue(i, 2)));
                transient.a(graphics, (int)throws1.b(super.Dla[i]), n2 + 3);
            }
            else if (super.yla.getValue(i, 0) == 2.0) {
                graphics.setColor(new Color((int)super.yla.getValue(i, 2)));
                transient.f(graphics, (int)throws1.b(super.Dla[i]), n3 - 3);
            }
        }
    }
}
