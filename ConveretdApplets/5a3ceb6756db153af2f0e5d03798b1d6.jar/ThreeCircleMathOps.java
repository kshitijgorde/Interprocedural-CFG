import java.awt.geom.Point2D;

// 
// Decompiled by Procyon v0.5.30
// 

class ThreeCircleMathOps extends DoubleMathOps
{
    private final CircleLayout c1;
    private final CircleLayout c2;
    private final CircleLayout c3;
    private final Point2D.Double start;
    private final double dx;
    private final double dy;
    
    public ThreeCircleMathOps(final CircleLayout c1, final CircleLayout c2, final double r3, final Point2D.Double start, final double dx, final double dy) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = new CircleLayout();
        this.c3.radius = r3;
        this.start = start;
        final double d = Math.sqrt(dx * dx + dy * dy);
        this.dx = dx / d;
        this.dy = dy / d;
    }
    
    public Comparable f(final Comparable x) {
        final double d = (double)x;
        this.c3.center = new Point2D.Double(this.start.getX() + d * this.dx, this.start.getY() + d * this.dy);
        return new Double(CircleGeometry.computeCommonArea(this.c1, this.c2, this.c3));
    }
}
