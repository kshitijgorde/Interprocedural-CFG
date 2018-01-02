// 
// Decompiled by Procyon v0.5.30
// 

public class x extends r
{
    public c p;
    public double p;
    public double d;
    public double a;
    
    public final void p(final l l, final l i) {
        this.p = i.d - l.d;
        this.d = l.p - i.p;
        this.a = -this.p * l.p - this.d * l.d;
    }
    
    public final boolean p(final i i, final l l, final l j) {
        i.p = false;
        i.d = false;
        final double n = this.p * l.p + this.d * l.d + this.a;
        final double n2 = this.p * j.p + this.d * j.d + this.a;
        if (this.n(n) || this.n(n2)) {
            i.p = this.n(n);
            i.d = this.n(n2);
            return false;
        }
        return (this.l(n) && this.l(n2)) || (this.e(n) && this.e(n2));
    }
    
    public x(final c p) {
        this.p = p;
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
    }
    
    public x(final l l, final l i, final c p3) {
        this.p = p3;
        this.p = i.d - l.d;
        this.d = l.p - i.p;
        this.a = -this.p * l.p - this.d * l.d;
    }
}
