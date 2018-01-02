// 
// Decompiled by Procyon v0.5.30
// 

public class dea extends public
{
    public dea(final String s, final int[] array, final b b) {
        super(s, 1, array, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        e.calculateSimpleAvg(h, super.Woa[0], 0, super.Oa[0]);
    }
}
