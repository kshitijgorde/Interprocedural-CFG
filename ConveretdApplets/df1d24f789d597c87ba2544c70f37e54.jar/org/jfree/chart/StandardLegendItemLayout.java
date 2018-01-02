// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.util.Iterator;

public class StandardLegendItemLayout implements LegendItemLayout
{
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    private int orientation;
    private double dimension;
    
    public StandardLegendItemLayout(final int orientation, final double dimension) {
        this.orientation = orientation;
        this.dimension = dimension;
    }
    
    public void layoutLegendItems(final LegendItemCollection collection) {
        if (this.orientation == 1) {
            this.doHorizontalLayout(collection);
        }
        else if (this.orientation == 0) {
            this.doVerticalLayout(collection);
        }
    }
    
    private void doHorizontalLayout(final LegendItemCollection collection) {
        final Iterator iterator = collection.iterator();
        boolean first = true;
        double currentRowX = 0.0;
        double currentRowY = 0.0;
        double currentRowHeight = 0.0;
        while (iterator.hasNext()) {
            final DrawableLegendItem item = iterator.next();
            if (first || item.getWidth() < this.dimension - currentRowX) {
                item.setX(currentRowX);
                item.setY(currentRowY);
                currentRowX += item.getWidth();
                currentRowHeight = Math.max(currentRowHeight, item.getHeight());
                first = false;
            }
            else {
                currentRowY += currentRowHeight;
                currentRowHeight = item.getHeight();
                item.setX(0.0);
                currentRowX = item.getWidth();
            }
        }
    }
    
    private void doVerticalLayout(final LegendItemCollection collection) {
        final Iterator iterator = collection.iterator();
        boolean first = true;
        double currentColumnX = 0.0;
        double currentColumnY = 0.0;
        double currentColumnWidth = 0.0;
        while (iterator.hasNext()) {
            final DrawableLegendItem item = iterator.next();
            if (first || item.getHeight() < this.dimension - currentColumnY) {
                item.setX(currentColumnX);
                item.setY(currentColumnY);
                currentColumnY += item.getHeight();
                currentColumnWidth = Math.max(currentColumnWidth, item.getWidth());
                first = false;
            }
            else {
                currentColumnX += currentColumnWidth;
                currentColumnWidth = item.getWidth();
                item.setY(0.0);
                currentColumnY = item.getHeight();
            }
        }
    }
}
