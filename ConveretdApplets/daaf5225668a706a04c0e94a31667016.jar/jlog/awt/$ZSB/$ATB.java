// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZSB;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class $ATB extends WindowAdapter
{
    $BTB $CTB;
    
    public $ATB(final $BTB $ctb) {
        this.$CTB = $ctb;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.$CTB.$DTB(false);
    }
}
