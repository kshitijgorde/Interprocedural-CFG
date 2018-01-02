// 
// Decompiled by Procyon v0.5.30
// 

package B;

public final class Z
{
    public double a;
    public double b;
    public double c;
    
    public Z() {
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
    }
    
    public Z(final double a, final double b, final double c) {
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final double a(final Z z) {
        return this.a * z.a + this.b * z.b + this.c * z.c;
    }
    
    public final double a() {
        return this.a * this.a + this.b * this.b + this.c * this.c;
    }
    
    public final Z a(final double n, final double n2) {
        return new Z(this.a, n * this.b - n2 * this.c, n2 * this.b + n * this.c);
    }
}
