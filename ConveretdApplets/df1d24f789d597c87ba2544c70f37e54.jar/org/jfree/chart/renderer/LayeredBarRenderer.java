// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryLabelGenerator;
import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.Shape;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Graphics2D;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.util.ObjectList;

public class LayeredBarRenderer extends BarRenderer
{
    protected ObjectList seriesBarWidthList;
    
    public LayeredBarRenderer() {
        this.seriesBarWidthList = new ObjectList();
    }
    
    protected void calculateBarWidth(final CategoryPlot plot, final Rectangle2D dataArea, final int rendererIndex, final CategoryItemRendererState state) {
        final CategoryAxis domainAxis = this.getDomainAxis(plot, rendererIndex);
        final CategoryDataset dataset = plot.getDataset(rendererIndex);
        if (dataset != null) {
            final int columns = dataset.getColumnCount();
            final int rows = dataset.getRowCount();
            double space = 0.0;
            final PlotOrientation orientation = plot.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                space = dataArea.getHeight();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                space = dataArea.getWidth();
            }
            double categoryMargin = 0.0;
            double currentItemMargin = 0.0;
            if (columns > 1) {
                categoryMargin = domainAxis.getCategoryMargin();
            }
            if (rows > 1) {
                currentItemMargin = this.getItemMargin();
            }
            final double used = space * (1.0 - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
            if (rows * columns > 0) {
                state.setBarWidth(used / dataset.getColumnCount());
            }
            else {
                state.setBarWidth(used);
            }
        }
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset data, final int row, final int column) {
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            this.drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            this.drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
        }
    }
    
    protected void drawHorizontalItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset data, final int row, final int column) {
        final Number dataValue = data.getValue(row, column);
        if (dataValue == null) {
            return;
        }
        double value = dataValue.doubleValue();
        double base = 0.0;
        final double lclip = this.getLowerClip();
        final double uclip = this.getUpperClip();
        if (uclip <= 0.0) {
            if (value >= uclip) {
                return;
            }
            base = uclip;
            if (value <= lclip) {
                value = lclip;
            }
        }
        else if (lclip <= 0.0) {
            if (value >= uclip) {
                value = uclip;
            }
            else if (value <= lclip) {
                value = lclip;
            }
        }
        else {
            if (value <= lclip) {
                return;
            }
            base = lclip;
            if (value >= uclip) {
                value = uclip;
            }
        }
        final RectangleEdge edge = plot.getRangeAxisEdge();
        final double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
        final double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
        final double rectX = Math.min(transX1, transX2);
        final double rectWidth = Math.abs(transX2 - transX1);
        double rectY = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        final int seriesCount = this.getRowCount();
        double shift = 0.0;
        double rectHeight = 0.0;
        if (this.getSeriesBarWidth(row, state) != state.getBarWidth()) {
            rectHeight = this.getSeriesBarWidth(row, state) * state.getBarWidth();
            rectY += (1.0 - this.getSeriesBarWidth(row, state)) * state.getBarWidth() / 2.0;
        }
        else {
            rectHeight = state.getBarWidth();
            if (seriesCount > 1) {
                shift = rectHeight * 0.2 / (seriesCount - 1);
            }
        }
        final Rectangle2D bar = new Rectangle2D.Double(rectX, rectY + (seriesCount - 1 - row) * shift, rectWidth, rectHeight - (seriesCount - 1 - row) * shift * 2.0);
        g2.setPaint(this.getItemPaint(row, column));
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
            this.drawItemLabel(g2, data, row, column, plot, generator, bar, transX1 > transX2);
        }
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null) {
                String tip = null;
                final CategoryToolTipGenerator tipster = this.getToolTipGenerator(row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(data, row, column);
                }
                String url = null;
                if (this.getItemURLGenerator(row, column) != null) {
                    url = this.getItemURLGenerator(row, column).generateURL(data, row, column);
                }
                final CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, data, row, data.getColumnKey(column), column);
                entities.addEntity(entity);
            }
        }
    }
    
    protected void drawVerticalItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset data, final int row, final int column) {
        final Number dataValue = data.getValue(row, column);
        if (dataValue == null) {
            return;
        }
        double rectX = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        final int seriesCount = this.getRowCount();
        double value = dataValue.doubleValue();
        double base = 0.0;
        final double lclip = this.getLowerClip();
        final double uclip = this.getUpperClip();
        if (uclip <= 0.0) {
            if (value >= uclip) {
                return;
            }
            base = uclip;
            if (value <= lclip) {
                value = lclip;
            }
        }
        else if (lclip <= 0.0) {
            if (value >= uclip) {
                value = uclip;
            }
            else if (value <= lclip) {
                value = lclip;
            }
        }
        else {
            if (value <= lclip) {
                return;
            }
            base = this.getLowerClip();
            if (value >= uclip) {
                value = uclip;
            }
        }
        final RectangleEdge edge = plot.getRangeAxisEdge();
        final double transY1 = rangeAxis.valueToJava2D(base, dataArea, edge);
        final double transY2 = rangeAxis.valueToJava2D(value, dataArea, edge);
        final double rectY = Math.min(transY2, transY1);
        double rectWidth = state.getBarWidth();
        final double rectHeight = Math.abs(transY2 - transY1);
        double shift = 0.0;
        rectWidth = 0.0;
        if (this.getSeriesBarWidth(row, state) != state.getBarWidth()) {
            rectWidth = this.getSeriesBarWidth(row, state) * state.getBarWidth();
            rectX += (1.0 - this.getSeriesBarWidth(row, state)) * state.getBarWidth() / 2.0;
        }
        else {
            rectWidth = state.getBarWidth();
            if (seriesCount > 1) {
                shift = rectWidth * 0.2 / (seriesCount - 1);
            }
        }
        final Rectangle2D bar = new Rectangle2D.Double(rectX + (seriesCount - 1 - row) * shift, rectY, rectWidth - (seriesCount - 1 - row) * shift * 2.0, rectHeight);
        g2.setPaint(this.getItemPaint(row, column));
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
        final double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
        final double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
        final CategoryLabelGenerator generator = this.getLabelGenerator(row, column);
        if (generator != null && this.isItemLabelVisible(row, column)) {
            this.drawItemLabel(g2, data, row, column, plot, generator, bar, transX1 > transX2);
        }
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null) {
                String tip = null;
                final CategoryToolTipGenerator tipster = this.getToolTipGenerator(row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(data, row, column);
                }
                String url = null;
                if (this.getItemURLGenerator(row, column) != null) {
                    url = this.getItemURLGenerator(row, column).generateURL(data, row, column);
                }
                final CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, data, row, data.getColumnKey(column), column);
                entities.addEntity(entity);
            }
        }
    }
    
    public double getSeriesBarWidth(final int series, final CategoryItemRendererState state) {
        if (this.seriesBarWidthList.get(series) != null) {
            return ((Number)this.seriesBarWidthList.get(series)).doubleValue();
        }
        return state.getBarWidth();
    }
    
    public void setSeriesBarWidth(final int series, final double width) {
        this.seriesBarWidthList.set(series, new Double(width));
    }
}
