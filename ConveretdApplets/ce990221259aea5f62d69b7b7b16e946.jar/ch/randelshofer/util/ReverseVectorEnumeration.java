// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.Vector;
import java.util.Enumeration;

public class ReverseVectorEnumeration implements Enumeration
{
    private Vector vector;
    private int index;
    
    public ReverseVectorEnumeration(final Vector vector) {
        this.vector = vector;
        this.index = vector.size() - 1;
    }
    
    public boolean hasMoreElements() {
        return this.index >= 0;
    }
    
    public Object nextElement() {
        return this.vector.elementAt(this.index--);
    }
}
