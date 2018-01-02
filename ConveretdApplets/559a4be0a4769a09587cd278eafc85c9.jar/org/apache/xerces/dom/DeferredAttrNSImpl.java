// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

public final class DeferredAttrNSImpl extends AttrNSImpl implements DeferredNode
{
    static final long serialVersionUID = 6074924934945957154L;
    protected transient int fNodeIndex;
    
    DeferredAttrNSImpl(final DeferredDocumentImpl ownerDocument, final int nodeIndex) {
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
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        super.name = ownerDocument.getNodeName(this.fNodeIndex);
        final int index = super.name.indexOf(58);
        String prefix;
        if (index < 0) {
            prefix = null;
            super.localName = super.name;
        }
        else {
            prefix = super.name.substring(0, index);
            super.localName = super.name.substring(index + 1);
        }
        final int extra = ownerDocument.getNodeExtra(this.fNodeIndex);
        this.isSpecified((extra & 0x20) != 0x0);
        this.isIdAttribute((extra & 0x200) != 0x0);
        super.namespaceURI = ownerDocument.getNodeURI(this.fNodeIndex);
        if (super.namespaceURI != null && super.namespaceURI.length() == 0) {
            super.namespaceURI = null;
        }
        if (super.namespaceURI == null) {
            if (prefix != null) {
                if (prefix.equals("xmlns")) {
                    super.namespaceURI = "http://www.w3.org/2000/xmlns/";
                }
            }
            else if (super.name.equals("xmlns")) {
                super.namespaceURI = "http://www.w3.org/2000/xmlns/";
            }
        }
    }
    
    protected void synchronizeChildren() {
        final DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl)this.ownerDocument();
        ownerDocument.synchronizeChildren(this, this.fNodeIndex);
    }
}
