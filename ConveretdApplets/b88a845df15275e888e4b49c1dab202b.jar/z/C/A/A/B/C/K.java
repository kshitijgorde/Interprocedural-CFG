// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

final class K
{
    int A;
    P D;
    I[] C;
    BitSet[] B;
    
    K(final P d, final int a) {
        this.D = d;
        this.A = a;
    }
    
    void B() {
        this.B = new BitSet[this.A];
        this.C = new I[this.A];
        int a = this.A;
        while (0 < a--) {
            this.B[a] = new BitSet(this.A);
        }
        this.D.A(this.B, this.C);
    }
    
    private void A(final BitSet set, final boolean[] array, final boolean[] array2) {
        for (int i = 0; i < this.A; ++i) {
            if (set.get(i) && !array2[i]) {
                array2[i] = true;
                for (int j = 0; j < 256; ++j) {
                    if (!array[j]) {
                        array[j] = this.C[i].A((char)j);
                    }
                }
            }
        }
    }
    
    boolean[] A() {
        final boolean[] array = new boolean[256];
        this.A(this.D.C(), array, new boolean[this.A]);
        return array;
    }
}
