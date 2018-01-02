// 
// Decompiled by Procyon v0.5.30
// 

public class abstract extends o
{
    public abstract(final int[] array, final a a) {
        super("ChO - Chaikin Oscillator", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[1];
    }
    
    protected void D() {
        final double[] i = super.rIb.i();
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (i == null || j == null || k == null || l == null || m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = 0.0;
        for (int n = 1; n < l.length; ++n) {
            if (j[n] > k[n]) {
                array[n] = array[n - 1] + m[n] * (l[n] - i[n]) / (j[n] - k[n]);
            }
            else {
                array[n] = array[n - 1];
            }
        }
        j.calculateExpAvg(array, super.sIb[0], 0, super.DDb[0]);
        j.calculateExpAvg(array, array, 0, super.DDb[1]);
        for (int n2 = 0; n2 < l.length; ++n2) {
            final double[] array2 = super.sIb[0];
            final int n3 = n2;
            array2[n3] -= array[n2];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
