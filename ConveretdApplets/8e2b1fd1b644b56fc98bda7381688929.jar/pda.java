// 
// Decompiled by Procyon v0.5.30
// 

public class pda extends public
{
    public pda(final int[] array, final b b) {
        super("ChV - Chaikin Volatility", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] + super.Oa[1] - 1;
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        if (_ == null || g == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][_.length];
        final double[] array = new double[_.length];
        for (int i = 0; i < _.length; ++i) {
            array[i] = _[i] - g[i];
        }
        final double[] array2 = new double[_.length];
        e.calculateExpAvg(array, array2, 0, super.Oa[0]);
        for (int j = 0; j < super.Woa[0].length; ++j) {
            if (j < super.Oa[1]) {
                super.Woa[0][j] = 0.0;
            }
            else if (array2[j - super.Oa[1]] != 0.0) {
                super.Woa[0][j] = 100.0 * (array2[j] - array2[j - super.Oa[1]]) / array2[j - super.Oa[1]];
            }
            else {
                super.Woa[0][j] = 100.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
