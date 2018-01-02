// 
// Decompiled by Procyon v0.5.30
// 

public class Yda extends public
{
    public Yda(final int[] array, final b b) {
        super("VROC - Volume Rate of Change", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = super.Oa[0];
    }
    
    protected void H() {
        final double[] i = super.Voa.i();
        if (i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][i.length];
        for (int j = super.Oa[0]; j < super.Woa[0].length; ++j) {
            if (i[j - super.Oa[0]] > 0.0) {
                super.Woa[0][j] = (i[j] / i[j - super.Oa[0]] - 1.0) * 100.0;
            }
            else {
                super.Woa[0][j] = 100.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
