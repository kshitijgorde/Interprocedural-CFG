// 
// Decompiled by Procyon v0.5.30
// 

public class ei extends implements
{
    public ei(final int[] array, final class class1) {
        super("ACC - Acceleration", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] + super.Ua[1];
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[_.length];
        for (int i = super.Ua[0]; i < array.length; ++i) {
            array[i] = _[i] - _[i - super.Ua[0]];
        }
        do._(array, super.Ua[0], 0.0);
        for (int j = super.r[0]; j < super.t[0].length; ++j) {
            super.t[0][j] = array[j] - array[j - super.Ua[1]];
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
