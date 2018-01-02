// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class Z extends A
{
    public static final int \u0142 = 1;
    public static final int \u013f = 2;
    public static final int \u013e = 28;
    public static final int \u0143 = 31;
    public static final int \u0141 = 3584;
    protected static final HashMap \u0140;
    
    public Z() {
        this.A(new O(this));
    }
    
    public String F() {
        return "Panasonic Makernote";
    }
    
    protected HashMap D() {
        return Z.\u0140;
    }
    
    static {
        (\u0140 = new HashMap()).put(new Integer(1), "Quality Mode");
        Z.\u0140.put(new Integer(2), "Version");
        Z.\u0140.put(new Integer(28), "Macro Mode");
        Z.\u0140.put(new Integer(31), "Record Mode");
        Z.\u0140.put(new Integer(3584), "Print Image Matching (PIM) Info");
    }
}
