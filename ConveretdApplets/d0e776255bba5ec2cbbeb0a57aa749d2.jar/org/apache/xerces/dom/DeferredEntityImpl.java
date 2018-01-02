// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.utils.StringPool;

public class DeferredEntityImpl extends EntityImpl implements DeferredNode
{
    static final long serialVersionUID = 4760180431078941638L;
    protected transient int fNodeIndex;
    
    DeferredEntityImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        super.name = deferredDocumentImpl.getNodeNameString(this.fNodeIndex);
        final StringPool stringPool = deferredDocumentImpl.getStringPool();
        final int nodeValue = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
        deferredDocumentImpl.getNodeType(nodeValue);
        super.publicId = stringPool.toString(deferredDocumentImpl.getNodeName(nodeValue));
        super.notationName = stringPool.toString(deferredDocumentImpl.getLastChild(nodeValue));
        final int nodeValue2 = deferredDocumentImpl.getNodeValue(nodeValue);
        super.systemId = stringPool.toString(deferredDocumentImpl.getNodeName(nodeValue2));
        super.version = stringPool.toString(deferredDocumentImpl.getNodeValue(nodeValue2));
        super.encoding = stringPool.toString(deferredDocumentImpl.getLastChild(nodeValue2));
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
        this.isReadOnly(false);
        this.synchronizeChildren(this.fNodeIndex);
        this.setReadOnly(true, true);
    }
}
