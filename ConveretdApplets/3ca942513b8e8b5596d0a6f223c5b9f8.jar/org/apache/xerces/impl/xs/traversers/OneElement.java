// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

class OneElement
{
    public Container attrList;
    public boolean allowNonSchemaAttr;
    
    public OneElement(final Container container) {
        this(container, true);
    }
    
    public OneElement(final Container attrList, final boolean allowNonSchemaAttr) {
        this.attrList = attrList;
        this.allowNonSchemaAttr = allowNonSchemaAttr;
    }
}
