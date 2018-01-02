// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import prefuse.data.Edge;

public interface EdgeItem extends VisualItem, Edge
{
    NodeItem getSourceItem();
    
    NodeItem getTargetItem();
    
    NodeItem getAdjacentItem(final NodeItem p0);
}
