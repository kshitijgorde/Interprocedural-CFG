// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;

public class DeferredDocumentTypeImpl extends DocumentTypeImpl implements DeferredNode
{
    static final long serialVersionUID = -2172579663227313509L;
    protected transient int fNodeIndex;
    
    DeferredDocumentTypeImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        this.needsSyncData(true);
        this.needsSyncChildren(true);
    }
    
    public int getNodeIndex() {
        return this.fNodeIndex;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        super.publicID = ownerDocument.getNodeValue(this.fNodeIndex);
        super.systemID = ownerDocument.getNodeURI(this.fNodeIndex);
        final int extraDataIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        super.internalSubset = ownerDocument.getNodeValue(extraDataIndex);
    }
    
    protected void synchronizeChildren() {
        final boolean orig = this.ownerDocument().getMutationEvents();
        this.ownerDocument().setMutationEvents(false);
        this.needsSyncChildren(false);
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)super.ownerDocument;
        super.entities = new NamedNodeMapImpl(this);
        super.notations = new NamedNodeMapImpl(this);
        super.elements = new NamedNodeMapImpl(this);
        DeferredNode last = null;
        for (int index = ownerDocument.getLastChild(this.fNodeIndex); index != -1; index = ownerDocument.getPrevSibling(index)) {
            final DeferredNode node = ownerDocument.getNodeObject(index);
            final int type = node.getNodeType();
            switch (type) {
                case 6: {
                    super.entities.setNamedItem(node);
                    continue;
                }
                case 12: {
                    super.notations.setNamedItem(node);
                    continue;
                }
                case -1: {
                    super.elements.setNamedItem(node);
                    continue;
                }
                case 1: {
                    if (((DocumentImpl)this.getOwnerDocument()).allowGrammarAccess) {
                        this.insertBefore(node, last);
                        last = node;
                        continue;
                    }
                    break;
                }
            }
            System.out.println("DeferredDocumentTypeImpl#synchronizeInfo: node.getNodeType() = " + node.getNodeType() + ", class = " + node.getClass().getName());
        }
        this.ownerDocument().setMutationEvents(orig);
        this.setReadOnly(true, false);
    }
}
