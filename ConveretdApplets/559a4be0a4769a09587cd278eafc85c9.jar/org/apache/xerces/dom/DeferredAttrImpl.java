// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public final class DeferredAttrImpl extends AttrImpl implements DeferredNode
{
    static final long serialVersionUID = 6903232312469148636L;
    protected transient int fNodeIndex;
    
    DeferredAttrImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        final int extra = ownerDocument.getNodeExtra(this.fNodeIndex);
        this.isSpecified((extra & 0x20) != 0x0);
        this.isIdAttribute((extra & 0x200) != 0x0);
    }
    
    protected void synchronizeChildren() {
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
    }
}
