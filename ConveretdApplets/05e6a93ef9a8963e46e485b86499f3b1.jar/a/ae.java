// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;
import java.util.Enumeration;

final class ae implements Enumeration
{
    private Object[] q;
    private int q;
    
    public final boolean hasMoreElements() {
        return this.q < this.q.length && this.q[this.q] != null;
    }
    
    public final Object nextElement() {
        if (this.q < this.q.length) {
            return this.q[this.q++];
        }
        throw new NoSuchElementException();
    }
    
    public ae(B b) {
        this.q = (b = b).q;
    }
}
