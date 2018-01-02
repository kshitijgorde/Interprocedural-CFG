// 
// Decompiled by Procyon v0.5.30
// 

public final class D extends ag
{
    private int[] n;
    
    public D() {
        this.n = new int[2];
    }
    
    public final void a(final V v) {
        super.a(v);
        this.a();
    }
    
    public final void a(final int n, final short n2) {
        if (n < 32768) {
            super.a(n, n2);
        }
        else if (n == 36864) {
            this.n[0] = (n2 & 0x18) >> 1;
        }
        else if (40960 <= n && n <= 65535) {
            this.n[1] = (n2 & 0x3);
        }
        this.a(this.n[0] | this.n[1], 32768);
        this.a(this.n[0] | 0x3, 49152);
    }
    
    public final void a(final a a) {
        if (!a.a()) {
            return;
        }
        this.a(this.n[0] | this.n[1], 32768);
        this.a(this.n[0] | 0x3, 49152);
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public final void a() {
        this.n[0] = 12;
        this.n[1] = 0;
    }
}
