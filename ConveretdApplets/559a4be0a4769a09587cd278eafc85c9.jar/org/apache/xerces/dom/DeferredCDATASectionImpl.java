// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredCDATASectionImpl extends CDATASectionImpl implements DeferredNode
{
    static final long serialVersionUID = 1983580632355645726L;
    protected transient int fNodeIndex;
    
    DeferredCDATASectionImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        super.data = ownerDocument.getNodeValueString(this.fNodeIndex);
    }
}
