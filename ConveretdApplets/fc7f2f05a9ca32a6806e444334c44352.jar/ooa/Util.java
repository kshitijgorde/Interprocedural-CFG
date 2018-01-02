// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

public abstract class Util
{
    public static double toRadians(final double deg) {
        return deg * 3.141592653589793 / 180;
    }
    
    public static double toDegrees(final double deg) {
        return deg * 180 / 3.141592653589793;
    }
    
    public static boolean anglesSimilar(final double a1, final double a2, final double range) {
        final double difference = differenceBetweenAngles(a1, a2);
        return difference < range;
    }
    
    public static double differenceBetweenAngles(final double a1, final double a2) {
        final double difference = Math.abs(a1 - a2);
        return Math.min(difference, 360 - difference);
    }
    
    public static double fixDegrees(final double degrees) {
        return degrees - Math.floor(degrees / 360) * 360;
    }
    
    public static boolean linesIntersect(final double X1, final double Y1, final double X2, final double Y2, final double X3, final double Y3, final double X4, final double Y4) {
        return relativeCCW(X1, Y1, X2, Y2, X3, Y3) * relativeCCW(X1, Y1, X2, Y2, X4, Y4) <= 0 && relativeCCW(X3, Y3, X4, Y4, X1, Y1) * relativeCCW(X3, Y3, X4, Y4, X2, Y2) <= 0;
    }
    
    public static int relativeCCW(final double X1, final double Y1, double X2, double Y2, double PX, double PY) {
        X2 -= X1;
        Y2 -= Y1;
        PX -= X1;
        PY -= Y1;
        double ccw = PX * Y2 - PY * X2;
        if (ccw == 0.0) {
            ccw = PX * X2 + PY * Y2;
            if (ccw > 0.0) {
                PX -= X2;
                PY -= Y2;
                ccw = PX * X2 + PY * Y2;
                if (ccw < 0.0) {
                    ccw = 0.0;
                }
            }
        }
        return (ccw < 0.0) ? -1 : ((ccw > 0.0) ? 1 : 0);
    }
}
