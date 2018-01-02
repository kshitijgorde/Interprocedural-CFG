// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public final class r extends Vector
{
    public r() {
    }
    
    public r(final Object o, final Object o2) {
        this(o);
        this.addElement(o2);
    }
    
    private r(final Object o) {
        this();
        this.addElement(o);
    }
    
    public final boolean equals(final Object o) {
        if (!(o instanceof Vector)) {
            return false;
        }
        final Vector vector = (Vector)o;
        if (this.size() != vector.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); ++i) {
            if (!this.elementAt(i).equals(vector.elementAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        int n = 0;
        for (int i = 0; i < this.size(); ++i) {
            n += this.elementAt(i).hashCode() + i;
        }
        return n;
    }
    
    public final void a(final Vector vector) {
        this.removeAllElements();
        for (int i = 0; i < vector.size(); ++i) {
            this.addElement(vector.elementAt(i));
        }
    }
}
