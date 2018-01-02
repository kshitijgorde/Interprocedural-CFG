// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.plot.Marker;

public class MarkerChangeEvent extends ChartChangeEvent
{
    private Marker marker;
    
    public MarkerChangeEvent(final Marker marker) {
        super(marker);
        this.marker = marker;
    }
    
    public Marker getMarker() {
        return this.marker;
    }
}
