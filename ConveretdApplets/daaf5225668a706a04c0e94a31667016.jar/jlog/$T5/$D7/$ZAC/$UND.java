// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.util.EventObject;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.event.ComponentAdapter;

class $UND extends ComponentAdapter
{
    $QFC $COD;
    Component $OU;
    
    public void $DOD(final ComponentEvent componentEvent) {
        this.$COD.$SSB(new $HJC(this.$COD, 7, this.$OU));
    }
    
    $UND(final $QFC $cod, final Component $ou) {
        this.$COD = $cod;
        this.$OU = $ou;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.$COD.$SSB(new $HJC(this.$COD, 8, this.$OU));
    }
}
