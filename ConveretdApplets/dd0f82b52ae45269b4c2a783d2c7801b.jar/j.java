// 
// Decompiled by Procyon v0.5.30
// 

public final class j extends ag
{
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        final short n3 = (short)(n2 & 0xF);
        final int n4 = (n2 & 0xF0) >> 4;
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.a(n3, 32768);
        this.d(n4, 0);
        if ((n & 0xFE00) != 0xFE00) {
            if ((n2 & 0x8) != 0x0) {
                this.a.c.a(4);
                return;
            }
            this.a.c.a(3);
        }
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int n = a.b() << 2;
        this.a(0, 32768);
        this.a(n - 1, 49152);
        this.h();
        this.a.b.a(2);
    }
}
