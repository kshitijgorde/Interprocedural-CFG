// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class J
{
    int I;
    L Z;
    int C;
    private float[][] arraycopy;
    private int sin;
    private int B;
    private int D;
    private int F;
    private int J;
    private int W;
    private int S;
    private int A;
    private long E;
    private long G;
    private long H;
    private long K;
    private long L;
    private long M;
    float[][][][][] N;
    Object[][] O;
    C[] P;
    Object[] Q;
    
    public J() {
        this.O = new Object[2][];
        this.N = new float[2][][][][];
        (this.N[0] = new float[2][][][])[0] = new float[2][][];
        this.N[0][1] = new float[2][][];
        this.N[0][0][0] = new float[2][];
        this.N[0][0][1] = new float[2][];
        this.N[0][1][0] = new float[2][];
        this.N[0][1][1] = new float[2][];
        (this.N[1] = new float[2][][][])[0] = new float[2][][];
        this.N[1][1] = new float[2][][];
        this.N[1][0][0] = new float[2][];
        this.N[1][0][1] = new float[2][];
        this.N[1][1][0] = new float[2][];
        this.N[1][1][1] = new float[2][];
    }
    
    private static int arraycopy(int i) {
        int n = 0;
        while (i > 1) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
    
    private static float[] sin(final int n, final int n2, final int n3, final int n4) {
        final float[] array = new float[n2];
        switch (n) {
            case 0: {
                final int n5 = n2 / 4 - n3 / 2;
                final int n6 = n2 - n2 / 4 - n4 / 2;
                for (int i = 0; i < n3; ++i) {
                    final float n7 = (float)Math.sin((float)((i + 0.5) / n3 * 3.1415927410125732 / 2.0));
                    array[i + n5] = (float)Math.sin((float)(n7 * n7 * 1.5707963705062866));
                }
                for (int j = n5 + n3; j < n6; ++j) {
                    array[j] = 1.0f;
                }
                for (int k = 0; k < n4; ++k) {
                    final float n8 = (float)Math.sin((float)((n4 - k - 0.5) / n4 * 3.1415927410125732 / 2.0));
                    array[k + n6] = (float)Math.sin((float)(n8 * n8 * 1.5707963705062866));
                }
                return array;
            }
            default: {
                return null;
            }
        }
    }
    
    private int arraycopy(final L z, final boolean b) {
        this.Z = z;
        this.C = arraycopy(z.S);
        this.O[0] = new Object[1];
        this.O[1] = new Object[1];
        this.O[0][0] = new ZI();
        this.O[1][0] = new ZI();
        ((ZI)this.O[0][0]).I(z.J[0]);
        ((ZI)this.O[1][0]).I(z.J[1]);
        this.N[0][0][0] = new float[1][];
        final float[][][] array = this.N[0][0];
        final int n = 1;
        final float[][][] array2 = this.N[0][1];
        final int n2 = 0;
        final float[][][] array3 = this.N[0][1];
        final int n3 = 1;
        final float[][] array4 = this.N[0][0][0];
        array3[n3] = array4;
        array[n] = (array2[n2] = array4);
        this.N[1][0][0] = new float[1][];
        this.N[1][0][1] = new float[1][];
        this.N[1][1][0] = new float[1][];
        this.N[1][1][1] = new float[1][];
        for (int i = 0; i < 1; ++i) {
            this.N[0][0][0][i] = sin(i, z.J[0], z.J[0] / 2, z.J[0] / 2);
            this.N[1][0][0][i] = sin(i, z.J[1], z.J[0] / 2, z.J[0] / 2);
            this.N[1][0][1][i] = sin(i, z.J[1], z.J[0] / 2, z.J[1] / 2);
            this.N[1][1][0][i] = sin(i, z.J[1], z.J[1] / 2, z.J[0] / 2);
            this.N[1][1][1][i] = sin(i, z.J[1], z.J[1] / 2, z.J[1] / 2);
        }
        this.P = new C[z.K];
        for (int j = 0; j < z.K; ++j) {
            (this.P[j] = new C()).I(z.W[j]);
        }
        this.sin = 8192;
        this.arraycopy = new float[z.Z][];
        for (int k = 0; k < z.Z; ++k) {
            this.arraycopy[k] = new float[this.sin];
        }
        final boolean b2 = false;
        this.W = (b2 ? 1 : 0);
        this.J = (b2 ? 1 : 0);
        this.A = z.J[1] / 2;
        this.B = this.A;
        this.Q = new Object[z.S];
        for (int l = 0; l < z.S; ++l) {
            final int b3 = z.M[l].B;
            this.Q[l] = Z.G.I[z.N[b3]].I(this, z.M[l], z.O[b3]);
        }
        return 0;
    }
    
    public final int I(final L l) {
        this.arraycopy(l, false);
        this.D = this.A;
        this.A -= l.J[this.W] / 4 + l.J[this.J] / 4;
        final long n = -1L;
        this.G = n;
        this.E = n;
        return 0;
    }
    
    public final int I(final I i) {
        if (this.A > this.Z.J[1] / 2 && this.D > 8192) {
            int d = this.A - this.Z.J[1] / 2;
            if (this.D < d) {
                d = this.D;
            }
            this.B -= d;
            this.A -= d;
            this.D -= d;
            if (d != 0) {
                for (int j = 0; j < this.Z.Z; ++j) {
                    System.arraycopy(this.arraycopy[j], d, this.arraycopy[j], 0, this.B);
                }
            }
        }
        this.J = this.W;
        this.W = i.W;
        this.S = -1;
        this.H += i.G;
        this.K += i.H;
        this.L += i.K;
        this.M += i.L;
        if (this.G + 1L != i.A) {
            this.E = -1L;
        }
        this.G = i.A;
        final int n = this.Z.J[this.W];
        int a = this.A + this.Z.J[this.J] / 4 + n / 4;
        final int n2 = a - n / 2;
        final int b = n2 + n;
        int n4;
        int n3 = n4 = 0;
        if (b > this.sin) {
            this.sin = b + this.Z.J[1];
            for (int k = 0; k < this.Z.Z; ++k) {
                final float[] array = new float[this.sin];
                System.arraycopy(this.arraycopy[k], 0, array, 0, this.arraycopy[k].length);
                this.arraycopy[k] = array;
            }
        }
        switch (this.W) {
            case 0: {
                n4 = 0;
                n3 = this.Z.J[0] / 2;
                break;
            }
            case 1: {
                n4 = this.Z.J[1] / 4 - this.Z.J[this.J] / 4;
                n3 = n4 + this.Z.J[this.J] / 2;
                break;
            }
        }
        for (int l = 0; l < this.Z.Z; ++l) {
            final int n5 = n2;
            int n6;
            for (n6 = n4; n6 < n3; ++n6) {
                final float[] array2 = this.arraycopy[l];
                final int n7 = n5 + n6;
                array2[n7] += i.I[l][n6];
            }
            while (n6 < n) {
                this.arraycopy[l][n5 + n6] = i.I[l][n6];
                ++n6;
            }
        }
        if (this.E == -1L) {
            this.E = i.S;
        }
        else {
            this.E += a - this.A;
            if (i.S != -1L && this.E != i.S) {
                if (this.E > i.S && i.J != 0) {
                    a -= (int)(this.E - i.S);
                }
                this.E = i.S;
            }
        }
        this.A = a;
        this.B = b;
        if (i.J != 0) {
            this.F = 1;
        }
        return 0;
    }
    
    public final int I(final float[][][] array, final int[] array2) {
        if (this.D < this.A) {
            if (array != null) {
                for (int i = 0; i < this.Z.Z; ++i) {
                    array2[i] = this.D;
                }
                array[0] = this.arraycopy;
            }
            return this.A - this.D;
        }
        return 0;
    }
    
    public final int I(final int n) {
        if (n != 0 && this.D + n > this.A) {
            return -1;
        }
        this.D += n;
        return 0;
    }
}
