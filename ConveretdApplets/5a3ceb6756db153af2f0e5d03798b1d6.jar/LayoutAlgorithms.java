import java.awt.geom.Point2D;

// 
// Decompiled by Procyon v0.5.30
// 

public class LayoutAlgorithms
{
    public static double computeDistance(final CircleLayout c1, final CircleLayout c2, final double area) {
        if (area == 0.0) {
            return c1.radius + c2.radius;
        }
        if (3.141592653589793 * c1.radius * c1.radius == area) {
            return c2.radius - c1.radius;
        }
        if (3.141592653589793 * c2.radius * c2.radius == area) {
            return c1.radius - c2.radius;
        }
        return (double)Bisection.find(new Double(c1.radius + c2.radius), new Double(c1.radius - c2.radius), new Double(area), new Double(0.001), (Bisection.MathOps)new TwoCircleMathOps(c1.radius, c2.radius));
    }
    
    public static boolean isValidThreeCircleLayout(final CircleLayout c1, final CircleLayout c2, final CircleLayout c3) {
        final Point2D.Double[] intersections = { new Point2D.Double(), new Point2D.Double() };
        if (CircleGeometry.getIntersections(c1, c2, intersections) != 2) {
            return false;
        }
        double d1 = c3.center.distance(intersections[0]);
        double d2 = c3.center.distance(intersections[1]);
        if (d1 > d2) {
            final double temp = d1;
            d1 = d2;
            d2 = temp;
        }
        return c3.radius > d1 && c3.radius < d2;
    }
    
    public static CircleLayout[] computeThreeCircleLayout(final double[] areas) {
        final CircleLayout[] circles = { new CircleLayout(), null, null };
        circles[0].radius = Math.sqrt((areas[1] + areas[3] + areas[5] + areas[7]) / 3.141592653589793);
        circles[1] = new CircleLayout();
        circles[1].radius = Math.sqrt((areas[2] + areas[3] + areas[6] + areas[7]) / 3.141592653589793);
        circles[2] = new CircleLayout();
        circles[2].radius = Math.sqrt((areas[4] + areas[5] + areas[6] + areas[7]) / 3.141592653589793);
        final double d12 = computeDistance(circles[0], circles[1], areas[3] + areas[7]);
        final double d13 = computeDistance(circles[0], circles[2], areas[5] + areas[7]);
        final double d14 = computeDistance(circles[1], circles[2], areas[6] + areas[7]);
        circles[0].center = new Point2D.Double(0.0, 0.0);
        circles[1].center = new Point2D.Double(d12, 0.0);
        final double x3 = (d14 * d14 - d13 * d13 - d12 * d12) / (-2.0 * d12);
        final double y3 = Math.sqrt(d13 * d13 - x3 * x3);
        circles[2].center = new Point2D.Double(x3, y3);
        if (y3 != Double.NaN) {
            System.out.println("Triangle inequality satisfied.");
        }
        else {
            System.out.println("Triangle inequality NOT satisfied.");
        }
        if (y3 == Double.NaN || !isValidThreeCircleLayout(circles[0], circles[1], circles[2])) {
            System.out.println("Layout invalid... computing alternate.");
            return computeApproxThreeCircleLayout(areas);
        }
        return circles;
    }
    
    public static CircleLayout[] computeApproxThreeCircleLayout(final double[] areas) {
        final CircleLayout[] circles = { new CircleLayout(), null, null };
        circles[0].radius = Math.sqrt((areas[1] + areas[3] + areas[5] + areas[7]) / 3.141592653589793);
        circles[1] = new CircleLayout();
        circles[1].radius = Math.sqrt((areas[2] + areas[3] + areas[6] + areas[7]) / 3.141592653589793);
        circles[2] = new CircleLayout();
        circles[2].radius = Math.sqrt((areas[4] + areas[5] + areas[6] + areas[7]) / 3.141592653589793);
        final double d12 = computeDistance(circles[0], circles[1], areas[3] + areas[7]);
        circles[0].center = new Point2D.Double(0.0, 0.0);
        circles[1].center = new Point2D.Double(d12, 0.0);
        final Point2D.Double[] intersections = { new Point2D.Double(), new Point2D.Double() };
        CircleGeometry.getIntersections(circles[0], circles[1], intersections);
        final Point2D.Double start = new Point2D.Double(intersections[0].getX(), intersections[0].getY() + circles[2].radius);
        final Double d13 = (Double)Bisection.find(new Double(0.0), new Double(Math.abs(intersections[0].getY() - intersections[1].getY())), new Double(areas[7]), new Double(0.001), (Bisection.MathOps)new ThreeCircleMathOps(circles[0], circles[1], circles[2].radius, start, 0.0, -1.0));
        circles[2].center = new Point2D.Double(intersections[0].getX(), intersections[0].getY() + circles[2].radius - d13);
        return circles;
    }
    
    public static void main(final String[] args) {
        System.out.println("begin...");
        double[] areas = { 0.0, 30.0, 65.0, 30.0, 19.0, 131.0, 152.0, 62.0 };
        final CircleLayout[] circles = computeThreeCircleLayout(areas);
        System.out.println("circle[0].center=" + circles[0].center);
        System.out.println("circle[0].radius=" + circles[0].radius);
        System.out.println("circle[1].center=" + circles[1].center);
        System.out.println("circle[1].radius=" + circles[1].radius);
        System.out.println("circle[2].center=" + circles[2].center);
        System.out.println("circle[2].radius=" + circles[2].radius);
        areas = CircleGeometry.computeArea(circles);
        System.out.println("Areas using geometry:");
        for (int i = 1; i <= 7; ++i) {
            System.out.println("area " + i + " = " + areas[i]);
        }
        System.out.println();
        System.out.println("end...");
    }
}
