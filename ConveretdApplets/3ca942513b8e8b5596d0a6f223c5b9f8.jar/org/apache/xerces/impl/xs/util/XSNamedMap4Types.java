// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.util.SymbolHash;

public class XSNamedMap4Types extends XSNamedMapImpl
{
    short fType;
    
    public XSNamedMap4Types(final String s, final SymbolHash symbolHash, final short fType) {
        super(s, symbolHash);
        this.fType = fType;
    }
    
    public XSNamedMap4Types(final String[] array, final SymbolHash[] array2, final int n, final short fType) {
        super(array, array2, n);
        this.fType = fType;
    }
    
    public synchronized int getLength() {
        if (super.fLength == -1) {
            int n = 0;
            for (int i = 0; i < super.fNSNum; ++i) {
                n += super.fMaps[i].getLength();
            }
            int n2 = 0;
            final XSObject[] array = new XSObject[n];
            for (int j = 0; j < super.fNSNum; ++j) {
                n2 += super.fMaps[j].getValues(array, n2);
            }
            super.fLength = 0;
            super.fArray = new XSObject[n];
            for (int k = 0; k < n; ++k) {
                final XSTypeDefinition xsTypeDefinition = (XSTypeDefinition)array[k];
                if (xsTypeDefinition.getTypeCategory() == this.fType) {
                    super.fArray[super.fLength++] = xsTypeDefinition;
                }
            }
        }
        return super.fLength;
    }
    
    public XSObject itemByName(String intern, final String s) {
        if (intern != null) {
            intern = intern.intern();
        }
        int i = 0;
        while (i < super.fNSNum) {
            if (intern == super.fNamespaces[i]) {
                final XSTypeDefinition xsTypeDefinition = (XSTypeDefinition)super.fMaps[i].get(s);
                if (xsTypeDefinition.getTypeCategory() == this.fType) {
                    return xsTypeDefinition;
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
        if (super.fArray == null) {
            this.getLength();
        }
        if (n < 0 || n >= super.fLength) {
            return null;
        }
        return super.fArray[n];
    }
}
