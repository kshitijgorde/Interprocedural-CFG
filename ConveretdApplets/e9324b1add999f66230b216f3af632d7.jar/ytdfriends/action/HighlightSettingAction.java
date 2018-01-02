// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.util.Iterator;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class HighlightSettingAction extends AbstractAction
{
    private int defaultValue;
    
    public HighlightSettingAction(final int defValue) {
        this.defaultValue = 0;
        this.defaultValue = defValue;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        Iterator iter = registry.getNodeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, this.defaultValue);
        }
        iter = registry.getEdgeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, this.defaultValue);
        }
        iter = registry.getAggregateItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setHighlighted(this.defaultValue != -1);
        }
    }
}
