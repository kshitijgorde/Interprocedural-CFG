// 
// Decompiled by Procyon v0.5.30
// 

public class Dda extends public
{
    public Dda(final int[] array, final b b) {
        super("MTM1 - Momentum (type 1)", 2, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
        super.Uoa[1] = super.Oa[0] + super.Oa[1] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        for (int i = super.Oa[0]; i < super.Woa[0].length; ++i) {
            super.Woa[0][i] = h[i] - h[i - super.Oa[0]];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Uoa[0], super.Oa[1]);
    }
}
