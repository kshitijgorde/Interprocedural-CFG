// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtils;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryLabelGenerator;
import java.awt.Stroke;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.CategoryDataset;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.GradientPaintTransformType;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class WaterfallBarRenderer extends BarRenderer implements Cloneable, PublicCloneable, Serializable
{
    private transient Paint firstBarPaint;
    private transient Paint lastBarPaint;
    private transient Paint positiveBarPaint;
    private transient Paint negativeBarPaint;
    
    public WaterfallBarRenderer() {
        this(new GradientPaint(0.0f, 0.0f, new Color(34, 34, 255), 0.0f, 0.0f, new Color(102, 102, 255)), new GradientPaint(0.0f, 0.0f, new Color(34, 255, 34), 0.0f, 0.0f, new Color(102, 255, 102)), new GradientPaint(0.0f, 0.0f, new Color(255, 34, 34), 0.0f, 0.0f, new Color(255, 102, 102)), new GradientPaint(0.0f, 0.0f, new Color(255, 255, 34), 0.0f, 0.0f, new Color(255, 255, 102)));
    }
    
    public WaterfallBarRenderer(final Paint firstBarPaint, final Paint positiveBarPaint, final Paint negativeBarPaint, final Paint lastBarPaint) {
        this.firstBarPaint = firstBarPaint;
        this.lastBarPaint = lastBarPaint;
        this.positiveBarPaint = positiveBarPaint;
        this.negativeBarPaint = negativeBarPaint;
        this.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
        this.setMinimumBarLength(1.0);
    }
    
    public Range getRangeExtent(final CategoryDataset dataset) {
        return DatasetUtilities.getCumulativeRangeExtent(dataset);
    }
    
    public Paint getFirstBarPaint() {
        return this.firstBarPaint;
    }
    
    public void setFirstBarPaint(final Paint paint) {
        this.firstBarPaint = paint;
    }
    
    public Paint getLastBarPaint() {
        return this.lastBarPaint;
    }
    
    public void setLastBarPaint(final Paint paint) {
        this.lastBarPaint = paint;
    }
    
    public Paint getPositiveBarPaint() {
        return this.positiveBarPaint;
    }
    
    public void setPositiveBarPaint(final Paint paint) {
        this.positiveBarPaint = paint;
    }
    
    public Paint getNegativeBarPaint() {
        return this.negativeBarPaint;
    }
    
    public void setNegativeBarPaint(final Paint paint) {
        this.negativeBarPaint = paint;
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset dataset, final int row, final int column) {
        double previous = state.getSeriesRunningTotal();
        if (column == dataset.getColumnCount() - 1) {
            previous = 0.0;
        }
        double current = 0.0;
        final Number n = dataset.getValue(row, column);
        if (n != null) {
            current = previous + n.doubleValue();
        }
        state.setSeriesRunningTotal(current);
        final int seriesCount = this.getRowCount();
        final int categoryCount = this.getColumnCount();
        final PlotOrientation orientation = plot.getOrientation();
        double rectX = 0.0;
        double rectY = 0.0;
        final RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
        final RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
        double j2dy0 = rangeAxis.valueToJava2D(previous, dataArea, rangeAxisLocation);
        double j2dy2 = rangeAxis.valueToJava2D(current, dataArea, rangeAxisLocation);
        final double valDiff = current - previous;
        if (j2dy2 < j2dy0) {
            final double temp = j2dy2;
            j2dy2 = j2dy0;
            j2dy0 = temp;
        }
        double rectWidth = state.getBarWidth();
        double rectHeight = Math.max(this.getMinimumBarLength(), Math.abs(j2dy2 - j2dy0));
        if (orientation == PlotOrientation.HORIZONTAL) {
            rectY = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, domainAxisLocation);
            if (seriesCount > 1) {
                final double seriesGap = dataArea.getHeight() * this.getItemMargin() / (categoryCount * (seriesCount - 1));
                rectY += row * (state.getBarWidth() + seriesGap);
            }
            else {
                rectY += row * state.getBarWidth();
            }
            rectX = j2dy0;
            rectHeight = state.getBarWidth();
            rectWidth = Math.max(this.getMinimumBarLength(), Math.abs(j2dy2 - j2dy0));
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            rectX = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, domainAxisLocation);
            if (seriesCount > 1) {
                final double seriesGap = dataArea.getWidth() * this.getItemMargin() / (categoryCount * (seriesCount - 1));
                rectX += row * (state.getBarWidth() + seriesGap);
            }
            else {
                rectX += row * state.getBarWidth();
            }
            rectY = j2dy0;
        }
        final Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
        Paint seriesPaint = this.getFirstBarPaint();
        if (column == 0) {
            seriesPaint = this.getFirstBarPaint();
        }
        else if (column == categoryCount - 1) {
            seriesPaint = this.getLastBarPaint();
        }
        else if (valDiff < 0.0) {
            seriesPaint = this.getNegativeBarPaint();
        }
        else if (valDiff > 0.0) {
            seriesPaint = this.getPositiveBarPaint();
        }
        else {
            seriesPaint = this.getLastBarPaint();
        }
        if (this.getGradientPaintTransformer() != null && seriesPaint instanceof GradientPaint) {
            final GradientPaint gp = (GradientPaint)seriesPaint;
            seriesPaint = this.getGradientPaintTransformer().transform(gp, bar);
        }
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (this.isDrawBarOutline() && state.getBarWidth() > 3.0) {
            final Stroke stroke = this.getItemOutlineStroke(row, column);
            final Paint paint = this.getItemOutlinePaint(row, column);
            if (stroke != null && paint != null) {
                g2.setStroke(stroke);
                g2.setPaint(paint);
                g2.draw(bar);
            }
        }
        final CategoryLabelGenerator generator = this.getLabelGenerator(row, column);
        if (generator != null && this.isItemLabelVisible(row, column)) {
            this.drawItemLabel(g2, dataset, row, column, plot, generator, bar, valDiff < 0.0);
        }
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null) {
                String tip = null;
                final CategoryToolTipGenerator tipster = this.getToolTipGenerator(row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(dataset, row, column);
                }
                String url = null;
                if (this.getItemURLGenerator(row, column) != null) {
                    url = this.getItemURLGenerator(row, column).generateURL(dataset, row, column);
                }
                final CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, dataset, row, dataset.getColumnKey(column), column);
                entities.addEntity(entity);
            }
        }
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (super.equals(object) && object instanceof WaterfallBarRenderer) {
            final WaterfallBarRenderer r = (WaterfallBarRenderer)object;
            final boolean b0 = PaintUtils.equal(this.firstBarPaint, r.firstBarPaint);
            final boolean b2 = PaintUtils.equal(this.lastBarPaint, r.lastBarPaint);
            final boolean b3 = PaintUtils.equal(this.positiveBarPaint, r.positiveBarPaint);
            final boolean b4 = PaintUtils.equal(this.negativeBarPaint, r.negativeBarPaint);
            return b0 && b2 && b3 && b4;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.firstBarPaint, stream);
        SerialUtilities.writePaint(this.lastBarPaint, stream);
        SerialUtilities.writePaint(this.positiveBarPaint, stream);
        SerialUtilities.writePaint(this.negativeBarPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.firstBarPaint = SerialUtilities.readPaint(stream);
        this.lastBarPaint = SerialUtilities.readPaint(stream);
        this.positiveBarPaint = SerialUtilities.readPaint(stream);
        this.negativeBarPaint = SerialUtilities.readPaint(stream);
    }
}
