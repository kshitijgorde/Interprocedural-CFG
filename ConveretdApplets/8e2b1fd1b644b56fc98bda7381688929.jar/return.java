// 
// Decompiled by Procyon v0.5.30
// 

public class return extends public
{
    public return(final int[] array, final b b) {
        super("ACC - Acceleration", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0] + super.Oa[1];
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        final double[] array = new double[h.length];
        for (int i = super.Oa[0]; i < array.length; ++i) {
            array[i] = h[i] - h[i - super.Oa[0]];
        }
        e.a(array, super.Oa[0], 0.0);
        for (int j = super.Uoa[0]; j < super.Woa[0].length; ++j) {
            super.Woa[0][j] = array[j] - array[j - super.Oa[1]];
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
