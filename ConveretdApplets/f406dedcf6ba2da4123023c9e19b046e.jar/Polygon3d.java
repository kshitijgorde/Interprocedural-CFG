import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Polygon3d extends Object3dAdaptor
{
    Point3d[] pol;
    Color col;
    
    public Polygon3d(final Point3d[] pol, final Color col) {
        super.centre = pol[0].add(pol[1]).add(pol[2]).scale(0.3333333333333333);
        this.pol = pol;
        this.col = col;
    }
    
    public void render(final View3d view3d) {
        final int[] array = new int[this.pol.length];
        final int[] array2 = new int[this.pol.length];
        for (int i = 0; i < this.pol.length; ++i) {
            final Point point = view3d.toPoint(this.pol[i]);
            array[i] = point.x;
            array2[i] = point.y;
        }
        view3d.g.setColor(this.col);
        view3d.g.fillPolygon(array, array2, this.pol.length);
        view3d.g.setColor(Color.black);
        view3d.g.drawPolygon(array, array2, this.pol.length);
    }
}
