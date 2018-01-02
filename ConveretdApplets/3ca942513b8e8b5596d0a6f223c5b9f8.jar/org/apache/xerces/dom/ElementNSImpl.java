// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.util.URI;
import org.w3c.dom.Attr;
import org.apache.xerces.xni.NamespaceContext;
import org.w3c.dom.DOMException;
import org.apache.xerces.xs.XSTypeDefinition;

public class ElementNSImpl extends ElementImpl
{
    static final long serialVersionUID = -9142310625494392642L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String namespaceURI;
    protected String localName;
    transient XSTypeDefinition type;
    
    protected ElementNSImpl() {
    }
    
    protected ElementNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s, final String s2) throws DOMException {
        super(coreDocumentImpl, s2);
        this.setName(s, s2);
    }
    
    private void setName(final String namespaceURI, final String localName) {
        this.namespaceURI = namespaceURI;
        if (namespaceURI != null) {
            this.namespaceURI = ((namespaceURI.length() == 0) ? null : namespaceURI);
        }
        if (localName == null) {
            throw new DOMException((short)14, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null));
        }
        final int index = localName.indexOf(58);
        final int lastIndex = localName.lastIndexOf(58);
        super.ownerDocument.checkNamespaceWF(localName, index, lastIndex);
        if (index < 0) {
            this.localName = localName;
            if (super.ownerDocument.errorChecking) {
                super.ownerDocument.checkQName(null, this.localName);
                if ((localName.equals("xmlns") && (namespaceURI == null || !namespaceURI.equals(NamespaceContext.XMLNS_URI))) || (namespaceURI != null && namespaceURI.equals(NamespaceContext.XMLNS_URI) && !localName.equals("xmlns"))) {
                    throw new DOMException((short)14, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null));
                }
            }
        }
        else {
            final String substring = localName.substring(0, index);
            this.localName = localName.substring(lastIndex + 1);
            if (super.ownerDocument.errorChecking) {
                if (namespaceURI == null || (substring.equals("xml") && !namespaceURI.equals(NamespaceContext.XML_URI))) {
                    throw new DOMException((short)14, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null));
                }
                super.ownerDocument.checkQName(substring, this.localName);
                super.ownerDocument.checkDOMNSErr(substring, namespaceURI);
            }
        }
    }
    
    protected ElementNSImpl(final CoreDocumentImpl coreDocumentImpl, final String namespaceURI, final String s, final String localName) throws DOMException {
        super(coreDocumentImpl, s);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }
    
    protected ElementNSImpl(final CoreDocumentImpl coreDocumentImpl, final String s) {
        super(coreDocumentImpl, s);
    }
    
    void rename(final String s, final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.setName(s, super.name = name);
        this.reconcileDefaultAttributes();
    }
    
    protected void setValues(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String name, final String localName) {
        super.firstChild = null;
        super.previousSibling = null;
        super.nextSibling = null;
        super.fNodeListCache = null;
        super.attributes = null;
        super.flags = 0;
        this.setOwnerDocument(ownerDocument);
        this.needsSyncData(true);
        super.name = name;
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
    
    public void setPrefix(final String s) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (s != null && s.length() != 0) {
                if (!CoreDocumentImpl.isXMLName(s, super.ownerDocument.isXML11Version())) {
                    throw new DOMException((short)5, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null));
                }
                if (this.namespaceURI == null || s.indexOf(58) >= 0) {
                    throw new DOMException((short)14, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null));
                }
                if (s.equals("xml") && !this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                    throw new DOMException((short)14, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null));
                }
            }
        }
        if (s != null && s.length() != 0) {
            super.name = s + ":" + this.localName;
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
        if (super.attributes != null) {
            final Attr attr = (Attr)super.attributes.getNamedItemNS("http://www.w3.org/XML/1998/namespace", "base");
            if (attr != null) {
                String s = attr.getNodeValue();
                if (s.length() != 0) {
                    try {
                        s = new URI(s).toString();
                    }
                    catch (URI.MalformedURIException ex) {
                        final NodeImpl nodeImpl = (this.parentNode() != null) ? this.parentNode() : super.ownerNode;
                        final String s2 = (nodeImpl != null) ? nodeImpl.getBaseURI() : null;
                        if (s2 != null) {
                            String string;
                            try {
                                string = new URI(new URI(s2), s).toString();
                            }
                            catch (URI.MalformedURIException ex2) {
                                return null;
                            }
                            return string;
                        }
                        return null;
                    }
                    return s;
                }
            }
        }
        final String s3 = (this.parentNode() != null) ? this.parentNode().getBaseURI() : null;
        if (s3 != null) {
            try {
                return new URI(s3).toString();
            }
            catch (URI.MalformedURIException ex3) {
                return null;
            }
        }
        final String s4 = (super.ownerNode != null) ? super.ownerNode.getBaseURI() : null;
        if (s4 != null) {
            try {
                return new URI(s4).toString();
            }
            catch (URI.MalformedURIException ex4) {
                return null;
            }
        }
        return null;
    }
    
    public String getTypeName() {
        if (this.type == null) {
            return null;
        }
        if (this.type instanceof XSSimpleTypeDefinition) {
            return ((XSSimpleTypeDecl)this.type).getTypeName();
        }
        return ((XSComplexTypeDecl)this.type).getTypeName();
    }
    
    public String getTypeNamespace() {
        if (this.type != null) {
            return this.type.getNamespace();
        }
        return null;
    }
    
    public boolean isDerivedFrom(final String s, final String s2, final int n) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.type == null) {
            return false;
        }
        if (this.type instanceof XSSimpleTypeDefinition) {
            return ((XSSimpleTypeDecl)this.type).isDOMDerivedFrom(s, s2, n);
        }
        return ((XSComplexTypeDecl)this.type).isDOMDerivedFrom(s, s2, n);
    }
    
    public void setType(final XSTypeDefinition type) {
        this.type = type;
    }
}
