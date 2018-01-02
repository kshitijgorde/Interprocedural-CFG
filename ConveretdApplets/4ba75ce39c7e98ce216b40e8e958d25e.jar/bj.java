import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class bj extends bh
{
    protected static final Point[] D;
    
    protected Point[] d() {
        return bj.D;
    }
    
    bj(final bn bn, final Rectangle rectangle) {
        super(bn, rectangle);
    }
    
    protected int f() {
        return 3;
    }
    
    boolean a(final Point point) {
        try {
            for (int i = 0; i != super.t.length; ++i) {
                if (super.t[i].inside(point.x, point.y)) {
                    return true;
                }
            }
        }
        catch (NullPointerException ex) {}
        return false;
    }
    
    static {
        D = new Point[] { new Point(65, 0), new Point(5, 0), new Point(-35, 0), new Point(25, 0), new Point(-15, 0), new Point(45, 0), new Point(-75, 0), new Point(-55, 0) };
    }
    
    protected int h() {
        return 0;
    }
}
