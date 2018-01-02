// 
// Decompiled by Procyon v0.5.30
// 

public class ti extends implements
{
    public ti(final int[] array, final class class1) {
        super("FI - Force Index", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (_ == null || f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        super.t[0][0] = 0.0;
        for (int i = 1; i < _.length; ++i) {
            super.t[0][i] = (_[i] - _[i - 1]) * f[i];
        }
        do.calculateExpAvg(super.t[0], super.t[0], 1, super.Ua[0]);
        do._(super.t[0], super.r[0], 0.0);
    }
}
