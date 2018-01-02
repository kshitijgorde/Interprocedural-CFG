// 
// Decompiled by Procyon v0.5.30
// 

public class Xda extends public
{
    public Xda(final int[] array, final b b) {
        super("VR - Volatility Ratio", 1, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        if (_ == null || g == null || h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = _[0] - g[0];
        for (int i = 1; i < h.length; ++i) {
            double n = _[i] - g[i];
            if (_[i] - h[i - 1] > n) {
                n = _[i] - h[i - 1];
            }
            if (h[i - 1] - g[i] > n) {
                n = h[i - 1] - g[i];
            }
            array[i] = n;
        }
        final double[] array2 = new double[_.length];
        e.calculateMax(_, array2, 0, super.Oa[0]);
        for (int j = super.Oa[0]; j < array2.length; ++j) {
            array2[j] = Math.max(array2[j], h[j - super.Oa[0]]);
        }
        final double[] array3 = new double[g.length];
        e.calculateMin(g, array3, 0, super.Oa[0]);
        for (int k = super.Oa[0]; k < array3.length; ++k) {
            array3[k] = Math.min(array3[k], h[k - super.Oa[0]]);
        }
        for (int l = 0; l < h.length; ++l) {
            final double n2 = array2[l] - array3[l];
            if (n2 > 0.0) {
                super.Woa[0][l] = array[l] / n2;
            }
            else {
                super.Woa[0][l] = 1.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
