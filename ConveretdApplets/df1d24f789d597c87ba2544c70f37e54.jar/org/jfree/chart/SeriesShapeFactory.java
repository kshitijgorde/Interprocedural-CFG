// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

public class SeriesShapeFactory implements ShapeFactory
{
    private static final int SHAPE_COUNT = 11;
    
    public Shape getShape(final int series, final int item, final double x, final double y, final double scale) {
        return this.getShape(series, null, x, y, scale);
    }
    
    public Shape getShape(final int series, final Object category, final double x, final double y, final double scale) {
        final double delta = 0.5 * scale;
        final int index = series % 11;
        int[] xpoints = null;
        int[] ypoints = null;
        switch (index) {
            case 0: {
                return new Rectangle2D.Double(x - delta, y - delta, scale, scale);
            }
            case 1: {
                return new Ellipse2D.Double(x - delta, y - delta, scale, scale);
            }
            case 2: {
                xpoints = intArray(x, x + delta, x - delta);
                ypoints = intArray(y - delta, y + delta, y + delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            case 3: {
                xpoints = intArray(x, x + delta, x, x - delta);
                ypoints = intArray(y - delta, y, y + delta, y);
                return new Polygon(xpoints, ypoints, 4);
            }
            case 4: {
                return new Rectangle2D.Double(x - delta, y - delta / 2.0, scale, scale / 2.0);
            }
            case 5: {
                xpoints = intArray(x - delta, x + delta, x);
                ypoints = intArray(y - delta, y - delta, y + delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            case 6: {
                return new Ellipse2D.Double(x - delta, y - delta / 2.0, scale, scale / 2.0);
            }
            case 7: {
                xpoints = intArray(x - delta, x + delta, x - delta);
                ypoints = intArray(y - delta, y, y + delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            case 8: {
                return new Rectangle2D.Double(x - delta / 2.0, y - delta, scale / 2.0, scale);
            }
            case 9: {
                xpoints = intArray(x - delta, x + delta, x + delta);
                ypoints = intArray(y, y - delta, y + delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            default: {
                return new Ellipse2D.Double(x - delta / 2.0, y - delta, scale / 2.0, scale);
            }
        }
    }
    
    private static int[] intArray(final double a, final double b, final double c) {
        return new int[] { (int)a, (int)b, (int)c };
    }
    
    private static int[] intArray(final double a, final double b, final double c, final double d) {
        return new int[] { (int)a, (int)b, (int)c, (int)d };
    }
}
