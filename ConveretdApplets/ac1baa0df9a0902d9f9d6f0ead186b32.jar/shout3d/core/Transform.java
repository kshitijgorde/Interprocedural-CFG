// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class Transform extends Group implements FieldObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final float[] defaultCenter;
    public final float[] defaultRotation;
    public final float[] defaultScale;
    public final float[] defaultScaleOrientation;
    public final float[] defaultTranslation;
    public final FloatArrayField center;
    public final FloatArrayField rotation;
    public final FloatArrayField scale;
    public final FloatArrayField scaleOrientation;
    public final FloatArrayField translation;
    private float[] g;
    private boolean j;
    private boolean l;
    private boolean h;
    private boolean i;
    private float[] p;
    protected float[] q;
    protected boolean k;
    protected boolean m;
    private boolean n;
    private boolean o;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private float H;
    private float I;
    private float J;
    private float K;
    private float L;
    private float M;
    private float N;
    private float O;
    private float P;
    private float Q;
    private float R;
    private float S;
    private float T;
    private float U;
    private float V;
    private float W;
    private float X;
    private float Y;
    private float Z;
    private float ba;
    private float bb;
    private float bc;
    private float bd;
    private float be;
    private float bf;
    private float bg;
    private float bh;
    private float bi;
    private float bj;
    private float bk;
    private float bl;
    private float bm;
    private float bn;
    private float bo;
    private float bp;
    private float bq;
    private float br;
    private float bs;
    private float bt;
    protected float[] bu;
    protected boolean bv;
    
    public Transform() {
        this.defaultCenter = new float[3];
        this.defaultRotation = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.defaultScale = new float[] { 1.0f, 1.0f, 1.0f };
        this.defaultScaleOrientation = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.defaultTranslation = new float[3];
        this.center = new FloatArrayField(this, "center", 6, this.defaultCenter);
        this.rotation = new FloatArrayField(this, "rotation", 21, this.defaultRotation);
        this.scale = new FloatArrayField(this, "scale", 23, this.defaultScale);
        this.scaleOrientation = new FloatArrayField(this, "scaleOrientation", 21, this.defaultScaleOrientation);
        this.translation = new FloatArrayField(this, "translation", 6, this.defaultTranslation);
        this.p = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.q = new float[12];
        this.k = false;
        this.n = false;
        this.o = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = true;
        this.D = Float.MAX_VALUE;
        this.E = Float.MAX_VALUE;
        this.F = Float.MAX_VALUE;
        this.G = Float.MAX_VALUE;
        this.H = Float.MAX_VALUE;
        this.I = Float.MAX_VALUE;
        this.bu = new float[12];
        this.bv = false;
        this.center.addFieldObserver(this, null);
        this.rotation.addFieldObserver(this, null);
        this.scale.addFieldObserver(this, null);
        this.scaleOrientation.addFieldObserver(this, null);
        this.translation.addFieldObserver(this, null);
    }
    
    protected void h(final g g) {
        if (!this.k) {
            this.d();
        }
        this.g = g.b();
        this.j = g.g();
        this.l = g.A();
        this.h = false;
        this.i = false;
        if (this.s && (this.scale.a[0] != this.scale.a[1] || this.scale.a[0] != this.scale.a[2])) {
            this.h = true;
            if (this.t) {
                this.i = true;
            }
        }
        if (this.l && this.o) {
            this.i = true;
        }
        g.c(this.h || this.l);
        g.b(this.i || this.j);
        System.arraycopy(this.q, 0, this.p, 0, 12);
        g.a(this.p = MatUtil.h(this.p, this.g));
    }
    
    protected void i(final g g) {
        g.a(this.g);
        g.b(this.j);
        g.c(this.l);
    }
    
    public float[] getMatrix() {
        if (!this.k) {
            this.d();
        }
        return MatUtil.d(this.q);
    }
    
    public float[] getInverseMatrix() {
        if (!this.bv) {
            this.e();
        }
        return MatUtil.d(this.bu);
    }
    
    public void a(final float[] array) {
        if (!this.k) {
            this.d();
        }
        System.arraycopy(this.q, 0, super.B, 0, 12);
        super.B = MatUtil.h(super.B, array);
        if (super.children.a == null) {
            return;
        }
        for (int i = 0; i < super.children.a.length; ++i) {
            if (super.children.a[i] instanceof Group) {
                ((Group)super.children.a[i]).a(super.B);
            }
        }
    }
    
    public void onFieldChange(final Field field, final Object o) {
        this.bv = false;
        this.k = false;
        if (field == this.rotation || field == this.scaleOrientation) {
            final float[] array = (field == this.rotation) ? this.rotation.a : this.scaleOrientation.a;
            if (array == null || array.length < 3) {
                return;
            }
            if (Math.abs(1.0f - (array[0] * array[0] + array[1] * array[1] + array[2] * array[2])) > 0.05f) {
                MatUtil.normalize(array);
            }
        }
    }
    
    public void b(final g g) {
        this.h(g);
        super.b(g);
        this.i(g);
    }
    
    protected void d() {
        if (this.v) {
            this.D = this.translation.a[0];
            this.E = this.translation.a[1];
            this.F = this.translation.a[2];
            this.z = this.rotation.a[0];
            this.A = this.rotation.a[1];
            this.B = this.rotation.a[2];
            this.C = this.rotation.a[3];
            this.w = this.center.a[0];
            this.x = this.center.a[1];
            this.y = this.center.a[2];
            this.G = this.scale.a[0];
            this.H = this.scale.a[1];
            this.I = this.scale.a[2];
            this.J = this.scaleOrientation.a[0];
            this.K = this.scaleOrientation.a[1];
            this.L = this.scaleOrientation.a[2];
            this.M = this.scaleOrientation.a[3];
            this.u = true;
            this.v = false;
        }
        else {
            this.u = false;
            if (this.D != this.translation.a[0] || this.E != this.translation.a[1] || this.F != this.translation.a[2]) {
                this.u = true;
                this.D = this.translation.a[0];
                this.E = this.translation.a[1];
                this.F = this.translation.a[2];
            }
            if (this.z != this.rotation.a[0] || this.A != this.rotation.a[1] || this.B != this.rotation.a[2] || this.C != this.rotation.a[3]) {
                this.u = true;
                this.z = this.rotation.a[0];
                this.A = this.rotation.a[1];
                this.B = this.rotation.a[2];
                this.C = this.rotation.a[3];
            }
            if (this.w != this.center.a[0] || this.x != this.center.a[1] || this.y != this.center.a[2]) {
                this.u = true;
                this.w = this.center.a[0];
                this.x = this.center.a[1];
                this.y = this.center.a[2];
            }
            if (this.G != this.scale.a[0] || this.H != this.scale.a[1] || this.I != this.scale.a[2]) {
                this.u = true;
                this.G = this.scale.a[0];
                this.H = this.scale.a[1];
                this.I = this.scale.a[2];
            }
            if (this.J != this.scaleOrientation.a[0] || this.K != this.scaleOrientation.a[1] || this.L != this.scaleOrientation.a[2] || this.M != this.scaleOrientation.a[3]) {
                this.u = true;
                this.J = this.scaleOrientation.a[0];
                this.K = this.scaleOrientation.a[1];
                this.L = this.scaleOrientation.a[2];
                this.M = this.scaleOrientation.a[3];
            }
        }
        if (!this.u) {
            return;
        }
        this.r = false;
        this.o = false;
        this.n = false;
        this.s = false;
        this.t = false;
        if (this.translation.a[0] != 0.0f || this.translation.a[1] != 0.0f || this.translation.a[2] != 0.0f) {
            this.r = true;
        }
        if (this.rotation.a[0] != 0.0f || this.rotation.a[1] != 0.0f || this.rotation.a[2] != 1.0f || this.rotation.a[3] != 0.0f) {
            this.o = true;
        }
        if (this.center.a[0] != 0.0f || this.center.a[1] != 0.0f || this.center.a[2] != 0.0f) {
            this.n = true;
        }
        if (this.scale.a[0] != 1.0f || this.scale.a[1] != 1.0f || this.scale.a[2] != 1.0f) {
            this.s = true;
        }
        if (this.scaleOrientation.a[0] != 0.0f || this.scaleOrientation.a[1] != 0.0f || this.scaleOrientation.a[2] != 1.0f || this.scaleOrientation.a[3] != 0.0f) {
            this.t = true;
        }
        this.m = (!this.n && !this.t && !this.s && !this.o && !this.r);
        MatUtil.b(this.q);
        if (!this.m) {
            if (this.r) {
                this.q[9] = this.D;
                this.q[10] = this.E;
                this.q[11] = this.F;
            }
            if (this.n && (this.o || this.s || this.t)) {
                final float[] q = this.q;
                final int n = 9;
                q[n] += this.w;
                final float[] q2 = this.q;
                final int n2 = 10;
                q2[n2] += this.x;
                final float[] q3 = this.q;
                final int n3 = 11;
                q3[n3] += this.y;
            }
            if (this.o) {
                this.z = -this.z;
                this.A = -this.A;
                this.B = -this.B;
                this.bf = (float)Math.sin(this.C);
                this.bg = (float)Math.cos(this.C);
                this.bh = 1.0f - this.bg;
                this.bi = this.z * this.bh;
                this.bj = this.A * this.bh;
                this.bk = this.B * this.bh;
                this.bl = this.z * this.bf;
                this.bm = this.A * this.bf;
                this.bn = this.B * this.bf;
                this.q[0] = this.z * this.bi + this.bg;
                this.q[1] = this.A * this.bi - this.bn;
                this.q[2] = this.B * this.bi + this.bm;
                this.q[3] = this.z * this.bj + this.bn;
                this.q[4] = this.A * this.bj + this.bg;
                this.q[5] = this.B * this.bj - this.bl;
                this.q[6] = this.z * this.bk - this.bm;
                this.q[7] = this.A * this.bk + this.bl;
                this.q[8] = this.B * this.bk + this.bg;
                this.z = -this.z;
                this.A = -this.A;
                this.B = -this.B;
            }
            if (this.t) {
                this.J = -this.J;
                this.K = -this.K;
                this.L = -this.L;
                this.bf = (float)Math.sin(this.M);
                this.bg = (float)Math.cos(this.M);
                this.bh = 1.0f - this.bg;
                this.bo = this.J * this.bh;
                this.bp = this.K * this.bh;
                this.bq = this.L * this.bh;
                this.br = this.J * this.bf;
                this.bs = this.K * this.bf;
                this.bt = this.L * this.bf;
                this.W = this.J * this.bo + this.bg;
                this.X = this.K * this.bo - this.bt;
                this.Y = this.L * this.bo + this.bs;
                this.Z = this.J * this.bp + this.bt;
                this.ba = this.K * this.bp + this.bg;
                this.bb = this.L * this.bp - this.br;
                this.bc = this.J * this.bq - this.bs;
                this.bd = this.K * this.bq + this.br;
                this.be = this.L * this.bq + this.bg;
                this.N = this.q[0];
                this.O = this.q[1];
                this.P = this.q[2];
                this.Q = this.q[3];
                this.R = this.q[4];
                this.S = this.q[5];
                this.T = this.q[6];
                this.U = this.q[7];
                this.V = this.q[8];
                this.q[0] = this.W * this.N + this.X * this.Q + this.Y * this.T;
                this.q[1] = this.W * this.O + this.X * this.R + this.Y * this.U;
                this.q[2] = this.W * this.P + this.X * this.S + this.Y * this.V;
                this.q[3] = this.Z * this.N + this.ba * this.Q + this.bb * this.T;
                this.q[4] = this.Z * this.O + this.ba * this.R + this.bb * this.U;
                this.q[5] = this.Z * this.P + this.ba * this.S + this.bb * this.V;
                this.q[6] = this.bc * this.N + this.bd * this.Q + this.be * this.T;
                this.q[7] = this.bc * this.O + this.bd * this.R + this.be * this.U;
                this.q[8] = this.bc * this.P + this.bd * this.S + this.be * this.V;
                this.J = -this.J;
                this.K = -this.K;
                this.L = -this.L;
            }
            if (this.s) {
                if (this.G > 0.0f && this.G != 1.0f) {
                    final float[] q4 = this.q;
                    final int n4 = 0;
                    q4[n4] *= this.G;
                    final float[] q5 = this.q;
                    final int n5 = 1;
                    q5[n5] *= this.G;
                    final float[] q6 = this.q;
                    final int n6 = 2;
                    q6[n6] *= this.G;
                }
                if (this.H > 0.0f && this.H != 1.0f) {
                    final float[] q7 = this.q;
                    final int n7 = 3;
                    q7[n7] *= this.H;
                    final float[] q8 = this.q;
                    final int n8 = 4;
                    q8[n8] *= this.H;
                    final float[] q9 = this.q;
                    final int n9 = 5;
                    q9[n9] *= this.H;
                }
                if (this.I > 0.0f && this.I != 1.0f) {
                    final float[] q10 = this.q;
                    final int n10 = 6;
                    q10[n10] *= this.I;
                    final float[] q11 = this.q;
                    final int n11 = 7;
                    q11[n11] *= this.I;
                    final float[] q12 = this.q;
                    final int n12 = 8;
                    q12[n12] *= this.I;
                }
            }
            if (this.t) {
                this.X += this.bt + this.bt;
                this.Y -= this.bs + this.bs;
                this.Z -= this.bt + this.bt;
                this.bb += this.br + this.br;
                this.bc += this.bs + this.bs;
                this.bd -= this.br + this.br;
                this.N = this.q[0];
                this.O = this.q[1];
                this.P = this.q[2];
                this.Q = this.q[3];
                this.R = this.q[4];
                this.S = this.q[5];
                this.T = this.q[6];
                this.U = this.q[7];
                this.V = this.q[8];
                this.q[0] = this.W * this.N + this.X * this.Q + this.Y * this.T;
                this.q[1] = this.W * this.O + this.X * this.R + this.Y * this.U;
                this.q[2] = this.W * this.P + this.X * this.S + this.Y * this.V;
                this.q[3] = this.Z * this.N + this.ba * this.Q + this.bb * this.T;
                this.q[4] = this.Z * this.O + this.ba * this.R + this.bb * this.U;
                this.q[5] = this.Z * this.P + this.ba * this.S + this.bb * this.V;
                this.q[6] = this.bc * this.N + this.bd * this.Q + this.be * this.T;
                this.q[7] = this.bc * this.O + this.bd * this.R + this.be * this.U;
                this.q[8] = this.bc * this.P + this.bd * this.S + this.be * this.V;
            }
            if (this.n && (this.o || this.s || this.t)) {
                this.N = -this.w;
                this.O = -this.x;
                this.P = -this.y;
                final float[] q13 = this.q;
                final int n13 = 9;
                q13[n13] += this.N * this.q[0] + this.O * this.q[3] + this.P * this.q[6];
                final float[] q14 = this.q;
                final int n14 = 10;
                q14[n14] += this.N * this.q[1] + this.O * this.q[4] + this.P * this.q[7];
                final float[] q15 = this.q;
                final int n15 = 11;
                q15[n15] += this.N * this.q[2] + this.O * this.q[5] + this.P * this.q[8];
            }
            this.bv = false;
        }
        this.bv = false;
        this.k = true;
    }
    
    protected void e() {
        if (this.bv) {
            return;
        }
        if (!this.k) {
            this.d();
        }
        MatUtil.b(this.bu);
        if (this.m) {
            return;
        }
        MatUtil.j(this.q, this.bu);
        this.bv = true;
    }
    
    public float[] b() {
        if (!this.k) {
            this.d();
        }
        return this.q;
    }
    
    public float[] c() {
        if (!this.bv) {
            this.e();
        }
        return this.bu;
    }
}
