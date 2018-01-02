// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredProcessingInstructionImpl extends ProcessingInstructionImpl implements DeferredNode
{
    static final long serialVersionUID = -4643577954293565388L;
    protected transient int fNodeIndex;
    
    DeferredProcessingInstructionImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument();
        super.target = deferredDocumentImpl.getNodeNameString(this.fNodeIndex);
        super.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
    }
}
