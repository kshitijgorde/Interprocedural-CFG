// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class BI
{
    private static int[] I;
    byte[] Z;
    int C;
    int B;
    byte[] D;
    int F;
    int J;
    
    private static int I(final int n) {
        int n2 = n << 24;
        for (int i = 0; i < 8; ++i) {
            if ((n2 & Integer.MIN_VALUE) != 0x0) {
                n2 = (n2 << 1 ^ 0x4C11DB7);
            }
            else {
                n2 <<= 1;
            }
        }
        return n2 & -1;
    }
    
    final int I() {
        return this.Z[this.C + 4] & 0xFF;
    }
    
    final int Z() {
        return this.Z[this.C + 5] & 0x1;
    }
    
    final int C() {
        return this.Z[this.C + 5] & 0x2;
    }
    
    public final int B() {
        return this.Z[this.C + 5] & 0x4;
    }
    
    final long D() {
        return (((((((this.Z[this.C + 13] & 0xFF) << 8 | (this.Z[this.C + 12] & 0xFF)) << 8 | (this.Z[this.C + 11] & 0xFF)) << 8 | (this.Z[this.C + 10] & 0xFF)) << 8 | (this.Z[this.C + 9] & 0xFF)) << 8 | (this.Z[this.C + 8] & 0xFF)) << 8 | (this.Z[this.C + 7] & 0xFF)) << 8 | (this.Z[this.C + 6] & 0xFF);
    }
    
    public final int F() {
        return (this.Z[this.C + 14] & 0xFF) | (this.Z[this.C + 15] & 0xFF) << 8 | (this.Z[this.C + 16] & 0xFF) << 16 | (this.Z[this.C + 17] & 0xFF) << 24;
    }
    
    final int J() {
        return (this.Z[this.C + 18] & 0xFF) | (this.Z[this.C + 19] & 0xFF) << 8 | (this.Z[this.C + 20] & 0xFF) << 16 | (this.Z[this.C + 21] & 0xFF) << 24;
    }
    
    final void S() {
        int n = 0;
        for (int i = 0; i < this.B; ++i) {
            n = (n << 8 ^ BI.I[(n >>> 24 & 0xFF) ^ (this.Z[this.C + i] & 0xFF)]);
        }
        for (int j = 0; j < this.J; ++j) {
            n = (n << 8 ^ BI.I[(n >>> 24 & 0xFF) ^ (this.D[this.F + j] & 0xFF)]);
        }
        this.Z[this.C + 22] = (byte)n;
        this.Z[this.C + 23] = (byte)(n >>> 8);
        this.Z[this.C + 24] = (byte)(n >>> 16);
        this.Z[this.C + 25] = (byte)(n >>> 24);
    }
    
    static {
        BI.I = new int[256];
        for (int i = 0; i < BI.I.length; ++i) {
            BI.I[i] = I(i);
        }
    }
}
