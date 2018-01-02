import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends p
{
    private double[] R;
    private Color mra;
    private Color nra;
    
    public v(final int n, final Color mra, final Color nra) {
        super(n, 2);
        this.R = new double[2];
        this.mra = Color.green;
        this.nra = Color.red;
        if (mra != null) {
            this.mra = mra;
        }
        if (nra != null) {
            this.nra = nra;
        }
    }
    
    public boolean b(final double n, final double n2, final double n3) {
        this.R[0] = n2;
        this.R[1] = n3;
        return super.b(n, this.R);
    }
    
    public void V() {
        super.era = Double.POSITIVE_INFINITY;
        super.dra = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (super.S >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                super.era = Math.min(super.era, super.X.getValue(i, 0));
                super.dra = Math.max(super.dra, super.X.getValue(i, 0));
            }
        }
        super.ora = true;
    }
    
    public void b(final Graphics graphics, final o o, final o o2) {
        if (super.S < 0) {
            return;
        }
        final int max = Math.max(super.U, this.b());
        int n = this._();
        if (super.V >= 0) {
            n = Math.min(n, super.V);
        }
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        for (int i = max; i <= n; ++i) {
            final int n2 = (int)o.b(super.W[i]);
            final int n3 = (int)o2.b(super.X.getValue(i, 0));
            final double value = super.X.getValue(i, 1);
            if (value > 0.0) {
                graphics.setColor(this.mra);
                final int n4 = (int)o2.k() - 1;
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
                graphics.setColor(this.nra);
                final int n5 = (int)o2.a() + 1;
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
