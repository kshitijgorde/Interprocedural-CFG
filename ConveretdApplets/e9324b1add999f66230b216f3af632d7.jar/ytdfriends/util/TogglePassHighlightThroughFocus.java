// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class TogglePassHighlightThroughFocus extends AbstractAction
{
    private FriendsPanel vizster;
    
    public TogglePassHighlightThroughFocus(final FriendsPanel vizster) {
        this.vizster = vizster;
    }
    
    public void actionPerformed(final ActionEvent ae) {
        final AbstractButton b = (AbstractButton)ae.getSource();
        this.vizster.setPassHighlightTroughFocus(b.isSelected());
    }
}
