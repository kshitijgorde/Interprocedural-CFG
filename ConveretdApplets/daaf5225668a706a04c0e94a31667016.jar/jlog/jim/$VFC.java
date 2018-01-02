// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$BI.$M4;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class $VFC extends WindowAdapter
{
    $YAC $ZAC;
    
    $VFC(final $YAC $zac) {
        this.$ZAC = $zac;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        try {
            if (this.$ZAC.$CTB.$WTB().equals("CARD_APPLICATION") || this.$ZAC.$CBC == null) {
                this.$ZAC.$FGC();
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
}
