// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.chart.HashUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.ui.TextAnchor;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public class TextAnnotation implements Serializable
{
    private static final long serialVersionUID = 7008912287533127432L;
    public static final Font DEFAULT_FONT;
    public static final Paint DEFAULT_PAINT;
    public static final TextAnchor DEFAULT_TEXT_ANCHOR;
    public static final TextAnchor DEFAULT_ROTATION_ANCHOR;
    public static final double DEFAULT_ROTATION_ANGLE = 0.0;
    private String text;
    private Font font;
    private transient Paint paint;
    private TextAnchor textAnchor;
    private TextAnchor rotationAnchor;
    private double rotationAngle;
    
    protected TextAnnotation(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        this.text = text;
        this.font = TextAnnotation.DEFAULT_FONT;
        this.paint = TextAnnotation.DEFAULT_PAINT;
        this.textAnchor = TextAnnotation.DEFAULT_TEXT_ANCHOR;
        this.rotationAnchor = TextAnnotation.DEFAULT_ROTATION_ANCHOR;
        this.rotationAngle = 0.0;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");
        }
        this.text = text;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.font = font;
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
    
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }
    
    public void setTextAnchor(final TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.textAnchor = anchor;
    }
    
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    public void setRotationAnchor(final TextAnchor anchor) {
        this.rotationAnchor = anchor;
    }
    
    public double getRotationAngle() {
        return this.rotationAngle;
    }
    
    public void setRotationAngle(final double angle) {
        this.rotationAngle = angle;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextAnnotation)) {
            return false;
        }
        final TextAnnotation that = (TextAnnotation)obj;
        return ObjectUtilities.equal(this.text, that.getText()) && ObjectUtilities.equal(this.font, that.getFont()) && PaintUtilities.equal(this.paint, that.getPaint()) && ObjectUtilities.equal(this.textAnchor, that.getTextAnchor()) && ObjectUtilities.equal(this.rotationAnchor, that.getRotationAnchor()) && this.rotationAngle == that.getRotationAngle();
    }
    
    public int hashCode() {
        int result = 193;
        result = 37 * result + this.font.hashCode();
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
        result = 37 * result + this.rotationAnchor.hashCode();
        final long temp = Double.doubleToLongBits(this.rotationAngle);
        result = 37 * result + (int)(temp ^ temp >>> 32);
        result = 37 * result + this.text.hashCode();
        result = 37 * result + this.textAnchor.hashCode();
        return result;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
    }
    
    static {
        DEFAULT_FONT = new Font("SansSerif", 0, 10);
        DEFAULT_PAINT = Color.black;
        DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
        DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
    }
}
