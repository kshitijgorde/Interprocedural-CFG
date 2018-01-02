// 
// Decompiled by Procyon v0.5.30
// 

public class li extends implements
{
    public li(final int[] array, final class class1) {
        super("ChO - Chaikin Oscillator", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[1];
    }
    
    protected void X() {
        final double[] g = super.s.g();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (g == null || a == null || b == null || _ == null || f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        array[0] = 0.0;
        for (int i = 1; i < _.length; ++i) {
            if (a[i] > b[i]) {
                array[i] = array[i - 1] + f[i] * (_[i] - g[i]) / (a[i] - b[i]);
            }
            else {
                array[i] = array[i - 1];
            }
        }
        do.calculateExpAvg(array, super.t[0], 0, super.Ua[0]);
        do.calculateExpAvg(array, array, 0, super.Ua[1]);
        for (int j = 0; j < _.length; ++j) {
            final double[] array2 = super.t[0];
            final int n = j;
            array2[n] -= array[j];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
