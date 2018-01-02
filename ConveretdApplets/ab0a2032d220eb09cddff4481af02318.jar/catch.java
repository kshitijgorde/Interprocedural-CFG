import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class catch extends o
{
    public catch(final int[] array, final a a) {
        super("DMI - Directional Movement Index", 3, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
        super.qIb[1] = super.DDb[0];
        super.qIb[2] = super.DDb[0];
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        if (j == null || k == null || l == null) {
            super.sIb = null;
            super.uIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = 0.0;
        for (int i = 1; i < array.length; ++i) {
            array[i] = j[i] - j[i - 1];
        }
        final double[] array2 = new double[l.length];
        array2[0] = 0.0;
        for (int n = 1; n < array2.length; ++n) {
            array2[n] = k[n] - k[n - 1];
        }
        for (int n2 = 0; n2 < array.length; ++n2) {
            if (array[n2] < 0.0 && array2[n2] > 0.0) {
                array[n2] = (array2[n2] = 0.0);
            }
        }
        for (int n3 = 0; n3 < array.length; ++n3) {
            array[n3] = Math.abs(array[n3]);
        }
        for (int n4 = 0; n4 < array.length; ++n4) {
            array2[n4] = Math.abs(array2[n4]);
        }
        for (int n5 = 0; n5 < array.length; ++n5) {
            if (array[n5] < array2[n5]) {
                array[n5] = 0.0;
            }
            else {
                array2[n5] = 0.0;
            }
        }
        final double[] array3 = new double[l.length];
        array3[0] = 0.0;
        for (int n6 = 1; n6 < array3.length; ++n6) {
            final double abs = Math.abs(j[n6] - k[n6]);
            final double abs2 = Math.abs(j[n6] - l[n6 - 1]);
            final double abs3 = Math.abs(k[n6] - l[n6 - 1]);
            array3[n6] = Math.max(abs, abs2);
            array3[n6] = Math.max(array3[n6], abs3);
        }
        j.calculateExpAvg(array, array, 1, super.DDb[0]);
        j.calculateExpAvg(array2, array2, 1, super.DDb[0]);
        j.calculateExpAvg(array3, array3, 1, super.DDb[0]);
        for (int n7 = 0; n7 < super.sIb[0].length; ++n7) {
            if (array3[n7] != 0.0) {
                super.sIb[1][n7] = array[n7] / array3[n7];
                super.sIb[2][n7] = array2[n7] / array3[n7];
                if (super.sIb[1][n7] + super.sIb[2][n7] != 0.0) {
                    super.sIb[0][n7] = Math.abs((super.sIb[1][n7] - super.sIb[2][n7]) / (super.sIb[1][n7] + super.sIb[2][n7]));
                }
            }
            else {
                super.sIb[1][n7] = 0.0;
                super.sIb[2][n7] = 0.0;
                super.sIb[0][n7] = 0.0;
            }
        }
        for (int n8 = 0; n8 < super.sIb[0].length; ++n8) {
            final double[] array4 = super.sIb[0];
            final int n9 = n8;
            array4[n9] *= 100.0;
            final double[] array5 = super.sIb[1];
            final int n10 = n8;
            array5[n10] *= 100.0;
            final double[] array6 = super.sIb[2];
            final int n11 = n8;
            array6[n11] *= 100.0;
        }
        j.calculateExpAvg(super.sIb[0], super.sIb[0], 1, super.DDb[0]);
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.a(super.sIb[1], super.qIb[1], 0.0);
        j.a(super.sIb[2], super.qIb[2], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)j.a(super.sIb[1], super.sIb[2], super.qIb[2]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)j.b(super.sIb[1], super.sIb[2], super.qIb[2]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
