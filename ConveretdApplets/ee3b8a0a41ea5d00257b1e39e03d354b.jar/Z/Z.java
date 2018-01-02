// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class Z
{
    private static final int[] I;
    private int Z;
    private byte[] C;
    private int B;
    private int D;
    private int F;
    
    final void I() {
        this.C = new byte[256];
        this.Z = 0;
        this.C[0] = 0;
        this.F = 256;
    }
    
    final void I(final byte[] array, int n) {
        int n2 = 0;
        while (n-- != 0) {
            array[n2++] = (byte)this.C(8);
        }
    }
    
    final void I(final byte[] c, final int z, final int f) {
        this.Z = z;
        this.C = c;
        final boolean b = false;
        this.D = (b ? 1 : 0);
        this.B = (b ? 1 : 0);
        this.F = f;
    }
    
    final int I(int n) {
        final int n2 = Z.Z.I[n];
        n += this.B;
        if (this.D + 4 >= this.F && this.D + (n - 1) / 8 >= this.F) {
            return -1;
        }
        int n3 = (this.C[this.Z] & 0xFF) >>> this.B;
        if (n > 8) {
            n3 |= (this.C[this.Z + 1] & 0xFF) << 8 - this.B;
            if (n > 16) {
                n3 |= (this.C[this.Z + 2] & 0xFF) << 16 - this.B;
                if (n > 24) {
                    n3 |= (this.C[this.Z + 3] & 0xFF) << 24 - this.B;
                    if (n > 32 && this.B != 0) {
                        n3 |= (this.C[this.Z + 4] & 0xFF) << 32 - this.B;
                    }
                }
            }
        }
        return n2 & n3;
    }
    
    final void Z(int n) {
        n += this.B;
        this.Z += n / 8;
        this.D += n / 8;
        this.B = (n & 0x7);
    }
    
    final int C(int n) {
        final int n2 = Z.Z.I[n];
        n += this.B;
        if (this.D + 4 >= this.F) {
            final int n3 = -1;
            if (this.D + (n - 1) / 8 >= this.F) {
                this.Z += n / 8;
                this.D += n / 8;
                this.B = (n & 0x7);
                return n3;
            }
        }
        int n4 = (this.C[this.Z] & 0xFF) >>> this.B;
        if (n > 8) {
            n4 |= (this.C[this.Z + 1] & 0xFF) << 8 - this.B;
            if (n > 16) {
                n4 |= (this.C[this.Z + 2] & 0xFF) << 16 - this.B;
                if (n > 24) {
                    n4 |= (this.C[this.Z + 3] & 0xFF) << 24 - this.B;
                    if (n > 32 && this.B != 0) {
                        n4 |= (this.C[this.Z + 4] & 0xFF) << 32 - this.B;
                    }
                }
            }
        }
        final int n5 = n4 & n2;
        this.Z += n / 8;
        this.D += n / 8;
        this.B = (n & 0x7);
        return n5;
    }
    
    final int Z() {
        if (this.D >= this.F) {
            final int n = -1;
            ++this.B;
            if (this.B > 7) {
                this.B = 0;
                ++this.Z;
                ++this.D;
            }
            return n;
        }
        final int n2 = this.C[this.Z] >> this.B & 0x1;
        ++this.B;
        if (this.B > 7) {
            this.B = 0;
            ++this.Z;
            ++this.D;
        }
        return n2;
    }
    
    static {
        I = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
    }
}
