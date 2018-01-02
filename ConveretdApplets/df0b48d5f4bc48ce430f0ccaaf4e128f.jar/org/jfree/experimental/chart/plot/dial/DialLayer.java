// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.plot.dial;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.util.EventListener;

public interface DialLayer
{
    boolean isVisible();
    
    void addChangeListener(final DialLayerChangeListener p0);
    
    void removeChangeListener(final DialLayerChangeListener p0);
    
    boolean hasListener(final EventListener p0);
    
    boolean isClippedToWindow();
    
    void draw(final Graphics2D p0, final DialPlot p1, final Rectangle2D p2, final Rectangle2D p3);
}
