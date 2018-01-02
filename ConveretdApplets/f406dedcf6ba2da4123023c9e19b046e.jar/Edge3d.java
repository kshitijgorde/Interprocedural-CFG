import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Edge3d extends Object3dAdaptor
{
    Point3d start;
    Point3d end;
    
    public Edge3d(final Point3d point3d, final Point3d end) {
        this.start = point3d;
        this.end = end;
        super.centre = point3d;
    }
    
    public Edge3d(final Point3d point3d, final Point3d point3d2, final int firstFrame) {
        this(point3d, point3d2);
        super.firstFrame = firstFrame;
    }
    
    public void render(final View3d view3d) {
        final Point point = view3d.toPoint(this.start);
        final Point point2 = view3d.toPoint(this.end);
        final Color color = view3d.getColor(this.getColorIndex(view3d, -1));
        if (color != null) {
            view3d.g.setColor(color);
            view3d.g.drawLine(point.x, point.y, point2.x, point2.y);
        }
    }
    
    public boolean equals(final Object o) {
        if (o instanceof Edge3d) {
            final Edge3d edge3d = (Edge3d)o;
            return (this.start == edge3d.end && this.end == edge3d.start) || (this.end == edge3d.end && this.start == edge3d.start);
        }
        return false;
    }
    
    public boolean inside(final Point3d point3d) {
        return new HalfSpace(this.start, this.end).inside(point3d);
    }
}
