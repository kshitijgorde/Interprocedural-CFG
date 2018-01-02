// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

final class R extends P
{
    P K;
    P L;
    
    boolean B() {
        return this.K.B() && this.L.B();
    }
    
    BitSet C() {
        if (this.K.B()) {
            final BitSet c = this.K.C();
            final BitSet c2 = this.L.C();
            final BitSet set = new BitSet(Math.max(c.size(), c2.size()));
            set.or(c2);
            set.or(c);
            return set;
        }
        return this.K.C();
    }
    
    BitSet A() {
        if (this.L.B()) {
            final BitSet a = this.K.A();
            final BitSet a2 = this.L.A();
            final BitSet set = new BitSet(Math.max(a.size(), a2.size()));
            set.or(a2);
            set.or(a);
            return set;
        }
        return this.L.A();
    }
    
    void A(final BitSet[] array, final P[] array2) {
        this.K.A(array, array2);
        this.L.A(array, array2);
        final BitSet a = this.K.A();
        final BitSet c = this.L.C();
        int size = a.size();
        while (0 < size--) {
            if (a.get(size)) {
                array[size].or(c);
            }
        }
    }
    
    P A(final int[] array) {
        final R r = new R();
        r.K = this.K.A(array);
        r.L = this.L.A(array);
        return r;
    }
}
