// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredCDATASectionImpl extends CDATASectionImpl implements DeferredNode
{
    static final long serialVersionUID = 1983580632355645726L;
    protected transient int fNodeIndex;
    
    DeferredCDATASectionImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument();
        super.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
        this.isIgnorableWhitespace(deferredDocumentImpl.getLastChild(this.fNodeIndex) == 1);
    }
}
