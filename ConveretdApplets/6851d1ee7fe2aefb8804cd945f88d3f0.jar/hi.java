// 
// Decompiled by Procyon v0.5.30
// 

public class hi extends implements
{
    public hi(final int[] array, final class class1) {
        super("BOP - Balance Of Power", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] g = super.s.g();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        if (g == null || a == null || b == null || _ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        for (int i = 0; i < _.length; ++i) {
            final double n = a[i] - b[i];
            if (n != 0.0) {
                super.t[0][i] = (_[i] - g[i]) / n;
            }
            else {
                super.t[0][i] = 0.0;
            }
        }
        do.calculateSimpleAvg(super.t[0], super.t[0], 0, super.Ua[0]);
        do._(super.t[0], super.r[0], 0.0);
    }
}
