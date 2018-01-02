// 
// Decompiled by Procyon v0.5.30
// 

class q
{
    float[][][] a;
    g[] b;
    k[] c;
    float[][] d;
    float[][] e;
    int[][] f;
    a9[] g;
    int h;
    int i;
    int[] j;
    int[] k;
    int[] l;
    int[] m;
    int n;
    int p;
    int[] q;
    int r;
    int s;
    float t;
    float u;
    int v;
    int[] w;
    int[] x;
    
    q() {
        this.h = 0;
        this.i = 0;
        this.n = 0;
        this.p = 0;
        this.s = 1024;
        this.r = this.s * 3;
        this.q = new int[this.r];
        this.v = 128;
        this.w = new int[this.v];
        this.x = new int[this.v];
        final boolean b = false;
        this.p = (b ? 1 : 0);
        this.n = (b ? 1 : 0);
        this.i = (b ? 1 : 0);
        this.h = (b ? 1 : 0);
    }
    
    void a() {
        final boolean l = c.l;
        final int p = this.p + (this.p >> 2) + 100;
        final int[] j = new int[p];
        final int[] k = new int[p];
        final int[] i = new int[p];
        final int[] m = new int[p];
        int n = 0;
        while (true) {
            while (true) {
                Label_0095: {
                    if (!l) {
                        break Label_0095;
                    }
                    j[n] = this.j[n];
                    k[n] = this.k[n];
                    i[n] = this.l[n];
                    m[n] = this.m[n];
                    ++n;
                }
                if (n < this.n) {
                    continue;
                }
                break;
            }
            this.j = j;
            this.k = k;
            this.l = i;
            this.m = m;
            this.p = p;
            if (!l) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean l = c.l;
        final int i = (this.i << 1) + 1;
        final float[][][] a = new float[i][][];
        final g[] b = new g[i];
        final k[] c = new k[i];
        final float[][] d = new float[i][];
        final float[][] e = new float[i][];
        final int[][] f = new int[i][];
        final a9[] g = new a9[i];
        int n = 0;
        while (true) {
            while (true) {
                Label_0147: {
                    if (!l) {
                        break Label_0147;
                    }
                    a[n] = this.a[n];
                    c[n] = this.c[n];
                    d[n] = this.d[n];
                    e[n] = this.e[n];
                    f[n] = this.f[n];
                    g[n] = this.g[n];
                    b[n] = this.b[n];
                    ++n;
                }
                if (n < this.h) {
                    continue;
                }
                break;
            }
            this.a = a;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
            this.i = i;
            this.b = b;
            if (!l) {
                return;
            }
            continue;
        }
    }
    
    void c() {
        if (this.c != null) {
            int n = 0;
            while (true) {
                Label_0025: {
                    if (!c.l) {
                        break Label_0025;
                    }
                    this.c[n] = null;
                    ++n;
                }
                if (n < this.c.length) {
                    continue;
                }
                break;
            }
        }
        this.c = null;
        this.b = null;
        this.a = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.l = null;
        this.k = null;
        this.j = null;
        this.q = null;
        this.w = null;
        this.x = null;
        this.p = 0;
        this.n = 0;
    }
    
    void a(float t, final float n) {
        final boolean l = c.l;
        this.n = 0;
        this.h = 0;
        int n2 = 0;
        while (true) {
            Label_0032: {
                if (!l) {
                    break Label_0032;
                }
                this.q[n2] = -1;
                ++n2;
            }
            if (n2 >= this.r) {
                if (t < 0.0f) {
                    t = 0.0f;
                }
                if (n <= t) {
                    final float n3 = 0.0f;
                    this.u = n3;
                    this.t = n3;
                    if (!l) {
                        return;
                    }
                }
                this.t = t;
                this.u = (this.s - 1) / (n - t);
                return;
            }
            continue;
        }
    }
    
    final int a(final g g, final float[] array, final int[] array2, final float[][] array3, final k k, final a9 a9) {
        if (this.h == this.i) {
            this.b();
        }
        this.b[this.h] = g;
        this.a[this.h] = array3;
        this.c[this.h] = k;
        this.d[this.h] = array;
        this.e[this.h] = g.ab;
        this.f[this.h] = array2;
        this.g[this.h] = a9;
        return this.h++;
    }
    
    final void a(final int n, final float n2, final int n3, final int n4, final boolean b, final int n5) {
        if (this.n == this.p) {
            this.a();
        }
        int n6 = (int)((n2 - this.t) * this.u);
        if (n6 < 0) {
            n6 = 0;
        }
        if (n6 >= this.s) {
            n6 = this.s - 1;
        }
        if (b) {
            n6 = this.s - 1 - n6;
        }
        if (n4 > 0) {
            n6 += n4 * this.s;
        }
        final int n7 = this.q[n6];
        this.l[this.n] = n;
        this.k[this.n] = n3;
        this.q[n6] = this.n;
        this.j[this.n] = n7;
        this.m[this.n] = n5;
        ++this.n;
    }
    
    final void a(final r r) {
        int n = -1;
        int n2 = 0;
        float[][] array = null;
        k k = null;
        g g = null;
        int[] array2 = null;
        float[] array3 = null;
        float[] array4 = null;
        a9 a9 = null;
        int n3 = 0;
        int n4 = 0;
        while (true) {
            if (n3 >= this.r) {
                n4 = n2;
                if (!c.l) {
                    break;
                }
            }
            else {
                final int n5 = this.q[n3];
            }
            int i = n4;
            if (i != -1) {
                do {
                    final int n6 = this.k[i];
                    if (n6 != n || n2 == this.v) {
                        if (n2 > 0) {
                            k.h = g;
                            k.a(this.w, n2, array3, array4, array2, array, r, g.an, a9, this.x);
                        }
                        k = this.c[n6];
                        g = this.b[n6];
                        array3 = this.d[n6];
                        array4 = this.e[n6];
                        array2 = this.f[n6];
                        a9 = this.g[n6];
                        array = this.a[n6];
                        n = n6;
                        n2 = 0;
                    }
                    this.x[n2] = this.m[i];
                    this.w[n2++] = this.l[i];
                    i = this.j[i];
                } while (i != -1);
            }
            ++n3;
        }
        if (n4 > 0) {
            k.h = g;
            k.a(this.w, n2, array3, array4, array2, array, r, g.an, a9, this.x);
        }
    }
}
