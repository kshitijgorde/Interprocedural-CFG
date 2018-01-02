// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class D extends B
{
    public D(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 512: {
                return this.O();
            }
            case 513: {
                return this.Q();
            }
            case 514: {
                return this.P();
            }
            case 516: {
                return this.R();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String R() throws C {
        if (!this.A.N(516)) {
            return null;
        }
        final int i = this.A.I(516);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 2: {
                return "Digital 2x Zoom";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String P() throws C {
        if (!this.A.N(514)) {
            return null;
        }
        final int i = this.A.I(514);
        switch (i) {
            case 0: {
                return "Normal (no macro)";
            }
            case 1: {
                return "Macro";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String Q() throws C {
        if (!this.A.N(513)) {
            return null;
        }
        final int i = this.A.I(513);
        switch (i) {
            case 1: {
                return "SQ";
            }
            case 2: {
                return "HQ";
            }
            case 3: {
                return "SHQ";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String O() throws C {
        if (!this.A.N(512)) {
            return null;
        }
        final int[] o = this.A.O(512);
        final StringBuffer sb = new StringBuffer();
        switch (o[0]) {
            case 0: {
                sb.append("Normal picture taking mode");
                break;
            }
            case 1: {
                sb.append("Unknown picture taking mode");
                break;
            }
            case 2: {
                sb.append("Fast picture taking mode");
                break;
            }
            case 3: {
                sb.append("Panorama picture taking mode");
                break;
            }
            default: {
                sb.append("Unknown picture taking mode");
                break;
            }
        }
        sb.append(" - ");
        switch (o[1]) {
            case 0: {
                sb.append("Unknown sequence number");
                break;
            }
            case 1: {
                sb.append("1st in a sequnce");
                break;
            }
            case 2: {
                sb.append("2nd in a sequence");
                break;
            }
            case 3: {
                sb.append("3rd in a sequence");
                break;
            }
            default: {
                sb.append(o[1]);
                sb.append("th in a sequence");
                break;
            }
        }
        switch (o[2]) {
            case 1: {
                sb.append("Left to right panorama direction");
                break;
            }
            case 2: {
                sb.append("Right to left panorama direction");
                break;
            }
            case 3: {
                sb.append("Bottom to top panorama direction");
                break;
            }
            case 4: {
                sb.append("Top to bottom panorama direction");
                break;
            }
        }
        return sb.toString();
    }
}
