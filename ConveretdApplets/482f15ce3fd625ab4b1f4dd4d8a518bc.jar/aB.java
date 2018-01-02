// 
// Decompiled by Procyon v0.5.30
// 

public final class aB extends at
{
    private int[] a;
    
    public aB() {
        this.a = new int[4];
    }
    
    public final int a() {
        return 68;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        switch (n & 0xF000) {
            case 32768: {
                this.g(n2 * 2 + 0);
                this.h(n2 * 2 + 1);
            }
            case 36864: {
                this.i(n2 * 2 + 0);
                this.j(n2 * 2 + 1);
            }
            case 40960: {
                this.k(n2 * 2 + 0);
                this.l(n2 * 2 + 1);
            }
            case 45056: {
                this.m(n2 * 2 + 0);
                this.n(n2 * 2 + 1);
            }
            case 49152: {
                this.a[2] = n2;
                this.f();
            }
            case 53248: {
                this.a[3] = n2;
                this.f();
            }
            case 57344: {
                this.a[0] = (n2 & 0x10) >> 4;
                this.a[1] = (n2 & 0x3);
                this.f();
            }
            case 61440: {
                this.c(n2 * 2);
                this.d(n2 * 2 + 1);
                break;
            }
        }
    }
    
    public final void a() {
        this.a(0, 1, this.b() - 2, this.b() - 1);
        this.a[0] = 0;
        this.a[1] = 0;
        this.a[2] = 0;
        this.a[3] = 0;
    }
    
    private void f() {
        if (this.a[0] != 0) {
            if (this.a[1] == 0) {
                this.o(this.a[2] + 128);
                this.p(this.a[3] + 128);
                this.q(this.a[2] + 128);
                this.r(this.a[3] + 128);
                return;
            }
            if (this.a[1] == 1) {
                this.o(this.a[2] + 128);
                this.p(this.a[2] + 128);
                this.q(this.a[3] + 128);
                this.r(this.a[3] + 128);
                return;
            }
            if (this.a[1] == 2) {
                this.o(this.a[2] + 128);
                this.p(this.a[2] + 128);
                this.q(this.a[2] + 128);
                this.r(this.a[2] + 128);
                return;
            }
            if (this.a[1] == 3) {
                this.o(this.a[3] + 128);
                this.p(this.a[3] + 128);
                this.q(this.a[3] + 128);
                this.r(this.a[3] + 128);
            }
        }
        else {
            if (this.a[1] == 0) {
                this.b();
                return;
            }
            if (this.a[1] == 1) {
                this.c();
                return;
            }
            if (this.a[1] == 2) {
                this.b(0, 0, 0, 0);
                return;
            }
            if (this.a[1] == 3) {
                this.b(1, 1, 1, 1);
            }
        }
    }
}
