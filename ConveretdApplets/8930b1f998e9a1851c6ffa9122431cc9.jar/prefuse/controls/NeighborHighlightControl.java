// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.util.Iterator;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;

public class NeighborHighlightControl extends ControlAdapter
{
    private String activity;
    private boolean highlightWithInvisibleEdge;
    
    public NeighborHighlightControl() {
        this(null);
    }
    
    public NeighborHighlightControl(final String activity) {
        this.activity = null;
        this.highlightWithInvisibleEdge = false;
        this.activity = activity;
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (visualItem instanceof NodeItem) {
            this.setNeighborHighlight((NodeItem)visualItem, true);
        }
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (visualItem instanceof NodeItem) {
            this.setNeighborHighlight((NodeItem)visualItem, false);
        }
    }
    
    protected void setNeighborHighlight(final NodeItem nodeItem, final boolean b) {
        final Iterator edges = nodeItem.edges();
        while (edges.hasNext()) {
            final EdgeItem edgeItem = edges.next();
            final NodeItem adjacentItem = edgeItem.getAdjacentItem(nodeItem);
            if (edgeItem.isVisible() || this.highlightWithInvisibleEdge) {
                edgeItem.setHighlighted(b);
                adjacentItem.setHighlighted(b);
            }
        }
        if (this.activity != null) {
            nodeItem.getVisualization().run(this.activity);
        }
    }
    
    public boolean isHighlightWithInvisibleEdge() {
        return this.highlightWithInvisibleEdge;
    }
    
    public void setHighlightWithInvisibleEdge(final boolean highlightWithInvisibleEdge) {
        this.highlightWithInvisibleEdge = highlightWithInvisibleEdge;
    }
}
