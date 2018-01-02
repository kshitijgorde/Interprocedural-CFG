// 
// Decompiled by Procyon v0.5.30
// 

public final class G extends at
{
    public int[] a;
    
    public G() {
        this.a = new int[4];
    }
    
    public final int a() {
        return 46;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a[0] = 0;
        this.a[1] = 0;
        this.a[2] = 0;
        this.a[3] = 0;
        this.f();
        this.b();
    }
    
    public final void a(final int n, final int n2) {
        if (n >= 24576 && n < 32768) {
            this.a[0] = (n2 & 0xF);
            this.a[1] = (n2 & 0xF0) >> 4;
            this.f();
            return;
        }
        if (n >= 32768) {
            this.a[2] = (n2 & 0x1);
            this.a[3] = (n2 & 0x70) >> 4;
            this.f();
        }
    }
    
    private void f() {
        this.c(this.a[0] * 8 + this.a[2] * 4 + 0);
        this.d(this.a[0] * 8 + this.a[2] * 4 + 1);
        this.e(this.a[0] * 8 + this.a[2] * 4 + 2);
        this.f(this.a[0] * 8 + this.a[2] * 4 + 3);
        this.g(this.a[1] * 64 + this.a[3] * 8 + 0);
        this.h(this.a[1] * 64 + this.a[3] * 8 + 1);
        this.i(this.a[1] * 64 + this.a[3] * 8 + 2);
        this.j(this.a[1] * 64 + this.a[3] * 8 + 3);
        this.k(this.a[1] * 64 + this.a[3] * 8 + 4);
        this.l(this.a[1] * 64 + this.a[3] * 8 + 5);
        this.m(this.a[1] * 64 + this.a[3] * 8 + 6);
        this.n(this.a[1] * 64 + this.a[3] * 8 + 7);
    }
}
