// 
// Decompiled by Procyon v0.5.30
// 

public final class m
{
    public float[][] a;
    public int w;
    public float[] b;
    public float[] d;
    public float[] f;
    public float[] g;
    public float[] a;
    public float[] c;
    public boolean e;
    public boolean g;
    public boolean d;
    public boolean c;
    public int s;
    public int g;
    public int t;
    public int x;
    public int c;
    public int u;
    public float[] e;
    public boolean a;
    public j a;
    public int[] a;
    public int f;
    public int p;
    public int i;
    public i a;
    public int[] b;
    public int o;
    public int l;
    public int d;
    public int q;
    public int m;
    public boolean b;
    public boolean f;
    public int j;
    public int e;
    public int k;
    public int[] d;
    public int[] c;
    public int b;
    public int a;
    public int n;
    public int h;
    public int v;
    public int r;
    
    private final int b(final int n) {
        return n & 0x7;
    }
    
    public final void a(final int w) {
        this.w = w;
        this.e = true;
        this.g = true;
        this.d = false;
        this.c = true;
        this.a = null;
    }
    
    public final float[] a() {
        if (this.w == this.a.length) {
            final float[][] a = new float[this.w << 1][24];
            System.arraycopy(this.a, 0, a, 0, this.w);
            this.a = a;
            this.b = new float[this.a.length];
            this.d = new float[this.a.length];
            this.f = new float[this.a.length];
            this.g = new float[this.a.length];
            this.a = new float[this.a.length];
            this.c = new float[this.a.length];
        }
        return this.a[this.w++];
    }
    
    public final boolean a(final float n, final float n2, final float n3) {
        if (this.w < 2) {
            return false;
        }
        if (Math.abs(this.a[this.w - 2][9] - n) < 1.0E-4f && Math.abs(this.a[this.w - 2][10] - n2) < 1.0E-4f && Math.abs(this.a[this.w - 2][11] - n3) < 1.0E-4f) {
            --this.w;
            return true;
        }
        return false;
    }
    
    public final void a(final i a) {
        this.a = a;
        this.b = a.e;
        this.l = a.b;
        this.o = a.k;
        this.m = a.K;
        this.q = this.l - 1;
        this.d = this.o - 1;
        this.d = true;
    }
    
    public final void a() {
        if (this.w < 3) {
            return;
        }
        this.a = this.a.e;
        this.e = this.a.d;
        this.a = j.a[5];
        this.f = this.a.j;
        boolean b = false;
        if (this.a.c && !j.a[3]) {
            b = true;
        }
        this.b = b;
        this.f = (this.f ? (this.a.b * 8) : this.a.b);
        this.p = (this.f ? (this.a.k * 8) : this.a.k);
        this.i = this.f - 1;
        if (!this.c) {
            this.g = (int)(this.a[0][3] * 255.0f);
            this.t = (int)(this.a[0][4] * 255.0f);
            this.x = (int)(this.a[0][5] * 255.0f);
            this.c = (int)(this.a[0][6] * 255.0f);
            this.u = this.c;
            this.s = (0xFF000000 | this.g << 16 | this.t << 8 | this.x);
        }
        for (int i = 0; i < this.w; ++i) {
            this.b[i] = 0.0f;
            this.d[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
        }
        if (j.a[6]) {
            final float n = -this.f * 2;
            final float n2 = -this.p * 2;
            final float n3 = this.f * 2;
            final float n4 = this.p * 2;
            for (int j = 0; j < this.w; ++j) {
                if (this.a[j][0] < n || this.a[j][0] > n3 || this.a[j][1] < n2 || this.a[j][1] > n4) {
                    return;
                }
            }
        }
        if (this.f) {
            for (int k = 0; k < this.w; ++k) {
                final float[] array = this.a[k];
                final int n5 = 0;
                array[n5] *= 8.0f;
                final float[] array2 = this.a[k];
                final int n6 = 1;
                array2[n6] *= 8.0f;
            }
            this.j = -1;
        }
        int n7 = 0;
        float n8 = this.a[0][1];
        float n9 = this.a[0][1];
        for (int l = 1; l < this.w; ++l) {
            if (this.a[l][1] < n8) {
                n8 = this.a[l][1];
                n7 = l;
            }
            if (this.a[l][1] > n9) {
                n9 = this.a[l][1];
            }
        }
        this.k = (int)(n9 - 0.5f);
        int n10 = n7;
        int n11 = n7;
        int n13;
        int n12 = (n13 = (int)(n8 + 0.5f)) - 1;
        int n14 = n13 - 1;
        this.e = true;
        int w = this.w;
        while (w > 0) {
            while (n12 <= n13 && w > 0) {
                --w;
                final int n15 = (n10 != 0) ? (n10 - 1) : (this.w - 1);
                this.b(this.a[n10], this.a[n15], this.f, this.g, n13);
                n12 = (int)(this.a[n15][1] + 0.5f);
                n10 = n15;
            }
            while (n14 <= n13 && w > 0) {
                --w;
                int n16 = 0;
                if (n11 != this.w - 1) {
                    n16 = n11 + 1;
                }
                final int n17 = n16;
                this.b(this.a[n11], this.a[n17], this.b, this.d, n13);
                n14 = (int)(this.a[n17][1] + 0.5f);
                n11 = n17;
            }
            while (n13 < n12 && n13 < n14) {
                if (n13 >= 0 && n13 < this.p) {
                    if (this.f[0] <= this.b[0]) {
                        this.a(n13, this.f, this.b);
                    }
                    else {
                        this.a(n13, this.b, this.f);
                    }
                }
                ++n13;
                this.a(this.f, this.g);
                this.a(this.b, this.d);
            }
        }
    }
    
    public final void c() {
        if (this.f) {
            for (int i = 0; i < this.w; ++i) {
                final float[] array = this.a[i];
                final int n = 0;
                array[n] /= 8.0f;
                final float[] array2 = this.a[i];
                final int n2 = 1;
                array2[n2] /= 8.0f;
            }
        }
    }
    
    private final void a(final int n, final float[] array, final float[] array2) {
        for (int i = 0; i < this.w; ++i) {
            this.a[i] = 0.0f;
            this.c[i] = 0.0f;
        }
        int n2;
        if ((n2 = (int)(array[0] + 0.49999f)) < 0) {
            n2 = 0;
        }
        int n3;
        if ((n3 = (int)(array2[0] - 0.5f)) > this.i) {
            n3 = this.i;
        }
        if (n2 > n3) {
            return;
        }
        if (this.f) {
            final int b = this.b(n);
            this.d[b] = n2;
            this.c[b] = n3;
            if (this.j == -1) {
                this.j = b;
                this.b = n2;
                this.n = n2;
                this.a = n3;
                this.h = n3;
            }
            else {
                if (this.b > this.d[b]) {
                    this.b = this.d[b];
                }
                if (this.n < this.d[b]) {
                    this.n = this.d[b];
                }
                if (this.a > this.c[b]) {
                    this.a = this.c[b];
                }
                if (this.h < this.c[b]) {
                    this.h = this.c[b];
                }
            }
            this.e = b;
            if (b != 7 && n != this.k) {
                return;
            }
            this.v = this.n / 8 + 1;
            this.r = this.a / 8 - 1;
        }
        this.a(array, array2, this.a, this.c, n2);
        final int n4 = this.f ? (this.a.b * (n / 8)) : (this.a.b * n);
        int n5 = 0;
        int n6 = 0;
        if (this.f) {
            n5 = n2 / 8;
            n6 = (n3 + 7) / 8;
            n2 = this.b / 8;
            n3 = (this.h + 7) / 8;
            if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > this.a.v) {
                n3 = this.a.v;
            }
        }
        this.e = false;
        for (int j = n2; j <= n3; ++j) {
            if (this.a || this.a[2] <= this.e[n4 + j]) {
                if (this.d) {
                    int q = (int)this.a[7];
                    int d = (int)this.a[8];
                    if (q > this.q) {
                        q = this.q;
                    }
                    if (d > this.d) {
                        d = this.d;
                    }
                    if (q < 0) {
                        q = 0;
                    }
                    if (d < 0) {
                        d = 0;
                    }
                    final int n7 = d * this.l + q;
                    int n16;
                    int n17;
                    int n18;
                    int n19;
                    if (this.f || this.b) {
                        final int n8 = (int)(255.0f * (this.a[7] - q));
                        final int n9 = (int)(255.0f * (this.a[8] - d));
                        final int n10 = 255 - n8;
                        final int n11 = 255 - n9;
                        final int n12 = this.b[n7];
                        final int n13 = (d < this.d) ? this.b[n7 + this.l] : this.b[n7];
                        final int n14 = (q < this.q) ? this.b[n7 + 1] : this.b[n7];
                        final int n15 = (d < this.d && q < this.q) ? this.b[n7 + this.l + 1] : this.b[n7];
                        if (this.m == 4) {
                            n16 = ((n12 * n10 + n14 * n8 >> 8) * n11 + (n13 * n10 + n15 * n8 >> 8) * n9 >> 8) * (this.c ? ((int)(this.a[6] * 255.0f)) : this.u) >> 8;
                        }
                        else if (this.m == 2) {
                            n16 = (((n12 >> 24 & 0xFF) * n10 + (n14 >> 24 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 24 & 0xFF) * n10 + (n15 >> 24 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.c ? ((int)(this.a[6] * 255.0f)) : this.u) >> 8;
                        }
                        else {
                            n16 = (this.c ? ((int)(this.a[6] * 255.0f)) : this.u);
                        }
                        if (this.m == 1 || this.m == 2) {
                            n17 = (((n12 >> 16 & 0xFF) * n10 + (n14 >> 16 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 16 & 0xFF) * n10 + (n15 >> 16 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.c ? ((int)this.a[3] * 255) : this.g) >> 8;
                            n18 = (((n12 >> 8 & 0xFF) * n10 + (n14 >> 8 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 8 & 0xFF) * n10 + (n15 >> 8 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.c ? ((int)this.a[4] * 255) : this.t) >> 8;
                            n19 = (((n12 & 0xFF) * n10 + (n14 & 0xFF) * n8 >> 8) * n11 + ((n13 & 0xFF) * n10 + (n15 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.c ? ((int)this.a[5] * 255) : this.x) >> 8;
                        }
                        else if (this.c) {
                            n17 = (int)(this.a[3] * 255.0f);
                            n18 = (int)(this.a[4] * 255.0f);
                            n19 = (int)(this.a[5] * 255.0f);
                        }
                        else {
                            n17 = this.g;
                            n18 = this.t;
                            n19 = this.x;
                        }
                        final int n20;
                        if ((n20 = (this.f ? this.a(j) : 255)) != 255) {
                            n16 = n16 * n20 >> 8;
                        }
                    }
                    else {
                        final int n21 = this.b[n7];
                        if (this.m == 4) {
                            n16 = n21;
                            if (this.c) {
                                n17 = (int)this.a[3] * 255;
                                n18 = (int)this.a[4] * 255;
                                n19 = (int)this.a[5] * 255;
                                if (this.a[6] != 1.0f) {
                                    n16 = (int)this.a[6] * 255 * n16 >> 8;
                                }
                            }
                            else {
                                n17 = this.g;
                                n18 = this.t;
                                n19 = this.x;
                                n16 = this.u * n16 >> 8;
                            }
                        }
                        else {
                            final int n22 = (this.m == 1) ? 255 : (n21 >> 24 & 0xFF);
                            if (this.c) {
                                n17 = (int)this.a[3] * 255 * (n21 >> 16 & 0xFF) >> 8;
                                n18 = (int)this.a[4] * 255 * (n21 >> 8 & 0xFF) >> 8;
                                n19 = (int)this.a[5] * 255 * (n21 & 0xFF) >> 8;
                                n16 = (int)this.a[6] * 255 * n22 >> 8;
                            }
                            else {
                                n17 = this.g * (n21 >> 16 & 0xFF) >> 8;
                                n18 = this.t * (n21 >> 8 & 0xFF) >> 8;
                                n19 = this.x * (n21 & 0xFF) >> 8;
                                n16 = this.u * n22 >> 8;
                            }
                        }
                    }
                    if (n16 == 254 || n16 == 255) {
                        this.a[n4 + j] = (0xFF000000 | n17 << 16 | n18 << 8 | n19);
                        this.e[n4 + j] = this.a[2];
                    }
                    else {
                        final int n23 = 255 - n16;
                        this.a[n4 + j] = (0xFF000000 | n17 * n16 + (this.a[n4 + j] >> 16 & 0xFF) * n23 >> 8 << 16 | (n18 * n16 + (this.a[n4 + j] >> 8 & 0xFF) * n23 & 0xFF00) | n19 * n16 + (this.a[n4 + j] & 0xFF) * n23 >> 8);
                        if (n16 > 204) {
                            this.e[n4 + j] = this.a[2];
                        }
                    }
                }
                else {
                    int c = this.f ? this.a(j) : 255;
                    if (this.c) {
                        this.g = (int)(this.a[3] * 255.0f);
                        this.t = (int)(this.a[4] * 255.0f);
                        this.x = (int)(this.a[5] * 255.0f);
                        if (this.a[6] != 1.0f) {
                            c = c * (int)(this.a[6] * 255.0f) >> 8;
                        }
                        if (c == 255) {
                            this.s = (0xFF000000 | this.g << 16 | this.t << 8 | this.x);
                        }
                    }
                    else if (this.u != 255) {
                        c = c * this.u >> 8;
                    }
                    if (c == 255) {
                        this.a[n4 + j] = this.s;
                        this.e[n4 + j] = this.a[2];
                    }
                    else {
                        final int n24 = this.a[n4 + j] >> 16 & 0xFF;
                        final int n25 = this.a[n4 + j] >> 8 & 0xFF;
                        final int n26 = this.a[n4 + j] & 0xFF;
                        this.c = c;
                        final int n27 = 255 - this.c;
                        this.a[n4 + j] = (0xFF000000 | n24 * n27 + this.g * this.c >> 8 << 16 | n25 * n27 + this.t * this.c >> 8 << 8 | n26 * n27 + this.x * this.c >> 8);
                        if (this.c > 204) {
                            this.e[n4 + j] = this.a[2];
                        }
                    }
                }
            }
            if (!this.f || (j >= n5 && j <= n6)) {
                this.a(this.a, this.c);
            }
        }
        this.j = -1;
        this.e = true;
    }
    
    private final int a(final int n) {
        if (n >= this.v && n <= this.r && this.j == 0 && this.e == 7) {
            return 255;
        }
        final int n3;
        final int n2 = (n3 = n * 8) + 8;
        int n4 = 0;
        for (int i = this.j; i <= this.e; ++i) {
            if (this.d[i] <= n2 && this.c[i] >= n3) {
                n4 += ((this.c[i] < n2) ? this.c[i] : n2) - ((this.d[i] > n3) ? this.d[i] : n3);
            }
        }
        final int n5;
        if ((n5 = n4 << 2) == 256) {
            return 255;
        }
        return n5;
    }
    
    private final void b(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2;
        if ((n2 = array2[1] - array[1]) == 0.0f) {
            n2 = 1.0f;
        }
        final float n3 = n + 0.5f - array[1];
        if (this.e) {
            array4[0] = (array2[0] - array[0]) / n2;
            array3[0] = array[0] + array4[0] * n3;
        }
        if (this.g) {
            array4[2] = (array2[2] - array[2]) / n2;
            array3[2] = array[2] + array4[2] * n3;
        }
        if (this.c) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array4[6] = (array2[6] - array[6]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
            array3[6] = array[6] + array4[6] * n3;
        }
        if (this.d) {
            array4[7] = (array2[7] - array[7]) / n2;
            array4[8] = (array2[8] - array[8]) / n2;
            array3[7] = array[7] + array4[7] * n3;
            array3[8] = array[8] + array4[8] * n3;
        }
    }
    
    private final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2;
        if ((n2 = array2[0] - array[0]) == 0.0f) {
            n2 = 1.0f;
        }
        float n3 = n + 0.5f - array[0];
        if (this.f) {
            n2 /= 8.0f;
            n3 /= 8.0f;
        }
        if (this.e) {
            array4[0] = (array2[0] - array[0]) / n2;
            array3[0] = array[0] + array4[0] * n3;
        }
        if (this.g) {
            array4[2] = (array2[2] - array[2]) / n2;
            array3[2] = array[2] + array4[2] * n3;
        }
        if (this.c) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array4[6] = (array2[6] - array[6]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
            array3[6] = array[6] + array4[6] * n3;
        }
        if (this.d) {
            array4[7] = (array2[7] - array[7]) / n2;
            array4[8] = (array2[8] - array[8]) / n2;
            array3[7] = array[7] + array4[7] * n3;
            array3[8] = array[8] + array4[8] * n3;
        }
    }
    
    private final void a(final float[] array, final float[] array2) {
        if (this.e) {
            final int n = 0;
            array[n] += array2[0];
        }
        if (this.g) {
            final int n2 = 2;
            array[n2] += array2[2];
        }
        if (this.c) {
            final int n3 = 3;
            array[n3] += array2[3];
            final int n4 = 4;
            array[n4] += array2[4];
            final int n5 = 5;
            array[n5] += array2[5];
            final int n6 = 6;
            array[n6] += array2[6];
        }
        if (this.d) {
            final int n7 = 7;
            array[n7] += array2[7];
            final int n8 = 8;
            array[n8] += array2[8];
        }
    }
    
    private final void b() {
        this.a = new float[64][24];
        this.b = new float[64];
        this.d = new float[64];
        this.f = new float[64];
        this.g = new float[64];
        this.a = new float[64];
        this.c = new float[64];
        this.d = new int[8];
        this.c = new int[8];
    }
    
    public m(final j a) {
        this.b();
        this.a = a;
        this.a(0);
    }
}
