// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.apache.xerces.xni.NamespaceContext;

public class AttrNSImpl extends AttrImpl
{
    static final long serialVersionUID = -781906615369795414L;
    static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String namespaceURI;
    protected String localName;
    
    public AttrNSImpl() {
    }
    
    protected AttrNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName) {
        super(ownerDocument, qualifiedName);
        this.setName(namespaceURI, qualifiedName);
    }
    
    private void setName(final String namespaceURI, final String qname) {
        this.namespaceURI = namespaceURI;
        if (namespaceURI != null) {
            this.namespaceURI = ((namespaceURI.length() == 0) ? null : namespaceURI.intern());
        }
        final int colon1 = qname.indexOf(58);
        final int colon2 = qname.lastIndexOf(58);
        this.ownerDocument().checkNamespaceWF(qname, colon1, colon2);
        if (colon1 < 0) {
            this.localName = qname;
            this.ownerDocument().checkQName(null, this.localName);
            if (this.ownerDocument().errorChecking && ((qname.equals("xmlns") && (namespaceURI == null || namespaceURI != NamespaceContext.XMLNS_URI)) || (namespaceURI == NamespaceContext.XMLNS_URI && !qname.equals("xmlns")))) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                throw new DOMException((short)14, msg);
            }
        }
        else {
            final String prefix = qname.substring(0, colon1);
            this.localName = qname.substring(colon2 + 1);
            this.ownerDocument().checkQName(prefix, this.localName);
            this.ownerDocument().checkDOMNSErr(prefix, namespaceURI);
        }
    }
    
    public AttrNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) {
        super(ownerDocument, qualifiedName);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }
    
    protected AttrNSImpl(final CoreDocumentImpl ownerDocument, final String value) {
        super(ownerDocument, value);
    }
    
    void rename(final String namespaceURI, final String qualifiedName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.setName(namespaceURI, super.name = qualifiedName);
    }
    
    public void setValues(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) {
        AttrImpl.textNode = null;
        super.flags = 0;
        this.isSpecified(true);
        this.hasStringValue(true);
        super.setOwnerDocument(ownerDocument);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
        super.name = qualifiedName;
        super.value = null;
    }
    
    public String getNamespaceURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.namespaceURI;
    }
    
    public String getPrefix() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int index = super.name.indexOf(58);
        return (index < 0) ? null : super.name.substring(0, index);
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.ownerDocument().errorChecking) {
            if (this.isReadOnly()) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg);
            }
            if (prefix != null && prefix.length() != 0) {
                if (!CoreDocumentImpl.isXMLName(prefix)) {
                    final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                    throw new DOMException((short)5, msg);
                }
                if (this.namespaceURI == null || prefix.indexOf(58) >= 0) {
                    final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                    throw new DOMException((short)14, msg);
                }
                if (prefix.equals("xmlns")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                        throw new DOMException((short)14, msg);
                    }
                }
                else if (prefix.equals("xml")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                        throw new DOMException((short)14, msg);
                    }
                }
                else if (super.name.equals("xmlns")) {
                    final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
                    throw new DOMException((short)14, msg);
                }
            }
        }
        if (prefix != null && prefix.length() != 0) {
            super.name = prefix + ":" + this.localName;
        }
        else {
            super.name = this.localName;
        }
    }
    
    public String getLocalName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.localName;
    }
}
