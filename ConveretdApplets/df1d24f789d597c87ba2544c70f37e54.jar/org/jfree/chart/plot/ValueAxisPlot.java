// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.data.Range;
import org.jfree.chart.axis.ValueAxis;

public interface ValueAxisPlot
{
    Range getDataRange(final ValueAxis p0);
    
    void zoomHorizontalAxes(final double p0);
    
    void zoomHorizontalAxes(final double p0, final double p1);
    
    void zoomVerticalAxes(final double p0);
    
    void zoomVerticalAxes(final double p0, final double p1);
}
