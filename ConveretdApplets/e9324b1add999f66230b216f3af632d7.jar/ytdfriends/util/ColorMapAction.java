// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.util;

import java.awt.event.ActionEvent;
import ytdfriends.FriendsPanel;
import javax.swing.AbstractAction;

public class ColorMapAction extends AbstractAction
{
    private FriendsPanel vizster;
    
    public ColorMapAction(final FriendsPanel vizster) {
        this.vizster = vizster;
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String cmd = e.getActionCommand();
        final int map = -1;
        this.vizster.getComparisonColorFunction().setColorMap(map);
        this.vizster.redraw();
    }
}
