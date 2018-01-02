import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class kq extends fq
{
    private double[] za;
    private Color qb;
    private Color rb;
    
    public kq(final int n, final Color qb, final Color rb) {
        super(n, 2);
        this.za = new double[2];
        this.qb = Color.green;
        this.rb = Color.red;
        if (qb != null) {
            this.qb = qb;
        }
        if (rb != null) {
            this.rb = rb;
        }
    }
    
    public boolean a(final double n, final double n2, final double n3) {
        this.za[0] = n2;
        this.za[1] = n3;
        return super.a(n, this.za);
    }
    
    public void h() {
        super.mb = Double.POSITIVE_INFINITY;
        super.nb = Double.NEGATIVE_INFINITY;
        final int b = this.b();
        final int _ = this._();
        if (super.Aa >= 0 && b <= _) {
            for (int i = b; i <= _; ++i) {
                super.mb = Math.min(super.mb, super.Fa.getValue(i, 0));
                super.nb = Math.max(super.nb, super.Fa.getValue(i, 0));
            }
        }
        super.sb = true;
    }
    
    public void b(final Graphics graphics, final Np np, final Np np2) {
        if (super.Aa < 0) {
            return;
        }
        final int max = Math.max(super.Ca, this.b());
        int n = this._();
        if (super.Da >= 0) {
            n = Math.min(n, super.Da);
        }
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        for (int i = max; i <= n; ++i) {
            final int n2 = (int)np.b(super.Ea[i]);
            final int n3 = (int)np2.b(super.Fa.getValue(i, 0));
            final double value = super.Fa.getValue(i, 1);
            if (value > 0.0) {
                graphics.setColor(this.qb);
                final int n4 = (int)np2.l() - 1;
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
                graphics.setColor(this.rb);
                final int n5 = (int)np2.a() + 1;
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
