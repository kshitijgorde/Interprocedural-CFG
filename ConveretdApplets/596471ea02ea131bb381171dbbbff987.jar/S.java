// 
// Decompiled by Procyon v0.5.30
// 

public final class S extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        n = ((n2 & 0xF) << 1) % this.a.j.b();
        final int n3 = (((n2 & 0xF) << 1) + 1) % this.a.j.b();
        this.a(n, 32768);
        this.a(n3, 49152);
        if (this.d.c() > 0) {
            n = (n2 >> 4 << 1) % this.a.j.c();
            this.b(n, 0);
            this.b(n + 1, 4096);
        }
    }
}
