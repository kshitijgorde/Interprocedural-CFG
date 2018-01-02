// 
// Decompiled by Procyon v0.5.30
// 

public final class U extends ag
{
    private int n;
    private int[] o;
    private int p;
    private int q;
    
    public U() {
        this.n = 0;
        this.o = new int[4];
        this.p = 0;
        this.q = 0;
    }
    
    public final void a(final V v) {
        super.a(v);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        final int n3 = (n & 0x7FFF) >> 13;
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if ((n2 & 0x80) != 0x0) {
            this.p = 0;
            this.q = 0;
            if (n3 == 0) {
                final int[] o = this.o;
                final int n4 = n3;
                o[n4] |= 0xC;
            }
        }
        else {
            this.p |= (n2 & 0x1) << this.q++;
            if (this.q == 5) {
                this.o[n3] = (this.p & 0x1F);
                final boolean b = false;
                this.q = (b ? 1 : 0);
                this.p = (b ? 1 : 0);
            }
        }
        if ((this.o[0] & 0x2) != 0x0) {
            if ((this.o[0] & 0x1) != 0x0) {
                this.a.c.a(1);
            }
            else {
                this.a.c.a(0);
            }
        }
        else if ((this.o[0] & 0x1) != 0x0) {
            this.a.c.a(4);
        }
        else {
            this.a.c.a(3);
        }
        switch (this.n) {
            case 0:
            case 1: {
                ++this.n;
            }
            case 2: {
                if ((this.o[1] & 0x8) == 0x0) {
                    this.g((this.o[1] & 0x6) << 1, 32768);
                    this.g(((this.o[1] & 0x6) << 1) + 1, 40960);
                    this.g(((this.o[1] & 0x6) << 1) + 2, 49152);
                    this.g(((this.o[1] & 0x6) << 1) + 3, 57344);
                    break;
                }
                if ((this.o[0] & 0x8) == 0x0) {
                    this.g(((this.o[3] & 0x6) << 1) + 16, 32768);
                    this.g(((this.o[3] & 0x6) << 1) + 17, 40960);
                    this.g(((this.o[3] & 0x6) << 1) + 18, 49152);
                    this.g(((this.o[3] & 0x6) << 1) + 19, 57344);
                    return;
                }
                if ((this.o[0] & 0x4) != 0x0) {
                    this.g(((this.o[3] & 0x7) << 1) + 16, 32768);
                    this.g(((this.o[3] & 0x7) << 1) + 17, 40960);
                    this.g(30, 49152);
                    this.g(31, 57344);
                    return;
                }
                this.g(16, 32768);
                this.g(17, 40960);
                this.g(((this.o[3] & 0x7) << 1) + 16, 49152);
                this.g(((this.o[3] & 0x7) << 1) + 17, 57344);
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(2, 49152);
        this.g(3, 57344);
        this.h();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.o[0] = 12;
        this.o[1] = 0;
        this.o[2] = 0;
        this.o[3] = 16;
        this.p = 0;
        this.q = 0;
        this.n = 0;
    }
}
