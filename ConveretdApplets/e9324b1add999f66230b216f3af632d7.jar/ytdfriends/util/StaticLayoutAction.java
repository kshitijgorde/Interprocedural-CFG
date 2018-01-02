// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import java.awt.event.ActionEvent;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class StaticLayoutAction extends AbstractAction
{
    private FriendsPanel vizster;
    
    public StaticLayoutAction(final FriendsPanel vizster) {
        this.vizster = vizster;
    }
    
    public void actionPerformed(final ActionEvent arg0) {
        this.vizster.runStaticLayout();
    }
}
