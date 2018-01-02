// 
// Decompiled by Procyon v0.5.30
// 

public class q extends o
{
    public q(final int[] array, final a a) {
        super("AD - Accumulation Distribution", 1, null, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 1;
    }
    
    protected void D() {
        final double[] i = super.rIb.i();
        final double[] j = super.rIb.j();
        final double[] k = super.rIb.k();
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (i == null || j == null || k == null || l == null || m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        if (j[0] != k[0]) {
            super.sIb[0][0] = m[0] * (l[0] - k[0] - (j[0] - l[0])) / (j[0] - k[0]);
        }
        else {
            super.sIb[0][0] = 0.0;
        }
        for (int n = 1; n < l.length; ++n) {
            if (j[n] != k[n]) {
                super.sIb[0][n] = super.sIb[0][n - 1] + m[n] * (l[n] - k[n] - (j[n] - l[n])) / (j[n] - k[n]);
            }
            else {
                super.sIb[0][n] = super.sIb[0][n - 1];
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
