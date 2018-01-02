// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.B;
import java.util.HashMap;
import z.A.A.A.A;

public class b extends A
{
    public static final int \u0169 = 1;
    public static final int \u0168 = 4;
    public static final int \u0170 = 6;
    public static final int \u016d = 7;
    public static final int \u0177 = 8;
    public static final int \u018c = 9;
    public static final int \u0187 = 12;
    public static final int \u0192 = 13;
    public static final int \u0178 = 15;
    public static final int \u0181 = 49409;
    public static final int \u018e = 49410;
    public static final int \u0184 = 49411;
    public static final int \u0164 = 49412;
    public static final int \u0162 = 49413;
    public static final int \u019c = 49414;
    public static final int \u0188 = 49415;
    public static final int \u019b = 49416;
    public static final int \u0198 = 49417;
    public static final int \u017b = 49418;
    public static final int \u0167 = 49419;
    public static final int \u017f = 49420;
    public static final int \u018d = 49421;
    public static final int \u0190 = 49422;
    public static final int \u0175 = 49423;
    public static final int \u016f = 49424;
    public static final int \u019a = 49425;
    public static final int \u015f = 49426;
    public static final int \u0197 = 49427;
    public static final int \u0172 = 49428;
    public static final int \u0194 = 49429;
    public static final int \u0193 = 49430;
    public static final int \u0183 = 49431;
    public static final int \u0179 = 49432;
    public static final int \u016e = 49433;
    public static final int \u0191 = 49434;
    public static final int \u0180 = 49435;
    public static final int \u016c = 49436;
    public static final int \u018f = 49437;
    public static final int \u017e = 49438;
    public static final int \u017d = 49439;
    public static final int \u0185 = 49440;
    public static final int \u0173 = 49671;
    public static final int \u0166 = 49673;
    public static final int \u018a = 49678;
    public static final int \u0182 = 49679;
    public static final int \u016b = 49680;
    public static final int \u018b = 49681;
    public static final int \u0163 = 49683;
    public static final int \u0195 = 49921;
    public static final int \u016a = 49922;
    public static final int \u017a = 49923;
    public static final int \u0186 = 49924;
    public static final int \u017c = 49925;
    public static final int \u0171 = 49926;
    public static final int \u0196 = 49927;
    public static final int \u0199 = 49928;
    public static final int \u0176 = 49929;
    public static final int \u0189 = 49930;
    public static final int \u0161 = 49931;
    public static final int \u0160 = 49932;
    public static final int \u0174 = 49933;
    protected static final HashMap \u0165;
    
    public b() {
        this.A(new J(this));
    }
    
    public String F() {
        return "Canon Makernote";
    }
    
    protected HashMap D() {
        return b.\u0165;
    }
    
    public void A(final int n, final int[] array) {
        if (n == 1) {
            final int n2 = 49408;
            for (int i = 1; i < array.length; ++i) {
                this.A(n2 + i, array[i]);
            }
        }
        else if (n == 4) {
            final int n3 = 49664;
            for (int j = 1; j < array.length; ++j) {
                this.A(n3 + j, array[j]);
            }
        }
        if (n == 15) {
            final int n4 = 49920;
            for (int k = 1; k < array.length; ++k) {
                this.A(n4 + k + 1, array[k] & 0xF);
            }
        }
        else {
            super.A(n, array);
        }
    }
    
    static {
        (\u0165 = new HashMap()).put(new Integer(7), "Firmware Version");
        b.\u0165.put(new Integer(8), "Image Number");
        b.\u0165.put(new Integer(6), "Image Type");
        b.\u0165.put(new Integer(9), "Owner Name");
        b.\u0165.put(new Integer(13), "Makernote Unknown 1");
        b.\u0165.put(new Integer(15), "Custom Functions");
        b.\u0165.put(new Integer(12), "Camera Serial Number");
        b.\u0165.put(new Integer(49427), "AF Point Selected");
        b.\u0165.put(new Integer(49413), "Continuous Drive Mode");
        b.\u0165.put(new Integer(49421), "Contrast");
        b.\u0165.put(new Integer(49419), "Easy Shooting Mode");
        b.\u0165.put(new Integer(49428), "Exposure Mode");
        b.\u0165.put(new Integer(49437), "Flash Details");
        b.\u0165.put(new Integer(49412), "Flash Mode");
        b.\u0165.put(new Integer(49433), "Focal Units per mm");
        b.\u0165.put(new Integer(49415), "Focus Mode");
        b.\u0165.put(new Integer(49440), "Focus Mode");
        b.\u0165.put(new Integer(49418), "Image Size");
        b.\u0165.put(new Integer(49424), "Iso");
        b.\u0165.put(new Integer(49431), "Long Focal Length");
        b.\u0165.put(new Integer(49409), "Macro Mode");
        b.\u0165.put(new Integer(49425), "Metering Mode");
        b.\u0165.put(new Integer(49422), "Saturation");
        b.\u0165.put(new Integer(49410), "Self Timer Delay");
        b.\u0165.put(new Integer(49423), "Sharpness");
        b.\u0165.put(new Integer(49432), "Short Focal Length");
        b.\u0165.put(new Integer(49411), "Quality");
        b.\u0165.put(new Integer(49414), "Unknown Camera State 2");
        b.\u0165.put(new Integer(49416), "Unknown Camera State 3");
        b.\u0165.put(new Integer(49417), "Unknown Camera State 4");
        b.\u0165.put(new Integer(49420), "Digital Zoom");
        b.\u0165.put(new Integer(49426), "Focus Type");
        b.\u0165.put(new Integer(49429), "Unknown Camera State 7");
        b.\u0165.put(new Integer(49430), "Unknown Camera State 8");
        b.\u0165.put(new Integer(49434), "Unknown Camera State 9");
        b.\u0165.put(new Integer(49435), "Unknown Camera State 10");
        b.\u0165.put(new Integer(49436), "Flash Activity");
        b.\u0165.put(new Integer(49438), "Unknown Camera State 12");
        b.\u0165.put(new Integer(49439), "Unknown Camera State 13");
        b.\u0165.put(new Integer(49671), "White Balance");
        b.\u0165.put(new Integer(49673), "Sequence Number");
        b.\u0165.put(new Integer(49678), "AF Point Used");
        b.\u0165.put(new Integer(49679), "Flash Bias");
        b.\u0165.put(new Integer(49680), "Auto Exposure Bracketing");
        b.\u0165.put(new Integer(49681), "AEB Bracket Value");
        b.\u0165.put(new Integer(49683), "Subject Distance");
        b.\u0165.put(new Integer(49921), "Long Exposure Noise Reduction");
        b.\u0165.put(new Integer(49922), "Shutter/Auto Exposure-lock Buttons");
        b.\u0165.put(new Integer(49923), "Mirror Lockup");
        b.\u0165.put(new Integer(49924), "Tv/Av And Exposure Level");
        b.\u0165.put(new Integer(49925), "AF-Assist Light");
        b.\u0165.put(new Integer(49926), "Shutter Speed in Av Mode");
        b.\u0165.put(new Integer(49927), "Auto-Exposure Bracketting Sequence/Auto Cancellation");
        b.\u0165.put(new Integer(49928), "Shutter Curtain Sync");
        b.\u0165.put(new Integer(49929), "Lens Auto-Focus Stop Button Function Switch");
        b.\u0165.put(new Integer(49930), "Auto Reduction of Fill Flash");
        b.\u0165.put(new Integer(49931), "Menu Button Return Position");
        b.\u0165.put(new Integer(49932), "SET Button Function When Shooting");
        b.\u0165.put(new Integer(49933), "Sensor Cleaning");
    }
}
