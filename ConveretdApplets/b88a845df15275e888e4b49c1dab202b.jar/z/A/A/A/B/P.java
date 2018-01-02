// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class P extends B
{
    public P(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 132: {
                return this.\u0112();
            }
            case 146: {
                return this.\u0113();
            }
            case 141: {
                return this.\u0111();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0112() throws C {
        if (!this.A.N(132)) {
            return null;
        }
        final z.A.A.C.A[] l = this.A.L(132);
        if (l.length != 4) {
            return this.A.K(132);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(l[0].intValue());
        sb.append('-');
        sb.append(l[1].intValue());
        sb.append("mm f/");
        sb.append(l[2].floatValue());
        sb.append('-');
        sb.append(l[3].floatValue());
        return sb.toString();
    }
    
    public String \u0113() {
        if (!this.A.N(146)) {
            return null;
        }
        return this.A.K(146) + " degrees";
    }
    
    public String \u0111() {
        if (!this.A.N(141)) {
            return null;
        }
        final String k = this.A.K(141);
        if (k.startsWith("MODE1")) {
            return "Mode I (sRGB)";
        }
        return k;
    }
}
