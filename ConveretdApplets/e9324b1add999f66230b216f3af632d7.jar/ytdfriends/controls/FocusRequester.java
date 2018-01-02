// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FocusRequester extends MouseAdapter
{
    public void mouseEntered(final MouseEvent evt) {
        final Component c = evt.getComponent();
        c.requestFocus();
    }
}
