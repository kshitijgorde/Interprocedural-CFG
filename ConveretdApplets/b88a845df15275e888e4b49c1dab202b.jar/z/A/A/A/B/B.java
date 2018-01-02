// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;

public class B extends z.A.A.A.B
{
    public B(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 1: {
                return this.M();
            }
            case 2: {
                return this.G();
            }
            case 3: {
                return this.H();
            }
            case 4: {
                return this.E();
            }
            case 7: {
                return this.I();
            }
            case 10: {
                return this.L();
            }
            case 11: {
                return this.N();
            }
            case 12: {
                return this.K();
            }
            case 13: {
                return this.F();
            }
            case 20: {
                return this.J();
            }
            case 23: {
                return this.D();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String D() throws C {
        if (!this.A.N(23)) {
            return null;
        }
        final int i = this.A.I(23);
        switch (i) {
            case 1: {
                return "Normal";
            }
            case 2: {
                return "Black & White";
            }
            case 3: {
                return "Sepia";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String J() throws C {
        if (!this.A.N(20)) {
            return null;
        }
        final int i = this.A.I(20);
        switch (i) {
            case 10: {
                return "ISO 100";
            }
            case 16: {
                return "ISO 200";
            }
            case 100: {
                return "ISO 100";
            }
            case 200: {
                return "ISO 200";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String F() throws C {
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
    
    public String K() throws C {
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
    
    public String N() throws C {
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
    
    public String L() throws C {
        if (!this.A.N(10)) {
            return null;
        }
        final float h = this.A.H(10);
        if (h == 0.0f) {
            return "Off";
        }
        return Float.toString(h);
    }
    
    public String I() throws C {
        if (!this.A.N(7)) {
            return null;
        }
        final int i = this.A.I(7);
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
                return "Fluorescent";
            }
            case 5: {
                return "Manual";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String E() throws C {
        if (!this.A.N(4)) {
            return null;
        }
        final int i = this.A.I(4);
        switch (i) {
            case 1: {
                return "Auto";
            }
            case 2: {
                return "Flash On";
            }
            case 4: {
                return "Flash Off";
            }
            case 6: {
                return "Red-eye Reduction";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String H() throws C {
        if (!this.A.N(3)) {
            return null;
        }
        final int i = this.A.I(3);
        switch (i) {
            case 2: {
                return "Custom";
            }
            case 3: {
                return "Auto";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String G() throws C {
        if (!this.A.N(2)) {
            return null;
        }
        final int i = this.A.I(2);
        switch (i) {
            case 0: {
                return "Good";
            }
            case 1: {
                return "Better";
            }
            case 2: {
                return "Best";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String M() throws C {
        if (!this.A.N(1)) {
            return null;
        }
        final int i = this.A.I(1);
        switch (i) {
            case 1: {
                return "Auto";
            }
            case 2: {
                return "Night-scene";
            }
            case 3: {
                return "Manual";
            }
            case 4: {
                return "Multiple";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
}
