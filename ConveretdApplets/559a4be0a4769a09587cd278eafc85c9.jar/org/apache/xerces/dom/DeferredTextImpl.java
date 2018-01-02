// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredTextImpl extends TextImpl implements DeferredNode
{
    static final long serialVersionUID = 2310613872100393425L;
    protected transient int fNodeIndex;
    
    DeferredTextImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
        this.isIgnorableWhitespace(ownerDocument.getNodeExtra(this.fNodeIndex) == 1);
    }
}
