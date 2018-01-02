// 
// Decompiled by Procyon v0.5.30
// 

public final class A
{
    private P e;
    private static int[] f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    int a;
    int b;
    private int o;
    int c;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    int d;
    
    public A(final P e, final boolean g) {
        this.e = e;
        this.g = g;
    }
    
    public final void a() {
        if (this.i && this.o > 0) {
            --this.o;
            if (this.o == 0) {
                this.d();
            }
        }
    }
    
    public final void b() {
        Label_0086: {
            A a;
            int v;
            if (this.m) {
                this.m = false;
                this.u = this.t + 1;
                a = this;
                v = 15;
            }
            else {
                if (--this.u > 0) {
                    break Label_0086;
                }
                this.u = this.t + 1;
                if (this.v > 0) {
                    a = this;
                    v = this.v - 1;
                }
                else {
                    a = this;
                    v = (this.l ? 15 : 0);
                }
            }
            a.v = v;
        }
        this.w = (this.k ? this.t : this.v);
        this.d();
    }
    
    public final void c() {
        final int p = this.p - 1;
        this.p = p;
        if (p <= 0) {
            this.p = this.q + 1;
            if (this.j && this.s > 0 && this.b > 7) {
                if (this.r == 0) {
                    this.b += this.b >> this.s;
                    if (this.b > 4095) {
                        this.b = 4095;
                    }
                }
                else {
                    this.b -= (this.b >> this.s) - (this.g ? 1 : 0);
                }
            }
        }
        if (this.n) {
            this.n = false;
            this.p = this.q + 1;
        }
    }
    
    public final void d() {
        if (this.h && this.o > 0 && this.b > 7 && (this.r != 0 || this.b + (this.b >> this.s) <= 4095)) {
            this.d = this.w * A.f[(this.x << 3) + this.c];
            return;
        }
        this.d = 0;
    }
    
    public final void a(final int n, final int n2) {
        final int n3 = this.g ? 0 : 4;
        if (n == n3 + 16384) {
            this.k = ((n2 & 0x10) != 0x0);
            this.t = (n2 & 0xF);
            this.l = ((n2 & 0x20) != 0x0);
            this.x = (n2 >> 6 & 0x3);
            this.i = ((n2 & 0x20) == 0x0);
            this.w = (this.k ? this.t : this.v);
            this.d();
            return;
        }
        if (n == n3 + 16385) {
            this.j = ((n2 & 0x80) != 0x0);
            this.q = (n2 >> 4 & 0x7);
            this.r = (n2 >> 3 & 0x1);
            this.s = (n2 & 0x7);
            this.n = true;
            return;
        }
        if (n == n3 + 16386) {
            this.b &= 0x700;
            this.b |= n2;
            return;
        }
        if (n == n3 + 16387) {
            this.b &= 0xFF;
            this.b |= (n2 & 0x7) << 8;
            if (this.h) {
                this.o = this.e.b(n2 & 0xF8);
            }
            this.m = true;
        }
    }
    
    public final void a(final boolean h) {
        if (!(this.h = h)) {
            this.o = 0;
        }
        this.d();
    }
    
    public final int e() {
        if (this.o == 0 || !this.h) {
            return 0;
        }
        return 1;
    }
    
    public final void f() {
        this.a = 0;
        this.b = 0;
        this.o = 0;
        this.c = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
    }
    
    public final void g() {
        this.e = null;
    }
    
    static {
        A.f = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        final int[] array = { 1, -1, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 1, 0, 0, 0, 0, 0 };
    }
}
