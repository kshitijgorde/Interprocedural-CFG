// 
// Decompiled by Procyon v0.5.30
// 

public final class z extends at
{
    private int a;
    private int b;
    private boolean a;
    private int[] a;
    
    public z() {
        this.a = 0;
        this.b = 0;
        this.a = false;
        this.a = new int[1];
    }
    
    public final int a() {
        return 189;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a[0] = 0;
        this.a = false;
        this.b = 0;
        this.a = 0;
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
    
    public final void b(final int n, final int n2) {
        if (n >= 16640 && n <= 16895) {
            final int n3 = (n2 & 0x30) >> 4;
            this.a(n3 * 4, n3 * 4 + 1, n3 * 4 + 2, n3 * 4 + 3);
        }
    }
    
    public final void a(final int n, final int n2) {
        switch (n) {
            case 32768: {
                this.a[0] = n2;
            }
            case 32769: {
                switch (this.a[0]) {
                    case 64: {
                        this.g(n2 + 0);
                        this.h(n2 + 1);
                        break;
                    }
                    case 65: {
                        this.i(n2 + 0);
                        this.j(n2 + 1);
                        break;
                    }
                    case 66: {
                        this.k(n2);
                        break;
                    }
                    case 67: {
                        this.l(n2);
                        break;
                    }
                    case 68: {
                        this.m(n2);
                        break;
                    }
                    case 69: {
                        this.n(n2);
                        break;
                    }
                    case 70: {
                        this.m(n2);
                        break;
                    }
                    case 71: {
                        this.l(n2);
                        break;
                    }
                }
            }
            case 40960: {
                if ((n2 & 0x1) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
            }
            case 49152: {
                this.a = n2;
            }
            case 49153: {
                this.b = n2;
            }
            case 57344: {
                this.a = false;
            }
            case 57345: {
                this.a = true;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n <= 239 && (super.a.a.a.b & 0x18) != 0x0 && 0 != --this.a) {
            this.a = this.b;
            return 3;
        }
        return 0;
    }
}
