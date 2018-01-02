// 
// Decompiled by Procyon v0.5.30
// 

public final class M extends ad
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
                if ((n2 & 0x80) != 0x0) {
                    this.g(((n2 & 0x3F) << 1) + 1, 32768);
                    this.g((n2 & 0x3F) << 1, 40960);
                    this.g(((n2 & 0x3F) << 1) + 3, 49152);
                    this.g(((n2 & 0x3F) << 1) + 2, 57344);
                }
                else {
                    this.g((n2 & 0x3F) << 1, 32768);
                    this.g(((n2 & 0x3F) << 1) + 1, 40960);
                    this.g(((n2 & 0x3F) << 1) + 2, 49152);
                    this.g(((n2 & 0x3F) << 1) + 3, 57344);
                }
                if ((n2 & 0x40) != 0x0) {
                    this.a.c.a(1);
                    return;
                }
                this.a.c.a(0);
            }
            case 32769: {
                if ((n2 & 0x80) != 0x0) {
                    this.g(((n2 & 0x3F) << 1) + 1, 49152);
                    this.g((n2 & 0x3F) << 1, 57344);
                    return;
                }
                this.g((n2 & 0x3F) << 1, 49152);
                this.g(((n2 & 0x3F) << 1) + 1, 57344);
            }
            case 32770: {
                if ((n2 & 0x80) != 0x0) {
                    this.g(((n2 & 0x3F) << 1) + 1, 32768);
                    this.g(((n2 & 0x3F) << 1) + 1, 40960);
                    this.g(((n2 & 0x3F) << 1) + 1, 49152);
                    this.g(((n2 & 0x3F) << 1) + 1, 57344);
                    return;
                }
                this.g((n2 & 0x3F) << 1, 32768);
                this.g((n2 & 0x3F) << 1, 40960);
                this.g((n2 & 0x3F) << 1, 49152);
                this.g((n2 & 0x3F) << 1, 57344);
            }
            case 32771: {
                if ((n2 & 0x80) != 0x0) {
                    this.g(((n2 & 0x3F) << 1) + 1, 49152);
                    this.g((n2 & 0x3F) << 1, 57344);
                }
                else {
                    this.g((n2 & 0x3F) << 1, 49152);
                    this.g(((n2 & 0x3F) << 1) + 1, 57344);
                }
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
            System.out.println("015: Invalid ROM! Unable to load.");
            return;
        }
        this.g(0, 32768);
        this.g(1, 40960);
        this.g(2, 49152);
        this.g(3, 57344);
        this.h();
        this.c();
        this.a.b.a(2);
    }
}
