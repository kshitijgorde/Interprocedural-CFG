// 
// Decompiled by Procyon v0.5.30
// 

package b.a.a;

public class d extends c
{
    protected double c;
    
    public d() {
        this.c = 0.0;
    }
    
    public d(final a a, final a a2, final double c) {
        super(a, a2);
        this.c = c;
    }
    
    public d(final double n, final double n2, final double c) {
        super(n, n2);
        this.c = c;
    }
    
    public d(final double n, final int n2, final double n3, final int n4, final double c) {
        super(n, n2, n3, n4);
        this.c = c;
    }
    
    public d(final c c, final double c2) {
        super(c.a(), c.d());
        this.c = c2;
    }
    
    public double f() {
        return this.c;
    }
    
    public d a(final d d) {
        final a a = d.a;
        final a b = d.b;
        final double c = d.c;
        final a a2 = this.a;
        final a b2 = this.b;
        final double c2 = this.c;
        return a(c2 * b2.d() * a2.d() - c * b.d() * a.d(), c2 * b2.d() * a2.c() - c * b.d() * a.c(), c2 * b2.c() - c * b.c());
    }
    
    public static d a(final double n, final double n2, final double n3) {
        return new d(a.b(n2, n), a.a(n3, Math.sqrt(n * n + n2 * n2)), Math.sqrt(n * n + n2 * n2 + n3 * n3));
    }
    
    public boolean equals(final Object o) {
        return o != null && this.getClass() == o.getClass() && (this == o || (this.a.equals(((d)o).a) && this.b.equals(((d)o).b) && this.c == ((d)o).c));
    }
    
    public String toString() {
        return "(lat: " + this.b + ", lon: " + this.a + ", rad: " + this.c + ")";
    }
}
