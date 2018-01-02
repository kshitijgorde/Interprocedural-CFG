// 
// Decompiled by Procyon v0.5.30
// 

public class ed implements d3
{
    private d7 a;
    private d7 b;
    
    public ed(final d7 a, final d7 b) {
        this.a = a;
        this.b = b;
    }
    
    public double a(final d7 d7, final int n, final int n2) {
        final double a = this.a.a(n, n2);
        final double a2 = this.b.a(n, n2);
        if (a > 0.0) {
            return 100.0 * a2 / a;
        }
        return 0.0;
    }
}
