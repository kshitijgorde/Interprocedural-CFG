// 
// Decompiled by Procyon v0.5.30
// 

public class Ii extends implements
{
    public Ii(final int[] array, final class class1) {
        super("ROC - Rate of Change", 2, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
        super.r[1] = super.Ua[0] + super.Ua[1] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        for (int i = super.Ua[0]; i < super.t[0].length; ++i) {
            if (_[i - super.Ua[0]] > 0.0) {
                super.t[0][i] = (_[i] / _[i - super.Ua[0]] - 1.0) * 100.0;
            }
            else {
                super.t[0][i] = 100.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[1]);
    }
}
