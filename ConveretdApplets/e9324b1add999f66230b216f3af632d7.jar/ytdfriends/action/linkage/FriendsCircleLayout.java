// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action.linkage;

import edu.berkeley.guir.prefuse.NodeItem;
import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.assignment.Layout;

public class FriendsCircleLayout extends Layout
{
    public void run(final ItemRegistry registry, final double frac) {
        final FocusSet fset = registry.getFocusManager().getFocusSet((Object)"clicked");
        final int nn = fset.size();
        final Rectangle2D r = super.getLayoutBounds(registry);
        final double height = r.getHeight();
        final double width = r.getWidth();
        final double cx = r.getCenterX();
        final double cy = r.getCenterY();
        final double radius = 0.45 * ((height < width) ? height : width);
        final Iterator nodeIter = fset.iterator();
        int i = 0;
        while (nodeIter.hasNext()) {
            final NodeItem n = registry.getNodeItem((Node)nodeIter.next());
            if (!n.isFixed()) {
                n.setFixed(true);
            }
            final double angle = 6.283185307179586 * i / nn;
            final double x = Math.cos(angle) * radius + cx;
            final double y = Math.sin(angle) * radius + cy;
            this.setLocation((VisualItem)n, (VisualItem)null, x, y);
            ++i;
        }
    }
}
