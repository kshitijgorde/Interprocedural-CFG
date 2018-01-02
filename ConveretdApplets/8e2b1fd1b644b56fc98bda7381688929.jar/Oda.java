// 
// Decompiled by Procyon v0.5.30
// 

public class Oda extends public
{
    public Oda(final int[] array, final b b) {
        super("RVI - Relative Volatility Index", 1, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] * 2 + super.Oa[1];
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        if (h == null || _ == null || g == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        e.calculateStdDev(h, array, 0, super.Oa[1]);
        final double[] array2 = new double[h.length];
        final double[] array3 = new double[h.length];
        array2[0] = (array3[0] = 0.0);
        for (int i = 1; i < h.length; ++i) {
            if (_[i] > _[i - 1]) {
                array2[i] = array[i];
            }
            else {
                array2[i] = 0.0;
            }
            if (g[i] < g[i - 1]) {
                array3[i] = array[i];
            }
            else {
                array3[i] = 0.0;
            }
        }
        e.calculateExpAvg(array2, array2, 1, super.Oa[0] * 2 - 1);
        e.calculateExpAvg(array3, array3, 1, super.Oa[0] * 2 - 1);
        for (int j = 0; j < h.length; ++j) {
            if (array2[j] + array3[j] != 0.0) {
                super.Woa[0][j] = 100.0 * array2[j] / (array2[j] + array3[j]);
            }
            else {
                super.Woa[0][j] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
