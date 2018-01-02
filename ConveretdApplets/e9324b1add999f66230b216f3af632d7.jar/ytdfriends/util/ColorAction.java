// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import ytdfriends.action.FriendsXRayColorFunction;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class ColorAction extends AbstractAction
{
    private FriendsPanel fPAnel;
    private JToggleButton prev;
    
    public ColorAction(final FriendsPanel fPanel) {
        this.fPAnel = fPanel;
    }
    
    public void actionPerformed(final ActionEvent e) {
        final JToggleButton tog = (JToggleButton)e.getSource();
        if (this.prev != null && this.prev != tog) {
            this.prev.setSelected(false);
            this.prev.putClientProperty("on", Boolean.FALSE);
            this.prev = null;
        }
        final Boolean onB = (Boolean)tog.getClientProperty("on");
        final boolean on = (onB == null) ? tog.isSelected() : (!onB);
        if (on) {
            final Integer attrI = (Integer)tog.getClientProperty("attr");
            tog.putClientProperty("on", Boolean.TRUE);
            final int idx = attrI;
            final FriendsXRayColorFunction cf = this.fPAnel.getComparisonColorFunction();
            cf.setCurrentAttribute(idx);
            this.fPAnel.setMode(1);
            this.prev = tog;
        }
        else {
            final JToggleButton inv = (JToggleButton)tog.getClientProperty("inv");
            inv.doClick();
            tog.putClientProperty("on", Boolean.FALSE);
            this.fPAnel.setMode(0);
        }
        this.fPAnel.redraw();
    }
}
