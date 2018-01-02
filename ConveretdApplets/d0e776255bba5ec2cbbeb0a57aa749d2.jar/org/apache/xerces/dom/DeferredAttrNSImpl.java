// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.utils.StringPool;

public final class DeferredAttrNSImpl extends AttrNSImpl implements DeferredNode
{
    static final long serialVersionUID = 6074924934945957154L;
    protected transient int fNodeIndex;
    
    DeferredAttrNSImpl(final DeferredDocumentImpl deferredDocumentImpl, final int fNodeIndex) {
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
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument();
        final int nodeName = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        final StringPool stringPool = deferredDocumentImpl.getStringPool();
        super.name = stringPool.toString(nodeName);
        final int index = super.name.indexOf(58);
        String substring;
        if (index < 0) {
            substring = null;
            super.localName = super.name;
        }
        else {
            substring = super.name.substring(0, index);
            super.localName = super.name.substring(index + 1);
        }
        this.isSpecified(deferredDocumentImpl.getNodeValue(this.fNodeIndex) == 1);
        super.namespaceURI = stringPool.toString(deferredDocumentImpl.getNodeURI(this.fNodeIndex));
        if (super.namespaceURI == null && super.namespaceURI.length() == 0) {
            if (substring != null) {
                if (substring.equals("xmlns")) {
                    super.namespaceURI = "http://www.w3.org/2000/xmlns/";
                }
            }
            else if (super.name.equals("xmlns")) {
                super.namespaceURI = "http://www.w3.org/2000/xmlns/";
            }
        }
    }
    
    protected void synchronizeChildren() {
        this.synchronizeChildren(this.fNodeIndex);
    }
}
