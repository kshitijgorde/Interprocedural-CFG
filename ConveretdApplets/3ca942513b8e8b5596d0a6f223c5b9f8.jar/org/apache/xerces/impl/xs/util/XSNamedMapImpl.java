// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSNamedMap;

public class XSNamedMapImpl implements XSNamedMap
{
    public static final XSNamedMap EMPTY_MAP;
    String[] fNamespaces;
    int fNSNum;
    SymbolHash[] fMaps;
    XSObject[] fArray;
    int fLength;
    QName fName;
    
    public XSNamedMapImpl(final String s, final SymbolHash symbolHash) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        this.fNamespaces = new String[] { s };
        this.fMaps = new SymbolHash[] { symbolHash };
        this.fNSNum = 1;
    }
    
    public XSNamedMapImpl(final String[] fNamespaces, final SymbolHash[] fMaps, final int fnsNum) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        this.fNamespaces = fNamespaces;
        this.fMaps = fMaps;
        this.fNSNum = fnsNum;
    }
    
    public XSNamedMapImpl(final XSObject[] fArray, final int fLength) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        if (fLength == 0) {
            this.fNSNum = 0;
            this.fLength = 0;
            return;
        }
        this.fNamespaces = new String[] { fArray[0].getNamespace() };
        this.fMaps = null;
        this.fNSNum = 1;
        this.fArray = fArray;
        this.fLength = fLength;
    }
    
    public synchronized int getLength() {
        if (this.fLength == -1) {
            this.fLength = 0;
            for (int i = 0; i < this.fNSNum; ++i) {
                this.fLength += this.fMaps[i].getLength();
            }
        }
        return this.fLength;
    }
    
    public XSObject itemByName(String intern, final String s) {
        if (intern != null) {
            intern = intern.intern();
        }
        int i = 0;
        while (i < this.fNSNum) {
            if (intern == this.fNamespaces[i]) {
                if (this.fMaps != null) {
                    return (XSObject)this.fMaps[i].get(s);
                }
                for (int j = 0; j < this.fLength; ++j) {
                    final XSObject xsObject = this.fArray[j];
                    if (xsObject.getName().equals(s)) {
                        return xsObject;
                    }
                }
                return null;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public synchronized XSObject item(final int n) {
        if (this.fArray == null) {
            this.getLength();
            this.fArray = new XSObject[this.fLength];
            int n2 = 0;
            for (int i = 0; i < this.fNSNum; ++i) {
                n2 += this.fMaps[i].getValues(this.fArray, n2);
            }
        }
        if (n < 0 || n >= this.fLength) {
            return null;
        }
        return this.fArray[n];
    }
    
    static {
        EMPTY_MAP = new XSNamedMap() {
            public int getLength() {
                return 0;
            }
            
            public XSObject itemByName(final String s, final String s2) {
                return null;
            }
            
            public XSObject item(final int n) {
                return null;
            }
        };
    }
}
