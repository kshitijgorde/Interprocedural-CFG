// 
// Decompiled by Procyon v0.5.30
// 

public class this extends o
{
    public this(final int[] array, final a a) {
        super("BOS - Bollinger Oscillator", 1, array, new double[] { 2.0, -2.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        final double[] array2 = new double[l.length];
        j.calculateSimpleAvg(l, array, 0, super.DDb[0]);
        j.calculateStdDev(l, array2, 0, super.DDb[0]);
        for (int i = super.qIb[0]; i < super.sIb[0].length; ++i) {
            if (array2[i] > 0.0) {
                super.sIb[0][i] = 1.0 * (l[i] - array[i]) / array2[i];
            }
            else {
                super.sIb[0][i] = 1.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 1.0);
    }
}
