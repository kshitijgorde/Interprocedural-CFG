// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class bn implements ActionListener
{
    private final dG a;
    
    private bn(final dG a, final byte b) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        dG.a(this.a, 0);
        synchronized (this.a) {
            this.a.notifyAll();
        }
    }
    
    bn(final dG dg) {
        this(dg, (byte)0);
    }
}
