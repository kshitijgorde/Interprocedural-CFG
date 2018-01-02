// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemLayout;
import org.jfree.chart.StandardLegendItemLayout;
import org.jfree.chart.LegendItemCollection;

public abstract class LegendTitle extends Title
{
    private LegendItemCollection items;
    
    public LegendTitle() {
        this(new StandardLegendItemLayout(0, 0.0));
    }
    
    public LegendTitle(final LegendItemLayout layout) {
    }
    
    public void addLegendItem(final LegendItem item) {
        this.items.add(item);
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
    }
}
