// 
// Decompiled by Procyon v0.5.30
// 

public final class ac extends ad
{
    public final void a(final U u) {
        super.a(u);
        this.a();
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
            case 36864: {
                final short n3;
                if ((n3 = (short)(n2 & 0x3)) == 0) {
                    this.a.c.a(0);
                    return;
                }
                if (n3 == 1) {
                    this.a.c.a(1);
                    return;
                }
                if (n3 == 2) {
                    this.a.c.a(3);
                    return;
                }
                this.a.c.a(4);
            }
            case 40960: {
                this.g(n2, 40960);
            }
            case 45056: {
                this.e(n2 >> 1, 0);
            }
            case 45057: {
                this.e(n2 >> 1, 1024);
            }
            case 49152: {
                this.e(n2 >> 1, 2048);
            }
            case 49153: {
                this.e(n2 >> 1, 3072);
            }
            case 53248: {
                this.e(n2 >> 1, 4096);
            }
            case 53249: {
                this.e(n2 >> 1, 5120);
            }
            case 57344: {
                this.e(n2 >> 1, 6144);
            }
            case 57345: {
                this.e(n2 >> 1, 7168);
                break;
            }
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("VRC2: Invalid ROM! Unable to load.");
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
