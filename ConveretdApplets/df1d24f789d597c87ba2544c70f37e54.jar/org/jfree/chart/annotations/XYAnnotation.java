// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.chart.axis.ValueAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;

public interface XYAnnotation
{
    void draw(final Graphics2D p0, final XYPlot p1, final Rectangle2D p2, final ValueAxis p3, final ValueAxis p4);
}
