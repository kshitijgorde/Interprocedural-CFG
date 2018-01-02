// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class c extends m
{
    public static final int[] am;
    public static final int an = 8;
    public static final int ag = 3;
    public static final int ah = 64;
    protected int ak;
    protected float ao;
    protected float[] ai;
    protected float[] aq;
    protected float[] ap;
    protected float[] af;
    protected float[] al;
    protected float[] aj;
    
    public void goto() {
        this.aa = case();
        this.t = 3;
    }
    
    public void char() {
        this.aa = else();
        this.t = 1;
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4, final float ao) {
        super.a(n, n2, n3, n4);
        this.ak = 2 * n;
        this.ao = ao;
        this.ac = 0.002f;
        this.ai = new float[this.ak];
        this.aq = new float[this.ak];
        this.ap = new float[this.ak];
        this.af = new float[n];
        this.al = new float[64];
        this.aj = new float[64];
    }
    
    protected static h[] case() {
        final v v = new v();
        final u u = new u(40, 10, 4, ab.byte, 5, 0);
        final u u2 = new u(40, 8, 5, ab.long, 7, 1);
        return new h[] { null, new h(0, 0, 1, 0, v, null, null, 0.75f, 0.75f, -1.0f, 36), new h(0, 0, 1, 0, v, null, u, 0.85f, 0.6f, -1.0f, 112), new h(0, 0, 1, 0, v, null, u2, 0.75f, 0.7f, -1.0f, 192), new h(0, 0, 1, 1, v, null, u2, 0.75f, 0.75f, -1.0f, 352), null, null, null };
    }
    
    protected static h[] else() {
        final v v = new v();
        final h[] array = new h[8];
        array[1] = new h(0, 0, 1, 0, v, null, null, 0.75f, 0.75f, -1.0f, 2);
        return array;
    }
    
    public int try() {
        return this.ak;
    }
    
    public boolean a() {
        return this.H != 0;
    }
    
    public float[] int() {
        final float[] array = new float[this.ak];
        for (int i = 0; i < this.z; ++i) {
            array[2 * i] = 2.0f * this.m[this.K + i];
        }
        return array;
    }
    
    public float[] if() {
        return this.int();
    }
    
    static {
        am = new int[] { 4, 36, 112, 192, 352, -1, -1, -1 };
    }
}
