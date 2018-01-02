// 
// Decompiled by Procyon v0.5.30
// 

public class kda extends public
{
    public kda(final int[] array, final b b) {
        super("BOP - Balance Of Power", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] b = super.Voa.b();
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        final double[] h = super.Voa.h();
        if (b == null || _ == null || g == null || h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        for (int i = 0; i < h.length; ++i) {
            final double n = _[i] - g[i];
            if (n != 0.0) {
                super.Woa[0][i] = (h[i] - b[i]) / n;
            }
            else {
                super.Woa[0][i] = 0.0;
            }
        }
        e.calculateSimpleAvg(super.Woa[0], super.Woa[0], 0, super.Oa[0]);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
