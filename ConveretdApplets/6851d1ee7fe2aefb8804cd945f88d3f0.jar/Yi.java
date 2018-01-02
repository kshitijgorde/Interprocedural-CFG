// 
// Decompiled by Procyon v0.5.30
// 

public class Yi extends implements
{
    public Yi(final String s, final int[] array, final class class1) {
        super(s, 2, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0] - 1;
        super.r[1] = super.Ua[0] - 1;
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
            super.t[0][i] = array[i] - array2[i] * super.Ua[1];
            super.t[1][i] = array[i] + array2[i] * super.Ua[1];
            if (super.t[0][i] < 0.0) {
                super.t[0][i] = 0.0;
            }
        }
        do._(super.t[0], super.r[0], _[0]);
        do._(super.t[1], super.r[0], _[0]);
    }
}
