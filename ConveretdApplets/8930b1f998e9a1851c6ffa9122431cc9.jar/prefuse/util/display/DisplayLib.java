// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import prefuse.Display;
import java.awt.geom.Point2D;
import prefuse.visual.VisualItem;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class DisplayLib
{
    public static Rectangle2D getBounds(final Iterator iterator, final double n, final Rectangle2D rectangle2D) {
        rectangle2D.setFrame(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        if (iterator.hasNext()) {
            rectangle2D.setFrame(iterator.next().getBounds());
        }
        while (iterator.hasNext()) {
            final Rectangle2D bounds = iterator.next().getBounds();
            final double n2 = (bounds.getMinX() < rectangle2D.getMinX()) ? bounds.getMinX() : rectangle2D.getMinX();
            final double n3 = (bounds.getMaxX() > rectangle2D.getMaxX()) ? bounds.getMaxX() : rectangle2D.getMaxX();
            final double n4 = (bounds.getMinY() < rectangle2D.getMinY()) ? bounds.getMinY() : rectangle2D.getMinY();
            rectangle2D.setFrame(n2, n4, n3 - n2, ((bounds.getMaxY() > rectangle2D.getMaxY()) ? bounds.getMaxY() : rectangle2D.getMaxY()) - n4);
        }
        rectangle2D.setFrame(rectangle2D.getMinX() - n, rectangle2D.getMinY() - n, rectangle2D.getWidth() + 2.0 * n, rectangle2D.getHeight() + 2.0 * n);
        return rectangle2D;
    }
    
    public static Rectangle2D getBounds(final Iterator iterator, final double n) {
        return getBounds(iterator, n, new Rectangle2D.Double());
    }
    
    public static Point2D getCentroid(final Iterator iterator, final Point2D point2D) {
        double n = 0.0;
        double n2 = 0.0;
        int n3 = 0;
        while (iterator.hasNext()) {
            final VisualItem visualItem = iterator.next();
            final double x = visualItem.getX();
            final double y = visualItem.getY();
            if (!Double.isInfinite(x) && !Double.isNaN(x) && !Double.isInfinite(y) && !Double.isNaN(y)) {
                n += x;
                n2 += y;
                ++n3;
            }
        }
        if (n3 > 0) {
            n /= n3;
            n2 /= n3;
        }
        point2D.setLocation(n, n2);
        return point2D;
    }
    
    public static Point2D getCentroid(final Iterator iterator) {
        return getCentroid(iterator, new Point2D.Double());
    }
    
    public static void fitViewToBounds(final Display display, final Rectangle2D rectangle2D, final long n) {
        fitViewToBounds(display, rectangle2D, null, n);
    }
    
    public static void fitViewToBounds(final Display display, final Rectangle2D rectangle2D, Point2D point2D, final long n) {
        final double n2 = display.getWidth();
        final double n3 = display.getHeight();
        final double n4 = (point2D == null) ? rectangle2D.getCenterX() : point2D.getX();
        final double n5 = (point2D == null) ? rectangle2D.getCenterY() : point2D.getY();
        final double n6 = Math.min(n2 / (2.0 * Math.max(n4 - rectangle2D.getMinX(), rectangle2D.getMaxX() - n4)), n3 / (2.0 * Math.max(n5 - rectangle2D.getMinY(), rectangle2D.getMaxY() - n5))) / display.getScale();
        if (point2D == null) {
            point2D = new Point2D.Double(n4, n5);
        }
        if (n > 0L) {
            display.animatePanAndZoomToAbs(point2D, n6, n);
        }
        else {
            display.panToAbs(point2D);
            display.zoomAbs(point2D, n6);
        }
    }
}
