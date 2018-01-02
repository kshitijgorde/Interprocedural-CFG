// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import java.util.Vector;
import org.apache.xerces.xs.StringList;

public class StringListImpl implements StringList
{
    public static final StringList EMPTY_LIST;
    private String[] fArray;
    private int fLength;
    private Vector fVector;
    
    public StringListImpl(final Vector fVector) {
        this.fArray = null;
        this.fLength = 0;
        this.fVector = fVector;
        this.fLength = ((fVector == null) ? 0 : fVector.size());
    }
    
    public StringListImpl(final String[] fArray, final int fLength) {
        this.fArray = null;
        this.fLength = 0;
        this.fArray = fArray;
        this.fLength = fLength;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public boolean contains(final String s) {
        if (this.fVector != null) {
            return this.fVector.contains(s);
        }
        if (s == null) {
            for (int i = 0; i < this.fLength; ++i) {
                if (this.fArray[i] == null) {
                    return true;
                }
            }
        }
        else {
            for (int j = 0; j < this.fLength; ++j) {
                if (s.equals(this.fArray[j])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String item(final int n) {
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        if (this.fVector != null) {
            return this.fVector.elementAt(n);
        }
        return this.fArray[n];
    }
    
    static {
        EMPTY_LIST = new StringList() {
            public int getLength() {
                return 0;
            }
            
            public boolean contains(final String s) {
                return false;
            }
            
            public String item(final int n) {
                return null;
            }
        };
    }
}
