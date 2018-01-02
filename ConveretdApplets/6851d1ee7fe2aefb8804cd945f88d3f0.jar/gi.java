// 
// Decompiled by Procyon v0.5.30
// 

public class gi extends implements
{
    public gi(final int[] array, final class class1) {
        super("ATR - Average True Range", 1, array, null, class1);
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
        super.t[0][0] = a[0] - b[0];
        for (int i = 1; i < _.length; ++i) {
            double n = a[i] - b[i];
            if (a[i] - _[i - 1] > n) {
                n = a[i] - _[i - 1];
            }
            if (_[i - 1] - b[i] > n) {
                n = _[i - 1] - b[i];
            }
            super.t[0][i] = n;
        }
        for (int j = 1; j < super.t[0].length; ++j) {
            super.t[0][j] = ((super.Ua[0] - 1) * super.t[0][j - 1] + super.t[0][j]) / super.Ua[0];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
