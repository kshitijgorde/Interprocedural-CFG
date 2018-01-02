// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;

public class DeferredElementDefinitionImpl extends ElementDefinitionImpl implements DeferredNode
{
    static final long serialVersionUID = 6703238199538041591L;
    protected transient int fNodeIndex;
    
    DeferredElementDefinitionImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncData(true);
        this.needsSyncChildren(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        super.name = ((DeferredDocumentImpl)super.ownerDocument).getNodeNameString(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        final boolean mutationEvents = super.ownerDocument.mutationEvents;
        this.needsSyncChildren(super.ownerDocument.mutationEvents = false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.attributes = new NamedNodeMapImpl(deferredDocumentImpl);
        for (int i = deferredDocumentImpl.getLastChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getPrevSibling(i)) {
            super.attributes.setNamedItem(deferredDocumentImpl.getNodeObject(i));
        }
        deferredDocumentImpl.mutationEvents = mutationEvents;
    }
}
