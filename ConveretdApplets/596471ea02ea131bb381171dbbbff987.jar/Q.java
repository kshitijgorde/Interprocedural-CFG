import java.util.Arrays;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Q
{
    private U h;
    private i i;
    private i j;
    private boolean k;
    boolean a;
    private boolean l;
    private boolean m;
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
    private int z;
    private short A;
    private boolean B;
    private int[] C;
    short b;
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
    private int S;
    private int[] T;
    private int[] U;
    private int[] V;
    private int[] W;
    private boolean[] X;
    private boolean[] Y;
    private boolean[] Z;
    private int aa;
    private int ab;
    private boolean ac;
    public Z[] c;
    private int[] ad;
    private g[] ae;
    private int af;
    private int[] ag;
    private int[] ah;
    private boolean ai;
    private boolean aj;
    private int ak;
    private short al;
    private boolean am;
    private int an;
    private int ao;
    private int ap;
    private int[] aq;
    private int[] ar;
    private int[] as;
    private int[] at;
    int[] d;
    private int[] au;
    boolean[] e;
    boolean f;
    private boolean av;
    private int aw;
    private Z[] ax;
    private Z ay;
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
    private int aN;
    public int g;
    
    public Q(final U h) {
        this.k = false;
        this.a = false;
        this.l = true;
        this.m = false;
        this.w = 6;
        this.x = 7;
        this.B = true;
        this.P = 0;
        this.ad = new int[4];
        this.af = -1;
        this.ag = new int[16];
        this.ah = new int[16];
        this.aq = new int[32];
        this.ar = new int[61440];
        this.as = new int[61440];
        this.at = new int[61440];
        this.e = new boolean[240];
        this.f = false;
        this.ax = new Z[32];
        this.g = 0;
        this.h = h;
    }
    
    public final void a() {
        this.i = this.h.f;
        this.j = this.h.g;
        this.b(0);
        this.c(0);
        this.R = 0;
        this.T = new int[64];
        this.U = new int[64];
        this.V = new int[64];
        this.W = new int[64];
        this.X = new boolean[64];
        this.Y = new boolean[64];
        this.Z = new boolean[64];
        if (this.c == null) {
            this.c = new Z[512];
            for (int i = 0; i < 512; ++i) {
                this.c[i] = new Z();
            }
        }
        this.ae = new g[4];
        for (int j = 0; j < 4; ++j) {
            this.ae[j] = new g(32, 32, "Nt" + j);
        }
        this.C = new int[32768];
        for (int k = 0; k < 32768; ++k) {
            this.C[k] = k;
        }
        this.S = -1;
        this.Q = 0;
        for (int l = 0; l < this.at.length; ++l) {
            this.at[l] = -1;
        }
    }
    
    public final void a(final int af) {
        if (af == this.af) {
            return;
        }
        this.af = af;
        this.f();
        if (this.C == null) {
            this.C = new int[32768];
        }
        for (int i = 0; i < 32768; ++i) {
            this.C[i] = i;
        }
        this.a(16160, 16128, 32);
        this.a(16192, 16128, 32);
        this.a(16256, 16128, 32);
        this.a(16320, 16128, 32);
        this.a(12288, 8192, 3840);
        this.a(16384, 0, 16384);
        if (af == 1) {
            this.ad[0] = 0;
            this.ad[1] = 0;
            this.ad[2] = 1;
            this.ad[3] = 1;
            this.a(9216, 8192, 1024);
            this.a(11264, 10240, 1024);
            return;
        }
        if (af == 0) {
            this.ad[0] = 0;
            this.ad[1] = 1;
            this.ad[2] = 0;
            this.ad[3] = 1;
            this.a(10240, 8192, 1024);
            this.a(11264, 9216, 1024);
            return;
        }
        if (af == 3) {
            this.ad[0] = 0;
            this.ad[1] = 0;
            this.ad[2] = 0;
            this.ad[3] = 0;
            this.a(9216, 8192, 1024);
            this.a(10240, 8192, 1024);
            this.a(11264, 8192, 1024);
            return;
        }
        if (af == 4) {
            this.ad[0] = 1;
            this.ad[1] = 1;
            this.ad[2] = 1;
            this.ad[3] = 1;
            this.a(9216, 9216, 1024);
            this.a(10240, 9216, 1024);
            this.a(11264, 9216, 1024);
            return;
        }
        this.ad[0] = 0;
        this.ad[1] = 1;
        this.ad[2] = 2;
        this.ad[3] = 3;
    }
    
    private void a(final int n, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            this.C[n + i] = n2 + i;
        }
    }
    
    public final void b() {
        while (this.g > 0) {
            if (this.R - 21 == this.ab && this.Q == this.aa && this.r == 1) {
                this.a(this.w, true);
            }
            if (this.aj) {
                --this.ak;
                if (this.ak == 0) {
                    this.aj = false;
                    this.h.b.a(1);
                    if (this.S < 239) {
                        this.a(this.h.a.e.b(), this.S + 1, 240 - this.S);
                    }
                    final int[] b = this.h.a.c().b();
                    if (this.l || this.u == 0 || this.t == 0) {
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
                    if (this.a && this.h.d.b != null) {
                        this.aL = this.h.d.b.getBufferSize();
                        this.aM = this.h.d.b.available();
                        this.aN = this.aL / 256;
                        for (int n = 0; n < 4; ++n) {
                            this.e[n] = true;
                            for (int n2 = 0; n2 < 256; ++n2) {
                                if (n2 >= this.aM / this.aN) {
                                    b[(n << 8) + n2] = 16777215;
                                }
                                else {
                                    b[(n << 8) + n2] = 0;
                                }
                            }
                        }
                    }
                    this.h.a.c().b(false);
                    this.S = -1;
                    final int[] b2 = this.h.a.c().b();
                    int n3 = 0;
                    if (this.v == 0) {
                        n3 = this.ah[0];
                    }
                    else {
                        switch (this.q) {
                            case 0: {
                                n3 = 0;
                                break;
                            }
                            default: {
                                n3 = 0;
                                break;
                            }
                        }
                    }
                    for (int n4 = 0; n4 < b2.length; ++n4) {
                        b2[n4] = n3;
                    }
                    for (int n5 = 0; n5 < this.as.length; ++n5) {
                        this.as[n5] = 65;
                    }
                }
            }
            ++this.Q;
            if (this.Q == 341) {
                this.Q = 0;
                if (this.R >= 19) {
                    if (this.R == 19) {
                        if (this.am) {
                            this.Q = 1;
                            this.am = !this.am;
                        }
                    }
                    else if (this.R == 20) {
                        this.a(this.x, false);
                        this.a(this.w, false);
                        this.ac = false;
                        this.aa = -1;
                        this.ab = -1;
                        if (this.s == 1 || this.r == 1) {
                            this.D = this.I;
                            this.E = this.J;
                            this.F = this.K;
                            this.G = this.L;
                            this.H = this.M;
                            if (this.s == 1) {
                                this.a(this.d, 0);
                            }
                        }
                        if (this.s == 1 && this.r == 1) {
                            this.f(0);
                        }
                        if (this.s == 1 || this.r == 1) {
                            this.h.h.b();
                        }
                    }
                    else if (this.R >= 21 && this.R <= 260) {
                        if (this.s == 1) {
                            this.H = this.M;
                            this.F = this.K;
                            this.a(this.ar, this.R + 1 - 21);
                            this.ai = false;
                            if (!this.ac && this.r == 1 && this.T[0] >= -7 && this.T[0] < 256 && this.U[0] + 1 <= this.R + 1 - 21 && this.U[0] + 1 + ((this.n == 0) ? 8 : 16) >= this.R + 1 - 21 && this.f(this.R + 1 - 21)) {
                                this.ac = true;
                            }
                        }
                        if (this.s == 1 || this.r == 1) {
                            this.h.h.b();
                        }
                    }
                    else if (this.R == 261) {
                        this.a(this.x, true);
                        this.aj = true;
                        this.ak = 9;
                        this.R = -1;
                    }
                }
                ++this.R;
                this.k();
                this.l();
            }
            --this.g;
        }
    }
    
    public final void b(final int n) {
        this.f();
        this.n = (n >> 5 & 0x1);
        this.o = (n >> 3 & 0x1);
        this.p = (n >> 2 & 0x1);
        this.J = (n >> 1 & 0x1);
        this.K = (n & 0x1);
        this.O = (n >> 4 & 0x1);
    }
    
    public final void c(final int n) {
        this.f();
        this.q = (n >> 5 & 0x7);
        this.r = (n >> 4 & 0x1);
        this.s = (n >> 3 & 0x1);
        this.t = (n >> 2 & 0x1);
        this.u = (n >> 1 & 0x1);
        this.v = (n & 0x1);
        if (this.v == 0) {
            this.h.i.a(this.q);
        }
        this.m();
    }
    
    private void a(int n, final boolean b) {
        n = 1 << n;
        final int n2 = (this.h.e.a[8194] & 255 - n) | (b ? n : 0);
        final i e = this.h.e;
        final int n3 = 8194;
        n = (short)n2;
        e.a[n3] = (short)n;
    }
    
    public final short c() {
        this.al = this.h.e.a[8194];
        this.B = true;
        this.a(this.x, false);
        return this.al;
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
        if (this.B) {
            this.M = (n >> 3 & 0x1F);
            this.N = (n & 0x7);
        }
        else {
            this.I = (n & 0x7);
            this.L = (n >> 3 & 0x1F);
        }
        this.B = !this.B;
    }
    
    public final void d(final int n) {
        if (this.B) {
            this.I = (n >> 4 & 0x3);
            this.J = (n >> 3 & 0x1);
            this.K = (n >> 2 & 0x1);
            this.L = ((this.L & 0x7) | (n & 0x3) << 3);
        }
        else {
            this.f();
            this.L = ((this.L & 0x18) | (n >> 5 & 0x7));
            this.M = (n & 0x1F);
            this.D = this.I;
            this.E = this.J;
            this.F = this.K;
            this.G = this.L;
            this.H = this.M;
            this.f(this.R + 1 - 21);
        }
        this.B = !this.B;
        this.l();
        if (this.y < 8192) {
            this.h.h.b(this.y);
        }
    }
    
    public final short e() {
        this.l();
        this.k();
        if (this.y <= 16127) {
            final short a = this.A;
            if (this.y < 8192) {
                this.A = this.i.a[this.y];
            }
            else {
                this.A = this.e(this.y);
            }
            if (this.y < 8192) {
                this.h.h.b(this.y);
            }
            this.y += ((this.p == 1) ? 32 : 1);
            this.j();
            this.i();
            return a;
        }
        final short e = this.e(this.y);
        this.y += ((this.p == 1) ? 32 : 1);
        this.j();
        this.i();
        return e;
    }
    
    public final void c(final short n) {
        this.f();
        this.l();
        this.k();
        if (this.y >= 8192) {
            final int y = this.y;
            if (y >= 16128 && y < 16160) {
                if (y == 16128 || y == 16144) {
                    this.a(16128, n);
                    this.a(16144, n);
                }
                else if (y == 16132 || y == 16148) {
                    this.a(16132, n);
                    this.a(16148, n);
                }
                else if (y == 16136 || y == 16152) {
                    this.a(16136, n);
                    this.a(16152, n);
                }
                else if (y == 16140 || y == 16156) {
                    this.a(16140, n);
                    this.a(16156, n);
                }
                else {
                    this.a(y, n);
                }
            }
            else if (y < this.C.length) {
                this.a(this.C[y], n);
            }
            else {
                this.h.b.a = true;
            }
        }
        else {
            this.a(this.y, n);
            this.h.h.b(this.y);
        }
        this.y += ((this.p == 1) ? 32 : 1);
        this.i();
        this.j();
    }
    
    public final void d(final short n) {
        final i e = this.h.e;
        final int n2 = n << 8;
        for (int i = this.b; i < 256; ++i) {
            this.b(i, this.j.a[i] = e.a[n2 + i]);
        }
        this.h.b.b(513);
    }
    
    private void i() {
        this.an = (this.z >> 8 & 0xFF);
        this.I = (this.an >> 4 & 0x7);
        this.J = (this.an >> 3 & 0x1);
        this.K = (this.an >> 2 & 0x1);
        this.L = ((this.L & 0x7) | (this.an & 0x3) << 3);
        this.an = (this.z & 0xFF);
        this.L = ((this.L & 0x18) | (this.an >> 5 & 0x7));
        this.M = (this.an & 0x1F);
    }
    
    private void j() {
        this.an = (this.y >> 8 & 0xFF);
        this.D = (this.an >> 4 & 0x3);
        this.E = (this.an >> 3 & 0x1);
        this.F = (this.an >> 2 & 0x1);
        this.G = ((this.G & 0x7) | (this.an & 0x3) << 3);
        this.an = (this.y & 0xFF);
        this.G = ((this.G & 0x18) | (this.an >> 5 & 0x7));
        this.H = (this.an & 0x1F);
    }
    
    private void k() {
        this.ao = (this.I & 0x7) << 4;
        this.ao |= (this.J & 0x1) << 3;
        this.ao |= (this.K & 0x1) << 2;
        this.ao |= (this.L >> 3 & 0x3);
        this.ap = (this.L & 0x7) << 5;
        this.ap |= (this.M & 0x1F);
        this.z = ((this.ao << 8 | this.ap) & 0x7FFF);
    }
    
    private void l() {
        this.ao = (this.D & 0x7) << 4;
        this.ao |= (this.E & 0x1) << 3;
        this.ao |= (this.F & 0x1) << 2;
        this.ao |= (this.G >> 3 & 0x3);
        this.ap = (this.G & 0x7) << 5;
        this.ap |= (this.H & 0x1F);
        this.y = ((this.ao << 8 | this.ap) & 0x7FFF);
    }
    
    private short e(final int n) {
        return this.i.a[this.C[n]];
    }
    
    public final void f() {
        if (this.R >= 21 && this.R <= 260) {
            this.a(this.d, this.S + 1, this.R - 21 - this.S);
            this.S = this.R - 21;
        }
    }
    
    private void a(final int[] array, final int n, int n2) {
        if (this.r == 1) {
            this.a(n, n2, true);
        }
        if (this.s == 1) {
            this.aD = n << 8;
            this.aE = n + n2 << 8;
            if (this.aE > 61440) {
                this.aE = 61440;
            }
            this.aA = this.aD;
            while (this.aA < this.aE) {
                if (this.as[this.aA] > 255) {
                    array[this.aA] = this.ar[this.aA];
                }
                ++this.aA;
            }
        }
        if (this.r == 1) {
            this.a(n, n2, false);
        }
        final F c;
        if ((c = this.h.a.c()).c() && !c.d()) {
            if (n + n2 > 240) {
                n2 = 240 - n;
            }
            for (int i = n; i < n + n2; ++i) {
                this.e[i] = false;
                this.aD = i << 8;
                int n3;
                int j;
                for (n3 = this.aD + 256, j = this.aD; j < n3; ++j) {
                    if (array[j] != this.at[j]) {
                        this.e[i] = true;
                        break;
                    }
                    this.at[j] = array[j];
                }
                System.arraycopy(array, j, this.at, j, n3 - j);
            }
        }
        this.av = false;
    }
    
    private void a(final int[] array, final int n) {
        this.aH = ((this.O == 0) ? 0 : 256);
        this.aA = (n << 8) - this.N;
        this.az = this.ad[this.E + this.E + this.F];
        this.H = this.M;
        this.F = this.K;
        this.az = this.ad[this.E + this.E + this.F];
        if (n < 240 && n - this.D >= 0) {
            this.aI = this.D << 3;
            this.aF = 0;
            while (this.aF < 32) {
                if (n >= 0) {
                    if (this.av) {
                        this.ay = this.ax[this.aF];
                        this.au = this.ay.a;
                        this.aw = this.aq[this.aF];
                    }
                    else {
                        final Z[] c = this.c;
                        final int ah = this.aH;
                        final g g = this.ae[this.az];
                        this.ay = c[ah + g.a[this.G * g.c + this.H]];
                        this.au = this.ay.a;
                        final g g2 = this.ae[this.az];
                        this.aw = g2.b[this.G * g2.c + this.H];
                        this.ax[this.aF] = this.ay;
                        this.aq[this.aF] = this.aw;
                    }
                    this.aC = 0;
                    this.aB = (this.aF << 3) - this.N;
                    if (this.aB > -8) {
                        if (this.aB < 0) {
                            this.aA -= this.aB;
                            this.aC = -this.aB;
                        }
                        if (this.ay.b[this.D]) {
                            while (this.aC < 8) {
                                array[this.aA] = this.ah[this.au[this.aI + this.aC] + this.aw];
                                final int[] as = this.as;
                                final int aa = this.aA;
                                as[aa] |= 0x100;
                                ++this.aA;
                                ++this.aC;
                            }
                        }
                        else {
                            while (this.aC < 8) {
                                this.aG = this.au[this.aI + this.aC];
                                if (this.aG != 0) {
                                    array[this.aA] = this.ah[this.aG + this.aw];
                                    final int[] as2 = this.as;
                                    final int aa2 = this.aA;
                                    as2[aa2] |= 0x100;
                                }
                                ++this.aA;
                                ++this.aC;
                            }
                        }
                    }
                }
                ++this.H;
                if (this.H == 32) {
                    this.H = 0;
                    ++this.F;
                    this.F %= 2;
                    this.az = this.ad[(this.E << 1) + this.F];
                }
                ++this.aF;
            }
            this.av = true;
        }
        ++this.D;
        if (this.D == 8) {
            this.D = 0;
            ++this.G;
            if (this.G == 30) {
                this.G = 0;
                ++this.E;
                this.E %= 2;
                this.az = this.ad[(this.E << 1) + this.F];
            }
            else if (this.G == 32) {
                this.G = 0;
            }
            this.av = false;
        }
    }
    
    private void a(final int n, final int n2, final boolean b) {
        this.d = this.h.a.c().b();
        if (this.r == 1) {
            for (int i = 0; i < 64; ++i) {
                if (this.Z[i] == b && this.T[i] >= 0 && this.T[i] < 256 && this.U[i] + 8 >= n && this.U[i] < n + n2) {
                    if (this.n == 0) {
                        this.aJ = 0;
                        this.aK = 8;
                        if (this.U[i] < n) {
                            this.aJ = n - this.U[i] - 1;
                        }
                        if (this.U[i] + 8 > n + n2) {
                            this.aK = n + n2 - this.U[i] + 1;
                        }
                        if (this.o == 0) {
                            this.c[this.V[i]].a(0, this.aJ, 8, this.aK, this.T[i], this.U[i] + 1, this.d, this.W[i], this.ag, this.Y[i], this.X[i], i, this.as);
                        }
                        else {
                            this.c[this.V[i] + 256].a(0, this.aJ, 8, this.aK, this.T[i], this.U[i] + 1, this.d, this.W[i], this.ag, this.Y[i], this.X[i], i, this.as);
                        }
                    }
                    else {
                        int n3;
                        if (((n3 = this.V[i]) & 0x1) != 0x0) {
                            n3 = this.V[i] - 1 + 256;
                        }
                        this.aJ = 0;
                        this.aK = 8;
                        if (this.U[i] < n) {
                            this.aJ = n - this.U[i] - 1;
                        }
                        if (this.U[i] + 8 > n + n2) {
                            this.aK = n + n2 - this.U[i];
                        }
                        this.c[n3 + (this.X[i] ? 1 : 0)].a(0, this.aJ, 8, this.aK, this.T[i], this.U[i] + 1, this.d, this.W[i], this.ag, this.Y[i], this.X[i], i, this.as);
                        this.aJ = 0;
                        this.aK = 8;
                        if (this.U[i] + 8 < n) {
                            this.aJ = n - (this.U[i] + 8 + 1);
                        }
                        if (this.U[i] + 16 > n + n2) {
                            this.aK = n + n2 - (this.U[i] + 8);
                        }
                        this.c[n3 + (this.X[i] ? 0 : 1)].a(0, this.aJ, 8, this.aK, this.T[i], this.U[i] + 1 + 8, this.d, this.W[i], this.ag, this.Y[i], this.X[i], i, this.as);
                    }
                }
            }
        }
    }
    
    private boolean f(final int n) {
        this.aa = -1;
        this.ab = -1;
        final int n2 = (this.o == 0) ? 0 : 256;
        int n3 = this.T[0];
        final int n4 = this.U[0] + 1;
        if (this.n == 0) {
            if (n4 <= n && n4 + 8 > n && n3 >= -7 && n3 < 256) {
                final Z b = this.c[this.V[0] + n2];
                int n5;
                if (this.X[0]) {
                    n5 = 7 - (n - n4);
                }
                else {
                    n5 = n - n4;
                }
                final int n6 = n5 << 3;
                int n7 = (n << 8) + n3;
                if (this.Y[0]) {
                    for (int i = 7; i >= 0; --i) {
                        if (n3 >= 0 && n3 < 256 && n7 >= 0 && n7 < 61440 && this.as[n7] != 0 && b.a[n6 + i] != 0) {
                            this.aa = n7 % 256;
                            this.ab = n;
                            return true;
                        }
                        ++n3;
                        ++n7;
                    }
                }
                else {
                    for (int j = 0; j < 8; ++j) {
                        if (n3 >= 0 && n3 < 256 && n7 >= 0 && n7 < 61440 && this.as[n7] != 0 && b.a[n6 + j] != 0) {
                            this.aa = n7 % 256;
                            this.ab = n;
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
            if (this.X[0]) {
                n8 = 15 - (n - n4);
            }
            else {
                n8 = n - n4;
            }
            Z b2;
            if (n8 < 8) {
                b2 = this.c[this.V[0] + (this.X[0] ? 1 : 0) + (((this.V[0] & 0x1) != 0x0) ? 255 : 0)];
            }
            else {
                b2 = this.c[this.V[0] + (this.X[0] ? 0 : 1) + (((this.V[0] & 0x1) != 0x0) ? 255 : 0)];
                if (this.X[0]) {
                    n8 = 15 - n8;
                }
                else {
                    n8 -= 8;
                }
            }
            final int n9 = n8 << 3;
            int n10 = (n << 8) + n3;
            if (this.Y[0]) {
                for (int k = 7; k >= 0; --k) {
                    if (n3 >= 0 && n3 < 256 && n10 >= 0 && n10 < 61440 && this.as[n10] != 0 && b2.a[n9 + k] != 0) {
                        this.aa = n10 % 256;
                        this.ab = n;
                        return true;
                    }
                    ++n3;
                    ++n10;
                }
            }
            else {
                for (int l = 0; l < 8; ++l) {
                    if (n3 >= 0 && n3 < 256 && n10 >= 0 && n10 < 61440 && this.as[n10] != 0 && b2.a[n9 + l] != 0) {
                        this.aa = n10 % 256;
                        this.ab = n;
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
                this.a(this.ad[0], n - 8192, n2);
                return;
            }
            if (n >= 9152 && n < 9216) {
                this.b(this.ad[0], n - 9152, n2);
                return;
            }
            if (n >= 9216 && n < 10176) {
                this.a(this.ad[1], n - 9216, n2);
                return;
            }
            if (n >= 10176 && n < 10240) {
                this.b(this.ad[1], n - 10176, n2);
                return;
            }
            if (n >= 10240 && n < 11200) {
                this.a(this.ad[2], n - 10240, n2);
                return;
            }
            if (n >= 11200 && n < 11264) {
                this.b(this.ad[2], n - 11200, n2);
                return;
            }
            if (n >= 11264 && n < 12224) {
                this.a(this.ad[3], n - 11264, n2);
                return;
            }
            if (n >= 12224 && n < 12288) {
                this.b(this.ad[3], n - 12224, n2);
                return;
            }
            if (n >= 16128 && n < 16160) {
                this.m();
            }
        }
    }
    
    private void m() {
        for (int i = 0; i < 16; ++i) {
            if (this.v == 0) {
                this.ah[i] = p.b(this.i.a[i + 16128] & 0x3F);
            }
            else {
                this.ah[i] = p.b(this.i.a[i + 16128] & 0x20);
            }
        }
        for (int j = 0; j < 16; ++j) {
            if (this.v == 0) {
                this.ag[j] = p.b(this.i.a[j + 16144] & 0x3F);
            }
            else {
                this.ag[j] = p.b(this.i.a[j + 16144] & 0x20);
            }
        }
    }
    
    private void a(final int n, int n2, final short n3) {
        final g g = this.ae[n];
        n2 = n2;
        g.a[n2] = n3;
        this.f(this.R + 1 - 21);
    }
    
    private void b(final int n, int n2, final short n3) {
        final g g = this.ae[n];
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
            this.f(this.R + 1 - 21);
        }
        if (n % 4 == 0) {
            this.U[n3] = n2;
            return;
        }
        if (n % 4 == 1) {
            this.V[n3] = n2;
            return;
        }
        if (n % 4 == 2) {
            this.X[n3] = ((n2 & 0x80) != 0x0);
            this.Y[n3] = ((n2 & 0x40) != 0x0);
            this.Z[n3] = ((n2 & 0x20) != 0x0);
            this.W[n3] = (n2 & 0x3) << 2;
            return;
        }
        if (n % 4 == 3) {
            this.T[n3] = n2;
        }
    }
    
    public final void g() {
        this.i.a();
        this.j.a();
        this.A = 0;
        this.b = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.aa = 0;
        this.ab = 0;
        this.af = -1;
        this.B = true;
        this.aj = false;
        this.ac = false;
        this.am = false;
        this.av = false;
        this.ak = 0;
        this.al = 0;
        this.aw = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
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
        this.O = 0;
        Arrays.fill(this.e, true);
        Arrays.fill(this.at, -1);
        this.a();
    }
    
    public final void h() {
        this.h = null;
        this.i = null;
        this.j = null;
        this.ax = null;
    }
}
