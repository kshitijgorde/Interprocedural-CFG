// 
// Decompiled by Procyon v0.5.30
// 

public class mi extends implements
{
    public mi(final int[] array, final class class1) {
        super("ChV - Chaikin Volatility", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] + super.Ua[1] - 1;
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        if (a == null || b == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][a.length];
        final double[] array = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            array[i] = a[i] - b[i];
        }
        final double[] array2 = new double[a.length];
        do.calculateExpAvg(array, array2, 0, super.Ua[0]);
        for (int j = 0; j < super.t[0].length; ++j) {
            if (j < super.Ua[1]) {
                super.t[0][j] = 0.0;
            }
            else if (array2[j - super.Ua[1]] != 0.0) {
                super.t[0][j] = 100.0 * (array2[j] - array2[j - super.Ua[1]]) / array2[j - super.Ua[1]];
            }
            else {
                super.t[0][j] = 100.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
