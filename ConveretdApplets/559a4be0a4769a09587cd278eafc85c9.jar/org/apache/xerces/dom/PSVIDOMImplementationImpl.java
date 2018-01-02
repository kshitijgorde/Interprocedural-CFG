// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

public class PSVIDOMImplementationImpl extends CoreDOMImplementationImpl
{
    static PSVIDOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return PSVIDOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String feature, final String version) {
        return super.hasFeature(feature, version) || feature.equalsIgnoreCase("PSVI");
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        if (doctype != null && doctype.getOwnerDocument() != null) {
            throw new DOMException((short)4, "DOM005 Wrong document");
        }
        final DocumentImpl doc = new PSVIDocumentImpl(doctype);
        final Element e = doc.createElementNS(namespaceURI, qualifiedName);
        doc.appendChild(e);
        return doc;
    }
    
    static {
        PSVIDOMImplementationImpl.singleton = new PSVIDOMImplementationImpl();
    }
}
