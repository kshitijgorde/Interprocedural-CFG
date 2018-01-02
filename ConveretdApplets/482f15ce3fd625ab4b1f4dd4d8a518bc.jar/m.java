// 
// Decompiled by Procyon v0.5.30
// 

public final class m extends at
{
    public int[] a;
    public int a;
    
    public m() {
        this.a = new int[1];
        this.a = 0;
    }
    
    public final int a() {
        return 32;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final long n) {
        if (n == 607815477L) {
            this.a = 1;
        }
        if (n == 674943524L) {
            this.a(30, 31, 30, 31);
        }
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF000) {
            case 32768: {
                if ((this.a[0] & 0x2) != 0x0) {
                    this.e(n2);
                    break;
                }
                this.c(n2);
                break;
            }
            case 36864: {
                if ((n2 & 0x1) != 0x0) {
                    this.c();
                }
                else {
                    this.b();
                }
                this.a[0] = n2;
                break;
            }
            case 40960: {
                this.d(n2);
                break;
            }
        }
        switch (n & 0xF007) {
            case 45056: {
                this.g(n2);
            }
            case 45057: {
                this.h(n2);
            }
            case 45058: {
                this.i(n2);
            }
            case 45059: {
                this.j(n2);
            }
            case 45060: {
                this.k(n2);
            }
            case 45061: {
                this.l(n2);
            }
            case 45062: {
                if (this.a == 1 && (n2 & 0x40) != 0x0) {
                    this.b(0, 0, 0, 1);
                }
                this.m(n2);
            }
            case 45063: {
                if (this.a == 1 && (n2 & 0x40) != 0x0) {
                    this.b(0, 0, 0, 0);
                }
                this.n(n2);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = 0;
        }
        if (this.a == 1) {
            this.b(0, 0, 0, 0);
        }
    }
}
