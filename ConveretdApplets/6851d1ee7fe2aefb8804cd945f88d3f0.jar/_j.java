// 
// Decompiled by Procyon v0.5.30
// 

public class _j extends implements
{
    public _j(final String s, final int[] array, final class class1) {
        super(s, 1, array, null, class1);
        this.W();
    }
    
    protected void W() {
        super.r[0] = super.Ua[0];
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        do.calculateSimpleAvg(_, super.t[0], 0, super.Ua[0]);
    }
}
