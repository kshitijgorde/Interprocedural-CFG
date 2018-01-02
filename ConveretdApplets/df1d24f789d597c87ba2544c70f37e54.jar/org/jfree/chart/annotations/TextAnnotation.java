// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.ui.TextAnchor;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public class TextAnnotation implements Serializable
{
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
        this.text = text;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        this.paint = paint;
    }
    
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }
    
    public void setTextAnchor(final TextAnchor anchor) {
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
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof TextAnnotation) {
            final TextAnnotation ta = (TextAnnotation)object;
            final boolean b0 = ObjectUtils.equal(this.text, ta.getText());
            final boolean b2 = ObjectUtils.equal(this.font, ta.getFont());
            final boolean b3 = ObjectUtils.equal(this.paint, ta.getPaint());
            final boolean b4 = ObjectUtils.equal(this.textAnchor, ta.getTextAnchor());
            final boolean b5 = ObjectUtils.equal(this.rotationAnchor, ta.getRotationAnchor());
            final boolean b6 = this.rotationAngle == ta.getRotationAngle();
            return b0 && b2 && b3 && b4 && b5 && b6;
        }
        return false;
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
