// 
// Decompiled by Procyon v0.5.30
// 

public class Ti extends implements
{
    public Ti(final int[] array, final class class1) {
        super("VR - Volatility Ratio", 1, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        if (a == null || b == null || _ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        array[0] = a[0] - b[0];
        for (int i = 1; i < _.length; ++i) {
            double n = a[i] - b[i];
            if (a[i] - _[i - 1] > n) {
                n = a[i] - _[i - 1];
            }
            if (_[i - 1] - b[i] > n) {
                n = _[i - 1] - b[i];
            }
            array[i] = n;
        }
        final double[] array2 = new double[a.length];
        do.calculateMax(a, array2, 0, super.Ua[0]);
        for (int j = super.Ua[0]; j < array2.length; ++j) {
            array2[j] = Math.max(array2[j], _[j - super.Ua[0]]);
        }
        final double[] array3 = new double[b.length];
        do.calculateMin(b, array3, 0, super.Ua[0]);
        for (int k = super.Ua[0]; k < array3.length; ++k) {
            array3[k] = Math.min(array3[k], _[k - super.Ua[0]]);
        }
        for (int l = 0; l < _.length; ++l) {
            final double n2 = array2[l] - array3[l];
            if (n2 > 0.0) {
                super.t[0][l] = array[l] / n2;
            }
            else {
                super.t[0][l] = 1.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
