// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.needle;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;

public class LineNeedle extends MeterNeedle implements Serializable
{
    protected void drawNeedle(final Graphics2D g2, final Rectangle2D plotArea, final Point2D rotate, final double angle) {
        final Line2D shape = new Line2D.Double();
        final double x = plotArea.getMinX() + plotArea.getWidth() / 2.0;
        shape.setLine(x, plotArea.getMinY(), x, plotArea.getMaxY());
        Shape s = shape;
        if (rotate != null && angle != 0.0) {
            this.getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
            s = this.getTransform().createTransformedShape(s);
        }
        this.defaultDisplay(g2, s);
    }
    
    public boolean equals(final Object object) {
        return object != null && (object == this || (super.equals(object) && object instanceof LineNeedle));
    }
}
