import java.util.Arrays;

// 
// Decompiled by Procyon v0.5.30
// 

public final class R
{
    private V h;
    private h i;
    private h j;
    private boolean k;
    boolean a;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private short z;
    private boolean A;
    private int[] B;
    short b;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int[] S;
    private int[] T;
    private int[] U;
    private int[] V;
    private boolean[] W;
    private boolean[] X;
    private boolean[] Y;
    private int Z;
    private int aa;
    private boolean ab;
    public ab[] c;
    private int[] ac;
    private g[] ad;
    private int ae;
    private int[] af;
    private int[] ag;
    private boolean ah;
    private boolean ai;
    private int aj;
    private short ak;
    private boolean al;
    private int am;
    private int an;
    private int ao;
    private int[] ap;
    private int[] aq;
    private int[] ar;
    private int[] as;
    int[] d;
    private int[] at;
    boolean[] e;
    boolean f;
    private boolean au;
    private int av;
    private ab[] aw;
    private ab ax;
    private int ay;
    private int az;
    private int aA;
    private int aB;
    private int aC;
    private int aD;
    private int aE;
    private int aF;
    private int aG;
    private int aH;
    private int aI;
    private int aJ;
    private int aK;
    private int aL;
    private int aM;
    public int g;
    
    public R(final V h) {
        this.k = false;
        this.a = false;
        this.l = true;
        this.v = 6;
        this.w = 7;
        this.A = true;
        this.O = 0;
        this.ac = new int[4];
        this.ae = -1;
        this.af = new int[16];
        this.ag = new int[16];
        this.ap = new int[32];
        this.aq = new int[61440];
        this.ar = new int[61440];
        this.as = new int[61440];
        this.e = new boolean[240];
        this.f = false;
        this.aw = new ab[32];
        this.g = 0;
        this.h = h;
    }
    
    public final void a() {
        this.i = this.h.f;
        this.j = this.h.g;
        this.b(0);
        this.c(0);
        this.Q = 0;
        this.S = new int[64];
        this.T = new int[64];
        this.U = new int[64];
        this.V = new int[64];
        this.W = new boolean[64];
        this.X = new boolean[64];
        this.Y = new boolean[64];
        if (this.c == null) {
            this.c = new ab[512];
            for (int i = 0; i < 512; ++i) {
                this.c[i] = new ab();
            }
        }
        this.ad = new g[4];
        for (int j = 0; j < 4; ++j) {
            this.ad[j] = new g(32, 32, "Nt" + j);
        }
        this.B = new int[32768];
        for (int k = 0; k < 32768; ++k) {
            this.B[k] = k;
        }
        this.R = -1;
        this.P = 0;
        for (int l = 0; l < this.as.length; ++l) {
            this.as[l] = -1;
        }
    }
    
    public final void a(final int ae) {
        if (ae == this.ae) {
            return;
        }
        this.ae = ae;
        this.f();
        if (this.B == null) {
            this.B = new int[32768];
        }
        for (int i = 0; i < 32768; ++i) {
            this.B[i] = i;
        }
        this.a(16160, 16128, 32);
        this.a(16192, 16128, 32);
        this.a(16256, 16128, 32);
        this.a(16320, 16128, 32);
        this.a(12288, 8192, 3840);
        this.a(16384, 0, 16384);
        if (ae == 1) {
            this.ac[0] = 0;
            this.ac[1] = 0;
            this.ac[2] = 1;
            this.ac[3] = 1;
            this.a(9216, 8192, 1024);
            this.a(11264, 10240, 1024);
            return;
        }
        if (ae == 0) {
            this.ac[0] = 0;
            this.ac[1] = 1;
            this.ac[2] = 0;
            this.ac[3] = 1;
            this.a(10240, 8192, 1024);
            this.a(11264, 9216, 1024);
            return;
        }
        if (ae == 3) {
            this.ac[0] = 0;
            this.ac[1] = 0;
            this.ac[2] = 0;
            this.ac[3] = 0;
            this.a(9216, 8192, 1024);
            this.a(10240, 8192, 1024);
            this.a(11264, 8192, 1024);
            return;
        }
        if (ae == 4) {
            this.ac[0] = 1;
            this.ac[1] = 1;
            this.ac[2] = 1;
            this.ac[3] = 1;
            this.a(9216, 9216, 1024);
            this.a(10240, 9216, 1024);
            this.a(11264, 9216, 1024);
            return;
        }
        this.ac[0] = 0;
        this.ac[1] = 1;
        this.ac[2] = 2;
        this.ac[3] = 3;
    }
    
    private void a(final int n, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            this.B[n + i] = n2 + i;
        }
    }
    
    public final void b() {
        while (this.g > 0) {
            if (this.Q - 21 == this.aa && this.P == this.Z && this.q == 1) {
                this.a(this.v, true);
            }
            if (this.ai) {
                --this.aj;
                if (this.aj == 0) {
                    this.ai = false;
                    this.h.b.a(1);
                    if (this.R < 239) {
                        this.a(this.h.a.e.b(), this.R + 1, 240 - this.R);
                    }
                    final int[] b = this.h.a.c().b();
                    if (this.l || this.t == 0 || this.s == 0) {
                        for (int i = 0; i < 240; ++i) {
                            for (int j = 0; j < 8; ++j) {
                                b[(i << 8) + j] = 0;
                            }
                        }
                    }
                    if (this.l) {
                        for (int k = 0; k < 240; ++k) {
                            for (int l = 0; l < 8; ++l) {
                                b[(k << 8) + 255 - l] = 0;
                            }
                        }
                    }
                    if (this.l) {
                        for (int n = 0; n < 8; ++n) {
                            for (int n2 = 0; n2 < 256; ++n2) {
                                b[(239 - n << 8) + n2] = (b[(n << 8) + n2] = 0);
                            }
                        }
                    }
                    if (this.a && this.h.d.b != null) {
                        this.aK = this.h.d.b.getBufferSize();
                        this.aL = this.h.d.b.available();
                        this.aM = this.aK / 256;
                        for (int n3 = 0; n3 < 4; ++n3) {
                            this.e[n3] = true;
                            for (int n4 = 0; n4 < 256; ++n4) {
                                if (n4 >= this.aL / this.aM) {
                                    b[(n3 << 8) + n4] = 16777215;
                                }
                                else {
                                    b[(n3 << 8) + n4] = 0;
                                }
                            }
                        }
                    }
                    this.h.a.c().b(false);
                    this.R = -1;
                    final int[] b2 = this.h.a.c().b();
                    int n5 = 0;
                    if (this.u == 0) {
                        n5 = this.ag[0];
                    }
                    else {
                        switch (this.p) {
                            case 0: {
                                n5 = 0;
                                break;
                            }
                            default: {
                                n5 = 0;
                                break;
                            }
                        }
                    }
                    for (int n6 = 0; n6 < b2.length; ++n6) {
                        b2[n6] = n5;
                    }
                    for (int n7 = 0; n7 < this.ar.length; ++n7) {
                        this.ar[n7] = 65;
                    }
                }
            }
            ++this.P;
            if (this.P == 341) {
                this.P = 0;
                if (this.Q >= 19) {
                    if (this.Q == 19) {
                        if (this.al) {
                            this.P = 1;
                            this.al = !this.al;
                        }
                    }
                    else if (this.Q == 20) {
                        this.a(this.w, false);
                        this.a(this.v, false);
                        this.ab = false;
                        this.Z = -1;
                        this.aa = -1;
                        if (this.r == 1 || this.q == 1) {
                            this.C = this.H;
                            this.D = this.I;
                            this.E = this.J;
                            this.F = this.K;
                            this.G = this.L;
                            if (this.r == 1) {
                                this.a(this.d, 0);
                            }
                        }
                        if (this.r == 1 && this.q == 1) {
                            this.f(0);
                        }
                        if (this.r == 1 || this.q == 1) {
                            this.h.h.b();
                        }
                    }
                    else if (this.Q >= 21 && this.Q <= 260) {
                        if (this.r == 1) {
                            this.G = this.L;
                            this.E = this.J;
                            this.a(this.aq, this.Q + 1 - 21);
                            this.ah = false;
                            if (!this.ab && this.q == 1 && this.S[0] >= -7 && this.S[0] < 256 && this.T[0] + 1 <= this.Q + 1 - 21 && this.T[0] + 1 + ((this.m == 0) ? 8 : 16) >= this.Q + 1 - 21 && this.f(this.Q + 1 - 21)) {
                                this.ab = true;
                            }
                        }
                        if (this.r == 1 || this.q == 1) {
                            this.h.h.b();
                        }
                    }
                    else if (this.Q == 261) {
                        this.a(this.w, true);
                        this.ai = true;
                        this.aj = 9;
                        this.Q = -1;
                    }
                }
                ++this.Q;
                this.k();
                this.l();
            }
            --this.g;
        }
    }
    
    public final void b(final int n) {
        this.f();
        this.m = (n >> 5 & 0x1);
        this.n = (n >> 3 & 0x1);
        this.o = (n >> 2 & 0x1);
        this.I = (n >> 1 & 0x1);
        this.J = (n & 0x1);
        this.N = (n >> 4 & 0x1);
    }
    
    public final void c(final int n) {
        this.f();
        this.p = (n >> 5 & 0x7);
        this.q = (n >> 4 & 0x1);
        this.r = (n >> 3 & 0x1);
        this.s = (n >> 2 & 0x1);
        this.t = (n >> 1 & 0x1);
        this.u = (n & 0x1);
        if (this.u == 0) {
            this.h.i.a(this.p);
        }
        this.m();
    }
    
    private void a(int n, final boolean b) {
        n = 1 << n;
        final int n2 = (this.h.e.a[8194] & 255 - n) | (b ? n : 0);
        final h e = this.h.e;
        final int n3 = 8194;
        n = (short)n2;
        e.a[n3] = (short)n;
    }
    
    public final short c() {
        this.ak = this.h.e.a[8194];
        this.A = true;
        this.a(this.w, false);
        return this.ak;
    }
    
    public final short d() {
        return this.j.a[this.b];
    }
    
    public final void a(final short n) {
        this.j.a[this.b] = n;
        this.b(this.b, n);
        ++this.b;
        this.b %= 256;
    }
    
    public final void b(final short n) {
        this.f();
        if (this.A) {
            this.L = (n >> 3 & 0x1F);
            this.M = (n & 0x7);
        }
        else {
            this.H = (n & 0x7);
            this.K = (n >> 3 & 0x1F);
        }
        this.A = !this.A;
    }
    
    public final void d(final int n) {
        if (this.A) {
            this.H = (n >> 4 & 0x3);
            this.I = (n >> 3 & 0x1);
            this.J = (n >> 2 & 0x1);
            this.K = ((this.K & 0x7) | (n & 0x3) << 3);
        }
        else {
            this.f();
            this.K = ((this.K & 0x18) | (n >> 5 & 0x7));
            this.L = (n & 0x1F);
            this.C = this.H;
            this.D = this.I;
            this.E = this.J;
            this.F = this.K;
            this.G = this.L;
            this.f(this.Q + 1 - 21);
        }
        this.A = !this.A;
        this.l();
        if (this.x < 8192) {
            this.h.h.b(this.x);
        }
    }
    
    public final short e() {
        this.l();
        this.k();
        if (this.x <= 16127) {
            final short z = this.z;
            if (this.x < 8192) {
                this.z = this.i.a[this.x];
            }
            else {
                this.z = this.e(this.x);
            }
            if (this.x < 8192) {
                this.h.h.b(this.x);
            }
            this.x += ((this.o == 1) ? 32 : 1);
            this.j();
            this.i();
            return z;
        }
        final short e = this.e(this.x);
        this.x += ((this.o == 1) ? 32 : 1);
        this.j();
        this.i();
        return e;
    }
    
    public final void c(final short n) {
        this.f();
        this.l();
        this.k();
        if (this.x >= 8192) {
            final int x = this.x;
            if (x >= 16128 && x < 16160) {
                if (x == 16128 || x == 16144) {
                    this.a(16128, n);
                    this.a(16144, n);
                }
                else if (x == 16132 || x == 16148) {
                    this.a(16132, n);
                    this.a(16148, n);
                }
                else if (x == 16136 || x == 16152) {
                    this.a(16136, n);
                    this.a(16152, n);
                }
                else if (x == 16140 || x == 16156) {
                    this.a(16140, n);
                    this.a(16156, n);
                }
                else {
                    this.a(x, n);
                }
            }
            else if (x < this.B.length) {
                this.a(this.B[x], n);
            }
            else {
                this.h.b.a = true;
            }
        }
        else {
            this.a(this.x, n);
            this.h.h.b(this.x);
        }
        this.x += ((this.o == 1) ? 32 : 1);
        this.i();
        this.j();
    }
    
    public final void d(final short n) {
        final h e = this.h.e;
        final int n2 = n << 8;
        for (int i = this.b; i < 256; ++i) {
            this.b(i, this.j.a[i] = e.a[n2 + i]);
        }
        this.h.b.b(513);
    }
    
    private void i() {
        this.am = (this.y >> 8 & 0xFF);
        this.H = (this.am >> 4 & 0x7);
        this.I = (this.am >> 3 & 0x1);
        this.J = (this.am >> 2 & 0x1);
        this.K = ((this.K & 0x7) | (this.am & 0x3) << 3);
        this.am = (this.y & 0xFF);
        this.K = ((this.K & 0x18) | (this.am >> 5 & 0x7));
        this.L = (this.am & 0x1F);
    }
    
    private void j() {
        this.am = (this.x >> 8 & 0xFF);
        this.C = (this.am >> 4 & 0x3);
        this.D = (this.am >> 3 & 0x1);
        this.E = (this.am >> 2 & 0x1);
        this.F = ((this.F & 0x7) | (this.am & 0x3) << 3);
        this.am = (this.x & 0xFF);
        this.F = ((this.F & 0x18) | (this.am >> 5 & 0x7));
        this.G = (this.am & 0x1F);
    }
    
    private void k() {
        this.an = (this.H & 0x7) << 4;
        this.an |= (this.I & 0x1) << 3;
        this.an |= (this.J & 0x1) << 2;
        this.an |= (this.K >> 3 & 0x3);
        this.ao = (this.K & 0x7) << 5;
        this.ao |= (this.L & 0x1F);
        this.y = ((this.an << 8 | this.ao) & 0x7FFF);
    }
    
    private void l() {
        this.an = (this.C & 0x7) << 4;
        this.an |= (this.D & 0x1) << 3;
        this.an |= (this.E & 0x1) << 2;
        this.an |= (this.F >> 3 & 0x3);
        this.ao = (this.F & 0x7) << 5;
        this.ao |= (this.G & 0x1F);
        this.x = ((this.an << 8 | this.ao) & 0x7FFF);
    }
    
    private short e(final int n) {
        return this.i.a[this.B[n]];
    }
    
    public final void f() {
        if (this.Q >= 21 && this.Q <= 260) {
            this.a(this.d, this.R + 1, this.Q - 21 - this.R);
            this.R = this.Q - 21;
        }
    }
    
    private void a(final int[] array, final int n, int n2) {
        if (this.q == 1) {
            this.a(n, n2, true);
        }
        if (this.r == 1) {
            this.aC = n << 8;
            this.aD = n + n2 << 8;
            if (this.aD > 61440) {
                this.aD = 61440;
            }
            this.az = this.aC;
            while (this.az < this.aD) {
                if (this.ar[this.az] > 255) {
                    array[this.az] = this.aq[this.az];
                }
                ++this.az;
            }
        }
        if (this.q == 1) {
            this.a(n, n2, false);
        }
        final G c;
        if ((c = this.h.a.c()).c() && !c.d()) {
            if (n + n2 > 240) {
                n2 = 240 - n;
            }
            for (int i = n; i < n + n2; ++i) {
                this.e[i] = false;
                this.aC = i << 8;
                int n3;
                int j;
                for (n3 = this.aC + 256, j = this.aC; j < n3; ++j) {
                    if (array[j] != this.as[j]) {
                        this.e[i] = true;
                        break;
                    }
                    this.as[j] = array[j];
                }
                System.arraycopy(array, j, this.as, j, n3 - j);
            }
        }
        this.au = false;
    }
    
    private void a(final int[] array, final int n) {
        this.aG = ((this.N == 0) ? 0 : 256);
        this.az = (n << 8) - this.M;
        this.ay = this.ac[this.D + this.D + this.E];
        this.G = this.L;
        this.E = this.J;
        this.ay = this.ac[this.D + this.D + this.E];
        if (n < 240 && n - this.C >= 0) {
            this.aH = this.C << 3;
            this.aE = 0;
            while (this.aE < 32) {
                if (n >= 0) {
                    if (this.au) {
                        this.ax = this.aw[this.aE];
                        this.at = this.ax.a;
                        this.av = this.ap[this.aE];
                    }
                    else {
                        final ab[] c = this.c;
                        final int ag = this.aG;
                        final g g = this.ad[this.ay];
                        this.ax = c[ag + g.a[this.F * g.c + this.G]];
                        this.at = this.ax.a;
                        final g g2 = this.ad[this.ay];
                        this.av = g2.b[this.F * g2.c + this.G];
                        this.aw[this.aE] = this.ax;
                        this.ap[this.aE] = this.av;
                    }
                    this.aB = 0;
                    this.aA = (this.aE << 3) - this.M;
                    if (this.aA > -8) {
                        if (this.aA < 0) {
                            this.az -= this.aA;
                            this.aB = -this.aA;
                        }
                        if (this.ax.b[this.C]) {
                            while (this.aB < 8) {
                                array[this.az] = this.ag[this.at[this.aH + this.aB] + this.av];
                                final int[] ar = this.ar;
                                final int az = this.az;
                                ar[az] |= 0x100;
                                ++this.az;
                                ++this.aB;
                            }
                        }
                        else {
                            while (this.aB < 8) {
                                this.aF = this.at[this.aH + this.aB];
                                if (this.aF != 0) {
                                    array[this.az] = this.ag[this.aF + this.av];
                                    final int[] ar2 = this.ar;
                                    final int az2 = this.az;
                                    ar2[az2] |= 0x100;
                                }
                                ++this.az;
                                ++this.aB;
                            }
                        }
                    }
                }
                ++this.G;
                if (this.G == 32) {
                    this.G = 0;
                    ++this.E;
                    this.E %= 2;
                    this.ay = this.ac[(this.D << 1) + this.E];
                }
                ++this.aE;
            }
            this.au = true;
        }
        ++this.C;
        if (this.C == 8) {
            this.C = 0;
            ++this.F;
            if (this.F == 30) {
                this.F = 0;
                ++this.D;
                this.D %= 2;
                this.ay = this.ac[(this.D << 1) + this.E];
            }
            else if (this.F == 32) {
                this.F = 0;
            }
            this.au = false;
        }
    }
    
    private void a(final int n, final int n2, final boolean b) {
        this.d = this.h.a.c().b();
        if (this.q == 1) {
            for (int i = 0; i < 64; ++i) {
                if (this.Y[i] == b && this.S[i] >= 0 && this.S[i] < 256 && this.T[i] + 8 >= n && this.T[i] < n + n2) {
                    if (this.m == 0) {
                        this.aI = 0;
                        this.aJ = 8;
                        if (this.T[i] < n) {
                            this.aI = n - this.T[i] - 1;
                        }
                        if (this.T[i] + 8 > n + n2) {
                            this.aJ = n + n2 - this.T[i] + 1;
                        }
                        if (this.n == 0) {
                            this.c[this.U[i]].a(0, this.aI, 8, this.aJ, this.S[i], this.T[i] + 1, this.d, this.V[i], this.af, this.X[i], this.W[i], i, this.ar);
                        }
                        else {
                            this.c[this.U[i] + 256].a(0, this.aI, 8, this.aJ, this.S[i], this.T[i] + 1, this.d, this.V[i], this.af, this.X[i], this.W[i], i, this.ar);
                        }
                    }
                    else {
                        int n3;
                        if (((n3 = this.U[i]) & 0x1) != 0x0) {
                            n3 = this.U[i] - 1 + 256;
                        }
                        this.aI = 0;
                        this.aJ = 8;
                        if (this.T[i] < n) {
                            this.aI = n - this.T[i] - 1;
                        }
                        if (this.T[i] + 8 > n + n2) {
                            this.aJ = n + n2 - this.T[i];
                        }
                        this.c[n3 + (this.W[i] ? 1 : 0)].a(0, this.aI, 8, this.aJ, this.S[i], this.T[i] + 1, this.d, this.V[i], this.af, this.X[i], this.W[i], i, this.ar);
                        this.aI = 0;
                        this.aJ = 8;
                        if (this.T[i] + 8 < n) {
                            this.aI = n - (this.T[i] + 8 + 1);
                        }
                        if (this.T[i] + 16 > n + n2) {
                            this.aJ = n + n2 - (this.T[i] + 8);
                        }
                        this.c[n3 + (this.W[i] ? 0 : 1)].a(0, this.aI, 8, this.aJ, this.S[i], this.T[i] + 1 + 8, this.d, this.V[i], this.af, this.X[i], this.W[i], i, this.ar);
                    }
                }
            }
        }
    }
    
    private boolean f(final int n) {
        this.Z = -1;
        this.aa = -1;
        final int n2 = (this.n == 0) ? 0 : 256;
        int n3 = this.S[0];
        final int n4 = this.T[0] + 1;
        if (this.m == 0) {
            if (n4 <= n && n4 + 8 > n && n3 >= -7 && n3 < 256) {
                final ab ab = this.c[this.U[0] + n2];
                int n5;
                if (this.W[0]) {
                    n5 = 7 - (n - n4);
                }
                else {
                    n5 = n - n4;
                }
                final int n6 = n5 << 3;
                int n7 = (n << 8) + n3;
                if (this.X[0]) {
                    for (int i = 7; i >= 0; --i) {
                        if (n3 >= 0 && n3 < 256 && n7 >= 0 && n7 < 61440 && this.ar[n7] != 0 && ab.a[n6 + i] != 0) {
                            this.Z = n7 % 256;
                            this.aa = n;
                            return true;
                        }
                        ++n3;
                        ++n7;
                    }
                }
                else {
                    for (int j = 0; j < 8; ++j) {
                        if (n3 >= 0 && n3 < 256 && n7 >= 0 && n7 < 61440 && this.ar[n7] != 0 && ab.a[n6 + j] != 0) {
                            this.Z = n7 % 256;
                            this.aa = n;
                            return true;
                        }
                        ++n3;
                        ++n7;
                    }
                }
            }
        }
        else if (n4 <= n && n4 + 16 > n && n3 >= -7 && n3 < 256) {
            int n8;
            if (this.W[0]) {
                n8 = 15 - (n - n4);
            }
            else {
                n8 = n - n4;
            }
            ab ab2;
            if (n8 < 8) {
                ab2 = this.c[this.U[0] + (this.W[0] ? 1 : 0) + (((this.U[0] & 0x1) != 0x0) ? 255 : 0)];
            }
            else {
                ab2 = this.c[this.U[0] + (this.W[0] ? 0 : 1) + (((this.U[0] & 0x1) != 0x0) ? 255 : 0)];
                if (this.W[0]) {
                    n8 = 15 - n8;
                }
                else {
                    n8 -= 8;
                }
            }
            final int n9 = n8 << 3;
            int n10 = (n << 8) + n3;
            if (this.X[0]) {
                for (int k = 7; k >= 0; --k) {
                    if (n3 >= 0 && n3 < 256 && n10 >= 0 && n10 < 61440 && this.ar[n10] != 0 && ab2.a[n9 + k] != 0) {
                        this.Z = n10 % 256;
                        this.aa = n;
                        return true;
                    }
                    ++n3;
                    ++n10;
                }
            }
            else {
                for (int l = 0; l < 8; ++l) {
                    if (n3 >= 0 && n3 < 256 && n10 >= 0 && n10 < 61440 && this.ar[n10] != 0 && ab2.a[n9 + l] != 0) {
                        this.Z = n10 % 256;
                        this.aa = n;
                        return true;
                    }
                    ++n3;
                    ++n10;
                }
            }
        }
        return false;
    }
    
    private void a(final int n, final short n2) {
        this.i.a[n] = n2;
        if (n < 8192) {
            this.i.a[n] = n2;
            final int n3 = n / 16;
            final int n4;
            if ((n4 = n % 16) >= 8) {
                this.c[n3].a(n4 - 8, this.i.a[n - 8], n2);
                return;
            }
            this.c[n3].a(n4, n2, this.i.a[n + 8]);
        }
        else {
            if (n >= 8192 && n < 9152) {
                this.a(this.ac[0], n - 8192, n2);
                return;
            }
            if (n >= 9152 && n < 9216) {
                this.b(this.ac[0], n - 9152, n2);
                return;
            }
            if (n >= 9216 && n < 10176) {
                this.a(this.ac[1], n - 9216, n2);
                return;
            }
            if (n >= 10176 && n < 10240) {
                this.b(this.ac[1], n - 10176, n2);
                return;
            }
            if (n >= 10240 && n < 11200) {
                this.a(this.ac[2], n - 10240, n2);
                return;
            }
            if (n >= 11200 && n < 11264) {
                this.b(this.ac[2], n - 11200, n2);
                return;
            }
            if (n >= 11264 && n < 12224) {
                this.a(this.ac[3], n - 11264, n2);
                return;
            }
            if (n >= 12224 && n < 12288) {
                this.b(this.ac[3], n - 12224, n2);
                return;
            }
            if (n >= 16128 && n < 16160) {
                this.m();
            }
        }
    }
    
    private void m() {
        for (int i = 0; i < 16; ++i) {
            if (this.u == 0) {
                this.ag[i] = p.b(this.i.a[i + 16128] & 0x3F);
            }
            else {
                this.ag[i] = p.b(this.i.a[i + 16128] & 0x20);
            }
        }
        for (int j = 0; j < 16; ++j) {
            if (this.u == 0) {
                this.af[j] = p.b(this.i.a[j + 16144] & 0x3F);
            }
            else {
                this.af[j] = p.b(this.i.a[j + 16144] & 0x20);
            }
        }
    }
    
    private void a(final int n, int n2, final short n3) {
        final g g = this.ad[n];
        n2 = n2;
        g.a[n2] = n3;
        this.f(this.Q + 1 - 21);
    }
    
    private void b(final int n, int n2, final short n3) {
        final g g = this.ad[n];
        n2 = n2;
        final g g2 = g;
        final int n4 = n2 % 8;
        n2 /= 8;
        final int n5 = n4 << 2;
        n2 <<= 2;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                final int n6 = n3 >> 2 * ((i << 1) + j) & 0x3;
                for (int k = 0; k < 2; ++k) {
                    for (int l = 0; l < 2; ++l) {
                        g2.b[(n2 + (i << 1) + k) * g2.c + (n5 + (j << 1) + l)] = (short)(n6 << 2 & 0xC);
                    }
                }
            }
        }
    }
    
    private void b(final int n, final short n2) {
        final int n3;
        if ((n3 = n / 4) == 0) {
            this.f(this.Q + 1 - 21);
        }
        if (n % 4 == 0) {
            this.T[n3] = n2;
            return;
        }
        if (n % 4 == 1) {
            this.U[n3] = n2;
            return;
        }
        if (n % 4 == 2) {
            this.W[n3] = ((n2 & 0x80) != 0x0);
            this.X[n3] = ((n2 & 0x40) != 0x0);
            this.Y[n3] = ((n2 & 0x20) != 0x0);
            this.V[n3] = (n2 & 0x3) << 2;
            return;
        }
        if (n % 4 == 3) {
            this.S[n3] = n2;
        }
    }
    
    public final void g() {
        this.i.a();
        this.j.a();
        this.z = 0;
        this.b = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.Z = 0;
        this.aa = 0;
        this.ae = -1;
        this.A = true;
        this.ai = false;
        this.ab = false;
        this.al = false;
        this.au = false;
        this.aj = 0;
        this.ak = 0;
        this.av = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        Arrays.fill(this.e, true);
        Arrays.fill(this.as, -1);
        this.a();
    }
    
    public final void h() {
        this.h = null;
        this.i = null;
        this.j = null;
        this.aw = null;
    }
}
