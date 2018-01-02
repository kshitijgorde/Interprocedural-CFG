// 
// Decompiled by Procyon v0.5.30
// 

public class p extends o
{
    public p(final int[] array, final a a) {
        super("ACC - Acceleration", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] + super.DDb[1];
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        for (int i = super.DDb[0]; i < array.length; ++i) {
            array[i] = l[i] - l[i - super.DDb[0]];
        }
        j.a(array, super.DDb[0], 0.0);
        for (int j = super.qIb[0]; j < super.sIb[0].length; ++j) {
            super.sIb[0][j] = array[j] - array[j - super.DDb[1]];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
