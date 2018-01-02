// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.chart.Effect3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;

public class CategoryAxis3D extends CategoryAxis implements Cloneable, Serializable
{
    public CategoryAxis3D() {
        this(null);
    }
    
    public CategoryAxis3D(final String label) {
        super(label);
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        if (!this.isVisible()) {
            return new AxisState(cursor);
        }
        final CategoryPlot plot = (CategoryPlot)this.getPlot();
        final Rectangle2D adjustedDataArea = new Rectangle2D.Double();
        if (plot.getRenderer() instanceof Effect3D) {
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
            adjustedDataArea.setRect(adjustedX, adjustedY, adjustedW, adjustedH);
        }
        else {
            adjustedDataArea.setRect(dataArea);
        }
        AxisState state = new AxisState(cursor);
        state = this.drawCategoryLabels(g2, plotArea, adjustedDataArea, edge, state, plotState);
        state = this.drawLabel(this.getLabel(), g2, plotArea, dataArea, edge, state);
        return state;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
