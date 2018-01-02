// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class L
{
    private int I;
    int Z;
    private int C;
    private int B;
    private int D;
    private int F;
    public int[] J;
    int S;
    private int A;
    int E;
    int G;
    int H;
    int K;
    int L;
    P[] M;
    int[] N;
    Object[] O;
    int[] P;
    Object[] Q;
    int[] R;
    Object[] T;
    int[] U;
    Object[] V;
    AI[] W;
    private DI[] X;
    
    public L() {
        this.J = new int[2];
        this.X = new DI[64];
    }
    
    public final void I() {
        this.C = 0;
    }
    
    private void Z() {
        for (int i = 0; i < this.S; ++i) {
            this.M[i] = null;
        }
        this.M = null;
        for (int j = 0; j < this.A; ++j) {
            Z.G.I[this.N[j]].I(this.O[j]);
        }
        this.O = null;
        for (int k = 0; k < this.E; ++k) {
            Z.K.I[this.P[k]].I(this.Q[k]);
        }
        this.Q = null;
        for (int l = 0; l < this.G; ++l) {
            Z.E.Z[this.R[l]].I(this.T[l]);
        }
        this.T = null;
        for (int n = 0; n < this.H; ++n) {
            Z.H.I[this.U[n]].I(this.V[n]);
        }
        this.V = null;
        for (int n2 = 0; n2 < this.K; ++n2) {
            if (this.W[n2] != null) {
                final AI ai = this.W[n2];
                AI.I();
                this.W[n2] = null;
            }
        }
        this.W = null;
        for (int n3 = 0; n3 < this.L; ++n3) {
            final DI di = this.X[n3];
            DI.I();
        }
    }
    
    private int I(final Z z) {
        this.I = z.C(32);
        if (this.I != 0) {
            return -1;
        }
        this.Z = z.C(8);
        this.C = z.C(32);
        this.B = z.C(32);
        this.D = z.C(32);
        this.F = z.C(32);
        this.J[0] = 1 << z.C(4);
        this.J[1] = 1 << z.C(4);
        if (this.C < 1 || this.Z < 1 || this.J[0] < 8 || this.J[1] < this.J[0] || z.C(1) != 1) {
            this.Z();
            return -1;
        }
        return 0;
    }
    
    private int Z(final Z z) {
        this.K = z.C(8) + 1;
        if (this.W == null || this.W.length != this.K) {
            this.W = new AI[this.K];
        }
        for (int i = 0; i < this.K; ++i) {
            this.W[i] = new AI();
            if (this.W[i].I(z) != 0) {
                this.Z();
                return -1;
            }
        }
        this.E = z.C(6) + 1;
        if (this.P == null || this.P.length != this.E) {
            this.P = new int[this.E];
        }
        if (this.Q == null || this.Q.length != this.E) {
            this.Q = new Object[this.E];
        }
        for (int j = 0; j < this.E; ++j) {
            this.P[j] = z.C(16);
            if (this.P[j] < 0 || this.P[j] >= 1) {
                this.Z();
                return -1;
            }
            this.Q[j] = Z.K.I[this.P[j]].I(this, z);
            if (this.Q[j] == null) {
                this.Z();
                return -1;
            }
        }
        this.G = z.C(6) + 1;
        if (this.R == null || this.R.length != this.G) {
            this.R = new int[this.G];
        }
        if (this.T == null || this.T.length != this.G) {
            this.T = new Object[this.G];
        }
        for (int k = 0; k < this.G; ++k) {
            this.R[k] = z.C(16);
            if (this.R[k] < 0 || this.R[k] >= 2) {
                this.Z();
                return -1;
            }
            this.T[k] = Z.E.Z[this.R[k]].I(this, z);
            if (this.T[k] == null) {
                this.Z();
                return -1;
            }
        }
        this.H = z.C(6) + 1;
        if (this.U == null || this.U.length != this.H) {
            this.U = new int[this.H];
        }
        if (this.V == null || this.V.length != this.H) {
            this.V = new Object[this.H];
        }
        for (int l = 0; l < this.H; ++l) {
            this.U[l] = z.C(16);
            if (this.U[l] < 0 || this.U[l] >= 3) {
                this.Z();
                return -1;
            }
            this.V[l] = Z.H.I[this.U[l]].I(this, z);
            if (this.V[l] == null) {
                this.Z();
                return -1;
            }
        }
        this.A = z.C(6) + 1;
        if (this.N == null || this.N.length != this.A) {
            this.N = new int[this.A];
        }
        if (this.O == null || this.O.length != this.A) {
            this.O = new Object[this.A];
        }
        for (int n = 0; n < this.A; ++n) {
            this.N[n] = z.C(16);
            if (this.N[n] < 0 || this.N[n] >= 1) {
                this.Z();
                return -1;
            }
            this.O[n] = Z.G.I[this.N[n]].I(this, z);
            if (this.O[n] == null) {
                this.Z();
                return -1;
            }
        }
        this.S = z.C(6) + 1;
        if (this.M == null || this.M.length != this.S) {
            this.M = new P[this.S];
        }
        for (int n2 = 0; n2 < this.S; ++n2) {
            this.M[n2] = new P();
            this.M[n2].I = z.C(1);
            this.M[n2].Z = z.C(16);
            this.M[n2].C = z.C(16);
            this.M[n2].B = z.C(8);
            if (this.M[n2].Z >= 1 || this.M[n2].C >= 1 || this.M[n2].B >= this.A) {
                this.Z();
                return -1;
            }
        }
        if (z.C(1) != 1) {
            this.Z();
            return -1;
        }
        return 0;
    }
    
    public final int I(final B b, final CI ci) {
        final Z z = new Z();
        if (ci != null) {
            z.I(ci.I, ci.Z, ci.C);
            final byte[] array = new byte[6];
            final int c = z.C(8);
            z.I(array, 6);
            if (array[0] != 118 || array[1] != 111 || array[2] != 114 || array[3] != 98 || array[4] != 105 || array[5] != 115) {
                return -1;
            }
            switch (c) {
                case 1: {
                    if (ci.B == 0) {
                        return -1;
                    }
                    if (this.C != 0) {
                        return -1;
                    }
                    return this.I(z);
                }
                case 3: {
                    if (this.C == 0) {
                        return -1;
                    }
                    return b.I(z);
                }
                case 5: {
                    if (this.C == 0 || b.B == null) {
                        return -1;
                    }
                    return this.Z(z);
                }
            }
        }
        return -1;
    }
}
