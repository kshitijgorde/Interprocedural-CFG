// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

class A extends P
{
    P M;
    
    A(final P m) {
        this.M = m;
    }
    
    boolean B() {
        return true;
    }
    
    BitSet C() {
        return this.M.C();
    }
    
    BitSet A() {
        return this.M.A();
    }
    
    void A(final BitSet[] array, final P[] array2) {
        this.M.A(array, array2);
        final BitSet a = this.A();
        final BitSet c = this.C();
        int size = a.size();
        while (0 < size--) {
            if (a.get(size)) {
                array[size].or(c);
            }
        }
    }
    
    P A(final int[] array) {
        return new A(this.M.A(array));
    }
}
