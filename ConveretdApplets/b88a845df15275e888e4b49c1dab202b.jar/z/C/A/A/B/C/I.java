// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

abstract class I extends P
{
    static final int E = 256;
    static final int B = 256;
    protected int C;
    protected BitSet D;
    
    I(final int c) {
        this.C = c;
        (this.D = new BitSet(c + 1)).set(c);
    }
    
    abstract boolean A(final char p0);
    
    final boolean B() {
        return false;
    }
    
    final BitSet C() {
        return this.D;
    }
    
    final BitSet A() {
        return this.D;
    }
    
    final void A(final BitSet[] array, final P[] array2) {
        array2[this.C] = this;
    }
}
