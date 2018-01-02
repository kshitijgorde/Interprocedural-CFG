// 
// Decompiled by Procyon v0.5.30
// 

public class Oi extends implements
{
    public Oi(final int[] array, final class class1) {
        super("TRD - Trend Deviation", 1, array, new double[] { 1.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        do.calculateSimpleAvg(_, super.t[0], 0, super.Ua[0]);
        for (int i = super.r[0]; i < super.t[0].length; ++i) {
            if (super.t[0][i] > 0.0) {
                super.t[0][i] = _[i] / super.t[0][i];
            }
            else {
                super.t[0][i] = 1.0;
            }
        }
        do._(super.t[0], super.r[0], 100.0);
    }
}
