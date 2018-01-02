// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    public double p;
    public double d;
    public double a;
    
    public final boolean n(final double n) {
        return Math.abs(n) <= this.p;
    }
    
    public final boolean c(final double n) {
        return Math.abs(n) <= this.a;
    }
    
    public final boolean p(final double n) {
        return Math.abs(n) > this.p;
    }
    
    public final boolean d(final double n) {
        return Math.abs(n) > this.d;
    }
    
    public final boolean b(final double n) {
        return n < -this.p;
    }
    
    public final boolean v(final double n) {
        return n > this.p;
    }
    
    public final boolean l(final double n) {
        return n < -this.a;
    }
    
    public final boolean e(final double n) {
        return n > this.a;
    }
    
    public final boolean a(final double n) {
        return n <= this.p;
    }
    
    public final boolean i(final double n) {
        return n >= -this.p;
    }
    
    public final boolean p(double n, double n2, final double n3, final char c) {
        if (n2 < n) {
            final double n4 = n;
            n = n2;
            n2 = n4;
        }
        return this.a(n - n3) && this.a(n3 - n2);
    }
    
    public final boolean d(final double n, final double n2) {
        return (this.a(n) && this.a(n2)) || (this.i(n) && this.i(n2));
    }
    
    public final boolean p(final double n, final double n2) {
        return (this.a(n) && this.i(n2)) || (this.i(n) && this.a(n2));
    }
    
    public final double p(final double n, final double n2, final double n3, final double n4, final double n5) {
        return (n - n2) * Math.cos(n5) - (n3 - n4) * Math.sin(n5) + n2;
    }
    
    public final double d(final double n, final double n2, final double n3, final double n4, final double n5) {
        return (n - n2) * Math.sin(n5) + (n3 - n4) * Math.cos(n5) + n4;
    }
    
    public r() {
        this.p = 1.0E-6;
        this.d = 1.0E-10;
        this.a = 0.001;
    }
}
