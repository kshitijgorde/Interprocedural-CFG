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

public class DOMImplementationImpl extends CoreDOMImplementationImpl implements DOMImplementation
{
    static DOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String feature, final String version) {
        final boolean anyVersion = version == null || version.length() == 0;
        return (feature.equalsIgnoreCase("Core") && (anyVersion || version.equals("1.0") || version.equals("2.0"))) || (feature.equalsIgnoreCase("XML") && (anyVersion || version.equals("1.0") || version.equals("2.0"))) || (feature.equalsIgnoreCase("Events") && (anyVersion || version.equals("2.0"))) || (feature.equalsIgnoreCase("MutationEvents") && (anyVersion || version.equals("2.0"))) || (feature.equalsIgnoreCase("Traversal") && (anyVersion || version.equals("2.0"))) || (feature.equalsIgnoreCase("Range") && (anyVersion || version.equals("2.0")));
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        if (doctype != null && doctype.getOwnerDocument() != null) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        final DocumentImpl doc = new DocumentImpl(doctype);
        final Element e = doc.createElementNS(namespaceURI, qualifiedName);
        doc.appendChild(e);
        return doc;
    }
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
}
