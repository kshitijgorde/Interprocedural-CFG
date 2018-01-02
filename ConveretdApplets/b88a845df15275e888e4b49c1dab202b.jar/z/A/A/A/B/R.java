// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class R extends B
{
    public R(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 2: {
                return this.\u011f();
            }
            case 3: {
                return this.\u012e();
            }
            case 4: {
                return this.\u011c();
            }
            case 8: {
                return this.\u012b();
            }
            case 9: {
                return this.\u012c();
            }
            case 13: {
                return this.\u0125();
            }
            case 20: {
                return this.\u012a();
            }
            case 25: {
                return this.\u012d();
            }
            case 29: {
                return this.\u0130();
            }
            case 31: {
                return this.\u0121();
            }
            case 32: {
                return this.\u0116();
            }
            case 33: {
                return this.\u0124();
            }
            case 3584: {
                return this.\u0129();
            }
            case 8192: {
                return this.\u0123();
            }
            case 8209: {
                return this.\u0122();
            }
            case 8210: {
                return this.\u011e();
            }
            case 8226: {
                return this.\u0128();
            }
            case 8244: {
                return this.\u0118();
            }
            case 12288: {
                return this.\u0131();
            }
            case 12289: {
                return this.\u011b();
            }
            case 12290: {
                return this.\u0117();
            }
            case 12291: {
                return this.\u012f();
            }
            case 12294: {
                return this.\u0127();
            }
            case 12295: {
                return this.\u011a();
            }
            case 12308: {
                return this.\u011d();
            }
            case 12309: {
                return this.\u0126();
            }
            case 12310: {
                return this.\u0119();
            }
            case 12311: {
                return this.\u0120();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u0120() throws C {
        if (!this.A.N(12311)) {
            return null;
        }
        final int i = this.A.I(12311);
        switch (i) {
            case 0: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0119() throws C {
        if (!this.A.N(12310)) {
            return null;
        }
        final int i = this.A.I(12310);
        switch (i) {
            case 0: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0126() throws C {
        if (!this.A.N(12309)) {
            return null;
        }
        final int i = this.A.I(12309);
        switch (i) {
            case 0: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u011d() throws C {
        if (!this.A.N(12308)) {
            return null;
        }
        final int i = this.A.I(12308);
        switch (i) {
            case 0: {
                return "Off";
            }
            case 1: {
                return "On";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u011a() throws C {
        if (!this.A.N(12295)) {
            return null;
        }
        return "Unknown (" + this.A.I(12295) + ")";
    }
    
    public String \u0127() {
        if (!this.A.N(12294)) {
            return null;
        }
        return this.A.K(12294);
    }
    
    public String \u012f() throws C {
        if (!this.A.N(12291)) {
            return null;
        }
        final int i = this.A.I(12291);
        switch (i) {
            case 1: {
                return "Fixation";
            }
            case 6: {
                return "Multi-Area Focus";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0117() throws C {
        if (!this.A.N(12290)) {
            return null;
        }
        final int i = this.A.I(12290);
        switch (i) {
            case 3: {
                return "Fine";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u011b() throws C {
        if (!this.A.N(12289)) {
            return null;
        }
        final int i = this.A.I(12289);
        switch (i) {
            case 1: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0131() throws C {
        if (!this.A.N(12288)) {
            return null;
        }
        final int i = this.A.I(12288);
        switch (i) {
            case 2: {
                return "Normal";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0118() throws C {
        if (!this.A.N(8244)) {
            return null;
        }
        final int i = this.A.I(8244);
        switch (i) {
            case 0: {
                return "Off";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0128() throws C {
        if (!this.A.N(8226)) {
            return null;
        }
        return Integer.toString(this.A.I(8226)) + " mm";
    }
    
    public String \u011e() throws C {
        if (!this.A.N(8210)) {
            return null;
        }
        final int i = this.A.I(8210);
        switch (i) {
            case 0: {
                return "Manual";
            }
            case 1: {
                return "Auto";
            }
            case 4: {
                return "Flash";
            }
            case 12: {
                return "Flash";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0122() {
        if (!this.A.N(8209)) {
            return null;
        }
        return this.A.K(8209);
    }
    
    public String \u0123() throws C {
        if (!this.A.N(8192)) {
            return null;
        }
        return "<" + this.A.G(8192).length + " bytes of image data>";
    }
    
    public String \u0129() {
        if (!this.A.N(3584)) {
            return null;
        }
        return this.A.K(3584);
    }
    
    public String \u0124() throws C {
        if (!this.A.N(33)) {
            return null;
        }
        final int i = this.A.I(33);
        switch (i) {
            case 0: {
                return "-1";
            }
            case 1: {
                return "Normal";
            }
            case 2: {
                return "+1";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0116() throws C {
        if (!this.A.N(32)) {
            return null;
        }
        final int i = this.A.I(32);
        switch (i) {
            case 0: {
                return "-1";
            }
            case 1: {
                return "Normal";
            }
            case 2: {
                return "+1";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0121() throws C {
        if (!this.A.N(31)) {
            return null;
        }
        final int i = this.A.I(31);
        switch (i) {
            case 0: {
                return "-1";
            }
            case 1: {
                return "Normal";
            }
            case 2: {
                return "+1";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0130() throws C {
        if (!this.A.N(29)) {
            return null;
        }
        return Double.toString(this.A.B(29) / 10.0) + " mm";
    }
    
    public String \u012d() throws C {
        if (!this.A.N(25)) {
            return null;
        }
        final int i = this.A.I(25);
        switch (i) {
            case 0: {
                return "Auto";
            }
            case 1: {
                return "Daylight";
            }
            case 2: {
                return "Shade";
            }
            case 3: {
                return "Tungsten";
            }
            case 4: {
                return "Flourescent";
            }
            case 5: {
                return "Manual";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u012a() throws C {
        if (!this.A.N(20)) {
            return null;
        }
        final int i = this.A.I(20);
        switch (i) {
            case 3: {
                return "50";
            }
            case 4: {
                return "64";
            }
            case 6: {
                return "100";
            }
            case 9: {
                return "200";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0125() throws C {
        if (!this.A.N(13)) {
            return null;
        }
        final int i = this.A.I(13);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 1: {
                return "Macro";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u012c() throws C {
        if (!this.A.N(9)) {
            return null;
        }
        final int i = this.A.I(9);
        switch (i) {
            case 0: {
                return "640 x 480 pixels";
            }
            case 4: {
                return "1600 x 1200 pixels";
            }
            case 5: {
                return "2048 x 1536 pixels";
            }
            case 20: {
                return "2288 x 1712 pixels";
            }
            case 21: {
                return "2592 x 1944 pixels";
            }
            case 22: {
                return "2304 x 1728 pixels";
            }
            case 36: {
                return "3008 x 2008 pixels";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u012b() throws C {
        if (!this.A.N(8)) {
            return null;
        }
        final int i = this.A.I(8);
        switch (i) {
            case 1: {
                return "Fine";
            }
            case 2: {
                return "Super Fine";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u011c() {
        if (!this.A.N(4)) {
            return null;
        }
        return this.A.K(4);
    }
    
    public String \u012e() throws C {
        if (!this.A.N(3)) {
            return null;
        }
        return Integer.toString(this.A.I(3)) + " bytes";
    }
    
    public String \u011f() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        final int[] o = this.A.O(2);
        if (o.length != 2) {
            return this.A.K(2);
        }
        return o[0] + " x " + o[1] + " pixels";
    }
}
