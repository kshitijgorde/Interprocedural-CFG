// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

public class DOMImplementationImpl implements DOMImplementation
{
    static DOMImplementationImpl singleton;
    
    public boolean hasFeature(final String s, final String s2) {
        final boolean b = s2 == null || s2.length() == 0;
        return (s.equalsIgnoreCase("Core") && (b || s2.equals("1.0") || s2.equals("2.0"))) || (s.equalsIgnoreCase("XML") && (b || s2.equals("1.0") || s2.equals("2.0"))) || (s.equalsIgnoreCase("Events") && (b || s2.equals("2.0"))) || (s.equalsIgnoreCase("MutationEvents") && (b || s2.equals("2.0"))) || (s.equalsIgnoreCase("Traversal") && (b || s2.equals("2.0")));
    }
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public DocumentType createDocumentType(final String s, final String s2, final String s3) {
        if (!DocumentImpl.isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        final int index = s.indexOf(58);
        if (index == 0 || index == s.length() - 1) {
            throw new DOMException((short)14, "DOM003 Namespace error");
        }
        return new DocumentTypeImpl(null, s, s2, s3);
    }
    
    public Document createDocument(final String s, final String s2, final DocumentType documentType) throws DOMException {
        if (documentType != null && documentType.getOwnerDocument() != null) {
            throw new DOMException((short)4, "DOM005 Wrong document");
        }
        final DocumentImpl documentImpl = new DocumentImpl(documentType);
        documentImpl.appendChild(documentImpl.createElementNS(s, s2));
        return documentImpl;
    }
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
}
