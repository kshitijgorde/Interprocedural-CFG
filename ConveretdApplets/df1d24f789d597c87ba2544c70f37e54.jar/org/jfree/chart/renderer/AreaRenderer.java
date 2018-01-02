// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.geom.GeneralPath;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class AreaRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable
{
    private AreaRendererEndType endType;
    
    public AreaRenderer() {
        this.endType = AreaRendererEndType.TAPER;
    }
    
    public AreaRendererEndType getEndType() {
        return this.endType;
    }
    
    public void setEndType(final AreaRendererEndType type) {
        if (type == null) {
            throw new IllegalArgumentException("Null 'type' argument.");
        }
        this.endType = type;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset dataset, final int row, final int column) {
        final Number value = dataset.getValue(row, column);
        if (value != null) {
            final PlotOrientation orientation = plot.getOrientation();
            final RectangleEdge axisEdge = plot.getDomainAxisEdge();
            final int count = dataset.getColumnCount();
            float x0 = (float)domainAxis.getCategoryStart(column, count, dataArea, axisEdge);
            float x2 = (float)domainAxis.getCategoryMiddle(column, count, dataArea, axisEdge);
            float x3 = (float)domainAxis.getCategoryEnd(column, count, dataArea, axisEdge);
            x0 = Math.round(x0);
            x2 = Math.round(x2);
            x3 = Math.round(x3);
            if (this.endType == AreaRendererEndType.TRUNCATE) {
                if (column == 0) {
                    x0 = x2;
                }
                else if (column == this.getColumnCount() - 1) {
                    x3 = x2;
                }
            }
            final double yy1 = value.doubleValue();
            double yy2 = 0.0;
            if (column > 0) {
                final Number n0 = dataset.getValue(row, column - 1);
                if (n0 != null) {
                    yy2 = (n0.doubleValue() + yy1) / 2.0;
                }
            }
            double yy3 = 0.0;
            if (column < dataset.getColumnCount() - 1) {
                final Number n2 = dataset.getValue(row, column + 1);
                if (n2 != null) {
                    yy3 = (n2.doubleValue() + yy1) / 2.0;
                }
            }
            final RectangleEdge edge = plot.getRangeAxisEdge();
            final float y0 = (float)rangeAxis.valueToJava2D(yy2, dataArea, edge);
            final float y2 = (float)rangeAxis.valueToJava2D(yy1, dataArea, edge);
            final float y3 = (float)rangeAxis.valueToJava2D(yy3, dataArea, edge);
            final float yz = (float)rangeAxis.valueToJava2D(0.0, dataArea, edge);
            g2.setPaint(this.getItemPaint(row, column));
            g2.setStroke(this.getItemStroke(row, column));
            final GeneralPath area = new GeneralPath();
            if (orientation == PlotOrientation.VERTICAL) {
                area.moveTo(x0, yz);
                area.lineTo(x0, y0);
                area.lineTo(x2, y2);
                area.lineTo(x3, y3);
                area.lineTo(x3, yz);
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                area.moveTo(yz, x0);
                area.lineTo(y0, x0);
                area.lineTo(y2, x2);
                area.lineTo(y3, x3);
                area.lineTo(yz, x3);
            }
            area.closePath();
            g2.setPaint(this.getItemPaint(row, column));
            g2.fill(area);
            if (this.isItemLabelVisible(row, column)) {
                this.drawItemLabel(g2, orientation, dataset, row, column, x2, y2, value.doubleValue() < 0.0);
            }
            if (state.getInfo() != null) {
                final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
                if (entities != null) {
                    String tip = null;
                    final CategoryToolTipGenerator generator = this.getToolTipGenerator(row, column);
                    if (generator != null) {
                        tip = generator.generateToolTip(dataset, row, column);
                    }
                    String url = null;
                    if (this.getItemURLGenerator(row, column) != null) {
                        url = this.getItemURLGenerator(row, column).generateURL(dataset, row, column);
                    }
                    final Comparable columnKey = dataset.getColumnKey(column);
                    final CategoryItemEntity entity = new CategoryItemEntity(area, tip, url, dataset, row, columnKey, column);
                    entities.addEntity(entity);
                }
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
