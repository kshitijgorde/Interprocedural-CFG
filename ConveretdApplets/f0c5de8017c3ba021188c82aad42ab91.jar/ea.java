// 
// Decompiled by Procyon v0.5.30
// 

public class ea implements d3
{
    private d7 a;
    
    public ea(final d7 a) {
        if (a == null) {
            throw new RuntimeException("QuotientDisplayValueGetter<init>: divisor is null");
        }
        this.a = a;
    }
    
    public double a(final d7 d7, final int n, final int n2) {
        final double a = this.a.a(n, n2);
        if (a != 0.0) {
            return d7.b(n, n2) / a;
        }
        return 0.0;
    }
}
