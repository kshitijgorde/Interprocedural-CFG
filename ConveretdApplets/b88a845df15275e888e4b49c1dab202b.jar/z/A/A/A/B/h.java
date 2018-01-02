// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import z.A.A.A.C;
import z.A.A.A.A;
import z.A.A.A.B;

public class h extends B
{
    public h(final A a) {
        super(a);
    }
    
    public String A(final int n) throws C {
        switch (n) {
            case 4097: {
                return this.\u015c();
            }
            case 4098: {
                return this.\u0157();
            }
            case 4099: {
                return this.\u0158();
            }
            case 4100: {
                return this.\u0154();
            }
            case 4112: {
                return this.\u0150();
            }
            case 4113: {
                return this.\u014f();
            }
            case 4128: {
                return this.\u015a();
            }
            case 4129: {
                return this.\u0155();
            }
            case 4144: {
                return this.\u0151();
            }
            case 4145: {
                return this.\u0156();
            }
            case 4352: {
                return this.\u0152();
            }
            case 4864: {
                return this.\u0153();
            }
            case 4865: {
                return this.\u0159();
            }
            case 4866: {
                return this.\u015b();
            }
            default: {
                return this.A.K(n);
            }
        }
    }
    
    public String \u015b() throws C {
        if (!this.A.N(4866)) {
            return null;
        }
        final int i = this.A.I(4866);
        switch (i) {
            case 0: {
                return "AE good";
            }
            case 1: {
                return "Over exposed (>1/1000s @ F11)";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0159() throws C {
        if (!this.A.N(4865)) {
            return null;
        }
        final int i = this.A.I(4865);
        switch (i) {
            case 0: {
                return "Auto focus good";
            }
            case 1: {
                return "Out of focus";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0153() throws C {
        if (!this.A.N(4864)) {
            return null;
        }
        final int i = this.A.I(4864);
        switch (i) {
            case 0: {
                return "No blur warning";
            }
            case 1: {
                return "Blur warning";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0152() throws C {
        if (!this.A.N(4352)) {
            return null;
        }
        final int i = this.A.I(4352);
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
    
    public String \u0156() throws C {
        if (!this.A.N(4145)) {
            return null;
        }
        final int i = this.A.I(4145);
        switch (i) {
            case 0: {
                return "Auto";
            }
            case 1: {
                return "Portrait scene";
            }
            case 2: {
                return "Landscape scene";
            }
            case 4: {
                return "Sports scene";
            }
            case 5: {
                return "Night scene";
            }
            case 6: {
                return "Program AE";
            }
            case 256: {
                return "Aperture priority AE";
            }
            case 512: {
                return "Shutter priority AE";
            }
            case 768: {
                return "Manual exposure";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0151() throws C {
        if (!this.A.N(4144)) {
            return null;
        }
        final int i = this.A.I(4144);
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
    
    public String \u0155() throws C {
        if (!this.A.N(4129)) {
            return null;
        }
        final int i = this.A.I(4129);
        switch (i) {
            case 0: {
                return "Auto focus";
            }
            case 1: {
                return "Manual focus";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u015a() throws C {
        if (!this.A.N(4128)) {
            return null;
        }
        final int i = this.A.I(4128);
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
    
    public String \u014f() throws C {
        if (!this.A.N(4113)) {
            return null;
        }
        return this.A.F(4113).A(false) + " EV (Apex)";
    }
    
    public String \u0150() throws C {
        if (!this.A.N(4112)) {
            return null;
        }
        final int i = this.A.I(4112);
        switch (i) {
            case 0: {
                return "Auto";
            }
            case 1: {
                return "On";
            }
            case 2: {
                return "Off";
            }
            case 3: {
                return "Red-eye reduction";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0154() throws C {
        if (!this.A.N(4100)) {
            return null;
        }
        final int i = this.A.I(4100);
        switch (i) {
            case 0: {
                return "Normal (STD)";
            }
            case 256: {
                return "High (HARD)";
            }
            case 512: {
                return "Low (ORG)";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0158() throws C {
        if (!this.A.N(4099)) {
            return null;
        }
        final int i = this.A.I(4099);
        switch (i) {
            case 0: {
                return "Normal (STD)";
            }
            case 256: {
                return "High";
            }
            case 512: {
                return "Low (ORG)";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u0157() throws C {
        if (!this.A.N(4098)) {
            return null;
        }
        final int i = this.A.I(4098);
        switch (i) {
            case 0: {
                return "Auto";
            }
            case 256: {
                return "Daylight";
            }
            case 512: {
                return "Cloudy";
            }
            case 768: {
                return "DaylightColor-fluorescence";
            }
            case 769: {
                return "DaywhiteColor-fluorescence";
            }
            case 770: {
                return "White-fluorescence";
            }
            case 1024: {
                return "Incandenscense";
            }
            case 3840: {
                return "Custom white balance";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
    
    public String \u015c() throws C {
        if (!this.A.N(4097)) {
            return null;
        }
        final int i = this.A.I(4097);
        switch (i) {
            case 1: {
                return "Softest";
            }
            case 2: {
                return "Soft";
            }
            case 3: {
                return "Normal";
            }
            case 4: {
                return "Hard";
            }
            case 5: {
                return "Hardest";
            }
            default: {
                return "Unknown (" + i + ")";
            }
        }
    }
}
