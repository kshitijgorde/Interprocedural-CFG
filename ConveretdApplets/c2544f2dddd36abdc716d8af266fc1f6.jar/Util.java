import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Util
{
    public static final double NEARLY_ZERO = 1.0E-5;
    public static boolean TYPE1_FIX;
    public static boolean TEST_FIX;
    public static int debug_level;
    public static String[] zoneIndex;
    public static String[] contourIndex;
    
    static {
        Util.TYPE1_FIX = true;
        Util.TEST_FIX = true;
        Util.debug_level = 1;
        Util.zoneIndex = new String[] { "Not Used", "A", "B", "AB", "C", "AC", "BC", "ABC" };
        Util.contourIndex = new String[] { "Not Used", "A", "B", "C" };
    }
    
    public static double round(final double inAmount, final int decimalPlaces) {
        long divider = 1L;
        for (int i = 1; i <= decimalPlaces; ++i) {
            divider *= 10L;
        }
        final double largeAmount = Math.rint(inAmount * divider);
        return largeAmount / divider;
    }
    
    public static int convertToInteger(final double inAmount) {
        final double noDecimals = round(inAmount, 0);
        return (int)noDecimals;
    }
    
    public static double lineAngle(final Point p1, final Point p2) {
        final Point2D.Double pd1 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd2 = new Point2D.Double(p2.x, p2.y);
        final double ret = lineAngle(pd1, pd2);
        return ret;
    }
    
    public static double lineAbsoluteAngle(final Point p1, final Point p2) {
        final double rise = p2.y - p1.y;
        final double run = p2.x - p1.x;
        return -Math.atan2(rise, run);
    }
    
    public static double lineAngle(final Point2D.Double p1, final Point2D.Double p2) {
        final double rise = p2.y - p1.y;
        final double run = p2.x - p1.x;
        double angle = -Math.atan2(rise, run);
        if (angle < 0.0) {
            angle += 6.283185307179586;
        }
        return angle;
    }
    
    public static double angle(final Point p1, final Point p2, final Point p3) {
        final Point2D.Double pd1 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd2 = new Point2D.Double(p2.x, p2.y);
        final Point2D.Double pd3 = new Point2D.Double(p3.x, p3.y);
        final double ret = angle(pd1, pd2, pd3);
        return ret;
    }
    
    public static double angle(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double p3) {
        double angle = 0.0;
        final double angle2 = lineAngle(p2, p1);
        final double angle3 = lineAngle(p2, p3);
        angle = Math.abs(angle2 - angle3);
        if (angle > 3.141592653589793) {
            angle = 6.283185307179586 - angle;
        }
        return angle;
    }
    
    public static double distance(final int x1, final int y1, final int x2, final int y2) {
        final Point2D.Double pd1 = new Point2D.Double(x1, y1);
        final Point2D.Double pd2 = new Point2D.Double(x2, y2);
        final double ret = distance(pd1, pd2);
        return ret;
    }
    
    public static double distance(final double x1, final double y1, final double x2, final double y2) {
        final Point2D.Double pd1 = new Point2D.Double(x1, y1);
        final Point2D.Double pd2 = new Point2D.Double(x2, y2);
        final double ret = distance(pd1, pd2);
        return ret;
    }
    
    public static double distance(final Point p1, final Point p2) {
        final Point2D.Double pd1 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd2 = new Point2D.Double(p2.x, p2.y);
        final double ret = distance(pd1, pd2);
        return ret;
    }
    
    public static double distance(final Point2D.Double p1, final Point2D.Double p2) {
        final double rise = p1.y - p2.y;
        final double run = p1.x - p2.x;
        final double distance = Math.sqrt(Math.pow(rise, 2.0) + Math.pow(run, 2.0));
        return distance;
    }
    
    public static Point midPoint(final Point p1, final Point p2) {
        return new Point(p1.x + (p2.x - p1.x) / 2, p1.y + (p2.y - p1.y) / 2);
    }
    
    public static Point2D.Double midPoint(final Point2D p1, final Point2D p2) {
        return new Point2D.Double(p1.getX() + (p2.getX() - p1.getX()) / 2.0, p1.getY() + (p2.getY() - p1.getY()) / 2.0);
    }
    
    public static Point betweenPoints(final Point p1, final Point p2, final double fraction) {
        return new Point(convertToInteger(p1.x + (p2.x - p1.x) * fraction), convertToInteger(p1.y + (p2.y - p1.y) * fraction));
    }
    
    public static Point2D.Double betweenPoints(final Point2D p1, final Point2D p2, final double fraction) {
        return new Point2D.Double(p1.getX() + (p2.getX() - p1.getX()) * fraction, p1.getY() + (p2.getY() - p1.getY()) * fraction);
    }
    
    public static double getRelativeAngle(final Point p1, final Point p2, final Point p3, final Point relativePoint) {
        double angle = angle(p1, p2, p3);
        final double relativeP1Angle = angle(p1, p2, relativePoint);
        final double relativeP3Angle = angle(p3, p2, relativePoint);
        final double totalRelativeAngle = relativeP1Angle + relativeP3Angle;
        if (totalRelativeAngle > angle) {
            angle = 6.283185307179586 - angle;
        }
        return angle;
    }
    
    public static Point intersectionPointOfTwoLines(final Point p1, final Point p2, final Point p3, final Point p4) {
        final Point2D.Double pd1 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd2 = new Point2D.Double(p2.x, p2.y);
        final Point2D.Double pd3 = new Point2D.Double(p3.x, p3.y);
        final Point2D.Double pd4 = new Point2D.Double(p4.x, p4.y);
        final Point2D.Double retd = intersectionPointOfTwoLines(pd1, pd2, pd3, pd4);
        if (retd == null) {
            return null;
        }
        final Point ret = new Point((int)retd.x, (int)retd.y);
        return ret;
    }
    
    public static Point2D.Double intersectionPointOfPolygonAndLine(final Polygon polygon, final Line2D.Double line) {
        if (polygon != null) {
            final int nPoints = polygon.npoints;
            final Point2D.Double p3 = new Point2D.Double(line.getX1(), line.getY1());
            final Point2D.Double p4 = new Point2D.Double(line.getX2(), line.getY2());
            if (nPoints > 2) {
                for (int i = 0; i < nPoints - 1; ++i) {
                    final double x1 = polygon.xpoints[i];
                    final double y1 = polygon.ypoints[i];
                    final double x2 = polygon.xpoints[i + 1];
                    final double y2 = polygon.ypoints[i + 1];
                    final Point2D.Double p5 = new Point2D.Double(x1, y1);
                    final Point2D.Double p6 = new Point2D.Double(x2, y2);
                    final Line2D.Double l = new Line2D.Double(x1, y1, x2, y2);
                    if (l.intersectsLine(line)) {
                        return intersectionPointOfTwoLines(p5, p6, p3, p4);
                    }
                }
                final double xs = polygon.xpoints[0];
                final double ys = polygon.ypoints[0];
                final double xe = polygon.xpoints[nPoints - 1];
                final double ye = polygon.ypoints[nPoints - 1];
                final Line2D.Double l2 = new Line2D.Double(xe, ye, xs, ys);
                final Point2D.Double p5 = new Point2D.Double(xs, ys);
                final Point2D.Double p6 = new Point2D.Double(xe, ye);
                if (l2.intersectsLine(line)) {
                    return intersectionPointOfTwoLines(p5, p6, p3, p4);
                }
            }
        }
        return null;
    }
    
    public static Point2D.Double intersectionPointOfTwoLines(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double p3, final Point2D.Double p4) {
        final double x1 = p1.x;
        final double y1 = p1.y;
        final double x2 = p2.x;
        final double y2 = p2.y;
        final double x3 = p3.x;
        final double y3 = p3.y;
        final double x4 = p4.x;
        final double y4 = p4.y;
        final double x5 = x1 + (x2 - x1) * (((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1)));
        final double y5 = y1 + (y2 - y1) * (((y4 - y3) * (x1 - x3) - (x4 - x3) * (y1 - y3)) / ((x4 - x3) * (y2 - y1) - (y4 - y3) * (x2 - x1)));
        if (Double.isNaN(x5)) {
            return null;
        }
        if (Double.isNaN(y5)) {
            return null;
        }
        if (Double.isInfinite(x5)) {
            return null;
        }
        if (Double.isInfinite(y5)) {
            return null;
        }
        return new Point2D.Double(x5, y5);
    }
    
    public static Point perpendicularPoint(final Point p0, final Point p1, final Point p2) {
        final Point2D.Double pd0 = new Point2D.Double(p0.x, p0.y);
        final Point2D.Double pd2 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd3 = new Point2D.Double(p2.x, p2.y);
        final Point2D.Double retd = perpendicularPoint(pd0, pd2, pd3);
        final Point ret = new Point((int)retd.x, (int)retd.y);
        return ret;
    }
    
    public static Point2D.Double perpendicularPoint(final Point2D.Double p0, final Point2D.Double p1, final Point2D.Double p2) {
        final double base_vec_x = p2.x - p1.x;
        final double base_vec_y = p2.y - p1.y;
        final double base_len = Math.sqrt(base_vec_x * base_vec_x + base_vec_y * base_vec_y);
        final double base_uvec_x = base_vec_x / base_len;
        final double base_uvec_y = base_vec_y / base_len;
        final double step_x = p0.x - p1.x;
        final double step_y = p0.y - p1.y;
        final double dot_prod = step_x * base_uvec_x + step_y * base_uvec_y;
        final double result_x = p1.x + dot_prod * base_uvec_x;
        final double result_y = p1.y + dot_prod * base_uvec_y;
        final Point2D.Double result = new Point2D.Double(result_x, result_y);
        return result;
    }
    
    public static Line2D.Double parallelLine(final Line2D.Double l, final double perpendicularDistance) {
        final Point2D.Double p1 = new Point2D.Double(l.getX1(), l.getY1());
        final Point2D.Double p2 = new Point2D.Double(l.getX2(), l.getY2());
        final double lineAngle = lineAngle(p1, p2);
        final double lineAngleDegrees = Math.toDegrees(lineAngle);
        double perpendicularAngleDegrees = lineAngleDegrees + 90.0;
        if (perpendicularDistance > 0.0) {
            perpendicularAngleDegrees = lineAngleDegrees - 90.0;
        }
        while (perpendicularAngleDegrees > 360.0) {
            perpendicularAngleDegrees -= 360.0;
        }
        while (perpendicularAngleDegrees < 0.0) {
            perpendicularAngleDegrees += 360.0;
        }
        final Point2D.Double moveP1 = movePointOnScreen(p1, perpendicularDistance, perpendicularAngleDegrees);
        final Point2D.Double moveP2 = movePointOnScreen(p2, perpendicularDistance, perpendicularAngleDegrees);
        final Line2D.Double line = new Line2D.Double(moveP1, moveP2);
        return line;
    }
    
    public static int scaleCoordinate(final int value, final int centre, final double multiplier) {
        return convertToInteger(scaleCoordinate(value, (double)centre, multiplier));
    }
    
    public static double scaleCoordinate(final double value, final double centre, final double multiplier) {
        double ret = value - centre;
        ret *= multiplier;
        ret += centre;
        return ret;
    }
    
    public static Point2D.Double getTopLeftMostPolygonPoint(final Polygon p) {
        if (p == null) {
            return null;
        }
        final Point2D.Double origin = new Point2D.Double(0.0, 0.0);
        final double[] points = new double[2];
        Point2D.Double ret = null;
        double distance = Double.MAX_VALUE;
        final PathIterator pit = p.getPathIterator(null);
        while (!pit.isDone()) {
            pit.currentSegment(points);
            final Point2D.Double nextPoint = new Point2D.Double(points[0], points[1]);
            final double nextDistance = distance(origin, nextPoint);
            if (nextDistance < distance) {
                ret = nextPoint;
                distance = nextDistance;
            }
            pit.next();
        }
        return ret;
    }
    
    public static Point getExtendedPoint(final Point2D p1, final Point2D p2, final double dis) {
        final double x1 = p2.getX() - dis;
        final double x2 = p2.getX() + dis;
        final double y1 = p2.getY() - dis;
        final double y2 = p2.getY() + dis;
        double x3;
        if (p1.getX() >= p2.getX()) {
            x3 = x1;
        }
        else {
            x3 = x2;
        }
        double y3;
        if (p1.getY() >= p2.getY()) {
            y3 = y1;
        }
        else {
            y3 = y2;
        }
        final Point ret = new Point((int)x3, (int)y3);
        return ret;
    }
    
    public static boolean isConcave(final Polygon p) {
        final Coord[] coords = new Coord[p.npoints];
        for (int i = 0; i < p.npoints; ++i) {
            coords[i] = new Coord(p.xpoints[i], p.ypoints[i]);
        }
        final int len = coords.length;
        final Coord first = coords[0];
        final Coord last = coords[len - 1];
        final boolean closed = first.lon == last.lon && first.lat == last.lat;
        assert !closed && len >= 3;
        if ((!closed && len == 3) || (closed && len == 4)) {
            return false;
        }
        double xp1 = 0.0;
        if (len >= 3) {
            xp1 = crossProduct(coords[0], coords[1], coords[2]);
        }
        for (int lastIdx = closed ? (len - 2) : (len - 1), j = 3; j <= lastIdx; ++j) {
            final double xp2 = crossProduct(coords[j - 2], coords[j - 1], coords[j]);
            if (xp2 != 0.0 && !sameSign(xp1, xp2)) {
                return true;
            }
        }
        final int im2 = closed ? (len - 3) : (len - 2);
        final int im3 = closed ? (len - 2) : (len - 1);
        final double xpm2 = crossProduct(coords[im2], coords[im3], coords[0]);
        final double xpm3 = crossProduct(coords[im3], coords[0], coords[1]);
        return (xpm3 != 0.0 && !sameSign(xp1, xpm3)) || (xpm2 != 0.0 && !sameSign(xp1, xpm2));
    }
    
    public static double crossProduct(final Coord prev, final Coord center, final Coord next) {
        final double x = center.lon;
        final double y = center.lat;
        final double xn = next.lon;
        final double yn = next.lat;
        final double xp = prev.lon;
        final double yp = prev.lat;
        return (x - xp) * (yn - y) - (y - yp) * (xn - x);
    }
    
    public static boolean sameSign(final double a, final double b) {
        return (a < 0.0 && b < 0.0) || (a >= 0.0 && b >= 0.0);
    }
    
    public static Point2D.Double movePointOnScreen(final Point2D.Double startPoint, final double distance, final double degrees) {
        final double newX = startPoint.getX() + distance * Math.cos(Math.toRadians(degrees));
        final double newY = startPoint.getY() - distance * Math.sin(Math.toRadians(degrees));
        return new Point2D.Double(newX, newY);
    }
    
    public static double calculateAngle(final double x1, final double y1, final double x2, final double y2) {
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        double angle = 0.0;
        if (dx == 0.0) {
            if (dy == 0.0) {
                angle = 0.0;
            }
            else if (dy > 0.0) {
                angle = 1.5707963267948966;
            }
            else {
                angle = 4.71238898038469;
            }
        }
        else if (dy == 0.0) {
            if (dx > 0.0) {
                angle = 0.0;
            }
            else {
                angle = 3.141592653589793;
            }
        }
        else if (dx < 0.0) {
            angle = Math.atan(dy / dx) + 3.141592653589793;
        }
        else if (dy < 0.0) {
            angle = Math.atan(dy / dx) + 6.283185307179586;
        }
        else {
            angle = Math.atan(dy / dx);
        }
        angle = angle * 180.0 / 3.141592653589793;
        return angle;
    }
    
    public static boolean pointIsWithinBounds(final Point p, final Point p1, final Point p2) {
        int left = p1.x;
        int right = p2.x;
        if (p1.x > p2.x) {
            left = p2.x;
            right = p1.x;
        }
        int top = p1.y;
        int bottom = p2.y;
        if (p1.y > p2.y) {
            top = p2.y;
            bottom = p1.y;
        }
        return p.x >= left && p.x <= right && p.y >= top && p.y <= bottom;
    }
    
    public static boolean linesCross(final Point p1, final Point p2, final Point q1, final Point q2) {
        return Line2D.linesIntersect(p1.x, p1.y, p2.x, p2.y, q1.x, q1.y, q2.x, q2.y);
    }
    
    public static boolean linesCross(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double q1, final Point2D.Double q2) {
        return Line2D.linesIntersect(p1.x, p1.y, p2.x, p2.y, q1.x, q1.y, q2.x, q2.y);
    }
    
    public static boolean linesParallel(final Point p1, final Point p2, final Point q1, final Point q2) {
        final Point2D.Double pd1 = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double pd2 = new Point2D.Double(p2.x, p2.y);
        final Point2D.Double qd1 = new Point2D.Double(q1.x, q1.y);
        final Point2D.Double qd2 = new Point2D.Double(q2.x, q2.y);
        return linesParallel(pd1, pd2, qd1, qd2);
    }
    
    public static boolean linesParallel(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double q1, final Point2D.Double q2) {
        final int DECIMAL_PLACES = 6;
        final double angleP = round(lineAngle(p1, p2), 6);
        final double angleQ = round(lineAngle(q1, q2), 6);
        return angleP == angleQ || angleP == round(lineAngle(q1, q2) - 3.141592653589793, 6) || round(lineAngle(p1, p2) - 3.141592653589793, 6) == angleQ;
    }
    
    public static boolean linesNearlyParallel(final Point p1, final Point p2, final Point q1, final Point q2, final double fudgeDegrees) {
        final Point2D.Double p1d = new Point2D.Double(p1.x, p1.y);
        final Point2D.Double p2d = new Point2D.Double(p2.x, p2.y);
        final Point2D.Double q1d = new Point2D.Double(q1.x, q1.y);
        final Point2D.Double q2d = new Point2D.Double(q2.x, q2.y);
        return linesNearlyParallel(p1d, p2d, q1d, q2d, fudgeDegrees);
    }
    
    public static boolean linesNearlyParallel(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double q1, final Point2D.Double q2, final double fudgeDegrees) {
        final double fudgeAngle = Math.toRadians(fudgeDegrees);
        final int DECIMAL_PLACES = 6;
        final double angleP = round(lineAngle(p1, p2), 6);
        final double angleQ = round(lineAngle(q1, q2), 6);
        return Math.abs(angleP - angleQ) < fudgeAngle || Math.abs(angleP - round(lineAngle(q1, q2) - 3.141592653589793, 6)) < fudgeAngle || Math.abs(angleQ - round(lineAngle(p1, p2) - 3.141592653589793, 6)) < fudgeAngle;
    }
    
    public static String reverseString(final String s) {
        final String[] splitString = s.split("");
        final StringBuffer reverse = new StringBuffer("");
        for (int i = splitString.length - 1; i >= 0; --i) {
            reverse.append(splitString[i]);
        }
        return reverse.toString();
    }
    
    public static Point getLineLineIntersection(final Point p1, final Point p2, final Point p3, final Point p4) {
        final Point ret = new Point();
        final double x1 = p1.getX();
        final double y1 = p1.getY();
        final double x2 = p2.getX();
        final double y2 = p2.getY();
        final double x3 = p3.getX();
        final double y3 = p3.getY();
        final double x4 = p4.getX();
        final double y4 = p4.getY();
        final double x5 = det(det(x1, y1, x2, y2), x1 - x2, det(x3, y3, x4, y4), x3 - x4) / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);
        final double y5 = det(det(x1, y1, x2, y2), y1 - y2, det(x3, y3, x4, y4), y3 - y4) / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);
        ret.setLocation(x5, y5);
        return ret;
    }
    
    public static double det(final double a, final double b, final double c, final double d) {
        return a * d - b * c;
    }
    
    public static double pointLineDistance(final Point p, final Point p1, final Point p2) {
        final Point perpPoint = perpendicularPoint(p, p1, p2);
        if (pointIsWithinBounds(perpPoint, p1, p2)) {
            final double distance = distance(p, perpPoint);
            return distance;
        }
        final double distance2 = distance(p, p1);
        final double distance3 = distance(p, p2);
        if (distance2 < distance3) {
            return distance2;
        }
        return distance3;
    }
    
    public static double computePolygonArea(final Polygon p) {
        double area = 0.0;
        for (int i = 0; i < p.npoints - 1; ++i) {
            area += p.xpoints[i] * p.ypoints[i + 1] - p.xpoints[i + 1] * p.ypoints[i];
        }
        area += p.xpoints[p.npoints - 1] * p.ypoints[0] - p.xpoints[0] * p.ypoints[p.npoints - 1];
        area *= 0.5;
        if (area < 0.0) {
            area = -area;
        }
        return area;
    }
    
    public static double computePolygonArea(final Point2D.Double[] ps) {
        double area = 0.0;
        for (int i = 0; i < ps.length - 1; ++i) {
            area += ps[i].x * ps[i + 1].y - ps[i + 1].x * ps[i].y;
        }
        area += ps[ps.length - 1].x * ps[0].y - ps[0].x * ps[ps.length - 1].y;
        area *= 0.5;
        if (area < 0.0) {
            area = -area;
        }
        return area;
    }
    
    public static double computeTriangleArea(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double p3) {
        final Point2D.Double[] ps = { p1, p2, p3 };
        return computePolygonArea(ps);
    }
    
    public static Point2D.Double computePolygonCentroid(final Polygon p) {
        double cx = 0.0;
        double cy = 0.0;
        for (int i = 0; i < p.npoints - 1; ++i) {
            final double a = p.xpoints[i] * p.ypoints[i + 1] - p.xpoints[i + 1] * p.ypoints[i];
            cx += (p.xpoints[i] + p.xpoints[i + 1]) * a;
            cy += (p.ypoints[i] + p.ypoints[i + 1]) * a;
        }
        final double a2 = p.xpoints[p.npoints - 1] * p.ypoints[0] - p.xpoints[0] * p.ypoints[p.npoints - 1];
        cx += (p.xpoints[p.npoints - 1] + p.xpoints[0]) * a2;
        cy += (p.ypoints[p.npoints - 1] + p.ypoints[0]) * a2;
        final double area = computePolygonArea(p);
        cx /= 6.0 * area;
        cy /= 6.0 * area;
        return new Point2D.Double(cx, cy);
    }
    
    public static ArrayList<Polygon> polygonsFromArea(final Area a) {
        if (!a.isPolygonal()) {
            return null;
        }
        final ArrayList<Polygon> ret = new ArrayList<Polygon>();
        Polygon p = new Polygon();
        final double[] coords = new double[6];
        final PathIterator pi = a.getPathIterator(null);
        while (!pi.isDone()) {
            final int coordType = pi.currentSegment(coords);
            if (coordType == 4 || coordType == 0) {
                if (coordType == 4) {
                    final int x = convertToInteger(coords[0]);
                    final int y = convertToInteger(coords[1]);
                    p.addPoint(x, y);
                }
                if (p.npoints > 2) {
                    final Rectangle2D boundingRectangle = p.getBounds2D();
                    final double boundingArea = boundingRectangle.getWidth() * boundingRectangle.getHeight();
                    if (boundingArea >= 1.0) {
                        ret.add(p);
                    }
                }
                p = new Polygon();
                if (coordType == 0) {
                    final int x = convertToInteger(coords[0]);
                    final int y = convertToInteger(coords[1]);
                    p.addPoint(x, y);
                }
            }
            if (coordType == 1) {
                final int x = convertToInteger(coords[0]);
                final int y = convertToInteger(coords[1]);
                p.addPoint(x, y);
            }
            if (coordType == 3) {
                System.out.println("Found a PathIterator.SEG_CUBICTO");
            }
            if (coordType == 2) {
                System.out.println("Found a PathIterator.SEG_QUADTO");
            }
            pi.next();
        }
        return ret;
    }
    
    public static double findAreaOfArea(final Area area) {
        return findAreaOfArea(null, area);
    }
    
    public static double findAreaOfArea(final Graphics2D g2, final Area area) {
        final ArrayList<Polygon> ps = polygonsFromArea(area);
        if (ps.size() > 1) {
            System.out.println("Multiple polygons for a zone");
        }
        double ret = 0.0;
        for (final Polygon p : ps) {
            ret += computePolygonArea(p);
        }
        return ret;
    }
    
    public static Polygon squarePolygonAroundPoint(final Point2D.Double p, final int width) {
        final Point pInt = new Point(convertToInteger(p.x), convertToInteger(p.y));
        return squarePolygonAroundPoint(pInt, width);
    }
    
    public static Polygon squarePolygonAroundPoint(final Point p, final int width) {
        final Polygon ret = new Polygon();
        final int d = width / 2;
        ret.addPoint(p.x - d, p.y - d);
        ret.addPoint(p.x - d, p.y + d);
        ret.addPoint(p.x + d, p.y + d);
        ret.addPoint(p.x + d, p.y - d);
        return ret;
    }
    
    public static Polygon diamondPolygonAroundPoint(final Point2D.Double p, final int width) {
        final Point pInt = new Point(convertToInteger(p.x), convertToInteger(p.y));
        return diamondPolygonAroundPoint(pInt, width);
    }
    
    public static Polygon diamondPolygonAroundPoint(final Point p, final int width) {
        final Polygon ret = new Polygon();
        final int d = width / 2;
        ret.addPoint(p.x - d, p.y);
        ret.addPoint(p.x, p.y + d);
        ret.addPoint(p.x + d, p.y);
        ret.addPoint(p.x, p.y - d);
        return ret;
    }
    
    public static double safeParseDouble(final String doubleString) {
        double ret = 0.0;
        try {
            ret = Double.parseDouble(doubleString);
        }
        catch (Exception e) {
            System.out.println("Could not parse double from String: " + doubleString);
            return -1.0;
        }
        return ret;
    }
    
    public static void debug(final int i, final String string) {
        if (i <= Util.debug_level) {
            System.out.println(string);
        }
    }
}
