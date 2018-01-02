// 
// Decompiled by Procyon v0.5.30
// 

public final class z extends ag
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
                this.p = n3;
                if (this.n == 253) {
                    this.b(n3, 0);
                }
            }
            case 12: {
                this.q = n3;
                if (this.n == 254) {
                    this.b(n3, 0);
                }
            }
            case 13: {
                this.r = n3;
                if (this.o == 253) {
                    this.b(n3, 4096);
                }
            }
            case 14: {
                this.s = n3;
                if (this.o == 254) {
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
        if ((n & 0x1FF0) == 0xFD0 && this.n != 253) {
            this.b(this.p, 0);
            this.n = 253;
            return;
        }
        if ((n & 0x1FF0) == 0xFE0 && this.n != 254) {
            this.b(this.q, 0);
            this.n = 254;
            return;
        }
        if ((n & 0x1FF0) == 0x1FD0 && this.o != 253) {
            this.b(this.r, 4096);
            this.o = 253;
            return;
        }
        if ((n & 0x1FF0) == 0x1FE0 && this.o != 254) {
            this.b(this.s, 4096);
            this.o = 254;
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
