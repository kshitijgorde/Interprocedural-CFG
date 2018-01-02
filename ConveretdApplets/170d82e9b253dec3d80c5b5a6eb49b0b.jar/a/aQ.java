// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class aQ implements FocusListener
{
    private final aP q;
    
    aQ(final aP q) {
        this.q = q;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        if (aP.q(this.q).t()) {
            aP.q(this.q);
            this.q.q();
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
