// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Arrays;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.Area;
import java.awt.geom.RectangularShape;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.awt.Shape;

public abstract class ShapeUtils
{
    private static final float SQRT2;
    
    public static Shape rotateShape(final Shape shape, final double n, final float n2, final float n3) {
        if (shape == null) {
            return null;
        }
        return AffineTransform.getRotateInstance(n, n2, n3).createTransformedShape(shape);
    }
    
    public static Shape clone(final Shape shape) {
        Shape shape2 = null;
        if (shape instanceof Line2D) {
            shape2 = (Shape)((Line2D)shape).clone();
        }
        else if (shape instanceof RectangularShape) {
            shape2 = (Shape)((RectangularShape)shape).clone();
        }
        else if (shape instanceof Area) {
            shape2 = (Shape)((Area)shape).clone();
        }
        else if (shape instanceof GeneralPath) {
            shape2 = (Shape)((GeneralPath)shape).clone();
        }
        return shape2;
    }
    
    public static boolean equal(final Polygon polygon, final Polygon polygon2) {
        if (polygon == null) {
            return polygon2 == null;
        }
        return polygon2 != null && polygon.npoints == polygon2.npoints && Arrays.equals(polygon.xpoints, polygon2.xpoints) && Arrays.equals(polygon.ypoints, polygon2.ypoints);
    }
    
    public static Shape translateShape(final Shape shape, final double n, final double n2) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        return AffineTransform.getTranslateInstance(n, n2).createTransformedShape(shape);
    }
    
    public static Shape createDiagonalCross(final float n, final float n2) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(-n - n2, -n + n2);
        generalPath.lineTo(-n + n2, -n - n2);
        generalPath.lineTo(0.0f, -n2 * ShapeUtils.SQRT2);
        generalPath.lineTo(n - n2, -n - n2);
        generalPath.lineTo(n + n2, -n + n2);
        generalPath.lineTo(n2 * ShapeUtils.SQRT2, 0.0f);
        generalPath.lineTo(n + n2, n - n2);
        generalPath.lineTo(n - n2, n + n2);
        generalPath.lineTo(0.0f, n2 * ShapeUtils.SQRT2);
        generalPath.lineTo(-n + n2, n + n2);
        generalPath.lineTo(-n - n2, n - n2);
        generalPath.lineTo(-n2 * ShapeUtils.SQRT2, 0.0f);
        generalPath.closePath();
        return generalPath;
    }
    
    public static Shape createRegularCross(final float n, final float n2) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(-n, n2);
        generalPath.lineTo(-n2, n2);
        generalPath.lineTo(-n2, n);
        generalPath.lineTo(n2, n);
        generalPath.lineTo(n2, n2);
        generalPath.lineTo(n, n2);
        generalPath.lineTo(n, -n2);
        generalPath.lineTo(n2, -n2);
        generalPath.lineTo(n2, -n);
        generalPath.lineTo(-n2, -n);
        generalPath.lineTo(-n2, -n2);
        generalPath.lineTo(-n, -n2);
        generalPath.closePath();
        return generalPath;
    }
    
    public static Shape createDiamond(final float n) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(0.0f, -n);
        generalPath.lineTo(n, 0.0f);
        generalPath.lineTo(0.0f, n);
        generalPath.lineTo(-n, 0.0f);
        generalPath.closePath();
        return generalPath;
    }
    
    public static Shape createUpTriangle(final float n) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(0.0f, -n);
        generalPath.lineTo(n, n);
        generalPath.lineTo(-n, n);
        generalPath.closePath();
        return generalPath;
    }
    
    public static Shape createDownTriangle(final float n) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(0.0f, n);
        generalPath.lineTo(n, -n);
        generalPath.lineTo(-n, -n);
        generalPath.closePath();
        return generalPath;
    }
    
    static {
        SQRT2 = (float)Math.pow(2.0, 0.5);
    }
}
