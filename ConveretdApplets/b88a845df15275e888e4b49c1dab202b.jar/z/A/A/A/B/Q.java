// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class Q extends B
{
    public Q(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 3584: {
                return this.\u0115();
            }
            case 1: {
                return this.\u0114();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0115() throws C {
        if (!this.A.N(3584)) {
            return null;
        }
        return "(" + this.A.G(3584).length + " bytes)";
    }
    
    public String \u0114() throws C {
        if (!this.A.N(1)) {
            return null;
        }
        return "(" + this.A.G(1).length + " bytes)";
    }
}
