// 
// Decompiled by Procyon v0.5.30
// 

public class class extends o
{
    public class(final int[] array, final a a) {
        super("DPO - Detrended Price Oscillator", 1, array, new double[] { 0.0 }, a);
        this.C();
    }
    
    protected void C() {
        super.qIb[0] = super.DDb[0] - 1 + super.DDb[0] / 2 + 1;
    }
    
    protected void D() {
        final double[] l = super.rIb.l();
        if (l == null) {
            super.sIb = null;
            return;
        }
        super.sIb = new double[super.tIb][l.length];
        final double[] array = new double[l.length];
        j.calculateSimpleAvg(l, array, 0, super.DDb[0]);
        int i;
        for (int n = i = super.DDb[0] / 2 + 1; i < l.length; ++i) {
            super.sIb[0][i] = l[i] - array[i - n];
        }
        j.a(super.sIb[0], super.qIb[0], 0.0);
    }
}
