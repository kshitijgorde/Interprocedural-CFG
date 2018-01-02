// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.util.URI;
import org.w3c.dom.Attr;
import org.apache.xerces.xni.NamespaceContext;
import org.w3c.dom.DOMException;

public class ElementNSImpl extends ElementImpl
{
    static final long serialVersionUID = -9142310625494392642L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String namespaceURI;
    protected String localName;
    
    protected ElementNSImpl() {
    }
    
    protected ElementNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName) throws DOMException {
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
            if ((qname.equals("xmlns") && (namespaceURI == null || namespaceURI != NamespaceContext.XMLNS_URI)) || (namespaceURI == NamespaceContext.XMLNS_URI && !qname.equals("xmlns"))) {
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
    
    protected ElementNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) throws DOMException {
        super(ownerDocument, qualifiedName);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }
    
    protected ElementNSImpl(final CoreDocumentImpl ownerDocument, final String value) {
        super(ownerDocument, value);
    }
    
    void rename(final String namespaceURI, final String qualifiedName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.setName(namespaceURI, super.name = qualifiedName);
        this.reconcileDefaultAttributes();
    }
    
    protected void setValues(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localName) {
        super.firstChild = null;
        super.previousSibling = null;
        super.nextSibling = null;
        super.fNodeListCache = null;
        super.attributes = null;
        super.flags = 0;
        this.setOwnerDocument(ownerDocument);
        this.needsSyncData(true);
        super.name = qualifiedName;
        this.localName = localName;
        this.namespaceURI = namespaceURI;
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
                if (prefix.equals("xml") && !this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
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
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final String baseURI = super.ownerNode.getBaseURI();
        if (super.attributes != null) {
            final Attr attrNode = (Attr)super.attributes.getNamedItemNS("http://www.w3.org/XML/1998/namespace", "base");
            if (attrNode != null) {
                String uri = attrNode.getNodeValue();
                if (uri.length() != 0) {
                    try {
                        uri = new URI(new URI(baseURI), uri).toString();
                    }
                    catch (URI.MalformedURIException e) {
                        return null;
                    }
                    return uri;
                }
            }
        }
        return baseURI;
    }
}
