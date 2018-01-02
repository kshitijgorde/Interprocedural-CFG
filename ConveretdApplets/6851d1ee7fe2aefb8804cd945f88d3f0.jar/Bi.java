// 
// Decompiled by Procyon v0.5.30
// 

public class Bi extends implements
{
    public Bi(final int[] array, final class class1) {
        super("OBV - On Balance Volume", 1, null, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = 1;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        final double[] f = super.s.f();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        super.t[0][0] = 0.0;
        for (int i = 1; i < super.t[0].length; ++i) {
            if (_[i] > _[i - 1]) {
                super.t[0][i] = super.t[0][i - 1] + f[i];
            }
            else if (_[i] < _[i - 1]) {
                super.t[0][i] = super.t[0][i - 1] - f[i];
            }
            else {
                super.t[0][i] = super.t[0][i - 1];
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
