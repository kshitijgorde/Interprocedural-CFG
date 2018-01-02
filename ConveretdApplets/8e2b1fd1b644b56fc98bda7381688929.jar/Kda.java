// 
// Decompiled by Procyon v0.5.30
// 

public class Kda extends public
{
    public Kda(final int[] array, final b b) {
        super("PVT - Price and Volume Trend", 1, null, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (h == null || i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        array[0] = 0.0;
        for (int j = 1; j < array.length; ++j) {
            if (h[j - 1] != 0.0) {
                array[j] = (h[j] - h[j - 1]) / h[j - 1];
            }
            else {
                array[j] = 0.0;
            }
        }
        super.Woa[0][0] = 0.0;
        for (int k = 1; k < super.Woa[0].length; ++k) {
            super.Woa[0][k] = array[k] * i[k] + super.Woa[0][k - 1];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
