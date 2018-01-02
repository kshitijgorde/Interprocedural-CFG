// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;

public class XSObjectListImpl implements XSObjectList
{
    public static final XSObjectList EMPTY_LIST;
    private static final int DEFAULT_SIZE = 4;
    private XSObject[] fArray;
    private int fLength;
    
    public XSObjectListImpl() {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = new XSObject[4];
        this.fLength = 0;
    }
    
    public XSObjectListImpl(final XSObject[] fArray, final int fLength) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = fArray;
        this.fLength = fLength;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public XSObject item(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fArray[n];
    }
    
    public void clear() {
        for (int i = 0; i < this.fLength; ++i) {
            this.fArray[i] = null;
        }
        this.fArray = null;
        this.fLength = 0;
    }
    
    public void add(final XSObject xsObject) {
        if (this.fLength == this.fArray.length) {
            final XSObject[] fArray = new XSObject[this.fLength + 4];
            System.arraycopy(this.fArray, 0, fArray, 0, this.fLength);
            this.fArray = fArray;
        }
        this.fArray[this.fLength++] = xsObject;
    }
    
    public void add(final int n, final XSObject xsObject) {
        this.fArray[n] = xsObject;
    }
    
    static {
        EMPTY_LIST = new XSObjectList() {
            public int getLength() {
                return 0;
            }
            
            public XSObject item(final int n) {
                return null;
            }
        };
    }
}
