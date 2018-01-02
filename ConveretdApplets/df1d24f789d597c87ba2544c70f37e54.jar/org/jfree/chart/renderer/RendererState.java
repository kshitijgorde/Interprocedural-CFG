// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.plot.PlotRenderingInfo;

public class RendererState
{
    private PlotRenderingInfo info;
    
    public RendererState(final PlotRenderingInfo info) {
        this.info = info;
    }
    
    public PlotRenderingInfo getInfo() {
        return this.info;
    }
}
