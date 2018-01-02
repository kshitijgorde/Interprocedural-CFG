// 
// Decompiled by Procyon v0.5.30
// 

public abstract class DoubleMathOps implements MathOps
{
    public Comparable mid(final Comparable x1, final Comparable x2) {
        final double x1_value = (double)x1;
        final double x2_value = (double)x2;
        return new Double((x1_value + x2_value) / 2.0);
    }
    
    public Comparable delta(final Comparable y1, final Comparable y2) {
        final double y1_value = (double)y1;
        final double y2_value = (double)y2;
        return new Double(Math.abs(y1_value - y2_value));
    }
    
    public abstract Comparable f(final Comparable p0);
}
