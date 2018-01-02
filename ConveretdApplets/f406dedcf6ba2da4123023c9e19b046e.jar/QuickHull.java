// 
// Decompiled by Procyon v0.5.30
// 

public class QuickHull extends HullAlgorithm
{
    public QuickHull(final Point3dObject3d[] array) {
        super(array);
    }
    
    int[] extraColors() {
        return new int[] { Object3dAdaptor.selectColor };
    }
    
    void findmaxmin() {
        for (int i = 0; i < super.pts.length; ++i) {
            if (super.pts[i].x() > super.pts[0].x()) {
                final Point3dObject3d point3dObject3d = super.pts[0];
                super.pts[0] = super.pts[i];
                super.pts[i] = point3dObject3d;
            }
            if (super.pts[i].x() < super.pts[1].x()) {
                final Point3dObject3d point3dObject3d2 = super.pts[1];
                super.pts[1] = super.pts[i];
                super.pts[i] = point3dObject3d2;
            }
        }
    }
    
    public Object3dList build2D() {
        int n = 1;
        final Object3dList list = new Object3dList(20);
        this.findmaxmin();
        final Edge3dPlus edge3dPlus;
        list.addElement(edge3dPlus = new Edge3dPlus(super.pts[0], super.pts[1], n++));
        final Edge3dPlus edge3dPlus2;
        list.addElement(edge3dPlus2 = new Edge3dPlus(super.pts[1], super.pts[0], n++));
        for (int i = 2; i < super.pts.length; ++i) {
            if (!edge3dPlus.add(super.pts[i])) {
                edge3dPlus2.add(super.pts[i]);
            }
        }
        for (int j = 0; j < list.size(); ++j) {
            final Object3d element = list.elementAt(j);
            if (element instanceof Edge3dPlus) {
                final Edge3dPlus edge3dPlus3 = (Edge3dPlus)element;
                if (edge3dPlus3.lastFrame > n) {
                    final Point3dObject3d extreme = edge3dPlus3.extreme();
                    if (extreme != null) {
                        edge3dPlus3.lastFrame = n;
                        edge3dPlus3.select(n++);
                        final Edge3dPlus edge3dPlus4;
                        list.addElement(edge3dPlus4 = new Edge3dPlus(edge3dPlus3.start, extreme, n++));
                        final Edge3dPlus edge3dPlus5;
                        list.addElement(edge3dPlus5 = new Edge3dPlus(extreme, edge3dPlus3.end, n++));
                        for (int k = 0; k < edge3dPlus3.pts.size(); ++k) {
                            final Point3dObject3d point3dObject3d = (Point3dObject3d)edge3dPlus3.pts.elementAt(k);
                            if (point3dObject3d != extreme && !edge3dPlus4.add(point3dObject3d)) {
                                edge3dPlus5.add(point3dObject3d);
                            }
                        }
                    }
                }
            }
        }
        list.lastFrame = n;
        return list;
    }
}
