// 
// Decompiled by Procyon v0.5.30
// 

public final class e extends at
{
    private int[] a;
    
    public e() {
        this.a = new int[2];
    }
    
    public final int a() {
        return 75;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        if (this.c() >= 8) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.a[0] = 0;
        this.a[1] = 1;
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n & 0xF000) {
            case 32768: {
                this.c(n2);
            }
            case 36864: {
                if ((n2 & 0x1) != 0x0) {
                    this.c();
                }
                else {
                    this.b();
                }
                this.a[0] = ((this.a[0] & 0xF) | (n2 & 0x2) << 3);
                this.g(this.a[0] * 4 + 0);
                this.h(this.a[0] * 4 + 1);
                this.i(this.a[0] * 4 + 2);
                this.j(this.a[0] * 4 + 3);
                this.a[1] = ((this.a[1] & 0xF) | (n2 & 0x4) << 2);
                this.k(this.a[1] * 4 + 0);
                this.l(this.a[1] * 4 + 1);
                this.m(this.a[1] * 4 + 2);
                this.n(this.a[1] * 4 + 3);
            }
            case 40960: {
                this.d(n2);
            }
            case 49152: {
                this.e(n2);
            }
            case 57344: {
                this.a[0] = ((this.a[0] & 0x10) | (n2 & 0xF));
                this.g(this.a[0] * 4 + 0);
                this.h(this.a[0] * 4 + 1);
                this.i(this.a[0] * 4 + 2);
                this.j(this.a[0] * 4 + 3);
            }
            case 61440: {
                this.a[1] = ((this.a[1] & 0x10) | (n2 & 0xF));
                this.k(this.a[1] * 4 + 0);
                this.l(this.a[1] * 4 + 1);
                this.m(this.a[1] * 4 + 2);
                this.n(this.a[1] * 4 + 3);
                break;
            }
        }
    }
}
