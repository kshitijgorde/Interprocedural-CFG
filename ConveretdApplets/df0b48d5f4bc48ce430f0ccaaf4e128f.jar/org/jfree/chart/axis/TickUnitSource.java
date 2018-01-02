// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

public interface TickUnitSource
{
    TickUnit getLargerTickUnit(final TickUnit p0);
    
    TickUnit getCeilingTickUnit(final TickUnit p0);
    
    TickUnit getCeilingTickUnit(final double p0);
}
