// 
// Decompiled by Procyon v0.5.30
// 

public class Bisection
{
    public static Comparable find(Comparable min_x, Comparable max_x, final Comparable y, final Comparable delta, final MathOps ops) {
        Comparable mid = ops.mid(min_x, max_x);
        for (Comparable y_mid = ops.f(mid); ops.delta(y_mid, y).compareTo(delta) >= 0; y_mid = ops.f(mid)) {
            if (y_mid.compareTo(y) < 0) {
                min_x = mid;
            }
            else {
                max_x = mid;
            }
            mid = ops.mid(min_x, max_x);
        }
        return mid;
    }
    
    interface MathOps
    {
        Comparable mid(final Comparable p0, final Comparable p1);
        
        Comparable delta(final Comparable p0, final Comparable p1);
        
        Comparable f(final Comparable p0);
    }
}
