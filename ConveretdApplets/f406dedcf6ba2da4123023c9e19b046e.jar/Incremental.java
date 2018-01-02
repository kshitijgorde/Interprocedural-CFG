// 
// Decompiled by Procyon v0.5.30
// 

public class Incremental extends HullAlgorithm
{
    public Incremental(final Point3dObject3d[] array) {
        super(array);
    }
    
    int[] extraColors() {
        return new int[] { Object3dAdaptor.selectColor };
    }
    
    public Object3dList build2D() {
        int n = 1;
        final PointStack pointStack = new PointStack();
        final Object3dList list = new Object3dList(20);
        list.addElement(new Edge3d(super.pts[0], super.pts[1], n++));
        list.addElement(new Edge3d(super.pts[1], super.pts[0], n++));
        for (int i = 2; i < super.pts.length; ++i) {
            boolean b = true;
            for (int j = 0; j < list.size(); ++j) {
                final Object3d element = list.elementAt(j);
                if (element instanceof Edge3d) {
                    final Edge3d edge3d = (Edge3d)element;
                    if (edge3d.lastFrame > n && edge3d.inside(super.pts[i])) {
                        edge3d.lastFrame = n;
                        b = false;
                        pointStack.put(edge3d.start, edge3d.end);
                    }
                }
            }
            if (!b) {
                list.addElement(new Point3dObject3d(super.pts[i], n++));
                list.addElement(new Edge3d(pointStack.getStart(), super.pts[i], n));
                list.addElement(new Edge3d(super.pts[i], pointStack.getEnd(), n++));
            }
        }
        list.lastFrame = n;
        return list;
    }
}
