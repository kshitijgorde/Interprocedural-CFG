// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class ax implements FocusListener
{
    private final bO q;
    
    ax(final bO q) {
        this.q = q;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        if (bO.q(this.q).e()) {
            bO.q(this.q);
            this.q.q();
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
