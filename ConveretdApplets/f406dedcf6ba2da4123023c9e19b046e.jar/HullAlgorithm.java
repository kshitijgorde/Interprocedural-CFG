// 
// Decompiled by Procyon v0.5.30
// 

public abstract class HullAlgorithm
{
    Point3dObject3d[] pts;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BEYOND = 2;
    public static final int BEHIND = 3;
    public static final int BETWEEN = 4;
    public static final int ORIGIN = 5;
    public static final int DESTINATION = 6;
    
    int[] extraColors() {
        return new int[0];
    }
    
    public HullAlgorithm(final Point3dObject3d[] pts) {
        this.pts = pts;
    }
    
    public abstract Object3dList build2D();
    
    public static int classify(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n3 - n;
        final double n8 = n5 - n;
        final double n9 = n4 - n2;
        final double n10 = n6 - n2;
        final double n11 = n7 * n10 - n8 * n9;
        if (n11 > 0.0) {
            return 0;
        }
        if (n11 < 0.0) {
            return 1;
        }
        if (n7 * n8 < 0.0 || n9 * n10 < 0.0) {
            return 3;
        }
        if (n7 * n7 + n9 * n9 < n8 * n8 + n10 * n10) {
            return 2;
        }
        if (n5 == n && n6 == n2) {
            return 5;
        }
        if (n5 == n3 && n6 == n4) {
            return 6;
        }
        return 4;
    }
    
    public static int classify(final Point3d point3d, final Point3d point3d2, final Point3d point3d3) {
        return classify(point3d.x(), point3d.y(), point3d2.x(), point3d2.y(), point3d3.x(), point3d3.y());
    }
    
    public static boolean colinear(final Point3d point3d, final Point3d point3d2, final Point3d point3d3) {
        return point3d.x() * (point3d2.y() - point3d3.y()) + point3d2.x() * (point3d3.y() - point3d.y()) + point3d3.x() * (point3d.y() - point3d2.y()) == 0.0;
    }
}
