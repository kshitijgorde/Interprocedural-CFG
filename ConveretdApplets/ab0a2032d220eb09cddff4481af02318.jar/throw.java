// 
// Decompiled by Procyon v0.5.30
// 

public class throw extends o
{
    public throw(final int[] array, final a a) {
        super("ULT - Ultimate Oscillator", 1, array, new double[] { 30.0, 70.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0], super.DDb[1]);
        super.qIb[0] = Math.max(super.qIb[0], super.DDb[2]);
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        if (l == null || j == null || k == null) {
            super.sIb = null;
            return;
        }
        final double n = super.DDb[2] / super.DDb[0];
        final double n2 = super.DDb[1] / super.DDb[0];
        final double n3 = 1.0 + n + n2;
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = k[0];
        for (int i = 1; i < l.length; ++i) {
            array[i] = Math.min(k[i], l[i - 1]);
        }
        final double[] array2 = new double[j.length];
        array2[0] = j[0] - k[0];
        for (int n4 = 1; n4 < l.length; ++n4) {
            double n5 = j[n4] - k[n4];
            if (j[n4] - l[n4 - 1] > n5) {
                n5 = j[n4] - l[n4 - 1];
            }
            if (l[n4 - 1] - k[n4] > n5) {
                n5 = l[n4 - 1] - k[n4];
            }
            array2[n4] = n5;
        }
        final double[] array3 = new double[l.length];
        for (int n6 = 0; n6 < l.length; ++n6) {
            array3[n6] = l[n6] - array[n6];
        }
        final double[] array4 = new double[l.length];
        j.calculateSimpleAvg(array3, array4, 0, super.DDb[0]);
        final double[] array5 = new double[l.length];
        j.calculateSimpleAvg(array2, array5, 0, super.DDb[0]);
        for (int n7 = 0; n7 < l.length; ++n7) {
            if (array5[n7] != 0.0) {
                array[n7] = n * array4[n7] / array5[n7];
            }
            else {
                array[n7] = 0.0;
            }
        }
        j.calculateSimpleAvg(array3, array4, 0, super.DDb[1]);
        j.calculateSimpleAvg(array2, array5, 0, super.DDb[1]);
        for (int n8 = 0; n8 < l.length; ++n8) {
            if (array5[n8] != 0.0) {
                final double[] array6 = array;
                final int n9 = n8;
                array6[n9] += n2 * array4[n8] / array5[n8];
            }
            else {
                array[n8] = 0.0;
            }
        }
        j.calculateSimpleAvg(array3, array4, 0, super.DDb[1]);
        j.calculateSimpleAvg(array2, array5, 0, super.DDb[1]);
        for (int n10 = 0; n10 < l.length; ++n10) {
            if (array5[n10] != 0.0) {
                final double[] array7 = array;
                final int n11 = n10;
                array7[n11] += array4[n10] / array5[n10];
                final double[] array8 = array;
                final int n12 = n10;
                array8[n12] /= n3;
                final double[] array9 = array;
                final int n13 = n10;
                array9[n13] *= 100.0;
            }
            else {
                array[n10] = 0.0;
            }
        }
        System.arraycopy(array, 0, super.sIb[0], 0, l.length);
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
