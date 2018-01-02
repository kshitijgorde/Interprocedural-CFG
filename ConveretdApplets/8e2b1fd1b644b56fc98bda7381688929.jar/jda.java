// 
// Decompiled by Procyon v0.5.30
// 

public class jda extends public
{
    public jda(final int[] array, final b b) {
        super("ATR - Average True Range", 1, array, null, b);
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
        super.Woa[0][0] = _[0] - g[0];
        for (int i = 1; i < h.length; ++i) {
            double n = _[i] - g[i];
            if (_[i] - h[i - 1] > n) {
                n = _[i] - h[i - 1];
            }
            if (h[i - 1] - g[i] > n) {
                n = h[i - 1] - g[i];
            }
            super.Woa[0][i] = n;
        }
        for (int j = 1; j < super.Woa[0].length; ++j) {
            super.Woa[0][j] = ((super.Oa[0] - 1) * super.Woa[0][j - 1] + super.Woa[0][j]) / super.Oa[0];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
