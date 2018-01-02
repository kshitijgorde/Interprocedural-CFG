// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.xy;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectInputStream;
import org.jfree.util.PaintUtilities;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.event.RendererChangeEvent;
import java.awt.Paint;

public class XYErrorRenderer extends XYLineAndShapeRenderer
{
    private boolean drawXError;
    private boolean drawYError;
    private double capLength;
    private transient Paint errorPaint;
    
    public XYErrorRenderer() {
        super(false, true);
        this.drawXError = true;
        this.drawYError = true;
        this.errorPaint = null;
        this.capLength = 4.0;
    }
    
    public boolean getDrawXError() {
        return this.drawXError;
    }
    
    public void setDrawXError(final boolean draw) {
        if (this.drawXError != draw) {
            this.drawXError = draw;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean getDrawYError() {
        return this.drawYError;
    }
    
    public void setDrawYError(final boolean draw) {
        if (this.drawYError != draw) {
            this.drawYError = draw;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public double getCapLength() {
        return this.capLength;
    }
    
    public void setCapLength(final double length) {
        this.capLength = length;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Paint getErrorPaint() {
        return this.errorPaint;
    }
    
    public void setErrorPaint(final Paint paint) {
        this.errorPaint = paint;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Range findDomainBounds(final XYDataset dataset) {
        if (dataset != null) {
            return DatasetUtilities.findDomainBounds(dataset, true);
        }
        return null;
    }
    
    public Range findRangeBounds(final XYDataset dataset) {
        if (dataset != null) {
            return DatasetUtilities.findRangeBounds(dataset, true);
        }
        return null;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        if (pass == 0 && dataset instanceof IntervalXYDataset && this.getItemVisible(series, item)) {
            final IntervalXYDataset ixyd = (IntervalXYDataset)dataset;
            final PlotOrientation orientation = plot.getOrientation();
            if (this.drawXError) {
                final double x0 = ixyd.getStartXValue(series, item);
                final double x2 = ixyd.getEndXValue(series, item);
                final double y = ixyd.getYValue(series, item);
                final RectangleEdge edge = plot.getDomainAxisEdge();
                final double xx0 = domainAxis.valueToJava2D(x0, dataArea, edge);
                final double xx2 = domainAxis.valueToJava2D(x2, dataArea, edge);
                final double yy = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
                Line2D cap1 = null;
                Line2D cap2 = null;
                final double adj = this.capLength / 2.0;
                Line2D line;
                if (orientation == PlotOrientation.VERTICAL) {
                    line = new Line2D.Double(xx0, yy, xx2, yy);
                    cap1 = new Line2D.Double(xx0, yy - adj, xx0, yy + adj);
                    cap2 = new Line2D.Double(xx2, yy - adj, xx2, yy + adj);
                }
                else {
                    line = new Line2D.Double(yy, xx0, yy, xx2);
                    cap1 = new Line2D.Double(yy - adj, xx0, yy + adj, xx0);
                    cap2 = new Line2D.Double(yy - adj, xx2, yy + adj, xx2);
                }
                g2.setStroke(new BasicStroke(1.0f));
                if (this.errorPaint != null) {
                    g2.setPaint(this.errorPaint);
                }
                else {
                    g2.setPaint(this.getItemPaint(series, item));
                }
                g2.draw(line);
                g2.draw(cap1);
                g2.draw(cap2);
            }
            if (this.drawYError) {
                final double y2 = ixyd.getStartYValue(series, item);
                final double y3 = ixyd.getEndYValue(series, item);
                final double x3 = ixyd.getXValue(series, item);
                final RectangleEdge edge = plot.getRangeAxisEdge();
                final double yy2 = rangeAxis.valueToJava2D(y2, dataArea, edge);
                final double yy3 = rangeAxis.valueToJava2D(y3, dataArea, edge);
                final double xx3 = domainAxis.valueToJava2D(x3, dataArea, plot.getDomainAxisEdge());
                Line2D cap1 = null;
                Line2D cap2 = null;
                final double adj = this.capLength / 2.0;
                Line2D line;
                if (orientation == PlotOrientation.VERTICAL) {
                    line = new Line2D.Double(xx3, yy2, xx3, yy3);
                    cap1 = new Line2D.Double(xx3 - adj, yy2, xx3 + adj, yy2);
                    cap2 = new Line2D.Double(xx3 - adj, yy3, xx3 + adj, yy3);
                }
                else {
                    line = new Line2D.Double(yy2, xx3, yy3, xx3);
                    cap1 = new Line2D.Double(yy2, xx3 - adj, yy2, xx3 + adj);
                    cap2 = new Line2D.Double(yy3, xx3 - adj, yy3, xx3 + adj);
                }
                g2.setStroke(new BasicStroke(1.0f));
                if (this.errorPaint != null) {
                    g2.setPaint(this.errorPaint);
                }
                else {
                    g2.setPaint(this.getItemPaint(series, item));
                }
                g2.draw(line);
                g2.draw(cap1);
                g2.draw(cap2);
            }
        }
        super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYErrorRenderer)) {
            return false;
        }
        final XYErrorRenderer that = (XYErrorRenderer)obj;
        return this.drawXError == that.drawXError && this.drawYError == that.drawYError && this.capLength == that.capLength && PaintUtilities.equal(this.errorPaint, that.errorPaint) && super.equals(obj);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.errorPaint = SerialUtilities.readPaint(stream);
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.errorPaint, stream);
    }
}
