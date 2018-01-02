// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.util.ObjectFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.apache.xerces.util.XMLChar;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.apache.xerces.impl.RevalidationHandler;
import org.w3c.dom.DOMImplementation;

public class CoreDOMImplementationImpl implements DOMImplementation
{
    RevalidationHandler fDOMRevalidator;
    boolean free;
    static CoreDOMImplementationImpl singleton;
    
    public CoreDOMImplementationImpl() {
        this.fDOMRevalidator = null;
        this.free = true;
    }
    
    public static DOMImplementation getDOMImplementation() {
        return CoreDOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String feature, final String version) {
        final boolean anyVersion = version == null || version.length() == 0;
        return (feature.equalsIgnoreCase("Core") && (anyVersion || version.equals("1.0") || version.equals("2.0"))) || (feature.equalsIgnoreCase("XML") && (anyVersion || version.equals("1.0") || version.equals("2.0"))) || (feature.equalsIgnoreCase("LS-Load") && (anyVersion || version.equals("3.0")));
    }
    
    public DocumentType createDocumentType(final String qualifiedName, final String publicID, final String systemID) {
        this.checkQName(qualifiedName);
        return new DocumentTypeImpl(null, qualifiedName, publicID, systemID);
    }
    
    final void checkQName(final String qname) {
        final int index = qname.indexOf(58);
        final int lastIndex = qname.lastIndexOf(58);
        final int length = qname.length();
        if (index == 0 || index == length - 1 || lastIndex != index) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NAMESPACE_ERR", null);
            throw new DOMException((short)14, msg);
        }
        int start = 0;
        if (index > 0) {
            if (!XMLChar.isNCNameStart(qname.charAt(start))) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                throw new DOMException((short)5, msg2);
            }
            for (int i = 1; i < index; ++i) {
                if (!XMLChar.isNCName(qname.charAt(i))) {
                    final String msg3 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                    throw new DOMException((short)5, msg3);
                }
            }
            start = index + 1;
        }
        if (!XMLChar.isNCNameStart(qname.charAt(start))) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
            throw new DOMException((short)5, msg2);
        }
        for (int i = start + 1; i < length; ++i) {
            if (!XMLChar.isNCName(qname.charAt(i))) {
                final String msg3 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_CHARACTER_ERR", null);
                throw new DOMException((short)5, msg3);
            }
        }
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        if (doctype != null && doctype.getOwnerDocument() != null) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        final CoreDocumentImpl doc = new CoreDocumentImpl(doctype);
        final Element e = doc.createElementNS(namespaceURI, qualifiedName);
        doc.appendChild(e);
        return doc;
    }
    
    public DOMImplementation getInterface(final String feature) {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    synchronized RevalidationHandler getValidator(final String schemaType) {
        if (this.fDOMRevalidator == null) {
            try {
                this.fDOMRevalidator = (RevalidationHandler)ObjectFactory.newInstance("org.apache.xerces.impl.xs.XMLSchemaValidator", ObjectFactory.findClassLoader(), true);
            }
            catch (Exception ex) {}
        }
        while (!this.isFree()) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                try {
                    return (RevalidationHandler)ObjectFactory.newInstance("org.apache.xerces.impl.xs.XMLSchemaValidator", ObjectFactory.findClassLoader(), true);
                }
                catch (Exception exception) {
                    return null;
                }
            }
        }
        this.free = false;
        return this.fDOMRevalidator;
    }
    
    synchronized void releaseValidator(final String schemaType) {
        this.notifyAll();
        this.free = true;
    }
    
    final synchronized boolean isFree() {
        return this.free;
    }
    
    static {
        CoreDOMImplementationImpl.singleton = new CoreDOMImplementationImpl();
    }
}
