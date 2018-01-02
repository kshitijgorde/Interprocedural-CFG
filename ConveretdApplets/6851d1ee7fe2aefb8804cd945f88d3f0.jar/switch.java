import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class switch extends instanceof
{
    private double[] xla;
    private Color Ela;
    private Color Fla;
    
    public switch(final int n, final Color ela, final Color fla) {
        super(n, 2);
        this.xla = new double[2];
        this.Ela = Color.green;
        this.Fla = Color.red;
        if (ela != null) {
            this.Ela = ela;
        }
        if (fla != null) {
            this.Fla = fla;
        }
    }
    
    public boolean _(final double n, final double n2, final double n3) {
        this.xla[0] = n2;
        this.xla[1] = n3;
        return super.b(n, this.xla);
    }
    
    public void q() {
        super.Uka = Double.POSITIVE_INFINITY;
        super.Vka = Double.NEGATIVE_INFINITY;
        final int u = this.u();
        final int v = this.v();
        if (super.aka >= 0 && u <= v) {
            for (int i = u; i <= v; ++i) {
                super.Uka = Math.min(super.Uka, super.yla.getValue(i, 0));
                super.Vka = Math.max(super.Vka, super.yla.getValue(i, 0));
            }
        }
        super.zla = true;
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
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        for (int i = max; i <= n; ++i) {
            final int n2 = (int)throws1.b(super.Dla[i]);
            final int n3 = (int)throws2.b(super.yla.getValue(i, 0));
            final double value = super.yla.getValue(i, 1);
            if (value > 0.0) {
                graphics.setColor(this.Ela);
                final int n4 = (int)throws2.m() - 1;
                array[0] = n2 - 3;
                array[1] = n2 + 3;
                array[2] = n2;
                array2[0] = n4;
                array2[2] = (array2[1] = n4) - 7;
                graphics.drawPolygon(array, array2, array.length);
                graphics.fillPolygon(array, array2, array.length);
                graphics.drawLine(n2, n4 - 7, n2, n3);
            }
            else if (value < 0.0) {
                graphics.setColor(this.Fla);
                final int n5 = (int)throws2.n() + 1;
                array[0] = n2 - 3;
                array[1] = n2 + 3;
                array[2] = n2;
                array2[0] = n5;
                array2[2] = (array2[1] = n5) + 7;
                graphics.drawPolygon(array, array2, array.length);
                graphics.fillPolygon(array, array2, array.length);
                graphics.drawLine(n2, n5 + 7, n2, n3);
            }
        }
    }
}
