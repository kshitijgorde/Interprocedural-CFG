// 
// Decompiled by Procyon v0.5.30
// 

public class switch extends o
{
    public switch(final int[] array, final a a) {
        super("TRD - Trend Deviation", 1, array, new double[] { 1.0 }, a);
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
        j.calculateSimpleAvg(l, super.sIb[0], 0, super.DDb[0]);
        for (int i = super.qIb[0]; i < super.sIb[0].length; ++i) {
            if (super.sIb[0][i] > 0.0) {
                super.sIb[0][i] = l[i] / super.sIb[0][i];
            }
            else {
                super.sIb[0][i] = 1.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 100.0);
    }
}
