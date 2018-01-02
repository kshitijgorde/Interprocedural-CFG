// 
// Decompiled by Procyon v0.5.30
// 

public class l extends r
{
    public double p;
    public double d;
    public boolean p;
    public boolean d;
    public dd p;
    public l p;
    
    public final void d() {
        this.p = false;
        this.d = false;
        this.p = null;
        this.p = null;
    }
    
    public final void d(final double p2, final double d) {
        this.p = p2;
        this.d = d;
        this.d();
    }
    
    public final void i(final l l) {
        this.p = l.p;
        this.d = l.d;
        this.p = true;
    }
    
    public final void p() {
        this.d(0.0, 0.0);
    }
    
    public final void n(final l l) {
        this.d(l.p, l.d);
    }
    
    public final void p(final l l, final dd p3, final l p4) {
        this.p = l.p;
        this.d = l.d;
        this.p = true;
        this.d = true;
        this.p = p3;
        this.p = p4;
    }
    
    public final void p(final dd p2, final l p3) {
        this.p = true;
        this.d = true;
        this.p = p2;
        this.p = p3;
    }
    
    public final void d(final l l) {
        this.p = l.p;
        this.d = l.d;
    }
    
    public final void a(final double p2, final double d) {
        this.p = p2;
        this.d = d;
    }
    
    public final void p(final l l) {
        this.p = l.p;
        this.d = l.d;
        this.p = l.p;
        this.d = l.d;
        this.p = l.p;
        this.p = l.p;
    }
    
    public final void p(final u u) {
        this.p += u.p;
        this.d += u.d;
    }
    
    public final void d(final u u) {
        this.p -= u.p;
        this.d -= u.d;
    }
    
    public final void p(final double p2, final double d) {
        this.p = p2;
        this.d = d;
    }
    
    public final void a(final l l) {
        this.p = l.p;
        this.d = l.d;
    }
    
    public final void v(final l l) {
        this.p += l.p;
        this.d += l.d;
    }
    
    public final boolean p(final l l) {
        return l != null && this.c(this.p - l.p) && this.c(this.d - l.d);
    }
    
    public final boolean p(final l l, final l i) {
        return this.d && (!this.p(l) || this.p(i) || (this.d = false));
    }
    
    public final boolean d(final l l, final l i) {
        return !this.p(l) && !this.p(i);
    }
    
    public final void p(final z z, final c c) {
        final double n = c.a - z.a;
        if (this.v(n)) {
            final double n2 = c.a / n;
            this.p = c.e + (z.p + c.p) * n2;
            this.d = c.f - (z.d - c.d) * n2;
        }
        else {
            System.out.print("Err:load Proj:" + n);
            this.p = 0.0;
            this.d = 0.0;
        }
    }
    
    public final void a(final u u) {
        this.p += u.p;
        this.d += u.d;
    }
    
    public final void p(final double n, final double n2, final double n3) {
        final double p3 = this.p;
        final double d = this.d;
        this.p = this.p(p3, n, d, n2, n3);
        this.d = this.d(p3, n, d, n2, n3);
    }
    
    public l() {
        this.p();
    }
    
    public l(final l l) {
        this.p = l.p;
        this.d = l.d;
    }
    
    public l(final double n, final double n2) {
        this.d(n, n2);
    }
    
    public l(final z z, final c c) {
        this.d();
        this.p(z, c);
    }
}
