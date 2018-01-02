// 
// Decompiled by Procyon v0.5.30
// 

public class super extends implements
{
    public super(final int[] array, final class class1) {
        super("EBull - Elder-ray Bull Power", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] _ = super.s._();
        if (a == null || _ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        do.calculateExpAvg(_, array, 0, super.Ua[0]);
        for (int i = 0; i < super.t[0].length; ++i) {
            super.t[0][i] = a[i] - array[i];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
