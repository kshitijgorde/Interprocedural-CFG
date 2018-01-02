// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class aw implements FocusListener
{
    private final bP q;
    
    aw(final bP q) {
        this.q = q;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        if (bP.q(this.q).e()) {
            bP.q(this.q);
            this.q.q();
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
