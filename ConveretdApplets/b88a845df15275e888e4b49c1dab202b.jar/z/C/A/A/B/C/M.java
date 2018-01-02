// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

class M extends P
{
    P H;
    P I;
    
    M(final P h, final P i) {
        this.H = h;
        this.I = i;
    }
    
    boolean B() {
        return this.H.B() || this.I.B();
    }
    
    BitSet C() {
        final BitSet c = this.H.C();
        final BitSet c2 = this.I.C();
        final BitSet set = new BitSet(Math.max(c.size(), c2.size()));
        set.or(c2);
        set.or(c);
        return set;
    }
    
    BitSet A() {
        final BitSet a = this.H.A();
        final BitSet a2 = this.I.A();
        final BitSet set = new BitSet(Math.max(a.size(), a2.size()));
        set.or(a2);
        set.or(a);
        return set;
    }
    
    void A(final BitSet[] array, final P[] array2) {
        this.H.A(array, array2);
        this.I.A(array, array2);
    }
    
    P A(final int[] array) {
        return new M(this.H.A(array), this.I.A(array));
    }
}
