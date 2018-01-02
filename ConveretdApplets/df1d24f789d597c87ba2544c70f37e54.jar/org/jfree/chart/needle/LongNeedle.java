// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.needle;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;

public class LongNeedle extends MeterNeedle implements Serializable
{
    public LongNeedle() {
        this.setRotateY(0.8);
    }
    
    protected void drawNeedle(final Graphics2D g2, final Rectangle2D plotArea, final Point2D rotate, final double angle) {
        final GeneralPath shape1 = new GeneralPath();
        final GeneralPath shape2 = new GeneralPath();
        final GeneralPath shape3 = new GeneralPath();
        final float minX = (float)plotArea.getMinX();
        final float minY = (float)plotArea.getMinY();
        final float maxX = (float)plotArea.getMaxX();
        final float maxY = (float)plotArea.getMaxY();
        final float midX = (float)(minX + plotArea.getWidth() * 0.5);
        final float midY = (float)(minY + plotArea.getHeight() * 0.8);
        float y = maxY - 2.0f * (maxY - midY);
        if (y < minY) {
            y = minY;
        }
        shape1.moveTo(minX, midY);
        shape1.lineTo(midX, minY);
        shape1.lineTo(midX, y);
        shape1.closePath();
        shape2.moveTo(maxX, midY);
        shape2.lineTo(midX, minY);
        shape2.lineTo(midX, y);
        shape2.closePath();
        shape3.moveTo(minX, midY);
        shape3.lineTo(midX, maxY);
        shape3.lineTo(maxX, midY);
        shape3.lineTo(midX, y);
        shape3.closePath();
        Shape s1 = shape1;
        Shape s2 = shape2;
        Shape s3 = shape3;
        if (rotate != null && angle != 0.0) {
            this.getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
            s1 = shape1.createTransformedShape(LongNeedle.transform);
            s2 = shape2.createTransformedShape(LongNeedle.transform);
            s3 = shape3.createTransformedShape(LongNeedle.transform);
        }
        if (this.getHighlightPaint() != null) {
            g2.setPaint(this.getHighlightPaint());
            g2.fill(s3);
        }
        if (this.getFillPaint() != null) {
            g2.setPaint(this.getFillPaint());
            g2.fill(s1);
            g2.fill(s2);
        }
        if (this.getOutlinePaint() != null) {
            g2.setStroke(this.getOutlineStroke());
            g2.setPaint(this.getOutlinePaint());
            g2.draw(s1);
            g2.draw(s2);
            g2.draw(s3);
        }
    }
    
    public boolean equals(final Object object) {
        return object != null && (object == this || (super.equals(object) && object instanceof LongNeedle));
    }
}
