// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.Enumeration;

final class VArrayEnumerator implements Enumeration
{
    VArray varray;
    int index;
    
    public boolean hasMoreElements() {
        return this.index < this.varray.count;
    }
    
    public Object nextElement() {
        if (this.index < this.varray.count) {
            return Array.get(this.varray.array, this.index++);
        }
        throw new NoSuchElementException("VArrayEnumerator");
    }
    
    VArrayEnumerator(final VArray varray) {
        this.varray = varray;
        this.index = 0;
    }
}
