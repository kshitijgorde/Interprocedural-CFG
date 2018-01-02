// 
// Decompiled by Procyon v0.5.30
// 

public class nda extends public
{
    public nda(final int[] array, final b b) {
        super("ChMF - Chaikin Money Flow", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
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
        for (int j = 0; j < h.length; ++j) {
            if (_[j] - g[j] != 0.0) {
                super.Woa[0][j] = i[j] * (h[j] - g[j] - (_[j] - h[j])) / (_[j] - g[j]);
            }
            else {
                super.Woa[0][j] = 0.0;
            }
        }
        e._(super.Woa[0], super.Woa[0], 0, super.Oa[0]);
        final double[] array = new double[i.length];
        e._(i, array, 0, super.Oa[0]);
        for (int k = 0; k < h.length; ++k) {
            if (array[k] > 0.0) {
                super.Woa[0][k] /= array[k];
            }
            else {
                super.Woa[0][k] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
