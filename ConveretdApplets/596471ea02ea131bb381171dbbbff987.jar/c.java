// 
// Decompiled by Procyon v0.5.30
// 

public final class c extends ad
{
    public final void a(final U u) {
        super.a(u);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.c(n2, 32768);
    }
}
