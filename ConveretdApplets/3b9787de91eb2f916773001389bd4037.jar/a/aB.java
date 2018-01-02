// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

final class aB implements FocusListener
{
    private final aA q;
    
    aB(final aA q) {
        this.q = q;
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        if (aA.q(this.q).t()) {
            aA.q(this.q);
            this.q.q();
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
}
