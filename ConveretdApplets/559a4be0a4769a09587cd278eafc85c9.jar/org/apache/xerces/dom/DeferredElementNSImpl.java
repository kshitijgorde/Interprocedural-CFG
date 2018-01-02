// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DeferredElementNSImpl extends ElementNSImpl implements DeferredNode
{
    static final long serialVersionUID = -5001885145370927385L;
    protected transient int fNodeIndex;
    
    DeferredElementNSImpl(final DeferredDocumentImpl ownerDoc, final int nodeIndex) {
        super(ownerDoc, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncChildren(true);
    }
    
    public final int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected final void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        final boolean orig = ownerDocument.mutationEvents;
        ownerDocument.mutationEvents = false;
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        final int index = super.name.indexOf(58);
        if (index < 0) {
            super.localName = super.name;
        }
        else {
            super.localName = super.name.substring(index + 1);
        }
        super.namespaceURI = ownerDocument.getNodeURI(this.fNodeIndex);
        if (super.namespaceURI != null && super.namespaceURI.length() == 0) {
            super.namespaceURI = null;
        }
        this.setupDefaultAttributes();
        int attrIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        if (attrIndex != -1) {
            final NamedNodeMap attrs = this.getAttributes();
            do {
                final NodeImpl attr = (NodeImpl)ownerDocument.getNodeObject(attrIndex);
                attrs.setNamedItem(attr);
                attrIndex = ownerDocument.getPrevSibling(attrIndex);
            } while (attrIndex != -1);
        }
        ownerDocument.mutationEvents = orig;
    }
    
    protected final void synchronizeChildren() {
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
    }
}
