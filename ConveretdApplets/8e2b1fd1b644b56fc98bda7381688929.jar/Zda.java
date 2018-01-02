// 
// Decompiled by Procyon v0.5.30
// 

public class Zda extends public
{
    public Zda(final int[] array, final b b) {
        super("WAD1 - Williams Acc. Dist. (type 1)", 1, null, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 1;
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (_ == null || g == null || h == null || i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = _[0];
        for (int j = 1; j < array.length; ++j) {
            array[j] = Math.max(_[j], h[j - 1]);
        }
        final double[] array2 = new double[h.length];
        array2[0] = g[0];
        for (int k = 1; k < array2.length; ++k) {
            array2[k] = Math.min(g[k], h[k - 1]);
        }
        final double[] array3 = new double[h.length];
        array3[0] = 0.0;
        for (int l = 1; l < array.length; ++l) {
            if (h[l] > h[l - 1]) {
                array3[l] = h[l] - array2[l];
            }
            else if (h[l] < h[l - 1]) {
                array3[l] = h[l] - array[l];
            }
            else {
                array3[l] = 0.0;
            }
        }
        super.Woa[0][0] = 0.0;
        for (int n = 1; n < super.Woa[0].length; ++n) {
            super.Woa[0][n] = array3[n] * i[n] + super.Woa[0][n - 1];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
