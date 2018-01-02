// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.xy;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.XYItemLabelGenerator;
import java.awt.Paint;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import java.awt.GradientPaint;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class ClusteredXYBarRenderer extends XYBarRenderer implements Cloneable, PublicCloneable, Serializable
{
    private static final long serialVersionUID = 5864462149177133147L;
    private boolean centerBarAtStartValue;
    
    public ClusteredXYBarRenderer() {
        this(0.0, false);
    }
    
    public ClusteredXYBarRenderer(final double margin, final boolean centerBarAtStartValue) {
        super(margin);
        this.centerBarAtStartValue = centerBarAtStartValue;
    }
    
    public Range findDomainBounds(final XYDataset dataset) {
        if (dataset == null) {
            return null;
        }
        if (this.centerBarAtStartValue) {
            return this.findDomainBoundsWithOffset((IntervalXYDataset)dataset);
        }
        return super.findDomainBounds(dataset);
    }
    
    protected Range findDomainBoundsWithOffset(final IntervalXYDataset dataset) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        for (int seriesCount = dataset.getSeriesCount(), series = 0; series < seriesCount; ++series) {
            for (int itemCount = dataset.getItemCount(series), item = 0; item < itemCount; ++item) {
                double lvalue = dataset.getStartXValue(series, item);
                double uvalue = dataset.getEndXValue(series, item);
                final double offset = (uvalue - lvalue) / 2.0;
                lvalue -= offset;
                uvalue -= offset;
                minimum = Math.min(minimum, lvalue);
                maximum = Math.max(maximum, uvalue);
            }
        }
        if (minimum > maximum) {
            return null;
        }
        return new Range(minimum, maximum);
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
        double y0;
        double y2;
        if (this.getUseYInterval()) {
            y0 = intervalDataset.getStartYValue(series, item);
            y2 = intervalDataset.getEndYValue(series, item);
        }
        else {
            y0 = this.getBase();
            y2 = intervalDataset.getYValue(series, item);
        }
        if (Double.isNaN(y0) || Double.isNaN(y2)) {
            return;
        }
        final double yy0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
        final double yy2 = rangeAxis.valueToJava2D(y2, dataArea, plot.getRangeAxisEdge());
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final double x0 = intervalDataset.getStartXValue(series, item);
        final double xx0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
        final double x2 = intervalDataset.getEndXValue(series, item);
        final double xx2 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
        double intervalW = xx2 - xx0;
        double baseX = xx0;
        if (this.centerBarAtStartValue) {
            baseX -= intervalW / 2.0;
        }
        final double m = this.getMargin();
        if (m > 0.0) {
            final double cut = intervalW * this.getMargin();
            intervalW -= cut;
            baseX += cut / 2.0;
        }
        final double intervalH = Math.abs(yy0 - yy2);
        final PlotOrientation orientation = plot.getOrientation();
        final int numSeries = dataset.getSeriesCount();
        final double seriesBarWidth = intervalW / numSeries;
        Rectangle2D bar = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            final double barY0 = baseX + seriesBarWidth * series;
            final double barY2 = barY0 + seriesBarWidth;
            final double rx = Math.min(yy0, yy2);
            final double rw = intervalH;
            final double ry = Math.min(barY0, barY2);
            final double rh = Math.abs(barY2 - barY0);
            bar = new Rectangle2D.Double(rx, ry, rw, rh);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final double barX0 = baseX + seriesBarWidth * series;
            final double barX2 = barX0 + seriesBarWidth;
            final double rx = Math.min(barX0, barX2);
            final double rw = Math.abs(barX2 - barX0);
            final double ry = Math.min(yy0, yy2);
            final double rh = intervalH;
            bar = new Rectangle2D.Double(rx, ry, rw, rh);
        }
        Paint itemPaint = this.getItemPaint(series, item);
        if (this.getGradientPaintTransformer() != null && itemPaint instanceof GradientPaint) {
            final GradientPaint gp = (GradientPaint)itemPaint;
            itemPaint = this.getGradientPaintTransformer().transform(gp, bar);
        }
        g2.setPaint(itemPaint);
        g2.fill(bar);
        if (this.isDrawBarOutline() && Math.abs(seriesBarWidth) > 3.0) {
            g2.setStroke(this.getItemOutlineStroke(series, item));
            g2.setPaint(this.getItemOutlinePaint(series, item));
            g2.draw(bar);
        }
        if (this.isItemLabelVisible(series, item)) {
            final XYItemLabelGenerator generator = this.getItemLabelGenerator(series, item);
            this.drawItemLabel(g2, dataset, series, item, plot, generator, bar, y2 < 0.0);
        }
        if (info != null) {
            final EntityCollection entities = info.getOwner().getEntityCollection();
            if (entities != null) {
                this.addEntity(entities, bar, dataset, series, item, bar.getCenterX(), bar.getCenterY());
            }
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClusteredXYBarRenderer)) {
            return false;
        }
        final ClusteredXYBarRenderer that = (ClusteredXYBarRenderer)obj;
        return this.centerBarAtStartValue == that.centerBarAtStartValue && super.equals(obj);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
