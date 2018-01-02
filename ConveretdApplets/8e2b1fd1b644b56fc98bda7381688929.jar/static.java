// 
// Decompiled by Procyon v0.5.30
// 

public class static extends public
{
    public static(final int[] array, final b b) {
        super("AD - Accumulation Distribution", 1, null, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 1;
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
        if (_[0] != g[0]) {
            super.Woa[0][0] = i[0] * (h[0] - g[0] - (_[0] - h[0])) / (_[0] - g[0]);
        }
        else {
            super.Woa[0][0] = 0.0;
        }
        for (int j = 1; j < h.length; ++j) {
            if (_[j] != g[j]) {
                super.Woa[0][j] = super.Woa[0][j - 1] + i[j] * (h[j] - g[j] - (_[j] - h[j])) / (_[j] - g[j]);
            }
            else {
                super.Woa[0][j] = super.Woa[0][j - 1];
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
