// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.ui.RectangleEdge;
import java.awt.Paint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import java.awt.GradientPaint;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.IntervalXYDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformer;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYBarRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private double margin;
    private GradientPaintTransformer gradientPaintTransformer;
    
    public XYBarRenderer() {
        this(0.0);
    }
    
    public XYBarRenderer(final double margin) {
        this.margin = margin;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
    }
    
    public double getMargin() {
        return this.margin;
    }
    
    public void setMargin(final double margin) {
        this.margin = margin;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;
    }
    
    public void setGradientPaintTransformer(final GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset dataset, final PlotRenderingInfo info) {
        final XYBarRendererState state = new XYBarRendererState(info);
        final ValueAxis rangeAxis = plot.getRangeAxisForDataset(plot.indexOf(dataset));
        state.setG2Zero(rangeAxis.valueToJava2D(0.0, dataArea, plot.getRangeAxisEdge()));
        return state;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
        final Paint seriesOutlinePaint = this.getItemOutlinePaint(series, item);
        final Number valueNumber = intervalDataset.getYValue(series, item);
        if (valueNumber == null) {
            return;
        }
        final double translatedValue = rangeAxis.valueToJava2D(valueNumber.doubleValue(), dataArea, plot.getRangeAxisEdge());
        final RectangleEdge location = plot.getDomainAxisEdge();
        final Number startXNumber = intervalDataset.getStartXValue(series, item);
        if (startXNumber == null) {
            return;
        }
        double translatedStartX = domainAxis.valueToJava2D(startXNumber.doubleValue(), dataArea, location);
        final Number endXNumber = intervalDataset.getEndXValue(series, item);
        if (endXNumber == null) {
            return;
        }
        final double translatedEndX = domainAxis.valueToJava2D(endXNumber.doubleValue(), dataArea, location);
        final XYBarRendererState s = (XYBarRendererState)state;
        final double g2Zero = s.getG2Zero();
        double translatedWidth = Math.max(1.0, Math.abs(translatedEndX - translatedStartX));
        final double translatedHeight = Math.abs(translatedValue - g2Zero);
        if (this.getMargin() > 0.0) {
            final double cut = translatedWidth * this.getMargin();
            translatedWidth -= cut;
            translatedStartX += cut / 2.0;
        }
        Rectangle2D bar = null;
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(Math.min(g2Zero, translatedValue), translatedEndX, translatedHeight, translatedWidth);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            bar = new Rectangle2D.Double(translatedStartX, Math.min(g2Zero, translatedValue), translatedWidth, translatedHeight);
        }
        Paint itemPaint = this.getItemPaint(series, item);
        if (this.getGradientPaintTransformer() != null && itemPaint instanceof GradientPaint) {
            final GradientPaint gp = (GradientPaint)itemPaint;
            itemPaint = this.getGradientPaintTransformer().transform(gp, bar);
        }
        g2.setPaint(itemPaint);
        g2.fill(bar);
        if (Math.abs(translatedEndX - translatedStartX) > 3.0) {
            g2.setStroke(this.getItemStroke(series, item));
            g2.setPaint(seriesOutlinePaint);
            g2.draw(bar);
        }
        if (info != null) {
            final EntityCollection entities = info.getOwner().getEntityCollection();
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
                final XYItemEntity entity = new XYItemEntity(bar, dataset, series, item, tip, url);
                entities.addEntity(entity);
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    class XYBarRendererState extends XYItemRendererState
    {
        private double g2Zero;
        
        public XYBarRendererState(final PlotRenderingInfo info) {
            super(info);
        }
        
        public double getG2Zero() {
            return this.g2Zero;
        }
        
        public void setG2Zero(final double value) {
            this.g2Zero = value;
        }
    }
}
