// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;

public class DeferredDocumentTypeImpl extends DocumentTypeImpl implements DeferredNode
{
    static final long serialVersionUID = -2172579663227313509L;
    protected transient int fNodeIndex;
    
    DeferredDocumentTypeImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        this.needsSyncData(true);
        this.needsSyncChildren(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        super.publicID = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
        super.systemID = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
        super.internalSubset = deferredDocumentImpl.getNodeValue(deferredDocumentImpl.getNodeExtra(this.fNodeIndex));
    }
    
    protected void synchronizeChildren() {
        final boolean mutationEvents = this.ownerDocument().getMutationEvents();
        this.ownerDocument().setMutationEvents(false);
        this.needsSyncChildren(false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.entities = new NamedNodeMapImpl(this);
        super.notations = new NamedNodeMapImpl(this);
        super.elements = new NamedNodeMapImpl(this);
        Node node = null;
        for (int i = deferredDocumentImpl.getLastChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getPrevSibling(i)) {
            final DeferredNode nodeObject = deferredDocumentImpl.getNodeObject(i);
            switch (nodeObject.getNodeType()) {
                case 6: {
                    super.entities.setNamedItem(nodeObject);
                    continue;
                }
                case 12: {
                    super.notations.setNamedItem(nodeObject);
                    continue;
                }
                case 21: {
                    super.elements.setNamedItem(nodeObject);
                    continue;
                }
                case 1: {
                    if (((DocumentImpl)this.getOwnerDocument()).allowGrammarAccess) {
                        this.insertBefore(nodeObject, node);
                        node = nodeObject;
                        continue;
                    }
                    break;
                }
            }
            System.out.println("DeferredDocumentTypeImpl#synchronizeInfo: node.getNodeType() = " + nodeObject.getNodeType() + ", class = " + nodeObject.getClass().getName());
        }
        this.ownerDocument().setMutationEvents(mutationEvents);
        this.setReadOnly(true, false);
    }
}
