// 
// Decompiled by Procyon v0.5.30
// 

public class p
{
    public byte[] a;
    public int b;
    public int c;
    public int d;
    public int[] e;
    public long[] f;
    public int g;
    public int h;
    public int i;
    public int j;
    public byte[] k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public long q;
    public long r;
    
    public p() {
        this.k = new byte[282];
        this.b = 16384;
        this.a = new byte[this.b];
        this.g = 1024;
        this.e = new int[this.g];
        this.f = new long[this.g];
    }
    
    public void a(final int o) {
        if (this.a == null) {
            this.b = 16384;
            this.a = new byte[this.b];
            this.g = 1024;
            this.e = new int[this.g];
            this.f = new long[this.g];
        }
        else {
            for (int i = 0; i < this.a.length; ++i) {
                this.a[i] = 0;
            }
            for (int j = 0; j < this.e.length; ++j) {
                this.e[j] = 0;
            }
            for (int k = 0; k < this.f.length; ++k) {
                this.f[k] = 0L;
            }
        }
        this.o = o;
    }
    
    public void a() {
        this.a = null;
        this.e = null;
        this.f = null;
    }
    
    public int a(final r r) {
        int j = this.j;
        if (this.i <= j) {
            return 0;
        }
        if ((this.e[j] & 0x400) != 0x0) {
            ++this.j;
            ++this.q;
            return -1;
        }
        int i = this.e[j] & 0xFF;
        final int n = 0;
        r.a = this.a;
        r.b = this.d;
        r.e = (this.e[j] & 0x200);
        r.d = (this.e[j] & 0x100);
        int c = n + i;
        while (i == 255) {
            final int n2 = this.e[++j];
            i = (n2 & 0xFF);
            if ((n2 & 0x200) != 0x0) {
                r.e = 512;
            }
            c += i;
        }
        r.g = this.q;
        r.f = this.f[j];
        r.c = c;
        this.d += c;
        this.j = j + 1;
        ++this.q;
        return 1;
    }
    
    public int a(final q q) {
        final byte[] a = q.a;
        final int b = q.b;
        final byte[] d = q.d;
        int e = q.e;
        int f = q.f;
        int i = 0;
        final int a2 = q.a();
        final int b2 = q.b();
        int c = q.c();
        final int d2 = q.d();
        final long e2 = q.e();
        final int f2 = q.f();
        final int g = q.g();
        final int n = a[b + 26] & 0xFF;
        final int j = this.j;
        final int d3 = this.d;
        if (d3 != 0) {
            this.c -= d3;
            if (this.c != 0) {
                System.arraycopy(this.a, d3, this.a, 0, this.c);
            }
            this.d = 0;
        }
        if (j != 0) {
            if (this.h - j != 0) {
                System.arraycopy(this.e, j, this.e, 0, this.h - j);
                System.arraycopy(this.f, j, this.f, 0, this.h - j);
            }
            this.h -= j;
            this.i -= j;
            this.j = 0;
        }
        if (f2 != this.o) {
            return -1;
        }
        if (a2 > 0) {
            return -1;
        }
        if (this.g <= this.h + n + 1) {
            this.g += n + 1 + 32;
            final int[] e3 = new int[this.g];
            System.arraycopy(this.e, 0, e3, 0, this.e.length);
            this.e = e3;
            final long[] f3 = new long[this.g];
            System.arraycopy(this.f, 0, f3, 0, this.f.length);
            this.f = f3;
        }
        if (g != this.p) {
            for (int k = this.i; k < this.h; ++k) {
                this.c -= (this.e[k] & 0xFF);
            }
            this.h = this.i;
            if (this.p != -1) {
                this.e[this.h++] = 1024;
                ++this.i;
            }
            if (b2 != 0) {
                c = 0;
                while (i < n) {
                    final int n2 = a[b + 27 + i] & 0xFF;
                    e += n2;
                    f -= n2;
                    if (n2 < 255) {
                        ++i;
                        break;
                    }
                    ++i;
                }
            }
        }
        if (f != 0) {
            if (this.b <= this.c + f) {
                this.b += f + 1024;
                final byte[] a3 = new byte[this.b];
                System.arraycopy(this.a, 0, a3, 0, this.a.length);
                this.a = a3;
            }
            System.arraycopy(d, e, this.a, this.c, f);
            this.c += f;
        }
        int h = -1;
        while (i < n) {
            final int n3 = a[b + 27 + i] & 0xFF;
            this.e[this.h] = n3;
            this.f[this.h] = -1L;
            if (c != 0) {
                final int[] e4 = this.e;
                final int h2 = this.h;
                e4[h2] |= 0x100;
                c = 0;
            }
            if (n3 < 255) {
                h = this.h;
            }
            ++this.h;
            ++i;
            if (n3 < 255) {
                this.i = this.h;
            }
        }
        if (h != -1) {
            this.f[h] = e2;
        }
        if (d2 != 0) {
            this.m = 1;
            if (this.h > 0) {
                final int[] e5 = this.e;
                final int n4 = this.h - 1;
                e5[n4] |= 0x200;
            }
        }
        this.p = g + 1;
        return 0;
    }
    
    public int b() {
        this.c = 0;
        this.d = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.p = -1;
        this.q = 0L;
        this.r = 0L;
        return 0;
    }
}
