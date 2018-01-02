// 
// Decompiled by Procyon v0.5.30
// 

public class eea extends public
{
    public eea(final String s, final int[] array, final b b) {
        super(s, 1, array, null, b);
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
        e.calculateExpAvg(i, super.Woa[0], 0, super.Oa[0]);
    }
}
