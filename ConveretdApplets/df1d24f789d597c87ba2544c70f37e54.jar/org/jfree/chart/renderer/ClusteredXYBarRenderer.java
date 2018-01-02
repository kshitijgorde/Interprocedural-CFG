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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.IntervalXYDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class ClusteredXYBarRenderer extends XYBarRenderer implements Cloneable, PublicCloneable, Serializable
{
    private double margin;
    private double translatedRangeZero;
    private boolean centerBarAtStartValue;
    
    public ClusteredXYBarRenderer() {
        this(0.0, false);
    }
    
    public ClusteredXYBarRenderer(final double margin, final boolean centerBarAtStartValue) {
        super(margin);
        this.margin = margin;
        this.centerBarAtStartValue = centerBarAtStartValue;
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        final XYItemRendererState state = super.initialise(g2, dataArea, plot, data, info);
        final ValueAxis rangeAxis = plot.getRangeAxis();
        this.translatedRangeZero = rangeAxis.valueToJava2D(0.0, dataArea, plot.getRangeAxisEdge());
        return state;
    }
    
    public void setMargin(final double margin) {
        super.setMargin(this.margin = margin);
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final IntervalXYDataset intervalData = (IntervalXYDataset)dataset;
        final Paint seriesPaint = this.getItemPaint(series, item);
        final Paint seriesOutlinePaint = this.getItemOutlinePaint(series, item);
        final Number y = intervalData.getYValue(series, item);
        if (y == null) {
            return;
        }
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double translatedY = rangeAxis.valueToJava2D(y.doubleValue(), dataArea, yAxisLocation);
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final double x1 = intervalData.getStartXValue(series, item).doubleValue();
        double translatedX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        final double x2 = intervalData.getEndXValue(series, item).doubleValue();
        final double translatedX2 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
        double translatedWidth = Math.max(1.0, Math.abs(translatedX2 - translatedX1));
        final double translatedHeight = Math.abs(translatedY - this.translatedRangeZero);
        if (this.centerBarAtStartValue) {
            translatedX1 -= translatedWidth / 2.0;
        }
        if (this.margin > 0.0) {
            final double cut = translatedWidth * this.margin;
            translatedWidth -= cut;
            translatedX1 += cut / 2.0;
        }
        final int numSeries = dataset.getSeriesCount();
        final double seriesBarWidth = translatedWidth / numSeries;
        Rectangle2D bar = null;
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(Math.min(this.translatedRangeZero, translatedY), translatedX1 - seriesBarWidth * (numSeries - series), translatedHeight, seriesBarWidth);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            bar = new Rectangle2D.Double(translatedX1 + seriesBarWidth * series, Math.min(this.translatedRangeZero, translatedY), seriesBarWidth, translatedHeight);
        }
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (Math.abs(translatedX2 - translatedX1) > 3.0) {
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
}
