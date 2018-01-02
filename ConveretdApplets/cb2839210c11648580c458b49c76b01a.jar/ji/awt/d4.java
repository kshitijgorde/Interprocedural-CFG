// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import ji.util.d;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;

public class d4 extends Polygon
{
    public d4() {
    }
    
    public d4(final int[] array, final int[] array2, final int n) {
        super(array, array2, n);
    }
    
    public d4(final Polygon polygon) {
        super(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
    
    public d4(final Rectangle rectangle) {
        super.addPoint(rectangle.x, rectangle.y);
        super.addPoint(rectangle.x + rectangle.width, rectangle.y);
        super.addPoint(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        super.addPoint(rectangle.x, rectangle.y + rectangle.height);
    }
    
    public final d4 a() {
        return new d4(super.xpoints, super.ypoints, super.npoints);
    }
    
    public final void a(final d5 d5) {
        super.addPoint((int)d5.a, (int)d5.b);
    }
    
    public final void a(final Point point) {
        super.addPoint(point.x, point.y);
    }
    
    public final void a(final d4 d4) {
        for (int b = d4.b(), i = 0; i < b; ++i) {
            final d5 a = d4.a(i);
            super.addPoint((int)a.a, (int)a.b);
        }
    }
    
    public final void a(final da da) {
        super.addPoint((int)da.a, (int)da.b);
        super.addPoint((int)(da.a + da.c), (int)da.b);
        super.addPoint((int)(da.a + da.c), (int)(da.b + da.d));
        super.addPoint((int)da.a, (int)(da.b + da.d));
    }
    
    public final int b() {
        return super.npoints;
    }
    
    public final int[] c() {
        return super.xpoints;
    }
    
    public final int[] d() {
        return super.ypoints;
    }
    
    public final d5 a(final int n) {
        return new d5(super.xpoints[n], super.ypoints[n]);
    }
    
    public final d5[] e() {
        final d5[] array = new d5[super.npoints];
        for (int i = 0; i < super.npoints; ++i) {
            array[i] = new d5(super.xpoints[i], super.ypoints[i]);
        }
        return array;
    }
    
    public String toString() {
        String s = "Polygon";
        try {
            s = String.valueOf(String.valueOf(s)).concat("(");
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.f())));
            s = String.valueOf(String.valueOf(s)).concat(")");
        }
        catch (Exception ex) {}
        return s;
    }
    
    public String f() {
        String s = "";
        try {
            for (int i = 0; i < super.npoints; ++i) {
                if (i > 0) {
                    s = String.valueOf(String.valueOf(s)).concat(", ");
                }
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("(").append(super.xpoints[i]).append(",").append(super.ypoints[i]).append(")"))))));
            }
        }
        catch (Exception ex) {}
        return d.bc(s);
    }
}
