// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public class bK implements aO
{
    public static final int[] C;
    public static final float[] D;
    public static final float[] E;
    protected bX F;
    protected ba G;
    protected bU[] H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected int O;
    protected int P;
    protected int Q;
    protected int R;
    protected float S;
    protected float T;
    protected float U;
    protected float V;
    protected float W;
    protected float X;
    protected float[] Y;
    protected int Z;
    protected float[] aa;
    protected int ab;
    protected float[] ac;
    protected float[] ad;
    protected float[] ae;
    protected float[] af;
    protected float[] ag;
    protected float[] ah;
    protected float[] ai;
    protected float[] aj;
    protected float[] ak;
    protected float[] al;
    protected float[] am;
    protected float an;
    protected float ao;
    protected float ap;
    protected int aq;
    protected int ar;
    
    public bK() {
        this.F = new bX();
        this.G = new ba();
    }
    
    public final void g() {
        final cv cv = new cv(aO.p, 7, 7);
        final cv cv2 = new cv(aO.q, 5, 0);
        final cv cv3 = new cv(aO.q, 5, 7);
        final cv cv4 = new cv(aO.q, 5, 7);
        final cC cc = new cC();
        final dD dd = new dD();
        final cd cd = new cd(40, 10, 4, aO.k, 4, 0);
        final cd cd2 = new cd(40, 10, 4, aO.l, 5, 0);
        final cd cd3 = new cd(40, 5, 8, aO.n, 6, 0);
        final cd cd4 = new cd(40, 8, 5, aO.o, 7, 0);
        final cd cd5 = new cd(40, 5, 8, aO.m, 8, 0);
        final cd cd6 = new cd(40, 20, 2, aO.j, 5, 0);
        final dt dt = new dt();
        final aC ac = new aC();
        final bU[] h;
        (h = new bU[16])[1] = new bU(0, 1, 0, 0, ac, cc, dd, 0.7f, 0.7f, -1.0f, 43);
        h[2] = new bU(0, 0, 0, 0, ac, cv2, cd, 0.7f, 0.5f, 0.55f, 119);
        h[3] = new bU(-1, 0, 1, 0, ac, cv3, cd2, 0.7f, 0.55f, 0.45f, 160);
        h[4] = new bU(-1, 0, 1, 0, ac, cv4, cd4, 0.7f, 0.63f, 0.35f, 220);
        h[5] = new bU(-1, 0, 3, 0, dt, cv, cd3, 0.7f, 0.65f, 0.25f, 300);
        h[6] = new bU(-1, 0, 3, 0, dt, cv, cd5, 0.68f, 0.65f, 0.1f, 364);
        h[7] = new bU(-1, 0, 3, 1, dt, cv, cd3, 0.65f, 0.65f, -1.0f, 492);
        h[8] = new bU(0, 1, 0, 0, ac, cc, cd6, 0.7f, 0.5f, 0.65f, 79);
        this.H = h;
        this.I = 5;
        this.a(160, 40, 10, 640);
    }
    
    protected void a(final int k, final int l, final int o, final int p4) {
        this.J = 1;
        this.K = k;
        this.N = k * 3 / 2;
        this.L = l;
        this.M = k / l;
        this.O = o;
        this.P = p4;
        this.Q = 17;
        this.R = 144;
        this.W = 0.0f;
        this.X = 0.0f;
        this.S = 0.9f;
        this.T = 0.6f;
        this.U = 0.01f;
        this.V = 1.0001f;
        this.Y = new float[p4];
        this.Z = p4 - this.N;
        this.aa = new float[p4];
        this.ab = p4 - this.N;
        this.ac = new float[k];
        this.ad = new float[o + 1];
        this.ae = new float[o];
        this.af = new float[o];
        this.ag = new float[o];
        this.ah = new float[o + 1];
        this.ai = new float[o * 5];
        this.aj = new float[this.M];
        this.ak = new float[o + 1];
        this.al = new float[o + 1];
        this.am = new float[o + 1];
        final float an = 0.0f;
        this.ap = an;
        this.ao = an;
        this.an = an;
        this.aq = 0;
        this.ar = 0;
    }
    
    public int c() {
        return this.K;
    }
    
    public boolean d() {
        return this.ar != 0;
    }
    
    public final float[] h() {
        return this.aj;
    }
    
    public float[] e() {
        final float[] array = new float[this.K];
        System.arraycopy(this.aa, this.ab, array, 0, this.K);
        return array;
    }
    
    public float[] f() {
        return this.ac;
    }
    
    static {
        C = new int[] { 5, 43, 119, 160, 220, 300, 364, 492, 79, 1, 1, 1, 1, 1, 1, 1 };
        D = new float[] { -0.35f, 0.05f };
        E = new float[] { -2.79475f, -1.81066f, -1.16985f, -0.848119f, -0.58719f, -0.329818f, -0.063266f, 0.282826f };
    }
}
