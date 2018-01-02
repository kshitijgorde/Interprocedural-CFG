// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class bc implements FocusListener
{
    private final cW q;
    
    bc(final cW q) {
        this.q = q;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        if (cW.q(this.q).e()) {
            cW.q(this.q);
            this.q.q();
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
