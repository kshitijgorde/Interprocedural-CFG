// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import java.io.UnsupportedEncodingException;
import z.A.A.A.C;
import z.A.A.A.A;
import java.text.DecimalFormat;
import z.A.A.A.B;

public class H extends B
{
    private boolean C;
    private static final DecimalFormat B;
    
    public H(final A a) {
        super(a);
        this.C = true;
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 274: {
                return this.v();
            }
            case 254: {
                return this.m();
            }
            case 255: {
                return this.\u00c3();
            }
            case 263: {
                return this.\u00d3();
            }
            case 266: {
                return this.r();
            }
            case 296: {
                return this.i();
            }
            case 531: {
                return this.j();
            }
            case 33434: {
                return this.\u00e3();
            }
            case 37377: {
                return this.y();
            }
            case 33437: {
                return this.\u00e1();
            }
            case 282: {
                return this.¥();
            }
            case 283: {
                return this.q();
            }
            case 513: {
                return this.\u00cd();
            }
            case 514: {
                return this.\u00c1();
            }
            case 37122: {
                return this.f();
            }
            case 37382: {
                return this.\u00e5();
            }
            case 37383: {
                return this.\u00c2();
            }
            case 37384: {
                return this.l();
            }
            case 37385: {
                return this.\u00ce();
            }
            case 37386: {
                return this.\u00dc();
            }
            case 40961: {
                return this.ª();
            }
            case 40962: {
                return this.\u00c8();
            }
            case 40963: {
                return this.\u00d2();
            }
            case 41488: {
                return this.µ();
            }
            case 41486: {
                return this.\u00e4();
            }
            case 41487: {
                return this.\u00d8();
            }
            case 256: {
                return this.\u00c0();
            }
            case 257: {
                return this.º();
            }
            case 258: {
                return this.\u00e0();
            }
            case 259: {
                return this.\u00d9();
            }
            case 262: {
                return this.x();
            }
            case 278: {
                return this.\u00ca();
            }
            case 279: {
                return this.\u00cf();
            }
            case 277: {
                return this.\u00da();
            }
            case 284: {
                return this.\u00cb();
            }
            case 530: {
                return this.t();
            }
            case 34850: {
                return this.\u00de();
            }
            case 37378: {
                return this.¢();
            }
            case 37381: {
                return this.o();
            }
            case 41495: {
                return this.\u00c4();
            }
            case 37380: {
                return this.u();
            }
            case 41728: {
                return this.\u00e2();
            }
            case 41729: {
                return this.z();
            }
            case 37121: {
                return this.p();
            }
            case 36864: {
                return this.k();
            }
            case 40960: {
                return this.e();
            }
            case 532: {
                return this.\u00db();
            }
            case 34855: {
                return this.n();
            }
            case 61441: {
                return this.\u00c6();
            }
            case 37510: {
                return this.s();
            }
            case 41985: {
                return this.\u00d0();
            }
            case 41986: {
                return this.\u00dd();
            }
            case 41987: {
                return this.\u00c9();
            }
            case 41988: {
                return this.\u00c5();
            }
            case 41989: {
                return this.\u00d6();
            }
            case 41990: {
                return this.\u00d5();
            }
            case 41991: {
                return this.\u00df();
            }
            case 41992: {
                return this.\u00cc();
            }
            case 41993: {
                return this.w();
            }
            case 41994: {
                return this.£();
            }
            case 41996: {
                return this.\u00d1();
            }
            case 40093: {
                return this.\u00c7();
            }
            case 40092: {
                return this.h();
            }
            case 40094: {
                return this.g();
            }
            case 40095: {
                return this.¤();
            }
            case 40091: {
                return this.\u00d4();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String m() throws C {
        if (!this.A.N(254)) {
            return null;
        }
        switch (this.A.I(254)) {
            case 1: {
                return "Full-resolution image";
            }
            case 2: {
                return "Reduced-resolution image";
            }
            case 3: {
                return "Single page of multi-page reduced-resolution image";
            }
            case 4: {
                return "Transparency mask";
            }
            case 5: {
                return "Transparency mask of reduced-resolution image";
            }
            case 6: {
                return "Transparency mask of multi-page image";
            }
            case 7: {
                return "Transparency mask of reduced-resolution multi-page image";
            }
            default: {
                return "Unknown (" + this.A.I(254) + ")";
            }
        }
    }
    
    public String \u00c3() throws C {
        if (!this.A.N(255)) {
            return null;
        }
        switch (this.A.I(255)) {
            case 1: {
                return "Full-resolution image";
            }
            case 2: {
                return "Reduced-resolution image";
            }
            case 3: {
                return "Single page of multi-page image";
            }
            default: {
                return "Unknown (" + this.A.I(255) + ")";
            }
        }
    }
    
    public String \u00d3() throws C {
        if (!this.A.N(263)) {
            return null;
        }
        switch (this.A.I(263)) {
            case 1: {
                return "No dithering or halftoning";
            }
            case 2: {
                return "Ordered dither or halftone";
            }
            case 3: {
                return "Randomized dither";
            }
            default: {
                return "Unknown (" + this.A.I(263) + ")";
            }
        }
    }
    
    public String r() throws C {
        if (!this.A.N(266)) {
            return null;
        }
        switch (this.A.I(266)) {
            case 1: {
                return "Normal";
            }
            case 2: {
                return "Reversed";
            }
            default: {
                return "Unknown (" + this.A.I(266) + ")";
            }
        }
    }
    
    public String \u00d1() throws C {
        if (!this.A.N(41996)) {
            return null;
        }
        switch (this.A.I(41996)) {
            case 0: {
                return "Unknown";
            }
            case 1: {
                return "Macro";
            }
            case 2: {
                return "Close view";
            }
            case 3: {
                return "Distant view";
            }
            default: {
                return "Unknown (" + this.A.I(41996) + ")";
            }
        }
    }
    
    public String £() throws C {
        if (!this.A.N(41994)) {
            return null;
        }
        switch (this.A.I(41994)) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Low";
            }
            case 2: {
                return "Hard";
            }
            default: {
                return "Unknown (" + this.A.I(41994) + ")";
            }
        }
    }
    
    public String w() throws C {
        if (!this.A.N(41993)) {
            return null;
        }
        switch (this.A.I(41993)) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Low saturation";
            }
            case 2: {
                return "High saturation";
            }
            default: {
                return "Unknown (" + this.A.I(41993) + ")";
            }
        }
    }
    
    public String \u00cc() throws C {
        if (!this.A.N(41992)) {
            return null;
        }
        switch (this.A.I(41992)) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Soft";
            }
            case 2: {
                return "Hard";
            }
            default: {
                return "Unknown (" + this.A.I(41992) + ")";
            }
        }
    }
    
    public String \u00df() throws C {
        if (!this.A.N(41991)) {
            return null;
        }
        switch (this.A.I(41991)) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Low gain up";
            }
            case 2: {
                return "Low gain down";
            }
            case 3: {
                return "High gain up";
            }
            case 4: {
                return "High gain down";
            }
            default: {
                return "Unknown (" + this.A.I(41991) + ")";
            }
        }
    }
    
    public String \u00d5() throws C {
        if (!this.A.N(41990)) {
            return null;
        }
        switch (this.A.I(41990)) {
            case 0: {
                return "Standard";
            }
            case 1: {
                return "Landscape";
            }
            case 2: {
                return "Portrait";
            }
            case 3: {
                return "Night scene";
            }
            default: {
                return "Unknown (" + this.A.I(41990) + ")";
            }
        }
    }
    
    public String \u00d6() throws C {
        if (!this.A.N(41989)) {
            return null;
        }
        final int i = this.A.I(41989);
        if (i == 0) {
            return "Unknown";
        }
        return H.B.format(i) + "mm";
    }
    
    public String \u00c5() throws C {
        if (!this.A.N(41988)) {
            return null;
        }
        final z.A.A.C.A f = this.A.F(41988);
        if (f.D() == 0) {
            return "Digital zoom not used.";
        }
        return H.B.format(f.doubleValue());
    }
    
    public String \u00c9() throws C {
        if (!this.A.N(41987)) {
            return null;
        }
        switch (this.A.I(41987)) {
            case 0: {
                return "Auto white balance";
            }
            case 1: {
                return "Manual white balance";
            }
            default: {
                return "Unknown (" + this.A.I(41987) + ")";
            }
        }
    }
    
    public String \u00dd() throws C {
        if (!this.A.N(41986)) {
            return null;
        }
        switch (this.A.I(41986)) {
            case 0: {
                return "Auto exposure";
            }
            case 1: {
                return "Manual exposure";
            }
            case 2: {
                return "Auto bracket";
            }
            default: {
                return "Unknown (" + this.A.I(41986) + ")";
            }
        }
    }
    
    public String \u00d0() throws C {
        if (!this.A.N(41985)) {
            return null;
        }
        switch (this.A.I(41985)) {
            case 0: {
                return "Normal process";
            }
            case 1: {
                return "Custom process";
            }
            default: {
                return "Unknown (" + this.A.I(41985) + ")";
            }
        }
    }
    
    public String s() throws C {
        if (!this.A.N(37510)) {
            return null;
        }
        final byte[] g = this.A.G(37510);
        if (g.length == 0) {
            return "";
        }
        final String[] array = { "ASCII", "UNICODE", "JIS" };
        if (g.length >= 10) {
            final String s = new String(g, 0, 10);
            for (int i = 0; i < array.length; ++i) {
                final String s2 = array[i];
                if (s.startsWith(s2)) {
                    for (int j = s2.length(); j < 10; ++j) {
                        final byte b = g[j];
                        if (b != 0 && b != 32) {
                            if (s2.equals("UNICODE")) {
                                try {
                                    return new String(g, j, g.length - j, "UTF-16LE").trim();
                                }
                                catch (UnsupportedEncodingException ex) {
                                    return null;
                                }
                            }
                            return new String(g, j, g.length - j).trim();
                        }
                    }
                    return new String(g, 10, g.length - 10).trim();
                }
            }
        }
        return new String(g).trim();
    }
    
    public String \u00c6() throws C {
        if (!this.A.N(61441)) {
            return null;
        }
        return "[" + this.A.O(61441).length + " bytes of thumbnail data]";
    }
    
    public String n() throws C {
        if (!this.A.N(34855)) {
            return null;
        }
        int i = this.A.I(34855);
        if (i < 50) {
            i *= 200;
        }
        return Integer.toString(i);
    }
    
    public String \u00db() throws C {
        if (!this.A.N(532)) {
            return null;
        }
        final int[] o = this.A.O(532);
        return "[" + o[0] + "," + o[2] + "," + o[4] + "] " + "[" + o[1] + "," + o[3] + "," + o[5] + "]";
    }
    
    public String k() throws C {
        if (!this.A.N(36864)) {
            return null;
        }
        return A(this.A.O(36864));
    }
    
    public String e() throws C {
        if (!this.A.N(40960)) {
            return null;
        }
        return A(this.A.O(40960));
    }
    
    public String z() throws C {
        if (!this.A.N(41729)) {
            return null;
        }
        final int i = this.A.I(41729);
        if (i == 1) {
            return "Directly photographed image";
        }
        return "Unknown (" + i + ")";
    }
    
    public String \u00e2() throws C {
        if (!this.A.N(41728)) {
            return null;
        }
        final int i = this.A.I(41728);
        if (i == 3) {
            return "Digital Still Camera (DSC)";
        }
        return "Unknown (" + i + ")";
    }
    
    public String u() throws C {
        if (!this.A.N(37380)) {
            return null;
        }
        return this.A.F(37380).A(true) + " EV";
    }
    
    public String o() throws C {
        if (!this.A.N(37381)) {
            return null;
        }
        return "F" + H.B.format(z.A.A.B.A.A(this.A.B(37381)));
    }
    
    public String ¢() throws C {
        if (!this.A.N(37378)) {
            return null;
        }
        return "F" + H.B.format(z.A.A.B.A.A(this.A.B(37378)));
    }
    
    public String \u00de() throws C {
        if (!this.A.N(34850)) {
            return null;
        }
        switch (this.A.I(34850)) {
            case 1: {
                return "Manual control";
            }
            case 2: {
                return "Program normal";
            }
            case 3: {
                return "Aperture priority";
            }
            case 4: {
                return "Shutter priority";
            }
            case 5: {
                return "Program creative (slow program)";
            }
            case 6: {
                return "Program action (high-speed program)";
            }
            case 7: {
                return "Portrait mode";
            }
            case 8: {
                return "Landscape mode";
            }
            default: {
                return "Unknown program (" + this.A.I(34850) + ")";
            }
        }
    }
    
    public String t() throws C {
        if (!this.A.N(530)) {
            return null;
        }
        final int[] o = this.A.O(530);
        if (o[0] == 2 && o[1] == 1) {
            return "YCbCr4:2:2";
        }
        if (o[0] == 2 && o[1] == 2) {
            return "YCbCr4:2:0";
        }
        return "(Unknown)";
    }
    
    public String \u00cb() throws C {
        if (!this.A.N(284)) {
            return null;
        }
        switch (this.A.I(284)) {
            case 1: {
                return "Chunky (contiguous for each subsampling pixel)";
            }
            case 2: {
                return "Separate (Y-plane/Cb-plane/Cr-plane format)";
            }
            default: {
                return "Unknown configuration";
            }
        }
    }
    
    public String \u00da() {
        if (!this.A.N(277)) {
            return null;
        }
        return this.A.K(277) + " samples/pixel";
    }
    
    public String \u00ca() {
        if (!this.A.N(278)) {
            return null;
        }
        return this.A.K(278) + " rows/strip";
    }
    
    public String \u00cf() {
        if (!this.A.N(279)) {
            return null;
        }
        return this.A.K(279) + " bytes";
    }
    
    public String x() throws C {
        if (!this.A.N(262)) {
            return null;
        }
        switch (this.A.I(262)) {
            case 0: {
                return "WhiteIsZero";
            }
            case 1: {
                return "BlackIsZero";
            }
            case 2: {
                return "RGB";
            }
            case 3: {
                return "RGB Palette";
            }
            case 4: {
                return "Transparency Mask";
            }
            case 5: {
                return "CMYK";
            }
            case 6: {
                return "YCbCr";
            }
            case 8: {
                return "CIELab";
            }
            case 9: {
                return "ICCLab";
            }
            case 10: {
                return "ITULab";
            }
            case 32803: {
                return "Color Filter Array";
            }
            case 32844: {
                return "Pixar LogL";
            }
            case 32845: {
                return "Pixar LogLuv";
            }
            case 32892: {
                return "Linear Raw";
            }
            default: {
                return "Unknown colour space";
            }
        }
    }
    
    public String \u00d9() throws C {
        if (!this.A.N(259)) {
            return null;
        }
        switch (this.A.I(259)) {
            case 1: {
                return "Uncompressed";
            }
            case 2: {
                return "CCITT 1D";
            }
            case 3: {
                return "T4/Group 3 Fax";
            }
            case 4: {
                return "T6/Group 4 Fax";
            }
            case 5: {
                return "LZW";
            }
            case 6: {
                return "JPEG (old-style)";
            }
            case 7: {
                return "JPEG";
            }
            case 8: {
                return "Adobe Deflate";
            }
            case 9: {
                return "JBIG B&W";
            }
            case 10: {
                return "JBIG Color";
            }
            case 32766: {
                return "Next";
            }
            case 32771: {
                return "CCIRLEW";
            }
            case 32773: {
                return "PackBits";
            }
            case 32809: {
                return "Thunderscan";
            }
            case 32895: {
                return "IT8CTPAD";
            }
            case 32896: {
                return "IT8LW";
            }
            case 32897: {
                return "IT8MP";
            }
            case 32898: {
                return "IT8BL";
            }
            case 32908: {
                return "PixarFilm";
            }
            case 32909: {
                return "PixarLog";
            }
            case 32946: {
                return "Deflate";
            }
            case 32947: {
                return "DCS";
            }
            case 32661: {
                return "JBIG";
            }
            case 32676: {
                return "SGILog";
            }
            case 32677: {
                return "SGILog24";
            }
            case 32712: {
                return "JPEG 2000";
            }
            case 32713: {
                return "Nikon NEF Compressed";
            }
            default: {
                return "Unknown compression";
            }
        }
    }
    
    public String \u00e0() {
        if (!this.A.N(258)) {
            return null;
        }
        return this.A.K(258) + " bits/component/pixel";
    }
    
    public String \u00c0() {
        if (!this.A.N(256)) {
            return null;
        }
        return this.A.K(256) + " pixels";
    }
    
    public String º() {
        if (!this.A.N(257)) {
            return null;
        }
        return this.A.K(257) + " pixels";
    }
    
    public String \u00e4() throws C {
        if (!this.A.N(41486)) {
            return null;
        }
        return this.A.F(41486).C().A(this.C) + " " + this.µ().toLowerCase();
    }
    
    public String \u00d8() throws C {
        if (!this.A.N(41487)) {
            return null;
        }
        return this.A.F(41487).C().A(this.C) + " " + this.µ().toLowerCase();
    }
    
    public String µ() throws C {
        if (!this.A.N(41488)) {
            return null;
        }
        switch (this.A.I(41488)) {
            case 1: {
                return "(No unit)";
            }
            case 2: {
                return "Inches";
            }
            case 3: {
                return "cm";
            }
            default: {
                return "";
            }
        }
    }
    
    public String \u00c8() throws C {
        if (!this.A.N(40962)) {
            return null;
        }
        return this.A.I(40962) + " pixels";
    }
    
    public String \u00d2() throws C {
        if (!this.A.N(40963)) {
            return null;
        }
        return this.A.I(40963) + " pixels";
    }
    
    public String ª() throws C {
        if (!this.A.N(40961)) {
            return null;
        }
        final int i = this.A.I(40961);
        if (i == 1) {
            return "sRGB";
        }
        if (i == 65535) {
            return "Undefined";
        }
        return "Unknown";
    }
    
    public String \u00dc() throws C {
        if (!this.A.N(37386)) {
            return null;
        }
        return new DecimalFormat("0.0##").format(this.A.F(37386).doubleValue()) + " mm";
    }
    
    public String \u00ce() throws C {
        if (!this.A.N(37385)) {
            return null;
        }
        final int i = this.A.I(37385);
        final StringBuffer sb = new StringBuffer();
        if ((i & 0x1) != 0x0) {
            sb.append("Flash fired");
        }
        else {
            sb.append("Flash did not fire");
        }
        if ((i & 0x4) != 0x0) {
            if ((i & 0x2) != 0x0) {
                sb.append(", return detected");
            }
            else {
                sb.append(", return not detected");
            }
        }
        if ((i & 0x10) != 0x0) {
            sb.append(", auto");
        }
        if ((i & 0x40) != 0x0) {
            sb.append(", red-eye reduction");
        }
        return sb.toString();
    }
    
    public String l() throws C {
        if (!this.A.N(37384)) {
            return null;
        }
        switch (this.A.I(37384)) {
            case 0: {
                return "Unknown";
            }
            case 1: {
                return "Daylight";
            }
            case 2: {
                return "Flourescent";
            }
            case 3: {
                return "Tungsten";
            }
            case 10: {
                return "Flash";
            }
            case 17: {
                return "Standard light";
            }
            case 18: {
                return "Standard light (B)";
            }
            case 19: {
                return "Standard light (C)";
            }
            case 20: {
                return "D55";
            }
            case 21: {
                return "D65";
            }
            case 22: {
                return "D75";
            }
            case 255: {
                return "(Other)";
            }
            default: {
                return "Unknown (" + this.A.I(37384) + ")";
            }
        }
    }
    
    public String \u00c2() throws C {
        if (!this.A.N(37383)) {
            return null;
        }
        switch (this.A.I(37383)) {
            case 0: {
                return "Unknown";
            }
            case 1: {
                return "Average";
            }
            case 2: {
                return "Center weighted average";
            }
            case 3: {
                return "Spot";
            }
            case 4: {
                return "Multi-spot";
            }
            case 5: {
                return "Multi-segment";
            }
            case 6: {
                return "Partial";
            }
            case 255: {
                return "(Other)";
            }
            default: {
                return "";
            }
        }
    }
    
    public String \u00e5() throws C {
        if (!this.A.N(37382)) {
            return null;
        }
        return new DecimalFormat("0.0##").format(this.A.F(37382).doubleValue()) + " metres";
    }
    
    public String f() throws C {
        if (!this.A.N(37122)) {
            return null;
        }
        final z.A.A.C.A f = this.A.F(37122);
        final String a = f.A(this.C);
        if (f.A() && f.intValue() == 1) {
            return a + " bit/pixel";
        }
        return a + " bits/pixel";
    }
    
    public String \u00c1() {
        if (!this.A.N(514)) {
            return null;
        }
        return this.A.K(514) + " bytes";
    }
    
    public String \u00cd() {
        if (!this.A.N(513)) {
            return null;
        }
        return this.A.K(513) + " bytes";
    }
    
    public String q() throws C {
        if (!this.A.N(283)) {
            return null;
        }
        return this.A.F(283).A(this.C) + " dots per " + this.i().toLowerCase();
    }
    
    public String ¥() throws C {
        if (!this.A.N(282)) {
            return null;
        }
        return this.A.F(282).A(this.C) + " dots per " + this.i().toLowerCase();
    }
    
    public String \u00e3() {
        if (!this.A.N(33434)) {
            return null;
        }
        return this.A.K(33434) + " sec";
    }
    
    public String y() throws C {
        if (!this.A.N(37377)) {
            return null;
        }
        final float h = this.A.H(37377);
        if (h <= 1.0f) {
            return Math.round((float)(1.0 / Math.exp(h * Math.log(2.0))) * 10.0) / 10.0f + " sec";
        }
        return "1/" + (int)Math.exp(h * Math.log(2.0)) + " sec";
    }
    
    public String \u00e1() throws C {
        if (!this.A.N(33437)) {
            return null;
        }
        return "F" + H.B.format(this.A.F(33437).doubleValue());
    }
    
    public String j() throws C {
        if (!this.A.N(531)) {
            return null;
        }
        final int i = this.A.I(531);
        switch (i) {
            case 1: {
                return "Center of pixel array";
            }
            case 2: {
                return "Datum point";
            }
            default: {
                return String.valueOf(i);
            }
        }
    }
    
    public String v() throws C {
        if (!this.A.N(274)) {
            return null;
        }
        final int i = this.A.I(274);
        switch (i) {
            case 1: {
                return "Top, left side (Horizontal / normal)";
            }
            case 2: {
                return "Top, right side (Mirror horizontal)";
            }
            case 3: {
                return "Bottom, right side (Rotate 180)";
            }
            case 4: {
                return "Bottom, left side (Mirror vertical)";
            }
            case 5: {
                return "Left side, top (Mirror horizontal and rotate 270 CW)";
            }
            case 6: {
                return "Right side, top (Rotate 90 CW)";
            }
            case 7: {
                return "Right side, bottom (Mirror horizontal and rotate 90 CW)";
            }
            case 8: {
                return "Left side, bottom (Rotate 270 CW)";
            }
            default: {
                return String.valueOf(i);
            }
        }
    }
    
    public String i() throws C {
        if (!this.A.N(296)) {
            return "";
        }
        switch (this.A.I(296)) {
            case 1: {
                return "(No unit)";
            }
            case 2: {
                return "Inch";
            }
            case 3: {
                return "cm";
            }
            default: {
                return "";
            }
        }
    }
    
    public String \u00c4() throws C {
        if (!this.A.N(41495)) {
            return null;
        }
        switch (this.A.I(41495)) {
            case 1: {
                return "(Not defined)";
            }
            case 2: {
                return "One-chip color area sensor";
            }
            case 3: {
                return "Two-chip color area sensor";
            }
            case 4: {
                return "Three-chip color area sensor";
            }
            case 5: {
                return "Color sequential area sensor";
            }
            case 7: {
                return "Trilinear sensor";
            }
            case 8: {
                return "Color sequential linear sensor";
            }
            default: {
                return "";
            }
        }
    }
    
    public String p() throws C {
        final int[] o = this.A.O(37121);
        final String[] array = { "", "Y", "Cb", "Cr", "R", "G", "B" };
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Math.min(4, o.length); ++i) {
            final int n = o[i];
            if (n > 0 && n < array.length) {
                sb.append(array[n]);
            }
        }
        return sb.toString();
    }
    
    public static String A(final int[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; n < 4 && n < array.length; ++n) {
            if (n == 2) {
                sb.append('.');
            }
            final String value = String.valueOf((char)array[n]);
            if (n != 0 || !"0".equals(value)) {
                sb.append(value);
            }
        }
        return sb.toString();
    }
    
    private String C(final int n) throws C {
        if (!this.A.N(n)) {
            return null;
        }
        final byte[] g = this.A.G(n);
        try {
            return new String(g, "UTF-16LE").trim();
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public String \u00c7() throws C {
        return this.C(40093);
    }
    
    public String h() throws C {
        return this.C(40092);
    }
    
    public String g() throws C {
        return this.C(40094);
    }
    
    public String \u00d4() throws C {
        return this.C(40091);
    }
    
    public String ¤() throws C {
        return this.C(40095);
    }
    
    static {
        B = new DecimalFormat("0.#");
    }
}
