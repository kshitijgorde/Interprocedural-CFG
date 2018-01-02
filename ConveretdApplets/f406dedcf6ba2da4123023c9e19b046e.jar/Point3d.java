import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class Point3d
{
    public double[] v;
    public static Point3d o;
    public static Point3d i;
    public static Point3d j;
    public static Point3d k;
    public static Point3d ijk;
    private static Random random;
    
    public Point3d() {
        this.v = new double[3];
    }
    
    public Point3d(final double n, final double n2, final double n3) {
        this();
        this.v[0] = n;
        this.v[1] = n2;
        this.v[2] = n3;
    }
    
    public static Point3d fromSpherical(final double n, final double n2, final double n3) {
        return new Point3d(n * Math.cos(n2) * Math.cos(n3), n * Math.sin(n2) * Math.cos(n3), n * Math.sin(n3));
    }
    
    public static Point3d fromCylindrical(final double n, final double n2, final double n3) {
        return new Point3d(n * Math.cos(n2), n3, n * Math.sin(n2));
    }
    
    public double x() {
        return this.v[0];
    }
    
    public double y() {
        return this.v[1];
    }
    
    public double z() {
        return this.v[2];
    }
    
    public Point3d add(final Point3d point3d) {
        final Point3d point3d2 = new Point3d();
        for (int i = 0; i < 3; ++i) {
            point3d2.v[i] = this.v[i] + point3d.v[i];
        }
        return point3d2;
    }
    
    public Point3d subtract(final Point3d point3d) {
        final Point3d point3d2 = new Point3d();
        for (int i = 0; i < 3; ++i) {
            point3d2.v[i] = this.v[i] - point3d.v[i];
        }
        return point3d2;
    }
    
    public Point3d scale(final double n) {
        final Point3d point3d = new Point3d();
        for (int i = 0; i < 3; ++i) {
            point3d.v[i] = n * this.v[i];
        }
        return point3d;
    }
    
    public Point3d scale(final double n, final double n2, final double n3) {
        return new Point3d(n * this.v[0], n2 * this.v[1], n3 * this.v[2]);
    }
    
    public double dot(final Point3d point3d) {
        double n = 0.0;
        for (int i = 0; i < 3; ++i) {
            n += this.v[i] * point3d.v[i];
        }
        return n;
    }
    
    public Point3d normalize() {
        return this.scale(1.0 / this.length());
    }
    
    public double length() {
        return Math.sqrt(this.dot(this));
    }
    
    public Point3d cross(final Point3d point3d) {
        return new Point3d(this.v[1] * point3d.v[2] - point3d.v[1] * this.v[2], this.v[2] * point3d.v[0] - point3d.v[2] * this.v[0], this.v[0] * point3d.v[1] - point3d.v[0] * this.v[1]);
    }
    
    public static void setSeed(final long seed) {
        Point3d.random.setSeed(seed);
    }
    
    public static Point3d random() {
        return new Point3d(Point3d.random.nextDouble(), Point3d.random.nextDouble(), Point3d.random.nextDouble());
    }
    
    public static Point3d randomGaussian() {
        return new Point3d(Point3d.random.nextGaussian(), Point3d.random.nextGaussian(), Point3d.random.nextGaussian());
    }
    
    public static Point3d randomInSphere() {
        Point3d subtract;
        do {
            subtract = random().scale(2.0).subtract(Point3d.ijk);
        } while (subtract.dot(subtract) > 1.0);
        return subtract;
    }
    
    public static Point3d randomInCircle() {
        Point3d scale;
        do {
            scale = random().scale(2.0).subtract(Point3d.ijk).scale(1.0, 1.0, 0.0);
        } while (scale.dot(scale) > 1.0);
        return scale;
    }
    
    public static Point3d randomOnSphere() {
        return randomInSphere().normalize();
    }
    
    public static Point3d randomOnCircle() {
        return randomInCircle().normalize();
    }
    
    static {
        Point3d.o = new Point3d(0.0, 0.0, 0.0);
        Point3d.i = new Point3d(1.0, 0.0, 0.0);
        Point3d.j = new Point3d(0.0, 1.0, 0.0);
        Point3d.k = new Point3d(0.0, 0.0, 1.0);
        Point3d.ijk = new Point3d(1.0, 1.0, 1.0);
        Point3d.random = new Random();
    }
}
