// 
// Decompiled by Procyon v0.5.30
// 

public class w extends r
{
    public z p;
    public z d;
    public z a;
    public boolean p;
    public boolean d;
    public boolean a;
    public boolean n;
    public boolean v;
    public boolean i;
    public l p;
    public l d;
    public l a;
    
    private final void d() {
        this.p = new z();
        this.d = new z();
        this.a = new z();
    }
    
    private final void a() {
        final boolean p = this.p;
        this.p = this.a;
        this.a = p;
        final boolean n = this.n;
        this.n = this.i;
        this.i = n;
    }
    
    public final void p(final z z, final z z2) {
        this.p.n(z);
        this.d.p();
        this.a.n(z2);
        this.p = true;
        this.d = false;
        this.a = true;
    }
    
    public final void d(final z p2, final z a) {
        this.p = p2;
        this.a = a;
    }
    
    public final void p(final c c) {
        final dd dd = new dd(c);
        if (this.p) {
            this.p.p(this.p, c);
        }
        if (this.d) {
            this.d.p(this.d, c);
        }
        if (this.a) {
            this.a.p(this.a, c);
        }
        if (this.d) {
            if (this.p) {
                dd.p(this.d, this.p);
            }
            if (this.a) {
                dd.p(this.d, this.a);
            }
        }
        else {
            dd.p(this.p, this.a);
        }
        if (dd.p(c.p, c.d)) {
            dd.p(c);
        }
    }
    
    public final void p() {
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
    }
    
    public final boolean p(final i i, final c c) {
        final double a = c.a - c.n;
        this.p();
        i.p = true;
        if (this.a(this.p.a - a) && this.a(this.a.a - a)) {
            this.p = true;
            this.a = true;
            return false;
        }
        if (this.i(this.p.a - a) && this.i(this.a.a - a)) {
            return i.p = false;
        }
        if (!this.n(this.p.a - this.a.a)) {
            boolean b = false;
            if (this.a.a < this.p.a) {
                this.p.a(this.a);
                b = true;
            }
            final double n = this.p.a - this.a.a;
            double n2;
            double n3;
            if (this.p(n)) {
                n2 = (this.p.p - this.a.p) / n;
                n3 = (this.p.d - this.a.d) / n;
            }
            else {
                System.out.print("Err:compIntersect denum==0:" + n);
                n2 = 0.0;
                n3 = 0.0;
            }
            final double n4 = this.p.p - n2 * this.p.a;
            final double n5 = this.p.d - n3 * this.p.a;
            this.d.a = a;
            this.d.p = n2 * a + n4;
            this.d.d = n3 * a + n5;
            this.d = true;
            this.p = true;
            if (b) {
                this.p.a(this.a);
                this.a();
            }
            return this.v = true;
        }
        if (this.p.a == a) {
            this.p = true;
            this.a = true;
            this.v = true;
            this.n = true;
            this.i = true;
            return false;
        }
        return false;
    }
    
    public w() {
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.p = new l();
        this.d = new l();
        this.a = new l();
        this.d();
        this.p.p();
        this.d.p();
        this.a.p();
    }
    
    public w(final z z, final z z2) {
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.p = new l();
        this.d = new l();
        this.a = new l();
        this.d();
        this.p(z, z2);
    }
}
