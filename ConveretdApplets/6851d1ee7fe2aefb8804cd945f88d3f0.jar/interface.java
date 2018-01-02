// 
// Decompiled by Procyon v0.5.30
// 

public class interface extends implements
{
    public interface(final String s, final int[] array, final class class1) {
        super(s, 1, array, null, class1);
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
        do.calculateExpAvg(f, super.t[0], 0, super.Ua[0]);
    }
}
