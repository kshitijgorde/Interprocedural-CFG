// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.plot.dial;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import java.awt.geom.Point2D;
import org.jfree.text.TextUtilities;
import org.jfree.ui.TextAnchor;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StandardDialScale extends AbstractDialLayer implements DialScale, DialLayer, Cloneable, PublicCloneable, Serializable
{
    private double lowerBound;
    private double upperBound;
    private double startAngle;
    private double extent;
    private double tickRadius;
    private double majorTickIncrement;
    private double majorTickLength;
    private transient Paint majorTickPaint;
    private transient Stroke majorTickStroke;
    private int minorTickCount;
    private double minorTickLength;
    private double tickLabelOffset;
    private Font tickLabelFont;
    private boolean tickLabelsVisible;
    private boolean firstTickLabelVisible;
    private transient Paint tickLabelPaint;
    
    public StandardDialScale() {
        this(0.0, 100.0, 175.0, -170.0);
    }
    
    public StandardDialScale(final double lowerBound, final double upperBound, final double startAngle, final double extent) {
        this.startAngle = startAngle;
        this.extent = extent;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.majorTickPaint = Color.black;
        this.majorTickStroke = new BasicStroke(3.0f);
        this.tickLabelFont = new Font("Dialog", 1, 16);
        this.tickLabelPaint = Color.blue;
        this.minorTickCount = 4;
        this.minorTickLength = 0.02;
        this.tickLabelOffset = 0.1;
        this.majorTickIncrement = 10.0;
        this.tickRadius = 0.7;
        this.tickLabelsVisible = true;
        this.firstTickLabelVisible = true;
    }
    
    public double getStartAngle() {
        return this.startAngle;
    }
    
    public void setStartAngle(final double angle) {
        this.startAngle = angle;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getExtent() {
        return this.extent;
    }
    
    public void setExtent(final double extent) {
        this.extent = extent;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getTickRadius() {
        return this.tickRadius;
    }
    
    public void setTickRadius(final double radius) {
        this.tickRadius = radius;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getMajorTickIncrement() {
        return this.majorTickIncrement;
    }
    
    public void setMajorTickIncrement(final double increment) {
        this.majorTickIncrement = increment;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getMajorTickLength() {
        return this.majorTickLength;
    }
    
    public void setMajorTickLength(final double length) {
        this.majorTickLength = length;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Paint getMajorTickPaint() {
        return this.majorTickPaint;
    }
    
    public void setMajorTickPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.majorTickPaint = paint;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Stroke getMajorTickStroke() {
        return this.majorTickStroke;
    }
    
    public void setMajorTickStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.majorTickStroke = stroke;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public int getMinorTickCount() {
        return this.minorTickCount;
    }
    
    public void setMinorTickCount(final int count) {
        this.minorTickCount = count;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getMinorTickLength() {
        return this.minorTickLength;
    }
    
    public void setMinorTickLength(final double length) {
        this.minorTickLength = length;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getTickLabelOffset() {
        return this.tickLabelOffset;
    }
    
    public void setTickLabelOffset(final double offset) {
        this.tickLabelOffset = offset;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }
    
    public void setTickLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.tickLabelFont = font;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }
    
    public void setTickLabelPaint(final Paint paint) {
        this.tickLabelPaint = paint;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public boolean getTickLabelsVisible() {
        return this.tickLabelsVisible;
    }
    
    public void setTickLabelsVisible(final boolean visible) {
        this.tickLabelsVisible = visible;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public boolean getFirstTickLabelVisible() {
        return this.firstTickLabelVisible;
    }
    
    public void setFirstTickLabelVisible(final boolean visible) {
        this.firstTickLabelVisible = visible;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public boolean isClippedToWindow() {
        return true;
    }
    
    public void draw(final Graphics2D g2, final DialPlot plot, final Rectangle2D frame, final Rectangle2D view) {
        final Rectangle2D arcRect = DialPlot.rectangleByRadius(frame, this.tickRadius, this.tickRadius);
        final Rectangle2D arcRectInner = DialPlot.rectangleByRadius(frame, this.tickRadius - this.minorTickLength, this.tickRadius - this.minorTickLength);
        final Rectangle2D arcRectForLabels = DialPlot.rectangleByRadius(frame, this.tickRadius - this.tickLabelOffset, this.tickRadius - this.tickLabelOffset);
        boolean firstLabel = true;
        final Arc2D arc = new Arc2D.Double();
        for (double v = this.lowerBound; v <= this.upperBound; v += this.majorTickIncrement) {
            arc.setArc(arcRect, this.startAngle, this.valueToAngle(v) - this.startAngle, 0);
            Point2D pt0 = arc.getEndPoint();
            arc.setArc(arcRectInner, this.startAngle, this.valueToAngle(v) - this.startAngle, 0);
            final Point2D pt2 = arc.getEndPoint();
            g2.setPaint(this.majorTickPaint);
            g2.setStroke(this.majorTickStroke);
            g2.draw(new Line2D.Double(pt0, pt2));
            arc.setArc(arcRectForLabels, this.startAngle, this.valueToAngle(v) - this.startAngle, 0);
            final Point2D pt3 = arc.getEndPoint();
            if (this.tickLabelsVisible && (!firstLabel || this.firstTickLabelVisible)) {
                g2.setFont(this.tickLabelFont);
                TextUtilities.drawAlignedString(String.valueOf(v), g2, (float)pt3.getX(), (float)pt3.getY(), TextAnchor.CENTER);
            }
            firstLabel = false;
            if (this.minorTickCount > 0) {
                final double minorTickIncrement = this.majorTickIncrement / (this.minorTickCount + 1);
                for (int i = 0; i < this.minorTickCount; ++i) {
                    final double vv = v + (i + 1) * minorTickIncrement;
                    if (vv >= this.upperBound) {
                        break;
                    }
                    final double angle = this.valueToAngle(vv);
                    arc.setArc(arcRect, this.startAngle, angle - this.startAngle, 0);
                    pt0 = arc.getEndPoint();
                    arc.setArc(arcRectInner, this.startAngle, angle - this.startAngle, 0);
                    final Point2D pt4 = arc.getEndPoint();
                    g2.setStroke(new BasicStroke(1.0f));
                    g2.draw(new Line2D.Double(pt0, pt4));
                }
            }
        }
    }
    
    public double valueToAngle(final double value) {
        final double range = this.upperBound - this.lowerBound;
        final double unit = this.extent / range;
        return this.startAngle + unit * (value - this.lowerBound);
    }
    
    public double angleToValue(final double angle) {
        return Double.NaN;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardDialScale)) {
            return false;
        }
        final StandardDialScale that = (StandardDialScale)obj;
        return this.lowerBound == that.lowerBound && this.upperBound == that.upperBound && this.startAngle == that.startAngle && this.extent == that.extent && this.tickRadius == that.tickRadius && this.majorTickIncrement == that.majorTickIncrement && this.majorTickLength == that.majorTickLength && PaintUtilities.equal(this.majorTickPaint, that.majorTickPaint) && this.majorTickStroke.equals(that.majorTickStroke) && this.minorTickCount == that.minorTickCount && this.minorTickLength == that.minorTickLength && this.tickLabelOffset == that.tickLabelOffset && this.tickLabelFont.equals(that.tickLabelFont) && PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.majorTickPaint, stream);
        SerialUtilities.writeStroke(this.majorTickStroke, stream);
        SerialUtilities.writePaint(this.tickLabelPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.majorTickPaint = SerialUtilities.readPaint(stream);
        this.majorTickStroke = SerialUtilities.readStroke(stream);
        this.tickLabelPaint = SerialUtilities.readPaint(stream);
    }
}
