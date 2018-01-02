// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLDOMImplementation;
import org.apache.xerces.dom.DOMImplementationImpl;

public class HTMLDOMImplementationImpl extends DOMImplementationImpl implements HTMLDOMImplementation
{
    private static HTMLDOMImplementation _instance;
    
    public final HTMLDocument createHTMLDocument(final String title) throws DOMException {
        if (title == null) {
            throw new NullPointerException("HTM014 Argument 'title' is null.");
        }
        final HTMLDocumentImpl htmlDocumentImpl = new HTMLDocumentImpl();
        htmlDocumentImpl.setTitle(title);
        return htmlDocumentImpl;
    }
    
    public static HTMLDOMImplementation getHTMLDOMImplementation() {
        return HTMLDOMImplementationImpl._instance;
    }
    
    static {
        HTMLDOMImplementationImpl._instance = new HTMLDOMImplementationImpl();
    }
}
