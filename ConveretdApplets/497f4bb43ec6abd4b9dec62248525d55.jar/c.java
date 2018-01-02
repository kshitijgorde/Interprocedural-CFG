// 
// Decompiled by Procyon v0.5.30
// 

final class c
{
    b o;
    c p;
    c q;
    c r;
    
    public c(final b o) {
        this.o = o;
        this.p = null;
        this.q = null;
        this.r = null;
    }
    
    public void a(final c q) {
        if (this.q != null) {
            this.q.a(q);
        }
        else {
            this.q = q;
        }
    }
    
    private boolean b(final c c) {
        return this.q != null && (this.q == c || this.q.b(c));
    }
    
    public c t() {
        if (this.p == null) {
            return this;
        }
        if (this.p.b(this)) {
            return this.p.t();
        }
        return this;
    }
    
    public void c(final c p) {
        this.p = p;
        if (this.r != null) {
            this.r.c(p);
        }
    }
    
    public void a(final c p2, final c c) {
        if (this.p == c) {
            this.p = p2;
        }
        if (this.q != null) {
            this.q.a(p2, c);
        }
        if (this.r != null) {
            this.r.a(p2, c);
        }
    }
    
    public boolean u() {
        boolean g = this.o.g();
        if (this.q != null) {
            g |= this.q.u();
        }
        if (this.r != null) {
            g |= this.r.u();
        }
        return g;
    }
}
