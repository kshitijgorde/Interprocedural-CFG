// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.util.SymbolHash;

public class XSNamedMap4Types extends XSNamedMapImpl
{
    short fType;
    
    public XSNamedMap4Types(final String namespace, final SymbolHash map, final short type) {
        super(namespace, map);
        this.fType = type;
    }
    
    public XSNamedMap4Types(final String[] namespaces, final SymbolHash[] maps, final int num, final short type) {
        super(namespaces, maps, num);
        this.fType = type;
    }
    
    public synchronized int getMapLength() {
        if (super.fLength == -1) {
            int length = 0;
            for (int i = 0; i < super.fNSNum; ++i) {
                length += super.fMaps[i].getLength();
            }
            int pos = 0;
            final XSObject[] array = new XSObject[length];
            for (int j = 0; j < super.fNSNum; ++j) {
                pos += super.fMaps[j].getValues(array, pos);
            }
            super.fLength = 0;
            super.fArray = new XSObject[length];
            for (int k = 0; k < length; ++k) {
                final XSTypeDefinition type = (XSTypeDefinition)array[k];
                if (type.getTypeCategory() == this.fType) {
                    super.fArray[super.fLength++] = type;
                }
            }
        }
        return super.fLength;
    }
    
    public XSObject getNSItem(String namespace, final String localName) {
        if (namespace != null) {
            namespace = namespace.intern();
        }
        int i = 0;
        while (i < super.fNSNum) {
            if (namespace == super.fNamespaces[i]) {
                final XSTypeDefinition type = (XSTypeDefinition)super.fMaps[i].get(localName);
                if (type.getTypeCategory() == this.fType) {
                    return type;
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
        if (super.fArray == null) {
            this.getMapLength();
        }
        if (index < 0 || index >= super.fLength) {
            return null;
        }
        return super.fArray[index];
    }
}
