import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dx
{
    private Vector p;
    
    public final synchronized Object p() {
        if (this.p.size() == 0) {
            return null;
        }
        final Object element = this.p.elementAt(0);
        this.p.removeElementAt(0);
        return element;
    }
    
    public final synchronized void p(final Object o) {
        this.p.addElement(o);
    }
    
    public final void p() {
        this.p.setSize(0);
    }
    
    public final int p() {
        return this.p.size();
    }
    
    public dx() {
        this.p = new Vector();
    }
}
