// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.entity.EntityCollection;
import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import java.awt.Polygon;
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

public class XYAreaRenderer2 extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private boolean plotShapes;
    private boolean plotLines;
    private boolean plotArea;
    private boolean showOutline;
    
    public XYAreaRenderer2() {
        this(null, null);
    }
    
    public XYAreaRenderer2(final XYToolTipGenerator labelGenerator, final XYURLGenerator urlGenerator) {
        this.plotArea = true;
        this.plotLines = false;
        this.plotShapes = false;
        this.showOutline = false;
        this.setToolTipGenerator(labelGenerator);
        this.setURLGenerator(urlGenerator);
    }
    
    public boolean isOutline() {
        return this.showOutline;
    }
    
    public void setOutline(final boolean show) {
        this.showOutline = show;
    }
    
    public boolean getPlotShapes() {
        return this.plotShapes;
    }
    
    public boolean getPlotLines() {
        return this.plotLines;
    }
    
    public boolean getPlotArea() {
        return this.plotArea;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final Number x1n = dataset.getXValue(series, item);
        Number y1n = dataset.getYValue(series, item);
        if (y1n == null) {
            y1n = AbstractRenderer.ZERO;
        }
        final double x1 = x1n.doubleValue();
        final double y1 = y1n.doubleValue();
        final double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
        final double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
        final Number x2 = dataset.getXValue(series, Math.max(item - 1, 0));
        Number y2 = dataset.getYValue(series, Math.max(item - 1, 0));
        if (y2 == null) {
            y2 = AbstractRenderer.ZERO;
        }
        final double transX2 = domainAxis.valueToJava2D(x2.doubleValue(), dataArea, plot.getDomainAxisEdge());
        final double transY2 = rangeAxis.valueToJava2D(y2.doubleValue(), dataArea, plot.getRangeAxisEdge());
        final int itemCount = dataset.getItemCount(series);
        final Number x3 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
        Number y3 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
        if (y3 == null) {
            y3 = AbstractRenderer.ZERO;
        }
        final double transX3 = domainAxis.valueToJava2D(x3.doubleValue(), dataArea, plot.getDomainAxisEdge());
        final double transY3 = rangeAxis.valueToJava2D(y3.doubleValue(), dataArea, plot.getRangeAxisEdge());
        final double transZero = rangeAxis.valueToJava2D(0.0, dataArea, plot.getRangeAxisEdge());
        Polygon hotspot = null;
        if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
            hotspot = new Polygon();
            hotspot.addPoint((int)transZero, (int)((transX2 + transX1) / 2.0));
            hotspot.addPoint((int)((transY2 + transY1) / 2.0), (int)((transX2 + transX1) / 2.0));
            hotspot.addPoint((int)transY1, (int)transX1);
            hotspot.addPoint((int)((transY1 + transY3) / 2.0), (int)((transX1 + transX3) / 2.0));
            hotspot.addPoint((int)transZero, (int)((transX1 + transX3) / 2.0));
        }
        else {
            hotspot = new Polygon();
            hotspot.addPoint((int)((transX2 + transX1) / 2.0), (int)transZero);
            hotspot.addPoint((int)((transX2 + transX1) / 2.0), (int)((transY2 + transY1) / 2.0));
            hotspot.addPoint((int)transX1, (int)transY1);
            hotspot.addPoint((int)((transX1 + transX3) / 2.0), (int)((transY1 + transY3) / 2.0));
            hotspot.addPoint((int)((transX1 + transX3) / 2.0), (int)transZero);
        }
        final PlotOrientation orientation = plot.getOrientation();
        final Paint paint = this.getItemPaint(series, item);
        final Stroke stroke = this.getItemStroke(series, item);
        g2.setPaint(paint);
        g2.setStroke(stroke);
        Shape shape = null;
        if (this.getPlotShapes()) {
            shape = this.getItemShape(series, item);
            if (orientation == PlotOrientation.VERTICAL) {
                shape = this.createTransformedShape(shape, transX1, transY1);
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                shape = this.createTransformedShape(shape, transY1, transX1);
            }
            g2.draw(shape);
        }
        if (this.getPlotLines() && item > 0) {
            if (plot.getOrientation() == PlotOrientation.VERTICAL) {
                state.workingLine.setLine(transX2, transY2, transX1, transY1);
            }
            else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
                state.workingLine.setLine(transY2, transX2, transY1, transX1);
            }
            g2.draw(state.workingLine);
        }
        if (this.getPlotArea()) {
            g2.fill(hotspot);
            if (this.isOutline()) {
                g2.setStroke(plot.getOutlineStroke());
                g2.setPaint(plot.getOutlinePaint());
                g2.draw(hotspot);
            }
        }
        this.updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null && hotspot != null) {
                String tip = null;
                final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
                if (generator != null) {
                    tip = generator.generateToolTip(dataset, series, item);
                }
                String url = null;
                if (this.getURLGenerator() != null) {
                    url = this.getURLGenerator().generateURL(dataset, series, item);
                }
                final XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, tip, url);
                entities.addEntity(entity);
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
