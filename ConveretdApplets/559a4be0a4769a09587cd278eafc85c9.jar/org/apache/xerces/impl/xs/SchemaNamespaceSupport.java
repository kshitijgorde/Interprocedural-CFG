// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.util.NamespaceSupport;

public class SchemaNamespaceSupport extends NamespaceSupport
{
    public SchemaNamespaceSupport() {
    }
    
    public SchemaNamespaceSupport(final SchemaNamespaceSupport nSupport) {
        super.fNamespaceSize = nSupport.fNamespaceSize;
        if (super.fNamespace.length < super.fNamespaceSize) {
            super.fNamespace = new String[super.fNamespaceSize];
        }
        System.arraycopy(nSupport.fNamespace, 0, super.fNamespace, 0, super.fNamespaceSize);
        super.fCurrentContext = nSupport.fCurrentContext;
        if (super.fContext.length <= super.fCurrentContext) {
            super.fContext = new int[super.fCurrentContext + 1];
        }
        System.arraycopy(nSupport.fContext, 0, super.fContext, 0, super.fCurrentContext + 1);
    }
    
    public void setEffectiveContext(final String[] namespaceDecls) {
        if (namespaceDecls == null || namespaceDecls.length == 0) {
            return;
        }
        this.pushContext();
        final int newSize = super.fNamespaceSize + namespaceDecls.length;
        if (super.fNamespace.length < newSize) {
            final String[] tempNSArray = new String[newSize];
            System.arraycopy(super.fNamespace, 0, tempNSArray, 0, super.fNamespace.length);
            super.fNamespace = tempNSArray;
        }
        System.arraycopy(namespaceDecls, 0, super.fNamespace, super.fNamespaceSize, namespaceDecls.length);
        super.fNamespaceSize = newSize;
    }
    
    public String[] getEffectiveLocalContext() {
        String[] returnVal = null;
        if (super.fCurrentContext >= 3) {
            final int bottomLocalContext = super.fContext[3];
            final int copyCount = super.fNamespaceSize - bottomLocalContext;
            if (copyCount > 0) {
                returnVal = new String[copyCount];
                System.arraycopy(super.fNamespace, bottomLocalContext, returnVal, 0, copyCount);
            }
        }
        return returnVal;
    }
    
    public void makeGlobal() {
        if (super.fCurrentContext >= 3) {
            super.fCurrentContext = 3;
            super.fNamespaceSize = super.fContext[3];
        }
    }
}
