// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

final class Y extends FocusAdapter
{
    private II a;
    
    Y(final II a) {
        this.a = a;
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.a.getParent().requestFocus();
    }
}
