// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class i extends B
{
    public i(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 6: {
                return this.\u0164();
            }
            case 5: {
                return this.\u015f();
            }
            case 9: {
                return this.\u0165();
            }
            case 10: {
                return this.\u0160();
            }
            case 12: {
                return this.\u015d();
            }
            case 14:
            case 16:
            case 23: {
                return this.E(n);
            }
            case 15:
            case 17:
            case 24: {
                return this.D(n);
            }
            case 25: {
                return this.\u0162();
            }
            case 7: {
                return this.\u0163();
            }
            case 4: {
                return this.\u015e();
            }
            case 2: {
                return this.\u0161();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0161() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        return this.F(2);
    }
    
    public String \u015e() throws C {
        if (!this.A.N(4)) {
            return null;
        }
        return this.F(4);
    }
    
    public String F(final int n) throws C {
        final z.A.A.C.A[] l = this.A.L(n);
        final int intValue = l[0].intValue();
        final float floatValue = l[1].floatValue();
        return String.valueOf(intValue) + "\"" + String.valueOf((int)floatValue) + "'" + String.valueOf(l[2].floatValue() + floatValue % 1.0f * 60.0f);
    }
    
    public String \u0163() throws C {
        if (!this.A.N(7)) {
            return null;
        }
        final int[] o = this.A.O(7);
        final StringBuffer sb = new StringBuffer();
        sb.append(o[0]);
        sb.append(":");
        sb.append(o[1]);
        sb.append(":");
        sb.append(o[2]);
        sb.append(" UTC");
        return sb.toString();
    }
    
    public String \u0162() {
        if (!this.A.N(25)) {
            return null;
        }
        final String trim = this.A.K(25).trim();
        if ("K".equalsIgnoreCase(trim)) {
            return "kilometers";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "miles";
        }
        if ("N".equalsIgnoreCase(trim)) {
            return "knots";
        }
        return "Unknown (" + trim + ")";
    }
    
    public String D(final int n) {
        if (!this.A.N(n)) {
            return null;
        }
        return this.A.K(n).trim() + " degrees";
    }
    
    public String E(final int n) {
        if (!this.A.N(n)) {
            return null;
        }
        final String trim = this.A.K(n).trim();
        if ("T".equalsIgnoreCase(trim)) {
            return "True direction";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "Magnetic direction";
        }
        return "Unknown (" + trim + ")";
    }
    
    public String \u015d() {
        if (!this.A.N(12)) {
            return null;
        }
        final String trim = this.A.K(12).trim();
        if ("K".equalsIgnoreCase(trim)) {
            return "kph";
        }
        if ("M".equalsIgnoreCase(trim)) {
            return "mph";
        }
        if ("N".equalsIgnoreCase(trim)) {
            return "knots";
        }
        return "Unknown (" + trim + ")";
    }
    
    public String \u0160() {
        if (!this.A.N(10)) {
            return null;
        }
        final String trim = this.A.K(10).trim();
        if ("2".equalsIgnoreCase(trim)) {
            return "2-dimensional measurement";
        }
        if ("3".equalsIgnoreCase(trim)) {
            return "3-dimensional measurement";
        }
        return "Unknown (" + trim + ")";
    }
    
    public String \u0165() {
        if (!this.A.N(9)) {
            return null;
        }
        final String trim = this.A.K(9).trim();
        if ("A".equalsIgnoreCase(trim)) {
            return "Measurement in progess";
        }
        if ("V".equalsIgnoreCase(trim)) {
            return "Measurement Interoperability";
        }
        return "Unknown (" + trim + ")";
    }
    
    public String \u015f() throws C {
        if (!this.A.N(5)) {
            return null;
        }
        final int i = this.A.I(5);
        if (i == 0) {
            return "Sea level";
        }
        return "Unknown (" + i + ")";
    }
    
    public String \u0164() throws C {
        if (!this.A.N(6)) {
            return null;
        }
        return this.A.F(6).A(true) + " metres";
    }
}
