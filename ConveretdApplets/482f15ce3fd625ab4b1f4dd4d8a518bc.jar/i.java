// 
// Decompiled by Procyon v0.5.30
// 

public final class i extends at
{
    public final int a() {
        return 225;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = (n & 0xF80) >> 7;
        final int n4 = n & 0x3F;
        this.g(n4 * 8 + 0);
        this.h(n4 * 8 + 1);
        this.i(n4 * 8 + 2);
        this.j(n4 * 8 + 3);
        this.k(n4 * 8 + 4);
        this.l(n4 * 8 + 5);
        this.m(n4 * 8 + 6);
        this.n(n4 * 8 + 7);
        if ((n & 0x2000) != 0x0) {
            this.c();
        }
        else {
            this.b();
        }
        if ((n & 0x1000) == 0x0) {
            this.c(n3 * 4 + 0);
            this.d(n3 * 4 + 1);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
            return;
        }
        if ((n & 0x40) != 0x0) {
            this.c(n3 * 4 + 2);
            this.d(n3 * 4 + 3);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
            return;
        }
        this.c(n3 * 4 + 0);
        this.d(n3 * 4 + 1);
        this.e(n3 * 4 + 0);
        this.f(n3 * 4 + 1);
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
}
