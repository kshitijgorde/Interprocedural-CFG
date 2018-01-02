// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.text;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.Size2D;
import org.jfree.ui.RectangleAnchor;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.jfree.ui.Spacer;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;

public class TextBox implements Serializable
{
    private transient Paint outlinePaint;
    private transient Stroke outlineStroke;
    private Spacer interiorGap;
    private transient Paint backgroundPaint;
    private transient Paint shadowPaint;
    private double shadowXOffset;
    private double shadowYOffset;
    private TextBlock textBlock;
    
    public TextBox() {
        this((TextBlock)null);
    }
    
    public TextBox(final String s) {
        this((TextBlock)null);
        if (s != null) {
            (this.textBlock = new TextBlock()).addLine(s, new Font("SansSerif", 0, 10), Color.black);
        }
    }
    
    public TextBox(final TextBlock textBlock) {
        this.shadowXOffset = 2.0;
        this.shadowYOffset = 2.0;
        this.outlinePaint = Color.black;
        this.outlineStroke = new BasicStroke(1.0f);
        this.interiorGap = new Spacer(1, 3.0, 1.0, 3.0, 1.0);
        this.backgroundPaint = new Color(255, 255, 192);
        this.shadowPaint = Color.gray;
        this.shadowXOffset = 2.0;
        this.shadowYOffset = 2.0;
        this.textBlock = textBlock;
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public void setOutlinePaint(final Paint outlinePaint) {
        this.outlinePaint = outlinePaint;
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public void setOutlineStroke(final Stroke outlineStroke) {
        this.outlineStroke = outlineStroke;
    }
    
    public Spacer getInteriorGap() {
        return this.interiorGap;
    }
    
    public void setInteriorGap(final Spacer interiorGap) {
        this.interiorGap = interiorGap;
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
    }
    
    public Paint getShadowPaint() {
        return this.shadowPaint;
    }
    
    public void setShadowPaint(final Paint shadowPaint) {
        this.shadowPaint = shadowPaint;
    }
    
    public double getShadowXOffset() {
        return this.shadowXOffset;
    }
    
    public void setShadowXOffset(final double shadowXOffset) {
        this.shadowXOffset = shadowXOffset;
    }
    
    public double getShadowYOffset() {
        return this.shadowYOffset;
    }
    
    public void setShadowYOffset(final double shadowYOffset) {
        this.shadowYOffset = shadowYOffset;
    }
    
    public TextBlock getTextBlock() {
        return this.textBlock;
    }
    
    public void setTextBlock(final TextBlock textBlock) {
        this.textBlock = textBlock;
    }
    
    public void draw(final Graphics2D graphics2D, final float n, final float n2, final RectangleAnchor rectangleAnchor) {
        final Size2D calculateDimensions = this.textBlock.calculateDimensions(graphics2D);
        final Rectangle2D rectangle = RectangleAnchor.createRectangle(new Size2D(this.interiorGap.getAdjustedWidth(calculateDimensions.getWidth()), this.interiorGap.getAdjustedHeight(calculateDimensions.getHeight())), n, n2, rectangleAnchor);
        if (this.shadowPaint != null) {
            final Rectangle2D.Double double1 = new Rectangle2D.Double(rectangle.getX() + this.shadowXOffset, rectangle.getY() + this.shadowYOffset, rectangle.getWidth(), rectangle.getHeight());
            graphics2D.setPaint(this.shadowPaint);
            graphics2D.fill(double1);
        }
        if (this.backgroundPaint != null) {
            graphics2D.setPaint(this.backgroundPaint);
            graphics2D.fill(rectangle);
        }
        if (this.outlinePaint != null && this.outlineStroke != null) {
            graphics2D.setPaint(this.outlinePaint);
            graphics2D.setStroke(this.outlineStroke);
            graphics2D.draw(rectangle);
        }
        this.textBlock.draw(graphics2D, (float)rectangle.getCenterX(), (float)rectangle.getCenterY(), TextBlockAnchor.CENTER);
    }
    
    public double getHeight(final Graphics2D graphics2D) {
        return this.interiorGap.getAdjustedHeight(this.textBlock.calculateDimensions(graphics2D).getHeight());
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof TextBox) {
            final TextBox textBox = (TextBox)o;
            final boolean equal = ObjectUtils.equal(this.outlinePaint, textBox.outlinePaint);
            final boolean equal2 = ObjectUtils.equal(this.outlineStroke, textBox.outlineStroke);
            final boolean equal3 = ObjectUtils.equal(this.interiorGap, textBox.interiorGap);
            final boolean equal4 = ObjectUtils.equal(this.backgroundPaint, textBox.backgroundPaint);
            final boolean equal5 = ObjectUtils.equal(this.shadowPaint, textBox.shadowPaint);
            final boolean b = this.shadowXOffset == textBox.shadowXOffset;
            final boolean b2 = this.shadowYOffset == textBox.shadowYOffset;
            final boolean equal6 = ObjectUtils.equal(this.textBlock, textBox.textBlock);
            return equal && equal2 && equal3 && equal4 && equal5 && b && b2 && equal6;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        SerialUtilities.writePaint(this.outlinePaint, objectOutputStream);
        SerialUtilities.writeStroke(this.outlineStroke, objectOutputStream);
        SerialUtilities.writePaint(this.backgroundPaint, objectOutputStream);
        SerialUtilities.writePaint(this.shadowPaint, objectOutputStream);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.outlinePaint = SerialUtilities.readPaint(objectInputStream);
        this.outlineStroke = SerialUtilities.readStroke(objectInputStream);
        this.backgroundPaint = SerialUtilities.readPaint(objectInputStream);
        this.shadowPaint = SerialUtilities.readPaint(objectInputStream);
    }
}
