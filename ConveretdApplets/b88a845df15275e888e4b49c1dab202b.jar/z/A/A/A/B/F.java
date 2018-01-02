// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class F extends B
{
    public F(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 1: {
                return this.Z();
            }
            case 2: {
                return this.Y();
            }
            case 3: {
                return this.V();
            }
            case 4: {
                return this.S();
            }
            case 5: {
                return this.c();
            }
            case 6: {
                return this.W();
            }
            case 7: {
                return this._();
            }
            case 10: {
                return this.b();
            }
            case 11: {
                return this.d();
            }
            case 12: {
                return this.a();
            }
            case 13: {
                return this.U();
            }
            case 20: {
                return this.T();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    private String T() throws C {
        if (!this.A.N(20)) {
            return null;
        }
        final int i = this.A.I(20);
        switch (i) {
            case 64: {
                return "Normal";
            }
            case 125: {
                return "+1.0";
            }
            case 250: {
                return "+2.0";
            }
            case 244: {
                return "+3.0";
            }
            case 80: {
                return "Normal";
            }
            case 100: {
                return "High";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String U() throws C {
        if (!this.A.N(13)) {
            return null;
        }
        final int i = this.A.I(13);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 1: {
                return "Low";
            }
            case 2: {
                return "High";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String a() throws C {
        if (!this.A.N(12)) {
            return null;
        }
        final int i = this.A.I(12);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 1: {
                return "Low";
            }
            case 2: {
                return "High";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String d() throws C {
        if (!this.A.N(11)) {
            return null;
        }
        final int i = this.A.I(11);
        switch (i) {
            case 0: {
                return "Normal";
            }
            case 1: {
                return "Soft";
            }
            case 2: {
                return "Hard";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String b() throws C {
        if (!this.A.N(10)) {
            return null;
        }
        final int i = this.A.I(10);
        switch (i) {
            case 65536: {
                return "No digital zoom";
            }
            case 65537: {
                return "2x digital zoom";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String _() throws C {
        if (!this.A.N(7)) {
            return null;
        }
        final int i = this.A.I(7);
        switch (i) {
            case 1: {
                return "Auto";
            }
            case 2: {
                return "Tungsten";
            }
            case 3: {
                return "Daylight";
            }
            case 4: {
                return "Flourescent";
            }
            case 5: {
                return "Shade";
            }
            case 129: {
                return "Manual";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String W() throws C {
        if (!this.A.N(6)) {
            return null;
        }
        return this.A.I(6) + "mm";
    }
    
    private String c() throws C {
        if (!this.A.N(5)) {
            return null;
        }
        final int i = this.A.I(5);
        switch (i) {
            case 11: {
                return "Weak";
            }
            case 13: {
                return "Normal";
            }
            case 15: {
                return "Strong";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String S() throws C {
        if (!this.A.N(4)) {
            return null;
        }
        final int i = this.A.I(4);
        switch (i) {
            case 1: {
                return "Auto";
            }
            case 2: {
                return "On";
            }
            case 3: {
                return "Off";
            }
            case 4: {
                return "Red eye reduction";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String V() throws C {
        if (!this.A.N(3)) {
            return null;
        }
        final int i = this.A.I(3);
        switch (i) {
            case 2: {
                return "Macro";
            }
            case 3: {
                return "Auto focus";
            }
            case 4: {
                return "Manual focus";
            }
            case 5: {
                return "Infinity";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String Y() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        final int i = this.A.I(2);
        switch (i) {
            case 1: {
                return "Economy";
            }
            case 2: {
                return "Normal";
            }
            case 3: {
                return "Fine";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String X() throws C {
        if (!this.A.N(3)) {
            return null;
        }
        final int i = this.A.I(3);
        switch (i) {
            case 2: {
                return "Macro";
            }
            case 3: {
                return "Auto focus";
            }
            case 4: {
                return "Manual focus";
            }
            case 5: {
                return "Infinity";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    private String Z() throws C {
        if (!this.A.N(1)) {
            return null;
        }
        final int i = this.A.I(1);
        switch (i) {
            case 1: {
                return "Single shutter";
            }
            case 2: {
                return "Panorama";
            }
            case 3: {
                return "Night scene";
            }
            case 4: {
                return "Portrait";
            }
            case 5: {
                return "Landscape";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
}
