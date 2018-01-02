// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B;

import z.C.A.A.B.A.H;
import z.C.A.A.B.A.G;
import z.C.A.A.B.A.R;

public abstract class L implements F
{
    R B;
    z.C.A.A.A.F C;
    public static final int A = 20;
    
    L(final z.C.A.A.A.F c, final R b) {
        this.C = c;
        this.B = b;
    }
    
    public final synchronized G B(final String s, final int n) throws H {
        final Object a = this.C.A(s);
        if (a != null) {
            final G g = (G)a;
            if (g.A() == n) {
                return g;
            }
        }
        final G a2 = this.B.A(s, n);
        this.C.A(s, a2);
        return a2;
    }
    
    public final synchronized G B(final String s) throws H {
        return this.B(s, 0);
    }
    
    public final synchronized G A(final String s, final int n) throws J {
        G b;
        try {
            b = this.B(s, n);
        }
        catch (H h) {
            throw new J("Invalid expression: " + s + "\n" + h.getMessage());
        }
        return b;
    }
    
    public final synchronized G A(final String s) throws J {
        return this.A(s, 0);
    }
    
    public final int A() {
        return this.C.A();
    }
    
    public final int B() {
        return this.C.B();
    }
}
