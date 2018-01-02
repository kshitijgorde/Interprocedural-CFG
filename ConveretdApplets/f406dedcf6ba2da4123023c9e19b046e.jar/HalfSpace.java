// 
// Decompiled by Procyon v0.5.30
// 

public class HalfSpace
{
    Point3d normal;
    double d;
    
    public HalfSpace(final Point3d point3d, final Point3d point3d2, final Point3d point3d3) {
        this.normal = point3d2.subtract(point3d).cross(point3d3.subtract(point3d)).normalize();
        this.d = this.normal.dot(point3d);
    }
    
    public HalfSpace(final Point3d point3d, final Point3d point3d2) {
        this.normal = point3d2.subtract(point3d).cross(Point3d.k).normalize();
        this.d = this.normal.dot(point3d);
    }
    
    public boolean inside(final Point3d point3d) {
        return this.normal.dot(point3d) > this.d;
    }
    
    public double zint(final Point3d point3d) {
        return (this.d - this.normal.x() * point3d.x() - this.normal.y() * point3d.y()) / this.normal.z();
    }
}
