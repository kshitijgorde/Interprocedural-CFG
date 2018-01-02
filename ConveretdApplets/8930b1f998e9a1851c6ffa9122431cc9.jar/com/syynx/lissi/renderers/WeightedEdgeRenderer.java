// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.renderers;

import prefuse.visual.VisualItem;
import prefuse.render.EdgeRenderer;

public class WeightedEdgeRenderer extends EdgeRenderer
{
    protected double getLineWidth(final VisualItem item) {
        int weight = 0;
        int displayWeight = 1;
        final int weightCol = item.getSourceTuple().getColumnIndex("weight");
        if (weightCol != -1) {
            weight = item.getSourceTuple().getInt(weightCol);
        }
        displayWeight = weight / 5;
        if (displayWeight > 10) {
            displayWeight = 10;
        }
        if (displayWeight < 1) {
            displayWeight = 1;
        }
        return item.getSize() * displayWeight;
    }
}
