// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;

public class A extends z.A.A.A.A
{
    public static final int G = 2;
    public static final int H = 3;
    public static final int K = 4;
    public static final int L = 5;
    public static final int M = 6;
    public static final int N = 7;
    public static final int J = 8;
    public static final int R = 9;
    public static final int O = 10;
    public static final int I = 11;
    public static final int Q = 3840;
    protected static final HashMap P;
    
    public A() {
        this.A(new Y(this));
    }
    
    public String F() {
        return "Nikon Makernote";
    }
    
    protected HashMap D() {
        return A.P;
    }
    
    static {
        (P = new HashMap()).put(new Integer(6), "CCD Sensitivity");
        A.P.put(new Integer(4), "Color Mode");
        A.P.put(new Integer(10), "Digital Zoom");
        A.P.put(new Integer(11), "Fisheye Converter");
        A.P.put(new Integer(8), "Focus");
        A.P.put(new Integer(5), "Image Adjustment");
        A.P.put(new Integer(3), "Quality");
        A.P.put(new Integer(2), "Makernote Unknown 1");
        A.P.put(new Integer(9), "Makernote Unknown 2");
        A.P.put(new Integer(3840), "Makernote Unknown 3");
        A.P.put(new Integer(7), "White Balance");
    }
}
