// 
// Decompiled by Procyon v0.5.30
// 

public class Ro extends o
{
    public Ro(final String s, final int[] array, final a a) {
        super(s, 1, array, null, a);
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
        j.calculateExpAvg(m, super.sIb[0], 0, super.DDb[0]);
    }
}