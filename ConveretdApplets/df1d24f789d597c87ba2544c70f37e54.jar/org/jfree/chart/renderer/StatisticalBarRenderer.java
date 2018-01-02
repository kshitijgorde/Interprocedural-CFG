// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Paint;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Line2D;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.StatisticalCategoryDataset;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StatisticalBarRenderer extends BarRenderer implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable
{
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset data, final int row, final int column) {
        if (!(data instanceof StatisticalCategoryDataset)) {
            throw new IllegalArgumentException("StatisticalBarRenderer.drawCategoryItem() : the data should be of type StatisticalCategoryDataset only.");
        }
        final StatisticalCategoryDataset statData = (StatisticalCategoryDataset)data;
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            this.drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, row, column);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            this.drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, row, column);
        }
    }
    
    protected void drawHorizontalItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final StatisticalCategoryDataset dataset, final int row, final int column) {
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        double rectY = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, xAxisLocation);
        final int seriesCount = this.getRowCount();
        final int categoryCount = this.getColumnCount();
        if (seriesCount > 1) {
            final double seriesGap = dataArea.getHeight() * this.getItemMargin() / (categoryCount * (seriesCount - 1));
            rectY += row * (state.getBarWidth() + seriesGap);
        }
        else {
            rectY += row * state.getBarWidth();
        }
        final Number meanValue = dataset.getMeanValue(row, column);
        double value = meanValue.doubleValue();
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
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
        final double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
        final double rectX = Math.min(transY2, transY1);
        final double rectHeight = state.getBarWidth();
        final double rectWidth = Math.abs(transY2 - transY1);
        final Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
        final Paint seriesPaint = this.getItemPaint(row, column);
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (state.getBarWidth() > 3.0) {
            g2.setStroke(this.getItemStroke(row, column));
            g2.setPaint(this.getItemOutlinePaint(row, column));
            g2.draw(bar);
        }
        final double valueDelta = dataset.getStdDevValue(row, column).doubleValue();
        final double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
        final double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
        Line2D line = null;
        line = new Line2D.Double(lowVal, rectY + rectHeight / 2.0, highVal, rectY + rectHeight / 2.0);
        g2.draw(line);
        line = new Line2D.Double(highVal, rectY + rectHeight * 0.25, highVal, rectY + rectHeight * 0.75);
        g2.draw(line);
        line = new Line2D.Double(lowVal, rectY + rectHeight * 0.25, lowVal, rectY + rectHeight * 0.75);
        g2.draw(line);
    }
    
    protected void drawVerticalItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final StatisticalCategoryDataset dataset, final int row, final int column) {
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        double rectX = domainAxis.getCategoryStart(column, this.getColumnCount(), dataArea, xAxisLocation);
        final int seriesCount = this.getRowCount();
        final int categoryCount = this.getColumnCount();
        if (seriesCount > 1) {
            final double seriesGap = dataArea.getWidth() * this.getItemMargin() / (categoryCount * (seriesCount - 1));
            rectX += row * (state.getBarWidth() + seriesGap);
        }
        else {
            rectX += row * state.getBarWidth();
        }
        final Number meanValue = dataset.getMeanValue(row, column);
        double value = meanValue.doubleValue();
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
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
        final double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
        final double rectY = Math.min(transY2, transY1);
        final double rectWidth = state.getBarWidth();
        final double rectHeight = Math.abs(transY2 - transY1);
        final Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
        final Paint seriesPaint = this.getItemPaint(row, column);
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (state.getBarWidth() > 3.0) {
            g2.setStroke(this.getItemStroke(row, column));
            g2.setPaint(this.getItemOutlinePaint(row, column));
            g2.draw(bar);
        }
        final double valueDelta = dataset.getStdDevValue(row, column).doubleValue();
        final double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
        final double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
        Line2D line = null;
        line = new Line2D.Double(rectX + rectWidth / 2.0, lowVal, rectX + rectWidth / 2.0, highVal);
        g2.draw(line);
        line = new Line2D.Double(rectX + rectWidth / 2.0 - 5.0, highVal, rectX + rectWidth / 2.0 + 5.0, highVal);
        g2.draw(line);
        line = new Line2D.Double(rectX + rectWidth / 2.0 - 5.0, lowVal, rectX + rectWidth / 2.0 + 5.0, lowVal);
        g2.draw(line);
    }
}
