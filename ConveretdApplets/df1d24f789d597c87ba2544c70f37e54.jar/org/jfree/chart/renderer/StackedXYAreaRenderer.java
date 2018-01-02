// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.geom.Line2D;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import java.awt.Point;
import org.jfree.chart.plot.PlotOrientation;
import java.util.Stack;
import java.awt.Polygon;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.TableXYDataset;
import org.jfree.data.Range;
import org.jfree.data.XYDataset;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StackedXYAreaRenderer extends XYAreaRenderer implements Cloneable, PublicCloneable, Serializable
{
    private Paint shapePaint;
    private Stroke shapeStroke;
    
    public StackedXYAreaRenderer() {
        this(4);
    }
    
    public StackedXYAreaRenderer(final int type) {
        this(type, null, null);
    }
    
    public StackedXYAreaRenderer(final int type, final XYToolTipGenerator labelGenerator, final XYURLGenerator urlGenerator) {
        super(type, labelGenerator, urlGenerator);
        this.shapePaint = null;
        this.shapeStroke = null;
    }
    
    public RangeType getRangeType() {
        return RangeType.STACKED;
    }
    
    public Range getRangeExtent(final XYDataset dataset) {
        return DatasetUtilities.getStackedRangeExtent((TableXYDataset)dataset);
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        return new StackedXYAreaRendererState(info);
    }
    
    public int getPassCount() {
        return 2;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final PlotOrientation orientation = plot.getOrientation();
        final StackedXYAreaRendererState areaState = (StackedXYAreaRendererState)state;
        final TableXYDataset tableXYDataset = (TableXYDataset)dataset;
        final int itemCount = tableXYDataset.getItemCount();
        final Number x1 = dataset.getXValue(series, item);
        Number y1 = dataset.getYValue(series, item);
        boolean nullPoint = false;
        if (y1 == null) {
            y1 = new Double(0.0);
            nullPoint = true;
        }
        final double ph1 = this.getPreviousHeight(dataset, series, item);
        final double transX1 = domainAxis.valueToJava2D(x1.doubleValue(), dataArea, plot.getDomainAxisEdge());
        final double transY1 = rangeAxis.valueToJava2D(y1.doubleValue() + ph1, dataArea, plot.getRangeAxisEdge());
        final Paint seriesPaint = this.getItemPaint(series, item);
        final Stroke seriesStroke = this.getItemStroke(series, item);
        if (pass == 0) {
            if (item == 0) {
                areaState.setSeriesArea(new Polygon());
                areaState.setLastSeriesPoints(areaState.getCurrentSeriesPoints());
                areaState.setCurrentSeriesPoints(new Stack());
                final double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
                if (orientation == PlotOrientation.VERTICAL) {
                    areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
                }
                else if (orientation == PlotOrientation.HORIZONTAL) {
                    areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
                }
            }
            if (orientation == PlotOrientation.VERTICAL) {
                final Point point = new Point((int)transX1, (int)transY1);
                areaState.getSeriesArea().addPoint((int)point.getX(), (int)point.getY());
                areaState.getCurrentSeriesPoints().push(point);
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                areaState.getSeriesArea().addPoint((int)transY1, (int)transX1);
            }
            if (this.getPlotLines() && item > 0) {
                final Number x2 = dataset.getXValue(series, item - 1);
                final Number y2 = dataset.getYValue(series, item - 1);
                final double ph2 = this.getPreviousHeight(dataset, series, item - 1);
                final double transX2 = domainAxis.valueToJava2D(x2.doubleValue(), dataArea, plot.getDomainAxisEdge());
                final double transY3 = rangeAxis.valueToJava2D(y2.doubleValue() + ph2, dataArea, plot.getRangeAxisEdge());
                if (orientation == PlotOrientation.VERTICAL) {
                    areaState.getLine().setLine(transX2, transY3, transX1, transY1);
                }
                else if (orientation == PlotOrientation.HORIZONTAL) {
                    areaState.getLine().setLine(transY3, transX2, transY1, transX1);
                }
                g2.draw(areaState.getLine());
            }
            if (this.getPlotArea() && item > 0 && item == itemCount - 1) {
                final double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
                if (orientation == PlotOrientation.VERTICAL) {
                    areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
                }
                else if (orientation == PlotOrientation.HORIZONTAL) {
                    areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
                }
                if (series != 0) {
                    final Stack points = areaState.getLastSeriesPoints();
                    while (!points.empty()) {
                        final Point point2 = points.pop();
                        areaState.getSeriesArea().addPoint((int)point2.getX(), (int)point2.getY());
                    }
                }
                g2.setPaint(seriesPaint);
                g2.setStroke(seriesStroke);
                g2.fill(areaState.getSeriesArea());
                if (this.isOutline()) {
                    g2.setStroke(plot.getOutlineStroke());
                    g2.setPaint(plot.getOutlinePaint());
                    g2.draw(areaState.getSeriesArea());
                }
            }
            this.updateCrosshairValues(crosshairState, x1.doubleValue(), y1.doubleValue(), transX1, transY1, orientation);
        }
        else if (pass == 1) {
            Shape shape = null;
            if (this.getPlotShapes()) {
                shape = this.getItemShape(series, item);
                if (plot.getOrientation() == PlotOrientation.VERTICAL) {
                    shape = this.createTransformedShape(shape, transX1, transY1);
                }
                else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
                    shape = this.createTransformedShape(shape, transY1, transX1);
                }
                if (!nullPoint) {
                    if (this.getShapePaint() != null) {
                        g2.setPaint(this.getShapePaint());
                    }
                    else {
                        g2.setPaint(seriesPaint);
                    }
                    if (this.getShapeStroke() != null) {
                        g2.setStroke(this.getShapeStroke());
                    }
                    else {
                        g2.setStroke(seriesStroke);
                    }
                    g2.draw(shape);
                }
            }
            else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
                shape = new Rectangle2D.Double(transX1 - 3.0, transY1 - 3.0, 6.0, 6.0);
            }
            else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
                shape = new Rectangle2D.Double(transY1 - 3.0, transX1 - 3.0, 6.0, 6.0);
            }
            if (state.getInfo() != null) {
                final EntityCollection entities = state.getInfo().getOwner().getEntityCollection();
                if (entities != null && shape != null && !nullPoint) {
                    String tip = null;
                    final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
                    if (generator != null) {
                        tip = generator.generateToolTip(dataset, series, item);
                    }
                    String url = null;
                    if (this.getURLGenerator() != null) {
                        url = this.getURLGenerator().generateURL(dataset, series, item);
                    }
                    final XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
                    entities.addEntity(entity);
                }
            }
        }
    }
    
    protected double getPreviousHeight(final XYDataset data, final int series, final int index) {
        double result = 0.0;
        for (int i = 0; i < series; ++i) {
            final Number tmp = data.getYValue(i, index);
            if (tmp != null) {
                result += tmp.doubleValue();
            }
        }
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public Paint getShapePaint() {
        return this.shapePaint;
    }
    
    public Stroke getShapeStroke() {
        return this.shapeStroke;
    }
    
    public void setShapePaint(final Paint shapePaint) {
        this.shapePaint = shapePaint;
    }
    
    public void setShapeStroke(final Stroke shapeStroke) {
        this.shapeStroke = shapeStroke;
    }
    
    static class StackedXYAreaRendererState extends XYItemRendererState
    {
        private Polygon seriesArea;
        private Line2D line;
        private Stack lastSeriesPoints;
        private Stack currentSeriesPoints;
        
        public StackedXYAreaRendererState(final PlotRenderingInfo info) {
            super(info);
            this.seriesArea = null;
            this.line = null;
            this.lastSeriesPoints = new Stack();
            this.currentSeriesPoints = null;
        }
        
        public Polygon getSeriesArea() {
            return this.seriesArea;
        }
        
        public void setSeriesArea(final Polygon area) {
            this.seriesArea = area;
        }
        
        public Line2D getLine() {
            return this.line;
        }
        
        public Stack getCurrentSeriesPoints() {
            return this.currentSeriesPoints;
        }
        
        public void setCurrentSeriesPoints(final Stack points) {
            this.currentSeriesPoints = points;
        }
        
        public Stack getLastSeriesPoints() {
            return this.lastSeriesPoints;
        }
        
        public void setLastSeriesPoints(final Stack points) {
            this.lastSeriesPoints = points;
        }
    }
}
