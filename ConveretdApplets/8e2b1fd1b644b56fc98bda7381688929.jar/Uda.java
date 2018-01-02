// 
// Decompiled by Procyon v0.5.30
// 

public class Uda extends public
{
    public Uda(final int[] array, final b b) {
        super("BOS - Bollinger Oscillator", 1, array, new double[] { 2.0, -2.0 }, b);
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
        final double[] array = new double[h.length];
        final double[] array2 = new double[h.length];
        e.calculateSimpleAvg(h, array, 0, super.Oa[0]);
        e.calculateStdDev(h, array2, 0, super.Oa[0]);
        for (int i = super.Uoa[0]; i < super.Woa[0].length; ++i) {
            if (array2[i] > 0.0) {
                super.Woa[0][i] = 1.0 * (h[i] - array[i]) / array2[i];
            }
            else {
                super.Woa[0][i] = 1.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 1.0);
    }
}
