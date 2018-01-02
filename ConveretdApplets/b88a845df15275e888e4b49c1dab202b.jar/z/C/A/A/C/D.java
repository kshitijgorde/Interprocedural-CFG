// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.C;

import z.C.A.A.B.A.R;
import z.C.A.A.B.A.I;
import z.C.A.A.B.F;
import z.C.A.A.B.A.N;

public class D extends A
{
    private static final N I;
    private static final F H;
    
    public D(final String s, final int n) {
        super(D.H, D.I, s, n);
    }
    
    public D(final String s) {
        super(D.H, D.I, s);
    }
    
    public D() {
        super(D.H, D.I);
    }
    
    static {
        I = new I();
        H = new z.C.A.A.B.D(new z.C.A.A.B.I());
    }
}
