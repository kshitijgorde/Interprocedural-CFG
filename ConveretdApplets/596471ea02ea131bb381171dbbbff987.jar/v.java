// 
// Decompiled by Procyon v0.5.30
// 

public final class v extends ad
{
    private int l;
    private int m;
    private short[] n;
    
    public final void a(U u) {
        super.a(u);
        this.l = 0;
        this.m = -1;
        final a j;
        final int b = (j = (u = u).j).b();
        this.n = new short[b << 14];
        for (int i = 0; i < b; ++i) {
            System.arraycopy(j.a(i), 0, this.n, i << 14, 16384);
        }
    }
    
    public final short a(final int n) {
        if (n < 32768) {
            return super.a(n);
        }
        if (n + this.l >= 262144) {
            return this.n[n + this.l - 262144];
        }
        return this.n[n + this.l];
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.l = (n2 & 0xF) - 1 << 15;
        if (this.m != (n2 & 0x10)) {
            this.m = (n2 & 0x10);
            if (this.m == 0) {
                this.a.c.a(3);
                return;
            }
            this.a.c.a(4);
        }
    }
    
    public final void a() {
        super.a();
        this.l = 0;
        this.m = -1;
    }
}
