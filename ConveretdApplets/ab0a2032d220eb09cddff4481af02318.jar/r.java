// 
// Decompiled by Procyon v0.5.30
// 

public class r extends o
{
    public r(final int[] array, final a a) {
        super("ATR - Average True Range", 1, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        if (j == null || k == null || l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        super.sIb[0][0] = j[0] - k[0];
        for (int i = 1; i < l.length; ++i) {
            double n = j[i] - k[i];
            if (j[i] - l[i - 1] > n) {
                n = j[i] - l[i - 1];
            }
            if (l[i - 1] - k[i] > n) {
                n = l[i - 1] - k[i];
            }
            super.sIb[0][i] = n;
        }
        for (int n2 = 1; n2 < super.sIb[0].length; ++n2) {
            super.sIb[0][n2] = ((super.DDb[0] - 1) * super.sIb[0][n2 - 1] + super.sIb[0][n2]) / super.DDb[0];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
