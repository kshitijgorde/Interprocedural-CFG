// 
// Decompiled by Procyon v0.5.30
// 

public class Fda extends public
{
    public Fda(final int[] array, final b b) {
        super("OBV - On Balance Volume", 1, null, null, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 1;
    }
    
    protected void H() {
        final double[] h = super.Voa.h();
        final double[] i = super.Voa.i();
        if (h == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][h.length];
        super.Woa[0][0] = 0.0;
        for (int j = 1; j < super.Woa[0].length; ++j) {
            if (h[j] > h[j - 1]) {
                super.Woa[0][j] = super.Woa[0][j - 1] + i[j];
            }
            else if (h[j] < h[j - 1]) {
                super.Woa[0][j] = super.Woa[0][j - 1] - i[j];
            }
            else {
                super.Woa[0][j] = super.Woa[0][j - 1];
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}
