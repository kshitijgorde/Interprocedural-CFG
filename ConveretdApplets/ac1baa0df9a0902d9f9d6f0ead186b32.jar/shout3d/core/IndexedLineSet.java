// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class IndexedLineSet extends Geometry implements FieldObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final int[] defaultColorIndex;
    public final int[] defaultCoordIndex;
    public final NodeField color;
    public final IntArrayField colorIndex;
    public final BooleanField colorPerVertex;
    public final NodeField coord;
    public final IntArrayField coordIndex;
    public final FloatField lineWidth;
    private FloatArrayField g;
    float[] l;
    float[] h;
    int[] i;
    float n;
    float F;
    float G;
    int[] j;
    int[] k;
    float[] m;
    int t;
    int u;
    protected FloatArrayField o;
    protected FloatArrayField p;
    protected static final int q = 1;
    protected static final int r = 2;
    protected static final int s = 4;
    protected static final int v = 8;
    protected static final int w = 16;
    protected static final int x = 32;
    protected static final int y = 64;
    protected static final int z = 128;
    protected static final int A = 256;
    protected static final int B = 512;
    protected static final int C = 1024;
    protected static final int D = 2048;
    protected int E;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    int be;
    int[] L;
    float[] M;
    float[] N;
    boolean O;
    boolean P;
    int bf;
    int Q;
    int R;
    float S;
    float T;
    float U;
    float V;
    float W;
    int X;
    int Y;
    int Z;
    int ba;
    int bb;
    int bc;
    int bd;
    int bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    float br;
    int bs;
    int bt;
    int bu;
    int bv;
    float bw;
    float bx;
    float by;
    float bz;
    int bA;
    int bB;
    float bC;
    float bD;
    float bE;
    float bF;
    int bG;
    float[] bH;
    float[] bI;
    float bJ;
    float bK;
    float bL;
    float bM;
    float bN;
    float bO;
    float bP;
    float bQ;
    float bR;
    float bS;
    float bT;
    float bU;
    float bV;
    float bW;
    float bX;
    float bY;
    float bZ;
    float ca;
    float cb;
    float cc;
    float cd;
    float ce;
    boolean cf;
    boolean cg;
    boolean ch;
    boolean ci;
    float cj;
    float ck;
    int cl;
    boolean cm;
    int cn;
    int co;
    int cp;
    int cq;
    float cr;
    float cs;
    int ct;
    int cu;
    int cv;
    float cw;
    float[] cx;
    float[] cy;
    float[] cz;
    float[] cA;
    float cB;
    float cC;
    float cD;
    float cE;
    float cF;
    float cG;
    float cH;
    float cI;
    int cJ;
    float cK;
    float cL;
    float cM;
    int cN;
    int cO;
    int cP;
    float cQ;
    float cR;
    float cS;
    float cT;
    int cU;
    int cV;
    int cW;
    int cX;
    int cY;
    int cZ;
    int da;
    int db;
    int dc;
    float dd;
    float de;
    float df;
    float dg;
    float dh;
    float di;
    float dj;
    float dk;
    float dl;
    float dm;
    float dn;
    int do;
    int dp;
    int dq;
    int dr;
    int ds;
    int dt;
    int du;
    int dv;
    int dw;
    int dx;
    int dy;
    int dz;
    int dA;
    int dB;
    int dC;
    float dD;
    float dE;
    private int dF;
    private int dG;
    private int dH;
    private int dI;
    
    void d(final g g) {
        this.S = g.bC;
        if (this.l != null) {
            this.bt = 0;
            while (this.bt < this.l.length) {
                this.W = this.S / this.l[this.bt + 2];
                this.h[this.bt] = this.l[this.bt] * this.W + g.bv;
                this.h[this.bt + 1] = this.l[this.bt + 1] * this.W + g.bw;
                this.h[this.bt + 2] = this.l[this.bt + 2];
                this.bt += 3;
            }
            if (this.H) {
                this.bt = 0;
                while (this.bt < this.l.length) {
                    this.i[this.bt] = 0;
                    this.bC = g.bg * this.l[this.bt];
                    this.bD = g.bi * this.l[this.bt + 2];
                    this.bE = g.bh * this.l[this.bt + 1];
                    this.bF = g.bj * this.l[this.bt + 2];
                    if (this.bC - this.bD < 0.0f) {
                        final int[] i = this.i;
                        final int bt = this.bt;
                        ++i[bt];
                    }
                    if (this.bE - this.bF < 0.0f) {
                        final int[] j = this.i;
                        final int bt2 = this.bt;
                        j[bt2] += 2;
                    }
                    if (this.l[this.bt + 2] > -0.1f) {
                        final int[] k = this.i;
                        final int bt3 = this.bt;
                        k[bt3] += 4;
                    }
                    if (-this.bC - this.bD < 0.0f) {
                        final int[] l = this.i;
                        final int bt4 = this.bt;
                        l[bt4] += 8;
                    }
                    if (-this.bE - this.bF < 0.0f) {
                        final int[] m = this.i;
                        final int bt5 = this.bt;
                        m[bt5] += 16;
                    }
                    this.bt += 3;
                }
            }
        }
        int n = 0;
        if (this.j != null) {
            this.bt = 0;
            while (this.bt < this.j.length) {
                final int bt6 = this.bt;
                while (this.bt + 1 < this.j.length) {
                    if (this.j[this.bt + 1] == -2) {
                        this.a(this.bt, bt6, this.j[this.bt], this.j[bt6], n);
                        break;
                    }
                    if (this.j[this.bt + 1] == -1) {
                        break;
                    }
                    this.a(this.bt, this.bt + 1, this.j[this.bt], this.j[this.bt + 1], n);
                    ++this.bt;
                }
                this.bt += 2;
                ++n;
            }
        }
    }
    
    void b() {
        this.do += 1 - super.d.bm * this.ds;
        ++this.dp;
        --this.du;
        this.cM += this.dd;
        this.dr += this.ds;
        if (this.I) {
            this.df += this.dg;
            this.di += this.dj;
            this.dl += this.dm;
        }
        this.dv += this.dw;
        if (this.dv >= this.dx) {
            this.do -= super.d.bm;
            this.dv -= this.dx;
            this.cM += this.de;
            if (this.I) {
                this.df += this.dh;
                this.di += this.dk;
                this.dl += this.dn;
            }
        }
    }
    
    void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.P = true;
        this.X = 0;
        this.O = false;
        if (this.H && (this.i[n3] != this.i[n4] || this.i[n3] <= 0)) {
            this.O = true;
        }
        if (!this.H || (this.H && this.O)) {
            if (this.H) {
                this.bA = this.i[n3];
                this.bB = this.i[n4];
            }
            this.L[0] = (int)this.h[n3];
            this.L[1] = (int)this.h[n3 + 1];
            this.L[3] = (int)this.h[n4];
            this.L[4] = (int)this.h[n4 + 1];
            this.M[0] = this.l[n3 + 2];
            this.M[1] = this.l[n4 + 2];
            if (this.I) {
                if (this.K) {
                    this.N[0] = this.m[this.k[n]];
                    this.N[1] = this.m[this.k[n] + 1];
                    this.N[2] = this.m[this.k[n] + 2];
                    this.N[3] = this.m[this.k[n2]];
                    this.N[4] = this.m[this.k[n2] + 1];
                    this.N[5] = this.m[this.k[n2] + 2];
                }
                else {
                    this.N[0] = this.m[this.j[n]];
                    this.N[1] = this.m[this.j[n] + 1];
                    this.N[2] = this.m[this.j[n] + 2];
                    this.N[3] = this.m[this.j[n2]];
                    this.N[4] = this.m[this.j[n2] + 1];
                    this.N[5] = this.m[this.j[n2] + 2];
                }
            }
            else if (this.J) {
                if (this.K) {
                    this.cn = (int)this.m[this.k[n5]];
                    this.co = (int)this.m[this.k[n5] + 1];
                    this.cp = (int)this.m[this.k[n5] + 2];
                    this.be = -16777216 + (this.cn << 16) + (this.co << 8) + this.cp;
                }
                else {
                    final int n6 = n5 * 3;
                    this.cn = (int)this.m[n6];
                    this.co = (int)this.m[n6 + 1];
                    this.cp = (int)this.m[n6 + 2];
                    this.be = -16777216 + (this.cn << 16) + (this.co << 8) + this.cp;
                }
            }
            this.P = !this.H;
            if (this.H && this.bA == 0 && this.bB == 0) {
                this.P = true;
            }
            if (this.P) {
                this.c();
                return;
            }
            this.bG = 6;
            this.bH[0] = this.l[n3];
            this.bH[1] = this.l[n3 + 1];
            this.bH[2] = this.l[n3 + 2];
            this.bH[3] = this.l[n4];
            this.bH[4] = this.l[n4 + 1];
            this.bH[5] = this.l[n4 + 2];
            if (this.I) {
                this.bI[0] = this.N[0];
                this.bI[1] = this.N[1];
                this.bI[2] = this.N[2];
                this.bI[3] = this.N[3];
                this.bI[4] = this.N[4];
                this.bI[5] = this.N[5];
            }
            if ((this.bA & 0x1) > 0 || (this.bB & 0x1) > 0) {
                this.a(super.d, 1);
            }
            if ((this.bA & 0x4) > 0 || (this.bB & 0x4) > 0) {
                this.a(super.d, 4);
            }
            if ((this.bA & 0x2) > 0 || (this.bB & 0x2) > 0) {
                this.a(super.d, 2);
            }
            if ((this.bA & 0x8) > 0 || (this.bB & 0x8) > 0) {
                this.a(super.d, 8);
            }
            if ((this.bA & 0x10) > 0 || (this.bB & 0x10) > 0) {
                this.a(super.d, 16);
            }
            for (int i = 0; i < this.bG; i += 3) {
                this.W = this.S / this.bH[i + 2];
                this.bH[i] = this.bH[i] * this.W + super.d.bv;
                this.bH[i + 1] = this.bH[i + 1] * this.W + super.d.bw;
            }
            if (this.bG >= 6) {
                this.L[0] = (int)this.bH[0];
                this.L[1] = (int)this.bH[1];
                this.M[0] = this.bH[2];
                if (this.I) {
                    this.N[0] = this.bI[0];
                    this.N[1] = this.bI[1];
                    this.N[2] = this.bI[2];
                }
                this.L[3] = (int)this.bH[3];
                this.L[4] = (int)this.bH[4];
                this.M[1] = this.bH[5];
                if (this.I) {
                    this.N[3] = this.bI[3];
                    this.N[4] = this.bI[4];
                    this.N[5] = this.bI[5];
                }
                this.c();
            }
        }
    }
    
    void c() {
        if (this.L[0] - this.u < super.d.S) {
            super.d.S = this.L[0] - this.u;
        }
        if (this.L[3] - this.u < super.d.S) {
            super.d.S = this.L[3] - this.u;
        }
        if (this.L[0] + this.u > super.d.T) {
            super.d.T = this.L[0] + this.u;
        }
        if (this.L[3] + this.u > super.d.T) {
            super.d.T = this.L[3] + this.u;
        }
        if (this.L[1] - this.u < super.d.U) {
            super.d.U = this.L[1] - this.u;
        }
        if (this.L[4] - this.u < super.d.U) {
            super.d.U = this.L[4] - this.u;
        }
        if (this.L[1] + this.u > super.d.V) {
            super.d.V = this.L[1] + this.u;
        }
        if (this.L[4] + this.u > super.d.V) {
            super.d.V = this.L[4] + this.u;
        }
        this.cQ = this.L[3] - this.L[0];
        this.cR = this.L[4] - this.L[1];
        this.cj = ((this.cQ > 0.0f) ? this.cQ : (-this.cQ));
        this.ck = ((this.cR > 0.0f) ? this.cR : (-this.cR));
        if (this.cj < this.ck) {
            this.a(this.cm = true);
            if (this.L[1] < this.L[4]) {
                this.dB = 0;
                this.dC = 3;
            }
            else {
                this.dB = 3;
                this.dC = 0;
            }
            this.g();
            while (this.dt > 0) {
                if (this.R == this.bf) {
                    this.i();
                }
                else {
                    this.h();
                }
                this.d();
            }
            return;
        }
        this.a(false);
        this.cm = false;
        if (this.L[0] < this.L[3]) {
            this.dB = 0;
            this.dC = 3;
        }
        else {
            this.dB = 3;
            this.dC = 0;
        }
        this.e();
        while (this.du > 0) {
            if (this.R == this.bf) {
                this.i();
            }
            else {
                this.h();
            }
            this.b();
        }
    }
    
    public IndexedLineSet() {
        this.defaultColorIndex = new int[0];
        this.defaultCoordIndex = new int[0];
        this.color = new NodeField(this, "color", 3, null);
        this.colorIndex = new IntArrayField(this, "colorIndex", 11, this.defaultColorIndex);
        this.colorPerVertex = new BooleanField(this, "colorPerVertex", 0, true);
        this.coord = new NodeField(this, "coord", 4, null);
        this.coordIndex = new IntArrayField(this, "coordIndex", 11, this.defaultCoordIndex);
        this.lineWidth = new FloatField(this, "lineWidth", 15, 1.0f);
        this.t = 0;
        this.u = 0;
        this.o = null;
        this.p = null;
        this.E = -1;
        this.L = new int[9];
        this.M = new float[2];
        this.N = new float[6];
        this.bf = 0;
        this.Q = 2;
        this.R = this.bf;
        this.X = 0;
        this.Y = 0;
        this.bH = new float[6];
        this.bI = new float[6];
        this.bJ = 0.0f;
        this.bK = 0.0f;
        this.bL = 0.0f;
        this.bM = 0.0f;
        this.bN = 0.0f;
        this.bO = 0.0f;
        this.bP = 0.0f;
        this.bQ = 0.0f;
        this.bR = 0.0f;
        this.bS = 0.0f;
        this.bT = 0.0f;
        this.bU = 0.0f;
        this.cm = false;
        this.cx = new float[2];
        this.cy = new float[2];
        this.cz = new float[2];
        this.cA = new float[2];
        this.cN = 32768;
        this.cO = this.cN;
        this.cP = this.cN - 1;
        this.dF = -1;
        this.dG = -1;
        this.dH = -1;
        this.dI = -1;
        this.color.addFieldObserver(this, new Integer(1));
        this.colorIndex.addFieldObserver(this, new Integer(8));
        this.coord.addFieldObserver(this, new Integer(32));
        this.coordIndex.addFieldObserver(this, new Integer(256));
        this.lineWidth.addFieldObserver(this, new Integer(1024));
    }
    
    void a(final g g, final int n) {
        if (this.bG < 6) {
            return;
        }
        this.bJ = this.bH[0];
        this.bK = this.bH[1];
        this.bL = this.bH[2];
        if (this.I) {
            this.bM = this.bI[0];
            this.bN = this.bI[1];
            this.bO = this.bI[2];
        }
        if (n == 1) {
            this.cd = g.bg * this.bJ - g.bi * this.bL;
        }
        else if (n == 2) {
            this.cd = g.bh * this.bK - g.bj * this.bL;
        }
        else if (n == 4) {
            this.cd = -0.1f - this.bL;
        }
        else if (n == 8) {
            this.cd = -g.bg * this.bJ - g.bi * this.bL;
        }
        else if (n == 16) {
            this.cd = -g.bh * this.bK - g.bj * this.bL;
        }
        this.ch = (this.cd >= 0.0f);
        this.bP = this.bH[3];
        this.bQ = this.bH[4];
        this.bR = this.bH[5];
        if (this.I) {
            this.bS = this.bI[3];
            this.bT = this.bI[4];
            this.bU = this.bI[5];
        }
        if (n == 1) {
            this.ce = g.bg * this.bP - g.bi * this.bR;
        }
        else if (n == 2) {
            this.ce = g.bh * this.bQ - g.bj * this.bR;
        }
        else if (n == 4) {
            this.ce = -0.1f - this.bR;
        }
        else if (n == 8) {
            this.ce = -g.bg * this.bP - g.bi * this.bR;
        }
        else if (n == 16) {
            this.ce = -g.bh * this.bQ - g.bj * this.bR;
        }
        this.ci = (this.ce >= 0.0f);
        this.cg = (this.ch && !this.ci);
        this.cf = (!this.ch && this.ci);
        if (this.cg || this.cf) {
            this.bY = this.bP - this.bJ;
            this.bZ = this.bQ - this.bK;
            this.ca = this.bR - this.bL;
            if (n == 1) {
                this.bW = g.bg * this.bY - g.bi * this.ca;
            }
            else if (n == 2) {
                this.bW = g.bh * this.bZ - g.bj * this.ca;
            }
            else if (n == 4) {
                this.bW = -this.ca;
            }
            else if (n == 8) {
                this.bW = -g.bg * this.bY - g.bi * this.ca;
            }
            else if (n == 16) {
                this.bW = -g.bh * this.bZ - g.bj * this.ca;
            }
            this.bX = -(this.cd / this.bW);
        }
        if (this.cg) {
            this.bH[3] = this.bJ + this.bX * this.bY;
            this.bH[4] = this.bK + this.bX * this.bZ;
            this.bH[5] = this.bL + this.bX * this.ca;
            if (this.I) {
                this.bI[3] = this.bM + this.bX * (this.bS - this.bM);
                this.bI[4] = this.bN + this.bX * (this.bT - this.bN);
                this.bI[5] = this.bO + this.bX * (this.bU - this.bO);
            }
        }
        else if (this.cf) {
            this.bH[0] = this.bJ + this.bX * this.bY;
            this.bH[1] = this.bK + this.bX * this.bZ;
            this.bH[2] = this.bL + this.bX * this.ca;
            if (this.I) {
                this.bI[0] = this.bM + this.bX * (this.bS - this.bM);
                this.bI[1] = this.bN + this.bX * (this.bT - this.bN);
                this.bI[2] = this.bO + this.bX * (this.bU - this.bO);
            }
        }
        else if ((!this.ch || !this.ci) && !this.ch && !this.ci) {
            this.bG = 0;
        }
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (o == null) {
            return;
        }
        this.E |= (int)o;
        switch ((int)o) {
            case 1: {
                this.g = null;
                if (this.color.a != null && this.color.a instanceof Color) {
                    this.g = ((Color)this.color.a).color;
                }
                if (this.g != this.p) {
                    if (this.p != null) {
                        this.p.removeFieldObserver(this);
                    }
                    this.p = this.g;
                    if (this.p != null) {
                        this.p.addFieldObserver(this, new Integer(2));
                        this.dF = ((this.p.a == null) ? 0 : this.p.a.length);
                    }
                }
                this.E = -1;
            }
            case 2: {
                if ((this.p.a == null && this.dF != 0) || (this.p.a != null && this.dF != this.p.a.length)) {
                    this.E |= 0x4;
                    this.dF = ((this.p.a == null) ? 0 : this.p.a.length);
                    return;
                }
                break;
            }
            case 8: {
                if ((this.colorIndex.a == null && this.dG != 0) || (this.colorIndex.a != null && this.dG != this.colorIndex.a.length)) {
                    this.E |= 0x10;
                    this.dG = ((this.colorIndex.a == null) ? 0 : this.colorIndex.a.length);
                    return;
                }
                break;
            }
            case 32: {
                this.g = null;
                if (this.coord.a != null && this.coord.a instanceof Coordinate) {
                    this.g = ((Coordinate)this.coord.a).point;
                }
                if (this.g != this.o) {
                    if (this.o != null) {
                        this.o.removeFieldObserver(this);
                    }
                    this.o = this.g;
                    if (this.o != null) {
                        this.o.addFieldObserver(this, new Integer(64));
                        this.dH = ((this.o.a == null) ? 0 : this.o.a.length);
                    }
                }
                this.E = -1;
            }
            case 64: {
                if (this.o.a != null && this.dH == 0) {
                    this.E = -1;
                    this.dH = ((this.o.a == null) ? 0 : this.o.a.length);
                }
                else if ((this.o.a == null && this.dH != 0) || (this.o.a != null && this.dH != this.o.a.length)) {
                    this.E |= 0x80;
                    this.dH = ((this.o.a == null) ? 0 : this.o.a.length);
                }
                this.E |= 0x800;
            }
            case 256: {
                if ((this.coordIndex.a == null && this.dI != 0) || (this.coordIndex.a != null && this.dI != this.coordIndex.a.length)) {
                    this.E |= 0x200;
                    this.dI = ((this.coordIndex.a == null) ? 0 : this.coordIndex.a.length);
                }
                this.E |= 0x800;
            }
        }
    }
    
    void d() {
        this.do += this.dq - super.d.bm;
        ++this.dr;
        --this.dt;
        this.cM += this.dd;
        this.dp += this.dq;
        if (this.I) {
            this.df += this.dg;
            this.di += this.dj;
            this.dl += this.dm;
        }
        this.dv += this.dw;
        if (this.dv >= this.dx) {
            ++this.do;
            ++this.dp;
            this.dv -= this.dx;
            this.cM += this.de;
            if (this.I) {
                this.df += this.dh;
                this.di += this.dk;
                this.dl += this.dn;
            }
        }
    }
    
    void e() {
        this.dc = this.dB + 1;
        this.db = this.L[this.dB] + 15;
        this.dp = this.db >> 4;
        this.db = this.L[this.dC] + 15;
        this.cU = this.db >> 4;
        this.du = this.cU - this.dp;
        if (this.du > 0) {
            this.cX = this.L[this.dC + 1] - this.L[this.dc];
            this.cZ = this.cX << 4;
            this.cY = this.L[this.dC] - this.L[this.dB];
            this.da = this.cY << 4;
            this.cW = this.cZ * this.dp - this.cX * this.L[this.dB] + this.cY * this.L[this.dc] - 1 + this.da;
            this.dr = this.cW / this.da;
            this.dv = this.cW % this.da;
            if (this.cW < 0) {
                this.dv = -this.dv;
                if (this.dv != 0) {
                    --this.dr;
                    this.dv = this.da - this.dv;
                }
            }
            this.ds = this.cZ / this.da;
            this.dw = this.cZ % this.da;
            if (this.cZ < 0) {
                this.dw = -this.dw;
                if (this.dw != 0) {
                    --this.ds;
                    this.dw = this.da - this.dw;
                }
            }
            this.dx = this.da;
            this.cS = ((this.dr << 4) - this.L[this.dc]) * 0.0625f;
            this.cT = ((this.dp << 4) - this.L[this.dB]) * 0.0625f;
            this.dB /= 3;
            this.do = this.dp + (super.d.bn - 1 - this.dr) * super.d.bm;
            this.cM = this.cx[this.dB] + this.cS * this.cC + this.cT * this.cB;
            this.dd = this.ds * this.cC + this.cB;
            this.de = this.cC;
            if (this.I) {
                this.df = this.cy[this.dB] + this.cS * this.cE + this.cT * this.cD;
                this.dg = this.ds * this.cE + this.cD;
                this.dh = this.cE;
                this.di = this.cz[this.dB] + this.cS * this.cG + this.cT * this.cF;
                this.dj = this.ds * this.cG + this.cF;
                this.dk = this.cG;
                this.dl = this.cA[this.dB] + this.cS * this.cI + this.cT * this.cH;
                this.dm = this.ds * this.cI + this.cH;
                this.dn = this.cI;
            }
        }
    }
    
    protected void f() {
        if (this.E == 0) {
            return;
        }
        this.t = (int)(this.lineWidth.a / 2.0f);
        this.u = 16 * this.t;
        if (this.o != null && this.o.a != null) {
            this.l = new float[this.o.a.length];
            this.h = new float[this.o.a.length];
            this.i = new int[this.o.a.length];
            System.arraycopy(this.o.a, 0, this.l, 0, this.o.a.length);
            if (this.coordIndex.a != null) {
                this.j = new int[this.coordIndex.a.length];
                for (int i = 0; i < this.coordIndex.a.length; ++i) {
                    if (this.coordIndex.a[i] == -2) {
                        this.j[i] = -2;
                    }
                    else if (this.coordIndex.a[i] == -1) {
                        this.j[i] = -1;
                    }
                    else {
                        this.j[i] = this.coordIndex.a[i] * 3;
                    }
                }
                if (this.colorIndex.a != null) {
                    this.k = new int[this.colorIndex.a.length];
                    for (int j = 0; j < this.colorIndex.a.length; ++j) {
                        if (this.colorIndex.a[j] == -2) {
                            this.k[j] = -2;
                        }
                        else if (this.colorIndex.a[j] == -1) {
                            this.k[j] = -1;
                        }
                        else {
                            this.k[j] = this.colorIndex.a[j] * 3;
                        }
                    }
                }
                if (this.p != null && this.p.a != null) {
                    this.m = new float[this.p.a.length];
                    for (int k = 0; k < this.p.a.length; ++k) {
                        this.m[k] = this.p.a[k] * 255.0f;
                    }
                }
            }
        }
        this.E = 0;
    }
    
    protected void c(final g g) {
        if ((this.E & 0x800) != 0x0) {
            super.c(g);
            this.E -= 2048;
        }
    }
    
    protected void e(final g g) {
        if (this.E != 0) {
            this.f();
        }
        final float[] b = g.b();
        if (this.o != null && this.o.a != null) {
            for (int i = 0; i < this.o.a.length; i += 3) {
                this.n = this.o.a[i];
                this.F = this.o.a[i + 1];
                this.G = this.o.a[i + 2];
                this.l[i] = this.n * b[0] + this.F * b[3] + this.G * b[6] + b[9];
                this.l[i + 1] = this.n * b[1] + this.F * b[4] + this.G * b[7] + b[10];
                this.l[i + 2] = this.n * b[2] + this.F * b[5] + this.G * b[8] + b[11];
            }
        }
    }
    
    public void b(final g d) {
        if (this.E != 0) {
            this.f();
        }
        this.e(super.d = d);
        super.e = d.bc;
        if (this.o == null || this.o.a == null) {
            return;
        }
        if (this.o.a.length == 0) {
            return;
        }
        if (this.j == null) {
            return;
        }
        if (this.j.length == 0) {
            return;
        }
        this.H = d.bf;
        this.I = false;
        this.J = false;
        this.K = false;
        if (this.colorIndex.a != null && this.colorIndex.a.length > 0) {
            this.K = true;
        }
        if (this.p != null && this.p.a != null && this.p.a.length > 0) {
            if (this.colorPerVertex.g) {
                this.I = true;
            }
            else {
                this.J = true;
            }
        }
        if (super.e == null) {
            super.s = false;
        }
        else {
            super.s = d.bc.a;
        }
        if (this.I) {
            this.R = this.Q;
        }
        else {
            this.R = this.bf;
            if (!this.J) {
                if (!super.s) {
                    this.be = -1;
                }
                else {
                    this.be = -16777216 + ((int)d.bc.f[0] << 16) + ((int)d.bc.f[1] << 8) + (int)d.bc.f[2];
                }
            }
        }
        this.d(d);
    }
    
    void a(final boolean b) {
        if (this.cQ == 0.0f) {
            this.cK = 0.0f;
        }
        else {
            this.cK = 1.0f / this.cQ;
        }
        if (this.cR == 0.0f) {
            this.cL = 0.0f;
        }
        else {
            this.cL = 1.0f / this.cR;
        }
        this.cx[0] = 1.0f / this.M[0];
        this.cx[1] = 1.0f / this.M[1];
        if (this.I) {
            this.cy[0] = this.N[0] * this.cx[0];
            this.cz[0] = this.N[1] * this.cx[0];
            this.cA[0] = this.N[2] * this.cx[0];
            this.cy[1] = this.N[3] * this.cx[1];
            this.cz[1] = this.N[4] * this.cx[1];
            this.cA[1] = this.N[5] * this.cx[1];
        }
        this.cQ *= 0.0625f;
        this.cR *= 0.0625f;
        this.dD = this.cj / (this.cj + this.ck);
        this.dE = this.ck / (this.cj + this.ck);
        this.cB = 16.0f * this.dD * (this.cx[1] - this.cx[0]) * this.cK;
        this.cC = 16.0f * this.dE * (this.cx[1] - this.cx[0]) * this.cL;
        if (this.I) {
            this.cD = 16.0f * this.dD * this.cK * (this.cy[1] - this.cy[0]);
            this.cF = 16.0f * this.dD * this.cK * (this.cz[1] - this.cz[0]);
            this.cH = 16.0f * this.dD * this.cK * (this.cA[1] - this.cA[0]);
            this.cE = 16.0f * this.dE * this.cL * (this.cy[1] - this.cy[0]);
            this.cG = 16.0f * this.dE * this.cL * (this.cz[1] - this.cz[0]);
            this.cI = 16.0f * this.dE * this.cL * (this.cA[1] - this.cA[0]);
        }
    }
    
    protected void finalize() throws Throwable {
        this.color.removeFieldObserver(this);
        this.colorIndex.removeFieldObserver(this);
        this.coord.removeFieldObserver(this);
        this.coordIndex.removeFieldObserver(this);
        this.lineWidth.removeFieldObserver(this);
        if (this.p != null) {
            this.p.removeFieldObserver(this);
        }
        if (this.o != null) {
            this.o.removeFieldObserver(this);
        }
        super.finalize();
    }
    
    void g() {
        this.dc = this.dB + 1;
        this.db = this.L[this.dc] + 15;
        this.dr = this.db >> 4;
        this.db = this.L[this.dC + 1] + 15;
        this.cV = this.db >> 4;
        this.dt = this.cV - this.dr;
        if (this.dt > 0) {
            this.cX = this.L[this.dC + 1] - this.L[this.dc];
            this.cZ = this.cX << 4;
            this.cY = this.L[this.dC] - this.L[this.dB];
            this.da = this.cY << 4;
            this.cW = this.da * this.dr - this.cY * this.L[this.dc] + this.cX * this.L[this.dB] - 1 + this.cZ;
            this.dp = this.cW / this.cZ;
            this.dv = this.cW % this.cZ;
            if (this.cW < 0) {
                this.dv = -this.dv;
                if (this.dv != 0) {
                    --this.dp;
                    this.dv = this.cZ - this.dv;
                }
            }
            this.dq = this.da / this.cZ;
            this.dw = this.da % this.cZ;
            if (this.da < 0) {
                this.dw = -this.dw;
                if (this.dw != 0) {
                    --this.dq;
                    this.dw = this.cZ - this.dw;
                }
            }
            this.dx = this.cZ;
            this.cS = ((this.dr << 4) - this.L[this.dc]) * 0.0625f;
            this.cT = ((this.dp << 4) - this.L[this.dB]) * 0.0625f;
            this.dB /= 3;
            this.do = this.dp + (super.d.bn - 1 - this.dr) * super.d.bm;
            this.cM = this.cx[this.dB] + this.cS * this.cC + this.cT * this.cB;
            this.dd = this.dq * this.cB + this.cC;
            this.de = this.cB;
            if (this.I) {
                this.df = this.cy[this.dB] + this.cS * this.cE + this.cT * this.cD;
                this.dg = this.dq * this.cD + this.cE;
                this.dh = this.cD;
                this.di = this.cz[this.dB] + this.cS * this.cG + this.cT * this.cF;
                this.dj = this.dq * this.cF + this.cG;
                this.dk = this.cF;
                this.dl = this.cA[this.dB] + this.cS * this.cI + this.cT * this.cH;
                this.dm = this.dq * this.cH + this.cI;
                this.dn = this.cH;
            }
        }
    }
    
    void h() {
        this.cq = this.do;
        if (this.cq < 0 || this.cq >= super.d.bq || this.dp >= super.d.bm) {
            return;
        }
        this.cs = 1.0f / this.cM;
        this.cn = (int)(this.cs * this.df);
        this.co = (int)(this.cs * this.di);
        this.cp = (int)(this.cs * this.dl);
        if (this.cn < 0) {
            this.cn = 0;
        }
        if (this.cn > 255) {
            this.cn = 255;
        }
        if (this.co < 0) {
            this.co = 0;
        }
        if (this.co > 255) {
            this.co = 255;
        }
        if (this.cp < 0) {
            this.cp = 0;
        }
        if (this.cp > 255) {
            this.cp = 255;
        }
        this.be = -16777216 + (this.cn << 16) + (this.co << 8) + this.cp;
        this.i();
    }
    
    void i() {
        this.cq = this.do;
        if (this.cq < 0 || this.cq >= super.d.bq || this.dp >= super.d.bm) {
            return;
        }
        this.ct = this.dp;
        if (this.cm) {
            this.cq -= this.t;
            this.ct -= this.t;
            this.cu = 1;
            this.cv = 1;
        }
        else {
            this.cq -= this.t * super.d.bm;
            this.cu = super.d.bm;
            this.cv = 0;
        }
        for (int n = 0; n < this.lineWidth.a; ++n) {
            if (this.ct >= 0 && this.ct < super.d.bm && this.cq >= 0 && this.cq < super.d.bq) {
                this.cw = ((this.dd > 0.0f) ? (-this.dd) : this.dd);
                if (this.cM + this.cw <= super.d.Q[this.cq]) {
                    super.d.Q[this.cq] = this.cM + this.cw;
                    this.b(this.cq, this.be);
                }
            }
            this.cq += this.cu;
            this.ct += this.cv;
        }
    }
}
