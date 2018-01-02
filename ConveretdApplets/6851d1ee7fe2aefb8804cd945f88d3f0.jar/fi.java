// 
// Decompiled by Procyon v0.5.30
// 

public class fi extends implements
{
    public fi(final int[] array, final class class1) {
        super("AD - Accumulation Distribution", 1, null, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 1;
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
        if (a[0] != b[0]) {
            super.t[0][0] = f[0] * (_[0] - b[0] - (a[0] - _[0])) / (a[0] - b[0]);
        }
        else {
            super.t[0][0] = 0.0;
        }
        for (int i = 1; i < _.length; ++i) {
            if (a[i] != b[i]) {
                super.t[0][i] = super.t[0][i - 1] + f[i] * (_[i] - b[i] - (a[i] - _[i])) / (a[i] - b[i]);
            }
            else {
                super.t[0][i] = super.t[0][i - 1];
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
