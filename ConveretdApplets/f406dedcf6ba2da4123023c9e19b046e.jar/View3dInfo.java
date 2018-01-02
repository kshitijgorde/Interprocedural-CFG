// 
// Decompiled by Procyon v0.5.30
// 

public class View3dInfo
{
    protected double wx;
    protected double wy;
    protected double wwidth;
    protected double wheight;
    protected double dinverse;
    public Point3d u;
    public Point3d v;
    public Point3d w;
    
    public View3dInfo() {
        this.setWindow(-1.0, -1.0, 2.0, 2.0);
    }
    
    public View3dInfo(final Point3d point3d, final Point3d point3d2) {
        this();
        this.setCamera(point3d, point3d2);
    }
    
    public View3dInfo(final Point3d point3d, final Point3d point3d2, final double dinverse) {
        this(point3d, point3d2);
        this.dinverse = dinverse;
    }
    
    public View3dInfo(final Point3d point3d, final Point3d point3d2, final boolean b) {
        this();
        this.setCamera(point3d, point3d2);
    }
    
    public View3dInfo(final Point3d camera) {
        this();
        this.setCamera(camera);
    }
    
    public View3dInfo(final Point3d point3d, final Point3d point3d2, final double dinverse, final double n, final double n2, final double n3, final double n4) {
        this.setWindow(n, n2, n3, n4);
        this.setCamera(point3d, point3d2);
        this.dinverse = dinverse;
    }
    
    public void set(final View3dInfo view3dInfo) {
        this.setWindow(view3dInfo.wx, view3dInfo.wy, view3dInfo.wwidth, view3dInfo.wheight);
        this.w = view3dInfo.w;
        this.v = view3dInfo.v;
        this.u = view3dInfo.u;
        this.dinverse = view3dInfo.dinverse;
    }
    
    public void setCamera(final Point3d point3d, final Point3d point3d2) {
        this.w = point3d.normalize();
        this.v = point3d2.subtract(this.w.scale(this.w.dot(point3d2))).normalize();
        this.u = this.v.cross(this.w);
    }
    
    public void setCamera(final Point3d point3d) {
        this.setCamera(point3d, new Point3d(0.0, 1.0, 0.0));
    }
    
    public void setWindow(final double wx, final double wy, final double wwidth, final double wheight) {
        this.wx = wx;
        this.wy = wy;
        this.wwidth = wwidth;
        this.wheight = wheight;
    }
}
