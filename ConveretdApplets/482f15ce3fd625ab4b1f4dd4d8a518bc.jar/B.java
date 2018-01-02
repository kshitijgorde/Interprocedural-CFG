import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class B extends at
{
    public int a;
    public int[][] a;
    public int[] a;
    public int[] b;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    
    public B() {
        this.a = 0;
        this.a = new int[8][2];
        this.b = new int[7];
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }
    
    public final int a() {
        return 5;
    }
    
    public final void a(final ap a) {
        super.a = a;
        this.a();
    }
    
    public final void a() {
        this.a = 1;
        this.a = super.a.d;
        for (int i = 0; i < 65536; ++i) {
            this.a[i] = super.a.c[i];
        }
        this.e(3, 0);
        this.a(this.b() - 1, this.b() - 1, this.b() - 1, this.b() - 1);
        if (this.c() > 0) {
            this.a(0, 1, 2, 3, 4, 5, 6, 7);
        }
        for (int j = 0; j < 8; ++j) {
            this.a[j][0] = j;
            this.a[j][1] = (j & 0x3) + 4;
        }
        this.b[3] = 0;
        final int[] b = this.b;
        final int n = 4;
        final int[] b2 = this.b;
        final int n2 = 5;
        final int[] b3 = this.b;
        final int n3 = 6;
        final int n4 = 8;
        b3[n3] = n4;
        b[n] = (b2[n2] = n4);
        this.b = 3;
        this.c = 2;
        this.d = 1;
        this.e = 3;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }
    
    public final void b(final int n, int n2) {
        switch (n) {
            case 20736: {
                this.b = (n2 & 0x3);
            }
            case 20737: {
                this.e = (n2 & 0x3);
            }
            case 20738: {
                this.c = (n2 & 0x3);
            }
            case 20739: {
                this.d = (n2 & 0x3);
            }
            case 20740: {
                this.f = (n2 & 0x3);
            }
            case 20741: {
                this.o(n2 & 0x3);
                n2 >>= 2;
                this.p(n2 & 0x3);
                n2 >>= 2;
                this.q(n2 & 0x3);
                n2 >>= 2;
                this.r(n2 & 0x3);
            }
            case 20742: {
                System.out.println("5106");
                for (int i = 0; i < 960; ++i) {
                    super.a.a.c[11264 + i] = n2;
                }
            }
            case 20743: {
                n2 = ((n2 &= 0x3) | n2 << 2 | n2 << 4 | n2 << 6);
                System.out.println("5107");
                for (int j = 0; j < 960; ++j) {
                    super.a.a.c[11264 + j] = n2;
                }
            }
            case 20755: {
                this.e(3, n2 & 0x7);
            }
            case 20756:
            case 20757:
            case 20758:
            case 20759: {
                this.d(n & 0x7, n2);
            }
            case 20768:
            case 20769:
            case 20770:
            case 20771:
            case 20772:
            case 20773:
            case 20774:
            case 20775: {
                this.a[n & 0x7][0] = n2;
                this.s(0);
            }
            case 20776:
            case 20777:
            case 20778:
            case 20779: {
                this.a[(n & 0x3) + 0][1] = n2;
                this.a[(n & 0x3) + 4][1] = n2;
            }
            case 20992: {}
            case 20993: {}
            case 20994: {}
            case 20995: {
                this.i = n2;
            }
            case 20996: {
                this.g = n2;
            }
            case 20997: {}
            case 20998: {}
            default: {
                if (n >= 20480 && n <= 20501) {
                    return;
                }
                if (n >= 23552 && n <= 24575 && this.f != 3) {
                    super.a.a.c[10240 + (n & 0x3FF)] = n2;
                }
            }
        }
    }
    
    public final void a(final int n, final int n2) {
        if (this.c == 2 && this.d == 1) {
            if (n >= 32768 && n <= 40959) {
                if (this.b[4] != 8) {
                    this.a[this.b[4] * 8192 + (n & 0x1FFF)] = n2;
                    super.a.c[this.b[4] * 8192 + (n & 0x1FFF)] = n2;
                }
            }
            else if (n >= 40960 && n <= 49151) {
                if (this.b[5] != 8) {
                    this.a[this.b[5] * 8192 + (n & 0x1FFF)] = n2;
                    super.a.c[this.b[5] * 8192 + (n & 0x1FFF)] = n2;
                }
            }
            else if (n >= 49152 && n <= 57343 && this.b[6] != 8) {
                this.a[this.b[6] * 8192 + (n & 0x1FFF)] = n2;
                super.a.c[this.b[6] * 8192 + (n & 0x1FFF)] = n2;
            }
        }
    }
    
    public final int a(final int n) {
        if (n <= 240) {
            if (n == this.i && (super.a.a.a.b & 0x18) != 0x0) {
                this.h |= 0x80;
            }
            if ((this.h & 0x80) != 0x0 && (this.g & 0x80) != 0x0) {
                return 3;
            }
        }
        else {
            this.h |= 0x40;
        }
        return 0;
    }
    
    private void d(final int n, final int n2) {
        if ((n2 & 0x80) != 0x0) {
            if (this.b == 0 && n == 7) {
                this.c((n2 & 0x7C) + 0);
                this.d((n2 & 0x7C) + 1);
                this.e((n2 & 0x7C) + 2);
                this.f((n2 & 0x7C) + 3);
                final int[] b = this.b;
                final int n3 = 4;
                final int[] b2 = this.b;
                final int n4 = 5;
                final int[] b3 = this.b;
                final int n5 = 6;
                final int n6 = 8;
                b3[n5] = n6;
                b[n3] = (b2[n4] = n6);
            }
            if (this.b == 1) {
                if (n == 5) {
                    this.c((n2 & 0x7E) + 0);
                    this.d((n2 & 0x7E) + 1);
                    this.b[4] = (this.b[5] = 8);
                }
                if (n == 7) {
                    this.e((n2 & 0x7E) + 0);
                    this.f((n2 & 0x7E) + 1);
                    this.b[6] = 8;
                }
            }
            if (this.b == 2) {
                if (n == 5) {
                    this.c((n2 & 0x7E) + 0);
                    this.d((n2 & 0x7E) + 1);
                    this.b[4] = (this.b[5] = 8);
                }
                if (n == 6) {
                    this.e(n2 & 0x7F);
                    this.b[6] = 8;
                }
                if (n == 7) {
                    this.f(n2 & 0x7F);
                }
            }
            if (this.b == 3) {
                if (n == 4) {
                    this.c(n2 & 0x7F);
                    this.b[4] = 8;
                }
                if (n == 5) {
                    this.d(n2 & 0x7F);
                    this.b[5] = 8;
                }
                if (n == 6) {
                    this.e(n2 & 0x7F);
                    this.b[6] = 8;
                }
                if (n == 7) {
                    this.f(n2 & 0x7F);
                }
            }
        }
        else {
            if (this.b == 1 && n == 5) {
                this.e(4, (n2 & 0x6) + 0);
                this.e(5, (n2 & 0x6) + 1);
            }
            if (this.b == 2) {
                if (n == 5) {
                    this.e(4, (n2 & 0x6) + 0);
                    this.e(5, (n2 & 0x6) + 1);
                }
                if (n == 6) {
                    this.e(6, n2 & 0x7);
                }
            }
            if (this.b == 3) {
                if (n == 4) {
                    this.e(4, n2 & 0x7);
                }
                if (n == 5) {
                    this.e(5, n2 & 0x7);
                }
                if (n == 6) {
                    this.e(6, n2 & 0x7);
                }
            }
        }
    }
    
    private void e(final int n, int n2) {
        if (n2 != 8) {
            if (this.a == 1) {
                n2 = ((n2 > 3) ? 8 : 0);
            }
            if (this.a == 2) {
                n2 = ((n2 > 3) ? 1 : 0);
            }
            if (this.a == 3) {
                n2 = ((n2 > 3) ? 8 : n2);
            }
            if (this.a == 4) {
                n2 = ((n2 > 3) ? 4 : n2);
            }
        }
        this.b[n] = n2;
    }
    
    public final int a(final int n, final int n2) {
        if (this.f != 1 || n != 1) {
            this.s(n);
        }
        return 0;
    }
    
    private void s(final int n) {
        if (this.e == 0) {
            this.g(this.a[7][n] * 8 + 0);
            this.h(this.a[7][n] * 8 + 1);
            this.i(this.a[7][n] * 8 + 2);
            this.j(this.a[7][n] * 8 + 3);
            this.k(this.a[7][n] * 8 + 4);
            this.l(this.a[7][n] * 8 + 5);
            this.m(this.a[7][n] * 8 + 6);
            this.n(this.a[7][n] * 8 + 7);
            return;
        }
        if (this.e == 1) {
            this.g(this.a[3][n] * 4 + 0);
            this.h(this.a[3][n] * 4 + 1);
            this.i(this.a[3][n] * 4 + 2);
            this.j(this.a[3][n] * 4 + 3);
            this.k(this.a[7][n] * 4 + 0);
            this.l(this.a[7][n] * 4 + 1);
            this.m(this.a[7][n] * 4 + 2);
            this.n(this.a[7][n] * 4 + 3);
            return;
        }
        if (this.e == 2) {
            this.g(this.a[1][n] * 2 + 0);
            this.h(this.a[1][n] * 2 + 1);
            this.i(this.a[3][n] * 2 + 0);
            this.j(this.a[3][n] * 2 + 1);
            this.k(this.a[5][n] * 2 + 0);
            this.l(this.a[5][n] * 2 + 1);
            this.m(this.a[7][n] * 2 + 0);
            this.n(this.a[7][n] * 2 + 1);
            return;
        }
        this.g(this.a[0][n]);
        this.h(this.a[1][n]);
        this.i(this.a[2][n]);
        this.j(this.a[3][n]);
        this.k(this.a[4][n]);
        this.l(this.a[5][n]);
        this.m(this.a[6][n]);
        this.n(this.a[7][n]);
    }
    
    public final void a(final InputStream inputStream) throws IOException {
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
    }
}
