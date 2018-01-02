import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class import extends instanceof
{
    private double[] xla;
    private int eka;
    
    public import(final int n) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        this.eka = 0;
    }
    
    public import(final int n, int eka) {
        super(n, 1);
        this.xla = new double[1];
        this.eka = 0;
        if (eka < 0) {
            eka = 0;
        }
        this.eka = eka;
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
        if (max == n) {
            graphics.fillOval((int)throws1.b(super.Dla[max]) - this.eka, (int)throws2.b(super.yla.getValue(max, 0)) - this.eka, 1 + this.eka * 2, 1 + this.eka * 2);
        }
        else if (max < n) {
            if (this.eka == 0) {
                final int[] array = new int[1 + n - max];
                final int[] array2 = new int[1 + n - max];
                for (int i = max; i <= n; ++i) {
                    array[i - max] = (int)throws1.b(super.Dla[i]);
                    array2[i - max] = (int)throws2.b(super.yla.getValue(i, 0));
                }
                graphics.drawPolyline(array, array2, array.length);
            }
            else {
                for (int j = max; j < n; ++j) {
                    continue.b(graphics, (int)throws1.b(super.Dla[j]), (int)throws2.b(super.yla.getValue(j, 0)), (int)throws1.b(super.Dla[j + 1]), (int)throws2.b(super.yla.getValue(j + 1, 0)), this.eka);
                }
            }
        }
    }
    
    public int x() {
        return this.eka;
    }
    
    public void h(final int eka) {
        this.eka = eka;
    }
}
