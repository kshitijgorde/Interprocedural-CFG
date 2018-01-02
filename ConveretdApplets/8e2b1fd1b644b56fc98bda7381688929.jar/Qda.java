// 
// Decompiled by Procyon v0.5.30
// 

public class Qda extends public
{
    public Qda(final int[] array, final b b) {
        super("StDev - Standard Deviation", 1, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] - 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        e.calculateStdDev(h, super.Woa[0], 0, super.Oa[0]);
        e.a(super.Woa[0], super.Oa[0], 0.0);
    }
}
