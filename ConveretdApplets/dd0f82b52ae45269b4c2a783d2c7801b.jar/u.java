// 
// Decompiled by Procyon v0.5.30
// 

public final class u extends ag
{
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    
    public u() {
        this.o = 1;
        this.p = 1;
    }
    
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if ((n2 & 0x80) != 0x0) {
            this.u = 0;
            this.t = 0;
            if (d(n) == 0) {
                this.o = 1;
                this.p = 1;
            }
        }
        else {
            this.t = ((this.t & 255 - (1 << this.u)) | (n2 & 0x1) << this.u);
            ++this.u;
            if (this.u == 5) {
                final int d = d(n);
                final int t = this.t;
                final int n3 = d;
                if (n3 == 0) {
                    final int n4;
                    if ((n4 = (t & 0x3)) != this.n) {
                        this.n = n4;
                        R r;
                        int n5;
                        if ((this.n & 0x2) == 0x0) {
                            r = this.a.c;
                            n5 = 3;
                        }
                        else {
                            r = this.a.c;
                            n5 = (((this.n & 0x1) != 0x0) ? 1 : 0);
                        }
                        r.a(n5);
                    }
                    this.o = (t >> 2 & 0x1);
                    this.p = (t >> 3 & 0x1);
                    this.q = (t >> 4 & 0x1);
                }
                else if (n3 == 1) {
                    this.r = (t >> 4 & 0x1);
                    if (this.a.j.c() > 0) {
                        if (this.q == 0) {
                            if (this.r == 0) {
                                this.d(t & 0xF, 0);
                            }
                            else {
                                this.d(this.a.j.c() / 2 + (t & 0xF), 0);
                            }
                        }
                        else if (this.r == 0) {
                            this.b(t & 0xF, 0);
                        }
                        else {
                            this.b(this.a.j.c() / 2 + (t & 0xF), 0);
                        }
                    }
                }
                else if (n3 == 2) {
                    this.s = (t >> 4 & 0x1);
                    if (this.a.j.c() > 0 && this.q == 1) {
                        if (this.s == 0) {
                            this.b(t & 0xF, 4096);
                        }
                        else {
                            this.b(this.a.j.c() / 2 + (t & 0xF), 4096);
                        }
                    }
                }
                else {
                    final int n6 = t & 0xF;
                    int n7 = 0;
                    final int b;
                    if ((b = this.a.j.b()) >= 32) {
                        if (this.q == 0) {
                            if (this.r == 1) {
                                n7 = 16;
                            }
                        }
                        else {
                            n7 = (this.r | this.s << 1) << 3;
                        }
                    }
                    else if (b >= 16 && this.r == 1) {
                        n7 = 8;
                    }
                    if (this.p == 0) {
                        this.c(n7 + n6, 32768);
                    }
                    else {
                        final int n8 = (n7 << 1) + n6;
                        if (this.o == 0) {
                            this.a(n8, 49152);
                        }
                        else {
                            this.a(n8, 32768);
                        }
                    }
                }
                this.t = 0;
                this.u = 0;
            }
        }
    }
    
    private static int d(final int n) {
        if (n >= 32768 && n <= 40959) {
            return 0;
        }
        if (n >= 40960 && n <= 49151) {
            return 1;
        }
        if (n >= 49152 && n <= 57343) {
            return 2;
        }
        return 3;
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.a(0, 32768);
        this.a(a.b() - 1, 49152);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.t = 0;
        this.u = 0;
        this.n = 0;
        this.o = 1;
        this.p = 1;
        this.q = 0;
        this.r = 0;
        this.s = 0;
    }
}
