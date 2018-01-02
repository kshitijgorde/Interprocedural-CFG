// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class m implements ab
{
    public static final float U = 0.0f;
    public static final int[] M;
    public static final int u = 16;
    public static final int s = 4;
    public static final float[] F;
    public static final float[] E;
    protected y ad;
    protected ae C;
    protected h[] aa;
    protected int t;
    protected int y;
    protected int z;
    protected int T;
    protected int Q;
    protected int o;
    protected int n;
    protected int X;
    protected int N;
    protected int ae;
    protected float J;
    protected float I;
    protected float ac;
    protected float w;
    protected float S;
    protected float V;
    protected float[] A;
    protected int Z;
    protected float[] m;
    protected int K;
    protected float[] x;
    protected float[] ab;
    protected float[] v;
    protected float[] W;
    protected float[] B;
    protected float[] D;
    protected float[] R;
    protected float[] r;
    protected float[] P;
    protected float[] O;
    protected float[] L;
    protected float q;
    protected float p;
    protected float Y;
    protected int G;
    protected int H;
    
    public m() {
        this.ad = new y();
        this.C = new ae();
    }
    
    public void for() {
        this.aa = do();
        this.t = 5;
        this.a(160, 40, 10, 640);
    }
    
    protected void a(final int z, final int t, final int n, final int x) {
        this.y = 1;
        this.z = z;
        this.o = z * 3 / 2;
        this.T = t;
        this.Q = z / t;
        this.n = n;
        this.X = x;
        this.N = 17;
        this.ae = 144;
        this.S = 0.0f;
        this.V = 0.0f;
        this.J = 0.9f;
        this.I = 0.6f;
        this.ac = 0.01f;
        this.w = 1.0001f;
        this.A = new float[x];
        this.Z = x - this.o;
        this.m = new float[x];
        this.K = x - this.o;
        this.x = new float[z];
        this.ab = new float[n + 1];
        this.v = new float[n];
        this.W = new float[n];
        this.B = new float[n];
        this.D = new float[n + 1];
        this.R = new float[5 * n];
        this.r = new float[this.Q];
        this.P = new float[n + 1];
        this.O = new float[n + 1];
        this.L = new float[n + 1];
        final float q = 0.0f;
        this.Y = q;
        this.p = q;
        this.q = q;
        this.G = 0;
        this.H = 0;
    }
    
    private static h[] do() {
        final d d = new d(ab.g, 7, 7);
        final d d2 = new d(ab.for, 5, 0);
        final d d3 = new d(ab.for, 5, 7);
        final d d4 = new d(ab.for, 5, 7);
        final x x = new x();
        final ac ac = new ac();
        final u u = new u(40, 10, 4, ab.e, 4, 0);
        final u u2 = new u(40, 10, 4, ab.a, 5, 0);
        final u u3 = new u(40, 5, 8, ab.f, 6, 0);
        final u u4 = new u(40, 8, 5, ab.k, 7, 0);
        final u u5 = new u(40, 5, 8, ab.void, 8, 0);
        final u u6 = new u(40, 20, 2, ab.c, 5, 0);
        final k k = new k();
        final t t = new t();
        return new h[] { null, new h(0, 1, 0, 0, t, x, ac, 0.7f, 0.7f, -1.0f, 43), new h(0, 0, 0, 0, t, d2, u, 0.7f, 0.5f, 0.55f, 119), new h(-1, 0, 1, 0, t, d3, u2, 0.7f, 0.55f, 0.45f, 160), new h(-1, 0, 1, 0, t, d4, u4, 0.7f, 0.63f, 0.35f, 220), new h(-1, 0, 3, 0, k, d, u3, 0.7f, 0.65f, 0.25f, 300), new h(-1, 0, 3, 0, k, d, u5, 0.68f, 0.65f, 0.1f, 364), new h(-1, 0, 3, 1, k, d, u3, 0.65f, 0.65f, -1.0f, 492), new h(0, 1, 0, 0, t, x, u6, 0.7f, 0.5f, 0.65f, 79), null, null, null, null, null, null, null };
    }
    
    public int try() {
        return this.z;
    }
    
    public boolean a() {
        return this.H != 0;
    }
    
    public float[] new() {
        return this.r;
    }
    
    public float[] int() {
        final float[] array = new float[this.z];
        System.arraycopy(this.m, this.K, array, 0, this.z);
        return array;
    }
    
    public float[] if() {
        return this.x;
    }
    
    static {
        M = new int[] { 5, 43, 119, 160, 220, 300, 364, 492, 79, 1, 1, 1, 1, 1, 1, 1 };
        F = new float[] { -0.35f, 0.05f };
        E = new float[] { -2.79475f, -1.81066f, -1.16985f, -0.848119f, -0.58719f, -0.329818f, -0.063266f, 0.282826f };
    }
}
