// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import org.apache.wml.WMLDOMImplementation;
import org.apache.xerces.dom.DOMImplementationImpl;

public class WMLDOMImplementationImpl extends DOMImplementationImpl implements WMLDOMImplementation
{
    static DOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return WMLDOMImplementationImpl.singleton;
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        final DocumentImpl doc = new WMLDocumentImpl(doctype);
        final Element e = doc.createElementNS(namespaceURI, qualifiedName);
        doc.appendChild(e);
        return doc;
    }
    
    static {
        WMLDOMImplementationImpl.singleton = new WMLDOMImplementationImpl();
    }
}
