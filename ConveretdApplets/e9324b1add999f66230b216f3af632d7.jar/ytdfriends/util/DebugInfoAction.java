// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import edu.berkeley.guir.prefuse.Display;
import java.awt.event.ActionEvent;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class DebugInfoAction extends AbstractAction
{
    private FriendsPanel vizster;
    
    public DebugInfoAction(final FriendsPanel vizster) {
        this.vizster = vizster;
    }
    
    public void actionPerformed(final ActionEvent e) {
        final Display d = this.vizster.getDisplay();
        d.setDebug(!d.getDebug());
    }
}
