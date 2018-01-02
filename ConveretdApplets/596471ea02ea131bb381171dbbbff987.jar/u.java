// 
// Decompiled by Procyon v0.5.30
// 

public final class u extends ad
{
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    
    public u() {
        this.m = 1;
        this.n = 1;
    }
    
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if ((n2 & 0x80) != 0x0) {
            this.s = 0;
            this.r = 0;
            if (d(n) == 0) {
                this.m = 1;
                this.n = 1;
            }
        }
        else {
            this.r = ((this.r & 255 - (1 << this.s)) | (n2 & 0x1) << this.s);
            ++this.s;
            if (this.s == 5) {
                final int d = d(n);
                final int r = this.r;
                final int n3 = d;
                if (n3 == 0) {
                    final int l;
                    if ((l = (r & 0x3)) != this.l) {
                        this.l = l;
                        Q q;
                        int n4;
                        if ((this.l & 0x2) == 0x0) {
                            q = this.a.c;
                            n4 = 3;
                        }
                        else {
                            q = this.a.c;
                            n4 = (((this.l & 0x1) != 0x0) ? 1 : 0);
                        }
                        q.a(n4);
                    }
                    this.m = (r >> 2 & 0x1);
                    this.n = (r >> 3 & 0x1);
                    this.o = (r >> 4 & 0x1);
                }
                else if (n3 == 1) {
                    this.p = (r >> 4 & 0x1);
                    if (this.a.j.c() > 0) {
                        if (this.o == 0) {
                            if (this.p == 0) {
                                this.d(r & 0xF, 0);
                            }
                            else {
                                this.d(this.a.j.c() / 2 + (r & 0xF), 0);
                            }
                        }
                        else if (this.p == 0) {
                            this.b(r & 0xF, 0);
                        }
                        else {
                            this.b(this.a.j.c() / 2 + (r & 0xF), 0);
                        }
                    }
                }
                else if (n3 == 2) {
                    this.q = (r >> 4 & 0x1);
                    if (this.a.j.c() > 0 && this.o == 1) {
                        if (this.q == 0) {
                            this.b(r & 0xF, 4096);
                        }
                        else {
                            this.b(this.a.j.c() / 2 + (r & 0xF), 4096);
                        }
                    }
                }
                else {
                    final int n5 = r & 0xF;
                    int n6 = 0;
                    final int b;
                    if ((b = this.a.j.b()) >= 32) {
                        if (this.o == 0) {
                            if (this.p == 1) {
                                n6 = 16;
                            }
                        }
                        else {
                            n6 = (this.p | this.q << 1) << 3;
                        }
                    }
                    else if (b >= 16 && this.p == 1) {
                        n6 = 8;
                    }
                    if (this.n == 0) {
                        this.c(n6 + n5, 32768);
                    }
                    else {
                        final int n7 = (n6 << 1) + n5;
                        if (this.m == 0) {
                            this.a(n7, 49152);
                        }
                        else {
                            this.a(n7, 32768);
                        }
                    }
                }
                this.r = 0;
                this.s = 0;
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
        this.r = 0;
        this.s = 0;
        this.l = 0;
        this.m = 1;
        this.n = 1;
        this.o = 0;
        this.p = 0;
        this.q = 0;
    }
}
