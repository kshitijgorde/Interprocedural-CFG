// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class O extends B
{
    public O(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 28: {
                return this.\u010e();
            }
            case 31: {
                return this.\u0110();
            }
            case 3584: {
                return this.\u010f();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u010f() throws C {
        if (!this.A.N(3584)) {
            return null;
        }
        return "(" + this.A.G(3584).length + " bytes)";
    }
    
    public String \u010e() throws C {
        if (!this.A.N(28)) {
            return null;
        }
        final int i = this.A.I(28);
        switch (i) {
            case 1: {
                return "On";
            }
            case 2: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0110() throws C {
        if (!this.A.N(31)) {
            return null;
        }
        final int i = this.A.I(31);
        switch (i) {
            case 1: {
                return "Normal";
            }
            case 2: {
                return "Portrait";
            }
            case 9: {
                return "Macro";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
}
