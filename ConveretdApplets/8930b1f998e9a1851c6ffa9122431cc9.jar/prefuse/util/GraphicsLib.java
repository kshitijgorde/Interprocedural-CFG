// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.awt.BasicStroke;
import java.awt.Shape;
import prefuse.visual.VisualItem;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class GraphicsLib
{
    public static final int NO_INTERSECTION = 0;
    public static final int COINCIDENT = -1;
    public static final int PARALLEL = -2;
    
    public static int intersectLineLine(final Line2D line2D, final Line2D line2D2, final Point2D point2D) {
        return intersectLineLine(line2D.getX1(), line2D.getY1(), line2D.getX2(), line2D.getY2(), line2D2.getX1(), line2D2.getY1(), line2D2.getX2(), line2D2.getY2(), point2D);
    }
    
    public static int intersectLineLine(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final Point2D point2D) {
        final double n9 = (n7 - n5) * (n2 - n6) - (n8 - n6) * (n - n5);
        final double n10 = (n3 - n) * (n2 - n6) - (n4 - n2) * (n - n5);
        final double n11 = (n8 - n6) * (n3 - n) - (n7 - n5) * (n4 - n2);
        if (n11 == 0.0) {
            return (n9 == 0.0 || n10 == 0.0) ? -1 : -2;
        }
        final double n12 = n9 / n11;
        final double n13 = n10 / n11;
        if (0.0 <= n12 && n12 <= 1.0 && 0.0 <= n13 && n13 <= 1.0) {
            point2D.setLocation(n + n12 * (n3 - n), n2 + n12 * (n4 - n2));
            return 1;
        }
        return 0;
    }
    
    public static int intersectLineRectangle(final Point2D point2D, final Point2D point2D2, final Rectangle2D rectangle2D, final Point2D[] array) {
        final double x = point2D.getX();
        final double y = point2D.getY();
        final double x2 = point2D2.getX();
        final double y2 = point2D2.getY();
        final double maxX = rectangle2D.getMaxX();
        final double maxY = rectangle2D.getMaxY();
        final double minX = rectangle2D.getMinX();
        final double minY = rectangle2D.getMinY();
        if (array[0] == null) {
            array[0] = new Point2D.Double();
        }
        if (array[1] == null) {
            array[1] = new Point2D.Double();
        }
        int n = 0;
        if (intersectLineLine(minX, minY, maxX, minY, x, y, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (intersectLineLine(maxX, minY, maxX, maxY, x, y, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (n == 2) {
            return n;
        }
        if (intersectLineLine(maxX, maxY, minX, maxY, x, y, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (n == 2) {
            return n;
        }
        if (intersectLineLine(minX, maxY, minX, minY, x, y, x2, y2, array[n]) > 0) {
            ++n;
        }
        return n;
    }
    
    public static int intersectLineRectangle(final Line2D line2D, final Rectangle2D rectangle2D, final Point2D[] array) {
        final double x1 = line2D.getX1();
        final double y1 = line2D.getY1();
        final double x2 = line2D.getX2();
        final double y2 = line2D.getY2();
        final double maxX = rectangle2D.getMaxX();
        final double maxY = rectangle2D.getMaxY();
        final double minX = rectangle2D.getMinX();
        final double minY = rectangle2D.getMinY();
        if (array[0] == null) {
            array[0] = new Point2D.Double();
        }
        if (array[1] == null) {
            array[1] = new Point2D.Double();
        }
        int n = 0;
        if (intersectLineLine(minX, minY, maxX, minY, x1, y1, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (intersectLineLine(maxX, minY, maxX, maxY, x1, y1, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (n == 2) {
            return n;
        }
        if (intersectLineLine(maxX, maxY, minX, maxY, x1, y1, x2, y2, array[n]) > 0) {
            ++n;
        }
        if (n == 2) {
            return n;
        }
        if (intersectLineLine(minX, maxY, minX, minY, x1, y1, x2, y2, array[n]) > 0) {
            ++n;
        }
        return n;
    }
    
    public static double[] convexHull(final double[] array, final int n) {
        if (n < 6) {
            throw new IllegalArgumentException("Input must have at least 3 points");
        }
        final int n2 = n / 2 - 1;
        return convexHull(array, n, new float[n2], new int[n2], new int[n / 2]);
    }
    
    public static double[] convexHull(final double[] array, final int n, final float[] array2, final int[] array3, final int[] array4) {
        final int n2 = n / 2 - 1;
        if (n < 6) {
            throw new IllegalArgumentException("Input must have at least 3 points");
        }
        if (array2.length < n2 || array3.length < n2 || array4.length < n / 2) {
            throw new IllegalArgumentException("Pre-allocated data structure too small");
        }
        int n3 = 0;
        for (int i = 2; i < n; i += 2) {
            if (array[i + 1] < array[n3 + 1]) {
                n3 = i;
            }
            else if (array[i + 1] == array[n3 + 1]) {
                n3 = ((array[i] < array[n3]) ? i : n3);
            }
        }
        int j = 0;
        int n4 = 0;
        while (j < n) {
            if (j != n3) {
                array2[n4] = (float)Math.atan2(array[j + 1] - array[n3 + 1], array[j] - array[n3]);
                array3[n4++] = j;
            }
            j += 2;
        }
        ArrayLib.sort(array2, array3, n2);
        float n5 = array2[0];
        int n6 = 0;
        int n7 = array3[0];
        for (int k = 1; k < n2; ++k) {
            final int n8 = array3[k];
            if (n5 == array2[k]) {
                final double n9 = array[n7] - array[n3];
                final double n10 = array[n7 + 1] - array[n3 + 1];
                final double n11 = array[n8] - array[n3];
                final double n12 = array[n8 + 1] - array[n3 + 1];
                if (n9 * n9 + n10 * n10 >= n11 * n11 + n12 * n12) {
                    array3[k] = -1;
                }
                else {
                    array3[n6] = -1;
                    n5 = array2[k];
                    n6 = k;
                    n7 = n8;
                }
            }
            else {
                n5 = array2[k];
                n6 = k;
                n7 = n8;
            }
        }
        int n13 = 0;
        array4[n13++] = n3;
        int l = 0;
        int n14 = 0;
        while (n14 < 2) {
            if (array3[l] != -1) {
                array4[n13++] = array3[l];
                ++n14;
            }
            ++l;
        }
        while (l < n2) {
            if (array3[l] != -1) {
                while (isNonLeft(n3, array4[n13 - 2], array4[n13 - 1], array3[l], array)) {
                    --n13;
                }
                array4[n13++] = array3[l];
            }
            ++l;
        }
        final double[] array5 = new double[2 * n13];
        for (int n15 = 0; n15 < n13; ++n15) {
            array5[2 * n15] = array[array4[n15]];
            array5[2 * n15 + 1] = array[array4[n15] + 1];
        }
        return array5;
    }
    
    private static boolean isNonLeft(final int n, final int n2, final int n3, final int n4, final double[] array) {
        final double sqrt = Math.sqrt(Math.pow(array[n3 + 1] - array[n2 + 1], 2.0) + Math.pow(array[n3] - array[n2], 2.0));
        final double sqrt2 = Math.sqrt(Math.pow(array[n4 + 1] - array[n3 + 1], 2.0) + Math.pow(array[n4] - array[n3], 2.0));
        final double sqrt3 = Math.sqrt(Math.pow(array[n4 + 1] - array[n + 1], 2.0) + Math.pow(array[n4] - array[n], 2.0));
        final double sqrt4 = Math.sqrt(Math.pow(array[n2 + 1] - array[n + 1], 2.0) + Math.pow(array[n2] - array[n], 2.0));
        final double sqrt5 = Math.sqrt(Math.pow(array[n3 + 1] - array[n + 1], 2.0) + Math.pow(array[n3] - array[n], 2.0));
        return 3.141592653589793 - Math.acos((sqrt2 * sqrt2 + sqrt5 * sqrt5 - sqrt3 * sqrt3) / (2.0 * sqrt2 * sqrt5)) - Math.acos((sqrt5 * sqrt5 + sqrt * sqrt - sqrt4 * sqrt4) / (2.0 * sqrt5 * sqrt)) <= 0.0;
    }
    
    public static float[] centroid(final float[] array, final int n) {
        final float[] array2 = { 0.0f, 0.0f };
        for (int i = 0; i < n; i += 2) {
            final float[] array3 = array2;
            final int n2 = 0;
            array3[n2] += array[i];
            final float[] array4 = array2;
            final int n3 = 1;
            array4[n3] += array[i + 1];
        }
        final float[] array5 = array2;
        final int n4 = 0;
        array5[n4] /= n / 2;
        final float[] array6 = array2;
        final int n5 = 1;
        array6[n5] /= n / 2;
        return array2;
    }
    
    public static void growPolygon(final float[] array, final int n, final float n2) {
        final float[] centroid = centroid(array, n);
        for (int i = 0; i < n; i += 2) {
            final float n3 = array[i] - centroid[0];
            final float n4 = array[i + 1] - centroid[1];
            final float n5 = (float)Math.sqrt(n3 * n3 + n4 * n4);
            final int n6 = i;
            array[n6] += n2 * n3 / n5;
            final int n7 = i + 1;
            array[n7] += n2 * n4 / n5;
        }
    }
    
    public static GeneralPath cardinalSpline(final float[] array, final float n, final boolean b) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(array[0], array[1]);
        return cardinalSpline(generalPath, array, n, b, 0.0f, 0.0f);
    }
    
    public static GeneralPath cardinalSpline(final float[] array, final int n, final int n2, final float n3, final boolean b) {
        final GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(array[n], array[n + 1]);
        return cardinalSpline(generalPath, array, n, n2, n3, b, 0.0f, 0.0f);
    }
    
    public static GeneralPath cardinalSpline(final GeneralPath generalPath, final float[] array, final float n, final boolean b, final float n2, final float n3) {
        int n4;
        for (n4 = 0; n4 < array.length && !Float.isNaN(array[n4]); ++n4) {}
        return cardinalSpline(generalPath, array, 0, n4 / 2, n, b, n2, n3);
    }
    
    public static GeneralPath cardinalSpline(final GeneralPath generalPath, final float[] array, final int n, final int n2, final float n3, final boolean b, final float n4, final float n5) {
        final int n6 = 2 * n2;
        final int n7 = n + n6;
        if (n6 < 6) {
            throw new IllegalArgumentException("To create spline requires at least 3 points");
        }
        float n8;
        float n9;
        if (b) {
            n8 = array[n + 2] - array[n7 - 2];
            n9 = array[n + 3] - array[n7 - 1];
        }
        else {
            n8 = array[n + 4] - array[n];
            n9 = array[n + 5] - array[n + 1];
        }
        int i;
        for (i = n + 2; i < n7 - 2; i += 2) {
            final float n10 = n8;
            final float n11 = n9;
            n8 = array[i + 2] - array[i - 2];
            n9 = array[i + 3] - array[i - 1];
            generalPath.curveTo(n4 + array[i - 2] + n3 * n10, n5 + array[i - 1] + n3 * n11, n4 + array[i] - n3 * n8, n5 + array[i + 1] - n3 * n9, n4 + array[i], n5 + array[i + 1]);
        }
        if (b) {
            final float n12 = n8;
            final float n13 = n9;
            final float n14 = array[n] - array[i - 2];
            final float n15 = array[n + 1] - array[i - 1];
            generalPath.curveTo(n4 + array[i - 2] + n3 * n12, n5 + array[i - 1] + n3 * n13, n4 + array[i] - n3 * n14, n5 + array[i + 1] - n3 * n15, n4 + array[i], n5 + array[i + 1]);
            generalPath.curveTo(n4 + array[n7 - 2] + n3 * n14, n5 + array[n7 - 1] + n3 * n15, n4 + array[0] - n3 * (array[n + 2] - array[n7 - 2]), n5 + array[1] - n3 * (array[n + 3] - array[n7 - 1]), n4 + array[0], n5 + array[1]);
            generalPath.closePath();
        }
        else {
            generalPath.curveTo(n4 + array[i - 2] + n3 * n8, n5 + array[i - 1] + n3 * n9, n4 + array[i] - n3 * n8, n5 + array[i + 1] - n3 * n9, n4 + array[i], n5 + array[i + 1]);
        }
        return generalPath;
    }
    
    public static GeneralPath stackSpline(final GeneralPath generalPath, final float[] array, final float n, final float n2, final boolean b, final float n3, final float n4) {
        int n5;
        for (n5 = 0; n5 < array.length && !Float.isNaN(array[n5]); ++n5) {}
        return stackSpline(generalPath, array, 0, n5 / 2, n, n2, b, n3, n4);
    }
    
    public static GeneralPath stackSpline(final GeneralPath generalPath, final float[] array, final int n, final int n2, final float n3, final float n4, final boolean b, final float n5, final float n6) {
        final int n7 = 2 * n2;
        final int n8 = n + n7;
        if (n7 < 6) {
            throw new IllegalArgumentException("To create spline requires at least 3 points");
        }
        float n9;
        float n10;
        if (b) {
            n9 = array[n + 2] - array[n8 - 2];
            n10 = array[n + 3] - array[n8 - 1];
        }
        else {
            n9 = array[n + 4] - array[n];
            n10 = array[n + 5] - array[n + 1];
        }
        int i;
        for (i = n + 2; i < n8 - 2; i += 2) {
            final float n11 = n9;
            final float n12 = n10;
            n9 = array[i + 2] - array[i - 2];
            n10 = array[i + 3] - array[i - 1];
            if (Math.abs(array[i] - array[i - 2]) < n3 || Math.abs(array[i + 1] - array[i - 1]) < n3) {
                generalPath.lineTo(n5 + array[i], n6 + array[i + 1]);
            }
            else {
                generalPath.curveTo(n5 + array[i - 2] + n4 * n11, n6 + array[i - 1] + n4 * n12, n5 + array[i] - n4 * n9, n6 + array[i + 1] - n4 * n10, n5 + array[i], n6 + array[i + 1]);
            }
        }
        final float n13 = n9;
        final float n14 = n10;
        final float n15 = array[n] - array[i - 2];
        final float n16 = array[n + 1] - array[i - 1];
        if (Math.abs(array[i] - array[i - 2]) < n3 || Math.abs(array[i + 1] - array[i - 1]) < n3) {
            generalPath.lineTo(n5 + array[i], n6 + array[i + 1]);
        }
        else {
            generalPath.curveTo(n5 + array[i - 2] + n4 * n13, n6 + array[i - 1] + n4 * n14, n5 + array[i] - n4 * n15, n6 + array[i + 1] - n4 * n16, n5 + array[i], n6 + array[i + 1]);
        }
        if (b) {
            if (Math.abs(array[n8 - 2] - array[0]) < n3 || Math.abs(array[n8 - 1] - array[1]) < n3) {
                generalPath.lineTo(n5 + array[0], n6 + array[1]);
            }
            else {
                generalPath.curveTo(n5 + array[n8 - 2] + n4 * n15, n6 + array[n8 - 1] + n4 * n16, n5 + array[0] - n4 * (array[n + 2] - array[n8 - 2]), n6 + array[1] - n4 * (array[n + 3] - array[n8 - 1]), n5 + array[0], n6 + array[1]);
            }
            generalPath.closePath();
        }
        return generalPath;
    }
    
    public static void expand(final Rectangle2D rectangle2D, final double n) {
        rectangle2D.setRect(rectangle2D.getX() - n, rectangle2D.getY() - n, rectangle2D.getWidth() + 2.0 * n, rectangle2D.getHeight() + 2.0 * n);
    }
    
    public static void setBounds(final VisualItem visualItem, final Shape shape, final BasicStroke basicStroke) {
        double n;
        double n2;
        double n3;
        double n4;
        if (shape instanceof RectangularShape) {
            final RectangularShape rectangularShape = (RectangularShape)shape;
            n = rectangularShape.getX();
            n2 = rectangularShape.getY();
            n3 = rectangularShape.getWidth();
            n4 = rectangularShape.getHeight();
        }
        else if (shape instanceof Line2D) {
            final Line2D line2D = (Line2D)shape;
            n = line2D.getX1();
            n2 = line2D.getY1();
            final double x2 = line2D.getX2();
            final double y2 = line2D.getY2();
            if (x2 < n) {
                final double n5 = n;
                n = x2;
                n3 = n5 - n;
            }
            else {
                n3 = x2 - n;
            }
            if (y2 < n2) {
                final double n6 = n2;
                n2 = y2;
                n4 = n6 - n2;
            }
            else {
                n4 = y2 - n2;
            }
        }
        else {
            final Rectangle2D bounds2D = shape.getBounds2D();
            n = bounds2D.getX();
            n2 = bounds2D.getY();
            n3 = bounds2D.getWidth();
            n4 = bounds2D.getHeight();
        }
        final double n7;
        if (basicStroke != null && (n7 = basicStroke.getLineWidth()) > 1.0) {
            final double n8 = n7 / 2.0;
            n -= n8;
            n2 -= n8;
            n3 += n7;
            n4 += n7;
        }
        visualItem.setBounds(n, n2, n3, n4);
    }
    
    public static void paint(final Graphics2D graphics2D, final VisualItem visualItem, final Shape shape, final BasicStroke stroke, final int n) {
        if (n == 0) {
            return;
        }
        final Color color = ColorLib.getColor(visualItem.getStrokeColor());
        final Color color2 = ColorLib.getColor(visualItem.getFillColor());
        final boolean b = (n == 1 || n == 3) && color.getAlpha() != 0;
        final boolean b2 = (n == 2 || n == 3) && color2.getAlpha() != 0;
        if (!b && !b2) {
            return;
        }
        Stroke stroke2 = null;
        if (b) {
            stroke2 = graphics2D.getStroke();
            graphics2D.setStroke(stroke);
        }
        final AffineTransform transform = graphics2D.getTransform();
        if (Math.max(transform.getScaleX(), transform.getScaleY()) > 1.5) {
            if (b2) {
                graphics2D.setPaint(color2);
                graphics2D.fill(shape);
            }
            if (b) {
                graphics2D.setPaint(color);
                graphics2D.draw(shape);
            }
        }
        else if (shape instanceof RectangularShape) {
            final RectangularShape rectangularShape = (RectangularShape)shape;
            final double x = rectangularShape.getX();
            final double width = rectangularShape.getWidth();
            final double y = rectangularShape.getY();
            final double height = rectangularShape.getHeight();
            final int n2 = (int)x;
            final int n3 = (int)y;
            final int n4 = (int)(width + x - n2);
            final int n5 = (int)(height + y - n3);
            if (shape instanceof Rectangle2D) {
                if (b2) {
                    graphics2D.setPaint(color2);
                    graphics2D.fillRect(n2, n3, n4, n5);
                }
                if (b) {
                    graphics2D.setPaint(color);
                    graphics2D.drawRect(n2, n3, n4, n5);
                }
            }
            else if (shape instanceof RoundRectangle2D) {
                final RoundRectangle2D roundRectangle2D = (RoundRectangle2D)shape;
                final int n6 = (int)roundRectangle2D.getArcWidth();
                final int n7 = (int)roundRectangle2D.getArcHeight();
                if (b2) {
                    graphics2D.setPaint(color2);
                    graphics2D.fillRoundRect(n2, n3, n4, n5, n6, n7);
                }
                if (b) {
                    graphics2D.setPaint(color);
                    graphics2D.drawRoundRect(n2, n3, n4, n5, n6, n7);
                }
            }
            else if (shape instanceof Ellipse2D) {
                if (b2) {
                    graphics2D.setPaint(color2);
                    graphics2D.fillOval(n2, n3, n4, n5);
                }
                if (b) {
                    graphics2D.setPaint(color);
                    graphics2D.drawOval(n2, n3, n4, n5);
                }
            }
            else {
                if (b2) {
                    graphics2D.setPaint(color2);
                    graphics2D.fill(shape);
                }
                if (b) {
                    graphics2D.setPaint(color);
                    graphics2D.draw(shape);
                }
            }
        }
        else if (shape instanceof Line2D) {
            if (b) {
                final Line2D line2D = (Line2D)shape;
                final int n8 = (int)(line2D.getX1() + 0.5);
                final int n9 = (int)(line2D.getY1() + 0.5);
                final int n10 = (int)(line2D.getX2() + 0.5);
                final int n11 = (int)(line2D.getY2() + 0.5);
                graphics2D.setPaint(color);
                graphics2D.drawLine(n8, n9, n10, n11);
            }
        }
        else {
            if (b2) {
                graphics2D.setPaint(color2);
                graphics2D.fill(shape);
            }
            if (b) {
                graphics2D.setPaint(color);
                graphics2D.draw(shape);
            }
        }
        if (b) {
            graphics2D.setStroke(stroke2);
        }
    }
}
