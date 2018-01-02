import java.awt.Point;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Points3d extends Object3dAdaptor
{
    public static int color;
    protected Point3dObject3d[] pts;
    protected Vector labels;
    private Object3dList model;
    
    public Points3d() {
        super.centre = Point3d.o;
        this.pts = null;
        this.labels = null;
    }
    
    public Points3d set(final Point3dObject3d[] pts) {
        this.pts = pts;
        this.labels = null;
        this.model = new Object3dList(pts.length);
        for (int i = 0; i < pts.length; ++i) {
            this.model.addElement(pts[i]);
        }
        this.model.lastFrame = 0;
        return this;
    }
    
    public void setLabels(final Vector labels) {
        this.labels = labels;
    }
    
    public void render(final View3d view3d) {
        if (view3d.getFrameNo() == 0 && this.pts != null) {
            this.model.render(view3d);
        }
        else {
            final Color color = view3d.getColor(Points3d.color);
            if (color != null && this.pts != null) {
                view3d.g.setColor(color);
                for (int i = 0; i < this.pts.length; ++i) {
                    final Point point = view3d.toPoint(this.pts[i]);
                    view3d.g.drawRect(point.x - 1, point.y - 1, 3, 3);
                }
            }
            if (this.labels != null) {
                view3d.g.setColor(Color.black);
                final StringBuffer sb = new StringBuffer(" ");
                for (int j = 0; j < this.labels.size(); ++j) {
                    final int intValue = this.labels.elementAt(j);
                    if (intValue >= 0 && intValue < this.pts.length) {
                        sb.setCharAt(0, (char)(65 + j));
                        view3d.drawStringBelow(sb.toString(), this.pts[intValue]);
                    }
                }
            }
        }
    }
}
