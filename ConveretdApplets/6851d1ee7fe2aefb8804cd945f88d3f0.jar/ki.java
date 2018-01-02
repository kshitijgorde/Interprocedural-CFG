// 
// Decompiled by Procyon v0.5.30
// 

public class ki extends implements
{
    public ki(final int[] array, final class class1) {
        super("ChMF - Chaikin Money Flow", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (a == null || b == null || _ == null || f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        for (int i = 0; i < _.length; ++i) {
            if (a[i] - b[i] != 0.0) {
                super.t[0][i] = f[i] * (_[i] - b[i] - (a[i] - _[i])) / (a[i] - b[i]);
            }
            else {
                super.t[0][i] = 0.0;
            }
        }
        do.a(super.t[0], super.t[0], 0, super.Ua[0]);
        final double[] array = new double[f.length];
        do.a(f, array, 0, super.Ua[0]);
        for (int j = 0; j < _.length; ++j) {
            if (array[j] > 0.0) {
                super.t[0][j] /= array[j];
            }
            else {
                super.t[0][j] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
