// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.util.ObjectUtils;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.Shape;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.util.BooleanList;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class LineAndShapeRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable
{
    public static final int SHAPES = 1;
    public static final int LINES = 2;
    public static final int SHAPES_AND_LINES = 3;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    private boolean drawShapes;
    private boolean drawLines;
    private Boolean shapesFilled;
    private BooleanList seriesShapesFilled;
    private Boolean defaultShapesFilled;
    private boolean useFillPaintForShapeOutline;
    
    public LineAndShapeRenderer() {
        this(3);
    }
    
    public LineAndShapeRenderer(final int type) {
        this.useFillPaintForShapeOutline = false;
        if (type == 1) {
            this.drawShapes = true;
        }
        if (type == 2) {
            this.drawLines = true;
        }
        if (type == 3) {
            this.drawShapes = true;
            this.drawLines = true;
        }
        this.shapesFilled = null;
        this.seriesShapesFilled = new BooleanList();
        this.defaultShapesFilled = Boolean.TRUE;
    }
    
    public boolean getUseFillPaintForShapeOutline() {
        return this.useFillPaintForShapeOutline;
    }
    
    public void setUseFillPaintForShapeOutline(final boolean use) {
        this.useFillPaintForShapeOutline = use;
    }
    
    public boolean isDrawShapes() {
        return this.drawShapes;
    }
    
    public void setDrawShapes(final boolean draw) {
        if (draw != this.drawShapes) {
            this.drawShapes = draw;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean isDrawLines() {
        return this.drawLines;
    }
    
    public void setDrawLines(final boolean draw) {
        if (draw != this.drawLines) {
            this.drawLines = draw;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean getItemShapeFilled(final int series, final int item) {
        return this.getSeriesShapesFilled(series);
    }
    
    public boolean getSeriesShapesFilled(final int series) {
        if (this.shapesFilled != null) {
            return this.shapesFilled;
        }
        final Boolean flag = this.seriesShapesFilled.getBoolean(series);
        if (flag != null) {
            return flag;
        }
        return this.defaultShapesFilled;
    }
    
    public Boolean getShapesFilled() {
        return this.shapesFilled;
    }
    
    public void setShapesFilled(final boolean filled) {
        if (filled) {
            this.setShapesFilled(Boolean.TRUE);
        }
        else {
            this.setShapesFilled(Boolean.FALSE);
        }
    }
    
    public void setShapesFilled(final Boolean filled) {
        this.shapesFilled = filled;
    }
    
    public void setSeriesShapesFilled(final int series, final Boolean filled) {
        this.seriesShapesFilled.setBoolean(series, filled);
    }
    
    public void setSeriesShapesFilled(final int series, final boolean filled) {
        this.seriesShapesFilled.setBoolean(series, new Boolean(filled));
    }
    
    public Boolean getDefaultShapesFilled() {
        return this.defaultShapesFilled;
    }
    
    public void setDefaultShapesFilled(final Boolean flag) {
        this.defaultShapesFilled = flag;
    }
    
    public void setDefaultShapesFilled(final boolean flag) {
        this.setDefaultShapesFilled(new Boolean(flag));
    }
    
    public void drawItem(final Graphics2D g2, final CategoryItemRendererState state, final Rectangle2D dataArea, final CategoryPlot plot, final CategoryAxis domainAxis, final ValueAxis rangeAxis, final CategoryDataset dataset, final int row, final int column) {
        final Number v = dataset.getValue(row, column);
        if (v == null) {
            return;
        }
        final PlotOrientation orientation = plot.getOrientation();
        final double x1 = domainAxis.getCategoryMiddle(column, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
        final double value = v.doubleValue();
        final double y1 = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        Shape shape = this.getItemShape(row, column);
        if (orientation == PlotOrientation.HORIZONTAL) {
            shape = this.createTransformedShape(shape, y1, x1);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            shape = this.createTransformedShape(shape, x1, y1);
        }
        if (this.isDrawShapes()) {
            if (this.getItemShapeFilled(row, column)) {
                g2.setPaint(this.getItemPaint(row, column));
                g2.fill(shape);
            }
            else {
                if (this.useFillPaintForShapeOutline) {
                    g2.setPaint(this.getItemPaint(row, column));
                }
                else {
                    g2.setPaint(this.getItemOutlinePaint(row, column));
                }
                g2.setStroke(this.getItemOutlineStroke(row, column));
                g2.draw(shape);
            }
        }
        if (this.isDrawLines() && column != 0) {
            final Number previousValue = dataset.getValue(row, column - 1);
            if (previousValue != null) {
                final double previous = previousValue.doubleValue();
                final double x2 = domainAxis.getCategoryMiddle(column - 1, this.getColumnCount(), dataArea, plot.getDomainAxisEdge());
                final double y2 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
                Line2D line = null;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    line = new Line2D.Double(y2, x2, y1, x1);
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    line = new Line2D.Double(x2, y2, x1, y1);
                }
                g2.setPaint(this.getItemPaint(row, column));
                g2.setStroke(this.getItemStroke(row, column));
                g2.draw(line);
            }
        }
        if (this.isItemLabelVisible(row, column)) {
            this.drawItemLabel(g2, orientation, dataset, row, column, x1, y1, value < 0.0);
        }
        if (state.getInfo() != null) {
            final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
            if (entities != null && shape != null) {
                String tip = null;
                final CategoryToolTipGenerator tipster = this.getToolTipGenerator(row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(dataset, row, column);
                }
                String url = null;
                if (this.getItemURLGenerator(row, column) != null) {
                    url = this.getItemURLGenerator(row, column).generateURL(dataset, row, column);
                }
                final CategoryItemEntity entity = new CategoryItemEntity(shape, tip, url, dataset, row, dataset.getColumnKey(column), column);
                entities.addEntity(entity);
            }
        }
    }
    
    public boolean equals(final Object obj) {
        boolean result = super.equals(obj);
        if (obj instanceof LineAndShapeRenderer) {
            final LineAndShapeRenderer r = (LineAndShapeRenderer)obj;
            final boolean b0 = r.drawLines == this.drawLines;
            final boolean b2 = r.drawShapes == this.drawShapes;
            final boolean b3 = ObjectUtils.equal(r.shapesFilled, this.shapesFilled);
            final boolean b4 = ObjectUtils.equal(r.seriesShapesFilled, this.seriesShapesFilled);
            final boolean b5 = ObjectUtils.equal(r.defaultShapesFilled, this.defaultShapesFilled);
            result = (result && b0 && b2 && b3 && b4 && b5);
        }
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final LineAndShapeRenderer clone = (LineAndShapeRenderer)super.clone();
        clone.seriesShapesFilled = (BooleanList)this.seriesShapesFilled.clone();
        return clone;
    }
}
