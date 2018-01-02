// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.render;

import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.render.DefaultEdgeRenderer;

public class FriendsEdgeRenderer extends DefaultEdgeRenderer
{
    public int getLineWidth(final VisualItem item) {
        if (item.isHighlighted()) {
            final int hval = FriendsLib.getHighlightValue(item);
            if (hval == 0 || hval == 1) {
                return this.m_width * 2;
            }
        }
        return this.m_width;
    }
}
