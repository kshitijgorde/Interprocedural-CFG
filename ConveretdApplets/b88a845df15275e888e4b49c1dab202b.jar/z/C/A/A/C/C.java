// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.C;

import z.C.A.A.B.A.R;
import z.C.A.A.B.D;
import z.C.A.A.B.C.E;
import z.C.A.A.B.C.Q;
import z.C.A.A.B.F;
import z.C.A.A.B.A.N;

public class C extends A
{
    private static final N G;
    private static final F F;
    
    public C(final String s, final int n) {
        super(C.F, C.G, s, n);
    }
    
    public C(final String s) {
        super(C.F, C.G, s);
    }
    
    public C() {
        super(C.F, C.G);
    }
    
    static {
        G = new Q();
        F = new D(new E());
    }
}
