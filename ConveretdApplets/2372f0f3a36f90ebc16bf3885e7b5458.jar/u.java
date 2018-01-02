// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    public int a;
    public s b;
    public int c;
    public float[][] d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public long m;
    public long n;
    public long o;
    public long p;
    public long q;
    public long r;
    public float[][][][][] s;
    public Object[][] t;
    public ah[] u;
    public Object[] v;
    
    public u() {
        this.t = new Object[2][];
        this.s = new float[2][][][][];
        (this.s[0] = new float[2][][][])[0] = new float[2][][];
        this.s[0][1] = new float[2][][];
        this.s[0][0][0] = new float[2][];
        this.s[0][0][1] = new float[2][];
        this.s[0][1][0] = new float[2][];
        this.s[0][1][1] = new float[2][];
        (this.s[1] = new float[2][][][])[0] = new float[2][][];
        this.s[1][1] = new float[2][][];
        this.s[1][0][0] = new float[2][];
        this.s[1][0][1] = new float[2][];
        this.s[1][1][0] = new float[2][];
        this.s[1][1][1] = new float[2][];
    }
    
    public static float[] a(final int n, final int n2, final int n3, final int n4) {
        final float[] array = new float[n2];
        switch (n) {
            case 0: {
                final int n5 = n2 / 4 - n3 / 2;
                final int n6 = n2 - n2 / 4 - n4 / 2;
                for (int i = 0; i < n3; ++i) {
                    final float n7 = (float)Math.sin((float)((i + 0.5) / n3 * 3.1415927410125732 / 2.0));
                    array[i + n5] = (float)Math.sin((float)(n7 * n7 * 1.5707963705062866));
                }
                for (int j = n5 + n3; j < n6; ++j) {
                    array[j] = 1.0f;
                }
                for (int k = 0; k < n4; ++k) {
                    final float n8 = (float)Math.sin((float)((n4 - k - 0.5) / n4 * 3.1415927410125732 / 2.0));
                    array[k + n6] = (float)Math.sin((float)(n8 * n8 * 1.5707963705062866));
                }
                return array;
            }
            default: {
                return null;
            }
        }
    }
    
    public int a(final s b) {
        this.b = b;
        this.c = ae.a(b.i, 2);
        this.t[0] = new Object[1];
        this.t[1] = new Object[1];
        this.t[0][0] = new ag();
        this.t[1][0] = new ag();
        ((ag)this.t[0][0]).a(b.h[0]);
        ((ag)this.t[1][0]).a(b.h[1]);
        this.s[0][0][0] = new float[1][];
        this.s[0][0][1] = this.s[0][0][0];
        this.s[0][1][0] = this.s[0][0][0];
        this.s[0][1][1] = this.s[0][0][0];
        this.s[1][0][0] = new float[1][];
        this.s[1][0][1] = new float[1][];
        this.s[1][1][0] = new float[1][];
        this.s[1][1][1] = new float[1][];
        for (int i = 0; i < 1; ++i) {
            this.s[0][0][0][i] = a(i, b.h[0], b.h[0] / 2, b.h[0] / 2);
            this.s[1][0][0][i] = a(i, b.h[1], b.h[0] / 2, b.h[0] / 2);
            this.s[1][0][1][i] = a(i, b.h[1], b.h[0] / 2, b.h[1] / 2);
            this.s[1][1][0][i] = a(i, b.h[1], b.h[1] / 2, b.h[0] / 2);
            this.s[1][1][1][i] = a(i, b.h[1], b.h[1] / 2, b.h[1] / 2);
        }
        this.u = new ah[b.n];
        for (int j = 0; j < b.n; ++j) {
            (this.u[j] = new ah()).a(b.y[j]);
        }
        this.e = 8192;
        this.d = new float[b.c][];
        for (int k = 0; k < b.c; ++k) {
            this.d[k] = new float[this.e];
        }
        this.i = 0;
        this.j = 0;
        this.l = b.h[1] / 2;
        this.f = this.l;
        this.v = new Object[b.i];
        for (int l = 0; l < b.i; ++l) {
            final int d = b.p[l].d;
            this.v[l] = aj.a[b.q[d]].a(this, b.p[l], b.r[d]);
        }
        this.g = this.l;
        this.l -= b.h[this.j] / 4 + b.h[this.i] / 4;
        this.m = -1L;
        this.n = -1L;
        return 0;
    }
    
    public int a(final v v) {
        if (this.l > this.b.h[1] / 2 && this.g > 8192) {
            final int n = this.l - this.b.h[1] / 2;
            final int n2 = (this.g < n) ? this.g : n;
            this.f -= n2;
            this.l -= n2;
            this.g -= n2;
            if (n2 != 0) {
                for (int i = 0; i < this.b.c; ++i) {
                    System.arraycopy(this.d[i], n2, this.d[i], 0, this.f);
                }
            }
        }
        this.i = this.j;
        this.j = v.d;
        this.k = -1;
        this.o += v.l;
        this.p += v.m;
        this.q += v.n;
        this.r += v.o;
        if (this.n + 1L != v.j) {
            this.m = -1L;
        }
        this.n = v.j;
        final int n3 = this.b.h[this.j];
        int l = this.l + this.b.h[this.i] / 4 + n3 / 4;
        final int n4 = l - n3 / 2;
        final int f = n4 + n3;
        int n5 = 0;
        int n6 = 0;
        if (f > this.e) {
            this.e = f + this.b.h[1];
            for (int j = 0; j < this.b.c; ++j) {
                final float[] array = new float[this.e];
                System.arraycopy(this.d[j], 0, array, 0, this.d[j].length);
                this.d[j] = array;
            }
        }
        switch (this.j) {
            case 0: {
                n5 = 0;
                n6 = this.b.h[0] / 2;
                break;
            }
            case 1: {
                n5 = this.b.h[1] / 4 - this.b.h[this.i] / 4;
                n6 = n5 + this.b.h[this.i] / 2;
                break;
            }
        }
        for (int k = 0; k < this.b.c; ++k) {
            final int n7 = n4;
            int n8;
            for (n8 = n5; n8 < n6; ++n8) {
                final float[] array2 = this.d[k];
                final int n9 = n7 + n8;
                array2[n9] += v.a[k][n8];
            }
            while (n8 < n3) {
                this.d[k][n7 + n8] = v.a[k][n8];
                ++n8;
            }
        }
        if (this.m == -1L) {
            this.m = v.i;
        }
        else {
            this.m += l - this.l;
            if (v.i != -1L && this.m != v.i) {
                if (this.m > v.i && v.h != 0) {
                    l -= (int)(this.m - v.i);
                }
                this.m = v.i;
            }
        }
        this.l = l;
        this.f = f;
        if (v.h != 0) {
            this.h = 1;
        }
        return 0;
    }
    
    public int a(final float[][][] array, final int[] array2) {
        if (this.g < this.l) {
            if (array != null) {
                for (int i = 0; i < this.b.c; ++i) {
                    array2[i] = this.g;
                }
                array[0] = this.d;
            }
            return this.l - this.g;
        }
        return 0;
    }
    
    public int a(final int n) {
        if (n != 0 && this.g + n > this.l) {
            return -1;
        }
        this.g += n;
        return 0;
    }
    
    public void a() {
    }
}
