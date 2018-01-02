// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class GI
{
    public byte[] I;
    private int arraycopy;
    private int Z;
    private int C;
    private int B;
    private int D;
    private int F;
    private BI J;
    private byte[] S;
    
    public GI() {
        this.J = new BI();
        this.S = new byte[4];
    }
    
    public final int I(final int n) {
        if (this.C != 0) {
            this.Z -= this.C;
            if (this.Z > 0) {
                System.arraycopy(this.I, this.C, this.I, 0, this.Z);
            }
            this.C = 0;
        }
        if (n > this.arraycopy - this.Z) {
            final int arraycopy = n + this.Z + 4096;
            if (this.I != null) {
                final byte[] i = new byte[arraycopy];
                System.arraycopy(this.I, 0, i, 0, this.I.length);
                this.I = i;
            }
            else {
                this.I = new byte[arraycopy];
            }
            this.arraycopy = arraycopy;
        }
        return this.Z;
    }
    
    public final int Z(final int n) {
        if (this.Z + n > this.arraycopy) {
            return -1;
        }
        this.Z += n;
        return 0;
    }
    
    private int arraycopy(final BI bi) {
        final int c = this.C;
        final int n = this.Z - this.C;
        if (this.D == 0) {
            if (n < 27) {
                return 0;
            }
            if (this.I[c] != 79 || this.I[c + 1] != 103 || this.I[c + 2] != 103 || this.I[c + 3] != 83) {
                final boolean b = false;
                this.F = (b ? 1 : 0);
                this.D = (b ? 1 : 0);
                int z = 0;
                for (int i = 0; i < n - 1; ++i) {
                    if (this.I[c + 1 + i] == 79) {
                        z = c + 1 + i;
                        break;
                    }
                }
                if (z == 0) {
                    z = this.Z;
                }
                return -((this.C = z) - c);
            }
            final int d = (this.I[c + 26] & 0xFF) + 27;
            if (n < d) {
                return 0;
            }
            for (int j = 0; j < (this.I[c + 26] & 0xFF); ++j) {
                this.F += (this.I[c + 27 + j] & 0xFF);
            }
            this.D = d;
        }
        if (this.F + this.D > n) {
            return 0;
        }
        synchronized (this.S) {
            System.arraycopy(this.I, c + 22, this.S, 0, 4);
            final byte[] k = this.I;
            final int n2 = c + 22;
            final byte[] l = this.I;
            final int n3 = c + 23;
            final byte[] m = this.I;
            final int n4 = c + 24;
            final byte[] i2 = this.I;
            final int n5 = c + 25;
            final boolean b2 = false;
            m[n4] = (i2[n5] = (byte)(b2 ? 1 : 0));
            k[n2] = (l[n3] = (byte)(b2 ? 1 : 0));
            final BI j2;
            final BI bi3;
            final BI bi2 = bi3 = (j2 = this.J);
            final byte[] i3 = this.I;
            bi3.D = i3;
            j2.Z = i3;
            bi2.C = c;
            bi2.B = this.D;
            bi2.F = c + this.D;
            bi2.J = this.F;
            bi2.S();
            if (this.S[0] != this.I[c + 22] || this.S[1] != this.I[c + 23] || this.S[2] != this.I[c + 24] || this.S[3] != this.I[c + 25]) {
                System.arraycopy(this.S, 0, this.I, c + 22, 4);
                final boolean b3 = false;
                this.F = (b3 ? 1 : 0);
                this.D = (b3 ? 1 : 0);
                int z2 = 0;
                for (int n6 = 0; n6 < n - 1; ++n6) {
                    if (this.I[c + 1 + n6] == 79) {
                        z2 = c + 1 + n6;
                        break;
                    }
                }
                if (z2 == 0) {
                    z2 = this.Z;
                }
                return -((this.C = z2) - c);
            }
        }
        final int c2 = this.C;
        if (bi != null) {
            bi.Z = this.I;
            bi.C = c2;
            bi.B = this.D;
            bi.D = this.I;
            bi.F = c2 + this.D;
            bi.J = this.F;
        }
        final int n7;
        this.C += (n7 = this.D + this.F);
        final boolean b4 = false;
        this.F = (b4 ? 1 : 0);
        this.D = (b4 ? 1 : 0);
        this.B = (b4 ? 1 : 0);
        return n7;
    }
    
    public final int I(final BI bi) {
        while (true) {
            final int arraycopy = this.arraycopy(bi);
            if (arraycopy > 0) {
                return 1;
            }
            if (arraycopy == 0) {
                return 0;
            }
            if (this.B == 0) {
                this.B = 1;
                return -1;
            }
        }
    }
    
    public static final void I() {
    }
}
