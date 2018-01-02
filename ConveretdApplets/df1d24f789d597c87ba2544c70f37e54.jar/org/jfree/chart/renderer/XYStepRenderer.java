// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.entity.EntityCollection;
import java.awt.geom.Line2D;
import org.jfree.ui.RectangleEdge;
import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.data.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYStepRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    public XYStepRenderer() {
    }
    
    public XYStepRenderer(final XYToolTipGenerator toolTipGenerator, final XYURLGenerator urlGenerator) {
        this.setToolTipGenerator(toolTipGenerator);
        this.setURLGenerator(urlGenerator);
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final PlotOrientation orientation = plot.getOrientation();
        final Paint seriesPaint = this.getItemPaint(series, item);
        final Stroke seriesStroke = this.getItemStroke(series, item);
        g2.setPaint(seriesPaint);
        g2.setStroke(seriesStroke);
        final Number x1 = dataset.getXValue(series, item);
        final Number y1 = dataset.getYValue(series, item);
        if (y1 == null) {
            return;
        }
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double transX1 = domainAxis.valueToJava2D(x1.doubleValue(), dataArea, xAxisLocation);
        final double transY1 = rangeAxis.valueToJava2D(y1.doubleValue(), dataArea, yAxisLocation);
        if (item > 0) {
            final Number x2 = dataset.getXValue(series, item - 1);
            final Number y2 = dataset.getYValue(series, item - 1);
            if (y2 != null) {
                final double transX2 = domainAxis.valueToJava2D(x2.doubleValue(), dataArea, xAxisLocation);
                final double transY2 = rangeAxis.valueToJava2D(y2.doubleValue(), dataArea, yAxisLocation);
                final Line2D line = state.workingLine;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    if (transY2 == transY1) {
                        line.setLine(transY2, transX2, transY1, transX1);
                        g2.draw(line);
                    }
                    else {
                        line.setLine(transY2, transX2, transY1, transX2);
                        g2.draw(line);
                        line.setLine(transY1, transX2, transY1, transX1);
                        g2.draw(line);
                    }
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    if (transY2 == transY1) {
                        line.setLine(transX2, transY2, transX1, transY1);
                        g2.draw(line);
                    }
                    else {
                        line.setLine(transX2, transY2, transX1, transY2);
                        g2.draw(line);
                        line.setLine(transX1, transY2, transX1, transY1);
                        g2.draw(line);
                    }
                }
            }
        }
        this.updateCrosshairValues(crosshairState, x1.doubleValue(), y1.doubleValue(), transX1, transY1, orientation);
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null) {
                final Shape shape = (orientation == PlotOrientation.VERTICAL) ? new Rectangle2D.Double(transX1 - 2.0, transY1 - 2.0, 4.0, 4.0) : new Rectangle2D.Double(transY1 - 2.0, transX1 - 2.0, 4.0, 4.0);
                if (shape != null) {
                    String tip = null;
                    final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
                    if (generator != null) {
                        tip = generator.generateToolTip(dataset, series, item);
                    }
                    String url = null;
                    if (this.getURLGenerator() != null) {
                        url = this.getURLGenerator().generateURL(dataset, series, item);
                    }
                    final XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
                    entities.addEntity(entity);
                }
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
