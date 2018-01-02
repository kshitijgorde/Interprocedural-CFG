// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryLabelGenerator;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.Paint;
import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.Shape;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Graphics2D;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.CategoryDataset;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StackedBarRenderer3D extends BarRenderer3D implements Cloneable, PublicCloneable, Serializable
{
    public StackedBarRenderer3D() {
    }
    
    public StackedBarRenderer3D(final double xOffset, final double yOffset) {
        super(xOffset, yOffset);
    }
    
    public Range getRangeExtent(final CategoryDataset dataset) {
        return DatasetUtilities.getStackedRangeExtent(dataset);
    }
    
    protected void calculateBarWidth(final CategoryPlot plot, final Rectangle2D dataArea, final int rendererIndex, final CategoryItemRendererState state) {
        final CategoryAxis domainAxis = this.getDomainAxis(plot, rendererIndex);
        final CategoryDataset data = plot.getDataset(rendererIndex);
        if (data != null) {
            final PlotOrientation orientation = plot.getOrientation();
            double space = 0.0;
            if (orientation == PlotOrientation.HORIZONTAL) {
                space = dataArea.getHeight();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                space = dataArea.getWidth();
            }
            final double maxWidth = space * this.getMaxBarWidth();
            final int columns = data.getColumnCount();
            double categoryMargin = 0.0;
            if (columns > 1) {
                categoryMargin = domainAxis.getCategoryMargin();
            }
            final double used = space * (1.0 - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin);
            if (columns > 0) {
                state.setBarWidth(Math.min(used / columns, maxWidth));
            }
            else {
                state.setBarWidth(Math.min(used, maxWidth));
            }
        }
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset dataset, final int row, final int column) {
        final Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return;
        }
        final double value = dataValue.doubleValue();
        final Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + this.getYOffset(), dataArea.getWidth() - this.getXOffset(), dataArea.getHeight() - this.getYOffset());
        final PlotOrientation orientation = plot.getOrientation();
        final double barW0 = domainAxis.getCategoryMiddle(column, this.getColumnCount(), adjusted, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0;
        double positiveBase = 0.0;
        double negativeBase = 0.0;
        for (int i = 0; i < row; ++i) {
            final Number v = dataset.getValue(i, column);
            if (v != null) {
                final double d = v.doubleValue();
                if (d > 0.0) {
                    positiveBase += d;
                }
                else {
                    negativeBase += d;
                }
            }
        }
        final RectangleEdge location = plot.getRangeAxisEdge();
        double translatedBase;
        double translatedValue;
        if (value > 0.0) {
            translatedBase = rangeAxis.valueToJava2D(positiveBase, adjusted, location);
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value, adjusted, location);
        }
        else {
            translatedBase = rangeAxis.valueToJava2D(negativeBase, adjusted, location);
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value, adjusted, location);
        }
        final double barL0 = Math.min(translatedBase, translatedValue);
        final double barLength = Math.max(Math.abs(translatedValue - translatedBase), this.getMinimumBarLength());
        Rectangle2D bar = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
        }
        else {
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
        }
        final Paint itemPaint = this.getItemPaint(row, column);
        g2.setPaint(itemPaint);
        g2.fill(bar);
        final double x0 = bar.getMinX();
        final double x2 = x0 + this.getXOffset();
        final double x3 = bar.getMaxX();
        final double x4 = x3 + this.getXOffset();
        final double y0 = bar.getMinY() - this.getYOffset();
        final double y2 = bar.getMinY();
        final double y3 = bar.getMaxY() - this.getYOffset();
        final double y4 = bar.getMaxY();
        GeneralPath bar3dRight = null;
        GeneralPath bar3dTop = null;
        if (value > 0.0 || orientation == PlotOrientation.VERTICAL) {
            bar3dRight = new GeneralPath();
            bar3dRight.moveTo((float)x3, (float)y4);
            bar3dRight.lineTo((float)x3, (float)y2);
            bar3dRight.lineTo((float)x4, (float)y0);
            bar3dRight.lineTo((float)x4, (float)y3);
            bar3dRight.closePath();
            if (itemPaint instanceof Color) {
                g2.setPaint(((Color)itemPaint).darker());
            }
            g2.fill(bar3dRight);
        }
        if (value > 0.0 || orientation == PlotOrientation.HORIZONTAL) {
            bar3dTop = new GeneralPath();
            bar3dTop.moveTo((float)x0, (float)y2);
            bar3dTop.lineTo((float)x2, (float)y0);
            bar3dTop.lineTo((float)x4, (float)y0);
            bar3dTop.lineTo((float)x3, (float)y2);
            bar3dTop.closePath();
            g2.fill(bar3dTop);
        }
        if (state.getBarWidth() > 3.0) {
            g2.setStroke(this.getItemOutlineStroke(row, column));
            g2.setPaint(this.getItemOutlinePaint(row, column));
            g2.draw(bar);
            if (bar3dRight != null) {
                g2.draw(bar3dRight);
            }
            if (bar3dTop != null) {
                g2.draw(bar3dTop);
            }
        }
        final CategoryLabelGenerator generator = this.getLabelGenerator(row, column);
        if (generator != null && this.isItemLabelVisible(row, column)) {
            this.drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0);
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
}
