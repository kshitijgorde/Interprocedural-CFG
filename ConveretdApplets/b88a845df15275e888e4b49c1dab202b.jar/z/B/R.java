// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

public class R implements M
{
    private static int V;
    private int[] Q;
    private int[] L;
    private int[][] T;
    private int[] R;
    private float P;
    private int M;
    private boolean K;
    private boolean N;
    private boolean S;
    private boolean O;
    private boolean[] U;
    
    public R() {
        this.Q = new int[z.B.R.V];
        this.L = new int[z.B.R.V];
        this.R = new int[z.B.R.V];
        this.T = new int[z.B.R.V][];
        this.U = new boolean[z.B.R.V];
        this.Q[0] = 1;
        this.L[0] = 1;
        this.R[0] = 0;
        this.T[0] = null;
        this.U[0] = false;
        this.Q[1] = 2;
        this.L[1] = 2;
        this.R[1] = 1;
        this.T[1] = null;
        this.U[1] = false;
        this.Q[2] = 2;
        this.L[2] = 2;
        this.R[2] = 1;
        this.T[2] = null;
        this.U[2] = false;
        this.P = 0.75f;
        this.M = 0;
        this.K = false;
        this.N = false;
        this.S = true;
    }
    
    public void B(final int n, final int n2) {
        this.Q[n] = n2;
    }
    
    public int E(final int n) {
        return this.Q[n];
    }
    
    public void A(final int n, final int n2) {
        this.L[n] = n2;
    }
    
    public int G(final int n) {
        return this.L[n];
    }
    
    public void A(final int[] array) {
        this.A(0, 0, array);
        this.U[0] = true;
        this.O = false;
    }
    
    public void B(final int[] array) {
        this.A(1, 1, array);
        this.A(2, 1, array);
        this.U[1] = true;
        this.U[2] = true;
        this.O = false;
    }
    
    public void A(final int n, final int n2, final int[] array) {
        this.T[n] = array.clone();
        this.R[n] = n2;
        this.U[n] = true;
        this.O = false;
    }
    
    public boolean H(final int n) {
        return this.U[n];
    }
    
    public int[] F(final int n) {
        if (!this.U[n]) {
            throw new IllegalStateException(m.A("JPEGEncodeParam0"));
        }
        return this.T[n];
    }
    
    public int I(final int n) {
        if (!this.U[n]) {
            throw new IllegalStateException(m.A("JPEGEncodeParam0"));
        }
        return this.R[n];
    }
    
    public void D(final int m) {
        this.M = m;
    }
    
    public int G() {
        return this.M;
    }
    
    public void A(final float p) {
        this.P = p;
        for (int i = 0; i < z.B.R.V; ++i) {
            this.U[i] = false;
        }
        this.O = true;
    }
    
    public boolean H() {
        return this.O;
    }
    
    public float K() {
        return this.P;
    }
    
    public void F(final boolean n) {
        this.N = n;
    }
    
    public boolean J() {
        return this.N;
    }
    
    public void G(final boolean k) {
        this.K = k;
    }
    
    public boolean L() {
        return this.K;
    }
    
    public void E(final boolean s) {
        this.S = s;
    }
    
    public boolean I() {
        return this.S;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    static {
        R.V = 3;
    }
}
