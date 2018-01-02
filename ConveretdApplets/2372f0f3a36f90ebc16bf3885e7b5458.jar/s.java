// 
// Decompiled by Procyon v0.5.30
// 

public class s
{
    public static byte[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int[] h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public af[] p;
    public int[] q;
    public Object[] r;
    public int[] s;
    public Object[] t;
    public int[] u;
    public Object[] v;
    public int[] w;
    public Object[] x;
    public ac[] y;
    public ad[] z;
    
    public s() {
        this.h = new int[2];
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = new ad[64];
    }
    
    public void a() {
        this.d = 0;
    }
    
    public void b() {
        for (int i = 0; i < this.i; ++i) {
            this.p[i] = null;
        }
        this.p = null;
        for (int j = 0; j < this.j; ++j) {
            z.a[this.q[j]].a(this.r[j]);
        }
        this.r = null;
        this.t = null;
        for (int k = 0; k < this.l; ++k) {
            aa.a[this.u[k]].a(this.v[k]);
        }
        this.v = null;
        for (int l = 0; l < this.m; ++l) {
            ab.a[this.w[l]].a(this.x[l]);
        }
        this.x = null;
        for (int n = 0; n < this.n; ++n) {
            if (this.y[n] != null) {
                this.y[n].a();
                this.y[n] = null;
            }
        }
        this.y = null;
        for (int n2 = 0; n2 < this.o; ++n2) {
            this.z[n2].a();
        }
    }
    
    public int a(final w w) {
        this.b = w.d(32);
        if (this.b != 0) {
            return -1;
        }
        this.c = w.d(8);
        this.d = w.d(32);
        this.e = w.d(32);
        this.f = w.d(32);
        this.g = w.d(32);
        this.h[0] = 1 << w.d(4);
        this.h[1] = 1 << w.d(4);
        if (this.d < 1 || this.c < 1 || this.h[0] < 8 || this.h[1] < this.h[0] || w.d(1) != 1) {
            this.b();
            return -1;
        }
        return 0;
    }
    
    public int b(final w w) {
        this.n = w.d(8) + 1;
        if (this.y == null || this.y.length != this.n) {
            this.y = new ac[this.n];
        }
        for (int i = 0; i < this.n; ++i) {
            this.y[i] = new ac();
            if (this.y[i].a(w) != 0) {
                this.b();
                return -1;
            }
        }
        this.k = w.d(6) + 1;
        if (this.s == null || this.s.length != this.k) {
            this.s = new int[this.k];
        }
        if (this.t == null || this.t.length != this.k) {
            this.t = new Object[this.k];
        }
        for (int j = 0; j < this.k; ++j) {
            this.s[j] = w.d(16);
            if (this.s[j] < 0 || this.s[j] >= 1) {
                this.b();
                return -1;
            }
            this.t[j] = "";
            if (this.t[j] == null) {
                this.b();
                return -1;
            }
        }
        this.l = w.d(6) + 1;
        if (this.u == null || this.u.length != this.l) {
            this.u = new int[this.l];
        }
        if (this.v == null || this.v.length != this.l) {
            this.v = new Object[this.l];
        }
        for (int k = 0; k < this.l; ++k) {
            this.u[k] = w.d(16);
            if (this.u[k] < 0 || this.u[k] >= 2) {
                this.b();
                return -1;
            }
            this.v[k] = aa.a[this.u[k]].a(this, w);
            if (this.v[k] == null) {
                this.b();
                return -1;
            }
        }
        this.m = w.d(6) + 1;
        if (this.w == null || this.w.length != this.m) {
            this.w = new int[this.m];
        }
        if (this.x == null || this.x.length != this.m) {
            this.x = new Object[this.m];
        }
        for (int l = 0; l < this.m; ++l) {
            this.w[l] = w.d(16);
            if (this.w[l] < 0 || this.w[l] >= 3) {
                this.b();
                return -1;
            }
            this.x[l] = ab.a[this.w[l]].a(this, w);
            if (this.x[l] == null) {
                this.b();
                return -1;
            }
        }
        this.j = w.d(6) + 1;
        if (this.q == null || this.q.length != this.j) {
            this.q = new int[this.j];
        }
        if (this.r == null || this.r.length != this.j) {
            this.r = new Object[this.j];
        }
        for (int n = 0; n < this.j; ++n) {
            this.q[n] = w.d(16);
            if (this.q[n] < 0 || this.q[n] >= 1) {
                this.b();
                return -1;
            }
            this.r[n] = z.a[this.q[n]].a(this, w);
            if (this.r[n] == null) {
                this.b();
                return -1;
            }
        }
        this.i = w.d(6) + 1;
        if (this.p == null || this.p.length != this.i) {
            this.p = new af[this.i];
        }
        for (int n2 = 0; n2 < this.i; ++n2) {
            this.p[n2] = new af();
            this.p[n2].a = w.d(1);
            this.p[n2].b = w.d(16);
            this.p[n2].c = w.d(16);
            this.p[n2].d = w.d(8);
            if (this.p[n2].b >= 1 || this.p[n2].c >= 1 || this.p[n2].d >= this.j) {
                this.b();
                return -1;
            }
        }
        if (w.d(1) != 1) {
            this.b();
            return -1;
        }
        return 0;
    }
    
    public int a(final t t, final r r) {
        final w w = new w();
        if (r != null) {
            w.a(r.a, r.b, r.c);
            final byte[] array = new byte[6];
            final int d = w.d(8);
            int n = 0;
            int n2 = 6;
            while (n2-- != 0) {
                array[n++] = (byte)w.d(8);
            }
            if (array[0] != 118 || array[1] != 111 || array[2] != 114 || array[3] != 98 || array[4] != 105 || array[5] != 115) {
                return -1;
            }
            switch (d) {
                case 1: {
                    if (r.d == 0) {
                        return -1;
                    }
                    if (this.d != 0) {
                        return -1;
                    }
                    return this.a(w);
                }
                case 3: {
                    if (this.d == 0) {
                        return -1;
                    }
                    return t.a(w);
                }
                case 5: {
                    if (this.d == 0 || t.e == null) {
                        return -1;
                    }
                    return this.b(w);
                }
            }
        }
        return -1;
    }
    
    static {
        s.a = zkmToString("\u0002bc\u0017[\u0007").getBytes();
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 't';
                    break;
                }
                case 1: {
                    c2 = '\r';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'u';
                    break;
                }
                default: {
                    c2 = '2';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
