// 
// Decompiled by Procyon v0.5.30
// 

public class n extends k
{
    public double p;
    public double d;
    public double a;
    public double n;
    public int p;
    public f p;
    public f d;
    public f a;
    public f n;
    public f v;
    public f i;
    public v p;
    public v d;
    
    public final void p(final double p4, final double d, final double a, final double n) {
        this.p = p4;
        this.d = d;
        this.a = a;
        this.n = n;
    }
    
    public final void p(final f f, final f f2, final double n, final double n2, final f f3, final f f4) {
        final f f5 = new f(f4);
        f5.p();
        f.p(n, f5);
        f2.p(n2, f5);
        final double p6 = f.p();
        final double p7 = f2.p();
        final double p8 = f4.p();
        final double n3 = p8 - p6;
        final double n4 = p6 + p7;
        if (this.n(n4)) {
            System.out.print("Err:loadConstantVectors" + n4);
            return;
        }
        final double n5 = n3 / n4;
        double floor = Math.floor(n5);
        if (this.n(n4 * (n5 - floor) - n4)) {
            ++floor;
        }
        this.p = (int)floor + 1;
        f3.p((p8 - p6 * this.p - p7 * (this.p - 1)) / 2.0, f5);
    }
    
    public final void p(final z z, final z z2, final z z3, final z z4, final boolean b) {
        if (this.p(z, z2, z3, z4) || 1.5 < z.p(z2, super.p) || 1.5 < z3.p(z4, super.p)) {
            this.d.p(z, z2, z4, z3);
            this.d.p(b);
        }
    }
    
    public final void d(final z z, final z z2, final z z3, final z z4, final boolean b) {
        final z z5 = new z(z);
        final z z6 = new z(z5, this.a);
        final z z7 = new z(z3);
        final z z8 = new z(z7, this.i);
        this.p(z, z6, z3, z8, b);
        for (int i = 0; i < this.p - 1; ++i) {
            z5.d(z6, this.p);
            z6.d(z5, this.d);
            z7.d(z8, this.n);
            z8.d(z7, this.v);
            this.p(z5, z6, z7, z8, false);
        }
        z5.a(z2, this.a);
        z7.a(z4, this.i);
        this.p(z5, z2, z7, z4, b);
    }
    
    public final void p(final boolean b) {
        final f f = new f(this.p.p[2].a, this.p.p[2].p);
        final f f2 = new f(this.p.p[0].p, this.p.p[0].a);
        this.p(this.p, this.d, this.p, this.a, this.a, f);
        this.p(this.n, this.v, this.p, this.a, this.i, f2);
        this.d(this.p.p[2].a, this.p.p[2].p, this.p.p[0].p, this.p.p[0].a, b);
    }
    
    public final void d(final boolean b) {
        final f f = new f(this.p.p[3].p, this.p.p[3].a);
        final f f2 = new f(this.p.p[1].a, this.p.p[1].p);
        this.p(this.p, this.d, this.d, this.n, this.a, f);
        this.p(this.n, this.v, this.d, this.n, this.i, f2);
        this.d(this.p.p[3].p, this.p.p[3].a, this.p.p[1].a, this.p.p[1].p, b);
    }
    
    public final void a(final boolean b) {
        final f f = new f(this.p.p[1].a, this.p.p[1].p);
        final f f2 = new f(this.p.p[0].p, this.p.p[0].a);
        this.p(this.p, this.d, this.p, this.a, this.a, f);
        this.p(this.n, this.v, this.p, this.a, this.i, f2);
        this.d(this.p.p[1].a, this.p.p[1].p, this.p.p[0].p, this.p.p[0].a, b);
    }
    
    public final void p() {
        if (this.p.v > 3) {
            this.d(true);
            this.p(true);
        }
        else {
            this.a(false);
            this.p(false);
        }
    }
    
    public n(final v p2, final c p3) {
        this.p = -1.0;
        this.d = -1.0;
        this.a = -1.0;
        this.n = -1.0;
        this.p = 0;
        this.p = new f();
        this.d = new f();
        this.a = new f();
        this.n = new f();
        this.v = new f();
        this.i = new f();
        this.p = p2;
        super.p = p3;
        this.d = new v(4, p2.p, p2.d, p2.p, p2.p, super.p);
        if (this.p.p.e) {
            this.p(5.0, 5.0, 5.0, 5.0);
        }
        else {
            final y p4 = this.p.p;
            this.p(p4.n, p4.v, p4.i, p4.l);
        }
    }
}
