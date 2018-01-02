// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLQuoteElement;

public class HTMLQuoteElementImpl extends HTMLElementImpl implements HTMLQuoteElement
{
    public String getCite() {
        return this.getAttribute("cite");
    }
    
    public void setCite(final String s) {
        this.setAttribute("cite", s);
    }
    
    public HTMLQuoteElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s);
    }
}
