// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class f extends A
{
    public static final int \u01e0 = 0;
    public static final int \u01dc = 1;
    public static final int \u01ee = 2;
    public static final int \u01eb = 3;
    public static final int \u01f4 = 4;
    public static final int \u01ef = 5;
    public static final int \u01fb = 6;
    public static final int \u01e4 = 7;
    public static final int \u01fa = 8;
    public static final int \u01e8 = 9;
    public static final int \u01ed = 10;
    public static final int \u01f1 = 11;
    public static final int \u01f0 = 12;
    public static final int \u01e3 = 13;
    public static final int \u01ec = 14;
    public static final int \u01de = 15;
    public static final int \u01e9 = 16;
    public static final int \u01ea = 17;
    public static final int \u01e6 = 18;
    public static final int \u01dd = 19;
    public static final int \u01e2 = 20;
    public static final int \u01e1 = 21;
    public static final int \u01df = 22;
    public static final int \u01e7 = 23;
    public static final int \u01f3 = 24;
    public static final int \u01f5 = 25;
    public static final int \u01f2 = 26;
    protected static final HashMap \u01e5;
    
    public f() {
        this.A(new i(this));
    }
    
    public String F() {
        return "GPS";
    }
    
    protected HashMap D() {
        return f.\u01e5;
    }
    
    static {
        (\u01e5 = new HashMap()).put(new Integer(0), "GPS Version ID");
        f.\u01e5.put(new Integer(1), "GPS Latitude Ref");
        f.\u01e5.put(new Integer(2), "GPS Latitude");
        f.\u01e5.put(new Integer(3), "GPS Longitude Ref");
        f.\u01e5.put(new Integer(4), "GPS Longitude");
        f.\u01e5.put(new Integer(5), "GPS Altitude Ref");
        f.\u01e5.put(new Integer(6), "GPS Altitude");
        f.\u01e5.put(new Integer(7), "GPS Time-Stamp");
        f.\u01e5.put(new Integer(8), "GPS Satellites");
        f.\u01e5.put(new Integer(9), "GPS Status");
        f.\u01e5.put(new Integer(10), "GPS Measure Mode");
        f.\u01e5.put(new Integer(11), "GPS DOP");
        f.\u01e5.put(new Integer(12), "GPS Speed Ref");
        f.\u01e5.put(new Integer(13), "GPS Speed");
        f.\u01e5.put(new Integer(14), "GPS Track Ref");
        f.\u01e5.put(new Integer(15), "GPS Track");
        f.\u01e5.put(new Integer(16), "GPS Img Direction Ref");
        f.\u01e5.put(new Integer(16), "GPS Img Direction");
        f.\u01e5.put(new Integer(18), "GPS Map Datum");
        f.\u01e5.put(new Integer(19), "GPS Dest Latitude Ref");
        f.\u01e5.put(new Integer(20), "GPS Dest Latitude");
        f.\u01e5.put(new Integer(21), "GPS Dest Longitude Ref");
        f.\u01e5.put(new Integer(22), "GPS Dest Longitude");
        f.\u01e5.put(new Integer(23), "GPS Dest Bearing Ref");
        f.\u01e5.put(new Integer(24), "GPS Dest Bearing");
        f.\u01e5.put(new Integer(25), "GPS Dest Distance Ref");
        f.\u01e5.put(new Integer(26), "GPS Dest Distance");
    }
}
