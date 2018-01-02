// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.chart.HashUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.AffineTransform;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.axis.ValueAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Shape;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class XYShapeAnnotation extends AbstractXYAnnotation implements Cloneable, PublicCloneable, Serializable
{
    private static final long serialVersionUID = -8553218317600684041L;
    private transient Shape shape;
    private transient Stroke stroke;
    private transient Paint outlinePaint;
    private transient Paint fillPaint;
    
    public XYShapeAnnotation(final Shape shape) {
        this(shape, new BasicStroke(1.0f), Color.black);
    }
    
    public XYShapeAnnotation(final Shape shape, final Stroke stroke, final Paint outlinePaint) {
        this(shape, stroke, outlinePaint, null);
    }
    
    public XYShapeAnnotation(final Shape shape, final Stroke stroke, final Paint outlinePaint, final Paint fillPaint) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        this.shape = shape;
        this.stroke = stroke;
        this.outlinePaint = outlinePaint;
        this.fillPaint = fillPaint;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis, final int rendererIndex, final PlotRenderingInfo info) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        final Rectangle2D bounds = this.shape.getBounds2D();
        final double x0 = bounds.getMinX();
        final double x2 = bounds.getMaxX();
        final double xx0 = domainAxis.valueToJava2D(x0, dataArea, domainEdge);
        final double xx2 = domainAxis.valueToJava2D(x2, dataArea, domainEdge);
        final double m00 = (xx2 - xx0) / (x2 - x0);
        final double m2 = xx0 - x0 * m00;
        final double y0 = bounds.getMaxY();
        final double y2 = bounds.getMinY();
        final double yy0 = rangeAxis.valueToJava2D(y0, dataArea, rangeEdge);
        final double yy2 = rangeAxis.valueToJava2D(y2, dataArea, rangeEdge);
        final double m3 = (yy2 - yy0) / (y2 - y0);
        final double m4 = yy0 - m3 * y0;
        Shape s = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            final AffineTransform t1 = new AffineTransform(0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
            final AffineTransform t2 = new AffineTransform(m3, 0.0, 0.0, m00, m4, m2);
            s = t1.createTransformedShape(this.shape);
            s = t2.createTransformedShape(s);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final AffineTransform t3 = new AffineTransform(m00, 0.0, 0.0, m3, m2, m4);
            s = t3.createTransformedShape(this.shape);
        }
        if (this.fillPaint != null) {
            g2.setPaint(this.fillPaint);
            g2.fill(s);
        }
        if (this.stroke != null && this.outlinePaint != null) {
            g2.setPaint(this.outlinePaint);
            g2.setStroke(this.stroke);
            g2.draw(s);
        }
        this.addEntity(info, s, rendererIndex, this.getToolTipText(), this.getURL());
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof XYShapeAnnotation)) {
            return false;
        }
        final XYShapeAnnotation that = (XYShapeAnnotation)obj;
        return this.shape.equals(that.shape) && ObjectUtilities.equal(this.stroke, that.stroke) && PaintUtilities.equal(this.outlinePaint, that.outlinePaint) && PaintUtilities.equal(this.fillPaint, that.fillPaint);
    }
    
    public int hashCode() {
        int result = 193;
        result = 37 * result + this.shape.hashCode();
        if (this.stroke != null) {
            result = 37 * result + this.stroke.hashCode();
        }
        result = 37 * result + HashUtilities.hashCodeForPaint(this.outlinePaint);
        result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeShape(this.shape, stream);
        SerialUtilities.writeStroke(this.stroke, stream);
        SerialUtilities.writePaint(this.outlinePaint, stream);
        SerialUtilities.writePaint(this.fillPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.shape = SerialUtilities.readShape(stream);
        this.stroke = SerialUtilities.readStroke(stream);
        this.outlinePaint = SerialUtilities.readPaint(stream);
        this.fillPaint = SerialUtilities.readPaint(stream);
    }
}
