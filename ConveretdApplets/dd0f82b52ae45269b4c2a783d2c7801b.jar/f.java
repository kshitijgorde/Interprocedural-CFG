// 
// Decompiled by Procyon v0.5.30
// 

public final class f extends ag
{
    private int[] n;
    
    public f() {
        this.n = new int[2];
    }
    
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        switch (n & 0xF000) {
            case 32768: {
                this.g(n2, 32768);
            }
            case 36864: {
                if ((n2 & 0x1) != 0x0) {
                    this.a.c.a(1);
                }
                else {
                    this.a.c.a(0);
                }
                this.b(this.n[0] = ((this.n[0] & 0xF) | (n2 & 0x2) << 3), 0);
                this.b(this.n[1] = ((this.n[1] & 0xF) | (n2 & 0x4) << 2), 4096);
            }
            case 40960: {
                this.g(n2, 40960);
            }
            case 49152: {
                this.g(n2, 49152);
            }
            case 57344: {
                this.b(this.n[0] = ((this.n[0] & 0x10) | (n2 & 0xF)), 0);
            }
            case 61440: {
                this.b(this.n[1] = ((this.n[1] & 0x10) | (n2 & 0xF)), 4096);
                break;
            }
        }
    }
    
    public final void a(final a a) {
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.n[0] = 0;
        this.n[1] = 1;
    }
}
