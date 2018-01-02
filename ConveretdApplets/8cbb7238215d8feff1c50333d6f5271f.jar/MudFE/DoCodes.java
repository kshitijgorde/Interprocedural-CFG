// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Color;

class DoCodes
{
    static Code_Attribute[] codes;
    
    public static Attribute getAttribute(final int a) {
        return DoCodes.codes[a].getAttribute();
    }
    
    static {
        final Color def_fg = Attribute._defaultfg;
        final Color def_back = Attribute._defaultbg;
        DoCodes.codes = new Code_Attribute[210];
        for (int i = 0; i < 210; ++i) {
            DoCodes.codes[i] = new Code_Attribute(def_fg, def_back);
        }
    }
    
    public static void setDefault(final Color f, final Color b) {
        for (int i = 0; i < DoCodes.codes.length; ++i) {
            if (DoCodes.codes[i].getAttribute().getFg() == Attribute._defaultfg) {
                DoCodes.codes[i].getAttribute().setFg(f);
            }
            if (DoCodes.codes[i].getAttribute().getBg() == Attribute._defaultbg) {
                DoCodes.codes[i].getAttribute().setBg(b);
            }
        }
        Attribute._defaultat.set(f, b);
        Attribute._defaultfg = f;
        Attribute._defaultbg = b;
    }
}
