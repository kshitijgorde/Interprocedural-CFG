// 
// Decompiled by Procyon v0.5.30
// 

public class Wda extends public
{
    public Wda(final int[] array, final b b) {
        super("VOS - Volume Oscillator", 2, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0], super.Oa[1]) - 1;
        super.Uoa[1] = super.Uoa[0] + super.Oa[2] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        e.calculateSimpleAvg(i, super.Woa[0], 0, super.Oa[0]);
        e.calculateSimpleAvg(i, super.Woa[1], 0, super.Oa[1]);
        for (int j = 0; j < h.length; ++j) {
            final double[] array = super.Woa[0];
            final int n = j;
            array[n] -= super.Woa[1][j];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
        e.calculateExpAvg(super.Woa[0], super.Woa[1], super.Uoa[0], super.Oa[2]);
    }
}
