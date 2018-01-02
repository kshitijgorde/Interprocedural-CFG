// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class _ extends A
{
    public static final int \u0148 = 1;
    public static final int \u0144 = 2;
    public static final int \u0149 = 4096;
    public static final int \u0146 = 4097;
    public static final int \u0145 = 4098;
    protected static final HashMap \u0147;
    
    public _() {
        this.A(new j(this));
    }
    
    public String F() {
        return "Interoperability";
    }
    
    protected HashMap D() {
        return _.\u0147;
    }
    
    static {
        (\u0147 = new HashMap()).put(new Integer(1), "Interoperability Index");
        _.\u0147.put(new Integer(2), "Interoperability Version");
        _.\u0147.put(new Integer(4096), "Related Image File Format");
        _.\u0147.put(new Integer(4097), "Related Image Width");
        _.\u0147.put(new Integer(4098), "Related Image Length");
    }
}
