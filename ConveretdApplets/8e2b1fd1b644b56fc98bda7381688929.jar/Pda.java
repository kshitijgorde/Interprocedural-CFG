// 
// Decompiled by Procyon v0.5.30
// 

public class Pda extends public
{
    public Pda(final int[] array, final b b) {
        super("SlowD - %D Slow", 1, array, new double[] { 20.0, 80.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1 + super.Oa[1] - 1 + super.Oa[2] - 1;
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
        final double[] array = new double[_.length];
        final double[] array2 = new double[g.length];
        e.calculateMin(g, array2, 0, super.Oa[0]);
        e.calculateMax(_, array, 0, super.Oa[0]);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] - array2[i] != 0.0) {
                super.Woa[0][i] = 100.0 * (h[i] - array2[i]) / (array[i] - array2[i]);
            }
            else {
                super.Woa[0][i] = 100.0;
            }
        }
        e.calculateSimpleAvg(super.Woa[0], super.Woa[0], super.Oa[0] - 1, super.Oa[1]);
        e.calculateSimpleAvg(super.Woa[0], super.Woa[0], super.Oa[0] - 1 + super.Oa[1] - 1, super.Oa[2]);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
