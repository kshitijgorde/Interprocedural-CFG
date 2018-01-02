// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.apache.xerces.utils.StringPool;
import org.w3c.dom.Node;

public class DeferredElementImpl extends ElementImpl implements DeferredNode
{
    static final long serialVersionUID = 1698024469924430384L;
    protected transient int fNodeIndex;
    
    DeferredElementImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        super.syncData = true;
        super.syncChildren = true;
    }
    
    public final int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected final void synchronizeData() {
        super.syncData = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.name = deferredDocumentImpl.getNodeNameString(this.fNodeIndex);
        if (deferredDocumentImpl.fNamespacesEnabled) {
            final int nodeName = deferredDocumentImpl.getNodeName(this.fNodeIndex);
            final StringPool fStringPool = deferredDocumentImpl.fStringPool;
            super.prefix = fStringPool.toString(fStringPool.getPrefixForQName(nodeName));
            super.namespaceURI = fStringPool.toString(fStringPool.getURIForQName(nodeName));
            super.localName = fStringPool.toString(fStringPool.getLocalPartForQName(nodeName));
        }
        else {
            super.localName = super.name;
        }
        this.setupDefaultAttributes();
        int i = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
        if (i != -1) {
            final NamedNodeMap attributes = this.getAttributes();
            do {
                final NodeImpl namedItem = (NodeImpl)deferredDocumentImpl.getNodeObject(i);
                attributes.setNamedItem(namedItem);
                namedItem.parentNode = this;
                i = deferredDocumentImpl.getNextSibling(i);
            } while (i != -1);
        }
    }
    
    protected final void synchronizeChildren() {
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
