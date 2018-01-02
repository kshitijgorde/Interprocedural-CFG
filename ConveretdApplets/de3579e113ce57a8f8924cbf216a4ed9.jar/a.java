import java.awt.Component;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class a
{
    private final try nb;
    Vector A;
    
    void b(final d d) {
        this.A.addElement(d);
    }
    
    void remove(final Component component) {
        for (int i = 0; i < this.A.size(); ++i) {
            final d d = this.A.elementAt(i);
            if (d.equals(component)) {
                this.A.removeElement(d);
                return;
            }
        }
    }
    
    c b() {
        return new c(this.nb, this.A);
    }
    
    int size() {
        return this.A.size();
    }
    
    d[] _(final d[] array) {
        this.A.copyInto(array);
        return array;
    }
    
    a(final try try1) {
        this.nb = try1;
        this.nb = try1;
        this.A = new Vector();
    }
}
