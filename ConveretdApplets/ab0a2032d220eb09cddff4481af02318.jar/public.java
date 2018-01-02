// 
// Decompiled by Procyon v0.5.30
// 

public class public extends o
{
    public public(final int[] array, final a a) {
        super("RVI - Relative Volatility Index", 1, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] + super.DDb[1] - 1;
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        if (j == null || k == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][j.length];
        final double[] array = new double[j.length];
        j.calculateStdDev(j, array, 0, super.DDb[0]);
        j.calculateExpAvg(array, array, super.DDb[0] - 1, super.DDb[1]);
        final double[] array2 = new double[k.length];
        j.calculateStdDev(k, array2, 0, super.DDb[0]);
        j.calculateExpAvg(array2, array2, super.DDb[0] - 1, super.DDb[1]);
        for (int i = 0; i < j.length; ++i) {
            if (array[i] + array2[i] != 0.0) {
                super.sIb[0][i] = 100.0 * array[i] / (array[i] + array2[i]);
            }
            else {
                super.sIb[0][i] = 0.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
