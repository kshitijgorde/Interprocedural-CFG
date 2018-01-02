// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class bu implements ActionListener
{
    private final dG a;
    
    private bu(final dG a, final byte b) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        dG.a(this.a).setText("");
        dG.b(this.a).setText("");
        if (bj.e()) {
            dG.a(this.a).requestFocus();
        }
    }
    
    bu(final dG dg) {
        this(dg, (byte)0);
    }
}
