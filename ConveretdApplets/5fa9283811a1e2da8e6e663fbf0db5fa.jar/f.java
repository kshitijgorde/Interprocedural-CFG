// 
// Decompiled by Procyon v0.5.30
// 

public class f extends r
{
    public double p;
    public double d;
    public double a;
    
    public final void p() {
        final double sqrt = Math.sqrt(this.p * this.p + this.d * this.d + this.a * this.a);
        this.p /= sqrt;
        this.d /= sqrt;
        this.a /= sqrt;
    }
    
    public final void p(final f f) {
        this.p = f.p;
        this.d = f.d;
        this.a = f.a;
    }
    
    public final void p(final z z, final z z2) {
        this.p = z2.p - z.p;
        this.d = z2.d - z.d;
        this.a = z2.a - z.a;
    }
    
    public final void d() {
        this.p = -this.p;
        this.d = -this.d;
        this.a = -this.a;
    }
    
    public final boolean p(final z z, final z z2) {
        final z z3 = new z();
        final z z4 = new z();
        z3.d(z2, this);
        z4.p(z2, this);
        return z3.p(z) < z4.p(z);
    }
    
    public final void d(final f f) {
        this.p += f.p;
        this.d += f.d;
        this.a += f.a;
    }
    
    public final void d(final double n) {
        this.p /= n;
        this.d /= n;
        this.a /= n;
    }
    
    public final void p(final double n) {
        this.p *= n;
        this.d *= n;
        this.a *= n;
    }
    
    public final void p(final double n, final f f) {
        this.p = n * f.p;
        this.d = n * f.d;
        this.a = n * f.a;
    }
    
    public final double p() {
        return Math.sqrt(this.p * this.p + this.d * this.d + this.a * this.a);
    }
    
    public final double d(final f f) {
        return f.p * this.p + f.d * this.d + f.a * this.a;
    }
    
    public final void p(final f f, final f f2) {
        this.p = f.d * f2.a - f.a * f2.d;
        this.d = f.a * f2.p - f.p * f2.a;
        this.a = f.p * f2.d - f.d * f2.p;
    }
    
    public final double p(final f f) {
        return this.d(f) / (this.p() * f.p());
    }
    
    public final double p(final double n, final double n2) {
        if (this.n(n) && this.n(n2)) {
            return 0.0;
        }
        if (this.n(n2)) {
            return -1.0;
        }
        if (this.n(n)) {
            return -1.0;
        }
        return n / n2;
    }
    
    public final boolean p(final f f) {
        final double p = this.p(this.p, f.p);
        if (this.b(p)) {
            return false;
        }
        final double p2 = this.p(this.d, f.d);
        if (this.b(p2)) {
            return false;
        }
        final double p3 = this.p(this.a, f.a);
        if (this.b(p3)) {
            return false;
        }
        final boolean b = this.n(p) || this.n(p2) || this.n(p - p2);
        final boolean b2 = this.n(p) || this.n(p3) || this.n(p - p3);
        final boolean b3 = this.n(p2) || this.n(p3) || this.n(p2 - p3);
        return b && b2 && b3;
    }
    
    public f() {
    }
    
    public f(final f f) {
        this.p(f);
    }
    
    public f(final z z, final z z2) {
        this.p(z, z2);
    }
    
    public f(final double p3, final double d, final double a) {
        this.p = p3;
        this.d = d;
        this.a = a;
    }
}
