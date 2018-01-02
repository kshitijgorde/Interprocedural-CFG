// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class I extends A
{
    public static final int \u00d0 = 1;
    public static final int \u00d2 = 3584;
    protected static final HashMap \u00d1;
    
    public I() {
        this.A(new Q(this));
    }
    
    public String F() {
        return "Kyocera/Contax Makernote";
    }
    
    protected HashMap D() {
        return I.\u00d1;
    }
    
    static {
        (\u00d1 = new HashMap()).put(new Integer(1), "Proprietary Thumbnail Format Data");
        I.\u00d1.put(new Integer(3584), "Print Image Matching (PIM) Info");
    }
}
