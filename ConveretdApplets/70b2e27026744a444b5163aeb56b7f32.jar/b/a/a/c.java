// 
// Decompiled by Procyon v0.5.30
// 

package b.a.a;

public class c extends b
{
    protected a a;
    protected a b;
    
    public c() {
        final a a = b.a.a.a.a;
        this.b = a;
        this.a = a;
    }
    
    public c(final a a, final a b) {
        this.a = a;
        this.b = b;
    }
    
    public c(final double n, final double n2) {
        this.a = new a(n, -2);
        this.b = new a(n2);
    }
    
    public c(final double n, final int n2, final double n3, final int n4) {
        this.a = new a(n, n2, -2);
        this.b = new a(n3, n4);
    }
    
    public a a() {
        return this.a;
    }
    
    public a b() {
        return this.a;
    }
    
    public a c() {
        return this.b;
    }
    
    public a d() {
        return this.b;
    }
    
    public a e() {
        return this.b;
    }
    
    public boolean equals(final Object o) {
        return o != null && this.getClass() == o.getClass() && (this == o || (this.a.equals(((c)o).a) && this.b.equals(((c)o).b)));
    }
    
    public String toString() {
        return "(lat: " + this.b + ", lon: " + this.a + ")";
    }
}
