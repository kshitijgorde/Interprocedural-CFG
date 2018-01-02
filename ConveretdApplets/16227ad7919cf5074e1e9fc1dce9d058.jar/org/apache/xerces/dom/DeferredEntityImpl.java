// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.apache.xerces.utils.StringPool;

public class DeferredEntityImpl extends EntityImpl implements DeferredNode
{
    static final long serialVersionUID = 4760180431078941638L;
    protected transient int fNodeIndex;
    
    DeferredEntityImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        super.publicId = stringPool.toString(deferredDocumentImpl.getFirstChild(nodeValue));
        super.systemId = stringPool.toString(deferredDocumentImpl.getLastChild(nodeValue));
        super.notationName = stringPool.toString(deferredDocumentImpl.getPreviousSibling(nodeValue));
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)super.ownerDocument;
        for (int i = deferredDocumentImpl.getFirstChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getNextSibling(i)) {
            this.appendChild(deferredDocumentImpl.getNodeObject(i));
        }
    }
}
