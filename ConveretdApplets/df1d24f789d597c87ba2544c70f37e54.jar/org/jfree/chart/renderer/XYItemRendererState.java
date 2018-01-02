// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Line2D;

public class XYItemRendererState extends RendererState
{
    public Line2D workingLine;
    
    public XYItemRendererState(final PlotRenderingInfo info) {
        super(info);
        this.workingLine = new Line2D.Double();
    }
}
