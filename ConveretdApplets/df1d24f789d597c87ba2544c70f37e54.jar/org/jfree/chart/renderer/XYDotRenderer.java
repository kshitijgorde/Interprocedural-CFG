// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.data.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYDotRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final Number xn = dataset.getXValue(series, item);
        final Number yn = dataset.getYValue(series, item);
        if (yn != null) {
            final double x = xn.doubleValue();
            final double y = yn.doubleValue();
            final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
            final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
            final double transX = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
            final double transY = rangeAxis.valueToJava2D(y, dataArea, yAxisLocation);
            g2.setPaint(this.getItemPaint(series, item));
            final PlotOrientation orientation = plot.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                g2.drawRect((int)transY, (int)transX, 1, 1);
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                g2.drawRect((int)transX, (int)transY, 1, 1);
            }
            this.updateCrosshairValues(crosshairState, x, y, transX, transY, orientation);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
