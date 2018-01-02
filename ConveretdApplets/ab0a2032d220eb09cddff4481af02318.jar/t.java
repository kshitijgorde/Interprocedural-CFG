import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends o
{
    public t(final int[] array, final a a) {
        super("CCI - Commodity Channel Index", 1, array, new double[] { -100.0, 100.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
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
        for (int i = 0; i < array.length; ++i) {
            array[i] = (j[i] + k[i] + l[i]) / 3.0;
        }
        final double[] array2 = new double[l.length];
        j.calculateSimpleAvg(array, array2, 0, super.DDb[0]);
        final double[] array3 = new double[l.length];
        for (int n = 0; n < array3.length; ++n) {
            array3[n] = 0.0;
            int n2;
            for (n2 = 0; n - n2 >= 0 && n2 < super.DDb[0]; ++n2) {
                final double[] array4 = array3;
                final int n3 = n;
                array4[n3] += Math.abs(array2[n] - array[n - n2]);
            }
            final double[] array5 = array3;
            final int n4 = n;
            array5[n4] /= n2 + 1;
        }
        for (int n5 = 0; n5 < super.sIb[0].length; ++n5) {
            if (array3[n5] != 0.0) {
                super.sIb[0][n5] = (array[n5] - array2[n5]) / (0.015 * array3[n5]);
            }
            else {
                super.sIb[0][n5] = 0.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = (Enumeration<Integer>)j._(super.sIb[0], 250.0, super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = -1;
        }
        final Enumeration<Integer> elements2 = (Enumeration<Integer>)j.a(super.sIb[0], -250.0, super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = 1;
        }
    }
}
