// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class j extends B
{
    public j(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 1: {
                return this.\u0167();
            }
            case 2: {
                return this.\u0166();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0166() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        return H.A(this.A.O(2));
    }
    
    public String \u0167() {
        if (!this.A.N(1)) {
            return null;
        }
        final String trim = this.A.K(1).trim();
        if ("R98".equalsIgnoreCase(trim)) {
            return "Recommended Exif Interoperability Rules (ExifR98)";
        }
        return "Unknown (" + trim + ")";
    }
}
