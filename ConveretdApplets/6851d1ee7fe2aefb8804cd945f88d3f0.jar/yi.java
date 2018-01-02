// 
// Decompiled by Procyon v0.5.30
// 

public class yi extends implements
{
    public yi(final int[] array, final class class1) {
        super("MI - Mass Index", 1, null, new double[] { 26.5, 27.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 40;
    }
    
    protected void X() {
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        if (a == null || b == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][a.length];
        final double[] array = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            array[i] = a[i] - b[i];
        }
        final double[] array2 = new double[a.length];
        do.calculateExpAvg(array, array2, 0, 9);
        do.calculateExpAvg(array2, array, 8, 9);
        for (int j = 0; j < a.length; ++j) {
            if (array[j] != 0.0) {
                array[j] = array2[j] / array[j];
            }
            else {
                array[j] = 0.0;
            }
        }
        do.a(array, super.t[0], 16, 25);
        do._(super.t[0], 40, 0.0);
    }
}
