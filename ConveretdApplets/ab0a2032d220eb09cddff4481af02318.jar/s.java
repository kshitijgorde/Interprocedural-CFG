// 
// Decompiled by Procyon v0.5.30
// 

public class s extends o
{
    public s(final int[] array, final a a) {
        super("BOP - Balance Of Power", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] i = super.rIb.i();
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        if (i == null || j == null || k == null || l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        for (int n = 0; n < l.length; ++n) {
            final double n2 = j[n] - k[n];
            if (n2 != 0.0) {
                super.sIb[0][n] = (l[n] - i[n]) / n2;
            }
            else {
                super.sIb[0][n] = 0.0;
            }
        }
        j.calculateSimpleAvg(super.sIb[0], super.sIb[0], 0, super.DDb[0]);
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
