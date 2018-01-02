// 
// Decompiled by Procyon v0.5.30
// 

public final class m extends ag
{
    private int[] n;
    private int o;
    
    public m() {
        this.n = new int[1];
        this.o = 0;
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
                if ((this.n[0] & 0x2) != 0x0) {
                    this.g(n2, 49152);
                    break;
                }
                this.g(n2, 32768);
                break;
            }
            case 36864: {
                if ((n2 & 0x1) != 0x0) {
                    this.a.c.a(1);
                }
                else {
                    this.a.c.a(0);
                }
                this.n[0] = n2;
                break;
            }
            case 40960: {
                this.g(n2, 40960);
                break;
            }
        }
        switch (n & 0xF007) {
            case 45056: {
                this.e(n2, 0);
            }
            case 45057: {
                this.e(n2, 1024);
            }
            case 45058: {
                this.e(n2, 2048);
            }
            case 45059: {
                this.e(n2, 3072);
            }
            case 45060: {
                this.e(n2, 4096);
            }
            case 45061: {
                this.e(n2, 5120);
            }
            case 45062: {
                this.e(n2, 6144);
            }
            case 45063: {
                this.e(n2, 7168);
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
        for (int i = 0; i < this.n.length; ++i) {
            this.n[i] = 0;
        }
    }
}
