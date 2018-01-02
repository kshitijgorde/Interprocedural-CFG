// 
// Decompiled by Procyon v0.5.30
// 

public class wda extends public
{
    public wda(final int[] array, final b b) {
        super("FI - Force Index", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (h == null || i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        super.Woa[0][0] = 0.0;
        for (int j = 1; j < h.length; ++j) {
            super.Woa[0][j] = (h[j] - h[j - 1]) * i[j];
        }
        e.calculateExpAvg(super.Woa[0], super.Woa[0], 1, super.Oa[0]);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
