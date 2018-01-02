// 
// Decompiled by Procyon v0.5.30
// 

public class Qo extends o
{
    public Qo(final String s, final int[] array, final a a) {
        super(s, 1, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0];
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        j.calculateSimpleAvg(l, super.sIb[0], 0, super.DDb[0]);
    }
}
