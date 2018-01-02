// 
// Decompiled by Procyon v0.5.30
// 

public class import extends o
{
    public import(final int[] array, final a a) {
        super("OBV - On Balance Volume", 1, null, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        super.sIb[0][0] = 0.0;
        for (int i = 1; i < super.sIb[0].length; ++i) {
            if (l[i] > l[i - 1]) {
                super.sIb[0][i] = super.sIb[0][i - 1] + m[i];
            }
            else if (l[i] < l[i - 1]) {
                super.sIb[0][i] = super.sIb[0][i - 1] - m[i];
            }
            else {
                super.sIb[0][i] = super.sIb[0][i - 1];
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
