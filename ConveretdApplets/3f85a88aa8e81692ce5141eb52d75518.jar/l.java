// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public int[] a;
    public boolean b;
    public byte[] c;
    public int d;
    public int e;
    public int f;
    public static final int[] g;
    public double h;
    public int i;
    public int j;
    public int k;
    public int l;
    public double m;
    public double[] n;
    public double[] o;
    public boolean p;
    public double q;
    public double[] r;
    public double[] s;
    public double[] t;
    public double[] u;
    public int[] v;
    public int[] w;
    public int[] x;
    public int[] y;
    public int[] z;
    
    public l(final int n, final int k, final int l, final double m, final int n2) {
        this.a = new int[32];
        this.h = 0.0;
        this.n = new double[32];
        this.o = new double[1024];
        for (int i = 1; i < 32; ++i) {
            this.a[i] = (this.a[i - 1] << 1 | 0x1);
        }
        this.l = l;
        if (k == 0) {
            this.q = (l * n / n2 - 36.0 - 32.0) / n;
        }
        else {
            this.q = (l * n / n2 - 36.0 - 4 * (11 + k)) / n;
        }
        this.p = (this.q < 1.4999999);
        this.i = (((n & 0xFFFC) < 1024) ? (n & 0xFFFC) : 1024);
        this.j = this.i / 4;
        this.k = k;
        this.m = m;
        this.u = new double[this.i];
        this.r = new double[9];
        this.s = new double[9];
        this.t = new double[9];
        this.v = new int[8];
        this.w = new int[this.i];
        this.x = new int[4];
        this.y = new int[4];
        this.z = new int[4];
    }
    
    public void a(final int n, final byte[] c, final short[] array) {
        this.b = false;
        this.c = c;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        for (int i = 0; i < 8; ++i) {
            this.v[i] = this.a(6 - i / 2);
        }
        if (this.k > 0) {
            for (int j = 0; j < 4; ++j) {
                this.x[j] = this.b(this.k);
                this.y[j] = this.b(3);
            }
        }
        this.z[0] = this.b(8);
        this.z[1] = this.b(8);
        this.z[2] = this.b(8);
        this.z[3] = this.b(8);
        for (int k = 0; k < 4; ++k) {
            final int n2 = (this.z[k] >> 6 != 0) ? 1 : 0;
            final int n3 = (this.z[k] >> 6) - 1;
            for (int l = 0; l < this.j; ++l) {
                if (n2 != 0 && ((this.p && l % 3 != n3) || (!this.p && l % 2 != n3))) {
                    this.w[k * this.j + l] = 0;
                }
                else {
                    this.w[k * this.j + l] = this.b() + n2;
                    if ((n2 != 0 || this.w[k * this.j + l] != 0) && this.a() != 0) {
                        this.w[k * this.j + l] = -this.w[k * this.j + l];
                    }
                }
            }
        }
        for (int n4 = 0; n4 < this.i / this.j; ++n4) {
            final int n5 = this.x[n4] + this.j;
            double n6;
            if (this.y[n4] == 7) {
                n6 = 1.0;
            }
            else if (this.y[n4] == 0) {
                n6 = 0.0;
            }
            else {
                n6 = (this.y[n4] + 0.5) / 8.0;
            }
            final double n7 = (this.z[n4] & 0x7) << 0 + l.g[(this.z[n4] & 0x38) >> 3];
            final boolean b = this.z[n4] >> 6 != 0;
            final int n8 = (this.z[n4] >> 6) - 1;
            for (int n9 = n4 * this.j; n9 < (n4 + 1) * this.j; ++n9) {
                final int n10 = this.w[n9];
                if (b && ((this.p && (n9 - n4 * this.j) % 3 != n8) || (!this.p && (n9 - n4 * this.j) % 2 != n8))) {
                    this.u[n9] = 0.0;
                }
                else if (b) {
                    this.u[n9] = n10 * n7 - ((n10 > 0) ? (n7 / 2.0) : ((n10 < 0) ? (-n7 / 2.0) : 0.0));
                }
                else {
                    this.u[n9] = n10 * n7 + ((n10 > 0) ? (n7 / 2.0) : ((n10 < 0) ? (-n7 / 2.0) : 0.0));
                }
            }
            for (int n11 = 0; n11 < this.j; ++n11) {
                final double[] u = this.u;
                final int n12 = n11 + n4 * this.j;
                u[n12] += n6 * this.o[4 * this.j - n5 + n11];
            }
            for (int n13 = 0; n13 < 3 * this.j; ++n13) {
                this.o[n13] = this.o[n13 + this.j];
            }
            for (int n14 = 0; n14 < this.j; ++n14) {
                this.o[3 * this.j + n14] = this.u[n14 + n4 * this.j];
            }
        }
        this.s[0] = (this.v[0] * 512.0 - 0.0) / 20480.0;
        this.s[1] = (this.v[1] * 512.0 - 0.0) / 20480.0;
        this.s[2] = (this.v[2] * 512.0 - 2048.0) / 20480.0;
        this.s[3] = (this.v[3] * 512.0 + 2560.0) / 20480.0;
        this.s[4] = (this.v[4] * 512.0 - 94.0) / 13964.0;
        this.s[5] = (this.v[5] * 512.0 + 1792.0) / 15360.0;
        this.s[6] = (this.v[6] * 512.0 + 341.0) / 8534.0;
        this.s[7] = (this.v[7] * 512.0 + 1144.0) / 9036.0;
        for (int n15 = 0; n15 < 8; ++n15) {
            double n16 = this.s[n15];
            if (n16 < 0.0) {
                n16 = -n16;
            }
            double n17;
            if (n16 < 0.3375) {
                n17 = n16 * 2.0;
            }
            else if (n16 < 0.6125) {
                n17 = n16 + 0.3375;
            }
            else {
                n17 = n16 / 4.0 + 0.796875;
            }
            if (this.s[n15] < 0.0) {
                this.s[n15] = -n17;
            }
            else {
                this.s[n15] = n17;
            }
        }
        this.a(this.t, this.s, this.i, this.u, this.u, this.n);
        for (int n18 = 0; n18 < this.i; ++n18) {
            this.h = this.u[n18] + this.h * this.m;
            this.u[n18] = this.h;
        }
        System.arraycopy(this.s, 0, this.t, 0, 9);
        for (int n19 = 0; n19 < n; ++n19) {
            array[n19] = (short)((this.u[n19] > 32767.0) ? 32767 : ((this.u[n19] < -32768.0) ? -32768 : ((short)this.u[n19])));
        }
    }
    
    private void a(final double[] array, final double[] array2, int n, final double[] array3, final double[] array4, final double[] array5) {
        final double[] array6 = new double[32];
        final int n2 = n;
        final int n3 = n2 / 4;
        for (int i = 0; i < 8; ++i) {
            array6[i] = array2[i];
        }
        int n4 = 0;
        while (n-- > 0) {
            if (n > n2 - n3) {
                for (int j = 0; j < 8; ++j) {
                    array6[j] = (array[j] * (n - n2 + n3) + array2[j] * (n2 - n)) / n3;
                }
            }
            double n5 = array3[n4];
            int n6 = 8;
            while (n6-- > 0) {
                n5 -= array6[n6] * array5[n6];
                array5[n6 + 1] = array6[n6] * n5 + array5[n6];
            }
            array4[n4] = (array5[0] = n5);
            ++n4;
        }
    }
    
    public int a(final int n) {
        int b = this.b(n);
        if ((b & 1 << n - 1) != 0x0) {
            b |= -1 << n;
        }
        return b;
    }
    
    public int a() {
        if (this.e == 0) {
            this.d = this.c[this.f++];
            this.e = 8;
        }
        final boolean b = (this.d & 0x80) != 0x0;
        this.d <<= 1;
        --this.e;
        return b ? 1 : 0;
    }
    
    public int b(int i) {
        int n = 0;
        while (i > 0) {
            if (this.e == 0) {
                this.d = this.c[this.f++];
                this.e = 8;
            }
            n = (n << 1 | (((this.d & 0x80) == 0x80) ? 1 : 0));
            this.d <<= 1;
            --this.e;
            --i;
        }
        return n;
    }
    
    public int b() {
        int n = 0;
        while (this.a() != 0) {
            ++n;
        }
        return n;
    }
    
    static {
        g = new int[] { 0, 1, 2, 3, 5, 7, 9, 11 };
    }
}
