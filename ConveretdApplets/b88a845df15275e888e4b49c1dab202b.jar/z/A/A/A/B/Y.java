// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class Y extends B
{
    public Y(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 3: {
                return this.\u0149();
            }
            case 4: {
                return this.\u014b();
            }
            case 5: {
                return this.\u0148();
            }
            case 6: {
                return this.\u0147();
            }
            case 7: {
                return this.\u014a();
            }
            case 8: {
                return this.\u014e();
            }
            case 10: {
                return this.\u014c();
            }
            case 11: {
                return this.\u014d();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u014d() throws C {
        if (!this.A.N(11)) {
            return null;
        }
        final int i = this.A.I(11);
        switch (i) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Fisheye converter";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u014c() throws C {
        if (!this.A.N(10)) {
            return null;
        }
        final z.A.A.C.A f = this.A.F(10);
        if (f.D() == 0) {
            return "No digital zoom";
        }
        return f.A(true) + "x digital zoom";
    }
    
    public String \u014e() throws C {
        if (!this.A.N(8)) {
            return null;
        }
        final z.A.A.C.A f = this.A.F(8);
        if (f.D() == 1 && f.B() == 0) {
            return "Infinite";
        }
        return f.A(true);
    }
    
    public String \u014a() throws C {
        if (!this.A.N(7)) {
            return null;
        }
        final int i = this.A.I(7);
        switch (i) {
            case 0: {
                return "Auto";
            }
            case 1: {
                return "Preset";
            }
            case 2: {
                return "Daylight";
            }
            case 3: {
                return "Incandescense";
            }
            case 4: {
                return "Flourescence";
            }
            case 5: {
                return "Cloudy";
            }
            case 6: {
                return "SpeedLight";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0147() throws C {
        if (!this.A.N(6)) {
            return null;
        }
        final int i = this.A.I(6);
        switch (i) {
            case 0: {
                return "ISO80";
            }
            case 2: {
                return "ISO160";
            }
            case 4: {
                return "ISO320";
            }
            case 5: {
                return "ISO100";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0148() throws C {
        if (!this.A.N(5)) {
            return null;
        }
        final int i = this.A.I(5);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 1: {
                return "Bright +";
            }
            case 2: {
                return "Bright -";
            }
            case 3: {
                return "Contrast +";
            }
            case 4: {
                return "Contrast -";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u014b() throws C {
        if (!this.A.N(4)) {
            return null;
        }
        final int i = this.A.I(4);
        switch (i) {
            case 1: {
                return "Color";
            }
            case 2: {
                return "Monochrome";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0149() throws C {
        if (!this.A.N(3)) {
            return null;
        }
        final int i = this.A.I(3);
        switch (i) {
            case 1: {
                return "VGA Basic";
            }
            case 2: {
                return "VGA Normal";
            }
            case 3: {
                return "VGA Fine";
            }
            case 4: {
                return "SXGA Basic";
            }
            case 5: {
                return "SXGA Normal";
            }
            case 6: {
                return "SXGA Fine";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
}
