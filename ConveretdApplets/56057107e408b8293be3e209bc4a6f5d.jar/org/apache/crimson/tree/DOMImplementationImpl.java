// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

public class DOMImplementationImpl implements DOMImplementation
{
    private static DOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String feature, final String version) {
        return hasFeature0(feature, version);
    }
    
    static boolean hasFeature0(final String feature, final String version) {
        return "XML".equalsIgnoreCase(feature) && (version == null || "2.0".equals(version) || "1.0".equals(version));
    }
    
    public DocumentType createDocumentType(final String qualifiedName, final String publicId, final String systemId) {
        return new Doctype(qualifiedName, publicId, systemId, null);
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        final Document doc = new XmlDocument();
        if (doctype != null) {
            doc.appendChild(doctype);
        }
        final Element docElement = doc.createElementNS(namespaceURI, qualifiedName);
        doc.appendChild(docElement);
        return doc;
    }
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
}
