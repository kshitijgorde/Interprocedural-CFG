// 
// Decompiled by Procyon v0.5.30
// 

public final class aa extends ag
{
    public final void a(final V v) {
        super.a(v);
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.c(n2 >> 4 & 0x3, 32768);
        this.d((n2 & 0x3) << 1, 0);
    }
}
