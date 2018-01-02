// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.Event;
import ch.randelshofer.gui.event.ChangeListener;

public class ToggleButton extends AbstractButton implements ChangeListener
{
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.isEnabled() && this.isArmed()) {
            final boolean selected = !this.isSelected();
            if (selected || super.group == null) {
                this.setSelected(selected);
            }
        }
        super.mouseUp(event, n, n2);
        return true;
    }
}
