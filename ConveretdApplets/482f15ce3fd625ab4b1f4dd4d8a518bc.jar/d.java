// 
// Decompiled by Procyon v0.5.30
// 

public final class d extends at
{
    private int[] a;
    
    public d() {
        this.a = new int[1];
    }
    
    public final int a() {
        return 76;
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
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        if (n == 32768) {
            this.a[0] = n2;
            return;
        }
        if (n == 32769) {
            switch (this.a[0] & 0x7) {
                case 2: {
                    this.g(n2 * 2 + 0);
                    this.h(n2 * 2 + 1);
                }
                case 3: {
                    this.i(n2 * 2 + 0);
                    this.j(n2 * 2 + 1);
                }
                case 4: {
                    this.k(n2 * 2 + 0);
                    this.l(n2 * 2 + 1);
                }
                case 5: {
                    this.m(n2 * 2 + 0);
                    this.n(n2 * 2 + 1);
                }
                case 6: {
                    this.c(n2);
                }
                case 7: {
                    this.d(n2);
                    break;
                }
            }
        }
    }
}
