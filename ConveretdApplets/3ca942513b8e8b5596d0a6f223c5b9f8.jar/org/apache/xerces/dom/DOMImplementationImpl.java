// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

public class DOMImplementationImpl extends CoreDOMImplementationImpl implements DOMImplementation
{
    static DOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(String substring, final String s) {
        final boolean hasFeature = super.hasFeature(substring, s);
        if (!hasFeature) {
            final boolean b = s == null || s.length() == 0;
            if (substring.startsWith("+")) {
                substring = substring.substring(1);
            }
            return (substring.equalsIgnoreCase("Events") && (b || s.equals("2.0"))) || (substring.equalsIgnoreCase("MutationEvents") && (b || s.equals("2.0"))) || (substring.equalsIgnoreCase("Traversal") && (b || s.equals("2.0"))) || (substring.equalsIgnoreCase("Range") && (b || s.equals("2.0"))) || (substring.equalsIgnoreCase("MutationEvents") && (b || s.equals("2.0")));
        }
        return hasFeature;
    }
    
    public Document createDocument(final String s, final String s2, final DocumentType documentType) throws DOMException {
        if (s == null && s2 == null && documentType == null) {
            return new DocumentImpl();
        }
        if (documentType != null && documentType.getOwnerDocument() != null) {
            throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
        }
        final DocumentImpl documentImpl = new DocumentImpl(documentType);
        documentImpl.appendChild(documentImpl.createElementNS(s, s2));
        return documentImpl;
    }
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
}
