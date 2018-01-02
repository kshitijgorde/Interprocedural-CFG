// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.event;

import java.util.Vector;
import java.util.Enumeration;

public class VectorEnumeration implements Enumeration
{
    private Vector _vector;
    private int _count;
    
    public VectorEnumeration(final Vector v) {
        this._vector = v;
        this._count = ((v != null) ? v.size() : 0);
    }
    
    public boolean hasMoreElements() {
        return this._count > 0;
    }
    
    public Object nextElement() {
        Object obj = null;
        synchronized (this._vector) {
            if (this._count > 0) {
                final Vector vector = this._vector;
                final int count = this._count - 1;
                this._count = count;
                obj = vector.elementAt(count);
            }
        }
        // monitorexit(this._vector)
        return obj;
    }
}
