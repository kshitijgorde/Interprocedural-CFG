// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.w3c.dom.DOMException;
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
    
    public Document createDocument(final String s, final String s2, final DocumentType documentType) throws DOMException {
        final WMLDocumentImpl wmlDocumentImpl = new WMLDocumentImpl(documentType);
        wmlDocumentImpl.appendChild(wmlDocumentImpl.createElementNS(s, s2));
        return wmlDocumentImpl;
    }
    
    static {
        WMLDOMImplementationImpl.singleton = new WMLDOMImplementationImpl();
    }
}
