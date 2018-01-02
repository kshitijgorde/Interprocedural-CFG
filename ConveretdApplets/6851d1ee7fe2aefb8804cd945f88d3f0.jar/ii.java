import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class ii extends implements
{
    public ii(final int[] array, final class class1) {
        super("CCI - Commodity Channel Index", 1, array, new double[] { -100.0, 100.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        if (a == null || b == null || _ == null) {
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
        do.calculateSimpleAvg(array, array2, 0, super.Ua[0]);
        final double[] array3 = new double[_.length];
        for (int j = 0; j < array3.length; ++j) {
            array3[j] = 0.0;
            int n;
            for (n = 0; j - n >= 0 && n < super.Ua[0]; ++n) {
                final double[] array4 = array3;
                final int n2 = j;
                array4[n2] += Math.abs(array2[j] - array[j - n]);
            }
            final double[] array5 = array3;
            final int n3 = j;
            array5[n3] /= n + 1;
        }
        for (int k = 0; k < super.t[0].length; ++k) {
            if (array3[k] != 0.0) {
                super.t[0][k] = (array[k] - array2[k]) / (0.015 * array3[k]);
            }
            else {
                super.t[0][k] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)do._(super.t[0], 250.0, super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = -1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)do.a(super.t[0], -250.0, super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = 1;
        }
    }
}
