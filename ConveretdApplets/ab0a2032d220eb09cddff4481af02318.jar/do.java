// 
// Decompiled by Procyon v0.5.30
// 

public class do extends o
{
    public do(final int[] array, final a a) {
        super("FI - Force Index", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (l == null || m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        super.sIb[0][0] = 0.0;
        for (int i = 1; i < l.length; ++i) {
            super.sIb[0][i] = (l[i] - l[i - 1]) * m[i];
        }
        j.calculateExpAvg(super.sIb[0], super.sIb[0], 1, super.DDb[0]);
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
