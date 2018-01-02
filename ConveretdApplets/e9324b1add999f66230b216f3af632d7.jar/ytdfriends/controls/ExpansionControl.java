// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.graph.Node;
import javax.swing.SwingUtilities;
import java.awt.Cursor;
import edu.berkeley.guir.prefuse.Display;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class ExpansionControl extends ControlAdapter
{
    private Object focusSetKey;
    protected int ccount;
    protected Entity curFocus;
    private FriendsPanel fPanel;
    
    public ExpansionControl(final FriendsPanel fPanel, final int clicks) {
        this.focusSetKey = "default";
        this.curFocus = null;
        this.ccount = clicks;
        this.fPanel = fPanel;
    }
    
    private boolean isAllowedType(final VisualItem item) {
        return item.getItemClass() == "node";
    }
    
    public void itemEntered(final VisualItem item, final MouseEvent e) {
        if (this.isAllowedType(item)) {
            final Display d = (Display)e.getSource();
            d.setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    public void itemExited(final VisualItem item, final MouseEvent e) {
        if (this.isAllowedType(item)) {
            final Display d = (Display)e.getSource();
            d.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void itemClicked(final VisualItem item, final MouseEvent e) {
        if (this.isAllowedType(item) && this.ccount > 0 && SwingUtilities.isLeftMouseButton(e)) {
            final NodeItem nitem = this.fPanel.registry.getNodeItem((Node)item.getEntity());
            this.fPanel.profileCard.loadNode(nitem);
            this.fPanel.profileCard.setVisible(true);
            if (e.getClickCount() == 2) {
                this.fPanel.profileCard.expand(item.getEntity());
            }
        }
    }
}
