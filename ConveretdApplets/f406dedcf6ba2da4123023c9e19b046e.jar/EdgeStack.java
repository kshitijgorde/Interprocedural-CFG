import java.util.Enumeration;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class EdgeStack
{
    private Stack data;
    
    public EdgeStack() {
        this.data = new Stack();
    }
    
    public boolean isEmpty() {
        return this.data.isEmpty();
    }
    
    public Edge3d get() {
        return this.data.pop();
    }
    
    public void put(final Edge3d edge3d) {
        this.data.push(edge3d);
    }
    
    public void put(final Point3d point3d, final Point3d point3d2) {
        this.put(new Edge3d(point3d, point3d2));
    }
    
    public void putp(final Edge3d edge3d) {
        final int index = this.data.indexOf(edge3d);
        if (index == -1) {
            this.data.push(edge3d);
        }
        else {
            this.data.removeElementAt(index);
        }
    }
    
    public void putp(final Point3d point3d, final Point3d point3d2) {
        this.putp(new Edge3d(point3d, point3d2));
    }
    
    public void dump() {
        final Enumeration elements = this.data.elements();
        System.out.println(this.data.size());
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
        System.out.println();
    }
}
