// 
// Decompiled by Procyon v0.5.30
// 

public final class P extends at
{
    private int a;
    private int b;
    private int[] a;
    
    public P() {
        this.a = 0;
        this.b = 0;
        this.a = new int[8];
    }
    
    public final int a() {
        return 183;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = i;
        }
        this.b = 0;
        this.a = 0;
    }
    
    public final void a(final int n, final int b) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 34816: {
                this.c(b);
            }
            case 43008: {
                this.d(b);
            }
            case 40960: {
                this.e(b);
            }
            case 45056: {
                this.g(this.a[0] = ((this.a[0] & 0xF0) | (b & 0xF)));
            }
            case 45060: {
                this.g(this.a[0] = ((this.a[0] & 0xF) | (b & 0xF) << 4));
            }
            case 45064: {
                this.h(this.a[1] = ((this.a[1] & 0xF0) | (b & 0xF)));
            }
            case 45068: {
                this.h(this.a[1] = ((this.a[1] & 0xF) | (b & 0xF) << 4));
            }
            case 49152: {
                this.i(this.a[2] = ((this.a[2] & 0xF0) | (b & 0xF)));
            }
            case 49156: {
                this.i(this.a[2] = ((this.a[2] & 0xF) | (b & 0xF) << 4));
            }
            case 49160: {
                this.j(this.a[3] = ((this.a[3] & 0xF0) | (b & 0xF)));
            }
            case 49164: {
                this.j(this.a[3] = ((this.a[3] & 0xF) | (b & 0xF) << 4));
            }
            case 53248: {
                this.k(this.a[4] = ((this.a[4] & 0xF0) | (b & 0xF)));
            }
            case 53252: {
                this.k(this.a[4] = ((this.a[4] & 0xF) | (b & 0xF) << 4));
            }
            case 53256: {
                this.l(this.a[5] = ((this.a[5] & 0xF0) | (b & 0xF)));
            }
            case 53260: {
                this.l(this.a[5] = ((this.a[5] & 0xF) | (b & 0xF) << 4));
            }
            case 57344: {
                this.m(this.a[6] = ((this.a[6] & 0xF0) | (b & 0xF)));
            }
            case 57348: {
                this.m(this.a[6] = ((this.a[6] & 0xF) | (b & 0xF) << 4));
            }
            case 57352: {
                this.n(this.a[7] = ((this.a[7] & 0xF0) | (b & 0xF)));
            }
            case 57356: {
                this.n(this.a[7] = ((this.a[7] & 0xF) | (b & 0xF) << 4));
            }
            case 36872: {
                if (b == 1) {
                    for (int i = 0; i < this.a.length; ++i) {
                        this.a[i] = i;
                    }
                    this.a(0, 1, this.b() - 2, this.b() - 1);
                    this.a(0, 1, 2, 3, 4, 5, 6, 7);
                    return;
                }
                break;
            }
            case 38912: {
                if (b == 0) {
                    this.b();
                    return;
                }
                if (b == 1) {
                    this.c();
                    return;
                }
                if (b == 2) {
                    this.b(0, 0, 0, 0);
                    return;
                }
                if (b == 3) {
                    this.b(1, 1, 1, 1);
                    return;
                }
                break;
            }
            case 61440: {
                this.a = ((this.a & 0xFF00) | b);
            }
            case 61444: {
                this.a = ((this.a & 0xFF) | b << 8);
            }
            case 61448: {
                this.b = b;
                break;
            }
        }
    }
    
    public final int a(final int n) {
        if ((this.b & 0x2) != 0x0) {
            if (this.a <= 113) {
                this.a = 0;
                return 3;
            }
            this.a -= 113;
        }
        return 0;
    }
}
