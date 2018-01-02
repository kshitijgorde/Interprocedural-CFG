// 
// Decompiled by Procyon v0.5.30
// 

public class null extends o
{
    public null(final int[] array, final a a) {
        super("PVT - Price and Volume Trend", 1, null, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (l == null || m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        array[0] = 0.0;
        for (int i = 1; i < array.length; ++i) {
            if (l[i - 1] != 0.0) {
                array[i] = (l[i] - l[i - 1]) / l[i - 1];
            }
            else {
                array[i] = 0.0;
            }
        }
        super.sIb[0][0] = 0.0;
        for (int j = 1; j < super.sIb[0].length; ++j) {
            super.sIb[0][j] = array[j] * m[j] + super.sIb[0][j - 1];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
