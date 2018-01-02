// 
// Decompiled by Procyon v0.5.30
// 

public final class aN extends at
{
    public final int a() {
        return 22;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    public final void a(final int n, int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                this.c(n2);
            }
            case 36864: {
                if ((n2 &= 0x3) == 0x0) {
                    this.b();
                    return;
                }
                if (n2 == 1) {
                    this.c();
                    return;
                }
                if (n2 == 2) {
                    this.b(1, 1, 1, 1);
                    return;
                }
                this.b(0, 0, 0, 0);
            }
            case 40960: {
                this.d(n2);
            }
            case 45056: {
                this.g(n2 >> 1);
            }
            case 45057: {
                this.h(n2 >> 1);
            }
            case 49152: {
                this.i(n2 >> 1);
            }
            case 49153: {
                this.j(n2 >> 1);
            }
            case 53248: {
                this.k(n2 >> 1);
            }
            case 53249: {
                this.l(n2 >> 1);
            }
            case 57344: {
                this.m(n2 >> 1);
            }
            case 57345: {
                this.n(n2 >> 1);
                break;
            }
        }
    }
}
