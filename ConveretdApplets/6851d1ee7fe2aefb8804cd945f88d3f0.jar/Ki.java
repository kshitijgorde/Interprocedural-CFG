// 
// Decompiled by Procyon v0.5.30
// 

public class Ki extends implements
{
    public Ki(final int[] array, final class class1) {
        super("RVI - Relative Volatility Index", 1, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] * 2 + super.Ua[1];
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        if (_ == null || a == null || b == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        do.calculateStdDev(_, array, 0, super.Ua[1]);
        final double[] array2 = new double[_.length];
        final double[] array3 = new double[_.length];
        array2[0] = (array3[0] = 0.0);
        for (int i = 1; i < _.length; ++i) {
            if (a[i] > a[i - 1]) {
                array2[i] = array[i];
            }
            else {
                array2[i] = 0.0;
            }
            if (b[i] < b[i - 1]) {
                array3[i] = array[i];
            }
            else {
                array3[i] = 0.0;
            }
        }
        do.calculateExpAvg(array2, array2, 1, super.Ua[0] * 2 - 1);
        do.calculateExpAvg(array3, array3, 1, super.Ua[0] * 2 - 1);
        for (int j = 0; j < _.length; ++j) {
            if (array2[j] + array3[j] != 0.0) {
                super.t[0][j] = 100.0 * array2[j] / (array2[j] + array3[j]);
            }
            else {
                super.t[0][j] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
