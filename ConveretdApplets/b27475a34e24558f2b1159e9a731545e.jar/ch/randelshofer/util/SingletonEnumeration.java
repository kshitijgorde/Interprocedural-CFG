// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.NoSuchElementException;
import java.util.Enumeration;

public class SingletonEnumeration implements Enumeration
{
    private Object object;
    
    public SingletonEnumeration(final Object object) {
        this.object = object;
    }
    
    public boolean hasMoreElements() {
        return this.object != null;
    }
    
    public synchronized Object nextElement() {
        if (this.object == null) {
            throw new NoSuchElementException();
        }
        final Object object = this.object;
        this.object = null;
        return object;
    }
}
