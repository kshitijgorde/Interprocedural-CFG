// 
// Decompiled by Procyon v0.5.30
// 

public class u extends r
{
    public double p;
    public double d;
    
    public final void p() {
        this.p = -this.p;
        this.d = -this.d;
    }
    
    public final double p() {
        return this.p * this.p + this.d * this.d;
    }
    
    public final void d() {
        final double sqrt = Math.sqrt(this.p * this.p + this.d * this.d);
        this.p /= sqrt;
        this.d /= sqrt;
    }
    
    public final void p(final l l, final l i) {
        this.p = i.p - l.p;
        this.d = i.d - l.d;
    }
    
    public final void a() {
        if (this.c(this.d)) {
            this.p = -this.d / this.p;
            this.d = 1.0;
        }
        else {
            this.d = -this.p / this.d;
            this.p = 1.0;
        }
    }
    
    public final void d(final l l, final l i) {
        this.p(l, i);
        this.a();
    }
    
    public final void p(final double n) {
        this.p *= n;
        this.d *= n;
    }
    
    public u() {
        this.p = 0.0;
        this.d = 0.0;
    }
}
