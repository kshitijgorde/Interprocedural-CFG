// 
// Decompiled by Procyon v0.5.30
// 

public class pi extends implements
{
    public pi(final int[] array, final class class1) {
        super("DPO - Detrended Price Oscillator", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1 + super.Ua[0] / 2 + 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        do.calculateSimpleAvg(_, array, 0, super.Ua[0]);
        int i;
        for (int n = i = super.Ua[0] / 2 + 1; i < _.length; ++i) {
            super.t[0][i] = _[i] - array[i - n];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
