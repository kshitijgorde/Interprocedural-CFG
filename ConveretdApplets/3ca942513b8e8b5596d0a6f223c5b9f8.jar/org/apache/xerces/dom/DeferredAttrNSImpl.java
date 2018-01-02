// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public final class DeferredAttrNSImpl extends AttrNSImpl implements DeferredNode
{
    static final long serialVersionUID = 6074924934945957154L;
    protected transient int fNodeIndex;
    
    DeferredAttrNSImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        super.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        final int index = super.name.indexOf(58);
        if (index < 0) {
            super.localName = super.name;
        }
        else {
            super.localName = super.name.substring(index + 1);
        }
        final int nodeExtra = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
        this.isSpecified((nodeExtra & 0x20) != 0x0);
        this.isIdAttribute((nodeExtra & 0x200) != 0x0);
        super.namespaceURI = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
        super.type = deferredDocumentImpl.getTypeInfo(deferredDocumentImpl.getLastChild(this.fNodeIndex));
    }
    
    protected void synchronizeChildren() {
        ((DeferredDocumentImpl)this.ownerDocument()).synchronizeChildren(this, this.fNodeIndex);
    }
}
