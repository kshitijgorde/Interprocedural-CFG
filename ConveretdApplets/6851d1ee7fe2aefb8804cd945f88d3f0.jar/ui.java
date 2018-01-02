import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class ui extends implements
{
    public ui(final int[] array, final class class1) {
        super("IMI - Intraday Momentum Index", 1, array, new double[] { array[1], array[2] }, class1);
        this.W();
    }
    
    public void a(final int n, final int n2) {
        if (super.A != null && n > 0 && n - 1 < super.A.length) {
            super.A[n - 1] = n2;
        }
        super.a(n, n2);
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
    }
    
    protected void X() {
        final double[] g = super.s.g();
        final double[] _ = super.s._();
        if (g == null || _ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        final double[] array2 = new double[_.length];
        for (int i = 0; i < _.length; ++i) {
            if (_[i] > g[i]) {
                array[i] = _[i] - g[i];
            }
            else {
                array[i] = 0.0;
            }
            if (_[i] < g[i]) {
                array2[i] = g[i] - _[i];
            }
            else {
                array2[i] = 0.0;
            }
        }
        do.calculateSimpleAvg(array, array, 0, super.Ua[0]);
        do.calculateSimpleAvg(array2, array2, 0, super.Ua[0]);
        for (int j = 0; j < _.length; ++j) {
            if (array[j] + array2[j] != 0.0) {
                super.t[0][j] = 100.0 * array[j] / (array[j] + array2[j]);
            }
            else {
                super.t[0][j] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[0], (double)super.Ua[2], super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = -1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], super.Ua[1], super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = 1;
        }
    }
}
