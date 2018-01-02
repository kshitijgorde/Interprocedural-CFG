// 
// Decompiled by Procyon v0.5.30
// 

public class if extends o
{
    public if(final int[] array, final a a) {
        super("MTM1 - Momentum (type 1)", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
        super.qIb[1] = super.DDb[0] + super.DDb[1] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        for (int i = super.DDb[0]; i < super.sIb[0].length; ++i) {
            super.sIb[0][i] = l[i] - l[i - super.DDb[0]];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[1]);
    }
}
