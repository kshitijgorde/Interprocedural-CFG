import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class qi extends implements
{
    public qi(final int[] array, final class class1) {
        super("EMV - Ease of Movement", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] f = super.s.f();
        if (a == null || b == null || f == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][a.length];
        final double[] array = new double[a.length];
        array[0] = 0.0;
        for (int i = 1; i < a.length; ++i) {
            array[i] = (a[i] + b[i]) / 2.0 - (a[i - 1] + b[i - 1]) / 2.0;
        }
        final double[] array2 = new double[a.length];
        for (int j = 0; j < a.length; ++j) {
            if (a[j] - b[j] != 0.0) {
                array2[j] = f[j] / 10000.0 / (a[j] - b[j]);
            }
            else {
                array2[j] = 0.0;
            }
        }
        for (int k = 0; k < super.t[0].length; ++k) {
            if (array2[k] != 0.0) {
                super.t[0][k] = array[k] / array2[k];
            }
            else {
                super.t[0][k] = 0.0;
            }
        }
        do.calculateSimpleAvg(super.t[0], super.t[0], 1, super.Ua[0]);
        do._(super.t[0], super.r[0], 0.0);
        super.z = new byte[a.length];
        final Enumeration<Integer> elements = do._(super.t[0], 0.0, super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], 0.0, super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
