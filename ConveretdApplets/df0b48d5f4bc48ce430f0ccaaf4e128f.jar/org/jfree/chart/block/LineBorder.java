// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.block;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.ui.RectangleInsets;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;

public class LineBorder implements BlockFrame, Serializable
{
    private transient Paint paint;
    private transient Stroke stroke;
    private RectangleInsets insets;
    
    public LineBorder() {
        this(Color.black, new BasicStroke(1.0f), new RectangleInsets(1.0, 1.0, 1.0, 1.0));
    }
    
    public LineBorder(final Paint paint, final Stroke stroke, final RectangleInsets insets) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        this.paint = paint;
        this.stroke = stroke;
        this.insets = insets;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public RectangleInsets getInsets() {
        return this.insets;
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        final double w = area.getWidth();
        final double h = area.getHeight();
        if (w <= 0.0 || h <= 0.0) {
            return;
        }
        final double t = this.insets.calculateTopInset(h);
        final double b = this.insets.calculateBottomInset(h);
        final double l = this.insets.calculateLeftInset(w);
        final double r = this.insets.calculateRightInset(w);
        final double x = area.getX();
        final double y = area.getY();
        final double x2 = x + l / 2.0;
        final double x3 = x + w - r / 2.0;
        final double y2 = y + h - b / 2.0;
        final double y3 = y + t / 2.0;
        g2.setPaint(this.getPaint());
        g2.setStroke(this.getStroke());
        final Line2D line = new Line2D.Double();
        if (t > 0.0) {
            line.setLine(x2, y3, x3, y3);
            g2.draw(line);
        }
        if (b > 0.0) {
            line.setLine(x2, y2, x3, y2);
            g2.draw(line);
        }
        if (l > 0.0) {
            line.setLine(x2, y2, x2, y3);
            g2.draw(line);
        }
        if (r > 0.0) {
            line.setLine(x3, y2, x3, y3);
            g2.draw(line);
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LineBorder)) {
            return false;
        }
        final LineBorder that = (LineBorder)obj;
        return PaintUtilities.equal(this.paint, that.paint) && ObjectUtilities.equal(this.stroke, that.stroke) && this.insets.equals(that.insets);
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
