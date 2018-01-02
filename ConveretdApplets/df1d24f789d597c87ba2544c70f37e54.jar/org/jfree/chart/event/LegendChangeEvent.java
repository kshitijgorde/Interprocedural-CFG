// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.Legend;

public class LegendChangeEvent extends ChartChangeEvent
{
    private Legend legend;
    
    public LegendChangeEvent(final Legend legend) {
        super(legend);
        this.legend = legend;
    }
    
    public Legend getLegend() {
        return this.legend;
    }
}
