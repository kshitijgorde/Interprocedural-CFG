// 
// Decompiled by Procyon v0.5.30
// 

abstract class bg extends k
{
    int a;
    int b;
    int c;
    int d;
    int g;
    int h;
    float[] l;
    float[] m;
    float[] n;
    float p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;
    int aa;
    int ab;
    int ac;
    int ad;
    int ae;
    int af;
    int ag;
    int ah;
    int ai;
    int aj;
    int ak;
    int al;
    int am;
    int an;
    float ao;
    float ap;
    float aq;
    float ar;
    float as;
    float at;
    float au;
    float av;
    float aw;
    float ax;
    float ay;
    float az;
    int a0;
    int a1;
    int[] a2;
    float a3;
    float a4;
    float a5;
    float a6;
    float a7;
    float a8;
    float a9;
    float ba;
    float bb;
    float bc;
    float bd;
    float be;
    float bf;
    float bg;
    float bh;
    float bi;
    float bj;
    float bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int bt;
    float bu;
    float bv;
    float bw;
    float bx;
    float by;
    float bz;
    int b0;
    int b1;
    int b2;
    boolean b3;
    int b4;
    int b5;
    int b6;
    int b7;
    int b8;
    int b9;
    float[] ca;
    float[] cb;
    static final int[] cc;
    float cd;
    float ce;
    int cf;
    int cg;
    int[] ch;
    int[] ci;
    float cj;
    float ck;
    static int cl;
    static int cm;
    static int cn;
    static int[] co;
    static int cp;
    static int cq;
    int cr;
    int cs;
    int ct;
    int cu;
    int cv;
    int cw;
    int cx;
    int cy;
    int cz;
    int c0;
    int c1;
    int c2;
    
    protected bg() {
        this.l = null;
        this.m = null;
        this.n = null;
        this.a2 = new int[2048];
        this.ca = new float[3];
        this.cb = new float[3];
        this.ch = new int[] { 99, 101, 53, 53, 83, 83, 86, 54 };
        this.ci = new int[] { 256, 256, 128, 85, 64 };
        this.cr = -1;
        this.cs = -1;
        this.ct = -1;
        final float[] l = null;
        this.n = l;
        this.m = l;
        this.l = l;
        super.ak = new float[3];
        super.aj = new float[3];
        super.al = new float[3];
    }
    
    void e() {
    }
    
    protected boolean a(final float[][] array) {
        this.h = 2147418367;
        this.l = array[0];
        this.m = array[1];
        this.n = array[2];
        return true;
    }
    
    void c() {
        super.c();
        final int[] array = null;
        super.t = array;
        super.u = array;
    }
    
    void d() {
        final int[] array = null;
        super.t = array;
        super.u = array;
        super.l = array;
        super.k = array;
        super.d();
    }
    
    void c(final int n, final int n2, int n3, final int n4) {
        for (int n5 = n + n2, i = n; i < n5; ++i, ++n3, this.p += super.bc) {
            if (this.p >= super.y[i]) {
                if (this.c(n3, n4)) {
                    super.aa[i] = this.h;
                    super.y[i] = this.p;
                    final int[] ab = super.ab;
                    final int n6 = i >> 5;
                    ab[n6] |= 1 << (i & 0x1F);
                }
            }
        }
    }
    
    private static final int a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n + n3 >> 1;
        final int n8 = n2 + n4 >> 1;
        int n9;
        int n10;
        for (n9 = ((n7 >> 8) * (n7 >> 8) + (n8 >> 8) * (n8 >> 8)) * 2 >> 6, n10 = 0; n10 < n5 && n9 > 65536; ++n10, n9 >>= 2) {}
        return n10;
    }
    
    static final float a(final float n) {
        return (n > 0.0f) ? n : (-n);
    }
    
    void b(final int n) {
        final l l = super.i[0];
        super.k = l.f[n];
        super.m = l.c(n);
        super.n = l.d(n);
        this.b0 = super.m * super.n;
        this.bm = 16 + n;
        this.bl = 8 + n;
        this.bn = 32768 << n;
    }
    
    boolean c(final int n) {
        final l l = super.i[1];
        super.t = l.f[n];
        super.v = l.c(n);
        super.w = l.d(n);
        this.b1 = super.v * super.w;
        this.bp = 16 + n;
        this.bo = 8 + n;
        this.bq = 32768 << n;
        this.a = super.v << this.bp;
        this.b = super.w << this.bp;
        return true;
    }
    
    void d(final int n) {
        final l l = super.i[2];
        super.u = l.f[n];
        super.s = l.c(n);
        super.r = l.c(n);
        this.b2 = super.s * super.r;
        this.bs = 16 + n;
        this.br = 8 + n;
        this.bt = 32768 << n;
        this.c = super.s << this.bs;
        this.d = super.r << this.bs;
    }
    
    final void a(final int n, final int[] array, final float[][] array2, final int n2) {
        final float p4 = super.x.p;
        if (super.bc > p4 || super.bc < -p4 || super.bd > p4 || super.bd < -p4) {
            super.ai = true;
        }
        else {
            super.ai = false;
        }
        this.a1 = 0;
        if (this.l != null && super.i[0] != null) {
            this.a1 |= 0x1;
        }
        if (this.m != null && super.i[1] != null) {
            this.a1 |= 0x2;
        }
        if (this.n != null && super.i[2] != null) {
            this.a1 |= 0x4;
        }
        if ((this.a0 & this.a1) != this.a0) {
            return;
        }
        this.a1 &= this.a0;
        int i = 0;
        int a = 0;
        int a2 = 0;
        this.g = 0;
        if ((this.a1 & 0x1) != 0x0) {
            if ((super.b.g & 0x1) == 0x0) {
                this.a3 = this.l[super.bj];
                this.a6 = this.l[super.bj + 1];
                this.a4 = this.l[super.bk];
                this.a7 = this.l[super.bk + 1];
                this.a5 = this.l[super.bl];
                this.a8 = this.l[super.bl + 1];
            }
            else {
                this.a3 = this.l[super.bg];
                this.a6 = this.l[super.bg + 1];
                this.a4 = this.l[super.bh];
                this.a7 = this.l[super.bh + 1];
                this.a5 = this.l[super.bi];
                this.a8 = this.l[super.bi + 1];
            }
            this.a4 -= this.a3;
            this.a5 -= this.a3;
            this.a7 -= this.a6;
            this.a8 -= this.a6;
            this.bu = (super.a8 * this.a4 + super.a9 * this.a5) * 65536.0f;
            this.bv = (super.a8 * this.a7 + super.a9 * this.a8) * 65536.0f;
            this.s = (int)this.bu;
            this.t = (int)this.bv;
            this.u = (int)((super.ba * this.a4 + super.bb * this.a5) * 65536.0);
            this.v = (int)((super.ba * this.a7 + super.bb * this.a8) * 65536.0);
            final float n3 = super.ba * this.a4 + super.bb * this.a5;
            final float n4 = super.ba * this.a7 + super.bb * this.a8;
            final int n5 = (this.s >> 12) * (this.s >> 12) + (this.t >> 12) * (this.t >> 12);
            final int n6 = (this.u >> 12) * (this.u >> 12) + (this.v >> 12) * (this.v >> 12);
            for (int n7 = (n5 > n6) ? n5 : n6, i = 0; i < super.i[0].g - 1; ++i, n7 >>= 2) {
                if (n7 <= 256) {
                    break;
                }
            }
        }
        if ((this.a1 & 0x2) != 0x0) {
            if ((super.b.g & 0x2) == 0x0) {
                this.a9 = this.m[super.bj];
                this.bc = this.m[super.bj + 1];
                this.ba = this.m[super.bk];
                this.bd = this.m[super.bk + 1];
                this.bb = this.m[super.bl];
                this.be = this.m[super.bl + 1];
            }
            else {
                this.a9 = this.m[super.bg];
                this.bc = this.m[super.bg + 1];
                this.ba = this.m[super.bh];
                this.bd = this.m[super.bh + 1];
                this.bb = this.m[super.bi];
                this.be = this.m[super.bi + 1];
                final float n8 = (this.a9 < this.ba) ? this.a9 : this.ba;
                final float n9 = (n8 < this.bb) ? n8 : this.bb;
                final float n10 = (this.bc < this.bd) ? this.bc : this.bd;
                final float n11 = (n10 < this.be) ? n10 : this.be;
                final int c = super.i[1].c;
                final int d = super.i[1].d;
                final int n12 = (int)(n9 / c);
                final int n13 = (int)(n11 / d);
                final int n14 = (n9 < 0.0f) ? ((n12 - 1) * c) : (n12 * c);
                final int n15 = (n11 < 0.0f) ? ((n13 - 1) * d) : (n13 * d);
                this.a9 -= n14;
                this.bc -= n15;
                this.ba -= n14;
                this.bd -= n15;
                this.bb -= n14;
                this.be -= n15;
            }
            if (this.a9 < 0.0f || this.ba < 0.0f || this.bb < 0.0f || this.bc < 0.0f || this.bd < 0.0f || this.be < 0.0f) {}
            this.au = this.a9 * super.av;
            this.aw = this.ba * super.aw;
            this.ay = this.bb * super.ax;
            this.av = this.bc * super.av;
            this.ax = this.bd * super.aw;
            this.az = this.be * super.ax;
            this.ba -= this.a9;
            this.bb -= this.a9;
            this.bd -= this.bc;
            this.be -= this.bc;
            this.bw = (super.a8 * this.ba + super.a9 * this.bb) * 65536.0f;
            this.bx = (super.a8 * this.bd + super.a9 * this.be) * 65536.0f;
            this.y = (int)this.bw;
            this.z = (int)this.bx;
            this.aa = (int)((super.ba * this.ba + super.bb * this.bb) * 65536.0);
            this.ab = (int)((super.ba * this.bd + super.bb * this.be) * 65536.0);
            a = a(this.y, this.z, this.aa, this.ab, super.i[1].g - 1, 2);
        }
        if ((this.a1 & 0x4) != 0x0) {
            if ((super.b.g & 0x4) == 0x0) {
                this.bf = this.n[super.bj];
                this.bi = this.n[super.bj + 1];
                this.bg = this.n[super.bk];
                this.bj = this.n[super.bk + 1];
                this.bh = this.n[super.bl];
                this.bk = this.n[super.bl + 1];
            }
            else {
                this.bf = this.n[super.bg];
                this.bi = this.n[super.bg + 1];
                this.bg = this.n[super.bh];
                this.bj = this.n[super.bh + 1];
                this.bh = this.n[super.bi];
                this.bk = this.n[super.bi + 1];
                final float n16 = (this.bf < this.bg) ? this.bf : this.bg;
                final float n17 = (n16 < this.bh) ? n16 : this.bh;
                final float n18 = (this.bi < this.bj) ? this.bi : this.bj;
                final float n19 = (n18 < this.bk) ? n18 : this.bk;
                final int c2 = super.i[2].c;
                final int d2 = super.i[2].d;
                final int n20 = (int)(n17 / c2);
                final int n21 = (int)(n19 / d2);
                final int n22 = (n17 < 0.0f) ? ((n20 - 1) * c2) : (n20 * c2);
                final int n23 = (n19 < 0.0f) ? ((n21 - 1) * d2) : (n21 * d2);
                this.bf -= n22;
                this.bi -= n23;
                this.bg -= n22;
                this.bj -= n23;
                this.bh -= n22;
                this.bk -= n23;
            }
            this.ao = this.bf * super.av;
            this.aq = this.bg * super.aw;
            this.as = this.bh * super.ax;
            this.ap = this.bi * super.av;
            this.ar = this.bj * super.aw;
            this.at = this.bk * super.ax;
            this.bg -= this.bf;
            this.bh -= this.bf;
            this.bj -= this.bi;
            this.bk -= this.bi;
            this.by = (super.a8 * this.bg + super.a9 * this.bh) * 65536.0f;
            this.bz = (super.a8 * this.bj + super.a9 * this.bk) * 65536.0f;
            this.ae = (int)this.by;
            this.af = (int)this.bz;
            this.ag = (int)((super.ba * this.bg + super.bb * this.bh) * 65536.0);
            this.ah = (int)((super.ba * this.bj + super.bb * this.bk) * 65536.0);
            a2 = a(this.ae, this.af, (int)((super.ba * this.bg + super.bb * this.bh) * 65536.0f), (int)((super.ba * this.bj + super.bb * this.bk) * 65536.0f), super.i[2].g - 1, 2);
        }
        final float n24 = super.as - (int)super.as;
        final float n25 = super.ap - (int)super.ap;
        final int n26 = (int)super.as;
        final int n27 = (int)super.ap;
        this.ai = (int)(this.a3 * 65536.0f) - (n26 * this.s + n27 * this.u);
        this.aj = (int)(this.a6 * 65536.0f) - (n26 * this.t + n27 * this.v);
        this.ai -= (int)(n24 * this.s + n25 * this.u);
        this.aj -= (int)(n24 * this.t + n25 * this.v);
        this.ak = (int)(this.a9 * 65536.0f) - (n26 * this.y + n27 * this.aa);
        this.al = (int)(this.bc * 65536.0f) - (n26 * this.z + n27 * this.ab);
        this.ak -= (int)(n24 * this.y + n25 * this.aa);
        this.al -= (int)(n24 * this.z + n25 * this.ab);
        this.am = (int)(this.bf * 65536.0f) - (n26 * this.ae + n27 * this.ag);
        this.an = (int)(this.bi * 65536.0f) - (n26 * this.af + n27 * this.ah);
        this.am -= (int)(n24 * this.ae + n25 * this.ag);
        this.an -= (int)(n24 * this.af + n25 * this.ah);
        this.e();
        this.f();
        if ((this.a1 & 0x1) != 0x0) {
            this.b(i);
        }
        if ((this.a1 & 0x2) != 0x0 && !this.c(a)) {
            return;
        }
        if ((this.a1 & 0x4) != 0x0) {
            this.d(a2);
        }
        this.b3 = false;
        if (this.ce < 12288 << a) {
            this.b3 = true;
        }
        final boolean b = true;
        if (b) {
            if (super.ap - (int)super.ap < 0.5f) {
                --super.ay;
                if (super.ay < 0) {
                    super.ay = 0;
                }
            }
            if (super.ar - (int)super.ar > 0.5f) {
                ++super.a0;
                if (super.a0 >= super.af) {
                    super.a0 = super.af - 1;
                }
            }
        }
        final float n28 = (super.au - super.as) / (super.ar - super.ap);
        if (k.br != null) {
            k.br.a(this);
            k.br.b(this);
        }
        if (super.az - super.ay > 0) {
            final float n29 = 1.0f / (super.aq - super.ap);
            final float n30 = 1.0f / (super.ar - super.ap);
            final float n31 = super.ay - super.ap;
            final float n32 = (super.at - super.as) / (super.aq - super.ap);
            float n33 = super.as + n31 * n32;
            float n34 = super.as + n31 * n28;
            if (n28 > n32) {
                if (b) {
                    this.cj = n32 * 0.5f;
                    this.ck = n28 * 0.5f;
                    if (this.cj < 0.0f) {
                        this.cj = -this.cj;
                    }
                    if (this.ck < 0.0f) {
                        this.ck = -this.ck;
                    }
                    if (this.cj < 0.5f) {
                        this.cj = 0.5f;
                    }
                    if (this.ck < 0.5f) {
                        this.ck = 0.5f;
                    }
                    final float n35 = n33 - this.cj;
                    final float n36 = n34 + this.ck;
                    n33 = n35 - 0.25f;
                    n34 = n36 + 0.25f;
                }
                this.a(n33, n32, n34, n28, super.ay, super.az, b, n2);
            }
            else {
                if (b) {
                    this.cj = n28 * 0.5f;
                    this.ck = n32 * 0.5f;
                    if (this.cj < 0.0f) {
                        this.cj = -this.cj;
                    }
                    if (this.ck < 0.0f) {
                        this.ck = -this.ck;
                    }
                    if (this.cj < 0.5f) {
                        this.cj = 0.5f;
                    }
                    if (this.ck < 0.5f) {
                        this.ck = 0.5f;
                    }
                    final float n37 = n34 - this.cj;
                    final float n38 = n33 + this.ck;
                    n34 = n37 - 0.25f;
                    n33 = n38 + 0.25f;
                }
                this.a(n34, n28, n33, n32, super.ay, super.az, b, n2);
            }
        }
        if (super.a0 - super.az > 0) {
            final float n39 = 1.0f / (super.ar - super.ap);
            final float n40 = 1.0f / (super.ar - super.aq);
            final float n41 = super.az - super.aq;
            final float n42 = super.az - super.ap;
            final float n43 = (super.au - super.at) / (super.ar - super.aq);
            float n44 = super.at + n41 * n43;
            float n45 = super.as + n42 * n28;
            if (n28 < n43) {
                if (b) {
                    this.cj = n43 * 0.5f;
                    this.ck = n28 * 0.5f;
                    if (this.cj < 0.0f) {
                        this.cj = -this.cj;
                    }
                    if (this.ck < 0.0f) {
                        this.ck = -this.ck;
                    }
                    if (this.cj < 0.5f) {
                        this.cj = 0.5f;
                    }
                    if (this.ck < 0.5f) {
                        this.ck = 0.5f;
                    }
                    final float n46 = n44 - this.cj;
                    final float n47 = n45 + this.ck;
                    n44 = n46 - 0.25f;
                    n45 = n47 + 0.25f;
                }
                this.a(n44, n43, n45, n28, super.az, super.a0, b, n2);
            }
            else {
                if (b) {
                    this.cj = n28 * 0.5f;
                    this.ck = n43 * 0.5f;
                    if (this.cj < 0.0f) {
                        this.cj = -this.cj;
                    }
                    if (this.ck < 0.0f) {
                        this.ck = -this.ck;
                    }
                    if (this.cj < 0.5f) {
                        this.cj = 0.5f;
                    }
                    if (this.ck < 0.5f) {
                        this.ck = 0.5f;
                    }
                    final float n48 = n45 - this.cj;
                    final float n49 = n44 + this.ck;
                    n45 = n48 - 0.25f;
                    n44 = n49 + 0.25f;
                }
                this.a(n45, n28, n44, n43, super.az, super.a0, b, n2);
            }
        }
    }
    
    private final void a(float n, final float n2, float n3, final float n4, int i, int n5, final boolean b, final int n6) {
        int n7;
        int n8;
        int ag;
        int n9;
        int n10;
        float p8;
        float p9;
        int n11;
        float[] r;
        int[] q;
        float p10;
        int n12;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        int j;
        for (n7 = i, i *= super.ah, n5 *= super.ah; i < n5; i += super.ah, n += n2, n3 += n4, ++n7) {
            n8 = (int)n;
            if (n < 0.0f && n != n8) {
                --n8;
            }
            if (++n8 < 0) {
                n8 = 0;
            }
            ag = (int)n3;
            if (n3 < 0.0f && n3 != ag) {
                --ag;
            }
            if (++ag > super.ag) {
                ag = super.ag;
            }
            this.p = n8 * super.bc + n7 * super.bd + super.be;
            this.cf = (int)(n + this.cj);
            this.cg = (int)(n3 - this.ck);
            n9 = n8 + i;
            n10 = ag + i - n9;
            if (n10 > 0) {
                if (this.p > this.p + super.bc * (n10 - 1)) {
                    p8 = this.p;
                }
                else {
                    p9 = this.p;
                }
                if (!super.d) {}
                n11 = n9 - i;
                if ((this.a1 & 0x1) != 0x0) {
                    this.q = n11 * this.s + n7 * this.u + this.ai;
                    this.r = n11 * this.t + n7 * this.v + this.aj;
                }
                if ((this.a1 & 0x2) != 0x0) {
                    this.w = n11 * this.y + n7 * this.aa + this.ak;
                    this.x = n11 * this.z + n7 * this.ab + this.al;
                }
                if ((this.a1 & 0x4) != 0x0) {
                    this.ac = n11 * this.ae + n7 * this.ag + this.am;
                    this.ad = n11 * this.af + n7 * this.ah + this.an;
                }
                if (super.x.s) {
                    r = super.x.r;
                    q = super.x.q;
                    p10 = this.p;
                    n12 = n9 - n7 * super.ah;
                    n13 = n7;
                    for (n14 = n9 + n10, n15 = this.cu * n12 + this.cx * n13 + this.c0, n16 = this.cv * n12 + this.cy * n13 + this.c1, n17 = this.cw * n12 + this.cz * n13 + this.c2, j = n9; j < n14; ++j, p10 += super.bc, n15 += this.cu, n16 += this.cv, n17 += this.cw) {
                        if (n15 >= 0 || n16 >= 0 || n17 >= 0) {
                            if (p10 >= r[j]) {
                                q[j] = n6;
                                r[j] = p10;
                            }
                        }
                    }
                }
                if (k.br != null) {
                    k.br.a(n9, n10, n9 - n7 * super.ah, n7, this);
                }
                else {
                    this.c(n9, n10, n9 - n7 * super.ah, n7);
                }
            }
        }
    }
    
    protected final int a(final int[] array) {
        int n = (this.x >>> this.bp) * super.v + (this.w >>> this.bp);
        if (n < 0 || n >= this.b1) {
            n = 0;
        }
        final int n2 = array[n];
        if ((n2 & Integer.MIN_VALUE) != 0x0) {
            final int n3 = this.w - this.bq;
            final int n4 = this.x - this.bq;
            int n5 = (n4 >> this.bp) * super.v + (n3 >> this.bp);
            if (n5 < 0 || n5 + super.v + 1 >= this.b1) {
                n5 = 0;
            }
            final int n6 = array[n5];
            final int n7 = array[n5 + 1];
            final int n8 = array[n5 + super.v];
            final int n9 = array[n5 + super.v + 1];
            final int n10 = n3 >> this.bo & 0xFF;
            final int n11 = 256 - n10;
            final int n12 = n4 >> this.bo & 0xFF;
            final int n13 = 256 - n12;
            final int n14 = n11 * n13 >> 9;
            final int n15 = n10 * n13 >> 9;
            final int n16 = n11 * n12 >> 9;
            final int n17 = 128 - n14 - n15 - n16;
            return (((n6 & 0x7F00FF00) >>> 7) * n14 + ((n7 & 0x7F00FF00) >>> 7) * n15 + ((n8 & 0x7F00FF00) >>> 7) * n16 + ((n9 & 0x7F00FF00) >>> 7) * n17 & 0x7F00FF00) | ((n6 & 0xFF00FF) * n14 + (n7 & 0xFF00FF) * n15 + (n8 & 0xFF00FF) * n16 + (n9 & 0xFF00FF) * n17 >> 7 & 0xFF00FF);
        }
        return n2;
    }
    
    final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        super.aj[0] = -(n4 - n2);
        super.ak[0] = n3 - n;
        super.aj[1] = -(n6 - n4);
        super.ak[1] = n5 - n3;
        super.aj[2] = -(n2 - n6);
        super.ak[2] = n - n5;
        final float n7 = 1.0f / (float)Math.sqrt(super.aj[0] * super.aj[0] + super.ak[0] * super.ak[0]);
        final float[] aj = super.aj;
        final int n8 = 0;
        aj[n8] *= n7;
        final float[] ak = super.ak;
        final int n9 = 0;
        ak[n9] *= n7;
        final float n10 = 1.0f / (float)Math.sqrt(super.aj[1] * super.aj[1] + super.ak[1] * super.ak[1]);
        final float[] aj2 = super.aj;
        final int n11 = 1;
        aj2[n11] *= n10;
        final float[] ak2 = super.ak;
        final int n12 = 1;
        ak2[n12] *= n10;
        final float n13 = 1.0f / (float)Math.sqrt(super.aj[2] * super.aj[2] + super.ak[2] * super.ak[2]);
        final float[] aj3 = super.aj;
        final int n14 = 2;
        aj3[n14] *= n13;
        final float[] ak3 = super.ak;
        final int n15 = 2;
        ak3[n15] *= n13;
        if ((n3 - n) * (n6 - n2) - (n5 - n) * (n4 - n2) < 0.0f) {
            super.aj[0] = -super.aj[0];
            super.ak[0] = -super.ak[0];
            super.aj[1] = -super.aj[1];
            super.ak[1] = -super.ak[1];
            super.aj[2] = -super.aj[2];
            super.ak[2] = -super.ak[2];
        }
        this.cu = (int)(super.aj[0] * 65536.0f);
        this.cv = (int)(super.aj[1] * 65536.0f);
        this.cw = (int)(super.aj[2] * 65536.0f);
        this.cx = (int)(super.ak[0] * 65536.0f);
        this.cy = (int)(super.ak[1] * 65536.0f);
        this.cz = (int)(super.ak[2] * 65536.0f);
        if (n2 < n4) {
            super.al[0] = -(n * super.aj[0] + n2 * super.ak[0]);
            this.c0 = (int)(-n * this.cu - n2 * this.cx);
        }
        else {
            super.al[0] = -(n3 * super.aj[0] + n4 * super.ak[0]);
            this.c0 = (int)(-n3 * this.cu - n4 * this.cx);
        }
        if (n4 < n6) {
            super.al[1] = -(n3 * super.aj[1] + n4 * super.ak[1]);
            this.c1 = (int)(-n3 * this.cv - n4 * this.cy);
        }
        else {
            super.al[1] = -(n5 * super.aj[1] + n6 * super.ak[1]);
            this.c1 = (int)(-n5 * this.cv - n6 * this.cy);
        }
        if (n6 < n2) {
            super.al[2] = -(n5 * super.aj[2] + n6 * super.ak[2]);
            this.c2 = (int)(-n5 * this.cw - n6 * this.cz);
        }
        else {
            super.al[2] = -(n * super.aj[2] + n2 * super.ak[2]);
            this.c2 = (int)(-n * this.cw - n2 * this.cz);
        }
        final int cr = -1;
        this.ct = cr;
        this.cs = cr;
        this.cr = cr;
    }
    
    final void a(final int n, final float n2, final float n3, final float n4, final float n5) {
        final float n6 = super.aj[n];
        final float n7 = super.ak[n];
        super.aj[n] = -(n5 - n3);
        super.ak[n] = n4 - n2;
        final float n8 = 1.0f / (float)Math.sqrt(super.aj[n] * super.aj[n] + super.ak[n] * super.ak[n]);
        final float[] aj = super.aj;
        aj[n] *= n8;
        final float[] ak = super.ak;
        ak[n] *= n8;
        if (n6 * super.aj[n] + n7 * super.ak[n] < 0.0f) {
            super.aj[n] = -super.aj[n];
            super.ak[n] = -super.ak[n];
        }
        if (n3 < n5) {
            super.al[n] = -(n2 * super.aj[n] + n3 * super.ak[n]);
            if (n == 0) {
                this.c0 = (int)(-n2 * this.cu - n3 * this.cx);
            }
            if (n == 1) {
                this.c1 = (int)(-n2 * this.cv - n3 * this.cy);
            }
            if (n == 2) {
                this.c2 = (int)(-n2 * this.cw - n3 * this.cz);
            }
        }
        else {
            super.al[n] = -(n4 * super.aj[n] + n5 * super.ak[n]);
            if (n == 0) {
                this.c0 = (int)(-n4 * this.cu - n5 * this.cx);
            }
            if (n == 1) {
                this.c1 = (int)(-n4 * this.cv - n5 * this.cy);
            }
            if (n == 2) {
                this.c2 = (int)(-n4 * this.cw - n5 * this.cz);
            }
        }
    }
    
    final void f() {
        if ((this.a1 & 0x2) == 0x0 && (this.a1 & 0x4) != 0x0) {
            this.b4 = (int)(this.ae * 0.97f + this.ag * 0.242f) >> 2;
            this.b7 = (int)(this.af * 0.97f + this.ah * 0.242f) >> 2;
            this.b5 = (int)(this.ag * 0.97f - this.ae * 0.242f) >> 2;
            this.b6 = (int)(this.ah * 0.97f - this.af * 0.242f) >> 2;
            final float max = Math.max(a((float)this.ae), a((float)this.ag));
            final float max2 = Math.max(a((float)this.af), a((float)this.ah));
            final float n = Math.max(max, max2) * 0.5f;
            this.ce = Math.min(max, max2) * 0.5f;
            this.cd = (int)(n * 1.52E-5);
        }
        else {
            this.b4 = (int)(this.y * 0.97f + this.aa * 0.242f) >> 2;
            this.b7 = (int)(this.z * 0.97f + this.ab * 0.242f) >> 2;
            this.b5 = (int)(this.aa * 0.97f - this.y * 0.242f) >> 2;
            this.b6 = (int)(this.ab * 0.97f - this.z * 0.242f) >> 2;
            final float n2 = Math.max(Math.abs(this.y), Math.abs(this.aa));
            final float n3 = Math.max(Math.abs(this.z), Math.abs(this.ab));
            final float n4 = Math.max(n2, n3) * 0.5f;
            this.ce = Math.min(n2, n3) * 0.5f;
            this.cd = (int)(n4 * 1.52E-5);
        }
        this.b8 = -(this.b4 + this.b5 + (this.b4 + this.b5 >> 1));
        this.b9 = -(this.b7 + this.b6 + (this.b7 + this.b6 >> 1));
        for (int i = 0; i < 3; ++i) {
            this.ca[i] = super.aj[i] * 0.97f + super.ak[i] * 0.242f;
            this.cb[i] = super.ak[i] * 0.97f - super.aj[i] * 0.242f;
            final float[] ca = this.ca;
            final int n5 = i;
            ca[n5] *= 0.25;
            final float[] cb = this.cb;
            final int n6 = i;
            cb[n6] *= 0.25;
        }
    }
    
    final boolean c(final int n, final int n2) {
        for (int i = 0; i < 3; ++i) {
            if (n * super.aj[i] + n2 * super.ak[i] + super.al[i] < 0.0f) {
                return false;
            }
        }
        return true;
    }
    
    static int b(final float n, final float n2) {
        float n3;
        if (n < n2) {
            if (-n > n2) {
                n3 = 4.95f - n2;
            }
            else {
                n3 = 3.54f - n;
            }
        }
        else if (-n > n2) {
            n3 = n + 0.707f;
        }
        else {
            n3 = n2 + 2.12f;
        }
        return (int)(n3 * (bg.cl * bg.cm * 0.17677f)) & (bg.cl - 1) * bg.cm;
    }
    
    final int a(final int n, final int n2, final int n3) {
        int n4 = 65535;
        if (n < 34800) {
            if (this.cr == -1) {
                this.cr = b(super.aj[0], super.ak[0]) + bg.cp;
            }
            final int n5 = this.cr + (n + bg.cq >> bg.cn);
            final int n6 = bg.co[n5];
            n4 = bg.co[n5];
        }
        if (n2 < 34800) {
            if (this.cs == -1) {
                this.cs = b(super.aj[1], super.ak[1]) + bg.cp;
            }
            final int n7 = this.cs + (n2 + bg.cq >> bg.cn);
            final int n8 = bg.co[n7];
            n4 &= bg.co[n7];
        }
        if (n3 < 34800) {
            if (this.ct == -1) {
                this.ct = b(super.aj[2], super.ak[2]) + bg.cp;
            }
            final int n9 = this.ct + (n3 + bg.cq >> bg.cn);
            final int n10 = bg.co[n9];
            n4 &= bg.co[n9];
        }
        return n4;
    }
    
    final int a(final int[] array, final boolean b) {
        while (this.w >= this.a) {
            this.w -= this.a;
        }
        while (this.w < 0) {
            this.w += this.a;
        }
        while (this.x >= this.b) {
            this.x -= this.b;
        }
        while (this.x < 0) {
            this.x += this.b;
        }
        if (b) {
            final int n = (this.x >> this.bp) * super.v + (this.w >> this.bp);
            if (n < 0 || n >= this.b1) {
                return array[0];
            }
            final int n2 = array[n];
            if (this.cd < (n2 & 0x7F000000) >>> 24 << this.bp - 16) {
                return n2;
            }
        }
        if (this.b3) {
            return this.a(array);
        }
        int n3 = 0;
        int n4 = 0;
        for (int n5 = this.b5 - (this.b4 << 2), n6 = this.b6 - (this.b7 << 2), i = this.w + this.b8, j = this.x + this.b9, k = 4; k > 0; --k, i += n5, j += n6) {
            for (int l = 4; l > 0; --l, i += this.b4, j += this.b7) {
                while (i < 0) {
                    i += this.a;
                }
                while (i >= this.a) {
                    i -= this.a;
                }
                while (j < 0) {
                    j += this.b;
                }
                while (j >= this.b) {
                    j -= this.b;
                }
                final int n7 = (j >>> this.bp) * super.v + (i >>> this.bp);
                int n8;
                if (n7 < 0 || n7 >= this.b1) {
                    n8 = array[0];
                }
                else {
                    n8 = array[n7];
                }
                n3 += (n8 & 0x7F00FF00) >> 4;
                n4 += (n8 & 0xFF00FF);
            }
        }
        return (n4 >> 4 & 0xFF00FF) | (n3 & 0x7F00FF00);
    }
    
    final int a(final int[] array, final boolean b, int i) {
        while (this.w >= this.a) {
            this.w -= this.a;
        }
        while (this.w < 0) {
            this.w += this.a;
        }
        while (this.x >= this.b) {
            this.x -= this.b;
        }
        while (this.x < 0) {
            this.x += this.b;
        }
        if (b) {
            final int n = array[(this.x >> this.bp) * super.v + (this.w >> this.bp)];
            int n2 = (n & 0x7F000000) >>> 24;
            if (this.cd < --n2 << this.bp - 16) {
                int a;
                if ((n & Integer.MIN_VALUE) == 0x0) {
                    a = n;
                }
                else {
                    a = this.a(array);
                }
                final int n3 = a & 0xFF00FF;
                final int n4 = (a & 0x7F00FF00) >> 8;
                final int e = e(i);
                return (n3 * e >> 4 & 0xFF00FF) | (n4 * e << 4 & 0x7F00FF00);
            }
        }
        if (this.b3) {
            final int a2 = this.a(array);
            return ((a2 & 0xFF00FF) * e(i) >> 4 & 0xFF00FF) | (((a2 & 0x7F00FF00) >> 8) * e(i) << 4 & 0x7F00FF00);
        }
        int n5 = 0;
        int n6 = 0;
        for (int n7 = this.w + this.b8, n8 = this.x + this.b9; i != 0; i >>= 4, n7 += this.b5, n8 += this.b6) {
            int j = n7;
            int k = n8;
            for (int l = i & 0xF; l != 0; l >>= 1) {
                while (j < 0) {
                    j += this.a;
                }
                while (j >= this.a) {
                    j -= this.a;
                }
                while (k < 0) {
                    k += this.b;
                }
                while (k >= this.b) {
                    k -= this.b;
                }
                if ((l & 0x1) != 0x0) {
                    final int n9 = (k >>> this.bp) * super.v + (j >>> this.bp);
                    int n10;
                    if (n9 < 0 || n9 >= this.b1) {
                        n10 = array[0];
                    }
                    else {
                        n10 = array[n9];
                    }
                    n5 += (n10 & 0x7F00FF00) >> 8;
                    n6 += (n10 & 0xFF00FF);
                }
                j += this.b4;
                k += this.b7;
            }
        }
        return (n6 >> 4 & 0xFF00FF) | (n5 << 4 & 0x7F00FF00);
    }
    
    final int b(final int[] array) {
        int n = this.q;
        int n2 = this.r;
        final int n3 = (super.m << this.bm) - this.bn - 1;
        final int n4 = (super.n << this.bm) - this.bn - 1;
        if (n < this.bn) {
            n = this.bn;
        }
        if (n2 < this.bn) {
            n2 = this.bn;
        }
        if (n > n3) {
            n = n3;
        }
        if (n2 > n4) {
            n2 = n4;
        }
        final int n5 = array[(n2 >> this.bm) * super.m + (n >> this.bm)];
        if ((n5 & Integer.MIN_VALUE) != 0x0 && super.m > 4 && super.n > 4) {
            final int n6 = n - this.bn;
            final int n7 = n2 - this.bn;
            final int n8 = (n7 >> this.bm) * super.m + (n6 >> this.bm);
            final int n9 = array[n8];
            final int n10 = array[n8 + 1];
            final int n11 = array[n8 + super.m];
            final int n12 = array[n8 + super.m + 1];
            final int n13 = n6 >> this.bl & 0xFF;
            final int n14 = 256 - n13;
            final int n15 = n7 >> this.bl & 0xFF;
            final int n16 = 256 - n15;
            final int n17 = n14 * n16 >> 9;
            final int n18 = n13 * n16 >> 9;
            final int n19 = n14 * n15 >> 9;
            final int n20 = 128 - n17 - n18 - n19;
            return ((n9 & 0xFF00) * n17 + (n10 & 0xFF00) * n18 + (n11 & 0xFF00) * n19 + (n12 & 0xFF00) * n20 >> 6 & 0x1FE00) | ((n9 & 0xFF00FF) * n17 + (n10 & 0xFF00FF) * n18 + (n11 & 0xFF00FF) * n19 + (n12 & 0xFF00FF) * n20 >> 6 & 0x1FE01FE);
        }
        return n5 << 1;
    }
    
    static final int e(final int n) {
        return bg.cc[n & 0xFF] + bg.cc[n >> 8 & 0xFF];
    }
    
    static {
        cc = new int[256];
        for (int i = 0; i < 256; ++i) {
            int j = i;
            bg.cc[i] = 0;
            while (j != 0) {
                j &= j - 1;
                final int[] cc2 = bg.cc;
                final int n = i;
                ++cc2[n];
            }
        }
        bg.cl = 512;
        bg.cm = 32;
        bg.cn = 12;
        bg.co = null;
        bg.cp = bg.cm >> 1;
        bg.cq = 1 << bg.cn - 1;
        bg.co = new int[bg.cl * bg.cm];
        final float n2 = 5.6569f / bg.cl;
        final float n3 = 0.2425f;
        final float n4 = 0.0605f;
        final float n5 = -0.0605f;
        final float n6 = 0.2425f;
        final float n7 = -n3 * 1.5f - n5 * 1.5f;
        final float n8 = -n4 * 1.5f - n6 * 1.5f;
        for (int k = 0; k < bg.cl; ++k) {
            final float n9 = k * n2;
            float n10;
            float n11;
            if (k < bg.cl >> 1) {
                if (k < bg.cl >> 2) {
                    n10 = -0.707f;
                    n11 = n9 - 0.707f;
                }
                else {
                    n11 = 0.707f;
                    n10 = n9 - 2.12f;
                }
            }
            else if (k < (bg.cl >> 1) + (bg.cl >> 2)) {
                n11 = -n9 + 3.54f;
                n10 = 0.707f;
            }
            else {
                n11 = -0.707f;
                n10 = -n9 + 4.95f;
            }
            final float n12 = 1.0f / (float)Math.sqrt(n11 * n11 + n10 * n10);
            final float n13 = n11 * n12;
            final float n14 = n10 * n12;
            for (int l = 0; l < bg.cm; ++l) {
                final float n15 = (l - bg.cp) / bg.cp;
                int n16 = 1;
                float n17 = n15 + n7 * n13 + n8 * n14;
                final float n18 = n3 * n13 + n4 * n14;
                final float n19 = n5 * n13 + n6 * n14 - n18 * 4.0f;
                int n20 = 0;
                for (int n21 = 4; n21 > 0; --n21, n17 += n19) {
                    for (int n22 = 4; n22 > 0; --n22, n17 += n18) {
                        if (n17 < 0.0f) {
                            n20 |= n16;
                        }
                        n16 <<= 1;
                    }
                }
                bg.co[k * bg.cm + l] = (n20 ^ 0xFFFF);
            }
        }
    }
}
