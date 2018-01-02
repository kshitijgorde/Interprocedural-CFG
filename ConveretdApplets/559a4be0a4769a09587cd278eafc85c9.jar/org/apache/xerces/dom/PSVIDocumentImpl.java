// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentType;

public class PSVIDocumentImpl extends DocumentImpl
{
    public PSVIDocumentImpl() {
    }
    
    public PSVIDocumentImpl(final DocumentType doctype) {
        super(doctype);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        return new PSVIElementNSImpl(this, namespaceURI, qualifiedName);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName, final String localpart) throws DOMException {
        return new PSVIElementNSImpl(this, namespaceURI, qualifiedName, localpart);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        return new PSVIAttrNSImpl(this, namespaceURI, qualifiedName);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName, final String localName) throws DOMException {
        return new PSVIAttrNSImpl(this, namespaceURI, qualifiedName, localName);
    }
}
