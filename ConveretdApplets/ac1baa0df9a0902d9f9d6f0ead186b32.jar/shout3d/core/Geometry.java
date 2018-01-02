// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public abstract class Geometry extends Node
{
    float[] a;
    float[] b;
    boolean c;
    g d;
    Appearance e;
    static final float f = -0.1f;
    static final short g = 1;
    static final short h = 2;
    static final short i = 4;
    static final short j = 8;
    static final short k = 16;
    float[] l;
    static int m;
    float n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int E;
    float F;
    float G;
    int H;
    int I;
    int J;
    short K;
    short L;
    short M;
    short N;
    short O;
    short P;
    short Q;
    short R;
    short S;
    short T;
    short U;
    short V;
    short W;
    short X;
    short Y;
    short Z;
    short ba;
    short bb;
    short bc;
    short bd;
    int be;
    int bf;
    protected short[][] bg;
    protected short[][] bh;
    protected short[][] bi;
    protected short[][] bj;
    
    void a() {
        int n = 1;
        do {
            this.l[n] = 65536.0f / n;
        } while (++n < 8);
    }
    
    void a(final int n, final int n2, final int n3) {
        if (this.d.p && !this.p) {
            this.a(n2, n3);
            this.d.O[n] = -16777216 + (this.C << 16) + (this.D << 8) + this.E;
            return;
        }
        this.be = n2 >> 16;
        this.bf = n3 >> 16;
        if (this.be < 0) {
            this.be = this.e.l.length - 1 + this.be % (this.e.l.length - 1);
        }
        else if (this.be >= this.e.l.length) {
            this.be %= this.e.l.length - 1;
        }
        if (this.bf < 0) {
            this.bf = this.e.l[0].length - 1 + this.bf % (this.e.l[0].length - 1);
        }
        else if (this.bf >= this.e.l[0].length) {
            this.bf %= this.e.l[0].length - 1;
        }
        if (!this.p) {
            this.d.O[n] = this.e.l[this.be][this.bf];
            return;
        }
        this.B = this.bg[this.be][this.bf];
        if (this.B == 0) {
            return;
        }
        if (this.B == 255) {
            this.d.O[n] = this.e.l[this.be][this.bf];
            return;
        }
        this.t = this.d.O[n];
        this.v = (short)(this.t >> 16 & 0xFF);
        this.w = (short)(this.t >> 8 & 0xFF);
        this.x = (short)(this.t & 0xFF);
        this.C = this.bh[this.be][this.bf];
        this.D = this.bi[this.be][this.bf];
        this.E = this.bj[this.be][this.bf];
        this.F = (255 - this.B) / 255.0f;
        this.y = (short)(this.F * this.v + this.C);
        this.z = (short)(this.F * this.w + this.D);
        this.A = (short)(this.F * this.x + this.E);
        if (this.y > 255) {
            this.y = 255;
        }
        if (this.z > 255) {
            this.z = 255;
        }
        if (this.A > 255) {
            this.A = 255;
        }
        this.d.O[n] = -16777216 + (this.y << 16) + (this.z << 8) + this.A;
    }
    
    public Geometry() {
        this.a = new float[] { Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE };
        this.b = new float[] { -3.4028235E38f, -3.4028235E38f, -3.4028235E38f };
        this.c = false;
        this.l = new float[8];
        this.n = 0.001960392f;
        this.o = false;
        this.p = false;
        this.q = true;
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = Math.round(32761.447f);
        this.a();
    }
    
    protected void c(final g g) {
        Field field = null;
        try {
            field = this.getField("coord");
        }
        catch (Exception ex) {}
        this.a[0] = Float.MAX_VALUE;
        this.b[0] = -3.4028235E38f;
        this.a[1] = Float.MAX_VALUE;
        this.b[1] = -3.4028235E38f;
        this.a[2] = Float.MAX_VALUE;
        this.b[2] = -3.4028235E38f;
        this.c = false;
        if (field != null && ((NodeField)field).a != null && ((NodeField)field).a instanceof Coordinate) {
            final Coordinate coordinate = (Coordinate)((NodeField)field).a;
            if (coordinate.point.a != null) {
                final float[] a = coordinate.point.a;
                if (a != null && a.length > 0) {
                    this.c = true;
                    for (int i = 0; i < a.length; ++i) {
                        if (a[i] < this.a[0]) {
                            this.a[0] = a[i];
                        }
                        if (a[i] > this.b[0]) {
                            this.b[0] = a[i];
                        }
                        ++i;
                        if (a[i] < this.a[1]) {
                            this.a[1] = a[i];
                        }
                        if (a[i] > this.b[1]) {
                            this.b[1] = a[i];
                        }
                        ++i;
                        if (a[i] < this.a[2]) {
                            this.a[2] = a[i];
                        }
                        if (a[i] > this.b[2]) {
                            this.b[2] = a[i];
                        }
                    }
                }
            }
        }
    }
    
    void a(int n, int n2) {
        n -= this.H;
        n2 -= this.H;
        this.be = n >> 16;
        this.bf = n2 >> 16;
        this.K = (short)((n >> this.d.v) - (this.be << this.d.u));
        this.L = (short)((n2 >> this.d.v) - (this.bf << this.d.u));
        this.I = this.be + 1;
        this.J = this.bf + 1;
        if (this.be < 0) {
            this.be = this.e.l.length - 1 + this.be % (this.e.l.length - 1);
        }
        else if (this.be >= this.e.l.length) {
            this.be %= this.e.l.length - 1;
        }
        if (this.bf < 0) {
            this.bf = this.e.l[0].length - 1 + this.bf % (this.e.l[0].length - 1);
        }
        else if (this.bf >= this.e.l[0].length) {
            this.bf %= this.e.l[0].length - 1;
        }
        if (this.I < 0) {
            this.I = this.e.l.length - 1 + this.I % (this.e.l.length - 1);
        }
        else if (this.I >= this.e.l.length) {
            this.I %= this.e.l.length - 1;
        }
        if (this.J < 0) {
            this.J = this.e.l[0].length - 1 + this.J % (this.e.l[0].length - 1);
        }
        else if (this.J >= this.e.l[0].length) {
            this.J %= this.e.l[0].length - 1;
        }
        this.O = this.bh[this.be][this.bf];
        this.P = this.bh[this.I][this.bf];
        this.Q = this.bh[this.be][this.J];
        this.R = this.bh[this.I][this.J];
        this.M = (short)(this.O + this.d.w[this.K][this.P - this.O + 255]);
        this.N = (short)(this.Q + this.d.w[this.K][this.R - this.Q + 255]);
        this.C = (short)(this.M + this.d.w[this.L][this.N - this.M + 255]);
        this.U = this.bi[this.be][this.bf];
        this.V = this.bi[this.I][this.bf];
        this.W = this.bi[this.be][this.J];
        this.X = this.bi[this.I][this.J];
        this.S = (short)(this.U + this.d.w[this.K][this.V - this.U + 255]);
        this.T = (short)(this.W + this.d.w[this.K][this.X - this.W + 255]);
        this.D = (short)(this.S + this.d.w[this.L][this.T - this.S + 255]);
        this.ba = this.bj[this.be][this.bf];
        this.bb = this.bj[this.I][this.bf];
        this.bc = this.bj[this.be][this.J];
        this.bd = this.bj[this.I][this.J];
        this.Y = (short)(this.ba + this.d.w[this.K][this.bb - this.ba + 255]);
        this.Z = (short)(this.bc + this.d.w[this.K][this.bd - this.bc + 255]);
        this.E = (short)(this.Y + this.d.w[this.L][this.Z - this.Y + 255]);
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        this.t = this.d.O[n];
        this.v = (short)(this.t >> 16 & 0xFF);
        this.w = (short)(this.t >> 8 & 0xFF);
        this.x = (short)(this.t & 0xFF);
        this.y = (short)(this.e.i[this.v] + n2);
        this.z = (short)(this.e.i[this.w] + n3);
        this.A = (short)(this.e.i[this.x] + n4);
        this.d.O[n] = -16777216 + (this.y << 16) + (this.z << 8) + this.A;
    }
    
    static {
        Geometry.m = 0;
    }
    
    void b(final int n, final int n2) {
        this.d.O[n] = n2;
    }
    
    void b(final int n, int n2, int n3) {
        if (n2 < 0) {
            n2 = this.e.l.length - 1 + n2 % (this.e.l.length - 1);
        }
        else if (n2 >= this.e.l.length) {
            n2 %= this.e.l.length - 1;
        }
        if (n3 < 0) {
            n3 = this.e.l[0].length - 1 + n3 % (this.e.l[0].length - 1);
        }
        else if (n3 >= this.e.l[0].length) {
            n3 %= this.e.l[0].length - 1;
        }
        if (this.e.q == 2 || this.e.q == 4) {
            this.B = this.bg[n2][n3];
            if (this.B == 0 || this.e.e > 0.9999f) {
                return;
            }
        }
        else {
            this.B = 255;
        }
        this.u = this.e.l[n2][n3];
        this.C = this.bh[n2][n3];
        this.D = this.bi[n2][n3];
        this.E = this.bj[n2][n3];
        if (this.B == 255) {
            this.y = (short)(this.e.f[0] + this.C);
            this.z = (short)(this.e.f[1] + this.D);
            this.A = (short)(this.e.f[2] + this.E);
            if (this.y > 255) {
                this.y = 255;
            }
            if (this.z > 255) {
                this.z = 255;
            }
            if (this.A > 255) {
                this.A = 255;
            }
            this.d.O[n] = -16777216 + (this.y << 16) + (this.z << 8) + this.A;
            return;
        }
        this.t = this.d.O[n];
        this.v = (short)(this.t >> 16 & 0xFF);
        this.w = (short)(this.t >> 8 & 0xFF);
        this.x = (short)(this.t & 0xFF);
        this.F = (255 - this.B) / 255.0f;
        this.G = 1.0f - this.F;
        this.y = (short)(this.F * this.v + this.G * this.e.f[0] + this.C);
        this.z = (short)(this.F * this.w + this.G * this.e.f[1] + this.D);
        this.A = (short)(this.F * this.x + this.G * this.e.f[2] + this.E);
        if (this.y > 255) {
            this.y = 255;
        }
        if (this.z > 255) {
            this.z = 255;
        }
        if (this.A > 255) {
            this.A = 255;
        }
        this.d.O[n] = -16777216 + (this.y << 16) + (this.z << 8) + this.A;
    }
}
