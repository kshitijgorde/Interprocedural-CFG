// 
// Decompiled by Procyon v0.5.30
// 

public class goto extends o
{
    public goto(final int[] array, final a a) {
        super("MI - Mass Index", 1, null, new double[] { 26.5, 27.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 40;
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
        j.calculateExpAvg(array, array2, 0, 9);
        j.calculateExpAvg(array2, array, 8, 9);
        for (int l = 0; l < j.length; ++l) {
            if (array[l] != 0.0) {
                array[l] = array2[l] / array[l];
            }
            else {
                array[l] = 0.0;
            }
        }
        j._(array, super.sIb[0], 16, 25);
        j.a(super.sIb[0], 40, 0.0);
    }
}
