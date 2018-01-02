// 
// Decompiled by Procyon v0.5.30
// 

package sexy.util;

public class MTRand
{
    static final int N = 624;
    static final int M = 397;
    static final int MATRIX_A = -1727483681;
    static final int UPPER_MASK = Integer.MIN_VALUE;
    static final int LOWER_MASK = Integer.MAX_VALUE;
    static final int TEMPERING_MASK_B = -1658038656;
    static final int TEMPERING_MASK_C = -272236544;
    int[] mt;
    int mti;
    public int mSeed;
    
    public MTRand(final int n) {
        this.mt = new int[624];
        this.mti = 625;
        this.SRand(n);
    }
    
    public MTRand() {
        this.mt = new int[624];
        this.mti = 625;
        this.SRand(4357);
    }
    
    void SRand(int mSeed) {
        if (mSeed == 0) {
            mSeed = 4357;
        }
        this.mSeed = mSeed;
        this.mt[0] = (mSeed & -1);
        this.mti = 1;
        while (this.mti < 624) {
            this.mt[this.mti] = (69069 * this.mt[this.mti - 1] & -1);
            ++this.mti;
        }
    }
    
    public int Next() {
        final int[] array = { 0, -1727483681 };
        if (this.mti >= 624) {
            int i = 0;
            do {
                final int n = (this.mt[i] & Integer.MIN_VALUE) | (this.mt[i + 1] & Integer.MAX_VALUE);
                this.mt[i] = (this.mt[i + 397] ^ (n >> 1 & Integer.MAX_VALUE) ^ array[n & 0x1]);
            } while (++i < 227);
            while (i < 623) {
                final int n2 = (this.mt[i] & Integer.MIN_VALUE) | (this.mt[i + 1] & Integer.MAX_VALUE);
                this.mt[i] = (this.mt[i - 227] ^ (n2 >> 1 & Integer.MAX_VALUE) ^ array[n2 & 0x1]);
                ++i;
            }
            final int n3 = (this.mt[623] & Integer.MIN_VALUE) | (this.mt[0] & Integer.MAX_VALUE);
            this.mt[623] = (this.mt[396] ^ (n3 >> 1 & Integer.MAX_VALUE) ^ array[n3 & 0x1]);
            this.mti = 0;
        }
        final int n4 = this.mt[this.mti++];
        final int n5 = n4 ^ (n4 >> 11 & 0x1FFFFF);
        final int n6 = n5 ^ (n5 << 7 & 0x9D2C5680);
        final int n7 = n6 ^ (n6 << 15 & 0xEFC60000);
        return (n7 ^ (n7 >> 18 & 0x3FFF)) & Integer.MAX_VALUE;
    }
}
