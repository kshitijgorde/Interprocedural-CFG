import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class for extends o
{
    public for(final int[] array, final a a) {
        super("MFI - Money Flow Index", 1, array, new double[] { array[1], array[2] }, a);
        this.C();
    }
    
    public void j(final int n, final int n2) {
        if (super.vIb != null && n > 0 && n - 1 < super.vIb.length) {
            super.vIb[n - 1] = n2;
        }
        super.j(n, n2);
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (j == null || k == null || l == null || m == null) {
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
        for (int n = 0; n < array.length; ++n) {
            array2[n] = array[n] * m[n];
        }
        for (int n2 = super.DDb[0]; n2 < super.sIb[0].length; ++n2) {
            double n4;
            double n3 = n4 = 0.0;
            for (int n5 = n2 - (super.DDb[0] - 1); n5 <= n2; ++n5) {
                if (array[n5] > array[n5 - 1]) {
                    n4 += array2[n5];
                }
                else if (array[n5] < array[n5 - 1]) {
                    n3 += array2[n5];
                }
            }
            double n6;
            if (n3 != 0.0) {
                n6 = n4 / n3;
            }
            else {
                n6 = 0.0;
            }
            if (1.0 + n6 != 0.0) {
                super.sIb[0][n2] = 100.0 - 100.0 / (1.0 + n6);
            }
            else {
                super.sIb[0][n2] = 0.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j._(super.sIb[0], super.DDb[1], super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.a(super.sIb[0], (double)super.DDb[2], super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
