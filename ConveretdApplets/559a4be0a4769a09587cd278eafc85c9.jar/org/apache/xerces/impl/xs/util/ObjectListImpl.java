// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import java.util.Vector;
import org.apache.xerces.impl.xs.psvi.ObjectList;

public class ObjectListImpl implements ObjectList
{
    private Object[] fArray;
    private int fLength;
    private Vector fVector;
    
    public ObjectListImpl(final Vector v) {
        this.fArray = null;
        this.fLength = 0;
        this.fVector = v;
        this.fLength = v.size();
    }
    
    public ObjectListImpl(final Object[] array, final int length) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = array;
        this.fLength = length;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public Object item(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        if (this.fVector != null) {
            return this.fVector.elementAt(index);
        }
        return this.fArray[index];
    }
}
