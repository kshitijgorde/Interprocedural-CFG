import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class const extends o
{
    public const(final int[] array, final a a) {
        super("EMV - Ease of Movement", 1, array, new double[] { 0.0 }, a);
        this.C();
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
        array[0] = 0.0;
        for (int i = 1; i < l.length; ++i) {
            array[i] = (j[i] + k[i]) * 0.5 - (j[i - 1] + k[i - 1]) * 0.5;
        }
        final double[] array2 = new double[l.length];
        for (int n = 0; n < l.length; ++n) {
            if (j[n] != k[n]) {
                array2[n] = m[n] / 1000000.0 / (j[n] - k[n]);
            }
            else {
                array2[n] = 0.0;
            }
        }
        for (int n2 = 0; n2 < super.sIb[0].length; ++n2) {
            if (array2[n2] != 0.0) {
                super.sIb[0][n2] = array[n2] / array2[n2];
            }
            else {
                super.sIb[0][n2] = 0.0;
            }
        }
        j.calculateExpAvg(super.sIb[0], super.sIb[0], 1, super.DDb[0]);
        j.a(super.sIb[0], super.qIb[0], 0.0);
        super.uIb = new byte[l.length];
        final Enumeration<Integer> elements = j._(super.sIb[0], 0.0, super.qIb[0]).elements();
        while (elements.hasMoreElements()) {
            super.uIb[elements.nextElement()] = 1;
        }
        final Enumeration<Integer> elements2 = j.a(super.sIb[0], 0.0, super.qIb[0]).elements();
        while (elements2.hasMoreElements()) {
            super.uIb[elements2.nextElement()] = -1;
        }
    }
}
