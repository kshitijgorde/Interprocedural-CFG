// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public float g;
    public float h;
    
    public v() {
        this.e = 0;
        this.d = 0;
        this.a();
    }
    
    public void a() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
    }
    
    public void a(final v v) {
        this.a = v.a;
        this.b = v.b;
        this.c = v.c;
        this.d = v.d;
    }
    
    public boolean b() {
        return this.a != -1;
    }
    
    public void a(final d d) {
        if (this.a != -1) {
            d.i(this.a);
        }
        if (this.b != -1) {
            d.i(this.b);
        }
        if (this.c != -1) {
            d.i(this.c);
        }
        this.a();
    }
    
    public void a(final d d, final int n, final int n2, final int e, final int n3, final int d2) {
        this.d = d2;
        this.e = e;
        this.g = n;
        this.h = n2;
        d.f(this.a = d.a("s", n, n2, p.de[d2], true, 5), 1);
        d.a(this.a, 1.0f);
        d.f(this.b = d.a("s", n, n2, p.dh[e], false, 5), 1);
        d.a(this.b, 1.0f);
        this.b(d, 0);
    }
    
    public void b(final d d) {
        this.a(d, (int)this.g, (int)this.h, this.e, 0, this.d);
    }
    
    public void a(final d d, final int n) {
        d.m(this.a, n);
    }
    
    public void a(final d d, final float n) {
        if (this.a != -1) {
            d.a(this.a, n);
        }
        if (this.b != -1) {
            d.a(this.b, n);
        }
    }
    
    public void c(final d d) {
        d.i(this.c = d.a("s", (int)this.g, (int)this.h + 21, u.h(this.e), false, 0), this.f - 1);
    }
    
    public void d(final d d) {
        if (this.c != -1) {
            d.i(this.c);
            this.c = -1;
        }
    }
    
    public void a(final d d, final boolean b) {
        if (this.b != -1) {
            d.c(this.a, b);
        }
    }
    
    public boolean e(final d d) {
        return this.a != -1 && this.a == d.dl;
    }
    
    public void a(final d d, final float g, final float h) {
        this.g = g;
        this.h = h;
        if (this.a != -1) {
            d.b(this.a, (int)g, (int)h);
        }
        if (this.b != -1) {
            d.b(this.b, (int)g, (int)h);
        }
    }
    
    public void b(final d d, final int f) {
        this.f = f;
        if (this.a != -1) {
            d.i(this.a, f);
        }
        if (this.b != -1) {
            d.i(this.b, f + 1);
        }
    }
}
