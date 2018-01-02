// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public class bO implements dv
{
    private int a;
    private int b;
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    
    public bO() {
        this.b();
    }
    
    private bO(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final boolean b, final int n7, final int n8, final int n9) {
        this.a(n, n2, s, n3, n4, n5, n6, b, n7, n8, n9);
    }
    
    public bO(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this(n, n2, "", n3, n4, n5, -1, false, -3, n7, n8);
    }
    
    protected final void a(final x x) {
        this.a = 12;
        this.d = x.b().c();
        this.e = x.b().d();
        this.f = x.c().b();
        this.h = false;
        if (x.b().b() == 1) {
            this.b = 2;
            this.g = this.f / (2 * this.d);
        }
        else if (x.b().b() == 6) {
            this.b = 11;
            this.g = this.f / this.d;
        }
        else if (x.b().b() == 7) {
            this.b = 12;
            this.g = this.f / this.d;
        }
        else if (x.b().b() == 64) {
            this.b = 21;
            this.g = -1;
        }
        else if (x.b().b() == 17) {
            this.b = 31;
            this.g = -1;
        }
        else if (x.b().b() == 2) {
            this.b = 32;
            this.g = -1;
        }
        else if (x.b().b() == 49) {
            this.b = 41;
            this.g = this.f / 65 * 320;
        }
        else {
            this.b = -1;
            this.g = -1;
        }
        this.i = x.b().f();
        this.j = this.d * this.e * this.i;
        this.k = x.b().e();
    }
    
    private void a(final int a, final int b, final String c, final int d, final int e, final int f, final int g, final boolean h, final int i, final int k, final int j) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.k = k;
        this.j = j;
    }
    
    public final void b() {
        this.a(0, 0, "", -1, -1, -1, -1, false, -1, -1, -1);
    }
    
    public final int c() {
        return this.a;
    }
    
    public final void a(final int a) {
        this.a = a;
    }
    
    public final int d() {
        return this.b;
    }
    
    public final void b(final int b) {
        this.b = b;
    }
    
    public final String e() {
        return this.c;
    }
    
    public final void a(final String c) {
        this.c = c;
    }
    
    public final int f() {
        return this.d;
    }
    
    public final void c(final int d) {
        this.d = d;
    }
    
    public final int g() {
        return this.e;
    }
    
    public final void d(final int e) {
        this.e = e;
    }
    
    public final int h() {
        return this.f;
    }
    
    public final void e(final int f) {
        this.f = f;
    }
    
    public final int i() {
        return this.g;
    }
    
    public final void f(final int g) {
        this.g = g;
    }
    
    public final boolean j() {
        return this.h;
    }
    
    public final void a(final boolean h) {
        this.h = h;
    }
    
    public final int k() {
        return this.i;
    }
    
    public final void g(final int i) {
        this.i = i;
    }
    
    public final int l() {
        return this.k;
    }
    
    public final void h(final int k) {
        this.k = k;
    }
    
    public final int m() {
        return this.j;
    }
    
    public final void i(final int j) {
        this.j = j;
    }
    
    public String toString() {
        return "format=" + bO.u[this.a] + "\tencoding=" + bO.v[this.b] + "\tchannels=" + this.d + "\tsamplerate=" + this.e + "\tdatasize=" + this.f + "\tsamplesize=" + this.i + "\tblocksize=" + this.k + "\tavgbitrate=" + this.j;
    }
}
