// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class U extends A
{
    public static final int \u0109 = 1;
    public static final int \u0101 = 2;
    public static final int \u00e8 = 3;
    public static final int \u010d = 4;
    public static final int \u00d6 = 5;
    public static final int \u00f5 = 6;
    public static final int \u010a = 7;
    public static final int \u00de = 8;
    public static final int \u00eb = 9;
    public static final int \u00ee = 10;
    public static final int \u00f4 = 11;
    public static final int \u00e6 = 12;
    public static final int \u00e5 = 13;
    public static final int \u00e3 = 14;
    public static final int \u00f2 = 15;
    public static final int \u00fa = 16;
    public static final int \u00e1 = 17;
    public static final int \u00f6 = 18;
    public static final int \u00ff = 19;
    public static final int \u0106 = 22;
    public static final int \u0104 = 23;
    public static final int \u0102 = 24;
    public static final int \u0100 = 25;
    public static final int \u00ea = 128;
    public static final int \u00fd = 129;
    public static final int \u00ec = 130;
    public static final int \u00df = 131;
    public static final int \u0108 = 132;
    public static final int \u00f3 = 133;
    public static final int \u010c = 134;
    public static final int \u00db = 135;
    public static final int \u00f8 = 136;
    public static final int \u00d8 = 137;
    public static final int \u0107 = 138;
    public static final int \u00d5 = 139;
    public static final int \u00d4 = 140;
    public static final int \u00e7 = 141;
    public static final int \u00ed = 144;
    public static final int \u00e4 = 145;
    public static final int \u010b = 146;
    public static final int \u00e9 = 149;
    public static final int \u00e2 = 151;
    public static final int \u00e0 = 152;
    public static final int \u00dd = 153;
    public static final int \u00da = 154;
    public static final int \u00fe = 160;
    public static final int \u00fc = 162;
    public static final int \u00fb = 163;
    public static final int \u0103 = 167;
    public static final int \u00f0 = 168;
    public static final int \u00ef = 169;
    public static final int \u00f9 = 170;
    public static final int \u00f1 = 171;
    public static final int \u0105 = 3585;
    public static final int \u00d9 = 3600;
    protected static final HashMap \u00dc;
    
    public U() {
        this.A(new S(this));
    }
    
    public z.A.A.C.A H() throws C {
        if (!this.N(18)) {
            return null;
        }
        return A(this.G(18));
    }
    
    public static z.A.A.C.A A(final byte[] array) {
        if (array.length == 3) {
            return new z.A.A.C.A(array[0] * array[1], array[2]);
        }
        return null;
    }
    
    public String F() {
        return "Nikon Makernote";
    }
    
    protected HashMap D() {
        return U.\u00dc;
    }
    
    static {
        (\u00dc = new HashMap()).put(new Integer(1), "Firmware Version");
        U.\u00dc.put(new Integer(2), "ISO");
        U.\u00dc.put(new Integer(4), "Quality & File Format");
        U.\u00dc.put(new Integer(5), "White Balance");
        U.\u00dc.put(new Integer(6), "Sharpening");
        U.\u00dc.put(new Integer(7), "AF Type");
        U.\u00dc.put(new Integer(11), "White Balance Fine");
        U.\u00dc.put(new Integer(12), "White Balance RB Coefficients");
        U.\u00dc.put(new Integer(19), "ISO");
        U.\u00dc.put(new Integer(15), "ISO Selection");
        U.\u00dc.put(new Integer(16), "Data Dump");
        U.\u00dc.put(new Integer(128), "Image Adjustment");
        U.\u00dc.put(new Integer(129), "Tone Compensation");
        U.\u00dc.put(new Integer(130), "Adapter");
        U.\u00dc.put(new Integer(132), "Lens");
        U.\u00dc.put(new Integer(133), "Manual Focus Distance");
        U.\u00dc.put(new Integer(134), "Digital Zoom");
        U.\u00dc.put(new Integer(141), "Colour Mode");
        U.\u00dc.put(new Integer(146), "Camera Hue Adjustment");
        U.\u00dc.put(new Integer(149), "Noise Reduction");
        U.\u00dc.put(new Integer(3585), "Capture Editor Data");
        U.\u00dc.put(new Integer(13), "Unknown 01");
        U.\u00dc.put(new Integer(14), "Unknown 02");
        U.\u00dc.put(new Integer(17), "Unknown 03");
        U.\u00dc.put(new Integer(131), "Unknown 04");
        U.\u00dc.put(new Integer(135), "Unknown 05");
        U.\u00dc.put(new Integer(136), "AF Focus Position");
        U.\u00dc.put(new Integer(137), "Unknown 07");
        U.\u00dc.put(new Integer(139), "Unknown 08");
        U.\u00dc.put(new Integer(140), "Unknown 09");
        U.\u00dc.put(new Integer(144), "Light source");
        U.\u00dc.put(new Integer(145), "Unknown 11");
        U.\u00dc.put(new Integer(151), "Unknown 12");
        U.\u00dc.put(new Integer(152), "Unknown 13");
        U.\u00dc.put(new Integer(153), "Unknown 14");
        U.\u00dc.put(new Integer(154), "Unknown 15");
        U.\u00dc.put(new Integer(3600), "Unknown 16");
        U.\u00dc.put(new Integer(8), "Flash Sync Mode");
        U.\u00dc.put(new Integer(9), "Auto Flash Mode");
        U.\u00dc.put(new Integer(18), "Auto Flash Compensation");
        U.\u00dc.put(new Integer(167), "Exposure Sequence Number");
        U.\u00dc.put(new Integer(3), "Color Mode");
        U.\u00dc.put(new Integer(138), "Unknown 20");
        U.\u00dc.put(new Integer(22), "Unknown 21");
        U.\u00dc.put(new Integer(23), "Unknown 22");
        U.\u00dc.put(new Integer(24), "Unknown 23");
        U.\u00dc.put(new Integer(25), "Unknown 24");
        U.\u00dc.put(new Integer(160), "Unknown 25");
        U.\u00dc.put(new Integer(162), "Unknown 26");
        U.\u00dc.put(new Integer(163), "Unknown 27");
        U.\u00dc.put(new Integer(170), "Unknown 29");
        U.\u00dc.put(new Integer(171), "Unknown 30");
        U.\u00dc.put(new Integer(168), "Unknown 32");
        U.\u00dc.put(new Integer(169), "Unknown 33");
    }
}
