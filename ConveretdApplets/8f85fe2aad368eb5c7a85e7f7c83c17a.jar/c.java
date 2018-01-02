// 
// Decompiled by Procyon v0.5.30
// 

public final class c
{
    private int[] c;
    private int[] b;
    private int[] a;
    private float[] i;
    private int e;
    private int n;
    private int l;
    private int m;
    private float H;
    private float U;
    public boolean a;
    public boolean f;
    public boolean b;
    private float[] j;
    private float[] f;
    private float[] a;
    private float[] d;
    private float[] h;
    private float[] c;
    private float[] g;
    private float[] e;
    private float[] b;
    private int b;
    private int g;
    private int h;
    private float ad;
    private float e;
    private float V;
    private float N;
    private float ac;
    private float C;
    private float g;
    private float j;
    private float F;
    private float Y;
    private float b;
    private float S;
    private float ab;
    private float l;
    private float K;
    private float X;
    private float Z;
    private float ah;
    private float p;
    private float ag;
    private float k;
    private float t;
    private float n;
    private float z;
    private float ae;
    private float Q;
    private float q;
    private float i;
    private float A;
    private float D;
    private float c;
    private float m;
    private float E;
    private float u;
    private float w;
    private float s;
    private float W;
    private float L;
    private float v;
    private float r;
    private float x;
    private float P;
    private float R;
    private float o;
    private float G;
    private float M;
    private float J;
    private float y;
    private float aa;
    private float af;
    private float B;
    private float T;
    private float h;
    private float f;
    private float O;
    private float d;
    private float a;
    private int a;
    private int i;
    private int p;
    private int f;
    private int c;
    private int d;
    private float I;
    private int k;
    public int j;
    public int o;
    private j a;
    private boolean e;
    private boolean d;
    private boolean c;
    
    public final void b() {
        this.e = this.a.b;
        this.n = this.a.k;
        this.c = this.a.e;
        this.b = this.a.a;
        this.i = this.a.d;
        this.a = false;
        this.f = false;
        this.b = false;
        this.a = null;
        this.j = 0;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.j[0] = n;
        this.j[1] = n4;
        this.j[2] = n7;
        this.f[0] = n2;
        this.f[1] = n5;
        this.f[2] = n8;
        this.a[0] = n3;
        this.a[1] = n6;
        this.a[2] = n9;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.d[0] = (n * this.H + 0.5f) * 65536.0f;
        this.d[1] = (n3 * this.H + 0.5f) * 65536.0f;
        this.d[2] = (n5 * this.H + 0.5f) * 65536.0f;
        this.h[0] = (n2 * this.U + 0.5f) * 65536.0f;
        this.h[1] = (n4 * this.U + 0.5f) * 65536.0f;
        this.h[2] = (n6 * this.U + 0.5f) * 65536.0f;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        if (n4 != 1.0f || n8 != 1.0f || n12 != 1.0f) {
            (this.b = true)[0] = (n4 * 253.0f + 1.0f) * 65536.0f;
            this.b[1] = (n8 * 253.0f + 1.0f) * 65536.0f;
            this.b[2] = (n12 * 253.0f + 1.0f) * 65536.0f;
            this.j |= 0x10;
        }
        else {
            this.b = false;
            this.j &= 0xFFFFFFEF;
        }
        if (n != n5 || n5 != n9) {
            this.f = true;
            this.j |= 0x1;
        }
        else if (n2 != n6 || n6 != n10) {
            this.f = true;
            this.j |= 0x1;
        }
        else if (n3 != n7 || n7 != n11) {
            this.f = true;
            this.j |= 0x1;
        }
        else {
            this.j &= 0xFFFFFFFE;
        }
        this.c[0] = (n * 253.0f + 1.0f) * 65536.0f;
        this.c[1] = (n5 * 253.0f + 1.0f) * 65536.0f;
        this.c[2] = (n9 * 253.0f + 1.0f) * 65536.0f;
        this.g[0] = (n2 * 253.0f + 1.0f) * 65536.0f;
        this.g[1] = (n6 * 253.0f + 1.0f) * 65536.0f;
        this.g[2] = (n10 * 253.0f + 1.0f) * 65536.0f;
        this.e[0] = (n3 * 253.0f + 1.0f) * 65536.0f;
        this.e[1] = (n7 * 253.0f + 1.0f) * 65536.0f;
        this.e[2] = (n11 * 253.0f + 1.0f) * 65536.0f;
        this.k = ((int)(255.0f * n) << 16 | (int)(255.0f * n2) << 8 | (int)(255.0f * n3));
    }
    
    public final void a(final i i) {
        this.a = i.e;
        this.l = i.b;
        this.m = i.k;
        this.H = this.l - 1;
        this.U = this.m - 1;
        this.a = true;
        if (i.K == 2) {
            this.j |= 0x8;
        }
        else if (i.K == 1) {
            this.j |= 0x4;
        }
        else if (i.K == 4) {
            this.j |= 0x2;
        }
        if (this.a.j) {
            this.c = true;
            return;
        }
        this.c = false;
    }
    
    public final void a() {
        this.c();
    }
    
    private final void c() {
        final float n = this.f[0];
        final float n2 = this.f[1];
        final float n3 = this.f[2];
        if (this.e) {
            final float n4 = this.j[0];
            if ((this.j[2] - n4) * (n2 - n) < (this.j[1] - n4) * (n3 - n)) {
                return;
            }
        }
        if (n < n2) {
            if (n3 < n2) {
                if (n3 < n) {
                    this.b = 2;
                    this.g = 0;
                    this.h = 1;
                }
                else {
                    this.b = 0;
                    this.g = 2;
                    this.h = 1;
                }
            }
            else {
                this.b = 0;
                this.g = 1;
                this.h = 2;
            }
        }
        else if (n3 > n2) {
            if (n3 < n) {
                this.b = 1;
                this.g = 2;
                this.h = 0;
            }
            else {
                this.b = 1;
                this.g = 0;
                this.h = 2;
            }
        }
        else {
            this.b = 2;
            this.g = 1;
            this.h = 0;
        }
        final float n6;
        int n5;
        if ((n5 = (int)((n6 = this.f[this.b]) + 0.5f)) > this.n) {
            return;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        final float n8;
        int n7;
        if ((n7 = (int)((n8 = this.f[this.h]) + 0.5f)) < 0) {
            return;
        }
        if (n7 > this.n) {
            n7 = this.n;
        }
        if (n7 > n5) {
            final float n9 = this.j[this.b];
            final float n10 = this.j[this.g];
            final float n11 = this.j[this.h];
            final float n13;
            int n12;
            if ((n12 = (int)((n13 = this.f[this.g]) + 0.5f)) < 0) {
                n12 = 0;
            }
            if (n12 > this.n) {
                n12 = this.n;
            }
            this.p = n11 - n9;
            this.ag = n13 - n6;
            this.t = n8 - n6;
            this.o = this.p / this.t;
            this.d = this.ag / this.t;
            this.a = this.d * this.p + n9 - n10;
            if (this.b) {
                this.Y = this.b[this.b];
                this.b = this.b[this.g];
                this.S = this.b[this.h];
                this.w = this.b - this.Y;
                this.s = this.S - this.Y;
                this.d = (int)((this.d * this.s - this.w) / this.a);
            }
            if (this.f) {
                this.ad = this.c[this.b];
                this.e = this.c[this.g];
                this.V = this.c[this.h];
                this.N = this.g[this.b];
                this.ac = this.g[this.g];
                this.C = this.g[this.h];
                this.g = this.e[this.b];
                this.j = this.e[this.g];
                this.F = this.e[this.h];
                this.A = this.e - this.ad;
                this.c = this.ac - this.N;
                this.E = this.j - this.g;
                this.D = this.V - this.ad;
                this.m = this.C - this.N;
                this.u = this.F - this.g;
                this.p = (int)((this.d * this.D - this.A) / this.a);
                this.f = (int)((this.d * this.m - this.c) / this.a);
                this.c = (int)((this.d * this.u - this.E) / this.a);
            }
            if (this.a) {
                this.ab = this.d[this.b];
                this.l = this.d[this.g];
                this.K = this.d[this.h];
                this.X = this.h[this.b];
                this.Z = this.h[this.g];
                this.ah = this.h[this.h];
                this.ae = this.l - this.ab;
                this.q = this.Z - this.X;
                this.Q = this.K - this.ab;
                this.i = this.ah - this.X;
                this.a = (int)((this.d * this.Q - this.ae) / this.a);
                this.i = (int)((this.d * this.i - this.q) / this.a);
            }
            final float n14 = this.a[this.b];
            final float n15 = this.a[this.g];
            final float n16 = this.a[this.h];
            this.n = n15 - n14;
            this.z = n16 - n14;
            this.I = (this.d * this.z - this.n) / this.a;
            if (n12 > n5) {
                this.O = n5 + 0.5f - n6;
                this.R = (n10 - n9) / this.ag;
                if (this.o > this.R) {
                    this.x = n9 + this.O * this.R;
                    this.P = n9 + this.O * this.o;
                    this.M = this.n / this.ag;
                    this.G = this.O * this.M + n14;
                    if (this.a) {
                        this.v = this.ae / this.ag;
                        this.r = this.q / this.ag;
                        this.W = this.O * this.v + this.ab;
                        this.L = this.O * this.r + this.X;
                    }
                    if (this.f) {
                        this.B = this.A / this.ag;
                        this.T = this.c / this.ag;
                        this.h = this.E / this.ag;
                        this.J = this.O * this.B + this.ad;
                        this.y = this.O * this.T + this.N;
                        this.aa = this.O * this.h + this.g;
                    }
                    if (this.b) {
                        this.f = this.w / this.ag;
                        this.af = this.O * this.f + this.Y;
                        if (this.j == 16) {
                            this.b(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 17) {
                            this.c(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 18) {
                            this.h(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 20) {
                            this.j(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 24) {
                            this.p(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 19) {
                            this.d(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 21) {
                            this.e(this.R, this.o, n5, n12);
                        }
                        else if (this.j == 25) {
                            this.m(this.R, this.o, n5, n12);
                        }
                    }
                    else if (this.j == 0) {
                        this.f(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 1) {
                        this.g(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 2) {
                        this.k(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 4) {
                        this.i(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 8) {
                        this.a(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 3) {
                        this.l(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 5) {
                        this.o(this.R, this.o, n5, n12);
                    }
                    else if (this.j == 9) {
                        this.n(this.R, this.o, n5, n12);
                    }
                    this.d = true;
                }
                else {
                    this.x = n9 + this.O * this.o;
                    this.P = n9 + this.O * this.R;
                    this.M = this.z / this.t;
                    this.G = this.O * this.M + n14;
                    if (this.a) {
                        this.v = this.Q / this.t;
                        this.r = this.i / this.t;
                        this.W = this.O * this.v + this.ab;
                        this.L = this.O * this.r + this.X;
                    }
                    if (this.f) {
                        this.B = this.D / this.t;
                        this.T = this.m / this.t;
                        this.h = this.u / this.t;
                        this.J = this.O * this.B + this.ad;
                        this.y = this.O * this.T + this.N;
                        this.aa = this.O * this.h + this.g;
                    }
                    if (this.b) {
                        this.f = this.s / this.t;
                        this.af = this.O * this.f + this.Y;
                        if (this.j == 16) {
                            this.b(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 17) {
                            this.c(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 18) {
                            this.h(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 20) {
                            this.j(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 24) {
                            this.p(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 19) {
                            this.d(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 21) {
                            this.e(this.o, this.R, n5, n12);
                        }
                        else if (this.j == 25) {
                            this.m(this.o, this.R, n5, n12);
                        }
                    }
                    else if (this.j == 0) {
                        this.f(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 1) {
                        this.g(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 2) {
                        this.k(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 4) {
                        this.i(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 8) {
                        this.a(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 3) {
                        this.l(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 5) {
                        this.o(this.o, this.R, n5, n12);
                    }
                    else if (this.j == 9) {
                        this.n(this.o, this.R, n5, n12);
                    }
                    this.d = false;
                }
                if (n7 == n12) {
                    return;
                }
                this.k = n8 - n13;
                this.R = (n11 - n10) / this.k;
            }
            else {
                this.k = n8 - n13;
                this.R = (n11 - n10) / this.k;
                if (this.o < this.R) {
                    this.P = (n12 + 0.5f - n6) * this.o + n9;
                    this.d = true;
                }
                else {
                    this.O = n12 + 0.5f - n6;
                    this.x = this.O * this.o + n9;
                    this.M = this.z / this.t;
                    this.G = this.O * this.M + n14;
                    if (this.a) {
                        this.v = this.Q / this.t;
                        this.r = this.i / this.t;
                        this.W = this.O * this.v + this.ab;
                        this.L = this.O * this.r + this.X;
                    }
                    if (this.f) {
                        this.B = this.D / this.t;
                        this.T = this.m / this.t;
                        this.h = this.u / this.t;
                        this.J = this.O * this.B + this.ad;
                        this.y = this.O * this.T + this.N;
                        this.aa = this.O * this.h + this.g;
                    }
                    if (this.b) {
                        this.f = this.s / this.t;
                        this.af = this.O * this.f + this.Y;
                    }
                    this.d = false;
                }
            }
            if (this.d) {
                this.O = n12 + 0.5f - n13;
                this.x = this.O * this.R + n10;
                this.M = (n16 - n15) / this.k;
                this.G = this.O * this.M + n15;
                if (this.a) {
                    this.v = (this.K - this.l) / this.k;
                    this.r = (this.ah - this.Z) / this.k;
                    this.W = this.O * this.v + this.l;
                    this.L = this.O * this.r + this.Z;
                }
                if (this.f) {
                    this.B = (this.V - this.e) / this.k;
                    this.T = (this.C - this.ac) / this.k;
                    this.h = (this.F - this.j) / this.k;
                    this.J = this.O * this.B + this.e;
                    this.y = this.O * this.T + this.ac;
                    this.aa = this.O * this.h + this.j;
                }
                if (this.b) {
                    this.f = (this.S - this.b) / this.k;
                    this.af = this.O * this.f + this.b;
                    if (this.j == 16) {
                        this.b(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 17) {
                        this.c(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 18) {
                        this.h(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 20) {
                        this.j(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 24) {
                        this.p(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 19) {
                        this.d(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 21) {
                        this.e(this.R, this.o, n12, n7);
                    }
                    else if (this.j == 25) {
                        this.m(this.R, this.o, n12, n7);
                    }
                }
                else if (this.j == 0) {
                    this.f(this.R, this.o, n12, n7);
                }
                else if (this.j == 1) {
                    this.g(this.R, this.o, n12, n7);
                }
                else if (this.j == 2) {
                    this.k(this.R, this.o, n12, n7);
                }
                else if (this.j == 4) {
                    this.i(this.R, this.o, n12, n7);
                }
                else if (this.j == 8) {
                    this.a(this.R, this.o, n12, n7);
                }
                else if (this.j == 3) {
                    this.l(this.R, this.o, n12, n7);
                }
                else if (this.j == 5) {
                    this.o(this.R, this.o, n12, n7);
                }
                else if (this.j == 9) {
                    this.n(this.R, this.o, n12, n7);
                }
                return;
            }
            this.P = (n12 + 0.5f - n13) * this.R + n10;
            if (this.b) {
                if (this.j == 16) {
                    this.b(this.o, this.R, n12, n7);
                }
                else if (this.j == 17) {
                    this.c(this.o, this.R, n12, n7);
                }
                else if (this.j == 18) {
                    this.h(this.o, this.R, n12, n7);
                }
                else if (this.j == 20) {
                    this.j(this.o, this.R, n12, n7);
                }
                else if (this.j == 24) {
                    this.p(this.o, this.R, n12, n7);
                }
                else if (this.j == 19) {
                    this.d(this.o, this.R, n12, n7);
                }
                else if (this.j == 21) {
                    this.e(this.o, this.R, n12, n7);
                }
                else if (this.j == 25) {
                    this.m(this.o, this.R, n12, n7);
                }
                return;
            }
            if (this.j == 0) {
                this.f(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 1) {
                this.g(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 2) {
                this.k(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 4) {
                this.i(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 8) {
                this.a(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 3) {
                this.l(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 5) {
                this.o(this.o, this.R, n12, n7);
                return;
            }
            if (this.j == 9) {
                this.n(this.o, this.R, n12, n7);
            }
        }
    }
    
    private final void f(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int k = this.k;
        final int o = this.o;
        while (i < n3) {
            int n4;
            if ((n4 = (int)(this.x + 0.5f)) < 0) {
                n4 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            float n5 = this.I * (n4 + 0.5f - this.x) + this.G;
            for (int j = n4 + i; j < e + i; ++j) {
                if (n5 <= this.i[j]) {
                    this.i[j] = n5;
                    this.c[j] = k;
                    this.b[j] = o;
                }
                n5 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.G += this.M;
        }
    }
    
    private final void b(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int n4 = this.k & 0xFF0000;
        final int n5 = this.k & 0xFF00;
        final int n6 = this.k & 0xFF;
        final int o = this.o;
        final float n7 = this.d;
        while (i < n3) {
            int n8;
            if ((n8 = (int)(this.x + 0.5f)) < 0) {
                n8 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n9 = n8 + 0.5f - this.x;
            float n10 = this.I * n9 + this.G;
            int n11 = (int)(n7 * n9 + this.af);
            for (int j = n8 + i; j < e + i; ++j) {
                if (n10 <= this.i[j]) {
                    final int n12 = n11 >> 16;
                    final int n14;
                    final int n13 = (n14 = this.c[j]) & 0xFF00;
                    final int n15 = n14 & 0xFF;
                    final int n16 = n14 & 0xFF0000;
                    this.c[j] = ((n16 + ((n4 - n16) * n12 >> 8) & 0xFF0000) | (n13 + ((n5 - n13) * n12 >> 8) & 0xFF00) | (n15 + ((n6 - n15) * n12 >> 8) & 0xFF));
                    this.b[j] = o;
                }
                n10 += this.I;
                n11 += this.d;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.G += this.M;
        }
    }
    
    private final void g(final float n, final float n2, int i, int n3) {
        final float n4 = this.p;
        final float n5 = this.f;
        final float n6 = this.c;
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        while (i < n3) {
            int n7;
            if ((n7 = (int)(this.x + 0.5f)) < 0) {
                n7 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n8 = n7 + 0.5f - this.x;
            int n9 = (int)(n4 * n8 + this.J);
            int n10 = (int)(n5 * n8 + this.y);
            int n11 = (int)(n6 * n8 + this.aa);
            float n12 = this.I * n8 + this.G;
            for (int j = n7 + i; j < e + i; ++j) {
                if (n12 <= this.i[j]) {
                    this.i[j] = n12;
                    this.c[j] = ((n9 & 0xFF0000) | (n10 >> 8 & 0xFF00) | n11 >> 16);
                    this.b[j] = o;
                }
                n9 += this.p;
                n10 += this.f;
                n11 += this.c;
                n12 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.G += this.M;
        }
    }
    
    private final void c(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.p;
        final float n5 = this.f;
        final float n6 = this.c;
        final float n7 = this.d;
        while (i < n3) {
            int n8;
            if ((n8 = (int)(this.x + 0.5f)) < 0) {
                n8 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n9 = n8 + 0.5f - this.x;
            int n10 = (int)(n4 * n9 + this.J);
            int n11 = (int)(n5 * n9 + this.y);
            int n12 = (int)(n6 * n9 + this.aa);
            int n13 = (int)(n7 * n9 + this.af);
            float n14 = this.I * n9 + this.G;
            for (int j = n8 + i; j < e + i; ++j) {
                if (n14 <= this.i[j]) {
                    final int n15 = n10 & 0xFF0000;
                    final int n16 = n11 >> 8 & 0xFF00;
                    final int n17 = n12 >> 16;
                    final int n19;
                    final int n18 = (n19 = this.c[j]) & 0xFF0000;
                    final int n20 = n19 & 0xFF00;
                    final int n21 = n19 & 0xFF;
                    final int n22 = n13 >> 16;
                    this.c[j] = ((n18 + ((n15 - n18) * n22 >> 8) & 0xFF0000) | (n20 + ((n16 - n20) * n22 >> 8) & 0xFF00) | (n21 + ((n17 - n21) * n22 >> 8) & 0xFF));
                    this.b[j] = o;
                }
                n10 += this.p;
                n11 += this.f;
                n12 += this.c;
                n13 += this.d;
                n14 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.af += this.f;
            this.G += this.M;
        }
    }
    
    private final void k(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final int n6 = this.k & 0xFF0000;
        final int n7 = this.k & 0xFF00;
        final int n8 = this.k & 0xFF;
        while (i < n3) {
            int n9;
            if ((n9 = (int)(this.x + 0.5f)) < 0) {
                n9 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n10 = n9 + 0.5f - this.x;
            int n11 = (int)(n4 * n10 + this.W);
            int n12 = (int)(n5 * n10 + this.L);
            float n13 = this.I * n10 + this.G;
            for (int j = n9 + i; j < e + i; ++j) {
                try {
                    if (n13 <= this.i[j]) {
                        int n21;
                        if (this.c) {
                            final int n14 = (n12 >> 16) * this.l + (n11 >> 16);
                            final char c = (char)(n11 & (char)(-1));
                            final int n15 = this.a[n14] & 0xFF;
                            final int n16 = this.a[n14 + 1] & 0xFF;
                            final int n17 = n14 + this.l;
                            final int n18 = this.a[n17] & 0xFF;
                            final int n19 = this.a[n17 + 1] & 0xFF;
                            final int n20 = n15 + ((n16 - n15) * c >> 16);
                            n21 = n20 + ((n18 + ((n19 - n18) * c >> 16) - n20) * (n12 & (char)(-1)) >> 16);
                        }
                        else {
                            n21 = (this.a[(n12 >> 16) * this.l + (n11 >> 16)] & 0xFF);
                        }
                        final int n23;
                        final int n22 = (n23 = this.c[j]) & 0xFF00;
                        final int n24 = n23 & 0xFF;
                        final int n25 = n23 & 0xFF0000;
                        this.c[j] = ((n25 + ((n6 - n25) * n21 >> 8) & 0xFF0000) | (n22 + ((n7 - n22) * n21 >> 8) & 0xFF00) | (n24 + ((n8 - n24) * n21 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n11 += this.a;
                n12 += this.i;
                n13 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
        }
    }
    
    private final void h(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.d;
        final int n7 = this.k & 0xFF0000;
        final int n8 = this.k & 0xFF00;
        final int n9 = this.k & 0xFF;
        while (i < n3) {
            int n10;
            if ((n10 = (int)(this.x + 0.5f)) < 0) {
                n10 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n11 = n10 + 0.5f - this.x;
            int n12 = (int)(n4 * n11 + this.W);
            int n13 = (int)(n5 * n11 + this.L);
            int n14 = (int)(n6 * n11 + this.af);
            float n15 = this.I * n11 + this.G;
            for (int j = n10 + i; j < e + i; ++j) {
                try {
                    if (n15 <= this.i[j]) {
                        int n23;
                        if (this.c) {
                            final int n16 = (n13 >> 16) * this.l + (n12 >> 16);
                            final char c = (char)(n12 & (char)(-1));
                            final int n17 = this.a[n16] & 0xFF;
                            final int n18 = this.a[n16 + 1] & 0xFF;
                            final int n19 = n16 + this.l;
                            final int n20 = this.a[n19] & 0xFF;
                            final int n21 = this.a[n19 + 1] & 0xFF;
                            final int n22 = n17 + ((n18 - n17) * c >> 16);
                            n23 = n22 + ((n20 + ((n21 - n20) * c >> 16) - n22) * (n13 & (char)(-1)) >> 16);
                        }
                        else {
                            n23 = (this.a[(n13 >> 16) * this.l + (n12 >> 16)] & 0xFF);
                        }
                        final int n24 = n23 * (n14 >> 16) >> 8;
                        final int n26;
                        final int n25 = (n26 = this.c[j]) & 0xFF00;
                        final int n27 = n26 & 0xFF;
                        final int n28 = n26 & 0xFF0000;
                        this.c[j] = ((n28 + ((n7 - n28) * n24 >> 8) & 0xFF0000) | (n25 + ((n8 - n25) * n24 >> 8) & 0xFF00) | (n27 + ((n9 - n27) * n24 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n12 += this.a;
                n13 += this.i;
                n15 += this.I;
                n14 += this.d;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
            this.af += this.f;
        }
    }
    
    private final void i(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        while (i < n3) {
            int n6;
            if ((n6 = (int)(this.x + 0.5f)) < 0) {
                n6 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n7 = n6 + 0.5f - this.x;
            int n8 = (int)(n4 * n7 + this.W);
            int n9 = (int)(n5 * n7 + this.L);
            float n10 = this.I * n7 + this.G;
            for (int j = n6 + i; j < e + i; ++j) {
                try {
                    if (n10 <= this.i[j]) {
                        this.i[j] = n10;
                        if (this.c) {
                            final int n11 = (n9 >> 16) * this.l + (n8 >> 16);
                            final char c = (char)((n8 & (char)(-1)) >> 9);
                            final char c2 = (char)((n9 & (char)(-1)) >> 9);
                            final int n12 = this.a[n11];
                            final int n13 = this.a[n11 + 1];
                            final int n14 = n11 + this.l;
                            final int n15 = this.a[n14];
                            final int n16 = this.a[n14 + 1];
                            final int n17 = n12 & 0xFF0000;
                            final int n18 = n15 & 0xFF0000;
                            final int n19 = n17 + (((n13 & 0xFF0000) - n17) * c >> 7);
                            final int n20 = n19 + ((n18 + (((n16 & 0xFF0000) - n18) * c >> 7) - n19) * c2 >> 7);
                            final int n21 = n12 & 0xFF00;
                            final int n22 = n15 & 0xFF00;
                            final int n23 = n21 + (((n13 & 0xFF00) - n21) * c >> 7);
                            final int n24 = n23 + ((n22 + (((n16 & 0xFF00) - n22) * c >> 7) - n23) * c2 >> 7);
                            final int n25 = n12 & 0xFF;
                            final int n26 = n15 & 0xFF;
                            final int n27 = n25 + (((n13 & 0xFF) - n25) * c >> 7);
                            this.c[j] = ((n20 & 0xFF0000) | (n24 & 0xFF00) | (n27 + ((n26 + (((n16 & 0xFF) - n26) * c >> 7) - n27) * c2 >> 7) & 0xFF));
                        }
                        else {
                            this.c[j] = this.a[(n9 >> 16) * this.l + (n8 >> 16)];
                        }
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n8 += this.a;
                n9 += this.i;
                n10 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
        }
    }
    
    private final void j(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.d;
        while (i < n3) {
            int n7;
            if ((n7 = (int)(this.x + 0.5f)) < 0) {
                n7 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n8 = n7 + 0.5f - this.x;
            int n9 = (int)(n4 * n8 + this.W);
            int n10 = (int)(n5 * n8 + this.L);
            int n11 = (int)(n6 * n8 + this.af);
            float n12 = this.I * n8 + this.G;
            for (int j = n7 + i; j < e + i; ++j) {
                try {
                    if (n12 <= this.i[j]) {
                        final int n13 = n11 >> 16;
                        if (this.c) {
                            final int n14 = (n10 >> 16) * this.l + (n9 >> 16);
                            final char c = (char)((n9 & (char)(-1)) >> 9);
                            final char c2 = (char)((n10 & (char)(-1)) >> 9);
                            final int n15 = this.a[n14];
                            final int n16 = this.a[n14 + 1];
                            final int n17 = n14 + this.l;
                            final int n18 = this.a[n17];
                            final int n19 = this.a[n17 + 1];
                            final int n20 = n15 & 0xFF0000;
                            final int n21 = n18 & 0xFF0000;
                            final int n22 = n20 + (((n16 & 0xFF0000) - n20) * c >> 7);
                            final int n23 = n22 + ((n21 + (((n19 & 0xFF0000) - n21) * c >> 7) - n22) * c2 >> 7);
                            final int n24 = n15 & 0xFF00;
                            final int n25 = n18 & 0xFF00;
                            final int n26 = n24 + (((n16 & 0xFF00) - n24) * c >> 7);
                            final int n27 = n26 + ((n25 + (((n19 & 0xFF00) - n25) * c >> 7) - n26) * c2 >> 7);
                            final int n28 = n15 & 0xFF;
                            final int n29 = n18 & 0xFF;
                            final int n30 = n28 + (((n16 & 0xFF) - n28) * c >> 7);
                            final int n31 = n30 + ((n29 + (((n19 & 0xFF) - n29) * c >> 7) - n30) * c2 >> 7);
                            final int n33;
                            final int n32 = (n33 = this.c[j]) & 0xFF0000;
                            final int n34 = n33 & 0xFF00;
                            final int n35 = n33 & 0xFF;
                            this.c[j] = ((n32 + ((n23 - n32) * n13 >> 8) & 0xFF0000) | (n34 + ((n27 - n34) * n13 >> 8) & 0xFF00) | (n35 + ((n31 - n35) * n13 >> 8) & 0xFF));
                        }
                        else {
                            final int n37;
                            final int n36 = (n37 = this.a[(n10 >> 16) * this.l + (n9 >> 16)]) & 0xFF00;
                            final int n38 = n37 & 0xFF;
                            final int n39 = n37 & 0xFF0000;
                            final int n41;
                            final int n40 = (n41 = this.c[j]) & 0xFF0000;
                            final int n42 = n41 & 0xFF00;
                            final int n43 = n41 & 0xFF;
                            this.c[j] = ((n40 + ((n39 - n40) * n13 >> 8) & 0xFF0000) | (n42 + ((n36 - n42) * n13 >> 8) & 0xFF00) | (n43 + ((n38 - n43) * n13 >> 8) & 0xFF));
                        }
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n9 += this.a;
                n10 += this.i;
                n11 += this.d;
                n12 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
            this.af += this.f;
        }
    }
    
    private final void a(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        while (i < n3) {
            int n6;
            if ((n6 = (int)(this.x + 0.5f)) < 0) {
                n6 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n7 = n6 + 0.5f - this.x;
            int n8 = (int)(n4 * n7 + this.W);
            int n9 = (int)(n5 * n7 + this.L);
            float n10 = this.I * n7 + this.G;
            for (int j = n6 + i; j < e + i; ++j) {
                try {
                    if (n10 <= this.i[j]) {
                        if (this.c) {
                            final int n11 = (n9 >> 16) * this.l + (n8 >> 16);
                            final char c = (char)((n8 & (char)(-1)) >> 9);
                            final char c2 = (char)((n9 & (char)(-1)) >> 9);
                            final int n12 = this.a[n11];
                            final int n13 = this.a[n11 + 1];
                            final int n14 = n11 + this.l;
                            final int n15 = this.a[n14];
                            final int n16 = this.a[n14 + 1];
                            final int n17 = n12 & 0xFF0000;
                            final int n18 = n15 & 0xFF0000;
                            final int n19 = n17 + (((n13 & 0xFF0000) - n17) * c >> 7);
                            final int n20 = n19 + ((n18 + (((n16 & 0xFF0000) - n18) * c >> 7) - n19) * c2 >> 7);
                            final int n21 = n12 & 0xFF00;
                            final int n22 = n15 & 0xFF00;
                            final int n23 = n21 + (((n13 & 0xFF00) - n21) * c >> 7);
                            final int n24 = n23 + ((n22 + (((n16 & 0xFF00) - n22) * c >> 7) - n23) * c2 >> 7);
                            final int n25 = n12 & 0xFF;
                            final int n26 = n15 & 0xFF;
                            final int n27 = n25 + (((n13 & 0xFF) - n25) * c >> 7);
                            final int n28 = n27 + ((n26 + (((n16 & 0xFF) - n26) * c >> 7) - n27) * c2 >> 7);
                            final int n29 = n12 >>> 24;
                            final int n30 = n15 >>> 24;
                            final int n31 = n29 + (((n13 >>> 24) - n29) * c >> 7);
                            final int n32 = n31 + ((n30 + (((n16 >>> 24) - n30) * c >> 7) - n31) * c2 >> 7);
                            final int n34;
                            final int n33 = (n34 = this.c[j]) & 0xFF0000;
                            final int n35 = n34 & 0xFF00;
                            final int n36 = n34 & 0xFF;
                            this.c[j] = ((n33 + ((n20 - n33) * n32 >> 8) & 0xFF0000) | (n35 + ((n24 - n35) * n32 >> 8) & 0xFF00) | (n36 + ((n28 - n36) * n32 >> 8) & 0xFF));
                        }
                        else {
                            final int n38;
                            final int n37 = (n38 = this.a[(n9 >> 16) * this.l + (n8 >> 16)]) >>> 24;
                            final int n39 = n38 & 0xFF00;
                            final int n40 = n38 & 0xFF;
                            final int n41 = n38 & 0xFF0000;
                            final int n43;
                            final int n42 = (n43 = this.c[j]) & 0xFF0000;
                            final int n44 = n43 & 0xFF00;
                            final int n45 = n43 & 0xFF;
                            this.c[j] = ((n42 + ((n41 - n42) * n37 >> 8) & 0xFF0000) | (n44 + ((n39 - n44) * n37 >> 8) & 0xFF00) | (n45 + ((n40 - n45) * n37 >> 8) & 0xFF));
                        }
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n8 += this.a;
                n9 += this.i;
                n10 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
            this.af += this.f;
        }
    }
    
    private final void p(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.d;
        while (i < n3) {
            int n7;
            if ((n7 = (int)(this.x + 0.5f)) < 0) {
                n7 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n8 = n7 + 0.5f - this.x;
            int n9 = (int)(n4 * n8 + this.W);
            int n10 = (int)(n5 * n8 + this.L);
            int n11 = (int)(n6 * n8 + this.af);
            float n12 = this.I * n8 + this.G;
            for (int j = n7 + i; j < e + i; ++j) {
                try {
                    if (n12 <= this.i[j]) {
                        final int n13 = n11 >> 16;
                        if (this.c) {
                            final int n14 = (n10 >> 16) * this.l + (n9 >> 16);
                            final char c = (char)((n9 & (char)(-1)) >> 9);
                            final char c2 = (char)((n10 & (char)(-1)) >> 9);
                            final int n15 = this.a[n14];
                            final int n16 = this.a[n14 + 1];
                            final int n17 = n14 + this.l;
                            final int n18 = this.a[n17];
                            final int n19 = this.a[n17 + 1];
                            final int n20 = n15 & 0xFF0000;
                            final int n21 = n18 & 0xFF0000;
                            final int n22 = n20 + (((n16 & 0xFF0000) - n20) * c >> 7);
                            final int n23 = n22 + ((n21 + (((n19 & 0xFF0000) - n21) * c >> 7) - n22) * c2 >> 7);
                            final int n24 = n15 & 0xFF00;
                            final int n25 = n18 & 0xFF00;
                            final int n26 = n24 + (((n16 & 0xFF00) - n24) * c >> 7);
                            final int n27 = n26 + ((n25 + (((n19 & 0xFF00) - n25) * c >> 7) - n26) * c2 >> 7);
                            final int n28 = n15 & 0xFF;
                            final int n29 = n18 & 0xFF;
                            final int n30 = n28 + (((n16 & 0xFF) - n28) * c >> 7);
                            final int n31 = n30 + ((n29 + (((n19 & 0xFF) - n29) * c >> 7) - n30) * c2 >> 7);
                            final int n32 = n15 >>> 24;
                            final int n33 = n18 >>> 24;
                            final int n34 = n32 + (((n16 >>> 24) - n32) * c >> 7);
                            final int n35 = n13 * (n34 + ((n33 + (((n19 >>> 24) - n33) * c >> 7) - n34) * c2 >> 7)) >> 8;
                            final int n37;
                            final int n36 = (n37 = this.c[j]) & 0xFF0000;
                            final int n38 = n37 & 0xFF00;
                            final int n39 = n37 & 0xFF;
                            this.c[j] = ((n36 + ((n23 - n36) * n35 >> 8) & 0xFF0000) | (n38 + ((n27 - n38) * n35 >> 8) & 0xFF00) | (n39 + ((n31 - n39) * n35 >> 8) & 0xFF));
                        }
                        else {
                            final int n40 = this.a[(n10 >> 16) * this.l + (n9 >> 16)];
                            final int n41 = n13 * (n40 >>> 24) >> 8;
                            final int n42 = n40 & 0xFF00;
                            final int n43 = n40 & 0xFF;
                            final int n44 = n40 & 0xFF0000;
                            final int n46;
                            final int n45 = (n46 = this.c[j]) & 0xFF0000;
                            final int n47 = n46 & 0xFF00;
                            final int n48 = n46 & 0xFF;
                            this.c[j] = ((n45 + ((n44 - n45) * n41 >> 8) & 0xFF0000) | (n47 + ((n42 - n47) * n41 >> 8) & 0xFF00) | (n48 + ((n43 - n48) * n41 >> 8) & 0xFF));
                        }
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n9 += this.a;
                n10 += this.i;
                n11 += this.d;
                n12 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.G += this.M;
            this.af += this.f;
        }
    }
    
    private final void l(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        while (i < n3) {
            int n9;
            if ((n9 = (int)(this.x + 0.5f)) < 0) {
                n9 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n10 = n9 + 0.5f - this.x;
            int n11 = (int)(n4 * n10 + this.W);
            int n12 = (int)(n5 * n10 + this.L);
            int n13 = (int)(n6 * n10 + this.J);
            int n14 = (int)(n7 * n10 + this.y);
            int n15 = (int)(n8 * n10 + this.aa);
            float n16 = this.I * n10 + this.G;
            for (int j = n9 + i; j < e + i; ++j) {
                try {
                    if (n16 <= this.i[j]) {
                        int n24;
                        if (this.c) {
                            final int n17 = (n12 >> 16) * this.l + (n11 >> 16);
                            final char c = (char)(n11 & (char)(-1));
                            final int n18 = this.a[n17] & 0xFF;
                            final int n19 = this.a[n17 + 1] & 0xFF;
                            final int n20 = n17 + this.l;
                            final int n21 = this.a[n20] & 0xFF;
                            final int n22 = this.a[n20 + 1] & 0xFF;
                            final int n23 = n18 + ((n19 - n18) * c >> 16);
                            n24 = n23 + ((n21 + ((n22 - n21) * c >> 16) - n23) * (n12 & (char)(-1)) >> 16);
                        }
                        else {
                            n24 = (this.a[(n12 >> 16) * this.l + (n11 >> 16)] & 0xFF);
                        }
                        final int n25 = n13 & 0xFF0000;
                        final int n26 = n14 >> 8 & 0xFF00;
                        final int n27 = n15 >> 16;
                        final int n29;
                        final int n28 = (n29 = this.c[j]) & 0xFF0000;
                        final int n30 = n29 & 0xFF00;
                        final int n31 = n29 & 0xFF;
                        this.c[j] = ((n28 + ((n25 - n28) * n24 >> 8) & 0xFF0000) | (n30 + ((n26 - n30) * n24 >> 8) & 0xFF00) | (n31 + ((n27 - n31) * n24 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n11 += this.a;
                n12 += this.i;
                n13 += this.p;
                n14 += this.f;
                n15 += this.c;
                n16 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.G += this.M;
        }
    }
    
    private final void d(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        final float n9 = this.d;
        while (i < n3) {
            int n10;
            if ((n10 = (int)(this.x + 0.5f)) < 0) {
                n10 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n11 = n10 + 0.5f - this.x;
            int n12 = (int)(n4 * n11 + this.W);
            int n13 = (int)(n5 * n11 + this.L);
            int n14 = (int)(n6 * n11 + this.J);
            int n15 = (int)(n7 * n11 + this.y);
            int n16 = (int)(n8 * n11 + this.aa);
            int n17 = (int)(n9 * n11 + this.af);
            float n18 = this.I * n11 + this.G;
            for (int j = n10 + i; j < e + i; ++j) {
                try {
                    if (n18 <= this.i[j]) {
                        int n26;
                        if (this.c) {
                            final int n19 = (n13 >> 16) * this.l + (n12 >> 16);
                            final char c = (char)(n12 & (char)(-1));
                            final int n20 = this.a[n19] & 0xFF;
                            final int n21 = this.a[n19 + 1] & 0xFF;
                            final int n22 = n19 + this.l;
                            final int n23 = this.a[n22] & 0xFF;
                            final int n24 = this.a[n22 + 1] & 0xFF;
                            final int n25 = n20 + ((n21 - n20) * c >> 16);
                            n26 = n25 + ((n23 + ((n24 - n23) * c >> 16) - n25) * (n13 & (char)(-1)) >> 16);
                        }
                        else {
                            n26 = (this.a[(n13 >> 16) * this.l + (n12 >> 16)] & 0xFF);
                        }
                        final int n27 = n26 * (n17 >> 16) >> 8;
                        final int n28 = n14 & 0xFF0000;
                        final int n29 = n15 >> 8 & 0xFF00;
                        final int n30 = n16 >> 16;
                        final int n32;
                        final int n31 = (n32 = this.c[j]) & 0xFF0000;
                        final int n33 = n32 & 0xFF00;
                        final int n34 = n32 & 0xFF;
                        this.c[j] = ((n31 + ((n28 - n31) * n27 >> 8) & 0xFF0000) | (n33 + ((n29 - n33) * n27 >> 8) & 0xFF00) | (n34 + ((n30 - n34) * n27 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n12 += this.a;
                n13 += this.i;
                n14 += this.p;
                n15 += this.f;
                n16 += this.c;
                n17 += this.d;
                n18 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.af += this.f;
            this.G += this.M;
        }
    }
    
    private final void o(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        while (i < n3) {
            int n9;
            if ((n9 = (int)(this.x + 0.5f)) < 0) {
                n9 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n10 = n9 + 0.5f - this.x;
            int n11 = (int)(n4 * n10 + this.W);
            int n12 = (int)(n5 * n10 + this.L);
            int n13 = (int)(n6 * n10 + this.J);
            int n14 = (int)(n7 * n10 + this.y);
            int n15 = (int)(n8 * n10 + this.aa);
            float n16 = this.I * n10 + this.G;
            for (int j = n9 + i; j < e + i; ++j) {
                try {
                    if (n16 <= this.i[j]) {
                        this.i[j] = n16;
                        int n26;
                        int n30;
                        int n34;
                        if (this.c) {
                            final int n17 = (n12 >> 16) * this.l + (n11 >> 16);
                            final char c = (char)((n11 & (char)(-1)) >> 9);
                            final char c2 = (char)((n12 & (char)(-1)) >> 9);
                            final int n18 = this.a[n17];
                            final int n19 = this.a[n17 + 1];
                            final int n20 = n17 + this.l;
                            final int n21 = this.a[n20];
                            final int n22 = this.a[n20 + 1];
                            final int n23 = n18 & 0xFF0000;
                            final int n24 = n21 & 0xFF0000;
                            final int n25 = n23 + (((n19 & 0xFF0000) - n23) * c >> 7);
                            n26 = n25 + ((n24 + (((n22 & 0xFF0000) - n24) * c >> 7) - n25) * c2 >> 7);
                            final int n27 = n18 & 0xFF00;
                            final int n28 = n21 & 0xFF00;
                            final int n29 = n27 + (((n19 & 0xFF00) - n27) * c >> 7);
                            n30 = n29 + ((n28 + (((n22 & 0xFF00) - n28) * c >> 7) - n29) * c2 >> 7);
                            final int n31 = n18 & 0xFF;
                            final int n32 = n21 & 0xFF;
                            final int n33 = n31 + (((n19 & 0xFF) - n31) * c >> 7);
                            n34 = n33 + ((n32 + (((n22 & 0xFF) - n32) * c >> 7) - n33) * c2 >> 7);
                        }
                        else {
                            final int n35;
                            n26 = ((n35 = this.a[(n12 >> 16) * this.l + (n11 >> 16)]) & 0xFF0000);
                            n30 = (n35 & 0xFF00);
                            n34 = (n35 & 0xFF);
                        }
                        this.c[j] = ((n26 * (n13 >> 16) & 0xFF000000) | (n30 * (n14 >> 16) & 0xFF0000) | n34 * (n15 >> 16)) >> 8;
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n11 += this.a;
                n12 += this.i;
                n13 += this.p;
                n14 += this.f;
                n15 += this.c;
                n16 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.G += this.M;
        }
    }
    
    private final void e(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        final float n9 = this.d;
        while (i < n3) {
            int n10;
            if ((n10 = (int)(this.x + 0.5f)) < 0) {
                n10 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n11 = n10 + 0.5f - this.x;
            int n12 = (int)(n4 * n11 + this.W);
            int n13 = (int)(n5 * n11 + this.L);
            int n14 = (int)(n6 * n11 + this.J);
            int n15 = (int)(n7 * n11 + this.y);
            int n16 = (int)(n8 * n11 + this.aa);
            int n17 = (int)(n9 * n11 + this.af);
            float n18 = this.I * n11 + this.G;
            for (int j = n10 + i; j < e + i; ++j) {
                try {
                    if (n18 < this.i[j]) {
                        final int n19 = n17 >> 16;
                        int n29;
                        int n33;
                        int n37;
                        if (this.c) {
                            final int n20 = (n13 >> 16) * this.l + (n12 >> 16);
                            final char c = (char)((n12 & (char)(-1)) >> 9);
                            final char c2 = (char)((n13 & (char)(-1)) >> 9);
                            final int n21 = this.a[n20];
                            final int n22 = this.a[n20 + 1];
                            final int n23 = n20 + this.l;
                            final int n24 = this.a[n23];
                            final int n25 = this.a[n23 + 1];
                            final int n26 = n21 & 0xFF0000;
                            final int n27 = n24 & 0xFF0000;
                            final int n28 = n26 + (((n22 & 0xFF0000) - n26) * c >> 7);
                            n29 = n28 + ((n27 + (((n25 & 0xFF0000) - n27) * c >> 7) - n28) * c2 >> 7) >> 16;
                            final int n30 = n21 & 0xFF00;
                            final int n31 = n24 & 0xFF00;
                            final int n32 = n30 + (((n22 & 0xFF00) - n30) * c >> 7);
                            n33 = n32 + ((n31 + (((n25 & 0xFF00) - n31) * c >> 7) - n32) * c2 >> 7) >> 8;
                            final int n34 = n21 & 0xFF;
                            final int n35 = n24 & 0xFF;
                            final int n36 = n34 + (((n22 & 0xFF) - n34) * c >> 7);
                            n37 = n36 + ((n35 + (((n25 & 0xFF) - n35) * c >> 7) - n36) * c2 >> 7);
                        }
                        else {
                            final int n38;
                            n29 = ((n38 = this.a[(n13 >> 16) * this.l + (n12 >> 16)]) & 0xFF0000) >> 16;
                            n33 = (n38 & 0xFF00) >> 8;
                            n37 = (n38 & 0xFF);
                        }
                        final int n39 = n29 * n14 >>> 8;
                        final int n40 = n33 * n15 >>> 16;
                        final int n41 = n37 * n16 >>> 24;
                        final int n43;
                        final int n42 = (n43 = this.c[j]) & 0xFF0000;
                        final int n44 = n43 & 0xFF00;
                        final int n45 = n43 & 0xFF;
                        this.c[j] = ((n42 + ((n39 - n42) * n19 >> 8) & 0xFF0000) | (n44 + ((n40 - n44) * n19 >> 8) & 0xFF00) | (n45 + ((n41 - n45) * n19 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n12 += this.a;
                n13 += this.i;
                n14 += this.p;
                n15 += this.f;
                n16 += this.c;
                n17 += this.d;
                n18 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.af += this.f;
            this.G += this.M;
        }
    }
    
    private final void n(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        while (i < n3) {
            int n9;
            if ((n9 = (int)(this.x + 0.5f)) < 0) {
                n9 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n10 = n9 + 0.5f - this.x;
            int n11 = (int)(n4 * n10 + this.W);
            int n12 = (int)(n5 * n10 + this.L);
            int n13 = (int)(n6 * n10 + this.J);
            int n14 = (int)(n7 * n10 + this.y);
            int n15 = (int)(n8 * n10 + this.aa);
            float n16 = this.I * n10 + this.G;
            for (int j = n9 + i; j < e + i; ++j) {
                try {
                    if (n16 <= this.i[j]) {
                        int n26;
                        int n30;
                        int n34;
                        int n38;
                        if (this.c) {
                            final int n17 = (n12 >> 16) * this.l + (n11 >> 16);
                            final char c = (char)((n11 & (char)(-1)) >> 9);
                            final char c2 = (char)((n12 & (char)(-1)) >> 9);
                            final int n18 = this.a[n17];
                            final int n19 = this.a[n17 + 1];
                            final int n20 = n17 + this.l;
                            final int n21 = this.a[n20];
                            final int n22 = this.a[n20 + 1];
                            final int n23 = n18 & 0xFF0000;
                            final int n24 = n21 & 0xFF0000;
                            final int n25 = n23 + (((n19 & 0xFF0000) - n23) * c >> 7);
                            n26 = n25 + ((n24 + (((n22 & 0xFF0000) - n24) * c >> 7) - n25) * c2 >> 7) >> 16;
                            final int n27 = n18 & 0xFF00;
                            final int n28 = n21 & 0xFF00;
                            final int n29 = n27 + (((n19 & 0xFF00) - n27) * c >> 7);
                            n30 = n29 + ((n28 + (((n22 & 0xFF00) - n28) * c >> 7) - n29) * c2 >> 7) >> 8;
                            final int n31 = n18 & 0xFF;
                            final int n32 = n21 & 0xFF;
                            final int n33 = n31 + (((n19 & 0xFF) - n31) * c >> 7);
                            n34 = n33 + ((n32 + (((n22 & 0xFF) - n32) * c >> 7) - n33) * c2 >> 7);
                            final int n35 = n18 >>> 24;
                            final int n36 = n21 >>> 24;
                            final int n37 = n35 + (((n19 >>> 24) - n35) * c >> 7);
                            n38 = n37 + ((n36 + (((n22 >>> 24) - n36) * c >> 7) - n37) * c2 >> 7);
                        }
                        else {
                            final int n39;
                            n38 = (n39 = this.a[(n12 >> 16) * this.l + (n11 >> 16)]) >>> 24;
                            n26 = (n39 & 0xFF0000) >> 16;
                            n30 = (n39 & 0xFF00) >> 8;
                            n34 = (n39 & 0xFF);
                        }
                        final int n40 = n26 * n13 >>> 8;
                        final int n41 = n30 * n14 >>> 16;
                        final int n42 = n34 * n15 >>> 24;
                        final int n44;
                        final int n43 = (n44 = this.c[j]) & 0xFF0000;
                        final int n45 = n44 & 0xFF00;
                        final int n46 = n44 & 0xFF;
                        this.c[j] = ((n43 + ((n40 - n43) * n38 >> 8) & 0xFF0000) | (n45 + ((n41 - n45) * n38 >> 8) & 0xFF00) | (n46 + ((n42 - n46) * n38 >> 8) & 0xFF));
                    }
                }
                catch (Exception ex) {}
                n11 += this.a;
                n12 += this.i;
                n13 += this.p;
                n14 += this.f;
                n15 += this.c;
                n16 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.G += this.M;
        }
    }
    
    private final void m(final float n, final float n2, int i, int n3) {
        i *= this.e;
        n3 *= this.e;
        final int o = this.o;
        final float n4 = this.a;
        final float n5 = this.i;
        final float n6 = this.p;
        final float n7 = this.f;
        final float n8 = this.c;
        final float n9 = this.d;
        while (i < n3) {
            int n10;
            if ((n10 = (int)(this.x + 0.5f)) < 0) {
                n10 = 0;
            }
            int e;
            if ((e = (int)(this.P + 0.5f)) > this.e) {
                e = this.e;
            }
            final float n11 = n10 + 0.5f - this.x;
            int n12 = (int)(n4 * n11 + this.W);
            int n13 = (int)(n5 * n11 + this.L);
            int n14 = (int)(n6 * n11 + this.J);
            int n15 = (int)(n7 * n11 + this.y);
            int n16 = (int)(n8 * n11 + this.aa);
            int n17 = (int)(n9 * n11 + this.af);
            float n18 = this.I * n11 + this.G;
            for (int j = n10 + i; j < e + i; ++j) {
                try {
                    if (n18 < this.i[j]) {
                        final int n19 = n17 >> 16;
                        int n29;
                        int n33;
                        int n37;
                        int n41;
                        if (this.c) {
                            final int n20 = (n13 >> 16) * this.l + (n12 >> 16);
                            final char c = (char)((n12 & (char)(-1)) >> 9);
                            final char c2 = (char)((n13 & (char)(-1)) >> 9);
                            final int n21 = this.a[n20];
                            final int n22 = this.a[n20 + 1];
                            final int n23 = n20 + this.l;
                            final int n24 = this.a[n23];
                            final int n25 = this.a[n23 + 1];
                            final int n26 = n21 & 0xFF0000;
                            final int n27 = n24 & 0xFF0000;
                            final int n28 = n26 + (((n22 & 0xFF0000) - n26) * c >> 7);
                            n29 = n28 + ((n27 + (((n25 & 0xFF0000) - n27) * c >> 7) - n28) * c2 >> 7) >> 16;
                            final int n30 = n21 & 0xFF00;
                            final int n31 = n24 & 0xFF00;
                            final int n32 = n30 + (((n22 & 0xFF00) - n30) * c >> 7);
                            n33 = n32 + ((n31 + (((n25 & 0xFF00) - n31) * c >> 7) - n32) * c2 >> 7) >> 8;
                            final int n34 = n21 & 0xFF;
                            final int n35 = n24 & 0xFF;
                            final int n36 = n34 + (((n22 & 0xFF) - n34) * c >> 7);
                            n37 = n36 + ((n35 + (((n25 & 0xFF) - n35) * c >> 7) - n36) * c2 >> 7);
                            final int n38 = n21 >>> 24;
                            final int n39 = n24 >>> 24;
                            final int n40 = n38 + (((n22 >>> 24) - n38) * c >> 7);
                            n41 = n19 * (n40 + ((n39 + (((n25 >>> 24) - n39) * c >> 7) - n40) * c2 >> 7)) >> 8;
                        }
                        else {
                            final int n42 = this.a[(n13 >> 16) * this.l + (n12 >> 16)];
                            n41 = n19 * (n42 >>> 24) >> 8;
                            n29 = (n42 & 0xFF0000) >> 16;
                            n33 = (n42 & 0xFF00) >> 8;
                            n37 = (n42 & 0xFF);
                        }
                        final int n43 = n29 * n14 >>> 8;
                        final int n44 = n33 * n15 >>> 16;
                        final int n45 = n37 * n16 >>> 24;
                        final int n47;
                        final int n46 = (n47 = this.c[j]) & 0xFF0000;
                        final int n48 = n47 & 0xFF00;
                        final int n49 = n47 & 0xFF;
                        this.c[j] = ((n46 + ((n43 - n46) * n41 >> 8) & 0xFF0000) | (n48 + ((n44 - n48) * n41 >> 8) & 0xFF00) | (n49 + ((n45 - n49) * n41 >> 8) & 0xFF));
                        this.b[j] = o;
                    }
                }
                catch (Exception ex) {}
                n12 += this.a;
                n13 += this.i;
                n14 += this.p;
                n15 += this.f;
                n16 += this.c;
                n17 += this.d;
                n18 += this.I;
            }
            i += this.e;
            this.x += n;
            this.P += n2;
            this.W += this.v;
            this.L += this.r;
            this.J += this.B;
            this.y += this.T;
            this.aa += this.h;
            this.af += this.f;
            this.G += this.M;
        }
    }
    
    public c(final j a) {
        this.j = new float[3];
        this.f = new float[3];
        this.a = new float[3];
        this.d = new float[3];
        this.h = new float[3];
        this.c = new float[3];
        this.g = new float[3];
        this.e = new float[3];
        this.b = new float[3];
        this.a = a;
        this.b();
    }
}
