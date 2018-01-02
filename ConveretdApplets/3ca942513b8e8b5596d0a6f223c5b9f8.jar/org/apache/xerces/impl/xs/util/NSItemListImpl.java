// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import java.util.Vector;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSNamespaceItemList;

public class NSItemListImpl implements XSNamespaceItemList
{
    private XSNamespaceItem[] fArray;
    private int fLength;
    private Vector fVector;
    
    public NSItemListImpl(final Vector fVector) {
        this.fArray = null;
        this.fLength = 0;
        this.fVector = fVector;
        this.fLength = fVector.size();
    }
    
    public NSItemListImpl(final XSNamespaceItem[] fArray, final int fLength) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = fArray;
        this.fLength = fLength;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public XSNamespaceItem item(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        if (this.fVector != null) {
            return this.fVector.elementAt(n);
        }
        return this.fArray[n];
    }
}
