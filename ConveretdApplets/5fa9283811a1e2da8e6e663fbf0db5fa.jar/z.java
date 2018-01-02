// 
// Decompiled by Procyon v0.5.30
// 

public class z extends r
{
    public double p;
    public double d;
    public double a;
    public boolean p;
    public boolean d;
    
    public final void p() {
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
        this.p = false;
        this.d = false;
    }
    
    public final void v(final z z) {
        this.p = z.p;
        this.d = z.d;
        this.a = z.a;
        this.p = z.p;
        this.d = z.d;
    }
    
    public final void n(final z z) {
        this.p = z.p;
        this.d = z.d;
        this.a = z.a;
        this.p = z.p;
        this.d = z.d;
    }
    
    public final void p(final double p3, final double d, final double a) {
        this.p = p3;
        this.d = d;
        this.a = a;
        this.p = true;
        this.d = false;
    }
    
    public final void d(final z z, final f f) {
        this.p = z.p + f.p;
        this.d = z.d + f.d;
        this.a = z.a + f.a;
        this.p = false;
        this.d = false;
    }
    
    public final void p(final z z, final f f) {
        this.p = z.p - f.p;
        this.d = z.d - f.d;
        this.a = z.a - f.a;
    }
    
    public final void a(final z z, final f f) {
        this.p = z.p - f.p;
        this.d = z.d - f.d;
        this.a = z.a - f.a;
        this.p = false;
        this.d = false;
    }
    
    public final void p(final z z, final f f, final double n) {
        final f f2 = new f(f);
        f2.p(n);
        this.d(z, f2);
    }
    
    public final void a(final z z) {
        final z z2 = new z(this.p, this.d, this.a, this.p, this.d);
        this.n(z);
        z.n(z2);
    }
    
    public final void p(final l l, final a a, final c c) {
        final double p3 = a.p;
        final double d = a.d;
        final double a2 = a.a;
        final double n = a.n;
        final double e = c.e;
        final double f = c.f;
        final double p4 = l.p;
        final double d2 = l.d;
        final double a3 = c.a;
        final double n2 = a3 * (p3 * c.p - d * c.d - a2 * a3 - n);
        final double n3 = p3 * (p4 - e) + d * (f - d2) - a2 * a3;
        if (this.d(n3)) {
            this.a = a3 - n2 / n3;
            if (this.d(a3)) {
                this.p = (p4 - e) * (a3 - this.a) / a3 - c.p;
                this.d = c.d - (d2 - f) * (a3 - this.a) / a3;
            }
            else {
                this.p = 0.0;
                this.d = 0.0;
                System.out.println("Err:backToSpace Zc==0:" + a3);
            }
        }
        else {
            this.a = 0.0;
            this.p = 0.0;
            this.d = 0.0;
            System.out.println("Err:backToSpace denom==0:" + n3);
        }
    }
    
    public final void d(final z z) {
        this.p += z.p;
        this.d += z.d;
        this.a += z.a;
    }
    
    public final void p(final z z) {
        this.p -= z.p;
        this.d -= z.d;
        this.a -= z.a;
    }
    
    public final void p(final double n) {
        this.p /= n;
        this.d /= n;
        this.a /= n;
    }
    
    public final void d(final double n) {
        this.p *= n;
        this.d *= n;
        this.a *= n;
    }
    
    public final void p(final z z, final z z2, final double n) {
        this.n(z2);
        this.p(z);
        this.d(n);
        this.d(z);
    }
    
    public final double p(final z z) {
        return Math.sqrt((this.p - z.p) * (this.p - z.p) + (this.d - z.d) * (this.d - z.d) + (this.a - z.a) * (this.a - z.a));
    }
    
    public final double p(final z z, final c c) {
        final l l = new l(this, c);
        final l i = new l(z, c);
        return Math.sqrt((l.p - i.p) * (l.p - i.p) + (l.d - i.d) * (l.d - i.d));
    }
    
    public z() {
        this.p();
    }
    
    public z(final z z) {
        this.n(z);
    }
    
    public z(final z z, final f f) {
        this.d(z, f);
    }
    
    public z(final double p3, final double d, final double a) {
        this.p = p3;
        this.d = d;
        this.a = a;
        this.p = false;
        this.d = false;
    }
    
    public z(final double p5, final double d, final double a, final boolean p6, final boolean d2) {
        this.p = p5;
        this.d = d;
        this.a = a;
        this.p = p6;
        this.d = d2;
    }
}
