// 
// Decompiled by Procyon v0.5.30
// 

public final class r extends ag
{
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    
    public r() {
        this.t = false;
    }
    
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(int o, final short n) {
        if (o < 32768) {
            super.a(o, n);
            return;
        }
        if (o == 32768) {
            this.n = (n & 0x7);
            if ((o = (n >> 6 & 0x1)) != this.o) {
                this.t = true;
            }
            this.o = o;
            this.p = (n >> 7 & 0x1);
            return;
        }
        if (o == 32769) {
            final int n2 = this.n;
            if (n2 == 0) {
                if (this.p == 0) {
                    this.e(n, 0);
                    this.e(n + 1, 1024);
                }
                else {
                    this.e(n, 4096);
                    this.e(n + 1, 5120);
                }
            }
            else if (n2 == 1) {
                if (this.p == 0) {
                    this.e(n, 2048);
                    this.e(n + 1, 3072);
                }
                else {
                    this.e(n, 6144);
                    this.e(n + 1, 7168);
                }
            }
            else if (n2 == 2) {
                if (this.p == 0) {
                    this.e(n, 4096);
                }
                else {
                    this.e(n, 0);
                }
            }
            else if (n2 == 3) {
                if (this.p == 0) {
                    this.e(n, 5120);
                }
                else {
                    this.e(n, 1024);
                }
            }
            else if (n2 == 4) {
                if (this.p == 0) {
                    this.e(n, 6144);
                }
                else {
                    this.e(n, 2048);
                }
            }
            else if (n2 == 5) {
                if (this.p == 0) {
                    this.e(n, 7168);
                }
                else {
                    this.e(n, 3072);
                }
            }
            else {
                if (n2 != 6) {
                    if (n2 == 7) {
                        this.g(n, 40960);
                        if (this.t) {
                            if (this.o == 0) {
                                this.g(this.a.j.b() - 1 << 1, 49152);
                            }
                            else {
                                this.g(this.a.j.b() - 1 << 1, 32768);
                            }
                            this.t = false;
                        }
                    }
                    return;
                }
                if (this.t) {
                    if (this.o == 0) {
                        this.g(this.a.j.b() - 1 << 1, 49152);
                    }
                    else {
                        this.g(this.a.j.b() - 1 << 1, 32768);
                    }
                    this.t = false;
                }
                if (this.o == 0) {
                    this.g(n, 32768);
                }
                else {
                    this.g(n, 49152);
                }
            }
        }
        else if (o == 40960) {
            if ((n & 0x1) != 0x0) {
                this.a.c.a(1);
                return;
            }
            this.a.c.a(0);
        }
        else {
            if (o == 40961) {
                this.a.j.a((n & 0x1) != 0x0);
                return;
            }
            if (o == 49152) {
                this.q = n;
                return;
            }
            if (o == 49153) {
                this.r = n;
                return;
            }
            if (o == 57344) {
                this.s = 0;
                return;
            }
            if (o == 57345) {
                this.s = 1;
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
        if (this.s == 1) {
            --this.q;
            if (this.q < 0) {
                this.a.b.a(0);
                this.q = this.r;
            }
        }
    }
    
    public final void a() {
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = false;
    }
}
