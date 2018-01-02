// 
// Decompiled by Procyon v0.5.30
// 

public class Cda extends public
{
    public Cda(final int[] array, final b b) {
        super("MI - Mass Index", 1, null, new double[] { 26.5, 27.0 }, b);
        this.G();
    }
    
    protected void G() {
        super.Uoa[0] = 40;
    }
    
    protected void H() {
        final double[] _ = super.Voa._();
        final double[] g = super.Voa.g();
        if (_ == null || g == null) {
            super.Woa = null;
            return;
        }
        super.Woa = new double[super.Xoa][_.length];
        final double[] array = new double[_.length];
        for (int i = 0; i < _.length; ++i) {
            array[i] = _[i] - g[i];
        }
        final double[] array2 = new double[_.length];
        e.calculateExpAvg(array, array2, 0, 9);
        e.calculateExpAvg(array2, array, 8, 9);
        for (int j = 0; j < _.length; ++j) {
            if (array[j] != 0.0) {
                array[j] = array2[j] / array[j];
            }
            else {
                array[j] = 0.0;
            }
        }
        e._(array, super.Woa[0], 16, 25);
        e.a(super.Woa[0], 40, 0.0);
    }
}
