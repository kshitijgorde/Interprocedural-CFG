// 
// Decompiled by Procyon v0.5.30
// 

public final class A extends at
{
    public boolean a;
    
    public A() {
        this.a = false;
    }
    
    public final int a() {
        return 80;
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
        this.a = false;
    }
    
    public final void a(final long n) {
        if (n == 1987637717L) {
            this.a = true;
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 32768) {
            switch (n) {
                case 32496: {
                    this.g(n2 & 0x7F);
                    this.h((n2 & 0x7F) + 1);
                    if (!this.a) {
                        break;
                    }
                    if ((n2 & 0x80) != 0x0) {
                        this.c(8, 1);
                        this.c(9, 1);
                        return;
                    }
                    this.c(8, 0);
                    this.c(9, 0);
                }
                case 32497: {
                    this.i(n2 & 0x7F);
                    this.j((n2 & 0x7F) + 1);
                    if (!this.a) {
                        break;
                    }
                    if ((n2 & 0x80) != 0x0) {
                        this.c(10, 1);
                        this.c(11, 1);
                        return;
                    }
                    this.c(10, 0);
                    this.c(11, 0);
                }
                case 32498: {
                    this.k(n2);
                }
                case 32499: {
                    this.l(n2);
                }
                case 32500: {
                    this.m(n2);
                }
                case 32501: {
                    this.n(n2);
                }
                case 32502: {
                    if ((n2 & 0x1) != 0x0) {
                        this.b();
                        return;
                    }
                    this.c();
                }
                case 32506:
                case 32507: {
                    this.c(n2);
                }
                case 32508:
                case 32509: {
                    this.d(n2);
                }
                case 32510:
                case 32511: {
                    this.e(n2);
                    break;
                }
            }
        }
    }
}
