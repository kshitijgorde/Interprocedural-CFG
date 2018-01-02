// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int[] Q;
    private int[] R;
    
    public b(final int e, final int o, final int p3) {
        this.E = e;
        this.F = 2 * this.E;
        this.H = this.F * this.F;
        this.O = o;
        this.P = p3;
        this.J = this.E + this.P + this.O;
        this.K = this.J * 2;
        this.I = this.K * this.K;
        this.M = this.E + 2 * this.P;
        this.N = this.M * 2;
        this.L = this.N * this.N;
        this.G = this.F + 2 * this.P;
        this.D();
    }
    
    public void F(final a a, final a a2, final int[] array, final int[] array2, final a a3, final int n, final int n2) {
        final int max = Math.max(0, this.J - n);
        final int min = Math.min(this.K, this.J + a.B - n);
        final int n3 = min - max;
        int n4 = max + this.P * (1 + this.N);
        int n5 = max + n - this.J + (n2 - this.J) * a.B;
        int n6 = max;
        for (int i = 0; i < this.O; ++i) {
            final int n7 = n2 + i;
            if (n7 >= this.J && (n7 < a.C + this.J || false)) {
                System.arraycopy(a2.A, n5, a3.A, n6, n3);
            }
            n5 += a.B;
            n6 += this.K;
        }
        for (int j = 0; j < this.G; ++j) {
            final int n8 = n2 + j + this.O;
            if (n8 >= this.J && (n8 < a.C + this.J || false)) {
                for (int k = max; k < this.O; ++k) {
                    a3.A[n6++] = a2.A[n5++];
                    ++n4;
                }
                for (int l = Math.max(this.O, max); l < Math.min(this.K - this.O, min); ++l) {
                    final int n9 = n4++ + array[n5] + array2[n5] * this.N;
                    final int n10 = a.A[n5++];
                    if (this.R[n9] == 256) {
                        a2.A[n6++] = n10;
                    }
                    else {
                        a3.A[n6++] = (0xFF000000 | (((n10 & 0xFF0000) * this.R[n9] >> 8 & 0xFF0000) | ((n10 & 0xFF00) * this.R[n9] >> 8 & 0xFF00) | (n10 & 0xFF) * this.R[n9] >> 8) + this.Q[n9]);
                    }
                }
                for (int n11 = this.K - this.O; n11 < min; ++n11) {
                    a3.A[n6++] = a2.A[n5++];
                    ++n4;
                }
                n4 += this.N - n3;
                n5 += a.B - n3;
                n6 += this.K - n3;
            }
            else {
                n4 += this.N;
                n5 += a.B;
                n6 += this.K;
            }
        }
        for (int n12 = 0; n12 < this.O; ++n12) {
            final int n13 = n2 + n12 + this.G + this.O;
            if (n13 >= this.J && (n13 < a.C + this.J || false)) {
                System.arraycopy(a2.A, n5, a3.A, n6, n3);
            }
            n5 += a.B;
            n6 += this.K;
        }
    }
    
    private void D() {
        this.Q = new int[this.L];
        this.R = new int[this.L];
        int n = 0;
        for (int i = -this.M; i < this.M; ++i) {
            for (int j = -this.M; j < this.M; ++j) {
                final double n2 = 3.141592653589793;
                final double n3 = i;
                final double n4 = j;
                final double cos = Math.cos(Math.min(n2 * Math.sqrt(n3 * n3 + n4 * n4) / this.F, 1.5707963267948966));
                final double n5 = 1.0;
                final double n6 = 3.141592653589793;
                final double n7 = i;
                final double n8 = j;
                final double n9 = n5 - Math.sin(Math.min(n6 * Math.sqrt(n7 * n7 + n8 * n8) / this.F, 1.5707963267948966));
                this.R[n] = (int)((1.0 - n9) * (64.0 + cos * 192.0));
                final int n10 = (int)(n9 * 255.0);
                this.Q[n++] = (n10 << 16 | n10 << 8 | n10);
            }
        }
    }
    
    private boolean E(final int n, final a a) {
        return n >= this.J && n < a.C + this.J;
    }
    
    private int G(final int n) {
        return Math.max(-this.P, Math.min(this.P, n));
    }
    
    private static double C(final double n, final double n2) {
        return Math.sqrt(n * n + n2 * n2);
    }
}
