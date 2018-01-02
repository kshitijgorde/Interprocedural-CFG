import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ni extends implements
{
    public Ni(final int[] array, final class class1) {
        super("STS - Stochastic", 2, array, new double[] { 20.0, 80.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0] - 1, super.Ua[1] - 1);
        super.r[1] = super.r[0] + super.Ua[2] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        if (_ == null || a == null || b == null) {
            super.t = null;
            super.z = null;
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
        do.calculateSimpleAvg(super.t[0], super.t[0], super.r[0], super.Ua[1]);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[2]);
        super.z = new byte[_.length];
        final Enumeration<Integer> elements = do._(super.t[0], 20.0, super.r[0]).elements();
        while (elements.hasMoreElements()) {
            super.z[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = do.a(super.t[0], 80.0, super.r[0]).elements();
        while (elements2.hasMoreElements()) {
            super.z[elements2.nextElement()] = -1;
        }
    }
}
