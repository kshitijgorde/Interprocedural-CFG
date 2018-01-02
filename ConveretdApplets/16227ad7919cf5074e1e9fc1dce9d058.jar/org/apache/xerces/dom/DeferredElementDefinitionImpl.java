// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

public class DeferredElementDefinitionImpl extends ElementDefinitionImpl implements DeferredNode
{
    static final long serialVersionUID = 6703238199538041591L;
    protected transient int fNodeIndex;
    
    DeferredElementDefinitionImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        super.syncData = true;
        super.syncChildren = true;
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        super.syncData = false;
        super.name = ((DeferredDocumentImpl)super.ownerDocument).getNodeNameString(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.attributes = new NamedNodeMapImpl(deferredDocumentImpl, null);
        for (int i = deferredDocumentImpl.getFirstChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getNextSibling(i)) {
            super.attributes.setNamedItem(deferredDocumentImpl.getNodeObject(i));
        }
    }
}
