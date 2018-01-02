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
import java.awt.Shape;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.ui.StandardGradientPaintTransformer;
import java.awt.Color;
import org.jfree.ui.GradientPaintTransformer;
import java.awt.Paint;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class DialBackground extends AbstractDialLayer implements DialLayer, Cloneable, PublicCloneable, Serializable
{
    private transient Paint paint;
    private GradientPaintTransformer gradientPaintTransformer;
    
    public DialBackground() {
        this(Color.white);
    }
    
    public DialBackground(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;
    }
    
    public void setGradientPaintTransformer(final GradientPaintTransformer t) {
        if (t == null) {
            throw new IllegalArgumentException("Null 't' argument.");
        }
        this.gradientPaintTransformer = t;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public boolean isClippedToWindow() {
        return true;
    }
    
    public void draw(final Graphics2D g2, final DialPlot plot, final Rectangle2D frame, final Rectangle2D view) {
        Paint p = this.paint;
        if (p instanceof GradientPaint) {
            p = this.gradientPaintTransformer.transform((GradientPaint)p, view);
        }
        g2.setPaint(p);
        g2.fill(view);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DialBackground)) {
            return false;
        }
        final DialBackground that = (DialBackground)obj;
        return PaintUtilities.equal(this.paint, that.paint) && this.gradientPaintTransformer.equals(that.gradientPaintTransformer);
    }
    
    public int hashCode() {
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
        result = 37 * result + this.gradientPaintTransformer.hashCode();
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
    }
}
