// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.needle;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;

public class ArrowNeedle extends MeterNeedle implements Serializable
{
    private boolean isArrowAtTop;
    
    public ArrowNeedle(final boolean isArrowAtTop) {
        this.isArrowAtTop = true;
        this.isArrowAtTop = isArrowAtTop;
    }
    
    protected void drawNeedle(final Graphics2D g2, final Rectangle2D plotArea, final Point2D rotate, final double angle) {
        final Line2D shape = new Line2D.Float();
        Shape d = null;
        final float x = (float)(plotArea.getMinX() + plotArea.getWidth() / 2.0);
        float minY = (float)plotArea.getMinY();
        final float maxY = (float)plotArea.getMaxY();
        shape.setLine(x, minY, x, maxY);
        final GeneralPath shape2 = new GeneralPath();
        if (this.isArrowAtTop) {
            shape2.moveTo(x, minY);
            minY += 4 * this.getSize();
        }
        else {
            shape2.moveTo(x, maxY);
            minY = maxY - 4 * this.getSize();
        }
        shape2.lineTo(x + this.getSize(), minY);
        shape2.lineTo(x - this.getSize(), minY);
        shape2.closePath();
        if (rotate != null && angle != 0.0) {
            this.getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
            d = this.getTransform().createTransformedShape(shape);
        }
        else {
            d = shape;
        }
        this.defaultDisplay(g2, d);
        if (rotate != null && angle != 0.0) {
            d = this.getTransform().createTransformedShape(shape2);
        }
        else {
            d = shape2;
        }
        this.defaultDisplay(g2, d);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (super.equals(object) && object instanceof ArrowNeedle) {
            final ArrowNeedle an = (ArrowNeedle)object;
            return this.isArrowAtTop == an.isArrowAtTop;
        }
        return false;
    }
}
