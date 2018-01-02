// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

public class DefaultShapeFactory implements ShapeFactory
{
    public Shape getShape(final int series, final int item, final double x, final double y, final double scale) {
        if (series == 0) {
            return new Rectangle2D.Double(x - 0.5 * scale, y - 0.5 * scale, scale, scale);
        }
        return new Ellipse2D.Double(x - 0.5 * scale, y - 0.5 * scale, scale, scale);
    }
    
    public Shape getShape(final int series, final Object category, final double x, final double y, final double scale) {
        return new Ellipse2D.Double(x - 0.5 * scale, y - 0.5 * scale, scale, scale);
    }
}
