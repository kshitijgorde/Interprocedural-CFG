// 
// Decompiled by Procyon v0.5.30
// 

public final class V
{
    public J a;
    public O b;
    public R c;
    public P d;
    public h e;
    public h f;
    public h g;
    public ag h;
    public p i;
    public a j;
    public ad k;
    public String l;
    boolean m;
    
    public V(final J a) {
        this.m = false;
        X.l = this;
        this.a = a;
        this.e = new h(this, 65536);
        this.f = new h(this, 32768);
        this.g = new h(this, 256);
        this.b = new O(this);
        this.i = new p();
        this.c = new R(this);
        this.d = new P(this);
        this.k = new ad();
        for (int i = 0; i < 20; ++i) {
            if (i == 16) {
                this.d.a(16400, (short)16);
            }
            else {
                this.d.a(i + 16384, (short)0);
            }
        }
        if (!this.i.a()) {
            this.i.b();
        }
        this.b.a();
        this.c.a();
        this.a(true);
        this.d();
    }
    
    public final void a() {
        if (X.h && !this.d.d()) {
            this.d.a();
        }
        if (this.j != null && this.j.a() && !this.b.e()) {
            this.b.c();
            this.m = true;
        }
    }
    
    public final void b() {
        if (this.b.e()) {
            this.b.d();
            this.m = false;
        }
        if (X.h && this.d.d()) {
            this.d.b();
        }
    }
    
    private void d() {
        final short d = X.d;
        for (int i = 0; i < 8192; ++i) {
            this.e.a[i] = d;
        }
        for (int j = 0; j < 4; ++j) {
            final int n = j << 11;
            this.e.a[n + 8] = 247;
            this.e.a[n + 9] = 239;
            this.e.a[n + 10] = 223;
            this.e.a[n + 15] = 191;
        }
    }
    
    public final boolean a(final String l) {
        if (this.m) {
            this.b();
        }
        (this.j = new a(this)).a(l);
        if (this.j.a()) {
            this.c();
            (this.h = this.j.e()).a(this);
            this.b.a(this.h);
            this.h.a(this.j);
            this.c.a(this.j.d());
            if (this.k.a() > 0) {
                this.h.a(true);
            }
            this.l = l;
        }
        return this.j.a();
    }
    
    public final void c() {
        if (this.j != null) {
            this.j.g();
        }
        if (this.h != null) {
            this.h.a();
        }
        this.e.a();
        this.f.a();
        this.g.a();
        this.d();
        this.b.b();
        this.b.a();
        this.c.g();
        this.i.c();
        this.d.c();
        final F c;
        if ((c = this.a.c) != null) {
            c.a();
        }
    }
    
    public final void a(final boolean h) {
        final boolean m;
        if (m = this.m) {
            this.b();
        }
        if (h) {
            this.d.a();
        }
        else {
            this.d.b();
        }
        X.h = h;
        if (m) {
            this.a();
        }
    }
}
