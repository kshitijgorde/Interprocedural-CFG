// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.plot.dial;

import org.jfree.chart.event.ChartChangeEvent;

public class DialLayerChangeEvent extends ChartChangeEvent
{
    private DialLayer layer;
    
    public DialLayerChangeEvent(final DialLayer layer) {
        super(layer);
        this.layer = layer;
    }
    
    public DialLayer getDialLayer() {
        return this.layer;
    }
}
