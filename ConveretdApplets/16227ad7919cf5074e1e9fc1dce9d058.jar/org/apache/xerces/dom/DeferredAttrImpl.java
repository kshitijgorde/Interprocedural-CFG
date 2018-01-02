// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.utils.StringPool;

public final class DeferredAttrImpl extends AttrImpl implements DeferredNode
{
    static final long serialVersionUID = 8793967374959140933L;
    protected transient int fNodeIndex;
    
    DeferredAttrImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        super.syncData = true;
        super.syncChildren = true;
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        super.syncData = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.name = deferredDocumentImpl.getNodeNameString(this.fNodeIndex);
        super.specified = (deferredDocumentImpl.getNodeValue(this.fNodeIndex) == 1);
        if (deferredDocumentImpl.fNamespacesEnabled) {
            final int nodeName = deferredDocumentImpl.getNodeName(this.fNodeIndex);
            final StringPool fStringPool = deferredDocumentImpl.fStringPool;
            super.prefix = fStringPool.toString(fStringPool.getPrefixForQName(nodeName));
            super.namespaceURI = fStringPool.toString(fStringPool.getURIForQName(nodeName));
            super.localName = fStringPool.toString(fStringPool.getLocalPartForQName(nodeName));
            return;
        }
        super.localName = super.name;
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        NodeImpl nodeImpl = null;
        for (int i = deferredDocumentImpl.getFirstChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getNextSibling(i)) {
            final NodeImpl nodeImpl2 = (NodeImpl)deferredDocumentImpl.getNodeObject(i);
            if (nodeImpl == null) {
                super.firstChild = nodeImpl2;
            }
            else {
                nodeImpl.nextSibling = nodeImpl2;
            }
            nodeImpl2.parentNode = this;
            nodeImpl2.previousSibling = nodeImpl;
            nodeImpl = nodeImpl2;
        }
        if (nodeImpl != null) {
            super.lastChild = nodeImpl;
        }
    }
}
