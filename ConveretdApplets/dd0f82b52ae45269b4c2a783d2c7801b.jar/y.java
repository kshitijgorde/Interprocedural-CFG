// 
// Decompiled by Procyon v0.5.30
// 

public final class y extends ag
{
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        switch (n) {
            case 32768: {
                this.g(n2, 32768);
            }
            case 32769: {
                this.g(n2, 40960);
            }
            case 32770: {
                this.f(n2 << 1, 0);
            }
            case 32771: {
                this.f(n2 << 1, 2048);
            }
            case 40960: {
                this.e(n2, 4096);
            }
            case 40961: {
                this.e(n2, 5120);
            }
            case 40962: {
                this.e(n2, 6144);
            }
            case 40963: {
                this.e(n2, 7168);
            }
            case 49152: {}
            case 49153:
            case 49154:
            case 57345:
            case 57346: {}
            case 57344: {
                if ((n2 & 0x40) != 0x0) {
                    this.a.c.a(1);
                    return;
                }
                this.a.c.a(0);
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("VRC4: Invalid ROM! Unable to load.");
            return;
        }
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
    }
}
