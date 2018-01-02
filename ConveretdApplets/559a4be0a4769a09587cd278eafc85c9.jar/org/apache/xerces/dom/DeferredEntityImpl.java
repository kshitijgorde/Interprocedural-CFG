// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredEntityImpl extends EntityImpl implements DeferredNode
{
    static final long serialVersionUID = 4760180431078941638L;
    protected transient int fNodeIndex;
    
    DeferredEntityImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        super.publicId = ownerDocument.getNodeValue(this.fNodeIndex);
        super.systemId = ownerDocument.getNodeURI(this.fNodeIndex);
        final int extraDataIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        ownerDocument.getNodeType(extraDataIndex);
        super.notationName = ownerDocument.getNodeName(extraDataIndex);
        super.version = ownerDocument.getNodeValue(extraDataIndex);
        super.encoding = ownerDocument.getNodeURI(extraDataIndex);
        final int extraIndex2 = ownerDocument.getNodeExtra(extraDataIndex);
        super.baseURI = ownerDocument.getNodeName(extraIndex2);
        super.actualEncoding = ownerDocument.getNodeValue(extraIndex2);
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
        this.isReadOnly(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
        this.setReadOnly(true, true);
    }
}
