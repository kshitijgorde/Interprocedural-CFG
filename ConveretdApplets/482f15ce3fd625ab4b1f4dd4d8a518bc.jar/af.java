// 
// Decompiled by Procyon v0.5.30
// 

public final class af extends at
{
    private int[] a;
    
    public af() {
        this.a = new int[1];
    }
    
    public final int a() {
        return 57;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a(0, 1, 0, 1);
        this.a(0, 1, 2, 3, 4, 5, 6, 7);
        this.a[0] = 0;
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        switch (n) {
            case 32768:
            case 32769:
            case 32770:
            case 32771: {
                if ((n2 & 0x40) != 0x0) {
                    this.g(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 0);
                    this.h(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 1);
                    this.i(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 2);
                    this.j(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 3);
                    this.k(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 4);
                    this.l(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 5);
                    this.m(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 6);
                    this.n(8 * ((n2 & 0x3) + ((this.a[0] & 0x10) >> 1) + (this.a[0] & 0x7)) + 7);
                    return;
                }
                break;
            }
            case 34816: {
                this.a[0] = n2;
                if ((n2 & 0x80) != 0x0) {
                    this.c(4 * ((n2 & 0x40) >> 6) + 8 + 0);
                    this.d(4 * ((n2 & 0x40) >> 6) + 8 + 1);
                    this.e(4 * ((n2 & 0x40) >> 6) + 8 + 2);
                    this.f(4 * ((n2 & 0x40) >> 6) + 8 + 3);
                }
                else {
                    this.c(2 * ((n2 & 0x60) >> 5) + 0);
                    this.d(2 * ((n2 & 0x60) >> 5) + 1);
                    this.e(2 * ((n2 & 0x60) >> 5) + 0);
                    this.f(2 * ((n2 & 0x60) >> 5) + 1);
                }
                this.g(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 0);
                this.h(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 1);
                this.i(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 2);
                this.j(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 3);
                this.k(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 4);
                this.l(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 5);
                this.m(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 6);
                this.n(8 * ((n2 & 0x7) + ((n2 & 0x10) >> 1)) + 7);
                if ((n2 & 0x8) != 0x0) {
                    this.c();
                    return;
                }
                this.b();
                break;
            }
        }
    }
}
