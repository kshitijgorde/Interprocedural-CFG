// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class K
{
    public static final float F;
    public static final float[] G;
    public static final float[] B;
    public static final float[] A;
    public static final float[] J;
    public static final float[] D;
    public static float[] O;
    public static final float[] C;
    public static float[] M;
    public static float[] I;
    public static float[] H;
    public float[] N;
    public int P;
    public int L;
    public int E;
    public int K;
    
    public K() {
        this(new float[9]);
    }
    
    public K(final float[] array) {
        this.N = null;
        this.A(3, 3, 1, 1, array);
    }
    
    public K(final int n, final int n2, final float[] array) {
        this.N = null;
        this.A(n, n2, array);
    }
    
    public K(final int n, final int n2, final int n3, final int n4, final float[] array) {
        this.N = null;
        this.A(n, n2, n3, n4, array);
    }
    
    public void A(final float[] array) {
        this.A(3, 3, 1, 1, array);
    }
    
    public void A(final int n, final int n2, final float[] array) {
        this.A(n, n2, n / 2, n2 / 2, array);
    }
    
    public void A(final int p5, final int l, final int e, final int k, final float[] n) {
        this.P = p5;
        this.L = l;
        this.E = e;
        this.K = k;
        this.N = n;
    }
    
    public float[] A() {
        return this.N;
    }
    
    public void B() {
        float n = 0.0f;
        for (int i = 0; i < this.N.length; ++i) {
            n += this.N[i];
        }
        if (n != 0.0f) {
            for (int j = 0; j < this.N.length; ++j) {
                final float[] n2 = this.N;
                final int n3 = j;
                n2[n3] /= n;
            }
        }
    }
    
    static {
        F = (float)Math.sqrt(2.0);
        G = new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f };
        B = new float[] { -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f };
        A = new float[] { -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f };
        J = new float[] { -1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f };
        D = new float[] { -1.0f, 0.0f, 1.0f, -2.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f };
        K.O = new float[] { -1.0f, -2.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f };
        C = new float[] { -1.0f, 0.0f, 1.0f, -K.F, 0.0f, K.F, -1.0f, 0.0f, 1.0f };
        K.M = new float[] { -1.0f, -K.F, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, K.F, 1.0f };
        K.I = new float[] { 0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f, -1.0f, 0.0f };
        K.H = new float[] { -1.0f, -1.0f, -1.0f, -1.0f, 8.0f, -1.0f, -1.0f, -1.0f, -1.0f };
    }
}
