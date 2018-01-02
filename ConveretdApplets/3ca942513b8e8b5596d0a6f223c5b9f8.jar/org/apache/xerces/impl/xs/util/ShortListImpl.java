// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xs.XSException;
import org.apache.xerces.xs.ShortList;

public class ShortListImpl implements ShortList
{
    private short[] fArray;
    private int fLength;
    
    public ShortListImpl(final short[] fArray, final int fLength) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = fArray;
        this.fLength = fLength;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public boolean contains(final short n) {
        for (int i = 0; i < this.fLength; ++i) {
            if (this.fArray[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public short item(final int n) throws XSException {
        if (n < 0 || n >= this.fLength) {
            throw new XSException((short)2, null);
        }
        return this.fArray[n];
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof ShortList)) {
            return false;
        }
        final ShortList list = (ShortList)o;
        if (this.fLength != list.getLength()) {
            return false;
        }
        for (int i = 0; i < this.fLength; ++i) {
            if (this.fArray[i] != list.item(i)) {
                return false;
            }
        }
        return true;
    }
}
