// 
// Decompiled by Procyon v0.5.30
// 

public class break extends o
{
    public break(final int[] array, final a a) {
        super("ChV - Chaikin Volatility", 1, array, new double[] { 0.0 }, a);
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
        for (int i = 0; i < j.length; ++i) {
            array[i] = j[i] - k[i];
        }
        final double[] array2 = new double[j.length];
        j.calculateExpAvg(array, array2, 0, super.DDb[0]);
        for (int l = 0; l < super.sIb[0].length; ++l) {
            if (l < super.DDb[1]) {
                super.sIb[0][l] = 0.0;
            }
            else if (array2[l - super.DDb[1]] != 0.0) {
                super.sIb[0][l] = 100.0 * (array2[l] - array2[l - super.DDb[1]]) / array2[l - super.DDb[1]];
            }
            else {
                super.sIb[0][l] = 100.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
