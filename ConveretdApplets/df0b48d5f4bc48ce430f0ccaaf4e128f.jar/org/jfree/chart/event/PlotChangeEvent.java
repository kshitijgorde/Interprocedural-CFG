// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.plot.Plot;

public class PlotChangeEvent extends ChartChangeEvent
{
    private Plot plot;
    
    public PlotChangeEvent(final Plot plot) {
        super(plot);
        this.plot = plot;
    }
    
    public Plot getPlot() {
        return this.plot;
    }
}
