// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import java.awt.geom.Line2D;
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
import java.io.Serializable;

public class XYLineAnnotation implements XYAnnotation, Cloneable, Serializable
{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private transient Stroke stroke;
    private transient Paint paint;
    
    public XYLineAnnotation(final double x1, final double y1, final double x2, final double y2) {
        this(x1, y1, x2, y2, new BasicStroke(1.0f), Color.black);
    }
    
    public XYLineAnnotation(final double x1, final double y1, final double x2, final double y2, final Stroke stroke, final Paint paint) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke;
        this.paint = paint;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        float j2DX1 = 0.0f;
        float j2DX2 = 0.0f;
        float j2DY1 = 0.0f;
        float j2DY2 = 0.0f;
        if (orientation == PlotOrientation.VERTICAL) {
            j2DX1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
            j2DY1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
            j2DX2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
            j2DY2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
        }
        else if (orientation == PlotOrientation.HORIZONTAL) {
            j2DY1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
            j2DX1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
            j2DY2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
            j2DX2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
        }
        g2.setPaint(this.paint);
        g2.setStroke(this.stroke);
        final Line2D line = new Line2D.Float(j2DX1, j2DY1, j2DX2, j2DY2);
        g2.draw(line);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof XYLineAnnotation) {
            final XYLineAnnotation a = (XYLineAnnotation)object;
            final boolean b0 = this.x1 == a.x1;
            final boolean b2 = this.y1 == a.y1;
            final boolean b3 = this.x2 == a.x2;
            final boolean b4 = this.y2 == a.y2;
            final boolean b5 = ObjectUtils.equal(this.paint, a.paint);
            final boolean b6 = ObjectUtils.equal(this.stroke, a.stroke);
            return b0 && b2 && b3 && b4 && b5 && b6;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
        SerialUtilities.writeStroke(this.stroke, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
        this.stroke = SerialUtilities.readStroke(stream);
    }
}
