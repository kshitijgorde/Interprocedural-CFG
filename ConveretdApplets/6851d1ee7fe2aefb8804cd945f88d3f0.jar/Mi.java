// 
// Decompiled by Procyon v0.5.30
// 

public class Mi extends implements
{
    public Mi(final int[] array, final class class1) {
        super("StDev - Standard Deviation", 1, array, null, class1);
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
        do.calculateStdDev(_, super.t[0], 0, super.Ua[0]);
        do._(super.t[0], super.Ua[0], 0.0);
    }
}
