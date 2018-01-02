// 
// Decompiled by Procyon v0.5.30
// 

public final class T extends ad
{
    private int l;
    private int[] m;
    private int n;
    private int o;
    
    public T() {
        this.l = 0;
        this.m = new int[4];
        this.n = 0;
        this.o = 0;
    }
    
    public final void a(final U u) {
        super.a(u);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        final int n3 = (n & 0x7FFF) >> 13;
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if ((n2 & 0x80) != 0x0) {
            this.n = 0;
            this.o = 0;
            if (n3 == 0) {
                final int[] m = this.m;
                final int n4 = n3;
                m[n4] |= 0xC;
            }
        }
        else {
            this.n |= (n2 & 0x1) << this.o++;
            if (this.o == 5) {
                this.m[n3] = (this.n & 0x1F);
                final boolean b = false;
                this.o = (b ? 1 : 0);
                this.n = (b ? 1 : 0);
            }
        }
        if ((this.m[0] & 0x2) != 0x0) {
            if ((this.m[0] & 0x1) != 0x0) {
                this.a.c.a(1);
            }
            else {
                this.a.c.a(0);
            }
        }
        else if ((this.m[0] & 0x1) != 0x0) {
            this.a.c.a(4);
        }
        else {
            this.a.c.a(3);
        }
        switch (this.l) {
            case 0:
            case 1: {
                ++this.l;
            }
            case 2: {
                if ((this.m[1] & 0x8) == 0x0) {
                    this.g((this.m[1] & 0x6) << 1, 32768);
                    this.g(((this.m[1] & 0x6) << 1) + 1, 40960);
                    this.g(((this.m[1] & 0x6) << 1) + 2, 49152);
                    this.g(((this.m[1] & 0x6) << 1) + 3, 57344);
                    break;
                }
                if ((this.m[0] & 0x8) == 0x0) {
                    this.g(((this.m[3] & 0x6) << 1) + 16, 32768);
                    this.g(((this.m[3] & 0x6) << 1) + 17, 40960);
                    this.g(((this.m[3] & 0x6) << 1) + 18, 49152);
                    this.g(((this.m[3] & 0x6) << 1) + 19, 57344);
                    return;
                }
                if ((this.m[0] & 0x4) != 0x0) {
                    this.g(((this.m[3] & 0x7) << 1) + 16, 32768);
                    this.g(((this.m[3] & 0x7) << 1) + 17, 40960);
                    this.g(30, 49152);
                    this.g(31, 57344);
                    return;
                }
                this.g(16, 32768);
                this.g(17, 40960);
                this.g(((this.m[3] & 0x7) << 1) + 16, 49152);
                this.g(((this.m[3] & 0x7) << 1) + 17, 57344);
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
        this.m[0] = 12;
        this.m[1] = 0;
        this.m[2] = 0;
        this.m[3] = 16;
        this.n = 0;
        this.o = 0;
        this.l = 0;
    }
}
