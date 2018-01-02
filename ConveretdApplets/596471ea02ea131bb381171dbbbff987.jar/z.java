// 
// Decompiled by Procyon v0.5.30
// 

public final class z extends ad
{
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    
    public final void a(final U u) {
        super.a(u);
        this.a();
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        final short n3 = (short)(n2 & 0xFF);
        switch ((n &= 0xF000) >> 12) {
            case 10: {
                this.g(n3, 32768);
            }
            case 11: {
                this.n = n3;
                if (this.l == 253) {
                    this.b(n3, 0);
                }
            }
            case 12: {
                this.o = n3;
                if (this.l == 254) {
                    this.b(n3, 0);
                }
            }
            case 13: {
                this.p = n3;
                if (this.m == 253) {
                    this.b(n3, 4096);
                }
            }
            case 14: {
                this.q = n3;
                if (this.m == 254) {
                    this.b(n3, 4096);
                }
            }
            case 15: {
                if ((n3 & 0x1) == 0x0) {
                    this.a.c.a(0);
                    return;
                }
                this.a.c.a(1);
            }
            default: {}
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(n - 3, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void b(final int n) {
        if ((n & 0x1FF0) == 0xFD0 && this.l != 253) {
            this.b(this.n, 0);
            this.l = 253;
            return;
        }
        if ((n & 0x1FF0) == 0xFE0 && this.l != 254) {
            this.b(this.o, 0);
            this.l = 254;
            return;
        }
        if ((n & 0x1FF0) == 0x1FD0 && this.m != 253) {
            this.b(this.p, 4096);
            this.m = 253;
            return;
        }
        if ((n & 0x1FF0) == 0x1FE0 && this.m != 254) {
            this.b(this.q, 4096);
            this.m = 254;
        }
    }
    
    public final void a() {
        this.l = 254;
        this.m = 254;
        this.n = 0;
        this.o = 4;
        this.p = 0;
        this.q = 0;
    }
}
