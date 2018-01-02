// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLBRElement;

public class HTMLBRElementImpl extends HTMLElementImpl implements HTMLBRElement
{
    public String getClear() {
        return this.capitalize(this.getAttribute("clear"));
    }
    
    public void setClear(final String s) {
        this.setAttribute("clear", s);
    }
    
    public HTMLBRElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s);
    }
}
