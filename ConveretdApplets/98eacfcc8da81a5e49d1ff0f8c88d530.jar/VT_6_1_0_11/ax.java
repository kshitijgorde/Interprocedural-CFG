// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public class ax extends bK
{
    public static final int[] a;
    protected int b;
    protected float c;
    protected float[] d;
    protected float[] e;
    protected float[] f;
    protected float[] g;
    protected float[] h;
    protected float[] i;
    
    public void a_() {
        final az az = new az();
        final cd cd = new cd(40, 10, 4, aO.r, 5, 0);
        final cd cd2 = new cd(40, 8, 5, aO.s, 7, 1);
        final bU[] h;
        (h = new bU[8])[1] = new bU(0, 0, 1, 0, az, null, null, 0.75f, 0.75f, -1.0f, 36);
        h[2] = new bU(0, 0, 1, 0, az, null, cd, 0.85f, 0.6f, -1.0f, 112);
        h[3] = new bU(0, 0, 1, 0, az, null, cd2, 0.75f, 0.7f, -1.0f, 192);
        h[4] = new bU(0, 0, 1, 1, az, null, cd2, 0.75f, 0.75f, -1.0f, 352);
        this.H = h;
        this.I = 3;
    }
    
    public void b_() {
        final bU[] h;
        (h = new bU[8])[1] = new bU(0, 0, 1, 0, new az(), null, null, 0.75f, 0.75f, -1.0f, 2);
        this.H = h;
        this.I = 1;
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4, final float c) {
        super.a(n, n2, n3, n4);
        this.b = n * 2;
        this.c = c;
        this.U = 0.002f;
        this.d = new float[this.b];
        this.e = new float[this.b];
        this.f = new float[this.b];
        this.g = new float[n];
        this.h = new float[64];
        this.i = new float[64];
    }
    
    public final int c() {
        return this.b;
    }
    
    public final boolean d() {
        return this.ar != 0;
    }
    
    public final float[] e() {
        final float[] array = new float[this.b];
        for (int i = 0; i < this.K; ++i) {
            array[i * 2] = 2.0f * this.aa[this.ab + i];
        }
        return array;
    }
    
    public final float[] f() {
        return this.e();
    }
    
    static {
        a = new int[] { 4, 36, 112, 192, 352, -1, -1, -1 };
    }
}
