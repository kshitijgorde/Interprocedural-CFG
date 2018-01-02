// 
// Decompiled by Procyon v0.5.30
// 

public final class n extends at
{
    public final int a() {
        return 228;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        int n3 = (n & 0x780) >> 7;
        final int n4 = (n & 0xF) << 2 | (n2 & 0x3);
        switch ((n & 0x1800) >> 11) {
            case 1: {
                n3 |= 0x10;
                break;
            }
            case 3: {
                n3 |= 0x20;
                break;
            }
        }
        if ((n & 0x20) != 0x0) {
            int n5 = n3 << 1;
            if ((n & 0x40) != 0x0) {
                ++n5;
            }
            this.c(n5 * 4 + 0);
            this.d(n5 * 4 + 1);
            this.e(n5 * 4 + 0);
            this.f(n5 * 4 + 1);
        }
        else {
            this.c(n3 * 4 + 0);
            this.d(n3 * 4 + 1);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
        }
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
            return;
        }
        this.b();
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
    }
}
