// 
// Decompiled by Procyon v0.5.30
// 

package ifs;

import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;

public class AffineMap
{
    private final double a;
    private final double b;
    private final double e;
    private final double c;
    private final double d;
    private final double f;
    public final double x0;
    public final double y0;
    public final double x1;
    public final double y1;
    public final double x2;
    public final double y2;
    public final double x3;
    public final double y3;
    public final double area;
    public final GeneralPath outline;
    
    public AffineMap(final double x0, final double y0, final double x1, final double y1, final double x2, final double y2) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.e = x0;
        this.f = y0;
        final double v1_x = x1 - x0;
        final double v1_y = y1 - y0;
        final double v2_x = x2 - x0;
        final double v2_y = y2 - y0;
        this.x3 = v1_x + v2_x + x0;
        this.y3 = v1_y + v2_y + y0;
        this.a = v1_x;
        this.b = v2_x;
        this.c = v1_y;
        this.d = v2_y;
        this.area = Math.abs(this.a * this.d - this.b * this.c);
        if (this.a * this.a + this.b * this.b < 1.0E-20 || this.c * this.c + this.d * this.d < 1.0E-20) {
            throw new IllegalArgumentException();
        }
        (this.outline = new GeneralPath()).moveTo((float)x0, (float)y0);
        this.outline.lineTo((float)x1, (float)y1);
        this.outline.lineTo((float)this.x3, (float)this.y3);
        this.outline.lineTo((float)x2, (float)y2);
        this.outline.closePath();
    }
    
    public void apply(final Point2D.Double pt) {
        final double x = pt.x;
        final double y = pt.y;
        pt.x = this.a * x + this.b * y + this.e;
        pt.y = this.c * x + this.d * y + this.f;
    }
    
    public boolean containsPoint(final double x, final double y) {
        return this.outline.contains(x, y);
    }
}
