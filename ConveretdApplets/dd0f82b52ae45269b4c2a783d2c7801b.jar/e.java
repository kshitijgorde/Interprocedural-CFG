// 
// Decompiled by Procyon v0.5.30
// 

public final class e extends ag
{
    private int n;
    
    public final void a(final V v) {
        super.a(v);
        this.n = -1;
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        final int b = a.b();
        this.a(0, 32768);
        this.a(b - 1, 49152);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        if (n >= 49152 && n2 != this.n) {
            this.a(this.n = n2, 32768);
        }
    }
    
    public final void a() {
        this.n = -1;
    }
}
