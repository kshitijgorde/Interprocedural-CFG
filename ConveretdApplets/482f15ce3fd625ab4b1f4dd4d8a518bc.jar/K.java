// 
// Decompiled by Procyon v0.5.30
// 

public final class K extends at
{
    private int[] a;
    
    public K() {
        this.a = new int[1];
    }
    
    public final int a() {
        return 88;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768: {
                this.a[0] = n2;
            }
            case 32769: {
                switch (this.a[0] & 0x7) {
                    case 0: {
                        this.g(n2 & 0xFE);
                        this.h((n2 & 0xFE) + 1);
                        break;
                    }
                    case 1: {
                        this.i(n2 & 0xFE);
                        this.j((n2 & 0xFE) + 1);
                        break;
                    }
                    case 2: {
                        this.k(n2 | 0x40);
                        break;
                    }
                    case 3: {
                        this.l(n2 | 0x40);
                        break;
                    }
                    case 4: {
                        this.m(n2 | 0x40);
                        break;
                    }
                    case 5: {
                        this.n(n2 | 0x40);
                        break;
                    }
                    case 6: {
                        this.c(n2);
                        break;
                    }
                    case 7: {
                        this.d(n2);
                        break;
                    }
                }
            }
            case 49152: {
                if ((n2 & 0x40) != 0x0) {
                    this.b(1, 1, 1, 1);
                    return;
                }
                this.b(0, 0, 0, 0);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
}
