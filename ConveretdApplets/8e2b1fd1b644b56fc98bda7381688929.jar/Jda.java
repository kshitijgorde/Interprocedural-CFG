// 
// Decompiled by Procyon v0.5.30
// 

public class Jda extends public
{
    public Jda(final int[] array, final b b) {
        super("PVO - Percentage Volume Oscillator", 1, array, new double[] { 0.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = Math.max(super.Oa[0], super.Oa[1]) - 1;
    }
    
    protected void H() {
        final double[] i = super.Voa.i();
        if (i == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][i.length];
        final double[] array = new double[i.length];
        e.calculateExpAvg(i, array, 0, super.Oa[0]);
        final double[] array2 = new double[i.length];
        e.calculateExpAvg(i, array2, 0, super.Oa[1]);
        for (int j = 0; j < i.length; ++j) {
            if (array[j] != 0.0) {
                super.Woa[0][j] = 100.0 * (array[j] - array2[j]) / array[j];
            }
            else {
                super.Woa[0][j] = 0.0;
            }
        }
        e.a(super.Woa[0], super.Uoa[0], 0.0);
    }
}