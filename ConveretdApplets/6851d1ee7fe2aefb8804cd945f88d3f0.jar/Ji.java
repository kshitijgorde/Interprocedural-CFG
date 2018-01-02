import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ji extends implements
{
    public Ji(final int[] array, final class class1) {
        super("RSI - Relative Strength Index", 1, array, new double[] { array[1], array[2] }, class1);
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
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            super.z = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        array[0] = 0.0;
        for (int i = 1; i < _.length; ++i) {
            array[i] = _[i] - _[i - 1];
        }
        double n2;
        double n = n2 = 0.0;
        for (int n3 = 0; n3 < super.Ua[0] && n3 < array.length; ++n3) {
            if (array[n3] >= 0.0) {
                n2 += array[n3];
            }
            else {
                n -= array[n3];
            }
        }
        double n4 = n2 / super.Ua[0];
        double n5 = n / super.Ua[0];
        for (int j = super.Ua[0]; j < array.length; ++j) {
            double n7;
            double n6 = n7 = 0.0;
            if (array[j] >= 0.0) {
                n7 = array[j];
            }
            else {
                n6 = -array[j];
            }
            n4 = (n4 * (super.Ua[0] - 1) + n7) / super.Ua[0];
            n5 = (n5 * (super.Ua[0] - 1) + n6) / super.Ua[0];
            if (n4 + n5 != 0.0) {
                super.t[0][j] = 100.0 * n4 / (n4 + n5);
            }
            else {
                super.t[0][j] = 100.0;
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
