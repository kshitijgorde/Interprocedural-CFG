// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredEntityReferenceImpl extends EntityReferenceImpl implements DeferredNode
{
    static final long serialVersionUID = 390319091370032223L;
    protected transient int fNodeIndex;
    
    DeferredEntityReferenceImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        super.baseURI = ownerDocument.getNodeValue(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
        this.isReadOnly(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
        this.setReadOnly(true, true);
    }
}
