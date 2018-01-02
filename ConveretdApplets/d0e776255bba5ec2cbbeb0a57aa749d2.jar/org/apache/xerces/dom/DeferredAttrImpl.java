// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public final class DeferredAttrImpl extends AttrImpl implements DeferredNode
{
    static final long serialVersionUID = 6903232312469148636L;
    protected transient int fNodeIndex;
    
    DeferredAttrImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument();
        super.name = deferredDocumentImpl.getStringPool().toString(deferredDocumentImpl.getNodeName(this.fNodeIndex));
        this.isSpecified(deferredDocumentImpl.getNodeValue(this.fNodeIndex) == 1);
    }
    
    protected void synchronizeChildren() {
        this.synchronizeChildren(this.fNodeIndex);
    }
}
