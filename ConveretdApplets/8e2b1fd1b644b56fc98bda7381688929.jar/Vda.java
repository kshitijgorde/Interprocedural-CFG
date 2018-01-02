// 
// Decompiled by Procyon v0.5.30
// 

public class Vda extends public
{
    public Vda(final int[] array, final b b) {
        super("ULT - Ultimate Oscillator", 1, array, new double[] { 30.0, 70.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0], super.Oa[1]);
        super.Uoa[0] = Math.max(super.Uoa[0], super.Oa[2]);
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        if (h == null || _ == null || g == null) {
            super.Woa = null;
            return;
        }
        final double n = super.Oa[2] / super.Oa[0];
        final double n2 = super.Oa[1] / super.Oa[0];
        final double n3 = 1.0 + n + n2;
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = g[0];
        for (int i = 1; i < h.length; ++i) {
            array[i] = Math.min(g[i], h[i - 1]);
        }
        final double[] array2 = new double[_.length];
        array2[0] = _[0] - g[0];
        for (int j = 1; j < h.length; ++j) {
            double n4 = _[j] - g[j];
            if (_[j] - h[j - 1] > n4) {
                n4 = _[j] - h[j - 1];
            }
            if (h[j - 1] - g[j] > n4) {
                n4 = h[j - 1] - g[j];
            }
            array2[j] = n4;
        }
        final double[] array3 = new double[h.length];
        for (int k = 0; k < h.length; ++k) {
            array3[k] = h[k] - array[k];
        }
        final double[] array4 = new double[h.length];
        e.calculateSimpleAvg(array3, array4, 0, super.Oa[0]);
        final double[] array5 = new double[h.length];
        e.calculateSimpleAvg(array2, array5, 0, super.Oa[0]);
        for (int l = 0; l < h.length; ++l) {
            if (array5[l] != 0.0) {
                array[l] = n * array4[l] / array5[l];
            }
            else {
                array[l] = 0.0;
            }
        }
        e.calculateSimpleAvg(array3, array4, 0, super.Oa[1]);
        e.calculateSimpleAvg(array2, array5, 0, super.Oa[1]);
        for (int n5 = 0; n5 < h.length; ++n5) {
            if (array5[n5] != 0.0) {
                final double[] array6 = array;
                final int n6 = n5;
                array6[n6] += n2 * array4[n5] / array5[n5];
            }
            else {
                array[n5] = 0.0;
            }
        }
        e.calculateSimpleAvg(array3, array4, 0, super.Oa[1]);
        e.calculateSimpleAvg(array2, array5, 0, super.Oa[1]);
        for (int n7 = 0; n7 < h.length; ++n7) {
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
        System.arraycopy(array, 0, super.Woa[0], 0, h.length);
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
