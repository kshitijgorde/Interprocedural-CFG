// 
// Decompiled by Procyon v0.5.30
// 

public final class N extends at
{
    private int[] a;
    
    public N() {
        this.a = new int[1];
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 26624) {
            this.a[0] = (n & 0xFF);
            this.c((this.a[0] & 0x7) * 4 + 0);
            this.d((this.a[0] & 0x7) * 4 + 1);
            this.e((this.a[0] & 0x7) * 4 + 2);
            this.f((this.a[0] & 0x7) * 4 + 3);
            if ((this.a[0] & 0x20) != 0x0) {
                this.c();
            }
            else {
                this.b();
            }
        }
        if (n >= 32768 && (this.a[0] & 0x4) != 0x0) {
            final int n3 = (this.a[0] & 0x18) >> 1 | (n2 & 0x3);
            this.g(n3 * 8 + 0);
            this.h(n3 * 8 + 1);
            this.i(n3 * 8 + 2);
            this.j(n3 * 8 + 3);
            this.k(n3 * 8 + 4);
            this.l(n3 * 8 + 5);
            this.m(n3 * 8 + 6);
            this.n(n3 * 8 + 7);
        }
    }
    
    public final int a() {
        return 41;
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
    }
}
