// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Hashtable;

public class NF12GraphicsUtil
{
    public static final int SOLID = 1;
    public static final int DOTTED = 2;
    public static final int DASHED = 3;
    public static final int DOTDASH = 4;
    public static final int DEFAULT_CAP = 1;
    public static final int DEFAULT_JOIN = 0;
    protected static Hashtable strokeCache;
    
    public static void drawLine(final Graphics graphics, final Point point, final Point point2) {
        drawLine(graphics, point, point2, 1);
    }
    
    public static void drawLine(final Graphics graphics, final Point point, final Point point2, final int n) {
        drawLine(graphics, point, point2, n, 1);
    }
    
    public static void drawLine(final Graphics graphics, final Point point, final Point point2, final int n, final int n2) {
        if (point == null || point2 == null) {
            return;
        }
        drawLine(graphics, point.x, point.y, point2.x, point2.y, n, n2);
    }
    
    public static void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        drawLine(graphics, n, n2, n3, n4, 1);
    }
    
    public static void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        drawLine(graphics, n, n2, n3, n4, n5, 1);
    }
    
    public static void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n6 == 0) {
            return;
        }
        Stroke stroke = null;
        if (n6 != 1 || n5 != 1) {
            stroke = getStroke(graphics);
            setStyledStroke(graphics, n5, n6);
        }
        if (graphics != null) {
            graphics.drawLine(n, n2, n3, n4);
        }
        if (n6 != 1 || n5 != 1) {
            setStroke(graphics, stroke);
        }
    }
    
    public static void drawPolyline(final Graphics graphics, final Polygon polygon) {
        drawPolyline(graphics, polygon, 1);
    }
    
    public static void drawPolyline(final Graphics graphics, final Polygon polygon, final int n) {
        drawPolyline(graphics, polygon, n, 1);
    }
    
    public static void drawPolyline(final Graphics graphics, final Polygon polygon, final int n, final int n2) {
        if (n2 == 0) {
            return;
        }
        final Stroke stroke = getStroke(graphics);
        setStyledStroke(graphics, n, n2);
        if (graphics != null && polygon != null) {
            graphics.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
        }
        setStroke(graphics, stroke);
    }
    
    public static void drawPolygon(final Graphics graphics, final Polygon polygon, final int n, final int n2) {
        if (n2 == 0) {
            return;
        }
        final Stroke stroke = getStroke(graphics);
        setStyledStroke(graphics, n, n2);
        if (graphics != null && polygon != null) {
            graphics.drawPolygon(polygon);
        }
        setStroke(graphics, stroke);
    }
    
    public static void setStroke(final Graphics graphics, final Stroke stroke) {
        if (graphics == null || !(graphics instanceof Graphics2D)) {
            return;
        }
        ((Graphics2D)graphics).setStroke(stroke);
    }
    
    public static Stroke getStroke(final Graphics graphics) {
        if (graphics == null || !(graphics instanceof Graphics2D)) {
            return null;
        }
        return ((Graphics2D)graphics).getStroke();
    }
    
    public static void setStyledStroke(final Graphics graphics, final int n, final int n2) {
        if (graphics == null || !(graphics instanceof Graphics2D)) {
            return;
        }
        final String string = String.valueOf(n) + ":" + String.valueOf(n2);
        Stroke stroke = getCachedStroke(string);
        if (stroke == null) {
            stroke = createStyledStroke(n, n2);
            cacheStroke(string, stroke);
        }
        ((Graphics2D)graphics).setStroke(stroke);
    }
    
    public static Stroke createStyledStroke(final int n, final int n2) {
        final int n3 = 1;
        final float[] array = new float[4];
        if (n2 != 1) {
            array[1] = (array[3] = n3 + 2 * n);
            switch (n2) {
                case 2: {
                    array[0] = (array[2] = array[1]);
                    break;
                }
                case 3: {
                    array[0] = (array[2] = 3.0f * array[1]);
                    break;
                }
                case 4: {
                    array[0] = array[1];
                    array[2] = 3.0f * array[1];
                    break;
                }
            }
        }
        return new BasicStroke(n, 1, 0, 10.0f, (float[])((n2 == 1) ? null : array), 0.0f);
    }
    
    protected static Stroke getCachedStroke(final String s) {
        return NF12GraphicsUtil.strokeCache.get(s);
    }
    
    protected static void cacheStroke(final String s, final Stroke stroke) {
        NF12GraphicsUtil.strokeCache.put(s, stroke);
    }
    
    public static void patternFillRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final NFPatternFill nfPatternFill) {
        if (nfPatternFill != null && nfPatternFill.pattern == 11) {
            graphics.fillRect(n, n2, n3, n4);
        }
        final Paint paint = getPaint(graphics);
        setPaint(graphics, NFPatternFactory.getPattern(nfPatternFill, graphics.getColor(), (color == null) ? Color.black : color, new Rectangle(n, n2, n3, n4)));
        graphics.fillRect(n, n2, n3, n4);
        setPaint(graphics, paint);
    }
    
    public static void patternFillPolygon(final Graphics graphics, final Polygon polygon, final Color color, final NFPatternFill nfPatternFill) {
        if (nfPatternFill != null && nfPatternFill.pattern == 11) {
            graphics.fillPolygon(polygon);
        }
        final Paint paint = getPaint(graphics);
        setPaint(graphics, NFPatternFactory.getPattern(nfPatternFill, graphics.getColor(), (color == null) ? Color.black : color, new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints).getBounds()));
        graphics.fillPolygon(polygon);
        setPaint(graphics, paint);
    }
    
    public static void patternFillOval(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final NFPatternFill nfPatternFill) {
        if (nfPatternFill != null && nfPatternFill.pattern == 11) {
            graphics.fillOval(n, n2, n3, n4);
        }
        final Paint paint = getPaint(graphics);
        setPaint(graphics, NFPatternFactory.getPattern(nfPatternFill, graphics.getColor(), (color == null) ? Color.black : color, new Rectangle(n, n2, n3, n4)));
        graphics.fillOval(n, n2, n3, n4);
        setPaint(graphics, paint);
    }
    
    public static void setPaint(final Graphics graphics, final Paint paint) {
        if (graphics == null || !(graphics instanceof Graphics2D)) {
            return;
        }
        ((Graphics2D)graphics).setPaint(paint);
    }
    
    public static Paint getPaint(final Graphics graphics) {
        if (graphics == null || !(graphics instanceof Graphics2D)) {
            return null;
        }
        return ((Graphics2D)graphics).getPaint();
    }
    
    static {
        NF12GraphicsUtil.strokeCache = new Hashtable();
    }
}
