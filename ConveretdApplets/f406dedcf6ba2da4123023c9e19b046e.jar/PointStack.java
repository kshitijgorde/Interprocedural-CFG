import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class PointStack
{
    private Vector starts;
    private Vector ends;
    
    public PointStack() {
        this.starts = new Vector();
        this.ends = new Vector();
    }
    
    public boolean isEmpty() {
        return this.starts.isEmpty();
    }
    
    public void put(final Point3d point3d, final Point3d point3d2) {
        if (!this.ends.removeElement(point3d)) {
            this.starts.addElement(point3d);
        }
        if (point3d == point3d2 || !this.starts.removeElement(point3d2)) {
            this.ends.addElement(point3d2);
        }
    }
    
    public Point3d getStart() {
        final Point3d point3d = this.starts.lastElement();
        this.starts.removeAllElements();
        return point3d;
    }
    
    public Point3d getEnd() {
        final Point3d point3d = this.ends.lastElement();
        this.ends.removeAllElements();
        return point3d;
    }
}
