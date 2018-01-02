// 
// Decompiled by Procyon v0.5.30
// 

public final class s extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        n = n2 % (this.a.j.c() / 2) << 1;
        this.b(n, 0);
        this.b(n + 1, 4096);
        this.d(n2 << 1, 0);
    }
}
