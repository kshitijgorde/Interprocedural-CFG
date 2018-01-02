// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.RefineryUtilities;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.GeneralPath;
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

public class XYPointerAnnotation extends XYTextAnnotation implements XYAnnotation, Cloneable, Serializable
{
    public static final double DEFAULT_TIP_RADIUS = 10.0;
    public static final double DEFAULT_BASE_RADIUS = 30.0;
    public static final double DEFAULT_LABEL_OFFSET = 3.0;
    public static final double DEFAULT_ARROW_LENGTH = 5.0;
    public static final double DEFAULT_ARROW_WIDTH = 3.0;
    private double angle;
    private double tipRadius;
    private double baseRadius;
    private double arrowLength;
    private double arrowWidth;
    private transient Stroke arrowStroke;
    private transient Paint arrowPaint;
    private double labelOffset;
    
    public XYPointerAnnotation(final String label, final double x, final double y, final double angle) {
        super(label, x, y);
        this.angle = angle;
        this.tipRadius = 10.0;
        this.baseRadius = 30.0;
        this.arrowLength = 5.0;
        this.arrowWidth = 3.0;
        this.labelOffset = 3.0;
        this.arrowStroke = new BasicStroke(1.0f);
        this.arrowPaint = Color.black;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public void setAngle(final double angle) {
        this.angle = angle;
    }
    
    public double getTipRadius() {
        return this.tipRadius;
    }
    
    public void setTipRadius(final double radius) {
        this.tipRadius = radius;
    }
    
    public double getBaseRadius() {
        return this.baseRadius;
    }
    
    public void setBaseRadius(final double radius) {
        this.baseRadius = radius;
    }
    
    public double getLabelOffset() {
        return this.labelOffset;
    }
    
    public void setLabelOffset(final double offset) {
        this.labelOffset = offset;
    }
    
    public double getArrowLength() {
        return this.arrowLength;
    }
    
    public void setArrowLength(final double length) {
        this.arrowLength = length;
    }
    
    public double getArrowWidth() {
        return this.arrowWidth;
    }
    
    public void setArrowWidth(final double width) {
        this.arrowWidth = width;
    }
    
    public Stroke getArrowStroke() {
        return this.arrowStroke;
    }
    
    public void setArrowStroke(final Stroke stroke) {
        this.arrowStroke = stroke;
    }
    
    public Paint getArrowPaint() {
        return this.arrowPaint;
    }
    
    public void setArrowPaint(final Paint paint) {
        this.arrowPaint = paint;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        final double j2DX = domainAxis.valueToJava2D(this.getX(), dataArea, domainEdge);
        final double j2DY = rangeAxis.valueToJava2D(this.getY(), dataArea, rangeEdge);
        final double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
        final double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
        final double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
        final double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
        final double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
        final double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
        final double arrowLeftX = arrowBaseX + Math.cos(this.angle + 1.5707963267948966) * this.arrowWidth;
        final double arrowLeftY = arrowBaseY + Math.sin(this.angle + 1.5707963267948966) * this.arrowWidth;
        final double arrowRightX = arrowBaseX - Math.cos(this.angle + 1.5707963267948966) * this.arrowWidth;
        final double arrowRightY = arrowBaseY - Math.sin(this.angle + 1.5707963267948966) * this.arrowWidth;
        final GeneralPath arrow = new GeneralPath();
        arrow.moveTo((float)endX, (float)endY);
        arrow.lineTo((float)arrowLeftX, (float)arrowLeftY);
        arrow.lineTo((float)arrowRightX, (float)arrowRightY);
        arrow.closePath();
        g2.setStroke(this.arrowStroke);
        g2.setPaint(this.arrowPaint);
        final Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);
        g2.fill(arrow);
        g2.setFont(this.getFont());
        g2.setPaint(this.getPaint());
        final double labelX = j2DX + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
        final double labelY = j2DY + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
        RefineryUtilities.drawAlignedString(this.getText(), g2, (float)labelX, (float)labelY, this.getTextAnchor());
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof XYPointerAnnotation) {
            final XYPointerAnnotation a = (XYPointerAnnotation)object;
            final boolean b0 = this.angle == a.angle;
            final boolean b2 = this.tipRadius == a.tipRadius;
            final boolean b3 = this.baseRadius == a.baseRadius;
            final boolean b4 = this.arrowLength == a.arrowLength;
            final boolean b5 = this.arrowWidth == a.arrowWidth;
            final boolean b6 = this.arrowPaint.equals(a.arrowPaint);
            final boolean b7 = this.arrowStroke.equals(a.arrowStroke);
            final boolean b8 = this.labelOffset == a.labelOffset;
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.arrowPaint, stream);
        SerialUtilities.writeStroke(this.arrowStroke, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.arrowPaint = SerialUtilities.readPaint(stream);
        this.arrowStroke = SerialUtilities.readStroke(stream);
    }
}
