// 
// Decompiled by Procyon v0.5.30
// 

public class Fi extends implements
{
    public Fi(final int[] array, final class class1) {
        super("PVO - Percentage Volume Oscillator", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = Math.max(super.Ua[0], super.Ua[1]) - 1;
    }
    
    protected void X() {
        final double[] f = super.s.f();
        if (f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][f.length];
        final double[] array = new double[f.length];
        do.calculateExpAvg(f, array, 0, super.Ua[0]);
        final double[] array2 = new double[f.length];
        do.calculateExpAvg(f, array2, 0, super.Ua[1]);
        for (int i = 0; i < f.length; ++i) {
            if (array[i] != 0.0) {
                super.t[0][i] = 100.0 * (array[i] - array2[i]) / array[i];
            }
            else {
                super.t[0][i] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
