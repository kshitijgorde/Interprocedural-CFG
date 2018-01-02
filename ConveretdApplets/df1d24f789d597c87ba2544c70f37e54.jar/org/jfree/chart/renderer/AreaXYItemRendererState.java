// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Line2D;
import java.awt.Polygon;

class AreaXYItemRendererState extends XYItemRendererState
{
    public Polygon area;
    public Line2D line;
    
    public AreaXYItemRendererState(final PlotRenderingInfo info) {
        super(info);
        this.area = new Polygon();
        this.line = new Line2D.Double();
    }
}
