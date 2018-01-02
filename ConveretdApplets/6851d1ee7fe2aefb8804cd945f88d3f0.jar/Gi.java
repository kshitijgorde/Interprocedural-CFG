// 
// Decompiled by Procyon v0.5.30
// 

public class Gi extends implements
{
    public Gi(final int[] array, final class class1) {
        super("PVT - Price and Volume Trend", 1, null, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (_ == null || f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        array[0] = 0.0;
        for (int i = 1; i < array.length; ++i) {
            if (_[i - 1] != 0.0) {
                array[i] = (_[i] - _[i - 1]) / _[i - 1];
            }
            else {
                array[i] = 0.0;
            }
        }
        super.t[0][0] = 0.0;
        for (int j = 1; j < super.t[0].length; ++j) {
            super.t[0][j] = array[j] * f[j] + super.t[0][j - 1];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
