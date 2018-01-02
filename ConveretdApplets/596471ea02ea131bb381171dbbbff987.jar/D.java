// 
// Decompiled by Procyon v0.5.30
// 

public final class D extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.g();
        this.h();
        this.a.b.a(2);
    }
    
    public final void a(int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
        }
        if (n >= 24576 && n < 32768) {
            n = (n2 & 0xF0) >> 4;
            final short n3 = (short)(n2 & 0xF);
            this.c(n, 32768);
            this.d(n3, 0);
        }
    }
}
