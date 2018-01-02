// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.util.UnitType;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.ui.RectangleAnchor;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;

public abstract class Marker implements Serializable, Cloneable
{
    private transient Paint paint;
    private transient Stroke stroke;
    private transient Paint outlinePaint;
    private transient Stroke outlineStroke;
    private float alpha;
    private String label;
    private Font labelFont;
    private transient Paint labelPaint;
    private RectangleAnchor labelAnchor;
    private TextAnchor labelTextAnchor;
    private RectangleInsets labelOffset;
    
    public Marker() {
        this(Color.gray);
    }
    
    public Marker(final Paint paint) {
        this(paint, new BasicStroke(0.5f), Color.gray, new BasicStroke(0.5f), 0.8f);
    }
    
    public Marker(final Paint paint, final Stroke stroke, final Paint outlinePaint, final Stroke outlineStroke, final float alpha) {
        this.label = null;
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.paint = paint;
        this.stroke = stroke;
        this.outlinePaint = outlinePaint;
        this.outlineStroke = outlineStroke;
        this.alpha = alpha;
        this.labelFont = new Font("SansSerif", 0, 9);
        this.labelPaint = Color.black;
        this.labelAnchor = RectangleAnchor.TOP_LEFT;
        this.labelOffset = new RectangleInsets(UnitType.ABSOLUTE, 3.0, 3.0, 3.0, 3.0);
        this.labelTextAnchor = TextAnchor.CENTER;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void setStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.stroke = stroke;
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public void setOutlinePaint(final Paint paint) {
        this.outlinePaint = paint;
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public void setOutlineStroke(final Stroke stroke) {
        this.outlineStroke = stroke;
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public void setLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.labelFont = font;
    }
    
    public Paint getLabelPaint() {
        return this.labelPaint;
    }
    
    public void setLabelPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Marker.setLabelPaint(...): null not permitted.");
        }
        this.labelPaint = paint;
    }
    
    public RectangleAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    public void setLabelAnchor(final RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelAnchor = anchor;
    }
    
    public RectangleInsets getLabelOffset() {
        return this.labelOffset;
    }
    
    public void setLabelOffset(final RectangleInsets offset) {
        if (offset == null) {
            throw new IllegalArgumentException("Null 'offset' argument.");
        }
        this.labelOffset = offset;
    }
    
    public TextAnchor getLabelTextAnchor() {
        return this.labelTextAnchor;
    }
    
    public void setLabelTextAnchor(final TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelTextAnchor = anchor;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof Marker) {
            final Marker marker = (Marker)object;
            final boolean b0 = ObjectUtils.equal(this.paint, marker.paint);
            final boolean b2 = ObjectUtils.equal(this.stroke, marker.stroke);
            final boolean b3 = this.alpha == marker.alpha;
            final boolean b4 = ObjectUtils.equal(this.label, marker.label);
            final boolean b5 = ObjectUtils.equal(this.labelFont, marker.labelFont);
            final boolean b6 = ObjectUtils.equal(this.labelPaint, marker.labelPaint);
            final boolean b7 = this.labelAnchor == marker.labelAnchor;
            return b0 && b2 && b3 && b4 && b5 && b6 && b7;
        }
        return false;
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
