// 
// Decompiled by Procyon v0.5.30
// 

public class GiftWrap extends HullAlgorithm
{
    public GiftWrap(final Point3dObject3d[] array) {
        super(array);
    }
    
    int index(final Point3d point3d) {
        for (int i = 0; i < super.pts.length; ++i) {
            if (point3d == super.pts[i]) {
                return i;
            }
        }
        return -1;
    }
    
    protected Point3d search2d(final Point3d point3d) {
        final int n = (super.pts[0] == point3d) ? 1 : 0;
        Point3dObject3d point3dObject3d = super.pts[n];
        HalfSpace halfSpace = new HalfSpace(point3d, point3dObject3d);
        for (int i = n + 1; i < super.pts.length; ++i) {
            if (super.pts[i] != point3d && halfSpace.inside(super.pts[i])) {
                point3dObject3d = super.pts[i];
                halfSpace = new HalfSpace(point3d, point3dObject3d);
            }
        }
        return point3dObject3d;
    }
    
    protected Point3d bottom() {
        Point3dObject3d point3dObject3d = super.pts[0];
        for (int i = 1; i < super.pts.length; ++i) {
            if (super.pts[i].y() < point3dObject3d.y()) {
                point3dObject3d = super.pts[i];
            }
        }
        return point3dObject3d;
    }
    
    public Object3dList build2D() {
        final Point3d bottom = this.bottom();
        final Object3dList list = new Object3dList(20);
        int lastFrame = 1;
        list.addElement(new Point3dObject3d(bottom, lastFrame++));
        Point3d point3d = bottom;
        do {
            final Point3d search2d = this.search2d(point3d);
            list.addElement(new Edge3d(point3d, search2d, lastFrame++));
            point3d = search2d;
        } while (point3d != bottom);
        list.lastFrame = lastFrame;
        return list;
    }
}
