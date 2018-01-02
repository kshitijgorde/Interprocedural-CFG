// 
// Decompiled by Procyon v0.5.30
// 

public final class l extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        switch (n) {
            case 32768: {
                if ((n2 & 0x40) != 0x0) {
                    this.a.c.a(1);
                }
                else {
                    this.a.c.a(0);
                }
                this.g(n2 & 0x1F, 32768);
            }
            case 32769: {
                this.g(n2 & 0x1F, 40960);
            }
            case 32770: {
                this.f(n2, 0);
            }
            case 32771: {
                this.f(n2, 2048);
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
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("048: Invalid ROM! Unable to load.");
            return;
        }
        final int n = a.b() << 1;
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(n - 2, 49152);
        this.g(n - 1, 57344);
        this.h();
        this.a.b.a(2);
    }
}
