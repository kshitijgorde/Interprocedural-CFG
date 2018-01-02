// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.C;

import z.C.A.A.B.D;
import z.C.A.A.B.A.I;
import z.C.A.A.B.F;
import z.C.A.A.B.A.N;

public class B extends A
{
    private static final N E;
    private static final F D;
    
    public B(final String s, final int n) {
        super(B.D, B.E, s, n);
    }
    
    public B(final String s) {
        super(B.D, B.E, s);
    }
    
    public B() {
        super(B.D, B.E);
    }
    
    static {
        E = new I();
        D = new D();
    }
}
