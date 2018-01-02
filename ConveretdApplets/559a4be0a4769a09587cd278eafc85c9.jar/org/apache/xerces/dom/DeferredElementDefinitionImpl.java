// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;

public class DeferredElementDefinitionImpl extends ElementDefinitionImpl implements DeferredNode
{
    static final long serialVersionUID = 6703238199538041591L;
    protected transient int fNodeIndex;
    
    DeferredElementDefinitionImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncData(true);
        this.needsSyncChildren(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        final boolean orig = super.ownerDocument.getMutationEvents();
        super.ownerDocument.setMutationEvents(false);
        this.needsSyncChildren(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.attributes = new NamedNodeMapImpl(ownerDocument);
        for (int nodeIndex = ownerDocument.getLastChild(this.fNodeIndex); nodeIndex != -1; nodeIndex = ownerDocument.getPrevSibling(nodeIndex)) {
            final Node attr = ownerDocument.getNodeObject(nodeIndex);
            super.attributes.setNamedItem(attr);
        }
        ownerDocument.setMutationEvents(orig);
    }
}
