// 
// Decompiled by Procyon v0.5.30
// 

public final class S extends ag
{
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    
    public final void a(final V v) {
        super.a(v);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        final short n3 = (short)(n2 & 0xFF);
        switch (n >> 12) {
            case 10: {
                this.a(n3, 32768);
            }
            case 11: {
                this.p = n3;
                if (this.n == 253) {
                    this.b(n3, 0);
                    return;
                }
                break;
            }
            case 12: {
                this.q = n3;
                if (this.n == 254) {
                    this.b(n3, 0);
                    return;
                }
                break;
            }
            case 13: {
                this.r = n3;
                if (this.o == 253) {
                    this.b(n3, 4096);
                    return;
                }
                break;
            }
            case 14: {
                this.s = n3;
                if (this.o == 254) {
                    this.b(n3, 4096);
                    return;
                }
                break;
            }
            case 15: {
                if ((n3 & 0x1) == 0x0) {
                    this.a.c.a(0);
                    return;
                }
                this.a.c.a(1);
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int n = a.b() << 2;
        this.a(0, 32768);
        this.a(n - 1, 49152);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void b(int n) {
        final boolean b = n < 8192;
        n &= 0xFF0;
        if (b) {
            if (n == 4048) {
                this.n = 253;
                this.b(this.p, 0);
                return;
            }
            if (n == 4064) {
                this.n = 254;
                this.b(this.q, 0);
            }
        }
        else {
            if (n == 4048) {
                this.o = 253;
                this.b(this.r, 4096);
                return;
            }
            if (n == 4064) {
                this.o = 254;
                this.b(this.s, 4096);
            }
        }
    }
    
    public final void a() {
        this.n = 254;
        this.o = 254;
        this.p = 0;
        this.q = 4;
        this.r = 0;
        this.s = 0;
    }
}
