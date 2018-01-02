// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredProcessingInstructionImpl extends ProcessingInstructionImpl implements DeferredNode
{
    static final long serialVersionUID = -4643577954293565388L;
    protected transient int fNodeIndex;
    
    DeferredProcessingInstructionImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
        super(ownerDocument, null, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        super.target = ownerDocument.getNodeName(this.fNodeIndex);
        super.data = ownerDocument.getNodeValueString(this.fNodeIndex);
        super.baseURI = ownerDocument.getNodeURI(this.fNodeIndex);
    }
}
