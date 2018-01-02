// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.XYToolTipGenerator;
import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.HighLowDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.data.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class HighLowRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private boolean drawOpenTicks;
    private boolean drawCloseTicks;
    
    public HighLowRenderer() {
        this.drawOpenTicks = true;
        this.drawCloseTicks = true;
    }
    
    public boolean getDrawOpenTicks() {
        return this.drawOpenTicks;
    }
    
    public void setDrawOpenTicks(final boolean draw) {
        this.drawOpenTicks = draw;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getDrawCloseTicks() {
        return this.drawCloseTicks;
    }
    
    public void setDrawCloseTicks(final boolean draw) {
        this.drawCloseTicks = draw;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final Number x = dataset.getXValue(series, item);
        if (x == null) {
            return;
        }
        final double xdouble = x.doubleValue();
        if (!domainAxis.getRange().contains(xdouble)) {
            return;
        }
        final double xx = domainAxis.valueToJava2D(xdouble, dataArea, plot.getDomainAxisEdge());
        Shape entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge location = plot.getRangeAxisEdge();
        final Paint p = this.getItemPaint(series, item);
        final Stroke s = this.getItemStroke(series, item);
        g2.setPaint(p);
        g2.setStroke(s);
        if (dataset instanceof HighLowDataset) {
            final HighLowDataset hld = (HighLowDataset)dataset;
            final Number yHigh = hld.getHighValue(series, item);
            final Number yLow = hld.getLowValue(series, item);
            if (yHigh != null && yLow != null) {
                final double yyHigh = rangeAxis.valueToJava2D(yHigh.doubleValue(), dataArea, location);
                final double yyLow = rangeAxis.valueToJava2D(yLow.doubleValue(), dataArea, location);
                if (orientation == PlotOrientation.HORIZONTAL) {
                    g2.draw(new Line2D.Double(yyLow, xx, yyHigh, xx));
                    entityArea = new Rectangle2D.Double(Math.min(yyLow, yyHigh), xx - 1.0, Math.abs(yyHigh - yyLow), 2.0);
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    g2.draw(new Line2D.Double(xx, yyLow, xx, yyHigh));
                    entityArea = new Rectangle2D.Double(xx - 1.0, Math.min(yyLow, yyHigh), 2.0, Math.abs(yyHigh - yyLow));
                }
            }
            double delta = 2.0;
            if (domainAxis.isInverted()) {
                delta = -delta;
            }
            if (this.getDrawOpenTicks()) {
                final Number yOpen = hld.getOpenValue(series, item);
                if (yOpen != null) {
                    final double yyOpen = rangeAxis.valueToJava2D(yOpen.doubleValue(), dataArea, location);
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        g2.draw(new Line2D.Double(yyOpen, xx + delta, yyOpen, xx));
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        g2.draw(new Line2D.Double(xx - delta, yyOpen, xx, yyOpen));
                    }
                }
            }
            if (this.getDrawCloseTicks()) {
                final Number yClose = hld.getCloseValue(series, item);
                if (yClose != null) {
                    final double yyClose = rangeAxis.valueToJava2D(yClose.doubleValue(), dataArea, location);
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        g2.draw(new Line2D.Double(yyClose, xx, yyClose, xx - delta));
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        g2.draw(new Line2D.Double(xx, yyClose, xx + delta, yyClose));
                    }
                }
            }
        }
        else if (item > 0) {
            final Number x2 = dataset.getXValue(series, item - 1);
            final Number y0 = dataset.getYValue(series, item - 1);
            final Number y2 = dataset.getYValue(series, item);
            if (x2 == null || y0 == null || y2 == null) {
                return;
            }
            final double xx2 = domainAxis.valueToJava2D(x2.doubleValue(), dataArea, plot.getDomainAxisEdge());
            final double yy0 = rangeAxis.valueToJava2D(y0.doubleValue(), dataArea, location);
            final double yy2 = rangeAxis.valueToJava2D(y2.doubleValue(), dataArea, location);
            if (orientation == PlotOrientation.HORIZONTAL) {
                g2.draw(new Line2D.Double(yy0, xx2, yy2, xx));
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                g2.draw(new Line2D.Double(xx2, yy0, xx, yy2));
            }
        }
        if (entities != null) {
            String tip = null;
            final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
            if (generator != null) {
                tip = generator.generateToolTip(dataset, series, item);
            }
            String url = null;
            if (this.getURLGenerator() != null) {
                url = this.getURLGenerator().generateURL(dataset, series, item);
            }
            final XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
            entities.addEntity(entity);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
