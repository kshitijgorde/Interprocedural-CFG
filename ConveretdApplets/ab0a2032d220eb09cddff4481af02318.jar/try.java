// 
// Decompiled by Procyon v0.5.30
// 

public class try extends o
{
    public try(final int[] array, final a a) {
        super("VROC - Volume Rate of Change", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] m = super.rIb.m();
        if (m == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][m.length];
        for (int i = super.DDb[0]; i < super.sIb[0].length; ++i) {
            if (m[i - super.DDb[0]] > 0.0) {
                super.sIb[0][i] = (m[i] / m[i - super.DDb[0]] - 1.0) * 100.0;
            }
            else {
                super.sIb[0][i] = 100.0;
            }
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
