// 
// Decompiled by Procyon v0.5.30
// 

public class ri extends implements
{
    public ri(final int[] array, final class class1) {
        super("FastK - %K Fast", 1, array, new double[] { array[1], array[2] }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
    }
    
    public void a(final int n, final int n2) {
        if (super.A != null && n > 0 && n - 1 < super.A.length) {
            super.A[n - 1] = n2;
        }
        super.a(n, n2);
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
        final double[] array = new double[a.length];
        final double[] array2 = new double[b.length];
        do.calculateMin(b, array2, 0, super.Ua[0]);
        do.calculateMax(a, array, 0, super.Ua[0]);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] - array2[i] != 0.0) {
                super.t[0][i] = 100.0 * (_[i] - array2[i]) / (array[i] - array2[i]);
            }
            else {
                super.t[0][i] = 100.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
