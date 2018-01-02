import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonIntersectTester
{
    private Polygon ref;
    private Rectangle boundingBox;
    private int marginSize;
    
    public PolygonIntersectTester(final Polygon polygon) {
        this.ref = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
    
    private void setBoundingBoxMargins(final int marginSize) {
        (this.boundingBox = this.ref.getBounds()).grow(marginSize, marginSize);
        this.marginSize = marginSize;
    }
    
    public void translate(final int n, final int n2) {
        this.ref.translate(n, n2);
    }
    
    public static boolean linesIntersect(final Point point, final Point point2, final Point point3, final Point point4) {
        return crossProd(point, point2, point3) * crossProd(point, point2, point4) <= 0.0 && crossProd(point3, point4, point) * crossProd(point3, point4, point2) <= 0.0;
    }
    
    private static final double crossProd(final Point point, final Point point2, final Point point3) {
        return (point.x - point2.x) * (point.y - point3.y) - (point.y - point2.y) * (point.x - point3.x);
    }
    
    public boolean intersects(final Polygon polygon) {
        final Point point = new Point((polygon.xpoints[0] + polygon.xpoints[2]) / 2, (polygon.ypoints[0] + polygon.ypoints[2]) / 2);
        int min;
        int max = min = polygon.xpoints[0];
        int min2;
        int max2 = min2 = polygon.ypoints[0];
        for (int i = 1; i < polygon.npoints; ++i) {
            max = Math.max(max, polygon.xpoints[i]);
            min = Math.min(min, polygon.xpoints[i]);
            max2 = Math.max(max2, polygon.ypoints[i]);
            min2 = Math.min(min2, polygon.ypoints[i]);
        }
        final int n = (max2 - min2 + 1) / 2;
        final int n2 = (max - min + 1) / 2;
        int boundingBoxMargins;
        if (n > n2) {
            boundingBoxMargins = n;
        }
        else {
            boundingBoxMargins = n2;
        }
        if (this.boundingBox == null || boundingBoxMargins > this.marginSize) {
            this.setBoundingBoxMargins(boundingBoxMargins);
        }
        if (!this.boundingBox.contains(point)) {
            return false;
        }
        if (this.ref.contains(point)) {
            return true;
        }
        if (polygon.contains(this.ref.xpoints[0], this.ref.ypoints[0])) {
            return true;
        }
        final int[] xpoints = polygon.xpoints;
        final int[] ypoints = polygon.ypoints;
        final Point point2 = new Point();
        final Point point3 = new Point();
        final Point location = new Point(this.ref.xpoints[this.ref.npoints - 1], this.ref.ypoints[this.ref.npoints - 1]);
        final Point location2 = new Point(xpoints[polygon.npoints - 1], ypoints[polygon.npoints - 1]);
        for (int j = 0; j < this.ref.npoints; ++j) {
            point2.setLocation(location);
            location.setLocation(this.ref.xpoints[j], this.ref.ypoints[j]);
            for (int k = 0; k < polygon.npoints; ++k) {
                point3.setLocation(location2);
                location2.setLocation(xpoints[k], ypoints[k]);
                if (linesIntersect(point2, location, point3, location2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
