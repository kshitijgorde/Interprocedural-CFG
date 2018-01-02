import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class oi extends implements
{
    public oi(final int[] array, final class class1) {
        super("DMI - Directional Movement Index", 3, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] * 2 + super.Ua[1] * 2;
        super.r[1] = super.Ua[0] * 2;
        super.r[2] = super.Ua[0] * 2;
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
        array[0] = 0.0;
        final double[] array2 = new double[_.length];
        array2[0] = 0.0;
        for (int i = 1; i < array.length; ++i) {
            if (a[i] > a[i - 1] || b[i] < b[i - 1]) {
                array[i] = a[i] - a[i - 1];
                array2[i] = b[i - 1] - b[i];
                if (array[i] > array2[i]) {
                    array2[i] = 0.0;
                }
                else if (array[i] < array2[i]) {
                    array[i] = 0.0;
                }
                else {
                    array[i] = (array2[i] = 0.0);
                }
            }
            else {
                array[i] = (array2[i] = 0.0);
            }
        }
        final double[] array3 = new double[_.length];
        array3[0] = 0.0;
        for (int j = 1; j < array3.length; ++j) {
            final double abs = Math.abs(a[j] - b[j]);
            final double abs2 = Math.abs(a[j] - _[j - 1]);
            final double abs3 = Math.abs(b[j] - _[j - 1]);
            array3[j] = Math.max(abs, abs2);
            array3[j] = Math.max(array3[j], abs3);
        }
        final int n = super.Ua[0] * 2 - 1;
        do.calculateExpAvg(array, array, 1, n);
        do.calculateExpAvg(array2, array2, 1, n);
        do.calculateExpAvg(array3, array3, 1, n);
        for (int k = 0; k < super.t[0].length; ++k) {
            if (array3[k] != 0.0) {
                super.t[1][k] = 100.0 * array2[k] / array3[k];
                super.t[2][k] = 100.0 * array[k] / array3[k];
                if (super.t[1][k] + super.t[2][k] != 0.0) {
                    super.t[0][k] = 100.0 * Math.abs(super.t[2][k] - super.t[1][k]) / (super.t[1][k] + super.t[2][k]);
                }
            }
            else {
                super.t[1][k] = 0.0;
                super.t[2][k] = 0.0;
                super.t[0][k] = 0.0;
            }
        }
        do.calculateExpAvg(super.t[0], super.t[0], 1, super.Ua[1] * 2 - 1);
        do._(super.t[0], super.r[0], 0.0);
        do._(super.t[1], super.r[1], 0.0);
        do._(super.t[2], super.r[2], 0.0);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[2], super.t[1], super.r[2]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[2], super.t[1], super.r[2]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
