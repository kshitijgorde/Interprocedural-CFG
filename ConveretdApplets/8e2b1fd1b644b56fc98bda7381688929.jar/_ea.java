// 
// Decompiled by Procyon v0.5.30
// 

public class _ea extends public
{
    public _ea(final int[] array, final b b) {
        super("WAD2 - Williams Acc. Dist. (type 2)", 1, null, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 1;
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
        array[0] = _[0];
        for (int i = 1; i < array.length; ++i) {
            array[i] = Math.max(_[i], h[i - 1]);
        }
        final double[] array2 = new double[h.length];
        array2[0] = g[0];
        for (int j = 1; j < array2.length; ++j) {
            array2[j] = Math.min(g[j], h[j - 1]);
        }
        final double[] array3 = new double[h.length];
        array3[0] = 0.0;
        for (int k = 1; k < array.length; ++k) {
            if (h[k] > h[k - 1]) {
                array3[k] = h[k] - array2[k];
            }
            else if (h[k] < h[k - 1]) {
                array3[k] = h[k] - array[k];
            }
            else {
                array3[k] = 0.0;
            }
        }
        super.Woa[0][0] = 0.0;
        for (int l = 1; l < super.Woa[0].length; ++l) {
            super.Woa[0][l] = array3[l] + super.Woa[0][l - 1];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
