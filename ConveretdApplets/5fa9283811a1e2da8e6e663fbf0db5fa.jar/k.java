// 
// Decompiled by Procyon v0.5.30
// 

public class k extends r
{
    public c p;
    public double p;
    public double d;
    public double a;
    
    public final boolean p(final z z, final z z2, final z z3, final z z4) {
        final double n = this.p.a - this.p.n;
        return z.a > n || z2.a > n || z3.a > n || z4.a > n;
    }
    
    public final void p(final z z, final double n, final double n2, final double n3, final char c) {
        final double p5 = z.p;
        final double d = z.d;
        final double a = z.a;
        switch (c) {
            case 'x': {
                z.d = this.p(d, n, a, n2, n3);
                z.a = this.d(d, n, a, n2, n3);
                break;
            }
            case 'y': {
                z.p = this.p(p5, n, a, n2, n3);
                z.a = this.d(p5, n, a, n2, n3);
                break;
            }
            case 'z': {
                z.p = this.p(p5, n, d, n2, n3);
                z.d = this.d(p5, n, d, n2, n3);
                break;
            }
            default: {
                System.out.println("err: genRot3D: rot:" + c);
                break;
            }
        }
    }
    
    public final void p(final f f, final double n, final double n2, final double n3, final char c) {
        final z z = new z(f.p, f.d, f.a);
        this.p(z, n, n2, n3, c);
        f.p = z.p;
        f.d = z.d;
        f.a = z.a;
    }
    
    public final void d(final z z, final z z2, final double n) {
        this.p(z, z2.d, z2.a, n, 'x');
    }
    
    public final void d(final f f, final double n) {
        this.p(f, 0.0, 0.0, n, 'x');
        this.p(this.p.a, this.p.d, this.p.a, n, 'x');
    }
    
    public final void p(final z z, final z z2, final double n) {
        this.p(z, z2.p, z2.a, n, 'y');
    }
    
    public final void a(final f f, final double n) {
        this.p(f, 0.0, 0.0, n, 'y');
        this.p(this.p.a, -this.p.p, this.p.a, n, 'y');
    }
    
    public final void a(final z z, final z z2, final double n) {
        this.p(z, z2.p, z2.d, n, 'z');
    }
    
    public final void p(final f f, final double n) {
        this.p(f, 0.0, 0.0, n, 'z');
        this.p(this.p.a, -this.p.p, this.p.d, n, 'z');
    }
    
    public final void p(final z z, final z z2, final l l, final g g, final g g2) {
        z.p(l, g.p.p, this.p);
        z2.p(l, g2.p.p, this.p);
    }
    
    public final boolean f(final double n) {
        return this.p.a > this.p.n + n;
    }
    
    public final boolean p(final boolean b, final double n) {
        return b && this.p.a < this.p.n + n;
    }
    
    public k() {
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
    }
    
    public k(final c p) {
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
        this.p = p;
    }
}
