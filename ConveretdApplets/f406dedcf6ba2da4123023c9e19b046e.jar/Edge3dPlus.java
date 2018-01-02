// 
// Decompiled by Procyon v0.5.30
// 

public class Edge3dPlus extends Edge3d
{
    Object3dList pts;
    int selectFrameNo;
    HalfSpace h;
    
    public Edge3dPlus(final Point3d point3d, final Point3d point3d2, final int n) {
        super(point3d, point3d2, n);
        this.selectFrameNo = -1;
        this.h = new HalfSpace(point3d, point3d2);
        this.pts = new Object3dList(10);
    }
    
    public boolean add(final Point3dObject3d point3dObject3d) {
        if (this.inside(point3dObject3d)) {
            this.pts.addElement(point3dObject3d);
            return true;
        }
        return false;
    }
    
    public void select(final int selectFrameNo) {
        this.selectFrameNo = selectFrameNo;
        this.extreme().select(selectFrameNo);
    }
    
    public Object3dList getPoints() {
        return this.pts;
    }
    
    public Point3dObject3d extreme() {
        Point3dObject3d point3dObject3d = null;
        double n = Double.MIN_VALUE;
        for (int i = 0; i < this.pts.size(); ++i) {
            final double dot = this.h.normal.dot((Point3d)this.pts.elementAt(i));
            if (dot > n) {
                point3dObject3d = (Point3dObject3d)this.pts.elementAt(i);
                n = dot;
            }
        }
        return point3dObject3d;
    }
}
