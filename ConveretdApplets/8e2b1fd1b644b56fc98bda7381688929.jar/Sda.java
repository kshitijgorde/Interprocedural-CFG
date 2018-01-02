// 
// Decompiled by Procyon v0.5.30
// 

public class Sda extends public
{
    public Sda(final int[] array, final b b) {
        super("TRD - Trend Deviation", 1, array, new double[] { 1.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        e.calculateSimpleAvg(h, super.Woa[0], 0, super.Oa[0]);
        for (int i = super.Uoa[0]; i < super.Woa[0].length; ++i) {
            if (super.Woa[0][i] > 0.0) {
                super.Woa[0][i] = h[i] / super.Woa[0][i];
            }
            else {
                super.Woa[0][i] = 1.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 100.0);
    }
}
