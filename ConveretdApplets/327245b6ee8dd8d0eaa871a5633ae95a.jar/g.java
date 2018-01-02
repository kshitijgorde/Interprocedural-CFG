// 
// Decompiled by Procyon v0.5.30
// 

class g implements h
{
    p a;
    l b;
    b c;
    ac d;
    f e;
    String f;
    i g;
    boolean h;
    protected float[] i;
    g j;
    g k;
    g l;
    g m;
    protected a5 n;
    protected a5 p;
    protected a5 q;
    float r;
    float s;
    protected a5 t;
    boolean u;
    int v;
    int w;
    int x;
    float y;
    float z;
    float aa;
    float[] ab;
    float[] ac;
    float[] ad;
    float ae;
    float af;
    float ag;
    float ah;
    float ai;
    float aj;
    float ak;
    float al;
    float am;
    int an;
    static int ao;
    bb ap;
    boolean aq;
    int ar;
    c as;
    t at;
    
    public void a(final c as) {
        this.as = as;
    }
    
    public c a() {
        return this.as;
    }
    
    public void a(final t at) {
        if (this.at == null) {
            this.at = at;
        }
    }
    
    public g() {
        this.h = false;
        this.r = (float)Math.tan(0.46);
        this.u = true;
        this.v = -1;
        this.w = -1;
        this.y = 1.0f;
        this.z = 1.0f;
        this.aa = 1.0f;
        this.an = 0;
        this.ap = new bb();
        this.aq = false;
        this.a(new c());
        (this.n = new a5()).a(new c());
        this.n.a.c = this.n;
        this.p = new a5();
        this.q = this.n;
        this.i = new float[24];
        this.an = g.ao;
        ++g.ao;
        this.s = blaze3d.a().al.f.c / 20;
    }
    
    void b() {
        final boolean l = c.l;
        g k = this.k;
        while (true) {
            while (true) {
                Label_0024: {
                    if (!l) {
                        break Label_0024;
                    }
                    final g i = k.l;
                    final g g = i;
                    k.b();
                    k = g;
                }
                if (k != null) {
                    continue;
                }
                break;
            }
            this.k = null;
            final g i = this;
            if (!l) {
                if (this.g != null) {
                    this.g.k();
                }
                this.g = null;
                this.j = null;
                this.l = null;
                this.m = null;
                if (this.a != null) {
                    this.a.a();
                }
                this.a = null;
                this.ab = null;
                this.ac = null;
                this.ad = null;
                this.as.c = null;
                this.at = null;
                return;
            }
            continue;
        }
    }
    
    void c() {
        final int l = this.c.c.a.l;
        final int m = this.c.c.a.m;
        final int n = l + 31 & 0xFFFFFFE0;
        if (this.b == null || this.b.c != n || this.b.d != m) {
            this.b = new l(new int[n * m], n, m);
            this.a.a(this.b);
        }
    }
    
    void a(final g g, final boolean b) {
        if (this.c.f("objectTrackingEnabled").ag() != this.aq) {
            this.aq = !this.aq;
            this.a.s = this.aq;
        }
        this.c();
        if (this.a.a(this, g, b)) {
            this.c.b.f.a(this.e);
            this.d();
            this.c.b.f.a(this.e);
            this.c.b.z = null;
        }
    }
    
    void d() {
        this.e = new f(this.a.bc * 4, this.a.bd * 4, (this.a.be + 1) * 4, (this.a.bf + 1) * 4);
    }
    
    int e() {
        final boolean l = c.l;
        int n = 0;
        g g = this.k;
        while (true) {
            while (true) {
                Label_0030: {
                    if (!l) {
                        break Label_0030;
                    }
                    final int n2 = ++n + g.e();
                    final int n3;
                    n = n3;
                    g = g.l;
                }
                if (g != null) {
                    continue;
                }
                break;
            }
            final int n3 = n;
            if (!l) {
                return n3;
            }
            continue;
        }
    }
    
    int a(final g[] array, int n) {
        final boolean l = c.l;
        g g = this.k;
        while (true) {
            while (true) {
                Label_0034: {
                    if (!l) {
                        break Label_0034;
                    }
                    (array[n++] = g).a(array, n);
                    final int n2;
                    n = n2;
                    g = g.l;
                }
                if (g != null) {
                    continue;
                }
                break;
            }
            final int n2 = n;
            if (!l) {
                return n2;
            }
            continue;
        }
    }
    
    final void a(final boolean u) {
        g g = this.k;
        while (true) {
            Label_0021: {
                if (!c.l) {
                    break Label_0021;
                }
                g.a(u);
                g = g.l;
            }
            if (g == null) {
                if (u != this.u) {
                    this.u = u;
                    if (this.g != null) {
                        this.g.a(this.u);
                    }
                    this.f();
                }
                return;
            }
            continue;
        }
    }
    
    void f() {
        this.x = blaze3d.c;
    }
    
    boolean g() {
        return this.x > this.ar;
    }
    
    void h() {
        this.ar = blaze3d.c;
    }
    
    boolean i() {
        final boolean g = this.g();
        final boolean c = this.n.c();
        boolean b = false;
        if (this.j != null) {
            this.j.i();
            final int ar = this.j.ar;
            b = (ar != this.v);
            this.v = ar;
        }
        if (g || c || b) {
            Label_0162: {
                if (this.j != null) {
                    this.q = this.n.b(this.j.q);
                    if (this.p != null) {
                        this.q = this.p.b(this.q);
                    }
                    this.y = this.j.y * this.z * this.aa / this.j.aa;
                    if (!c.l) {
                        break Label_0162;
                    }
                }
                this.q = this.n;
            }
            this.h();
            this.n.d();
            return true;
        }
        return false;
    }
    
    int j() {
        if (this.g != null) {
            final int e = this.g.e();
            if (e > this.w) {
                this.ad = new float[3 * this.g.g];
                if (this.g.e != null) {
                    this.ab = new float[3 * this.g.e.b];
                    this.ac = new float[this.g.e.b * 3];
                }
                this.w = e;
            }
            if (this.w > this.x) {
                this.x = this.w;
            }
        }
        return this.x;
    }
    
    public void a(final i g) {
        if (this.g == g) {
            return;
        }
        if (this.g != null) {
            this.g.k();
        }
        (this.g = g).j();
        this.f();
    }
    
    public i k() {
        return this.g;
    }
    
    public void a(final a5 n) {
        this.n = n;
        this.f();
    }
    
    public a5 l() {
        return this.n;
    }
    
    public void b(final boolean b) {
        this.a(b);
        this.f();
    }
    
    public boolean m() {
        return this.u;
    }
    
    public void a(final float n) {
        this.r = (float)Math.tan(n / 114.59156f);
        this.f();
    }
    
    public float p() {
        return (float)(114.59156036376953 * Math.atan(this.r));
    }
    
    public void b(final float s) {
        this.s = s;
        this.f();
    }
    
    public float q() {
        return this.s;
    }
    
    public a5 a(final float ae, final float af, final float ag, final float ah, final float ai, final float aj, final float ak, final float al, final float am) {
        this.ae = ae;
        this.af = af;
        this.ag = ag;
        this.ah = ah;
        this.ai = ai;
        this.aj = aj;
        this.ak = ak;
        this.al = al;
        this.am = am;
        this.p = new a5(ak, 0.0f, 0.0f, 0.0f, al, 0.0f, 0.0f, 0.0f, am, 0.0f, 0.0f, 0.0f);
        this.n = new a5();
        final a5 a5 = new a5();
        a5.c(ah, ai, aj);
        (this.n = this.n.b(a5)).e(ae, af, ag);
        this.n.a(new c());
        this.n.a.c = this.n;
        return this.n;
    }
    
    public void a(final String f, final g g) {
        this.a(f);
        if (g == null) {
            return;
        }
        this.as.c(f, g.as);
        g.v();
        this.a(g);
        g.f = f;
        g.c = this.c;
        g.t();
        this.f();
    }
    
    void a(final String s) {
        g g = this.k;
        while (true) {
            Label_0031: {
                if (!c.l) {
                    break Label_0031;
                }
                if (g.f.equals(s)) {
                    g.v();
                }
                g = g.l;
            }
            if (g == null) {
                return;
            }
            continue;
        }
    }
    
    final void a(final g g) {
        g.l = this.k;
        g.m = null;
        if (this.k != null) {
            this.k.m = g;
        }
        this.k = g;
        g.j = this;
    }
    
    void t() {
        final boolean l = c.l;
        g k = this.k;
        while (true) {
            while (true) {
                Label_0022: {
                    if (!l) {
                        break Label_0022;
                    }
                    k.t();
                    final g i = k.l;
                    k = i;
                }
                if (k != null) {
                    continue;
                }
                break;
            }
            final g i = this;
            if (!l) {
                if (this.g != null && this.u) {
                    this.g.a(true);
                }
                return;
            }
            continue;
        }
    }
    
    void u() {
        final boolean l = c.l;
        g k = this.k;
        while (true) {
            while (true) {
                Label_0022: {
                    if (!l) {
                        break Label_0022;
                    }
                    k.u();
                    final g i = k.l;
                    k = i;
                }
                if (k != null) {
                    continue;
                }
                break;
            }
            final g i = this;
            if (!l) {
                if (this.g != null) {
                    this.g.a(false);
                }
                return;
            }
            continue;
        }
    }
    
    public void v() {
        if (this.f != null && this.j.as.f(this.f) == this.as) {
            this.j.as.e(this.f);
        }
        if (this.l != null) {
            this.l.m = this.m;
        }
        Label_0110: {
            if (this.m != null) {
                this.m.l = this.l;
                if (!c.l) {
                    break Label_0110;
                }
            }
            if (this.j != null) {
                this.j.k = this.l;
                this.j.f();
            }
        }
        if (this.j == null) {
            this.u();
        }
    }
    
    public String toString() {
        if (this.j != null) {
            return this.j.toString() + "." + this.f;
        }
        return this.f;
    }
    
    static {
        g.ao = 0;
    }
}
