// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;

public class DeferredEntityReferenceImpl extends EntityReferenceImpl implements DeferredNode
{
    static final long serialVersionUID = 390319091370032223L;
    protected transient int fNodeIndex;
    
    DeferredEntityReferenceImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        super.name = ((DeferredDocumentImpl)super.ownerDocument).getNodeNameString(this.fNodeIndex);
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
        final DocumentType doctype = super.ownerDocument.getDoctype();
        boolean b = false;
        if (doctype != null) {
            final boolean mutationEvents = super.ownerDocument.mutationEvents;
            super.ownerDocument.mutationEvents = false;
            final NamedNodeMap entities = doctype.getEntities();
            if (entities != null) {
                final Entity entity = (Entity)entities.getNamedItem(this.getNodeName());
                if (entity != null) {
                    b = true;
                    final boolean readOnly = this.isReadOnly();
                    this.isReadOnly(false);
                    for (Node node = entity.getFirstChild(); node != null; node = node.getNextSibling()) {
                        this.appendChild(node.cloneNode(true));
                    }
                    if (readOnly) {
                        this.setReadOnly(true, true);
                    }
                }
            }
            super.ownerDocument.mutationEvents = mutationEvents;
        }
        if (!b) {
            this.isReadOnly(false);
            this.synchronizeChildren(this.fNodeIndex);
            this.setReadOnly(true, true);
        }
    }
    
    protected void synchronize() {
    }
}
