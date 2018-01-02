import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Point
{
    public double x;
    public double y;
    public double z;
    
    Point(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public final void scale(final double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
    }
    
    public final void copy(final Point point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }
    
    public final void zero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
    
    public String toString() {
        return "(" + (float)this.x + "," + (float)this.y + "," + (float)this.z + ")";
    }
    
    public final double dot(final Point point) {
        return point.x * this.x + point.y * this.y + point.z * this.z;
    }
    
    public final void minus(final Point point, final Point point2) {
        this.x = point.x - point2.x;
        this.y = point.y - point2.y;
        this.z = point.z - point2.z;
    }
    
    public final void plus(final Point point, final Point point2) {
        this.x = point.x + point2.x;
        this.y = point.y + point2.y;
        this.z = point.z + point2.z;
    }
    
    public final void plusa(final Point point, final double n, final Point point2) {
        this.x = point.x + n * point2.x;
        this.y = point.y + n * point2.y;
        this.z = point.z + n * point2.z;
    }
    
    public static double s2d(final String s, final double n) {
        if (s == null) {
            return n;
        }
        try {
            return Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static Color s2c(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        return new Color(Integer.parseInt(s, 16));
    }
    
    public void eval(final Point[] array, final double n) {
        double n2 = 1.0;
        this.zero();
        for (int i = 0; i < array.length; ++i) {
            this.plusa(this, n2, array[i]);
            n2 *= n;
        }
    }
}
