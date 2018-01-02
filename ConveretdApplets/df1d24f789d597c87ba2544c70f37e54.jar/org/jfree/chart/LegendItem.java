// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Shape;
import java.io.Serializable;

public class LegendItem implements Serializable
{
    private String label;
    private String description;
    private transient Shape shape;
    private boolean shapeFilled;
    private transient Paint paint;
    private transient Stroke stroke;
    private transient Paint outlinePaint;
    private transient Stroke outlineStroke;
    
    public LegendItem(final String label, final Paint paint) {
        this(label, label, new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0), true, paint, new BasicStroke(0.5f), Color.lightGray, new BasicStroke(0.5f));
    }
    
    public LegendItem(final String label, final String description, final Shape shape, final boolean shapeFilled, final Paint paint, final Stroke stroke, final Paint outlinePaint, final Stroke outlineStroke) {
        if (label == null) {
            throw new IllegalArgumentException("Null 'label' argument.");
        }
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        if (outlinePaint == null) {
            throw new IllegalArgumentException("Null 'outlinePaint' argument.");
        }
        if (outlineStroke == null) {
            throw new IllegalArgumentException("Null 'outlineStroke' argument.");
        }
        this.label = label;
        this.description = description;
        this.shape = shape;
        this.shapeFilled = shapeFilled;
        this.paint = paint;
        this.stroke = stroke;
        this.outlinePaint = outlinePaint;
        this.outlineStroke = outlineStroke;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public Shape getShape() {
        return this.shape;
    }
    
    public boolean isShapeFilled() {
        return this.shapeFilled;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LegendItem) {
            final LegendItem item = (LegendItem)obj;
            return this.label.equals(item.label) && ObjectUtils.equal(this.description, item.description) && ObjectUtils.equal(this.shape, item.shape) && this.shapeFilled == item.shapeFilled && this.stroke.equals(item.stroke) && this.paint.equals(item.paint) && this.outlineStroke.equals(item.outlineStroke) && this.outlinePaint.equals(item.outlinePaint);
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeShape(this.shape, stream);
        SerialUtilities.writeStroke(this.stroke, stream);
        SerialUtilities.writePaint(this.paint, stream);
        SerialUtilities.writeStroke(this.outlineStroke, stream);
        SerialUtilities.writePaint(this.outlinePaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.shape = SerialUtilities.readShape(stream);
        this.stroke = SerialUtilities.readStroke(stream);
        this.paint = SerialUtilities.readPaint(stream);
        this.outlineStroke = SerialUtilities.readStroke(stream);
        this.outlinePaint = SerialUtilities.readPaint(stream);
    }
    
    public LegendItem(final String label, final String description, final Shape shape, final Paint paint, final Paint outlinePaint, final Stroke stroke) {
        this(label, description, shape, true, paint, outlinePaint, stroke);
    }
    
    public LegendItem(final String label, final String description, final Shape shape, final boolean shapeFilled, final Paint paint, final Paint outlinePaint, final Stroke stroke) {
        this.label = label;
        this.description = description;
        this.shape = shape;
        this.shapeFilled = shapeFilled;
        this.paint = paint;
        this.outlinePaint = outlinePaint;
        this.stroke = stroke;
    }
}
