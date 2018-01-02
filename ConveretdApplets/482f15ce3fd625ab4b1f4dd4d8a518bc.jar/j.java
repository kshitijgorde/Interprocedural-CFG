// 
// Decompiled by Procyon v0.5.30
// 

public final class j extends at
{
    public int[] a;
    
    public j() {
        this.a = new int[2];
    }
    
    public final int a() {
        return 226;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        if (n < 32768) {
            return;
        }
        if ((n & 0x1) != 0x0) {
            this.a[1] = n2;
        }
        else {
            this.a[0] = n2;
        }
        if ((this.a[0] & 0x40) != 0x0) {
            this.b();
        }
        else {
            this.c();
        }
        final int n3 = (this.a[0] & 0x1E) >> 1 | (this.a[0] & 0x80) >> 3 | (this.a[1] & 0x1) << 5;
        if ((this.a[0] & 0x20) == 0x0) {
            this.c(n3 * 4 + 0);
            this.d(n3 * 4 + 1);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
            return;
        }
        if ((this.a[0] & 0x1) != 0x0) {
            this.c(n3 * 4 + 2);
            this.d(n3 * 4 + 3);
            this.e(n3 * 4 + 2);
            this.f(n3 * 4 + 3);
            return;
        }
        this.c(n3 * 4 + 0);
        this.d(n3 * 4 + 1);
        this.e(n3 * 4 + 0);
        this.f(n3 * 4 + 1);
    }
    
    public final void a() {
        this.a(0, 1, 2, 3);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        this.a[0] = 0;
        this.a[1] = 0;
    }
}
