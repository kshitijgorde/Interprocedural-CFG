// 
// Decompiled by Procyon v0.5.30
// 

public class Ri extends implements
{
    public Ri(final int[] array, final class class1) {
        super("ULT - Ultimate Oscillator", 1, array, new double[] { 30.0, 70.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0], super.Ua[1]);
        super.r[0] = Math.max(super.r[0], super.Ua[2]);
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        if (_ == null || a == null || b == null) {
            super.t = null;
            return;
        }
        final double n = super.Ua[2] / super.Ua[0];
        final double n2 = super.Ua[1] / super.Ua[0];
        final double n3 = 1.0 + n + n2;
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        array[0] = b[0];
        for (int i = 1; i < _.length; ++i) {
            array[i] = Math.min(b[i], _[i - 1]);
        }
        final double[] array2 = new double[a.length];
        array2[0] = a[0] - b[0];
        for (int j = 1; j < _.length; ++j) {
            double n4 = a[j] - b[j];
            if (a[j] - _[j - 1] > n4) {
                n4 = a[j] - _[j - 1];
            }
            if (_[j - 1] - b[j] > n4) {
                n4 = _[j - 1] - b[j];
            }
            array2[j] = n4;
        }
        final double[] array3 = new double[_.length];
        for (int k = 0; k < _.length; ++k) {
            array3[k] = _[k] - array[k];
        }
        final double[] array4 = new double[_.length];
        do.calculateSimpleAvg(array3, array4, 0, super.Ua[0]);
        final double[] array5 = new double[_.length];
        do.calculateSimpleAvg(array2, array5, 0, super.Ua[0]);
        for (int l = 0; l < _.length; ++l) {
            if (array5[l] != 0.0) {
                array[l] = n * array4[l] / array5[l];
            }
            else {
                array[l] = 0.0;
            }
        }
        do.calculateSimpleAvg(array3, array4, 0, super.Ua[1]);
        do.calculateSimpleAvg(array2, array5, 0, super.Ua[1]);
        for (int n5 = 0; n5 < _.length; ++n5) {
            if (array5[n5] != 0.0) {
                final double[] array6 = array;
                final int n6 = n5;
                array6[n6] += n2 * array4[n5] / array5[n5];
            }
            else {
                array[n5] = 0.0;
            }
        }
        do.calculateSimpleAvg(array3, array4, 0, super.Ua[1]);
        do.calculateSimpleAvg(array2, array5, 0, super.Ua[1]);
        for (int n7 = 0; n7 < _.length; ++n7) {
            if (array5[n7] != 0.0) {
                final double[] array7 = array;
                final int n8 = n7;
                array7[n8] += array4[n7] / array5[n7];
                final double[] array8 = array;
                final int n9 = n7;
                array8[n9] /= n3;
                final double[] array9 = array;
                final int n10 = n7;
                array9[n10] *= 100.0;
            }
            else {
                array[n7] = 0.0;
            }
        }
        System.arraycopy(array, 0, super.t[0], 0, _.length);
        do._(super.t[0], super.r[0], 0.0);
    }
}
