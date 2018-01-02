// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.ui.RectangleEdge;
import java.awt.geom.AffineTransform;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
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

public class XYShapeAnnotation implements XYAnnotation, Serializable
{
    private Shape shape;
    private Stroke stroke;
    private Paint paint;
    
    public XYShapeAnnotation(final Shape shape) {
        this(shape, new BasicStroke(1.0f), Color.black);
    }
    
    public XYShapeAnnotation(final Shape shape, final Stroke stroke, final Paint paint) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.shape = shape;
        this.stroke = stroke;
        this.paint = paint;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        final double m02 = domainAxis.valueToJava2D(0.0, dataArea, domainEdge);
        final double m3 = rangeAxis.valueToJava2D(0.0, dataArea, rangeEdge);
        final double m4 = domainAxis.valueToJava2D(1.0, dataArea, domainEdge) - m02;
        final double m5 = rangeAxis.valueToJava2D(1.0, dataArea, rangeEdge) - m3;
        Shape s = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            final AffineTransform t1 = new AffineTransform(0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
            final AffineTransform t2 = new AffineTransform(m5, 0.0, 0.0, m4, m3, m02);
            s = t1.createTransformedShape(this.shape);
            s = t2.createTransformedShape(s);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final AffineTransform t3 = new AffineTransform(m4, 0.0, 0.0, m5, m02, m3);
            s = t3.createTransformedShape(this.shape);
        }
        g2.setPaint(this.paint);
        g2.setStroke(this.stroke);
        g2.draw(s);
    }
}
