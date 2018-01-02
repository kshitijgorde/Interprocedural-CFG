// 
// Decompiled by Procyon v0.5.30
// 

public class wi extends implements
{
    public wi(final int[] array, final class class1) {
        super("MTM2 - Momentum (type 2)", 2, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
        super.r[1] = super.r[0] + super.Ua[1] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        do.calculateSimpleAvg(_, super.t[1], 0, super.Ua[0]);
        for (int i = super.r[0]; i < super.t[0].length; ++i) {
            super.t[0][i] = _[i] - super.t[1][i];
        }
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[1]);
    }
}
