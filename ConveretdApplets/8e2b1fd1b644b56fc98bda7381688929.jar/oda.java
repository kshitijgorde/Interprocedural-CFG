// 
// Decompiled by Procyon v0.5.30
// 

public class oda extends public
{
    public oda(final int[] array, final b b) {
        super("ChO - Chaikin Oscillator", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[1];
    }
    
    protected void H() {
        final double[] b = super.Voa.b();
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (b == null || _ == null || g == null || h == null || i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = 0.0;
        for (int j = 1; j < h.length; ++j) {
            if (_[j] > g[j]) {
                array[j] = array[j - 1] + i[j] * (h[j] - b[j]) / (_[j] - g[j]);
            }
            else {
                array[j] = array[j - 1];
            }
        }
        e.calculateExpAvg(array, super.Woa[0], 0, super.Oa[0]);
        e.calculateExpAvg(array, array, 0, super.Oa[1]);
        for (int k = 0; k < h.length; ++k) {
            final double[] array2 = super.Woa[0];
            final int n = k;
            array2[n] -= array[k];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
