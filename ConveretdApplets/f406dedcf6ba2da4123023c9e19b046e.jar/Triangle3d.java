import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Triangle3d extends Object3dAdaptor
{
    Point3d[] tri;
    int col;
    HalfSpace h;
    static int backFaceColor;
    
    public Triangle3d(final Point3d[] tri, final int col) {
        this.tri = tri;
        this.col = col;
        this.computeHalfSpace();
    }
    
    public Triangle3d(final Point3d point3d, final Point3d point3d2, final Point3d point3d3, final int firstFrame) {
        (this.tri = new Point3d[3])[0] = point3d;
        this.tri[1] = point3d2;
        this.tri[2] = point3d3;
        this.col = -1;
        super.firstFrame = firstFrame;
        this.computeHalfSpace();
    }
    
    private void computeHalfSpace() {
        this.h = new HalfSpace(this.tri[0], this.tri[1], this.tri[2]);
        super.centre = this.tri[0].add(this.tri[1]).add(this.tri[2]).scale(0.3333333333333333);
    }
    
    public void render(final View3d view3d) {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        for (int i = 0; i < this.tri.length; ++i) {
            final Point point = view3d.toPoint(this.tri[i]);
            array[i] = point.x;
            array2[i] = point.y;
        }
        view3d.g.setColor(Color.black);
        view3d.g.drawPolygon(array, array2, 3);
        view3d.g.drawLine(array[2], array2[2], array[0], array2[0]);
    }
    
    public boolean inside(final Point3d point3d) {
        return this.h.inside(point3d);
    }
}
