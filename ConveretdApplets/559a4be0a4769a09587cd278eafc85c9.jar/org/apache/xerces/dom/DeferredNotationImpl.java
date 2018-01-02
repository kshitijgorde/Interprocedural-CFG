// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredNotationImpl extends NotationImpl implements DeferredNode
{
    static final long serialVersionUID = 5705337172887990848L;
    protected transient int fNodeIndex;
    
    DeferredNotationImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        ownerDocument.getNodeType(this.fNodeIndex);
        super.publicId = ownerDocument.getNodeValue(this.fNodeIndex);
        super.systemId = ownerDocument.getNodeURI(this.fNodeIndex);
        final int extraDataIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        ownerDocument.getNodeType(extraDataIndex);
        super.baseURI = ownerDocument.getNodeName(extraDataIndex);
    }
}
