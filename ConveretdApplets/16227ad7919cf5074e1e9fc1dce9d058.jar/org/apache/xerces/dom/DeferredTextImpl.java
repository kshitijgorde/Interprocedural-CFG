// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public class DeferredTextImpl extends TextImpl implements DeferredNode
{
    static final long serialVersionUID = 2310613872100393425L;
    protected transient int fNodeIndex;
    
    DeferredTextImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        super.syncData = true;
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        super.syncData = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.value = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
        final int parentNode;
        if (this.getNodeType() == 3 && (parentNode = deferredDocumentImpl.getParentNode(this.fNodeIndex)) != -1 && deferredDocumentImpl.getNodeType(parentNode) == 1) {
            int n = deferredDocumentImpl.getRealNextSibling(this.fNodeIndex);
            short n2 = deferredDocumentImpl.getNodeType(n);
            if (n != -1 && n2 == 3) {
                final StringBuffer sb = new StringBuffer(super.value);
                while (n != -1 && n2 == 3) {
                    sb.append(deferredDocumentImpl.getNodeValueString(n));
                    n = deferredDocumentImpl.getRealNextSibling(n);
                    n2 = deferredDocumentImpl.getNodeType(n);
                }
                super.value = sb.toString();
            }
        }
        super.ignorableWhitespace = (deferredDocumentImpl.getFirstChild(this.fNodeIndex) == 1);
    }
}
