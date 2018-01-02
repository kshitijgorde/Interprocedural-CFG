// 
// Decompiled by Procyon v0.5.30
// 

public class transient extends o
{
    public transient(final int[] array, final a a) {
        super("VOS - Volume Oscillator", 2, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = Math.max(super.DDb[0], super.DDb[1]) - 1;
        super.qIb[1] = super.qIb[0] + super.DDb[2] - 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        final double[] m = super.rIb.m();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        j.calculateSimpleAvg(m, super.sIb[0], 0, super.DDb[0]);
        j.calculateSimpleAvg(m, super.sIb[1], 0, super.DDb[1]);
        for (int i = 0; i < l.length; ++i) {
            final double[] array = super.sIb[0];
            final int n = i;
            array[n] -= super.sIb[1][i];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
        j.calculateExpAvg(super.sIb[0], super.sIb[1], super.qIb[0], super.DDb[2]);
    }
}
