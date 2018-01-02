// 
// Decompiled by Procyon v0.5.30
// 

public final class U
{
    public I a;
    public N b;
    public Q c;
    public O d;
    public i e;
    public i f;
    public i g;
    public ad h;
    public p i;
    public a j;
    public String k;
    boolean l;
    
    public U(final I a) {
        this.l = false;
        W.l = this;
        this.a = a;
        this.e = new i(this, 65536);
        this.f = new i(this, 32768);
        this.g = new i(this, 256);
        this.b = new N(this);
        this.i = new p();
        this.c = new Q(this);
        this.d = new O(this);
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
        if (W.h && !this.d.d()) {
            this.d.a();
        }
        if (this.j != null && this.j.a() && !this.b.e()) {
            this.b.c();
            this.l = true;
        }
    }
    
    public final void b() {
        if (this.b.e()) {
            this.b.d();
            this.l = false;
        }
        if (W.h && this.d.d()) {
            this.d.b();
        }
    }
    
    private void d() {
        final short d = W.d;
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
    
    public final boolean a(final String k) {
        if (this.l) {
            this.b();
        }
        (this.j = new a(this)).a(k);
        if (this.j.a()) {
            this.c();
            (this.h = this.j.e()).a(this);
            this.b.a(this.h);
            this.h.a(this.j);
            this.c.a(this.j.d());
            this.k = k;
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
        final E c;
        if ((c = this.a.c) != null) {
            c.a();
        }
    }
    
    public final void a(final boolean h) {
        final boolean l;
        if (l = this.l) {
            this.b();
        }
        if (h) {
            this.d.a();
        }
        else {
            this.d.b();
        }
        W.h = h;
        if (l) {
            this.a();
        }
    }
}
