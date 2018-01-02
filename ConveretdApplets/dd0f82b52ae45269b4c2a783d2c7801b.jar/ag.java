// 
// Decompiled by Procyon v0.5.30
// 

public class ag
{
    public V a;
    public h b;
    public short[] c;
    public a d;
    public R e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    int m;
    
    public void a(final V a) {
        this.a = a;
        this.b = a.e;
        this.c = this.b.a;
        this.d = a.j;
        this.e = a.c;
        this.h = -1;
    }
    
    public void a(final a a) {
        if (!a.a() || a.b() <= 0) {
            return;
        }
        this.g();
        this.h();
        this.c();
        this.a.b.a(2);
    }
    
    public void a(final int n, final short n2) {
        if (n < 8192) {
            this.b.a[n & 0x7FF] = n2;
            return;
        }
        if (n > 16407) {
            this.b.a[n] = n2;
            if (n >= 24576 && n < 32768 && this.d != null) {
                this.d.a(n, n2);
            }
        }
        else {
            if (n > 8199 && n < 16384) {
                this.b(8192 + (n & 0x7), n2);
                return;
            }
            this.b(n, n2);
        }
    }
    
    public short a(int n) {
        if (this.j && this.a.k.b[n]) {
            this.m = this.a.k.a(n);
            if (this.a.k.b(this.m) == 0) {
                return (short)this.a.k.c(this.m);
            }
            if (this.c[n] == this.a.k.d(this.m)) {
                return (short)this.a.k.c(this.m);
            }
        }
        if ((n &= 0xFFFF) > 16407) {
            return this.c[n];
        }
        if (n >= 8192) {
            return this.c(n);
        }
        return this.c[n & 0x7FF];
    }
    
    public void a() {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = false;
    }
    
    public void a(final boolean b) {
        this.j = true;
    }
    
    public void b() {
    }
    
    public void c() {
        final short[] f;
        if (this.d.a && (f = this.d.f()) != null && f.length == 8192) {
            System.arraycopy(f, 0, this.a.e.a, 24576, 8192);
        }
    }
    
    public void d() {
        this.a = null;
        this.b = null;
        this.d = null;
        this.e = null;
    }
    
    public void a(final boolean i, final int k, final int l) {
        this.i = i;
        this.k = k;
        this.l = l;
    }
    
    public void b(final int n) {
    }
    
    public short c(int max) {
        Label_0548: {
            switch (max >> 12) {
                case 0: {}
                case 2:
                case 3: {
                    switch (max & 0x7) {
                        case 0: {
                            return this.b.a[8192];
                        }
                        case 1: {
                            return this.b.a[8193];
                        }
                        case 2: {
                            return this.e.c();
                        }
                        case 3: {
                            return 0;
                        }
                        case 4: {
                            return this.e.d();
                        }
                        case 5: {
                            return 0;
                        }
                        case 6: {
                            return 0;
                        }
                        case 7: {
                            return this.e.e();
                        }
                        default: {
                            break Label_0548;
                        }
                    }
                    break;
                }
                case 4: {
                    switch (max - 16405) {
                        case 0: {
                            final P d = this.a.d;
                            final w e;
                            final o f;
                            final q g;
                            final int n = 0x0 | d.c.e() | d.d.e() << 1 | (((e = d.e).j != 0 && e.b) ? 1 : 0) << 2 | (((f = d.f).g != 0 && f.b) ? 1 : 0) << 3 | (((g = d.g).k != 0 && g.b) ? 1 : 0) << 4 | ((d.m && d.l) ? 1 : 0) << 6 | (d.g.c ? 1 : 0) << 7;
                            d.m = false;
                            d.g.c = false;
                            return (short)n;
                        }
                        case 1: {
                            return this.e();
                        }
                        case 2: {
                            if (this.i && this.a.c != null && this.a.c.d != null) {
                                max = Math.max(0, this.k - 4);
                                final int min = Math.min(256, this.k + 4);
                                final int max2 = Math.max(0, this.l - 4);
                                final int min2 = Math.min(240, this.l + 4);
                                int n2 = 0;
                                for (int i = max2; i < min2; ++i) {
                                    for (int j = max; j < min; ++j) {
                                        if ((this.a.c.d[(i << 8) + j] & 0xFFFFFF) == 0xFFFFFF) {
                                            n2 = 8;
                                            break;
                                        }
                                    }
                                }
                                return (short)(this.f() | (n2 | (this.i ? 16 : 0)));
                            }
                            return this.f();
                        }
                        default: {
                            break Label_0548;
                        }
                    }
                    break;
                }
            }
        }
        return 0;
    }
    
    public void b(int n, final short n2) {
        switch (n) {
            case 8192: {
                final h b = this.b;
                n = n;
                b.a[n] = n2;
                this.e.b((int)n2);
            }
            case 8193: {
                final h b2 = this.b;
                n = n;
                b2.a[n] = n2;
                this.e.c((int)n2);
            }
            case 8195: {
                this.e.b = n2;
            }
            case 8196: {
                this.e.a(n2);
            }
            case 8197: {
                this.e.b(n2);
            }
            case 8198: {
                this.e.d((int)n2);
            }
            case 8199: {
                this.e.c(n2);
            }
            case 16404: {
                this.e.d(n2);
            }
            case 16405: {
                this.a.d.a(n, n2);
            }
            case 16406: {
                if (n2 == 0 && this.h == 1) {
                    this.f = 0;
                    this.g = 0;
                }
                this.h = n2;
            }
            case 16407: {
                this.a.d.a(n, n2);
            }
            default: {
                if (n >= 16384 && n <= 16407) {
                    this.a.d.a(n, n2);
                }
            }
        }
    }
    
    public short e() {
        final n a = this.a.a.a();
        short n = 0;
        switch (this.f) {
            case 0: {
                n = a.a(0);
                break;
            }
            case 1: {
                n = a.a(1);
                break;
            }
            case 2: {
                n = a.a(3);
                break;
            }
            case 3: {
                n = a.a(2);
                break;
            }
            case 4: {
                n = a.a(4);
                break;
            }
            case 5: {
                n = a.a(5);
                break;
            }
            case 6: {
                n = a.a(6);
                break;
            }
            case 7: {
                n = a.a(7);
                break;
            }
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18: {
                n = 0;
                break;
            }
            case 19: {
                n = 1;
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        ++this.f;
        if (this.f == 24) {
            this.f = 0;
        }
        return n;
    }
    
    public short f() {
        final n b = this.a.a.b();
        final int g = this.g;
        ++this.g;
        if (this.g == 24) {
            this.g = 0;
        }
        if (g == 0) {
            return b.a(0);
        }
        if (g == 1) {
            return b.a(1);
        }
        if (g == 2) {
            return b.a(3);
        }
        if (g == 3) {
            return b.a(2);
        }
        if (g == 4) {
            return b.a(4);
        }
        if (g == 5) {
            return b.a(5);
        }
        if (g == 6) {
            return b.a(6);
        }
        if (g == 7) {
            return b.a(7);
        }
        if (g == 16) {
            return 0;
        }
        if (g == 17) {
            return 0;
        }
        if (g == 18) {
            return 1;
        }
        if (g == 19) {
            return 0;
        }
        return 0;
    }
    
    protected void g() {
        if (this.d.b() > 1) {
            this.a(0, 32768);
            this.a(1, 49152);
            return;
        }
        this.a(0, 32768);
        this.a(0, 49152);
    }
    
    protected void h() {
        if (this.d.c() > 0) {
            if (this.d.c() == 1) {
                this.b(0, 0);
                this.b(0, 4096);
                return;
            }
            this.b(0, 0);
            this.b(1, 4096);
        }
    }
    
    protected void a(int n, final int n2) {
        n %= this.d.b();
        System.arraycopy(this.d.a(n), 0, this.b.a, n2, 16384);
    }
    
    protected void b(final int n, final int n2) {
        if (this.d.c() == 0) {
            return;
        }
        this.e.f();
        System.arraycopy(this.d.b(n % this.d.c()), 0, this.a.f.a, n2, 4096);
        System.arraycopy(this.d.c(n % this.d.c()), 0, this.e.c, n2 >> 4, 256);
    }
    
    protected void c(final int n, final int n2) {
        this.a((n << 1) % this.d.b(), 32768);
        this.a(((n << 1) + 1) % this.d.b(), 49152);
    }
    
    protected void d(final int n, final int n2) {
        if (this.d.c() == 0) {
            return;
        }
        this.e.f();
        this.b(n % this.d.c(), 0);
        this.b((n + 1) % this.d.c(), 4096);
    }
    
    protected void e(final int n, int n2) {
        if (this.d.c() == 0) {
            return;
        }
        this.e.f();
        final int n3 = n / 4 % this.d.c();
        System.arraycopy(this.d.b(n3), 0, this.a.f.a, n % 4 << 10, 1024);
        final ab[] c = this.d.c(n3);
        n2 >>= 4;
        for (int i = 0; i < 64; ++i) {
            this.e.c[n2 + i] = c[(n % 4 << 6) + i];
        }
    }
    
    protected void f(final int n, int n2) {
        if (this.d.c() == 0) {
            return;
        }
        this.e.f();
        final int n3 = n / 2 % this.d.c();
        System.arraycopy(this.d.b(n3), n % 2 << 11, this.a.f.a, n2, 2048);
        final ab[] c = this.d.c(n3);
        n2 >>= 4;
        for (int i = 0; i < 128; ++i) {
            this.e.c[n2 + i] = c[(n % 2 << 7) + i];
        }
    }
    
    protected void g(int n, int n2) {
        final int n3 = n / 2 % this.d.b();
        n = n % 2 << 13;
        final short[] a = this.d.a(n3);
        final h b = this.b;
        final int n4 = n2;
        final short[] array = a;
        final int n5 = n;
        n = 8192;
        final int n6 = n5;
        final short[] array2 = array;
        n2 = n4;
        final h h = b;
        if (n2 + 8192 <= h.a.length) {
            System.arraycopy(array2, n6, h.a, n2, 8192);
        }
    }
}
