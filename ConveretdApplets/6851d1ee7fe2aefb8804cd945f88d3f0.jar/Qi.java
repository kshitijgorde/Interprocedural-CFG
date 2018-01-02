// 
// Decompiled by Procyon v0.5.30
// 

public class Qi extends implements
{
    public Qi(final int[] array, final class class1) {
        super("BOS - Bollinger Oscillator", 1, array, new double[] { 2.0, -2.0 }, class1);
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
        final double[] array = new double[_.length];
        final double[] array2 = new double[_.length];
        do.calculateSimpleAvg(_, array, 0, super.Ua[0]);
        do.calculateStdDev(_, array2, 0, super.Ua[0]);
        for (int i = super.r[0]; i < super.t[0].length; ++i) {
            if (array2[i] > 0.0) {
                super.t[0][i] = 1.0 * (_[i] - array[i]) / array2[i];
            }
            else {
                super.t[0][i] = 1.0;
            }
        }
        do._(super.t[0], super.r[0], 1.0);
    }
}
