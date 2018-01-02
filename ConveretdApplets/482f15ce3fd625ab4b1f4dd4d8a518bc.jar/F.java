// 
// Decompiled by Procyon v0.5.30
// 

public final class F extends at
{
    public int[] a;
    
    public F() {
        this.a = new int[1];
    }
    
    public final int a() {
        return 82;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.b();
        this.a[0] = 0;
    }
    
    public final void a(final int n, final int n2) {
        switch (n) {
            case 32496: {
                if (this.a[0] != 0) {
                    this.k((n2 & 0xFE) + 0);
                    this.l((n2 & 0xFE) + 1);
                    return;
                }
                this.g((n2 & 0xFE) + 0);
                this.h((n2 & 0xFE) + 1);
            }
            case 32497: {
                if (this.a[0] != 0) {
                    this.m((n2 & 0xFE) + 0);
                    this.n((n2 & 0xFE) + 1);
                    return;
                }
                this.i((n2 & 0xFE) + 0);
                this.j((n2 & 0xFE) + 1);
            }
            case 32498: {
                if (this.a[0] == 0) {
                    this.k(n2);
                    return;
                }
                this.g(n2);
            }
            case 32499: {
                if (this.a[0] == 0) {
                    this.l(n2);
                    return;
                }
                this.h(n2);
            }
            case 32500: {
                if (this.a[0] == 0) {
                    this.m(n2);
                    return;
                }
                this.i(n2);
            }
            case 32501: {
                if (this.a[0] == 0) {
                    this.n(n2);
                    return;
                }
                this.j(n2);
            }
            case 32502: {
                this.a[0] = (n2 & 0x2);
                if ((n2 & 0x1) != 0x0) {
                    this.b();
                    return;
                }
                this.c();
            }
            case 32506: {
                this.c(n2 >> 2);
            }
            case 32507: {
                this.d(n2 >> 2);
            }
            case 32508: {
                this.e(n2 >> 2);
                break;
            }
        }
    }
}
