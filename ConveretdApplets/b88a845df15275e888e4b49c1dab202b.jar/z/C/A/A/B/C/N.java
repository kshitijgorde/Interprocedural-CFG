// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

class N extends I
{
    BitSet G;
    
    N(final int n) {
        super(n);
        this.G = new BitSet(257);
    }
    
    void A(final int n) {
        this.G.set(n);
    }
    
    void A(int i, final int n) {
        while (i <= n) {
            this.G.set(i++);
        }
    }
    
    boolean A(final char c) {
        return this.G.get(c);
    }
    
    P A(final int[] array) {
        final N n = new N(array[0]++);
        n.G = (BitSet)this.G.clone();
        return n;
    }
}
