// 
// Decompiled by Procyon v0.5.30
// 

public class Vi extends implements
{
    public Vi(final int[] array, final class class1) {
        super("WAD1 - Williams Acc. Dist. (type 1)", 1, null, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 1;
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
        final double[] array = new double[_.length];
        array[0] = a[0];
        for (int i = 1; i < array.length; ++i) {
            array[i] = Math.max(a[i], _[i - 1]);
        }
        final double[] array2 = new double[_.length];
        array2[0] = b[0];
        for (int j = 1; j < array2.length; ++j) {
            array2[j] = Math.min(b[j], _[j - 1]);
        }
        final double[] array3 = new double[_.length];
        array3[0] = 0.0;
        for (int k = 1; k < array.length; ++k) {
            if (_[k] > _[k - 1]) {
                array3[k] = _[k] - array2[k];
            }
            else if (_[k] < _[k - 1]) {
                array3[k] = _[k] - array[k];
            }
            else {
                array3[k] = 0.0;
            }
        }
        super.t[0][0] = 0.0;
        for (int l = 1; l < super.t[0].length; ++l) {
            super.t[0][l] = array3[l] * f[l] + super.t[0][l - 1];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
