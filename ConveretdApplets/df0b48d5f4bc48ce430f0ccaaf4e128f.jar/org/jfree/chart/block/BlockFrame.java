// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.block;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.ui.RectangleInsets;

public interface BlockFrame
{
    RectangleInsets getInsets();
    
    void draw(final Graphics2D p0, final Rectangle2D p1);
}
