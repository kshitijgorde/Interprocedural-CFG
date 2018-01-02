// 
// Decompiled by Procyon v0.5.30
// 

public final class aG extends at
{
    private int a;
    private boolean a;
    private int b;
    public int[] a;
    
    public aG() {
        this.a = 0;
        this.a = false;
        this.b = 0;
        this.a = new int[3];
    }
    
    public final int a() {
        return 64;
    }
    
    public final void a() {
        this.c(this.b() - 1);
        this.d(this.b() - 1);
        this.e(this.b() - 1);
        this.f(this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.b = 0;
        this.a = 0;
        (this.a = false)[0] = false;
        this.a[1] = 0;
        this.a[2] = 0;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int b) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF003) {
            case 32768: {
                this.a[0] = (b & 0xF);
                this.a[1] = (b & 0x40);
                this.a[2] = (b & 0x80);
            }
            case 32769: {
                switch (this.a[0]) {
                    case 0: {
                        if (this.a[2] != 0) {
                            this.k(b + 0);
                            this.l(b + 1);
                            break;
                        }
                        this.g(b + 0);
                        this.h(b + 1);
                        break;
                    }
                    case 1: {
                        if (this.a[2] != 0) {
                            this.m(b + 0);
                            this.n(b + 1);
                            break;
                        }
                        this.i(b + 0);
                        this.j(b + 1);
                        break;
                    }
                    case 2: {
                        if (this.a[2] != 0) {
                            this.g(b);
                            break;
                        }
                        this.k(b);
                        break;
                    }
                    case 3: {
                        if (this.a[2] != 0) {
                            this.h(b);
                            break;
                        }
                        this.l(b);
                        break;
                    }
                    case 4: {
                        if (this.a[2] != 0) {
                            this.i(b);
                            break;
                        }
                        this.m(b);
                        break;
                    }
                    case 5: {
                        if (this.a[2] != 0) {
                            this.j(b);
                            break;
                        }
                        this.n(b);
                        break;
                    }
                    case 6: {
                        if (this.a[1] != 0) {
                            this.d(b);
                            break;
                        }
                        this.c(b);
                        break;
                    }
                    case 7: {
                        if (this.a[1] != 0) {
                            this.e(b);
                            break;
                        }
                        this.d(b);
                        break;
                    }
                    case 8: {
                        this.h(b);
                        break;
                    }
                    case 9: {
                        this.j(b);
                        break;
                    }
                    case 15: {
                        if (this.a[1] != 0) {
                            this.c(b);
                            break;
                        }
                        this.e(b);
                        break;
                    }
                }
            }
            case 40960: {
                if ((b & 0x1) == 0x0) {
                    this.b();
                    return;
                }
                this.c();
            }
            case 49152: {
                this.b = b;
                this.a = this.b;
            }
            case 49153: {
                this.a = this.b;
            }
            case 57344: {
                this.a = false;
                this.a = this.b;
            }
            case 57345: {
                this.a = true;
                this.a = this.b;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if (this.a && n >= 0 && n <= 239 && (super.a.a.a.b & 0x18) != 0x0 && --this.a == 0) {
            this.a = this.b;
            return 3;
        }
        return 0;
    }
}
