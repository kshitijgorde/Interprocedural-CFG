// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

public class DOMImplementationImpl implements DOMImplementation
{
    static DOMImplementationImpl singleton;
    
    public boolean hasFeature(final String s, final String s2) {
        return (s.equalsIgnoreCase("XML") && s2.equals("1.0")) || s2.equals("2.0");
    }
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public DocumentType createDocumentType(final String s, final String s2, final String s3) {
        if (!DocumentImpl.isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new DocumentTypeImpl(null, s, s2, s3);
    }
    
    public Document createDocument(final String s, final String s2, final DocumentType documentType) throws DOMException {
        if (documentType.getOwnerDocument() != null) {
            throw new DOMExceptionImpl((short)4, "WRONG_DOCUMENT_ERR");
        }
        final DocumentImpl documentImpl = new DocumentImpl(documentType);
        documentImpl.appendChild(documentImpl.createElementNS(s, s2));
        return documentImpl;
    }
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
}
