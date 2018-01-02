// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredCommentImpl extends CommentImpl implements DeferredNode
{
    static final long serialVersionUID = 6498796371083589338L;
    protected transient int fNodeIndex;
    
    DeferredCommentImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncData(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        super.data = ((DeferredDocumentImpl)this.ownerDocument()).getNodeValueString(this.fNodeIndex);
    }
}
