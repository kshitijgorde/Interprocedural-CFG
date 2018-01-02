// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action.linkage;

import edu.berkeley.guir.prefuse.NodeItem;
import java.util.Iterator;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.assignment.Layout;

public class ReleaseFixedAction extends Layout
{
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final FocusSet click = fman.getFocusSet((Object)"clicked");
        final FocusSet mouse = fman.getFocusSet((Object)"moused");
        for (final Node n : click) {
            final NodeItem nitem = registry.getNodeItem(n);
            nitem.setFixed(false);
            final double x = nitem.getStartLocation().getX();
            final double y = nitem.getStartLocation().getY();
            this.setLocation((VisualItem)nitem, (VisualItem)null, x, y);
        }
    }
}
