// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredCommentImpl extends CommentImpl implements DeferredNode
{
    static final long serialVersionUID = 6498796371083589338L;
    protected transient int fNodeIndex;
    
    DeferredCommentImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
