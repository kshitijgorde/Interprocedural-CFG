// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.impl.xs.psvi.XSObjectList;

public class XSObjectListImpl implements XSObjectList
{
    private XSObject[] fArray;
    private int fLength;
    
    public XSObjectListImpl(final XSObject[] array, final int length) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = array;
        this.fLength = length;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public XSObject getItem(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fArray[index];
    }
}
