// 
// Decompiled by Procyon v0.5.30
// 

public class new extends o
{
    public new(final int[] array, final a a) {
        super("PVO - Percentage Volume Oscillator", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0], super.DDb[1]) - 1;
    }
    
    protected void D() {
        final double[] m = super.rIb.m();
        if (m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][m.length];
        final double[] array = new double[m.length];
        j.calculateExpAvg(m, array, 0, super.DDb[0]);
        final double[] array2 = new double[m.length];
        j.calculateExpAvg(m, array2, 0, super.DDb[1]);
        for (int i = 0; i < m.length; ++i) {
            if (array[i] != 0.0) {
                super.sIb[0][i] = 100.0 * (array[i] - array2[i]) / array[i];
            }
            else {
                super.sIb[0][i] = 0.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
