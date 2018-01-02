// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.util.ObjectUtils;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.util.BooleanUtils;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.util.BooleanList;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYLineAndShapeRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private Boolean linesVisible;
    private BooleanList seriesLinesVisible;
    private boolean defaultLinesVisible;
    private Boolean shapesVisible;
    private BooleanList seriesShapesVisible;
    private boolean defaultShapesVisible;
    private Boolean shapesFilled;
    private BooleanList seriesShapesFilled;
    private boolean defaultShapesFilled;
    private boolean drawOutlines;
    private boolean useOutlinePaint;
    
    public XYLineAndShapeRenderer() {
        this.linesVisible = null;
        this.seriesLinesVisible = new BooleanList();
        this.defaultLinesVisible = true;
        this.shapesVisible = null;
        this.seriesShapesVisible = new BooleanList();
        this.defaultShapesVisible = true;
        this.shapesFilled = null;
        this.seriesShapesFilled = new BooleanList();
        this.defaultShapesFilled = true;
        this.drawOutlines = false;
        this.useOutlinePaint = false;
    }
    
    public int getPassCount() {
        return 2;
    }
    
    public boolean getItemLineVisible(final int series, final int item) {
        Boolean flag = this.linesVisible;
        if (flag == null) {
            flag = this.getSeriesLinesVisible(series);
        }
        if (flag != null) {
            return flag;
        }
        return this.defaultLinesVisible;
    }
    
    public Boolean getLinesVisible() {
        return this.linesVisible;
    }
    
    public void setLinesVisible(final Boolean visible) {
        this.linesVisible = visible;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setLinesVisible(final boolean visible) {
        this.setLinesVisible(BooleanUtils.valueOf(visible));
    }
    
    public Boolean getSeriesLinesVisible(final int series) {
        return this.seriesLinesVisible.getBoolean(series);
    }
    
    public void setSeriesLinesVisible(final int series, final Boolean flag) {
        this.seriesLinesVisible.setBoolean(series, flag);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setSeriesLinesVisible(final int series, final boolean visible) {
        this.setSeriesLinesVisible(series, BooleanUtils.valueOf(visible));
    }
    
    public boolean getDefaultLinesVisible() {
        return this.defaultLinesVisible;
    }
    
    public void setDefaultLinesVisible(final boolean flag) {
        this.defaultLinesVisible = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getItemShapeVisible(final int series, final int item) {
        Boolean flag = this.shapesVisible;
        if (flag == null) {
            flag = this.getSeriesShapesVisible(series);
        }
        if (flag != null) {
            return flag;
        }
        return this.defaultShapesVisible;
    }
    
    public Boolean getShapesVisible() {
        return this.shapesVisible;
    }
    
    public void setShapesVisible(final Boolean visible) {
        this.shapesVisible = visible;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setShapesVisible(final boolean visible) {
        this.setShapesVisible(BooleanUtils.valueOf(visible));
    }
    
    public Boolean getSeriesShapesVisible(final int series) {
        return this.seriesShapesVisible.getBoolean(series);
    }
    
    public void setSeriesShapesVisible(final int series, final boolean visible) {
        this.setSeriesShapesVisible(series, BooleanUtils.valueOf(visible));
    }
    
    public void setSeriesShapesVisible(final int series, final Boolean flag) {
        this.seriesShapesVisible.setBoolean(series, flag);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getDefaultShapesVisible() {
        return this.defaultShapesVisible;
    }
    
    public void setDefaultShapesVisible(final boolean flag) {
        this.defaultShapesVisible = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getItemShapeFilled(final int series, final int item) {
        Boolean flag = this.shapesFilled;
        if (flag == null) {
            flag = this.getSeriesShapesFilled(series);
        }
        if (flag != null) {
            return flag;
        }
        return this.defaultShapesVisible;
    }
    
    public void setShapesFilled(final boolean filled) {
        this.setShapesFilled(BooleanUtils.valueOf(filled));
    }
    
    public void setShapesFilled(final Boolean filled) {
        this.shapesFilled = filled;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Boolean getSeriesShapesFilled(final int series) {
        return this.seriesShapesFilled.getBoolean(series);
    }
    
    public void setSeriesShapesFilled(final int series, final boolean flag) {
        this.setSeriesShapesFilled(series, BooleanUtils.valueOf(flag));
    }
    
    public void setSeriesShapesFilled(final int series, final Boolean flag) {
        this.seriesShapesFilled.setBoolean(series, flag);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getDefaultShapesFilled() {
        return this.defaultShapesFilled;
    }
    
    public void setDefaultShapesFilled(final boolean flag) {
        this.defaultShapesFilled = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getDrawOutlines() {
        return this.drawOutlines;
    }
    
    public void setDrawOutlines(final boolean flag) {
        this.drawOutlines = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }
    
    public void setUseOutlinePaint(final boolean flag) {
        this.useOutlinePaint = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        final XYItemRendererState state = new XYItemRendererState(info);
        return state;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final Number x1n = dataset.getXValue(series, item);
        final Number y1n = dataset.getYValue(series, item);
        if (y1n == null || x1n == null) {
            return;
        }
        final double x1 = x1n.doubleValue();
        final double y1 = y1n.doubleValue();
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        final double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
        if (pass == 0) {
            if (this.getItemLineVisible(series, item) && item > 0) {
                final Number x0n = dataset.getXValue(series, item - 1);
                final Number y0n = dataset.getYValue(series, item - 1);
                if (y0n != null && x0n != null) {
                    final double x2 = x0n.doubleValue();
                    final double y2 = y0n.doubleValue();
                    final double transX2 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
                    final double transY2 = rangeAxis.valueToJava2D(y2, dataArea, yAxisLocation);
                    if (Double.isNaN(transX2) || Double.isNaN(transY2) || Double.isNaN(transX1) || Double.isNaN(transY1)) {
                        return;
                    }
                    final PlotOrientation orientation = plot.getOrientation();
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        state.workingLine.setLine(transY2, transX2, transY1, transX1);
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        state.workingLine.setLine(transX2, transY2, transX1, transY1);
                    }
                    if (state.workingLine.intersects(dataArea)) {
                        g2.setStroke(this.getItemStroke(series, item));
                        g2.setPaint(this.getItemPaint(series, item));
                        g2.draw(state.workingLine);
                    }
                }
            }
        }
        else if (pass == 1 && this.getItemShapeVisible(series, item)) {
            Shape shape = this.getItemShape(series, item);
            final PlotOrientation orientation2 = plot.getOrientation();
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                shape = this.createTransformedShape(shape, transY1, transX1);
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                shape = this.createTransformedShape(shape, transX1, transY1);
            }
            if (shape.intersects(dataArea)) {
                if (this.getItemShapeFilled(series, item)) {
                    g2.setPaint(this.getItemPaint(series, item));
                    g2.fill(shape);
                    if (this.getDrawOutlines()) {
                        if (this.getUseOutlinePaint()) {
                            g2.setPaint(this.getItemOutlinePaint(series, item));
                        }
                        else {
                            g2.setPaint(this.getItemPaint(series, item));
                        }
                        g2.draw(shape);
                    }
                }
                else {
                    if (this.getUseOutlinePaint()) {
                        g2.setPaint(this.getItemOutlinePaint(series, item));
                    }
                    else {
                        g2.setPaint(this.getItemPaint(series, item));
                    }
                    g2.draw(shape);
                }
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof XYLineAndShapeRenderer) {
            final XYLineAndShapeRenderer r = (XYLineAndShapeRenderer)obj;
            if (super.equals(obj)) {
                final boolean b0 = ObjectUtils.equal(this.linesVisible, r.linesVisible);
                final boolean b2 = ObjectUtils.equal(this.seriesLinesVisible, r.seriesLinesVisible);
                final boolean b3 = this.defaultLinesVisible == r.defaultLinesVisible;
                final boolean b4 = ObjectUtils.equal(this.shapesVisible, r.shapesVisible);
                final boolean b5 = ObjectUtils.equal(this.seriesShapesVisible, r.seriesShapesVisible);
                final boolean b6 = this.defaultShapesVisible == r.defaultShapesVisible;
                final boolean b7 = ObjectUtils.equal(this.shapesFilled, r.shapesFilled);
                final boolean b8 = ObjectUtils.equal(this.seriesShapesFilled, r.seriesShapesFilled);
                final boolean b9 = this.defaultShapesFilled == r.defaultShapesFilled;
                final boolean b10 = this.drawOutlines == r.drawOutlines;
                final boolean b11 = this.useOutlinePaint == r.useOutlinePaint;
                return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11;
            }
        }
        return false;
    }
}
