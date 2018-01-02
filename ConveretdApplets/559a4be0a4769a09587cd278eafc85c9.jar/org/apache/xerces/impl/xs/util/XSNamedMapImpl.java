// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.impl.xs.psvi.XSNamedMap;

public class XSNamedMapImpl implements XSNamedMap
{
    String[] fNamespaces;
    int fNSNum;
    SymbolHash[] fMaps;
    XSObject[] fArray;
    int fLength;
    QName fName;
    
    public XSNamedMapImpl(final String namespace, final SymbolHash map) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        this.fNamespaces = new String[] { namespace };
        this.fMaps = new SymbolHash[] { map };
        this.fNSNum = 1;
    }
    
    public XSNamedMapImpl(final String[] namespaces, final SymbolHash[] maps, final int num) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        this.fNamespaces = namespaces;
        this.fMaps = maps;
        this.fNSNum = num;
    }
    
    public XSNamedMapImpl(final XSObject[] array, final int length) {
        this.fArray = null;
        this.fLength = -1;
        this.fName = new QName();
        if (length == 0) {
            this.fNSNum = 0;
            this.fLength = 0;
            return;
        }
        this.fNamespaces = new String[] { array[0].getNamespace() };
        this.fMaps = null;
        this.fNSNum = 1;
        this.fArray = array;
        this.fLength = length;
    }
    
    public synchronized int getMapLength() {
        if (this.fLength == -1) {
            this.fLength = 0;
            for (int i = 0; i < this.fNSNum; ++i) {
                this.fLength += this.fMaps[i].getLength();
            }
        }
        return this.fLength;
    }
    
    public XSObject getNSItem(String namespace, final String localName) {
        if (namespace != null) {
            namespace = namespace.intern();
        }
        int i = 0;
        while (i < this.fNSNum) {
            if (namespace == this.fNamespaces[i]) {
                if (this.fMaps != null) {
                    return (XSObject)this.fMaps[i].get(localName);
                }
                for (int j = 0; j < this.fLength; ++j) {
                    final XSObject ret = this.fArray[j];
                    if (ret.getName().equals(localName)) {
                        return ret;
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
    
    public synchronized XSObject getItem(final int index) {
        if (this.fArray == null) {
            this.getMapLength();
            this.fArray = new XSObject[this.fLength];
            int pos = 0;
            for (int i = 0; i < this.fNSNum; ++i) {
                pos += this.fMaps[i].getValues(this.fArray, pos);
            }
        }
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fArray[index];
    }
}
