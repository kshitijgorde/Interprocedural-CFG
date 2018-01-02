import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Misc
{
    public static final Color InterpolateColour(final Color color, final Color color2, final long n, final long n2) {
        if (n < 0L) {
            return color;
        }
        if (n >= n2) {
            return color2;
        }
        return new Color((int)(color.getRed() + (color2.getRed() - color.getRed()) * n / n2), (int)(color.getGreen() + (color2.getGreen() - color.getGreen()) * n / n2), (int)(color.getBlue() + (color2.getBlue() - color.getBlue()) * n / n2));
    }
    
    public static final int InterpolateInt(final int n, final int n2, final long n3, final long n4) {
        if (n3 < 0L) {
            return n;
        }
        if (n3 >= n4) {
            return n2;
        }
        return (int)(n + (n2 - n) * n3 / n4);
    }
    
    public static final Point InterpolatePoint(final Point point, final Point point2, final long n, final long n2) {
        if (n < 0L) {
            return point;
        }
        if (n >= n2) {
            return point2;
        }
        return new Point((int)(point.x + (point2.x - point.x) * n / n2), (int)(point.y + (point2.y - point.y) * n / n2));
    }
    
    public static final Color initColour(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        try {
            intValue = Integer.valueOf(stringTokenizer.nextToken());
            intValue2 = Integer.valueOf(stringTokenizer.nextToken());
            intValue3 = Integer.valueOf(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        return new Color(intValue, intValue2, intValue3);
    }
    
    public static final Rectangle InterpolateRectangle(final Rectangle rectangle, final Rectangle rectangle2, final long n, final long n2) {
        if (n < 0L) {
            return rectangle;
        }
        if (n >= n2) {
            return rectangle2;
        }
        return new Rectangle((int)(rectangle.x + (rectangle2.x - rectangle.x) * n / n2), (int)(rectangle.y + (rectangle2.y - rectangle.y) * n / n2), (int)(rectangle.width + (rectangle2.width - rectangle.width) * n / n2), (int)(rectangle.height + (rectangle2.height - rectangle.height) * n / n2));
    }
}
