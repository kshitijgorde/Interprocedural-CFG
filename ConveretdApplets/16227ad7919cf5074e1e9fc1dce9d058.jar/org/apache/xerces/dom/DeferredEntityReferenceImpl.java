// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.Entity;

public class DeferredEntityReferenceImpl extends EntityReferenceImpl implements DeferredNode
{
    static final long serialVersionUID = 390319091370032223L;
    protected transient int fNodeIndex;
    
    DeferredEntityReferenceImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        super.name = ((DeferredDocumentImpl)super.ownerDocument).getNodeNameString(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        final DocumentType doctype = super.ownerDocument.getDoctype();
        boolean b = false;
        if (doctype != null) {
            final NamedNodeMap entities = doctype.getEntities();
            if (entities != null) {
                final Entity entity = (Entity)entities.getNamedItem(this.getNodeName());
                if (entity != null) {
                    b = true;
                    super.readOnly = false;
                    for (Node node = entity.getFirstChild(); node != null; node = node.getNextSibling()) {
                        this.appendChild(node.cloneNode(true));
                    }
                    super.readOnly = true;
                }
            }
        }
        if (!b) {
            final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
            int i = deferredDocumentImpl.getFirstChild(this.fNodeIndex);
            super.readOnly = false;
            while (i != -1) {
                this.appendChild(deferredDocumentImpl.getNodeObject(i));
                i = deferredDocumentImpl.getNextSibling(i);
            }
            super.readOnly = true;
        }
    }
}
