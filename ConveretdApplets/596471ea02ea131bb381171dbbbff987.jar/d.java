// 
// Decompiled by Procyon v0.5.30
// 

public final class d extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        n = (n2 & 0xF);
        final int b = this.d.b();
        if ((n2 & 0x80) != 0x0) {
            this.a(n << 1, 32768);
            this.a(b - 1, 49152);
        }
        if ((n2 & 0x40) != 0x0) {
            this.d(n << 3, 0);
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            System.out.println("048: Invalid ROM! Unable to load.");
            return;
        }
        final int n = a.b() << 1;
        this.a(1, 32768);
        this.a(n - 1, 49152);
        this.h();
        this.a.b.a(2);
    }
}
