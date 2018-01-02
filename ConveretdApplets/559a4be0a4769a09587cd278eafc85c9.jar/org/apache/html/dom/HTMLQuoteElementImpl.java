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
    
    public void setCite(final String cite) {
        this.setAttribute("cite", cite);
    }
    
    public HTMLQuoteElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
