// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.apache.xerces.xs.XSTypeDefinition;

public class DeferredElementNSImpl extends ElementNSImpl implements DeferredNode
{
    static final long serialVersionUID = -5001885145370927385L;
    protected transient int fNodeIndex;
    
    DeferredElementNSImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncChildren(true);
    }
    
    public final int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected final void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        final boolean mutationEvents = deferredDocumentImpl.mutationEvents;
        deferredDocumentImpl.mutationEvents = false;
        super.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        final int index = super.name.indexOf(58);
        if (index < 0) {
            super.localName = super.name;
        }
        else {
            super.localName = super.name.substring(index + 1);
        }
        super.namespaceURI = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
        super.type = (XSTypeDefinition)deferredDocumentImpl.getTypeInfo(this.fNodeIndex);
        this.setupDefaultAttributes();
        int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
        if (i != -1) {
            final NamedNodeMap attributes = this.getAttributes();
            do {
                attributes.setNamedItem(deferredDocumentImpl.getNodeObject(i));
                i = deferredDocumentImpl.getPrevSibling(i);
            } while (i != -1);
        }
        deferredDocumentImpl.mutationEvents = mutationEvents;
    }
    
    protected final void synchronizeChildren() {
        ((DeferredDocumentImpl)this.ownerDocument()).synchronizeChildren(this, this.fNodeIndex);
    }
}
