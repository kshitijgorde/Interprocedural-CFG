// 
// Decompiled by Procyon v0.5.30
// 

public final class Q extends ag
{
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if ((n & 0xFFF0) == 0xFF00) {
            n = (n2 & 0x1C) >> 2;
            this.a(n, 32768);
        }
    }
    
    public final void a(final a a) {
        final int b = a.b();
        this.a(0, 32768);
        this.a(b - 1, 49152);
        this.h();
        this.a.b.a(2);
    }
}
