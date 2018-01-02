// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.xy;

import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Line2D;
import org.jfree.chart.renderer.RendererState;

public class XYItemRendererState extends RendererState
{
    public Line2D workingLine;
    private boolean processVisibleItemsOnly;
    
    public XYItemRendererState(final PlotRenderingInfo info) {
        super(info);
        this.workingLine = new Line2D.Double();
        this.processVisibleItemsOnly = true;
    }
    
    public boolean getProcessVisibleItemsOnly() {
        return this.processVisibleItemsOnly;
    }
    
    public void setProcessVisibleItemsOnly(final boolean flag) {
        this.processVisibleItemsOnly = flag;
    }
}
