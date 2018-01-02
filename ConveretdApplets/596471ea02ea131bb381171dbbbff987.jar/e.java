// 
// Decompiled by Procyon v0.5.30
// 

public final class e extends ad
{
    private int l;
    
    public final void a(final U u) {
        super.a(u);
        this.l = -1;
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
    
    public final void a(final int n, final short l) {
        if (n < 32768) {
            super.a(n, l);
            return;
        }
        if (n >= 49152 && l != this.l) {
            this.a(this.l = l, 32768);
        }
    }
    
    public final void a() {
        this.l = -1;
    }
}
