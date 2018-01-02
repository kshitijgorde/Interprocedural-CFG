// 
// Decompiled by Procyon v0.5.30
// 

public class finally extends o
{
    public finally(final int[] array, final a a) {
        super("MTM2 - Momentum (type 2)", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
        super.qIb[1] = super.qIb[0] + super.DDb[1] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        j.calculateSimpleAvg(l, super.sIb[1], 0, super.DDb[0]);
        for (int i = super.qIb[0]; i < super.sIb[0].length; ++i) {
            super.sIb[0][i] = l[i] - super.sIb[1][i];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[1]);
    }
}
