// 
// Decompiled by Procyon v0.5.30
// 

public final class v extends ag
{
    private int n;
    private int o;
    private short[] p;
    
    public final void a(V v) {
        super.a(v);
        this.n = 0;
        this.o = -1;
        final a j;
        final int b = (j = (v = v).j).b();
        this.p = new short[b << 14];
        for (int i = 0; i < b; ++i) {
            System.arraycopy(j.a(i), 0, this.p, i << 14, 16384);
        }
    }
    
    public final short a(final int n) {
        if (n < 32768) {
            return super.a(n);
        }
        if (n + this.n >= 262144) {
            return this.p[n + this.n - 262144];
        }
        return this.p[n + this.n];
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
            return;
        }
        this.n = (n2 & 0xF) - 1 << 15;
        if (this.o != (n2 & 0x10)) {
            this.o = (n2 & 0x10);
            if (this.o == 0) {
                this.a.c.a(3);
                return;
            }
            this.a.c.a(4);
        }
    }
    
    public final void a() {
        super.a();
        this.n = 0;
        this.o = -1;
    }
}
