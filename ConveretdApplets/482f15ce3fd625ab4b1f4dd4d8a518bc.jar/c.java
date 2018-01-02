// 
// Decompiled by Procyon v0.5.30
// 

public final class c extends at
{
    public boolean a;
    
    public c() {
        this.a = false;
    }
    
    public final int a() {
        return 70;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final long n) {
        if (n == 3148188645L) {
            this.a = true;
        }
        if (n == 3717124343L || n == 3508805237L || n == 2427987139L) {
            this.a = true;
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        final int n3 = n2 & 0xF;
        final int n4 = (n2 & 0x70) >> 4;
        this.c(n4 * 2 + 0);
        this.d(n4 * 2 + 1);
        this.g(n3 * 8 + 0);
        this.h(n3 * 8 + 1);
        this.i(n3 * 8 + 2);
        this.j(n3 * 8 + 3);
        this.k(n3 * 8 + 4);
        this.l(n3 * 8 + 5);
        this.m(n3 * 8 + 6);
        this.n(n3 * 8 + 7);
        if (this.a) {
            if ((n2 & 0x80) != 0x0) {
                this.c();
                return;
            }
            this.b();
        }
        else {
            if ((n2 & 0x80) != 0x0) {
                this.b(1, 1, 1, 1);
                return;
            }
            this.b(0, 0, 0, 0);
        }
    }
}
