// 
// Decompiled by Procyon v0.5.30
// 

public final class Waterpic
{
    public short[] z0;
    int z1;
    int z2;
    public int z3;
    int z4;
    int z5;
    int z6;
    public int z7;
    int z8;
    int z9;
    int[] z10;
    
    public void z0(final Screen32 screen32, final Screen32 screen33) {
        int n = 0;
        int z8 = this.z8;
        for (int i = 0; i < this.z3; ++i) {
            final int n2 = i - this.z4;
            for (int j = 0; j < this.z7; ++j) {
                final short n3 = (short)((short)(this.z0[z8 - this.z7] + this.z0[z8 + this.z7] + this.z0[z8 - 1] + this.z0[z8 + 1] >> 1) - this.z0[this.z9 + n]);
                final short n4 = (short)(n3 - (n3 >> this.z1));
                this.z0[this.z9 + n] = n4;
                final short n5 = (short)(1024 - n4);
                int n6 = ((j - this.z5) * n5 >> 10) + this.z5;
                int n7 = (n2 * n5 >> 10) + this.z4;
                if (n6 >= this.z7 || n6 < 0) {
                    n6 = 0;
                }
                if (n7 >= this.z3 || n7 < 0) {
                    n7 = 0;
                }
                screen32.z0[n] = screen33.z0[n6 + screen33.z5[n7]];
                ++n;
                ++z8;
            }
        }
    }
    
    public void z0() {
        if (this.z8 == this.z7) {
            this.z8 = this.z9;
            this.z9 = this.z7;
            return;
        }
        this.z9 = this.z8;
        this.z8 = this.z7;
    }
    
    public void z0(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n2 + i + 1 > 0 && n2 + i + 1 < this.z3 - n4) {
                    final short[] z0 = this.z0;
                    final int n5 = this.z9 + this.z10[n2 + i + 1] + n + j + 1;
                    z0[n5] += (short)n3;
                }
            }
        }
    }
    
    public Waterpic(final int z7, final int z8) {
        this.z7 = z7;
        this.z3 = z8;
        this.z5 = z7 >> 1;
        this.z4 = z8 >> 1;
        this.z2 = 9;
        this.z6 = 6;
        this.z1 = 5;
        this.z0 = new short[this.z7 * (this.z3 + 2) * 2];
        this.z8 = this.z7;
        this.z9 = this.z7 * (this.z3 + 3);
        this.z10 = new int[this.z3 + 2];
        int n = 0;
        for (int i = 0; i < this.z3 + 2; ++i) {
            this.z10[i] = n;
            n += this.z7;
        }
    }
}
