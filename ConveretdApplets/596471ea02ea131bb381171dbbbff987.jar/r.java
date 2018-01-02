// 
// Decompiled by Procyon v0.5.30
// 

public final class r extends ad
{
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    
    public r() {
        this.r = false;
    }
    
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(int m, final short n) {
        if (m < 32768) {
            super.a(m, n);
            return;
        }
        if (m == 32768) {
            this.l = (n & 0x7);
            if ((m = (n >> 6 & 0x1)) != this.m) {
                this.r = true;
            }
            this.m = m;
            this.n = (n >> 7 & 0x1);
            return;
        }
        if (m == 32769) {
            final int l = this.l;
            if (l == 0) {
                if (this.n == 0) {
                    this.e(n, 0);
                    this.e(n + 1, 1024);
                }
                else {
                    this.e(n, 4096);
                    this.e(n + 1, 5120);
                }
            }
            else if (l == 1) {
                if (this.n == 0) {
                    this.e(n, 2048);
                    this.e(n + 1, 3072);
                }
                else {
                    this.e(n, 6144);
                    this.e(n + 1, 7168);
                }
            }
            else if (l == 2) {
                if (this.n == 0) {
                    this.e(n, 4096);
                }
                else {
                    this.e(n, 0);
                }
            }
            else if (l == 3) {
                if (this.n == 0) {
                    this.e(n, 5120);
                }
                else {
                    this.e(n, 1024);
                }
            }
            else if (l == 4) {
                if (this.n == 0) {
                    this.e(n, 6144);
                }
                else {
                    this.e(n, 2048);
                }
            }
            else if (l == 5) {
                if (this.n == 0) {
                    this.e(n, 7168);
                }
                else {
                    this.e(n, 3072);
                }
            }
            else {
                if (l != 6) {
                    if (l == 7) {
                        this.g(n, 40960);
                        if (this.r) {
                            if (this.m == 0) {
                                this.g(this.a.j.b() - 1 << 1, 49152);
                            }
                            else {
                                this.g(this.a.j.b() - 1 << 1, 32768);
                            }
                            this.r = false;
                        }
                    }
                    return;
                }
                if (this.r) {
                    if (this.m == 0) {
                        this.g(this.a.j.b() - 1 << 1, 49152);
                    }
                    else {
                        this.g(this.a.j.b() - 1 << 1, 32768);
                    }
                    this.r = false;
                }
                if (this.m == 0) {
                    this.g(n, 32768);
                }
                else {
                    this.g(n, 49152);
                }
            }
        }
        else if (m == 40960) {
            if ((n & 0x1) != 0x0) {
                this.a.c.a(1);
                return;
            }
            this.a.c.a(0);
        }
        else {
            if (m == 40961) {
                this.a.j.a((n & 0x1) != 0x0);
                return;
            }
            if (m == 49152) {
                this.o = n;
                return;
            }
            if (m == 49153) {
                this.p = n;
                return;
            }
            if (m == 57344) {
                this.q = 0;
                return;
            }
            if (m == 57345) {
                this.q = 1;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.g(this.a.j.b() - 1 << 1, 49152);
        this.g((this.a.j.b() - 1 << 1) + 1, 57344);
        this.g(0, 32768);
        this.g(1, 40960);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void b() {
        if (this.q == 1) {
            --this.o;
            if (this.o < 0) {
                this.a.b.a(0);
                this.o = this.p;
            }
        }
    }
    
    public final void a() {
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = false;
    }
}
