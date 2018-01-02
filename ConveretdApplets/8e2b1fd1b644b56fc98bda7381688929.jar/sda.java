// 
// Decompiled by Procyon v0.5.30
// 

public class sda extends public
{
    public sda(final int[] array, final b b) {
        super("DPO - Detrended Price Oscillator", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1 + super.Oa[0] / 2 + 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        e.calculateSimpleAvg(h, array, 0, super.Oa[0]);
        int i;
        for (int n = i = super.Oa[0] / 2 + 1; i < h.length; ++i) {
            super.Woa[0][i] = h[i] - array[i - n];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
