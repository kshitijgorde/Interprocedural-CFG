// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.awt.geom.GeneralPath;
import org.jfree.chart.labels.XYToolTipGenerator;
import java.awt.Stroke;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.geom.Line2D;
import java.awt.Shape;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import java.awt.Color;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYDifferenceRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private transient Paint positivePaint;
    private transient Paint negativePaint;
    private boolean plotShapes;
    
    public XYDifferenceRenderer() {
        this(Color.green, Color.red, false);
    }
    
    public XYDifferenceRenderer(final Paint positivePaint, final Paint negativePaint, final boolean shapes) {
        this.plotShapes = true;
        if (positivePaint == null) {
            throw new IllegalArgumentException("Null 'positivePaint' argument.");
        }
        if (negativePaint == null) {
            throw new IllegalArgumentException("Null 'negativePaint' argument.");
        }
        this.positivePaint = positivePaint;
        this.negativePaint = negativePaint;
        this.plotShapes = shapes;
    }
    
    public Paint getPositivePaint() {
        return this.positivePaint;
    }
    
    public void setPositivePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.positivePaint = paint;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Paint getNegativePaint() {
        return this.negativePaint;
    }
    
    public void setNegativePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.negativePaint = paint;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getPlotShapes() {
        return this.plotShapes;
    }
    
    public void setPlotShapes(final boolean flag) {
        this.plotShapes = flag;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        return super.initialise(g2, dataArea, plot, data, info);
    }
    
    public int getPassCount() {
        return 2;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        if (pass == 0) {
            this.drawItemPass0(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
        }
        else if (pass == 1) {
            this.drawItemPass1(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
        }
    }
    
    protected void drawItemPass0(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState) {
        if (series == 0) {
            final Number y0n = dataset.getYValue(0, item);
            final Number x1n = dataset.getXValue(1, item);
            final Number y1n = dataset.getYValue(1, item);
            final PlotOrientation orientation = plot.getOrientation();
            final RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
            final RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
            final double y0 = y0n.doubleValue();
            final double transY0 = rangeAxis.valueToJava2D(y0, dataArea, rangeAxisLocation);
            final double x1 = x1n.doubleValue();
            final double y2 = y1n.doubleValue();
            final double transX1 = domainAxis.valueToJava2D(x1, dataArea, domainAxisLocation);
            final double transY2 = rangeAxis.valueToJava2D(y2, dataArea, rangeAxisLocation);
            if (item > 0) {
                final Number prevx0n = dataset.getXValue(0, item - 1);
                final Number prevy0n = dataset.getYValue(0, item - 1);
                final Number prevy1n = dataset.getYValue(1, item - 1);
                final double prevx0 = prevx0n.doubleValue();
                final double prevy0 = prevy0n.doubleValue();
                final double prevtransX0 = domainAxis.valueToJava2D(prevx0, dataArea, domainAxisLocation);
                final double prevtransY0 = rangeAxis.valueToJava2D(prevy0, dataArea, rangeAxisLocation);
                final double prevy2 = prevy1n.doubleValue();
                final double prevtransY2 = rangeAxis.valueToJava2D(prevy2, dataArea, rangeAxisLocation);
                final Shape positive = this.getPositiveArea((float)prevtransX0, (float)prevtransY0, (float)prevtransY2, (float)transX1, (float)transY0, (float)transY2, orientation);
                if (positive != null) {
                    g2.setPaint(this.getPositivePaint());
                    g2.fill(positive);
                }
                final Shape negative = this.getNegativeArea((float)prevtransX0, (float)prevtransY0, (float)prevtransY2, (float)transX1, (float)transY0, (float)transY2, orientation);
                if (negative != null) {
                    g2.setPaint(this.getNegativePaint());
                    g2.fill(negative);
                }
            }
        }
    }
    
    protected void drawItemPass1(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState) {
        Shape entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        final Paint seriesPaint = this.getItemPaint(series, item);
        final Stroke seriesStroke = this.getItemStroke(series, item);
        g2.setPaint(seriesPaint);
        g2.setStroke(seriesStroke);
        if (series == 0) {
            final Number x0n = dataset.getXValue(0, item);
            final Number y0n = dataset.getYValue(0, item);
            final Number x1n = dataset.getXValue(1, item);
            final Number y1n = dataset.getYValue(1, item);
            final PlotOrientation orientation = plot.getOrientation();
            final RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
            final RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
            final double x0 = x0n.doubleValue();
            final double y0 = y0n.doubleValue();
            final double transX0 = domainAxis.valueToJava2D(x0, dataArea, domainAxisLocation);
            final double transY0 = rangeAxis.valueToJava2D(y0, dataArea, rangeAxisLocation);
            final double x2 = x1n.doubleValue();
            final double y2 = y1n.doubleValue();
            final double transX2 = domainAxis.valueToJava2D(x2, dataArea, domainAxisLocation);
            final double transY2 = rangeAxis.valueToJava2D(y2, dataArea, rangeAxisLocation);
            if (item > 0) {
                final Number prevx0n = dataset.getXValue(0, item - 1);
                final Number prevy0n = dataset.getYValue(0, item - 1);
                final Number prevx1n = dataset.getXValue(1, item - 1);
                final Number prevy1n = dataset.getYValue(1, item - 1);
                final double prevx0 = prevx0n.doubleValue();
                final double prevy0 = prevy0n.doubleValue();
                final double prevtransX0 = domainAxis.valueToJava2D(prevx0, dataArea, domainAxisLocation);
                final double prevtransY0 = rangeAxis.valueToJava2D(prevy0, dataArea, rangeAxisLocation);
                final double prevx2 = prevx1n.doubleValue();
                final double prevy2 = prevy1n.doubleValue();
                final double prevtransX2 = domainAxis.valueToJava2D(prevx2, dataArea, domainAxisLocation);
                final double prevtransY2 = rangeAxis.valueToJava2D(prevy2, dataArea, rangeAxisLocation);
                Line2D line0 = null;
                Line2D line2 = null;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    line0 = new Line2D.Double(transY0, transX0, prevtransY0, prevtransX0);
                    line2 = new Line2D.Double(transY2, transX2, prevtransY2, prevtransX2);
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    line0 = new Line2D.Double(transX0, transY0, prevtransX0, prevtransY0);
                    line2 = new Line2D.Double(transX2, transY2, prevtransX2, prevtransY2);
                }
                if (line0 != null && line0.intersects(dataArea)) {
                    g2.setPaint(this.getItemPaint(series, item));
                    g2.draw(line0);
                }
                if (line2 != null && line2.intersects(dataArea)) {
                    g2.setPaint(this.getItemPaint(1, item));
                    g2.draw(line2);
                }
            }
            if (this.getPlotShapes()) {
                Shape shape0 = this.getItemShape(series, item);
                if (orientation == PlotOrientation.HORIZONTAL) {
                    shape0 = this.createTransformedShape(shape0, transY0, transX0);
                }
                else {
                    shape0 = this.createTransformedShape(shape0, transX0, transY0);
                }
                if (shape0.intersects(dataArea)) {
                    g2.setPaint(this.getItemPaint(series, item));
                    g2.fill(shape0);
                }
                entityArea = shape0;
                if (entities != null) {
                    if (entityArea == null) {
                        entityArea = new Rectangle2D.Double(transX0 - 2.0, transY0 - 2.0, 4.0, 4.0);
                    }
                    String tip = null;
                    final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
                    if (generator != null) {
                        tip = generator.generateToolTip(dataset, series, item);
                    }
                    String url = null;
                    if (this.getURLGenerator() != null) {
                        url = this.getURLGenerator().generateURL(dataset, series, item);
                    }
                    final XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
                    entities.addEntity(entity);
                }
                Shape shape2 = this.getItemShape(series + 1, item);
                shape2 = this.createTransformedShape(shape2, transX2, transY2);
                if (shape2.intersects(dataArea)) {
                    g2.setPaint(this.getItemPaint(series + 1, item));
                    g2.fill(shape2);
                }
                entityArea = shape2;
                if (entities != null) {
                    if (entityArea == null) {
                        entityArea = new Rectangle2D.Double(transX2 - 2.0, transY2 - 2.0, 4.0, 4.0);
                    }
                    String tip2 = null;
                    final XYToolTipGenerator generator2 = this.getToolTipGenerator(series, item);
                    if (generator2 != null) {
                        tip2 = generator2.generateToolTip(dataset, series + 1, item);
                    }
                    String url2 = null;
                    if (this.getURLGenerator() != null) {
                        url2 = this.getURLGenerator().generateURL(dataset, series + 1, item);
                    }
                    final XYItemEntity entity2 = new XYItemEntity(entityArea, dataset, series + 1, item, tip2, url2);
                    entities.addEntity(entity2);
                }
            }
        }
    }
    
    protected Shape getPositiveArea(final float x0, final float y0A, final float y0B, final float x1, final float y1A, final float y1B, final PlotOrientation orientation) {
        Shape result = null;
        boolean startsNegative = y0A >= y0B;
        boolean endsNegative = y1A >= y1B;
        if (orientation == PlotOrientation.HORIZONTAL) {
            startsNegative = (y0B >= y0A);
            endsNegative = (y1B >= y1A);
        }
        if (startsNegative) {
            if (!endsNegative) {
                final float[] p = this.getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
                final GeneralPath area = new GeneralPath();
                if (orientation == PlotOrientation.HORIZONTAL) {
                    area.moveTo(y1A, x1);
                    area.lineTo(p[1], p[0]);
                    area.lineTo(y1B, x1);
                    area.closePath();
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    area.moveTo(x1, y1A);
                    area.lineTo(p[0], p[1]);
                    area.lineTo(x1, y1B);
                    area.closePath();
                }
                result = area;
            }
        }
        else if (endsNegative) {
            final float[] p = this.getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
            final GeneralPath area = new GeneralPath();
            if (orientation == PlotOrientation.HORIZONTAL) {
                area.moveTo(y0A, x0);
                area.lineTo(p[1], p[0]);
                area.lineTo(y0B, x0);
                area.closePath();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                area.moveTo(x0, y0A);
                area.lineTo(p[0], p[1]);
                area.lineTo(x0, y0B);
                area.closePath();
            }
            result = area;
        }
        else {
            final GeneralPath area2 = new GeneralPath();
            if (orientation == PlotOrientation.HORIZONTAL) {
                area2.moveTo(y0A, x0);
                area2.lineTo(y1A, x1);
                area2.lineTo(y1B, x1);
                area2.lineTo(y0B, x0);
                area2.closePath();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                area2.moveTo(x0, y0A);
                area2.lineTo(x1, y1A);
                area2.lineTo(x1, y1B);
                area2.lineTo(x0, y0B);
                area2.closePath();
            }
            result = area2;
        }
        return result;
    }
    
    protected Shape getNegativeArea(final float x0, final float y0A, final float y0B, final float x1, final float y1A, final float y1B, final PlotOrientation orientation) {
        Shape result = null;
        boolean startsNegative = y0A >= y0B;
        boolean endsNegative = y1A >= y1B;
        if (orientation == PlotOrientation.HORIZONTAL) {
            startsNegative = (y0B >= y0A);
            endsNegative = (y1B >= y1A);
        }
        if (startsNegative) {
            if (endsNegative) {
                final GeneralPath area = new GeneralPath();
                if (orientation == PlotOrientation.HORIZONTAL) {
                    area.moveTo(y0A, x0);
                    area.lineTo(y1A, x1);
                    area.lineTo(y1B, x1);
                    area.lineTo(y0B, x0);
                    area.closePath();
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    area.moveTo(x0, y0A);
                    area.lineTo(x1, y1A);
                    area.lineTo(x1, y1B);
                    area.lineTo(x0, y0B);
                    area.closePath();
                }
                result = area;
            }
            else {
                final float[] p = this.getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
                final GeneralPath area2 = new GeneralPath();
                if (orientation == PlotOrientation.HORIZONTAL) {
                    area2.moveTo(y0A, x0);
                    area2.lineTo(p[1], p[0]);
                    area2.lineTo(y0B, x0);
                    area2.closePath();
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    area2.moveTo(x0, y0A);
                    area2.lineTo(p[0], p[1]);
                    area2.lineTo(x0, y0B);
                    area2.closePath();
                }
                result = area2;
            }
        }
        else if (endsNegative) {
            final float[] p = this.getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
            final GeneralPath area2 = new GeneralPath();
            if (orientation == PlotOrientation.HORIZONTAL) {
                area2.moveTo(y1A, x1);
                area2.lineTo(p[1], p[0]);
                area2.lineTo(y1B, x1);
                area2.closePath();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                area2.moveTo(x1, y1A);
                area2.lineTo(p[0], p[1]);
                area2.lineTo(x1, y1B);
                area2.closePath();
            }
            result = area2;
        }
        return result;
    }
    
    private float[] getIntersection(final float x1, final float y1, final float x2, final float y2, final float x3, final float y3, final float x4, final float y4) {
        final float n = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
        final float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        final float u = n / d;
        final float[] result = { x1 + u * (x2 - x1), y1 + u * (y2 - y1) };
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.positivePaint, stream);
        SerialUtilities.writePaint(this.negativePaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.positivePaint = SerialUtilities.readPaint(stream);
        this.negativePaint = SerialUtilities.readPaint(stream);
    }
}
