import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleGeometry
{
    public static void transformCircles(final CircleLayout[] circles, final Rectangle2D bbox) {
        double maxx;
        double minx = maxx = circles[0].center.getX() - circles[0].radius;
        double maxy;
        double miny = maxy = circles[0].center.getY() - circles[0].radius;
        for (int i = 1; i < circles.length; ++i) {
            if (circles[i].center.getX() - circles[i].radius < minx) {
                minx = circles[i].center.getX() - circles[i].radius;
            }
            if (circles[i].center.getX() + circles[i].radius > maxx) {
                maxx = circles[i].center.getX() + circles[i].radius;
            }
            if (circles[i].center.getY() - circles[i].radius < miny) {
                miny = circles[i].center.getY() - circles[i].radius;
            }
            if (circles[i].center.getY() + circles[i].radius > maxy) {
                maxy = circles[i].center.getY() + circles[i].radius;
            }
        }
        final double scalex = bbox.getWidth() / (maxx - minx);
        final double scaley = bbox.getHeight() / (maxy - miny);
        final double scale = (scalex < scaley) ? scalex : scaley;
        final double deltax = bbox.getX() - minx * scale;
        final double deltay = bbox.getY() - miny * scale;
        for (int j = 0; j < circles.length; ++j) {
            final CircleLayout circleLayout = circles[j];
            circleLayout.radius *= scale;
            circles[j].center.setLocation(circles[j].center.getX() * scale + deltax, circles[j].center.getY() * scale + deltay);
        }
    }
    
    public static int getIntersections(final CircleLayout c1, final CircleLayout c2, final Point2D[] intersections) {
        final double dx = c2.center.getX() - c1.center.getX();
        final double dy = c2.center.getY() - c1.center.getY();
        final double d = Math.sqrt(dx * dx + dy * dy);
        if (d > c1.radius + c2.radius) {
            return 0;
        }
        if (d < Math.abs(c1.radius - c2.radius)) {
            return -1;
        }
        final double ndx = dx / d;
        final double ndy = dy / d;
        if (d == c1.radius + c2.radius) {
            intersections[0].setLocation(c1.center.getX() + c1.radius * dx, c1.center.getY() + c1.radius * dy);
            return 1;
        }
        final double a = (c1.radius * c1.radius - c2.radius * c2.radius + d * d) / (2.0 * d);
        final double px = c1.center.getX() + a * ndx;
        final double py = c1.center.getY() + a * ndy;
        final double nhx = -ndy;
        final double nhy = ndx;
        final double h = Math.sqrt(c1.radius * c1.radius - a * a);
        intersections[0].setLocation(px + h * nhx, py + h * nhy);
        intersections[1].setLocation(px - h * nhx, py - h * nhy);
        return 2;
    }
    
    public static double computeSegmentArea(final CircleLayout c1, final Point2D.Double p1, final Point2D.Double p2) {
        final double cx = p2.getX() - p1.getX();
        final double cy = p2.getY() - p1.getY();
        final double c2 = cx * cx + cy * cy;
        final double d = 0.5 * Math.sqrt(4.0 * c1.radius * c1.radius - c2);
        final double h = c1.radius - d;
        final double a = c1.radius * c1.radius * Math.acos((c1.radius - h) / c1.radius) - (c1.radius - h) * Math.sqrt(2.0 * c1.radius * h - h * h);
        if (c1.center.getX() * p1.getY() - p1.getX() * c1.center.getY() + p1.getX() * p2.getY() - p2.getX() * p1.getY() + p2.getX() * c1.center.getY() - c1.center.getX() * p2.getY() >= 0.0) {
            return 3.141592653589793 * c1.radius * c1.radius - a;
        }
        return a;
    }
    
    public static double computeCommonArea(final CircleLayout c1, final CircleLayout c2) {
        final double dx = c2.center.getX() - c1.center.getX();
        final double dy = c2.center.getY() - c1.center.getY();
        final double d = Math.sqrt(dx * dx + dy * dy);
        if (d > c1.radius + c2.radius) {
            return 0.0;
        }
        if (d < Math.abs(c1.radius - c2.radius)) {
            final double r = Math.min(c1.radius, c2.radius);
            return 3.141592653589793 * r * r;
        }
        return c1.radius * c1.radius * Math.acos((d * d + c1.radius * c1.radius - c2.radius * c2.radius) / (2.0 * d * c1.radius)) + c2.radius * c2.radius * Math.acos((d * d + c2.radius * c2.radius - c1.radius * c1.radius) / (2.0 * d * c2.radius)) - 0.5 * Math.sqrt((-1.0 * d + c1.radius + c2.radius) * (d + c1.radius - c2.radius) * (d - c1.radius + c2.radius) * (d + c1.radius + c2.radius));
    }
    
    public static double computeCommonArea(CircleLayout c1, CircleLayout c2, final CircleLayout c3) {
        if (isInside(c1, c2)) {
            return computeCommonArea(c1, c3);
        }
        if (isInside(c2, c1)) {
            return computeCommonArea(c2, c3);
        }
        if (isInside(c1, c3)) {
            return computeCommonArea(c1, c2);
        }
        if (isInside(c3, c1)) {
            return computeCommonArea(c3, c2);
        }
        if (isInside(c2, c3)) {
            return computeCommonArea(c2, c1);
        }
        if (isInside(c3, c2)) {
            return computeCommonArea(c3, c1);
        }
        if (c1.center.getX() * c2.center.getY() - c2.center.getX() * c1.center.getY() + c2.center.getX() * c3.center.getY() - c3.center.getX() * c2.center.getY() + c3.center.getX() * c1.center.getY() - c1.center.getX() * c3.center.getY() > 0.0) {
            final CircleLayout tmp = c1;
            c1 = c2;
            c2 = tmp;
        }
        final Point2D.Double[] intersections = { new Point2D.Double(), new Point2D.Double() };
        final Intersection[] p = new Intersection[4];
        int psize = 0;
        for (int i = getIntersections(c1, c2, intersections) - 1; i >= 0; --i) {
            if (isInside(intersections[i], c3)) {
                p[psize++] = new Intersection(intersections[i], c1, c2);
                intersections[i] = new Point2D.Double();
            }
        }
        for (int i = getIntersections(c2, c3, intersections) - 1; i >= 0; --i) {
            if (isInside(intersections[i], c1)) {
                p[psize++] = new Intersection(intersections[i], c2, c3);
                intersections[i] = new Point2D.Double();
            }
        }
        for (int i = getIntersections(c3, c1, intersections) - 1; i >= 0; --i) {
            if (isInside(intersections[i], c2)) {
                p[psize++] = new Intersection(intersections[i], c3, c1);
                intersections[i] = new Point2D.Double();
            }
        }
        if (psize == 0) {
            return 0.0;
        }
        if (psize == 2) {
            return computeCommonArea(p[0].c1, p[0].c2);
        }
        if (psize == 3) {
            double a = -0.5 * (p[0].p.getX() * p[1].p.getY() - p[1].p.getX() * p[0].p.getY() + p[1].p.getX() * p[2].p.getY() - p[2].p.getX() * p[1].p.getY() + p[2].p.getX() * p[0].p.getY() - p[0].p.getX() * p[2].p.getY());
            a += computeSegmentArea(c2, p[0].p, p[1].p);
            a += computeSegmentArea(c3, p[1].p, p[2].p);
            a += computeSegmentArea(c1, p[2].p, p[0].p);
            return a;
        }
        if (psize == 4) {
            double a = 0.5 * (p[0].p.getX() * p[1].p.getY() - p[1].p.getX() * p[0].p.getY() + p[1].p.getX() * p[3].p.getY() - p[3].p.getX() * p[1].p.getY() + p[3].p.getX() * p[2].p.getY() - p[2].p.getX() * p[3].p.getY() + p[2].p.getX() * p[0].p.getY() - p[0].p.getX() * p[2].p.getY());
            if (a < 0.0) {
                Intersection tmp2 = p[0];
                p[0] = p[1];
                p[1] = tmp2;
                tmp2 = p[2];
                p[2] = p[3];
                p[3] = tmp2;
                a *= -1.0;
            }
            final CircleLayout cq = (p[0].c1 == p[2].c1 || p[0].c1 == p[2].c2) ? p[0].c1 : p[0].c2;
            a += computeSegmentArea((p[0].c1 == cq) ? p[0].c2 : p[0].c1, p[1].p, p[0].p);
            a += computeSegmentArea(cq, p[3].p, p[1].p);
            a += computeSegmentArea((p[3].c1 == cq) ? p[3].c2 : p[3].c1, p[2].p, p[3].p);
            a += computeSegmentArea(cq, p[0].p, p[2].p);
            return a;
        }
        throw new RuntimeException("Unexpected number of bounding vertices:" + psize);
    }
    
    public static boolean isInside(final Point2D.Double p, final CircleLayout c) {
        final double dx = p.getX() - c.center.getX();
        final double dy = p.getY() - c.center.getY();
        final double d2 = dx * dx + dy * dy;
        return d2 <= c.radius * c.radius;
    }
    
    public static boolean isInside(final CircleLayout c1, final CircleLayout c2) {
        final double dx = c2.center.getX() - c1.center.getX();
        final double dy = c2.center.getY() - c1.center.getY();
        final double d2 = dx * dx + dy * dy;
        return c1.radius <= c2.radius && d2 <= (c2.radius - c1.radius) * (c2.radius - c1.radius);
    }
    
    public static double[] computeArea(final CircleLayout[] circles) {
        final double[] areas = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, computeCommonArea(circles[0], circles[1], circles[2]) };
        areas[3] = computeCommonArea(circles[0], circles[1]) - areas[7];
        areas[6] = computeCommonArea(circles[1], circles[2]) - areas[7];
        areas[5] = computeCommonArea(circles[0], circles[2]) - areas[7];
        areas[1] = 3.141592653589793 * circles[0].radius * circles[0].radius - areas[3] - areas[5] - areas[7];
        areas[2] = 3.141592653589793 * circles[1].radius * circles[1].radius - areas[3] - areas[6] - areas[7];
        areas[4] = 3.141592653589793 * circles[2].radius * circles[2].radius - areas[5] - areas[6] - areas[7];
        return areas;
    }
    
    public static void main(final String[] args) {
        System.out.println("begin...");
        final CircleLayout[] circles = { new CircleLayout(), null, null };
        circles[0].radius = 0.8125;
        circles[0].center = new Point2D.Double(5.625, 0.875);
        circles[1] = new CircleLayout();
        circles[1].radius = 0.9375;
        circles[1].center = new Point2D.Double(6.625, 0.875);
        circles[2] = new CircleLayout();
        circles[2].radius = 0.395;
        circles[2].center = new Point2D.Double(6.125, 1.25);
        System.out.println("common area=" + computeCommonArea(circles[0], circles[1], circles[2]));
        System.out.println("common area=" + computeCommonArea(circles[0], circles[2], circles[1]));
        System.out.println("common area=" + computeCommonArea(circles[1], circles[0], circles[2]));
        System.out.println("common area=" + computeCommonArea(circles[1], circles[2], circles[0]));
        System.out.println("common area=" + computeCommonArea(circles[2], circles[0], circles[1]));
        System.out.println("common area=" + computeCommonArea(circles[2], circles[1], circles[0]));
        System.out.println();
        double[] areas = computeArea(circles);
        System.out.println("Areas using geometry:");
        for (int i = 1; i <= 7; ++i) {
            System.out.println("area " + i + " = " + areas[i]);
        }
        System.out.println();
        areas = CircleAreaRunner.computeAreaByPolygons(circles);
        System.out.println("Areas using polygons:");
        for (int i = 1; i <= 7; ++i) {
            System.out.println("area " + i + " = " + areas[i]);
        }
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; ++j) {
            computeArea(circles);
        }
        System.out.println("Elapsed time to compute 100 areas by geometry (ms): " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int j = 0; j < 100; ++j) {
            CircleAreaRunner.computeAreaByPolygons(circles);
        }
        System.out.println("Elapsed time to compute 100 areas by polygon (ms): " + (System.currentTimeMillis() - start));
        System.out.println("end...");
    }
}
