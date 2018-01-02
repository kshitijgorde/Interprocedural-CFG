// 
// Decompiled by Procyon v0.5.30
// 

public final class t extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.a(n2, 32768);
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.a(0, 32768);
        this.a(a.b() - 1, 49152);
        this.h();
        this.a.b.a(2);
    }
}
