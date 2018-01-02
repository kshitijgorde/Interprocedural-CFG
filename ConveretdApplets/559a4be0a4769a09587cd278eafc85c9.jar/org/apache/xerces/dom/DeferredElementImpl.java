// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DeferredElementImpl extends ElementImpl implements DeferredNode
{
    static final long serialVersionUID = -7670981133940934842L;
    protected transient int fNodeIndex;
    
    DeferredElementImpl(final DeferredDocumentImpl ownerDoc, final int nodeIndex) {
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
        this.setupDefaultAttributes();
        int index = ownerDocument.getNodeExtra(this.fNodeIndex);
        if (index != -1) {
            final NamedNodeMap attrs = this.getAttributes();
            do {
                final NodeImpl attr = (NodeImpl)ownerDocument.getNodeObject(index);
                attrs.setNamedItem(attr);
                index = ownerDocument.getPrevSibling(index);
            } while (index != -1);
        }
        ownerDocument.mutationEvents = orig;
    }
    
    protected final void synchronizeChildren() {
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
    }
}
