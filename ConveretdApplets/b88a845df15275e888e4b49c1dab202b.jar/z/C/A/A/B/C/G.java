// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.BitSet;

final class G extends N
{
    G(final int n) {
        super(n);
        this.G.set(256);
    }
    
    boolean A(final char c) {
        return !this.G.get(c);
    }
    
    P A(final int[] array) {
        final G g = new G(array[0]++);
        g.G = (BitSet)this.G.clone();
        return g;
    }
}
