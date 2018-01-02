// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.apache.xerces.utils.StringPool;

public class DeferredDocumentTypeImpl extends DocumentTypeImpl implements DeferredNode
{
    static final long serialVersionUID = -2172579663227313509L;
    protected transient int fNodeIndex;
    
    DeferredDocumentTypeImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        final StringPool stringPool = deferredDocumentImpl.getStringPool();
        final int nodeValue = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
        super.publicID = stringPool.toString(deferredDocumentImpl.getFirstChild(nodeValue));
        super.systemID = stringPool.toString(deferredDocumentImpl.getLastChild(nodeValue));
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        super.entities = new NamedNodeMapImpl(deferredDocumentImpl, null);
        super.notations = new NamedNodeMapImpl(deferredDocumentImpl, null);
        super.elements = new NamedNodeMapImpl(deferredDocumentImpl, null);
        for (int i = deferredDocumentImpl.getFirstChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getNextSibling(i)) {
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
                case -1: {
                    super.elements.setNamedItem(nodeObject);
                    final NamedNodeMap attributes = nodeObject.getAttributes();
                    for (int j = deferredDocumentImpl.getFirstChild(nodeObject.getNodeIndex()); j != -1; j = deferredDocumentImpl.getNextSibling(j)) {
                        attributes.setNamedItem(deferredDocumentImpl.getNodeObject(j));
                    }
                    continue;
                }
                case 1: {
                    if (((DocumentImpl)this.getOwnerDocument()).allowGrammarAccess) {
                        this.appendChild(nodeObject);
                        continue;
                    }
                    break;
                }
            }
            System.out.println("DeferredDocumentTypeImpl#synchronizeInfo: node.getNodeType() = " + nodeObject.getNodeType() + ", class = " + nodeObject.getClass().getName());
        }
    }
}
