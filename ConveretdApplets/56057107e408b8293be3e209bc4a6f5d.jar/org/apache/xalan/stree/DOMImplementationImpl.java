// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import org.apache.xml.utils.UnImplNode;

public class DOMImplementationImpl extends UnImplNode implements DOMImplementation
{
    static DOMImplementationImpl singleton;
    
    static {
        DOMImplementationImpl.singleton = new DOMImplementationImpl();
    }
    
    public Document createDocument(final String namespaceURI, final String qualifiedName, final DocumentType doctype) throws DOMException {
        if (doctype != null && doctype.getOwnerDocument() != null) {
            throw new DOMException((short)4, "DOM005 Wrong document");
        }
        final DocumentImpl doc = new DocumentImpl(doctype);
        return doc;
    }
    
    public DocumentType createDocumentType(final String qualifiedName, final String publicID, final String systemID) {
        return null;
    }
    
    public static DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String feature, final String version) {
        return (feature.equalsIgnoreCase("XML") && (version == null || version.equals("1.0") || version.equals("2.0"))) || (feature.equalsIgnoreCase("Events") && (version == null || version.equals("2.0"))) || (feature.equalsIgnoreCase("MutationEvents") && (version == null || version.equals("2.0"))) || (feature.equalsIgnoreCase("Traversal") && (version == null || version.equals("2.0")));
    }
}
