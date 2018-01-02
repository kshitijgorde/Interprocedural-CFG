import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class xi extends implements
{
    public xi(final int[] array, final class class1) {
        super("MFI - Money Flow Index", 1, array, new double[] { array[1], array[2] }, class1);
        this.W();
    }
    
    public void a(final int n, final int n2) {
        if (super.A != null && n > 0 && n - 1 < super.A.length) {
            super.A[n - 1] = n2;
        }
        super.a(n, n2);
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (a == null || b == null || _ == null || f == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (a[i] + b[i] + _[i]) / 3.0;
        }
        final double[] array2 = new double[_.length];
        for (int j = 0; j < array.length; ++j) {
            array2[j] = array[j] * f[j];
        }
        for (int k = super.Ua[0]; k < super.t[0].length; ++k) {
            double n2;
            double n = n2 = 0.0;
            for (int l = k - (super.Ua[0] - 1); l <= k; ++l) {
                if (array[l] > array[l - 1]) {
                    n2 += array2[l];
                }
                else if (array[l] < array[l - 1]) {
                    n += array2[l];
                }
            }
            double n3;
            if (n != 0.0) {
                n3 = n2 / n;
            }
            else {
                n3 = 0.0;
            }
            if (1.0 + n3 != 0.0) {
                super.t[0][k] = 100.0 - 100.0 / (1.0 + n3);
            }
            else {
                super.t[0][k] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[0], (double)super.Ua[1], super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], super.Ua[2], super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
