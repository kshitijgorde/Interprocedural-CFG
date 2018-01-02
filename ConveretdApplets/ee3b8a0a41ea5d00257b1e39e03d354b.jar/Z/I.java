// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class I
{
    float[][] I;
    Z Z;
    int C;
    int W;
    int B;
    int D;
    int F;
    int J;
    long S;
    long A;
    J E;
    int G;
    int H;
    int K;
    int L;
    
    public I(final J e) {
        this.I = new float[0][];
        this.Z = new Z();
        this.E = e;
        if (e.I != 0) {
            this.Z.I();
        }
    }
    
    public final void I(final J e) {
        this.E = e;
    }
    
    public final int I(final CI ci) {
        final L z = this.E.Z;
        this.Z.I(ci.I, ci.Z, ci.C);
        if (this.Z.C(1) != 0) {
            return -1;
        }
        final int c = this.Z.C(this.E.C);
        if (c == -1) {
            return -1;
        }
        this.F = c;
        this.W = z.M[this.F].I;
        if (this.W != 0) {
            this.C = this.Z.C(1);
            this.B = this.Z.C(1);
            if (this.B == -1) {
                return -1;
            }
        }
        else {
            final boolean b = false;
            this.B = (b ? 1 : 0);
            this.C = (b ? 1 : 0);
        }
        this.S = ci.F;
        this.A = ci.J - 3L;
        this.J = ci.D;
        this.D = z.J[this.W];
        if (this.I.length < z.Z) {
            this.I = new float[z.Z][];
        }
        for (int i = 0; i < z.Z; ++i) {
            if (this.I[i] == null || this.I[i].length < this.D) {
                this.I[i] = new float[this.D];
            }
            else {
                for (int j = 0; j < this.D; ++j) {
                    this.I[i][j] = 0.0f;
                }
            }
        }
        return Z.G.I[z.N[z.M[this.F].B]].I(this, this.E.Q[this.F]);
    }
}
