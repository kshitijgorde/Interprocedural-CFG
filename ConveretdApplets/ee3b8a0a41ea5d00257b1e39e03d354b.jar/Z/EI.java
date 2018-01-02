// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class EI
{
    private byte[] arraycopy;
    private int I;
    private int Z;
    private int C;
    private int[] B;
    private long[] D;
    private int F;
    private int J;
    private int S;
    private int A;
    private int E;
    private int G;
    private int H;
    private long K;
    
    public EI() {
        this.arraycopy();
    }
    
    private void arraycopy() {
        this.I = 16384;
        this.arraycopy = new byte[this.I];
        this.F = 1024;
        this.B = new int[this.F];
        this.D = new long[this.F];
    }
    
    public final void I(final int g) {
        if (this.arraycopy == null) {
            this.arraycopy();
        }
        else {
            for (int i = 0; i < this.arraycopy.length; ++i) {
                this.arraycopy[i] = 0;
            }
            for (int j = 0; j < this.B.length; ++j) {
                this.B[j] = 0;
            }
            for (int k = 0; k < this.D.length; ++k) {
                this.D[k] = 0L;
            }
        }
        this.G = g;
    }
    
    private void Z(final int n) {
        if (this.I <= this.Z + n) {
            this.I += n + 1024;
            final byte[] arraycopy = new byte[this.I];
            System.arraycopy(this.arraycopy, 0, arraycopy, 0, this.arraycopy.length);
            this.arraycopy = arraycopy;
        }
    }
    
    private void C(final int n) {
        if (this.F <= this.J + n) {
            this.F += n + 32;
            final int[] b = new int[this.F];
            System.arraycopy(this.B, 0, b, 0, this.B.length);
            this.B = b;
            final long[] d = new long[this.F];
            System.arraycopy(this.D, 0, d, 0, this.D.length);
            this.D = d;
        }
    }
    
    public final int I(final CI ci) {
        int a = this.A;
        if (this.S <= a) {
            return 0;
        }
        if ((this.B[a] & 0x400) != 0x0) {
            ++this.A;
            ++this.K;
            return -1;
        }
        int i = this.B[a] & 0xFF;
        final int n = 0;
        ci.I = this.arraycopy;
        ci.Z = this.C;
        ci.D = (this.B[a] & 0x200);
        ci.B = (this.B[a] & 0x100);
        int c = n + i;
        while (i == 255) {
            final int n2 = this.B[++a];
            i = (n2 & 0xFF);
            if ((n2 & 0x200) != 0x0) {
                ci.D = 512;
            }
            c += i;
        }
        ci.J = this.K;
        ci.F = this.D[a];
        ci.C = c;
        this.C += c;
        this.A = a + 1;
        ++this.K;
        return 1;
    }
    
    public final int I(final BI bi) {
        final byte[] z = bi.Z;
        final int c = bi.C;
        final byte[] d = bi.D;
        int f = bi.F;
        int j = bi.J;
        int i = 0;
        final int k = bi.I();
        final int z2 = bi.Z();
        int c2 = bi.C();
        final int b = bi.B();
        final long d2 = bi.D();
        final int f2 = bi.F();
        final int l = bi.J();
        final int n = z[c + 26] & 0xFF;
        final int a = this.A;
        final int c3 = this.C;
        if (c3 != 0) {
            this.Z -= c3;
            if (this.Z != 0) {
                System.arraycopy(this.arraycopy, c3, this.arraycopy, 0, this.Z);
            }
            this.C = 0;
        }
        if (a != 0) {
            if (this.J - a != 0) {
                System.arraycopy(this.B, a, this.B, 0, this.J - a);
                System.arraycopy(this.D, a, this.D, 0, this.J - a);
            }
            this.J -= a;
            this.S -= a;
            this.A = 0;
        }
        if (f2 != this.G) {
            return -1;
        }
        if (k > 0) {
            return -1;
        }
        this.C(n + 1);
        if (l != this.H) {
            for (int s = this.S; s < this.J; ++s) {
                this.Z -= (this.B[s] & 0xFF);
            }
            this.J = this.S;
            if (this.H != -1) {
                this.B[this.J++] = 1024;
                ++this.S;
            }
            if (z2 != 0) {
                c2 = 0;
                while (i < n) {
                    final int n2 = z[c + 27 + i] & 0xFF;
                    f += n2;
                    j -= n2;
                    if (n2 < 255) {
                        ++i;
                        break;
                    }
                    ++i;
                }
            }
        }
        if (j != 0) {
            this.Z(j);
            System.arraycopy(d, f, this.arraycopy, this.Z, j);
            this.Z += j;
        }
        int m = -1;
        while (i < n) {
            final int n3 = z[c + 27 + i] & 0xFF;
            this.B[this.J] = n3;
            this.D[this.J] = -1L;
            if (c2 != 0) {
                final int[] b2 = this.B;
                final int j2 = this.J;
                b2[j2] |= 0x100;
                c2 = 0;
            }
            if (n3 < 255) {
                m = this.J;
            }
            ++this.J;
            ++i;
            if (n3 < 255) {
                this.S = this.J;
            }
        }
        if (m != -1) {
            this.D[m] = d2;
        }
        if (b != 0) {
            this.E = 1;
            if (this.J > 0) {
                final int[] b3 = this.B;
                final int n4 = this.J - 1;
                b3[n4] |= 0x200;
            }
        }
        this.H = l + 1;
        return 0;
    }
}
