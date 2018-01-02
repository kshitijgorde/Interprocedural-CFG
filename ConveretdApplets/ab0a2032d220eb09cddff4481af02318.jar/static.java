// 
// Decompiled by Procyon v0.5.30
// 

public class static extends o
{
    public static(final int[] array, final a a) {
        super("StDev - Standard Deviation", 1, array, null, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        j.calculateStdDev(l, super.sIb[0], 0, super.DDb[0]);
        j.a(super.sIb[0], super.DDb[0], 0.0);
    }
}
