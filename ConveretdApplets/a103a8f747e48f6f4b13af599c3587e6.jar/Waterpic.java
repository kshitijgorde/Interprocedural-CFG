// 
// Decompiled by Procyon v0.5.30
// 

public final class Waterpic
{
    public int[] jm21;
    int[] jm22;
    public int jm24;
    public int jm25;
    int jm26;
    int jm27;
    int jm28;
    int jm29;
    int jm30;
    int jm31;
    int jm32;
    
    public final void jm35(final Screen32 screen32, final Screen32 screen33) {
        int jm29 = this.jm29;
        int n = 0;
        for (int i = 0; i < this.jm25; ++i) {
            for (int j = 0; j < this.jm24; ++j) {
                final int n2 = (this.jm21[jm29 - this.jm24] + this.jm21[jm29 + this.jm24] + this.jm21[jm29 - 1] + this.jm21[jm29 + 1] >> 1) - this.jm21[this.jm30 + n];
                final int n3 = n2 - (n2 >> this.jm28);
                this.jm21[this.jm30 + n] = n3;
                final int n4 = 1024 - n3;
                int n5 = ((j - this.jm26) * n4 >> 10) + this.jm26;
                if (n5 >= this.jm24 || n5 < 0) {
                    n5 = 0;
                }
                int n6 = ((i - this.jm27) * n4 >> 10) + this.jm27;
                if (n6 >= this.jm25 || n6 < 0) {
                    n6 = 0;
                }
                screen32.jm21[n] = screen33.jm21[n5 + screen33.jm22[n6]];
                ++jm29;
                ++n;
            }
        }
    }
    
    public Waterpic(final int jm24, final int jm25) {
        this.jm24 = jm24;
        this.jm25 = jm25;
        this.jm26 = jm24 >> 1;
        this.jm27 = jm25 >> 1;
        this.jm31 = 9;
        this.jm32 = 6;
        this.jm28 = 5;
        this.jm21 = new int[this.jm24 * (this.jm25 + 2) * 2];
        this.jm29 = this.jm24;
        this.jm30 = this.jm24 * (this.jm25 + 3);
        this.jm22 = new int[this.jm25 + 2];
        int n = 0;
        for (int i = 0; i < this.jm25 + 2; ++i) {
            this.jm22[i] = n;
            n += this.jm24;
        }
    }
    
    public final void jm18(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n4; ++j) {
                if (n2 + i + 1 > 0 && n2 + i + 1 < this.jm25 - n4) {
                    final int[] jm21 = this.jm21;
                    final int n5 = this.jm30 + this.jm22[n2 + i + 1] + n + j + 1;
                    jm21[n5] += n3;
                }
            }
        }
    }
    
    public final void jm19() {
        if (this.jm29 == this.jm24) {
            this.jm29 = this.jm30;
            this.jm30 = this.jm24;
            return;
        }
        this.jm30 = this.jm29;
        this.jm29 = this.jm24;
    }
}
