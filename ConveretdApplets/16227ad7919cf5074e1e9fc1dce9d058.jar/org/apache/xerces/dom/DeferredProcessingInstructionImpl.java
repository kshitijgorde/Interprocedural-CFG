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
        super.syncData = true;
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        super.syncData = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.name = deferredDocumentImpl.getNodeNameString(this.fNodeIndex);
        super.value = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
    }
}
