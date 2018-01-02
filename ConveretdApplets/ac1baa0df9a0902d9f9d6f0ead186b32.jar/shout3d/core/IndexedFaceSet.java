// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class IndexedFaceSet extends Geometry implements FieldObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final int[] defaultColorIndex;
    public final int[] defaultCoordIndex;
    public final int[] defaultTexCoordIndex;
    public final BooleanField ccw;
    public final NodeField color;
    public final IntArrayField colorIndex;
    public final BooleanField colorPerVertex;
    public final NodeField coord;
    public final IntArrayField coordIndex;
    public final FloatField creaseAngle;
    public final BooleanField solid;
    public final NodeField texCoord;
    public final IntArrayField texCoordIndex;
    private FloatArrayField g;
    protected float[] l;
    protected float[] h;
    protected float[] i;
    protected int t;
    protected int u;
    protected int[] j;
    protected int[] k;
    protected float[] m;
    protected float[][] n;
    protected float[] o;
    protected float[][] p;
    protected int[] q;
    protected int[] r;
    protected int[][] s;
    protected int[] v;
    protected int[] w;
    protected float[] x;
    protected float[][] y;
    private float F;
    private float G;
    private float z;
    protected FloatArrayField A;
    protected FloatArrayField B;
    protected FloatArrayField C;
    protected static final int D = 1;
    protected static final int E = 2;
    protected static final int H = 4;
    protected static final int I = 8;
    protected static final int J = 16;
    protected static final int K = 32;
    protected static final int L = 64;
    protected static final int M = 128;
    protected static final int N = 256;
    protected static final int O = 512;
    protected static final int P = 1024;
    protected static final int Q = 2048;
    protected static final int R = 4096;
    protected static final int S = 8192;
    protected static final int T = 16384;
    protected static final int U = 32768;
    protected static final int V = 65536;
    protected int be;
    protected int bf;
    protected int W;
    protected int X;
    protected int Y;
    protected int Z;
    protected int ba;
    protected boolean bb;
    private float[] bc;
    private int bd;
    private int bg;
    private int bh;
    private boolean bi;
    private boolean bj;
    private boolean bk;
    private boolean bl;
    protected boolean bm;
    protected boolean bn;
    protected boolean bo;
    protected boolean bp;
    private float bq;
    private boolean br;
    private boolean bs;
    private double bt;
    private int bu;
    private boolean bv;
    private boolean bw;
    private int[] bx;
    private int[][] by;
    private int[][] bz;
    private int[] bA;
    private float bB;
    private float[] bC;
    private float[] bD;
    private boolean bE;
    private float bF;
    private boolean bG;
    private boolean bH;
    private boolean bI;
    private int bJ;
    private int bK;
    private int bL;
    private int bM;
    private int bN;
    private int bO;
    private int bP;
    protected boolean[] bQ;
    private int bR;
    private float bS;
    private float bT;
    private float bU;
    private float bV;
    private float bW;
    private float bX;
    private float bY;
    private float bZ;
    private float ca;
    private float cb;
    private float cc;
    private float cd;
    private float ce;
    private int[] cf;
    private float[] cg;
    private float[] ch;
    private int[] ci;
    private boolean cj;
    private boolean ck;
    private boolean cl;
    private boolean cm;
    private boolean cn;
    private boolean co;
    private boolean cp;
    private double cq;
    private int cr;
    private int cs;
    private int ct;
    private int cu;
    private int cv;
    private float cw;
    private float cx;
    private float cy;
    private float cz;
    private float cA;
    private int cB;
    private int cC;
    private int cD;
    private int cE;
    private int cF;
    private int cG;
    private int cH;
    private int cI;
    private int cJ;
    private int cK;
    private int cL;
    private int cM;
    private int cN;
    private int cO;
    private int cP;
    private int cQ;
    private int cR;
    private int cS;
    private float cT;
    private int cU;
    private int cV;
    private int cW;
    private int cX;
    private float cY;
    private float cZ;
    private float da;
    private float db;
    private int dc;
    private int dd;
    private int de;
    private float df;
    private float dg;
    private float dh;
    private float di;
    private int dj;
    private int dk;
    private int dl;
    private float[] dm;
    private float[] dn;
    private int[] do;
    private float[] dp;
    private float[] dq;
    private int[] dr;
    private float ds;
    private float dt;
    private float du;
    private float dv;
    private float dw;
    private float dx;
    private float dy;
    private float dz;
    private float dA;
    private float dB;
    private float dC;
    private float dD;
    private float dE;
    private float dF;
    private float dG;
    private float dH;
    private float dI;
    private float dJ;
    private float dK;
    private float dL;
    private boolean dM;
    private boolean dN;
    private int dO;
    private int dP;
    private int dQ;
    private int dR;
    private int dS;
    private int dT;
    private int dU;
    private int[] dV;
    private int[] dW;
    private boolean dX;
    private boolean dY;
    private boolean dZ;
    private int ea;
    private int eb;
    private int ec;
    private int ed;
    private int ee;
    private int ef;
    private int eg;
    int eh;
    private b ei;
    private boolean ej;
    private int ek;
    private int el;
    private float em;
    private float en;
    private float eo;
    private float ep;
    private b eq;
    private b er;
    protected float[] es;
    protected float[] et;
    protected float[] eu;
    protected float ev;
    protected float ew;
    protected float ex;
    protected float ey;
    protected float ez;
    protected float eA;
    protected int eB;
    protected int eC;
    protected int eD;
    protected int eE;
    protected int eF;
    protected int eG;
    private long eH;
    private long eI;
    private long eJ;
    private long eK;
    private float eL;
    private float eM;
    private float eN;
    private float eO;
    private float eP;
    private float eQ;
    private int eR;
    private float eS;
    private float eT;
    private float eU;
    private int eV;
    private int eW;
    private int eX;
    private float eY;
    private float eZ;
    private float fa;
    private float fb;
    private float fc;
    private float fd;
    private float fe;
    private float ff;
    private int fg;
    private int fh;
    private int fi;
    private int fj;
    private int fk;
    private int fl;
    private int fm;
    private int fn;
    private int fo;
    private int fp;
    private int fq;
    private int fr;
    private int fs;
    private int ft;
    private int fu;
    private int fv;
    private int fw;
    private int fx;
    private float fy;
    private float fz;
    private float fA;
    private float fB;
    private float fC;
    private float fD;
    private float fE;
    private float fF;
    private float fG;
    private float fH;
    private float fI;
    private int fJ;
    
    private void a(final g g, final int n) {
        if (this.dk < 9) {
            return;
        }
        this.dO = 0;
        this.dP = 0;
        this.dQ = 0;
        this.ds = this.dm[this.dk - 3];
        this.dt = this.dm[this.dk - 2];
        this.du = this.dm[this.dk - 1];
        if (super.r) {
            this.dv = this.dn[this.dl - 2];
            this.dw = this.dn[this.dl - 1];
        }
        if (this.bm) {
            this.dV[0] = this.do[this.dk - 3];
            this.dV[1] = this.do[this.dk - 2];
            this.dV[2] = this.do[this.dk - 1];
        }
        if (n == 1) {
            this.dK = g.bg * this.ds - g.bi * this.du;
        }
        else if (n == 2) {
            this.dK = g.bh * this.dt - g.bj * this.du;
        }
        else if (n == 4) {
            this.dK = -0.1f - this.du;
        }
        else if (n == 8) {
            this.dK = -g.bg * this.ds - g.bi * this.du;
        }
        else if (n == 16) {
            this.dK = -g.bh * this.dt - g.bj * this.du;
        }
        this.dM = (this.dK >= 0.0f);
        this.dS = 0;
        this.dT = 0;
        this.dU = 0;
        for (int i = 0; i < this.dk; i += 3) {
            this.dx = this.dm[this.dS++];
            this.dy = this.dm[this.dS++];
            this.dz = this.dm[this.dS++];
            if (super.r) {
                this.dA = this.dn[this.dT++];
                this.dB = this.dn[this.dT++];
            }
            if (this.bm) {
                System.arraycopy(this.do, this.dU, this.dW, 0, 3);
                this.dU += 3;
            }
            if (n == 1) {
                this.dL = g.bg * this.dx - g.bi * this.dz;
            }
            else if (n == 2) {
                this.dL = g.bh * this.dy - g.bj * this.dz;
            }
            else if (n == 4) {
                this.dL = -0.1f - this.dz;
            }
            else if (n == 8) {
                this.dL = -g.bg * this.dx - g.bi * this.dz;
            }
            else if (n == 16) {
                this.dL = -g.bh * this.dy - g.bj * this.dz;
            }
            this.dN = (this.dL >= 0.0f);
            if (this.dM && !this.dN) {
                this.dF = this.dx - this.ds;
                this.dG = this.dy - this.dt;
                this.dH = this.dz - this.du;
                if (n == 1) {
                    this.dD = g.bg * this.dF - g.bi * this.dH;
                }
                else if (n == 2) {
                    this.dD = g.bh * this.dG - g.bj * this.dH;
                }
                else if (n == 4) {
                    this.dD = -this.dH;
                }
                else if (n == 8) {
                    this.dD = -g.bg * this.dF - g.bi * this.dH;
                }
                else if (n == 16) {
                    this.dD = -g.bh * this.dG - g.bj * this.dH;
                }
                this.dE = -(this.dK / this.dD);
                this.dp[this.dO++] = this.ds + this.dE * this.dF;
                this.dp[this.dO++] = this.dt + this.dE * this.dG;
                this.dp[this.dO++] = this.du + this.dE * this.dH;
                if (super.r) {
                    this.dq[this.dP++] = this.dv + this.dE * (this.dA - this.dv);
                    this.dq[this.dP++] = this.dw + this.dE * (this.dB - this.dw);
                }
                if (this.bm) {
                    this.dr[this.dQ++] = (int)(this.dV[0] + this.dE * (this.dW[0] - this.dV[0]));
                    this.dr[this.dQ++] = (int)(this.dV[1] + this.dE * (this.dW[1] - this.dV[1]));
                    this.dr[this.dQ++] = (int)(this.dV[2] + this.dE * (this.dW[2] - this.dV[2]));
                }
            }
            else if (!this.dM && this.dN) {
                this.dF = this.dx - this.ds;
                this.dG = this.dy - this.dt;
                this.dH = this.dz - this.du;
                if (n == 1) {
                    this.dD = g.bg * this.dF - g.bi * this.dH;
                }
                else if (n == 2) {
                    this.dD = g.bh * this.dG - g.bj * this.dH;
                }
                else if (n == 4) {
                    this.dD = -this.dH;
                }
                else if (n == 8) {
                    this.dD = -g.bg * this.dF - g.bi * this.dH;
                }
                else if (n == 16) {
                    this.dD = -g.bh * this.dG - g.bj * this.dH;
                }
                this.dE = -(this.dK / this.dD);
                this.dp[this.dO++] = this.ds + this.dE * this.dF;
                this.dp[this.dO++] = this.dt + this.dE * this.dG;
                this.dp[this.dO++] = this.du + this.dE * this.dH;
                if (super.r) {
                    this.dq[this.dP++] = this.dv + this.dE * (this.dA - this.dv);
                    this.dq[this.dP++] = this.dw + this.dE * (this.dB - this.dw);
                }
                if (this.bm) {
                    this.dr[this.dQ++] = (int)(this.dV[0] + this.dE * (this.dW[0] - this.dV[0]));
                    this.dr[this.dQ++] = (int)(this.dV[1] + this.dE * (this.dW[1] - this.dV[1]));
                    this.dr[this.dQ++] = (int)(this.dV[2] + this.dE * (this.dW[2] - this.dV[2]));
                }
                this.dp[this.dO++] = this.dx;
                this.dp[this.dO++] = this.dy;
                this.dp[this.dO++] = this.dz;
                if (super.r) {
                    this.dq[this.dP++] = this.dA;
                    this.dq[this.dP++] = this.dB;
                }
                if (this.bm) {
                    System.arraycopy(this.dW, 0, this.dr, this.dQ, 3);
                    this.dQ += 3;
                }
            }
            else if (this.dM && this.dN) {
                this.dp[this.dO++] = this.dx;
                this.dp[this.dO++] = this.dy;
                this.dp[this.dO++] = this.dz;
                if (super.r) {
                    this.dq[this.dP++] = this.dA;
                    this.dq[this.dP++] = this.dB;
                }
                if (this.bm) {
                    System.arraycopy(this.dW, 0, this.dr, this.dQ, 3);
                    this.dQ += 3;
                }
            }
            else if (this.dM || !this.dN) {}
            this.ds = this.dx;
            this.dt = this.dy;
            this.du = this.dz;
            if (super.r) {
                this.dv = this.dA;
                this.dw = this.dB;
            }
            if (this.bm) {
                System.arraycopy(this.dW, 0, this.dV, 0, 3);
            }
            this.dK = this.dL;
            this.dM = this.dN;
        }
        System.arraycopy(this.dp, 0, this.dm, 0, this.dO);
        this.dk = this.dO;
        if (super.r) {
            System.arraycopy(this.dq, 0, this.dn, 0, this.dP);
            this.dl = this.dP;
        }
        if (this.bm) {
            System.arraycopy(this.dr, 0, this.do, 0, this.dQ);
        }
    }
    
    private void b() {
        this.bu = this.A.a.length / 3;
        this.bx = new int[this.bu];
        final int[] value = this.coordIndex.getValue();
        for (int i = 0; i < value.length; ++i) {
            if (value[i] != -1) {
                final int[] bx = this.bx;
                final int n = value[i];
                ++bx[n];
            }
        }
        final int[] array = new int[this.bu];
        this.by = new int[this.bu][];
        this.bz = new int[this.bu][];
        for (int j = 0; j < this.bu; ++j) {
            this.by[j] = new int[this.bx[j]];
            this.bz[j] = new int[this.bx[j]];
        }
        for (int k = 0; k < value.length; ++k) {
            if (value[k] != -1) {
                final int n2 = k;
                do {
                    final int n3 = value[k];
                    this.by[n3][array[n3]] = n2;
                    this.bz[n3][array[n3]] = k;
                    final int[] array2 = array;
                    final int n4 = n3;
                    ++array2[n4];
                } while (++k < value.length && value[k] != -1);
            }
        }
        if (value.length - 1 > 0 && value[value.length - 1] == -1) {
            this.bA = new int[value.length];
            return;
        }
        this.bA = new int[value.length + 1];
    }
    
    private void d(final g g) {
        this.i(g);
        this.cm = true;
        if (this.v != null) {
            this.cV = 0;
            while (this.cV < this.v.length) {
                int cv = this.cV;
                int n = 1;
                while (++cv < this.v.length && this.v[cv] != -1) {
                    ++n;
                }
                if (n > 2) {
                    final int n2 = this.cV + 1;
                    final int n3 = this.cV + 2;
                    if (n == 3) {
                        if (this.ccw.g) {
                            this.a(this.cV, n2, n3, this.v[this.cV], this.v[n2], this.v[n3], this.bQ[this.cV]);
                        }
                        else {
                            this.a(n3, n2, this.cV, this.v[n3], this.v[n2], this.v[this.cV], this.bQ[this.cV]);
                        }
                    }
                    else {
                        if (this.ccw.g) {
                            this.a(this.cV, n2, n3, this.v[this.cV], this.v[n2], this.v[n3], this.bQ[this.cV]);
                        }
                        else {
                            this.a(n3, n2, this.cV, this.v[n3], this.v[n2], this.v[this.cV], this.bQ[this.cV]);
                        }
                        for (int i = 2; i < n - 1; ++i) {
                            if (this.ccw.g) {
                                this.a(this.cV, this.cV + i, n2 + i, this.v[this.cV], this.v[this.cV + i], this.v[n2 + i], this.bQ[this.cV]);
                            }
                            else {
                                this.a(n2 + i, this.cV + i, this.cV, this.v[n2 + i], this.v[this.cV + i], this.v[this.cV], this.bQ[this.cV]);
                            }
                        }
                    }
                }
                this.cV += n + 1;
            }
        }
    }
    
    private void a(final int n) {
        this.fy = this.eq.n;
        this.fl = this.eq.w;
        this.fi = this.er.w - this.fl;
        if (this.fi > 0) {
            this.fJ = 0;
            while (this.fJ < this.fi) {
                if (this.fy < super.d.Q[this.fl]) {
                    super.d.Q[this.fl] = this.fy;
                    this.b(this.fl, n);
                }
                ++this.fl;
                this.fy += this.ev;
                ++this.fJ;
            }
        }
    }
    
    private void e(final g g) {
        if (super.s) {
            if (!this.bj) {
                this.bR = 0;
                while (this.bR < g.be.size()) {
                    final DirectionalLight directionalLight = g.be.elementAt(this.bR);
                    if (directionalLight.on.g && directionalLight.intensity.a > 0.0f) {
                        directionalLight.g[0] = directionalLight.defaultColor[0] * g.bc.b[0];
                        directionalLight.g[1] = directionalLight.defaultColor[1] * g.bc.b[1];
                        directionalLight.g[2] = directionalLight.defaultColor[2] * g.bc.b[2];
                    }
                    ++this.bR;
                }
            }
            else {
                if (this.bk && this.x.length > 2) {
                    this.cc = this.x[0];
                    this.cd = this.x[1];
                    this.ce = this.x[2];
                }
                if (!this.ck && this.bl && this.m.length > 2) {
                    this.bZ = this.m[0];
                    this.ca = this.m[1];
                    this.cb = this.m[2];
                }
            }
            for (int i = 0; i < this.v.length; ++i) {
                if (this.bk && this.v[i] == -1 && i < this.v.length - 3) {
                    this.cc = this.x[i + 1];
                    this.cd = this.x[i + 2];
                    this.ce = this.x[i + 3];
                }
                if (!this.ck && this.bl && this.v[i] == -1 && i < this.v.length - 3) {
                    this.bZ = this.m[i + 1];
                    this.ca = this.m[i + 2];
                    this.cb = this.m[i + 3];
                }
                if (this.v[i] != -1) {
                    if (this.ck) {
                        this.bZ = this.n[i][0];
                        this.ca = this.n[i][1];
                        this.cb = this.n[i][2];
                    }
                    if (this.bl) {
                        this.cc = this.y[i][0];
                        this.cd = this.y[i][1];
                        this.ce = this.y[i][2];
                    }
                    if (super.r) {
                        this.bT = 0.0f;
                        this.bU = 0.0f;
                        this.bV = 0.0f;
                    }
                    else {
                        this.bT = g.bc.c[0];
                        this.bU = g.bc.c[1];
                        this.bV = g.bc.c[2];
                    }
                    this.bR = 0;
                    while (this.bR < g.be.size()) {
                        final DirectionalLight directionalLight2 = g.be.elementAt(this.bR);
                        if (directionalLight2.on.g && directionalLight2.intensity.a > 0.0f) {
                            this.bS = -this.bZ * directionalLight2.h[0] - this.ca * directionalLight2.h[1] - this.cb * directionalLight2.h[2];
                            if (!this.bQ[i]) {
                                this.bS *= (float)(-1.0);
                            }
                            if (this.bj) {
                                if (this.bS > 0.0f) {
                                    this.bT += directionalLight2.defaultColor[0] * this.cc * this.bS;
                                    this.bU += directionalLight2.defaultColor[1] * this.cd * this.bS;
                                    this.bV += directionalLight2.defaultColor[2] * this.ce * this.bS;
                                }
                            }
                            else if (this.bS > 0.0f) {
                                this.bT += directionalLight2.g[0] * this.bS;
                                this.bU += directionalLight2.g[1] * this.bS;
                                this.bV += directionalLight2.g[2] * this.bS;
                            }
                        }
                        ++this.bR;
                    }
                    if (this.bT > 1.0f) {
                        this.bT = 1.0f;
                    }
                    if (this.bU > 1.0f) {
                        this.bU = 1.0f;
                    }
                    if (this.bV > 1.0f) {
                        this.bV = 1.0f;
                    }
                    if (g.bc.e > 0.0f) {
                        this.s[i][0] = (int)(this.bT * g.bc.g * 1.3369344E8f + 0.4999f);
                        this.s[i][1] = (int)(this.bU * g.bc.g * 1.3369344E8f + 0.4999f);
                        this.s[i][2] = (int)(this.bV * g.bc.g * 1.3369344E8f + 0.4999f);
                    }
                    else {
                        this.s[i][0] = (int)(this.bT * 1.3369344E8f + 0.4999f);
                        this.s[i][1] = (int)(this.bU * 1.3369344E8f + 0.4999f);
                        this.s[i][2] = (int)(this.bV * 1.3369344E8f + 0.4999f);
                    }
                }
            }
        }
    }
    
    public IndexedFaceSet() {
        this.defaultColorIndex = new int[0];
        this.defaultCoordIndex = new int[0];
        this.defaultTexCoordIndex = new int[0];
        this.ccw = new BooleanField(this, "ccw", 0, true);
        this.color = new NodeField(this, "color", 3, null);
        this.colorIndex = new IntArrayField(this, "colorIndex", 11, this.defaultColorIndex);
        this.colorPerVertex = new BooleanField(this, "colorPerVertex", 0, true);
        this.coord = new NodeField(this, "coord", 4, null);
        this.coordIndex = new IntArrayField(this, "coordIndex", 11, this.defaultCoordIndex);
        this.creaseAngle = new FloatField(this, "creaseAngle", 15, 0.0f);
        this.solid = new BooleanField(this, "solid", 0, true);
        this.texCoord = new NodeField(this, "texCoord", 25, null);
        this.texCoordIndex = new IntArrayField(this, "texCoordIndex", 11, this.defaultTexCoordIndex);
        this.x = null;
        this.y = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.be = -1;
        this.bf = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.ba = 0;
        this.bb = true;
        this.bc = new float[3];
        this.bn = true;
        this.bo = true;
        this.bp = true;
        this.bq = 1.0E-4f;
        this.br = false;
        this.bw = false;
        this.bC = new float[12];
        this.bD = new float[9];
        this.bG = false;
        this.cf = new int[9];
        this.cg = new float[3];
        this.ch = new float[6];
        this.ci = new int[9];
        this.cr = 0;
        this.cs = 1;
        this.ct = 2;
        this.cu = 3;
        this.cv = this.ct;
        this.cB = 0;
        this.cC = 0;
        this.dk = 9;
        this.dl = 6;
        this.dm = new float[60];
        this.dn = new float[60];
        this.do = new int[60];
        this.dp = new float[60];
        this.dq = new float[60];
        this.dr = new int[60];
        this.ds = 0.0f;
        this.dt = 0.0f;
        this.du = 0.0f;
        this.dv = 0.0f;
        this.dw = 0.0f;
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.dz = 0.0f;
        this.dA = 0.0f;
        this.dB = 0.0f;
        this.dR = 0;
        this.dV = new int[3];
        this.dW = new int[3];
        this.eh = 0;
        this.eq = new b(this);
        this.er = new b(this);
        this.es = new float[3];
        this.et = new float[3];
        this.eu = new float[3];
        this.eV = 32768;
        this.eW = this.eV;
        this.eX = this.eV - 1;
        this.fk = 8;
        this.fq = 0;
        this.fr = 0;
        this.fI = 65536 / this.fk;
        this.fJ = 0;
        this.color.addFieldObserver(this, new Integer(1));
        this.colorIndex.addFieldObserver(this, new Integer(8));
        this.coord.addFieldObserver(this, new Integer(32));
        this.coordIndex.addFieldObserver(this, new Integer(256));
        this.creaseAngle.addFieldObserver(this, new Integer(1024));
        this.texCoord.addFieldObserver(this, new Integer(2048));
        this.texCoordIndex.addFieldObserver(this, new Integer(16384));
    }
    
    protected void f(final g g) {
        if (this.A != null && this.A.a != null) {
            MatUtil.a(g.b(), this.A.a, this.l);
        }
    }
    
    private void c() {
        this.fz = this.eq.n + this.eL;
        this.fA = this.eq.q + this.eN;
        this.fB = this.eq.t + this.eO;
        this.fC = 1.0f / this.eq.n;
        this.fD = this.fC * this.eq.q;
        this.fE = this.fC * this.eq.t;
        this.fq = 0;
        this.fr = 0;
        this.fy = this.eq.n;
        this.fl = this.eq.w;
        this.fi = this.er.w - this.fl;
        if (this.cp) {
            if (this.bm) {
                this.fv = this.eq.G;
                this.fw = this.eq.J;
                this.fx = this.eq.M;
            }
            else {
                this.fv = this.ci[0];
                this.fw = this.ci[1];
                this.fx = this.ci[2];
            }
        }
        if (this.fi > 0) {
            this.fs = this.fi >> 3;
            this.ft = this.fi % 8;
            if (this.ft == 0) {
                --this.fs;
                this.ft = this.fk;
            }
            while (this.fs-- > 0) {
                this.fF = 1.0f / this.fz;
                this.fG = this.fF * this.fA;
                this.fH = this.fF * this.fB;
                this.fo = (int)(this.fD * 65536.0f + this.eH);
                this.fp = (int)(this.fE * 65536.0f + this.eI);
                this.fq = (int)((this.fG - this.fD) * this.fI);
                this.fr = (int)((this.fH - this.fE) * this.fI);
                this.fm = 0;
                while (this.fm < this.fk) {
                    if (this.fy < super.d.Q[this.fl]) {
                        super.d.Q[this.fl] = this.fy;
                        if (this.cp) {
                            this.a(this.fl, this.fo >> 16, this.fp >> 16, this.fo, this.fp, this.fv, this.fw, this.fx);
                        }
                        else {
                            this.a(this.fl, this.fo, this.fp);
                        }
                    }
                    ++this.fl;
                    this.fo += this.fq;
                    this.fp += this.fr;
                    this.fy += this.ev;
                    if (this.bm) {
                        this.fv += this.eB;
                        this.fw += this.eD;
                        this.fx += this.eF;
                    }
                    ++this.fm;
                }
                this.fC = this.fF;
                this.fD = this.fG;
                this.fE = this.fH;
                this.fz += this.eL;
                this.fA += this.eN;
                this.fB += this.eO;
            }
            if (this.ft != 0) {
                this.fF = 1.0f / (this.er.n - this.ev);
                this.fG = this.fF * (this.er.q - this.ex);
                this.fH = this.fF * (this.er.t - this.ez);
                this.fo = (int)(this.fD * 65536.0f + this.eH);
                this.fp = (int)(this.fE * 65536.0f + this.eI);
                if (--this.ft != 0) {
                    this.fq = (int)((this.fG - this.fD) * super.l[this.ft]);
                    this.fr = (int)((this.fH - this.fE) * super.l[this.ft]);
                }
                this.fn = 0;
                while (this.fn <= this.ft) {
                    if (this.fy < super.d.Q[this.fl]) {
                        super.d.Q[this.fl] = this.fy;
                        if (this.cp) {
                            this.a(this.fl, this.fo >> 16, this.fp >> 16, this.fo, this.fp, this.fv, this.fw, this.fx);
                        }
                        else {
                            this.a(this.fl, this.fo, this.fp);
                        }
                    }
                    ++this.fl;
                    this.fo += this.fq;
                    this.fp += this.fr;
                    this.fy += this.ev;
                    if (this.bm) {
                        this.fv += this.eB;
                        this.fw += this.eD;
                        this.fx += this.eF;
                    }
                    ++this.fn;
                }
            }
        }
    }
    
    void a(final int[] array, final float[] array2, final float[] array3, final int[] array4) {
        System.arraycopy(array, 0, this.cf, 0, 9);
        System.arraycopy(array2, 0, this.cg, 0, 3);
        System.arraycopy(array3, 0, this.ch, 0, 6);
        System.arraycopy(array4, 0, this.ci, 0, 9);
        this.ej = true;
        this.d();
    }
    
    private void d() {
        if (!this.ej && (super.o || super.p)) {
            super.d.a(this, this.cf, this.cg, this.ch, this.ci);
            return;
        }
        this.dX = false;
        this.dY = false;
        if (this.cf[1] < this.cf[4]) {
            if (this.cf[7] < this.cf[1]) {
                this.ea = 6;
                this.eb = 0;
                this.ec = 3;
            }
            else {
                this.ea = 0;
                if (this.cf[4] < this.cf[7]) {
                    this.eb = 3;
                    this.ec = 6;
                }
                else {
                    this.eb = 6;
                    this.ec = 3;
                    this.dX = true;
                }
            }
        }
        else if (this.cf[7] < this.cf[4]) {
            this.ea = 6;
            this.eb = 3;
            this.ec = 0;
            this.dX = true;
        }
        else {
            this.ea = 3;
            if (this.cf[1] < this.cf[7]) {
                this.eb = 0;
                this.ec = 6;
                this.dX = true;
            }
            else {
                this.eb = 6;
                this.ec = 0;
            }
        }
        this.g();
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (o == null) {
            return;
        }
        this.be |= (int)o;
        switch ((int)o) {
            case 1: {
                this.g = null;
                if (this.color.a != null && this.color.a instanceof Color) {
                    this.g = ((Color)this.color.a).color;
                }
                if (this.g != this.B) {
                    if (this.B != null) {
                        this.B.removeFieldObserver(this);
                    }
                    this.B = this.g;
                    if (this.B != null) {
                        this.B.addFieldObserver(this, new Integer(2));
                        this.bf = ((this.B.a == null) ? 0 : this.B.a.length);
                    }
                }
                this.be = -1;
            }
            case 2: {
                if ((this.B.a == null && this.bf != 0) || (this.B.a != null && this.bf != this.B.a.length)) {
                    this.be |= 0x4;
                    this.bf = ((this.B.a == null) ? 0 : this.B.a.length);
                    return;
                }
                break;
            }
            case 8: {
                if ((this.colorIndex.a == null && this.W != 0) || (this.colorIndex.a != null && this.W != this.colorIndex.a.length)) {
                    this.be |= 0x10;
                    this.W = ((this.colorIndex.a == null) ? 0 : this.colorIndex.a.length);
                    return;
                }
                break;
            }
            case 32: {
                this.g = null;
                if (this.coord.a != null && this.coord.a instanceof Coordinate) {
                    this.g = ((Coordinate)this.coord.a).point;
                }
                if (this.g != this.A) {
                    if (this.A != null) {
                        this.A.removeFieldObserver(this);
                    }
                    this.A = this.g;
                    if (this.A != null) {
                        this.A.addFieldObserver(this, new Integer(64));
                        this.X = ((this.A.a == null) ? 0 : this.A.a.length);
                    }
                }
                this.be = -1;
            }
            case 64: {
                if (this.A.a != null && this.X == 0) {
                    this.be = -1;
                    this.X = ((this.A.a == null) ? 0 : this.A.a.length);
                }
                else if ((this.A.a == null && this.X != 0) || (this.A.a != null && this.X != this.A.a.length)) {
                    this.be |= 0x80;
                    this.be |= 0x10000;
                    this.X = ((this.A.a == null) ? 0 : this.A.a.length);
                }
                this.be |= 0x10000;
            }
            case 256: {
                if ((this.coordIndex.a == null && this.Y != 0) || (this.coordIndex.a != null && this.Y != this.coordIndex.a.length)) {
                    this.be |= 0x200;
                    this.Y = ((this.coordIndex.a == null) ? 0 : this.coordIndex.a.length);
                }
                this.be |= 0x10000;
            }
            case 2048: {
                this.g = null;
                if (this.texCoord.a != null && this.texCoord.a instanceof TextureCoordinate) {
                    this.g = ((TextureCoordinate)this.texCoord.a).point;
                }
                if (this.g != this.C) {
                    if (this.C != null) {
                        this.C.removeFieldObserver(this);
                    }
                    this.C = this.g;
                    if (this.C != null) {
                        this.C.addFieldObserver(this, new Integer(4096));
                        this.Z = ((this.C.a == null) ? 0 : this.C.a.length);
                    }
                }
                this.be = -1;
            }
            case 4096: {
                if ((this.C.a == null && this.Z != 0) || (this.C.a != null && this.Z != this.C.a.length)) {
                    this.be |= 0x2000;
                    this.Z = ((this.C.a == null) ? 0 : this.C.a.length);
                    return;
                }
                break;
            }
            case 16384: {
                if ((this.texCoordIndex.a == null && this.ba != 0) || (this.texCoordIndex.a != null && this.ba != this.texCoordIndex.a.length)) {
                    this.be |= 0x8000;
                    this.ba = ((this.texCoordIndex.a == null) ? 0 : this.texCoordIndex.a.length);
                    return;
                }
                break;
            }
        }
    }
    
    protected void e() {
        if (this.be == 0) {
            return;
        }
        if (this.creaseAngle.a > 1.0E-5f) {
            this.cn = !this.ck;
            this.ck = true;
            this.bw = (this.creaseAngle.a >= 3.14);
            this.cq = Math.cos(this.creaseAngle.a);
        }
        else {
            this.co = this.ck;
            this.ck = false;
        }
        if (this.A != null && this.A.a != null) {
            if ((this.be & 0x80) != 0x0) {
                if (this.A.a == null || this.l == null || this.l.length != this.A.a.length) {
                    this.l = new float[this.A.a.length];
                }
                this.h = new float[this.A.a.length];
                this.j = new int[this.A.a.length];
            }
            if (this.coordIndex.a != null && this.coordIndex.a.length > 0) {
                if ((this.be & 0x100) != 0x0) {
                    this.bd = 0;
                    if (this.coordIndex.a.length > 0) {
                        this.bn = (this.coordIndex.a[this.coordIndex.a.length - 1] == -1);
                    }
                    else {
                        this.bn = true;
                    }
                    if ((this.be & 0x200) != 0x0) {
                        if (this.bn) {
                            this.v = new int[this.coordIndex.a.length];
                        }
                        else {
                            this.v = new int[this.coordIndex.a.length + 1];
                        }
                    }
                    for (int i = 0; i < this.coordIndex.a.length; ++i) {
                        if (this.coordIndex.a[i] == -1) {
                            ++this.bd;
                            this.v[i] = -1;
                        }
                        else {
                            this.v[i] = this.coordIndex.a[i] * 3;
                        }
                    }
                    if (!this.bn) {
                        this.v[this.v.length - 1] = -1;
                    }
                }
                if (this.texCoordIndex.a != null && this.texCoordIndex.a.length != 0) {
                    this.bo = (this.texCoordIndex.a[this.texCoordIndex.a.length - 1] == -1);
                    if ((this.be & 0x4000) != 0x0) {
                        if ((this.be & 0x8000) != 0x0) {
                            if (this.bo) {
                                this.w = new int[this.texCoordIndex.a.length];
                            }
                            else {
                                this.w = new int[this.texCoordIndex.a.length + 1];
                            }
                        }
                        for (int j = 0; j < this.texCoordIndex.a.length; ++j) {
                            if (this.texCoordIndex.a[j] == -1) {
                                this.w[j] = -1;
                            }
                            else {
                                this.w[j] = this.texCoordIndex.a[j] * 2;
                            }
                        }
                        if (!this.bo) {
                            this.w[this.w.length - 1] = -1;
                        }
                    }
                }
                else if (this.C != null && this.C.a != null && ((this.be & 0x100) != 0x0 || (this.be & 0x4000) != 0x0)) {
                    if (this.w == null || this.w.length != this.v.length) {
                        this.w = new int[this.v.length];
                    }
                    for (int k = 0; k < this.coordIndex.a.length; ++k) {
                        if (this.coordIndex.a[k] == -1) {
                            this.w[k] = -1;
                        }
                        else {
                            this.w[k] = this.coordIndex.a[k] * 2;
                        }
                    }
                    if (!this.bn) {
                        this.w[this.w.length - 1] = -1;
                    }
                }
                if ((this.be & 0x100) != 0x0 || (this.be & 0x2) != 0x0 || (this.be & 0x8) != 0x0) {
                    this.bk = false;
                    this.bl = false;
                    this.bj = false;
                    if (this.B != null && this.B.a != null && this.B.a.length > 0) {
                        this.bg = this.B.a.length / 3;
                        this.bi = true;
                        if (!this.colorPerVertex.g) {
                            if (this.colorIndex != null && this.colorIndex.a.length > 0 && this.colorIndex.a.length == this.bd) {
                                int n = -1;
                                boolean b = true;
                                this.bh = 0;
                                while (this.bh < this.colorIndex.a.length) {
                                    if (this.colorIndex.a[this.bh] == -1) {
                                        b = false;
                                        break;
                                    }
                                    if (this.colorIndex.a[this.bh] > n) {
                                        n = this.colorIndex.a[this.bh];
                                    }
                                    ++this.bh;
                                }
                                if (b && n <= this.bg - 1) {
                                    this.bk = true;
                                    this.bj = true;
                                    this.bi = false;
                                    this.x = new float[this.v.length];
                                    int n2 = 0;
                                    final float[] value = this.B.getValue();
                                    this.bh = 0;
                                    while (this.bh < this.x.length) {
                                        System.arraycopy(value, this.colorIndex.a[n2++] * 3, this.x, this.bh, 3);
                                        do {
                                            ++this.bh;
                                        } while (this.bh < this.x.length && this.v[this.bh] != -1);
                                        ++this.bh;
                                    }
                                }
                            }
                            if (this.bi && this.bg >= this.bd) {
                                this.bk = true;
                                this.bj = true;
                                this.x = new float[this.v.length];
                                int n3 = 0;
                                this.bh = 0;
                                while (this.bh < this.x.length) {
                                    this.x[this.bh] = this.B.a[n3++];
                                    this.x[this.bh + 1] = this.B.a[n3++];
                                    this.x[this.bh + 2] = this.B.a[n3++];
                                    do {
                                        ++this.bh;
                                    } while (this.bh < this.x.length && this.v[this.bh] != -1);
                                    ++this.bh;
                                }
                            }
                        }
                        else {
                            if (this.colorIndex != null && this.colorIndex.a.length > 0 && this.colorIndex.a.length == this.coordIndex.a.length) {
                                int n4 = -1;
                                boolean b2 = true;
                                this.bh = 0;
                                while (this.bh < this.coordIndex.a.length) {
                                    if ((this.coordIndex.a[this.bh] == -1 && this.colorIndex.a[this.bh] != -1) || (this.coordIndex.a[this.bh] != -1 && this.colorIndex.a[this.bh] == -1)) {
                                        b2 = false;
                                        break;
                                    }
                                    if (this.colorIndex.a[this.bh] > n4) {
                                        n4 = this.colorIndex.a[this.bh];
                                    }
                                    ++this.bh;
                                }
                                if (b2 && n4 <= this.bg - 1) {
                                    this.bl = true;
                                    this.bj = true;
                                    this.bi = false;
                                    this.y = new float[this.v.length][3];
                                    this.bh = 0;
                                    while (this.bh < this.v.length) {
                                        if (this.v[this.bh] != -1) {
                                            final int n5 = this.colorIndex.a[this.bh] * 3;
                                            this.y[this.bh][0] = this.B.a[n5];
                                            this.y[this.bh][1] = this.B.a[n5 + 1];
                                            this.y[this.bh][2] = this.B.a[n5 + 2];
                                        }
                                        ++this.bh;
                                    }
                                }
                            }
                            if (this.bi) {
                                int n6 = -1;
                                this.bh = 0;
                                while (this.bh < this.coordIndex.a.length) {
                                    if (this.coordIndex.a[this.bh] > n6) {
                                        n6 = this.coordIndex.a[this.bh];
                                    }
                                    ++this.bh;
                                }
                                if (n6 <= this.bg - 1) {
                                    this.bl = true;
                                    this.bj = true;
                                    this.y = new float[this.v.length][3];
                                    this.bh = 0;
                                    while (this.bh < this.v.length) {
                                        if (this.v[this.bh] != -1) {
                                            final int n7 = this.v[this.bh];
                                            this.y[this.bh][0] = this.B.a[n7];
                                            this.y[this.bh][1] = this.B.a[n7 + 1];
                                            this.y[this.bh][2] = this.B.a[n7 + 2];
                                        }
                                        ++this.bh;
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.ck) {
                    this.bm = true;
                }
                else if (this.bl) {
                    this.bm = true;
                }
                else {
                    this.bm = false;
                }
                if ((this.be & 0x200) != 0x0) {
                    this.m = new float[this.v.length];
                    this.o = new float[this.v.length];
                }
                if (this.ck && (this.cn || (this.be & 0x200) != 0x0)) {
                    this.n = new float[this.v.length][3];
                    this.p = new float[this.v.length][3];
                }
                if ((this.be & 0x200) != 0x0) {
                    this.q = new int[this.v.length];
                }
                if (!this.bm) {
                    if (this.co || (this.be & 0x200) != 0x0) {
                        this.r = new int[this.v.length];
                    }
                }
                else if (this.cn || (this.be & 0x200) != 0x0) {
                    this.s = new int[this.v.length][3];
                }
                if ((this.be & 0x40) != 0x0 || (this.be & 0x100) != 0x0) {
                    final float[] a = this.A.a;
                    for (int l = 0, n8 = 1, n9 = 2; l < this.v.length; ++l, ++n8, ++n9) {
                        if (this.v[l] != -1 && this.v[n8] != -1 && this.v[n9] != -1) {
                            int n10 = l;
                            int n11 = n8;
                            int n12 = n9;
                            float n13;
                            float n14;
                            float n15;
                            while (true) {
                                n13 = a[this.v[n10]];
                                n14 = a[this.v[n10] + 1];
                                n15 = a[this.v[n10] + 2];
                                final float n16 = a[this.v[n11]] - n13;
                                final float n17 = a[this.v[n11] + 1] - n14;
                                final float n18 = a[this.v[n11] + 2] - n15;
                                final float n19 = a[this.v[n12]] - n13;
                                final float n20 = a[this.v[n12] + 1] - n14;
                                final float n21 = a[this.v[n12] + 2] - n15;
                                this.bc[0] = n17 * n21 - n20 * n18;
                                this.bc[1] = -n16 * n21 + n19 * n18;
                                this.bc[2] = n16 * n20 - n19 * n17;
                                if (MatUtil.normalize(this.bc) > 0.001 || n12 + 1 >= this.v.length || this.v[n12 + 1] == -1) {
                                    break;
                                }
                                ++n10;
                                ++n11;
                                ++n12;
                            }
                            if (this.ccw.g) {
                                this.o[l] = this.bc[0];
                                this.o[n8] = this.bc[1];
                                this.o[n9] = this.bc[2];
                            }
                            else {
                                this.o[l] = -this.bc[0];
                                this.o[n8] = -this.bc[1];
                                this.o[n9] = -this.bc[2];
                            }
                            this.o[l + 3] = -(this.bc[0] * n13 + this.bc[1] * n14 + this.bc[2] * n15);
                            int n22 = 0;
                            if (this.bc[0] < 0.0f) {
                                this.bc[0] = -this.bc[0];
                            }
                            if (this.bc[1] < 0.0f) {
                                this.bc[1] = -this.bc[1];
                            }
                            if (this.bc[2] < 0.0f) {
                                this.bc[2] = -this.bc[2];
                            }
                            float n23 = this.bc[0];
                            if (this.bc[1] > n23) {
                                n22 = 1;
                                n23 = this.bc[1];
                            }
                            if (this.bc[2] > n23) {
                                n22 = 2;
                                final float n24 = this.bc[2];
                            }
                            if (n22 == 0) {
                                this.q[l] = 1;
                                this.q[n8] = 2;
                            }
                            else if (n22 == 1) {
                                this.q[l] = 0;
                                this.q[n8] = 2;
                            }
                            else if (n22 == 2) {
                                this.q[l] = 0;
                                this.q[n8] = 1;
                            }
                            do {
                                ++l;
                                ++n8;
                                ++n9;
                            } while (l < this.v.length && this.v[l] != -1);
                        }
                    }
                }
                System.arraycopy(this.o, 0, this.m, 0, this.o.length);
                if (this.ck) {
                    this.i();
                }
            }
        }
        if ((this.be & 0x1000) != 0x0 || (this.be & 0x2000) != 0x0) {
            this.bb = true;
        }
        this.be = 0;
    }
    
    protected void g(final g g) {
        final float[] b = g.b();
        this.bG = (g.g() || g.A());
        this.bE = false;
        if (this.bG) {
            MatUtil.b(this.bC);
            MatUtil.j(b, this.bC);
            MatUtil.f(this.bC, this.bD);
        }
        else if (Math.abs(1.0f - (b[0] * b[0] + b[1] * b[1] + b[2] * b[2])) > 0.05) {
            this.bE = true;
        }
        else if (Math.abs(1.0f - (b[3] * b[3] + b[4] * b[4] + b[5] * b[5])) > 0.05) {
            this.bE = true;
        }
        else if (Math.abs(1.0f - (b[6] * b[6] + b[7] * b[7] + b[8] * b[8])) > 0.05) {
            this.bE = true;
        }
        if (this.o != null) {
            for (int i = 0; i < this.o.length; ++i) {
                if (this.v[i] != -1 && this.v[i + 1] != -1 && this.v[i + 2] != -1) {
                    this.F = this.o[i];
                    this.G = this.o[i + 1];
                    this.z = this.o[i + 2];
                    if (this.bG) {
                        this.m[i] = this.F * this.bD[0] + this.G * this.bD[3] + this.z * this.bD[6];
                        this.m[i + 1] = this.F * this.bD[1] + this.G * this.bD[4] + this.z * this.bD[7];
                        this.m[i + 2] = this.F * this.bD[2] + this.G * this.bD[5] + this.z * this.bD[8];
                    }
                    else {
                        this.m[i] = this.F * b[0] + this.G * b[3] + this.z * b[6];
                        this.m[i + 1] = this.F * b[1] + this.G * b[4] + this.z * b[7];
                        this.m[i + 2] = this.F * b[2] + this.G * b[5] + this.z * b[8];
                    }
                    if (this.bG || this.bE) {
                        MatUtil.a(this.m, i);
                    }
                    while (++i < this.v.length && this.v[i] != -1) {}
                }
            }
        }
        if (this.n != null) {
            for (int j = 0; j < this.v.length; ++j) {
                if (this.v[j] != -1) {
                    this.F = this.p[j][0];
                    this.G = this.p[j][1];
                    this.z = this.p[j][2];
                    if (this.bG) {
                        this.n[j][0] = this.F * this.bD[0] + this.G * this.bD[3] + this.z * this.bD[6];
                        this.n[j][1] = this.F * this.bD[1] + this.G * this.bD[4] + this.z * this.bD[7];
                        this.n[j][2] = this.F * this.bD[2] + this.G * this.bD[5] + this.z * this.bD[8];
                    }
                    else {
                        this.n[j][0] = this.F * b[0] + this.G * b[3] + this.z * b[6];
                        this.n[j][1] = this.F * b[1] + this.G * b[4] + this.z * b[7];
                        this.n[j][2] = this.F * b[2] + this.G * b[5] + this.z * b[8];
                    }
                    if (this.bG || this.bE) {
                        MatUtil.normalize(this.n[j]);
                    }
                }
            }
        }
    }
    
    protected void c(final g g) {
        if ((this.be & 0x10000) != 0x0) {
            super.c(g);
            this.be -= 65536;
        }
    }
    
    private void f() {
        for (int i = 0; i < this.v.length; ++i) {
            if (this.v[i] != -1) {
                this.bT = this.y[i][0];
                this.bU = this.y[i][1];
                this.bV = this.y[i][2];
                if (this.bT > 1.0f) {
                    this.bT = 1.0f;
                }
                if (this.bU > 1.0f) {
                    this.bU = 1.0f;
                }
                if (this.bV > 1.0f) {
                    this.bV = 1.0f;
                }
                this.s[i][0] = (int)(this.bT * 1.3369344E8f + 0.4999f);
                this.s[i][1] = (int)(this.bU * 1.3369344E8f + 0.4999f);
                this.s[i][2] = (int)(this.bV * 1.3369344E8f + 0.4999f);
            }
        }
    }
    
    public void b(final g d) {
        if (this.be != 0) {
            this.e();
        }
        this.f(d);
        this.g(d);
        this.j();
        this.ej = false;
        super.d = d;
        super.e = d.bc;
        if (this.A == null || this.A.a == null) {
            return;
        }
        if (this.A.a.length == 0) {
            return;
        }
        if (this.v == null) {
            return;
        }
        if (this.v.length == 0) {
            return;
        }
        if (d.bc == null) {
            super.s = false;
        }
        else {
            super.s = d.bc.a;
        }
        if (super.s && d.bc.e == 1.0f) {
            return;
        }
        this.bI = d.bf;
        this.bH = (d.be.size() > 0 && super.s);
        if (d.bc == null) {
            super.r = false;
        }
        else {
            super.r = d.bc.k;
        }
        if (super.r) {
            if (this.C == null || this.C.a == null || this.C.a.length < 1) {
                this.i = null;
            }
            else if (this.i == null || this.i.length != this.C.a.length) {
                this.i = new float[this.C.a.length];
                this.bb = true;
            }
            if (d.bc.m != this.t || d.bc.n != this.u) {
                this.t = d.bc.m;
                this.u = d.bc.n;
                this.bb = true;
            }
            if (this.bb && this.i != null) {
                for (int i = 0; i < this.C.a.length; i += 2) {
                    this.i[i] = this.C.a[i] * (this.t - 1.0f);
                    this.i[i + 1] = this.u - 1.0f - this.C.a[i + 1] * (this.u - 1.0f);
                }
            }
            this.bb = false;
        }
        if (super.r && (this.C == null || this.C.a == null)) {
            super.r = false;
        }
        if (super.r && (this.C.a.length == 0 || this.w == null || this.w.length == 0)) {
            super.r = false;
        }
        if (super.r && (d.bc.q == 2 || d.bc.q == 4)) {
            super.p = true;
        }
        else {
            super.p = false;
        }
        super.o = (super.s && d.bc.e != 0.0f);
        if (super.s) {
            this.cj = this.ck;
        }
        else {
            this.cj = this.bl;
        }
        this.cp = false;
        if (!this.bH) {
            if (!super.r) {
                if (super.o) {
                    this.cv = this.cs;
                    this.bK = (int)(d.bc.g * 255.0f + 0.4999f);
                    this.bL = (int)(d.bc.g * 255.0f + 0.4999f);
                    this.bM = (int)(d.bc.g * 255.0f + 0.4999f);
                }
                else if (this.bl) {
                    this.cv = this.cu;
                    this.f();
                }
                else {
                    this.cv = this.cr;
                    this.bJ = -1;
                }
            }
            else {
                this.cv = this.ct;
            }
        }
        else {
            if (!super.r) {
                if (this.ck || this.bl) {
                    this.cv = this.cu;
                }
                else if (super.o) {
                    this.cv = this.cs;
                }
                else {
                    this.cv = this.cr;
                }
            }
            else {
                this.cv = this.ct;
                this.cj = true;
                this.cp = true;
            }
            if (!this.bk && !this.bl) {
                if (this.ck) {
                    this.e(d);
                }
                else {
                    this.h(d);
                }
            }
            else if (!this.ck && this.bk) {
                this.h(d);
            }
            else {
                this.e(d);
            }
        }
        if (super.r) {
            boolean b = false;
            boolean b2 = false;
            if (super.p) {
                b = true;
                b2 = true;
            }
            else if (super.o || this.cp || d.p || d.q) {
                b2 = true;
            }
            if (b) {
                super.bg = d.bc.d();
            }
            if (b2) {
                super.bh = d.bc.a();
                super.bi = d.bc.c();
                super.bj = d.bc.b();
            }
        }
        this.d(d);
    }
    
    private void h(final g g) {
        if (super.s) {
            if (!this.bk) {
                this.bR = 0;
                while (this.bR < g.be.size()) {
                    final DirectionalLight directionalLight = g.be.elementAt(this.bR);
                    if (directionalLight.on.g && directionalLight.intensity.a > 0.0f) {
                        directionalLight.g[0] = directionalLight.defaultColor[0] * g.bc.b[0];
                        directionalLight.g[1] = directionalLight.defaultColor[1] * g.bc.b[1];
                        directionalLight.g[2] = directionalLight.defaultColor[2] * g.bc.b[2];
                    }
                    ++this.bR;
                }
            }
            for (int i = 0; i < this.v.length; ++i) {
                this.bZ = this.m[i];
                this.ca = this.m[i + 1];
                this.cb = this.m[i + 2];
                if (this.bk) {
                    this.cc = this.x[i];
                    this.cd = this.x[i + 1];
                    this.ce = this.x[i + 2];
                }
                if (super.r) {
                    this.bT = 0.0f;
                    this.bU = 0.0f;
                    this.bV = 0.0f;
                }
                else {
                    this.bT = g.bc.c[0];
                    this.bU = g.bc.c[1];
                    this.bV = g.bc.c[2];
                }
                this.bR = 0;
                while (this.bR < g.be.size()) {
                    final DirectionalLight directionalLight2 = g.be.elementAt(this.bR);
                    if (directionalLight2.on.g && directionalLight2.intensity.a > 0.0f) {
                        this.bS = -this.bZ * directionalLight2.h[0] - this.ca * directionalLight2.h[1] - this.cb * directionalLight2.h[2];
                        if (!this.bQ[i]) {
                            this.bS *= (float)(-1.0);
                        }
                        if (this.bk) {
                            if (this.bS > 0.0f) {
                                this.bT += directionalLight2.defaultColor[0] * this.cc * this.bS;
                                this.bU += directionalLight2.defaultColor[1] * this.cd * this.bS;
                                this.bV += directionalLight2.defaultColor[2] * this.ce * this.bS;
                            }
                        }
                        else if (this.bS > 0.0f) {
                            this.bT += directionalLight2.g[0] * this.bS;
                            this.bU += directionalLight2.g[1] * this.bS;
                            this.bV += directionalLight2.g[2] * this.bS;
                        }
                    }
                    ++this.bR;
                }
                if (this.bT > 1.0f) {
                    this.bT = 1.0f;
                }
                if (this.bU > 1.0f) {
                    this.bU = 1.0f;
                }
                if (this.bV > 1.0f) {
                    this.bV = 1.0f;
                }
                if (!this.cp) {
                    if (g.bc.e == 0.0f) {
                        this.r[i] = (int)(this.bT * 255.0f + 0.4999f);
                        this.r[i + 1] = (int)(this.bU * 255.0f + 0.4999f);
                        this.r[i + 2] = (int)(this.bV * 255.0f + 0.4999f);
                    }
                    else {
                        this.r[i] = (int)(this.bT * g.bc.g * 255.0f + 0.4999f);
                        this.r[i + 1] = (int)(this.bU * g.bc.g * 255.0f + 0.4999f);
                        this.r[i + 2] = (int)(this.bV * g.bc.g * 255.0f + 0.4999f);
                    }
                }
                else if (g.bc.e > 0.0f) {
                    this.r[i] = (int)(this.bT * g.bc.g * 1.3369344E8f + 0.4999f);
                    this.r[i + 1] = (int)(this.bU * g.bc.g * 1.3369344E8f + 0.4999f);
                    this.r[i + 2] = (int)(this.bV * g.bc.g * 1.3369344E8f + 0.4999f);
                }
                else {
                    this.r[i] = (int)(this.bT * 1.3369344E8f + 0.4999f);
                    this.r[i + 1] = (int)(this.bU * 1.3369344E8f + 0.4999f);
                    this.r[i + 2] = (int)(this.bV * 1.3369344E8f + 0.4999f);
                }
                while (++i < this.v.length && this.v[i] != -1) {}
            }
        }
    }
    
    protected void finalize() throws Throwable {
        this.color.removeFieldObserver(this);
        this.colorIndex.removeFieldObserver(this);
        this.coord.removeFieldObserver(this);
        this.coordIndex.removeFieldObserver(this);
        this.creaseAngle.removeFieldObserver(this);
        this.texCoord.removeFieldObserver(this);
        this.texCoordIndex.removeFieldObserver(this);
        if (this.B != null) {
            this.B.removeFieldObserver(this);
        }
        if (this.A != null) {
            this.A.removeFieldObserver(this);
        }
        if (this.C != null) {
            this.C.removeFieldObserver(this);
        }
        super.finalize();
    }
    
    private void g() {
        this.a(true, this.cf, this.cg, this.ch);
        if (this.dX) {
            this.eq.a(super.d, this.ea, this.eb, this.cf, this.ci);
            this.er.a(super.d, this.ea, this.ec, this.cf, this.ci);
            this.ei = this.eq;
        }
        else {
            this.eq.a(super.d, this.ea, this.ec, this.cf, this.ci);
            this.er.a(super.d, this.ea, this.eb, this.cf, this.ci);
            this.ei = this.er;
        }
        this.eg = 0;
        while (this.eg < 2) {
            while (this.ei.B > 0) {
                if (this.cv == this.cr) {
                    this.a(this.bJ);
                }
                else if (this.cv == this.cs) {
                    this.c(this.bK, this.bL, this.bM);
                }
                else if (this.cv == this.ct) {
                    this.c();
                }
                else if (this.cv == this.cu) {
                    this.h();
                }
                this.eq.a();
                this.er.a();
            }
            if (this.eg == 0) {
                if (this.cf[this.eb + 1] == this.cf[this.ec + 1]) {
                    return;
                }
                this.ei.a(super.d, this.eb, this.ec, this.cf, this.ci);
            }
            ++this.eg;
        }
    }
    
    private void a(final boolean b, final int[] array, final float[] array2, final float[] array3) {
        this.fc = array[3] - array[6];
        this.fd = array[1] - array[7];
        this.fe = array[0] - array[6];
        this.ff = array[4] - array[7];
        this.eS = 16.0f / (this.fc * this.fd / 16.0f - this.fe * this.ff / 16.0f);
        this.eT = -this.eS;
        this.es[0] = 1.0f / array2[0];
        this.es[1] = 1.0f / array2[1];
        this.es[2] = 1.0f / array2[2];
        if (super.r) {
            this.et[0] = array3[0] * this.es[0];
            this.eu[0] = array3[1] * this.es[0];
            this.et[1] = array3[2] * this.es[1];
            this.eu[1] = array3[3] * this.es[1];
            this.et[2] = array3[4] * this.es[2];
            this.eu[2] = array3[5] * this.es[2];
        }
        this.fc *= 0.0625f;
        this.fd *= 0.0625f;
        this.fe *= 0.0625f;
        this.ff *= 0.0625f;
        this.ev = this.eS * ((this.es[1] - this.es[2]) * this.fd - (this.es[0] - this.es[2]) * this.ff);
        this.ew = this.eT * ((this.es[1] - this.es[2]) * this.fe - (this.es[0] - this.es[2]) * this.fc);
        if (this.bm) {
            this.eB = (int)(this.eS * ((this.ci[3] - this.ci[6]) * this.fd - (this.ci[0] - this.ci[6]) * this.ff));
            this.eC = (int)(this.eT * ((this.ci[3] - this.ci[6]) * this.fe - (this.ci[0] - this.ci[6]) * this.fc));
            this.eD = (int)(this.eS * ((this.ci[4] - this.ci[7]) * this.fd - (this.ci[1] - this.ci[7]) * this.ff));
            this.eE = (int)(this.eT * ((this.ci[4] - this.ci[7]) * this.fe - (this.ci[1] - this.ci[7]) * this.fc));
            this.eF = (int)(this.eS * ((this.ci[5] - this.ci[8]) * this.fd - (this.ci[2] - this.ci[8]) * this.ff));
            this.eG = (int)(this.eT * ((this.ci[5] - this.ci[8]) * this.fe - (this.ci[2] - this.ci[8]) * this.fc));
        }
        if (super.r) {
            this.ex = this.eS * ((this.et[1] - this.et[2]) * this.fd - (this.et[0] - this.et[2]) * this.ff);
            this.ez = this.eS * ((this.eu[1] - this.eu[2]) * this.fd - (this.eu[0] - this.eu[2]) * this.ff);
            this.ey = this.eT * ((this.et[1] - this.et[2]) * this.fe - (this.et[0] - this.et[2]) * this.fc);
            this.eA = this.eT * ((this.eu[1] - this.eu[2]) * this.fe - (this.eu[0] - this.eu[2]) * this.fc);
            if (b) {
                this.eY = this.ex * this.es[0] - this.et[0] * this.ev;
                if (this.eY > 0.0f) {
                    this.eH = this.eW;
                }
                else if (this.eY < 0.0f) {
                    this.eH = this.eX;
                }
                else if (this.ey * this.es[0] - this.et[0] * this.ew >= 0.0f) {
                    this.eH = this.eW;
                }
                else {
                    this.eH = this.eX;
                }
                this.eZ = this.ez * this.es[0] - this.eu[0] * this.ev;
                if (this.eZ > 0.0f) {
                    this.eI = this.eW;
                }
                else if (this.eZ < 0.0f) {
                    this.eI = this.eX;
                }
                else if (this.eA * this.es[0] - this.eu[0] * this.ew >= 0.0f) {
                    this.eI = this.eW;
                }
                else {
                    this.eI = this.eX;
                }
                this.eL = this.ev * 8.0f;
                this.eN = this.ex * 8.0f;
                this.eO = this.ez * 8.0f;
            }
        }
    }
    
    protected void i(final g g) {
        this.cw = g.bC;
        if (this.l != null) {
            this.cV = 0;
            while (this.cV < this.l.length) {
                this.cA = this.cw / this.l[this.cV + 2];
                this.h[this.cV] = this.l[this.cV] * this.cA + g.bv;
                this.h[this.cV + 1] = this.l[this.cV + 1] * this.cA + g.bw;
                this.h[this.cV + 2] = this.l[this.cV + 2];
                this.cV += 3;
            }
            if (this.bI) {
                this.cV = 0;
                while (this.cV < this.l.length) {
                    this.j[this.cV] = 0;
                    this.df = g.bg * this.l[this.cV];
                    this.dg = g.bi * this.l[this.cV + 2];
                    this.dh = g.bh * this.l[this.cV + 1];
                    this.di = g.bj * this.l[this.cV + 2];
                    if (this.df - this.dg < 0.0f) {
                        final int[] j = this.j;
                        final int cv = this.cV;
                        ++j[cv];
                    }
                    if (this.dh - this.di < 0.0f) {
                        final int[] i = this.j;
                        final int cv2 = this.cV;
                        i[cv2] += 2;
                    }
                    if (this.l[this.cV + 2] > -0.1f) {
                        final int[] k = this.j;
                        final int cv3 = this.cV;
                        k[cv3] += 4;
                    }
                    if (-this.df - this.dg < 0.0f) {
                        final int[] l = this.j;
                        final int cv4 = this.cV;
                        l[cv4] += 8;
                    }
                    if (-this.dh - this.di < 0.0f) {
                        final int[] m = this.j;
                        final int cv5 = this.cV;
                        m[cv5] += 16;
                    }
                    this.cV += 3;
                }
            }
        }
    }
    
    private void h() {
        this.fy = this.eq.n;
        this.fl = this.eq.w;
        this.fi = this.er.w - this.fl;
        if (this.fi > 0) {
            this.fv = this.eq.G;
            this.fw = this.eq.J;
            this.fx = this.eq.M;
            this.fJ = 0;
            while (this.fJ < this.fi) {
                if (this.fy < super.d.Q[this.fl]) {
                    super.d.Q[this.fl] = this.fy;
                    this.b(this.fl, this.fv, this.fw, this.fx);
                }
                ++this.fl;
                this.fy += this.ev;
                this.fv += this.eB;
                this.fw += this.eD;
                this.fx += this.eF;
                ++this.fJ;
            }
        }
    }
    
    private void c(final int n, final int n2, final int n3) {
        this.fy = this.eq.n;
        this.fl = this.eq.w;
        this.fi = this.er.w - this.fl;
        if (this.fi > 0) {
            this.fJ = 0;
            while (this.fJ < this.fi) {
                if (this.fy < super.d.Q[this.fl]) {
                    super.d.Q[this.fl] = this.fy;
                    this.a(this.fl, n, n2, n3);
                }
                ++this.fl;
                this.fy += this.ev;
                ++this.fJ;
            }
        }
    }
    
    private void b(final int n, int n2, int n3, int n4) {
        if (n2 > 133693440) {
            n2 = 133693440;
        }
        else if (n2 < 0) {
            n2 = 0;
        }
        if (n3 > 133693440) {
            n3 = 133693440;
        }
        else if (n3 < 0) {
            n3 = 0;
        }
        if (n4 > 133693440) {
            n4 = 133693440;
        }
        else if (n4 < 0) {
            n4 = 0;
        }
        if (super.o) {
            super.t = super.d.O[n];
            super.v = (super.t >> 16 & 0xFF);
            super.w = (super.t >> 8 & 0xFF);
            super.x = (super.t & 0xFF);
            super.y = super.e.i[super.v] + (n2 >> 19);
            super.z = super.e.i[super.w] + (n3 >> 19);
            super.A = super.e.i[super.x] + (n4 >> 19);
            super.d.O[n] = -16777216 + (super.y << 16) + (super.z << 8) + super.A;
            return;
        }
        super.d.O[n] = -16777216 + (n2 >> 19 << 16) + (n3 >> 19 << 8) + (n4 >> 19);
    }
    
    protected void i() {
        if ((this.be & 0x80) != 0x0 || (this.be & 0x100) != 0x0 || this.cn) {
            this.b();
        }
        for (int i = 0; i < this.v.length; ++i) {
            final int n = i;
            while (this.v[i] != -1) {
                System.arraycopy(this.m, n, this.n[i], 0, 3);
                this.bA[i++] = 1;
                if (i >= this.v.length || this.v[i] == -1) {
                    break;
                }
            }
        }
        for (int j = 0; j < this.bu; ++j) {
            final int[] array = this.by[j];
            final int[] array2 = this.bz[j];
            if (array.length > 1) {
                for (int k = 0; k < array.length - 1; ++k) {
                    for (int l = k + 1; l < array.length; ++l) {
                        this.bv = false;
                        if (this.bw) {
                            this.bv = true;
                        }
                        else {
                            this.bt = this.m[array[k]] * this.m[array[l]] + this.m[array[k] + 1] * this.m[array[l] + 1] + this.m[array[k] + 2] * this.m[array[l] + 2];
                            this.bv = (this.bt > this.cq);
                        }
                        if (this.bv) {
                            final float[] array3 = this.n[array2[k]];
                            final int n2 = 0;
                            array3[n2] += this.m[array[l]];
                            final float[] array4 = this.n[array2[k]];
                            final int n3 = 1;
                            array4[n3] += this.m[array[l] + 1];
                            final float[] array5 = this.n[array2[k]];
                            final int n4 = 2;
                            array5[n4] += this.m[array[l] + 2];
                            final float[] array6 = this.n[array2[l]];
                            final int n5 = 0;
                            array6[n5] += this.m[array[k]];
                            final float[] array7 = this.n[array2[l]];
                            final int n6 = 1;
                            array7[n6] += this.m[array[k] + 1];
                            final float[] array8 = this.n[array2[l]];
                            final int n7 = 2;
                            array8[n7] += this.m[array[k] + 2];
                            final int[] ba = this.bA;
                            final int n8 = array2[k];
                            ++ba[n8];
                            final int[] ba2 = this.bA;
                            final int n9 = array2[l];
                            ++ba2[n9];
                        }
                    }
                }
            }
        }
        for (int n10 = 0; n10 < this.v.length; ++n10) {
            if (this.v[n10] != -1 && this.bA[n10] > 1) {
                final float[] array9 = this.n[n10];
                final int n11 = 0;
                array9[n11] /= this.bA[n10];
                final float[] array10 = this.n[n10];
                final int n12 = 1;
                array10[n12] /= this.bA[n10];
                final float[] array11 = this.n[n10];
                final int n13 = 2;
                array11[n13] /= this.bA[n10];
                MatUtil.normalize(this.n[n10]);
            }
            System.arraycopy(this.n[n10], 0, this.p[n10], 0, 3);
        }
    }
    
    private void a(final int n, int n2, int n3, final int n4, final int n5, int n6, int n7, int n8) {
        if (n6 > 133693440) {
            n6 = 133693440;
        }
        else if (n6 < 0) {
            n6 = 0;
        }
        if (n7 > 133693440) {
            n7 = 133693440;
        }
        else if (n7 < 0) {
            n7 = 0;
        }
        if (n8 > 133693440) {
            n8 = 133693440;
        }
        else if (n8 < 0) {
            n8 = 0;
        }
        if (n2 < 0) {
            n2 = super.e.l.length - 1 + n2 % (super.e.l.length - 1);
        }
        else if (n2 >= super.e.l.length) {
            n2 %= super.e.l.length - 1;
        }
        if (n3 < 0) {
            n3 = super.e.l[0].length - 1 + n3 % (super.e.l[0].length - 1);
        }
        else if (n3 >= super.e.l[0].length) {
            n3 %= super.e.l[0].length - 1;
        }
        if (super.d.q && !super.p) {
            this.a(n4, n5);
        }
        else {
            super.u = super.e.l[n2][n3];
            super.C = super.bh[n2][n3];
            super.D = super.bi[n2][n3];
            super.E = super.bj[n2][n3];
        }
        if (super.e.q == 2 || super.e.q == 4) {
            super.B = super.bg[n2][n3];
            if (super.B == 0 || super.e.e > 0.9999f) {
                return;
            }
        }
        else {
            super.B = 255;
        }
        if (!super.o && super.B == 255) {
            if (super.B == 255) {
                super.y = (int)(super.e.f[0] + super.C * super.d.bI[n6 >> 19]);
                super.z = (int)(super.e.f[1] + super.D * super.d.bI[n7 >> 19]);
                super.A = (int)(super.e.f[2] + super.E * super.d.bI[n8 >> 19]);
                if (super.y > 255) {
                    super.y = 255;
                }
                if (super.z > 255) {
                    super.z = 255;
                }
                if (super.A > 255) {
                    super.A = 255;
                }
                super.d.O[n] = -16777216 + (super.y << 16) + (super.z << 8) + super.A;
            }
            return;
        }
        super.t = super.d.O[n];
        super.v = (super.t >> 16 & 0xFF);
        super.w = (super.t >> 8 & 0xFF);
        super.x = (super.t & 0xFF);
        if (super.B == 255) {
            super.y = super.e.i[super.v] + super.e.j[(int)(super.e.f[0] + super.C * super.d.bI[n6 >> 19])];
            super.z = super.e.i[super.w] + super.e.j[(int)(super.e.f[1] + super.D * super.d.bI[n7 >> 19])];
            super.A = super.e.i[super.x] + super.e.j[(int)(super.e.f[2] + super.E * super.d.bI[n8 >> 19])];
            super.d.O[n] = -16777216 + (super.y << 16) + (super.z << 8) + super.A;
            return;
        }
        super.C = (super.u >> 16 & 0xFF);
        super.D = (super.u >> 8 & 0xFF);
        super.E = (super.u & 0xFF);
        super.G = super.B / 255.0f;
        if (super.o) {
            super.G *= 1.0f - super.e.e;
        }
        super.F = 1.0f - super.G;
        super.y = (int)(super.e.d[0] + super.C * super.d.bI[n6 >> 19]);
        super.z = (int)(super.e.d[1] + super.D * super.d.bI[n7 >> 19]);
        super.A = (int)(super.e.d[2] + super.E * super.d.bI[n8 >> 19]);
        if (super.y > 255) {
            super.y = 255;
        }
        if (super.z > 255) {
            super.z = 255;
        }
        if (super.A > 255) {
            super.A = 255;
        }
        super.y = (int)(super.F * super.v + super.G * super.y);
        super.z = (int)(super.F * super.w + super.G * super.z);
        super.A = (int)(super.F * super.x + super.G * super.A);
        super.d.O[n] = -16777216 + (super.y << 16) + (super.z << 8) + super.A;
    }
    
    private void j() {
        if (this.v == null || this.l == null) {
            return;
        }
        if (this.bQ == null || this.bQ.length != this.v.length) {
            this.bQ = new boolean[this.v.length];
        }
        for (int i = 0; i < this.v.length; ++i) {
            final int n = i + 1;
            final int n2 = i + 2;
            if (this.v[i] != -1 && this.v[n] != -1 && this.v[n2] != -1) {
                final boolean b = this.m[i] * this.l[this.v[i]] + this.m[n] * this.l[this.v[i] + 1] + this.m[n2] * this.l[this.v[i] + 2] < 1.0E-5f;
                this.bQ[i] = b;
                while (++i < this.v.length && this.v[i] != -1) {
                    this.bQ[i] = b;
                }
            }
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        this.cm = true;
        if (!this.solid.g || b) {
            this.cB = 0;
            this.cl = false;
            if (this.bI && (this.j[n4] != this.j[n5] || this.j[n5] != this.j[n6] || this.j[n4] <= 0)) {
                this.cl = true;
            }
            if (!this.bI || (this.bI && this.cl)) {
                if (this.solid.g || (!this.solid.g && b)) {
                    if (this.bI) {
                        this.dc = this.j[n4];
                        this.dd = this.j[n5];
                        this.de = this.j[n6];
                    }
                    this.cf[0] = (int)this.h[n4];
                    this.cf[1] = (int)this.h[n4 + 1];
                    this.cf[3] = (int)this.h[n5];
                    this.cf[4] = (int)this.h[n5 + 1];
                    this.cf[6] = (int)this.h[n6];
                    this.cf[7] = (int)this.h[n6 + 1];
                    this.cg[0] = this.l[n4 + 2];
                    this.cg[1] = this.l[n5 + 2];
                    this.cg[2] = this.l[n6 + 2];
                    if (super.r) {
                        this.ch[0] = this.i[this.w[n]];
                        this.ch[1] = this.i[this.w[n] + 1];
                        this.ch[2] = this.i[this.w[n2]];
                        this.ch[3] = this.i[this.w[n2] + 1];
                        this.ch[4] = this.i[this.w[n3]];
                        this.ch[5] = this.i[this.w[n3] + 1];
                    }
                    if (this.bm) {
                        this.ci[0] = this.s[n][0];
                        this.ci[1] = this.s[n][1];
                        this.ci[2] = this.s[n][2];
                        this.ci[3] = this.s[n2][0];
                        this.ci[4] = this.s[n2][1];
                        this.ci[5] = this.s[n2][2];
                        this.ci[6] = this.s[n3][0];
                        this.ci[7] = this.s[n3][1];
                        this.ci[8] = this.s[n3][2];
                    }
                }
                else {
                    if (this.bI) {
                        this.dc = this.j[n6];
                        this.dd = this.j[n5];
                        this.de = this.j[n4];
                    }
                    this.cf[0] = (int)this.h[n6];
                    this.cf[1] = (int)this.h[n6 + 1];
                    this.cf[3] = (int)this.h[n5];
                    this.cf[4] = (int)this.h[n5 + 1];
                    this.cf[6] = (int)this.h[n4];
                    this.cf[7] = (int)this.h[n4 + 1];
                    this.cg[0] = this.l[n6 + 2];
                    this.cg[1] = this.l[n5 + 2];
                    this.cg[2] = this.l[n4 + 2];
                    if (super.r) {
                        this.ch[0] = this.i[this.w[n3]];
                        this.ch[1] = this.i[this.w[n3] + 1];
                        this.ch[2] = this.i[this.w[n2]];
                        this.ch[3] = this.i[this.w[n2] + 1];
                        this.ch[4] = this.i[this.w[n]];
                        this.ch[5] = this.i[this.w[n] + 1];
                    }
                    if (this.bm) {
                        this.ci[0] = this.s[n3][0];
                        this.ci[1] = this.s[n3][1];
                        this.ci[2] = this.s[n3][2];
                        this.ci[3] = this.s[n2][0];
                        this.ci[4] = this.s[n2][1];
                        this.ci[5] = this.s[n2][2];
                        this.ci[6] = this.s[n][0];
                        this.ci[7] = this.s[n][1];
                        this.ci[8] = this.s[n][2];
                    }
                }
                this.cm = !this.bI;
                if (this.bI && this.dc == 0 && this.dd == 0 && this.de == 0) {
                    this.cm = true;
                }
                if (super.s && this.bH && !this.bm) {
                    this.dj = n;
                    while (this.dj >= 1 && this.coordIndex.a[this.dj - 1] != -1) {
                        --this.dj;
                    }
                    if (this.cv == this.cs) {
                        this.bK = this.r[this.dj];
                        this.bL = this.r[this.dj + 1];
                        this.bM = this.r[this.dj + 2];
                    }
                    else {
                        this.ci[0] = this.r[this.dj];
                        this.ci[1] = this.r[this.dj + 1];
                        this.ci[2] = this.r[this.dj + 2];
                        this.bJ = -16777216 + (this.ci[0] << 16) + (this.ci[1] << 8) + this.ci[2];
                    }
                }
                if (!this.bH && this.bk && this.x.length > 2) {
                    this.dj = n;
                    while (this.dj > 1 && this.coordIndex.a[this.dj - 1] != -1) {
                        --this.dj;
                    }
                    this.bN = (int)(255.0f * this.x[this.dj]);
                    this.bO = (int)(255.0f * this.x[this.dj + 1]);
                    this.bP = (int)(255.0f * this.x[this.dj + 2]);
                    this.bJ = -16777216 + (this.bN << 16) + (this.bO << 8) + this.bP;
                }
                if (this.cm) {
                    this.d();
                    return;
                }
                this.dk = 9;
                this.dl = 6;
                if (this.solid.g || (!this.solid.g && b)) {
                    this.dm[0] = this.l[n4];
                    this.dm[1] = this.l[n4 + 1];
                    this.dm[2] = this.l[n4 + 2];
                    this.dm[6] = this.l[n6];
                    this.dm[7] = this.l[n6 + 1];
                    this.dm[8] = this.l[n6 + 2];
                }
                else {
                    this.dm[0] = this.l[n6];
                    this.dm[1] = this.l[n6 + 1];
                    this.dm[2] = this.l[n6 + 2];
                    this.dm[6] = this.l[n4];
                    this.dm[7] = this.l[n4 + 1];
                    this.dm[8] = this.l[n4 + 2];
                }
                this.dm[3] = this.l[n5];
                this.dm[4] = this.l[n5 + 1];
                this.dm[5] = this.l[n5 + 2];
                if (super.r) {
                    System.arraycopy(this.ch, 0, this.dn, 0, 6);
                }
                if (this.bm) {
                    System.arraycopy(this.ci, 0, this.do, 0, 9);
                }
                if ((this.dc & 0x1) > 0 || (this.dd & 0x1) > 0 || (this.de & 0x1) > 0) {
                    this.a(super.d, 1);
                }
                if ((this.dc & 0x4) > 0 || (this.dd & 0x4) > 0 || (this.de & 0x4) > 0) {
                    this.a(super.d, 4);
                }
                if ((this.dc & 0x2) > 0 || (this.dd & 0x2) > 0 || (this.de & 0x2) > 0) {
                    this.a(super.d, 2);
                }
                if ((this.dc & 0x8) > 0 || (this.dd & 0x8) > 0 || (this.de & 0x8) > 0) {
                    this.a(super.d, 8);
                }
                if ((this.dc & 0x10) > 0 || (this.dd & 0x10) > 0 || (this.de & 0x10) > 0) {
                    this.a(super.d, 16);
                }
                for (int i = 0; i < this.dk; i += 3) {
                    this.cA = this.cw / this.dm[i + 2];
                    this.dm[i] = this.dm[i] * this.cA + super.d.bv;
                    this.dm[i + 1] = this.dm[i + 1] * this.cA + super.d.bw;
                }
                if (this.dk >= 9) {
                    this.cf[0] = (int)this.dm[0];
                    this.cf[1] = (int)this.dm[1];
                    this.cg[0] = this.dm[2];
                    if (super.r) {
                        this.ch[0] = this.dn[0];
                        this.ch[1] = this.dn[1];
                    }
                    if (this.bm) {
                        this.ci[0] = this.do[0];
                        this.ci[1] = this.do[1];
                        this.ci[2] = this.do[2];
                    }
                    this.cf[3] = (int)this.dm[3];
                    this.cf[4] = (int)this.dm[4];
                    this.cg[1] = this.dm[5];
                    if (super.r) {
                        this.ch[2] = this.dn[2];
                        this.ch[3] = this.dn[3];
                    }
                    if (this.bm) {
                        this.ci[3] = this.do[3];
                        this.ci[4] = this.do[4];
                        this.ci[5] = this.do[5];
                    }
                    this.cf[6] = (int)this.dm[6];
                    this.cf[7] = (int)this.dm[7];
                    this.cg[2] = this.dm[8];
                    if (super.r) {
                        this.ch[4] = this.dn[4];
                        this.ch[5] = this.dn[5];
                    }
                    if (this.bm) {
                        this.ci[6] = this.do[6];
                        this.ci[7] = this.do[7];
                        this.ci[8] = this.do[8];
                    }
                    this.d();
                    if (this.dk >= 12) {
                        for (int n7 = 4, j = 6; j < this.dk; j += 3, n7 += 2) {
                            this.cf[3] = (int)this.dm[j];
                            this.cf[4] = (int)this.dm[j + 1];
                            this.cg[1] = this.dm[j + 2];
                            if (super.r) {
                                this.ch[2] = this.dn[n7];
                                this.ch[3] = this.dn[n7 + 1];
                            }
                            if (this.bm) {
                                this.ci[3] = this.do[j];
                                this.ci[4] = this.do[j + 1];
                                this.ci[5] = this.do[j + 2];
                            }
                            if (j + 3 < this.dk) {
                                this.cf[6] = (int)this.dm[j + 3];
                                this.cf[7] = (int)this.dm[j + 4];
                                this.cg[2] = this.dm[j + 5];
                                if (super.r) {
                                    this.ch[4] = this.dn[n7 + 2];
                                    this.ch[5] = this.dn[n7 + 3];
                                }
                                if (this.bm) {
                                    this.ci[6] = this.do[j + 3];
                                    this.ci[7] = this.do[j + 4];
                                    this.ci[8] = this.do[j + 5];
                                }
                                this.d();
                            }
                        }
                    }
                }
            }
        }
    }
}
