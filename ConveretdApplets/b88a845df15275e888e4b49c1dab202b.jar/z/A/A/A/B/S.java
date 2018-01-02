// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import java.text.DecimalFormat;
import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class S extends B
{
    public S(final A a) {
        super(a);
    }
    
    private U \u013a() {
        return (U)this.A;
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 132: {
                return this.\u0134();
            }
            case 146: {
                return this.\u0137();
            }
            case 141: {
                return this.\u0135();
            }
            case 18: {
                return this.\u0133();
            }
            case 2: {
                return this.\u0136();
            }
            case 134: {
                return this.\u0139();
            }
            case 136: {
                return this.\u0138();
            }
            case 1: {
                return this.\u0132();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0138() throws C {
        if (!this.A.N(136)) {
            return null;
        }
        final int[] o = this.A.O(136);
        if (o.length != 4 || o[0] != 0 || o[2] != 0 || o[3] != 0) {
            return "Unknown (" + this.A.K(136) + ")";
        }
        switch (o[1]) {
            case 0: {
                return "Centre";
            }
            case 1: {
                return "Top";
            }
            case 2: {
                return "Bottom";
            }
            case 3: {
                return "Left";
            }
            case 4: {
                return "Right";
            }
            default: {
                return "Unknown (" + o[1] + ")";
            }
        }
    }
    
    public String \u0139() throws C {
        if (!this.A.N(134)) {
            return null;
        }
        final z.A.A.C.A f = this.A.F(134);
        if (f.intValue() == 1) {
            return "No digital zoom";
        }
        return f.A(true) + "x digital zoom";
    }
    
    public String \u0136() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        final int[] o = this.A.O(2);
        if (o[0] != 0 || o[1] == 0) {
            return "Unknown (" + this.A.K(2) + ")";
        }
        return "ISO " + o[1];
    }
    
    public String \u0133() throws C {
        final z.A.A.C.A h = this.\u013a().H();
        if (h == null) {
            return "Unknown";
        }
        return new DecimalFormat("0.##").format(h.floatValue()) + " EV";
    }
    
    public String \u0134() throws C {
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
    
    public String \u0137() {
        if (!this.A.N(146)) {
            return null;
        }
        return this.A.K(146) + " degrees";
    }
    
    public String \u0135() {
        if (!this.A.N(141)) {
            return null;
        }
        final String k = this.A.K(141);
        if (k.startsWith("MODE1")) {
            return "Mode I (sRGB)";
        }
        return k;
    }
    
    public String \u0132() throws C {
        if (!this.A.N(1)) {
            return null;
        }
        return H.A(this.A.O(1));
    }
}
