// 
// Decompiled by Procyon v0.5.30
// 

public class DivideAndConquer extends HullAlgorithm
{
    int frameNo;
    static int leftColor;
    static int rightColor;
    
    public DivideAndConquer(final Point3dObject3d[] array) {
        super(array);
    }
    
    int[] extraColors() {
        return new int[] { DivideAndConquer.leftColor, DivideAndConquer.rightColor };
    }
    
    void sortpts() {
        int i = 1;
        int length = super.pts.length;
        while (i != 0) {
            i = 0;
            for (int j = 1; j < length; ++j) {
                final int n = j - 1;
                if (super.pts[n].x() < super.pts[j].x()) {
                    final Point3dObject3d point3dObject3d = super.pts[n];
                    super.pts[n] = super.pts[j];
                    super.pts[j] = point3dObject3d;
                    i = 1;
                }
            }
            --length;
        }
    }
    
    public Object3dList build2D() {
        this.sortpts();
        this.frameNo = 1;
        final Object3dList build2D = this.build2D(0, super.pts.length - 1);
        build2D.lastFrame = this.frameNo;
        final Object3d element = build2D.elementAt(0);
        if (element instanceof Object3dList) {
            ((Object3dList)element).setDefaultColor(DivideAndConquer.rightColor);
        }
        final Object3d element2 = build2D.elementAt(1);
        if (element2 instanceof Object3dList) {
            ((Object3dList)element2).setDefaultColor(DivideAndConquer.leftColor);
        }
        return build2D;
    }
    
    protected Object3dList build2D(final int n, final int n2) {
        if (n2 - n < 1) {
            return null;
        }
        final int n3 = (n + n2) / 2;
        final Object3dList list = new Object3dList(5);
        list.firstFrame = this.frameNo;
        final Object3dList build2D = this.build2D(n, n3);
        final Object3dList build2D2 = this.build2D(n3 + 1, n2);
        if (build2D != null) {
            list.addElement(build2D);
        }
        if (build2D2 != null) {
            list.addElement(build2D2);
        }
        ++this.frameNo;
        list.centre = new Point3d(super.pts[n3].x(), 0.0, 0.0);
        final PointStack pointStack = new PointStack();
        this.deleteFaces2D(build2D, n3 + 1, n2, pointStack);
        if (pointStack.isEmpty()) {
            pointStack.put(super.pts[n], super.pts[n]);
        }
        final PointStack pointStack2 = new PointStack();
        this.deleteFaces2D(build2D2, n, n3, pointStack2);
        if (pointStack2.isEmpty()) {
            pointStack2.put(super.pts[n2], super.pts[n2]);
        }
        list.addElement(new Edge3d(pointStack.getStart(), pointStack2.getEnd(), this.frameNo));
        list.addElement(new Edge3d(pointStack2.getStart(), pointStack.getEnd(), this.frameNo++));
        return list;
    }
    
    protected void deleteFaces2D(final Object3dList list, final int n, final int n2, final PointStack pointStack) {
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final Object3d element = list.elementAt(i);
                if (element instanceof Edge3d) {
                    final Edge3d edge3d = (Edge3d)element;
                    if (edge3d.lastFrame > this.frameNo) {
                        for (int j = n; j <= n2; ++j) {
                            if (edge3d.inside(super.pts[j])) {
                                edge3d.lastFrame = this.frameNo;
                                pointStack.put(edge3d.start, edge3d.end);
                                break;
                            }
                        }
                    }
                }
                else if (element instanceof Object3dList) {
                    this.deleteFaces2D((Object3dList)element, n, n2, pointStack);
                }
            }
        }
    }
}
