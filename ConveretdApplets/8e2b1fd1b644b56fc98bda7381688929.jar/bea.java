// 
// Decompiled by Procyon v0.5.30
// 

public class bea extends public
{
    public bea(final String s, final int[] array, final b b) {
        super(s, 2, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
        super.Uoa[1] = super.Oa[0] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        final double[] array2 = new double[h.length];
        e.calculateSimpleAvg(h, array, 0, super.Oa[0]);
        e.calculateStdDev(h, array2, 0, super.Oa[0]);
        for (int i = super.Uoa[0]; i < super.Woa[0].length; ++i) {
            super.Woa[0][i] = array[i] - array2[i] * super.Oa[1];
            super.Woa[1][i] = array[i] + array2[i] * super.Oa[1];
            if (super.Woa[0][i] < 0.0) {
                super.Woa[0][i] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], h[0]);
        e.a(super.Woa[1], super.Uoa[0], h[0]);
    }
}
