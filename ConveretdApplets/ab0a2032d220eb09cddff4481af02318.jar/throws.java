// 
// Decompiled by Procyon v0.5.30
// 

public class throws extends o
{
    public throws(final int[] array, final a a) {
        super("VLT - Volatility Ratio", 1, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = l[0];
        for (int i = 1; i < l.length; ++i) {
            array[i] = Math.min(l[i], l[i - 1]);
        }
        final double[] array2 = new double[l.length];
        array2[0] = 0.0;
        for (int j = 1; j < l.length; ++j) {
            array2[j] = Math.max(l[j], l[j - 1]) - array[j];
        }
        super.sIb[0][0] = array2[0];
        for (int k = 1; k < l.length; ++k) {
            super.sIb[0][k] = ((super.DDb[0] - 1) * super.sIb[0][k - 1] + array2[k]) / super.DDb[0];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
