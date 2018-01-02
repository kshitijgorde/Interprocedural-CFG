// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

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
    
    public boolean hasFeature(final String s, final String s2) {
        return super.hasFeature(s, s2) || s.equalsIgnoreCase("psvi");
    }
    
    public Document createDocument(final String s, final String s2, final DocumentType documentType) throws DOMException {
        if (documentType != null && documentType.getOwnerDocument() != null) {
            throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "WRONG_DOCUMENT_ERR", null));
        }
        final PSVIDocumentImpl psviDocumentImpl = new PSVIDocumentImpl(documentType);
        psviDocumentImpl.appendChild(psviDocumentImpl.createElementNS(s, s2));
        return psviDocumentImpl;
    }
    
    static {
        PSVIDOMImplementationImpl.singleton = new PSVIDOMImplementationImpl();
    }
}
