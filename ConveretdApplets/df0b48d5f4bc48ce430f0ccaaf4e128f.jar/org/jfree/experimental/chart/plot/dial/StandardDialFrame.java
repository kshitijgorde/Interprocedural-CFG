// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.plot.dial;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.chart.HashUtilities;
import org.jfree.util.PaintUtilities;
import java.awt.geom.Area;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Arc2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StandardDialFrame extends AbstractDialLayer implements DialFrame, Cloneable, PublicCloneable, Serializable
{
    private transient Paint backgroundPaint;
    private transient Paint foregroundPaint;
    private transient Stroke stroke;
    private double startAngle;
    private double extent;
    private double innerRadius;
    private double outerRadius;
    
    public StandardDialFrame() {
        this(0.0, 180.0);
    }
    
    public StandardDialFrame(final double startAngle, final double extent) {
        this.backgroundPaint = Color.gray;
        this.foregroundPaint = new Color(100, 100, 150);
        this.stroke = new BasicStroke(2.0f);
        this.innerRadius = 0.25;
        this.outerRadius = 0.75;
        this.startAngle = startAngle;
        this.extent = extent;
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.backgroundPaint = paint;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Paint getForegroundPaint() {
        return this.foregroundPaint;
    }
    
    public void setForegroundPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.foregroundPaint = paint;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void setStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.stroke = stroke;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getInnerRadius() {
        return this.innerRadius;
    }
    
    public void setInnerRadius(final double radius) {
        this.innerRadius = radius;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public double getOuterRadius() {
        return this.outerRadius;
    }
    
    public void setOuterRadius(final double radius) {
        this.outerRadius = radius;
        this.notifyListeners(new DialLayerChangeEvent(this));
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
    
    public Shape getWindow(final Rectangle2D frame) {
        final Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, this.innerRadius, this.innerRadius);
        final Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, this.outerRadius, this.outerRadius);
        final Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle, this.extent, 0);
        final Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle + this.extent, -this.extent, 0);
        final GeneralPath p = new GeneralPath();
        final Point2D point1 = inner.getStartPoint();
        p.moveTo((float)point1.getX(), (float)point1.getY());
        p.append(inner, true);
        p.append(outer, true);
        p.closePath();
        return p;
    }
    
    protected Shape getOuterWindow(final Rectangle2D frame) {
        final double radiusMargin = 0.02;
        final double angleMargin = 1.5;
        final Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, this.innerRadius - radiusMargin, this.innerRadius - radiusMargin);
        final Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, this.outerRadius + radiusMargin, this.outerRadius + radiusMargin);
        final Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle - angleMargin, this.extent + 2.0 * angleMargin, 0);
        final Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle + angleMargin + this.extent, -this.extent - 2.0 * angleMargin, 0);
        final GeneralPath p = new GeneralPath();
        final Point2D point1 = inner.getStartPoint();
        p.moveTo((float)point1.getX(), (float)point1.getY());
        p.append(inner, true);
        p.append(outer, true);
        p.closePath();
        return p;
    }
    
    public void draw(final Graphics2D g2, final DialPlot plot, final Rectangle2D frame, final Rectangle2D view) {
        final Shape window = this.getWindow(frame);
        final Shape outerWindow = this.getOuterWindow(frame);
        final Area area1 = new Area(outerWindow);
        final Area area2 = new Area(window);
        area1.subtract(area2);
        g2.setPaint(Color.lightGray);
        g2.fill(area1);
        g2.setStroke(this.stroke);
        g2.setPaint(this.foregroundPaint);
        g2.draw(window);
        g2.draw(outerWindow);
    }
    
    public boolean isClippedToWindow() {
        return false;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardDialFrame)) {
            return false;
        }
        final StandardDialFrame that = (StandardDialFrame)obj;
        return PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint) && PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint) && this.startAngle == that.startAngle && this.extent == that.extent && this.innerRadius == that.innerRadius && this.outerRadius == that.outerRadius && this.stroke.equals(that.stroke);
    }
    
    public int hashCode() {
        int result = 193;
        long temp = Double.doubleToLongBits(this.startAngle);
        result = 37 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.extent);
        result = 37 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.innerRadius);
        result = 37 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.outerRadius);
        result = 37 * result + (int)(temp ^ temp >>> 32);
        result = 37 * result + HashUtilities.hashCodeForPaint(this.backgroundPaint);
        result = 37 * result + HashUtilities.hashCodeForPaint(this.foregroundPaint);
        result = 37 * result + this.stroke.hashCode();
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.backgroundPaint, stream);
        SerialUtilities.writePaint(this.foregroundPaint, stream);
        SerialUtilities.writeStroke(this.stroke, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.backgroundPaint = SerialUtilities.readPaint(stream);
        this.foregroundPaint = SerialUtilities.readPaint(stream);
        this.stroke = SerialUtilities.readStroke(stream);
    }
}
