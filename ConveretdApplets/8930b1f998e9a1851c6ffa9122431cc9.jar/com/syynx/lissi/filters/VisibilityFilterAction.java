// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.filters;

import java.util.Iterator;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.action.GroupAction;

public class VisibilityFilterAction extends GroupAction
{
    int minCount;
    int minWeight;
    int minPubCount;
    
    public VisibilityFilterAction(final String graphGroup) {
        super(graphGroup);
        this.minCount = 0;
        this.minWeight = 0;
        this.minPubCount = 0;
    }
    
    public void setMinWeight(final int w) {
        this.minWeight = w;
    }
    
    public void setMinCount(final int c) {
        this.minCount = c;
    }
    
    public void setMinPubCount(final int c) {
        this.minPubCount = c;
    }
    
    public void run(final double frac) {
        final Iterator items = this.m_vis.items("tree");
        while (items.hasNext()) {
            boolean itemVisible = false;
            final VisualItem item = items.next();
            if (item instanceof NodeItem) {
                int edgeCount = 0;
                final NodeItem node = (NodeItem)item;
                final Iterator nodeEdges = node.edges();
                while (nodeEdges.hasNext()) {
                    nodeEdges.next();
                    ++edgeCount;
                }
                if (edgeCount >= this.minCount) {
                    itemVisible = true;
                }
                final int doccountCol = node.getSourceTuple().getColumnIndex("doccount");
                if (doccountCol != -1 && node.getSourceTuple().getInt(doccountCol) < this.minPubCount) {
                    itemVisible = false;
                }
            }
            if (item instanceof EdgeItem) {
                final EdgeItem edge = (EdgeItem)item;
                final int weightCol = edge.getSourceTuple().getColumnIndex("weight");
                if (weightCol != -1 && edge.getSourceTuple().getInt(weightCol) > this.minWeight) {
                    itemVisible = true;
                }
            }
            if (itemVisible) {
                item.setHighlighted(false);
            }
            else {
                item.setHighlighted(true);
            }
        }
    }
}
