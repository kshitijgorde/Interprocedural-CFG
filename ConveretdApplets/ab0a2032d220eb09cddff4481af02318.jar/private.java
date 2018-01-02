// 
// Decompiled by Procyon v0.5.30
// 

public class private extends o
{
    public private(final int[] array, final a a) {
        super("ROC - Rate of Change", 2, array, new double[] { 0.0 }, a);
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
            if (l[i - super.DDb[0]] > 0.0) {
                super.sIb[0][i] = (l[i] / l[i - super.DDb[0]] - 1.0) * 100.0;
            }
            else {
                super.sIb[0][i] = 100.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[1]);
    }
}
