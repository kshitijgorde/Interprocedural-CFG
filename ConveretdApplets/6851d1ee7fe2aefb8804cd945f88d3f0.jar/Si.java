// 
// Decompiled by Procyon v0.5.30
// 

public class Si extends implements
{
    public Si(final int[] array, final class class1) {
        super("VOS - Volume Oscillator", 2, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0], super.Ua[1]) - 1;
        super.r[1] = super.r[0] + super.Ua[2] - 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        do.calculateSimpleAvg(f, super.t[0], 0, super.Ua[0]);
        do.calculateSimpleAvg(f, super.t[1], 0, super.Ua[1]);
        for (int i = 0; i < _.length; ++i) {
            final double[] array = super.t[0];
            final int n = i;
            array[n] -= super.t[1][i];
        }
        do._(super.t[0], super.r[0], 0.0);
        do.calculateExpAvg(super.t[0], super.t[1], super.r[0], super.Ua[2]);
    }
}
