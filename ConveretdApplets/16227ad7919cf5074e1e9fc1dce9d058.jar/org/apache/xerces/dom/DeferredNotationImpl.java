// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.utils.StringPool;

public class DeferredNotationImpl extends NotationImpl implements DeferredNode
{
    static final long serialVersionUID = 5705337172887990848L;
    protected transient int fNodeIndex;
    
    DeferredNotationImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = fNodeIndex;
        super.syncData = true;
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
    }
}
