// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.util.List;
import org.jfree.chart.Effect3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;

public class NumberAxis3D extends NumberAxis implements Serializable
{
    public NumberAxis3D() {
        this(null);
    }
    
    public NumberAxis3D(final String label) {
        super(label);
        this.setAxisLineVisible(false);
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        if (!this.isVisible()) {
            final AxisState state = new AxisState(cursor);
            final List ticks = this.refreshTicks(g2, state, plotArea, dataArea, edge);
            state.setTicks(ticks);
            return state;
        }
        final CategoryPlot plot = (CategoryPlot)this.getPlot();
        final Effect3D e3D = (Effect3D)plot.getRenderer();
        double adjustedX = dataArea.getMinX();
        double adjustedY = dataArea.getMinY();
        final double adjustedW = dataArea.getWidth() - e3D.getXOffset();
        final double adjustedH = dataArea.getHeight() - e3D.getYOffset();
        if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
            adjustedY += e3D.getYOffset();
        }
        else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
            adjustedX += e3D.getXOffset();
        }
        final Rectangle2D adjustedDataArea = new Rectangle2D.Double(adjustedX, adjustedY, adjustedW, adjustedH);
        AxisState info = this.drawTickMarksAndLabels(g2, cursor, plotArea, adjustedDataArea, edge);
        info = this.drawLabel(this.getLabel(), g2, plotArea, dataArea, edge, info);
        return info;
    }
}
