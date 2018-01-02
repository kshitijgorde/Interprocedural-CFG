// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

final class aX extends WindowAdapter
{
    private final dG a;
    
    private aX(final dG a, final byte b) {
        this.a = a;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        new bn(this.a).actionPerformed(null);
    }
    
    aX(final dG dg) {
        this(dg, (byte)0);
    }
}
