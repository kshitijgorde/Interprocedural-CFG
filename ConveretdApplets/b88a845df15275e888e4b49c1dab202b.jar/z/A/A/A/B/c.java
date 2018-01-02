// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import java.util.HashMap;
import z.A.A.A.A;

public class c extends A
{
    public static final int \u01a5 = 1;
    public static final int \u019f = 2;
    public static final int \u01a1 = 3;
    public static final int \u019d = 4;
    public static final int \u01a7 = 7;
    public static final int \u01a3 = 10;
    public static final int \u01ab = 11;
    public static final int \u01a6 = 12;
    public static final int \u01a2 = 13;
    public static final int \u01aa = 20;
    public static final int \u01a9 = 23;
    public static final int \u01a8 = 3584;
    public static final int \u01a4 = 4096;
    public static final int \u019e = 4097;
    protected static final HashMap \u01a0;
    
    public c() {
        this.A(new B(this));
    }
    
    public String F() {
        return "Pentax Makernote";
    }
    
    protected HashMap D() {
        return c.\u01a0;
    }
    
    static {
        (\u01a0 = new HashMap()).put(new Integer(1), "Capture Mode");
        c.\u01a0.put(new Integer(2), "Quality Level");
        c.\u01a0.put(new Integer(3), "Focus Mode");
        c.\u01a0.put(new Integer(4), "Flash Mode");
        c.\u01a0.put(new Integer(7), "White Balance");
        c.\u01a0.put(new Integer(10), "Digital Zoom");
        c.\u01a0.put(new Integer(11), "Sharpness");
        c.\u01a0.put(new Integer(12), "Contrast");
        c.\u01a0.put(new Integer(13), "Saturation");
        c.\u01a0.put(new Integer(20), "ISO Speed");
        c.\u01a0.put(new Integer(23), "Colour");
        c.\u01a0.put(new Integer(3584), "Print Image Matching (PIM) Info");
        c.\u01a0.put(new Integer(4096), "Time Zone");
        c.\u01a0.put(new Integer(4097), "Daylight Savings");
    }
}
