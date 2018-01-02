// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.controls;

import java.util.Iterator;
import prefuse.util.PrefuseLib;
import prefuse.visual.NodeItem;
import prefuse.visual.EdgeItem;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import prefuse.controls.NeighborHighlightControl;

public class EdgeNeighborHighlightControl extends NeighborHighlightControl
{
    private String activity;
    
    public EdgeNeighborHighlightControl(final String activity) {
        this.activity = null;
        this.activity = activity;
    }
    
    public void itemEntered(final VisualItem item, final MouseEvent e) {
        this.setNeighborHighlight(item, true);
    }
    
    public void itemExited(final VisualItem item, final MouseEvent e) {
        this.setNeighborHighlight(item, false);
    }
    
    protected void setNeighborHighlight(final VisualItem item, final boolean state) {
        if (item instanceof EdgeItem) {
            final NodeItem nitem1 = ((EdgeItem)item).getSourceItem();
            final NodeItem nitem2 = ((EdgeItem)item).getTargetItem();
            item.setHover(false);
            if (!item.isHighlighted()) {
                item.setHover(state);
                nitem2.setHover(state);
                nitem1.setHover(state);
            }
        }
        if (item instanceof NodeItem) {
            item.setHover(false);
            if (!item.isHighlighted()) {
                final Iterator iter = ((NodeItem)item).edges();
                while (iter.hasNext()) {
                    item.setHover(false);
                    item.set(String.valueOf(PrefuseLib.FIELD_PREFIX) + "prefocus", state);
                    final EdgeItem eitem = iter.next();
                    final NodeItem nitem3 = eitem.getAdjacentItem((NodeItem)item);
                    if (eitem.isVisible()) {
                        eitem.setHover(state);
                        nitem3.setHover(state);
                    }
                }
            }
        }
        if (this.activity != null) {
            item.getVisualization().run(this.activity);
        }
    }
}
