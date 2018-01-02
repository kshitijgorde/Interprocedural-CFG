// 
// Decompiled by Procyon v0.5.30
// 

public final class aJ extends at
{
    private int[] a;
    
    public aJ() {
        this.a = new int[4];
    }
    
    public final int a() {
        return 255;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void b(final int n, final int n2) {
        if (n >= 22528) {
            this.a[n & 0x3] = (n2 & 0xF);
        }
    }
    
    public final int b(final int n) {
        if (n >= 22528) {
            return this.a[n & 0x3] & 0xF;
        }
        return n >> 8;
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = (n & 0xF80) >> 7;
        final int n4 = n & 0x3F;
        final int n5 = (n & 0x4000) >> 14;
        if ((n & 0x2000) != 0x0) {
            this.c();
        }
        else {
            this.b();
        }
        if ((n & 0x1000) != 0x0) {
            if ((n & 0x40) != 0x0) {
                this.c(128 * n5 + n3 * 4 + 2);
                this.d(128 * n5 + n3 * 4 + 3);
                this.e(128 * n5 + n3 * 4 + 2);
                this.f(128 * n5 + n3 * 4 + 3);
            }
            else {
                this.c(128 * n5 + n3 * 4 + 0);
                this.d(128 * n5 + n3 * 4 + 1);
                this.e(128 * n5 + n3 * 4 + 0);
                this.f(128 * n5 + n3 * 4 + 1);
            }
        }
        else {
            this.c(128 * n5 + n3 * 4 + 0);
            this.d(128 * n5 + n3 * 4 + 1);
            this.e(128 * n5 + n3 * 4 + 2);
            this.f(128 * n5 + n3 * 4 + 3);
        }
        this.g(512 * n5 + n4 * 8 + 0);
        this.h(512 * n5 + n4 * 8 + 1);
        this.i(512 * n5 + n4 * 8 + 2);
        this.j(512 * n5 + n4 * 8 + 3);
        this.k(512 * n5 + n4 * 8 + 4);
        this.l(512 * n5 + n4 * 8 + 5);
        this.m(512 * n5 + n4 * 8 + 6);
        this.n(512 * n5 + n4 * 8 + 7);
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        this.b();
    }
}
