// 
// Decompiled by Procyon v0.5.30
// 

public class Ui extends implements
{
    public Ui(final int[] array, final class class1) {
        super("VROC - Volume Rate of Change", 1, array, new double[] { 0.0 }, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] f = super.s.f();
        if (f == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][f.length];
        for (int i = super.Ua[0]; i < super.t[0].length; ++i) {
            if (f[i - super.Ua[0]] > 0.0) {
                super.t[0][i] = (f[i] / f[i - super.Ua[0]] - 1.0) * 100.0;
            }
            else {
                super.t[0][i] = 100.0;
            }
        }
        do._(super.t[0], super.r[0], 0.0);
    }
}
